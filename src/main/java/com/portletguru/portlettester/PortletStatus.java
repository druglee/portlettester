/**
 * 
 */
package com.portletguru.portlettester;

import java.util.HashMap;
import java.util.Map;

import javax.portlet.PortletMode;
import javax.portlet.PortletPreferences;
import javax.portlet.PortletRequest;
import javax.portlet.WindowState;

import com.portletguru.portlettester.mocks.MockPortletPreferences;

/**
 * Represents the current status of the portlet being tested. Basically 
 * you should not use this class directly.
 * 
 * @author Derek Linde Li
 *
 */
public class PortletStatus {
	private PortletMode portletMode;
	private WindowState windowState;
	private PortletPreferences preferences;
	private DefaultPreferencesConfig defaultPreferencesConfig;
	private String windowID;
	//TODO - Find out what private parameter map is for
	//just put it here temporarily
	private Map<String,String[]> privateParameterMap;
	private Map<String,String> publicParameterMap;
	
	public PortletStatus(DefaultPreferencesConfig defaultPreferencesConfig){
		portletMode = PortletMode.VIEW;
		windowState = WindowState.NORMAL;
		windowID = "MockWindow";
		publicParameterMap = new HashMap<String, String>();
		privateParameterMap = new HashMap<String, String[]>();
		this.defaultPreferencesConfig = defaultPreferencesConfig; 
	}
	/**
	 * @return the portletMode
	 */
	public PortletMode getPortletMode() {
		return portletMode;
	}
	/**
	 * @param portletMode the portletMode to set
	 */
	public void setPortletMode(PortletMode portletMode) {
		this.portletMode = portletMode;
	}

	/**
	 * @return the windowState
	 */
	public WindowState getWindowState() {
		return windowState;
	}
	/**
	 * @param windowState the windowState to set
	 */
	public void setWindowState(WindowState windowState) {
		this.windowState = windowState;
	}
	/**
	 * @return the preferences
	 */
	public PortletPreferences getPreferences(PortletRequest request) {
		if(preferences == null) {
			preferences = new MockPortletPreferences(request, defaultPreferencesConfig.getDefaultPreferences(), defaultPreferencesConfig.getValidator() );
		}
		return preferences;
	}
	/**
	 * @param preferences the preferences to set
	 */
	public void setPreferences(PortletPreferences preferences) {
		this.preferences = preferences;
	}
	/**
	 * @return the windowID
	 */
	public String getWindowID() {
		return windowID;
	}
	/**
	 * @param windowID the windowID to set
	 */
	public void setWindowID(String windowID) {
		this.windowID = windowID;
	}
	/**
	 * @return the privateParameterMap
	 */
	public Map<String, String[]> getPrivateParameterMap() {
		return privateParameterMap;
	}
	/**
	 * @param privateParameterMap the privateParameterMap to set
	 */
	public void setPrivateParameterMap(Map<String, String[]> privateParameterMap) {
		this.privateParameterMap = privateParameterMap;
	}
	/**
	 * @return the publicParameterMap
	 */
	public Map<String, String> getPublicParameterMap() {
		return publicParameterMap;
	}
	/**
	 * @param publicParameterMap the publicParameterMap to set
	 */
	public void setPublicParameterMap(Map<String, String> publicParameterMap) {
		this.publicParameterMap = publicParameterMap;
	}
	
	
}
