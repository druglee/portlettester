/**
 * 
 */
package com.portletguru.portlettester.mocks;

import javax.portlet.PortalContext;
import javax.portlet.PortletContext;
import javax.portlet.ResourceRequest;

import com.portletguru.portlettester.PortletStatus;

/**
 * @author Derek Linde Li
 *
 */
public class ResourceRequestGenerator extends ClientDataRequestGenerator{

	public ResourceRequestGenerator(PortalContext portalContext,
			PortletContext portletContext, PortletStatus portletStatus) {
		super(portalContext, portletContext, portletStatus);
		this.portletRequest = new MockResourceRequest(portalContext, portletContext, portletStatus);
	}
	
	/**
	 * 
	 * @param resourceID
	 */
	public void setResourceID(String resourceID) {
		getResourceRequest().resourceID = resourceID;
	}
	
	/**
	 * 
	 * @param cacheability
	 */
	public void setCacheability(String cacheability) {
		getResourceRequest().cacheability = cacheability;
	}
	
	/**
	 * 
	 * @param etag
	 */
	public void setETag(String etag) {
		getResourceRequest().etag = etag;
	}
	
	/**
	 * 
	 * @param name
	 * @param values
	 */
	public void addPrivateRenderParameter(String name, String[] values) {
		getResourceRequest().privateRenderParameterMap.put(name, values);
	}
	
	private MockResourceRequest getResourceRequest() {
		return (MockResourceRequest) portletRequest;
	}

	@Override
	public ResourceRequest generateRequest() {
		return (ResourceRequest) portletRequest;
	}

}
