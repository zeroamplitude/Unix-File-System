package com.jfsmemory;

import com.jfsinternal.BlockIO;
import com.jfsinternal.DiskBitmap;

/**
 * Created by nicholas on 09/11/14.
 */
public class SuperBlkBuffer {


    int[] superBlkBuf = new int[BlockIO.BLKSIZE];

    public SuperBlkBuffer(DiskBitmap bitmap) {
        //for (int i = 0; i < BlockIO.NUMBLKS; i = i + 4) {
            superBlkBuf[0] = bitmap.encodeBitmap(0);
        //}
    }

    public int updateBuff() {

        return 0;
    }

    public int getBuffer(int i) {
        return superBlkBuf[i];
    }
}
