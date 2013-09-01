/**
 * 
 */
package com.portletguru.portlettester.mocks;

import java.util.Collection;
import javax.portlet.PortletMode;
import javax.portlet.PortletRequest;
import javax.portlet.RenderResponse;

import com.portletguru.portlettester.TestResultHolder;

/**
 * @author Derek Linde Li
 *
 */
public class MockRenderResponse extends MockMimeResponse implements
		RenderResponse {
	
	public MockRenderResponse(PortletRequest request, TestResultHolder resultHolder) {
		super(request, resultHolder);
	}

	private String title;
	private Collection<PortletMode> nextPossiblePortletModes;

	
	public void setTitle(String title) {
		this.title = title;
	}

	
	public void setNextPossiblePortletModes(Collection<PortletMode> portletModes) {		
		this.nextPossiblePortletModes = portletModes;
	}

	
	protected String getLifeCycle() {		
		return PortletRequest.RENDER_PHASE;
	}	
 	

}
