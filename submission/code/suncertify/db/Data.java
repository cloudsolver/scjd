package suncertify.db;

import com.rohitsood.urlybird.config.ConfigurationProperties;
import com.rohitsood.urlybird.util.RandomAccessFileUtility;
import com.rohitsood.urlybird.util.StringUtil;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

import java.rmi.dgc.VMID;

import java.util.ArrayList;
import java.util.List;


/**
 * This is the actual data access class. All database activity implementation lives here. It is an implementation of
 * the <tt>DBMain</tt> interface and implements all methods defined there. This class can be accessed only with a
 * <tt>VMID</tt> which is used for lock tracking and maintenence. This uses the <tt>RandomAccessFile</tt> to seek,
 * create, read, update and delete data.
 *
 * @author Rohit Sood
 * @version 1.6
 */
public class Data implements DBMain, DataRow {
    /** The meta data reader for the database file. */
    private static MetaData metaDataReader;

    /** Flag to indicate whether debug messages should be printed to the console. */
    private static boolean debug = false;

    /** The configuration properties as specified in the suncertify.properties file. */
    private ConfigurationProperties properties;

    /** The unique id of the client invoking calls to the database. */
    private VMID vmid;

    /**
     * Creates a new Data object. This can be constructed only with a <tt>VMID</tt>. A new instance is created for a
     * unique client. This is used for locked records.
     *
     * @param vmid The unique client id.
     */
    public Data(VMID vmid) {
        this.vmid = vmid;
        metaDataReader = MetaData.getInstance();
    }

    /**
     * Retrieves the database file in a given mode. All modes of the <tt>RandomAccessFile</tt> are supported.
     *
     * @param mode The mode in which the files
     *
     * @return The <tt>RandomAccessFile</tt> instance.
     *
     * @throws IOException If there was a problem obtaining the file.
     */
    private RandomAccessFile getFile(String mode) throws IOException {
        properties = new ConfigurationProperties();

        RandomAccessFile file = null;

        try {
            file = new RandomAccessFile(properties.getDbLocation(), mode);
        } catch (Exception e) {
            throw new IOException("Database file could not be located or created.");
        }

        return file;
    }

    /**
     * Retrives the first deleted record. Searches for the lowest record id available. If a record has been logically
     * deleted it returns its record id, and consequent data is overwritten. If a record is locked it will not make it
     * available even though a deletion is detected for it.
     *
     * @return The next available record id.
     *
     * @throws IOException If there is problem reading the database file.
     * @throws RecordNotFoundException If no reusable record ids were found.
     */
    private int findFirstDeletedRecord() throws IOException, RecordNotFoundException {
        int record = -1;

        for (int a = 1; a < getTotalRecords(); a++) {
            //check for all deleted records
            debug("isDeleted? Checking record " + a);

            boolean deleted = isDeleted(a);

            if (deleted) {
                debug("deleted record is found " + a);
                record = a;
                debug("record is set to " + record);
                lock(record);

                break;
            }

            debug("Checked record " + a);
        }

        debug("First deleted record is " + record);

        if (record < 0) {
            debug("Throwing exception because Record is = " + record);
            throw new RecordNotFoundException("No deleted records available for reuse");
        }

        return record;
    }

    /**
     * Selects the next available record id which is currently deleted. If no logically deleted records are available
     * for reuse then the max record id is incremented by one. The newly created record id locked.
     *
     * @return The new record id.
     *
     * @throws IOException If there is a problem reading from the local database file.
     * @throws RecordNotFoundException If the record could not be found for locking.
     */
    private synchronized int newRecordID() throws IOException, RecordNotFoundException {
        int newRecordID = -1;

        //search for a deleted record to overwrite
        try {
            newRecordID = findFirstDeletedRecord();
            debug("Unused record id found: " + newRecordID);
        } catch (IOException e) {
            throw e;
        } catch (RecordNotFoundException e) {
            debug("No logically deleted records available for reuse.");
        }

        //no deleted record was found so prepare for new record
        if (newRecordID < 0) {
            newRecordID = ((int) getTotalRecords()) + 1;

            //lock this record - so it can be created
            try {
                lock(newRecordID);
            } catch (RecordNotFoundException e) {
                throw e;
            }

            debug("New Rec ID is " + (newRecordID));
        }

        return newRecordID;
    }

    /**
     * This counts the records - but does not ignore the deleted ones.
     *
     * @return The total number of records currently including logically deleted records.
     *
     * @throws IOException If there is a problem reading from the database file.
     */
    private long getTotalRecords() throws IOException {
        final RandomAccessFile file = getFile("r");
        final long totalLength = file.length();
        file.close();
        debug("file length=" + totalLength);

        if (totalLength >= Integer.MAX_VALUE) {
            throw new IOException("Max Database size of " + Integer.MAX_VALUE + " reached.");
        }

        final int len = (int) totalLength;

        final long totalRecords = (len - metaDataReader.getDataSectionPointer()) / metaDataReader.getDataSectionInterval();
        debug("Total Records=" + totalRecords);

        return totalRecords;
    }

    /* (non-Javadoc)
     * @see suncertify.db.DBMain#read(int)
     */
    public String[] read(int recNo) throws RecordNotFoundException {
        return read(recNo, false); //readDeletedRecord=false
    }

    /**
     * Reads records from the underlying database file system.  A provision is made to read even deleted records. This
     * can be leveraged for admin functions for an undelete functionality. No locks are held for the record that is
     * read.
     *
     * @param recNo The record number to read.
     * @param readDeletedRecord Flag indicating if deleted records should be read.
     *
     * @return The array of strings that represent that data.
     *
     * @throws RecordNotFoundException If the record was not found.
     */
    private String[] read(int recNo, boolean readDeletedRecord) throws RecordNotFoundException {
        RandomAccessFile file = null;
        final String[] record = new String[metaDataReader.getNumberOfFieldsPerRecord()];

        try {
            file = getFile("r");
            file.seek(seekPosition(recNo));

            final boolean deleted = file.readBoolean();

            if (deleted && !readDeletedRecord) //&& !ignoreDeleted)
             {
                throw new RecordNotFoundException("Record with id " + recNo + " is deleted, and cannot be accessed");
            }

            //read the record
            for (int item = 0; item < metaDataReader.getNumberOfFieldsPerRecord(); item++) {
                record[item] = RandomAccessFileUtility.readString(file, metaDataReader.getFieldLengths()[item]);
            }
        } catch (IOException e) {
            throw new RecordNotFoundException("Record with id " + recNo + " was not found ");
        } finally {
            try {
                file.close();
            } catch (IOException e1) {
                throw new RuntimeException("File could not be closed after reading " + recNo);
            }
        }

        return record;
    }

    /**
     * Given a record id, seeks the position in the file where the data record starts.
     *
     * @param recNo The id of the record to locate.
     *
     * @return The file pointer position of the record.
     */
    private long seekPosition(int recNo) {
        return metaDataReader.getDataSectionPointer() + ((recNo - 1) * metaDataReader.getDataSectionInterval());
    }

    /**
     * Checks to see if the record id is deleted. Locks the record before you call this method so that the value
     * returned is not dirty.
     *
     * @param recNo The record id to check for deleted flag.
     *
     * @return <tt>true</tt> if deleted, <tt>false</tt> otherwise.
     *
     * @throws RecordNotFoundException If the record is not  found.
     */
    private boolean isDeleted(int recNo) throws RecordNotFoundException {
        boolean deleted = true;
        RandomAccessFile file = null;

        try {
            file = getFile("r");
            file.seek(seekPosition(recNo));
            deleted = file.readBoolean();
        } catch (FileNotFoundException e) {
            throw new RecordNotFoundException("Record could not be found because File could not found " + e.getMessage());
        } catch (IOException e) {
            throw new RecordNotFoundException("Record could not be found because of an I/O problem" + e.getMessage());
        } finally {
            try {
                file.close();
            } catch (IOException e) {
                throw new RecordNotFoundException("An unexpected I/O problem occurred " + e.getMessage());
            }
        }

        return deleted;
    }

    /* (non-Javadoc)
         * @see suncertify.db.DBMain#update(int, java.lang.String[])
         */
    public void update(int recNo, String[] data) throws RecordNotFoundException {
        lock(recNo); //lock a record so that only the client that has this instance can update it.

        RandomAccessFile file = null;

        try {
            file = getFile("rw");
            writeRecord(file, recNo, data);
        } catch (IOException e) {
            throw new RecordNotFoundException("Could not write the record due to an I/O issue: " + e);
        } finally {
            try {
                unlock(recNo);
            } catch (RecordNotFoundException e) {
                e.printStackTrace();
            }

            try {
                file.close();
            } catch (IOException e) {
                throw new RecordNotFoundException("Could not close the database file due to an I/O issue: " + e);
            }
        }
    }

    /* (non-Javadoc)
     * @see suncertify.db.DBMain#delete(int)
     */
    public void delete(int recNo) throws RecordNotFoundException {
        RandomAccessFile file = null;

        try {
            lock(recNo);
            file = getFile("rw");
            file.seek(seekPosition(recNo));
            file.writeBoolean(true);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                unlock(recNo);
                file.close();
            } catch (IOException e) {
                throw new RecordNotFoundException("Could not delete the record due to an I/O issue: " + e);
            }
        }
    }

    /**
     * A future enhancement implementation for undeleting records that are logically deleted.
     *
     * @param recNo The record number to undelete
     *
     * @throws RecordNotFoundException If the record is not found.
     */
    public void undelete(int recNo) throws RecordNotFoundException {
        RandomAccessFile file = null;

        try {
            file = getFile("rw");
            file.seek(seekPosition(recNo));
            file.writeBoolean(false);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                file.close();
            } catch (IOException e) {
                throw new RecordNotFoundException("Could not undelete the record due to an I/O issue: " + e);
            }
        }
    }

    /**
     * If criteria[n] is null it is considered to be a match Also, if criteria[n] is an empty string it is considered
     * to be a match Record element 'n' must atleast begin with criteria[n] to be a match Searches are case-sensitive.
     *
     * @param criteria The criteria to search against.
     *
     * @throws RecordNotFoundException If no records are returned for the criteria.
     */
    public int[] find(String[] criteria) throws RecordNotFoundException {
        final List records = new ArrayList();
        int count = 0;
        int[] results = null;

        try {
            for (int recNo = 1; recNo <= getTotalRecords(); recNo++) {
                if (isDeleted(recNo)) {
                    continue;
                }

                final String[] record = read(recNo);

                if (isMatch(criteria, record)) {
                    debug("...Matched! " + recNo);

                    //add the record id
                    records.add(new Integer(recNo));
                    count++;
                }
            }
        } catch (IOException e) {
            throw new RecordNotFoundException("Record not found due to " + e.getMessage());
        }

        debug("Total Matches: " + count);

        if (count == 0) {
            throw new RecordNotFoundException(RecordNotFoundException.FIND_FAILED);
        }

        results = parseList(records);

        return results;
    }

    /**
     * Given a list of data records parses the list and retireves the record id.
     *
     * @param list The list of data records.
     *
     * @return The array of record ids.
     */
    private int[] parseList(List list) {
        final int[] ids = new int[list.size()];

        //go thru the list and grab an int
        for (int a = 0; a < list.size(); a++) {
            ids[a] = ((Integer) list.get(a)).intValue();
        }

        return ids;
    }

    /**
     * Matches a record based on criteria.  A match is all records if all the strings are marked as null or empty. If a
     * criteria field is not null then a match is determined. If none of the criteria are given then match everything
     * If atleast one of the criteria is given then filter based on that Each criteria is a logical AND rather than an
     * OR This is because an OR adds no value to the search
     *
     * @param criteria The criteria to match.
     * @param record The record to match against.
     *
     * @return <tt>true</tt> if it is a match, <tt>false</tt> otherwise.
     */
    private boolean isMatch(String[] criteria, String[] record) {
        boolean match = false;

        //if the entire criteria is null or empty match this record
        if (isEmptyOrNullCriteria(criteria)) {
            match = true;

            return match;
        }

        for (int n = 0; n < metaDataReader.getNumberOfFieldsPerRecord(); n++) {
            if ((null == criteria[n]) || criteria[n].trim().equals("")) {
                match = true;
            }

            if ((null != record[n]) && record[n].startsWith(criteria[n])) {
                match = true;
            } else {
                match = false;

                break;
            }
        }

        return match;
    }

    /**
     * Checks if the criteria object is null or all fields in it are empty or null.
     *
     * @param c The criteria array.
     *
     * @return <tt>true</tt> if the criteria is empty, null or has only empty or null items, <tt>false</tt> otherwise.
     */
    private boolean isEmptyOrNullCriteria(String[] c) {
        boolean nullOrEmpty = true;

        if (null == c) {
            return nullOrEmpty;
        }

        //if every element is either null or empty then return true
        for (int a = 0; a < c.length; a++) {
            if ((null != c[a]) && !"".equals(c[a])) //if the element is not null and not blank return false
             {
                nullOrEmpty = false;

                break;
            }
        }

        return nullOrEmpty;
    }

    /**
     * Create will first search any available recordID which is deleted If a deleted record is found - the new record
     * will be written there If no deleted record is found  in the database - then the new record will be written at
     * the end of the file
     *
     * @param record The record to be created.
     *
     * @return int The record id of the newly created record.
     *
     * @throws DuplicateKeyException If the same id already exists.
     */
    public int create(String[] record) throws DuplicateKeyException {
        debug("Enter create");

        int recNo = -1;
        RandomAccessFile file = null;

        try {
            file = getFile("rw");
            recNo = newRecordID(); //this record number comes in as a locked record.
            debug("Will create on " + recNo);
            file.seek(seekPosition(recNo));

            //it is guranteed that each concurrent thread will call the write method
            //with a unique recNo
            writeRecord(file, recNo, record);
        }catch(IOException ioe){
			throw new RuntimeException("An exception was encountered reading the database.");
        } catch (Exception e) {
            throw new RuntimeException("An unknown exception was encountered creating a record.");
        } finally {
            try {
                unlock(recNo);
            } catch (RecordNotFoundException rnfe) {
                throw new RuntimeException("There was a problem unlocking record" + recNo + " error: " + rnfe.getMessage());
            }

            try {
                file.close();
            } catch (IOException ioe) {
                throw new RuntimeException("There was a problem closing the database file. " + ioe.getMessage());
            }
        }

        debug("Returning " + recNo);

        return recNo;
    }

    /**
     * The data record is scrubbed before being saved. The scrubbing includes fixing the lengths of data to match the
     * schema.
     *
     * @param record The record to scrub.
     *
     * @return The scrubbed data record.
     */
    private String[] scrubRecord(String[] record) {
        final String[] scrubbedRecord = new String[record.length];

        for (int a = 0; a < record.length; a++) {
            scrubbedRecord[a] = StringUtil.fixLength(record[a], metaDataReader.getFieldLengths()[a]);
        }

        return scrubbedRecord;
    }

    /**
     * Writes the record to the underlying file-system. This method is expected to be called only when that record is
     * locked.
     *
     * @param file The file to write the data record to.
     * @param recNo The record number of the data record.
     * @param record The record to write.
     *
     * @throws IOException If there is a problem with the file system access.
     */
    private void writeRecord(RandomAccessFile file, int recNo, String[] record) throws IOException {
        //prepare the record to its exact length
        record = scrubRecord(record);

        long seekPosition = seekPosition(recNo);
        file.seek(seekPosition);
        debug("File pos is " + file.getFilePointer());
        file.writeBoolean(false); //not deleted - because its new/or updated

        for (int f = 0; f < metaDataReader.getNumberOfFieldsPerRecord(); f++) {
            file.writeBytes(record[f]);
        }
    }

    /* (non-Javadoc)
     * @see suncertify.db.DBMain#lock(int)
     */
    public void lock(int recNo) throws RecordNotFoundException {
        debug("Enter lock(" + recNo + ")");

        //If the record in question is locked by another thread
        //the current thread should detect it and wait for it to be released
        if (isLocked(recNo)) //has this record been locked by another client
         {
            try {
                debug(vmid + " waiting on " + LockManager.getInstance().getClient(new Integer(recNo)));

                final Integer key = RecordKeyManager.getKey(recNo); //get the key object from the key manager

                //synchronize access to the key such that this thread can then wait on the key
                synchronized (key) {
                    key.wait(); //this thread will give up the CPU until the key becomes available
                    debug(vmid + " done waiting ");
                    LockManager.getInstance().lock(key, vmid); //this thread will wake up and lock the key
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        } else {
            final Integer key = RecordKeyManager.getKey(recNo);

            synchronized (key) {
                LockManager.getInstance().lock(key, vmid);
            }
        }

        debug("Exit lock(" + recNo + ")\n");
    }

    /* (non-Javadoc)
     * @see suncertify.db.DBMain#unlock(int)
     */
    public void unlock(int recNo) throws RecordNotFoundException {
        debug("Enter unlock(" + recNo + ")");

        final Integer key = RecordKeyManager.getKey(recNo);

        //the thread needs serialized access to the key before it can unlock the record
        //the lock manager has serialized access to the locks
        //once the locks are released, the thread can notify the waiters on that key.
        synchronized (key) {
            LockManager.getInstance().unlock(key, vmid); //unlock the key
            key.notify(); //notify another waiter thread to take the key
        }

        debug("Exit	 unlock(" + recNo + ")");
    }

    /* (non-Javadoc)
     * @see suncertify.db.DBMain#isLocked(int)
     */
    public boolean isLocked(int recNo) throws RecordNotFoundException {
        boolean lock = false;

        final Integer key = RecordKeyManager.getKey(recNo);

        //check the lock manager for the existence of the key.
        lock = LockManager.getInstance().isLocked(key, vmid); //check for locks
        debug("Record " + recNo + " is locked? " + lock);

        return lock;
    }

    /**
     * Prints debug messages to the console. If the debug mode is <tt>false</tt> no messages are printed.
     *
     * @param msg The message to print.
     */
    private void debug(String msg) {
        if (!debug) {
            return;
        }

        System.out.println(msg);
    }
}
