package com.jfsfunctions;

/**
 * @author MILAN KORNICER
 */
public abstract class JfsRead implements JfsInterface {
    public int jfsRead(int fd, int start, int length, String mem_pointer) {
        /*
        ##########################################################3
        to read a file it must already be opened

         */


        if (fd >= 0 && fd < MAX_FILES && start >=0 && length> 0) // cant enter fd greater then 64 since this might cause an array out of bounds error
        {
            if (checkExists(fd) == -1) //checks if the file with fd association exists, returns 0 if everything is good
            {
                return -1; // in sfs_test once this returns -1 the file was not able to open

            } else
            {

                file = system_open(fd);

                // file type

                if ()
            }
























        return 0;
    }
}
