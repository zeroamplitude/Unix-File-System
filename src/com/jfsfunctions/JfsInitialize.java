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

import com.jfsinternal.INode;
import com.jfsinternal.JfsInternalConstants;
import com.jfsinternal.SuperBlock;
import com.jfsmemory.JfsDirectoryTree;
import com.jfsmemory.JfsMemory;

/**
 *
 * @author Nicholas De souza
 */
public class JfsInitialize extends JfsInterface {
    private SuperBlock sb;
    private INode root;
    private JfsMemory memory;
    private JfsDirectoryTree dt;
    int error = 0;

    public JfsInitialize(JfsMemory memory) {
        this.memory = memory;
        sb = new SuperBlock();
    }

    /**
     * @param erase
     * @return 0 if successful, -1 on failure
     * TODO: Needs Work
     */
    @Override
    public int jfsInitialize(int erase) {

        if (erase == 1) {

            /* Erased the entire disk */
            if ((error = wipeDisk()) < 0) {

                return -1;

            } else {
                System.out.println("Disk cleaned.");
            }

            if ((error = writeSuperBlock()) < 0) {
                return -1;
            } else {
                System.out.println("SuperBlock created.");
            }

            // Creates the root
            if ((error = getRooted()) < 0) {
                return -1;
            } else {
                System.out.println("Disk has been rooted.");
                int has = 0;
            }

        } else {

            sb = new SuperBlock((short) 1);
            memory.setSb(sb);
            /* Read SuperBlock */
            if (sb.getMagic() == -1) {
                return -1;
            }

//            /* Read Root ('/') to memory */
//            if ((error = readRoot()) < 0) {
//                return -1;
//            }
        }

        return 0;
    }


    /**
     * wipeDisk
     * This method provides a default value of true
     * to for the wipeDisk protocol. If value is not,
     * 0 null then this default disk formatter will
     * return true used.
     *
     * @return false if none specified.
     */
    public int wipeDisk() {
        return wipeDisk(false);
    }

    /**
     * wipeDisk
     * This method will purge all existing data
     * on the jFileSystem. If called with a security
     * flag the this method will provide a 7-pass
     * rewrite on your FileSystem leaving no room
     *
     * @return 0 on success, -1 on failure.
     */
    private int wipeDisk(boolean hackit) {

        byte[] buffer = new byte[128];

//        if (hackit == true) {
//            for (int blocks = 0; blocks < 512; blocks++) {
//
//                for (int b = 0; b < 127; b++) {
//
//                    buffer[b] = 0;
//
//                }
//
//                int i = 0;
//
//                int j = disk.NUMBLKS;
//
//                while ( i < 8 ) {
//                    try {
//                        if ( j < NUMBLKS) {
//
//                            disk.putBlock(j, buffer);
//
//                        } else if ( j == NUMBLKS )
//
//                            disk.putBlock(j, buffer);
//
//
//                    } catch (Exception e) {
//
//                        System.out.println("Disk write error :"
//                                        + "@JfsInitialize."
//                                        + ".wipeDisk(boolean hackit: "
//                                        + e );
//
//                    }
//                }
//            }
        try {

            for (int b = 0; b < 128; b++) {

                buffer[b] = (byte) 0;

            }

            for (int blocks = 0; blocks < 512; blocks++) {
                try {

                    sb.disk.putBlock(blocks, buffer);

                } catch (Exception e) {

                    System.out.println("Error" + e);
                    return -1;

                }

            }

        } catch (Exception e) {

            System.out.println("Wipe disk error " + e);

        }
        return 0;
    }

    /**
     * writeSuperBlock
     *      This method will write a new SuperBlock
     *      to disk, while establishing a count of the
     *      free iNode and blocks. It also will create
     *      a the jfs getit free block list and and the
     *      jfs geti free inode list.
     *
     * @return 0 on success, -1 on failure
     */
    private int writeSuperBlock() {
        sb = new SuperBlock();
        memory.setSb(sb);
        try {

            sb.writeToDisk();



        } catch (Exception e) {

            return -1;

        }

        if ((error = mapMyBits()) == -1) {
            return -1;
        }

        return 0;

    }

    private int mapMyBits() {

        byte[] iMap = new byte[JfsInternalConstants.BLKSIZE];
        byte[] emptyblock = new byte[JfsInternalConstants.BLKSIZE];

        try {

            sb.disk.putBlock(2, emptyblock);
            sb.disk.putBlock(10, iMap);

        } catch (Exception e) {

            System.out.println("Disk read error"
                    + "@JfsInitialize.writeSuperBlock()."
                    + "mapMyBits: " + e);

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
        // Initialize root iNode

        root = new INode("/");
        if (root.getMagic() < 0) {
            System.out.println("Error constructing iNode");
            return -1;
        }

        return 0;
    }

}
