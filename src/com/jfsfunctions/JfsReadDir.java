package com.jfsfunctions;

/**
 * @author Modified  Nicholas De Souza on 24/11/14.
 */
public class JfsReadDir extends JfsInterface {
    @Override
    public int jfsReadDir(int fd, String mem_pointer) {

        return 0;
    }


    /**
     * iName:
     *         Walks through the directory structure starting at the
     *         value of cwd and follows the path described by the
     *         sequence of strings in path.
     *
     * @param iNumber
     *
     *              Represents the start value of the search function.
     *              If an absolute path is specified the value it has a
     *              value of 1 else it's the value of the current working
     *              directory.
     *
     * @param path
     *
     *              An array of strings representing the location of the
     *              the file or directory the user would like to navigate
     *              to.
     *
     * @return iNumber     Returns the iNumber on success and -1 on failure.
     *
     * @throws Exception   FileNotFound error on arrival of an expected
     *                     directory and could only find a file, or upon
     *                     arrival of the root directory.
     */
//    protected int iName(int iNumber, String[] path) throws Exception {
//        for (int i = 0; i < path.length; i++) {
//            if (INode[iNumber].type != Type.DIRECTORY) {
//                throw new Exception(path + " No such file or directory");
//            }
//            iNumber  == nameToInumber(INode[iNumber], path[i]);
//            if (iNumber == 0) {
//                throw new Exception("No such file or directory");
//                return -1;
//            } }
//        return iNumber;
//    }
//
//    int nameToINumber(INode node, String name) {
//
//    }


}

