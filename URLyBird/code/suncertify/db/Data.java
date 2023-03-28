  package suncertify.db;

  import com.rohitsood.urlybird.server.ConfigurationProperties;
  import com.rohitsood.urlybird.util.RandomAccessFileUtility;
  import com.rohitsood.urlybird.util.StringUtil;

  import java.io.FileNotFoundException;
  import java.io.IOException;
  import java.io.RandomAccessFile;

  import java.rmi.dgc.VMID;

  import java.util.ArrayList;
  import java.util.HashMap;
  import java.util.Iterator;
  import java.util.List;
  import java.util.Map;


  /**
   * This is the actual data access class. All database activity implementation lives here. It is an
   * implementation of the <tt>DBMain</tt> interface and implements all methods defined there. This class
   * can be accessed only with a <tt>VMID</tt> which is used for lock tracking and maintenence. This uses
   * the <tt>RandomAccessFile</tt> to seek, create, read, update and delete data.
   *
   * @author Rohit Sood
   * @version 1.6
   */
  public class Data implements DBMain, DataRow
  {
      /** Map of locked records. */
      private static final Map LOCKED_RECORDS_MAP = new HashMap();

      /** Flag to indicate whether debug messages should be printed to the console. */
      private static boolean debug = true;

      /** The configuration properties as specified in the suncertify.properties file. */
      private ConfigurationProperties properties;

      /** The unique id of the client invoking calls to the database. */
      private VMID vmid;

      /**
       * Creates a new Data object. This can be constructed only with a <tt>VMID</tt>. A new instance is
       * created for a unique client. This is used for locked records.
       *
       * @param vmid The unique client id.
       */
      public Data(VMID vmid)
      {
          this.vmid = vmid;
      }

      /**
       * Clears all locks for the client with the current <tt>VMID</tt> This is called when the client no
       * longer needs database access.
       */
      public void clearLocks()
      {
          final Iterator keyIterator = LOCKED_RECORDS_MAP.keySet().iterator();

          while (keyIterator.hasNext())
          {
              final Integer recordNumber = (Integer) keyIterator.next();
              final VMID  id = (VMID) LOCKED_RECORDS_MAP.get(recordNumber);

              //if the vmids match - delete the lock
              if (id.equals(vmid))
              {
                  LOCKED_RECORDS_MAP.remove(recordNumber);
              }
          }
      }

      /**
       * Retrieves the database file in a given mode. All modes of the <tt>RandomAccessFile</tt> are
       * supported.
       *
       * @param mode The mode in which the files
       *
       * @return The <tt>RandomAccessFile</tt> instance.
       *
       * @throws IOException If there was a problem obtaining the file.
       */
      private RandomAccessFile getFile(String mode)
          throws IOException
      {
          properties = new ConfigurationProperties();

          RandomAccessFile file = null;

          try
          {
              file = new RandomAccessFile(properties.getDbLocation(), mode);
          }
          catch (Exception e)
          {
              throw new IOException("Database file could not be located");
          }

          return file;
      }

      /**
       * Retrives the first deleted record. Searches for the lowest record id available. If a record has
       * been logically deleted it returns its record id, and consequent data is overwritten. If a record
       * is locked it will not make it available even though a deletion is detected for it.
       *
       * @return The next available record id.
       *
       * @throws IOException If there is problem reading the database file.
       * @throws RecordNotFoundException If no reusable record ids were found.
       */
      private int findFirstDeletedRecord() throws IOException, RecordNotFoundException
      {
          int record = -1;

          for (int a = 1;a < getTotalRecords();a++)
          {
              //don't check for locked records
              if (!isLocked(a) && isDeleted(a))
              {
                  record = a;
                  lock(record);

                  break;
              }
          }

          if (record < 0)
          {
              throw new RecordNotFoundException("No deleted records available for reuse");
          }

          return record;
      }

      /**
       * Selects the next available record id which is currently deleted. If no logically deleted records
       * are available for reuse then the max record id is incremented by one. The newly created record
       * id locked.
       *
       * @return The new record id.
       *
       * @throws IOException If there is a problem reading from the local database file.
       */
      private synchronized int newRecordID() throws IOException
      {
          int newRecordID = -1;

          //search for a deleted record to overwrite
          try
          {
              newRecordID = findFirstDeletedRecord();

              //boolean lock=isLocked(newRecordID);
          }
          catch (IOException e)
          {
              throw e;
          }
          catch (RecordNotFoundException e)
          {
              debug("No logically deleted records available for reuse.");
          }

          //no deleted record was found so prepare for new record
          if (newRecordID < 0)
          {
              newRecordID = getTotalRecords() + 1;

              //lock this record - so it can be created
              try
              {
                  lock(newRecordID);
              }
              catch (RecordNotFoundException e1)
              {
                  // TODO Auto-generated catch block
                  e1.printStackTrace();
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
      private int getTotalRecords() throws IOException
      {
          final RandomAccessFile file      = getFile("r");
          final long           totalLength = file.length();
          file.close();
          debug("file length=" + totalLength);

          if (totalLength >= Integer.MAX_VALUE)
          {
              throw new IOException("Max Database size of " + Integer.MAX_VALUE + " reached.");
          }

          final int len        = (int) totalLength;
          final int totalRecords = (len - DataRow.DATA_SECTION_POINTER) / DataRow.DATA_SECTION_INTERVAL;
          debug("Total Records=" + totalRecords);

          return totalRecords;
      }

      /* (non-Javadoc)
       * @see suncertify.db.DBMain#read(int)
       */
      public String[] read(int recNo) throws RecordNotFoundException
      {
          return read(recNo, false); //readDeletedRecord=false
      }

      /**
       * Reads records from the underlying database file system.  A provision is made to read even deleted
       * records. This can be leveraged for admin functions for an undelete functionality.
       *
       * @param recNo The record number to read.
       * @param readDeletedRecord Flag indicating if deleted records should be read.
       *
       * @return The array of strings that represent that data.
       *
       * @throws RecordNotFoundException If the record was not found.
       */
      private String[] read(int recNo, boolean readDeletedRecord)
          throws RecordNotFoundException
      {
          RandomAccessFile file = null;
          final String[] record = new String[NUMBER_OF_COLUMNS];

          try
          {
              file = getFile("r");
              file.seek(seekPosition(recNo));

              final boolean deleted = file.readBoolean();

              if (deleted && !readDeletedRecord) //&& !ignoreDeleted)
              {
                  throw new RecordNotFoundException("Record with id " + recNo +
                      " is deleted, and cannot be accessed");
              }

              record[DataRow.HOTEL_ARRAY_POSITION]   = RandomAccessFileUtility.readString(file,
                      DataRow.HOTEL_NAME_LENGTH);
              record[DataRow.CITY_ARRAY_POSITION]   = RandomAccessFileUtility.readString(file,
                      DataRow.CITY_NAME_LENGTH);
              record[DataRow.MAX_OCCUPANCY_ARRAY_POSITION]   = RandomAccessFileUtility.readString(file,
                      DataRow.MAX_OCCUPANCY_LENGTH);
              record[DataRow.SMOKING_ARRAY_POSITION]   = RandomAccessFileUtility.readString(file,
                      DataRow.SMOKING_FLAG_LENGTH);
              record[DataRow.PRICE_ARRAY_POSITION]   = RandomAccessFileUtility.readString(file,
                      DataRow.PRICE_LENGTH);
              record[DataRow.DATE_ARRAY_POSITION]   = RandomAccessFileUtility.readString(file,
                      DataRow.DATE_AVAILABLE_LENGTH);
              record[DataRow.CUSTOMER_ARRAY_POSITION] = RandomAccessFileUtility.readString(file,
                      DataRow.CUSTOMER_RECORD_LENGTH);
          }
          catch (IOException e)
          {
              throw new RecordNotFoundException("Record with id " + recNo + " was not found ");
          }
          finally
          {
              try
              {
                  file.close();
              }
              catch (IOException e1)
              {
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
      private long seekPosition(int recNo)
      {
          return DataRow.DATA_SECTION_POINTER + ((recNo - 1) * DataRow.DATA_SECTION_INTERVAL);
      }

      /**
       * Checks to see if the record id is deleted. Locks the record before you call this method so that
       * the value returned is not dirty.
       *
       * @param recNo The record id to check for deleted flag.
       *
       * @return <tt>true</tt> if deleted, <tt>false</tt> otherwise.
       *
       * @throws RecordNotFoundException If the record is not  found.
       */
      private boolean isDeleted(int recNo) throws RecordNotFoundException
      {
          boolean        deleted = true;
          RandomAccessFile file = null;

          try
          {
              file = getFile("r");
              file.seek(seekPosition(recNo));
              deleted = file.readBoolean();
          }
          catch (FileNotFoundException e)
          {
              throw new RecordNotFoundException(
                  "Record could not be found because File could not found " + e.getMessage());
          }
          catch (IOException e)
          {
              throw new RecordNotFoundException("Record could not be found because of an I/O problem" +
                  e.getMessage());
          }
          finally
          {
              unlock(recNo);

              try
              {
                  file.close();
              }
              catch (IOException e)
              {
                  throw new RecordNotFoundException("An unexpected I/O problem occurred " +
                      e.getMessage());
              }
          }

          return deleted;
      }

      /**
       * DOCUMENT ME!
       *
       * @param recNo DOCUMENT ME!
       * @param data DOCUMENT ME!
       *
       * @throws RecordNotFoundException DOCUMENT ME!
       */
      public void update(int recNo, String[] data)
          throws RecordNotFoundException
      {
          lock(recNo); //a record can be locked only if its not deleted

          RandomAccessFile file = null;

          try
          {
              file = getFile("rw");
              writeRecord(file, recNo, data);
          }
          catch (IOException e)
          {
              // TODO Auto-generated catch block
              e.printStackTrace();
          }
          finally
          {
              try
              {
                  file.close();
              }
              catch (IOException e1)
              {
                  // TODO Auto-generated catch block
                  e1.printStackTrace();
              }

              unlock(recNo);
          }
      }

      /* (non-Javadoc)
       * @see suncertify.db.DBMain#delete(int)
       */
      public void delete(int recNo) throws RecordNotFoundException
      {
          RandomAccessFile file = null;

          try
          {
              lock(recNo);
              file = getFile("rw");
              file.seek(seekPosition(recNo));
              file.writeBoolean(true);
          }
          catch (FileNotFoundException e)
          {
              e.printStackTrace();
          }
          catch (IOException e)
          {
              e.printStackTrace();
          }
          finally
          {
              try
              {
                  unlock(recNo);
                  file.close();
              }
              catch (IOException e1)
              {
                  // TODO Auto-generated catch block
                  e1.printStackTrace();
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
      public void undelete(int recNo) throws RecordNotFoundException
      {
          RandomAccessFile file = null;

          try
          {
              file = getFile("rw");
              file.seek(seekPosition(recNo));
              file.writeBoolean(false);
          }
          catch (FileNotFoundException e)
          {
              e.printStackTrace();
          }
          catch (IOException e)
          {
              e.printStackTrace();
          }
          finally
          {
              try
              {
                  file.close();
              }
              catch (IOException e1)
              {
                  // TODO Auto-generated catch block
                  e1.printStackTrace();
              }
          }
      }

      /**
       * If criteria[n] is null it is considered to be a match Also, if criteria[n] is an empty string it
       * is considered to be a match Record element 'n' must atleast begin with criteria[n] to be a match
       * Searches are case-sensitive.
       *
       * @param criteria The criteria to search against.
       *
       * @throws RecordNotFoundException If no records are returned for the criteria.
       */
      public int[] find(String[] criteria) throws RecordNotFoundException
      {
          final List records = new ArrayList();
          int      count   = 0;
          int[]    results = null;

          try
          {
              for (int recNo = 1;recNo <= getTotalRecords();recNo++)
              {
                  if (isDeleted(recNo))
                  {
                      continue;
                  }

                  final String[] record = read(recNo);

                  if (isMatch(criteria, record))
                  {
                      debug("...Matched! " + recNo); //TODO remove
                      records.add(new Integer(recNo)); //add an int
                      count++;
                  }
              }
          }
          catch (IOException e)
          {
              throw new RecordNotFoundException("Record not found due to " + e.getMessage());
          }

          debug("Total Matches: " + count);

          if (count == 0)
          {
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
      private int[] parseList(List list)
      {
          final int[] ids = new int[list.size()];

          //go thru the list and grab an int
          for (int a = 0;a < list.size();a++)
          {
              ids[a] = ((Integer) list.get(a)).intValue();
          }

          return ids;
      }

      /**
       * Matches a record based on criteria.  A match is all records if all the strings are marked as null
       * or empty. If a criteria field is not null then a match is determined. If none of the criteria
       * are given then match everything If atleast one of the criteria is given then filter based on
       * that Each criteria is a logical AND rather than an OR This is because an OR adds no value to the
       * search
       *
       * @param criteria The criteria to match.
       * @param record The record to match against.
       *
       * @return <tt>true</tt> if it is a match, <tt>false</tt> otherwise.
       */
      private boolean isMatch(String[] criteria, String[] record)
      {
          boolean match = false;

          //if the entire criteria is null or empty match this record
          if (isEmptyOrNullCriteria(criteria))
          {
              match = true;

              return match;
          }

          for (int n = 0;n < (NUMBER_OF_COLUMNS);n++)
          {
              if ((null == criteria[n]) || criteria[n].trim().equals(""))
              {
                  match = true;
              }

              if ((null != record[n]) && record[n].startsWith(criteria[n]))
              {
                  match = true;
              }
              else
              {
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
       * @return <tt>true</tt> if the criteria is empty, null or has only empty or null items,
       *         <tt>false</tt> otherwise.
       */
      private boolean isEmptyOrNullCriteria(String[] c)
      {
          boolean nullOrEmpty = true;

          if (null == c)
          {
              return nullOrEmpty;
          }

          //if every element is either null or empty then return true
          for (int a = 0;a < c.length;a++)
          {
              if ((null != c[a]) && !"".equals(c[a])) //if the element is not null and not blank return false
              {
                  nullOrEmpty = false;

                  break;
              }
          }

          return nullOrEmpty;
      }

      /**
       * Create will first search any available recordID which is deleted If a deleted record is found -
       * the new record will be written there If no deleted record is found  in the database - then the
       * new record will be written at the end of the file
       *
       * @param record The record to be created.
       *
       * @return int The record id of the newly created record.
       *
       * @throws DuplicateKeyException If the same id already exists.
       */
      public int create(String[] record) throws DuplicateKeyException
      {
          int            recNo = 0;
          RandomAccessFile file = null;

          try
          {
              file    = getFile("rw");
              recNo   = newRecordID(); //this record number comes in as a locked record
              file.seek(seekPosition(recNo));

              writeRecord(file, recNo, record);
          }
          catch (Exception e)
          {
              e.printStackTrace();
          }
          finally
          {
              try
              {
                  //close something
                  file.close();
                  unlock(recNo);
              }
              catch (IOException ioe)
              {
                  ioe.printStackTrace();
              }
              catch (RecordNotFoundException rnfe)
              {
                  rnfe.printStackTrace();
              }
          }

          debug("Returning " + recNo);

          return recNo;
      }

      /**
       * Seeks the position in the given file such that the file pointer moves to the data column of the
       * record id.
       *
       * @param file The file within which to move the pointer.
       * @param recNo The record id to which the pointer should be moved.
       * @param dataColumn The column before which the pointer should rest.
       *
       * @return The seeked position in the file.
       *
       * @throws IOException If there is a problem accessing the file.
       */
      private long seek(RandomAccessFile file, int recNo, int dataColumn)
          throws IOException
      {
          final long seekPosition = seekPosition(recNo) + dataColumn;
          file.seek(seekPosition);

          return seekPosition;
      }

      /**
       * The data record is scrubbed before being saved. The scrubbing includes fixing the lengths of data
       * to match the schema.
       *
       * @param record The record to scrub.
       *
       * @return The scrubbed data record.
       */
      private String[] scrubRecord(String[] record)
      {
          final String[] scrubbedRecord = new String[record.length];

          for (int a = 0;a < record.length;a++)
          {
              scrubbedRecord[a] = StringUtil.fixLength(record[a], META_DATA[a]);
          }

          return scrubbedRecord;
      }

      /**
       * Writes the record to the underlying file-system.
       *
       * @param file The file to write the data record to.
       * @param recNo The record number of the data record.
       * @param record The record to write.
       *
       * @throws IOException If there is a problem with the file system access.
       */
      private void writeRecord(RandomAccessFile file, int recNo, String[] record)
          throws IOException
      {
          record = scrubRecord(record);

          long seekPosition = seekPosition(recNo);
          file.seek(seekPosition);
          debug("File pos is " + file.getFilePointer());
          file.writeBoolean(false); //not deleted - because its new/or updated

          seekPosition = seek(file, recNo, DataRow.HOTEL_NAME_POSITION);
          debug("Seeked " + seekPosition);
          file.writeBytes(record[DataRow.HOTEL_ARRAY_POSITION]); //write hotel name
          debug("writen " + record[DataRow.HOTEL_ARRAY_POSITION]);

          seekPosition = seek(file, recNo, DataRow.CITY_NAME_POSITION);
          debug("Seeked " + seekPosition);
          file.writeBytes(record[DataRow.CITY_ARRAY_POSITION]);
          debug("writen " + record[DataRow.CITY_ARRAY_POSITION]);

          seekPosition = seek(file, recNo, DataRow.MAX_OCCUPANCY_POSITION);
          debug("Seeked " + seekPosition);
          file.writeBytes(record[DataRow.MAX_OCCUPANCY_ARRAY_POSITION]);
          debug("writen " + record[DataRow.MAX_OCCUPANCY_ARRAY_POSITION]);

          seekPosition = seek(file, recNo, DataRow.SMOKING_FLAG_POSITION);
          debug("Seeked " + seekPosition);
          file.writeBytes(record[DataRow.SMOKING_ARRAY_POSITION]);
          debug("writen " + record[DataRow.SMOKING_ARRAY_POSITION]);

          seekPosition = seek(file, recNo, DataRow.PRICE_POSITION);
          debug("Seeked " + seekPosition);
          file.writeBytes(record[DataRow.PRICE_ARRAY_POSITION]);
          debug("writen " + record[DataRow.PRICE_ARRAY_POSITION]);

          seekPosition = seek(file, recNo, DataRow.DATE_AVAILABLE_POSITION);
          debug("Seeked " + seekPosition);
          file.writeBytes(record[DataRow.DATE_ARRAY_POSITION]);
          debug("writen " + record[DataRow.DATE_ARRAY_POSITION]);

          seekPosition = seek(file, recNo, DataRow.CUSTOMER_RECORD_POSITION);
          debug("Seeked " + seekPosition);
          file.writeBytes(record[DataRow.CUSTOMER_ARRAY_POSITION]);
          debug("writen " + record[DataRow.CUSTOMER_ARRAY_POSITION]);
      }

      /* (non-Javadoc)
       * @see suncertify.db.DBMain#lock(int)
       */
      public void lock(int recNo) throws RecordNotFoundException
      {
          debug("Enter lock(" + recNo + ")");

          if (isDeleted(recNo))
          {
              throw new RecordNotFoundException("Record " + recNo +
                  " cannot be locked since its already deleted");
          }

          //If the record in question is locked by another thread
          //the current thread should detect it and wait for it to be released
          //no CPU is used
          if (isLocked(recNo)) //has this record been locked by another client
          {
              try
              {
                  debug(vmid + " waiting on " + LOCKED_RECORDS_MAP.get(new Integer(recNo)));

                  final Integer key = RecordKeyManager.getKey(recNo);

                  synchronized (key)
                  {
                      key.wait();
                      debug(vmid + " done waiting ");
                      LOCKED_RECORDS_MAP.put(key, vmid);
                  }
              }
              catch (InterruptedException e)
              {
                  e.printStackTrace();
              }
          }
          else
          {
              //Integer key=new Integer(recNo);//create a new key since this record is not locked.
              LOCKED_RECORDS_MAP.put(RecordKeyManager.getKey(recNo), vmid);
          }

          debug("Exit lock(" + recNo + ")\n");
      }

      /* (non-Javadoc)
       * @see suncertify.db.DBMain#unlock(int)
       */
      public void unlock(int recNo) throws RecordNotFoundException
      {
          debug("Enter unlock(" + recNo + ")");

          final Integer key = RecordKeyManager.getKey(recNo);

          synchronized (key)
          {
              LOCKED_RECORDS_MAP.remove(key);
              key.notify();
          }

          debug("Exit	 unlock(" + recNo + ")");
      }

      /* (non-Javadoc)
       * @see suncertify.db.DBMain#isLocked(int)
       */
      public boolean isLocked(int recNo) throws RecordNotFoundException
      {
          boolean lock = false;

          synchronized (LOCKED_RECORDS_MAP)
          {
              //check to see if a record is locked
              //if the record is locked by the same client then
              //the record is not considered to be locked
              if (LOCKED_RECORDS_MAP.containsKey(RecordKeyManager.getKey(recNo))) //key found in locked records
              {
                  if (LOCKED_RECORDS_MAP.containsValue(vmid))
                  { //did this client lock the record ?
                      debug("rec " + recNo + " is locked by this client");
                      lock = false;
                  }
                  else
                  { //did some other client lock this record
                      debug("rec " + recNo + " is locked by " +
                          LOCKED_RECORDS_MAP.get(new Integer(recNo)));
                      lock = true;
                  }
              }
          }

          debug("Record " + recNo + " is locked? " + lock);

          return lock;
      }

      /**
       * Prints debug messages to the console. If the debug mode is <tt>false</tt> no messages are
       * printed.
       *
       * @param msg The message to print.
       */
      private void debug(String msg)
      {
          if (!debug)
          {
              return;
          }

          System.out.println(msg);
      }
  }
