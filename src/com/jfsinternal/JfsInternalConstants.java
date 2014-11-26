package com.jfsinternal;

/**
 * Created by Nicholas De Souza on 23/11/14.
 */

public interface JfsInternalConstants {

    /* The total size of the disk in blocks */
    static final short NUMBLKS = 512;

    /*The size of each block on disk */
    static final short BLKSIZE = 128;

    /* Number of files on disk */
    static final short NUMFILES = 64;

    /* Size of the iNode Table in blocks */
    static final short SIZEOFINODETABLE = 8;

    /* Size of an individual iNode */
    static final short INODESIZE = SIZEOFINODETABLE / NUMFILES;

    static final short NUMDIRECT = 12;

    static final short NUMINDIRECT = BlockIO.BLKSIZE / Short.SIZE;

    static final short NUMDINDIRECT = NUMINDIRECT * NUMINDIRECT;

    static final short SIZEOFPATH = 6;

    static enum Type{
        FREEINODE,
        FILE,
        DIRECTORY
    }

}
