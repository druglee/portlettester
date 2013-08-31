package com.portletguru.portlettester.mocks;

import javax.portlet.PortalContext;
import javax.portlet.PortletContext;
import javax.portlet.PortletRequest;
import javax.portlet.RenderRequest;

import com.portletguru.portlettester.PortletStatus;

public class MockRenderRequest extends MockPortletRequest implements RenderRequest {
	
	protected String etag;
	
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
	
	@Override
	public String getProperty(String name) {
		if(RenderRequest.ETAG.equals(name)) {
			return getETag();
		}
		return super.getProperty(name);
	}
	
	public String getETag() {
		return etag;
	}

	
	public String getLifeCycle() {
		return PortletRequest.RENDER_PHASE;
	}
	
}
