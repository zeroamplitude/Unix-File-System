package com.company;

public class Main {

    public static void main(String[] args) {
	    BlockIO Disk1 = new BlockIO();
        String testFile = "Test";
        byte[] dataTest;
        dataTest = stringToByteASCII(testFile);
        Disk1.putBlock(0, dataTest);
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
}


