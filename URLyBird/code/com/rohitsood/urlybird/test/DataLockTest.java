  package com.rohitsood.urlybird.test;

  import junit.framework.TestCase;

  import suncertify.db.Data;

  import java.rmi.dgc.VMID;


  /**
   * Tests the locks.
   *
   * @author Rohit
   */
  public class DataLockTest extends TestCase
  {
      /**
       * Creates a new DataLockTest object.
       *
       * @param s DOCUMENT ME!
       */
      public DataLockTest(String s)
      {
          super(s);
      }

      /**
       * DOCUMENT ME!
       *
       * @throws Exception DOCUMENT ME!
       * @throws AssertionError DOCUMENT ME!
       */
      public void testLockUnlock() throws Exception
      {
          System.out.println("Enter lock-unlock");

          final Data       data1 = new Data(new VMID());
          final Data       data2 = new Data(new VMID());

          final DataLocker locker1   = new DataLocker(data1, 1); //c1 locks 1
          final DataLocker locker2   = new DataLocker(data1, 2); //c1 locks 2
          final DataUnLocker unlocker1 = new DataUnLocker(data1, 2); //c1 unlocks 1

          final DataLocker locker1b = new DataLocker(data2, 2); //c2 locks 2

          final Thread     t1 = new Thread(locker1);
          final Thread     t2 = new Thread(locker2);
          final Thread     t3 = new Thread(locker1b);
          final Thread     t4 = new Thread(unlocker1);

          final long       time = 1000L;
          t1.start(); //c1 locks 1
          Thread.sleep(time);
          t2.start(); //c1 locks 2
          Thread.sleep(time);
          t3.start(); //c2 locks 2..will wait
          Thread.sleep(time);
          t4.start(); //c1 releases 1

          //if c2 can lock2 then we should fail
          if (data1.isLocked(2))
          {
              throw new AssertionError("c can never lock");
          }

          Thread.sleep(time);

          System.out.println("Exit lock-unlock");
      }
  }
