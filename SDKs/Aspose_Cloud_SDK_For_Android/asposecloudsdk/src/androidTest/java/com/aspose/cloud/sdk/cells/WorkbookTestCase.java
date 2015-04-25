package com.aspose.cloud.sdk.cells;

import java.io.File;

import com.aspose.cloud.sdk.cells.model.EncryptionTypeEnum;
import com.aspose.cloud.sdk.cells.model.ProtectionTypeEnum;
import com.aspose.cloud.sdk.cells.model.ValidFormatsForDocumentEnum;
import com.aspose.cloud.sdk.cells.model.ValidFormatsForPresentationEnum;
import com.aspose.cloud.sdk.cells.api.Workbook;
import com.aspose.cloud.sdk.cells.model.NamesCountFromAWorkbookResponse.NamesCountFromAWorkbookResult;
import com.aspose.cloud.sdk.cells.model.SplitWorksheetsOfAWorkbookResponse.SplitWorksheetsOfAWorkbookResult;

import junit.framework.TestCase;

public class WorkbookTestCase extends TestCase {

	public WorkbookTestCase(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testCreateAnEmptyExcelWorkbook() throws Exception {
		boolean isWorkbookCreatedSuccessfully = Workbook.createAnEmptyExcelWorkbook("myworkbook.xlsx");
		assertEquals("Failed to create an empty excel workbook", true, isWorkbookCreatedSuccessfully);
	}
	
	public void testCreateExcelWorkbookFromATemplateFile() throws Exception {
		boolean isWorkbookCreatedSuccessfullyFromTemplate = 
				Workbook.createExcelWorkbookFromATemplateFile("myworkbooktemp.xlsx", "ie_data.xls");
		assertEquals("Failed to create excel workbook from a template file", true, isWorkbookCreatedSuccessfullyFromTemplate);
	}
	
	public void testCreateExcelWorkbookFromASmartMarkerTemplate() throws Exception {
		boolean isWorkbookCreatedSuccesfullyFromSmartMarkerTemplate = Workbook.createExcelWorkbookFromASmartMarkerTemplate("newworkbook.xlsx", "2014-calendar.xlsx", "SmartMarker.xml");
		assertEquals("Failed to create excel workbook from a smartMarker template", true, isWorkbookCreatedSuccesfullyFromSmartMarkerTemplate);
	}
	
	public void testConvertExcelWorkbookToDifferentFileFormats() throws Exception {
		String localFilePath = Workbook.convertExcelWorkbookToDifferentFileFormats("myworkbook.xls", ValidFormatsForPresentationEnum.pdf);
		File file = new File(localFilePath);
		assertEquals("Failed to convert excel workbook to designated file format", true, file.exists());
	}
	
	public void testConvertExcelWorkbookToDifferentFileFormatsWithoutUsingStorage() throws Exception {
		String localFilePath = Workbook.convertExcelWorkbookToDifferentFileFormatsWithoutUsingStorage("/storage/emulated/0/AsposeFiles/myworkbook.xls", ValidFormatsForPresentationEnum.pdf);
		File file = new File(localFilePath);
		assertEquals("Failed to convert excel workbook to designated file format without using storage", 
				true, file.exists());
	}
	
	public void testConvertExcelWorkbookWithAdditionalSettings() throws Exception {
		String xmlData = "<PdfSaveOptions>" +
	            			"<desiredPPI>300</desiredPPI>" +
	            			"<jpegQuality>70</jpegQuality>" +
	            			"<OnePagePerSheet>true</OnePagePerSheet>" +
	            			"<SaveFormat>Pdf</SaveFormat>" +
	            		 "</PdfSaveOptions>";
     
		String localFilePath = Workbook.convertExcelWorkbookWithAdditionalSettings("myworkbook.xlsx", xmlData, "convertedworkbook.pdf");
		File file = new File(localFilePath);
		assertEquals("Failed to convert excel workbook with additional settings", true, file.exists());
	}
	
	public void testMergeExcelWorkbooks() throws Exception {
		boolean isExcelFilesMergedSuccessfully = Workbook.mergeExcelWorkbooks("myworkbook.xlsx", "myworkbook2.xlsx");
		assertEquals("Failed to merge multiple workbooks into a single workbook", true, isExcelFilesMergedSuccessfully);
	}
	
	public void testSplitWorksheetsOfAWorkbook() throws Exception {
		SplitWorksheetsOfAWorkbookResult splitWorksheetsOfAWorkbookResult = Workbook.splitWorksheetsOfAWorkbook("myworkbook.xlsx", ValidFormatsForDocumentEnum.pdf, 0, 0);
		assertNotNull("Failed to split worksheets of a workbook file", splitWorksheetsOfAWorkbookResult);
	}
	
	public void testProtectAWorkbook() throws Exception {
		boolean isWorkbookProtectedSuccessfully = Workbook.protectAWorkbook("myworkbook.xlsx", ProtectionTypeEnum.All, "password1234");
		assertEquals("Failed to protect a workbook", true, isWorkbookProtectedSuccessfully);
	}
	
	public void testunprotectAWorkbook() throws Exception {
		boolean isWorkbookUnProtectedSuccessfully = Workbook.unprotectAWorkbook("myworkbook.xlsx", "password1234");
		assertEquals("Failed to unprotect a workbook", true, isWorkbookUnProtectedSuccessfully);
	}
	
	public void testEncryptAWorkbook() throws Exception {
		boolean isWorkbookEncryptedSuccessfully = Workbook.encryptAWorkbook("myworkbook.xlsx", EncryptionTypeEnum.EnhancedCryptographicProviderV1, "password1234", 128);
		assertEquals("Failed to encrypt a workbook", true, isWorkbookEncryptedSuccessfully);
	}
	
	public void testDecryptAWorkbook() throws Exception {
		boolean isWorkbookDecryptedSuccessfully = Workbook.decryptAWorkbook("myworkbook.xlsx", "password1234");
		assertEquals("Failed to decrypt a workbook", true, isWorkbookDecryptedSuccessfully);
	}
	
	public void testSetModifyPasswordOfAWorkbook() throws Exception {
		boolean modifyPasswordSetSuccessfullyOfAWorkbook = Workbook.setModifyPasswordOfAWorkbook("myworkbook.xlsx", "password1234");
		assertEquals("Failed to set modify password of a workbook", true, modifyPasswordSetSuccessfullyOfAWorkbook);
	}
	
	public void testClearModifyPasswordOfAWorkbook() throws Exception {
		boolean modifyPasswordClearedSuccessfullyOfAWorkbook = Workbook.clearModifyPasswordOfAWorkbook("myworkbook.xlsx", "password1234");
		assertEquals("Failed to clear modify password of a workbook", true, modifyPasswordClearedSuccessfullyOfAWorkbook);
	}
	
	public void testGetNamesCountFromAWorkbook() throws Exception {
		NamesCountFromAWorkbookResult namesCountFromAWorkbookResult = Workbook.getNamesCountFromAWorkbook("myworkbook.xls");
		assertNotNull("Failed to get names count from a workbook", namesCountFromAWorkbookResult);
	}
}
