package com.company;

import java.io.*;

/**
 * Created by nicholas on 08/11/14.
 */
public class blockio {

    private static final String DISKFILE = "simdisk.data";
    private static final int BLKSIZE = 128;
    private static final int NUMBLKS = 512;


    private static final int S_IRUSR = 00400;
    private static final int S_IWUSR = 00200;
    private static final int S_IRWXG = 00070;


    private static int diskfd = -1;

    private File fileName;
    private boolean bool = false;
    private RandomAccessFile disk;

    public blockio() {
        try{
            fileName = new File(DISKFILE);
            disk = new RandomAccessFile(fileName, "rw");
        } catch (IOException e) {
            System.err.println ("Unable to start disk");
            System.exit(1);
        }
    }

    private void seek(int blocknum) throws IOException {
        if ((blocknum < 0) || blocknum >= NUMBLKS) {
            throw new RuntimeException(blocknum +
                    " is out of range");
        }
        disk.seek((long)(blocknum * BLKSIZE));
    }


    public void getBlock(int blocknum, byte[] buffer) {
        if (buffer.length != BLKSIZE) {
            throw new RuntimeException("getBlock: bad buffer size "
                    + buffer.length);
        }
        try {
            seek(blocknum);
            disk.read(buffer);
        } catch (IOException e) {
            System.err.println(e);
            System.exit(1);
        }
    }

    public void putBlock(int blocknum, byte[] buffer){
        if (buffer.length != BLKSIZE){
            throw new RuntimeException("putBlock: bad buffer size "
                    + buffer.length);
        }
        try {
            seek(blocknum);
            disk.write(buffer);
        } catch(IOException e) {
            System.err.println(e);
            System.exit(1);
        }
    }



}
