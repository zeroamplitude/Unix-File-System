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

    /* SuperBlock size in blocks */
    static final short SUPERBLKSIZE = 11;

    static final short IMAP = 10;

    /* Size of the iNode Table in blocks */
    static final short INODETBLSIZE = 8;

    static final short INODETBLSTART = 2;

    static final short INODETBLBLKSIZE = 8;

    /* Size of an individual iNode in the iNode table*/
    static final short INODESIZE = 16;

    static final short NUMDIRECT = 12;

    static final short NUMINDIRECT = BlockIO.BLKSIZE / Short.SIZE;

    static final short NUMDINDIRECT = NUMINDIRECT * NUMINDIRECT;

    static final short SHORTSIZE = 2;

    static final short SIZEOFPATH = 6;

    static enum Type{
        FREEINODE,
        FILE,
        DIRECTORY
    }

    static enum FLAGS {
        CHECK,
        ADD,
        REMOVE
    }
}
