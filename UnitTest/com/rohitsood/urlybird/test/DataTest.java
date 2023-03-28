package com.rohitsood.urlybird.test;
import java.rmi.dgc.VMID;

import suncertify.db.DBMain;
import suncertify.db.Data;
import suncertify.db.DuplicateKeyException;
import suncertify.db.RecordNotFoundException;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
/**
 * DataTest will test stuff for us
 * @author N0110643
 */
public class DataTest extends TestCase
{
    protected int recNo = 0;
    public static Test suite()
    {
        return new TestSuite(DataTest.class);
    }
    //this test needs to first create, and then read it
    /**
     * If the search criteria is a null - it is a match
     * If any of the items in the search criteria is a null - it is a match
     *
     */

    public void testFindAll()
    {
        //if(true)return;
        System.out.println("Start testFindAll");
        DBMain data = new Data(new VMID());
        int[] rec=null;
        String c1[] = null; //{"","","","","","",""};//should this find everything and each attr narrows the search
        String c[] = { "P", "", "", "", "", "", null };
        try
        {
            rec = data.find(c);
			testRead(rec);
        } catch (RecordNotFoundException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
            fail("problems");
        } catch (Throwable t)
        {
            t.printStackTrace();
            fail("testFind failed " + t);
        }
        System.out.println("End testFindAll");
        
    }

    
   
    
    
	public void testRead(int[] rec) throws Exception{
		System.out.println("Start testRead "+rec);
		DBMain data = new Data(new VMID());
		
		for(int a=0;a<rec.length;a++){
			String d[]=data.read(rec[a]);	
			showData(d);
		}	
		
		System.out.println("End testRead");
		
	}

    private void showData(String[] d){
    	System.out.println("Hotel:"+d[0]+" City:"+d[1]+" Capacity:"+d[2]+" Smoking:"+d[3]+" Price:"+d[4]+" Date:"+d[5]+" User: "+d[6]);
    }
    
    public void testDelete(){
    	if(true) return;
		DBMain data = new Data(new VMID());
    	try
        {
            data.delete(6);
        } catch (RecordNotFoundException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    	try
        {
            data.read(6);
        } catch (RecordNotFoundException e1)
        {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }
    
    public void testCreate()
    {
       
        System.out.println("Start testCreate");
        int recNo = 0;
		DBMain data = new Data(new VMID());
        String[] dataRecord = { "My  Lovely Hotel", "Wonderful City", "5", "Y", "$33.00", "12/12/2004", "N0110643" };
        String[] result = null;
        //read it
        try
        {
            recNo = data.create(dataRecord);
        } catch (DuplicateKeyException dke)
        {
            dke.printStackTrace();
            fail("Duplicate Key Exception in create");
        } catch (Exception e)
        {
            fail("Exception happened in create " + e);
        }
        try
        {
            System.out.println("Read " + recNo);
            result = data.read(recNo);
        } catch (RecordNotFoundException e1)
        {
            e1.printStackTrace();
            fail("Record was not found");
        } catch (Throwable t)
        {
            fail("Exception happened");
        }
        for (int a = 0; a < dataRecord.length; a++)
        {
            assertTrue(dataRecord[a].equals(result[a]));
        }
        System.out.println("End testCreate");
    }
}
