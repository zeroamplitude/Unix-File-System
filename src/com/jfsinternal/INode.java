package com.jfsinternal;

import java.io.IOException;

/**
 * Created by nicholas on 08/11/14.
 */
public class INode {

    public final static int ISIZE = 64;
    String name;
    int iNum;
    Integer type; // 0 for file regular file , 1 for directory
    int fileSize;

    /* combined scheme file system
     * new[11] - first 12 blocks point to direct blocks (contain addresses that contain data of the file)
     * 128 bytes * 12 = 1536 bytes of data accessed directly
     */
    int[] directAccess;


    public INode(int iNum) {
        self.iNum = iNum; //This is wrong
        name = null;
        type = null;
        directAccess = new int[12];

    }



    public int getiNum() {
        try {
            return this.iNum;
        } catch () {
            System.out.println("Could not get iNum" + e);
            return -1;
        }
    }

    public int setName(String name) {
        try {
            this.name = name;
        } catch (Char  e) {
            System.out.println("Set name error:" + e);
            return -1;
        }
        return 0;
    }

    public int setType(int type) {
        try {
            this.type = type;
        } catch {

        }

    }




}
