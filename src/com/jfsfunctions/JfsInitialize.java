package com.jfsfunctions;

import com.jfsglobal.JfsGlobalInterface;
import com.jfsinternal.*;
/**
 *
 * @author Nicholas De souza
 */
public class JfsInitialize extends JfsInterface {
    private BlockIO disk;
    private SuperBlock sb;
    public DiskBitmap bitmap;
    int error = 0;

    /**
     *
     * @param erase
     * @return 0 if successful, -1 on failure
     */
    @Override
    public int jfsInitialize(int erase) {
        disk = new BlockIO();


        if (erase != 0) {
            // Erased the entire disk
            wipeDisk();
        }

        // Writes the SuperBlock to disk
        sb = new SuperBlock();
        error = writeSuperBlock();
        if (error == -1) {
            return -1;
        }

        // Writes the disk bitmap to disk
        bitmap = new DiskBitmap();
        error = writeDiskBitmap();
        if (error == -1) {
            return -1;
        }

        // Sets iNode table zero's
        error = clearInodeTable();
        if (error == -1) {
            return -1;
        }

        // Writes root to File Table
        error = writeRoot();
        if (error == -1) {
            return -1;
        }

        // Creates Free Block List
        error = mkFreeBlkList();
        if (error == -1) {
            return -1;
        }

        return 0;
    }

    public int wipeDisk() {
        byte[] buffer = new byte[128];
        try {
            for (int blocks = 0; blocks < 511; blocks++) {
                for (int b = 0; b < 127; b++) {
                    buffer[b] = 0;
                }
                disk.putBlock(blocks, buffer);
            }
        } catch (Exception e) {
            System.out.println("Wipe disk error " + e);
        }
        return 0;
    }

    private int writeSuperBlock() {
        byte[] buffer = new byte[128];
        // Write the total number of blocks to the buffer
        buffer[0] = (byte)(sb.diskSize >> 8);
        buffer[1] = (byte)(sb.diskSize);

        // Writes the size of each block to the buffer
        buffer[2] = (byte)(sb.blockSize >> 8);
        buffer[3] = (byte)(sb.blockSize);

        // Writes the total number of iNodes to the buffer
        buffer[4] = (byte)(sb.iNodeCount >> 8);
        buffer[5] = (byte)(sb.iNodeCount);

        // Writes the size of the iNode table to the buffer
        buffer[6] = (byte)(sb.iNodeTable >> 8);
        buffer[7] = (byte)(sb.iNodeTable);

        // Writes the location of the first free block
        buffer[8] = (byte)(sb.freeBlkHeap >> 8);
        buffer[9] = (byte)(sb.freeBlkHeap);

        try {
            disk.putBlock(0, buffer);
        } catch (Exception e) {
            System.out.println("Fatal error: Could not write" +
                    "superblock to disk" + e);
            return -1;
        }
        return 0;
    }

    private int writeDiskBitmap() {
        byte[] bmBuffer = new byte[sb.BLKSIZE];

        for (int i = 0; i < (127); i++) {
            bmBuffer[i] = 1;
        }
        try {
            disk.putBlock(1, bmBuffer);
        } catch (Exception e) {
            System.out.println("Fatal error: Could not write " +
                    "bitmap to disk" + e);
            return -1;
        }
        return 0;
    }

    private int clearInodeTable() {
        byte[] clear = new byte[sb.BLKSIZE];
        try {
            for (int i = 1; i <= sb.iNodeTable; i++) {
                disk.putBlock((i + 1), clear);
            }
        } catch (Exception e) {
            System.out.println("Fatal error: Could not clear iNode table: " + e);
            return -1;
        }
        return 0;
    }

    private int writeRoot() {
        short location = 11;
        String name = "/";
        short type = 1;



        byte[] rootBuf = new byte[sb.BLKSIZE];

        rootBuf[0] = (byte)(location >> 8);
        rootBuf[1] = (byte)(location);

        byte[] nameBuf = name.getBytes();
        rootBuf[2] = nameBuf[0];

        rootBuf[14] = (byte)(type >> 8);
        rootBuf[15] = (byte)(type);

        try {
            disk.putBlock(2, rootBuf);
        } catch (Exception e) {
            System.out.println("Fatal error: Could not write" +
                    "root to disk: " + e);
            return -1;
        }
        return 0;
    }


    private int mkFreeBlkList() {
        short i = 11;
        while (i < sb.diskSize) {
            byte[] buffer = new byte[128];
            short nextFreeBlock = (short) (i + 1);
            buffer[0] = (byte)(nextFreeBlock >> 8);
            buffer[1] = (byte)(nextFreeBlock);

            try {
                disk.putBlock(i, buffer);
            } catch (Exception e) {
                System.out.println("Error: Could not" +
                        "create FreeBlock List" + e);
                return -1;
            }
            i++;
        }
        return 0;
    }

}



//        short count = 0;
//        for (int i = 0; i < sb.numBlocks; i++) {
//            try {
//                disk.getBlock(i, buffer);
//            } catch (Exception e) {
//                System.out.println("Disk getBlock error: " + e);
//                return -1;
//            }
//            if (buffer == null) {
//                byte empty = 0;
//                try {
//                    bitmap.putBit(i, empty);
//                } catch (Exception e) {
//                    System.out.println("Bitmap putBit error: " + e);
//                    return -1;
//                }
//                try {
//
//                } catch (Exception e) {
//
//                }
//            } else {
//                byte full = 1;
//                try {
//                    bitmap.putBit(i, full);
//                    count += 1;
//                } catch (Exception e) {
//                    System.out.println("Bitmap putBit error: " + e);
//                    return -1;
//                }
//            }
//        }
//        sb = new SuperBlock(count, );
//        sb.putSuperBlock(bitmap);
//
//
//
//    return 0;
