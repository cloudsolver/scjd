  /**
   * URLyBird Application Copyright Notice. Programmed by: Rohit Sood. Motivation: Sun Certified Java Developer
   * for Java 1.4 (2004 All rights reserved.)
   */
  package com.rohitsood.urlybird.server;

  import java.rmi.RemoteException;
  import java.rmi.dgc.VMID;
  import java.rmi.server.UnicastRemoteObject;


  /**
   * Provides the implementation for the <tt>DatabaseNetworkServerFactory</tt>. Allows the client to create a
   * new instance of <tt>DatabaseNetworkServer</tt>.
   *
   * @author Rohit Sood
   * @version 1.1
   */
  public class DatabaseNetworkServerFactoryImpl extends UnicastRemoteObject
          implements DatabaseNetworkServerFactory
  {
      /**
       * Creates a new DatabaseNetworkServerFactoryImpl object.
       *
       * @throws RemoteException If a remote problems happens instantiating.
       */
      public DatabaseNetworkServerFactoryImpl() throws RemoteException
      {
          super();
      }

      /* (non-Javadoc)
       * @see com.rohitsood.urlybird.server.DatabaseNetworkServerFactory
       * #createServer(java.rmi.dgc.VMID)
       */
      public DatabaseNetworkServer createServer(VMID vmid)
          throws RemoteException
      {
          final DatabaseNetworkServer server = new DatabaseNetworkServerImpl(vmid);

          return server;
      }
  }
