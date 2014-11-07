#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <unistd.h>

#include "sfs_ds.h"

#define SFS_ENTRY_RATIO 0.10 /* 10% of all blocks */
#define SFS_ENTRY_TABLE_BLOCK_START 1

sfs_super_block_t sb =
        {
                .type = SIMULA_FS_TYPE,
                .block_size = SIMULA_FS_BLOCK_SIZE,
                .entry_size = SIMULA_FS_ENTRY_SIZE,
                .entry_table_block_start = SFS_ENTRY_TABLE_BLOCK_START
        };
sfs_file_entry_t fe; /* All 0's */

void write_super_block(int sfs_handle, sfs_super_block_t *sb)
{
    write(sfs_handle, sb, sizeof(sfs_super_block_t));
}
void clear_file_entries(int sfs_handle, sfs_super_block_t *sb)
{
    int i;

    for (i = 0; i < sb->entry_count; i++)
    {
        write(sfs_handle, &fe, sizeof(fe));
    }
}
void mark_data_blocks(int sfs_handle, sfs_super_block_t *sb)
{
    char c = 0;

    lseek(sfs_handle, sb->partition_size * sb->block_size - 1, SEEK_SET);
    write(sfs_handle, &c, 1); /* To make the file size to partition size */
}

int main(int argc, char *argv[])
{
    int sfs_handle;

    if (argc != 2)
    {
        fprintf(stderr, "Usage: %s <partition size in 512-byte blocks>\n",
                argv[0]);
        return 1;
    }
    sb.partition_size = atoi(argv[1]);
    sb.entry_table_size = sb.partition_size * SFS_ENTRY_RATIO;
    sb.entry_count = sb.entry_table_size * sb.block_size / sb.entry_size;
    sb.data_block_start = SFS_ENTRY_TABLE_BLOCK_START + sb.entry_table_size;

    sfs_handle = creat(SIMULA_DEFAULT_FILE,
            S_IRUSR | S_IWUSR | S_IRGRP | S_IROTH);
    if (sfs_handle == -1)
    {
        perror("No permissions to format");
        return 2;
    }
    write_super_block(sfs_handle, &sb);
    clear_file_entries(sfs_handle, &sb);
    mark_data_blocks(sfs_handle, &sb);
    close(sfs_handle);
    return 0;
}