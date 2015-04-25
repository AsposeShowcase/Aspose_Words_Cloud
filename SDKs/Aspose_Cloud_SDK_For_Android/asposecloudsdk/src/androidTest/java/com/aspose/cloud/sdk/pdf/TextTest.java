package com.aspose.cloud.sdk.pdf;

import com.aspose.cloud.sdk.pdf.model.MultipleTextReplacesRequest;
import com.aspose.cloud.sdk.pdf.api.Text;
import com.aspose.cloud.sdk.pdf.model.TextFormatResponse.TextFormat;
import com.aspose.cloud.sdk.pdf.model.TextItemsResponse.TextItems;
import com.aspose.cloud.sdk.pdf.model.TextReplace;

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
	
	public void testGetAllTextItemsFromAParticularFragmentOfAPDF() throws Exception {
		TextItems textItems = Text.getAllTextItemsFromAParticularFragmentOfAPDF("Bitcoin.pdf", 1, 1);
		assertNotNull("Failed to get all text items from a particular fragment of a PDF", textItems);
	}
	
	public void testGetAllTextItemsFromAPDFDocument() throws Exception {
		TextItems textItems = Text.getAllTextItemsFromAPDFDocument("Bitcoin.pdf");
		assertNotNull("Failed to get all text items from a PDF document", textItems);
	}
	
	public void testGetAllTextItemsFromAPDFPage() throws Exception {
		TextItems textItems = Text.getAllTextItemsFromAPDFPage("Bitcoin.pdf", 1);
		assertNotNull("Failed to get all text items from a PDF page", textItems);
	}
	
	public void testGetFragmentCountFromAPDFPage() throws Exception {
		int fragmentsCount = Text.getFragmentCountFromAPDFPage("Bitcoin.pdf", 1);
		assertEquals("Failed to get fragment count from a particular page", true, fragmentsCount >= 0);
	}
	
	public void testGetSegmentCountFromAPDFFragment() throws Exception {
		int segmentCount = Text.getSegmentCountFromAPDFFragment("Bitcoin.pdf", 1, 1);
		assertEquals("Failed to get segment count from a PDF fragment", true, segmentCount >= 0);
	}
	
	public void testGetTextFormatOfAParticularSegment() throws Exception {
		TextFormat textFormat = Text.getTextFormatOfAParticularSegment("Bitcoin.pdf", 1, 1, 1);
		assertNotNull("Failed to get text format of a particular segment", textFormat);
	}
	
	public void testGetTextFormatOfAPDFFragment() throws Exception {
		TextFormat textFormat = Text.getTextFormatOfAPDFFragment("Bitcoin.pdf", 1, 1);
		assertNotNull("Failed to get text format of a PDF fragment", textFormat);
	}
	
	public void testReplaceMultipleTextsInAPDF() throws Exception {
		MultipleTextReplacesRequest multipleTextReplacesRequest = new MultipleTextReplacesRequest();
		TextReplace textReplace = new TextReplace();
		textReplace.NewValue = "Money";
		textReplace.OldValue = "Cash";
		textReplace.Regex = false;
		multipleTextReplacesRequest.TextReplaces.add(textReplace);
		
		textReplace = new TextReplace();
		textReplace.NewValue = "would";
		textReplace.OldValue = "should";
		textReplace.Regex = false;
		multipleTextReplacesRequest.TextReplaces.add(textReplace);
		
		
		int matchesCount = Text.replaceMultipleTextsInAPDF("Bitcoin.pdf", multipleTextReplacesRequest);
		assertEquals("Failed to replace multiple texts in a PDF", true, matchesCount >= 0);
	}
	
	public void testReplaceMultipleTextsInAPDFPage() throws Exception {
		MultipleTextReplacesRequest multipleTextReplacesRequest = new MultipleTextReplacesRequest();
		TextReplace textReplace = new TextReplace();
		textReplace.NewValue = "Money";
		textReplace.OldValue = "Cash";
		textReplace.Regex = false;
		multipleTextReplacesRequest.TextReplaces.add(textReplace);
		
		textReplace = new TextReplace();
		textReplace.NewValue = "would";
		textReplace.OldValue = "should";
		textReplace.Regex = false;
		multipleTextReplacesRequest.TextReplaces.add(textReplace);
		
		
		int matchesCount = Text.replaceMultipleTextsInAPDFPage("Bitcoin.pdf", 1, multipleTextReplacesRequest);
		assertEquals("Failed to replace multiple texts in a PDF page", true, matchesCount >= 0);
	}
	
	public void testReplaceTextInAPDFFile() throws Exception {
		
		TextReplace textReplace = new TextReplace();
		textReplace.NewValue = "Money";
		textReplace.OldValue = "Cash";
		textReplace.Regex = false;
		
		int matchesCount = Text.replaceTextInAPDFFile("Bitcoin.pdf", textReplace);
		assertEquals("Failed to replace text in a PDF file", true, matchesCount >= 0);
	}
	
	public void testReplaceTextInAPDFPage() throws Exception {
		
		TextReplace textReplace = new TextReplace();
		textReplace.NewValue = "Money";
		textReplace.OldValue = "Cash";
		textReplace.Regex = false;
		
		int matchesCount = Text.replaceTextInAPDFPage("Bitcoin.pdf", 1, textReplace);
		assertEquals("Failed to replace text in a PDF page", true, matchesCount >= 0);
	}
}
