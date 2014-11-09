void sfs_write(int sfs_handle, char *fn)
{
    int i, cur_read_i, to_read, cur_read, total_size, block_i, free_i;
    sfs_file_entry_t fe;

    if ((i = sfs_lookup(sfs_handle, fn, &fe)) == -1)
    {
        printf("File %s doesn't exist\n", fn);
        return;
    }
    cur_read_i = 0;
    to_read = sb.block_size;
    total_size = 0;
    block_i = 0;
    while ((cur_read = read(0, block + cur_read_i, to_read)) > 0)
    {
        if (cur_read == to_read)
        {
            /* Write this block */
            if (block_i == SIMULA_FS_DATA_BLOCK_CNT)
                break; /* File size limit */
            if ((free_i = get_data_block(sfs_handle)) == -1)
                break; /* File system full */
            lseek(sfs_handle, free_i * sb.block_size, SEEK_SET);
            write(sfs_handle, block, sb.block_size);
            fe.blocks[block_i] = free_i;
            block_i++;
            total_size += sb.block_size;
            /* Reset various variables */
            cur_read_i = 0;
            to_read = sb.block_size;
        }
        else
        {
            cur_read_i += cur_read;
            to_read -= cur_read;
        }
    }
    if ((cur_read <= 0) && (cur_read_i))
    {
        /* Write this partial block */
        if ((block_i != SIMULA_FS_DATA_BLOCK_CNT) &&
                ((fe.blocks[block_i] = get_data_block(sfs_handle)) != -1))
        {
            lseek(sfs_handle, fe.blocks[block_i] * sb.block_size,
                    SEEK_SET);
            write(sfs_handle, block, cur_read_i);
            total_size += cur_read_i;
        }
    }

    fe.size = total_size;
    fe.timestamp = time(NULL);
    lseek(sfs_handle, sb.entry_table_block_start * sb.block_size +
            i * sb.entry_size, SEEK_SET);
    write(sfs_handle, &fe, sizeof(sfs_file_entry_t));
}