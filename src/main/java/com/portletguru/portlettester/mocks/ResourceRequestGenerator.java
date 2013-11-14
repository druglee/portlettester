/**
 * 
 */
package com.portletguru.portlettester.mocks;

import javax.portlet.PortalContext;
import javax.portlet.PortletContext;
import javax.portlet.ResourceRequest;

import com.portletguru.portlettester.PortletStatus;
import com.portletguru.portlettester.PortletTester;

/**
 * @author Derek Linde Li
 *
 */
public class ResourceRequestGenerator extends ClientDataRequestGenerator{

	/**
	 * Constructor. Don't construct the generator yourself. Use 
	 * {@link PortletTester#getResourceRequestGenerator()} instead.
	 * 
	 * @param portalContext
	 * @param portletContext
	 * @param portletStatus
	 */
	public ResourceRequestGenerator(PortalContext portalContext,
			PortletContext portletContext, PortletStatus portletStatus) {
		super(portalContext, portletContext, portletStatus);
		this.portletRequest = new MockResourceRequest(portalContext, portletContext, portletStatus);
	}
	
	/**
	 * Sets the id of the resource
	 * 
	 * @param resourceID
	 */
	public void setResourceID(String resourceID) {
		getResourceRequest().resourceID = resourceID;
	}
	
	/**
	 * Sets the cacheability of the resource
	 * 
	 * @param cacheability
	 */
	public void setCacheability(String cacheability) {
		getResourceRequest().cacheability = cacheability;
	}
	
	/**
	 * Sets the ETag of the resource
	 * 
	 * @param etag
	 */
	public void setETag(String etag) {
		getResourceRequest().etag = etag;
	}
	
	/**
	 * Adds a private render parameter to the resource
	 * 
	 * @param name the name of the parameter
	 * @param values the values of the parameter
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
