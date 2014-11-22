package com.jfsinternal;

/**
 * Created by nicholas on 09/11/14.
 */
public class Block {

    byte[] block = new byte[BlockIO.BLKSIZE];

    public Block(){
        for (int i = 0; i < BlockIO.BLKSIZE; i++) {
            block = null;
        }

    }

    public byte[] getBlock() {
        return block;
    }
}
