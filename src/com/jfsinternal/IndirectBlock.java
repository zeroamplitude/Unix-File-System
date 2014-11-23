package com.jfsinternal;

/**
 * Created by Nicholas De Souza on 09/11/14.
 * Modified by Nicholas De Souza on 23/11/14.
 */
public class IndirectBlock implements InternalConstants{

    public short indirect[] = new short[NUMINDIRECT];

    public IndirectBlock(short[] blocks) {
        for (int i = 0; i < blocks.length; i++) {
            indirect[i] = blocks[i];
        }
    }

    public IndirectBlock() {
        for (int i = 0; i < SuperBlock.freeBlockCount; i++){

        }
    }
}
