/**
 * 
 */
package com.portletguru.portlettester.mocks;

import java.util.HashMap;
import java.util.Map;

import javax.portlet.PreferencesValidator;


/**
 * This class stands for the default preferences settings defined 
 * in the portlet.xml file in the &lt;portlet-preferences&gt; tag.
 * It also allows you to specify the 
 * javax.portlet.PreferencesValidator for preferences.
 * 
 * @author Derek Linde Li
 *
 */
public class DefaultPreferencesConfig {

	private Map<String, Preference> defaultPreferences;
	private PreferencesValidator validator;
	
	public DefaultPreferencesConfig() {
		defaultPreferences = new HashMap<String, Preference>();
	}
	
	/**
	 * Add a default preferences item to this config.
	 * 
	 * @param name the name of the preference item
	 * @param values the values of the preference item
	 * @param isReadOnly if the preference item is read-only
	 */
	public void addPreference(String name, String[] values, boolean isReadOnly) {
		Preference preference = new Preference(name, values, isReadOnly);
		defaultPreferences.put(name, preference);
	}
	
	/**
	 * Returns all preference items added to this config.
	 * 
	 * @return a map whose key is the name of preference and whose value 
	 * is the {@link com.portletguru.portlettester.mocks.Preference} instance 
	 * that represents the preference item.
	 */
	public Map<String, Preference> getDefaultPreferences() {
		return defaultPreferences;
	}
	
	/**
	 * Specifies the validator of all preference items. It should be 
	 * the validator ususally specified in the &lt;preferences-validator&gt; tag.
	 * 
	 * @param validator
	 */
	public void setValidator(PreferencesValidator validator) {
		this.validator = validator;
	}
	
	/**
	 * Returns the validator specified for this config.
	 * 
	 * @return a {@link javax.portlet.PreferencesValidator} object
	 */
	public PreferencesValidator getValidator() {
		return validator;
	}
}
