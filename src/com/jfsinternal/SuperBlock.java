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

    private int numBlocks;
    private int blockSize;
    private int freeBlocks;

    public SuperBlkBuffer buffer = new SuperBlkBuffer();

    public int putSuperBlock(DiskBitmap bitmap) {
        int[] encoded = new int[BlockIO.BLKSIZE] ;
        int i = 0;
        while(i < BlockIO.NUMBLKS) {
            int j = 3;
            int sum = 0;
            for (int z = i; z < 4; z++) {
                sum += (int) (bitmap.getBit(z) * Math.pow(2, j));
                j--;
            }
            encoded[i] = sum;
            i = i + 4;
        }
        buffer.updateBuff(encoded)
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

}
