package com.portletguru.portletester;

import java.io.IOException;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.GenericPortlet;
import javax.portlet.PortletException;

/**
 * This portlet is used to prove all public APIs are working as expected
 * 
 * @author Derek Linde Li
 *
 */
public class MockPortlet extends GenericPortlet {
	
	
	
	public void actionRequestTest(ActionRequest request, ActionResponse response) throws IOException, PortletException {
		
	}
}
