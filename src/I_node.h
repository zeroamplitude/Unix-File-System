#include <time.h>
#include <unistd.h>
#include <tiff.h>

#define MAX_SIZE_NAME 7


typedef struct {
    char name[MAX_SIZE_NAME];
    int32 magic1;
    int32 uid;
    int32 gid;
    int32 mode;
    inode_addr


};