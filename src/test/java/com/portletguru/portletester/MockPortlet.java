package com.portletguru.portletester;

import java.io.IOException;
import java.util.Collections;
import java.util.Enumeration;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.GenericPortlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

/**
 * This portlet is used to prove all public APIs are working as expected
 * 
 * @author Derek Linde Li
 *
 */
public class MockPortlet extends GenericPortlet {
	
	public static final String TEST_ACTION_ATTRIBUTE = "testActionAttribute";
	public static final String TEST_ACTION_ATTRIBUTE_SIZE = "testActionAttributeSize";
	public static final String TEST_ACTION_ATTRIBUTE_VALUE = "testActionAttributeValue";
	public static final String TEST_ACTION_PARAM = "testActionParam";
	public static final String TEST_ACTION_PARAM_VALUE = "testActionParamValue";
	public static final String TEST_ACTION_PARAM_SIZE = "testActionParamSize";
	
	@Override
	protected void doView(RenderRequest request, RenderResponse response)
			throws PortletException, IOException {
		// TODO Auto-generated method stub
		super.doView(request, response);
	}
	
	@Override
	protected void doEdit(RenderRequest request, RenderResponse response)
			throws PortletException, IOException {
		// TODO Auto-generated method stub
		super.doEdit(request, response);
	}
	
	public void portletRequestTest(ActionRequest request, ActionResponse response) throws IOException, PortletException {
		/* test attributes are working */
		Enumeration<String> attributeNames = request.getAttributeNames();
		response.setRenderParameter(TEST_ACTION_ATTRIBUTE_SIZE, String.valueOf(Collections.list(attributeNames).size()));
		String testAttrValue = (String) request.getAttribute(TEST_ACTION_ATTRIBUTE);
		response.setRenderParameter(TEST_ACTION_ATTRIBUTE, testAttrValue);
		/* test parameters are working */
		Enumeration<String> paramNames = request.getParameterNames();
		response.setRenderParameter(TEST_ACTION_PARAM_SIZE, String.valueOf(Collections.list(paramNames).size()));
		String testParamValue = request.getParameter(TEST_ACTION_PARAM);
		response.setRenderParameter(TEST_ACTION_PARAM, testParamValue);
		
	}
}
