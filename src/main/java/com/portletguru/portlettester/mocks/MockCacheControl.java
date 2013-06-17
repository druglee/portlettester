/**
 * 
 */
package com.portletguru.portlettester.mocks;

import javax.portlet.CacheControl;

/**
 * @author Derek Linde Li
 *
 */
public class MockCacheControl implements CacheControl {
	
	private int expirationTime;
	private boolean publicScope;
	private boolean cachedContent;
	private String eTag;

	/* (non-Javadoc)
	 * @see javax.portlet.CacheControl#getExpirationTime()
	 */
	
	public int getExpirationTime() {
		return expirationTime;
	}

	/* (non-Javadoc)
	 * @see javax.portlet.CacheControl#setExpirationTime(int)
	 */
	
	public void setExpirationTime(int time) {
		this.expirationTime = time;
	}

	/* (non-Javadoc)
	 * @see javax.portlet.CacheControl#isPublicScope()
	 */
	
	public boolean isPublicScope() {
		return publicScope;
	}

	/* (non-Javadoc)
	 * @see javax.portlet.CacheControl#setPublicScope(boolean)
	 */
	
	public void setPublicScope(boolean publicScope) {
		this.publicScope = publicScope;
	}

	/* (non-Javadoc)
	 * @see javax.portlet.CacheControl#getETag()
	 */
	
	public String getETag() {
		return eTag;
	}

	/* (non-Javadoc)
	 * @see javax.portlet.CacheControl#setETag(java.lang.String)
	 */
	
	public void setETag(String token) {
		this.eTag = token;
	}

	/* (non-Javadoc)
	 * @see javax.portlet.CacheControl#useCachedContent()
	 */
	
	public boolean useCachedContent() {
		return cachedContent;
	}

	/* (non-Javadoc)
	 * @see javax.portlet.CacheControl#setUseCachedContent(boolean)
	 */
	
	public void setUseCachedContent(boolean useCachedContent) {
		this.cachedContent = useCachedContent;
	}

}
