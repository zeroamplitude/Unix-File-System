package com.jfsmemory;

import com.jfsinternal.SuperBlock;


/**
 * Created by Nicholas De Souza on 29/11/14.
 */
public class JfsMemory {

    public SuperBlock sb;
    public SystemWideOpenFileTable swoft;
    public JfsDirectoryTree jfsDirectoryTree;
    public INodeTable iNodeTable;

    private static JfsMemory memory = new JfsMemory();

    private JfsMemory() {
    }

    public static JfsMemory getInstance() {
        return memory;
    }

    public void setSb(SuperBlock sb) {

        this.sb = sb;

    }

    public SuperBlock getSb() {
        return sb;
    }

    public SuperBlock getSb(short newFlag) {

        return sb;

    }

    public void setSwoft(SystemWideOpenFileTable swoft) {

        this.swoft = swoft;

    }

    public SystemWideOpenFileTable getSwoft() {

        return swoft;

    }

    public void setJfsDirectoryTree(JfsDirectoryTree jfsDirectoryTree) {

        this.jfsDirectoryTree = jfsDirectoryTree;

    }

    public JfsDirectoryTree getJfsDirectoryTree() {

        return jfsDirectoryTree;

    }

    public void setiNodeTable(INodeTable iNodes) {

        this.iNodeTable = iNodes;

    }

    public INodeTable getiNodeTable() {
        return iNodeTable;
    }

}
