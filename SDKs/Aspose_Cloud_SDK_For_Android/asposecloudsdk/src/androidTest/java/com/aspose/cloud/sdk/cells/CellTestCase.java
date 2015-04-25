package com.aspose.cloud.sdk.cells;

import com.aspose.cloud.sdk.cells.api.Cell;
import com.aspose.cloud.sdk.cells.model.CellsResponse.CellData;
import com.aspose.cloud.sdk.cells.model.MergedCellResponse.MergedCell;
import com.aspose.cloud.sdk.cells.model.ColorModel;
import com.aspose.cloud.sdk.cells.model.FontModel;
import com.aspose.cloud.sdk.cells.model.Style;

import junit.framework.TestCase;

public class CellTestCase extends TestCase {

	private Cell cell;
	
	public CellTestCase(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
		cell = new Cell("myworkbook.xlsx", "Sheet1");
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void testGetACellFromAWorksheet() throws Exception {
		CellData cellData = cell.getACellFromAWorksheet("A5");
		assertNotNull("Failed to get a cell from a worksheet", cellData);
	}

	public void testGetFirstCellOfAWorksheet() throws Exception {
		CellData cellData = cell.getFirstCellOfAWorksheet();
		assertNotNull("Failed to get first cell of a worksheet", cellData);
	}
	
	public void testGetLastCellOfAWorksheet() throws Exception {
		CellData cellData = cell.getLastCellOfAWorksheet();
		assertNotNull("Failed to get last cell of a worksheet", cellData);
	}
	
	public void testGetMaxRowFromExcelWorksheet() throws Exception {
		int maxRowIndex = cell.getMaxRowFromExcelWorksheet();
		assertEquals("Failed to get MaxRow from excel worksheet", true, maxRowIndex>= 0);
	}
	
	public void testGetMaxDataRowFromExcelWorksheet() throws Exception {
		int maxDataRowIndex = cell.getMaxDataRowFromExcelWorksheet();	
		assertEquals("Failed to get MaxDataRow from excel worksheet", true, maxDataRowIndex>= 0);
	}
	
	public void testGetMaxColumnFromExcelWorksheet() throws Exception {
		int maxColumnIndex = cell.getMaxColumnFromExcelWorksheet();
		assertEquals("Failed to get MaxColumn from excel worksheet", true, maxColumnIndex>= 0);
	}
	
	public void testGetMaxDataColumnFromExcelWorksheet() throws Exception {
		int maxDataColumnIndex = cell.getMaxDataColumnFromExcelWorksheet();	
		assertEquals("Failed to get MaxDataColumn from excel worksheet", true, maxDataColumnIndex>= 0);
	}
	
	public void testGetMinRowFromExcelWorksheet() throws Exception {
		int minRowIndex = cell.getMinRowFromExcelWorksheet();
		assertEquals("Failed to get MinRow from excel worksheet", true, minRowIndex>= 0);
	}
	
	public void testGetMinDataRowFromExcelWorksheet() throws Exception {
		int minDataRowIndex = cell.getMinDataRowFromExcelWorksheet();	
		assertEquals("Failed to get MinDataRow from excel worksheet", true, minDataRowIndex>= 0);
	}
	
	public void testGetMinColumnFromExcelWorksheet() throws Exception {
		int minColumnIndex = cell.getMinColumnFromExcelWorksheet();
		assertEquals("Failed to get MinColumn from excel worksheet", true, minColumnIndex>= 0);
	}
	
	public void testGetMinDataColumnFromExcelWorksheet() throws Exception {
		int minDataColumnIndex = cell.getMinDataColumnFromExcelWorksheet();	
		assertEquals("Failed to get MinDataColumn from excel worksheet", true, minDataColumnIndex>= 0);
	}
	
	public void testSetValueOfACellInAWorksheet() throws Exception {
		CellData cellData = cell.setValueOfACellInAWorksheet("A7", "Saaspose", "string");
		assertNotNull("Failed to set value of a cell in a worksheet", cellData);
	}
	
	public void testSetFormulaForACellInAWorksheet() throws Exception {
		CellData cellData = cell.setFormulaForACellInAWorksheet("A4", "sum(A1:A2)");
		assertNotNull("Failed to set formula for a cell in a worksheet", cellData);
	}
	
	public void testGetCellStyleFromAWorksheet() throws Exception {
		Style style = cell.getCellStyleFromAWorksheet("E11");
		assertNotNull("Failed to get cell style from a worksheet", style);
	}
	
	public void testChangeCellStyleInExcelWorksheet() throws Exception {
		Style style = new Style();
		FontModel font = new FontModel();
		font.DoubleSize = 20;
		font.IsBold = true;
		font.IsItalic = true;
		font.IsStrikeout = true;
		font.Size = 15;

		style.Font = font;
		style.ShrinkToFit = true;

		ColorModel color = new ColorModel();
		color.R = 25;
		color.G = 25;
		color.B = 25;
		style.BackgroundColor = color; 
		
		boolean isCellStyleChanged = cell.changeCellStyleInExcelWorksheet("A1:C4", style);
		assertEquals("Failed to set the styles of selected cells in a worksheet", true, isCellStyleChanged);
	}
	
	public void testGetMergedCellsFromAWorksheet() throws Exception {
		MergedCell mergedCell = cell.getMergedCellsFromAWorksheet(0);
		assertNotNull("Failed to get merged cells from a worksheet", mergedCell);
	}
	
	public void testClearContentsAndStylesOfCellsInAWorksheet() throws Exception {
		boolean isContentsAndStylesOfCellsClearedSuccessfully = cell.clearContentsAndStylesOfCellsInAWorksheet("A1:C4");
		assertEquals("Failed to clear contents and styles of selected cells in a worksheet", true, isContentsAndStylesOfCellsClearedSuccessfully);
	}
	
	public void testMergeCellsInExcelWorksheet() throws Exception {
		boolean isCellsMergedSuccessfully = cell.mergeCellsInExcelWorksheet(0, 0, 5, 5);
		assertEquals("Failed to merge selected cells in a worksheet", true, isCellsMergedSuccessfully);
	}
	
	public void testUnmergeCellsInExcelWorksheet() throws Exception {
		boolean isCellsUnMergedSuccessfully = cell.unmergeCellsInExcelWorksheet(0, 0, 5, 5);
		assertEquals("Failed to unmerge selected merged cells in a worksheet", true, isCellsUnMergedSuccessfully);
	}
	
	public void testSetValueForSelectedRangeInAWorksheet() throws Exception {
		boolean isValueSetSuccessfullyForRangeInAWorksheet = cell.setValueForSelectedRangeInAWorksheet("A1:E5", "1234", "string");
		assertEquals("Failed to set value for selected range in a worksheet", true, isValueSetSuccessfullyForRangeInAWorksheet);
	}
	
	public void testClearCellsFormattingInExcelWorksheet() throws Exception {
		boolean isCellFormattingClearedSuccessfully = cell.clearCellsFormattingInExcelWorksheet(0, 0, 10, 10);
		assertEquals("Failed to clear cells' formatting in a worksheet", true, isCellFormattingClearedSuccessfully);
	}
	
}
