package com.jfsmemory;

import com.jfsinternal.INode;

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

    private int index = 0;

    public SystemFileTable() {

    }

    /*
        public int add(INode iNode, int inumber, int fd) {
            if (bitmap[fd] != 0)

        }
        */
    public int find_space() {
        for (int i = 0; i < MAX_FILES; i++) {
            if (bitmap[i] == 0)
                return index;
            else
                return -1; // needs to be an error
        }
        return 0;
    }
}
