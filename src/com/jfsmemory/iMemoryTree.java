package com.jfsmemory;

import com.jfsinternal.INode;

import java.util.HashMap;


/**
 * Created by Nicholas De Souza on 28/11/14.
 */
public class iMemoryTree {

    HashMap<INode, String> iMemeryMap = new HashMap<INode, String>();


    public HashMap<INode, String> getiMemeryMap() {
        return iMemeryMap;
    }

    public void setiMemeryMap(HashMap<INode, String> iMemeryMap) {
        this.iMemeryMap = iMemeryMap;
    }

}
