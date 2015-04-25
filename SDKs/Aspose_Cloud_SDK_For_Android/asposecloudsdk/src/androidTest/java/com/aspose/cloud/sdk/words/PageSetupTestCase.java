package com.aspose.cloud.sdk.words;

import com.aspose.cloud.sdk.words.api.PageSetup;
import com.aspose.cloud.sdk.words.model.PageSetupProperties;

import junit.framework.TestCase;

public class PageSetupTestCase extends TestCase {

	public PageSetupTestCase(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testExtractPageSetupInformationOfASectionFromAWordDocument() throws Exception {
		
		PageSetupProperties pageSetupProperties = PageSetup.extractPageSetupInformationOfASectionFromAWordDocument("myworddocument.docx", 0);
		assertNotNull("Failed to extract page setup information of a section from a Word Document", pageSetupProperties);
	}
	
	public void testUpdatePageSetupOfASectionInAWordDocument() throws Exception {
		String xmlData = "<PageSetup>" +        
							"<LeftMargin>99</LeftMargin>" +    
							"<Orientation>Landscape</Orientation>" +    
							"<PaperSize>A5</PaperSize>" +
						 "</PageSetup>";
		
		PageSetupProperties pageSetupProperties = PageSetup.updatePageSetupOfASectionInAWordDocument("myworddocument.docx", 0, xmlData, "xml");
		assertNotNull("Failed to update page setup of a section in a Word Document", pageSetupProperties);
	}
}
