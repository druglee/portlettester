/**
 * 
 */
package com.portletguru.portlettester.mocks;

import javax.portlet.EventRequest;
import javax.portlet.EventResponse;
import javax.portlet.PortletRequest;

import com.portletguru.portlettester.PortletStatus;

/**
 * @author Derek Linde Li
 *
 */
public class MockEventResponse extends MockStateAwareResponse implements
		EventResponse {


	public MockEventResponse(PortletStatus portletStatus,
			PortletRequest portletRequest) {
		super(portletStatus, portletRequest);
	}

	/* (non-Javadoc)
	 * @see javax.portlet.EventResponse#setRenderParameters(javax.portlet.EventRequest)
	 */
	
	public void setRenderParameters(EventRequest request) {
		//TODO: To be research, not really understand what is used for
	}

	
	protected String getLifeCycle() {
		return PortletRequest.EVENT_PHASE;
	}

}
