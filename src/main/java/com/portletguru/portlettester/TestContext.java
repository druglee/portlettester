/**
 * 
 */
package com.portletguru.portlettester;

import javax.portlet.Portlet;
import javax.portlet.PortletConfig;
import javax.portlet.PortletException;

import com.portletguru.portlettester.mocks.ActionRequestGenerator;
import com.portletguru.portlettester.mocks.ActionResponseGenerator;
import com.portletguru.portlettester.mocks.EventRequestGenerator;
import com.portletguru.portlettester.mocks.EventResponseGenerator;
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
	
	public TestResultHolder getTestResult();
	
	/* generators */
	public PortletConfigGenerator getPortletConfigGenerator();
	public ActionRequestGenerator getActionRequestGenerator();
	public ActionResponseGenerator getActionResponseGenerator();
	public ResourceRequestGenerator getResourceRequestGenerator();
	public ResourceResponseGenerator getResourceResponseGenerator();
	public RenderRequestGenerator getRenderRequestGenerator();
	public RenderResponseGenerator getRenderResponseGenerator();
	public EventRequestGenerator getEventRequestGenerator();
	public EventResponseGenerator getEventResponseGenerator();
	
	public void reset();
}
