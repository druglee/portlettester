package com.portletguru.portlettester.mocks;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;

import javax.portlet.PortalContext;
import javax.portlet.PortletContext;
import javax.portlet.PortletMode;
import javax.portlet.PortletPreferences;
import javax.portlet.PortletRequest;
import javax.portlet.PortletSession;
import javax.portlet.WindowState;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import com.portletguru.portlettester.PortletStatus;
import com.portletguru.portlettester.utils.Constants;

public abstract class MockPortletRequest implements PortletRequest {
	
	protected PortletSession portletSession;
	protected PortalContext portalContext;
	protected PortletContext portletContext;
	protected String remoteUser;
	protected Principal userPrincipal;
	protected Locale locale;
	protected PortletStatus portletStatus;
	
	/* normally these should be in HttpServletRequest */
	protected String authType;
	protected List<String> roles;
	protected Map<String,Object> attributes;
	protected Map<String,String[]> parameters;
	protected boolean secured;
	protected List<Locale> locales;
	protected List<Cookie> cookies;
	
	protected String requestedSessionId;
	protected boolean sessionIdValid;
	protected String scheme;
	protected String serverName;
	protected int serverPort;
	protected String responseContentType;
	protected List<String> responseContentTypes;
	
	private Map<String, String[]> publicRenderParameters;
	
	public MockPortletRequest(PortalContext portalContext, PortletContext portletContext,
		PortletStatus portletStatus	) {
		this.portalContext = portalContext;
		this.portletContext = portletContext;
		this.portletStatus = portletStatus;
		this.remoteUser = "tester";
		this.userPrincipal = new MockPrinciple();
		
		this.authType = HttpServletRequest.BASIC_AUTH;
		this.roles = new LinkedList<String>();
		this.attributes = new HashMap<String, Object>();
		this.parameters = new HashMap<String, String[]>();
		this.locales = new LinkedList<Locale>();
		this.cookies = new LinkedList<Cookie>();
		
		this.sessionIdValid = true;
		this.requestedSessionId = "sessionId";
		this.scheme = "http";
		this.serverName = "portletguru";
		this.serverPort = 8080;
		
		this.responseContentType = Constants.TEXT_HTML;
		this.responseContentTypes = new LinkedList<String>();
		
		Map<String, String[]> map = new HashMap<String, String[]>();
		for(Entry<String, String> entry : portletStatus.getPublicParameterMap().entrySet()) {
			map.put(entry.getKey(), new String[]{entry.getValue()});
		}
		publicRenderParameters = Collections.unmodifiableMap(map);
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
		return getPortletSession(true);
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
		return authType;
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
		return roles.contains(role);
	}

	
	public Object getAttribute(String name) {
		/* According to JSR286 we must return a CCPP profile if the attribute 
		 * name equals PortletRequest.CCPP_PROFILE */
		if( PortletRequest.CCPP_PROFILE.equals(name) ) {
			//TODO - Make this better
			return new MockProfile();
		} else if ( PortletRequest.LIFECYCLE_PHASE.equals(name) ) {
			/* According to JSR286: 
			 * The main intent of this attribute is to allow frameworks implemented 
			 * on top of the Java Portlet Specification to perform the correct type 
			 * casts from the PortletRequest/PortletResponse to a specific 
			 * request/response pair
			 * */
			return getLifeCycle();
		}
		return attributes.get(name);
	}

	
	public Enumeration<String> getAttributeNames() {		
		return Collections.enumeration(attributes.keySet());
	}

	
	public String getParameter(String name) {
		String[] values = parameters.get(name);
		if(values != null) {
			return values[0];
		}
		return null;
	}

	
	public Enumeration<String> getParameterNames() {
		return Collections.enumeration(parameters.keySet());
	}

	
	public String[] getParameterValues(String name) {		
		return parameters.get(name);
	}

	
	public Map<String, String[]> getParameterMap() {		
		return Collections.unmodifiableMap( parameters );
	}

	
	public boolean isSecure() {		
		return secured;
	}

	
	public void setAttribute(String name, Object o) {
		if(name == null){
			throw new IllegalArgumentException("name cannot be null");
		}
		attributes.put(name, o);
	}

	
	public void removeAttribute(String name) {
		attributes.remove(name);
	}

	
	public String getRequestedSessionId() {
		return requestedSessionId;
	}

	
	public boolean isRequestedSessionIdValid() {
		return sessionIdValid;
	}

	
	public String getResponseContentType() {
		/*
		 * Portlet developers may code portlets to support multiple content types. A portlet can
		 * obtain, using the getResponseContentType method of the request object, a string
		 * representing the default content type the portlet container assumes for the output.
		 * */
		return responseContentType;
	}

	
	public Enumeration<String> getResponseContentTypes() {
		/*
		 * If the portlet container supports additional content types for the portlet��s output, it must
		   declare the additional content types through the getResponseContentTypes method of
		   the request object. The returned Enumeration of strings should contain the content types
		   the portlet container supports in order of preference. The first element of the enumeration
		   must be the same content type returned by the getResponseContentType method.
		 * */
		responseContentTypes.add(getResponseContentType());
		return Collections.enumeration(responseContentTypes);
	}

	
	public Locale getLocale() {
		if (locale == null) {
			locale = locales.get(0);
		}
		if (locale == null) {
			locale = new Locale("en");
		}
		return locale;
	}

	
	public Enumeration<Locale> getLocales() {		
		return Collections.enumeration(locales);
	}

	
	public String getScheme() {		
		return scheme;
	}

	
	public String getServerName() {
		return serverName;
	}

	
	public int getServerPort() {		
		return serverPort;
	}

	
	public String getWindowID() {		
		return this.portletStatus.getWindowID();
	}

	
	public Cookie[] getCookies() {		
		return cookies.toArray(new Cookie[]{});
	}

	
	public Map<String, String[]> getPrivateParameterMap() {		
		//TODO - To be researched
		return this.portletStatus.getPrivateParameterMap();
	}

	
	public Map<String, String[]> getPublicParameterMap() {
		// Defined in supported-public-render-parameter of the portlet.xml
		return publicRenderParameters;
	}
	
	/**
	 * Return the name of the life cycle the request is in.
	 * All the subclass of PortletRequest should provide their
	 * own name.
	 * @return the name of the life cycle the portlet is in.
	 */
	public abstract String getLifeCycle();

}
