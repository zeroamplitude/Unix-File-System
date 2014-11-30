package com.jfsmemory;

import java.util.HashMap;

/**
 * Created by Nicholas De Souza on 29/11/14.
 */
public class JfsDirectoryNode {

    String name;
    short iNumber;
    HashMap<String, JfsDirectoryNode> child;

    public JfsDirectoryNode(String name, short iNumber) {
        this.name = name;
        this.iNumber = iNumber;
        this.child = new HashMap<String, JfsDirectoryNode>();
    }

    public void addChild(JfsDirectoryNode newChild) {

        this.child.put(newChild.name, newChild);

    }

    public void deleteChild(String name) {

        this.child.remove(name);

    }

    public JfsDirectoryNode search(String[] tokens, int cur) {

        JfsDirectoryNode error = new JfsDirectoryNode("ERROR", (short) 0);

        if (cur == (tokens.length - 2) && child.containsKey(tokens[cur])) {

            return child.get(tokens[cur]);

        } else if (child.containsKey(tokens[cur])) {

            cur += 1;
            return child.get(tokens[cur]).search(tokens, cur);

        } else {

            return error;

        }

    }

}
