/**
 * 
 */
package com.portletguru.portlettester;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.EventRequest;
import javax.portlet.EventResponse;
import javax.portlet.Portlet;
import javax.portlet.PortletConfig;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import com.portletguru.portlettester.mocks.ActionRequestGenerator;
import com.portletguru.portlettester.mocks.ActionResponseGenerator;

/**
 * @author Derek Linde Li
 *
 */
public interface TestContext {
	
	public RenderRequest getRenderRequest();
	public RenderResponse getRenderResponse();
	public EventRequest getEventRequest();
	public EventResponse getEventResponse();
	public ResourceRequest getResourceRequest();
	public ResourceResponse getResourceResponse();
	public ActionRequest getActionRequest();
	public ActionResponse getActionResponse();
	public void reset();
	
	
	public void initPortlet( Portlet portlet, PortletConfig portletConfig) throws PortletException;
	public void initPortlet( Portlet portlet ) throws PortletException;
	
	public TestResultHolder getTestResult();
	
	public PortletConfigGenerator getPortletConfigGenerator();
	public ActionRequestGenerator getActionRequestGenerator();
	public ActionResponseGenerator getActionResponseGenerator();
}
