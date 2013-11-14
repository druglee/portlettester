/**
 * 
 */
package com.portletguru.portlettester.mocks;

import javax.portlet.EventResponse;
import javax.portlet.PortletRequest;

import com.portletguru.portlettester.PortletStatus;
import com.portletguru.portlettester.PortletTester;

/**
 * @author Derek Linde Li
 *
 */
public class EventResponseGenerator extends StateAwareResponseGenerator {

	/**
	 * Constructor. Don't construct the generator yourself. Use 
	 * {@link PortletTester#getEventResponseGenerator()} instead.
	 * 
	 * @param portletStatus
	 * @param portletRequest
	 */
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
