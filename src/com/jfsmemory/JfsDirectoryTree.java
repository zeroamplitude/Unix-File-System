package com.jfsmemory;

/**
 * Created by Nicholas De Souza on 29/11/14.
 */
public class JfsDirectoryTree {

    JfsDirectoryEntry root;

    public JfsDirectoryTree(String name, short iNumber) {

        root = new JfsDirectoryEntry(name, iNumber);

    }


    public int updateDirectoryTree(String[] tokens, short iNumber) {

        int location = (tokens.length);
        String name = tokens[location - 1];

        JfsDirectoryEntry newEntry = new JfsDirectoryEntry(name, iNumber);

        traverseTree(tokens).addChild(newEntry);
        System.out.println("Directory Added");

        return 0;
    }

    public int removeEntry(String[] tokens) {
        traverseTree(tokens).deleteChild(tokens[tokens.length - 1]);
        return 0;
    }

    public JfsDirectoryEntry getParent(String[] tokens) {
        return traverseTree(tokens);
    }

    public JfsDirectoryEntry getEntry(String[] tokens) {
        return traverseTree(tokens).getChild(tokens[tokens.length - 1]);
    }

    public JfsDirectoryEntry traverseTree(String[] tokens) {
        if (tokens[0].equals("") && tokens.length == 2) {
            return this.root;
        } else {
            JfsDirectoryEntry current = root.search(tokens, 1);
            if (current.name.equals("ERROR")) {
                System.out.println("Error");
            }
            return current;
        }
    }
}
