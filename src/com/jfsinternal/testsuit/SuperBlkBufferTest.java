package com.jfsinternal.testsuit;

import com.jfsinternal.DiskBitmap;
import com.jfsmemory.SuperBlkBuffer;

/**
 * @author Nicholas De Souza
 */
public class SuperBlkBufferTest {

    public static void main(String[] args) {
        DiskBitmap bitmap = new DiskBitmap();
        bitmap.updateBitmap();

        SuperBlkBuffer sbb = new SuperBlkBuffer(bitmap);

        int test = sbb.getBuffer(0);
        System.out.println(test);

    }

}
