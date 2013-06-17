/**
 * 
 */
package com.portletguru.portlettester.mocks;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

import javax.portlet.PortalContext;
import javax.portlet.PortletMode;
import javax.portlet.WindowState;

/**
 * @author Derek Linde Li
 *
 */
public class MockPortalContext implements PortalContext {
	
	private Properties properties;
	private List<PortletMode> supportedPortletModes;
	private List<WindowState> supportedWindowStates;
	
	public MockPortalContext() {
		supportedPortletModes = new ArrayList<PortletMode>();
		supportedPortletModes.add(PortletMode.EDIT);
		supportedPortletModes.add(PortletMode.VIEW);
		supportedPortletModes.add(PortletMode.HELP);
		
		supportedWindowStates = new ArrayList<WindowState>();
		supportedWindowStates.add(WindowState.MAXIMIZED);
		supportedWindowStates.add(WindowState.MINIMIZED);
		supportedWindowStates.add(WindowState.NORMAL);
		
		properties = new Properties();
		// A non-null value of PortalContext.MARKUP_HEAD_ELEMENT_SUPPORT
		// indicates that we support MimeResponse.MARKUP_HEAD_ELEMENT
		// TODO - let the user to set this in the future
		properties.put(PortalContext.MARKUP_HEAD_ELEMENT_SUPPORT, "true");
	}

	/* (non-Javadoc)
	 * @see javax.portlet.PortalContext#getProperty(java.lang.String)
	 */
	
	public String getProperty(String name) {
		return properties.getProperty(name);
	}

	/* (non-Javadoc)
	 * @see javax.portlet.PortalContext#getPropertyNames()
	 */
	
	public Enumeration<String> getPropertyNames() {
		List<String> properties = new ArrayList<String>();
		Enumeration names = this.properties.propertyNames();
		while(names.hasMoreElements()){
			properties.add((String)names.nextElement());
		}
		return Collections.enumeration(properties);
	}

	/* (non-Javadoc)
	 * @see javax.portlet.PortalContext#getSupportedPortletModes()
	 */
	
	public Enumeration<PortletMode> getSupportedPortletModes() {
		return Collections.enumeration(supportedPortletModes);
	}

	/* (non-Javadoc)
	 * @see javax.portlet.PortalContext#getSupportedWindowStates()
	 */
	
	public Enumeration<WindowState> getSupportedWindowStates() {		
		return Collections.enumeration(supportedWindowStates);
	}

	/* (non-Javadoc)
	 * @see javax.portlet.PortalContext#getPortalInfo()
	 */
	
	public String getPortalInfo() {		
		return "PortletTesterPortal V0.1";
	}

}
