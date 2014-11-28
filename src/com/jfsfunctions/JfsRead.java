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
 * @author <<Fill in your name here>>
 */
public class JfsRead extends JfsInterface {
    @Override
    public int jfsRead(int fd, int start, int length, String mem_pointer) {


        byte data = 0;
        int startLoc = 0;
        String allData = "";
        if (fd >= 0 && fd < 64 && start >=0 && length > 0){

            if (checkExists(fd) == 0) {
                // means the file is in the table





                int inum = systemOpenFile(fd);


                // this function returns the inum of the associated file

                /*
                So now we have a fd, and the iNumber of the file in question (that needs to be read)

                how does one access the inode and actually read /

                ^^^^^^what method needs to be called?
                 */
                INode file = new INode();
                //^^ that was used before

                if (file.type == 1) {
                    // THIS IS A DIRECTORY
                    return -1;
                }

                 // using the function from INode that should read all the necessary information
                allData = readFromBlock(inum);
                System.out.println(allData);

                // this could work in theory, but needs substaintially inode function work

            } else {
                // please open the file you wish to read first
                return -1;
            }





























        }







        // invalid file descriptor OR reading from bad location
        return 0;
    }
}
