package com.jfsinternal;

/**
 * Created by nicholas on 26/11/14.
 */
public class INode implements JfsInternalConstants{

    private short iNumber;
    private byte status;
    private short size;
    private short Type;
    private String name;
    private short openCount;
    private short cdate;
    private short adate;
    private short mdate;
    public short direct[] = new short[11];
    public short indirect[];

    INode(short location, String name, short Type) {
        this.status = 1;
        this.size = 0;
        this.name = name;
        this.Type = Type;
        this.openCount =0;
    }

    public INode(short size, short Type, short[] direct, short indirect)  {
        this.size = size;
        this.Type = Type;

    }

}
