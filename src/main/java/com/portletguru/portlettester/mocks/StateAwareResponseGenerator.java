/**
 * 
 */
package com.portletguru.portlettester.mocks;

import javax.portlet.PortletRequest;
import javax.portlet.StateAwareResponse;

import com.portletguru.portlettester.PortletStatus;


/**
 * @author Derek Linde Li
 *
 */
public abstract class StateAwareResponseGenerator extends PortletResponseGenerator<StateAwareResponse> {

	public StateAwareResponseGenerator(PortletStatus portletStatus, PortletRequest portletRequest) {
		
	}
}
