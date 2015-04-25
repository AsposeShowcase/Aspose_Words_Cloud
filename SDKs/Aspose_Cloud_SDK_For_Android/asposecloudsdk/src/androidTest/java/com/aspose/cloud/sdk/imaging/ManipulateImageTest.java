package com.aspose.cloud.sdk.imaging;

import java.io.File;

import com.aspose.cloud.sdk.imaging.api.ManipulateImage;

import junit.framework.TestCase;

public class ManipulateImageTest extends TestCase {

	public ManipulateImageTest(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testResizeLocallyStoredImageAndChangeFormat() throws Exception {
		String updatedFilePath = ManipulateImage.resizeLocallyStoredImageAndChangeFormat("/storage/emulated/0/AsposeFiles/sample.jpg", 200, 200, "png");
		File file = new File(updatedFilePath);
		assertEquals("Failed to resize image stored on device", true, file.exists());
	}
	
	public void testResizeImageStoredOnCloundAndChangeFormat() throws Exception {
		String localFilePath = ManipulateImage.resizeImageStoredOnCloundAndChangeFormat("sample.jpg", 200, 200, "png");
		File file = new File(localFilePath);
		assertEquals("Failed to resize image stored on cloud", true, file.exists());
	}
	
	public void testCropLocallyStoredImageAndChangeFormat() throws Exception {
		String updatedFilePath = ManipulateImage.cropLocallyStoredImageAndChangeFormat("/storage/emulated/0/AsposeFiles/sample.jpg", 10, 10, 100, 100, "png");
		File file = new File(updatedFilePath);
		assertEquals("Failed to crop image stored on disk", true, file.exists());
	}
	
	public void testCropImageStoredOnCloundAndChangeFormat() throws Exception {
		String localFilePath = ManipulateImage.cropImageStoredOnCloundAndChangeFormat("sample.jpg", 10, 10, 100, 100, "png");
		File file = new File(localFilePath);
		assertEquals("Failed to crop image stored on cloud", true, file.exists());
	}
	
	public void testRotateFlipLocallyStoredImageAndChangeFormat() throws Exception {
		String updatedFilePath = ManipulateImage.rotateFlipLocallyStoredImageAndChangeFormat("/storage/emulated/0/AsposeFiles/sample.jpg", "Rotate180FlipX", "png");
		File file = new File(updatedFilePath);
		assertEquals("Failed to perform RotateFlip operation on an image stored on device", true, file.exists());
	}
	
	public void testRotateFlipImageStoredOnCloundAndChangeFormat() throws Exception {
		String localFilePath = ManipulateImage.rotateFlipImageStoredOnCloundAndChangeFormat("sample.jpg", "Rotate180FlipX", "png");
		File file = new File(localFilePath);
		assertEquals("Failed to perform RotateFlip operation on an image stored on cloud", true, file.exists());
	}
	
	public void testChangeLocallyStoredImageFormat() throws Exception {
		String updatedFilePath = ManipulateImage.changeLocallyStoredImageFormat("/storage/emulated/0/AsposeFiles/sample.jpg", "png");
		File file = new File(updatedFilePath);
		assertEquals("Failed to perform format change operation on an image stored on device", true, file.exists());
	}
	
	public void testChangeFormatOfImageStoredOnCloud() throws Exception {
		String localFilePath = ManipulateImage.changeFormatOfImageStoredOnCloud("sample.jpg", "png");
		File file = new File(localFilePath);
		assertEquals("Failed to perform format change operation on an image stored on cloud", true, file.exists());
	}
	
	public void testMergeTiffImages() throws Exception {
		boolean isTiffImagesMergedSuccessfully = ManipulateImage.mergeTiffImages("another.tiff", "append.tiff");
		assertEquals("Failed to merge TIFF files", true, isTiffImagesMergedSuccessfully);
	}
	
	public void testConvertTiffImageToFaxCompatibleFormat() throws Exception {
		String faxCompatibleFilePath = ManipulateImage.convertTiffImageToFaxCompatibleFormat("another.tiff");
		File file = new File(faxCompatibleFilePath);
		assertEquals("Failed to convert tiff image to Fax Compatible Format", true, file.exists());
	}
}
