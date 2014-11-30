package com.jfsmemory;

/**
 * Created by Nicholas De Souza on 29/11/14.
 */
public class JfsDirectoryTree {

    JfsDirectoryNode root;

    public JfsDirectoryTree(String name, short iNumber) {

        root = new JfsDirectoryNode(name, iNumber);

    }


    public int updateDirectoryTree(String[] tokens, short iNumber) {

        int location = (tokens.length);
        String name = tokens[location - 1];

        JfsDirectoryNode newEntry = new JfsDirectoryNode(name, iNumber);

        traverseTree(tokens).addChild(newEntry);

        return 0;
    }


    public JfsDirectoryNode traverseTree(String[] tokens) {
        if (tokens[0].equals("")) {

            System.out.print("ROOT");

            return this.root;

        } else {



            JfsDirectoryNode current = root.search(tokens, 1);


            if (current.name.equals("ERROR")) {
                System.out.println("Error");
            }

            return current;

        }

    }
}
