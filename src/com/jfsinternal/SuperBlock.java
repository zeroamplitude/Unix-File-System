package com.jfsinternal;

import com.jfsmemory.SuperBlkBuffer;

import java.util.PriorityQueue;

/**
 * SuperBlock:
 *      This class is designed to construct the
 *      superblock of the UNIX-File-System
 *
 * @author Nicholas De Souza
 */
public class SuperBlock {

    public SuperBlkBuffer buffer = new SuperBlkBuffer();

    public static int numBlocks;
    public static int blockSize;
    protected static byte freeBlockCount;
    PriorityQueue freeBlockList;

    public SuperBlock(byte freeBlkCount, PriorityQueue freeblockHeap) {
        numBlocks = BlockIO.NUMBLKS;
        blockSize = BlockIO.BLKSIZE;
        freeBlockCount = 0;
        freeBlockList = new PriorityQueue(freeblockHeap);
    }

    public int putSuperBlock(DiskBitmap bitmap) {
        byte[] encoded = new byte[blockSize] ;
        int i = 0;
        int y = 0;
        while(i < numBlocks) {
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
        try {
            this.freeBlockList.
        }

        return 0;
    }

    int releaseBlock(int releaseBlkNo) {

        return 0;
    }

}
