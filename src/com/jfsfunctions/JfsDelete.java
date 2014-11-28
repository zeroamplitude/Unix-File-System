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
 * @author MILAN KORNICER
 */
public abstract class JfsDelete extends JfsInterface {
    @Override
    public int jfsDelete(String pathname) {

        /*
        ###############################################################
        THIS DELETES A FILE EVEN IF IT IS OPEN, DOES NOT REQUIRE IT TO BE CLOSED BEFORE HAND
        */


//        int inum = 0;
//        int fd = 0;
//        int type = 0;
//        int indexBlock = 0;
//
//        inum = traverse_file_sys(pathname); // this handles if the file exists or not
//        // returns -1 if the file doesn't exist or the actual inum which is > 0
//
//
//        if (inum > 0) {
//
//            type = getType(inum); // from inode
//
//            if (type == 1) {
//                // THIS IS A DIRECTORY MUST MAKE SURE THEY HAVE NO CHILDREN BEFORE DELETING
//
//
//            } else if (type == 0) {
//
//                // check if the file is open or not before deleting
//
//                fd = getFD(inum);
//                if (fd > 0) {
//                    // if the file is already open we need to close it b4 deleting
//
//                    remove(fd);
//
//                    indexBlock = getIndexBlock(inum);
//                    // remove everything that the inode is associated with
//                    // remove index block / and all data blocks
//                    // set all types to zero or null w.e.
//
//
//                } else {
//
//
//                    indexBlock = getIndexBlock(inum);
//                    // remove everything that the inode is associated with
//                    // remove index block / and all data blocks
//                    // set all types to zero or null w.e.
//
//
//                }
//
//
//            } else {
//                System.out.println("something went wrong");
//                return -1;
//            }
//
//        } else {
//
//            System.out.println("Could not delete the file");
//            return -1;
//
//        }


        return 0;
    }
}