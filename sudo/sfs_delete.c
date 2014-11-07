#include <unistd.h>

void sfs_delete(int sfs_handle, char *fn)
{
    int i;
    sfs_file_entry_t fe;

    lseek(sfs_handle, sb.entry_table_block_start * sb.block_size, SEEK_SET);
    for (i = 0; i < sb.entry_count; i++)
    {
        read(sfs_handle, &fe, sizeof(sfs_file_entry_t));
        if (!fe.name[0]) continue;
        if (strcmp(fe.name, fn) == 0) break;
    }
    if (i == sb.entry_count)
    {
        printf(File %s doesnt exist\n, fn);
        return;
    }

    lseek(sfs_handle, -sb.entry_size, SEEK_CUR);

    memset(&fe, 0, sizeof(sfs_file_entry_t));
    write(sfs_handle, &fe, sizeof(sfs_file_entry_t));
}