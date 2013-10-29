/**
 * 
 */
package com.portletguru.portlettester;

import java.util.HashMap;
import java.util.Map;

import javax.portlet.PreferencesValidator;

import com.portletguru.portlettester.mocks.Preference;

/**
 * @author Derek Linde Li
 *
 */
public class DefaultPreferencesConfig {

	private Map<String, Preference> defaultPreferences;
	private PreferencesValidator validator;
	
	public DefaultPreferencesConfig() {
		defaultPreferences = new HashMap<String, Preference>();
	}
	
	public void addPreference(String name, String[] values, boolean isReadOnly) {
		Preference preference = new Preference(name, values, isReadOnly);
		defaultPreferences.put(name, preference);
	}
	
	public Map<String, Preference> getDefaultPreferences() {
		return defaultPreferences;
	}
	
	public void setValidator(PreferencesValidator validator) {
		this.validator = validator;
	}
	
	public PreferencesValidator getValidator() {
		return validator;
	}
}
