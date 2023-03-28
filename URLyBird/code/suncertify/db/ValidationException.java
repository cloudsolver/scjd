  package suncertify.db;

  /**
   * This is thrown whenever there is a validation failure. Validation can occur on the client side as
   * well as on the server side. Whenever data is invalid and any CRUD operation is being requested, this
   * may be thrown.
   *
   * @author Rohit Sood
   * @version 1.1
   */
  public class ValidationException extends Exception
  {
      /**
       * Creates a new ValidationException object.
       *
       * @param msg The message for the exception.
       */
      public ValidationException(String msg)
      {
          super(msg);
      }
  }
