package com.jfsmemory.testsuit;

import com.jfsinternal.DiskBitmap;
import com.jfsinternal.SuperBlock;
import com.jfsmemory.SuperBlkBuffer;

/**
 * @author Nicholas De Souza
 */
public class SuperBlkBufferTest {

    public static void main(String[] args) {
        SuperBlock sb = new SuperBlock();
        DiskBitmap bitmap = new DiskBitmap();
        //SuperBlkBuffer sbb = new SuperBlkBuffer();
//
//        bitmap.updateBitmap();
//        sb.putSuperBlock(bitmap);




        byte[] test = sb.buffer.getBuffer();
        for (int i = 0; i < test.length; i++)
            System.out.print(test[i] + " ");

    }

}
