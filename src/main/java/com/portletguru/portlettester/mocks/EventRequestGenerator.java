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
 * This class helps to create an EventRequest object
 * 
 * @author Derek Linde Li
 *
 */
public class EventRequestGenerator extends PortletRequestGenerator<EventRequest> {

	/**
	 * Constructor. Don't construct the generator yourself. Use 
	 * {@link PortletTester#getEventRequestGenerator()} instead.
	 * 
	 * @param portalContext
	 * @param portletContext
	 * @param portletStatus
	 */
	public EventRequestGenerator(PortalContext portalContext,
			PortletContext portletContext, PortletStatus portletStatus) {
		super(portalContext, portletContext, portletStatus);
		this.portletRequest = new MockEventRequest(portalContext, portletContext, portletStatus);
	}
	
	/**
	 * Specifies the method
	 * 
	 * @param method the method to specify
	 */
	public void setMethod(String method) {
		getEventRequest().method = method;
	}
	
	/**
	 * Specifies the Event object in this request
	 * 
	 * @param event the Event object to specify
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
