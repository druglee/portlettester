package com.portletguru.portletester;

import javax.portlet.PortletConfig;
import javax.portlet.PortletException;

import org.junit.After;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.portletguru.portlettester.PortletConfigGenerator;
import com.portletguru.portlettester.PortletTester;

/**
 * Testing different scenarios to use PortletTester
 * 
 * @author Derek Linde Li
 *
 */
public class PortletTesterTest {
	
	private static PortletTester tester;
	private static MockPortlet portlet;
	
	@BeforeClass
	public static void setupClass() {
		portlet = new MockPortlet();
		tester = new PortletTester();
	}
	
	@Test
	public void testPortletInitializationWithPortletConfig() {
		PortletConfigGenerator generator = tester.getPortletConfigGenerator();
		generator.setPortletName("testPortlet");
		generator.setBundleBaseName("com.test");
		generator.setDefaultNamespace("testNameSpace");
		generator.addProcessingEvent("testProcessingEvent");
		generator.addPublicRenderParameter("p1", "v1");
		generator.addProcessingEvent("testPublishingEvent");
		generator.addSupportedLocale("en");
		generator.addContainerRuntimeOption("ro1", new String[]{"v1", "v2"});
		
		PortletConfig portletConfig = generator.generatePortletConfig();
		PortletException e = null;
		try {
			tester.initPortlet(portlet, portletConfig);
		} catch (PortletException ex) {
			e = ex;
		}
		
		Assert.assertNull(e);
		Assert.assertEquals("PortletConfig was changed during init.", portletConfig, portlet.getPortletConfig());
	}
	
	@Test
	public void testPortletInitializationWithoutPortletConfig() {
		PortletException e = null;
		try {
			tester.initPortlet(portlet);
		} catch (PortletException ex) {
			e = ex;
		}
		
		Assert.assertNull(e);
		
		PortletConfig portletConfig = portlet.getPortletConfig();
		Assert.assertNotNull(portletConfig);
		Assert.assertNotNull(portletConfig.getInitParameterNames());
		Assert.assertNotNull(portletConfig.getContainerRuntimeOptions());
		Assert.assertNotNull(portletConfig.getProcessingEventQNames());
		Assert.assertNotNull(portletConfig.getPublicRenderParameterNames());
		Assert.assertNotNull(portletConfig.getPublishingEventQNames());
		Assert.assertNotNull(portletConfig.getPortletContext());
		Assert.assertNotNull(portletConfig.getSupportedLocales());
	}
	
	@After
	public void tearDown(){
		tester.resetTestContext();
	}
}
