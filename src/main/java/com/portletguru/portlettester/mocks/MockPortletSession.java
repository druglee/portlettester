/**
 * 
 */
package com.portletguru.portlettester.mocks;

import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.portlet.PortletContext;
import javax.portlet.PortletSession;

/**
 * @author Derek Linde Li
 *
 */
public class MockPortletSession implements PortletSession {
	
	private Map<String,Object> portletAttributes;
	private Map<String,Object> portletAppAttributes;
	private long creationTime;
	private String id;
	private boolean valid;
	private boolean isNew;
	private int maxInactiveInterval;
	private PortletContext portletContext;
	
	public MockPortletSession( PortletContext context ) {
		portletAttributes = new HashMap<String, Object>();
		portletAppAttributes = new HashMap<String, Object>();
		creationTime = System.currentTimeMillis();
		id = String.valueOf(creationTime);
		valid = true;
		isNew = true;
		maxInactiveInterval = 0;
		portletContext = context;
	}

	/* (non-Javadoc)
	 * @see javax.portlet.PortletSession#getAttribute(java.lang.String)
	 */
	
	public Object getAttribute(String name) {
		if (!valid) {
			throw new IllegalStateException("Session is invalidated.");
		}
		return portletAttributes.get(name);
	}

	/* (non-Javadoc)
	 * @see javax.portlet.PortletSession#getAttribute(java.lang.String, int)
	 */
	
	public Object getAttribute(String name, int scope) {
		if (!valid) {
			throw new IllegalStateException("Session is invalidated.");
		}
		if( scope == PortletSession.APPLICATION_SCOPE ) {
			return portletAppAttributes.get(name);
		} else if ( scope == PortletSession.PORTLET_SCOPE ) {
			return portletAttributes.get(name);
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see javax.portlet.PortletSession#getAttributeNames()
	 */
	
	public Enumeration<String> getAttributeNames() {
		if (!valid) {
			throw new IllegalStateException("Session is invalidated.");
		}
		return Collections.enumeration(portletAttributes.keySet());
	}

	/* (non-Javadoc)
	 * @see javax.portlet.PortletSession#getAttributeNames(int)
	 */
	
	public Enumeration<String> getAttributeNames(int scope) {
		if (!valid) {
			throw new IllegalStateException("Session is invalidated.");
		}
		if ( scope == PortletSession.APPLICATION_SCOPE ) {
			return Collections.enumeration(portletAppAttributes.keySet());
		} else if ( scope == PortletSession.PORTLET_SCOPE ) {
			return Collections.enumeration(portletAttributes.keySet());
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see javax.portlet.PortletSession#getCreationTime()
	 */
	
	public long getCreationTime() {
		if (!valid) {
			throw new IllegalStateException("Session is invalidated.");
		}
		return this.creationTime;
	}

	/* (non-Javadoc)
	 * @see javax.portlet.PortletSession#getId()
	 */
	
	public String getId() {
		if (!valid) {
			throw new IllegalStateException("Session is invalidated.");
		}
		return this.id;
	}

	/* (non-Javadoc)
	 * @see javax.portlet.PortletSession#getLastAccessedTime()
	 */
	
	public long getLastAccessedTime() {
		if (!valid) {
			throw new IllegalStateException("Session is invalidated.");
		}
		return this.creationTime;
	}

	/* (non-Javadoc)
	 * @see javax.portlet.PortletSession#getMaxInactiveInterval()
	 */
	
	public int getMaxInactiveInterval() {
		if (!valid) {
			throw new IllegalStateException("Session is invalidated.");
		}
		// TODO Auto-generated method stub
		return maxInactiveInterval;
	}

	/* (non-Javadoc)
	 * @see javax.portlet.PortletSession#invalidate()
	 */
	
	public void invalidate() {
		if (!valid) {
			throw new IllegalStateException("Session is invalidated.");
		}
		valid = false;
		portletAttributes = new HashMap<String, Object>();
		portletAppAttributes = new HashMap<String, Object>();
	}

	/* (non-Javadoc)
	 * @see javax.portlet.PortletSession#isNew()
	 */
	
	public boolean isNew() {
		if (!valid) {
			throw new IllegalStateException("Session is invalidated.");
		}
		// TODO - return a more accurate value
		return isNew;
	}

	/* (non-Javadoc)
	 * @see javax.portlet.PortletSession#removeAttribute(java.lang.String)
	 */
	
	public void removeAttribute(String name) {
		if (!valid) {
			throw new IllegalStateException("Session is invalidated.");
		}
		portletAttributes.remove(name);
	}

	/* (non-Javadoc)
	 * @see javax.portlet.PortletSession#removeAttribute(java.lang.String, int)
	 */
	
	public void removeAttribute(String name, int scope) {
		if (!valid) {
			throw new IllegalStateException("Session is invalidated.");
		}
		if( scope == PortletSession.APPLICATION_SCOPE ) {
			portletAppAttributes.remove(name);
		} else if ( scope == PortletSession.PORTLET_SCOPE ) {
			portletAttributes.remove(name);
		}
	}

	/* (non-Javadoc)
	 * @see javax.portlet.PortletSession#setAttribute(java.lang.String, java.lang.Object)
	 */
	
	public void setAttribute(String name, Object value) {
		if (!valid) {
			throw new IllegalStateException("Session is invalidated.");
		}
		if ( name == null ) {
			throw new IllegalArgumentException("name cannot be null");
		}
		portletAttributes.put(name, value);
	}

	/* (non-Javadoc)
	 * @see javax.portlet.PortletSession#setAttribute(java.lang.String, java.lang.Object, int)
	 */
	
	public void setAttribute(String name, Object value, int scope) {
		if (!valid) {
			throw new IllegalStateException("Session is invalidated.");
		}
		if ( name == null ) {
			throw new IllegalArgumentException("name cannot be null");
		}
		if( scope == PortletSession.APPLICATION_SCOPE ) {
			portletAppAttributes.put(name, value);
		} else if ( scope == PortletSession.PORTLET_SCOPE ) {
			portletAttributes.put(name, value);
		}
	}

	/* (non-Javadoc)
	 * @see javax.portlet.PortletSession#setMaxInactiveInterval(int)
	 */
	
	public void setMaxInactiveInterval(int interval) {
		if (!valid) {
			throw new IllegalStateException("Session is invalidated.");
		}
		maxInactiveInterval = interval;
	}

	/* (non-Javadoc)
	 * @see javax.portlet.PortletSession#getPortletContext()
	 */
	
	public PortletContext getPortletContext() {
		if (!valid) {
			throw new IllegalStateException("Session is invalidated.");
		}
		return portletContext;
	}

	/* (non-Javadoc)
	 * @see javax.portlet.PortletSession#getAttributeMap()
	 */
	
	public Map<String, Object> getAttributeMap() {
		if (!valid) {
			throw new IllegalStateException("Session is invalidated.");
		}
		return portletAttributes;
	}

	/* (non-Javadoc)
	 * @see javax.portlet.PortletSession#getAttributeMap(int)
	 */
	
	public Map<String, Object> getAttributeMap(int scope) {
		if (!valid) {
			throw new IllegalStateException("Session is invalidated.");
		}
		if( scope == PortletSession.APPLICATION_SCOPE ) {
			return portletAppAttributes;
		} else if ( scope == PortletSession.PORTLET_SCOPE ) {
			return portletAttributes;
		}
		return null;
	}

}
