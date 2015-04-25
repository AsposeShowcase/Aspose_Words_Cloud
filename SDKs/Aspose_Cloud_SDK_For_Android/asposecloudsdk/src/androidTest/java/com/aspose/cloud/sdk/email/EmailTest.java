package com.aspose.cloud.sdk.email;

import java.io.File;

import com.aspose.cloud.sdk.email.api.Email;

import junit.framework.TestCase;

public class EmailTest extends TestCase {

	public EmailTest(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void testConvertEmailToFormat() throws Exception {
		String localFilePath = Email.convertEmailToFormat("Message.msg", "eml");
		File file = new File(localFilePath);
		assertEquals("Failed to convert email messages back and forth between EML, MSG and MHT formats", true, file.exists());
	}
	
	public void testGetEmailAttachment() throws Exception {
		String localAttachmentPath = Email.getEmailAttachment("Message.msg", "License.txt");
		File file = new File(localAttachmentPath);
		assertEquals("Failed to download attachment from Message", true, file.exists());
	}

	public void testGetEmailProperty() throws Exception {
		String propertyName = "Body";
		String propertyValue = Email.getEmailProperty("Message.msg", propertyName);
		assertNotNull("Failed to retrieve value of message property " + propertyName, propertyValue);
	}

	public void testSetEmailProperty() throws Exception {
		String propertyName = "Body";
		boolean isPropertyUpdated = Email.setEmailProperty("Message.msg", propertyName, "New body text");
		assertEquals("Failed to set value of message property " + propertyName, true, isPropertyUpdated);
	}
}
