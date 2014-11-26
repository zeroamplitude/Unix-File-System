package com.jfsinternal;

/**
 * Created by nicholas on 26/11/14.
 */
public class INode implements JfsInternalConstants{
    public short iNumber;
    public short size;
    public short Type;
    public char[] name;

    public short direct[] = new short[11];
    public short indirect[];

    INode(short location) {

        this.size = 0;
        this.Type = 1;
    }

//    public INode(short size, short Type, short[] direct, short indirect)  {
//        this.size = size;
//        this.Type = Type;
//
//    }

}
