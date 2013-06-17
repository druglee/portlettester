/**
 * 
 */
package com.portletguru.portlettester.mocks;

import java.util.Arrays;
import java.util.List;

/**
 * @author Derek Linde Li
 *
 */
public class Preference {
	
	private String name;
	private List<String> values;
	private boolean readOnly;
	
	public Preference(String name) {
		this.name = name;
	}
	
	public Preference(String name,String[] values){
		this.name = name;
		this.values = Arrays.asList(values);
	}
	
	public Preference(String name,String[] values,boolean readOnly){
		this.name = name;
		this.values = Arrays.asList(values);
		this.readOnly = readOnly;
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the values
	 */
	public String[] getValues() {
		return values.toArray(new String[values.size()]);
	}
	/**
	 * @param values the values to set
	 */
	public void setValues(String[] values) {
		this.values = Arrays.asList(values);
	}
	/**
	 * Add a new value
	 * @param value the value to add
	 */
	public void addValue(String value) {
		this.values.add(value);
	}
	/**
	 * @return the readOnly
	 */
	public boolean isReadOnly() {
		return readOnly;
	}
	/**
	 * @param readOnly the readOnly to set
	 */
	public void setReadOnly(boolean readOnly) {
		this.readOnly = readOnly;
	}
	
	

}
