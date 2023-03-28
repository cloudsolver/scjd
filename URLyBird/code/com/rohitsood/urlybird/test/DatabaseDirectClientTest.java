  /*
   * Created on Mar 21, 2004
   *
   * To change the template for this generated file go to
   * Window - Preferences - Java - Code Generation - Code and Comments
   */
  package com.rohitsood.urlybird.test;

  import com.rohitsood.urlybird.Mode;
  import com.rohitsood.urlybird.client.DatabaseClient;
  import com.rohitsood.urlybird.client.DatabaseClientFactory;

  import junit.framework.TestCase;

  import suncertify.db.RecordNotFoundException;


  /**
   * @author N0110643 To change the template for this generated type comment go to Window - Preferences -
   *         Java - Code Generation - Code and Comments
   */
  public class DatabaseDirectClientTest extends TestCase
  {
      /**
       * Creates a new DatabaseDirectClientTest object.
       *
       * @param s DOCUMENT ME!
       */
      public DatabaseDirectClientTest(String s)
      {
          super(s);
      }

      /**
       * DOCUMENT ME!
       */
      public void testFind()
      {
          System.out.println("Enter DatabaseDirectClientTest.testFind()");

          final DatabaseClientFactory f     = new DatabaseClientFactory();
          final DatabaseClient      client  = f.getDatabaseClient(Mode.OFFLINE);
          final String[]            c       = { "P", "", "", "", "", "", "", "" };
          int[]                     results = null;

          try
          {
              results = client.find(c);
          }
          catch (RecordNotFoundException e)
          {
              // TODO Auto-generated catch block
              fail("rec not found " + e);
              e.printStackTrace();
          }

          System.out.println("*** Total results " + results.length);

          for (int a = 0;a < results.length;a++)
          {
              try
              {
                  Show.printRecord(client.read(results[a]));
              }
              catch (RecordNotFoundException e1)
              {
                  // TODO Auto-generated catch block
                  e1.printStackTrace();
              }
          }

          System.out.println("Exit DatabaseDirectClientTest.testFind()");
      }
  }
