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
	
	protected String characterEncoding;
	protected String contentType;
	protected int contentLength;
	protected String method;
	protected BufferedReader bufferedReader;
	protected InputStream inputStream;

	public MockClientDataRequest(PortalContext portalContext,
			PortletContext portletContext, PortletStatus portletStatus) {
		super(portalContext, portletContext, portletStatus);
		
		this.characterEncoding = "UTF-8";
		this.contentType = "text/html";
		this.method = "GET";
	}

	/* (non-Javadoc)
	 * @see javax.portlet.ClientDataRequest#getPortletInputStream()
	 */
	
	public InputStream getPortletInputStream() throws IOException {
		return inputStream;
	}

	/* (non-Javadoc)
	 * @see javax.portlet.ClientDataRequest#setCharacterEncoding(java.lang.String)
	 */
	
	public void setCharacterEncoding(String enc)
			throws UnsupportedEncodingException {
		characterEncoding = enc;
	}

	/* (non-Javadoc)
	 * @see javax.portlet.ClientDataRequest#getReader()
	 */
	
	public BufferedReader getReader() throws UnsupportedEncodingException,
			IOException {
		return bufferedReader;
	}

	/* (non-Javadoc)
	 * @see javax.portlet.ClientDataRequest#getCharacterEncoding()
	 * Always return UTF-8
	 */
	
	public String getCharacterEncoding() {
		return characterEncoding;
	}

	/* (non-Javadoc)
	 * @see javax.portlet.ClientDataRequest#getContentType()
	 */
	
	public String getContentType() {
		return contentType;
	}

	/* (non-Javadoc)
	 * @see javax.portlet.ClientDataRequest#getContentLength()
	 * Always return 0.
	 */
	
	public int getContentLength() {
		return contentLength;
	}

	/* (non-Javadoc)
	 * @see javax.portlet.ClientDataRequest#getMethod()
	 * Always return POST.
	 */
	
	public String getMethod() {		
		return method;
	}

}
