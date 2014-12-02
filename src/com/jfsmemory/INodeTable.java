package com.jfsmemory;

import com.jfsinternal.INode;
import com.jfsinternal.JfsInternalConstants;

import java.util.HashMap;

/**
 * Created by Nicholas De Souza on 01/12/14.
 */
public class INodeTable implements JfsInternalConstants {

    private JfsMemory memory = JfsMemory.getInstance();
    private HashMap<String, INode> iTable;


    public INodeTable() {
        iTable = new HashMap<String, INode>(NUMFILES);
    }


    public int cacheInodeTable() {
        for (int iNumber = 0; iNumber < 64; iNumber++) {
            INode iNodes = new INode(iNumber);
            if (cacheINode(iNodes) < 0) {
                return -1;
            }
        }
        return 0;
    }

    public int cacheINode(INode newInode) {
        try {
            this.iTable.put(newInode.getName(), newInode);
        } catch (Exception e) {
            System.out.println("Cache iNode error "
                    + e);
            return -1;
        }
        return 0;
    }

    public INode getCachedINode(String name) {
        if (this.iTable.size() == 0) {
            return null;
        } else {
            return this.iTable.get(name);
        }

    }
}
