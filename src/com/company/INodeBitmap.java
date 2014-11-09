package com.company;

/**
 * Created by nicholas on 08/11/14.
 */
public class INodeBitmap {

    public Byte[] bitmap;

    public INodeBitmap(){
        bitmap = new Byte[SuperBlock.NUMFILES];
    }

    private int updateBitmap() {

        return 0;
    }
}
