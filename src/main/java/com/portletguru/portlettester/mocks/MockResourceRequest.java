/**
 * 
 */
package com.portletguru.portlettester.mocks;

import java.util.HashMap;
import java.util.Map;

import javax.portlet.PortalContext;
import javax.portlet.PortletContext;
import javax.portlet.PortletRequest;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceURL;

import com.portletguru.portlettester.PortletStatus;

/**
 * @author Derek Linde Li
 *
 */
public class MockResourceRequest extends MockClientDataRequest implements
		ResourceRequest {
	
	protected String resourceID;
	protected String cacheability;
	protected Map<String, String[]> privateRenderParameterMap;
	protected String etag;

	public MockResourceRequest(PortalContext portalContext,
			PortletContext portletContext, PortletStatus portletStatus) {
		super(portalContext, portletContext, portletStatus);
		
		this.privateRenderParameterMap = new HashMap<String, String[]>();
		this.cacheability = ResourceURL.PAGE;
	}	
	
	/*If the getResponseContentType or getResponseContentTypes methods are exposed
	via an ResourceRequest the returned values should be based on the HTTP Accept header
	provided by the client.*/

	
	public String getETag() {
		return etag;
	}

	public String getResourceID() {
		return resourceID;
	}

	public Map<String, String[]> getPrivateRenderParameterMap() {
		// TODO - Do some research on where these parameters come from
		return privateRenderParameterMap;
	}

	public String getCacheability() {
		/* Possible return values are: ResourceURL.FULL, 
		 * ResourceURL.PORTLET or ResourceURL.PAGE.
		 */
		return cacheability;
	}

	@Override
	public String getProperty(String name) {
		if(ResourceRequest.ETAG.equals(name)) {
			return getETag();
		}
		return super.getProperty(name);
	}
	
	public String getLifeCycle() {
		return PortletRequest.RESOURCE_PHASE;
	}
		
}
