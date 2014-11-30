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

import com.jfsinternal.BlockIO;
import com.jfsinternal.INode;
import com.jfsinternal.JfsInternalConstants;

/**
 * Created by Nicholas De Souza on 27/11/14.
 */
public class WriteInodeTest implements JfsInternalConstants {
    public static void main(String[] args) {

        INode test = new INode("test", (short) 0, (short) 2);
//        System.out.println(test.status);
//        System.out.println(test.location);
//        System.out.println(test.name);
//        System.out.println(test.Type);


        test.writeToTable((short) 0);


        BlockIO disk = new BlockIO();

        byte[] buffer = new byte[BLKSIZE];

        disk.getBlock(0, buffer);

        String test1 = new String(buffer, 3, 6);

        System.out.println(test1);

    }


}
