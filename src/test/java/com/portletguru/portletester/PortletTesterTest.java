package com.portletguru.portletester;

import org.junit.After;
import org.junit.BeforeClass;

import com.portletguru.portlettester.PortletTester;

/**
 * Testing different scenarios to use PortletTester
 * 
 * @author Derek Linde Li
 *
 */
public class TestPortletTester {
	
	private static PortletTester tester;
	private static MockPortlet portlet;
	
	@BeforeClass
	public static void setupClass() {
		portlet = new MockPortlet();
		tester = new PortletTester();
	}
	
	@After
	public void tearDown(){
		tester.resetTestContext();
	}
}
