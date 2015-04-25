package com.aspose.cloud.sdk.pdf;

import com.aspose.cloud.sdk.pdf.model.DocumentPropertiesResponse.DocumentPropertiesResult;
import com.aspose.cloud.sdk.pdf.api.DocumentProperties;
import com.aspose.cloud.sdk.pdf.model.DocumentPropertyModel;

import junit.framework.TestCase;

public class DocumentPropertiesTest extends TestCase {

	public DocumentPropertiesTest(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testGetAllDocumentPropertiesFromAPDF() throws Exception {
		DocumentPropertiesResult documentProperties = DocumentProperties.getAllDocumentPropertiesFromAPDF("Bitcoin.pdf");
		assertNotNull("Failed to get all document properties from a PDF", documentProperties);
	}
	
	public void testGetAParticularDocumentPropertyFromAPDF() throws Exception {
		DocumentPropertyModel documentProperty = DocumentProperties.getAParticularDocumentPropertyFromAPDF("Bitcoin.pdf", "author");
		assertNotNull("Failed to get a particular document property from a PDF", documentProperty);
	}
	
	public void testRemoveAllDocumentPropertiesFromAPDF() throws Exception {
		boolean isDocumentPropertiesDeletedSuccessfully = DocumentProperties.removeAllDocumentPropertiesFromAPDF("Bitcoin.pdf");
		assertEquals("Failed to remove all document properties from a PDF", true, isDocumentPropertiesDeletedSuccessfully);
	}
	
	public void testSetASingleDocumentPropertyInAPDF() throws Exception {
		DocumentPropertyModel documentProperty = DocumentProperties.setASingleDocumentPropertyInAPDF("Bitcoin.pdf", "author", "Andy");
		assertNotNull("Failed to set a single document property in a PDF", documentProperty);
	}
}
