package com.portletguru.portlettester.mocks;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.portlet.PortalContext;
import javax.portlet.PortletContext;
import javax.portlet.PortletMode;
import javax.portlet.PortletPreferences;
import javax.portlet.PortletRequest;
import javax.portlet.PortletSession;
import javax.portlet.WindowState;
import javax.servlet.http.Cookie;

import com.portletguru.portlettester.PortletStatus;
import com.portletguru.portlettester.mocks.http.MockHttpServletRequest;
import com.portletguru.portlettester.utils.Constants;

public abstract class MockPortletRequest implements PortletRequest {
	
	private PortletSession portletSession;
	private PortalContext portalContext;
	private MockHttpServletRequest request;
	private PortletContext portletContext;
	private String remoteUser;
	private Principal userPrincipal;
	private Locale locale;
	private PortletStatus portletStatus;
	
	public MockPortletRequest(PortalContext portalContext, PortletContext portletContext,
		PortletStatus portletStatus	) {
		this.portalContext = portalContext;
		this.portletContext = portletContext;
		this.portletStatus = portletStatus;
		this.request = new MockHttpServletRequest();
		this.remoteUser = "tester";
		this.userPrincipal = new MockPrinciple();
	}

	
	public boolean isWindowStateAllowed(WindowState state) {
		Enumeration<WindowState> supportedState = portalContext.getSupportedWindowStates();
		while(supportedState.hasMoreElements()){
			if( supportedState.nextElement().equals(state) ) {
				return true;
			}
		}
		return false;
	}

	
	public boolean isPortletModeAllowed(PortletMode mode) {
		Enumeration<PortletMode> supportedModes = portalContext.getSupportedPortletModes();
		while( supportedModes.hasMoreElements() ){
			if( supportedModes.nextElement().equals(mode) ) {
				return true;
			}
		}
		return false;
	}

	
	public PortletMode getPortletMode() {		
		return portletStatus.getPortletMode();
	}

	
	public WindowState getWindowState() {		
		return portletStatus.getWindowState();
	}

	
	public PortletPreferences getPreferences() {
		return portletStatus.getPreferences(this);
	}

	
	public PortletSession getPortletSession() {		
		if( portletSession == null){
			portletSession = new MockPortletSession(portletContext);
		}
		return portletSession;
	}

	
	public PortletSession getPortletSession(boolean create) {
		if(create && portletSession == null){
			portletSession = new MockPortletSession(portletContext);
		}
		return portletSession;
	}

	
	public String getProperty(String name) {		
		return portalContext.getProperty(name);
	}

	
	public Enumeration<String> getProperties(String name) {		
		List<String> values = new ArrayList<String>();
		String value = portalContext.getProperty(name);
		if (value != null) {
			values.add(value);
		}
		return Collections.enumeration(values);
	}

	
	public Enumeration<String> getPropertyNames() {		
		return portalContext.getPropertyNames();
	}

	
	public PortalContext getPortalContext() {		
		return portalContext;
	}

	
	public String getAuthType() {
		/*
		 * The getAuthType indicates the authentication scheme being used between the user and
		 * the portal. It may return one of the defined constants (BASIC_AUTH, DIGEST_AUTH,
		 * CERT_AUTH and FORM_AUTH) or another String value that represents a vendor provided
		 * authentication type. If the user is not authenticated the getAuthType method must return
		 * null
		 * */
		return request.getAuthType();
	}

	
	public String getContextPath() {
		/* According to JSR286 
		 * The context path of a request is exposed via the request object. 
		 * The context path is the path prefix associated with the deployed portlet 
		 * application. If the portlet application is rooted at the base of the
		 * web server URL namespace (also known as "default" context), 
		 * this path must be an empty string. Otherwise, it must be the path 
		 * the portlet application is rooted to, the path must start with a '/' 
		 * and it must not end with a '/' character.
		 * */
		return Constants.SLASH + portletContext.getPortletContextName();
	}

	
	public String getRemoteUser() {
		//returns the login name of the user making this request.
		return remoteUser;
	}

	
	public Principal getUserPrincipal() {		
		return userPrincipal;
	}

	
	public boolean isUserInRole(String role) {
		return request.isUserInRole(role);
	}

	
	public Object getAttribute(String name) {
		/* According to JSR286 we must return a CCPP profile if the attribute 
		 * name equals PortletRequest.CCPP_PROFILE */
		if( PortletRequest.CCPP_PROFILE.equals(name) ) {
			//TODO - Make this better
			return new MockProfile();
		} else if ( PortletRequest.LIFECYCLE_PHASE.equals(name) ) {
			return getLifeCycle();
		}
		return request.getAttribute(name);
	}

	
	public Enumeration<String> getAttributeNames() {		
		return request.getAttributeNames();
	}

	
	public String getParameter(String name) {		
		return request.getParameter(name);
	}

	
	public Enumeration<String> getParameterNames() {
		return request.getParameterNames();
	}

	
	public String[] getParameterValues(String name) {		
		return request.getParameterValues(name);
	}

	
	public Map<String, String[]> getParameterMap() {		
		return Collections.unmodifiableMap( request.getParameterMap() );
	}

	
	public boolean isSecure() {		
		return request.isSecure();
	}

	
	public void setAttribute(String name, Object o) {
		request.setAttribute(name, o);
	}

	
	public void removeAttribute(String name) {
		request.removeAttribute(name);
	}

	
	public String getRequestedSessionId() {
		return request.getRequestedSessionId();
	}

	
	public boolean isRequestedSessionIdValid() {
		return request.isRequestedSessionIdValid();
	}

	
	public String getResponseContentType() {
		/*
		 * Portlet developers may code portlets to support multiple content types. A portlet can
		 * obtain, using the getResponseContentType method of the request object, a string
		 * representing the default content type the portlet container assumes for the output.
		 * */
		return Constants.TEXT_HTML;
	}

	
	public Enumeration<String> getResponseContentTypes() {
		/*
		 * If the portlet container supports additional content types for the portlet��s output, it must
		   declare the additional content types through the getResponseContentTypes method of
		   the request object. The returned Enumeration of strings should contain the content types
		   the portlet container supports in order of preference. The first element of the enumeration
		   must be the same content type returned by the getResponseContentType method.
		 * */
		List<String> responseContentTypes = new ArrayList<String>();
		responseContentTypes.add(getResponseContentType());
		return Collections.enumeration(responseContentTypes);
	}

	
	public Locale getLocale() {
		if (locale == null) {
			locale = request.getLocale();
		}
		if (locale == null) {
			locale = new Locale("en");
		}
		return locale;
	}

	
	public Enumeration<Locale> getLocales() {		
		return request.getLocales();
	}

	
	public String getScheme() {		
		return request.getScheme();
	}

	
	public String getServerName() {
		return this.request.getServerName();
	}

	
	public int getServerPort() {		
		return this.request.getServerPort();
	}

	
	public String getWindowID() {		
		return this.portletStatus.getWindowID();
	}

	
	public Cookie[] getCookies() {		
		return this.request.getCookies();
	}

	
	public Map<String, String[]> getPrivateParameterMap() {		
		//TODO - To be researched
		return this.portletStatus.getPrivateParameterMap();
	}

	
	public Map<String, String[]> getPublicParameterMap() {
		//TODO - To be researched
		return this.portletStatus.getPublicParameterMap();
	}
		
	/**
	 * This is not a method from the PortletRequest interface, this allows
	 *  the user to set up the require parameters
	 * @param name
	 * @param value
	 */
	public void setParameter(String name, String[] value){
		if( name == null ) {
			throw new IllegalArgumentException("parameter name cannot be null");
		}
		request.setParameter(name, value);
	}
	
	/**
	 * Return the name of the life cycle the request is in.
	 * All the subclass of PortletRequest should provide their
	 * own name.
	 * @return the name of the life cycle the portlet is in.
	 */
	public abstract String getLifeCycle();

}
