package com.jfstest.testsuit;

import com.jfsinternal.BlockIO;

/**
 * Created by nicholas on 26/11/14.
 */
public class ReadBlockTest {

    public static void main(String[] args) {
        BlockIO disk = new BlockIO();
        byte[] buffer = new byte[128];

        disk.getBlock(0, buffer);

        short nextblk = (short) (((buffer[0] & 0x00ff) << 8)
                + (buffer[1] & 0x00ff));
        short nextblk1 = (short) (((buffer[2] & 0x00ff) << 8)
                + (buffer[3] & 0x00ff));
        short nextblk2 = (short) (((buffer[4] & 0x00ff) << 8)
                + (buffer[5] & 0x00ff));
        short nextblk3 = (short) (((buffer[6] & 0x00ff) << 8)
                + (buffer[7] & 0x00ff));
        short nextblk4 = (short) (((buffer[8] & 0x00ff) << 8)
                + (buffer[9] & 0x00ff));

        System.out.println(nextblk);
        System.out.println(nextblk1);
        System.out.println(nextblk2);
        System.out.println(nextblk3);
        System.out.println(nextblk4);

    }

}
