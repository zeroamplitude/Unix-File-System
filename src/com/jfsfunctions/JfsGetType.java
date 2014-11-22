package com.jfsfunctions;

import java.util.StringTokenizer;

/**
 * @author <<Fill in your name here>>
 */
public abstract class JfsGetType implements JfsInterface {
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
