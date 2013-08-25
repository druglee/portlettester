/**
 * 
 */
package com.portletguru.portlettester;

import javax.portlet.Portlet;
import javax.portlet.PortletConfig;
import javax.portlet.PortletException;

import com.portletguru.portlettester.impl.DefaultTestContext;
import com.portletguru.portlettester.mocks.ActionRequestGenerator;
import com.portletguru.portlettester.mocks.ActionResponseGenerator;

/**
 * @author Derek Linde Li
 *
 */
public class PortletTester {
	
	private TestContext testContext;
	
	public PortletTester() {
		testContext = new DefaultTestContext();
	}
	
	public void initPortlet(Portlet portlet) throws PortletException {
		testContext.initPortlet(portlet);
	}
	
	public void initPortlet(Portlet portlet, PortletConfig portletConfig) throws PortletException {
		testContext.initPortlet(portlet, portletConfig);
	}
	
	public void reset() {
		testContext.reset();
	}
	
	public TestResultHolder getTestResults() {
		return testContext.getTestResult();
	}
	
	/* generator getters */
	public PortletConfigGenerator getPortletConfigGenerator() {
		return testContext.getPortletConfigGenerator();
	}
	
	public ActionRequestGenerator getActionRequestGenerator() {
		return testContext.getActionRequestGenerator();
	}
	
	public ActionResponseGenerator getActionResponseGenerator() {
		return testContext.getActionResponseGenerator();
	}
}
