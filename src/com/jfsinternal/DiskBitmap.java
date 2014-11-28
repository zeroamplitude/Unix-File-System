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
 * Created by nicholas on 08/11/14.
 */
public class DiskBitmap {

    public byte[] bitmap;

    public DiskBitmap(){
        bitmap = new byte[BlockIO.NUMBLKS];
    }

    public int putBit(int blocknum, byte block) {
        try {
            bitmap[blocknum] = block;
        } catch (Exception e) {
            System.out.println("Put bit error: " + e);
            return -1;
        }
        return 0;
    }

    public int getBit(int i) {
        return bitmap[i];
    }

}
