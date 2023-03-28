package com.rohitsood.urlybird.test;

import java.rmi.dgc.VMID;

import suncertify.db.Data;
import suncertify.db.RecordNotFoundException;
import junit.framework.TestCase;

/**
 * Tests the locks.
 * @author Rohit
 */
public class DataLockTest extends TestCase {


	public void testLockUnlock(){
		System.out.println("Enter lock-unlock");
		
		Data data1 = new Data(new VMID());
		Data data2 = new Data(new VMID());
		
		try
		{
			data1.lock(1);
			data1.lock(2);
			data2.lock(2);
		} catch (RecordNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
		try
		{
			data1.unlock(1);
            
			
			
		} catch (RecordNotFoundException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		System.out.println("Exit lock-unlock");
	}

}
