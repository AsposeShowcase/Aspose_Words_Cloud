package com.aspose.cloud.sdk.cells;

import com.aspose.cloud.sdk.cells.api.Hyperlink;
import com.aspose.cloud.sdk.cells.model.HyperlinkResponse.HyperlinkData;

import junit.framework.TestCase;

public class HyperlinkTestCase extends TestCase {

	private Hyperlink hyperlink;
	
	public HyperlinkTestCase(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
		hyperlink = new Hyperlink("myworkbook.xlsx", "Sheet1");
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void testGetHyperlinkFromExcelWorksheet() throws Exception {
		HyperlinkData hyperlinkData = hyperlink.getHyperlinkFromExcelWorksheet(0);
		assertNotNull("Failed to get hyperlink from excel worksheet", hyperlinkData);
	}
	
	public void testAddHyperlinksToExcelWorksheet() throws Exception {
		HyperlinkData hyperlinkData = hyperlink.addHyperlinksToExcelWorksheet(3, 0, 1, 2, "www.banckle.com");
		assertNotNull("Failed to add hyperlinks to excel worksheet", hyperlinkData);
	}
	
	public void testUpdateHyperlinksInExcelWorksheet() throws Exception {
		
		String xmlHyperlinkRequest = "<Hyperlink>" +
										"<Address>http://www.aspose.com</Address>" +
										"<TextToDisplay>Welcome to Aspose</TextToDisplay>" +
										"<ScreenTip>Hello World</ScreenTip>" +
									"</Hyperlink>";
		
		HyperlinkData hyperlinkData = hyperlink.updateHyperlinksInExcelWorksheet(0, xmlHyperlinkRequest);
		assertNotNull("Failed to update hyperlinks in excel worksheet", hyperlinkData);
	}
	
	public void testDeleteAllHyperlinksFromExcelWorksheet() throws Exception {
		boolean isAllHyperlinksDeletedSuccessfully = hyperlink.deleteAllHyperlinksFromExcelWorksheet();
		assertEquals("Failed to delete all hyperlinks from excel worksheet", true, isAllHyperlinksDeletedSuccessfully);
	}
	
	public void testDeleteASpecificHyperlinkFromExcelWorksheet() throws Exception {
		boolean isAHyperlinkDeletedSuccessfully = hyperlink.deleteASpecificHyperlinkFromExcelWorksheet(0);
		assertEquals("Failed to delete a specific hyperlink from excel worksheet", true, isAHyperlinkDeletedSuccessfully);
	}
}
