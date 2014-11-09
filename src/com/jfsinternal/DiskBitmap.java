package com.jfsinternal;

/**
 * Created by nicholas on 08/11/14.
 */
public class DiskBitmap {

    public Byte[] bitmap;

    public DiskBitmap(){
        bitmap = new Byte[BlockIO.NUMBLKS];
    }

    private int updateBitmap() {
            bitmap[0] = 0;
            bitmap[1] = 0;
            bitmap[2] = 0;
            bitmap[3] = 1;
        return 0;
    }

    public Byte encodeBitmap(int i) {
        int encoded =   (bitmap[ i ]<<24)&0xff000000|
                        (bitmap[i+1]<<16)&0x00ff0000|
                        (bitmap[i+2]<< 8)&0x0000ff00|
                        (bitmap[i+3]<< 0)&0x000000ff;
        return (byte)encoded;
    }
}
