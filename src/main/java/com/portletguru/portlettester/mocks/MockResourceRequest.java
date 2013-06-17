/**
 * 
 */
package com.portletguru.portlettester.mocks;

import java.util.Enumeration;
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

	public MockResourceRequest(PortalContext portalContext,
			PortletContext portletContext, PortletStatus portletStatus) {
		super(portalContext, portletContext, portletStatus);
	}	
	
	/*If the getResponseContentType or getResponseContentTypes methods are exposed
	via an ResourceRequest the returned values should be based on the HTTP Accept header
	provided by the client.*/
	
	public String getResponseContentType() {
		// TODO Auto-generated method stub
		return super.getResponseContentType();
	}
	
	
	public Enumeration<String> getResponseContentTypes() {
		// TODO Auto-generated method stub
		return super.getResponseContentTypes();
	}

	
	public String getETag() {
		return null;
	}

	/**
	 * Always return "MockID"
	 */
	
	public String getResourceID() {
		// TODO Auto-generated method stub
		return "MockID";
	}


	/**
	 * Always return an empty map
	 */
	
	public Map<String, String[]> getPrivateRenderParameterMap() {
		// TODO - Do some research on where these parameters come from
		return new HashMap<String, String[]>();
	}


	/**
	 * Always return ResourceURL.PAGE
	 */
	
	public String getCacheability() {
		/* Possible return values are: ResourceURL.FULL, 
		 * ResourceURL.PORTLET or ResourceURL.PAGE.
		 */
		return ResourceURL.PAGE;
	}

	
	public String getLifeCycle() {
		return PortletRequest.RESOURCE_PHASE;
	}
		
}
