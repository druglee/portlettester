/**
 * 
 */
package com.portletguru.portlettester.mocks;

import javax.portlet.Event;
import javax.portlet.EventRequest;
import javax.portlet.PortalContext;
import javax.portlet.PortletContext;
import javax.portlet.PortletRequest;

import com.portletguru.portlettester.PortletStatus;

/**
 * @author Derek Linde Li
 *
 */
public class MockEventRequest extends MockPortletRequest implements
		EventRequest {

	public MockEventRequest(PortalContext portalContext,
			PortletContext portletContext, PortletStatus portletStatus) {
		super(portalContext, portletContext, portletStatus);
	}

	/* (non-Javadoc)
	 * @see javax.portlet.EventRequest#getEvent()
	 * Always return a MockEvent object
	 */
	
	public Event getEvent() {
		return new MockEvent();
	}

	/* (non-Javadoc)
	 * @see javax.portlet.EventRequest#getMethod()
	 */
	
	public String getMethod() {
		return "POST";
	}

	/* (non-Javadoc)
	 * @see com.portletmanic.portlettester.mocks.MockPortletRequest#getLifeCycle()
	 */
	
	public String getLifeCycle() {
		return PortletRequest.EVENT_PHASE;
	}

	
}
