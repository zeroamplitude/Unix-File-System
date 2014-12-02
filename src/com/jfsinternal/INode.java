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

import com.jfsmemory.JfsDirectoryNode;
import com.jfsmemory.JfsDirectoryTree;
import com.jfsmemory.JfsMemory;

import java.util.Date;

import static java.lang.Math.floor;

/**
 * Created by Nicholas De Souza on 26/11/14.
 */
public class INode implements JfsInternalConstants {
    private JfsMemory memory = JfsMemory.getInstance();
    private SuperBlock sb = memory.getSb();
    private JfsDirectoryTree dt;

    protected int magic;
    protected short magic2;
    protected JfsDirectoryNode magic3;
    protected String[] tokens;

    private byte status;
    private short location;
    private String name;
    private short type;
    private short openCount;
    private short iNumber;
    private short numFiles;
    private short size;
    private String cdate;
    private String adate;
    private String mdate;
    private short direct[];
    private short indirect;


    public INode(int iNumbers) {
        byte[] rBuffer = new byte[BLKSIZE];
        short iTblloc = (short) (floor(iNumber / (INODETBLBLKSIZE)) + INODETBLSTART);
        short offset = (short) (iNumber % (BLKSIZE / SHORTSIZE));
        try {
            sb.disk.getBlock(iTblloc, rBuffer);
            this.magic = 0;
        } catch (Exception e) {
            this.magic = -1;
        }
        this.status = rBuffer[offset];
        this.location = (short) (((rBuffer[offset + 1] & 0x00ff) << 8)
                + (rBuffer[offset + 2] & 0x00ff));
        this.name = new String(rBuffer, offset + 3, 6);
        this.type = (short) (((rBuffer[offset + 9] & 0x00ff) << 8)
                + (rBuffer[offset + 10] & 0x00ff));
        this.openCount = (short) (((rBuffer[offset + 11] & 0x00ff) << 8)
                + (rBuffer[offset + 12] & 0x00ff));

    }

    public INode(String root) {
        this.status = (byte) 1;     // 1 byte
        this.location = sb.getFreeBlock();          // 2 bytes
        this.name = root;           // 6 bytes
        this.type = 1;   // 0 if file, else 1
        this.openCount = 0;         // 2 bytes

        try {
            this.iNumber = sb.getFreeINode();
            this.magic = 0;
        } catch (Exception e) {
            this.magic = -1;
        }

        this.numFiles = 0;
        this.size = 0;
        this.cdate = new Date().toString();
        this.adate = new Date().toString();
        this.mdate = new Date().toString();
        this.direct = new short[12];
        this.indirect = 0;

        // On verification that the iNode was
        // constructed it will write the iNode
        // to the iNode table.
        if (this.magic == 0) {
            magic = writeToTable(this.iNumber);
        }

        // If this iNode is a directory it will
        // create an iNode block on disk.
        if (this.magic == 0 && this.type == 1) {
            magic = writeToBlock(this.iNumber);
        }

        if (magic == 0) {
            dt = new JfsDirectoryTree(this.name, this.iNumber);
            memory.setJfsDirectoryTree(dt);
        }

    }

    /**
     * INode Constructor
     * This is the first constructor of an iNode when
     * a file is creates. This will set the values of
     * the iNode in the iNode table.
     *
     * @param name A 6 character string value that repr-
     *             esnts the name of the file.
     * @param type A short integer value that represents
     *             the iNodes type. 0 is a file, 1 is a
     *             directory.
     */
    public INode(String[] name, int type) {

        dt = memory.getJfsDirectoryTree();
        magic3 = dt.traverseTree(name);
        if (!magic3.name.equals("ERROR")) {
            this.status = (byte) 1;     // 1 byte

            if (type == 1) {
                this.location = sb.getFreeBlock();
            } else {
                this.location = 0;
            }

            this.tokens = name;
            this.name = name[name.length - 1];
            this.type = (short) type;   // 0 if file, else 1
            this.openCount = 0;

            try {
                this.iNumber = sb.getFreeINode();
                this.magic = 0;
            } catch (Exception e) {
                this.magic = -1;
            }
            this.magic2 = this.iNumber;

            this.numFiles = 0;

            this.size = 0;
            this.cdate = new Date().toString();
            this.adate = new Date().toString();
            this.mdate = new Date().toString();
            this.direct = new short[12];
            this.indirect = 0;


            if (magic == 0) {
                INode parent = new INode(magic3.iNumber);
                short block = (short) floor(parent.numFiles / (BLKSIZE / SHORTSIZE));
                short offset = (short) (parent.numFiles % (BLKSIZE / SHORTSIZE));
                byte[] buffer = new byte[BLKSIZE];

                try {

                    if ((parent.numFiles % (BLKSIZE / SHORTSIZE)) == 0) {
                        parent.direct[block] = sb.getFreeBlock();
                    }
                    // change made here
                    sb.disk.getBlock(parent.direct[block], buffer);

                    buffer[offset] = (byte) (this.magic2 >> 8);
                    buffer[offset + 1] = (byte) (this.magic2);

                    buffer[offset] = (byte) (parent.numFiles++ >> 8);

                    sb.disk.putBlock(block, buffer);

                    parent.numFiles++;

                    parent.writeToBlock(parent.iNumber);

                } catch (Exception e) {
                    System.out.println("Disk read error "
                            + "@Inode.pointParent: "
                            + e);
                    this.magic = -1;
                }
            }

            if (this.magic == 0) {
                this.magic = dt.updateDirectoryTree(tokens, magic2);
            }

            // On verification that the iNode was
            // constructed it will write the iNode
            // to the iNode table.
            if (this.magic == 0) {
                this.magic = writeToTable(this.iNumber);
            }

            // If this iNode is a directory it will
            // create an iNode block on disk.
            if (this.magic == 0 && this.type == 1) {
                this.magic = writeToBlock(this.iNumber);
            }


        } else {
            this.magic = -1;
        }

    }

    /**
     * INode Constructor
     * This will construct and INode by reading it
     * from disk.
     *
     * @param iNumber An integer representing the value of
     *                the iNodes location in the iNode table
     */
    public INode(short iNumber) {

        byte[] rBuffer = new byte[BLKSIZE];

        short iTblloc = (short) (floor(iNumber / (INODETBLBLKSIZE)) + INODETBLSTART);

        short offset = (short) (iNumber % (BLKSIZE / SHORTSIZE));

        try {

            sb.disk.getBlock(iTblloc, rBuffer);
            this.magic = 0;

            short block = (short) (((rBuffer[offset + 1] & 0x00ff) << 8)
                    + (rBuffer[offset + 2] & 0x00ff));

            rBuffer = new byte[BLKSIZE];

            sb.disk.getBlock(block, rBuffer);

        } catch (Exception e) {

            this.magic = -1;

        }


        this.status = rBuffer[0];

        this.location = (short) (((rBuffer[1] & 0x00ff) << 8)
                + (rBuffer[2] & 0x00ff));

        this.name = new String(rBuffer, 3, 6);

        this.type = (short) (((rBuffer[9] & 0x00ff) << 8)
                + (rBuffer[10] & 0x00ff));

        this.openCount = (short) (((rBuffer[11] & 0x00ff) << 8)
                + (rBuffer[12] & 0x00ff));

        this.iNumber = (short) (((rBuffer[13] & 0x00ff) << 8)
                + (rBuffer[14] & 0x00ff));

        this.size = (short) (((rBuffer[15] & 0x00ff) << 8)
                + (rBuffer[16] & 0x00ff));

        this.cdate = new String(rBuffer, 17, 28);

        this.adate = new String(rBuffer, 45, 28);

        this.mdate = new String(rBuffer, 73, 28);

        int j = 0;
        for (int i = 0; i < 12; i++) {
            this.direct = new short[NUMDIRECT];
            this.direct[i] = (short) (((rBuffer[101 + j] & 0x00ff) << 8)
                    + (rBuffer[101 + j + 1] & 0x00ff));
            j += 2;
        }

        this.indirect = (short) (((rBuffer[125] & 0x00ff) << 8)
                + (rBuffer[126] & 0x00ff));


    }

    public int getMagic() {
        return this.magic;
    }

    public String getName() {
        return this.name;
    }

    /**
     * writeToTable
     *      This method can be called from various methods
     *      in the jfsInterface. It is usually called when
     *      an iNode is created and will write the iNode to
     *      the iNode table.
     *
     * @param iNumber A short integer value that represents
     *                iNumber of an iNode, which is a
     *                reference to its location in the iNode
     *                Table.
     *
     * @return 0 on success and -1 on failure.
     */
    private int writeToTable(short iNumber) {
        // Calculate the iNodeTable block position
        short block = (short) (floor(iNumber / INODETBLSIZE) + INODETBLSTART);
        // Calculate the offset inside the iNodeTable block
        short offset = (short) ((iNumber % INODETBLSIZE) * INODESIZE);
        // Allocate a new buffer to store the block data
        byte[] buffer = new byte[BLKSIZE];

        // Read the iNodeTable block from disk
        // If disk read error: catch exception, display error,
        // and return -1
        try {
            sb.disk.getBlock(block, buffer);
        } catch (Exception e) {
            System.out.println("Disk read error "
                    + "@ INode.writeToTable(short iNumber)"
                    + e);
            return -1;
        }

        // Copy the status to buffer
        buffer[offset] = this.status;
        // Copy the pointer to buffer
        buffer[offset+1] = (byte)(this.location >> 8);
        buffer[offset+2] = (byte)(this.location);
        // Copy the name to buffer
        byte[] nameBuf = this.name.getBytes();
        for(int i = 3; i < 9; i++) {
            if (i < nameBuf.length + 3) {
                buffer[offset+i] = nameBuf[i-3];
            } else {
                buffer[offset+i] = 0;
            }
        }
        // Copy the Type to buffer
        buffer[offset+9] = (byte)(this.type >> 8);
        buffer[offset + 10] = (byte) (this.type);
        // Copy the open count to buffer
        buffer[offset+11] = (byte)(this.openCount >> 8);
        buffer[offset+12] = (byte)(this.openCount);
        // Write the modified buffer back to the block
        // If disk write error: catch exception, display error
        // and return -1
        try {
            sb.disk.putBlock(block, buffer);
        } catch (Exception e) {
            System.out.println("Disk write error "
                    + "@ INode.writeToTable(short iNumber): "
                    + e);
            return -1;
        }
        // If all operations successful return 0
        return 0;
    }


    /**
     * writeNewToBlock
     *      This method writes an iNode to disk. It can be
     *      called to write a new iNode in which, the associated
     *      iNode's location points to null in the iNodeTable.
     *      It can also be called to modify an existing iNode on
     *      disk.
     *
     * @param iNumber A short integer value representing the
     *                index location of the iNode in the
     *                iNodeTable.
     *
     * @return 0 on success, -1 on failure.
     */
    private int writeToBlock(short iNumber) {

        // Allocate a new buffer to store the block data
        byte[] buffer = new byte[BLKSIZE];

        // Checks if this iNode is pointing to null
        if(this.location == 0) {
            // If yes, a requests to the SuperBlock is made for a
            // free block
            short freeBlock = sb.getFreeBlock();

            // If the return value is a negative value, an error
            // message is displayed and the method returns -1
            if (freeBlock < 0) {
                System.out.print("Error: Disk is full");
                return -1;
            }

            // Sets location to point to the free block
            this.location = freeBlock;

            try {
                writeToTable(iNumber);
            } catch (Exception e) {
                System.out.println("Write to table error"
                        + e);
                return -1;
            }
        }

        // Clear the buffer
        byte[] byteBuffer = new byte[BLKSIZE];


        /*
         * There is data redundancy here, but by maintaining the
         * structure of the iNode it makes it easier to cache into
         * memory. Plus there is space for it.
         */

        // Copy the status to buffer
        byteBuffer[0] = this.status;

        // Copy the pointer to buffer
        byteBuffer[1] = (byte) (this.location >> 8);
        byteBuffer[2] = (byte) (this.location);

        // Copy the name to byteBuffer
        byte[] nameBuf = this.name.getBytes();
        for(int i = 3; i < 9; i++) {
            if (i - 3 < nameBuf.length) {
                byteBuffer[i] = nameBuf[i - 3];

            } else {
                byteBuffer[i] = 0;
            }
        }

        // Copy the Type to byteBuffer
        byteBuffer[9] = (byte) (this.type >> 8);
        byteBuffer[10] = (byte) (this.type);

        // Copy the open file count to byteBuffer
        byteBuffer[11] = (byte) (this.openCount >> 8);
        byteBuffer[12] = (byte) (this.openCount);

        // Copy the iNumber to the byteBuffer
        byteBuffer[13] = (byte) (this.iNumber >> 8);
        byteBuffer[14] = (byte) (this.iNumber);

        // Copy the size of the file to byteBuffer
        byteBuffer[15] = (byte) (this.size >> 8);
        byteBuffer[16] = (byte) (this.size);

        // Sets a counter for the following copies
        int j = 17;

        // Copy the creation date to byteBuffer
        byte[] date = this.cdate.getBytes();
        for (int i = 0; i < date.length; i++) {
            byteBuffer[j] = date[i];
            j++;
        }

        // Copy the last access date to byteBuffer
        date = this.adate.getBytes();
        for (int i = 0; i < date.length; i++) {
            byteBuffer[j] = date[i];
            j++;
        }

        // Copy the last modified date to byteBuffer
        date = this.mdate.getBytes();
        for (int i = 0; i < date.length; i++) {
            byteBuffer[j] = date[i];
            j++;
        }

        // Copy the reference to the direct access
        // blocks to byteBuffer
        for (int i = 0; i < 12; i++) {
            byteBuffer[j] = (byte) (this.direct[i] >> 8);
            byteBuffer[j + 1] = (byte) (this.direct[i]);
            j += 2;
        }

        // Copy the reference to the indirect access
        // block to byteBuffer
        byteBuffer[j] = (byte) (this.indirect >> 8);
        byteBuffer[j + 1] = (byte) (this.indirect);

        byteBuffer[j] = (byte) (this.numFiles >> 8);
        byteBuffer[j + 1] = (byte) (this.numFiles);
        // Write the modified byteBuffer back to the block
        // If disk write error: catch exception, display error
        // and return -1
        try {
            sb.disk.putBlock(this.location, byteBuffer);
        } catch (Exception e) {
            System.out.println("Disk write error "
                    + "@ INode.writeToTable(short iNumber): "
                    + e);
            return -1;
        }

        // If all operations successful return 0
        return 0;
    }


    /**
     * Expand
     *      This method is invoked if the size of file exceeds
     *      1,536 bytes (12 kilobytes). It requests a set of free
     *      blocks from the SuperBlock and allocates them to the
     *      indirect block expanding the file size to 9,728 bytes
     *      (76 kilobytes).
     *
     * @return 0 on success, -1 on failure.int j = 0
     *
     */
    private int Expand() {

        return 0;
    }

}
