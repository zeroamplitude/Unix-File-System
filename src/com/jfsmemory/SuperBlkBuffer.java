package com.jfsmemory;

import com.jfsinternal.BlockIO;

/**
 * Created by nicholas on 09/11/14.
 */
public class SuperBlkBuffer {


    byte[] superBlkBuf;

    public SuperBlkBuffer() {
        superBlkBuf = new byte[BlockIO.BLKSIZE];
    }

    public int updateBuff(byte[] buffer) {
        superBlkBuf = buffer;
        return 0;
    }

    public byte[] getBuffer() {
        return superBlkBuf;
    }
}
