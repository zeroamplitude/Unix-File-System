package com.jfsinternal;

import com.jfsmemory.DirectoryTree;
import com.jfsmemory.JfsMemory;
import com.jfsmemory.SystemWideOpenFileTable;

import java.util.Date;

/**
 * Created by Nicholas De Souza on 04/12/14.
 */
public class Inode {

    // Memory components
    private JfsMemory memory = JfsMemory.getInstance();
    private SystemWideOpenFileTable swoft = memory.getSwoft();
    private SuperBlock sb = memory.getSb();
    private DirectoryTree dt;


    // Integrity checking components
    protected int magic;            // This indicates whether the creation of and iNode
    // has been successful or not

    protected short magic2;         // Checks the integrity of the iNumber provided by
    // the SuperBlock

    protected Inode magic3;         // magic3 indicates whether the iNode being created has
    // a valid path and will store the iNodes parent iNode.


    // iNode structural attributes
    private byte status;            // Indicates if the iNode is available

    private short location;         // References the location of the iNode on disk

    private String name;            // The given name of the iNode

    private short type;             // The given type of the iNode

    private short iNumber;          // The iNumber of the iNode

    private short numFiles;         // Keeps count of the number of files
    // if the iNode is a directory

    private short size;             // Tracks the size of an iNode in bytes

    private String cdate;           // Tracks the creation date of an iNode

    private String adate;           // Tracks the last accessed date of an iNode

    private String mdate;           // Tracks the last modified date of an iNode

    private short direct[];         // References the location of the direct blocks
    // on disk

    private short indirect;         // References the location of the indirect block
    // on disk


    public Inode(String[] tokens, int type) {

        this.magic3 = new Inode(tokens[tokens.length - 2]);

        this.status = (byte) 1;     // On construction of a new iNode set status to 1

        if (type == 1) {            // If type is a directory allocate a block for the iNode

            this.location = sb.getFreeBlock();

        } else {                    // otherwise, set the location to reference null

            this.location = 0;

        }

        this.name = tokens[tokens.length - 1];        // Set the name of the iNode

        this.type = (short) type;   // Set the type of iNode 0 for file, 1 for directory

        try {                       // Request a free iNode from the SuperBlock

            this.iNumber = sb.getFreeINode();       // Set the iNumber for the iNode from the
            // return of SuperBlock's allocation method

            this.magic2 = this.iNumber;

            this.magic = 0;         // Set magic to 0 for success in generating a new
            // iNumber
        } catch (Exception e) {

            this.magic = -1;        // Set magic to -1 for a failure in generating a new
            // iNumber
        }

        if (this.magic2 >= 0 && this.magic == 0) {  // Check to see if both integrity checks pass

            this.numFiles = 0;      // Set the number of files to zero

            this.size = 0;          // Set the size of the iNode to zero

            this.cdate = new Date().toString();     // Set the creation date to today

            this.adate = new Date().toString();     // Set the last accessed date to today

            this.mdate = new Date().toString();     // Set the last modified date to today

            this.direct = new short[12];            // Set the direct blocks to point to null

            this.indirect = 0;                      // Set the indirect block to point to null

        } else {                    // If either check does not pass

            this.magic = -1;        // Set magic to a negative value representing a invalid iNode

        }


        // This part of the iNode's constructor finds the parent iNode and has its first available
        // data block reference this iNode.
        if (magic == 0) {           // Always check to see if the iNode has been created successfully


        }
    }


    public Inode(String name) {

    }

}
