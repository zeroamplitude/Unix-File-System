#include <sys/types.h>
#include <stdint.h>


typedef struct superblock {
    char       name[IDENT_NAME_LENGTH]
    int32_t    magic1;
    uint32_t   block_size;
    off_t      num_blocks;
    off_t      used_blocks;

    int32_t    inode_size;


} superblock;