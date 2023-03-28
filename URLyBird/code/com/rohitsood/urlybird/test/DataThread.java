  package com.rohitsood.urlybird.test;

  import suncertify.db.DBMain;
  import suncertify.db.Data;
  import suncertify.db.RecordNotFoundException;

  import java.rmi.dgc.VMID;


  /**
   * @author Rohit
   */
  public class DataThread implements Runnable
  {
      /**DOCUMENT ME! */
      static final int WAIT_TIME = 4000;

      /** DOCUMENT ME! */
      int rec;

      /** DOCUMENT ME! */
      VMID vmid;

      /** DOCUMENT ME! */
      DBMain db;

      /**
       * Creates a new DataThread object.
       *
       * @param vmid DOCUMENT ME!
       */
      DataThread(VMID vmid)
      {
          this.vmid   = vmid;
          db          = new Data(vmid);
      }

      /**
       * Run the test on its own thread
       */
      public void run()
      {
          doTest();

          long duration = 1L;

          if (rec == 1)
          {
              duration = WAIT_TIME;
          }

          try
          {
              Thread.sleep(duration);
          }
          catch (InterruptedException e)
          {
              // TODO Auto-generated catch block
              e.printStackTrace();
          }

          unlock();
      }

      /**
       * DOCUMENT ME!
       */
      public void doTest()
      {
          try
          {
              db.lock(rec);
          }
          catch (RecordNotFoundException e)
          {
              // TODO Auto-generated catch block
              e.printStackTrace();
          }
      }

      /**
       * DOCUMENT ME!
       */
      public void unlock()
      {
          try
          {
              db.unlock(rec);
          }
          catch (RecordNotFoundException e)
          {
              // TODO Auto-generated catch block
              e.printStackTrace();
          }
      }

      /**
       * DOCUMENT ME!
       *
       * @param args DOCUMENT ME!
       */
      public static void main(String[] args)
      {
final          DataThread dt1 = new DataThread(new VMID());
          dt1.rec = 1;

     final     DataThread dt2 = new DataThread(new VMID());
          dt2.rec = 1;

          final DataThread dt3 = new DataThread(new VMID());
          dt3.rec = 2;

          final Thread t1 = new Thread(dt1);
          final Thread t2 = new Thread(dt2);
          final Thread t3 = new Thread(dt3);
          t1.start();
          t2.start();
          t3.start();
      }
  }
