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


public abstract class JfsClose implements JfsInterface {
    public int jfsClose(int fd) {

        /*
        ####################################################
        to close a file the teacher must have the fd. EXPLAIN IN (jfsOPEN)
        ----------------------------------------------------

        fd can only be a number between 0 and 64 ( max size of files )

        to 'close' a file you only need to remove it from the systemFileTable
            - since no other operation (read/write) can be performed on a file that
            does not have a fd which is removed and assigned by the systemfileTable
            - this is a CHEEKY simple method of closing and opening files


        ####################################################
*/
        if (fd >= 0 && fd < MAX_FILES) // cant enter fd greater then 64 since this might cause an array out of bounds error
        {
            if (checkExists(fd) == -1) //checks if the file with fd association exists, returns 0 if everything is good
            {
                return -1; // in sfs_test once this returns -1 the file was not able to open

            } else
            {
                remove(fd);
                return 0; //(BACK TO SFS_TEST) if 0 FILE SUCCESSFULLY DELETED
            }

        }


        return 0;
    }
}
