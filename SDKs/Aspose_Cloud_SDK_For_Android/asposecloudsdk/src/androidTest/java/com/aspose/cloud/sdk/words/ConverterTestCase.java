package com.aspose.cloud.sdk.words;

import java.io.File;
import java.io.InputStream;

import com.aspose.cloud.sdk.common.Utils;
import com.aspose.cloud.sdk.words.api.Converter;
import com.aspose.cloud.sdk.words.model.SaveResult;
import com.aspose.cloud.sdk.words.model.ValidFormatsEnum;
import com.aspose.cloud.sdk.storage.api.Folder;

import junit.framework.TestCase;

public class ConverterTestCase extends TestCase {

	public ConverterTestCase(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void testWordDocumentConversionToFormat() throws Exception {
		String filePath = Converter.convertWordDocumentToFormat("myworddocument.doc", ValidFormatsEnum.pdf);
		File file = new File(filePath);
		assertEquals("Failed to convert word document to designated format", true, file.exists());
	}
	
	public void testLocallyStoredWordDocumentConversionToFormat() throws Exception {
		String filePath = Converter.convertLocallyStoredWordDocumentToFormat("/storage/emulated/0/AsposeFiles/myworddocument.docx", ValidFormatsEnum.pdf);
		File file = new File(filePath);
		assertEquals("Failed to convert locally stored word document to designated format", true, file.exists());
	}
	
	public void testWordDocumentConversionToFormatWithAdditionalSettings() throws Exception {
		String rtfSaveOptionsRequest = "<RtfSaveOptions>" +
							"<SaveFormat>rtf</SaveFormat>" +
							"<FileName>TestSaveAs.rtf</FileName>" +
							"<ExportImagesForOldReaders>false</ExportImagesForOldReaders>" +
						 "</RtfSaveOptions>";
		SaveResult saveResult = Converter.convertWordDocumentToFormatWithAdditionalSettings("myworddocument.docx", rtfSaveOptionsRequest, "xml");
		assertNotNull("Failed to convert word document to designated format with additional settings", saveResult);
		
		//Get converted file from Aspose server
		InputStream responseStream = Folder.getFile(saveResult.destDocument.href);
		//Save file on Disk
		String filePath = Utils.saveStreamToFile(responseStream, saveResult.destDocument.href);
		File file = new File(filePath);
		assertEquals("Failed to save convert word document to disk", true, file.exists());
	}
	
	public void testConvertWebPagesToWordDocument() throws Exception {
		String loadWebDocumentDataRequest = "<LoadWebDocumentData>" +
												"<LoadingDocumentUrl>http://google.com</LoadingDocumentUrl>" +
												"<DocSaveOptionsData>" +
													"<SaveFormat>doc</SaveFormat>" +
													"<FileName>google.doc</FileName>" +
													"<SaveRoutingSlip>true</SaveRoutingSlip>" +
												"</DocSaveOptionsData>" +
											"</LoadWebDocumentData>";
		SaveResult saveResult = Converter.convertWebPagesToWordDocument(loadWebDocumentDataRequest, "xml");
		assertNotNull("Failed to convert web pages to Word document", saveResult);
		
		//Get converted file from Aspose server
		InputStream responseStream = Folder.getFile(saveResult.destDocument.href);
		//Save file on Disk
		String filePath = Utils.saveStreamToFile(responseStream, saveResult.destDocument.href);
		File file = new File(filePath);
		assertEquals("Failed to save convert word document to disk", true, file.exists());
	}
}
