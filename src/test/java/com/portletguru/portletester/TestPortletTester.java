package com.portletguru.portletester;

import org.junit.After;
import org.junit.BeforeClass;

import com.portletguru.portlettester.PortletTester;
import com.portletguru.portlettester.mocks.MockPortlet;


public class TestPortletTester {
	
	private static PortletTester tester;
	private static MockPortlet portlet;
	
	@BeforeClass
	public static void setupClass() {
		portlet = new MockPortlet();
		tester = new PortletTester(portlet);
	}
	
	@After
	public void tearDown(){
		tester.resetTestContext();
	}
}
