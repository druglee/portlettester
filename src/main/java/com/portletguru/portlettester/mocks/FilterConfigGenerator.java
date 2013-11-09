/**
 * 
 */
package com.portletguru.portlettester.mocks;

import javax.portlet.PortletContext;
import javax.portlet.filter.FilterConfig;

/**
 * A helper class to create FilterConfig object
 * 
 * @author Derek Linde Li
 *
 */
public class FilterConfigGenerator {
	
	private MockFilterConfig filterConfig;

	public FilterConfigGenerator(PortletContext portletContext) {
		filterConfig = new MockFilterConfig(portletContext);
	}
	
	public void setFilterName(String filterName) {
		filterConfig.filterName = filterName;
	}
	
	public void addInitParameter(String key, String value) {
		filterConfig.initParameters.put(key, value);
	}
	
	public FilterConfig generateFilterConfig() {
		return filterConfig;
	}
}
