  package com.rohitsood.urlybird.client;

  import com.rohitsood.urlybird.Mode;
  import com.rohitsood.urlybird.server.ConfigurationProperties;
  import com.rohitsood.urlybird.server.DatabaseNetworkServer;
  import com.rohitsood.urlybird.server.DatabaseNetworkServerFactory;

  import suncertify.db.DatabaseServerException;
  import suncertify.db.DuplicateKeyException;
  import suncertify.db.RecordNotFoundException;
  import suncertify.db.ValidationException;

  import java.net.MalformedURLException;

  import java.rmi.Naming;
  import java.rmi.NotBoundException;
  import java.rmi.RemoteException;


  /**
   * Provides access to the database server over a network using JRMP.
   *
   * @author Rohit Sood
   * @version 1.3
   */
  public class DatabaseNetworkClient extends AbstractDatabaseClient
  {
      /**The network server factory. */
      private static DatabaseNetworkServerFactory factory;

      /**The server which connects to the data implementation class. */
      private DatabaseNetworkServer server;

      /**
       * Default constructor. Looks up the server object and initializes it.
       * The client needs to be prepared to catch a <tt>RuntimeException</tt>
       */
      DatabaseNetworkClient()
      {
          server = getServer();
      }

      /* (non-Javadoc)
       * @see com.rohitsood.urlybird.client.DatabaseClient#disconnect()
       */
      public void disconnect()
      {
          server    = null;
          factory   = null;
          System.gc();
      }

      /* (non-Javadoc)
       * @see com.rohitsood.urlybird.Mode#getMode()
       */
      public int getMode()
      {
          return Mode.ONLINE;
      }

      /**
       * This method is called internally to get a server to connect with.
       * All calls are delegate to this server.
       * @return An instance of the remote server.
       */
      private DatabaseNetworkServer getServer()
      {
          if (server == null)
          {
              try
              {
                  final ConfigurationProperties config = new ConfigurationProperties();

                  if (factory == null)
                  {
                      factory = (DatabaseNetworkServerFactory) Naming.lookup(config.getLookupName());
                  }

                  server = factory.createServer(super.getVMID());
              }
              catch (MalformedURLException e)
              {
                  final String msg = (e.getMessage() == null) ? " Unknown Reasons " : e.getMessage();
                  throw new DatabaseServerException(
                      "There was a problem with the remote server URL.\n" + e.getMessage());
              }
              catch (RemoteException e)
              {
                  final String msg = (e.getMessage() == null) ? " Unknown Reasons " : e.getMessage();
                  throw new DatabaseServerException("There was a problem with the remote server.\n"
                      + msg);
              }
              catch (NotBoundException e)
              {
                  final String msg = (e.getMessage() == null) ? " Unknown Reasons " : e.getMessage();
                  throw new DatabaseServerException("Server is not bound to the registry.\n" + msg);
              }


          }

          return server;
      }

      /* (non-Javadoc)
       * @see com.rohitsood.urlybird.client.DatabaseClient#book(java.lang.String, java.lang.String, java.lang.String)
       */
      public boolean book(String recId, String maxOccupancy, String customerId)
          throws RecordNotFoundException, ValidationException
      {
          super.book(recId, maxOccupancy, customerId);

          boolean confirmed = false;

          try
          {
              confirmed = getServer().book(recId, maxOccupancy, customerId);
          }
          catch (RemoteException e)
          {
              throw new DatabaseServerException(DatabaseServerException.ERROR_MESSAGE
                  + e.getMessage());
          }

          return confirmed;
      }

      /* (non-Javadoc)
       * @see suncertify.db.DBMain#read(int)
       */
      public String[] read(int recNo) throws RecordNotFoundException
      {
          String[] ids = null;

          try
          {
              ids = getServer().read(recNo);
          }
          catch (RemoteException e)
          {
              throw new DatabaseServerException(DatabaseServerException.ERROR_MESSAGE
                  + e.getMessage());
          }

          return ids;
      }

      /* (non-Javadoc)
       * @see suncertify.db.DBMain#update(int, java.lang.String[])
       */
      public void update(int recordNumber, String[] data)
          throws RecordNotFoundException
      {
          try
          {
              getServer().update(recordNumber, data);
          }
          catch (RemoteException e)
          {
              throw new DatabaseServerException(DatabaseServerException.ERROR_MESSAGE
                  + e.getMessage());
          }
      }

      /* (non-Javadoc)
       * @see suncertify.db.DBMain#delete(int)
       */
      public void delete(int recNo) throws RecordNotFoundException
      {
          try
          {
              getServer().delete(recNo);
          }
          catch (RemoteException e)
          {
              throw new DatabaseServerException(DatabaseServerException.ERROR_MESSAGE
                  + e.getMessage());
          }
      }

      /* (non-Javadoc)
       * @see suncertify.db.DBMain#find(java.lang.String[])
       */
      public int[] find(String[] criteria) throws RecordNotFoundException
      {
          int[] ids = null;

          try
          {
              ids = getServer().find(criteria);
          }
          catch (RemoteException e)
          {
              throw new DatabaseServerException(DatabaseServerException.ERROR_MESSAGE
                  + e.getMessage());
          }

          return ids;
      }

      /* (non-Javadoc)
       * @see suncertify.db.DBMain#create(java.lang.String[])
       */
      public int create(String[] data) throws DuplicateKeyException
      {
          int createID = -1;

          try
          {
              createID = getServer().create(data);
          }
          catch (RemoteException e)
          {
              throw new DatabaseServerException(DatabaseServerException.ERROR_MESSAGE
                  + e.getMessage());
          }

          return createID;
      }

      /* (non-Javadoc)
       * @see suncertify.db.DBMain#lock(int)
       */
      public void lock(int recNo) throws RecordNotFoundException
      {
          try
          {
              getServer().lock(recNo);
          }
          catch (RemoteException e)
          {
              throw new DatabaseServerException(DatabaseServerException.ERROR_MESSAGE
                  + e.getMessage());
          }
      }

      /* (non-Javadoc)
       * @see suncertify.db.DBMain#unlock(int)
       */
      public void unlock(int recNo) throws RecordNotFoundException
      {
          try
          {
              getServer().unlock(recNo);
          }
          catch (RemoteException e)
          {
              throw new DatabaseServerException(DatabaseServerException.ERROR_MESSAGE
                  + e.getMessage());
          }
      }

      /* (non-Javadoc)
       * @see suncertify.db.DBMain#isLocked(int)
       */
      public boolean isLocked(int recNo) throws RecordNotFoundException
      {
          boolean locked = false;

          try
          {
              locked = getServer().isLocked(recNo);
          }
          catch (RemoteException e)
          {
              throw new DatabaseServerException(DatabaseServerException.ERROR_MESSAGE
                  + e.getMessage());
          }

          return locked;
      }
  }
  ;
