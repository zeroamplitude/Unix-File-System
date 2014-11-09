package com.jfstest;

import com.jfsfunctions.*;
import java.util.Scanner;
import javax.validation.constraints.Size;

/**
 * JfsTest:
 *      This a simple interactive test program for use with
 *      the file system interface functions.
 *      To use this program, import the package containing
 *      your java source code and BlockIO functions.
 *      The program will print out a list of available commands
 *      for each function in the file system interface.
 *      This program is very simple. It does minimal error
 *      checking and recovery. All data written to and read from
 *      files passes through a single buffer(io_buffer). Only
 *      printable ASCII characters may be written to files. Non-
 *      printable characters read from files will not be displayed
 *      properly.
 *
 *      Further implementation of this program will be to create a
 *      gui, which emulates the UI that can be seen with most
 *      X-window operating systems.
 *
 *
 * @author Nicholas De Souza
 */
public class JfsTest {

    private static final int MAXIOLEN = 1024;


    private static final int MAXINPUTLEN = 512;

    public static Scanner in = new Scanner(System.in);


    public static char[] cmdBuffer = new char[MAXINPUTLEN+1];
    @Size(max = MAXIOLEN+1)
    public static String ioBuffer;
    @Size(max = MAXINPUTLEN)
    public static String dataBuffer;

    public static int p1,p2,p3;

    public static JfsInterface jfs;


    public static void main(String[] args) {
        int i, retval;
        i = 0;
        retval=0;

        while(true) {
            System.out.println();
            System.out.println("o: open a file");
            System.out.println("r: read from a file");
            System.out.println("w: write to a file");
            System.out.println("R: read from a directory");
            System.out.println("c: close a file");
            System.out.println("m: create (make) a new file");
            System.out.println("d: delete a file");
            System.out.println("s: get the size of a file");
            System.out.println("t: get the type of a file");
            System.out.println("i: initialize the file system");
            System.out.println("q: quit - exit this program");

            System.out.println("<< Command >>");
            if ((cmdBuffer = (in.nextLine()).toCharArray()) == null) break;

            switch(cmdBuffer[0]) {

                case 'o': /* Open a file */
                    System.out.println("Enter the full path of the file to open: ");
                    dataBuffer = in.nextLine();
                    retval = jfs.jfsOpen(dataBuffer);
                    if (retval >= 0) {
                        System.out.println("Open succeeded. File " +
                                "Descriptor number is " + retval);
                    } else {
                        System.out.println("Error. Return value was "
                                + retval);
                    }
                    break;

                case 'r': /* Read From a file */
                    System.out.println("Enter the file descriptor number: ");
                    p1 = in.nextInt();
                    System.out.println("Enter read start location: ");
                    p2 = in.nextInt();
                    System.out.println("Enter number of bytes to read: ");
                    p3 = in.nextInt();
                    retval = jfs.jfsRead(p1, p2, p3, ioBuffer);
                    if (retval > 0) {
                        System.out.println("Read successful.");
                        System.out.println("The following data was " +
                                "read (only printable ASCII will " +
                                "display)");
                        System.out.print(ioBuffer);
                        System.out.println();
                    } else {
                        System.out.println("Error. The return value " +
                                "was" + retval);
                    }
                    break;

                case 'w': /* Write to a file */
                    System.out.println("Enter file descriptor number: ");
                    p1 = in.nextInt();
                    System.out.println("Enter write start location: ");
                    p2 = in.nextInt();
                    System.out.println("Enter number of bytes to write: ");
                    p3 = in.nextInt();
                    System.out.println("This system only allows only non " +
                            "white space, printable ASCII characters to be" +
                            "written to a file");
                    System.out.println("Enter " + p3 + " characters to" +
                            " be written: ");
                    ioBuffer = in.nextLine();
                    retval = jfs.jfsWrite(p1, p2, p3, ioBuffer);
                    if (retval > 0) {
                        System.out.println("Write successful.");
                        System.out.println("Wrote" + ioBuffer +
                                " to the disk");
                    } else {
                        System.out.println("Error. Return value was " +
                                retval);
                    }
                    break;

                case 'R': /* Read from a directory */
                    System.out.println("Enter file descriptor number: ");
                    p1 = in.nextInt();
                    retval = jfs.jfsReadDir(p1, ioBuffer);
                    if (retval > 0) {
                        System.out.println("jfsReadDir successful.");
                        System.out.println("Directory entry is: " +
                                ioBuffer);
                    }
                    else if (retval == 0) {
                        System.out.println("jfsReadDir successful.");
                        System.out.println("No more entries in " +
                                "this directory");
                    }
                    else {
                        System.out.println("Error. Return value was " + retval);
                    }
                    break;

                case 'c': /* Close a file */
                    System.out.println("Enter file descriptor number: ");
                    p1 = in.nextInt();
                    retval = jfs.jfsClose(p1);
                    if (retval > 0) {
                        System.out.println("jfsClose successful.");
                    }
                    else {
                        System.out.println("Error. Return value was " + retval);
                    }
                    break;

                case 'm': /* Create a new file */
                    System.out.println("Enter full path name of new" +
                            " file: ");
                    dataBuffer = in.nextLine();
                    System.out.println("Enter 0 for regular file, 1 " +
                            "for directory: ");
                    p1 = in.nextInt();
                    retval = jfs.jfsCreate(dataBuffer, p1);
                    if (retval > 0) {
                        System.out.println("jfsCreate successful.");
                    }
                    else {
                        System.out.println("Error. Return value was "
                                + retval);
                    }
                    break;

                case 'd': /* Delete a file */
                    System.out.println("Enter full path name of file" +
                            " to delete: ");
                    dataBuffer = in.nextLine();
                    retval = jfs.jfsDelete(dataBuffer);
                    if (retval > 0) {
                        System.out.println("jfsDelete successful.");
                    }
                    else {
                        System.out.println("Error. Return value was "
                                + retval);
                    }
                    break;

                case 's': /* Get the size of a file */
                    System.out.println("Enter full path name of file: ");
                    dataBuffer = in.nextLine();
                    retval = jfs.jfsGetSize(dataBuffer);
                    if (retval >= 0) {
                        System.out.println("jfsGetsize successful.");
                        System.out.println("size = " + retval);
                    }
                    else {
                        System.out.println("Error. Return value was "
                                + retval);
                    }
                    break;

                case 't': /* Get the type of a file */
                    System.out.println("Enter full path name of file: ");
                    dataBuffer = in.nextLine();
                    retval = jfs.jfsGetType(dataBuffer);
                    if (retval >= 0) {
                        System.out.println("jfsGettype successful.");
                        if (retval == 0) {
                            System.out.println("file type is REGULAR");
                        }
                        else if (retval == 1) {
                            System.out.println("file type is DIRECTORY");
                        }
                        else {
                            System.out.println("file has unknown type " + retval);
                        }
                    }
                    else {
                        System.out.println("Error. Return value was " + retval);
                    }
                    break;

                case 'i': /* Initialize the file system */
                    System.out.println("Enter 1 to erase disk while " +
                            "initializing, 0 otherwise: ");
                    p1 = in.nextInt();
                    retval = jfs.jfsInitialize(p1);
                    if (retval > 0) {
                        System.out.println("jfsInitialize successful.");
                    }
                    else {
                        System.out.println("Error. Return value was "
                                + retval);
                    }
                    break;

                case 'q': /* Quit this program */
                    break;

                default:
                    System.out.println("Unknown command: "
                            + cmdBuffer);
                    break;
            }
            if (cmdBuffer[0] == 'q') break;
        }
    }
}
