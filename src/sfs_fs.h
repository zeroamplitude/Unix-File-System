#include <stddef.h>

#define FILE_NAME_LENGTH     7
#define BLOCK_SIZE          512
#define FIRST_DATA_BLOCK
#define DIRECT_BLOCKS 16
#define MAX_FILES
#define MAX_BLOCKS


/*Define Superblock*/

struct sfs_superblock {
    unsigned int32      magic_num;
    unsigned int32      mod;
    unsigned int32      free_inodes;
    unsigned int32      inode[MAX_FILES];
    unsigned int32      free_blocks;
    unsigned int32      blocks[MAX_BLOCKS];
};

struct sfs_inode {
    unsigned int32      mode;                       // Specifies the type of file
    unsigned int32      num_links;                  // Count of the number of hard links to file
    unsigned int32      ctime;                      // Records the creation date
    unsigned int32      atime;                      // Records the last access time
    unsigned int32      mtime;                      // Records the last time the file was modified
    unsigned int32      size;                       // The size of the file in bytes
    unsigned int32      blocks;                     // The number of blocks of a file
    unsigned int32      address[DIRECT_BLOCKS];      //  Holds the address of the blocks on disk
};

/*
 * Allocation flags
 */

#define FREE_INODE 0
#define USED_INODE 1
#define FREE_BLOCK 0
#define USED_BLOCK 1

