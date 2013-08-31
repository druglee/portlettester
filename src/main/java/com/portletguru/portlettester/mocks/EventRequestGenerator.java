/**
 * 
 */
package com.portletguru.portlettester.mocks;

import javax.portlet.Event;
import javax.portlet.EventRequest;
import javax.portlet.PortalContext;
import javax.portlet.PortletContext;

import com.portletguru.portlettester.PortletStatus;

/**
 * @author Derek Linde Li
 *
 */
public class EventRequestGenerator extends PortletRequestGenerator<EventRequest> {

	public EventRequestGenerator(PortalContext portalContext,
			PortletContext portletContext, PortletStatus portletStatus) {
		super(portalContext, portletContext, portletStatus);
		this.portletRequest = new MockEventRequest(portalContext, portletContext, portletStatus);
	}
	
	/**
	 * 
	 * @param method
	 */
	public void setMethod(String method) {
		getEventRequest().method = method;
	}
	
	/**
	 * 
	 * @param event
	 */
	public void setEvent(Event event) {
		getEventRequest().event = event;
	}
	
	private MockEventRequest getEventRequest() {
		return (MockEventRequest) portletRequest;
	}

	@Override
	public EventRequest generateRequest() {
		return (EventRequest) portletRequest;
	}

}
