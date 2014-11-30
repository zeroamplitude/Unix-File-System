package com.jfsmemory;

import com.jfsinternal.INode;
import com.jfsinternal.SuperBlock;


/**
 * Created by Nicholas De Souza on 29/11/14.
 */
public class JfsMemory {

    public SuperBlock sb;
    public INode iNode;
    public SystemFileTable sft;
    public JfsDirectoryTree jfsDirectoryTree;

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

    public void setiNode(INode iNode) {

        this.iNode = iNode;

    }

    public INode getInode() {

        return iNode;

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


}
