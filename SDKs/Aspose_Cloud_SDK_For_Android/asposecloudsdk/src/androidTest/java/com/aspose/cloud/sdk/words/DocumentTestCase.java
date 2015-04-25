package com.aspose.cloud.sdk.words;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;

import com.aspose.cloud.sdk.common.Utils;
import com.aspose.cloud.sdk.words.api.Document;
import com.aspose.cloud.sdk.storage.api.Folder;
import com.aspose.cloud.sdk.words.model.StatisticsOfDocumentResponse;
import com.aspose.cloud.sdk.words.model.TrackingChangesResponse.TrackChangesResult;
import com.aspose.cloud.sdk.words.model.ValidFormatsEnum;

import junit.framework.TestCase;

public class DocumentTestCase extends TestCase {

	public DocumentTestCase(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void testAppendDocument() throws Exception {
		//Create the string array to hold document names
	    String[] appendDocs = {"TestAppendTemplate1.doc","TestAppendTemplate2.doc"};
        //Create the string array to hold import format modes
    	String[] importFormatsModes = {"KeepSourceFormatting", "UseDestinationStyles"};
    	String fileName = "myworddocument.docx";
    	boolean response = Document.appendDocument(fileName, appendDocs, importFormatsModes, "TempWords");
    	assertEquals("Failed to append a list of word documents", true, response);
    	
    	//Get appended file from Cloud
		InputStream responseStream = Folder.getFile(fileName);
		//Save file on Disk
		String filePath = Utils.saveStreamToFile(responseStream, fileName);
		File file = new File(filePath);
		assertEquals("Failed to save convert word document to disk", true, file.exists());
	}
	
	public void testSplitAllPagesToNewPDFs() throws Exception {
		ArrayList<String> filePaths = Document.splitAllPagesToNewPDFs("myworddocument.docx");
		assertNotNull("Failed to split all pages to new PDFs", filePaths);
	}
	
	public void testSplitSpecificPagesToFormat() throws Exception {
		ArrayList<String> filePaths = Document.splitSpecificPagesToFormat("myworddocument.docx", ValidFormatsEnum.pdf, 1, 4);
		assertNotNull("Failed to split specific pages to designated format", filePaths);
	}
	
	public void testAcceptAllTrackingChanges() throws Exception {
		TrackChangesResult acceptTrackChangesResult = Document.acceptAllTrackingChanges("myworddocument.docx", "acceptedtrackingchanges.docx");
		assertNotNull("Failed to accept all tracking changes in the document", acceptTrackChangesResult);
	}
	
	public void testRejectAllTrackingChanges() throws Exception {
		TrackChangesResult rejectTrackChangesResult = Document.rejectAllTrackingChanges("myworddocument.docx", "rejectedtrackingchanges.docx");
		assertNotNull("Failed to reject all tracking changes in the document", rejectTrackChangesResult);
	}
	
	public void testStatisticsOfDocument() throws Exception {
		StatisticsOfDocumentResponse statisticsOfDocument = Document.statisticsOfDocument("myworddocument.docx");
		assertNotNull("Failed to find statistics of the document", statisticsOfDocument);
	}
}
