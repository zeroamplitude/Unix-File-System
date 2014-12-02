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
import com.jfsmemory.JfsMemory;

/**
 * @author Nicholas De Souza
 *
 */
public class JfsCreate extends JfsInterface {

    private static final int NUMFILES = 64;
    private static final int BLKSIZE = 128;
    private JfsMemory memory;


    public JfsCreate(JfsMemory memory) {
        this.memory = memory;
    }

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
     *
     * TODO: Link direct blocks in directories
     * TODO: Validate file type
     *
     *
     */
    @Override
    public int jfsCreate(String pathname, int type) {

        String[] tokens = pathname.split("/");

        // Initialize root iNode
        INode create = new INode(tokens, (short) type);

        if (create.getMagic() < 0) {

            System.out.println("iNode creation error "
                    + "@JfsCreate(String pathname, int type)");
            return -1;

        }

        return 0;

    }

}
