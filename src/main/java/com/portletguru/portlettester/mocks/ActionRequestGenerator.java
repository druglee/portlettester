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
	
	public ActionRequestGenerator(PortalContext portalContext,
			PortletContext portletContext, PortletStatus portletStatus) {
		super(portalContext, portletContext, portletStatus);
		this.portletRequest = new MockActionRequest(portalContext, portletContext, portletStatus);
	}

	@Override
	public ActionRequest generateRequest() {
		return (ActionRequest) portletRequest;
	}


}
