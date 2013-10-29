/**
 * 
 */
package com.portletguru.portlettester.mocks;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import javax.portlet.PortalContext;
import javax.portlet.PortletContext;
import javax.portlet.PortletMode;
import javax.portlet.PortletRequest;
import javax.portlet.WindowState;

import org.junit.Before;
import org.junit.Test;

import com.portletguru.portlettester.DefaultPreferencesConfig;
import com.portletguru.portlettester.PortletStatus;
import com.portletguru.portlettester.TestResultHolder;

/**
 * @author Derek Linde Li
 *
 */
public class MockPortletRequestTest {
	
	/* this class is for testing purpose only, doing this is because MockPortletRequest is an 
	 * abstract class */
	private static class TestingPurposePortletRequest extends MockPortletRequest {
		public TestingPurposePortletRequest(PortalContext portalContext,
				PortletContext portletContext, PortletStatus portletStatus) {
			super(portalContext, portletContext, portletStatus);
		}

		@Override
		public String getLifeCycle() {
			return null;
		}
	}
	
	private MockPortletRequest request;
	
	@Before
	public void setup(){
		request = new TestingPurposePortletRequest(new MockPortalContext(), new MockPortletContext(new TestResultHolder()), new PortletStatus(new DefaultPreferencesConfig()));
	}
	
	@Test
	public void testIsWindowStateAllowed(){
		assertTrue(request.isWindowStateAllowed(WindowState.MAXIMIZED));
		assertTrue(request.isWindowStateAllowed(WindowState.MINIMIZED));
		assertTrue(request.isWindowStateAllowed(WindowState.NORMAL));
	}
	
	@Test
	public void testIsPortletModeAllowed(){
		assertTrue(request.isPortletModeAllowed(PortletMode.EDIT));
		assertTrue(request.isPortletModeAllowed(PortletMode.VIEW));
		assertTrue(request.isPortletModeAllowed(PortletMode.HELP));
	}

	@Test
	public void testAttributesAccessing(){
		String value = "value";
		String key = "key";
		// If there's nothing in it, it should return null
		assertNull(request.getAttribute(key));
		
		// If the value for the key exists, it should return the corresponding value
		request.setAttribute(key, value );
		assertEquals(value, request.getAttribute(key));
		
		// If the key is null, it should throw an IllegalArgumentException
		Exception excep = null;
		try {
			request.setAttribute(null, value);
		} catch( IllegalArgumentException e ) {
			excep = e;
		}
		assertNotNull(excep);
		
		// If the specific attribute is removed
		request.removeAttribute(key);
		assertNull(request.getAttribute(key));
		
		assertTrue( request.getAttribute(PortletRequest.CCPP_PROFILE) instanceof MockProfile );
	}
	
	@Test
	public void testParametersAccessing(){
		String value1 = "value1";
		String value2 = "value2";
		String[] values = new String[]{value1,value2};
		String key = "key";
		// If there's nothing in it, it should return null
		assertNull(request.getParameter(key));
		
		// If the value for the key exists, it should return the corresponding value
		request.parameters.put(key, values);
		assertArrayEquals(values, request.getParameterValues(key));
		// It should return the first value
		assertEquals(value1, request.getParameter(key));
		

		// The parameter map should be immutable
		UnsupportedOperationException uoe = null;
		try {
			request.getParameterMap().put("", new String[]{""});
		} catch( UnsupportedOperationException e ) {
			uoe = e;
		}
		assertNotNull(uoe);

	}
}
