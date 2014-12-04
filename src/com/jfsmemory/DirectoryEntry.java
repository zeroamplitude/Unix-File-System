package com.jfsmemory;

import com.jfsinternal.Inode;

import java.util.HashMap;

/**
 * Created by Nicholas De Souza on 04/12/14.
 */
public class DirectoryEntry {

    public String name;             // name is the key used to search the directory
    public Inode inode;             // inode is the value which stores the metadata of a file
    public HashMap<String,
            DirectoryEntry> child;  // child is a map of all the childen that belong to the iNode

    public DirectoryEntry(String name, Inode inode) {
        this.name = name;
        this.inode = inode;
        this.child = new HashMap<String, DirectoryEntry>();
    }


}
