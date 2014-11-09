package com.jfsinternal.testsuit;

import com.jfsinternal.DiskBitmap;

/**
 * Created by nicholas on 09/11/14.
 */
public class DiskBitmapTest {

    public static void main(String[] args) {
        DiskBitmap bm = new DiskBitmap();

        bm.updateBitmap();

        int test = bm.encodeBitmap(0);

        System.out.println(test);
    }
}
