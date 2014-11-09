package com.jfsmemory;

import com.jfsinternal.BlockIO;
import com.jfsinternal.DiskBitmap;
import com.jfsinternal.SuperBlock;

/**
 * Created by nicholas on 09/11/14.
 */
public class SuperBlkBuffer {


    int[] superBlkBuf;

    public SuperBlkBuffer() {
        superBlkBuf = new int[BlockIO.BLKSIZE];
    }

    public int updateBuff(int[] buffer) {
        superBlkBuf = buffer;
        return 0;
    }

    public int getBuffer(int i) {

        return superBlkBuf[i];
    }
}
