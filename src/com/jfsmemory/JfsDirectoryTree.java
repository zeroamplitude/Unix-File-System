package com.jfsmemory;

import static com.jfsinternal.JfsInternalConstants.FLAGS;

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

        traverseTree(tokens, FLAGS.ADD).addChild(newEntry);
        System.out.println("Directory Added");

        return 0;
    }

    public JfsDirectoryEntry traverseTree(String[] tokens, FLAGS flag) {

        if (tokens[0].equals("") && tokens.length == 2) {

            return this.root;

        } else {
            JfsDirectoryEntry current;
            if (flag == FLAGS.ADD) {
                current = root.search(tokens, 1, FLAGS.ADD);
            } else if (flag == FLAGS.REMOVE) {
                current = root.search(tokens, 1, FLAGS.REMOVE);
            } else {
                current = root.search(tokens, 1, FLAGS.CHECK);
            }

            if (current.name.equals("ERROR")) {
                System.out.println("Error");
            }

            return current;

        }

    }
}
