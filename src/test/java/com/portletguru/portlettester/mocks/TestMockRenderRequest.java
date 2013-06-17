/**
 * 
 */
package com.portletguru.portlettester.mocks;

import static org.junit.Assert.assertEquals;

import javax.portlet.PortletRequest;

import org.junit.Before;
import org.junit.Test;

import com.portletguru.portlettester.PortletStatus;

/**
 * @author Derek Linde Li
 *
 */
public class TestMockRenderRequest {
	private MockRenderRequest request;
	
	@Before
	public void setup(){
		request = new MockRenderRequest(new MockPortalContext(), new MockPortletContext(), new PortletStatus());
	}
	
	@Test
	public void testGetAttribute(){
		assertEquals(PortletRequest.RENDER_PHASE, (String)request.getAttribute(PortletRequest.LIFECYCLE_PHASE));
	}
}
