  package com.rohitsood.urlybird.test;

  import com.rohitsood.urlybird.util.StringUtil;

  import junit.framework.TestCase;


  /**
   * @author Rohit
   */
  public class StringUtilTest extends TestCase
  {
      /**DOCUMENT ME! */
      final int three = 3;

      /**DOCUMENT ME! */
      final int four = 4;

      /**
       * Creates a new StringUtilTest object.
       *
       * @param s DOCUMENT ME!
       */
      public StringUtilTest(String s)
      {
          super(s);
      }

      /**
       * DOCUMENT ME!
       */
      public void testFixLength()
      {
          assertTrue("123".equals(StringUtil.fixLength("123", three)));
      }

      /**
       * DOCUMENT ME!
       */
      public void testFixLengthPad()
      {
          assertTrue("123 ".equals(StringUtil.fixLength("123", four)));
      }

      /**
       * DOCUMENT ME!
       */
      public void testFixLengthSubstring()
      {
          final String sub = StringUtil.fixLength("123", 2);
          assertTrue("12".equals(sub));
      }
  }
