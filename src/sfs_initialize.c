#include <fcntl.h>
#include <bits/stdio2.h>
#include <unistd.h>
#include <stdio.h>
#include <cpio.h>
#include "sfs_fs.h"


main(int argc, char **argv)
{
    struct sfs_superblock    sb;
    struct sfs_i_node        inode;
    time_t               time;
    off_t                nsectors = MAX_BLOCKS;
    int                  devfd, error, i;
    int                  block_map;
    char                block[BLOCK_SIZE];

    if (argc != 2) {
        fprintf(stderr, "Please specify the device\n");
        exit(1);
    }

    devfd - open(argv[1], O_WRONLY);
    if (devfd < 0) {
        fprintf(stderr, "sfs_inititalize: Failed to open device.\n");
    }
    error = lseek(devfd, (off_t) (nsecotrs * 512), SEEK_SET);
    if (error == -1) {
        fprintf(stderr, "sfsintitialize: Unable to create file-system"
                        " of specified size\n");
        exit(1);
    }
    lseek(devfd, 0, SEEK_SET);

    /* CREATE SUPERBLOCK - set param and write it to the first block */
    sb.magic_num = MAGIC;
    //sb.mod   =
    sb.free_inodes = MAX_FILES;
    sb.free_blocks = MAX_BLOCKS;

    for (i = 4; 1 < MAX_FILES ; i ++) {
        sb.inode[i] = FREE_INODE;
    }

    sb.block[0] = USED_BLOCK; //root directory
    //sb.block[1] = USED_BLOCK; // lost + found dir

    /*
     * Flag remaining blocks as FREE
     */

    for (i = 1; i < MAX_BLOCKS; i++) {
        sb.block[i] = FREE_BLOCK;
    }

    write(devfd, (char *)&sb, sizeof(struct sfs_superblock));
    /*
     * Initialize  the root inode
     */

    time(&tm);
    memset((void *)&inode, 0, sizeof(struct sfs_inode))



}