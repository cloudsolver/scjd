  package com.rohitsood.urlybird.test;

  import com.rohitsood.urlybird.Mode;
  import com.rohitsood.urlybird.client.DatabaseClient;
  import com.rohitsood.urlybird.client.DatabaseClientFactory;

  import junit.framework.TestCase;

  import suncertify.db.RecordNotFoundException;


  /**
   * DOCUMENT ME!
   *
   * @author Rohit Sood
   * @version $Revision$
   */
  public class DatabaseNetworkClientTest extends TestCase
  {
      /**
       * Creates a new DatabaseNetworkClientTest object.
       *
       * @param s DOCUMENT ME!
       */
      public DatabaseNetworkClientTest(String s)
      {
          super(s);
      }

      /**
       * DOCUMENT ME!
       */
      public void testFind()
      {
          final DatabaseClientFactory f     = new DatabaseClientFactory();
          final DatabaseClient      client  = f.getDatabaseClient(Mode.ONLINE);
          final String[]            c       = { "P", "", "", "", "", "", "", "" };
          int[]               results = null;

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

          for (int a = 0;a < results.length;a++)
          {
              System.out.println(results[a]);

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
      }
  }
