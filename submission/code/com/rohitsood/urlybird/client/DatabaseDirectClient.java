package com.rohitsood.urlybird.client;

import suncertify.db.DBMain;
import suncertify.db.Data;
import suncertify.db.DuplicateKeyException;
import suncertify.db.LockManager;
import suncertify.db.RecordNotFoundException;
import suncertify.db.RoomBooker;
import suncertify.db.ValidationException;


/**
 * Provides direct access to the underlying database file system. No remote calls are made. This serves as a proxy for
 * the <tt>Data</tt> and <tt>RoomBooker</tt> class. Most methods delegate calls to the <tt>Data</tt> class.
 *
 * @author Rohit Sood
 * @version 1.1
 */
class DatabaseDirectClient extends AbstractDatabaseClient {
    /** The data implementation instance */
    private DBMain db;

    /**
     * Default constructor. Creates a new instance of VMID and Data instance. The unique id is obtained from the super
     * class.
     */
    DatabaseDirectClient() {
        super();
    }

    /**
     * Initializes the data implementation class
     */
    private void init() {
        if (null == db) {
            db = new Data(super.getVMID());
        }
    }

    /**
     * @see DatabaseClient#disconnect
     */
    public void disconnect() {
        if (null != db) {
            LockManager.getInstance().clearLocks(super.getVMID());
            db = null;
        }

        System.gc();
    }

    /**
     * @see DatabaseClient#book(String,String,String)
     */
    public boolean book(String recId, String maxOccupancy, String customerId) throws RecordNotFoundException, ValidationException {
        init();

        boolean confirmed = false;

        RoomBooker booker = new RoomBooker(db);
        booker.book(recId, maxOccupancy, customerId);
        confirmed = true;

        return confirmed;
    }

    /**
     * @see DatabaseClient#create(String[])
     */
    public int create(String[] data) throws DuplicateKeyException {
        init();
		int recordId=db.create(data);
        return recordId;
    }

    /**
     * @see DatabaseClient#delete(int)
     */
    public void delete(int recNo) throws RecordNotFoundException {
        init();
        db.delete(recNo);
    }

    /**
     * @see DatabaseClient#find(String[])
     */
    public int[] find(String[] criteria) throws RecordNotFoundException {
        init();
		int[] recordIds=db.find(criteria);
        return recordIds;
    }

    /**
     * @see DatabaseClient#isLocked(int)
     */
    public boolean isLocked(int recNo) throws RecordNotFoundException {
        init();
		boolean locked=db.isLocked(recNo);
        return locked;
    }

    /**
     * @see DatabaseClient#lock(int)
     */
    public void lock(int recNo) throws RecordNotFoundException {
        init();
        db.lock(recNo);
    }

    /**
     * @see DatabaseClient#read(int)
     */
    public String[] read(int recNo) throws RecordNotFoundException {
        init();
		String[] record=db.read(recNo);
        return record;
    }

    /**
     * DatabaseClient#unlock(int)
     */
    public void unlock(int recNo) throws RecordNotFoundException {
        init();
        db.unlock(recNo);
    }

    /**
     * @see DatabaseClient#update(int,String[])
     */
    public void update(int recNo, String[] data) throws RecordNotFoundException {
        init();
        db.update(recNo, data);
    }
}
