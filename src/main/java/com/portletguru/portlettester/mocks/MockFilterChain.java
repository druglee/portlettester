/**
 * 
 */
package com.portletguru.portlettester.mocks;

import java.io.IOException;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.EventRequest;
import javax.portlet.EventResponse;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.portlet.filter.FilterChain;

import com.portletguru.portlettester.TestResultHolder;

/**
 * @author Derek Linde Li
 *
 */
public class MockFilterChain implements FilterChain {
	
	private TestResultHolder resultHolder;
	
	public MockFilterChain(TestResultHolder resultHolder) {
		this.resultHolder = resultHolder;
	}

	/* (non-Javadoc)
	 * @see javax.portlet.filter.FilterChain#doFilter(javax.portlet.ActionRequest, javax.portlet.ActionResponse)
	 */
	public void doFilter(ActionRequest request, ActionResponse response)
			throws IOException, PortletException {
		resultHolder.setFilterPassed();

	}

	/* (non-Javadoc)
	 * @see javax.portlet.filter.FilterChain#doFilter(javax.portlet.EventRequest, javax.portlet.EventResponse)
	 */
	public void doFilter(EventRequest request, EventResponse response)
			throws IOException, PortletException {
		resultHolder.setFilterPassed();

	}

	/* (non-Javadoc)
	 * @see javax.portlet.filter.FilterChain#doFilter(javax.portlet.RenderRequest, javax.portlet.RenderResponse)
	 */
	public void doFilter(RenderRequest request, RenderResponse response)
			throws IOException, PortletException {
		resultHolder.setFilterPassed();
	}

	/* (non-Javadoc)
	 * @see javax.portlet.filter.FilterChain#doFilter(javax.portlet.ResourceRequest, javax.portlet.ResourceResponse)
	 */
	public void doFilter(ResourceRequest request, ResourceResponse response)
			throws IOException, PortletException {
		resultHolder.setFilterPassed();
	}

}
