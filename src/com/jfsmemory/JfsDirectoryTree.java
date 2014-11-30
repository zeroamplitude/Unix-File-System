package com.jfsmemory;

/**
 * Created by Nicholas De Souza on 29/11/14.
 */
public class JfsDirectoryTree {

    JfsDirectoryNode root;

    public JfsDirectoryTree(String name, short iNumber) {

        root = new JfsDirectoryNode(name, iNumber);

    }

    public void traverseTree(String path) {

        String[] tokens = path.split("/");

        JfsDirectoryNode current = root.search(tokens, 0);

        if (current.name.equals("ERROR")) {

            System.out.println("Error");

        }

    }

}
