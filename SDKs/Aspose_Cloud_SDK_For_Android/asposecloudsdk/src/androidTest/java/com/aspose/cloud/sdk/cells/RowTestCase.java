package com.aspose.cloud.sdk.cells;

import com.aspose.cloud.sdk.cells.api.Row;
import com.aspose.cloud.sdk.cells.model.RowResponse.RowData;
import com.aspose.cloud.sdk.cells.model.RowsResponse.RowsData;

import junit.framework.TestCase;

public class RowTestCase extends TestCase {

	private Row row;
	
	public RowTestCase(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
		row = new Row("myworkbook.xlsx", "Sheet1");
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void testGetRowFromAWorksheet() throws Exception {
		RowData rowData = row.getRowFromAWorksheet(1);
		assertNotNull("Failed to get a row from a worksheet", rowData);
	}
	
	public void testGetRowsFromAWorksheet() throws Exception {
		RowsData rows = row.getRowsFromAWorksheet(0, 10);
		assertNotNull("Failed to get rows from a worksheet", rows);
	}
	
	public void testAddAnEmptyRowInAWorksheet() throws Exception {
		RowData rowData = row.addAnEmptyRowInAWorksheet(1);
		assertNotNull("Failed to add an empty row in a worksheet", rowData);
	}
	
	public void testDeleteARowFromAWorksheet() throws Exception {
		boolean isRowDeletedSuccessfully = row.deleteARowFromAWorksheet(0);
		assertEquals("Failed to delete a row from a worksheet", true, isRowDeletedSuccessfully);
	}
	
	public void testCopyRowsInAWorksheet() throws Exception {
		boolean isRowsCopiedSuccessfully = row.copyRowsInAWorksheet(2, 4, 2);
		assertEquals("Failed to copy rows in a worksheet", true, isRowsCopiedSuccessfully);
	}
	
	public void testHideRowsInAWorksheet() throws Exception {
		boolean isRowsHideSuccessfully = row.hideRowsInAWorksheet(1, 5);
		assertEquals("Failed to hide rows in a worksheet", true, isRowsHideSuccessfully);
	}
	
	public void testUnhideRowsInAWorksheet() throws Exception {
		boolean isRowsUnHideSuccessfully = row.unhideRowsInAWorksheet(1, 5);
		assertEquals("Failed to unhide hidden rows in a worksheet", true, isRowsUnHideSuccessfully);
	}
	
	public void testGroupRowsInAWorksheet() throws Exception {
		boolean isRowsGroupedSuccessfully = row.groupRowsInAWorksheet(1, 10);
		assertEquals("Failed to group rows in a worksheet", true, isRowsGroupedSuccessfully);
	}
	
	public void testunGroupRowsInAWorksheet() throws Exception {
		boolean isRowsUnGroupedSuccessfully = row.ungroupRowsInAWorksheet(1, 10);
		assertEquals("Failed to ungroup grouped rows in a worksheet", true, isRowsUnGroupedSuccessfully);
	}
	
	public void testAutoFitRowsInAWorksheet() throws Exception {
		boolean isRowsAutoFitSuccessfully = row.autoFitRowsInAWorkbook(true);
		assertEquals("Failed to auto fit rows in a worksheet", true, isRowsAutoFitSuccessfully);
	}
}
