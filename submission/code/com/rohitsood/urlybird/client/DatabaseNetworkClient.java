package com.rohitsood.urlybird.client;

import com.rohitsood.urlybird.config.ConfigurationProperties;
import com.rohitsood.urlybird.mode.Mode;
import com.rohitsood.urlybird.server.DatabaseNetworkServer;
import com.rohitsood.urlybird.server.DatabaseNetworkServerFactory;

import suncertify.db.DuplicateKeyException;
import suncertify.db.RecordNotFoundException;
import suncertify.db.ValidationException;

import java.net.MalformedURLException;

import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;


/**
 * Provides access to the database server over a network. The network client is responsible to connect to the remote
 * database server. Allows local clients to interact with the remote database server.
 *
 * @author Rohit Sood
 * @version 1.3
 */
class DatabaseNetworkClient extends AbstractDatabaseClient {
    /** The network server factory. */
    private DatabaseNetworkServerFactory factory;

    /** The server which connects to the data implementation class. */
    private DatabaseNetworkServer server;

    /**
     * Default constructor.
     */
    DatabaseNetworkClient() {
    }

    /* (non-Javadoc)
     * @see com.rohitsood.urlybird.client.DatabaseClient#disconnect()
     */
    public void disconnect() {
        server = null;
        factory = null;
        System.gc();
    }

    /* (non-Javadoc)
     * @see com.rohitsood.urlybird.Mode#getMode()
     */
    public int getMode() {
        return Mode.ONLINE;
    }

    /**
     * This method is called internally to get a server to connect with. All calls are delegate to this server.
     *
     * @return An instance of the remote server.
     */
    private DatabaseNetworkServer getServer() {
        if (server == null) {
            try {
                final ConfigurationProperties config = new ConfigurationProperties();

                if (factory == null) {
                    String lookupName = config.getLookupName(); //get the lookup name
                    factory = (DatabaseNetworkServerFactory) Naming.lookup(lookupName); //look up the factory
                }

                //if the factory lookup did not result in a null ask it to create a server
                if (factory != null) {
                    server = factory.createServer(super.getVMID());
                } else {
                    throw new RuntimeException("Unable to create a server.");
                }
            } catch (MalformedURLException e) {
                disconnect();

                final String msg = (e.getMessage() == null) ? " Unknown Reasons " : e.getMessage();
                throw new DatabaseServerException("There was a problem with the remote server URL.\n" + msg);
            } catch (RemoteException e) {
                handleRemoteException(e);
            } catch (NotBoundException e) {
                disconnect();

                final String msg = (e.getMessage() == null) ? " Unknown Reasons " : e.getMessage();
                throw new DatabaseServerException("Server is not bound to the registry.\n" + msg);
            }
        }

        return server;
    }

    /* (non-Javadoc)
     * @see com.rohitsood.urlybird.client.DatabaseClient#book(java.lang.String, java.lang.String, java.lang.String)
     */
    public boolean book(String recId, String maxOccupancy, String customerId) throws RecordNotFoundException, ValidationException {
        boolean confirmed = false;

        try {
            confirmed = getServer().book(recId, maxOccupancy, customerId);
        } catch (RemoteException e) {
            handleRemoteException(e);
        }

        return confirmed;
    }

    /* (non-Javadoc)
     * @see suncertify.db.DBMain#read(int)
     */
    public String[] read(int recNo) throws RecordNotFoundException {
        String[] ids = null;

        try {
            ids = getServer().read(recNo);
        } catch (RemoteException e) {
            handleRemoteException(e);
        }

        return ids;
    }

    /* (non-Javadoc)
     * @see suncertify.db.DBMain#update(int, java.lang.String[])
     */
    public void update(int recordNumber, String[] data) throws RecordNotFoundException {
        try {
            getServer().update(recordNumber, data);
        } catch (RemoteException e) {
            handleRemoteException(e);
        }
    }

    /* (non-Javadoc)
     * @see suncertify.db.DBMain#delete(int)
     */
    public void delete(int recNo) throws RecordNotFoundException {
        try {
            getServer().delete(recNo);
        } catch (RemoteException e) {
            handleRemoteException(e);
        }
    }

    /* (non-Javadoc)
     * @see suncertify.db.DBMain#find(java.lang.String[])
     */
    public int[] find(String[] criteria) throws RecordNotFoundException {
        int[] ids = null;

        DatabaseNetworkServer s = getServer();

        try {
            ids = s.find(criteria);
        } catch (RemoteException e) {
            handleRemoteException(e);
        }

        return ids;
    }

    /* (non-Javadoc)
     * @see suncertify.db.DBMain#create(java.lang.String[])
     */
    public int create(String[] data) throws DuplicateKeyException {
        int createID = -1;

        try {
            createID = getServer().create(data);
        } catch (RemoteException e) {
            handleRemoteException(e);
        }

        return createID;
    }

    /* (non-Javadoc)
     * @see suncertify.db.DBMain#lock(int)
     */
    public void lock(int recNo) throws RecordNotFoundException {
        try {
            getServer().lock(recNo);
        } catch (RemoteException e) {
            handleRemoteException(e);
        }
    }

    /* (non-Javadoc)
     * @see suncertify.db.DBMain#unlock(int)
     */
    public void unlock(int recNo) throws RecordNotFoundException {
        try {
            getServer().unlock(recNo);
        } catch (RemoteException e) {
            handleRemoteException(e);
        }
    }

    /* (non-Javadoc)
     * @see suncertify.db.DBMain#isLocked(int)
     */
    public boolean isLocked(int recNo) throws RecordNotFoundException {
        boolean locked = false;

        try {
            locked = getServer().isLocked(recNo);
        } catch (RemoteException e) {
            handleRemoteException(e);
        }

        return locked;
    }

    /**
     * Handles a remote exception. Disconnects the client and throws <tt>DatabaseServerException</tt>.
     *
     * @param re The <tt>RemoteException</tt> to handle.
     */
    private void handleRemoteException(RemoteException re) {
        disconnect();
        throw new DatabaseServerException(DatabaseServerException.ERROR_MESSAGE + re.getMessage());
    }
}
