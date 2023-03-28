
package com.rohitsood.urlybird.test;

import suncertify.db.RecordNotFoundException;

import com.rohitsood.urlybird.Mode;
import com.rohitsood.urlybird.client.DatabaseClient;
import com.rohitsood.urlybird.client.DatabaseClientFactory;

import junit.framework.TestCase;


public class DatabaseNetworkClientTest extends TestCase
{
	
	public void testFind(){
		
		DatabaseClientFactory f=new DatabaseClientFactory();
		DatabaseClient client=f.getDatabaseClient(Mode.ONLINE);
		String[] c={"P","","","","","","",""};
		int[] results=null;
		try
        {
            results=client.find(c);
        } catch (RecordNotFoundException e)
        {
            // TODO Auto-generated catch block
           	fail("rec not found "+e);
            e.printStackTrace();
        }
        
        for(int a=0;a<results.length;a++){
        	System.out.println(results[a]);
        	
        	try
            {
                Show.printRecord(client.read(results[a]));
            } catch (RecordNotFoundException e1)
            {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        	
        	 
        }
	
	}
}
