 package suncertify.db;

  /**
   * This is the interface which defines the data access class A record is never physically deleted from
   * the data file A record number is the logical pointer to the start of a particular record.
   *
   * @author Sun Microsystems
   */
  public interface DBMain
  {
      /**
       * Reads a record from the file. Returns an array where each element is a record value.
       *
       * @param recNo The record number of the record to be read.
       */
       String[] read(int recNo) throws RecordNotFoundException;

      /**
       * Modifies the fields of a record. The new value for field n appears in data[n].
       *
       * @param recNo The record number to update.
       * @param data The data to update the record with.
       */
       void update(int recNo, String[] data)
          throws RecordNotFoundException;

      /**
       * Deletes a record, making the record number and associated disk storage available for reuse.
       *
       * @param recNo this is the record number to be deleted
       */
       void delete(int recNo) throws RecordNotFoundException;

      /**
       * Returns an array of record numbers that match the specified criteria. Field n in the database
       * file is described by criteria[n]. A null value in criteria[n] matches any field value. A
       * non-null  value in criteria[n] matches any field value that begins with criteria[n]. (For
       * example, "Fred" matches "Fred" or "Freddy".)
       *
       * @param criteria The criteria against which to query the database.
       *
       * @return Array of record numbers which matched the criteria.
       *
       * @throws RecordNotFoundException If no records were found for the criteria.
       */
       int[] find(String[] criteria) throws RecordNotFoundException;

      /**
       * Creates a new record in the database (possibly reusing a deleted entry). Inserts the given data,
       * and returns the record number of the new record.
       *
       * @param data The data record to be created.
       *
       * @return The record number of the new record.
       *
       * @throws DuplicateKeyException If the record number alread exists.
       */
       int create(String[] data) throws DuplicateKeyException;

      /**
       * Locks a record so that it can only be updated or deleted by this client. If the specified record
       * is already locked, the current thread gives up the CPU and consumes no CPU cycles until the
       * record is unlocked.
       *
       * @param recNo The record number of the record to lock.
       *
       * @throws RecordNotFoundException If the record could not be found.
       */
       void lock(int recNo) throws RecordNotFoundException;

      /**
       * Releases a lock currently held.
       *
       * @param recNo The record number of the record for which the lock is to be released.
       *
       * @throws RecordNotFoundException If the record could not be found.
       */
       void unlock(int recNo) throws RecordNotFoundException;

      /**
       * Determines if a record is currenly locked. Returns true if the record is locked, false otherwise.
       *
       * @param recNo The record number to query for locks.
       *
       * @return <tt>true</tt> If the record is locked.
       *
       * @throws RecordNotFoundException If the record could not be found.
       */
       boolean isLocked(int recNo) throws RecordNotFoundException;
  }
