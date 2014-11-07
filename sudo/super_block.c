/* contains functions related to super_block handling */



// super_blk_buf[128]
short int super_blk_buf[128];

// super_blo


// 1.
// Encodes each four locations (0 adn 1) of disk_bitmap[512]
// into an integer number and puts in super_blk_buf[128]
// 2.
// Gets super-block (blocks 0 and 1) as "short integer bytes"
// Puts blocks 0 / 1 into super_blk_buf[128]
int put_super_blk()
{


}


// 1.
// Gets super-block(block 0 and 1) as "short integer bytes" and
// puts then in the "super_blk_buf[128]
// 2.
// Decodes each integer(<15 & >0) in super)blk_buf[128] int0
// 4 bits and puts them in disk_bitmap[512]
int get_super_blk()
{

}


// 1.
// Seaches the super-block and if it has an empty block, marks
// it as a busy block and returns its block number to the calling
// function.
int get_empty_blk(int *free_blk_no)
{

}



// 1.
// Releases the block as a free block to the system
// this block was already allocated to a file;
// Updates the disk_bitmap[512]