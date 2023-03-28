  package com.rohitsood.urlybird.test;

  import suncertify.db.Data;
  import suncertify.db.RecordNotFoundException;


  /**
   * @author Rohit
   */
  public class DataLocker implements Runnable
  {
      /**DOCUMENT ME! */
      Data d;

      /**DOCUMENT ME! */
      int recNo;

      /**
       * Creates a new DataLocker object.
       *
       * @param d DOCUMENT ME!
       * @param recNo DOCUMENT ME!
       */
      DataLocker(Data d, int recNo)
      {
          this.d       = d;
          this.recNo   = recNo;
      }

      /**
       * DOCUMENT ME!
       */
      public void run()
      {
          try
          {
              d.lock(recNo);
          }
          catch (RecordNotFoundException e)
          {

              e.printStackTrace();
          }
      }
  }
