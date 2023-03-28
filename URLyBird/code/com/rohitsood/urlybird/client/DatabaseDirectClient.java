  package com.rohitsood.urlybird.client;

  import com.rohitsood.urlybird.Mode;
  import com.rohitsood.urlybird.biz.validator.DataValidator;

  import suncertify.db.Data;
  import suncertify.db.DuplicateKeyException;
  import suncertify.db.RecordNotFoundException;
  import suncertify.db.ValidationException;

  import java.util.Date;


  /**
   * Provides direct access to the underlying database file system. No remote calls are made.
   * This serves as a proxy for the <tt>Data</tt> class. Most methods delegate calls to Data class.
   *
   *
   * @author Rohit Sood
   * @version 1.1
   */
  public class DatabaseDirectClient extends AbstractDatabaseClient
  {
      /**The data implementation instance */
      private Data db;

      /**
       * Default constructor. Creates a new instance of VMID and Data instance.
       * The unique id is obtained from the super class.
       */
      DatabaseDirectClient()
      {
          super();
          db = new Data(super.getVMID());
      }

      /**
       * @see DatabaseClient#getMode
       */
      public int getMode()
      {
          return Mode.OFFLINE;
      }

      /**
       * @see DatabaseClient#disconnect
       */
      public void disconnect()
      {
          db.clearLocks();
          db = null;
          System.gc();
      }

      /**
       * @see DatabaseClient#book(String,String,String)
       */
      public boolean book(String recId, String maxOccupancy, String customerId)
          throws RecordNotFoundException, ValidationException
      {
          super.book(recId, maxOccupancy, customerId);

          boolean      confirmed = false;

          final Date   today    = new Date();
          final int    recordId = Integer.parseInt(recId);
          final String[] data   = read(recordId);
          DataValidator.isBookable(data, maxOccupancy, customerId);
          data[Data.CUSTOMER_ARRAY_POSITION] = "" + customerId;

          update(recordId, data);

          confirmed = true;

          return confirmed;
      }

      /**
       * @see DatabaseClient#create(String[])
       */
      public int create(String[] data) throws DuplicateKeyException
      {
          return db.create(data);
      }

      /**
       * @see DatabaseClient#delete(int)
       */
      public void delete(int recNo) throws RecordNotFoundException
      {
          db.delete(recNo);
      }

      /**
       * @see DatabaseClient#find(String[])
       */
      public int[] find(String[] criteria) throws RecordNotFoundException
      {
          return db.find(criteria);
      }

      /**
       * @see DatabaseClient#isLocked(int)
       */
      public boolean isLocked(int recNo) throws RecordNotFoundException
      {
          return db.isLocked(recNo);
      }

      /**
       * @see DatabaseClient#lock(int)
       */
      public void lock(int recNo) throws RecordNotFoundException
      {
          db.lock(recNo);
      }

      /**
       * @see DatabaseClient#read(int)
       */
      public String[] read(int recNo) throws RecordNotFoundException
      {
          return db.read(recNo);
      }

      /**
       * DatabaseClient#unlock(int)
       */
      public void unlock(int recNo) throws RecordNotFoundException
      {
          db.unlock(recNo);
      }

      /**
       * @see DatabaseClient#update(int,String[])
       */
      public void update(int recNo, String[] data) throws RecordNotFoundException
      {
          db.update(recNo, data);
      }
  }
