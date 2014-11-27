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

    public BlockIO disk = new BlockIO();

    public SuperBlkBuffer sbBuffer = new SuperBlkBuffer();

    private short diskSize;
    private short blockSize;
    public short iNodeTable;
    private short iNodeCount;
    private short freeINodeCount;
    private short freeINodeList;
    private short freeBlkCount;
    private short freeBlkList;


    public SuperBlock() {
        diskSize = NUMBLKS;
        blockSize = BLKSIZE;
        iNodeTable = INODETBLSIZE;
        iNodeCount = NUMFILES;
        freeINodeCount = 0;
        freeBlkCount = 0;
        freeBlkList = 0;
        freeINodeList = 0;


    }

    public int writeToDisk() {
        byte[] buffer = new byte[128];
        // Write the total number of blocks to the buffer
        buffer[0] = (byte)(this.diskSize >> 8);
        buffer[1] = (byte)(this.diskSize);

        // Writes the size of each block to the buffer
        buffer[2] = (byte)(this.blockSize >> 8);
        buffer[3] = (byte)(this.blockSize);

        // Writes the total number of iNodes to the buffer
        buffer[4] = (byte)(this.iNodeCount >> 8);
        buffer[5] = (byte)(this.iNodeCount);

        // Writes the size of the iNode table to the buffer
        buffer[6] = (byte)(this.iNodeTable >> 8);
        buffer[7] = (byte)(this.iNodeTable);

        // Writes the location of the first free block
        buffer[8] = (byte)(this.freeBlkList >> 8);
        buffer[9] = (byte)(this.freeBlkList);

        try {
            disk.putBlock(0, buffer);
        } catch (Exception e) {
            System.out.println("Fatal error: Could not write" +
                    "superblock to disk" + e);
            return -1;
        }
        return 0;
    }

//    private short makeFreeINodeList() {
//        for (int i = 0; i < NUMFILES; i++) {
//
//        }
//
//        return freeINodeListHead;
//    }
//
//    private short makeFreeBlockList() {
//
//        return freeBlkListHead;
//    }
//
//    public int getFreeBlock() {
//        return freeBlock;
//    }
//
//    public short getFreeINode() {
//        return freeINode;
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

}
