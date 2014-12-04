package com.jfsmemory;

import java.util.HashMap;

/**
 * Created by Nicholas De Souza on 29/11/14.
 */
public class JfsDirectoryEntry

{

    public String name;
    public short iNumber;
    public HashMap<String, JfsDirectoryEntry> child;

    public JfsDirectoryEntry(String name, short iNumber) {
        this.name = name;
        this.iNumber = iNumber;
        this.child = new HashMap<String, JfsDirectoryEntry>();
    }

    public void addChild(JfsDirectoryEntry newChild) {

        this.child.put(newChild.name, newChild);

    }

    public void deleteChild(String name) {
        this.child.remove(name);
    }

    public JfsDirectoryEntry search(String[] tokens, int cur) {

        JfsDirectoryEntry error = new JfsDirectoryEntry("ERROR", (short) 0);

        if (cur == (tokens.length - 2) && child.containsKey(tokens[cur])) {

            if ((child.get(tokens[cur]).child.containsKey(tokens[cur + 1]))) {

                return child.get(tokens[cur]).child.get(tokens[cur + 1]);

            } else {

                return child.get(tokens[cur]);

            }

        } else if (child.containsKey(tokens[cur])) {

            cur += 1;
            return child.get(tokens[cur - 1]).search(tokens, cur);

        } else {

            return error;

        }

    }

}
