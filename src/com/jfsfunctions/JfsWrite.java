package com.jfsfunctions;

/**
 * @author <<Fill in your name here>>
 */
public abstract class JfsWrite implements JfsInterface {
    @Override
    public int jfsWrite(int fd, int start, int length, char[] mem_pointer) {
        return 0;
    }
}
