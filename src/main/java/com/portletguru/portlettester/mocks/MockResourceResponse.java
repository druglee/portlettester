/**
 * 
 */
package com.portletguru.portlettester.mocks;

import java.util.Locale;

import javax.portlet.PortletRequest;
import javax.portlet.ResourceResponse;

import com.portletguru.portlettester.TestResultHolder;

/**
 * @author Derek Linde Li
 *
 */
public class MockResourceResponse extends MockMimeResponse implements
		ResourceResponse {
	
	private int contentLength;

	public MockResourceResponse(PortletRequest request, TestResultHolder resultHolder) {
		super(request, resultHolder);
	}

	/* (non-Javadoc)
	 * @see javax.portlet.ResourceResponse#setLocale(java.util.Locale)
	 */
	
	public void setLocale(Locale loc) {
		locale = loc;
	}

	/* (non-Javadoc)
	 * @see javax.portlet.ResourceResponse#setCharacterEncoding(java.lang.String)
	 */
	
	public void setCharacterEncoding(String charset) {
		characterEncoding = charset;
	}

	/* (non-Javadoc)
	 * @see javax.portlet.ResourceResponse#setContentLength(int)
	 */
	
	public void setContentLength(int len) {
		contentLength = len;
	}

	/* (non-Javadoc)
	 * @see com.portletmanic.portlettester.mocks.MockPortletResponse#getLifeCycle()
	 */
	
	protected String getLifeCycle() {
		return PortletRequest.RESOURCE_PHASE;
	}

}
