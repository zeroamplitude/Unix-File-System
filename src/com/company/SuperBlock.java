package com.company;

/**
 * SuperBlock:
 *      This class is designed to construct the
 *      superblock of the UNIX-File-System
 *
 * @author Nicholas De Souza
 */
public class SuperBlock {
    private final int NUMFILES = 64;

    public int numBlocks;
    public int blockSize;
    public int freeBlockCount;
    public Block freeBlocks;
    public int freeInodeCount;
    public INode iNode;


    public SuperBlock(int diskSize) {
        byte[] superBlock = new byte[BlockIO.BLKSIZE];
        blockSize


    }

}
