  package com.rohitsood.urlybird.test;

  import com.rohitsood.urlybird.Mode;
  import com.rohitsood.urlybird.client.DatabaseClient;
  import com.rohitsood.urlybird.client.DatabaseClientFactory;
  import com.rohitsood.urlybird.client.DatabaseDirectClient;
  import com.rohitsood.urlybird.client.DatabaseNetworkClient;

  import junit.framework.TestCase;


  /**
   * DOCUMENT ME!
   *
   * @author Rohit Sood
   * @version $Revision$
   */
  public class DatabaseClientFactoryTest extends TestCase
  {
      /**
       * Creates a new DatabaseClientFactoryTest object.
       *
       * @param testName DOCUMENT ME!
       */
      public DatabaseClientFactoryTest(String testName)
      {
          super(testName);
      }

      /**
       * DOCUMENT ME!
       */
      public void testGetDatabaseClient()
      {
         final DatabaseClientFactory factory = new DatabaseClientFactory();
          DatabaseClient      client = factory.getDatabaseClient(Mode.ONLINE);
          assertTrue(client instanceof DatabaseNetworkClient);

          client = factory.getDatabaseClient(Mode.OFFLINE);
          assertTrue(client instanceof DatabaseDirectClient);

          try
          {
              client = factory.getDatabaseClient(-1);
              fail("No exception was thrown when expected");
          }
          catch (IllegalArgumentException e)
          {
              assertTrue("Argument exception was thrown ", e != null);
          }
      }
  }
