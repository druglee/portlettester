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

import com.portletguru.portlettester.mocks.ActionRequestGenerator;
import com.portletguru.portlettester.mocks.ActionResponseGenerator;
import com.portletguru.portlettester.mocks.EventRequestGenerator;
import com.portletguru.portlettester.mocks.EventResponseGenerator;
import com.portletguru.portlettester.mocks.FilterConfigGenerator;
import com.portletguru.portlettester.mocks.RenderRequestGenerator;
import com.portletguru.portlettester.mocks.RenderResponseGenerator;
import com.portletguru.portlettester.mocks.ResourceRequestGenerator;
import com.portletguru.portlettester.mocks.ResourceResponseGenerator;

/**
 * @author Derek Linde Li
 *
 */
public interface TestContext {
	
	public void initPortlet( Portlet portlet, PortletConfig portletConfig) throws PortletException;
	public void initPortlet( Portlet portlet ) throws PortletException;
	public void initFilter(PortletFilter filter, FilterConfig filterConfig) throws PortletException;
	
	public FilterChain getFilterChain();
	public TestResultHolder getTestResult();
	
	/* generators */
	public PortletConfigGenerator getPortletConfigGenerator();
	public FilterConfigGenerator getFilterConfigGenerator();
	
	public ActionRequestGenerator getActionRequestGenerator();
	public ActionResponseGenerator getActionResponseGenerator();
	public ResourceRequestGenerator getResourceRequestGenerator();
	public ResourceResponseGenerator getResourceResponseGenerator();
	public RenderRequestGenerator getRenderRequestGenerator();
	public RenderResponseGenerator getRenderResponseGenerator();
	public EventRequestGenerator getEventRequestGenerator();
	public EventResponseGenerator getEventResponseGenerator();
	
	/* preferences */
	public void addDefaultPreferences(String name, String[] values, boolean isReadOnly);
	public void setPreferencesValidator(PreferencesValidator validator);
	
	public void reset();
}
