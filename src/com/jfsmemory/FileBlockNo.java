package com.jfsmemory;

import com.jfsinternal.SuperBlock;

/**
 * Created by nicholas on 09/11/14.
 */
public class FileBlockNo {

    public static final int INODEBLOCKS = 8;

    int[][] fileBlockNo = new int[INODEBLOCKS][SuperBlock.NUMFILES];

    public FileBlockNo() {
        for (int i = 0; i < SuperBlock.NUMFILES; i++) {

        }
    }

}
