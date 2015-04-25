package com.aspose.cloud.sdk.pdf;

import com.aspose.cloud.sdk.pdf.api.Images;
import com.aspose.cloud.sdk.pdf.model.ValidImageFormatsEnum;

import junit.framework.TestCase;

import java.io.File;

public class ImagesTest extends TestCase {

	public ImagesTest(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testExtractAParticularImageFromAPDFPageWithDefaultSize() throws Exception {
		String localFilePath = Images.extractAParticularImageFromAPDFPageWithDefaultSize("Bitcoin.pdf", 1, 1, 
				ValidImageFormatsEnum.png, "/storage/emulated/0/AsposeFiles/output.png");
		File file = new File(localFilePath);
		assertEquals("Failed to extract a particular image from a PDF page with default size", true, file.exists());
	}
	
	public void testExtractAParticularImageFromAPDFPageWithSpecifiedSize() throws Exception {
		String localFilePath = Images.extractAParticularImageFromAPDFPageWithSpecifiedSize("Bitcoin.pdf", 1, 1, 
				ValidImageFormatsEnum.png, 500, 500, "/storage/emulated/0/AsposeFiles/output.png");
		File file = new File(localFilePath);
		assertEquals("Failed to extract a particular image from a PDF page with specified size", true, file.exists());
	}
	
	public void testReplaceImageInAPDFFileUsingImageFile() throws Exception {
		boolean isImageReplacedSuccessfully = Images.replaceImageInAPDFFileUsingImageFile("Bitcoin.pdf", 1, 1, "newImage.png");
		assertEquals("Failed to replace image in a PDF File using image file", true, isImageReplacedSuccessfully);
	}
	
	public void testGetImageCountFromAPDFPage() throws Exception {
		int imageCount = Images.getImageCountFromAPDFPage("Bitcoin.pdf", 1);
		assertEquals("Failed to get image count from a PDF page", true, imageCount>=0);
	}
}
