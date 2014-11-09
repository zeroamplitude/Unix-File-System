#include <caca_conio.h>

/*
 * Reading a file
 *
 * Reading a file is basically sequentially reading & displaying the contents of the data blocks
 * indicated by their position from the blocks array of file’s entry and displaying that on stdout’s
 * file descriptor 1. A couple of things to be taken care of:
 *
 * File is assumed to be without holes, i.e. block position of 0 in the blocks array indicates no more data block’s for the file
 * Reading should not go beyond the file size. Special care to be taken while reading the last block with data, as it may be partially valid
 *
 *
 */



uint1_t block[SIMULA_FS_BLOCK_SIZE]; // Shared as global with sfs_write

void sfs_read(int sfs_handle, char *fn)
{
    int i, block_i, already_read, rem_to_read, to_read;
    sfs_file_entry_t fe;

    if ((i = sfs_lookup(sfs_handle, fn, &fe)) == -1)
    {
        printf("File %s doesn't exist\n", fn);
        return;
    }
    already_read = 0;
    rem_to_read = fe.size;
    for (block_i = 0; block_i < SIMULA_FS_DATA_BLOCK_CNT; block_i++)
    {
        if (!fe.blocks[block_i]) break;
        to_read = (rem_to_read >= sb.block_size) ?
                sb.block_size : rem_to_read;
        lseek(sfs_handle, fe.blocks[block_i] * sb.block_size, SEEK_SET);
        read(sfs_handle, block, to_read);
        write(1, block, to_read);
        already_read += to_read;
        rem_to_read -= to_read;
        if (!rem_to_read) break;
    }
}