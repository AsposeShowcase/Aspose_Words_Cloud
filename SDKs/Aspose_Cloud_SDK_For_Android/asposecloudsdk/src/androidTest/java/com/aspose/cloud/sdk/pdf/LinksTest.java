package com.aspose.cloud.sdk.pdf;

import com.aspose.cloud.sdk.pdf.model.LinkDetails;
import com.aspose.cloud.sdk.pdf.api.Links;
import com.aspose.cloud.sdk.pdf.model.PdfLinksResponse.PdfLinksResult;

import junit.framework.TestCase;

public class LinksTest extends TestCase {

	public LinksTest(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testGetAllLinksFromAPDFPage() throws Exception {
		PdfLinksResult links = Links.getAllLinksFromAPDFPage("Bitcoin.pdf", 1);
		assertNotNull("Failed to get all links from a PDF page", links);
	}

	public void testGetLinkCountFromAPDFPage() throws Exception {
		int linkCount = Links.getLinkCountFromAPDFPage("Bitcoin.pdf", 1);
		assertEquals("Failed to get links count from a PDF page", true, linkCount>=0);
	}
	
	public void testGetASpecificLinkFromAPDFPage() throws Exception {
		LinkDetails link =  Links.getASpecificLinkFromAPDFPage("Bitcoin.pdf", 1, 1);
		assertNotNull("Failed to get a specific link from a PDF page", link);
	}
	
}
