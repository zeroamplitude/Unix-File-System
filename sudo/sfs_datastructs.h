#include <time.h>

#define NUMBLOCKS 512
#define BLOCKSIZE 128
#define MAX_NUMFILES 64
#define MAX_FILESIZE 8
#define MAXNAME 7


typedef struct {
    unsigned int size_of_disk;
    unsigned int block_size;
    unsigned int free_inode;
    unsigned int root;
} super_block;


typedef struct {
    char file[MAXNAME];
    bool type;
    bool permissions[3];
    time_t cDate;
    time_t aDate;
    time_t mDate;
    unsigned int size;
    unsigned int address;
} i_node;


