package com.aspose.cloud.sdk.cells;


import com.aspose.cloud.sdk.cells.api.DocumentProperties;
import com.aspose.cloud.sdk.cells.model.GetDocumentPropertiesResponse.DocumentPropertiesModel;
import com.aspose.cloud.sdk.cells.model.GetSpecificPropertyResponse.DocumentPropertyValue;

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
		DocumentPropertiesModel documentProperties = DocumentProperties.getAllDocumentProperties("myworkbook.xlsx");
		assertNotNull("Failed to get all document properties of a document", documentProperties);
	}
	
	public void testGetAParticularDocumentProperty() throws Exception {
		DocumentPropertyValue documentProperty = DocumentProperties.getAParticularDocumentProperty("myworkbook.xlsx", "author");
		assertNotNull("Failed to get a particular document property of a document", documentProperty);
	}
	
	public void testSetASingleDocumentProperty() throws Exception {
		DocumentPropertyValue documentProperty = DocumentProperties.setASingleDocumentProperty("myworkbook.xlsx", "author", "James John");
		assertNotNull("Failed to set a single document property of a document", documentProperty);
	}
	
	public void testRemoveAParticularDocumentProperty() throws Exception {
		boolean isPropertyDeletedSuccessfully = DocumentProperties.removeAParticularDocumentProperty("myworkbook.xlsx", "first_property");
		assertEquals("Failed to remove a particular document property", true, isPropertyDeletedSuccessfully);
	}
	
	public void testDeleteAllCustomAndResetBuiltInPropertiesToDefaultValues() throws Exception {
		boolean isAllCustomPropertiesDeletedSuccessfully = DocumentProperties.deleteAllCustomAndResetBuiltInPropertiesToDefaultValues("myworkbook.xlsx");
		assertEquals("Failed to delete all custom properties", true, isAllCustomPropertiesDeletedSuccessfully);
	}
}
