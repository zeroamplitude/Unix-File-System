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
            bitmap[3] = 1;
        return 0;
    }

    public int encodeBitmap(int i) {
        int encoded = 0;
        int j = 3;
        int i1;
        for (i1 = i; i1 < 4; i1++) {
            encoded = encoded + (int) (bitmap[i1] * Math.pow(2, j));
            j--;
        }
        return encoded;
    }



}
