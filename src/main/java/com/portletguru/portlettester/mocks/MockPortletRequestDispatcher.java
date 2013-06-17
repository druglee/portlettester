/**
 * 
 */
package com.portletguru.portlettester.mocks;

import java.io.IOException;

import javax.portlet.PortletException;
import javax.portlet.PortletRequest;
import javax.portlet.PortletRequestDispatcher;
import javax.portlet.PortletResponse;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

/**
 * @author Derek Linde Li
 *
 */
public class MockPortletRequestDispatcher implements PortletRequestDispatcher {

	/* (non-Javadoc)
	 * @see javax.portlet.PortletRequestDispatcher#include(javax.portlet.RenderRequest, javax.portlet.RenderResponse)
	 */
	
	public void include(RenderRequest request, RenderResponse response)
			throws PortletException, IOException {
		//TODO - Find another way to inform the invoker
		System.out.print("include completed.");
	}

	/* (non-Javadoc)
	 * @see javax.portlet.PortletRequestDispatcher#include(javax.portlet.PortletRequest, javax.portlet.PortletResponse)
	 */
	
	public void include(PortletRequest request, PortletResponse response)
			throws PortletException, IOException {
		//TODO - Find another way to inform the invoker
		System.out.print("include completed.");
	}

	/* (non-Javadoc)
	 * @see javax.portlet.PortletRequestDispatcher#forward(javax.portlet.PortletRequest, javax.portlet.PortletResponse)
	 */
	
	public void forward(PortletRequest request, PortletResponse response)
			throws PortletException, IOException {
		//TODO - Find another way to inform the invoker
		System.out.print("forward completed.");
	}

}
