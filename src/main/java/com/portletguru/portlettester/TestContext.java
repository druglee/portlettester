/**
 * 
 */
package com.portletguru.portlettester;

import java.util.Map;

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
	public PortletConfig getPortletConfig();
	
	public void initPortlet( Portlet portlet, Map<String,String> initParameters) throws PortletException;
	
	public TestResultHolder getTestResult();
}
