 package com.rohitsood.urlybird.util;

  import java.io.IOException;
  import java.io.RandomAccessFile;


  /**
   * Utility that reads data off of a file which resides on the local file system.
   * Encapsulates details of working with a <tt>RandomAccessFile</tt>.
   * @author Rohit Sood
   * @version 1.1
   */
  public final class RandomAccessFileUtility
  {
      /**
       * Creates a new RandomAccessFileReader object.
       */
      private RandomAccessFileUtility()
      {
      }

      /**
       * DOCUMENT ME!
       *
       * @param file DOCUMENT ME!
       * @param len DOCUMENT ME!
       *
       * @return DOCUMENT ME!
       *
       * @throws IOException DOCUMENT ME!
       */
      public static String readString(RandomAccessFile file, int len)
          throws IOException
      {
          String           returnValue = null;
          final StringBuffer buff = new StringBuffer();
          final byte[]     b    = new byte[len];
          file.readFully(b);

          for (int a = 0;a < b.length;a++)
          {
              buff.append((char) b[a]);
          }

          returnValue   = buff.toString();
          returnValue   = ((null == returnValue) ? "" : returnValue);

          return returnValue.trim();
      }
  }
