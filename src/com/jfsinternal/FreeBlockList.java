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
 * Created by 100488265 on 22/11/2014.
 */
class FreeBlockList implements JfsInternalConstants {

//    public short numPtrs = (BLKSIZE /Short.SIZE - 1);
    public short nextFreeBlock;
//    public short[] freeblocks = new short[numPtrs];


    public FreeBlockList(short freeBlockHead) {
        nextFreeBlock = (short) (freeBlockHead + 1);
    }
}


//        nextFreeBlock = (short)(freeBlockHead + numPtrs);

//        short i = 0;
//        short location =
//        while(i < numPtrs) {
//
//            if ()
//
//        }