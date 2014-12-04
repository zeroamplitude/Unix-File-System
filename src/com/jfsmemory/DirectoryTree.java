package com.jfsmemory;

import com.jfsinternal.Inode;

/**
 * Created by Nicholas De Souza on 04/12/14.
 */
public class DirectoryTree {

    DirectoryEntry root;        // root is the parent directory of the entire file system

    public DirectoryTree(String name, Inode inode) {

        this.root = new DirectoryEntry(name, inode);    // Set the root directory

    }


    private Inode traverseTree(String[] tokens) {

    }

}
