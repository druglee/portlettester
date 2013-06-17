/**
 * 
 */
package com.portletguru.portlettester.mocks;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import javax.portlet.ClientDataRequest;
import javax.portlet.PortalContext;
import javax.portlet.PortletContext;

import com.portletguru.portlettester.PortletStatus;

/**
 * @author Derek Linde Li
 * ClientDataRequest interface represents the request information of the HTTP request 
 * issued from the client to the consuming application / portal, such as the input stream.
 */
public abstract class MockClientDataRequest extends MockPortletRequest implements
		ClientDataRequest {

	public MockClientDataRequest(PortalContext portalContext,
			PortletContext portletContext, PortletStatus portletStatus) {
		super(portalContext, portletContext, portletStatus);
	}

	/* (non-Javadoc)
	 * @see javax.portlet.ClientDataRequest#getPortletInputStream()
	 */
	
	public InputStream getPortletInputStream() throws IOException {
		throw new UnsupportedOperationException("Not available");
	}

	/* (non-Javadoc)
	 * @see javax.portlet.ClientDataRequest#setCharacterEncoding(java.lang.String)
	 */
	
	public void setCharacterEncoding(String enc)
			throws UnsupportedEncodingException {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see javax.portlet.ClientDataRequest#getReader()
	 */
	
	public BufferedReader getReader() throws UnsupportedEncodingException,
			IOException {
		throw new UnsupportedOperationException("Not available");
	}

	/* (non-Javadoc)
	 * @see javax.portlet.ClientDataRequest#getCharacterEncoding()
	 * Always return UTF-8
	 */
	
	public String getCharacterEncoding() {		
		return "UTF-8";
	}

	/* (non-Javadoc)
	 * @see javax.portlet.ClientDataRequest#getContentType()
	 */
	
	public String getContentType() {
		throw new UnsupportedOperationException("Not available");
	}

	/* (non-Javadoc)
	 * @see javax.portlet.ClientDataRequest#getContentLength()
	 * Always return 0.
	 */
	
	public int getContentLength() {
		return 128;
	}

	/* (non-Javadoc)
	 * @see javax.portlet.ClientDataRequest#getMethod()
	 * Always return POST.
	 */
	
	public String getMethod() {		
		return "POST";
	}

}
