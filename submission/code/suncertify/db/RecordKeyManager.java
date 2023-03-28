package suncertify.db;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


/**
 * Provides the same record key object and reuses the key. Caches the record id as an <tt>Integer</tt> and reuses the
 * same instance. Access to the key can then be synchronized.
 *
 * @author Rohit Sood
 * @version 1.3
 */
final class RecordKeyManager {
    /** Integer pairs, where the second integer is used for locking records */
    private static final Map RECORD_KEYS = Collections.synchronizedMap(new HashMap());

    /**
     * Prevents the creation of a new KeyUtil object outside this class.
     */
    private RecordKeyManager() {
    }

    /**
     * Returns a cached key object given the record number. If a cached key is not available then a new one is created
     * and returned. The first hit also adds the key to the cache of keys.
     *
     * @param recNo The record number to lookup.
     *
     * @return The record key.
     */
    public static Integer getKey(int recNo) {
        Integer key = null;

        //need to synchronize access to keys
        synchronized (RECORD_KEYS) {
            key = (Integer) RECORD_KEYS.get(new Integer(recNo));

            if (key == null) {
                final Integer newkey = new Integer(recNo);
                RECORD_KEYS.put(key, newkey);
                key=newkey;
            }
        }

        return key;
    }
}
