/**
 * 
 */
package com.portletguru.portlettester.impl;

import java.util.HashMap;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.EventRequest;
import javax.portlet.EventResponse;
import javax.portlet.PortalContext;
import javax.portlet.Portlet;
import javax.portlet.PortletConfig;
import javax.portlet.PortletContext;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import com.portletguru.portlettester.PortletConfigGenerator;
import com.portletguru.portlettester.PortletStatus;
import com.portletguru.portlettester.TestContext;
import com.portletguru.portlettester.TestResultHolder;
import com.portletguru.portlettester.mocks.ActionRequestGenerator;
import com.portletguru.portlettester.mocks.ActionResponseGenerator;
import com.portletguru.portlettester.mocks.MockActionRequest;
import com.portletguru.portlettester.mocks.MockActionResponse;
import com.portletguru.portlettester.mocks.MockEventRequest;
import com.portletguru.portlettester.mocks.MockEventResponse;
import com.portletguru.portlettester.mocks.MockPortalContext;
import com.portletguru.portlettester.mocks.MockPortletConfig;
import com.portletguru.portlettester.mocks.MockPortletContext;
import com.portletguru.portlettester.mocks.MockRenderRequest;
import com.portletguru.portlettester.mocks.MockRenderResponse;
import com.portletguru.portlettester.mocks.MockResourceRequest;
import com.portletguru.portlettester.mocks.MockResourceResponse;

/**
 * @author Derek Linde Li
 *
 */
public class DefaultTestContext implements TestContext {
	
	private ActionRequest actionRequest;
	private ActionResponse actionResponse;
	private RenderRequest renderRequest;
	private RenderResponse renderResponse;
	private EventRequest eventRequest;
	private EventResponse eventResponse;
	private ResourceRequest resourceRequest;
	private ResourceResponse resourceResponse;
	
	private PortalContext portalContext;
	private PortletContext portletContext;
	private PortletStatus portletStatus;
	
	private TestResultHolder testResult;
	
	private PortletConfigGenerator portletConfigGenerator;
	private ActionRequestGenerator actionRequestGenerator;
	private ActionResponseGenerator actionResponseGenerator;
	
	/**
	 * Constructor
	 */
	public DefaultTestContext(){
		this(new HashMap<String,String>());
	}
	
	public DefaultTestContext(Map<String,String> contextInitParameters){
		testResult = new TestResultHolder();
		portalContext = new MockPortalContext();
		portletStatus = new PortletStatus();
		portletContext = new MockPortletContext(contextInitParameters, testResult);
	}
	
	/* (non-Javadoc)
	 * @see com.portletmaniac.portlettester.TestContext#getActionRequest()
	 */
	
	public ActionRequest getActionRequest() {
		if( actionRequest == null ) {
			actionRequest = new MockActionRequest(portalContext, portletContext, portletStatus);
		}
		return actionRequest;
	}

	/* (non-Javadoc)
	 * @see com.portletmaniac.portlettester.TestContext#getActionResponse()
	 */
	
	public ActionResponse getActionResponse() {
		if( actionResponse == null ) {
			actionResponse = new MockActionResponse(portletStatus, getActionRequest(), testResult);
		}
		return actionResponse;
	}

	/* (non-Javadoc)
	 * @see com.portletmanic.portlettester.TestContext#getRenderRequest()
	 */
	
	public RenderRequest getRenderRequest() {
		if( renderRequest == null){
			renderRequest = new MockRenderRequest(portalContext, portletContext, portletStatus);
		}
		return renderRequest;
	}

	/* (non-Javadoc)
	 * @see com.portletmanic.portlettester.TestContext#getRenderResponse()
	 */
	
	public RenderResponse getRenderResponse() {
		if( renderResponse == null){
			renderResponse = new MockRenderResponse(getRenderRequest(), testResult);
		}
		return renderResponse;
	}

	/* (non-Javadoc)
	 * @see com.portletmanic.portlettester.TestContext#getEventRequest()
	 */
	
	public EventRequest getEventRequest() {
		if( eventRequest == null){
			eventRequest = new MockEventRequest(portalContext, portletContext, portletStatus);
		}
		return eventRequest;
	}

	/* (non-Javadoc)
	 * @see com.portletmanic.portlettester.TestContext#getEventResponse()
	 */
	
	public EventResponse getEventResponse() {
		if( eventResponse == null){
			eventResponse = new MockEventResponse(portletStatus, getEventRequest());
		}
		return eventResponse;
	}

	/* (non-Javadoc)
	 * @see com.portletmanic.portlettester.TestContext#getResourceRequest()
	 */
	
	public ResourceRequest getResourceRequest() {
		if( resourceRequest == null){
			resourceRequest = new MockResourceRequest(portalContext, portletContext, portletStatus);
		}
		return resourceRequest;
	}

	/* (non-Javadoc)
	 * @see com.portletmanic.portlettester.TestContext#getResourceResponse()
	 */
	
	public ResourceResponse getResourceResponse() {
		if( resourceResponse == null){
			resourceResponse = new MockResourceResponse(getResourceRequest(), testResult);
		}
		return resourceResponse;
	}
	
	/* (non-Javadoc)
	 * @see com.portletmanic.portlettester.TestContext#getPortletConfigGenerator()
	 */
	
	public PortletConfigGenerator getPortletConfigGenerator() {
		if(portletConfigGenerator == null) {
			portletConfigGenerator = new PortletConfigGenerator(portletContext);
		}
		return portletConfigGenerator;
	}
	
	public ActionRequestGenerator getActionRequestGenerator() {
		if(actionRequestGenerator == null) {
			actionRequestGenerator = new ActionRequestGenerator(portalContext, portletContext, portletStatus);
		}
		return actionRequestGenerator;
	}
	
	public ActionResponseGenerator getActionResponseGenerator() {
		ActionRequest actionRequest = getActionRequestGenerator().generateActionRequest();
		if(actionResponseGenerator == null) {
			actionResponseGenerator = new ActionResponseGenerator(portletStatus, actionRequest, testResult);
		}
		return actionResponseGenerator;
	}
	
	/* (non-Javadoc)
	 * @see com.portletmaniac.portlettester.TestContext#getTestResult()
	 */
	public TestResultHolder getTestResult() {
		return testResult;
	}
	
	/* (non-Javadoc)
	 * @see com.portletmaniac.portlettester.TestContext#initPortlet(javax.portlet.Portlet)
	 */
	public void initPortlet(Portlet portlet ) throws PortletException {
		portlet.init(new MockPortletConfig(portletContext));
	}
	
	/* (non-Javadoc)
	 * @see com.portletmaniac.portlettester.TestContext#initPortlet(javax.portlet.Portlet, javax.portlet.PortletConfig)
	 */
	public void initPortlet(Portlet portlet, PortletConfig portletConfig)
			throws PortletException {
		portlet.init(portletConfig);		
	}

	/* (non-Javadoc)
	 * @see com.portletmanic.portlettester.TestContext#reset()
	 */
	
	public void reset() {
		this.renderRequest = null;
		this.renderResponse = null;
		this.eventRequest = null;
		this.eventResponse = null;
		this.resourceRequest = null;
		this.resourceResponse = null;
		this.actionRequest = null;
		this.actionResponse = null;
		
		this.portletConfigGenerator = null;
		this.actionRequestGenerator = null;
		
		this.testResult.reset();
	}
	
}
