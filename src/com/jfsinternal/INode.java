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

    int putINodeTable() {

        return 0;
    }

    int getFilePointer(int iNumber, int filePtr) {
    return 0;
    }

    int allocBlk2File(int iNumber, int allocBlkNo) {
    return 0;
    }

    int parsePathName(String path, int numComponents) {
    return 0;
    }

    int parseDirEntry(int componentNo, int component, int iNumber) {

    return 0;
    }


}
