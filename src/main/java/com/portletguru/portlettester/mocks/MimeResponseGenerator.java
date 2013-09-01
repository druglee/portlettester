/**
 * 
 */
package com.portletguru.portlettester.mocks;

import javax.portlet.MimeResponse;
import javax.portlet.PortletRequest;

import com.portletguru.portlettester.TestResultHolder;

/**
 * @author Derek Linde Li
 *
 */
public abstract class MimeResponseGenerator extends PortletResponseGenerator<MimeResponse> {
	
	public MimeResponseGenerator(PortletRequest request, TestResultHolder resultHolder) {
		
	}
	
	/**
	 * 
	 * @param contentType
	 */
	public void setContentType(String contentType) {
		getMimeResponse().contentType = contentType;
	}
	
	/**
	 * 
	 * @param encoding
	 */
	public void setCharacterEncoding(String encoding) {
		getMimeResponse().characterEncoding = encoding;
	}
	
	private MockMimeResponse getMimeResponse() {
		return (MockMimeResponse) portletResponse;
	}
}
