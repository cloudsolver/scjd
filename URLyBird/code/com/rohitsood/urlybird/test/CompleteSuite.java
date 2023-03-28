/*
 * Created on Mar 21, 2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package com.rohitsood.urlybird.test;

import junit.framework.TestSuite;

/**
 * @author N0110643
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class CompleteSuite extends TestSuite


{
	public CompleteSuite(){
		addTest(new DatabaseClientFactoryTest("DatabaseClientFactoryTest"));
		addTest(new DatabaseDirectClientTest("DatabaseDirectClientTest"));
		addTest(new DatabaseNetworkClientTest("DatabaseNetworkClientTest"));
		addTest(new DataTest("DataTest"));
	}

}
