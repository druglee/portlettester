/**
 * 
 */
package com.portletguru.portlettester;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.portlet.PortletConfig;
import javax.portlet.PortletContext;
import javax.xml.namespace.QName;

import com.portletguru.portlettester.mocks.MockPortletConfig;

/**
 * Helper class to create a PortletConfig instance
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
	
	public void setBundleBaseName(String baseName) {
		this.portletConfig.setBundleBaseName(baseName);
	}
	
	public void setPortletName(String portletName) {
		this.portletConfig.setPortletName(portletName);
	}
	
	public void setDefaultNamespace(String defaultNamespace) {
		this.portletConfig.setDefaultNamespace(defaultNamespace);
	}
	
	/* container runtime options */
	public void setContainerRuntimeOptions(
			Map<String, String[]> containerRuntimeOptions) {
		this.containerRuntimeOptions = containerRuntimeOptions;
	}
	
	public void addContainerRuntimeOption(String key, String[] values) {
		containerRuntimeOptions.put(key, values);
	}

	public void setPublicRenderParameters(Map<String, String> publicRenderParameters) {
		this.publicRenderParameters = publicRenderParameters;
		portletStatus.setPublicParameterMap(publicRenderParameters);
	}
	
	public void addPublicRenderParameter(String key, String value) {
		this.publicRenderParameters.put(key, value);
		portletStatus.setPublicParameterMap(publicRenderParameters);
	}

	/* publishing events  */
	public void setPublishingEvents(List<QName> publishingEvents) {
		this.publishingEvents = publishingEvents;
	}
	
	public void addPublishingEvent(QName qname) {
		this.publishingEvents.add(qname);
	}
	
	public void addPublishingEvent(String localPart) {
		this.publishingEvents.add(new QName(localPart));
	}
	
	public void addPublishingEvent(String namespaceUri, String localPart) {
		this.publishingEvents.add(new QName(namespaceUri, localPart));
	}
	
	public void addPublishingEvent(String namespaceUri, String localPart, String prefix) {
		this.publishingEvents.add(new QName(namespaceUri, localPart, prefix));
	}

	/* processing events  */
	public void setProcessingEvents(List<QName> processingEvents) {
		this.processingEvents = processingEvents;
	}
	
	public void addProcessingEvent(QName qname) {
		this.processingEvents.add(qname);
	}
	
	public void addProcessingEvent(String localPart) {
		this.processingEvents.add(new QName(localPart));
	}
	
	public void addProcessingEvent(String namespaceUri, String localPart) {
		this.processingEvents.add(new QName(namespaceUri, localPart));
	}
	
	public void addProcessingEvent(String namespaceUri, String localPart, String prefix) {
		this.processingEvents.add(new QName(namespaceUri, localPart, prefix));
	}

	/* supported locales */
	public void setSupportedLocales(List<Locale> supportedLocales) {
		this.supportedLocales = supportedLocales;
	}
	
	public void addSupportedLocale(Locale locale) {
		this.supportedLocales.add(locale);
	}
	
	public void addSupportedLocale(String language) {
		this.supportedLocales.add(new Locale(language));
	}
	
	public void addSupportedLocale(String language, String country) {
		this.supportedLocales.add(new Locale(language, country));
	}
	
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
