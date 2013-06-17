/**
 * 
 */
package com.portletguru.portlettester.mocks;

import javax.portlet.ActionRequest;
import javax.portlet.PortalContext;
import javax.portlet.PortletContext;
import javax.portlet.PortletRequest;

import com.portletguru.portlettester.PortletStatus;

/**
 * @author Derek Linde Li
 *
 */
public class MockActionRequest extends MockClientDataRequest implements
		ActionRequest {

	public MockActionRequest(PortalContext portalContext,
			PortletContext portletContext, PortletStatus portletStatus) {
		super(portalContext, portletContext, portletStatus);
	}

	@Override
	public String getLifeCycle() {
		return PortletRequest.ACTION_PHASE;
	}
	


}
