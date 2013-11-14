/**
 * 
 */
package com.portletguru.portlettester.mocks;

import javax.portlet.PortletRequest;
import javax.portlet.ResourceResponse;

import com.portletguru.portlettester.PortletTester;
import com.portletguru.portlettester.TestResultHolder;

/**
 * @author Derek Linde Li
 *
 */
public class ResourceResponseGenerator extends MimeResponseGenerator {

	/**
	 * Constructor. Don't construct the generator yourself. Use 
	 * {@link PortletTester#getResourceResponseGenerator()} instead.
	 * 
	 * @param request
	 * @param resultHolder
	 */
	public ResourceResponseGenerator(PortletRequest request,
			TestResultHolder resultHolder) {
		super(request, resultHolder);
		portletResponse = new MockResourceResponse(request, resultHolder);
	}

	@Override
	public ResourceResponse generateResponse() {
		return (ResourceResponse) portletResponse;
	}

}
