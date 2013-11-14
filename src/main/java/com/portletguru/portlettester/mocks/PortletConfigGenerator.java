/**
 * 
 */
package com.portletguru.portlettester.mocks;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.portlet.PortletConfig;
import javax.portlet.PortletContext;
import javax.xml.namespace.QName;

import com.portletguru.portlettester.PortletStatus;
import com.portletguru.portlettester.PortletTester;

/**
 * Helper class to create a PortletConfig instance. It allows you to fill 
 * in the content usually defined in the portlet.xml file into a PortletConfig 
 * object, so that it can be used later to initialize a Portlet object.  
 * 
 * @author Derek Linde Li
 *
 */
public class PortletConfigGenerator {

	private MockPortletConfig portletConfig;
	private Map<String,String[]> containerRuntimeOptions;
	private Map<String,String> initParameters;
	private Map<String,String> publicRenderParameters;
	private List<QName> publishingEvents;
	private List<QName> processingEvents;
	private List<Locale> supportedLocales;
	
	private PortletStatus portletStatus;
	
	/**
	 * Constructor. Don't create a generator by yourself. Use 
	 * {@link PortletTester#getPortletConfigGenerator()} instead.
	 * 
	 * @param portletContext
	 * @param portletStatus
	 */
	public PortletConfigGenerator(PortletContext portletContext, PortletStatus portletStatus) {
		this.portletConfig = new MockPortletConfig(portletContext);
		containerRuntimeOptions = new HashMap<String, String[]>();
		initParameters = new HashMap<String, String>();
		publicRenderParameters = new HashMap<String, String>();
		publishingEvents = new LinkedList<QName>();
		processingEvents = new LinkedList<QName>();
		supportedLocales = new LinkedList<Locale>();
		this.portletStatus = portletStatus;
	}
	
	/**
	 * Set the bundle base name of the portlet
	 * 
	 * @param baseName
	 */
	public void setBundleBaseName(String baseName) {
		this.portletConfig.setBundleBaseName(baseName);
	}
	
	/**
	 * Sets the name of the portlet being tested
	 * 
	 * @param portletName the name of the portlet
	 */
	public void setPortletName(String portletName) {
		this.portletConfig.setPortletName(portletName);
	}
	
	/**
	 * Set the default namespace for the portlet
	 * 
	 * @param defaultNamespace
	 */
	public void setDefaultNamespace(String defaultNamespace) {
		this.portletConfig.setDefaultNamespace(defaultNamespace);
	}
	
	/**
	 * Add a init parameter for the portlet
	 * 
	 * @param key the key of the parameter
	 * @param value the value of the parameter
	 */
	public void addInitParameter(String key, String value) {
		initParameters.put(key, value);
	}
	
	/* container runtime options */
	/**
	 * Specify the container runtime options
	 * 
	 * @param containerRuntimeOptions
	 */
	public void setContainerRuntimeOptions(
			Map<String, String[]> containerRuntimeOptions) {
		this.containerRuntimeOptions = containerRuntimeOptions;
	}
	
	/**
	 * Add a container runtime options
	 * 
	 * @param key the key of the option
	 * @param values the values of the option
	 */
	public void addContainerRuntimeOption(String key, String[] values) {
		containerRuntimeOptions.put(key, values);
	}

	/**
	 * Set the public render parameters of the portlet
	 * 
	 * @param publicRenderParameters
	 */
	public void setPublicRenderParameters(Map<String, String> publicRenderParameters) {
		this.publicRenderParameters = publicRenderParameters;
		portletStatus.setPublicParameterMap(publicRenderParameters);
	}
	
	/**
	 * Add a public render parameter for the portlet
	 * 
	 * @param key the key of the parameter
	 * @param value the value of the parameter
	 */
	public void addPublicRenderParameter(String key, String value) {
		this.publicRenderParameters.put(key, value);
		portletStatus.setPublicParameterMap(publicRenderParameters);
	}

	/* publishing events  */
	/**
	 * Set a list of events could be published by this portlet
	 * 
	 * @param publishingEvents the list of QName of the events
	 */
	public void setPublishingEvents(List<QName> publishingEvents) {
		this.publishingEvents = publishingEvents;
	}
	
	/**
	 * Add a QName of the publishing event
	 * 
	 * @param qname the QName of the event
	 */
	public void addPublishingEvent(QName qname) {
		this.publishingEvents.add(qname);
	}
	
	/**
	 * Add a QName of the publishing event with the local part string specified, and 
	 * leave the others as null
	 * 
	 * @param qname the QName of the event
	 */
	public void addPublishingEvent(String localPart) {
		this.publishingEvents.add(new QName(localPart));
	}
	
	/**
	 * Add a QName of the publishing event with the local part and namespace URI specified,
	 * and leave the prefix as null
	 * 
	 * @param namespaceUri the namespace URI string
	 * @param localPart the local part string
	 */
	public void addPublishingEvent(String namespaceUri, String localPart) {
		this.publishingEvents.add(new QName(namespaceUri, localPart));
	}
	
	/**
	 * Add a QName of the publishing event with the namespace URI, local part and prefix 
	 * specified
	 * 
	 * @param namespaceUri the namespace URI
	 * @param localPart the local part
	 * @param prefix the prefix
	 */
	public void addPublishingEvent(String namespaceUri, String localPart, String prefix) {
		this.publishingEvents.add(new QName(namespaceUri, localPart, prefix));
	}

	/* processing events  */
	/**
	 * Set the list of events could be handled by this portlet
	 * 
	 * @param processingEvents the list of QNames of supported events
	 */
	public void setProcessingEvents(List<QName> processingEvents) {
		this.processingEvents = processingEvents;
	}
	
	/**
	 * Add a QName of the supported event
	 * 
	 * @param qname the QName of the event
	 */
	public void addProcessingEvent(QName qname) {
		this.processingEvents.add(qname);
	}
	
	/**
	 * Add a QName of the supported event with the local part string specified, and 
	 * leave the others as null
	 * 
	 * @param localPart the local part string used to create a QName
	 */
	public void addProcessingEvent(String localPart) {
		this.processingEvents.add(new QName(localPart));
	}
	
	/**
	 * Add a QName of the supported event with the local part and namespace URI specified,
	 * and leave the prefix as null
	 * 
	 * @param namespaceUri the namespace URI string
	 * @param localPart the local part string
	 */
	public void addProcessingEvent(String namespaceUri, String localPart) {
		this.processingEvents.add(new QName(namespaceUri, localPart));
	}
	
	/**
	 * Add a QName of the supported event with the namespace URI, local part and prefix 
	 * specified
	 * 
	 * @param namespaceUri the namespace URI
	 * @param localPart the local part
	 * @param prefix the prefix
	 */
	public void addProcessingEvent(String namespaceUri, String localPart, String prefix) {
		this.processingEvents.add(new QName(namespaceUri, localPart, prefix));
	}

	/* supported locales */
	/**
	 * Set the supported locales
	 * 
	 * @param supportedLocales the list of supported locales
	 */
	public void setSupportedLocales(List<Locale> supportedLocales) {
		this.supportedLocales = supportedLocales;
	}
	
	/**
	 * Add a supported locale
	 * 
	 * @param locale a supported locale to add
	 */
	public void addSupportedLocale(Locale locale) {
		this.supportedLocales.add(locale);
	}
	
	/**
	 * Add a supported locale with the specified language string
	 * 
	 * @param language the language string used to create the locale
	 */
	public void addSupportedLocale(String language) {
		this.supportedLocales.add(new Locale(language));
	}
	
	/**
	 * Add a supported locale with the specified language and country
	 * 
	 * @param language the language string used to create the locale
	 * @param country the country string used to create the locale
	 */
	public void addSupportedLocale(String language, String country) {
		this.supportedLocales.add(new Locale(language, country));
	}
	
	/**
	 * Add a supported locale with the specified language, country and variant
	 * 
	 * @param language the language string used to create the locale
	 * @param country the country string used to create the locale
	 * @param variant the variant string used to create the locale
	 */
	public void addSupportedLocale(String language, String country, String variant) {
		this.supportedLocales.add(new Locale(language, country, variant));
	}
	
	/**
	 * Generates a PortletConfig instance based on the settings provided to this generator. All 
	 * previous configuration go into the object created.
	 * 
	 * @return a PortletConfig instance based on the settings provided to this generator
	 */
	public PortletConfig generatePortletConfig() {
		portletConfig.setContainerRuntimeOptions(containerRuntimeOptions);
		portletConfig.setInitParameters(initParameters);
		portletConfig.setPublicRenderParameters(publicRenderParameters);
		portletConfig.setPublishingEvents(publishingEvents);
		portletConfig.setProcessingEvents(processingEvents);
		portletConfig.setSupportedLocales(supportedLocales);
		return portletConfig;
	}
}
