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
import com.jfsmemory.JfsDirectoryEntry;
import com.jfsmemory.JfsDirectoryTree;
import com.jfsmemory.JfsMemory;
import com.jfsmemory.SystemWideOpenFileTable;

import static com.jfsinternal.JfsInternalConstants.FLAGS;

/**
 * @author Nicholas De Souza
 */
public class JfsDelete extends JfsInterface {
    JfsMemory memory;
    JfsDirectoryTree dt;
    SystemWideOpenFileTable swoft;


    public JfsDelete(JfsMemory memory) {
        memory = JfsMemory.getInstance();
        dt = memory.getJfsDirectoryTree();
        swoft = memory.getSwoft();
    }



    @Override
    public int jfsDelete(String pathname) {

        // Parse pathname
        String[] tokens = pathname.split("/");

        // Check if file exist in the directoryTree
        // while, geting the iNumber;
        JfsDirectoryEntry iDelete = dt.traverseTree(tokens, FLAGS.CHECK);
        if (iDelete.name.equals("ERROR")) {
            return -1;
        }

        // If exists in directoryTree check for instances in swoft.
        int exists = swoft.checkFileStatus(iDelete.iNumber);

        // Send to INode to handle deletion.
        INode delete = new INode(iDelete, tokens, exists);



        return 0;
    }
}