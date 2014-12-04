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
import com.jfsmemory.*;

/**
 * @author <<Fill in your name here>>
 */
public class JfsOpen extends JfsInterface {

    JfsMemory memory;
    JfsDirectoryTree dt;
    SystemWideOpenFileTable swoft;
    INode iopen;

    public JfsOpen(JfsMemory memory) {
        this.memory = memory;
        this.dt = memory.getJfsDirectoryTree();
        this.swoft = memory.getSwoft();

    }


    @Override
    public synchronized int jfsOpen(String pathname) {


        // Tokenize the string
        String[] tokens = pathname.split("/");

        // validate path
        JfsDirectoryEntry iOpen = dt.traverseTree(tokens);
        if (iOpen.name.equals("ERROR")) {
            return -1;
        }

        // check to see if it exists
        int exists = swoft.checkFileStatus(iOpen.iNumber);

        iopen = new INode(iOpen.iNumber, tokens, exists);

        new Thread(new PerProcessOpenFileTable(iopen, memory)).run();

        return 0; // should be returning the file description
    }


}
