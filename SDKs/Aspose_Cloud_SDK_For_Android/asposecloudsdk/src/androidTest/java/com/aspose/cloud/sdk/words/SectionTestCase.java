package com.aspose.cloud.sdk.words;

import com.aspose.cloud.sdk.words.api.Section;
import com.aspose.cloud.sdk.words.model.GetSectionListResponse.SectionList;
import com.aspose.cloud.sdk.words.model.GetSpecificSectionResponse.SectionDetails;

import junit.framework.TestCase;

public class SectionTestCase extends TestCase {

	public SectionTestCase(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testGetListOfSectionsFromAWordDocument() throws Exception {
		SectionList sectionsList = Section.getListOfSectionsFromAWordDocument("myworddocument.docx");
		assertNotNull("Failed to get a list of all sections present in a Word document", sectionsList);
	}
	
	public void testGetASpecificSectionFromAWordDocument() throws Exception {
		SectionDetails section = Section.getASpecificSectionFromAWordDocument("myworddocument.docx", 1);
		assertNotNull("Failed to get a specific section from a Word document", section);
	}
	
	public void testDeleteAllHeadersAndFootersFromTheDocument() throws Exception {
		boolean isAllHeadersAndFootersDeletedSuccessfully = Section.deleteAllHeadersAndFootersFromTheDocument("myworddocument.docx");
		assertEquals("Failed to delete all headers and footers of a word document", true, isAllHeadersAndFootersDeletedSuccessfully);
	}
}
