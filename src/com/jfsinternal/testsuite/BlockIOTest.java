package com.jfsinternal.testsuit;

import com.jfsinternal.BlockIO;
import com.jfsinternal.DiskBitmap;
import com.jfsinternal.SuperBlock;

public class BlockIOTest {

    public static void main(String[] args) {
        BlockIO disk = new BlockIO();
        SuperBlock sb = new SuperBlock();
        DiskBitmap bitmap = new DiskBitmap();
        //SuperBlkBuffer sbb = new SuperBlkBuffer();

//        bitmap.updateBitmap();
//        sb.putSuperBlock(bitmap);
        /*
        String testFile = "Test";
        Byte[] dataTest;
        dataTest = stringToByteASCII(testFile);
        Disk1.putBlock(0, dataTest);*/


//        byte[] testbuf = new byte[BlockIO.BLKSIZE];
//        for (int i = 0; i < BlockIO.BLKSIZE; i++) {
//            testbuf[i] = 1;
//        }
//
//        Block block = new Block();
//
//        disk.putBlock(0, sb.buffer.getBuffer());
//        disk.putBlock(1, testbuf);
//        disk.putBlock(2, block.getBlock());


        byte[] dataTest2 = new byte[128];
        disk.getBlock(0, dataTest2);
        //String test2 = new String(dataTest2);
        System.out.println(dataTest2);


    }

    public static Byte[] stringToByteASCII(String str) {
        char[] buffer = str.toCharArray();
        Byte[] b = new Byte[128];
        for (int i = 0; i < b.length; i++) {
            if (i < buffer.length) {
                b[i] = (byte) buffer[i];
            } else {
                b[i] = (byte) 0;
            }
        }
        return b;
    }

    public static String BytesToString(Byte[] bytes) {
        char[] buffer = new char[bytes.length >> 1];
        for (int i = 0; i < buffer.length; i++) {
            int bpos = i << 1;
            char c = (char) (((bytes[bpos] & 0x00FF) << 8)
                    + (bytes[bpos + 1] & 0x00FF));
            buffer[i] = c;
        }
        return new String(buffer);
    }
}


