/**
 * 
 */
package com.portletguru.portlettester.mocks;

import javax.portlet.ResourceURL;

/**
 * @author Derek Linde Li
 * 
 */
public class MockResourceURL extends MockBaseURL implements ResourceURL {

	private String resourceID;
	private String cacheLevel;

	public MockResourceURL() {
		super();
		cacheLevel = ResourceURL.PAGE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.portlet.ResourceURL#setResourceID(java.lang.String)
	 */
	public void setResourceID(String resourceID) {
		this.resourceID = resourceID;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.portlet.ResourceURL#getCacheability()
	 */
	public String getCacheability() {
		return cacheLevel;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.portlet.ResourceURL#setCacheability(java.lang.String)
	 */
	public void setCacheability(String cacheLevel) {
		if (ResourceURL.PAGE.equals(cacheLevel)
				|| ResourceURL.FULL.equals(cacheLevel)
				|| ResourceURL.PORTLET.equals(cacheLevel)) {
			this.cacheLevel = cacheLevel;
		} else {
			throw new IllegalArgumentException("Unexpected cache level");
		}
	}

}
