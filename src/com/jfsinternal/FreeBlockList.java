package com.jfsinternal;

/**
 * Created by 100488265 on 22/11/2014.
 */
class FreeBlockList implements JfsInternalConstants {

//    public short numPtrs = (BLKSIZE /Short.SIZE - 1);
    public short nextFreeBlock;
//    public short[] freeblocks = new short[numPtrs];


    public FreeBlockList(short freeBlockHead) {
        nextFreeBlock = (short) (freeBlockHead + 1);
    }
}


//        nextFreeBlock = (short)(freeBlockHead + numPtrs);

//        short i = 0;
//        short location =
//        while(i < numPtrs) {
//
//            if ()
//
//        }