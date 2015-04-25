package com.aspose.cloud.sdk.words;

import com.aspose.cloud.sdk.words.api.Watermark;
import com.aspose.cloud.sdk.words.model.DocumentResponse.Document;

import junit.framework.TestCase;

public class WatermarkTestCase extends TestCase {

	public WatermarkTestCase(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void testAddWatermarkTextToAWordDocument() throws Exception {
		Document document = Watermark.addWatermarkTextToAWordDocument("myworddocument.docx", "Watermark text", 45.0);
		assertNotNull("Failed to add watermark text to a Word document", document);
	}
	
	public void testAddWatermarkImageToAWordDocument() throws Exception {
		Document document = Watermark.addWatermarkImageToAWordDocument("myworddocument.docx", "bookmark.png", -60.0);
		assertNotNull("Failed to add watermark image to a Word document", document);
	}
	
	public void testRemoveWatermarkFromAWordDocument() throws Exception {
		Document document = Watermark.removeWatermarkFromAWordDocument("myworddocument.docx");
		assertNotNull("Failed to remove watermark from a Word Document", document);
	}
}
