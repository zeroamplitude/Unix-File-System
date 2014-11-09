package com.jfsinternal;

/**
 * Created by nicholas on 09/11/14.
 */
public class IndirectBlock {
    public int pointer[] = new int[BlockIO.BLKSIZE/4];

    public IndirectBlock() {
        makeIndirectBlock();
    }

    public void makeIndirectBlock() {
        for (int i = 0; i < BlockIO.BLKSIZE/4; i++) {
            pointer[i] = 0;
        }
    }
}
