/**
 * 
 */
package com.portletguru.portlettester;

import java.util.LinkedList;
import java.util.List;

/**
 * This class is the container of the test results, such as the name/path 
 * of the PortletRequestDispatcher whose include/forward method has been called,
 * the content of the PrintWriter has sent, etc. 
 * 
 * @author Derek Linde Li
 *
 */
public class TestResultHolder {

	private String requestDispatcherPath;
	private String requestDispatcherName;
	private String redirectLocation;
	
	private boolean isFilterPassed;
	
	private StringBuilder outputContent;
	private List<Byte> outputBytes;
	
	/**
	 * Constructor. Don't create this object yourself. Use 
	 * {@link PortletTester#getTestResults()} instead.
	 */
	public TestResultHolder() {
		outputContent = new StringBuilder();
		outputBytes = getOutputBytesList();
	}
	
	/**
	 * Returns the path specified when invoking 
	 * javax.portlet.PortletContext#getRequestDispatcher(java.lang.String)
	 * 
	 * @return the path passed to the method
	 */
	public String getRequestDispatcherPath(){
		return requestDispatcherPath;
	}
	
	/**
	 * Returns the name of the request dispatcher passed in to the 
	 * javax.portlet.PortletContext#getNamedDispatcher(java.lang.String)
	 * 
	 * @return the name of the dispatcher
	 */
	public String getRequestDispatcherName(){
		return requestDispatcherName;
	}

	/**
	 * @param requestDispatcherPath the requestDispatcherPath to set
	 */
	public void setRequestDispatcherPath(String requestDispatcherPath) {
		this.requestDispatcherPath = requestDispatcherPath;
	}

	/**
	 * @param requestDispatcherName the requestDispatcherName to set
	 */
	public void setRequestDispatcherName(String requestDispatcherName) {
		this.requestDispatcherName = requestDispatcherName;
	}	
	
	/**
	 * Returns the URL string specified when invoking the 
	 * javax.portlet.ActionResponse#sendRedirect method.
	 * 
	 * @return the redirectLocation
	 */
	public String getRedirectLocation() {
		return redirectLocation;
	}

	/**
	 * @param redirectLocation the redirectLocation to set
	 */
	public void setRedirectLocation(String redirectLocation) {
		this.redirectLocation = redirectLocation;
	}
	
	/**
	 * Returns the content written by write/print/println methods
	 * 
	 * @return a String that contains the content has been written
	 */
	public String getOutputContent(){
		return outputContent.toString();
	}
	
	/**
	 * Appends the specified output content
	 */
	public void appendOutputContent(String s) {
		outputContent.append(s);
	}
	
	/**
	 * The content passed into write/print/println methods
	 * 
	 * @return a byte array that contains the content the user has written in OutputStream
	 */
	public Byte[] getOutputBytes(){
		return outputBytes.toArray(new Byte[0]);
	}
	
	/**
	 * Appends the specified byte
	 */
	public void appendOutputContent(byte b) {
		outputBytes.add(b);
	}
	
	/**
	 * Mark the filter as passed
	 */
	public void setFilterPassed() {
		isFilterPassed = true;
	}
	
	/**
	 * Indicates the result of a FilterChain object
	 * 
	 * @return true if FilterChain#doFilter() is invoked
	 */
	public boolean isFilterPassed() {
		return isFilterPassed;
	}

	public void reset() {
		requestDispatcherPath = null;
		requestDispatcherName = null;
		redirectLocation = null;
		outputContent = new StringBuilder();
		outputBytes = getOutputBytesList();
		isFilterPassed = false;
	}
	
	private List<Byte> getOutputBytesList(){
		return new LinkedList<Byte>();
	}	
}
