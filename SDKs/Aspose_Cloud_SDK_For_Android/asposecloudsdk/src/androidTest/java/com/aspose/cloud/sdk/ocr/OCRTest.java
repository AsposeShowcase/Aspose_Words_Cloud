package com.aspose.cloud.sdk.ocr;

import com.aspose.cloud.sdk.ocr.model.LanguageEnum;
import com.aspose.cloud.sdk.ocr.model.OCRResponse;
import com.aspose.cloud.sdk.ocr.api.OCR;

import junit.framework.TestCase;

public class OCRTest extends TestCase {

	public OCRTest(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void testExtractOCRorHOCRTextFromImages() throws Exception {
		OCRResponse ocrResponse = OCR.extractOCRorHOCRTextFromImages("recoginize_alphabets.bmp", LanguageEnum.English, true);
		assertNotNull("Failed to extract text from a BMP or TIFF image", ocrResponse);
	}
	
	public void testExtractOCRorHOCRTextFromASpecificBlock() throws Exception {
		OCRResponse ocrResponse = OCR.extractOCRorHOCRTextFromASpecificBlock("sample.bmp", LanguageEnum.English, 0, 0, 501, 316, true);
		assertNotNull("Failed to extract text from a specific block of an image", ocrResponse);
	}
	
	public void testExtractImageTextFromlocalFile() throws Exception {
		OCRResponse ocrResponse = OCR.extractImageTextFromlocalFile("/storage/emulated/0/AsposeFiles/sample.bmp", LanguageEnum.English, true);
		assertNotNull("Failed to extract image text from local file", ocrResponse);
	}
}
