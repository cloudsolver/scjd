  package suncertify.db;

  /**
   * A special <tt>RuntimeException</tt> that signifies an unexpected exception on the database server.
   * This should be thrown by the client whenever unexpected exceptions occur in the remote server.
   *
   * @author Rohit Sood
   * @version 1.1
   */
  public class DatabaseServerException extends RuntimeException
  {
      /** Error message constant. */
      public static final String ERROR_MESSAGE = "There was a problem with the remote server.\n";

      /**
       * Creates a new DatabaseServerException object.
       */
      public DatabaseServerException()
      {
          this(ERROR_MESSAGE);
      }

      /**
       * Creates a new DatabaseServerException object with a message.
       *
       * @param message The message of the exception.
       */
      public DatabaseServerException(String message)
      {
          super(message);
      }
  }
