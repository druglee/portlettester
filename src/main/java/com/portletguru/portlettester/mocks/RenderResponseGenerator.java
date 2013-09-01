/**
 * 
 */
package com.portletguru.portlettester.mocks;

import java.util.Locale;

import javax.portlet.PortletRequest;
import javax.portlet.RenderResponse;

import com.portletguru.portlettester.TestResultHolder;

/**
 * @author Derek Linde Li
 *
 */
public class RenderResponseGenerator extends MimeResponseGenerator {

	public RenderResponseGenerator(PortletRequest request,
			TestResultHolder resultHolder) {
		super(request, resultHolder);
		this.portletResponse = new MockRenderResponse(request, resultHolder);
	}
	
	/**
	 * 
	 * @param locale
	 */
	public void setLocale(Locale locale) {
		getRenderResponse().locale = locale;
	}
	
	private MockRenderResponse getRenderResponse(){
		return (MockRenderResponse) portletResponse;
	}
	

	@Override
	public RenderResponse generateResponse() {
		return (RenderResponse) portletResponse;
	}

}
