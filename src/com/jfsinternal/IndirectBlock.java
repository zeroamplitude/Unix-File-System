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
 * Created by Nicholas De Souza on 09/11/14.
 * Modified by Nicholas De Souza on 23/11/14.
 */
public class IndirectBlock implements JfsInternalConstants {

    public short indirect[] = new short[NUMINDIRECT];

    public IndirectBlock(short[] blocks) {
        for (int i = 0; i < blocks.length; i++) {
            indirect[i] = blocks[i];
        }
    }

//    public IndirectBlock() {
//        for (int i = 0; i < SuperBlock.freeBlockCount; i++){
//
//        }
//    }
}
