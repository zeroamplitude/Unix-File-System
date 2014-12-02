/*
 * This file is part of JavaFileSystem (jfs).
 *
 *     jfs is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     jfs is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with jfs.  If not, see <http://www.gnu.org/licenses/>.
 *
 * Copyright (c) 2014. Nicholas De Souza, Arun Gireesan, Milan Kornicer, Peter Little. All rights reserved
 */

package com.jfsinternal;

import com.jfsmemory.JfsMemory;

import static java.lang.Math.floor;

/**
 * SuperBlock:
 *      This class is designed to construct the
 *      superblock jfsystem
 *
 * @author Nicholas De Souza last Modified 24/11/14.
 */
public class SuperBlock implements JfsInternalConstants {
    byte[] bitmap = new byte[NUMBLKS];
    byte[] iMap = new byte[BLKSIZE];
    public BlockIO disk = new BlockIO();
    JfsMemory memory = JfsMemory.getInstance();

    short magic;
    private short diskSize;
    private short blockSize;
    public short iNodeTable;
    private short iNodeCount;
    private short freeINodeCount;
    private short freeInodeQueue;
    private short freeBlkCount;
    private short freeBlkList;


    public SuperBlock(short newFlag) {

        byte[] readBuffer = new byte[BLKSIZE];

        try {

            disk.getBlock(0, readBuffer);
            magic = 0;

        } catch (Exception e) {

            System.out.println("Disk read error: Fatal "
                    + "@SuperBlock.readFromDisk() "
                    + e);

            magic = -1;

        }

        magic = 0;

        diskSize = (short) (((short) (readBuffer[0] << 8))
                + (short) (readBuffer[1]));

        blockSize = (short) (((short) (readBuffer[2] << 8))
                + (short) (readBuffer[3]));

        iNodeTable = (short) (((short) (readBuffer[4] << 8))
                + (short) (readBuffer[5]));

        iNodeCount = (short) (((short) (readBuffer[6] << 8))
                + (short) (readBuffer[7]));

        freeINodeCount = (short) (((short) (readBuffer[8] << 8))
                + (short) (readBuffer[9]));

        freeBlkCount = (short) (((short) (readBuffer[10] << 8))
                + (short) (readBuffer[11]));

        freeBlkList = (short) (((short) (readBuffer[12] << 8))
                + (short) (readBuffer[13]));

        freeInodeQueue = (short) (((short) (readBuffer[14] << 8))
                + (short) (readBuffer[15]));

    }

    public SuperBlock() {
        magic = 0;
        diskSize = NUMBLKS;
        blockSize = BLKSIZE;
        iNodeTable = INODETBLSTART;
        iNodeCount = NUMFILES;
        freeINodeCount = NUMFILES;
        freeBlkCount = 0;
        freeBlkList = makeFreeBlkList();
        freeInodeQueue = INODETBLSTART;
    }

    public short getMagic() {
        return magic;
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

            System.out.println("Disk write error:  "
                    + "write block to disk "
                    + e);

            return -1;

        }
        return 0;
    }

    private short makeFreeBlkList() {
        // Creating block buffers for FreeList Head
        byte[] block1 = new byte[BLKSIZE];
        byte[] block2 = new byte[BLKSIZE];
        byte[] block3 = new byte[BLKSIZE];
        byte[] block4 = new byte[BLKSIZE];
        byte[] block5 = new byte[BLKSIZE];
        byte[] block6 = new byte[BLKSIZE];
        byte[] block7 = new byte[BLKSIZE];
        byte[] block8 = new byte[BLKSIZE];

        this.freeBlkCount = 0;
        int i = 0;
        for (int j = 0; j < (BLKSIZE / 2); j++) {

            if (j != 63) {
                try {

                    // Reads the the location of the child block into
                    // buffer starting with child 11 block until 73.
                    block1[i] = (byte) ((SUPERBLKSIZE + j) >> 8);
                    block1[i + 1] = (byte) ((SUPERBLKSIZE + j));
                    this.freeBlkCount += 1;

                    // Reads the location of the child block into
                    // buffer starting with child block 75 until 137.
                    block2[i] = (byte) ((SUPERBLKSIZE + 64 + j) >> 8);
                    block2[i + 1] = (byte) ((SUPERBLKSIZE + 64 + j));
                    this.freeBlkCount += 1;

                    // Reads the location of the child block into
                    // buffer starting with the child block 139 until 201.
                    block3[i] = (byte) ((SUPERBLKSIZE + (64 * 2) + j) >> 8);
                    block3[i + 1] = (byte) ((SUPERBLKSIZE + (64 * 2) + j));
                    this.freeBlkCount += 1;

                    // Reads the location of the child block into
                    // buffer starting with the child block 203 until 265.
                    block4[i] = (byte) ((SUPERBLKSIZE + (64 * 3) + j) >> 8);
                    block4[i + 1] = (byte) ((SUPERBLKSIZE + (64 * 3) + j));
                    this.freeBlkCount += 1;

                    // Reads the location of the child block into
                    // buffer starting with the child block 267 until 329.
                    block5[i] = (byte) ((SUPERBLKSIZE + (64 * 4) + j) >> 8);
                    block5[i + 1] = (byte) ((SUPERBLKSIZE + (64 * 4) + j));
                    this.freeBlkCount += 1;

                    // Reads the location of the child block into
                    // buffer starting with the child block 331 until 393.
                    block6[i] = (byte) ((SUPERBLKSIZE + (64 * 5) + j) >> 8);
                    block6[i + 1] = (byte) ((SUPERBLKSIZE + (64 * 5) + j));
                    this.freeBlkCount += 1;

                    // Reads the location of the child block into
                    // buffer  starting with the child block 395 until 457.
                    block7[i] = (byte) ((SUPERBLKSIZE + (64 * 6) + j) >> 8);
                    block7[i + 1] = (byte) ((SUPERBLKSIZE + (64 * 6) + j));
                    this.freeBlkCount += 1;

                    if (j < 52) {
                        // If the index pointer is less than 52 which represents
                        // the remaining children, then it read the location of
                        // the child block into the buffer starting at location
                        // 459 to 510
                        block8[i] = (byte) ((SUPERBLKSIZE + (64 * 7) + j) >> 8);
                        block8[i + 1] = (byte) ((SUPERBLKSIZE + (64 * 7) + j));
                        this.freeBlkCount += 1;
                    }
                } catch (Exception e) {
                    System.out.println("Buffer read error: "
                            + "@SuperBlock.makeFreeInodeList()"
                            + e);
                    return -1;
                }
            } else {
                try {
                    // At the last memory address the of the buffer the heads
                    // turn their orientation and read the memory location
                    // the next freeBlockListHead.


                    // Reads the location into the buffer of the next
                    // FreeBlockHead, which is located at block 138.
                    block1[i] = (byte) (((SUPERBLKSIZE + 64 * 2) - 1) >> 8);
                    block1[i + 1] = (byte) (((SUPERBLKSIZE + 64 * 2) - 1));
                    this.freeBlkCount += 1;

                    try {
                        // If
                        disk.putBlock(((SUPERBLKSIZE + 64) - 1), block1);
                    } catch (Exception e) {
                        System.out.println("1. Disk write error:"
                                + "@SuperBlock.makeFreeInodeList()."
                                + ".BlockIO.putBlock(int block, byte[] buffer): "
                                + e);
                        return -1;
                    }


                    // Reads the location into memory of the next
                    // FreeBlockHead, which is located at block 202.
                    block2[i] = (byte) (((SUPERBLKSIZE + 64 * 3) - 1) >> 8);
                    block2[i + 1] = (byte) ((SUPERBLKSIZE + 64 * 3) - 1);
                    this.freeBlkCount += 1;

                    try {
                        disk.putBlock((SUPERBLKSIZE + (2 * 64) - 1), block2);
                    } catch (Exception e) {
                        System.out.println("2. Disk write error:"
                                + "@SuperBlock.makeFreeInodeList()."
                                + ".BlockIO.putBlock(int block, byte[] buffer): "
                                + e);
                        return -1;
                    }

                    // Reads the location into memory of the next
                    // FreeBlockHead, which is located at block 266.
                    block3[i] = (byte) ((SUPERBLKSIZE + (64 * 4) - 1) >> 8);
                    block3[i + 1] = (byte) ((SUPERBLKSIZE + (64 * 4) - 1));
                    this.freeBlkCount += 1;

                    try {
                        disk.putBlock((SUPERBLKSIZE + (3 * 64) - 1), block3);
                    } catch (Exception e) {
                        System.out.println("3. Disk write error:"
                                + "@SuperBlock.makeFreeInodeList()."
                                + ".BlockIO.putBlock(int block, byte[] buffer): "
                                + e);
                        return -1;
                    }

                    // Reads the location into memory of the next
                    // FreeBlockHead, which is located at block 330.
                    block4[i] = (byte) ((SUPERBLKSIZE + (64 * 5) - 1) >> 8);
                    block4[i + 1] = (byte) ((SUPERBLKSIZE + (64 * 5) - 1));
                    this.freeBlkCount += 1;

                    try {
                        disk.putBlock((SUPERBLKSIZE + (4 * 64) - 1), block4);
                    } catch (Exception e) {
                        System.out.println("4. Disk write error:"
                                + "@SuperBlock.makeFreeInodeList()."
                                + ".BlockIO.putBlock(int block, byte[] buffer): "
                                + e);
                        return -1;
                    }


                    // Reads the location into memory of the next
                    // FreeBlockHead, which is located at block 394.
                    block5[i] = (byte) ((SUPERBLKSIZE + (64 * 6) - 1) >> 8);
                    block5[i + 1] = (byte) ((SUPERBLKSIZE + (64 * 6) - 1));
                    this.freeBlkCount += 1;
                    try {
                        disk.putBlock((SUPERBLKSIZE + (5 * 64) - 1), block5);
                    } catch (Exception e) {
                        System.out.println("5. Disk write error:"
                                + "@SuperBlock.makeFreeInodeList()."
                                + ".BlockIO.putBlock(int block, byte[] buffer): "
                                + e);
                    }

                    // Reads the location into memory of the next
                    // FreeBlockHead, which is located at block 458.
                    block6[i] = (byte) ((SUPERBLKSIZE + (64 * 7) - 1) >> 8);
                    block6[i + 1] = (byte) ((SUPERBLKSIZE + (64 * 7) - 1));
                    this.freeBlkCount += 1;

                    try {
                        disk.putBlock((SUPERBLKSIZE + (6 * 64) - 1), block6);
                    } catch (Exception e) {
                        System.out.println("6. Disk write error:"
                                + "@SuperBlock.makeFreeInodeList()."
                                + ".BlockIO.putBlock(int block, byte[] buffer): "
                                + e);
                    }

                    // Reads the location into memory of the next
                    // FreeBlockHead, which is located at block 511.
                    block7[i] = (byte) ((SUPERBLKSIZE + (64 * 8) - 1) >> 8);
                    block7[i + 1] = (byte) ((SUPERBLKSIZE + (64 * 8) - 1));
                    this.freeBlkCount += 1;

                    try {
                        disk.putBlock((SUPERBLKSIZE + (7 * 64) - 1), block7);
                    } catch (Exception e) {
                        System.out.println("7. Disk write error:"
                                + "@SuperBlock.makeFreeInodeList()."
                                + ".BlockIO.putBlock(int block, byte[] buffer): "
                                + e);
                    }

                    // Reads the block into buffer and calls BlockIO to putDisk
                    // in block number 511
                    try {
                        disk.putBlock(((8 * 64) - 1), block8);
                        this.freeBlkCount += 1;
                    } catch (Exception e) {
                        System.out.println("8. Disk write error:"
                                + "@SuperBlock.makeFreeInodeList()."
                                + ".BlockIO.putBlock(int block, byte[] buffer): "
                                + e);
                        return -1;
                    }
                } catch (Exception e) {
                    System.out.println("Buffer read error: "
                            + "@SuperBlock.makeFreeInodeList()"
                            + e);
                }
            }

            // Add 2 to the
            // buffer counter.
            i += 2;
        }

        return ((SUPERBLKSIZE + 64) - 1);

    }

    public short getFreeINode() {

        if (freeINodeCount == 0) {
            return -1;
        } else {

            byte[] buffer = new byte[BLKSIZE];

            disk.getBlock(freeInodeQueue, buffer);

            int j = 0;
            for (short i = 0; i < INODETBLSIZE; i++) {

                if ((buffer[j] == (byte) 0) && i == 7) {

                    freeInodeQueue++;
                    freeINodeCount--;

                    return (short) (i + (((freeInodeQueue - 1) - INODETBLSTART) * INODETBLSIZE));

                } else if (buffer[j] == (byte) 0) {

                    freeINodeCount--;
                    return (short) (i + (freeInodeQueue - INODETBLSTART) * INODETBLSIZE);

                }
                j += 16;

            }

            return -1;

        }

    }

    public short getFreeBlock() {
        if (freeBlkCount == 0) {
            return -1;
        } else {

            byte[] buffer = new byte[BLKSIZE];

            disk.getBlock(freeBlkList, buffer);

            for (short i = 0; i < BLKSIZE; i += 2) {

                short freeBlk = (short) (((buffer[i] & 0x00ff) << 8)
                        + (buffer[i + 1] & 0x00ff));

                if ((freeBlk != 0) && i == 124) {

                    short nextFreeBlkHead = (short) (((buffer[i + 2] & 0x00ff) << 8)
                            + (buffer[i + 3] & 0x00ff));

                    freeBlkList = nextFreeBlkHead;
                    freeBlkCount--;

                    return freeBlk;

                } else if (freeBlk != (byte) 0) {
                    buffer[i] = (byte) 0;
                    buffer[i + 1] = (byte) 0;

                    try {
                        disk.putBlock(freeBlkList, buffer);
                    } catch (Exception e) {
                        System.out.println("Disk write error"
                                + e);
                        return -1;
                    }

                    freeBlkCount--;
                    return freeBlk;

                }
            }

            return -1;

        }
    }

    public int checkiMap() {
        // disk.getBlock(IMAP, sbBuffer);
        return 0;
    }

    public int diskBitMap(DiskBitmap bitmap) {
        byte[] encoded = new byte[BLKSIZE];
        int i = 0;
        int y = 0;
        while (i < NUMBLKS) {
            int j = 3;
            int sum = 0;
            for (int z = i; z < 4; z++) {
                sum += (int) (bitmap.getBit(z) * Math.pow(2, j));
                j--;
            }
            encoded[y] = (byte) sum;
            i = i + 4;
            y++;
        }
        return updateBitmap(encoded);
    }

    private int updateBitmap(byte[] encoded) {


        return 0;
    }

    public short updateINodeMap(short iNumber) {

        byte[] emptyblock = new byte[BLKSIZE];

        // Calculate the iNodeTable block position
        short block = (short) (floor((iNumber * INODESIZE)
                / BLKSIZE) + INODETBLSTART);


        // Calculate the offset inside the iNodeTable block
        short offset = (short) ((iNumber * INODESIZE) % BLKSIZE);


        // Allocate a new buffer to store the block data
        byte[] buffer = new byte[BLKSIZE];

        // Read the iNodeTable block from disk
        // If disk read error: catch exception, display error,
        // and return -1
        try {
            disk.getBlock(block, buffer);
        } catch (Exception e) {
            System.out.println("Disk read error "
                    + "@ INode.writeToTable(short iNumber)"
                    + e);
            return -1;
        }


        // Copy the pointer to buffer

        return 0;
    }
}
