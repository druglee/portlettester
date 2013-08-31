/**
 * 
 */
package com.portletguru.portlettester.mocks;

import java.util.List;
import java.util.Locale;

import javax.portlet.PortalContext;
import javax.portlet.PortletContext;
import javax.portlet.PortletRequest;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import com.portletguru.portlettester.PortletStatus;

/**
 * @author Derek Linde Li
 *
 */
public abstract class PortletRequestGenerator<T extends PortletRequest> {

	protected MockPortletRequest portletRequest;
	
	public PortletRequestGenerator(PortalContext portalContext, PortletContext portletContext, PortletStatus portletStatus) {
		
	}

	/**
	 * 
	 * @param key
	 * @param value
	 */
	public void setAttribute(String key, Object value) {
		this.portletRequest.setAttribute(key, value);
	}
	
	/**
	 * 
	 * @param key
	 * @param value
	 */
	public void setParameter(String key, String value) {
		if( key == null ) {
			throw new IllegalArgumentException("parameter name cannot be null");
		}
		portletRequest.parameters.put(key, new String[]{value});
	}
	
	/**
	 * 
	 * @param roles
	 */
	public void addRoles(List<String> roles) {
		portletRequest.roles.addAll(roles);
	}
	
	/**
	 * 
	 * @param authType
	 */
	public void setAuthType(String authType) {
		if(HttpServletRequest.BASIC_AUTH.equals(authType) || HttpServletRequest.CLIENT_CERT_AUTH.equals(authType) 
				|| HttpServletRequest.DIGEST_AUTH.equals(authType) || HttpServletRequest.FORM_AUTH.equals(authType)) {
			portletRequest.authType = authType;
		} else {
			throw new IllegalArgumentException("auth type must be one of BASIC, CLIENT_CERT, DIGEST, FORM");
		}
	}
	
	/**
	 * 
	 * @param secured
	 */
	public void setSecured(boolean secured) {
		portletRequest.secured = secured;
	}
	
	/**
	 * 
	 * @param locale
	 */
	public void setLocale(Locale locale) {
		portletRequest.locale = locale;
	}
	
	/**
	 * 
	 * @param locales
	 */
	public void addLocales(List<Locale> locales) {
		portletRequest.locales.addAll(locales);
	}
	
	/**
	 * 
	 * @param cookies
	 */
	public void addCookies(List<Cookie> cookies) {
		portletRequest.cookies.addAll(cookies);
	}
	
	/**
	 * 
	 * @param valid
	 */
	public void setSessionIdValid(boolean valid) {
		portletRequest.sessionIdValid = valid;
	}
	
	/**
	 * 
	 * @param contentType
	 */
	public void setResponseContentType(String contentType) {
		portletRequest.responseContentType = contentType;
	}
	
	/**
	 * 
	 * @param contentType
	 */
	public void addResponseContentType(String contentType) {
		portletRequest.responseContentTypes.add(contentType);
	}

	/**
	 * 
	 * @return
	 */
	public abstract T generateRequest();
}
