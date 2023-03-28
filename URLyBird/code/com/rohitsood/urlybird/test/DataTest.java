  package com.rohitsood.urlybird.test;

  import junit.framework.Test;
  import junit.framework.TestCase;
  import junit.framework.TestSuite;

  import suncertify.db.DBMain;
  import suncertify.db.Data;
  import suncertify.db.DataRow;
  import suncertify.db.DuplicateKeyException;
  import suncertify.db.RecordNotFoundException;

  import java.rmi.dgc.VMID;


  /**
   * DataTest will test stuff for us
   *
   * @author N0110643
   */
  public class DataTest extends TestCase
  {
      /** DOCUMENT ME! */
      protected int recNo;

      /**
       * Creates a new DataTest object.
       *
       * @param s DOCUMENT ME!
       */
      public DataTest(String s)
      {
          super(s);
      }

      /**
       * DOCUMENT ME!
       *
       * @return DOCUMENT ME!
       */
      public static Test suite()
      {
          return new TestSuite(DataTest.class);
      }

      //this test needs to first create, and then read it

      /**
       * If the search criteria is a null - it is a match If any of the items in the search criteria is a
       * null - it is a match
       */
      public void testFindAll()
      {
          //if(true)return;
          System.out.println("Start testFindAll");

          final DBMain data = new Data(new VMID());
          int[]      rec = null;

          //		should this find everything and each attr narrows the search
          final String[] c1 = null; //{"","","","","","",""};

          final String[] c = { "P", "", "", "", "", "", null };

          try
          {
              rec = data.find(c);
              testRead(rec);
          }
          catch (RecordNotFoundException e)
          {
              // TODO Auto-generated catch block
              e.printStackTrace();
              fail("problems");
          }
          catch (Throwable t)
          {
              t.printStackTrace();
              fail("testFind failed " + t);
          }

          System.out.println("End testFindAll");
      }

      /**
       * DOCUMENT ME!
       *
       * @param rec DOCUMENT ME!
       *
       * @throws Exception DOCUMENT ME!
       */
      public void testRead(int[] rec) throws Exception
      {
          System.out.println("Start testRead " + rec);

          final DBMain data = new Data(new VMID());

          for (int a = 0;a < rec.length;a++)
          {
              final String[] d = data.read(rec[a]);
              showData(d);
          }

          System.out.println("End testRead");
      }

      /**
       * DOCUMENT ME!
       *
       * @param d DOCUMENT ME!
       */
      private void showData(String[] d)
      {
          System.out.println("Hotel:" + d[0] + " City:" + d[1] + " Capacity:" + d[2] + " Smoking:" +
              d[DataRow.SMOKING_ARRAY_POSITION] + " Price:" + d[DataRow.PRICE_ARRAY_POSITION] +
              " Date:" + d[DataRow.DATE_ARRAY_POSITION] + " User: " +
              d[DataRow.CUSTOMER_ARRAY_POSITION]);
      }

      /**
       * DOCUMENT ME!
       */
      public void testDelete()
      {
          if (true)
          {
              return;
          }

          final DBMain data = new Data(new VMID());

          try
          {
              data.delete(2);
          }
          catch (RecordNotFoundException e)
          {
              // TODO Auto-generated catch block
              e.printStackTrace();
          }

          try
          {
              data.read(2);
          }
          catch (RecordNotFoundException e1)
          {
              // TODO Auto-generated catch block
              e1.printStackTrace();
          }
      }

      /**
       * DOCUMENT ME!
       */
      public void testCreate()
      {
          System.out.println("Start testCreate");

          int          recNo      = 0;
          final DBMain data       = new Data(new VMID());
          final String[] dataRecord = { "My  Hotel", "Wonderful City", "5", "Y", "$33.00", "12/12/2004", "N0110643" };
          String[]     result     = null;

          //read it
          try
          {
              recNo = data.create(dataRecord);
          }
          catch (DuplicateKeyException dke)
          {
              dke.printStackTrace();
              fail("Duplicate Key Exception in create");
          }
          catch (Exception e)
          {
              fail("Exception happened in create " + e);
          }

          try
          {
              System.out.println("Read " + recNo);
              result = data.read(recNo);
          }
          catch (RecordNotFoundException e1)
          {
              e1.printStackTrace();
              fail("Record was not found");
          }
          catch (Throwable t)
          {
              fail("Exception happened");
          }

          for (int a = 0;a < dataRecord.length;a++)
          {
              assertTrue(dataRecord[a].equals(result[a]));
          }

          System.out.println("End testCreate");
      }
  }
