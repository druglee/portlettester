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
import com.portletguru.portlettester.mocks.EventRequestGenerator;
import com.portletguru.portlettester.mocks.EventResponseGenerator;
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
import com.portletguru.portlettester.mocks.RenderRequestGenerator;
import com.portletguru.portlettester.mocks.RenderResponseGenerator;
import com.portletguru.portlettester.mocks.ResourceRequestGenerator;
import com.portletguru.portlettester.mocks.ResourceResponseGenerator;

/**
 * @author Derek Linde Li
 *
 */
public class DefaultTestContext implements TestContext {
	
	private PortalContext portalContext;
	private PortletContext portletContext;
	private PortletStatus portletStatus;
	
	private TestResultHolder testResult;
	
	private PortletConfigGenerator portletConfigGenerator;
	
	private ActionRequestGenerator actionRequestGenerator;
	private ActionResponseGenerator actionResponseGenerator;
	private RenderRequestGenerator renderRequestGenerator;
	private RenderResponseGenerator renderResponseGenerator;
	private EventRequestGenerator eventRequestGenerator;
	private EventResponseGenerator eventResponseGenerator;
	private ResourceRequestGenerator resourceRequestGenerator;
	private ResourceResponseGenerator resourceResponseGenerator;
	
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
	 * @see com.portletmanic.portlettester.TestContext#getPortletConfigGenerator()
	 */
	
	public PortletConfigGenerator getPortletConfigGenerator() {
		if(portletConfigGenerator == null) {
			portletConfigGenerator = new PortletConfigGenerator(portletContext, portletStatus);
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
		ActionRequest actionRequest = getActionRequestGenerator().generateRequest();
		if(actionResponseGenerator == null) {
			actionResponseGenerator = new ActionResponseGenerator(portletStatus, actionRequest, testResult);
		}
		return actionResponseGenerator;
	}
	
	public ResourceRequestGenerator getResourceRequestGenerator() {
		if(resourceRequestGenerator == null) {
			resourceRequestGenerator = new ResourceRequestGenerator(portalContext, portletContext, portletStatus);
		}
		return resourceRequestGenerator;
	}

	public ResourceResponseGenerator getResourceResponseGenerator() {
		
		if(resourceResponseGenerator == null) {
			ResourceRequest request = getResourceRequestGenerator().generateRequest();
			resourceResponseGenerator = new ResourceResponseGenerator(request, testResult);
		}
		return resourceResponseGenerator;
	}

	public RenderRequestGenerator getRenderRequestGenerator() {
		if(renderRequestGenerator == null) {
			renderRequestGenerator = new RenderRequestGenerator(portalContext, portletContext, portletStatus);
		}
		return renderRequestGenerator;
	}

	public RenderResponseGenerator getRenderResponseGenerator() {
		if(renderResponseGenerator == null) {
			RenderRequest request = getRenderRequestGenerator().generateRequest();
			renderResponseGenerator = new RenderResponseGenerator(request, testResult);
		}
		return renderResponseGenerator;
	}

	public EventRequestGenerator getEventRequestGenerator() {
		if(eventRequestGenerator == null) {
			eventRequestGenerator = new EventRequestGenerator(portalContext, portletContext, portletStatus);
		}
		return eventRequestGenerator;
	}

	public EventResponseGenerator getEventResponseGenerator() {
		if(eventResponseGenerator == null) {
			EventRequest request = getEventRequestGenerator().generateRequest();
			eventResponseGenerator = new EventResponseGenerator(portletStatus, request);
		}
		return eventResponseGenerator;
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
		
		this.portletConfigGenerator = null;
		this.actionRequestGenerator = null;
		
		this.testResult.reset();
	}
}
