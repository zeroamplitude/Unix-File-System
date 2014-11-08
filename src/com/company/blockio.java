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


    private static FileDescriptor diskfd;

    private File fileName;
    private boolean bool = false;
    private RandomAccessFile disk;

    public blockio() {
        diskfd = null;
        try{
            fileName = new File(DISKFILE);
            bool = fileName.createNewFile();
            disk = new RandomAccessFile(fileName, "rw");
        } catch (IOException e) {
            System.err.println ("Unable to start disk");
            System.exit(1);
        }
    }

    private int init_disk(){
        char garbage = '\0';
        try {
            diskfd = disk.getFD();
        } catch (IOException e) {
            System.err.println(e);
            System.exit(1);
        }

        try {
            disk.seek((long)(BLKSIZE*NUMBLKS));
        } catch (IOException e) {
            System.err.println(e);
            System.exit(1);
        }

        try {
            disk.write(garbage);
        } catch (IOException e) {
            System.err.println(e);
        }
        return 0;
    }

    private void seek(int blocknum) throws IOException {
        if ((blocknum < 0) || blocknum >= NUMBLKS) {
            throw new RuntimeException(blocknum +
                    " is out of range");
        }
        disk.seek((long)(blocknum * BLKSIZE));
    }


    public int getBlock(int blocknum, byte[] buffer) {
        if (buffer.length != BLKSIZE) {
            throw new RuntimeException("getBlock: bad buffer size "
                    + buffer.length);
        }
        if (diskfd == null){
            if (init_disk() != 0) return -1;
        }
        try {
            seek(blocknum);
            disk.read(buffer);
        } catch (IOException e) {
            System.err.println(e);
            return -1;
        }
        return 0;
    }

    public int putBlock(int blocknum, byte[] buffer){
        if (buffer.length != BLKSIZE){
            throw new RuntimeException("putBlock: bad buffer size "
                    + buffer.length);
        }
        if (diskfd == null){
            if (init_disk() != 0) return -1;
        }
        try {
            seek(blocknum);
            disk.write(buffer);
        } catch(IOException e) {
            System.err.println(e);
            return -1;
        }
        return 0;
    }
}
