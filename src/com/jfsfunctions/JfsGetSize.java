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
 * sfs_getsize(char *pathname): If the specified file is a regular file, this function should return the number of
 * bytes in the file. If it is a directory file, this function should return the number of directory entries in the file.
 */
public class JfsGetSize extends JfsInterface {
    @Override
    public int jfsGetSize(String pathname) {
        int fileSize = 0;

        /*
         * parse the pathname to determine if it is a correct pathname
         */
//        int tokenCounter = 0;
//        String token;
//        String[] tokens = new String[64];
//        StringTokenizer tokenizer = new StringTokenizer(pathname, "\\");
//
//        while (tokenizer.hasMoreTokens()) {
//            token = tokenizer.nextToken();
//            System.out.println(token);
//            tokenCounter ++;
//
//            if (token.length() > 5){
//                System.out.println("ERROR: INVALID PATHNAME");
//                return -1;
//            }else tokens[tokenCounter - 1] = token;
//        }
//
//        if (tokenCounter == 0){
//            System.out.println("ERROR: INVALID PATHNAME");
//            return -1;
//        }
//
//        String fileName = tokens[tokenCounter - 1];
//        System.out.println(fileName);
//
////        int fileType = getInode(fileName).JfsGetType(pathname);
////
////
////
////            if (fileType == 0){ //is a directory
////                fileSize = getInode(fileName).getdirectAccess().size();
////        } else if (fileType == 1){ // is a file
////                fileSize = getInode(fileName).Size();
////            }
////
////        return fileSize;
        return 0;
    }
}
