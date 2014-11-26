package com.jfsglobal;

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

    public byte[] intToByte(short var) {
        int i = 0;
        byte[] i2B = new byte[Integer.SIZE];
        i2B[i]   = (byte)(var >> 24);           // <-----=<< Higher Order
        i2B[i=1] = (byte)(var >> 16);
        i2B[i+2] = (byte)(var >> 8);
        i2B[i+3] = (byte)(var);
        return i2B;
    }

    public byte[] shortToByte(int var) {
        int i = 0;
        byte[] s2B = new byte[Byte.SIZE];
        s2B[i]   = (byte) (var >> 8);           // <-----=<< Higher Order
        s2B[i+1] = (byte) (var);
        return s2B;
    }


    public int byteToInt(byte[] b) {
        int i = 0;
        return ((b[i] & 0xff) << 24)
                + ((b[i+1] & 0xff) << 16)
                + ((b[i+2] & 0xff) << 8)
                +  (b[i+3] & 0xff);
    }

    public short byteToShort(byte[] b) {
        int i = 0;
        return (short) (((b[i] & 0x00ff) << 8)
                + (b[i+1] & 0x00ff));
    }
}
