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
 * The container of the entire PortletTester framework. Usually, you need to interact 
 * with this class in most of the cases, including but not limited to initializing 
 * the Portlet or PortletFilter being tested, getting the corresponding generators 
 * of the desired PortletRequest/PortletResponse objects. For more details on how to 
 * use this class, please refer to the sample project of PortletTester: 
 * <a href="http://github.com/druglee/portlettester-sample">PortletTester-Sample</a>
 * 
 * @author Derek Linde Li
 *
 */
public class PortletTester {
	
	private TestContext testContext;
	
	public PortletTester() {
		testContext = new DefaultTestContext();
	}
	
	/**
	 * Initializes the specified Portlet object
	 * 
	 * @param portlet
	 * @throws PortletException
	 */
	public void initPortlet(Portlet portlet) throws PortletException {
		testContext.initPortlet(portlet);
	}
	
	/**
	 * Initializes the specified Portlet object with a PortletConfig object 
	 * 
	 * @param portlet
	 * @param portletConfig
	 * @throws PortletException
	 */
	public void initPortlet(Portlet portlet, PortletConfig portletConfig) throws PortletException {
		testContext.initPortlet(portlet, portletConfig);
	}
	
	/**
	 * Initializes the PortletFilter object with the FilterConfig object
	 * 
	 * @param filter
	 * @param filterConfig
	 * @throws PortletException
	 */
	public void initFilter(PortletFilter filter, FilterConfig filterConfig) throws PortletException {
		testContext.initFilter(filter, filterConfig);
	}
	
	/**
	 * Resets the PortletTest container, everything will be set to its original state
	 */
	public void reset() {
		testContext.reset();
	}
	
	/**
	 * Returns the {@link TestResultHolder} object that stores the test results
	 * 
	 * @return
	 */
	public TestResultHolder getTestResults() {
		return testContext.getTestResult();
	}
	
	/**
	 * Adds a default preference item.
	 * 
	 * @param name the name of the preference item
	 * @param values the values of the preference item
	 * @param isReadOnly if the preference item is read-only
	 */
	public void addDefaultPreferences(String name, String[] values, boolean isReadOnly) {
		testContext.addDefaultPreferences(name, values, isReadOnly);
	}
	
	/**
	 * Specifies the validator of all preference items. It should be 
	 * the validator ususally specified in the &lt;preferences-validator&gt; tag.
	 * 
	 * @param validator
	 */
	public void setPreferencesValidator(PreferencesValidator validator) {
		testContext.setPreferencesValidator(validator);
	}
	
	/**
	 * Returns a FilterChain object
	 * 
	 * @return
	 */
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
