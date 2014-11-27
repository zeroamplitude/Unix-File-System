package com.jfsfunctions;

import com.jfsinternal.BlockIO;
import com.jfsinternal.SuperBlock;
import sun.org.mozilla.javascript.ast.Block;

/**
 * @author Nicholas De Souza
 *
 * TODO: Improve disk access by reading iNode Table to memory
 */
public class JfsCreate extends JfsInterface {

    private static final int NUMFILES = 64;
    private static final int BLKSIZE = 128;
    public static BlockIO disk = new BlockIO();

    /**
     * jfsCreate:
     *      This method takes the pathname and type specified by
     *      the user and checks to make sure the file does not
     *      exist on file. If no file exist it creates a new iNode
     *      in the iNodeTable.
     *
     * @param pathname
     * @param type
     * @return
     *
     * TODO: Merge all functions into one
     */
    @Override
    public int jfsCreate(String pathname, int type) {
        int error = 0;

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

            }
        }
        return 0;

        error = createINode(pathname);
        if (error < 0) {
            if (error == -2) {
                System.out.println("No room on disk");
            }
            return -1;
        }


    }

    public int searchITable(String pathname) {
        byte[] buffer = new byte[BLKSIZE];
        for(int i = 2; i < 8; i++) {
            try {
                disk.getBlock(i, buffer);
            } catch (Exception e) {
                System.out.println("Disk read error: "
                        + e);
            }
            for(int j = 2; j < BLKSIZE; j += 16) {
                String curname;
                try {
                    curname = new String(buffer, j, 6);
                    if (curname == pathname) {
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
        for(int i = 0; i < 8; i++) {
            try {
                disk.getBlock(i, buffer);
            } catch (Exception e) {
                System.out.println("Disk read error: "
                        + e);
                return -1;
            }
            for(int j = 0; j < BLKSIZE; j += 16) {
                if(buffer[j] == (byte) 0) {
                    return curInum;
                }
                curInum++;
            }
        }
        return (short) -2;
    }

    public int createINode(String pathname) {
        byte[] buffer = new byte[BLKSIZE];
        

        return 0;
    }


}
