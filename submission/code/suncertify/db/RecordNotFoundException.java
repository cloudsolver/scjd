  package suncertify.db;

  /**
   * This exception is thrown whenever a record is not found.  For all CRUD operations that are performed
   * this exception may be thrown.
   *
   * @author Rohit Sood
   * @version 1.1
   */
  public class RecordNotFoundException extends Exception
  {
      /** Default message. */
      public static final String FIND_FAILED = "No records could be matched for your search criteria";

      /**
       * Creates a new <tt>RecordNotFoundException</tt> object.
       */
      public RecordNotFoundException()
      {
          this(FIND_FAILED);
      }

      /**
       * Creates a new <tt>RecordNotFoundException</tt> object with a message.
       *
       * @param msg The message of the exception.
       */
      public RecordNotFoundException(String msg)
      {
          super(msg);
      }

      /**
       * Creates a new <tt>RecordNotFoundException</tt> object.
       *
       * @param t The <tt>Throwable</tt> as a nested.
       */
      public RecordNotFoundException(Throwable t)
      {
          super(t);
      }

      /**
       * Creates a new <tt>RecordNotFoundException</tt> object.
       *
       * @param msg The message of the exception.
       * @param t The <tt>Throwable instance</tt> as a nested.
       */
      public RecordNotFoundException(String msg, Throwable t)
      {
          super(msg, t);
      }
  }
