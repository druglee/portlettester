/**
 * 
 */
package com.portletguru.portlettester.mocks;

import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.portlet.PortletContext;
import javax.portlet.filter.FilterConfig;

/**
 * @author Derek Linde Li
 *
 */
public class MockFilterConfig implements FilterConfig {
	
	protected String filterName;
	protected Map<String,String> initParameters;
	private PortletContext portletContext;
	
	public MockFilterConfig(PortletContext portletContext) {
		this.portletContext = portletContext;
		this.initParameters = new HashMap<String, String>();
	}

	/* (non-Javadoc)
	 * @see javax.portlet.filter.FilterConfig#getFilterName()
	 */
	public String getFilterName() {
		return filterName;
	}

	/* (non-Javadoc)
	 * @see javax.portlet.filter.FilterConfig#getPortletContext()
	 */
	public PortletContext getPortletContext() {
		return portletContext;
	}

	/* (non-Javadoc)
	 * @see javax.portlet.filter.FilterConfig#getInitParameter(java.lang.String)
	 */
	public String getInitParameter(String name) {
		return initParameters.get(name);
	}

	/* (non-Javadoc)
	 * @see javax.portlet.filter.FilterConfig#getInitParameterNames()
	 */
	public Enumeration<String> getInitParameterNames() {
		return Collections.enumeration(initParameters.keySet());
	}

}
