package com.jfsinternal;

/**
 * Created by 100487498 on 09/11/2014.
 */
public class GlobalFunctions {
// also handles errors
    public String fileFromPath(String pathName) {
        int len = pathName.length();
        char[] charArray = pathName.toCharArray();
        String fileName = "";
        for (int i = (len - 1); i > 0; i--) {
            if (charArray[i] == '/') {
                fileName = pathName.substring((i + 1), len);
                return fileName;
            }
        }

        return pathName;
    }
}
