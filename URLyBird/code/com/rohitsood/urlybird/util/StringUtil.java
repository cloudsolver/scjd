  /*
   * Created on Feb 1, 2004
   */
  package com.rohitsood.urlybird.util;

  import java.util.Arrays;


  /**
   * DOCUMENT ME!
   *
   * @author Rohit Sood
   * @version $Revision$
   */
  public final class StringUtil
  {
      /** Constant for whitespace. */
      public static final char WHITESPACE = '\u0020';

      /**
       * Prevents the creation of a new StringUtil object since this is a utility class.
       */
      private StringUtil()
      {
      }

      /**
       * Provides a fixed length version of a <tt>String</tt>. Returns the same <tt>String</tt> object  if
       * the length passed in is equal to the length of the <tt>String</tt>. Pads a string with spaces if
       * the string length is less than the length of the <tt>String</tt> passed in. Prunes the string
       * causing a loss of data - Coverts to whitespaces if a null <code>String</code> object is passed
       * in. A <tt>length()</tt> call to the string returned will equal to the length passed in.
       *
       * @param string The <tt>String</tt> to fix with a length.
       * @param length The exact length of the desired string.
       *
       * @return The <tt>String</tt> object that is exactly the length as specified.
       */
      public static String fixLength(String string, int length)
      {
          if (length < 1)
          {
              length = Math.abs(length);
          }

          if (null == string)
          {
              return pad("", length);
          }

          String s = null;

          if (length == string.length())
          {
              s = string;
          }
          else if (string.length() > length)
          {
              s = string.substring(0, length);
          }
          else
          {
              s = pad(string, length);
          }

          return s;
      }

      /**
       * Pads the string till the length of the string equals the length specified. A <tt>length()</tt>
       * call to the string returned will equal to the length passed in.
       *
       * @param data The <tt>String</tt> to pad.
       * @param length The length of the desired <tt>String</tt>.
       *
       * @return The string padded with whitespaces.
       */
      private static String pad(String data, int length)
      {
          final StringBuffer buffer = new StringBuffer(length);
          buffer.append(data);

          final char[] whitespaces = new char[length - data.length()];
          Arrays.fill(whitespaces, WHITESPACE);
          buffer.append(whitespaces);

          return buffer.toString();
      }
  }
