package com.jfsinternal;


/**
 * Created by nicholas on 08/11/14.
 */
public class DiskBitmap {

    public byte[] bitmap;

    public DiskBitmap(){
        bitmap = new byte[BlockIO.NUMBLKS];
    }

    public int updateBitmap() {
            bitmap[0] = 1;
            bitmap[1] = 1;
            bitmap[2] = 1;
            bitmap[3] = 1;
        return 0;
    }

    public int getBit(int i) {
        return bitmap[i];
    }

}
