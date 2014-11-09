package com.jfsinternal;

/**
 * Created by nicholas on 09/11/14.
 */
public class INodeBlock {
    INode node[] = new INode[BlockIO.BLKSIZE/INode.SIZE];

    public INodeBlock() {
        for (int i = 0; i < BlockIO.BLKSIZE/INode.SIZE; i++){
            node[i] = new INode();
        }

    }

}
