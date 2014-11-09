package com.company;

/**
 * SuperBlock:
 *      This class is designed to construct the
 *      superblock of the UNIX-File-System
 *
 * @author Nicholas De Souza
 */
public class SuperBlock {

    protected static final int NUMFILES = 64;

    public int numBlocks;
    public int blockSize;
    public int freeBlockCount;
    public FreeBlock[] freeBlocks;
    public int freeInodeCount;
    public INode[] iNodes;


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
