package com.jfsfunctions;

import com.jfsinternal.*;
/**
 *
 * @author Nicholas De souza
 */
public abstract class JfsInitialize implements JfsInterface {
    private BlockIO disk;
    private SuperBlock sb;
    public DiskBitmap bitmap;
    public IndirectBlock freeBlkList;
    final int NoOfBlocks = 512;
    final int BlockSize = 128;
    BlockIO ourBlockIO = new BlockIO();


    /**
     * @param erase
     * @return
     */
    public int jfsInitialize(int erase) {

        disk = new BlockIO();
        sb = new SuperBlock();
        return 0;
    }

    public int wipeDisk(){
        byte[] nullArray = new byte[BlockSize];
        byte nullByte = 00000000;

        for (int i = 0; i < BlockSize; i ++){
            nullArray[i] = nullByte;
        }

        for (int i = 0; i <  NoOfBlocks; i ++){
            ourBlockIO.putBlock(i, nullArray);
        }
        return 0;
    }
}
