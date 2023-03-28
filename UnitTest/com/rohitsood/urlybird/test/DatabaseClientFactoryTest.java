
package com.rohitsood.urlybird.test;

import junit.framework.TestCase;

import com.rohitsood.urlybird.Mode;
import com.rohitsood.urlybird.client.DatabaseClient;
import com.rohitsood.urlybird.client.DatabaseClientFactory;
import com.rohitsood.urlybird.client.DatabaseDirectClient;
import com.rohitsood.urlybird.client.DatabaseNetworkClient;


public class DatabaseClientFactoryTest extends TestCase
{

	public void testGetDatabaseClient(){
	
		DatabaseClientFactory factory=new DatabaseClientFactory();
		DatabaseClient client=factory.getDatabaseClient(Mode.ONLINE);
		assertTrue(client instanceof DatabaseNetworkClient);
		
		client=factory.getDatabaseClient(Mode.OFFLINE);
		assertTrue(client instanceof DatabaseDirectClient);
		
		try{
		client=factory.getDatabaseClient(-99);
		fail("No exception was thrown when expected");
		}catch(IllegalArgumentException e){
			assertTrue("Argument exception was thrown ",e!=null);
		}
		
	}

}
