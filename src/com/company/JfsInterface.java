package com.company;

/**
 * jfsInterface:
 *      This is the interface for the JfsTest functions
 *
 * @author Nicholas De Souza
 */
public interface JfsInterface {

    int jfsOpen(char[] pathname);

    //int jfsRead(int fd, int start, int length, char[] mem_pointer);

    //int jfsWrite(int fd, int start, int length, char[] mem_pointer);

    //int jfsReadDir(int fd, char[] mem_pointer);

    int jfsClose(int fd);

    int jfsDelete(char[] pathname);

    int jfsCreate(char[] pathname, int type);

    //int jfsGetSize(char[] pathname);

    //int jfsGetType(char[] pathname);

    int jfsinitialize(int erase);

}
