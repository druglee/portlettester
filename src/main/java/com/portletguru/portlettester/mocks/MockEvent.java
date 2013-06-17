/**
 * 
 */
package com.portletguru.portlettester.mocks;

import java.io.Serializable;

import javax.portlet.Event;
import javax.xml.namespace.QName;

/**
 * @author Derek Linde Li
 *
 */
public class MockEvent implements Event {
	
	private QName qName;
	private Serializable value;
	private String name;
	
	public MockEvent(String name,Serializable value) {
		this.name = name;
		this.value = value;
		this.qName = new QName("{http://com.portletmaniac.portlettester}", name);
	}
	
	public MockEvent(QName qname,Serializable value){
		this.qName = qname;
		this.value = value;
		this.name = qName.getLocalPart();
	}
	
	public MockEvent(){}

	/* (non-Javadoc)
	 * @see javax.portlet.Event#getQName()
	 */
	
	public QName getQName() {
		return qName;
	}

	/* (non-Javadoc)
	 * @see javax.portlet.Event#getName()
	 */
	
	public String getName() {
		return name;
	}

	/* (non-Javadoc)
	 * @see javax.portlet.Event#getValue()
	 */
	
	public Serializable getValue() {
		return value;
	}

}
