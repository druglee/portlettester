/**
 * 
 */
package com.portletguru.portlettester.mocks;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.portlet.PortletMode;
import javax.portlet.PortletModeException;
import javax.portlet.PortletURL;
import javax.portlet.WindowState;
import javax.portlet.WindowStateException;

/**
 * @author Derek Linde Li
 *
 */
public class MockPortletURL extends MockBaseURL implements PortletURL {
	
	private WindowState windowState;
	private PortletMode portletMode;
	private Map<String,String> publicRenderParameters;
	
	public MockPortletURL(Map<String,String[]> publicRenderParameters) {
		// make a copy so that we don't modify the original one
		this.publicRenderParameters = new HashMap<String, String>();
		for(Entry<String, String[]> entry : publicRenderParameters.entrySet()) {
			this.publicRenderParameters.put(entry.getKey(), entry.getValue()[0]);
		}
	}

	public void setWindowState(WindowState windowState)
			throws WindowStateException {
		// TODO: Check for supported states of current portlet
		this.windowState = windowState;
	}

	public void setPortletMode(PortletMode portletMode)
			throws PortletModeException {
		// TODO: Check for supported modes of current portlet
		this.portletMode = portletMode;
	}

	public PortletMode getPortletMode() {
		return portletMode;
	}

	public WindowState getWindowState() {
		return windowState;
	}

	public void removePublicRenderParameter(String name) {
		//TODO : Create a PortletApp class to store this info
		//Defined in public-render-parameter
		this.publicRenderParameters.remove(name);
	}

}
