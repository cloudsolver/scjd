  /**
   * URLyBird Application Copyright Notice. Programmed by: Rohit Sood. Motivation: Sun Certified Java
   * Developer for Java 1.4 (2004 All rights reserved.)
   */
  package com.rohitsood.urlybird.server;

  import suncertify.db.DuplicateKeyException;
  import suncertify.db.RecordNotFoundException;
  import suncertify.db.ValidationException;

  import java.rmi.Remote;
  import java.rmi.RemoteException;


  /**
   * Remote database server supports all data access funtions required  by the system. As defined by
   * <tt>DBMain</tt> this permits clients to manage the data records. In addition this also permits the
   * user to book a room.
   *
   * @author Rohit Sood
   * @version 1.4
   */
  public interface DatabaseNetworkServer extends Remote
  {
      /**
       * Books a room for the customer.  Validation is made based on the current system date, customer id
       * and max occupancy.
       *
       * @param recId The record id of the room to be booked.
       * @param maxOccupancy The expected number of guests.
       * @param customerId The customer id of the customer for whom this room is requested.
       *
       * @return <tt>true</tt> if the room was booked successfully.
       *
       * @throws RecordNotFoundException If the record does not exist.
       * @throws ValidationException If the room is not available or the validation failerd.
       */
      boolean book(String recId, String maxOccupancy, String customerId)
          throws RecordNotFoundException, ValidationException, RemoteException;

      /**
       * Reads a record from the file. Returns an array where each element is a record value.
       *
       * @param recNo The record number of the record to be read.
       */
      String[] read(int recNo) throws RecordNotFoundException, RemoteException;

      /**
       * Modifies the fields of a record. The new value for field n appears in data[n].
       *
       * @param recordNumber The record number to update.
       * @param data The data to update the record with.
       *
       * @throws RecordNotFoundException If the record does not exist.
       * @throws RemoteException If an exception happens remotely.
       */
      void update(int recordNumber, String[] data)
          throws RecordNotFoundException, RemoteException;

      /**
       * Deletes a record, making the record number and associated disk storage available for reuse.
       *
       * @param recNo The record number to delete.
       *
       * @throws RecordNotFoundException If the record is not found.
       * @throws RemoteException If an exception happens remotely.
       */
      void delete(int recNo) throws RecordNotFoundException, RemoteException;

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
       * @throws RemoteException If any problems occured remotely.
       */
      int[] find(String[] criteria) throws RecordNotFoundException, RemoteException;

      /**
       * Creates a new record in the database (possibly reusing a deleted entry). Inserts the given data,
       * and returns the record number of the new record.
       *
       * @param data The data record to be created.
       *
       * @return The record number of the new record.
       *
       * @throws DuplicateKeyException If the record number alread exists.
       * @throws RemoteException If any problems occured remotely.
       */
      int create(String[] data) throws DuplicateKeyException, RemoteException;

      /**
       * Locks a record so that it can only be updated or deleted by this client. If the specified record
       * is already locked, the current thread gives up the CPU and consumes no CPU cycles until the
       * record is unlocked.
       *
       * @param recNo The record number of the record to lock.
       *
       * @throws RecordNotFoundException If the record could not be found.
       * @throws RemoteException If any problems occured remotely.
       */
      void lock(int recNo) throws RecordNotFoundException, RemoteException;

      /**
       * Releases a lock currently held.
       *
       * @param recNo The record number of the record for which the lock is to be released.
       *
       * @throws RecordNotFoundException If the record could not be found.
       * @throws RemoteException If any problems occured remotely.
       */
      void unlock(int recNo) throws RecordNotFoundException, RemoteException;

      /**
       * Determines if a record is currenly locked. Returns true if the record is locked, false otherwise.
       *
       * @param recNo The record number to query for locks.
       *
       * @return <tt>true</tt> If the record is locked.
       *
       * @throws RecordNotFoundException If the record could not be found.
       * @throws RemoteException If any problems occured remotely.
       */
      boolean isLocked(int recNo) throws RecordNotFoundException, RemoteException;
  }
