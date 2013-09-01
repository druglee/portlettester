/**
 * 
 */
package com.portletguru.portlettester.mocks;

import java.io.IOException;
import java.io.Writer;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.portlet.BaseURL;
import javax.portlet.PortletSecurityException;

import org.apache.commons.lang.StringEscapeUtils;

/**
 * @author Derek Linde Li
 *
 */
public class MockBaseURL implements BaseURL {
	
	private Map<String, String[]> parameters;
	private boolean secure;
	private Map<String, List<String>> properties;
	
	public MockBaseURL() {
		parameters = new HashMap<String, String[]>();
		properties = new HashMap<String, List<String>>();
	}
	

	/* (non-Javadoc)
	 * @see javax.portlet.BaseURL#setParameter(java.lang.String, java.lang.String)
	 */
	public void setParameter(String name, String value) {
		parameters.put(name, new String[]{value});
	}

	/* (non-Javadoc)
	 * @see javax.portlet.BaseURL#setParameter(java.lang.String, java.lang.String[])
	 */
	public void setParameter(String name, String[] values) {
		parameters.put(name, values);
	}

	/* (non-Javadoc)
	 * @see javax.portlet.BaseURL#setParameters(java.util.Map)
	 */
	public void setParameters(Map<String, String[]> parameters) {
		if(parameters == null) {
			throw new IllegalArgumentException("parameters cannot be null");
		}
		this.parameters = parameters; 
	}

	/* (non-Javadoc)
	 * @see javax.portlet.BaseURL#setSecure(boolean)
	 */
	public void setSecure(boolean secure) throws PortletSecurityException {
		this.secure = secure;
	}

	/* (non-Javadoc)
	 * @see javax.portlet.BaseURL#getParameterMap()
	 */
	public Map<String, String[]> getParameterMap() {
		return Collections.unmodifiableMap(parameters);
	}

	/* (non-Javadoc)
	 * @see javax.portlet.BaseURL#write(java.io.Writer)
	 */
	public void write(Writer out) throws IOException {
		write(out, true);
	}

	/* (non-Javadoc)
	 * @see javax.portlet.BaseURL#write(java.io.Writer, boolean)
	 */
	public void write(Writer out, boolean escapeXML) throws IOException {
		String urlStr = toString();
		if(escapeXML) {
			urlStr = StringEscapeUtils.escapeXml(urlStr);
		}
		out.write(urlStr);
	}

	/* (non-Javadoc)
	 * @see javax.portlet.BaseURL#addProperty(java.lang.String, java.lang.String)
	 */
	public void addProperty(String key, String value) {
		List<String> values = getPropertyValues(key);
		values.add(value);
		properties.put(key, values);
	}

	/* (non-Javadoc)
	 * @see javax.portlet.BaseURL#setProperty(java.lang.String, java.lang.String)
	 */
	public void setProperty(String key, String value) {
		List<String> values = new LinkedList<String>();
		values.add(value);
		properties.put(key, values);
	}

	private List<String> getPropertyValues(String key) {
		List<String> values = properties.get(key);
		if(values == null) {
			return new LinkedList<String>();
		}
		return values;
	}
	
	@Override
	public String toString() {
		// TODO: Implement with the parameters and properties
		return super.toString();
	}
}
