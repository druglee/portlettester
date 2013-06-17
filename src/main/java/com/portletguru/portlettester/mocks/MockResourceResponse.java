/**
 * 
 */
package com.portletguru.portlettester.mocks;

import java.util.Locale;

import javax.portlet.PortletRequest;
import javax.portlet.ResourceResponse;

/**
 * @author Derek Linde Li
 *
 */
public class MockResourceResponse extends MockMimeResponse implements
		ResourceResponse {

	public MockResourceResponse(PortletRequest request) {
		super(request);
	}

	/* (non-Javadoc)
	 * @see javax.portlet.ResourceResponse#setLocale(java.util.Locale)
	 */
	
	public void setLocale(Locale loc) {
		response.setLocale(loc);
	}

	/* (non-Javadoc)
	 * @see javax.portlet.ResourceResponse#setCharacterEncoding(java.lang.String)
	 */
	
	public void setCharacterEncoding(String charset) {
		response.setCharacterEncoding(charset);
	}

	/* (non-Javadoc)
	 * @see javax.portlet.ResourceResponse#setContentLength(int)
	 */
	
	public void setContentLength(int len) {
		response.setContentLength(len);
	}

	/* (non-Javadoc)
	 * @see com.portletmanic.portlettester.mocks.MockPortletResponse#getLifeCycle()
	 */
	
	protected String getLifeCycle() {
		return PortletRequest.RESOURCE_PHASE;
	}

}
