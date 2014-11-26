package com.jfsinternal;

/**
 * Created by Nicholas De Souza on 09/11/14.
 * Modified by Nicholas De Souza on 23/11/14.
 */
public class FilePointerTable implements JfsInternalConstants {

    short[] table;

    public FilePointerTable() {
         table = new short[NUMFILES];
    }

    public int setTable(short[] table) {
        try {
            this.table = table;
        } catch (Exception e) {
            System.out.println("Set table error: " + e);
            return -1;
        }
        return 0;
    }

    /**
     * @return
     */
    public short[] getTable() {
        return table;
    }
}
