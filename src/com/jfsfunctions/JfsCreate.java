package com.jfsfunctions;

import com.jfsinternal.BlockIO;
import com.jfsinternal.INode;
import com.jfsinternal.SuperBlock;
import sun.org.mozilla.javascript.ast.Block;

import static java.lang.Math.floor;

/**
 * @author Nicholas De Souza
 *         <p/>
 *         TODO: Improve disk access by reading iNode Table to memory
 */
public class JfsCreate extends JfsInterface {

    private static final int NUMFILES = 64;
    private static final int BLKSIZE = 128;
    public static BlockIO disk = new BlockIO();

    /**
     * jfsCreate:
     * This method takes the pathname and type specified by
     * the user and checks to make sure the file does not
     * exist on file. If no file exist it creates a new iNode
     * in the iNodeTable.
     *
     * @param pathname
     * @param type
     * @return
     * TODO: Merge all functions into one
     */
    @Override
    public int jfsCreate(String pathname, int type) {
        short error = 0;

        error = searchITable(pathname);
        if (error < 0) {
            if (error == -2) {
                System.out.println("File already exists on drive");
            }
            return -1;
        }

        error = getFreeINode();
        if (error < 0) {
            if (error == -2) {
                System.out.println("No room on disk");
            }
        }

//        error = createINode(pathname, error);
//        if (error < 0) {
//            System.out.println("Could not create iNode");
//            return -1;
//        }

        return 0;
    }

    public short searchITable(String pathname) {
        byte[] buffer = new byte[BLKSIZE];
        for (int i = 2; i < 8; i++) {
            try {
                disk.getBlock(i, buffer);
            } catch (Exception e) {
                System.out.println("Disk read error: "
                        + e);
            }
            for (int j = 2; j < BLKSIZE; j += 16) {
                String curname;
                try {
                    curname = new String(buffer, j, 6);
                    if (curname.equals(pathname)) {
                        return -2;
                    }
                } catch (Exception e) {
                    System.out.println("Name read error: "
                            + e);
                    return -1;
                }
            }
        }
        return 0;
    }

    public short getFreeINode() {
        byte[] buffer = new byte[BLKSIZE];
        short curInum = 0;
        for (int i = 0; i < 8; i++) {
            try {
                disk.getBlock(i, buffer);
            } catch (Exception e) {
                System.out.println("Disk read error: "
                        + e);
                return -1;
            }
            for (int j = 0; j < BLKSIZE; j += 16) {
                if (buffer[j] == (byte) 0) {
                    return curInum;
                }
                curInum++;
            }
        }
        return (short) -2;
    }

//    public short createINode(String pathname, short iNumber) {
//        byte[] buffer = new byte[BLKSIZE];
//        INode curr = new INode((short) 0, pathname,(short) 0);
//        short block = (short) (floor(iNumber / 8) + 2);
//        try {
//            disk.getBlock(block, buffer);
//        } catch (Exception e) {
//            System.out.println("Read disk failure: " + e);
//            return -1;
//        }
//
//        short location = (short) floor(iNumber / 8);
//        byte[] name = pathname.getBytes();
//        int nameSize = name.length;
//        int nameCount = 0;
//
//        try {
//            for (short i = 0; i < 16; i++) {
//                if (i < 2) {
//                    buffer[i + location] = (byte) 1;
//                } else if (i < 3) {
//                    buffer[i + location] = (byte) 0;
//                } else if (i < 9) {
//                    if (nameCount < nameSize) {
//                        buffer[i + location] = name[nameCount];
//                    } else {
//                        buffer[i + location] = (byte) 0;
//                    }
//                    nameCount++;
//                } else if (i < 11) {
//                    buffer[i + location] = (byte) 1;
//                } else {
//                    buffer[i + location] = (byte) 0;
//                }
//            }
//        } catch (Exception e) {
//            System.out.println("Create iNode buffer failure: " + e);
//            return -1;
//        }
//
//        try {
//            disk.putBlock(block, buffer);
//        } catch (Exception e) {
//            System.out.println("Disk write error: " + e);
//            return -1;
//        }
//
//        return 0;
//    }


}
