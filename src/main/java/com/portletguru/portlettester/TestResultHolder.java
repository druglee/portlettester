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

	private static TestResultHolder instance;
	private String requestDispatcherPath;
	private String requestDispatcherName;
	private String redirectLocation;
	
	private StringBuilder outputContent;
	private List<Byte> outputBytes;
	
	private TestResultHolder() {
		outputContent = new StringBuilder();
		outputBytes = getOutputBytesList();
	}
	
	public synchronized static TestResultHolder getInstance(){
		if(instance == null) {
			instance = new TestResultHolder();
		}
		return instance;
	}
	
	public String getRequestDispatcherPath(){
		return requestDispatcherPath;
	}
	
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
	 * The content passed into write/print/println methods
	 * @return a String that contains the content the user has written
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

	public void reset() {
		requestDispatcherPath = null;
		requestDispatcherName = null;
		redirectLocation = null;
		outputContent = new StringBuilder();
		outputBytes = getOutputBytesList();
	}
	
	private List<Byte> getOutputBytesList(){
		return new LinkedList<Byte>();
	}	
}
