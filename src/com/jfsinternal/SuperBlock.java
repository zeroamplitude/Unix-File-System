package com.jfsinternal;

import com.jfsmemory.SuperBlkBuffer;

/**
 * SuperBlock:
 *      This class is designed to construct the
 *      superblock of the UNIX-File-System
 *
 * @author Nicholas De Souza
 */
public class SuperBlock {

    public static final int NUMFILES = 64;
    public SuperBlkBuffer buffer = new SuperBlkBuffer();

    private int numBlocks;
    private int blockSize;
    private int freeBlockCount;
    IndirectBlock freeBlockList = new IndirectBlock();

    public SuperBlock() {
        numBlocks = BlockIO.NUMBLKS;
        blockSize = BlockIO.BLKSIZE;
        freeBlockList = getList();
    }



    public int putSuperBlock(DiskBitmap bitmap) {
        byte[] encoded = new byte[BlockIO.BLKSIZE] ;
        int i = 0;
        int y = 0;
        while(i < BlockIO.NUMBLKS) {
            int j = 3;
            int sum = 0;
            for (int z = i; z < 4; z++) {
                sum += (int) (bitmap.getBit(z) * Math.pow(2, j));
                j--;
            }
            encoded[y] = (byte)sum;
            i = i + 4;
            y++;
        }
        buffer.updateBuff(encoded);
        return 0;
    }

    int getSuperBlock() {
        return 0;
    }

    int getEmptyBlock(int freeBlkNo) {
        return 0;
    }

    int releaseBlock(int releaseBlkNo) {
        return 0;
    }

//    public IndirectBlock getList() {
//        freeBlockList.pointer[i] =
//        return freeBlockList;
//    }
}
