/**
 * 
 */
package com.portletguru.portlettester.mocks;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.portlet.PortletContext;
import javax.portlet.PortletRequestDispatcher;
import javax.servlet.ServletContext;

import com.portletguru.portlettester.TestResultHolder;
import com.portletguru.portlettester.utils.Constants;

/**
 * This is a temporary version of MockPortletContext, according to the JSR286:
 * 
 * The following methods of the PortletContext should provide the same functionality as
 * the methods of the ServletContext of similar name: getAttribute,
 * getAttributeNames, getInitParameter, getInitParameterNames, getMimeType,
 * getRealPath, getResource, getResourcePaths, getResourceAsStream, log,
 * removeAttribute and setAttribute.
 * 
 * But currently we don't do it following this guideline since we are too lazy
 * to develop a MockServletContext :)
 * 
 * @author Derek Linde Li
 *
 */
public class MockPortletContext implements PortletContext {
	
	// Comment this out since it has not been implemented.
	// Well, it doesn't exist.
	//private MockServletContext servletContext
	
	private Map<String, Object> attributes;
	
	//According to the JSR286 the init parameters of a PortletContext
	//must be the same as the ones of its corresponding ServletContext
	//But in this framework, we save it directly in the PortletContext
	//instance just for convenience, these parameters are defined in 
	//the web.xml file
	//TODO - save the init parameters in the corresponding ServletContext
	private Map<String,String> initParameters;
	
	private PortletRequestDispatcher requestDispatcher;
	
	private TestResultHolder resultHolder;
	
	public MockPortletContext(TestResultHolder resultHolder) {
		this(new HashMap<String,String>(), resultHolder);
		
	}
	
	public MockPortletContext(Map<String,String> initParameters, TestResultHolder resultHolder){
		attributes = new HashMap<String, Object>();
		this.initParameters = initParameters;
		requestDispatcher = new MockPortletRequestDispatcher();
		this.resultHolder = resultHolder;
	}

	/* (non-Javadoc)
	 * @see javax.portlet.PortletContext#getServerInfo()
	 */
	
	public String getServerInfo() {		
		return "PortletTesterMockServer";
	}

	/* (non-Javadoc)
	 * @see javax.portlet.PortletContext#getRequestDispatcher(java.lang.String)
	 */
	
	public PortletRequestDispatcher getRequestDispatcher(String path) {
		resultHolder.setRequestDispatcherPath(path);
		return requestDispatcher;
	}

	/* (non-Javadoc)
	 * @see javax.portlet.PortletContext#getNamedDispatcher(java.lang.String)
	 */
	
	public PortletRequestDispatcher getNamedDispatcher(String name) {
		resultHolder.setRequestDispatcherName(name);
		return requestDispatcher;
	}

	/* (non-Javadoc)
	 * @see javax.portlet.PortletContext#getResourceAsStream(java.lang.String)
	 */
	
	public InputStream getResourceAsStream(String path) {		
		//TODO - return servletContext.getResourceAsStream(path);
		throw new UnsupportedOperationException("Not yet implemented");
	}

	/* (non-Javadoc)
	 * @see javax.portlet.PortletContext#getMajorVersion()
	 */
	
	public int getMajorVersion() {		
		return 1;
	}

	/* (non-Javadoc)
	 * @see javax.portlet.PortletContext#getMinorVersion()
	 */
	
	public int getMinorVersion() {
		return 1;
	}

	/* (non-Javadoc)
	 * @see javax.portlet.PortletContext#getMimeType(java.lang.String)
	 */
	
	public String getMimeType(String file) {
		//TODO - return servletContext.getMimeType(file);
		file = file.toLowerCase();
		if( file.endsWith(".jpg") || file.endsWith(".jpeg") ) {
			return Constants.IMAGE_JPEG;
		} else if ( file.endsWith(".css") ) {
			return Constants.TEXT_CSS;
		} else if ( file.endsWith(".csv") ) {
			return Constants.TEXT_CSV;
		} else if ( file.endsWith(".html") || file.endsWith(".htm") ) {
			return Constants.TEXT_HTML;
		} else if ( file.endsWith(".js") ) {
			return Constants.TEXT_JAVASCRIPT;
		} else if ( file.endsWith(".txt") ) {
			return Constants.TEXT_PLAIN;
		} else if ( file.endsWith(".xml") ) {
			return Constants.TEXT_XML;
		} else if ( file.endsWith(".wml") ) {
			return Constants.TEXT_WML;
		} else {
			return Constants.APPLICATION_TEXT;
		}			
	}

	/* (non-Javadoc)
	 * @see javax.portlet.PortletContext#getRealPath(java.lang.String)
	 */
	
	public String getRealPath(String path) {		
		//TODO - return servletContext.getRealPath(path);
		throw new UnsupportedOperationException("Not yet implemented");
	}

	/* (non-Javadoc)
	 * @see javax.portlet.PortletContext#getResourcePaths(java.lang.String)
	 */
	
	public Set<String> getResourcePaths(String path) {
		//TODO - return servletContext.getResourcePaths(path);
		throw new UnsupportedOperationException("Not yet implemented");
	}

	/* (non-Javadoc)
	 * @see javax.portlet.PortletContext#getResource(java.lang.String)
	 */
	
	public URL getResource(String path) throws MalformedURLException {		
		//TODO - return servletContext.getResource(path);
		throw new UnsupportedOperationException("Not yet implemented");
	}

	/* (non-Javadoc)
	 * @see javax.portlet.PortletContext#getAttribute(java.lang.String)
	 */
	
	public Object getAttribute(String name) {
		if( name == null ){
			throw new IllegalArgumentException("name should not be null");
		}
		//TODO - return servletContext.getAttribute(name);
		return attributes.get(name);
	}

	/* (non-Javadoc)
	 * @see javax.portlet.PortletContext#getAttributeNames()
	 */
	
	public Enumeration<String> getAttributeNames() {
		//TODO - return servletContext.getAttributeNames();
		return Collections.enumeration(attributes.keySet());
	}

	/* (non-Javadoc)
	 * @see javax.portlet.PortletContext#getInitParameter(java.lang.String)
	 */
	
	public String getInitParameter(String name) {
		if( name == null ) {
			throw new IllegalArgumentException("name cannot be null");
		}
		//TODO - return servletContext.getInitParameter(name);
		return initParameters.get(name);
	}

	/* (non-Javadoc)
	 * @see javax.portlet.PortletContext#getInitParameterNames()
	 */
	
	public Enumeration<String> getInitParameterNames() {
		//TODO - return servletContext.getInitParameterNames();
		return Collections.enumeration(initParameters.keySet());
	}

	/* (non-Javadoc)
	 * @see javax.portlet.PortletContext#log(java.lang.String)
	 */
	
	public void log(String msg) {
		//TODO - servletContext.log(msg);
		System.out.println(msg);
	}

	/* (non-Javadoc)
	 * @see javax.portlet.PortletContext#log(java.lang.String, java.lang.Throwable)
	 */
	
	public void log(String message, Throwable throwable) {
		//TOOD - servletContext.log(message, throwable);
		System.out.println(message);
		throwable.printStackTrace();
	}

	/* (non-Javadoc)
	 * @see javax.portlet.PortletContext#removeAttribute(java.lang.String)
	 */
	
	public void removeAttribute(String name) {
		if( name == null ){
			throw new IllegalArgumentException("name cannot be null");
		}
		//TODO - servletContext.removeAttribute(name);
		attributes.remove(name);
	}

	/* (non-Javadoc)
	 * @see javax.portlet.PortletContext#setAttribute(java.lang.String, java.lang.Object)
	 */
	
	public void setAttribute(String name, Object object) {
		if( name == null ){
			throw new IllegalArgumentException("name cannot be null");
		}		
		//TODO - servletContext.setAttribute(name, object);
		attributes.put(name, object);
	}

	/* (non-Javadoc)
	 * @see javax.portlet.PortletContext#getPortletContextName()
	 */
	
	public String getPortletContextName() {
		return "portletContext";
	}

	/* (non-Javadoc)
	 * @see javax.portlet.PortletContext#getContainerRuntimeOptions()
	 */
	
	public Enumeration<String> getContainerRuntimeOptions() {
		/*
		 * A Container Runtime Option is a value defined in the portlet.xml
		 * file of a Portlet. The Portlet container will read the options 
		 * defined in a Portlet and perform or not perform certain behavior 
		 * based on the value defined. 
		 * 
		 * Here are the mandatory options need to be supported by all Portlet 
		 * containers.
		 * 
		 * javax.portlet.escapeXml - true for XML escaping URL
		 * javax.portlet.renderHeaders - The default for this setting is false. 
		 * 		When set to true streaming portal implementations should call 
		 * 		the render method of the portlet twice with RENDER_PART attribute 
		 * 		set in the render request.
		 * javax.portlet.actionScopedRequestAttributes - true to allow the attributes
		 * 		saved in the request when processing action to be accessible by other methods that process
		 * 		event and serve resource until the action is accessed again or any 
		 * 		other methods that process event(before the first render of this scope occurs), 
		 * 		process render and serve resource is accessed without the previous scope ID.
		 * 		In order to determine if a render has already occurred for the current action-scope it is
		 *		assumed that the portlet container stores a bit invisible to the portlet in the action-scoped
		 *		attributes that indicates if a render has already occurred.
		 * */
		
		//TODO - Read the these options from the portlet.xml file
		return Collections.enumeration(new ArrayList<String>());
	}

}
