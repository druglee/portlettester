/**
 * 
 */
package com.portletguru.portlettester.mocks;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import javax.portlet.PortletConfig;
import javax.portlet.PortletContext;
import javax.xml.XMLConstants;
import javax.xml.namespace.QName;


/**
 * @author Derek Linde Li
 *
 */
public class MockPortletConfig implements PortletConfig {
	
	private static String DEFAULT_BUNDLE_BASE_NAME = "com.portletmanic.portlettester.resource";
	
	private PortletContext portletContext;
	private String portletName;
	private String bundleBaseName;
	private String defaultNamespace;
	private Map<String,String[]> containerRuntimeOptions;
	private Map<String,String> initParameters;
	
	public MockPortletConfig(PortletContext portletContext){
		this.portletContext = portletContext;
		this.containerRuntimeOptions = new HashMap<String, String[]>();
		this.initParameters = new HashMap<String,String>();
	}

	/* (non-Javadoc)
	 * @see javax.portlet.PortletConfig#getPortletName()
	 */
	
	public String getPortletName() {
		//TODO - read this from the portlet.xml file
		if(portletName == null || portletName.equals("")){
			return "Mock Portlet";
		}
		return portletName;
	}

	/* (non-Javadoc)
	 * @see javax.portlet.PortletConfig#getPortletContext()
	 */
	
	public PortletContext getPortletContext() {
		return portletContext;
	}

	/* (non-Javadoc)
	 * @see javax.portlet.PortletConfig#getResourceBundle(java.util.Locale)
	 */
	
	public ResourceBundle getResourceBundle(Locale locale) {
		if( bundleBaseName == null || bundleBaseName.equals("") ) {
			return new ResourceBundle(){
				public Enumeration<String> getKeys() { return Collections.enumeration(new ArrayList<String>()); }
			    protected Object handleGetObject(String key) { return ""; }
			    public String toString() { return "NONEXISTENT_BUNDLE"; }
			};
		}
		return ResourceBundle.getBundle(bundleBaseName,locale);
	}

	/* (non-Javadoc)
	 * @see javax.portlet.PortletConfig#getInitParameter(java.lang.String)
	 */
	
	public String getInitParameter(String name) {
		if( name == null ) {
			throw new IllegalArgumentException("name cannot be null");
		}
		//TODO - read from portlet.xml
		return initParameters.get(name);
	}
	
	public void setInitParameters( Map<String,String> map ) {
		if( map == null ){
			throw new IllegalArgumentException("null is not acceptable");
		}
		initParameters = map;
	}
	

	/* (non-Javadoc)
	 * @see javax.portlet.PortletConfig#getInitParameterNames()
	 */
	
	public Enumeration<String> getInitParameterNames() {
		//TODO - read from portlet.xml
		return Collections.enumeration(initParameters.keySet());
	}

	/* (non-Javadoc)
	 * @see javax.portlet.PortletConfig#getPublicRenderParameterNames()
	 */
	
	public Enumeration<String> getPublicRenderParameterNames() {
		/* The getPublicRenderParameterNames method of the PortletConfig interface returns
		 * the public render parameter names found in the portlet definition in the deployment
		 * descriptor with the supported-public-render-parameter element or an empty
		 * enumeration if no public render parameters are defined for the current portlet definition.
		 * */
		//TODO - read from the portlet.xml
		return Collections.enumeration(new ArrayList<String>());
	}

	/* (non-Javadoc)
	 * @see javax.portlet.PortletConfig#getDefaultNamespace()
	 */
	
	public String getDefaultNamespace() {
		// TODO - Get from the portlet.xml
		if( defaultNamespace == null ) {
			return XMLConstants.NULL_NS_URI;
		}
		return defaultNamespace;
	}

	/* (non-Javadoc)
	 * @see javax.portlet.PortletConfig#getPublishingEventQNames()
	 */
	
	public Enumeration<QName> getPublishingEventQNames() {
		/* The getPublishingEventQNames method of the PortletConfig interface returns the
		 * publishing event QNames found in the portlet definition in the deployment descriptor
		 * with the supported-publishing-event element or an empty enumeration if no
		 * publishing events are defined for the current portlet definition.
		 * If the event was defined using the name element instead of the qname element the defined
		 * default namespace must be added as namespace for the returned QName.*/
		//TODO - read from the portlet.xml
		return Collections.enumeration(new ArrayList<QName>());
	}

	/* (non-Javadoc)
	 * @see javax.portlet.PortletConfig#getProcessingEventQNames()
	 */
	
	public Enumeration<QName> getProcessingEventQNames() {
		//Similar to above
		//TODO - read from the portlet.xml
		return Collections.enumeration(new ArrayList<QName>());
	}

	/* (non-Javadoc)
	 * @see javax.portlet.PortletConfig#getSupportedLocales()
	 */
	
	public Enumeration<Locale> getSupportedLocales() {
		//TODO - read from the portlet.xml
		return Collections.enumeration(new ArrayList<Locale>());
	}

	/* (non-Javadoc)
	 * @see javax.portlet.PortletConfig#getContainerRuntimeOptions()
	 */
	
	public Map<String, String[]> getContainerRuntimeOptions() {
		/* The getContainerRuntimeOptions method returns an immutable Map containing
		 * portlet application level container runtime options merged with the portlet level container
		 * runtime options, containing the names as keys and the container runtime values as map
		 * values, or an empty Map if no portlet application level or portlet level container runtime
		 * options are set in the portlet.xml or supported by this portlet container. The map
		 * returned from getContainerRuntimeOptions will provide the subset the portlet
		 * container supports of the options the portlet has specified in the portlet deployment
		 * descriptor. The keys in the map are of type String. The values in the map are of type
		 * String array. If a container runtime option is set on the portlet application level and on the
		 * portlet level with the same name the setting on the portlet level takes precedence and
		 * overwrites the one set on the portal application level.
		 * */
		//TODO - read from portlet.xml
		return containerRuntimeOptions;
	}
	
	public void setBundleBaseName(String baseName){
		this.bundleBaseName = baseName;
	}

}
