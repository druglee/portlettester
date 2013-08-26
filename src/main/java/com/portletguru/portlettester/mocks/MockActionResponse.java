/**
 * 
 */
package com.portletguru.portlettester.mocks;

import java.io.IOException;
import java.util.Map;

import javax.portlet.ActionResponse;
import javax.portlet.PortletMode;
import javax.portlet.PortletRequest;
import javax.portlet.WindowState;

import com.portletguru.portlettester.PortletStatus;
import com.portletguru.portlettester.TestResultHolder;
import com.portletguru.portlettester.utils.Constants;

/**
 * @author Derek Linde Li
 *
 */
public class MockActionResponse extends MockStateAwareResponse implements
		ActionResponse {
	
	private TestResultHolder resultHolder;


	public MockActionResponse(PortletStatus portletStatus,
			PortletRequest portletRequest, TestResultHolder resultHolder) {
		super(portletStatus, portletRequest);
		this.resultHolder = resultHolder;
	}

	/* (non-Javadoc)
	 * @see javax.portlet.ActionResponse#sendRedirect(java.lang.String)
	 */
	
	public void sendRedirect(String location) throws IOException {
		/*According to JSR286 */
		/* If the sendRedirect(String location) method is called after the setPortletMode,
		 * setWindowState, removePublicRenderParameter, setRenderParameter or
		 * setRenderParameters methods of the ActionResponse interface, an
		 * IllegalStateException must be thrown and the redirection must not be executed.
		 * */
		if(isRenderParameterCalled()){
			throw new IllegalStateException(
				"One of the follow methods must have been called: setPortletMode, setWindowState, " +
				"removePublicRenderParameter, setRenderParameter, setRenderParameters"
			);
		}
		if( !isPathValid(location) ) {
			throw new IllegalArgumentException("location:" + location + " is a relative path.");
		}
		setRedirectURL(location);
		resultHolder.setRedirectLocation(location);
	}

	/* (non-Javadoc)
	 * @see javax.portlet.ActionResponse#sendRedirect(java.lang.String, java.lang.String)
	 */
	
	public void sendRedirect(String location, String renderUrlParamName)
			throws IOException {
		//TODO - To be researched
		/* The portlet container must attach a render URL with the currently set portlet mode,
		 * window state and render parameters on the ActionResponse and the current public
		 * render parameters. The attached URL must be available as query parameter value
		 * under the key provided with the renderUrlParamName parameter.
		 * New values for portlet mode, window state, private or public render parameters must be
		 * encoded in the attached render URL, but are not remembered after the redirect is issued.*/
		
		//I'm not quite sure about the meaning of the JSR286, the implementation is just a guess
		if(isRenderParameterCalled()){
			throw new IllegalStateException(
				"One of the follow methods must have been called: setPortletMode, setWindowState, " +
				"removePublicRenderParameter, setRenderParameter, setRenderParameters"
			);
		}
		
		if( !isPathValid(location) ) {
			throw new IllegalArgumentException("location:" + location + " is a relative path.");
		}
		String encodedLocation = generateURL(location, getWindowState(), getPortletMode(), getRenderParameterMap());
		setRenderParameter(renderUrlParamName,encodedLocation);
		setRedirectURL(encodedLocation);
		resultHolder.setRedirectLocation(location);
	}
	
	/**
	 * Generate a URL string according to the current WindowState, PortletMode, private or public render parameters
	 * @return An encode String of URL that contains the info of current WindowState, PortletMode, private or public render parameters
	 */
	private String generateURL(String location, WindowState winState, 
			PortletMode portletMode, Map<String,String[]> renderParameters ){
		return location;
	}
	
	private boolean isPathValid(String path){
		return !(!path.startsWith(Constants.SLASH) && path.indexOf("://") == -1);
	}

	
	protected String getLifeCycle() {
		return PortletRequest.ACTION_PHASE;
	}

}
