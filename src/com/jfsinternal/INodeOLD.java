package com.jfsinternal;

import java.util.Arrays;


/**
 * Created by Nicholas on 08/11/14.
 * Modified by Nicholas on 23/11/14.
 */
public class INodeOLD implements JfsInternalConstants {

    int iNumber;                      //  1 byte
    char[] name;                        // 12 bytes
    byte type;                          //  1 byte
    short fileSize;                     //  2 bytes
    short[] direct;                     // 24 bytes
    IndirectBlock singleIndirect;       //  8 bytes
    IndirectBlock[] doubleIndirect;     // 16 bytes
    // ---------
    // 64 bytes

    public INodeOLD(short inumber) {
        iNumber = inumber;
        name = new char[SIZEOFPATH];
        type = Byte.parseByte(null);
        fileSize = 0;
        direct = new short[NUMDIRECT];
    }

    public int getiNumber() {
        try {
            iNumber = this.iNumber;
        } catch (Exception e) {
            System.out.println("Could not get iNum" + e);
            return -1;
        }
        return 0;
    }

    public int setName(char[] name) {
        try {
            this.name = name;
        } catch (Exception e) {
            System.out.println("Set name error:" + e);
            return -1;
        }
        return 0;
    }

    public int setType(byte type) {
        try {
            this.type = type;
        } catch (Exception e) {
            System.out.println("Set type error: " + e);
            return -1;
        }
        return 0;
    }

    public short getType(short iNumber) {
        try {
            return this.type;
        } catch (Exception e) {
            System.out.println("Get Type error: " + e);
            return -1;
        }
    }

    public int setFileSize(short size) {
        try {
            this.fileSize = size;
        } catch (Exception e) {
            System.out.println("Set file size error: " + e);
            return -1;
        }
        return 0;
    }

    public short getSize(short iNumber) {
        try {
            return this.fileSize;
        } catch (Exception e) {
            System.out.println("Get size error: " + e);
            return -1;
        }
    }

    public int setBlocks(short[] blocks) {
        try {
            if (blocks.length > NUMDIRECT) {
                for (int i = 0; i < NUMDIRECT; i++) {
                    direct[i] = blocks[i];
                }
                if (Arrays.copyOfRange(blocks, NUMDIRECT,
                        blocks.length).length > NUMINDIRECT) {

                    singleIndirect = new IndirectBlock(
                            Arrays.copyOfRange(blocks,
                                    NUMDIRECT, NUMDIRECT + NUMINDIRECT));

                    doubleIndirect = new IndirectBlock[NUMINDIRECT];
                    if (blocks.length > NUMDINDIRECT) {
                        System.out.println("File Size is too large");
                        return -1;
                    } else {
                        int blks = NUMDIRECT + NUMINDIRECT;
                        boolean finished = false;
                        for (int i = 0; i < NUMINDIRECT; i++) {
                            for (int j = 0; j < NUMINDIRECT; j++) {
                                doubleIndirect[i].indirect[j] = blocks[blks];
                                blks++;
                                if (blks == blocks.length) {
                                    finished = true;
                                    break;
                                }
                            }
                            if (finished) {
                                break;
                            }
                        }
                    }
                } else {
                    singleIndirect = new IndirectBlock(Arrays.
                            copyOfRange(blocks, NUMDIRECT,
                                    blocks.length));
                }
            } else {
                for (int i = 0; i < blocks.length; i++) {
                    direct[i] = blocks[i];
                }
            }
        } catch (Exception e) {
            System.out.println("Set direct error: " + e);
            return -1;
        }
        return 0;
    }

//    public short getBlocks(short iNumber, short fileSize) {
//        byte[] file = new byte[BLKSIZE];
//        if (fileSize < NUMDIRECT) {
//            for (int i = 0; i < fileSize; i++) {
//                try {
//                    disk.getBlock(i, file);
//                } catch (Exception e) {
//                    System.out.println("INode: getBlock error - " + e);
//                    return -1;
//                }
//            }
//        }
//        return 0;
//    }
}