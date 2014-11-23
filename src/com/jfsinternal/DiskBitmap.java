package com.jfsinternal;


/**
 * Created by nicholas on 08/11/14.
 */
public class DiskBitmap {

    public byte[] bitmap;

    public DiskBitmap(){
        bitmap = new byte[BlockIO.NUMBLKS];
    }

    public int putBit(int blocknum, byte block) {
        try {
            bitmap[blocknum] = block;
        } catch (Exception e) {
            System.out.println("Put bit error: " + e);
            return -1;
        }
        return 0;
    }

    public int getBit(int i) {
        return bitmap[i];
    }

}
