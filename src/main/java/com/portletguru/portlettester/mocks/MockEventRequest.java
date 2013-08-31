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
	
	protected String method;
	protected Event event;

	public MockEventRequest(PortalContext portalContext,
			PortletContext portletContext, PortletStatus portletStatus) {
		super(portalContext, portletContext, portletStatus);
		this.method = "GET";
	}

	/* (non-Javadoc)
	 * @see javax.portlet.EventRequest#getEvent()
	 * Always return a MockEvent object
	 */
	
	public Event getEvent() {
		if(event == null) {
			event = new MockEvent();
		}
		return event;
	}

	/* (non-Javadoc)
	 * @see javax.portlet.EventRequest#getMethod()
	 */
	
	public String getMethod() {
		return method;
	}

	/* (non-Javadoc)
	 * @see com.portletmanic.portlettester.mocks.MockPortletRequest#getLifeCycle()
	 */
	
	public String getLifeCycle() {
		return PortletRequest.EVENT_PHASE;
	}

	
}
