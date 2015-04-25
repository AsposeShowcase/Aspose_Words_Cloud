package com.aspose.cloud.sdk.slides;

import com.aspose.cloud.sdk.slides.model.ValidFormatsEnum;
import com.aspose.cloud.sdk.slides.api.Document;
import com.aspose.cloud.sdk.slides.model.DocumentResponse.DocumentModel;
import com.aspose.cloud.sdk.slides.model.MergePresentationsRequest;
import com.aspose.cloud.sdk.slides.model.MergeSelectedSlidesOfPowerPointPresentationsRequest;
import com.aspose.cloud.sdk.slides.model.MergeSelectedSlidesOfPowerPointPresentationsRequest.PathAndSlides;
import com.aspose.cloud.sdk.slides.model.SplitPowerPointPresentationsResponse.SplitResult;
import com.aspose.cloud.sdk.slides.model.ValidSlidesFormats;

import junit.framework.TestCase;

import java.io.File;
import java.util.HashMap;

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
	
	public void testCreateEmptyPowerPointPresentation() throws Exception {
		DocumentModel document = Document.createEmptyPowerPointPresentation("Sales.pptx");
		assertNotNull("Failed to create a new empty presentation", document);
	}
	
	public void testConvertPowerPointDocumentsToOtherFileFormats() throws Exception {
		String localFilePath = Document.convertPowerPointDocumentToOtherFileFormats("Effective_presentation.ppt", ValidFormatsEnum.pdf);
		File file = new File(localFilePath);
		assertEquals("Failed to convert powerpoint document to designated format", true, file.exists());
	}
	
	public void testConvertPowerPointDocumentToOtherFileFormatsWithAdditionalSettings() throws Exception {
		HashMap<String, String> exportOptions = new HashMap<String, String>();
		exportOptions.put(Document.JPEGQUALITY_KEY, "50");
		
		String localFilePath = Document.convertPowerPointDocumentToOtherFileFormatsWithAdditionalSettings("Effective_presentation.ppt", 
				ValidFormatsEnum.pdf, exportOptions);
		File file = new File(localFilePath);
		assertEquals("Failed to convert powerpoint document to designated format with additional settings", true, file.exists());
	}
	
	public void testConvertLocallyStoredPowerPointDocumentToOtherFileFormats() throws Exception {
		String localFilePath = Document.convertLocallyStoredPowerPointDocumentToOtherFileFormats("/storage/emulated/0/AsposeFiles/Effective_presentation.ppt", ValidFormatsEnum.pdf);
		File file = new File(localFilePath);
		assertEquals("Failed to convert locally stored powerpoint document to designated format", true, file.exists());
	}
	
	public void testMergePowerPointPresentations() throws Exception {
		MergePresentationsRequest mergePresentationsRequest = new MergePresentationsRequest();
		mergePresentationsRequest.presentationPaths.add("Pres1.pptx");
		mergePresentationsRequest.presentationPaths.add("Pres2.pptx");
		
		DocumentModel document = Document.mergePowerPointPresentations("Sales.pptx", mergePresentationsRequest);
		assertNotNull("Failed to merge multiple presentation files", document);
	}
	
	public void testMergeSelectedSlidesOfPowerPointPresentations() throws Exception {
		MergeSelectedSlidesOfPowerPointPresentationsRequest mergePresentationsRequest = new MergeSelectedSlidesOfPowerPointPresentationsRequest();
		PathAndSlides pathAndSlides = mergePresentationsRequest.new PathAndSlides();
		pathAndSlides.path = "Pres1.pptx";
		pathAndSlides.slides.add(2);
		pathAndSlides.slides.add(1);
		mergePresentationsRequest.presentations.add(pathAndSlides);
		
		DocumentModel document = Document.mergeSelectedSlidesOfPowerPointPresentations("Sales.pptx", mergePresentationsRequest);
		assertNotNull("Failed to merge selected slides of powerpoint presentations", document);
	}
	
	public void testSplitAllSlidesOfAPresentationFile() throws Exception {
		SplitResult splitResult = Document.splitPowerPointPresentations("Sales.pptx");
		assertNotNull("Failed to split all slides of a presentation file", splitResult);
	}
	
	public void testSplitSpecificSlidesOfAPresentationFile() throws Exception {
		SplitResult splitResult = Document.splitPowerPointPresentations("Sales.pptx", 2, 3, ValidSlidesFormats.png);
		assertNotNull("Failed to split specific slides of a presentation file", splitResult);
	}
}
