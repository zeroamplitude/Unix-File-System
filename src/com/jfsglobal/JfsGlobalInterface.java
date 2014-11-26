package com.jfsglobal;

/**
 * Created by nicholas on 25/11/14.
 */
public interface JfsGlobalInterface {

    public String fileFromPath(String pathName);

    public byte[] intToByte(short var);

    public byte[] shortToByte(int var);

    public int byteToInt(byte[] b);

    public short byteToShort(byte[] b);

}
