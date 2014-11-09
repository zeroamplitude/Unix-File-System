package com.jfsinternal;

import com.jfsmemory.SystemFileTable;

/**
 * Created by 100487498 on 09/11/2014.
 */
public class ErrorHandling {


    public int checkPathName(String pathName) {
        int len = pathName.length();
        char[] charArray = pathName.toCharArray();
        if (charArray[0] == '/') {
            // check for okay length
            int check = 0;

            for (int i = 1; i < len; i++) {
                if (charArray[i] == '/') {
                    if ((i - check) > 5) {
                        return(-1); // exceed maximal characters
                    } else return(1); // success
                }
            }
        }
        else {
            return(0); //invalid path
        }
        return(0);
    }
    public void printPathError(int x) {
        if (x==1)
            System.out.println("success");
        else if (x==-1)
            System.out.println("exceed maximal characters");
        else if (x==0)
            System.out.println("invalid path");
    }
}

