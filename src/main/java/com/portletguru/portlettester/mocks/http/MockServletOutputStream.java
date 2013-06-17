/**
 * 
 */
package com.portletguru.portlettester.mocks.http;

import java.io.IOException;

import javax.servlet.ServletOutputStream;

import com.portletguru.portlettester.TestResultHolder;

/**
 * @author Derek Linde Li
 *
 */
public class MockServletOutputStream extends ServletOutputStream {
	
	private TestResultHolder resultHolder;
	
	public MockServletOutputStream() {
		resultHolder = TestResultHolder.getInstance();
	}

	/* (non-Javadoc)
	 * @see java.io.OutputStream#write(int)
	 */
	@Override
	public void write(int b) throws IOException {
		resultHolder.appendOutputContent((byte)b);
	}

}
