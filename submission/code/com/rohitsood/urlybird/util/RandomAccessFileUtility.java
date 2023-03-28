package com.rohitsood.urlybird.util;

import java.io.IOException;
import java.io.RandomAccessFile;


/**
 * Utility that reads data off of a file which resides on the local file system. Encapsulates some of the details of
 * working with a <tt>RandomAccessFile</tt>. Provides a quick way of reading <tt>String</tt>s of fixed lengths from
 * the file. More utility methids may be provided in future enhancements.
 *
 * @author Rohit Sood
 * @version 1.1
 */
public final class RandomAccessFileUtility {
    /**
     * Creates a new RandomAccessFileReader object.
     */
    private RandomAccessFileUtility() {
    }

    /**
     * Reads bytes and converts to <tt>String</tt> from the <tt>RandomAccessFile</tt>. The length of the string is
     * provided.
     *
     * @param file The <tt>RandomAccessFile</tt> with the current file pointer pre-seeked.
     * @param len The length of bytes to read.
     *
     * @return The <tt>String</tt> that was read.
     *
     * @throws IOException If there is a problem reading the file.
     */
    public static String readString(RandomAccessFile file, int len) throws IOException {
        String returnValue = null;
        final StringBuffer buff = new StringBuffer();
        final byte[] b = new byte[len];
        file.readFully(b);

        for (int a = 0; a < b.length; a++) {
            buff.append((char) b[a]);
        }

        returnValue = buff.toString();
        returnValue = ((null == returnValue) ? "" : returnValue);

        return returnValue.trim();
    }
}
