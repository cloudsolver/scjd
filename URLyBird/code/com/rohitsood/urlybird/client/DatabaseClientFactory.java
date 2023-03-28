  package com.rohitsood.urlybird.client;

import com.rohitsood.urlybird.Mode;

  /**
   * Factory for creating clients. The <tt>DatabaseClientFactory</tt> creates client based on the
   * mode requested. Modes are defined in <tt>DatabaseClient</tt> interface.
   *
   * @author Rohit Sood
   * @version 1.1
   */
  public class DatabaseClientFactory
  {
      private static final String UNSUPPORTED_CLIENT_MESSAGE = "Unsupported Client Mode";

      /**
       * Creates a DatabaseClient object based on mode.  Checks for mode, if a mode is not matched,
       * then a <tt>IllegalArgumentException</tt> is thrown.
       *
       * @param mode The mode for which a database client is requested.
       *
       * @return <tt>DatabaseClient</tt> based on the mode.
       */
      public DatabaseClient getDatabaseClient(int mode)
      {
          DatabaseClient client = null;

          switch (mode)
          {
              case Mode.ONLINE:
                  client = new DatabaseNetworkClient();

                  break;

              case Mode.OFFLINE:
                  client = new DatabaseDirectClient();

                  break;

              default:
                  throw new IllegalArgumentException(UNSUPPORTED_CLIENT_MESSAGE);
          }

          return client;
      }
  }
