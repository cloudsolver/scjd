  package suncertify.db;

  /**
   * This is thrown whenever a duplicate key is detected.  This is primarily used when a new record is
   * created.  If a new record is attempted to be written over one that already exists this should be
   * thrown.
   *
   * @author Rohit Sood
   * @version 1.2
   */
  public class DuplicateKeyException extends Exception
  {
      /**
       * Creates a new <tt>DuplicateKeyException</tt> object.
       */
      public DuplicateKeyException()
      {
          super();
      }

      /**
       * Creates a new <tt>DuplicateKeyException</tt> object with a message.
       *
       * @param msg The message of the exception.
       */
      public DuplicateKeyException(String msg)
      {
          super(msg);
      }

      /**
       * Creates a new <tt>DuplicateKeyException</tt> object.
       *
       * @param t The <tt>Throwable</tt> as a nested.
       */
      public DuplicateKeyException(Throwable t)
      {
          super(t);
      }

      /**
       * Creates a new <tt>DuplicateKeyException</tt> object.
       *
       * @param msg The message of the exception.
       * @param t The <tt>Throwable instance</tt> as a nested.
       */
      public DuplicateKeyException(String msg, Throwable t)
      {
          super(msg, t);
      }
  }
