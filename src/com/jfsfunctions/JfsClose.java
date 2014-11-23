package com.jfsfunctions;
// NEED TO INCLUDE THE SYSTEM FILE TABLE.
// so the functions below are not red


/**
 * @author MILAN KORNICER
 */
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
