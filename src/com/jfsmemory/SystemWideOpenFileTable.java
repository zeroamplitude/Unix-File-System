package com.jfsmemory;

import com.jfsinternal.INode;
import com.jfsinternal.JfsInternalConstants;

import java.util.HashMap;

/**
 * Created by Nicholas De Souza on 02/12/14.
 */
public class SystemWideOpenFileTable implements JfsInternalConstants {

    public HashMap<Short, INode> swoft;


    /**
     * System Wide Open File Table - SWOFT
     * Constructs an HashMap that stores the
     * iNumber as referenced on the iNode table
     * and stores the basic construct of an Inode.
     */
    public SystemWideOpenFileTable() {
        swoft = new HashMap<Short, INode>();

    }

    private int addFile(short iNumber) {

        return 0;
    }

    public int rmFile(short iNumber) {
        return 0;
    }

    public int checkFileStatus(short iNumber) {
        if (swoft.containsKey(iNumber)) {
            return -1;
        }
        return 0;
    }

    public INode getValue(short iNumber) {
        if (swoft.containsKey(iNumber)) {
            return swoft.get(iNumber);
        }
        return null;
    }

    public int addEntry(INode iNode) {
        swoft.put(iNode.getiNumber(), iNode);

        return 0;
    }


}
