package com.jfsmemory;

import com.jfsinternal.INode;

/**
 * Created by Nicholas De Souza on 02/12/14.
 */
public class PerProcessOpenFileTable implements Runnable {

    public SystemWideOpenFileTable fd = null;
    private INode iNode;
    private SystemWideOpenFileTable swoft;

    public PerProcessOpenFileTable(INode iNode, JfsMemory memory) {
        this.iNode = iNode;
        this.swoft = memory.getSwoft();
    }

    @Override
    public void run() {
        swoft.addEntry(iNode);
    }
}
