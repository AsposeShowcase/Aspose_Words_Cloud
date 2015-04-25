package com.aspose.cloud.sdk.pdf;

import com.aspose.cloud.sdk.pdf.model.AnnotationDetails;
import com.aspose.cloud.sdk.pdf.api.Annotations;
import com.aspose.cloud.sdk.pdf.model.AnnotationsResponse.AnnotationsResult;

import junit.framework.TestCase;

public class AnnotationsTest extends TestCase {

	public AnnotationsTest(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void testGetAllAnnotationsFromAPDFPage() throws Exception {
		AnnotationsResult annotations = Annotations.getAllAnnotationsFromAPDFPage("Bitcoin.pdf", 1);
		assertNotNull("Failed to get all annotations from a PDF page", annotations);
	}

	public void testGetAnnotationsCountFromAPDFPage() throws Exception {
		int annotationsCount = Annotations.getAnnotationsCountFromAPDFPage("Bitcoin.pdf", 1);
		assertEquals("Failed to get annotations count from a PDF page", true, annotationsCount>=0);
	}
	
	public void testGetASpecificAnnotationFromAPDFPage() throws Exception {
		AnnotationDetails annotation = Annotations.getASpecificAnnotationFromAPDFPage("Bitcoin.pdf", 1, 1);
		assertNotNull("Failed to get a specific annotation from a PDF page", annotation);
	}
}
