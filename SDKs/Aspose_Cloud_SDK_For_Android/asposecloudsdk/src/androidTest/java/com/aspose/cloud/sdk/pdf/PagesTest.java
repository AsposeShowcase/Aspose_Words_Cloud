package com.aspose.cloud.sdk.pdf;

import com.aspose.cloud.sdk.pdf.model.ValidImageFormatsEnum;
import com.aspose.cloud.sdk.pdf.api.Pages;

import junit.framework.TestCase;

import java.io.File;

public class PagesTest extends TestCase {

	public PagesTest(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void testConvertAPDFPageToImageWithDefaultSize() throws Exception {
		String localFilePath = Pages.convertAPDFPageToImageWithDefaultSize("Bitcoin.pdf", 1, ValidImageFormatsEnum.png, "output.png");
		File file = new File(localFilePath);
		assertEquals("Failed to convert a PDF page to image with default size", true, file.exists());
	}
	
	public void testConvertAPDFPageToImageWithSpecifiedSize() throws Exception {
		String localFilePath = Pages.convertAPDFPageToImageWithSpecifiedSize("Bitcoin.pdf", 1, ValidImageFormatsEnum.png, 500, 500, "output.png");
		File file = new File(localFilePath);
		assertEquals("Failed to convert a PDF page to image with specified size", true, file.exists());
	}
	
	public void testAddANewPageInPDF() throws Exception {
		boolean isNewPageAddedSuccessfully = Pages.addANewPageInPDF("Bitcoin.pdf");
		assertEquals("Failed to add a new page in PDF", true, isNewPageAddedSuccessfully);
	}
	
	public void testDeletePageFromPDF() throws Exception {
		boolean isPageDeletedSuccessfully = Pages.deletePageFromPDF("Bitcoin.pdf", 1);
		assertEquals("Failed to delete a page from a PDF file", true, isPageDeletedSuccessfully);
	}
	
	public void testMovePDFPageToNewLocationInAPDFFile() throws Exception {
		boolean isPageMovedToNewLocationSuccessfully = Pages.movePDFPageToNewLocationInAPDFFile("Bitcoin.pdf", 1, 4);
		assertEquals("Failed to move page to a new location in a PDF file", true, isPageMovedToNewLocationSuccessfully);
	}
	
	public void testGetPDFDocumentPageCount() throws Exception {
		int pageCount = Pages.getPDFDocumentPageCount("Bitcoin.pdf");
		assertEquals("Failed to get page count of PDF Document", true, pageCount >= 0);
	}
}
