/**
 * 
 */
package com.portletguru.portlettester.mocks;

import javax.portlet.PortletResponse;

/**
 * @author Derek Linde Li
 *
 */
public abstract class PortletResponseGenerator<T extends PortletResponse> {
	
	protected MockPortletResponse portletResponse;
	
	/**
	 * 
	 * @param namespace
	 */
	public void setNampespace(String namespace) {
		portletResponse.namespace = namespace;
	}
	
	public abstract T generateResponse();
}
