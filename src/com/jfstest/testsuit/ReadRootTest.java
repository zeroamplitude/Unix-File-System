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

/**
 * Created by nicholas on 26/11/14.
 */
public class ReadRootTest {
    public static void main(String[] args) {
        BlockIO disk = new BlockIO();
        byte[] buffer = new byte[128];

        disk.getBlock(2, buffer);

//        String trial = "/";
//        byte[] t2b = trial.getBytes();
//
//        buffer[3] = t2b[0];
//
//        //System.out.println(buffer.toString());
//
//
        byte status = buffer[0];
//
//        disk.putBlock(0, buffer);


        short location = (byte) (((buffer[1] & 0x00ff) << 8)
                + (buffer[2] & 0x00ff));


        String name = new String(buffer, 3, 6);


        short nextblk1 = (short) (((buffer[14] & 0x00ff) << 8)
                + (buffer[15] & 0x00ff));

        System.out.println(status);
        System.out.println(location);
        System.out.println(name);
        System.out.println(nextblk1);
    }
}
