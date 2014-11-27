package com.jfstest.testsuit;

import com.jfsinternal.BlockIO;

/**
 * Created by nicholas on 26/11/14.
 */
public class ReadINodeTest {
    public static void main(String[] args) {
        BlockIO disk = new BlockIO();
        byte[] buffer = new byte[128];

        disk.getBlock(2, buffer);

        short nextblk = (short) (((buffer[16] & 0x00ff) << 8)
                + (buffer[17] & 0x00ff));

        short nextblk1 = (short) (((buffer[18] & 0x00ff) << 8)
                + (buffer[19] & 0x00ff));


        String name = new String(buffer, 20, 1);


        short nextblk3 = (short) (((buffer[26] & 0x00ff) << 8)
                + (buffer[27] & 0x00ff));

        System.out.println(nextblk);
        System.out.println(nextblk1);
        System.out.println(name);
        System.out.println(nextblk3);
    }
}
