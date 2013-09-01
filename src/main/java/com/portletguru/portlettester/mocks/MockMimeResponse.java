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

import com.portletguru.portlettester.TestResultHolder;
import com.portletguru.portlettester.mocks.http.MockPrintWriter;
import com.portletguru.portlettester.mocks.http.MockServletOutputStream;

/**
 * @author Derek Linde Li
 *
 */
public abstract class MockMimeResponse extends MockPortletResponse implements
		MimeResponse {
	
	private boolean isCalledGetPortletOutputStream;
	private boolean isCalledGetWriter;
	//TODO : take care of the committed state
	private boolean committed;
	
	private int bufferSize;
	private PrintWriter writer;
	private OutputStream outputStream;
	private CacheControl cacheControl;
	
	protected String contentType;
	protected String characterEncoding;
	protected Locale locale;
	protected Map<String, Object> existingHeaders;
 	
 	public MockMimeResponse(PortletRequest request, TestResultHolder resultHolder) {
 		existingHeaders = new LinkedHashMap<String, Object>();
 		/* clone the existing properties for reseting */
 		// if reset is called later, just simply replace the _headers with this clone one
 		for( Map.Entry<String, Object> entry : _headers.entrySet() ) {
 			existingHeaders.put(entry.getKey(), entry.getValue());
 		}
 		this.request = request;
 		this.writer = new PrintWriter(new MockPrintWriter(resultHolder));
 		this.outputStream = new MockServletOutputStream(resultHolder);
 		this.cacheControl = new MockCacheControl();
	}

	/* (non-Javadoc)
	 * @see javax.portlet.MimeResponse#getContentType()
	 */
	
	public String getContentType() {
		if(contentType == null) {
			contentType = request.getResponseContentType();
		}
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
	}

	/* (non-Javadoc)
	 * @see javax.portlet.MimeResponse#getCharacterEncoding()
	 */
	
	public String getCharacterEncoding() {
		return characterEncoding;
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
		return writer;
	}

	/* (non-Javadoc)
	 * @see javax.portlet.MimeResponse#getLocale()
	 */
	
	public Locale getLocale() {
		if(locale == null) {
			locale = request.getLocale();
		}
		return locale;
	}

	/* (non-Javadoc)
	 * @see javax.portlet.MimeResponse#setBufferSize(int)
	 */
	
	public void setBufferSize(int size) {
		bufferSize = size;
	}

	/* (non-Javadoc)
	 * @see javax.portlet.MimeResponse#getBufferSize()
	 */
	
	public int getBufferSize() {
		return bufferSize;
	}

	/* (non-Javadoc)
	 * @see javax.portlet.MimeResponse#flushBuffer()
	 */
	
	public void flushBuffer() throws IOException {
		//TODO - Find a better way to inform the invoker
		System.out.println("buffer has been flushed successfully.");
	}

	/* (non-Javadoc)
	 * @see javax.portlet.MimeResponse#resetBuffer()
	 */
	
	public void resetBuffer() {
		if(isCommitted()){
			throw new IllegalStateException("response has been committed");
		}
		//TODO - Find a better way to inform the invoker
		System.out.println("buffer has been reset successfully.");
	}

	/* (non-Javadoc)
	 * @see javax.portlet.MimeResponse#isCommitted()
	 */
	
	public boolean isCommitted() {
		return committed;
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
		resetBuffer();
		super._headers = existingHeaders;
	}

	/* (non-Javadoc)
	 * @see javax.portlet.MimeResponse#getPortletOutputStream()
	 */
	
	public OutputStream getPortletOutputStream() throws IOException {
		if (isCalledGetWriter) {
			throw new IllegalStateException();
		}
		
		getContentType();
		
		isCalledGetPortletOutputStream = true;
		return outputStream;
	}	

	/* (non-Javadoc)
	 * @see javax.portlet.MimeResponse#getCacheControl()
	 */
	
	public CacheControl getCacheControl() {
		return cacheControl;
	}
	
	
	/* (non-Javadoc)
	 * @see javax.portlet.MimeResponse#createRenderURL()
	 */
	
	public PortletURL createRenderURL() {
		return super.createRenderURL();
	}

	/* (non-Javadoc)
	 * @see javax.portlet.MimeResponse#createActionURL()
	 */
	
	public PortletURL createActionURL() {
		return super.createActionURL();
	}

	/* (non-Javadoc)
	 * @see javax.portlet.MimeResponse#createResourceURL()
	 */
	
	public ResourceURL createResourceURL() {
		return super.createResourceURL();
	}

}
