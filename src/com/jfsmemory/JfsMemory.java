package com.jfsmemory;

import com.jfsinternal.SuperBlock;


/**
 * Created by Nicholas De Souza on 29/11/14.
 */
public class JfsMemory {

    public SuperBlock sb;
    public SystemFileTable sft;
    public JfsDirectoryTree jfsDirectoryTree;
    public INodeTable iNodeTable;

    private static JfsMemory memory = new JfsMemory();

    private JfsMemory() {
    }

    public static JfsMemory getInstance() {
        return memory;
    }

//    public static JfsMemory createJfsMemory() {
//        return new JfsMemory();
//    }

    public void setSb(SuperBlock sb) {

        this.sb = sb;

    }

    public SuperBlock getSb() {
        return sb;
    }

    public SuperBlock getSb(short newFlag) {

        return sb;

    }

    public void setSft(SystemFileTable sft) {

        this.sft = sft;

    }

    public SystemFileTable getSft() {

        return sft;

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
