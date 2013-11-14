/**
 * 
 */
package com.portletguru.portlettester.mocks;

import javax.portlet.ActionRequest;
import javax.portlet.PortalContext;
import javax.portlet.PortletContext;

import com.portletguru.portlettester.PortletStatus;
import com.portletguru.portlettester.PortletTester;


/**
 * Helper class to generate an ActionRequest object
 * 
 * @author Derek Linde Li
 *
 */
public class ActionRequestGenerator extends ClientDataRequestGenerator {
	
	/**
	 * Constructor. Don't construct the generator yourself. Use 
	 * {@link PortletTester#getActionRequestGenerator()} instead.
	 * 
	 * @param portalContext
	 * @param portletContext
	 * @param portletStatus
	 */
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
