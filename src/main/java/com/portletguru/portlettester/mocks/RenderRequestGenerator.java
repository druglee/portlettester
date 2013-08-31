/**
 * 
 */
package com.portletguru.portlettester.mocks;

import javax.portlet.PortalContext;
import javax.portlet.PortletContext;
import javax.portlet.RenderRequest;

import com.portletguru.portlettester.PortletStatus;

/**
 * @author Derek Linde Li
 *
 */
public class RenderRequestGenerator extends PortletRequestGenerator<RenderRequest> {

	public RenderRequestGenerator(PortalContext portalContext,
			PortletContext portletContext, PortletStatus portletStatus) {
		super(portalContext, portletContext, portletStatus);
		this.portletRequest = new MockRenderRequest(portalContext, portletContext, portletStatus);
	}
	
	/**
	 * 
	 * @param etag
	 */
	public void setETag(String etag) {
		((MockRenderRequest) portletRequest).etag = etag;
	}

	@Override
	public RenderRequest generateRequest() {
		return (RenderRequest) portletRequest;
	}

}
