package com.jfstest;

/**
 * Created by 100488265 on 09/11/2014.
 */


import java.util.StringTokenizer;

/**
 * @author Peter Little
 *
 * sfs_getsize(char *pathname): If the specified file is a regular file, this function should return the number of
 * bytes in the file. If it is a directory file, this function should return the number of directory entries in the file.
 */
public abstract class JfsGetSizeTest{

    public static void main(String[] args){
        String path = "\\cats\\dogs\\frogs\\bats\\mouse";
        int tokenCounter = 0;
        String token;
        String[] tokens = new String[64];
        StringTokenizer tokenizer = new StringTokenizer(path, "\\");

        while (tokenizer.hasMoreTokens()) {
            token = tokenizer.nextToken();
            System.out.println(token);
            tokenCounter ++;

            if (token.length() > 5){
                System.out.println("ERROR: INVALID PATHNAME");
                //return 0;
            }else tokens[tokenCounter - 1] = token;
        }

        if (tokenCounter == 0){
            System.out.println("ERROR: INVALID PATHNAME");
            //return 0;
        }
    }
}