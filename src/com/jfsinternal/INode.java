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

import java.util.Date;
import static java.lang.Math.floor;

/**
 * Created by Nicholas De Souza on 26/11/14.
 */
public class INode implements JfsInternalConstants{

    private BlockIO disk = new BlockIO();
    private SuperBlock  sb = new SuperBlock();

    protected byte    status;
    protected short   location;
    protected String  name;
    protected short   Type;
    protected short   openCount;
    protected short   iNumber;
    protected short   size;
    protected String  cdate;
    protected String  adate;
    protected String  mdate;
    protected short   direct[];
    protected short   indirect;


    public INode(byte[] buffer) {
        this.status = buffer[0];
        this.location = (short) (((buffer[1] & 0x00ff) << 8)
                + (buffer[2] & 0x00ff));
        this.name = new String(buffer, 3, 6);
        this.Type = (short) (((buffer[9] & 0x00ff) << 8)
                + (buffer[10] & 0x00ff));
        this.openCount = (short) (((buffer[11] & 0x00ff) << 8)
                + (buffer[12] & 0x00ff));
        this.iNumber = (short) (((buffer[13] & 0x00ff) << 8)
                + (buffer[14] & 0x00ff));
        this.size = (short) (((buffer[15] & 0x00ff) << 8)
                + (buffer[16] & 0x00ff));
        this.cdate = new String(buffer, 17, 10);
        this.adate = new String(buffer, 27, 10);
        this.mdate = new String(buffer, 37, 10);
        for (int i = 0; i < 12; i++) {
            this.direct[i] = (short) (((buffer[47+i] & 0x00ff) << 8)
                    + (buffer[47+i] & 0x00ff));
        }
        this.indirect = (short) (((buffer[58] & 0x00ff) << 8)
                + (buffer[58] & 0x00ff));
    }


    /**
     * INode Constructor
     *      This is the first constructor of an iNode when
     *      a file is creates. This will set the values of
     *      the iNode in the iNode table.
     *
     * @param name      A 6 character string value that repr-
     *                  esnts the name of the file.
     *
     * @param Type     A short integer value that represents
     *                 the iNodes type. 0 is a file, 1 is a
     *                 directory.
     */
    public INode(String name, short Type) {
        this.status = (byte) 1;     // 1 byte
        this.location = 0;          // 2 bytes
        this.name = name;           // 6 bytes
        this.Type = Type;           // 2 bytes
        this.openCount = 0;         // 2 bytes
                 // Total ITableNode: 13 bytes + R: 3 bytes = 16 bytes
        try {
//            this.iNumber = sb.getFreeINode();
        } catch (Exception e) {

        }

        this.size = 0;
        this.cdate = new Date().toString();
        this.adate = new Date().toString();
        this.mdate = new Date().toString();
        this.direct = new short[12];
        this.indirect = 0;
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
    public int writeToTable(short iNumber) {

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

        // Copy the status to buffer
        buffer[offset] = this.status;

        // Copy the pointer to buffer
        buffer[offset+1] = (byte)(this.location >> 8);
        buffer[offset+2] = (byte)(this.location);

        // Copy the name to buffer
        byte[] nameBuf = name.getBytes();
        for(int i = 3; i < 9; i++) {
            if (i < nameBuf.length) {
                buffer[offset+i] = nameBuf[i-3];
            } else {
                buffer[offset+i] = 0;
            }
        }

        // Copy the Type to buffer
        buffer[offset+9] = (byte)(this.Type >> 8);
        buffer[offset+10] = (byte)(this.Type);

        // Copy the open count to buffer
        buffer[offset+11] = (byte)(this.openCount >> 8);
        buffer[offset+12] = (byte)(this.openCount);

        // Write the modified buffer back to the block
        // If disk write error: catch exception, display error
        // and return -1
        try {
            disk.putBlock(block, buffer);
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
    public int writeToBlock(short iNumber) {

        // Allocate a new buffer to store the block data
        byte[] buffer = new byte[BLKSIZE];

        // Checks if this iNode is pointing to null
        if(this.location == 0) {
            // If yes, a requests to the SuperBlock is made for a
            // free block
            short freeBlock = (short)0; //sb.getFreeBlock();

            // If the return value is a negative value, an error
            // message is displayed and the method returns -1
            if (freeBlock < 0) {
                System.out.print("Error: Disk is full");
                return -1;
            }

            // Sets location to point to the free block
            this.location = freeBlock;

            // Calculate the iNodeTable block position
            short block = (short) (floor((iNumber * INODESIZE)
                    / BLKSIZE) + INODETBLSTART);

            // Calculate the offset inside the iNodeTable block
            short offset = (short) ((iNumber * INODESIZE) % BLKSIZE);

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

            // Copies the location to the buffer
            buffer[offset+1] = (byte)(this.location >> 8);
            buffer[offset+2] = (byte)(this.location);

            // Writes the iNodeTable Block back to disk
            // If disk write error: catch exception, display error,
            // and return -1
            try {
                disk.putBlock(block, buffer);
            } catch (Exception e) {
                System.out.println("Disk write error "
                        + "@ INode.writeToBlock(short iNumber) "
                        + e);
                return -1;
            }
        }

        // Clear the buffer
        buffer = new byte[BLKSIZE];


        /*
         * There is data redundancy here, but by maintaining the
         * structure of the iNode it makes it easier to cache into
         * memory. Plus there is space for it.
         */

        // Copy the status to buffer
        buffer[0] = this.status;

        // Copy the pointer to buffer
        buffer[1] = (byte)(this.location >> 8);
        buffer[2] = (byte)(this.location);

        // Copy the name to buffer
        byte[] nameBuf = name.getBytes();
        for(int i = 3; i < 9; i++) {
            if (i < nameBuf.length) {
                buffer[i] = nameBuf[0];
            } else {
                buffer[i] = 0;
            }
        }

        // Copy the Type to buffer
        buffer[9] = (byte)(this.Type >> 8);
        buffer[10] = (byte)(this.Type);

        // Copy the open file count to buffer
        buffer[11] = (byte)(this.openCount >> 8);
        buffer[12] = (byte)(this.openCount);

        // Copy the iNumber to the buffer
        buffer[13] = (byte)(this.iNumber >> 8);
        buffer[14] = (byte)(this.iNumber);

        // Copy the size of the file to buffer
        buffer[15] = (byte)(this.size >> 8);
        buffer[16] = (byte)(this.size);

        // Sets a counter for the following copies
        int j = 17;

        // Copy the creation date to buffer
        byte[] date = this.cdate.getBytes();
        for (int i = 0; i < date.length; i++) {
            buffer[j] = date[i];
            j++;
        }

        // Copy the last access date to buffer
        date = this.adate.getBytes();
        for (int i = 0; i < date.length; i++) {
            buffer[j] = date[i];
            j++;
        }

        // Copy the last modified date to buffer
        date = this.mdate.getBytes();
        for (int i = 0; i < date.length; i++) {
            buffer[j] = date[i];
            j++;
        }

        // Copy the reference to the direct access
        // blocks to buffer
        for (int i = 0; i < 12; i++) {
            buffer[j] = (byte)(this.direct[i] >> 8);
            buffer[j+1] = (byte)(this.direct[i]);
            j += 2;
        }

        // Copy the reference to the indirect access
        // block to buffer
        buffer[j] = (byte)(this.indirect >> 8);
        buffer[j+1] = (byte)(this.indirect);


        // Write the modified buffer back to the block
        // If disk write error: catch exception, display error
        // and return -1
        try {
            disk.putBlock(this.location, buffer);
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
     *      blocks from the superblock and allocates them to the
     *      indirect block expanding the file size to 9,728 bytes
     *      (76 kilobytes).
     *
     * @return 0 on success, -1 on failure.
     */
    private int Expand() {

        return 0;
    }

    /**
     * readFromBlock
     *      This method locates the iNode, specified by the
     *      iNumber, reads it from disk, caches it into memory.
     *
     * @param iNumber A short integer value that represents the
     *                index of an iNode in the iNodeTable.
     *
     * @return 0 on success, -1 on failure.
     */
    public int readFromBlock(short iNumber) {

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

        short location = (short) (((buffer[1] & 0x00ff) << 8)
                + (buffer[2] & 0x00ff));

        // Clear the buffer to store the iNode data
        buffer = new byte[BLKSIZE];

        // Read the iNodeTable block from disk
        // If disk read error: catch exception, display error,
        // and return -1
        try {
            disk.getBlock(location, buffer);
        } catch (Exception e) {
            System.out.println("Disk read error "
                    + "@ INode.readFromBlock(short iNumber)"
                    + e);
            return -1;
        }

        INode open;
        try {
            open = new INode(buffer);
        } catch (Exception e) {
            System.out.println("iNode open error "
                    + "@ INode.readFromBlock(short iNumber)"
                    + e);
            return -1;
        }

        return open.iNumber;
    }

    public int readFromTable(String pathname) {


        return 0;
    }
}
