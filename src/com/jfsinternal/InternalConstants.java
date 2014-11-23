package com.jfsinternal;

import static com.jfsinternal.BlockIO.*;

/**
 * Created by Nicholas De Souza on 23/11/14.
 */
public interface InternalConstants {

    static final int NUMFILES = 64;

    static final int SIZEOFPATH = 6;


    /* Size of the iNode Table in blocks */
    static final int SIZEOFINODETABLE = 8;

    /* Size of an individual iNode */
    static final int  SIZEOFINODE = SIZEOFINODETABLE / NUMFILES;

    static final int NUMDIRECT = 12;

    static final int NUMINDIRECT = BLKSIZE / Short.SIZE;

    static final int NUMDINDIRECT = NUMINDIRECT * NUMINDIRECT;


}
