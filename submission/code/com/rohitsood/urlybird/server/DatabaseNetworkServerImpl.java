package com.rohitsood.urlybird.server;

import suncertify.db.Data;
import suncertify.db.DuplicateKeyException;
import suncertify.db.LockManager;
import suncertify.db.RecordNotFoundException;
import suncertify.db.RoomBooker;
import suncertify.db.ValidationException;

import java.rmi.RemoteException;
import java.rmi.dgc.VMID;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.server.Unreferenced;


/**
 * This is the implementation of the <tt>DatabaseNetworkServer</tt>. A new instance is created for every client request
 * based on the client's <tt>VMID</tt>. Instantiates a <tt>Data</tt> object using the <tt>VMID</tt> of the client.
 * Interacts with the database directly by delegating all calls to it. When the client disconnects, all locks held by
 * that client are released.
 *
 * @author Rohit Sood
 * @version 1.4
 */
class DatabaseNetworkServerImpl extends UnicastRemoteObject implements DatabaseNetworkServer, Unreferenced {
    /** The database to interact with. */
    private Data db;
	private VMID vmid;
    /**
     * Creates a new DatabaseNetworkServerImpl object.
     *
     * @param vmid The unique client id.
     *
     * @throws RemoteException If there is a problem with the server.
     */
    public DatabaseNetworkServerImpl(VMID vmid) throws RemoteException {
        super();
        this.vmid=vmid;
        db = new Data(vmid);
    }

    /* (non-Javadoc)
     * @see com.rohitsood.urlybird.server.DatabaseNetworkServer
     * #book(java.lang.String, java.lang.String, java.lang.String)
     */
    public boolean book(String recId, String maxOccupancy, String customerId) throws RecordNotFoundException, ValidationException {
        boolean confirmed = false;

        RoomBooker booker = new RoomBooker(db);
        booker.book(recId, maxOccupancy, customerId);
        confirmed = true;

        return confirmed;
    }

    /**
     * Called when the client terminates the session and disconnects from the server. All database locks held by the
     * client are released. And a garbage collection is suggested.
     */
    public void unreferenced() {
        LockManager.getInstance().clearLocks(vmid);
        System.gc();
    }

    /* (non-Javadoc)
     * @see com.rohitsood.urlybird.server.DatabaseNetworkServer#create(java.lang.String[])
     */
    public int create(String[] data) throws DuplicateKeyException {
        return db.create(data);
    }

    /* (non-Javadoc)
     * @see com.rohitsood.urlybird.server.DatabaseNetworkServer#delete(int)
     */
    public void delete(int recNo) throws RecordNotFoundException {
        db.delete(recNo);
    }

    /* (non-Javadoc)
     * @see com.rohitsood.urlybird.server.DatabaseNetworkServer#find(java.lang.String[])
     */
    public int[] find(String[] criteria) throws RecordNotFoundException {
        return db.find(criteria);
    }

    /* (non-Javadoc)
     * @see com.rohitsood.urlybird.server.DatabaseNetworkServer#isLocked(int)
     */
    public boolean isLocked(int recNo) throws RecordNotFoundException {
        return db.isLocked(recNo);
    }

    /* (non-Javadoc)
     * @see com.rohitsood.urlybird.server.DatabaseNetworkServer#lock(int)
     */
    public void lock(int recNo) throws RecordNotFoundException {
        db.lock(recNo);
    }

    /* (non-Javadoc)
     * @see com.rohitsood.urlybird.server.DatabaseNetworkServer#read(int)
     */
    public String[] read(int recNo) throws RecordNotFoundException {
        return db.read(recNo);
    }

    /* (non-Javadoc)
     * @see com.rohitsood.urlybird.server.DatabaseNetworkServer#unlock(int)
     */
    public void unlock(int recNo) throws RecordNotFoundException {
        db.unlock(recNo);
    }

    /* (non-Javadoc)
     * @see com.rohitsood.urlybird.server.DatabaseNetworkServer#update(int, java.lang.String[])
     */
    public void update(int recNo, String[] data) throws RecordNotFoundException {
        db.update(recNo, data);
    }
}
