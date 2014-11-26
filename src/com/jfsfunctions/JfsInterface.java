package com.jfsfunctions;

import com.jfsglobal.JfsGlobalInterface;

/**
 * jfsInterface:
 *      This is the interface for the javaFileSystem
 *      functions.
 *
 * @author Nicholas De Souza
 */
public abstract class JfsInterface { //implements JfsFunctionConstants, JfsFunctionMethods, JfsGlobalInterface {

    public int jfsOpen(String pathname){return 0;}

    public int jfsRead(int fd, int start, int length, String mem_pointer){return 0;}

    public int jfsWrite(int fd, int start, int length, String mem_pointer){return 0;}

    public int jfsReadDir(int fd, String mem_pointer){return 0;}

    public int jfsClose(int fd){return 0;}

    public int jfsDelete(String pathname){return 0;}

    public int jfsCreate(String pathname, int type){return 0;}

    public int jfsGetSize(String pathname){return 0;}

    public int jfsGetType(String pathname){return 0;}

    public int jfsInitialize(int p1){return 0;}
}
