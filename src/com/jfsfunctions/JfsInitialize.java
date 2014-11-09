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

    /**
     * @param erase
     * @return
     */
    public int jfsInitialize(int erase) {
        disk = new BlockIO();
        sb = new SuperBlock();























        return 0;
    }
}
