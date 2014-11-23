package com.jfsfunctions;

import com.jfsinternal.*;
/**
 *
 * @author Nicholas De souza
 */
public abstract class JfsInitialize implements JfsInterface {
    private BlockIO disk;
    private SuperBlock sb;
    public DiskBitmap bitmap;
    public IndirectBlock freeBlk;

//    final int NoOfBlocks = 512;
//    final int BlockSize = 128;

    byte[] buffer;


    /**
     * @param erase
     * @return
     */
    public int jfsInitialize(int erase) {
        disk = new BlockIO();
        bitmap = new DiskBitmap();
        buffer = new byte[BlockIO.BLKSIZE];
        return 0;
    }

    public int wipeDisk() {
        try {
            for (int blocks = 0; blocks < sb.numBlocks; blocks++) {
                for (int bYtes = 0; bYtes < sb.blockSize; bYtes++) {
                    buffer[bYtes] = 0;
                }
                disk.putBlock(blocks, buffer);
            }
        } catch (Exception e) {
            System.out.println("Wipe disk error " + e);
        }
        return 0;
    }

    public int writeSuperBlock() {
        short count = 0;
        for (int i = 0; i < sb.numBlocks; i++) {
            try {
                disk.getBlock(i, buffer);
            } catch (Exception e) {
                System.out.println("Disk getBlock error: " + e);
                return -1;
            }
            if (buffer == null) {
                byte empty = 0;
                try {
                    bitmap.putBit(i, empty);
                } catch (Exception e) {
                    System.out.println("Bitmap putBit error: " + e);
                    return -1;
                }
                try {

                } catch (Exception e) {

                }
            } else {
                byte full = 1;
                try {
                    bitmap.putBit(i, full);
                    count += 1;
                } catch (Exception e) {
                    System.out.println("Bitmap putBit error: " + e);
                    return -1;
                }
            }
        }
        sb = new SuperBlock(count, );
        sb.putSuperBlock(bitmap);



    return 0;
    }
}
