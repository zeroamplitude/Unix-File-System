package com.jfsinternal;

import com.jfsmemory.SuperBlkBuffer;

import java.io.File;
import java.io.FileDescriptor;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * BlockIO:
 *      This class is designed to simulate
 *      block-oriented access to a disk.
 *
 * @author Nicholas De Souza.
 */
public class BlockIO {
    /* File for storing simulated disk's data */
    private static final String DISKFILE = "simdisk.data";
    /* Size of blocks on simulated disk */
    public static final int BLKSIZE = 128;
    /* Number of blocks on simulated disk */
    protected static final int NUMBLKS = 512;

    /* File descriptor of disk data file when open.
     * A null value indicated file is not opened. */
    private static FileDescriptor diskfd;

    /* File objects used to operate on simdisk.data */
    private File simdisk;
    private boolean bool = false;
    private RandomAccessFile disk;


    /**
     * Class constructor
     */
    public BlockIO() {
        diskfd = null;
        try{
            simdisk = new File(DISKFILE);
            bool = simdisk.createNewFile();
            disk = new RandomAccessFile(simdisk, "rw");
        } catch (IOException e) {
            System.err.println ("Unable to start disk");
            System.exit(1);
        }
    }

    /**
     * init_disk():
     *      private function used to open the disk data file
     *      a new file is created if one does not exist.
     *      New files read as all zeros.
     * @throws IOException
     * @return 0 for success, -1 otherwise
     */
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

    /**
     * seek(int blocknum):
     *
     *
     * @param blocknum
     * @throws IOException
     */
    private void seek(int blocknum) throws IOException {
        if ((blocknum < 0) || blocknum >= NUMBLKS) {
            throw new RuntimeException(blocknum +
                    " is out of range");
        }
        disk.seek((long)(blocknum * BLKSIZE));
    }

    /**
     * getBlock(int blocknum, byte[] buffer):
     *      retrieves one block from the simulated disk
     *      if disk is not open, an attempt is made to
     *      open it.
     * @param blocknum The number of the desired block
     *                 (zero-base count)
     * @param buffer Should contain a block sized buffer
     * @return 0 if successful, -1 otherwise
     */
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

    /**
     * put_block(blknum,buf)
     *      writes one block to the simulated disk.
     *      If disk file is not yet open, an attempt
     *      is made to open it.
     * @param blocknum the number of the desired block
     *                 (zero-based count)
     * @param buffer should be block-sized buffer
     * @return 0 if successful, -1 otherwise
     */
    public int putBlock(int blocknum, byte[] buffer){
        /*if (buffer.length != BLKSIZE){
            throw new RuntimeException("putBlock: bad buffer size "
                    + buffer.length);
        }*/
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
