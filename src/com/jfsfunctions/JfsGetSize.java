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

package com.jfsfunctions;

/**
 * sfs_getsize(char *pathname): If the specified file is a regular file, this function should return the number of
 * bytes in the file. If it is a directory file, this function should return the number of directory entries in the file.
 */
public class JfsGetSize extends JfsInterface {
    @Override
    public int jfsGetSize(String pathname) {
        int fileSize = 0;
        int inum = 0;

        // does not need to invoke open file table

        // get the name of file from pathname
        inum = traverseFS(pathname); // returns inumber of pathname file
        // ?? create new object ??
        INode file = new INode();

        fileSize = file.getSize(inum); // ?? i don't know if this will work

        return fileSize;
    }
}
