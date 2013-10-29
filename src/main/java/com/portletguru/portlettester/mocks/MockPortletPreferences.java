/**
 * 
 */
package com.portletguru.portlettester.mocks;

import java.io.IOException;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.portlet.PortletPreferences;
import javax.portlet.PortletRequest;
import javax.portlet.PreferencesValidator;
import javax.portlet.ReadOnlyException;
import javax.portlet.ValidatorException;

/**
 * @author Derek Linde Li
 *
 */
public class MockPortletPreferences implements PortletPreferences {
	
	private Map<String,Preference> defaultPreferences;
	
	private Map<String,Preference> preferences;
	private PortletRequest request;
	private PreferencesValidator validator;
	
	public MockPortletPreferences(PortletRequest request, Map<String, Preference> defaultPreferences, PreferencesValidator validator) {
		this.request = request;
		this.validator = validator;
		this.defaultPreferences = defaultPreferences;
		this.preferences = new HashMap<String, Preference>();
	}

	/* (non-Javadoc)
	 * @see javax.portlet.PortletPreferences#isReadOnly(java.lang.String)
	 */
	
	public boolean isReadOnly(String key) {
		if (key == null) {
			throw new IllegalArgumentException("key cannot be null");
		}
		Preference preference = preferences.get(key);
		return preference != null && preference.isReadOnly();			
	}

	/* (non-Javadoc)
	 * @see javax.portlet.PortletPreferences#getValue(java.lang.String, java.lang.String)
	 */
	
	public String getValue(String key, String def) {
		if (key == null) {
			throw new IllegalArgumentException("key cannot be null");
		}
		Preference preference = preferences.get(key);
		if ( preference == null ) {
			return def;
		}
		return preference.getValues()[0];
	}

	/* (non-Javadoc)
	 * @see javax.portlet.PortletPreferences#getValues(java.lang.String, java.lang.String[])
	 */
	
	public String[] getValues(String key, String[] def) {
		if (key == null) {
			throw new IllegalArgumentException("key cannot be null");
		}
		Preference preference = preferences.get(key);
		if ( preference == null ) {
			return def;
		}
		return preference.getValues();
	}

	/* (non-Javadoc)
	 * @see javax.portlet.PortletPreferences#setValue(java.lang.String, java.lang.String)
	 */
	
	public void setValue(String key, String value) throws ReadOnlyException {
		if (key == null) {
			throw new IllegalArgumentException("key cannot be null");
		}
		Preference preference = preferences.get(key);
		if ( preference == null ) {
			preference = new Preference(key, new String[]{value});
			preferences.put(key, preference);
		} else {
			if( preference.isReadOnly() ) {
				throw new IllegalStateException( key + " is read only");
			}
			preference.setValues(new String[]{value});
		}
	}

	/* (non-Javadoc)
	 * @see javax.portlet.PortletPreferences#setValues(java.lang.String, java.lang.String[])
	 */
	
	public void setValues(String key, String[] values) throws ReadOnlyException {
		if (key == null) {
			throw new IllegalArgumentException("key cannot be null");
		}
		Preference preference = preferences.get(key);
		if ( preference == null ) {
			preference = new Preference(key, values);
		} else {
			if( preference.isReadOnly() ) {
				throw new IllegalStateException( key + " is read only");
			}
			preference.setValues(values);
		}
	}

	/* (non-Javadoc)
	 * @see javax.portlet.PortletPreferences#getNames()
	 */
	
	public Enumeration<String> getNames() {
		return Collections.enumeration(preferences.keySet());
	}

	/* (non-Javadoc)
	 * @see javax.portlet.PortletPreferences#getMap()
	 */
	
	public Map<String, String[]> getMap() {
		Map<String, String[]> map = new HashMap<String, String[]>();

		for (Map.Entry<String, Preference> entry : preferences.entrySet()) {
			String key = entry.getKey();
			Preference preference = entry.getValue();
			map.put(key, preference.getValues());
		}
		return Collections.unmodifiableMap(map);
	}

	/* (non-Javadoc)
	 * @see javax.portlet.PortletPreferences#reset(java.lang.String)
	 */
	
	public void reset(String key) throws ReadOnlyException {
		/* The reset method must reset a preference attribute to its default value. If there is no
		 * default value, the preference attribute must be deleted.cl It is left to the vendor to specify
		 * how and from where the default value is obtained.
		 */
		if(key == null){
			throw new IllegalArgumentException("key cannot be null");
		}
		Preference preference = preferences.get(key);
		if(preference == null){return;}
		
		if(preference.isReadOnly()) {
			throw new ReadOnlyException("Preference attribute " + preference.getName() 
					+ " is read-only, cannot be reset");
		}
		/* Try to get default value, delete the attribute if there's no default */
		Preference defaultPref = defaultPreferences.get(key);
		if(defaultPref == null) {
			preferences.remove(key);
		} else {
			preference.setValues(defaultPref.getValues());
		}
	}

	/* (non-Javadoc)
	 * @see javax.portlet.PortletPreferences#store()
	 */
	
	public void store() throws IOException, ValidatorException {
		if(request.getAttribute(PortletRequest.LIFECYCLE_PHASE)
				.equals(PortletRequest.RENDER_PHASE)){
			throw new IllegalStateException("store cannot be invoked in during render");
		}
		/* Get the validator for this portlet */
		PreferencesValidator validator = getValidator();
		if( validator != null ) {
			validator.validate(this);
		}
		System.out.println("Preferences are stored successfully.");
	}
	
	private PreferencesValidator getValidator(){
		return validator;
	}

}
