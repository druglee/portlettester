package com.portletguru.portlettester.mocks;

import javax.portlet.PortalContext;
import javax.portlet.PortletContext;
import javax.portlet.PortletRequest;
import javax.portlet.RenderRequest;

import com.portletguru.portlettester.PortletStatus;

public class MockRenderRequest extends MockPortletRequest implements RenderRequest {
	
	
	/**
	 * Constructor
	 * @param portalContext
	 * @param portletContext
	 * @param portletStatus
	 */
	public MockRenderRequest(PortalContext portalContext,
			PortletContext portletContext, PortletStatus portletStatus) {
		super(portalContext, portletContext, portletStatus);
	}
	
	
	public Object getAttribute(String name) {
		/* According to JSR286: 
		 * The main intent of this attribute is to allow frameworks implemented 
		 * on top of the Java Portlet Specification to perform the correct type 
		 * casts from the PortletRequest/PortletResponse to a specific 
		 * request/response pair
		 * */
		if ( PortletRequest.LIFECYCLE_PHASE.equals(name) ) {
			return PortletRequest.RENDER_PHASE;
		}
		return super.getAttribute(name);
	}

	
	public String getETag() {
		//TODO - To be researched.
		return null;
	}

	
	public String getLifeCycle() {
		return PortletRequest.RENDER_PHASE;
	}
	
}
