package com.aspose.cloud.sdk.barcode;

import com.aspose.cloud.sdk.barcode.api.BarcodeGeneration;
import com.aspose.cloud.sdk.barcode.model.BarcodeTypeEnum;
import com.aspose.cloud.sdk.barcode.model.CodeLocationEnum;
import com.aspose.cloud.sdk.barcode.model.EnableChecksumEnum;
import com.aspose.cloud.sdk.barcode.model.GRUnitEnum;
import com.aspose.cloud.sdk.barcode.model.ValidFormatsEnum;

import junit.framework.TestCase;

import java.io.File;

public class BarcodeGenerationTest extends TestCase {

	public BarcodeGenerationTest(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testGenerateImageWithBarcodeOfSpecifiedTypeAndParameters() throws Exception {
		String outputFilePath = BarcodeGeneration.generateImageWithBarcodeOfSpecifiedTypeAndParameters("Text of the new barcode", 
				BarcodeTypeEnum.QR, ValidFormatsEnum.PNG, "Barcode.png");
		File file = new File(outputFilePath);
		assertEquals("Failed to generate image with barcode of specified type and parameters", true, file.exists());
	}
	
	public void testCreateBarcodeOnAsposeCloudStorage() throws Exception {
		boolean isBarcodeCreatedSuccessfully = BarcodeGeneration.createBarcodeOnAsposeCloudStorage("test.png", "Text of the new barcode", BarcodeTypeEnum.code128,
				ValidFormatsEnum.PNG);
		assertEquals("Failed to create barcode on the Aspose Cloud Storage", true, isBarcodeCreatedSuccessfully);
	}
	
	public void testGenerateBarcodeWithAppropriateCodeTextLocation() throws Exception {
		boolean isBarcodeCreatedSuccessfully = BarcodeGeneration.generateBarcodeWithAppropriateCodeTextLocation("QR_Code.png", 
				"Code text here", BarcodeTypeEnum.QR, ValidFormatsEnum.PNG, CodeLocationEnum.Above);
		assertEquals("Failed to generate barcode with appropriate code text location", true, isBarcodeCreatedSuccessfully);
	}
	
	public void testGenerateBarcodeWithChecksumOption() throws Exception {
		boolean isBarcodeGeneratedSuccessfully = BarcodeGeneration.generateBarcodeWithChecksumOption("QR_Code.png", 
				"Code text here", BarcodeTypeEnum.QR, ValidFormatsEnum.PNG, EnableChecksumEnum.Default);
		assertEquals("Failed to generate barcode with checksum option", true, isBarcodeGeneratedSuccessfully);
	}
	
	public void testRotateBarcodeImageWithSuitableAngle() throws Exception {
		boolean isBarcodeRotatedSuccessfully = BarcodeGeneration.rotateBarcodeImageWithSuitableAngle("QR_Code.png", 
				"Code text here", BarcodeTypeEnum.QR, ValidFormatsEnum.PNG, 90);
		assertEquals("Failed to rotate barcode image with suitable angle", true, isBarcodeRotatedSuccessfully);
	}
	
	public void testSetBarcodeImageMargin() throws Exception {
		boolean isBarcodeImageMarginSetSuccessfully = BarcodeGeneration.setBarcodeImageMargin("QR_Code.png", "Code text here", 
				BarcodeTypeEnum.QR, ValidFormatsEnum.PNG, 2, 2, 2, 2);
		assertEquals("Failed to set barcode image margins", true, isBarcodeImageMarginSetSuccessfully);
	}
	
	public void testSetBarcodeImageResolution() throws Exception {
		String outputFilePath = BarcodeGeneration.setBarcodeImageResolution("Text of the new barcode", 
				BarcodeTypeEnum.QR, ValidFormatsEnum.PNG, 200.0, 200.0, "Barcode.png");
		File file = new File(outputFilePath);
		assertEquals("Failed to set barcode image resolution", true, file.exists());
	}
	
	public void testSetHeightOfTheBarsInTheBarcodeImage() throws Exception {
		boolean isBarcodeHeightSetSuccessfully = BarcodeGeneration.setHeightOfTheBarsInTheBarcodeImage("QR_Code.png", "Code text here", 
				BarcodeTypeEnum.QR, ValidFormatsEnum.PNG, 20, GRUnitEnum.pt);
		assertEquals("Failed to set height of the bars in the barcode image", true, isBarcodeHeightSetSuccessfully);
	}
	
	public void testSetXAndYDimensionsOfABarcode() throws Exception {
		String outputFilePath = BarcodeGeneration.setXAndYDimensionsOfABarcode("Text of the new barcode", 
				BarcodeTypeEnum.QR, ValidFormatsEnum.PNG, 1.0, 0.5, "Barcode.png");
		File file = new File(outputFilePath);
		assertEquals("Failed to set X and Y dimensions of a barcode", true, file.exists());
	}
}
