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

package com.jfsmemory;

/**
 * Created by 100487498 on 09/11/2014.
 *
 * create() and delete() are only available commands for files that are not in systemfiletable()
 * delete() needs to close() file in systemfiletable() and free blocks and then deletefile()
 *
 * open() adds file to systemfiletable
 * close() removes file from systemfiletable
 * read() can only be done with an already open file
 * write() can only be done with an already open file
 */
public class SystemFileTable {
    public static final int MAX_FILES = 64;
    public int bitmap[];
    public int inode_location[] = new int[64];


    public SystemFileTable() { }

    // before this we need to check if the file you are trying to open is already open or we will have multiple open files
    public int addFile(int block){
        /* to add a file into the system open table -- opening a file
            1. file must exist ( have been created )
            2. file must not already be opened in system file table
            3. there must be space in the system open file table
            4. find a spot available in index bitmap
                - if there isn't space, error (NO SPACE)
                - if there is space 5.
                5. get the exact location
                6. get the inode (FCB) that is being added -- fd
                7. put that inode address into the system open table
         */

        int fd = 0;
        fd = findSpace();
        if (fd == -1) {
            return -1; // throws error that the table is already full ie. cant open more than 64 files
        }
        else {
            bitmap[fd] = 1; // location is taken
            // fd[location] = get_inum(block); // store the inode (FCB) in the system open table
        }

        return fd; // this is the 'fd'
    }


    public int findSpace() {
        for (int i = 0; i < MAX_FILES; i++) {
            if (bitmap[i] == 0)
                return i;
        }
        return -1; // throws error that the table is already full ie. cant open more than 64 files
    }

    int checkExists(int fd) {
        for (int i = 0; i < MAX_FILES; i++){
            if (bitmap[i] == 1){
                if (inode_location[i]==fd) {
                    return 0;
                }
            }
        }
        return -1; // can't read/write since the file is not open
    }

    public void remove(int fd) {// essentially close file
        // to remove (close a file)
        // get the file you want to close
        // search through for a spot that is taken

        for (int i = 0; i < MAX_FILES; i++){
            if (bitmap[i] == 1){
                if (inode_location[i]==fd) {
                    bitmap[i] = 0;
                    inode_location[i] = 0;
                }
            }
        }

    }
}
