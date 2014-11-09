include <stddef.h>

#define FILE_NAME_LENGTH     7
#define BLOCK_SIZE          512
#define FIRST_DATA_BLOCK    50
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

struct inode {
    unsigned long i_ino;    /* Inode number */
    atomic_t i_count;       /* Reference count */
    kdev_t i_dev;       /* Filesystem device */
    umode_t i_mode; /* Type/access rights */
    nlink_t i_nlink; /* # of hard links */
    uid_t i_uid; /* User ID */
    gid_t i_gid; /* Group ID */
    kdev_t i_rdev; /* For device files */
    loff_t i_size; /* File size */
    time_t i_atime; /* Access time */
    time_t i_mtime; /* Modification time */
    time_t i_ctime; /* Creation time */
    unsigned long i_blksize; /* Fs block size */
    unsigned long i_blocks; /* # of blocks in file */
    struct inode_operations *i_op; /* Inode operations */
    struct super_block *i_sb; /* Superblock/mount */
    struct vm_area_struct *i_mmap; /* Mapped file areas */
    unsigned char i_update; /* Is inode current? */
    union { /* One per fs type! */
        struct minix_inode_info minix_i;
        struct ext2_inode_info ext2_i;
        ...
        void *generic_ip;
    } u;
}

/*
 * Allocation flags
 */

#define FREE_INODE 0
#define USED_INODE 1
#define FREE_BLOCK 0
#define USED_BLOCK 1

