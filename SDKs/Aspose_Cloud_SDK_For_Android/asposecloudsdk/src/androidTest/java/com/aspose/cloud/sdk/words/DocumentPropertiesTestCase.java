package com.aspose.cloud.sdk.words;

import com.aspose.cloud.sdk.words.api.DocumentProperties;
import com.aspose.cloud.sdk.words.model.GetDocumentPropertiesResponse.DocumentPropertiesModel;
import com.aspose.cloud.sdk.words.model.GetSpecificPropertyResponse.DocumentPropertyValue;

import junit.framework.TestCase;

public class DocumentPropertiesTestCase extends TestCase {

	public DocumentPropertiesTestCase(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testGetAllDocumentProperties() throws Exception {
		DocumentPropertiesModel documentProperties = DocumentProperties.getAllDocumentProperties("myworddocument.docx");
		assertNotNull("Failed to get all document properties of a document", documentProperties);
	}
	
	public void testGetAParticularDocumentProperty() throws Exception {
		DocumentPropertyValue documentProperty = DocumentProperties.getAParticularDocumentProperty("myworddocument.docx", "author");
		assertNotNull("Failed to get a particular document property of a document", documentProperty);
	}
	
	public void testSetASingleDocumentProperty() throws Exception {
		DocumentPropertyValue documentProperty = DocumentProperties.setASingleDocumentProperty("myworddocument.docx", "author", "James John");
		assertNotNull("Failed to set a single document property of a document", documentProperty);
	}
	
	public void testRemoveAParticularDocumentProperty() throws Exception {
		boolean isPropertyDeletedSuccessfully = DocumentProperties.removeAParticularDocumentProperty("myworddocument.docx", "first_property");
		assertEquals("Failed to remove a particular document property", true, isPropertyDeletedSuccessfully);
	}
	
}
