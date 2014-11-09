package com.jfsinternal.testsuit;

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

        bitmap.updateBitmap();
        sb.putSuperBlock(bitmap);




        int test = sb.buffer.getBuffer(0);
        System.out.println(test);

    }

}
