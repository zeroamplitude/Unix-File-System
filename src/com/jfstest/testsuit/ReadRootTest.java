package com.jfstest.testsuit;

import com.jfsinternal.BlockIO;

/**
 * Created by nicholas on 26/11/14.
 */
public class ReadRootTest {
    public static void main(String[] args) {
        BlockIO disk = new BlockIO();
        byte[] buffer = new byte[128];

        disk.getBlock(2, buffer);

        short nextblk = (short) (((buffer[0] & 0x00ff) << 8)
                + (buffer[1] & 0x00ff));


        String name = new String(buffer, 2, 1);


        short nextblk1 = (short) (((buffer[14] & 0x00ff) << 8)
                + (buffer[15] & 0x00ff));

        System.out.println(nextblk);
        System.out.println(name);
        System.out.println(nextblk1);
    }
}
