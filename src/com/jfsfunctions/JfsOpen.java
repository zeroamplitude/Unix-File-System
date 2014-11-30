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

import com.jfsmemory.JfsMemory;

/**
 * @author <<Fill in your name here>>
 */
public class JfsOpen extends JfsInterface {

    JfsMemory memory;


    public JfsOpen(JfsMemory memory) {

        this.memory = memory;

    }


    @Override
    public int jfsOpen(String pathname) {
//
//        /*
//        #####################################################
//        sfs_open gets called from the SFS test
//        the teacher will input a pathname that starts from ROOT
//            - root/home/lol/file
//        a function (traverse_file_sys) must be created to go through the whole file system and find the file
//                - this function (traverse_file_sys) returns -1 if it cant find the file
//                - if this function finds the right file and it returns the inum of that file
//        after the inum is received it is sent to addFile
//        the add file RETURNS THE fd BECAUSE if you look at CLOSE/READ/WRITE THEY ALL INVOLVE THE TEACHER USING the FD
//        PATH NAME IS ONLY USED TO OPEN THE FILE AFTER THAT ONLY FD is used.
//
//        example : FROM C
//        case 'c':
//			 Close a file
//        printf("Enter file descriptor number: "); // SHE WILL BE ENTERING FD
//        scanf("%d", &p1);
//        retval = sfs_close(p1);
//        if (retval > 0) {
//            printf("sfs_close succeeded.\n");
//        } else {
//            printf("Error.  Return value was %d\n", retval);
//        }
//        break;
//
//
//        #######################################################
//        */
//        int inum = 0;
//
//        inum = traverse_file_sys(pathname); // this traverse file system should go through the whole file system and return the inode block
//
//        if (inum == -1) {
//          send error that the disk location could not be read
//          return -1;
//        } else {
//         return addFile(inum) // this returns a FD which is used to immediately access the file that they just opened
//
//        }
//
//
//
//        // left this here to just not cause errors in intellJ
        return 0; // should be returning the file description
    }


}
