/**
 * 
 */
package com.portletguru.portlettester;

import javax.portlet.Portlet;
import javax.portlet.PortletConfig;
import javax.portlet.PortletException;
import javax.portlet.PreferencesValidator;
import javax.portlet.filter.FilterChain;
import javax.portlet.filter.FilterConfig;
import javax.portlet.filter.PortletFilter;

import com.portletguru.portlettester.impl.DefaultTestContext;
import com.portletguru.portlettester.mocks.ActionRequestGenerator;
import com.portletguru.portlettester.mocks.ActionResponseGenerator;
import com.portletguru.portlettester.mocks.EventRequestGenerator;
import com.portletguru.portlettester.mocks.EventResponseGenerator;
import com.portletguru.portlettester.mocks.FilterConfigGenerator;
import com.portletguru.portlettester.mocks.PortletConfigGenerator;
import com.portletguru.portlettester.mocks.RenderRequestGenerator;
import com.portletguru.portlettester.mocks.RenderResponseGenerator;
import com.portletguru.portlettester.mocks.ResourceRequestGenerator;
import com.portletguru.portlettester.mocks.ResourceResponseGenerator;

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
	
	public void initFilter(PortletFilter filter, FilterConfig filterConfig) throws PortletException {
		testContext.initFilter(filter, filterConfig);
	}
	
	public void reset() {
		testContext.reset();
	}
	
	public TestResultHolder getTestResults() {
		return testContext.getTestResult();
	}
	
	public void addDefaultPreferences(String name, String[] values, boolean isReadOnly) {
		testContext.addDefaultPreferences(name, values, isReadOnly);
	}
	
	public void setPreferencesValidator(PreferencesValidator validator) {
		testContext.setPreferencesValidator(validator);
	}
	
	public FilterChain getFilterChain() {
		return testContext.getFilterChain();
	}
	
	/* generator getters */
	public PortletConfigGenerator getPortletConfigGenerator() {
		return testContext.getPortletConfigGenerator();
	}
	
	public RenderRequestGenerator getRenderRequestGenerator() {
		return testContext.getRenderRequestGenerator();
	}
	
	public RenderResponseGenerator getRenderResponseGenerator() {
		return testContext.getRenderResponseGenerator();
	}
	
	public ResourceRequestGenerator getResourceRequestGenerator() {
		return testContext.getResourceRequestGenerator();
	}
	
	public ResourceResponseGenerator getResourceResponseGenerator() {
		return testContext.getResourceResponseGenerator();
	}
	
	public EventRequestGenerator getEventRequestGenerator() {
		return testContext.getEventRequestGenerator();
	}
	
	public EventResponseGenerator getEventResponseGenerator() {
		return testContext.getEventResponseGenerator();
	}
	
	public ActionRequestGenerator getActionRequestGenerator() {
		return testContext.getActionRequestGenerator();
	}
	
	public ActionResponseGenerator getActionResponseGenerator() {
		return testContext.getActionResponseGenerator();
	}
	
	public FilterConfigGenerator getFilterConfigGenerator() {
		return testContext.getFilterConfigGenerator();
	}
}
