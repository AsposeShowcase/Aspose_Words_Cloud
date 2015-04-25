package com.aspose.cloud.sdk.pdf;

import com.aspose.cloud.sdk.pdf.api.Document;
import com.aspose.cloud.sdk.pdf.model.DocumentResponse.DocumentData;
import com.aspose.cloud.sdk.pdf.model.MergeDocumentsRequest;
import com.aspose.cloud.sdk.pdf.model.SignatureModel;
import com.aspose.cloud.sdk.pdf.model.SignatureTypeEnum;
import com.aspose.cloud.sdk.pdf.model.SplitDocumentResponse.SplitResult;
import com.aspose.cloud.sdk.pdf.model.ValidFormatsForPresentationEnum;
import com.aspose.cloud.sdk.pdf.model.ValidTemplateTypeEnum;

import junit.framework.TestCase;

import java.io.File;

public class DocumentTest extends TestCase {

	public DocumentTest(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void testCreateEmptyPDF() throws Exception {
		DocumentData document = Document.createEmptyPDF("first.pdf");
		assertNotNull("Failed to create empty PDF file", document);
	}
	
	public void testCreatePDFFromTemplate() throws Exception {
		DocumentData document = Document.createPDFFromTemplate("PDFFromJPEG.pdf", "sample.jpg", ValidTemplateTypeEnum.jpeg, "");
		assertNotNull("Failed to create PDF file from template", document);
	}
	
	public void testConvertPDFDocumentToOtherFileFormats() throws Exception {
		String localFilePath = Document.convertPDFDocumentToOtherFileFormats("Bitcoin.pdf", ValidFormatsForPresentationEnum.doc);
		File file = new File(localFilePath);
		assertEquals("Failed to convert PDF document to designated format", true, file.exists());
	}
	
	public void testConvertLocallyStoredPDFDocumentToOtherFileFormats() throws Exception {
		String localFilePath = Document.convertLocallyStoredPDFDocumentToOtherFileFormats("/storage/emulated/0/AsposeFiles/Bitcoin.pdf", ValidFormatsForPresentationEnum.doc);
		File file = new File(localFilePath);
		assertEquals("Failed to convert locally stored PDF document to designated format", true, file.exists());
	}
	
	public void testConvertPDFFromRemoteServerToOtherFileFormats() throws Exception {
		String localFilePath = Document.convertPDFFromRemoteServerToOtherFileFormats("www.rootsoftllc.com/pdf/input.pdf", ValidFormatsForPresentationEnum.doc, "/storage/emulated/0/AsposeFiles/input.doc");
		File file = new File(localFilePath);
		assertEquals("Failed to convert PDF document uploaded at a remote server to designated format", true, file.exists());
	}
	
	public void testMergeMultiplePDFFiles() throws Exception {
		MergeDocumentsRequest mergeDocumentsRequest = new MergeDocumentsRequest();
        mergeDocumentsRequest.List.add("input1.pdf");
        mergeDocumentsRequest.List.add("input2.pdf");
        mergeDocumentsRequest.List.add("input3.pdf");
		
		DocumentData document = Document.mergeMultiplePDFFiles("Bitcoin.pdf", mergeDocumentsRequest);
		assertNotNull("Failed to merge multiple PDF files", document);
	}
	
	public void testSplitAllPagesOfAPDFFile() throws Exception {
		SplitResult result = Document.splitAllPagesOfAPDFFile("Bitcoin.pdf");
		assertNotNull("Failed to split all pages of a PDF file", result);
	}
	
	public void testSignPDFDocuments() throws Exception {
		SignatureModel signature = new SignatureModel();
		signature.SignaturePath = "Signature.pfx";
		signature.SignatureType = SignatureTypeEnum.PKCS7;
        signature.Password = "abcd";
        signature.Appearance = "Image1.jpg";
        signature.Reason = "Success";
        signature.Contact = "test@test.com";
        signature.Location = "Location1";
        signature.Visible = true;
        signature.Rectangle = signature.new Rectangle(100, 100, 400, 100);
        signature.FormFieldName = "Signature1";
        signature.Authority = "Authority1";
        
        boolean isPDFSignedSuccessfully = Document.signPDFDocuments("Bitcoin.pdf", signature);
        assertEquals("Failed to sign PDF documents", true, isPDFSignedSuccessfully);
	}
	
	public void testAppendPDFFiles() throws Exception {
		DocumentData document = Document.appendPDFFiles("Bitcoin.pdf", 2, 6, "appendFile.pdf");
		assertNotNull("Failed to append two PDF files", document);
	}
}
