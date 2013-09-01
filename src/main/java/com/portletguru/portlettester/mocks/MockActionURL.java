/**
 * 
 */
package com.portletguru.portlettester.mocks;

import java.util.Map;

/**
 * @author Derek Linde Li
 *
 */
public class MockActionURL extends MockPortletURL {
	
	public MockActionURL(Map<String, String[]> publicRenderParameters) {
		super(publicRenderParameters);
	}

	@Override
	public void removePublicRenderParameter(String name) {
		// It should do nothing according to JSR286
	}
}
