package com.aspose.cloud.sdk.slides;

import com.aspose.cloud.sdk.slides.api.Text;
import com.aspose.cloud.sdk.slides.model.TextItemsResponse.TextItemsData;

import junit.framework.TestCase;

public class TextTest extends TestCase {

	public TextTest(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void testGetAllTextItemsFromAPresentation() throws Exception {
		TextItemsData textItems = Text.getAllTextItemsFromAPresentation("Effective_presentation.ppt");
		assertNotNull("Failed to get all text items from a presentation", textItems);
	}
	
	public void testGetAllTextItemsFromASlide() throws Exception {
		TextItemsData textItems = Text.getAllTextItemsFromASlide("Effective_presentation.ppt", 1);
		assertNotNull("Failed to get all text items from a Slide", textItems);
	}
	
	public void testReplaceAllTextInstancesInAPresentation() throws Exception {
		int matches = Text.replaceAllTextInstancesInAPresentation("Effective_presentation.ppt", "OldText", "NewText", true);
		assertEquals("Failed to replace all instances of a text in a Presentation", true, matches>=0);
	}
	
	public void testReplaceAllTextInstancesInASlide() throws Exception {
		int matches = Text.replaceAllTextInstancesInASlide("Effective_presentation.ppt", 1, "OldText", "NewText", true);
		assertEquals("Failed to replace all instances of a text in a slide", true, matches>=0);
	}
}
