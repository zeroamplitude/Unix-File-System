package com.jfsinternal;

/**
 * Created by Nicholas De Souza on 23/11/14.
 */

public interface JfsInternalConstants {

    /* The total size of the disk in blocks */
    static final short NUMBLKS = BlockIO.NUMBLKS;

    /*The size of each block on disk */
    static final short BLKSIZE = BlockIO.BLKSIZE;

    /* Number of files on disk */
    static final short NUMFILES = 64;

    /* Size of the iNode Table in blocks */
    static final short INODETBLSIZE = 8;

    static final short INODETBLSTART = 2;

    /* Size of an individual iNode */
    static final short INODESIZE = INODETBLSIZE / NUMFILES;

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
