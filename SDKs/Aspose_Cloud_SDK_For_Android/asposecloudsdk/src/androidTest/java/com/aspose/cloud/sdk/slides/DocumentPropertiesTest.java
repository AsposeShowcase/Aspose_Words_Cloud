package com.aspose.cloud.sdk.slides;

import com.aspose.cloud.sdk.slides.api.DocumentProperties;
import com.aspose.cloud.sdk.slides.model.DocumentPropertiesResponse.DocumentPropertiesResult;

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
	
	public void testGetDocumentProperties() throws Exception {
		DocumentPropertiesResult documentProperties = DocumentProperties.getDocumentProperties("Effective_presentation.ppt");
		assertNotNull("Failed to get properties of a powerpoint document", documentProperties);
	}

	public void testRemoveAllProperties() throws Exception {
		DocumentPropertiesResult documentProperties = DocumentProperties.removeAllProperties("Effective_presentation.ppt");
		assertNotNull("Failed to delete all document properties", documentProperties);
	}
}
