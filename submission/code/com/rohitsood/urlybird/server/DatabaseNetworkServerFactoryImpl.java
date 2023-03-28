package com.rohitsood.urlybird.server;

import java.rmi.NoSuchObjectException;
import java.rmi.RemoteException;
import java.rmi.dgc.VMID;
import java.rmi.server.UnicastRemoteObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;


/**
 * Provides the implementation for the <tt>DatabaseNetworkServerFactory</tt>. Allows the client to create a new
 * instance of <tt>DatabaseNetworkServer</tt>.
 *
 * @author Rohit Sood
 * @version 1.1
 */
class DatabaseNetworkServerFactoryImpl extends UnicastRemoteObject implements DatabaseNetworkServerFactory {
    /** The list of server instances. */
    private static List servers = null;

    /**
     * Creates a new DatabaseNetworkServerFactoryImpl object.
     *
     * @throws RemoteException If a remote problems happens instantiating.
     */
    public DatabaseNetworkServerFactoryImpl() throws RemoteException {
        super();

        //if the list of servers is null, then create a new list.
        if (null == servers) {
            servers = Collections.synchronizedList(new ArrayList());
        }
    }

    /* (non-Javadoc)
     * @see com.rohitsood.urlybird.server.DatabaseNetworkServerFactory
     * #createServer(java.rmi.dgc.VMID)
     */
    public DatabaseNetworkServer createServer(VMID vmid) throws RemoteException {
        final DatabaseNetworkServer server = new DatabaseNetworkServerImpl(vmid);

        synchronized (servers) {
            servers.add(server);
        }

        return server;
    }

    /**
     * Shuts down the server. Locates all instances of the remote server and unexports them. Once the unexports are
     * complete the list of servers is nulled out.
     *
     * @return <tt>true</tt> if the shutdown was successful, <tt>false</tt> if there were problems shutting down.
     */
    public static boolean shutDown() {
        boolean clean = true;

        if (servers == null) {
            return clean;
        }

        Iterator i = null;

        synchronized (servers) {
            i = servers.iterator();
        }

        while (i.hasNext()) {
            DatabaseNetworkServer server = (DatabaseNetworkServer) i.next();

            try {
                UnicastRemoteObject.unexportObject(server, true);
            } catch (NoSuchObjectException e) {
                clean = false;
            }
        }

        servers = null;

        return clean;
    }
}
