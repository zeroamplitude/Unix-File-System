package com.jfsinternal;

public class Main {

    public static void main(String[] args) {
	    BlockIO Disk1 = new BlockIO();
        String testFile = "Test";
        byte[] dataTest;
        dataTest = stringToByteASCII(testFile);
        Disk1.putBlock(0, dataTest);

        byte[] dataTest2 = new byte[128];
        Disk1.getBlock(0, dataTest2);
        String test2 = new String(dataTest2);
        System.out.println(test2);

    }

    public static byte[] stringToByteASCII(String str) {
        char[] buffer = str.toCharArray();
        byte[] b = new byte[128];
        for (int i = 0; i < b.length; i++) {
            if (i < buffer.length) {
                b[i] = (byte) buffer[i];
            } else {
                b[i] = (byte) 0;
            }
        }
        return b;
    }

    public static String bytesToString(byte[] bytes) {
        char[] buffer = new char[bytes.length >> 1];
        for(int i = 0; i < buffer.length; i++) {
             int bpos = i << 1;
             char c = (char)(((bytes[bpos]&0x00FF)<<8)
                     + (bytes[bpos+1]&0x00FF));
             buffer[i] = c;
        }
        return new String(buffer);
    }
}


