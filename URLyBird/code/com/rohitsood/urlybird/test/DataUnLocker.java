  package com.rohitsood.urlybird.test;

  import suncertify.db.Data;
  import suncertify.db.RecordNotFoundException;


  /**
   * @author Rohit
   */
  public class DataUnLocker implements Runnable
  {
      /**DOCUMENT ME! */
      Data d;

      /**DOCUMENT ME! */
      int recNo;

      /**
       * Creates a new DataUnLocker object.
       *
       * @param d DOCUMENT ME!
       * @param recNo DOCUMENT ME!
       */
      DataUnLocker(Data d, int recNo)
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
              d.unlock(recNo);
          }
          catch (RecordNotFoundException e)
          {
              // TODO Auto-generated catch block
              e.printStackTrace();
          }
      }
  }
