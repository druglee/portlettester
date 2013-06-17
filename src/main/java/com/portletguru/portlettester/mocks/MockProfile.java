/**
 * 
 */
package com.portletguru.portlettester.mocks;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.ccpp.Attribute;
import javax.ccpp.Component;
import javax.ccpp.Profile;
import javax.ccpp.ProfileDescription;

/**
 * @author Derek Linde Li
 *
 */
public class MockProfile implements Profile {
	
	private Map<String,Attribute> attributes;
	private Map<String,Component> components;
	private ProfileDescription description;
	
	public MockProfile() {
		attributes = new HashMap<String, Attribute>();
		components = new HashMap<String, Component>();
	}
	
	//TODO - Implement this class after doing more research

	/* (non-Javadoc)
	 * @see javax.ccpp.Profile#getAttribute(java.lang.String)
	 */
	
	public Attribute getAttribute(String arg0) {
		return attributes.get(arg0);
	}

	/* (non-Javadoc)
	 * @see javax.ccpp.Profile#getAttributes()
	 */
	
	public Set getAttributes() {
		return new HashSet<Attribute>(attributes.values());
	}

	/* (non-Javadoc)
	 * @see javax.ccpp.Profile#getComponent(java.lang.String)
	 */
	
	public Component getComponent(String arg0) {
		return components.get(arg0);
	}

	/* (non-Javadoc)
	 * @see javax.ccpp.Profile#getComponents()
	 */
	
	public Set getComponents() {
		return new HashSet<Component>(components.values());
	}

	/* (non-Javadoc)
	 * @see javax.ccpp.Profile#getDescription()
	 */
	
	public ProfileDescription getDescription() {
		return description;
	}

}
