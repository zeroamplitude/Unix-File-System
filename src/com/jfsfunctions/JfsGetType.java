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
 * @author <<Fill in your name here>>
 */
public class JfsGetType extends JfsInterface {
    @Override
    public int jfsGetType(String pathname) {

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
//        if (tokenCounter == -1){
//            System.out.println("ERROR: INVALID PATHNAME");
//            return 0;
//        }
//
//        String fileName = tokens[tokenCounter - 1];
//        System.out.println(fileName);
//
//        int fileType = getInode(fileName).getType();
//
//        return fileType;

        return 0;
    }
}
