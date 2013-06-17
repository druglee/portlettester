/**
 * 
 */
package com.portletguru.portlettester.mocks;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

import javax.portlet.CacheControl;
import javax.portlet.MimeResponse;
import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;
import javax.portlet.ResourceURL;
import javax.servlet.http.HttpServletResponse;

import com.portletguru.portlettester.mocks.http.MockHttpServletResponse;

/**
 * @author Derek Linde Li
 *
 */
public abstract class MockMimeResponse extends MockPortletResponse implements
		MimeResponse {
	
	private String contentType;
	protected HttpServletResponse response;
	private boolean isCalledGetPortletOutputStream;
 	private boolean isCalledGetWriter;
 	private PortletRequest request;
 	private Map<String, Object> existingHeaders;
 	
 	public MockMimeResponse(PortletRequest request) {
 		existingHeaders = new LinkedHashMap<String, Object>();
 		/* clone the existing properties for reseting */
 		// if reset is called later, just simply replace the _headers with this clone one
 		for( Map.Entry<String, Object> entry : _headers.entrySet() ) {
 			existingHeaders.put(entry.getKey(), entry.getValue());
 		}
 		this.request = request;
 		this.response = new MockHttpServletResponse();
	} 	

	/* (non-Javadoc)
	 * @see javax.portlet.MimeResponse#getContentType()
	 */
	
	public String getContentType() {		
		return contentType;
	}

	/* (non-Javadoc)
	 * @see javax.portlet.MimeResponse#setContentType(java.lang.String)
	 */
	
	public void setContentType(String type) {
		if (type == null || type.equals("")) {
			throw new IllegalArgumentException("content type should not be empty");
		}
		//boolean valid = false;
		boolean valid = true;
		if( getLifeCycle().equals(PortletRequest.RESOURCE_PHASE) ){
			valid = true;
		} else {
			Enumeration<String> supportedTypes = request.getResponseContentTypes();
			//TODO - MODIFY THE LOGIC HERE
//			while(supportedTypes.hasMoreElements()){
//				String thisType = supportedTypes.nextElement();
//				if( type.startsWith(thisType) ){
//					valid = true;
//					break;
//				}
//			}
		}
		if(!valid){
			throw new IllegalArgumentException("content type" + type + " is not supported");
		}
		this.contentType = type;
		this.response.setContentType(type);
	}

	/* (non-Javadoc)
	 * @see javax.portlet.MimeResponse#getCharacterEncoding()
	 */
	
	public String getCharacterEncoding() {
		return response.getCharacterEncoding();
	}

	/* (non-Javadoc)
	 * @see javax.portlet.MimeResponse#getWriter()
	 */
	
	public PrintWriter getWriter() throws IOException {
		if (isCalledGetPortletOutputStream) {
			throw new IllegalStateException();
		}

		if (contentType == null) {
			setContentType(request.getResponseContentType());
		}
		isCalledGetWriter = true;
		return response.getWriter();
	}

	/* (non-Javadoc)
	 * @see javax.portlet.MimeResponse#getLocale()
	 */
	
	public Locale getLocale() {
		return request.getLocale();
	}

	/* (non-Javadoc)
	 * @see javax.portlet.MimeResponse#setBufferSize(int)
	 */
	
	public void setBufferSize(int size) {
		response.setBufferSize(size);
	}

	/* (non-Javadoc)
	 * @see javax.portlet.MimeResponse#getBufferSize()
	 */
	
	public int getBufferSize() {
		return response.getBufferSize();
	}

	/* (non-Javadoc)
	 * @see javax.portlet.MimeResponse#flushBuffer()
	 */
	
	public void flushBuffer() throws IOException {
		response.flushBuffer();
	}

	/* (non-Javadoc)
	 * @see javax.portlet.MimeResponse#resetBuffer()
	 */
	
	public void resetBuffer() {
		if(response.isCommitted()){
			throw new IllegalStateException("response has been committed");
		}
		response.resetBuffer();
	}

	/* (non-Javadoc)
	 * @see javax.portlet.MimeResponse#isCommitted()
	 */
	
	public boolean isCommitted() {
		return response.isCommitted();
	}

	/* (non-Javadoc)
	 * @see javax.portlet.MimeResponse#reset()
	 */
	
	public void reset() {
		if(isCommitted()){
			throw new IllegalStateException("response has been committed");
		}
		/* reset properties and buffer */
		/* According to JSR286, reset method must reset both the buffer 
		 * and the properties set prior to it is called */
		response.resetBuffer();
		super._headers = existingHeaders;
	}

	/* (non-Javadoc)
	 * @see javax.portlet.MimeResponse#getPortletOutputStream()
	 */
	
	public OutputStream getPortletOutputStream() throws IOException {
		if (isCalledGetWriter) {
			throw new IllegalStateException();
		}
		if (contentType == null) {
			setContentType(request.getResponseContentType());
		}
		isCalledGetPortletOutputStream = true;
		return response.getOutputStream();
	}	

	/* (non-Javadoc)
	 * @see javax.portlet.MimeResponse#getCacheControl()
	 */
	
	public CacheControl getCacheControl() {
		return new MockCacheControl();
	}
	
	
	/* (non-Javadoc)
	 * @see javax.portlet.MimeResponse#createRenderURL()
	 */
	
	public PortletURL createRenderURL() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see javax.portlet.MimeResponse#createActionURL()
	 */
	
	public PortletURL createActionURL() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see javax.portlet.MimeResponse#createResourceURL()
	 */
	
	public ResourceURL createResourceURL() {
		// TODO Auto-generated method stub
		return null;
	}

}
