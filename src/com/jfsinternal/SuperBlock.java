package com.jfsinternal;

/**
 * SuperBlock:
 *      This class is designed to construct the
 *      superblock of the UNIX-File-System
 *
 * @author Nicholas De Souza
 */
public class SuperBlock {

    private int numBlocks;
    private int blockSize;
    private int freeBlocks;

    int putSuperBlock() {
        return 0;
    }

    int getSuperBlock() {
        return 0;
    }

    int getEmptyBlock(int freeBlkNo) {
        return 0;
    }

    int releaseBlock(int releaseBlkNo) {
        return 0;
    }

}
