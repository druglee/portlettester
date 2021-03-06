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
import javax.portlet.PreferencesValidator;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.portlet.filter.FilterChain;
import javax.portlet.filter.FilterConfig;
import javax.portlet.filter.PortletFilter;

import com.portletguru.portlettester.PortletStatus;
import com.portletguru.portlettester.TestContext;
import com.portletguru.portlettester.TestResultHolder;
import com.portletguru.portlettester.mocks.ActionRequestGenerator;
import com.portletguru.portlettester.mocks.ActionResponseGenerator;
import com.portletguru.portlettester.mocks.DefaultPreferencesConfig;
import com.portletguru.portlettester.mocks.EventRequestGenerator;
import com.portletguru.portlettester.mocks.EventResponseGenerator;
import com.portletguru.portlettester.mocks.FilterConfigGenerator;
import com.portletguru.portlettester.mocks.MockActionRequest;
import com.portletguru.portlettester.mocks.MockActionResponse;
import com.portletguru.portlettester.mocks.MockEventRequest;
import com.portletguru.portlettester.mocks.MockEventResponse;
import com.portletguru.portlettester.mocks.MockFilterChain;
import com.portletguru.portlettester.mocks.MockPortalContext;
import com.portletguru.portlettester.mocks.MockPortletConfig;
import com.portletguru.portlettester.mocks.MockPortletContext;
import com.portletguru.portlettester.mocks.MockRenderRequest;
import com.portletguru.portlettester.mocks.MockRenderResponse;
import com.portletguru.portlettester.mocks.MockResourceRequest;
import com.portletguru.portlettester.mocks.MockResourceResponse;
import com.portletguru.portlettester.mocks.PortletConfigGenerator;
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
	
	private FilterChain filterChain;
	
	private DefaultPreferencesConfig defaultPreferencesConfig;
	
	private TestResultHolder testResult;
	
	private PortletConfigGenerator portletConfigGenerator;
	private FilterConfigGenerator filterConfigGenerator;
	
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
		defaultPreferencesConfig = new DefaultPreferencesConfig();
		portalContext = new MockPortalContext();
		portletContext = new MockPortletContext(contextInitParameters, testResult);
		portletStatus = new PortletStatus(defaultPreferencesConfig);
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
	
	public FilterConfigGenerator getFilterConfigGenerator() {
		if(filterConfigGenerator == null) {
			filterConfigGenerator = new FilterConfigGenerator(portletContext);
		}
		return filterConfigGenerator;
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
	
	public void addDefaultPreferences(String name, String[] values, boolean isReadOnly) {
		defaultPreferencesConfig.addPreference(name, values, isReadOnly);
	}
	
	public void setPreferencesValidator(PreferencesValidator validator) {
		defaultPreferencesConfig.setValidator(validator);
	}
	
	
	public FilterChain getFilterChain() {
		if(filterChain == null) {
			filterChain = new MockFilterChain(testResult);
		}
		return filterChain;
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
	 * @see com.portletmaniac.portlettester.TestContext#initPortlet(javax.portlet.Portlet, javax.portlet.PortletConfig)
	 */
	public void initFilter(PortletFilter filter, FilterConfig filterConfig) throws PortletException {
		filter.init(filterConfig);
	}

	/* (non-Javadoc)
	 * @see com.portletmanic.portlettester.TestContext#reset()
	 */
	
	public void reset() {
		
		this.portletConfigGenerator = null;
		this.filterConfigGenerator = null;
		this.actionRequestGenerator = null;
		this.actionResponseGenerator = null;
		this.eventRequestGenerator = null;
		this.eventResponseGenerator = null;
		this.renderRequestGenerator = null;
		this.renderResponseGenerator = null;
		this.resourceRequestGenerator = null;
		this.resourceResponseGenerator = null;
		
		this.defaultPreferencesConfig = new DefaultPreferencesConfig();
		
		this.testResult.reset();
	}
}
