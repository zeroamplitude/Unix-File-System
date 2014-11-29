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
    public short iNodeTable;
    private short diskSize;
    private short blockSize;
    private short iNodeCount;
    private short freeINodeCount;
    private short[] freeINodeList;
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
        //freeINodeList = 0 ;
    }

    public int writeToDisk() {
        byte[] buffer = new byte[128];
        // Write the total number of blocks to the buffer
        buffer[0] = (byte) (this.diskSize >> 8);
        buffer[1] = (byte) (this.diskSize);

        // Writes the size of each block to the buffer
        buffer[2] = (byte) (this.blockSize >> 8);
        buffer[3] = (byte) (this.blockSize);

        // Writes the total number of iNodes to the buffer
        buffer[4] = (byte) (this.iNodeCount >> 8);
        buffer[5] = (byte) (this.iNodeCount);

        // Writes the size of the iNode table to the buffer
        buffer[6] = (byte) (this.iNodeTable >> 8);
        buffer[7] = (byte) (this.iNodeTable);

        // Writes the location of the first free block
        buffer[8] = (byte) (this.freeBlkList >> 8);
        buffer[9] = (byte) (this.freeBlkList);

        try {
            disk.putBlock(0, buffer);
        } catch (Exception e) {
            System.out.println("Fatal error: Could not write" +
                    "superblock to disk" + e);
            return -1;
        }
        return 0;
    }


    public short makeFreeBlkList2()

    {

        // Creating block buffers for FreeList Head
        byte[] block1 = new byte[BLKSIZE];
        byte[] block2 = new byte[BLKSIZE];
        byte[] block3 = new byte[BLKSIZE];
        byte[] block4 = new byte[BLKSIZE];
        byte[] block5 = new byte[BLKSIZE];
        byte[] block6 = new byte[BLKSIZE];
        byte[] block7 = new byte[BLKSIZE];
        byte[] block8 = new byte[BLKSIZE];

        short freeBlockCount = 0;
        int i = 0;
        for (int j = 0; j < (BLKSIZE / 2); j++) {

            if (j != 63) {
                try {

                    // Reads the the location of the child block into
                    // buffer starting with child 11 block until 73.
                    block1[i] = (byte) ((SUPERBLKSIZE + j) >> 8);
                    block1[i + 1] = (byte) ((SUPERBLKSIZE + j));
                    freeBlockCount++;

                    // Reads the location of the child block into
                    // buffer starting with child block 75 until 137.
                    block2[i] = (byte) ((SUPERBLKSIZE + 64 + j) >> 8);
                    block2[i + 1] = (byte) ((SUPERBLKSIZE + 64 + j));
                    freeBlockCount++;

                    // Reads the location of the child block into
                    // buffer starting with the child block 139 until 201.
                    block3[i] = (byte) ((SUPERBLKSIZE + (64 * 2) + j) >> 8);
                    block3[i + 1] = (byte) ((SUPERBLKSIZE + (64 * 2) + j));
                    freeBlockCount++;

                    // Reads the location of the child block into
                    // buffer starting with the child block 203 until 265.
                    block4[i] = (byte) ((SUPERBLKSIZE + (64 * 3) + j) >> 8);
                    block4[i + 1] = (byte) ((SUPERBLKSIZE + (64 * 3) + j));
                    freeBlockCount++;

                    // Reads the location of the child block into
                    // buffer starting with the child block 267 until 329.
                    block5[i] = (byte) ((SUPERBLKSIZE + (64 * 4) + j) >> 8);
                    block5[i + 1] = (byte) ((SUPERBLKSIZE + (64 * 4) + j));
                    freeBlockCount++;

                    // Reads the location of the child block into
                    // buffer starting with the child block 331 until 393.
                    block6[i] = (byte) ((SUPERBLKSIZE + (64 * 5) + j) >> 8);
                    block6[i + 1] = (byte) ((SUPERBLKSIZE + (64 * 5) + j));
                    freeBlockCount++;

                    // Reads the location of the child block into
                    // buffer  starting with the child block 395 until 457.
                    block7[i] = (byte) ((SUPERBLKSIZE + (64 * 6) + j) >> 8);
                    block7[i + 1] = (byte) ((SUPERBLKSIZE + (64 * 6) + j));
                    freeBlockCount++;

                    if (j < 52) {
                        // If the index pointer is less than 52 which represents
                        // the remaining children, then it read the location of
                        // the child block into the buffer starting at location
                        // 459 to 510
                        block8[i] = (byte) ((SUPERBLKSIZE + (64 * 7) + j) >> 8);
                        block8[i + 1] = (byte) ((SUPERBLKSIZE + (64 * 7) + j));
                        freeBlockCount++;
                    }
                } catch (Exception e) {
                    System.out.println("Buffer read error: "
                            + "@SuperBlock.makeFreeInodeList()"
                            + e);
                    freeBlockCount = -1;
                    return freeBlockCount;
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
                    freeBlockCount++;

                    try {
                        // If
                        disk.putBlock(((SUPERBLKSIZE + 64) - 1), block1);

                        System.out.println("1. Success new Free List Block Created");

                        this.freeBlkList = ((SUPERBLKSIZE + 64) - 1);

                    } catch (Exception e) {
                        System.out.println("Disk write error:"
                                + "@SuperBlock.makeFreeInodeList()."
                                + ".BlockIO.putBlock(int block, byte[] buffer): "
                                + e);
                        freeBlockCount = -1;
                        return freeBlockCount;
                    }


                    // Reads the location into memory of the next
                    // FreeBlockHead, which is located at block 202.
                    block2[i] = (byte) (((SUPERBLKSIZE + 64 * 3) - 1) >> 8);
                    block2[i + 1] = (byte) ((SUPERBLKSIZE + 64 * 3) - 1);
                    freeBlockCount++;

                    try {
                        disk.putBlock((SUPERBLKSIZE + (2 * 64) - 1), block2);
                        System.out.println("2. Success new Free List Block Created");
                    } catch (Exception e) {
                        System.out.println("Disk write error:"
                                + "@SuperBlock.makeFreeInodeList()."
                                + ".BlockIO.putBlock(int block, byte[] buffer): "
                                + e);
                        return freeBlockCount;
                    }

                    // Reads the location into memory of the next
                    // FreeBlockHead, which is located at block 266.
                    block3[i] = (byte) ((SUPERBLKSIZE + (64 * 4) - 1) >> 8);
                    block3[i + 1] = (byte) ((SUPERBLKSIZE + (64 * 4) - 1));
                    freeBlockCount++;

                    try {
                        disk.putBlock((SUPERBLKSIZE + (3 * 64) - 1), block3);
                        System.out.println("3. Success new Free List Block Created");
                    } catch (Exception e) {
                        System.out.println("Disk write error:"
                                + "@SuperBlock.makeFreeInodeList()."
                                + ".BlockIO.putBlock(int block, byte[] buffer): "
                                + e);
                        return freeBlockCount;
                    }

                    // Reads the location into memory of the next
                    // FreeBlockHead, which is located at block 330.
                    block4[i] = (byte) ((SUPERBLKSIZE + (64 * 5) - 1) >> 8);
                    block4[i + 1] = (byte) ((SUPERBLKSIZE + (64 * 5) - 1));
                    freeBlockCount++;

                    try {
                        disk.putBlock((SUPERBLKSIZE + (4 * 64) - 1), block4);
                        System.out.println("4. Success new Free List Block Created");
                    } catch (Exception e) {
                        System.out.println("Disk write error:"
                                + "@SuperBlock.makeFreeInodeList()."
                                + ".BlockIO.putBlock(int block, byte[] buffer): "
                                + e);
                        return freeBlockCount;
                    }


                    // Reads the location into memory of the next
                    // FreeBlockHead, which is located at block 394.
                    block5[i] = (byte) ((SUPERBLKSIZE + (64 * 6) - 1) >> 8);
                    block5[i + 1] = (byte) ((SUPERBLKSIZE + (64 * 6) - 1));
                    freeBlockCount++;

                    try {
                        disk.putBlock((SUPERBLKSIZE + (5 * 64) - 1), block5);
                        System.out.println("5. Success new Free List Block Created");
                    } catch (Exception e) {
                        System.out.println("Disk write error:"
                                + "@SuperBlock.makeFreeInodeList()."
                                + ".BlockIO.putBlock(int block, byte[] buffer): "
                                + e);
                        return freeBlockCount = -1;
                    }

                    // Reads the location into memory of the next
                    // FreeBlockHead, which is located at block 458.
                    block6[i] = (byte) ((SUPERBLKSIZE + (64 * 7) - 1) >> 8);
                    block6[i + 1] = (byte) ((SUPERBLKSIZE + (64 * 7) - 1));
                    freeBlockCount++;

                    try {
                        disk.putBlock((SUPERBLKSIZE + (6 * 64) - 1), block6);
                        System.out.println("6. Success new Free List Block Created");
                    } catch (Exception e) {
                        System.out.println("Disk write error:"
                                + "@SuperBlock.makeFreeInodeList()."
                                + ".BlockIO.putBlock(int block, byte[] buffer): "
                                + e);
                        return freeBlockCount = -1;
                    }

                    // Reads the location into memory of the next
                    // FreeBlockHead, which is located at block 511.
                    block7[i] = (byte) ((SUPERBLKSIZE + (64 * 8) - 1) >> 8);
                    block7[i + 1] = (byte) ((SUPERBLKSIZE + (64 * 8) - 1));
                    freeBlockCount++;

                    try {
                        disk.putBlock((SUPERBLKSIZE + (7 * 64) - 1), block7);
                        System.out.println("7. Success new Free List Block Created");
                    } catch (Exception e) {
                        System.out.println("Disk write error:"
                                + "@SuperBlock.makeFreeInodeList()."
                                + ".BlockIO.putBlock(int block, byte[] buffer): "
                                + e);
                        return freeBlockCount = -1;
                    }

                    // Reads the block into buffer and calls BlockIO to putDisk
                    // in block number 511
                    try {
                        disk.putBlock(((8 * 64) - 1), block8);
                    } catch (Exception e) {
                        System.out.println("8 Disk write error:"
                                + "@SuperBlock.makeFreeInodeList()."
                                + ".BlockIO.putBlock(int block, byte[] buffer): "
                                + e);
                        return -1;
                    }

                } catch (Exception e) {
                    System.out.println("Buffer read error: "
                            + "@SuperBlock.makeFreeInodeList()"
                            + e);
                    return freeBlockCount = -1;
                }
            }

            // Add 2 to the
            // buffer counter.
            i += 2;
        }
        makeFreeINodeList();

        return 0;
    }

    public short makeFreeBlkList() {

        // Creating block buffers for FreeList Head
        byte[] block1 = new byte[BLKSIZE];
        byte[] block2 = new byte[BLKSIZE];
        byte[] block3 = new byte[BLKSIZE];
        byte[] block4 = new byte[BLKSIZE];
        byte[] block5 = new byte[BLKSIZE];
        byte[] block6 = new byte[BLKSIZE];
        byte[] block7 = new byte[BLKSIZE];
        byte[] block8 = new byte[BLKSIZE];

        short freeBlockCount = 0;
        int i = 0;
        for (int j = 0; j < (BLKSIZE / 2); j++) {

            if (j != 63) {
                try {

                    // Reads the the location of the child block into
                    // buffer starting with child 11 block until 73.
                    block1[i] = (byte) ((SUPERBLKSIZE + j) >> 8);
                    block1[i + 1] = (byte) ((SUPERBLKSIZE + j));
                    freeBlockCount++;

                    // Reads the location of the child block into
                    // buffer starting with child block 75 until 137.
                    block2[i] = (byte) ((SUPERBLKSIZE + 64 + j) >> 8);
                    block2[i + 1] = (byte) ((SUPERBLKSIZE + 64 + j));
                    freeBlockCount++;

                    // Reads the location of the child block into
                    // buffer starting with the child block 139 until 201.
                    block3[i] = (byte) ((SUPERBLKSIZE + (64 * 2) + j) >> 8);
                    block3[i + 1] = (byte) ((SUPERBLKSIZE + (64 * 2) + j));
                    freeBlockCount++;

                    // Reads the location of the child block into
                    // buffer starting with the child block 203 until 265.
                    block4[i] = (byte) ((SUPERBLKSIZE + (64 * 3) + j) >> 8);
                    block4[i + 1] = (byte) ((SUPERBLKSIZE + (64 * 3) + j));
                    freeBlockCount++;

                    // Reads the location of the child block into
                    // buffer starting with the child block 267 until 329.
                    block5[i] = (byte) ((SUPERBLKSIZE + (64 * 4) + j) >> 8);
                    block5[i + 1] = (byte) ((SUPERBLKSIZE + (64 * 4) + j));
                    freeBlockCount++;

                    // Reads the location of the child block into
                    // buffer starting with the child block 331 until 393.
                    block6[i] = (byte) ((SUPERBLKSIZE + (64 * 5) + j) >> 8);
                    block6[i + 1] = (byte) ((SUPERBLKSIZE + (64 * 5) + j));
                    freeBlockCount++;

                    // Reads the location of the child block into
                    // buffer  starting with the child block 395 until 457.
                    block7[i] = (byte) ((SUPERBLKSIZE + (64 * 6) + j) >> 8);
                    block7[i + 1] = (byte) ((SUPERBLKSIZE + (64 * 6) + j));
                    freeBlockCount++;

                    if (j < 52) {
                        // If the index pointer is less than 52 which represents
                        // the remaining children, then it read the location of
                        // the child block into the buffer starting at location
                        // 459 to 510
                        block8[i] = (byte) ((SUPERBLKSIZE + (64 * 7) + j) >> 8);
                        block8[i + 1] = (byte) ((SUPERBLKSIZE + (64 * 7) + j));
                        freeBlockCount++;
                    }
                } catch (Exception e) {
                    System.out.println("Buffer read error: "
                            + "@SuperBlock.makeFreeInodeList()"
                            + e);
                    freeBlockCount = -1;
                    return freeBlockCount;
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
                    freeBlockCount++;

                    try {
                        // If
                        disk.putBlock(((SUPERBLKSIZE + 64) - 1), block1);

                        System.out.println("1. Success new Free List Block Created");

                        this.freeBlkList = ((SUPERBLKSIZE + 64) - 1);

                    } catch (Exception e) {
                        System.out.println("Disk write error:"
                                + "@SuperBlock.makeFreeInodeList()."
                                + ".BlockIO.putBlock(int block, byte[] buffer): "
                                + e);
                        freeBlockCount = -1;
                        return freeBlockCount;
                    }


                    // Reads the location into memory of the next
                    // FreeBlockHead, which is located at block 202.
                    block2[i] = (byte) (((SUPERBLKSIZE + 64 * 3) - 1) >> 8);
                    block2[i + 1] = (byte) ((SUPERBLKSIZE + 64 * 3) - 1);
                    freeBlockCount++;

                    try {
                        disk.putBlock((SUPERBLKSIZE + (2 * 64) - 1), block2);
                        System.out.println("2. Success new Free List Block Created");
                    } catch (Exception e) {
                        System.out.println("Disk write error:"
                                + "@SuperBlock.makeFreeInodeList()."
                                + ".BlockIO.putBlock(int block, byte[] buffer): "
                                + e);
                        return freeBlockCount;
                    }

                    // Reads the location into memory of the next
                    // FreeBlockHead, which is located at block 266.
                    block3[i] = (byte) ((SUPERBLKSIZE + (64 * 4) - 1) >> 8);
                    block3[i + 1] = (byte) ((SUPERBLKSIZE + (64 * 4) - 1));
                    freeBlockCount++;

                    try {
                        disk.putBlock((SUPERBLKSIZE + (3 * 64) - 1), block3);
                        System.out.println("3. Success new Free List Block Created");
                    } catch (Exception e) {
                        System.out.println("Disk write error:"
                                + "@SuperBlock.makeFreeInodeList()."
                                + ".BlockIO.putBlock(int block, byte[] buffer): "
                                + e);
                        return freeBlockCount;
                    }

                    // Reads the location into memory of the next
                    // FreeBlockHead, which is located at block 330.
                    block4[i] = (byte) ((SUPERBLKSIZE + (64 * 5) - 1) >> 8);
                    block4[i + 1] = (byte) ((SUPERBLKSIZE + (64 * 5) - 1));
                    freeBlockCount++;

                    try {
                        disk.putBlock((SUPERBLKSIZE + (4 * 64) - 1), block4);
                        System.out.println("4. Success new Free List Block Created");
                    } catch (Exception e) {
                        System.out.println("Disk write error:"
                                + "@SuperBlock.makeFreeInodeList()."
                                + ".BlockIO.putBlock(int block, byte[] buffer): "
                                + e);
                        return freeBlockCount;
                    }


                    // Reads the location into memory of the next
                    // FreeBlockHead, which is located at block 394.
                    block5[i] = (byte) ((SUPERBLKSIZE + (64 * 6) - 1) >> 8);
                    block5[i + 1] = (byte) ((SUPERBLKSIZE + (64 * 6) - 1));
                    freeBlockCount++;

                    try {
                        disk.putBlock((SUPERBLKSIZE + (5 * 64) - 1), block5);
                        System.out.println("5. Success new Free List Block Created");
                    } catch (Exception e) {
                        System.out.println("Disk write error:"
                                + "@SuperBlock.makeFreeInodeList()."
                                + ".BlockIO.putBlock(int block, byte[] buffer): "
                                + e);
                        return freeBlockCount = -1;
                    }

                    // Reads the location into memory of the next
                    // FreeBlockHead, which is located at block 458.
                    block6[i] = (byte) ((SUPERBLKSIZE + (64 * 7) - 1) >> 8);
                    block6[i + 1] = (byte) ((SUPERBLKSIZE + (64 * 7) - 1));
                    freeBlockCount++;

                    try {
                        disk.putBlock((SUPERBLKSIZE + (6 * 64) - 1), block6);
                        System.out.println("6. Success new Free List Block Created");
                    } catch (Exception e) {
                        System.out.println("Disk write error:"
                                + "@SuperBlock.makeFreeInodeList()."
                                + ".BlockIO.putBlock(int block, byte[] buffer): "
                                + e);
                        return freeBlockCount = -1;
                    }

                    // Reads the location into memory of the next
                    // FreeBlockHead, which is located at block 511.
                    block7[i] = (byte) ((SUPERBLKSIZE + (64 * 8) - 1) >> 8);
                    block7[i + 1] = (byte) ((SUPERBLKSIZE + (64 * 8) - 1));
                    freeBlockCount++;

                    try {
                        disk.putBlock((SUPERBLKSIZE + (7 * 64) - 1), block7);
                        System.out.println("7. Success new Free List Block Created");
                    } catch (Exception e) {
                        System.out.println("Disk write error:"
                                + "@SuperBlock.makeFreeInodeList()."
                                + ".BlockIO.putBlock(int block, byte[] buffer): "
                                + e);
                        return freeBlockCount = -1;
                    }

                    // Reads the block into buffer and calls BlockIO to putDisk
                    // in block number 511
                    try {
                        disk.putBlock(((8 * 64) - 1), block8);
                    } catch (Exception e) {
                        System.out.println("8 Disk write error:"
                                + "@SuperBlock.makeFreeInodeList()."
                                + ".BlockIO.putBlock(int block, byte[] buffer): "
                                + e);
                        return -1;
                    }

                } catch (Exception e) {
                    System.out.println("Buffer read error: "
                            + "@SuperBlock.makeFreeInodeList()"
                            + e);
                    return freeBlockCount = -1;
                }
            }

            // Add 2 to the
            // buffer counter.
            i += 2;
        }
        makeFreeINodeList();

        return 0;
    }


    public short[] makeFreeINodeList()

    {
        byte[] buffer = new byte[128];

        for (int j = INODETBLSTART; j <= INODETBLSIZE + 1; j++) {
            disk.getBlock(j, buffer);

            for (int i = 0; i < 8; i++) {
                //if (buffer[16 * i] != 1)
                //System.out.print("inside if statement");
                // {
                freeINodeList[freeINodeCount] = (short) (buffer[16 * i + 1] + buffer[16 * i + 2]);
                freeINodeCount++;
                System.out.print(freeINodeCount);
                //}
            }
        }

        System.out.print(freeINodeCount);
        return freeINodeList;

    }


}