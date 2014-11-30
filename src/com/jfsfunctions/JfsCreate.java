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

import com.jfsinternal.INode;

/**
 * @author Nicholas De Souza
 *         <p/>
 *         TODO: Improve disk access by reading iNode Table to memory
 */
public class JfsCreate extends JfsInterface {

    private static final int NUMFILES = 64;
    private static final int BLKSIZE = 128;

    /**
     * jfsCreate:
     * This method takes the pathname and type specified by
     * the user and checks to make sure the file does not
     * exist on file. If no file exist it creates a new iNode
     * in the iNodeTable.
     *
     * @param pathname
     * @param type
     * @return
     * TODO: Merge all functions into one
     */
    @Override
    public int jfsCreate(String pathname, int type) {

        short Type = (short) type;

        String path = pathname;
        String delims = "/";
        String[] pathTok = path.split(delims);

        INode create = new INode(path, Type);

        int i = 0;
        while (i < pathTok.length) {



        }


        return 0;
    }

}
