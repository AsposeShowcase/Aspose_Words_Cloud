package com.aspose.cloud.sdk.cells;

import java.io.File;

import com.aspose.cloud.sdk.cells.api.Pictures;
import com.aspose.cloud.sdk.cells.model.PicturesResponse;
import com.aspose.cloud.sdk.cells.model.PicturesResponse.PictureData;
import com.aspose.cloud.sdk.cells.model.ValidFormatsOfWorksheet;

import junit.framework.TestCase;

public class PicturesTestCase extends TestCase {

	private Pictures pictures;
	
	public PicturesTestCase(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
		pictures = new Pictures("myworkbook.xlsx", "Sheet1");
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void testGetPictureFromAWorksheet() throws Exception {
		PictureData pictureData = pictures.getPictureFromAWorksheet(1);
		assertNotNull("Failed to get picture from a worksheet",  pictureData);
	}

	public void testConvertPictureToImage() throws Exception {
		String localFilePath = pictures.convertPictureToImage(0, ValidFormatsOfWorksheet.png, "convertedImage.png");
		File file = new File(localFilePath);
		assertEquals("Failed to convert picture to designated format", true, file.exists());
	}
	
	public void testAddPicturesToExcelWorksheet() throws Exception {
		PictureData pictureData = pictures.addPicturesToExcelWorksheet(5, 5, 10, 32, "sample.png");
		assertNotNull("Failed to add pictures to excel worksheet",  pictureData);
	}
	
	public void testUpdateASpecificPictureFromExcelWorksheet() throws Exception {
		PicturesResponse pictureRequest = new PicturesResponse();
		PictureData pictureData = pictureRequest.new PictureData(); 
		pictureRequest.picture = pictureData;
		pictureData.Name = "test_picture";
		pictureData.AutoShapeType = "Picture";
		pictureData.Placement = "MoveAndSize";
		pictureData.UpperLeftRow = 5;
		pictureData.Top = 100;
		pictureData.UpperLeftColumn = 5;
		pictureData.Left = 100;
		pictureData.LowerRightRow = 9;
		pictureData.Bottom = 0;
		pictureData.LowerRightColumn = 11;
		pictureData.Right = 0;
		pictureData.Width = 100;
		pictureData.Height = 100;
		pictureData.X = 687;
		pictureData.Y = 100;
		pictureData.RotationAngle = 0;
	    pictureData.Text = "sfsdfsdf";
	    pictureData.TextHorizontalAlignment = "Left";
	    pictureData.TextHorizontalOverflow = "Clip";
	    pictureData.TextOrientationType = "NoRotation";
	    pictureData.TextVerticalOverflow = "Clip";
	    pictureData.TextVerticalAlignment = "Top";
	    pictureData.IsGroup = false;
	    pictureData.IsHidden = false;
	    pictureData.IsLockAspectRatio = false;
	    pictureData.IsLocked = true;
	    pictureData.IsPrintable = true;
	    pictureData.IsTextWrapped = true;
	    pictureData.ZOrderPosition = 2;
	    
	    PictureData pictureResponseData =  pictures.updateASpecificPictureFromExcelWorksheet(0, pictureRequest);
	    assertNotNull("Failed to update a specific picture from excel worksheet", pictureResponseData);
	}
	
	public void testDeleteAllPicturesFromExcelWorksheet() throws Exception {
		boolean isAllPicturesDeletedSuccessfully = pictures.deleteAllPicturesFromExcelWorksheet();
		assertEquals("Failed to delete all pictures from excel worksheet", true, isAllPicturesDeletedSuccessfully);
	}
	
	public void testDeleteASpecificPictureFromExcelWorksheet() throws Exception {
		boolean isAPictureDeletedSuccessfully = pictures.deleteASpecificPictureFromExcelWorksheet(0);
		assertEquals("Failed to delete a specific picture from excel worksheet", true, isAPictureDeletedSuccessfully);
	}
}
