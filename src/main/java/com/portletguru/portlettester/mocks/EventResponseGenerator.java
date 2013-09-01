/**
 * 
 */
package com.portletguru.portlettester.mocks;

import javax.portlet.EventResponse;
import javax.portlet.PortletRequest;

import com.portletguru.portlettester.PortletStatus;

/**
 * @author Derek Linde Li
 *
 */
public class EventResponseGenerator extends StateAwareResponseGenerator {

	public EventResponseGenerator(PortletStatus portletStatus,
			PortletRequest portletRequest) {
		super(portletStatus, portletRequest);
		portletResponse = new MockEventResponse(portletStatus, portletRequest);
	}

	@Override
	public EventResponse generateResponse() {
		return (EventResponse) portletResponse;
	}

}
