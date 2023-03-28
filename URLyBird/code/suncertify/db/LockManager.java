package suncertify.db;

import java.rmi.dgc.VMID;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


/**
 * Manages locks for database records. This is a singleton and manages a map of locked records and <tt>VMID</tt>.
 * Contains a map of locked records and <tt>VMID</tt> of the clients.
 *
 * @author Rohit Sood
 * @version 1.0
 */
public final class LockManager {
    /** Instance of this. */
    private static LockManager lockManager;

    /** Map of locked records. Maps a record id to a unique client. */
    private static final Map LOCKED_RECORDS_MAP = Collections.synchronizedMap(new HashMap());

    /**
     * Disallows the creation of a new <tt>LockManager</tt> object from outside this class.
     */
    private LockManager() {
    }

    /**
     * Gets the singleton instance of the <tt>LockManager</tt>.
     *
     * @return The <tt>LockManager</tt>
     */
    public static LockManager getInstance() {
        if (lockManager == null) {
            lockManager = new LockManager();
        }

        return lockManager;
    }

    /**
     * Clears all locks for the client with the current <tt>VMID</tt>. This is called when the client is shutting down
     * and no longer needs database access. Synchronizes access to the locked records.
     *
     * @param vmid The <tt>VMID</tt> of the client for whom the locks must be cleared.
     */
    public void clearLocks(VMID vmid) {
        Iterator keyIterator = null;

        synchronized (LOCKED_RECORDS_MAP) {
            keyIterator = LOCKED_RECORDS_MAP.keySet().iterator();
        }

        while (keyIterator.hasNext()) {
            final Integer recordNumber = (Integer) keyIterator.next();
            final VMID id = (VMID) LOCKED_RECORDS_MAP.get(recordNumber);

            //if the vmids match - delete the lock
            if (id.equals(vmid)) {
                LOCKED_RECORDS_MAP.remove(recordNumber);
            }
        }
    }

    /**
     * Locks a record for the given client id. Synchronizes access to the locked records.
     *
     * @param recordId The record to lock.
     * @param client The client that wishes to lock this record.
     */
    void lock(Integer recordId, VMID client) {
        synchronized (LOCKED_RECORDS_MAP) {
            if (!LOCKED_RECORDS_MAP.containsKey(recordId)) {
                LOCKED_RECORDS_MAP.put(recordId, client);
            }
        }
    }

    /**
     * Gets the client which has locked the given record. Synchronizes access to the locked records.
     *
     * @param recordId The record id for which to get the client.
     *
     * @return The <tt>VMID</tt> of the client that locked this record.
     */
    VMID getClient(Integer recordId) {
        synchronized (LOCKED_RECORDS_MAP) {
            if (LOCKED_RECORDS_MAP.containsKey(recordId)) {
                return (VMID) LOCKED_RECORDS_MAP.get(recordId);
            }
        }

        return null;
    }

    /**
     * Unlocks the record if the record is locked by the same client who wished to unlock it.
     *
     * @param recordId The record id to unlock.
     */
    void unlock(Integer recordId, VMID vmid) {
        synchronized (LOCKED_RECORDS_MAP) {
            if (LOCKED_RECORDS_MAP.containsKey(recordId) && LOCKED_RECORDS_MAP.get(recordId).equals(vmid)) {
                LOCKED_RECORDS_MAP.remove(recordId);
            }
        }
    }

    /**
     * Checks to see if the record is currently locked by any client other than the client itself. If the client
     * calling this method has the record locked, then that client gets an unlocked status.
     *
     * @param recordId The record id to check for locks.
     * @param vmid The client that is checking the lock.
     *
     * @return <tt>true</tt> if the record is locked by some other client, <tt>false</tt> otherwise.
     */
    boolean isLocked(Integer recordId, VMID vmid) {
        boolean locked = false;

        synchronized (LOCKED_RECORDS_MAP) {
            //if the record is locked and the client in not vmid then its locked
            if (LOCKED_RECORDS_MAP.containsKey(recordId) && !LOCKED_RECORDS_MAP.get(recordId).equals(vmid)) {
                locked = true;
            }
        }

        return locked;
    }
}
