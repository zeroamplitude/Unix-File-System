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

package com.jfsfunctions;

import com.jfsinternal.*;
/**
 *
 * @author Nicholas De souza
 */
public class JfsInitialize extends JfsInterface {
    private BlockIO disk;
    private SuperBlock sb;
    private INode root;
    public DiskBitmap bitmap;
    int error = 0;

    /**
     * @param erase
     * @return 0 if successful, -1 on failure
     * TODO: Needs Work
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
//
//        // Sets iNode table zero's
//        error = clearInodeTable();
//        if (error == -1) {
//            return -1;
//        }

        // Creates the root
        error = getRooted();
        if (error == -1) {
            return -1;
        }


        return 0;
    }

    public int wipeDisk() {
        byte[] buffer = new byte[128];
        try {
            for (int blocks = 0; blocks < 512; blocks++) {
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
        try {
            sb.writeToDisk();
        } catch (Exception e) {
            return -1;
        }
        return 0;
    }

    private int clearInodeTable() {
        byte[] clear = new byte[JfsInternalConstants.BLKSIZE];
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

    /**
     * getRooted
     *      This function is called when the disk is
     *      initialized. It writes root to the iNode
     *      table and then to the first data block.
     *
     * @return 0 on success and -1 on failure
     */
    private int getRooted() {

        // Intitialize root iNode
        String rt = "/";
        root = new INode(rt, (short) 0);

        // Calls writeToTable with iNumber value 0
        // If
        try {
            root.writeToTable((short) 0);
        } catch (Exception e) {
            System.out.println("Disk write error "
                    + "@ INode.getRooted()." +
                    "writeToTable(short iNumber): "
                    + e);
            return -1;
        }

        try {
            root.writeToBlock((short) 11);
        } catch (Exception e) {
            System.out.println("Disk write error "
                    + "@ INode.getRooted()." +
                    "writeToBlock(short blockNum): "
                    + e);
            return -1;
        }
        return 0;
    }

    //
//        // Writes the disk bitmap to disk
//        bitmap = new DiskBitmap();
//        error = writeDiskBitmap();
//        if (error == -1) {
//            return -1;
//        }
//

//
//        // Writes root to File Table
//        error = writeRoot();
//        if (error == -1) {
//            return -1;
//        }
//
//        // Creates Free Block List
//        error = mkFreeBlkList();
//        if (error == -1) {
//            return -1;
//        }

//    private int writeDiskBitmap() {
//        byte[] bmBuffer = new byte[sb.BLKSIZE];
//
//        for (int i = 0; i < (127); i++) {
//            bmBuffer[i] = 1;
//        }
//        try {
//            disk.putBlock(1, bmBuffer);
//        } catch (Exception e) {
//            System.out.println("Fatal error: Could not write " +
//                    "bitmap to disk" + e);
//            return -1;
//        }
//        return 0;
//    }
//

//
//    private int writeRoot() {
//        short location = 11;
//        String name = "/";
//        short type = 1;
//
//
//
//        byte[] rootBuf = new byte[sb.BLKSIZE];
//
//        rootBuf[0] = (byte)(location >> 8);
//        rootBuf[1] = (byte)(location);
//
//        byte[] nameBuf = name.getBytes();
//        rootBuf[2] = nameBuf[0];
//
//        rootBuf[14] = (byte)(type >> 8);
//        rootBuf[15] = (byte)(type);
//
//        try {
//            disk.putBlock(2, rootBuf);
//        } catch (Exception e) {
//            System.out.println("Fatal error: Could not write" +
//                    "root to disk: " + e);
//            return -1;
//        }
//        return 0;
//    }
//
//
//    private int mkFreeBlkList() {
//        short i = 11;
//        while (i < sb.diskSize) {
//            byte[] buffer = new byte[128];
//            short nextFreeBlock = (short) (i + 1);
//            buffer[0] = (byte)(nextFreeBlock >> 8);
//            buffer[1] = (byte)(nextFreeBlock);
//
//            try {
//                disk.putBlock(i, buffer);
//            } catch (Exception e) {
//                System.out.println("Error: Could not" +
//                        "create FreeBlock List" + e);
//                return -1;
//            }
//            i++;
//        }
//        return 0;
//    }
//
//}


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

}
