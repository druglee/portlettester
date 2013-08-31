/**
 * 
 */
package com.portletguru.portlettester.mocks;

import java.io.BufferedReader;
import java.io.InputStream;

import javax.portlet.ClientDataRequest;
import javax.portlet.PortalContext;
import javax.portlet.PortletContext;

import com.portletguru.portlettester.PortletStatus;

/**
 * @author Derek Linde Li
 *
 */
public abstract class ClientDataRequestGenerator extends
		PortletRequestGenerator<ClientDataRequest> {

	public ClientDataRequestGenerator(PortalContext portalContext,
			PortletContext portletContext, PortletStatus portletStatus) {
		super(portalContext, portletContext, portletStatus);

	}

	/**
	 * 
	 * @param encoding
	 */
	public void setCharacterEncoding(String encoding) {
		getClientDataRequest().characterEncoding = encoding;
	}
	
	/**
	 * 
	 * @param type
	 */
	public void setContentType(String type) {
		getClientDataRequest().contentType = type;
	}
	
	/**
	 * 
	 * @param lenght
	 */
	public void setContentLenght(int lenght) {
		getClientDataRequest().contentLength = lenght;
	}
	
	/**
	 * 
	 * @param method
	 */
	public void setMethod(String method) {
		getClientDataRequest().method = method;
	}
	
	/**
	 * 
	 * @param inputStream
	 */
	public void setInputStream(InputStream inputStream) {
		getClientDataRequest().inputStream = inputStream;
	}
	
	/**
	 * 
	 * @param bufferedReader
	 */
	public void setBufferedReader(BufferedReader bufferedReader) {
		getClientDataRequest().bufferedReader = bufferedReader;
	}
	
	private MockClientDataRequest getClientDataRequest() {
		return (MockClientDataRequest) portletRequest;
	} 
}
