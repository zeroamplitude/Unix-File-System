package com.jfsinternal;

/**
 * SuperBlock:
 *      This class is designed to construct the
 *      superblock of the UNIX-File-System
 *
 * @author Nicholas De Souza
 */
public class SuperBlock {

    protected static final int NUMFILES = 64;

    private int numBlocks;
    private int blockSize;
    private int freeBlockCount;
    private FreeBlock[] freeBlocks;
    private int freeInodeCount;
    private INode[] iNodes;


    public SuperBlock(int diskSize) {
        numBlocks = BlockIO.NUMBLKS;
        blockSize = BlockIO.BLKSIZE;
        freeBlockCount = CountFreeBlocks();
        freeBlocks = new FreeBlock[freeBlockCount];
        freeInodeCount = CountFreeInode();
        iNodes = new INode[NUMFILES];
    }

    public int CountFreeBlocks() {
        int count = 1;
        return count;
    }

    public int CountFreeInode() {
        int count = 1;
        return count;
    }

}
