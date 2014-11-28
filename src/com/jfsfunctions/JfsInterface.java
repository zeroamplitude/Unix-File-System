/*
 * This file is part of JavaFileSystem (jfs).
 *
 *     jfs is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     jfs is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with jfs.  If not, see <http://www.gnu.org/licenses/>.
 *
 * Copyright (c) 2014. Nicholas De Souza, Arun Gireesan, Milan Kornicer, Peter Little. All rights reserved
 */

package com.jfsfunctions;

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
