/**
 * 
 */
package com.portletguru.portlettester.mocks;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import javax.portlet.PortletURL;
import javax.portlet.ResourceURL;
import javax.servlet.http.Cookie;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.DOMException;
import org.w3c.dom.Element;

/**
 * @author Derek Linde Li
 *
 */
public abstract class MockPortletResponse implements PortletResponse {
	
	protected Map<String, Object> _headers = new LinkedHashMap<String, Object>();
	protected String namespace;
	protected PortletRequest request;

	/* (non-Javadoc)
	 * @see javax.portlet.PortletResponse#addProperty(java.lang.String, java.lang.String)
	 */
	
	public void addProperty(String key, String value) {
		if( key == null ) {
			throw new IllegalArgumentException();
		}
		addHeader(key,value);
	}

	/* (non-Javadoc)
	 * @see javax.portlet.PortletResponse#setProperty(java.lang.String, java.lang.String)
	 */
	
	public void setProperty(String key, String value) {
		if (key == null) {
			throw new IllegalArgumentException();
		}
		setHeader(key, value);
	}

	/* (non-Javadoc)
	 * @see javax.portlet.PortletResponse#encodeURL(java.lang.String)
	 */
	
	public String encodeURL(String path) {
		if ((path == null) ||
				(!path.startsWith("#") && !path.startsWith("/") &&
					(path.indexOf("://") == -1))) {

				// Allow '#' as well to workaround a bug in Oracle ADF 10.1.3

				throw new IllegalArgumentException(
					"URL path must start with a '/' or include '://'");
			}			
			return path;
	}

	/* (non-Javadoc)
	 * @see javax.portlet.PortletResponse#getNamespace()
	 */
	
	public String getNamespace() {
		if(namespace == null) {
			namespace = "portlettester";
		}
		return namespace;
	}

	/* (non-Javadoc)
	 * @see javax.portlet.PortletResponse#addProperty(javax.servlet.http.Cookie)
	 */
	
	public void addProperty(Cookie cookie) {
		if (cookie == null) {
			throw new IllegalArgumentException();
		}

		if (_headers.containsKey("cookies")) {
			Cookie[] cookies = (Cookie[])_headers.get("cookies");
			List<Cookie> cookiesList = Arrays.asList(cookies);
			cookiesList.add(cookie);			
			_headers.put("cookies", cookiesList.toArray());
		} else {
			Cookie[] cookies = new Cookie[] {cookie};

			_headers.put("cookies", cookies);
		}
	}

	/* (non-Javadoc)
	 * @see javax.portlet.PortletResponse#addProperty(java.lang.String, org.w3c.dom.Element)
	 */
	
	public void addProperty(String key, Element element) {
		/*
		 * If a DOM element with the provided key already exists the provided 
		 * element will be stored in addition to the existing element under the 
		 * same key. 
		 * If the element is null the key is removed from the response. 
		 * 
		 * */
		if( element == null ) {
			_headers.remove(key);
		} else if ( _headers.containsKey(key)) {
			List<Element> list = Arrays.asList((Element[])_headers.get(key));
			list.add(element);
			_headers.put(key, list.toArray());
		} else {
			_headers.put(key, new Element[]{element});
		}
	}

	/* (non-Javadoc)
	 * @see javax.portlet.PortletResponse#createElement(java.lang.String)
	 */
	
	public Element createElement(String tagName) throws DOMException {
		/*A new Element object with the nodeName attribute set to tagName, 
		 * and localName, prefix, and namespaceURI set to null. */
		DocumentBuilderFactory documentfactory = DocumentBuilderFactory.newInstance(); 
		DocumentBuilder builder = null;
		try {
			builder = documentfactory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
		return builder.newDocument().createElement(tagName);
	}

	
	protected void addHeader(String name, String value) {
		if (name == null) {
			throw new IllegalArgumentException();
		}

		if (_headers.containsKey(name)) {
			String[] values = (String[])_headers.get(name);
			List<String> valuesList = Arrays.asList(values);
			valuesList.add(value);
			_headers.put(name, valuesList.toArray());
		}
		else {
			setHeader(name, value);
		}
	}
	
	
	protected void setHeader(String name, String value) {
		if (name == null) {
			throw new IllegalArgumentException();
		}
		if (value == null) {
			_headers.remove(name);
		}
		else {
			_headers.put(name, new String[] {value});
		}
	}
	
	protected PortletURL createRenderURL() {
		return new MockPortletURL(request.getPublicParameterMap());
	}

	protected PortletURL createActionURL() {
		return new MockActionURL(request.getPublicParameterMap());
	}

	protected ResourceURL createResourceURL() {
		return new MockResourceURL();
	}
	
	protected abstract String getLifeCycle();
}
