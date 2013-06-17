/**
 * 
 */
package com.portletguru.portlettester.mocks;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.portlet.PortletMode;
import javax.portlet.PortletModeException;
import javax.portlet.PortletRequest;
import javax.portlet.StateAwareResponse;
import javax.portlet.WindowState;
import javax.portlet.WindowStateException;
import javax.xml.namespace.QName;

import com.portletguru.portlettester.PortletStatus;

/**
 * @author Derek Linde Li
 *
 */
public abstract class MockStateAwareResponse extends MockPortletResponse implements
		StateAwareResponse {
	
	private PortletStatus portletStatus;
	private Map<String,String[]> renderParamenterMap;
	private Map<QName,Serializable> triggeredEvents;
	private String redirectURL;
	private boolean isRenderParematerCalled;
	private PortletRequest portletRequest;
	
	public MockStateAwareResponse(PortletStatus portletStatus, PortletRequest portletRequest) {
		this.portletStatus = portletStatus;
		this.renderParamenterMap = new HashMap<String, String[]>();
		this.triggeredEvents = new LinkedHashMap<QName, Serializable>();
		this.portletRequest = portletRequest;
	}

	/* (non-Javadoc)
	 * @see javax.portlet.StateAwareResponse#getRenderParameterMap()
	 */
	
	public Map<String, String[]> getRenderParameterMap() {
		return renderParamenterMap;
	}

	/* (non-Javadoc)
	 * @see javax.portlet.StateAwareResponse#getPortletMode()
	 */
	
	public PortletMode getPortletMode() {
		return portletStatus.getPortletMode();
	}

	/* (non-Javadoc)
	 * @see javax.portlet.StateAwareResponse#getWindowState()
	 */
	
	public WindowState getWindowState() {	
		return portletStatus.getWindowState();
	}

	/* (non-Javadoc)
	 * @see javax.portlet.StateAwareResponse#removePublicRenderParameter(java.lang.String)
	 */
	
	public void removePublicRenderParameter(String name) {
		if(redirectURL != null){
			throw new IllegalStateException("redirect URL is already set");
		}
		if(name == null) {
			throw new IllegalArgumentException("name cannot be null");
		}
		renderParamenterMap.remove(name);
		isRenderParematerCalled = true;
	}

	/* (non-Javadoc)
	 * @see javax.portlet.StateAwareResponse#setEvent(javax.xml.namespace.QName, java.io.Serializable)
	 */
	
	public void setEvent(QName arg0, Serializable arg1) {
		if(arg0 == null) {
			triggeredEvents.put(arg0, arg1);
		}
	}

	/* (non-Javadoc)
	 * @see javax.portlet.StateAwareResponse#setWindowState(javax.portlet.WindowState)
	 */
	
	public void setWindowState(WindowState windowState)
			throws WindowStateException {
		if(redirectURL != null){
			throw new IllegalStateException("redirect URL is already set");
		}
		if(!portletRequest.isWindowStateAllowed(windowState)){
			throw new WindowStateException("Unsupported state: " + windowState.toString(), windowState);
		}
		portletStatus.setWindowState(windowState);
		isRenderParematerCalled = true;
	}

	/* (non-Javadoc)
	 * @see javax.portlet.StateAwareResponse#setPortletMode(javax.portlet.PortletMode)
	 */
	
	public void setPortletMode(PortletMode portletMode)
			throws PortletModeException {
		if(redirectURL != null){
			throw new IllegalStateException("redirect URL is already set");
		}
		if(!portletRequest.isPortletModeAllowed(portletMode)){
			throw new PortletModeException("Unsupported mode:" + portletMode.toString(), portletMode);
		}
		portletStatus.setPortletMode(portletMode);
		isRenderParematerCalled = true;
	}

	/* (non-Javadoc)
	 * @see javax.portlet.StateAwareResponse#setRenderParameters(java.util.Map)
	 */
	
	public void setRenderParameters(Map<String, String[]> parameters) {
		if(redirectURL != null){
			throw new IllegalStateException("redirect URL is already set");
		}
		if(parameters == null){
			throw new IllegalArgumentException("parameters cannot be null");
		}
		renderParamenterMap = parameters;
		isRenderParematerCalled = true;
	}

	/* (non-Javadoc)
	 * @see javax.portlet.StateAwareResponse#setRenderParameter(java.lang.String, java.lang.String)
	 */
	
	public void setRenderParameter(String key, String value) {
		if(redirectURL != null){
			throw new IllegalStateException("redirect URL is already set");
		}
		if(key == null) {
			throw new IllegalArgumentException("key cannot be null");
		}
		renderParamenterMap.put(key, new String[]{value});
		isRenderParematerCalled = true;
	}

	/* (non-Javadoc)
	 * @see javax.portlet.StateAwareResponse#setRenderParameter(java.lang.String, java.lang.String[])
	 */
	
	public void setRenderParameter(String key, String[] values) {
		if(redirectURL != null){
			throw new IllegalStateException("redirect URL is already set");
		}
		if(key == null) {
			throw new IllegalArgumentException("key cannot be null");
		}
		renderParamenterMap.put(key, values);
		isRenderParematerCalled = true;
	}

	/* (non-Javadoc)
	 * @see javax.portlet.StateAwareResponse#setEvent(java.lang.String, java.io.Serializable)
	 */
	
	public void setEvent(String name, Serializable value) {
		triggeredEvents.put(new QName(getNamespace(), name), value);
	}
	
	protected void setRedirectURL(String redirectURL){
		this.redirectURL = redirectURL;
	}
	
	protected boolean isRenderParameterCalled(){
		return isRenderParematerCalled;
	}

}
