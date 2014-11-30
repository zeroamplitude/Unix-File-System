package com.jfsmemory;

import com.jfsinternal.SuperBlock;


/**
 * Created by Nicholas De Souza on 29/11/14.
 */
public class JfsMemory {

    protected SuperBlock sb;
    protected SystemFileTable sft;
    protected JfsDirectoryTree jfsDirectoryTree;


    public JfsMemory() {

        sb = null;
        sft = null;
        jfsDirectoryTree = null;

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
