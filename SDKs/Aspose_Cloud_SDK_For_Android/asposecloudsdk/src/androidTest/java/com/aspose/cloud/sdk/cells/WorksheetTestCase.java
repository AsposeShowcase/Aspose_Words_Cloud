package com.aspose.cloud.sdk.cells;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.aspose.cloud.sdk.cells.model.PositionEnum;
import com.aspose.cloud.sdk.cells.model.SortKey;
import com.aspose.cloud.sdk.cells.model.ValidFormatsOfWorksheet;
import com.aspose.cloud.sdk.cells.api.Worksheet;
import com.aspose.cloud.sdk.cells.model.CalculateFormulaResponse.CalculateFormulaResult;
import com.aspose.cloud.sdk.cells.model.GetAutoshapeFromAWorksheetResponse.AutoShape;
import com.aspose.cloud.sdk.cells.model.GetColumnFromAWorksheetResponse.Column;
import com.aspose.cloud.sdk.cells.model.GetCommentFromAWorksheetResponse.Comment;
import com.aspose.cloud.sdk.cells.model.GetValidationFromAWorksheetResponse.Validation;
import com.aspose.cloud.sdk.cells.model.WorksheetResponse.WorksheetResult;

import junit.framework.TestCase;

public class WorksheetTestCase extends TestCase {
	
	private Worksheet worksheet;
	
	public WorksheetTestCase(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
		worksheet = new Worksheet("myworkbook.xlsx", "Sheet1");
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void testConvertWorksheetToImage() throws Exception {
		String localFilePath = worksheet.convertWorksheetToImage(ValidFormatsOfWorksheet.png);
		File file = new File(localFilePath);
		assertEquals("Failed to convert a worksheet to designated image format", true, file.exists());
	}

	public void testAddANewWorksheet() throws Exception {
		WorksheetResult worksheetResult = worksheet.addANewWorksheet("newWorksheet");
		assertNotNull("Failed to add a new worksheet in a workbook", worksheetResult);
	}
	
	public void testGetWorksheetCount() throws Exception {
		int worksheetCount = worksheet.getWorksheetCount();
		boolean worksheetCountResult = worksheetCount >= 0 ? true : false;
		assertEquals("Failed to get worksheet count", true, worksheetCountResult);
	}
	
	public void testHideAWorksheetInAWorkbook() throws Exception {
		boolean isWorksheetHideSuccessfully = worksheet.hideAWorksheetInAWorkbook();
		assertEquals("Failed to hide a worksheet in a workbook", true, isWorksheetHideSuccessfully);
	}
	
	public void testUnhideAWorksheetInAWorkbook() throws Exception {
		boolean isWorksheetUnHideSuccessfully = worksheet.unhideAWorksheetInAWorkbook();
		assertEquals("Failed to unhide a worksheet in a workbook", true, isWorksheetUnHideSuccessfully);
	}
	
	public void testMoveAWorksheetToANewLocationInAWorkbook() throws Exception {
		boolean isWorksheetMovedSuccessfully = worksheet.moveAWorksheetToANewLocationInAWorkbook("newWorksheet", PositionEnum.AFTER);
		assertEquals("Failed to move a worksheet to a new location in a workbook", true, isWorksheetMovedSuccessfully);
	}
	
	public void testRemoveAWorksheetFromAWorkbook() throws Exception {
		boolean isWorksheetRemovedSuccessfully = worksheet.removeAWorksheetFromAWorkbook();
		assertEquals("Failed to remove a worksheet from a workbook", true, isWorksheetRemovedSuccessfully);
	}
	
	public void testGetAutoshapeFromAWorksheet() throws Exception {
		AutoShape autoShape = worksheet.getAutoshapeFromAWorksheet(1);
		assertNotNull("Failed to get autoshape from a worksheet", autoShape);
	}
	
	public void testGetCommentFromAWorksheet() throws Exception {
		Comment comment = worksheet.getCommentFromAWorksheet("A1");
		assertNotNull("Failed to get a comment from a worksheet", comment);
	}
	 
	public void testGetValidationFromAWorksheet() throws Exception {
		Validation validation = worksheet.getValidationFromAWorksheet(0);
		assertNotNull("Failed to get validation from a worksheet ", validation);
	}
	
	public void testCalculateFormulaInAWorksheet() throws Exception {
		CalculateFormulaResult value = worksheet.calculateFormulaInAWorksheet("AVERAGE(B1:B18)");
		assertNotNull("Failed to calculate formula in a worksheet", value);
	}
	
	public void testSortWorksheetData() throws Exception {
		List<SortKey> keyList = new ArrayList<SortKey>();
		SortKey sort = new SortKey();
		sort.Key = 3;
		sort.SortOrder = "Ascending";
		keyList.add(sort);
		sort = new SortKey();
		sort.Key = 4;
		sort.SortOrder = "Ascending";
		keyList.add(sort);
		sort = new SortKey();
		sort.Key = 5;
		sort.SortOrder = "Ascending";
		keyList.add(sort);
		boolean isWorksheetDataSortedSuccessfully = worksheet.sortWorksheetData("D8:F17", false, false, false, keyList);
		assertEquals("Failed to sort worksheet data", true, isWorksheetDataSortedSuccessfully);
	}
	
	public void testGetColumnFromAWorksheet() throws Exception {
		Column column = worksheet.getColumnFromAWorksheet(0);
		assertNotNull("Failed to get column from a worksheet ", column);
	}
	
	public void testCopyAWorksheet() throws Exception {
		boolean isWorksheetCopiedSuccessfully = worksheet.copyAWorksheet("Second Sheet");
		assertEquals("Failed to copy a worksheet", true, isWorksheetCopiedSuccessfully);
	}
	
	public void testRenameAWorksheet() throws Exception {
		boolean isWorksheetRenamedSuccessfully = worksheet.renameAWorksheet("Last Sheet");
		assertEquals("Failed to rename a worksheet", true, isWorksheetRenamedSuccessfully);
	}
	
	public void testUpdatePropertiesOfAWorksheet() throws Exception {
		String xmlData = 
				"<Worksheet>" +
					"<Type>Worksheet</Type>" +
					"<Name>Last Sheet</Name>" +
					"<Zoom>80</Zoom>" +
					"<IsGridlinesVisible>False</IsGridlinesVisible>" +
					"<ViewType>PageLayoutView</ViewType>" +
					"<VisibilityType>Visible</VisibilityType>" +
					"<IsVisible>True</IsVisible>" +
				"</Worksheet>";
		
		boolean isWorksheetPropertiesUpdatedSuccessfully = worksheet.updatePropertiesOfAWorksheet(xmlData);
		assertEquals("Failed to update properties of a worksheet", true, isWorksheetPropertiesUpdatedSuccessfully);
	}
	
	public void testSetABackgroundImageOrWatermarkImageForAWorksheet() throws Exception {
		boolean isBackgroundImageSetSuccessfully = worksheet.setABackgroundImageOrWatermarkImageForAWorksheet("sample.png");
		assertEquals("Failed to set a background image or watermark image for a worksheet", true, isBackgroundImageSetSuccessfully);
	}
	
	public void testFreezePanesInAWorksheet() throws Exception {
		boolean isPanesFreezedSuccessfully = worksheet.freezePanesInAWorksheet(1, 1, 1, 1);
		assertEquals("Failed to freeze panes in a worksheet", true, isPanesFreezedSuccessfully);
	}
	
	public void testUnFreezePanesInAWorksheet() throws Exception {
		boolean isPanesFreezedSuccessfully = worksheet.unFreezePanesInAWorksheet(1, 1, 1, 1);
		assertEquals("Failed to unfreeze panes in a worksheet", true, isPanesFreezedSuccessfully);
	}
	
	public void testDeleteABackgroundOrWatermarkImageOfAWorksheet() throws Exception {
		boolean isBackgroundImageDeletedSuccessfully = worksheet.deleteABackgroundOrWatermarkImageOfAWorksheet();
		assertEquals("Failed to delete a background or watermark image of a worksheet", true, isBackgroundImageDeletedSuccessfully);
	}
}
