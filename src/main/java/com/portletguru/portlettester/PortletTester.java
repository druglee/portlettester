/**
 * 
 */
package com.portletguru.portlettester;

import javax.portlet.EventRequest;
import javax.portlet.EventResponse;
import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import com.portletguru.portlettester.impl.DefaultTestContext;

/**
 * @author Derek Linde Li
 *
 */
public class PortletTester {
	
	private TestContext testContext;
	
	public PortletTester(Portlet portlet) {
		testContext = new DefaultTestContext();
		try {
			portlet.init(testContext.getPortletConfig());
		} catch (PortletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * @return the renderRequest
	 */
	public RenderRequest getRenderRequest() {
		return testContext.getRenderRequest();
	}

	/**
	 * @return the renderResponse
	 */
	public RenderResponse getRenderResponse() {
		return testContext.getRenderResponse();
	}

	/**
	 * @return the eventRequest
	 */
	public EventRequest getEventRequest() {
		return testContext.getEventRequest();
	}

	/**
	 * @return the eventResponse
	 */
	public EventResponse getEventResponse() {
		return testContext.getEventResponse();
	}

	/**
	 * @return the resourceRequest
	 */
	public ResourceRequest getResourceRequest() {
		return testContext.getResourceRequest();
	}

	/**
	 * @return the resourceResponse
	 */
	public ResourceResponse getResourceResponse() {
		return testContext.getResourceResponse();
	}
	
	/**
	 * Reset the test context
	 */
	public void resetTestContext(){
		this.testContext.reset();
	}
	
	public String getRequestDispatcherName() {
		return testContext.getTestResult().getRequestDispatcherName();
	}

	public String getRequestDispatcherPath() {
		return testContext.getTestResult().getRequestDispatcherPath();
	}
	
	public String getRedirectLocation(){
		return testContext.getTestResult().getRedirectLocation();
	}
	
	/**
	 * The content passed into write/print/println methods
	 * @return a String that contains the content the user has written
	 */
	public String getOutputContent(){
		return testContext.getTestResult().getOutputContent();
	}
	
}
