  package com.rohitsood.urlybird.server;

  import java.rmi.Remote;
  import java.rmi.RemoteException;
  import java.rmi.dgc.VMID;


  /**
   * Factory for creating new server objects based on client uniqueness. A <tt>DatabaseNetworkServer</tt>
   * object is created for each unique client. The uniqueness of the client is defined by the <tt>VMID</tt>
   * instance it passes to the factory.
   *
   * @author Rohit Sood
   * @version 1.1
   */
  public interface DatabaseNetworkServerFactory extends Remote
  {
      /**
       * Creates a new <tt>DatabaseNetworkServer</tt> instance based on a unique <tt>VMID</tt> instance. Each
       * call to this method will create a new server instance and returns it to the client.
       *
       * @param vmid The unique id of the client for which a new server instance should be returned.
       *
       * @return An instance of <tt>DatabaseNetworkServer</tt>.
       *
       * @throws RemoteException If an exception is thrown remotely.
       */
      DatabaseNetworkServer createServer(VMID vmid)
          throws RemoteException;
  }
