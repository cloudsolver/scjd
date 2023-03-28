package com.rohitsood.urlybird.test;

import com.rohitsood.urlybird.util.StringUtil;

import junit.framework.TestCase;

/**
 * 
 * @author Rohit
 */
public class StringUtilTest extends TestCase {


	public void testFixLength(){
	
		assertTrue("123".equals(StringUtil.fixLength("123",3)));
		
	
		
	
	}
	
	public void testFixLengthPad(){
		assertTrue("123 ".equals(StringUtil.fixLength("123",4)));
	}
	public void testFixLengthSubstring(){
		String sub=StringUtil.fixLength("123",2);
		assertTrue("12".equals(sub));
	}
	
	
}
