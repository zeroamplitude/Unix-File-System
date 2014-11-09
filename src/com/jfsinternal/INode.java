package com.jfsinternal;

/**
 * Created by nicholas on 08/11/14.
 */
public class INode {

    public final static int SIZE = 64;
    int flags;
    int owner;
    int fileSize;
    public int[] pointer;

    /**
     * Constructor class
     */
    public INode() {

    }


}
