#include <sys/stat.h>
#include 'sfs_datastructs.h'
#include 'blockio.h'
#include 'blockio.c'

#define SUPERBLOCK 0
#define DISCBITMAP 1

int main(int argc, char **argv)
{
    put_block(SUPERBLOCK, BLKSIZE);
    return 0;
}