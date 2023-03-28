package com.rohitsood.urlybird.client;

import suncertify.db.RecordNotFoundException;
import suncertify.db.ValidationException;

import java.rmi.dgc.VMID;


/**
 * Responsible for creating a unique <tt>VMID</tt> for the client. All sub-classes are required to be able to book
 * rooms. The sub-classes will use the <tt>VMID</tt> created by this class.
 *
 * @author Rohit Sood
 * @version 1.4
 */
abstract class AbstractDatabaseClient implements DatabaseClient {
    /** The unique virtual machine id of the client. */
    private static final VMID CLIENT_VMID = new VMID();

    /**
     * Default no-args cosntructor creates a new <tt>AbstractDatabaseClient</tt> object.
     */
    AbstractDatabaseClient() {
    }

    /**
     * Gets the VMID associated with this virtual machine. The VMID is created once and cached as a constant. Calls to
     * this returns the cached <tt>VMID</tt>
     *
     * @return The unique VMID.
     */
    public VMID getVMID() {
        return CLIENT_VMID;
    }

    /* (non-Javadoc)
     * @see com.rohitsood.urlybird.client.DatabaseClient#book(java.lang.String, java.lang.String, java.lang.String)
     */
    public abstract boolean book(String recId, String maxOccupancy, String customerId) throws RecordNotFoundException, ValidationException;
}
