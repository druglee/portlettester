/**
 * 
 */
package com.portletguru.portlettester.mocks;

import javax.portlet.PortletContext;
import javax.portlet.filter.FilterConfig;

import com.portletguru.portlettester.PortletTester;

/**
 * A helper class to create FilterConfig object
 * 
 * @author Derek Linde Li
 *
 */
public class FilterConfigGenerator {
	
	private MockFilterConfig filterConfig;

	/**
	 * Constructor. Don't construct the generator yourself. Use 
	 * {@link PortletTester#getFilterConfigGenerator()} instead.
	 * 
	 * @param portletContext
	 */
	public FilterConfigGenerator(PortletContext portletContext) {
		filterConfig = new MockFilterConfig(portletContext);
	}
	
	/**
	 * Specifies the name of the filter
	 * 
	 * @param filterName
	 */
	public void setFilterName(String filterName) {
		filterConfig.filterName = filterName;
	}
	
	/**
	 * Adds a pair of init-parameter to this config
	 * 
	 * @param key the key of the parameter
	 * @param value the value of the parameter
	 */
	public void addInitParameter(String key, String value) {
		filterConfig.initParameters.put(key, value);
	}
	
	/**
	 * Returns the FilterConfig object with the content specified(filter name 
	 * and the init-parameters).
	 * 
	 * @return the initialized FilterConfig object
	 */
	public FilterConfig generateFilterConfig() {
		return filterConfig;
	}
}
