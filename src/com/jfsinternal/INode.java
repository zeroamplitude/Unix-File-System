package com.jfsinternal;

/**
 * Created by nicholas on 08/11/14.
 */
public class INode {

    public final static int SIZE = 64;
    String name;
    int type; // 0 for file regular file , 1 for directory
    int fileSize;
    /* combined scheme file system
    new[11] - first 12 blocks point to direct blocks (contain addresses that contain data of the file)
    128 bytes * 12 = 1536 bytes of data accessed directly
     */
    int[] directAccess = new int[11];

    // give inode name/type/owner/ computer file size
    void setName(String name) {
        this.name = name;
    }
    void setType(int type) {
        this.type = type;
    }




}
