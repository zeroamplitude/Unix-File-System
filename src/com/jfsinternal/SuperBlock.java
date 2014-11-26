package com.jfsinternal;

import com.jfsmemory.SuperBlkBuffer;

/**
 * SuperBlock:
 *      This class is designed to construct the
 *      superblock jfsystem
 *
 * @author Nicholas De Souza last Modified 24/11/14.
 */
public class SuperBlock implements JfsInternalConstants {

    public SuperBlkBuffer buffer = new SuperBlkBuffer();

    public short diskSize;
    public short blockSize;
    public short iNodeCount;
    public short iNodeTable;
    public short freeBlkHeap;

//    public SuperBlock(short diskSize, short blockSize, short iNodeCount, short freeBlkHeap) {
//        this.diskSize = diskSize;
//        this.blockSize = blockSize;
//        this.iNodeCount = iNodeCount;
//        this.freeBlkHeap = freeBlkHeap;
//    }

    public SuperBlock() {
        diskSize = NUMBLKS;
        blockSize = BLKSIZE;
        iNodeCount = NUMFILES;
        iNodeTable = SIZEOFINODETABLE;
        freeBlkHeap = SIZEOFINODETABLE + 3;
    }




//    public SuperBlock() {
//        diskSize = NUMBLKS;
//        iNodeTsize = SIZEOFINODETABLE;
//        freeBlkHeap =
//
//
//    }

//    public int putSuperBlock(DiskBitmap bitmap) {
//        byte[] encoded = new byte[blockSize] ;
//        int i = 0;
//        int y = 0;
//        while(i < numBlocks) {
//            int j = 3;
//            int sum = 0;
//            for (int z = i; z < 4; z++) {
//                sum += (int) (bitmap.getBit(z) * Math.pow(2, j));
//                j--;
//            }
//            encoded[y] = (byte)sum;
//            i = i + 4;
//            y++;
//        }
//        buffer.updateBuff(encoded);
//        return 0;
//    }
//
//    int getSuperBlock() {
//
//        return 0;
//    }
//
//    int getEmptyBlock(int freeBlkNo) {
//        try {
//            this.freeBlockList.
//        }
//
//        return 0;
//    }
//
//    int releaseBlock(int releaseBlkNo) {
//
//        return 0;
//    }

}
