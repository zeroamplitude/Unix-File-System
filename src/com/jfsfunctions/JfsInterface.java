package com.jfsfunctions;

/**
 * jfsInterface:
 *      This is the interface for the JfsTest functions
 *
 * @author Nicholas De Souza
 */
public interface JfsInterface {

    int jfsOpen(String pathname);

    int jfsRead(int fd, int start, int length, String mem_pointer);

    int jfsWrite(int fd, int start, int length, String mem_pointer);

    int jfsReadDir(int fd, String mem_pointer);

    int jfsClose(int fd);

    int jfsDelete(String pathname);

    int jfsCreate(String pathname, int type);

    int jfsGetSize(String pathname);

    int jfsGetType(String pathname);

    int jfsInitialize(int p1);
}
