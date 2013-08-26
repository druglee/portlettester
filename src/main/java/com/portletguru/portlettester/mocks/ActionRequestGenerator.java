/**
 * 
 */
package com.portletguru.portlettester.mocks;

import javax.portlet.ActionRequest;
import javax.portlet.PortalContext;
import javax.portlet.PortletContext;

import com.portletguru.portlettester.PortletStatus;

/**
 * Helper class to generate a ActionRequest object
 * 
 * @author Derek Linde Li
 *
 */
public class ActionRequestGenerator extends ClientDataRequestGenerator {
	
	private MockActionRequest actionRequest;
	
	public ActionRequestGenerator(PortalContext portalContext, PortletContext portletContext, PortletStatus portletStatus) {
		this.actionRequest = new MockActionRequest(portalContext, portletContext, portletStatus);
	}

	/* attributes */
	public void setAttribute(String key, Object value) {
		this.actionRequest.setAttribute(key, value);
	}
	
	public ActionRequest generateActionRequest() {
		return actionRequest;
	}
	
	/* parameters */
	public void setParameter(String key, String value) {
		this.actionRequest.setParameter(key, new String[]{value});
	}
}
