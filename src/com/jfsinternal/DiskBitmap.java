package com.jfsinternal;


/**
 * Created by nicholas on 08/11/14.
 */
public class DiskBitmap {

    public int[] bitmap;

    public DiskBitmap(){
        bitmap = new int[BlockIO.NUMBLKS];
    }

    public int updateBitmap() {
            bitmap[0] = 1;
            bitmap[1] = 1;
            bitmap[2] = 1;
            bitmap[3] = 0;
        return 0;
    }

    public int encodeBitmap(int i) {
        int encoded = 0;
        int j = 3;
        for (int dig = i ; dig < 4; dig++) {
            encoded = encoded + (int) (bitmap[dig] * Math.pow(2, j));
            j--;
        }
        return encoded;
    }
}
