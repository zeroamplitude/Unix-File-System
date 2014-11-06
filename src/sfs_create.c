#include <time.h>
#include <unistd.h>
#include <stdio.h>
#include <string.h>
#include "sfs_ds.h"


void sfs_create(int sfs_handle, char *fn)
{
    int i;
    sfs_file_entry_t fe;

    lseek(sfs_handle, sb.entry_table_block_start * sb.block_size, SEEK_SET);
    for (i = 0; i < sb.entry_count; i++)
    {
        read(sfs_handle, &fe, sizeof(sfs_file_entry_t));
        if (!fe.name[0]) break;
        if (strcmp(fe.name, fn) == 0)
        {
            printf(File %s already exists\n, fn);
            return;
        }
    }
    if (i == sb.entry_count)
    {
        printf(No entries left\n, fn);
        return;
    }

    lseek(sfs_handle, -sb.entry_size, SEEK_CUR);

    strncpy(fe.name, fn, 15);
    fe.name[15] = 0;
    fe.size = 0;
    fe.timestamp = time(NULL);
    fe.perms = 07;
    for (i = 0; i < NUM_DATA_BLOCK; i++)
    {
        fe.blocks[i] = 0;
    }
