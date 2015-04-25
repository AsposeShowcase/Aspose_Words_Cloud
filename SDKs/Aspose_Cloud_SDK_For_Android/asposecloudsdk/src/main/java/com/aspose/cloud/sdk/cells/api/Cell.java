package com.aspose.cloud.sdk.cells.api;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;

import android.net.Uri;

import com.aspose.cloud.sdk.cells.model.CellStyleResponse;
import com.aspose.cloud.sdk.cells.model.CellsResponse;
import com.aspose.cloud.sdk.cells.model.CellsResponse.CellData;
import com.aspose.cloud.sdk.cells.model.MergedCellResponse;
import com.aspose.cloud.sdk.cells.model.MergedCellResponse.MergedCell;
import com.aspose.cloud.sdk.cells.model.Style;
import com.aspose.cloud.sdk.common.AsposeApp;
import com.aspose.cloud.sdk.common.BaseResponse;
import com.aspose.cloud.sdk.common.Utils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Cell --- Using this class you can get a cell from a worksheet, get MaxRow, MaxDataRow, MaxColumn, MaxDataColumn, MinRow, 
 * MinDataRow, MinColumn and MinDataColumn from a worksheet, set value of a cell in a worksheet, set formula for a cell in 
 * a worksheet, get cell style and change cell style in a worksheet, get MergedCell from a worksheet, clear contents and styles 
 * of cells in excel worksheet, merge/unmerge cells in excel worksheet, set value for selected range in a worksheet and 
 * clear cells' formatting in a worksheet.
 * @author   M. Sohail Ismail
 */
public class Cell {
	
	private static final String CELLS_URI = AsposeApp.BASE_PRODUCT_URI + "/cells/";
	private static final List<String> cellValueTypes = Arrays.asList("empty", "object", "dbnull", 
			"boolean", "char", "sbyte", "byte", "int16", "uint16", "int", "int32", "uint32", 
			"int64", "uint64", "single", "double", "decimal", "datetime", "string");
	
	private String fileName;
	private String worksheetName;
	
	public Cell(String fileName, String worksheetName) {
		this.fileName = Uri.encode(fileName);
		this.worksheetName = Uri.encode(worksheetName);
	}
	
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	public void setWorksheetName(String worksheetName) {
		this.worksheetName = worksheetName;
	}
	
	/**
	 * Get a cell from a worksheet
	 * @param cellName Cell name
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return An object that contains cell's attributes
	*/
	public CellData getACellFromAWorksheet(String cellName) throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		
		CellData cell = null;
		
		if(fileName == null || fileName.length() == 0) {
			throw new IllegalArgumentException("File name cannot be null or empty");
		}
		
		if(worksheetName == null || worksheetName.length() == 0) {
			throw new IllegalArgumentException("Worksheet name cannot be null or empty");
		}
		
		String strURL = CELLS_URI + fileName + "/worksheets/" + worksheetName + "/cells/" + Uri.encode(cellName);
        //sign URL
        String signedURL = Utils.sign(strURL);
        
		InputStream responseStream = Utils.processCommand(signedURL, "GET");
		String responseJSONString = Utils.streamToString(responseStream);
        
        //Parsing JSON
		Gson gson = new Gson();
		CellsResponse cellResponse = gson.fromJson(responseJSONString, CellsResponse.class);
		if (cellResponse.getCode().equals("200") && cellResponse.getStatus().equals("OK")) {
			cell = cellResponse.cell;
		}
		
		return cell;
	}
	
	/**
	 * Get first cell of a worksheet
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return An object that contains cell's attributes
	*/
	public CellData getFirstCellOfAWorksheet() throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		
		CellData cell = null;
		
		if(fileName == null || fileName.length() == 0) {
			throw new IllegalArgumentException("File name cannot be null or empty");
		}
		
		if(worksheetName == null || worksheetName.length() == 0) {
			throw new IllegalArgumentException("Worksheet name cannot be null or empty");
		}
		
		String strURL = CELLS_URI + fileName + "/worksheets/" + worksheetName + "/cells/firstcell";
        //sign URL
        String signedURL = Utils.sign(strURL);
        
		InputStream responseStream = Utils.processCommand(signedURL, "GET");
		String responseJSONString = Utils.streamToString(responseStream);
        
        //Parsing JSON
		Gson gson = new Gson();
		CellsResponse cellResponse = gson.fromJson(responseJSONString, CellsResponse.class);
		if (cellResponse.getCode().equals("200") && cellResponse.getStatus().equals("OK")) {
			cell = cellResponse.cell;
		}
		
		return cell;
	}
	
	/**
	 * Get last cell of a worksheet
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return An object that contains cell's attributes
	*/
	public CellData getLastCellOfAWorksheet() throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		
		CellData cell = null;
		
		if(fileName == null || fileName.length() == 0) {
			throw new IllegalArgumentException("File name cannot be null or empty");
		}
		
		if(worksheetName == null || worksheetName.length() == 0) {
			throw new IllegalArgumentException("Worksheet name cannot be null or empty");
		}
		
		String strURL = CELLS_URI + fileName + "/worksheets/" + worksheetName + "/cells/endcell";
        //sign URL
        String signedURL = Utils.sign(strURL);
        
		InputStream responseStream = Utils.processCommand(signedURL, "GET");
		String responseJSONString = Utils.streamToString(responseStream);
        
        //Parsing JSON
		Gson gson = new Gson();
		CellsResponse cellResponse = gson.fromJson(responseJSONString, CellsResponse.class);
		if (cellResponse.getCode().equals("200") && cellResponse.getStatus().equals("OK")) {
			cell = cellResponse.cell;
		}
		
		return cell;
	}
	
	/**
	 * Get MaxRow from a worksheet
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return Index of last row which contains data or style in a worksheet
	*/
	public int getMaxRowFromExcelWorksheet() throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		
		int maxRowIndex = -1;
		
		if(fileName == null || fileName.length() == 0) {
			throw new IllegalArgumentException("File name cannot be null or empty");
		}
		
		if(worksheetName == null || worksheetName.length() == 0) {
			throw new IllegalArgumentException("Worksheet name cannot be null or empty");
		}
		
		String strURL = CELLS_URI + fileName + "/worksheets/" + worksheetName + "/cells/maxrow";
        //sign URL
        String signedURL = Utils.sign(strURL);
        
		InputStream responseStream = Utils.processCommand(signedURL, "GET");
		String responseString = Utils.streamToString(responseStream);
		maxRowIndex = Integer.parseInt(responseString);
		
        return maxRowIndex;
	}
	
	/**
	 * Get MaxDataRow from a worksheet
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return Index of last row which contains data in a worksheet
	*/
	public int getMaxDataRowFromExcelWorksheet() throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		
		int maxDataRowIndex = -1;
		
		if(fileName == null || fileName.length() == 0) {
			throw new IllegalArgumentException("File name cannot be null or empty");
		}
		
		if(worksheetName == null || worksheetName.length() == 0) {
			throw new IllegalArgumentException("Worksheet name cannot be null or empty");
		}
		
		String strURL = CELLS_URI + fileName + "/worksheets/" + worksheetName + "/cells/maxdatarow";
        //sign URL
        String signedURL = Utils.sign(strURL);
        
		InputStream responseStream = Utils.processCommand(signedURL, "GET");
		String responseString = Utils.streamToString(responseStream);
		maxDataRowIndex = Integer.parseInt(responseString);
		
        return maxDataRowIndex;
	}
	
	/**
	 * Get MaxColumn from excel worksheet
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return Index of last column which contains data or style in a worksheet 
	*/
	public int getMaxColumnFromExcelWorksheet() throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		
		int maxColumnIndex = -1;
		
		if(fileName == null || fileName.length() == 0) {
			throw new IllegalArgumentException("File name cannot be null or empty");
		}
		
		if(worksheetName == null || worksheetName.length() == 0) {
			throw new IllegalArgumentException("Worksheet name cannot be null or empty");
		}
		
		String strURL = CELLS_URI + fileName + "/worksheets/" + worksheetName + "/cells/maxcolumn";
        //sign URL
        String signedURL = Utils.sign(strURL);
        
		InputStream responseStream = Utils.processCommand(signedURL, "GET");
		String responseString = Utils.streamToString(responseStream);
		maxColumnIndex = Integer.parseInt(responseString);
		
        return maxColumnIndex;
	}

	/**
	 * Get MaxDataColumn from excel worksheet
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return Index of last column which contains data in a worksheet  
	*/
	public int getMaxDataColumnFromExcelWorksheet() throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		
		int maxDataColumnIndex = -1;
		
		if(fileName == null || fileName.length() == 0) {
			throw new IllegalArgumentException("File name cannot be null or empty");
		}
		
		if(worksheetName == null || worksheetName.length() == 0) {
			throw new IllegalArgumentException("Worksheet name cannot be null or empty");
		}
		
		String strURL = CELLS_URI + fileName + "/worksheets/" + worksheetName + "/cells/maxdatacolumn";
        //sign URL
        String signedURL = Utils.sign(strURL);
        
		InputStream responseStream = Utils.processCommand(signedURL, "GET");
		String responseString = Utils.streamToString(responseStream);
		maxDataColumnIndex = Integer.parseInt(responseString);
		
        return maxDataColumnIndex;
	}
	
	/**
	 * Get MinRow from excel worksheet
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return Index of first row which contains data or style in a worksheet  
	*/
	public int getMinRowFromExcelWorksheet() throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		
		int minRowIndex = -1;
		
		if(fileName == null || fileName.length() == 0) {
			throw new IllegalArgumentException("File name cannot be null or empty");
		}
		
		if(worksheetName == null || worksheetName.length() == 0) {
			throw new IllegalArgumentException("Worksheet name cannot be null or empty");
		}
		
		String strURL = CELLS_URI + fileName + "/worksheets/" + worksheetName + "/cells/minrow";
        //sign URL
        String signedURL = Utils.sign(strURL);
        
		InputStream responseStream = Utils.processCommand(signedURL, "GET");
		String responseString = Utils.streamToString(responseStream);
		minRowIndex = Integer.parseInt(responseString);
		
        return minRowIndex;
	}
	
	/**
	 * Get MinDataRow from excel worksheet
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return Index of first row which contains data in a worksheet  
	*/
	public int getMinDataRowFromExcelWorksheet() throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		
		int minDataRowIndex = -1;
		
		if(fileName == null || fileName.length() == 0) {
			throw new IllegalArgumentException("File name cannot be null or empty");
		}
		
		if(worksheetName == null || worksheetName.length() == 0) {
			throw new IllegalArgumentException("Worksheet name cannot be null or empty");
		}
		
		String strURL = CELLS_URI + fileName + "/worksheets/" + worksheetName + "/cells/mindatarow";
        //sign URL
        String signedURL = Utils.sign(strURL);
        
		InputStream responseStream = Utils.processCommand(signedURL, "GET");
		String responseString = Utils.streamToString(responseStream);
		minDataRowIndex = Integer.parseInt(responseString);
		
        return minDataRowIndex;
	}
	
	/**
	 * Get MinColumn from excel worksheet
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return Index of first column which contains data or style in a worksheet  
	*/
	public int getMinColumnFromExcelWorksheet() throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		
		int minColumnIndex = -1;
		
		if(fileName == null || fileName.length() == 0) {
			throw new IllegalArgumentException("File name cannot be null or empty");
		}
		
		if(worksheetName == null || worksheetName.length() == 0) {
			throw new IllegalArgumentException("Worksheet name cannot be null or empty");
		}
		
		String strURL = CELLS_URI + fileName + "/worksheets/" + worksheetName + "/cells/mincolumn";
        //sign URL
        String signedURL = Utils.sign(strURL);
        
		InputStream responseStream = Utils.processCommand(signedURL, "GET");
		String responseString = Utils.streamToString(responseStream);
		minColumnIndex = Integer.parseInt(responseString);
		
        return minColumnIndex;
	}

	/**
	 * Get MinDataColumn from excel worksheet
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return Index of first column which contains data in a worksheet  
	*/
	public int getMinDataColumnFromExcelWorksheet() throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		
		int minDataColumnIndex = -1;
		
		if(fileName == null || fileName.length() == 0) {
			throw new IllegalArgumentException("File name cannot be null or empty");
		}
		
		if(worksheetName == null || worksheetName.length() == 0) {
			throw new IllegalArgumentException("Worksheet name cannot be null or empty");
		}
		
		String strURL = CELLS_URI + fileName + "/worksheets/" + worksheetName + "/cells/mindatacolumn";
        //sign URL
        String signedURL = Utils.sign(strURL);
        
		InputStream responseStream = Utils.processCommand(signedURL, "GET");
		String responseString = Utils.streamToString(responseStream);
		minDataColumnIndex = Integer.parseInt(responseString);
		
        return minDataColumnIndex;
	}
	
	/**
	 * Set value of a cell in a worksheet
	 * @param cellName Cell name
	 * @param cellValue Cell value
	 * @param type Cell value type
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return An object that contains cell's attributes  
	*/
	public CellData setValueOfACellInAWorksheet(String cellName, String cellValue, String type) throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		
		CellData cell = null;
		
		if(fileName == null || fileName.length() == 0) {
			throw new IllegalArgumentException("File name cannot be null or empty");
		}
		
		if(worksheetName == null || worksheetName.length() == 0) {
			throw new IllegalArgumentException("Worksheet name cannot be null or empty");
		}
		
		if(cellName == null || cellName.length() == 0) {
			throw new IllegalArgumentException("Cell name cannot be null or empty");
		}
		
		if(cellValue == null || cellValue.length() == 0) {
			throw new IllegalArgumentException("Cell value cannot be null or empty");
		}
		
		if(!cellValueTypes.contains(type)) {
			throw new IllegalArgumentException("Accepted cell value types are empty, object, dbnull, " +
					"boolean, char, sbyte, byte, int16, uint16, int, int32, uint32, int64, uint64, " +
					"single, double, decimal, datetime, string");
		}
		
		String strURL = CELLS_URI + fileName + "/worksheets/" + worksheetName + "/cells/" + 
				Uri.encode(cellName) + "?value=" + Uri.encode(cellValue) + "&type=" + type;
        //sign URL
        String signedURL = Utils.sign(strURL);
        
		InputStream responseStream = Utils.processCommand(signedURL, "POST");
		String responseJSONString = Utils.streamToString(responseStream);
        
        //Parsing JSON
		Gson gson = new Gson();
		CellsResponse cellResponse = gson.fromJson(responseJSONString, CellsResponse.class);
		if (cellResponse.getCode().equals("200") && cellResponse.getStatus().equals("OK")) {
			cell = cellResponse.cell;
		}
		
		return cell;
	}
	
	/**
	 * Set formula for a cell in a worksheet
	 * @param cellName Cell name
	 * @param formula Set formula for cell e.g. sum(b1:b8)
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return An object that contains cell's attributes  
	*/
	public CellData setFormulaForACellInAWorksheet (String cellName, String formula) throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		
		CellData cell = null;
		
		if(fileName == null || fileName.length() == 0) {
			throw new IllegalArgumentException("File name cannot be null or empty");
		}
		
		if(worksheetName == null || worksheetName.length() == 0) {
			throw new IllegalArgumentException("Worksheet name cannot be null or empty");
		}
		
		if(cellName == null || cellName.length() == 0) {
			throw new IllegalArgumentException("Cell name cannot be null or empty");
		}
		
		if(formula == null || formula.length() == 0) {
			throw new IllegalArgumentException("Formula cannot be null or empty");
		}
		
		String strURL = CELLS_URI + fileName + "/worksheets/" + worksheetName + "/cells/" + 
				Uri.encode(cellName) + "?formula=" + Uri.encode(formula);
        //sign URL
        String signedURL = Utils.sign(strURL);
        
		InputStream responseStream = Utils.processCommand(signedURL, "POST");
		String responseJSONString = Utils.streamToString(responseStream);
        
        //Parsing JSON
		Gson gson = new Gson();
		CellsResponse cellResponse = gson.fromJson(responseJSONString, CellsResponse.class);
		if (cellResponse.getCode().equals("200") && cellResponse.getStatus().equals("OK")) {
			cell = cellResponse.cell;
		}
		
		return cell;
	}
	
	/**
	 * Get cell style from a worksheet
	 * @param cellName Cell name
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return An object that contains cell's style attributes  
	*/
	public Style getCellStyleFromAWorksheet(String cellName) throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		
		Style style = null;
		
		if(fileName == null || fileName.length() == 0) {
			throw new IllegalArgumentException("File name cannot be null or empty");
		}
		
		if(worksheetName == null || worksheetName.length() == 0) {
			throw new IllegalArgumentException("Worksheet name cannot be null or empty");
		}
		
		if(cellName == null || cellName.length() == 0) {
			throw new IllegalArgumentException("Cell name cannot be null or empty");
		}
		
		String strURL = CELLS_URI + fileName + "/worksheets/" + worksheetName + "/cells/" + 
				Uri.encode(cellName) + "/style";
        //sign URL
        String signedURL = Utils.sign(strURL);
        
		InputStream responseStream = Utils.processCommand(signedURL, "GET");
		String responseJSONString = Utils.streamToString(responseStream);
        
        //Parsing JSON
		Gson gson = new Gson();
		CellStyleResponse cellStyleResponse = gson.fromJson(responseJSONString, CellStyleResponse.class);
		if (cellStyleResponse.getCode().equals("200") && cellStyleResponse.getStatus().equals("OK")) {
			style = cellStyleResponse.style;
		}
		
		return style;
	}
	
	/**
	 * Change cell style in excel worksheet
	 * @param range Cell range e.g. A1:B10
	 * @param style Style object
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return An object that contains cell's updated style attributes  
	*/
	public boolean changeCellStyleInExcelWorksheet(String range, Style style) throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		
		boolean isCellStyleChanged = false;
		
		if(fileName == null || fileName.length() == 0) {
			throw new IllegalArgumentException("File name cannot be null or empty");
		}
		
		if(worksheetName == null || worksheetName.length() == 0) {
			throw new IllegalArgumentException("Worksheet name cannot be null or empty");
		}
		
		if(range == null || range.length() == 0) {
			throw new IllegalArgumentException("Range cannot be null or empty");
		}
		
		if(style == null) {
			throw new IllegalArgumentException("Style is not specified");
		}
		
		GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        String requestJSONString = gson.toJson(style, Style.class);
		
		String strURL = CELLS_URI + fileName + "/worksheets/" + worksheetName + "/cells/style?range=" + Uri.encode(range);
        //sign URL
        String signedURL = Utils.sign(strURL);
        
		InputStream responseStream = Utils.processCommand(signedURL, "POST", requestJSONString);
		String responseJSONString = Utils.streamToString(responseStream);
        
        //Parsing JSON
		CellStyleResponse cellStyleResponse = gson.fromJson(responseJSONString, CellStyleResponse.class);
		if (cellStyleResponse.getCode().equals("200") && cellStyleResponse.getStatus().equals("OK")) {
			isCellStyleChanged = true;
		}
		
		return isCellStyleChanged;
	}
	
	/**
	 * Get MergedCell from a worksheet
	 * @param mergedCellIndex Merged cell index
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return An object that contains merged cell attributes  
	*/
	public MergedCell getMergedCellsFromAWorksheet(int mergedCellIndex) throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		
		MergedCell mergedCell = null;
		
		if(fileName == null || fileName.length() == 0) {
			throw new IllegalArgumentException("File name cannot be null or empty");
		}
		
		if(worksheetName == null || worksheetName.length() == 0) {
			throw new IllegalArgumentException("Worksheet name cannot be null or empty");
		}
		
		String strURL = CELLS_URI + fileName + "/worksheets/" + worksheetName + "/mergedCells/" + mergedCellIndex;
        //sign URL
        String signedURL = Utils.sign(strURL);
        
		InputStream responseStream = Utils.processCommand(signedURL, "GET");
		String responseJSONString = Utils.streamToString(responseStream);
        
        //Parsing JSON
		Gson gson = new Gson();
		MergedCellResponse mergedCellResponse = gson.fromJson(responseJSONString, MergedCellResponse.class);
		if (mergedCellResponse.getCode().equals("200") && mergedCellResponse.getStatus().equals("OK")) {
			mergedCell = mergedCellResponse.mergedCell;
		}
		
		return mergedCell;
	}
	
	/**
	 * Clear contents and styles of cells in excel worksheet
	 * @param range Update the specified cells area e.g. A1:B3
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return Boolean variable that indicates whether contents and styles of cell cleared successfully  
	*/
	public boolean clearContentsAndStylesOfCellsInAWorksheet(String range) throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		
		boolean isContentsAndStylesOfCellsClearedSuccessfully = false;
		
		if(fileName == null || fileName.length() == 0) {
			throw new IllegalArgumentException("File name cannot be null or empty");
		}
		
		if(worksheetName == null || worksheetName.length() == 0) {
			throw new IllegalArgumentException("Worksheet name cannot be null or empty");
		}
		
		if(range == null || range.length() == 0) {
			throw new IllegalArgumentException("Range cannot be null or empty");
		}
		
		String strURL = CELLS_URI + fileName + "/worksheets/" + worksheetName + "/cells/clearcontents?range=" + range;
        //sign URL
        String signedURL = Utils.sign(strURL);
        
		InputStream responseStream = Utils.processCommand(signedURL, "POST");
		String responseJSONString = Utils.streamToString(responseStream);
        
        //Parsing JSON
		Gson gson = new Gson();
		BaseResponse baseResponse = gson.fromJson(responseJSONString, BaseResponse.class);
		if (baseResponse.getCode().equals("200") && baseResponse.getStatus().equals("OK")) {
			isContentsAndStylesOfCellsClearedSuccessfully = true;
		}
		
		return isContentsAndStylesOfCellsClearedSuccessfully;
	}
	
	/**
	 * Merge cells in excel worksheet
	 * @param startrow Start row index
	 * @param startcolumn Start column index
	 * @param totalrows Number of rows(one based)
	 * @param totalcolumns Number of columns(one based)
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return Boolean variable that indicates whether cells merged successfully  
	*/
	public boolean mergeCellsInExcelWorksheet(int startrow, int startcolumn, int totalrows, int totalcolumns) throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		
		boolean isCellsMergedSuccessfully = false;
		
		if(fileName == null || fileName.length() == 0) {
			throw new IllegalArgumentException("File name cannot be null or empty");
		}
		
		if(worksheetName == null || worksheetName.length() == 0) {
			throw new IllegalArgumentException("Worksheet name cannot be null or empty");
		}
		
		String strURL = CELLS_URI + fileName + "/worksheets/" + worksheetName + "/cells/merge?startrow=" + startrow + "&startcolumn=" + 
				startcolumn + "&totalrows=" + totalrows + "&totalcolumns=" + totalcolumns;
        //sign URL
        String signedURL = Utils.sign(strURL);
        
		InputStream responseStream = Utils.processCommand(signedURL, "POST");
		String responseJSONString = Utils.streamToString(responseStream);
        
        //Parsing JSON
		Gson gson = new Gson();
		BaseResponse baseResponse = gson.fromJson(responseJSONString, BaseResponse.class);
		if (baseResponse.getCode().equals("200") && baseResponse.getStatus().equals("OK")) {
			isCellsMergedSuccessfully = true;
		}
		
		return isCellsMergedSuccessfully;
	}
	
	/**
	 * Unmerge cells in excel worksheet
	 * @param startrow Start row index
	 * @param startcolumn Start column index
	 * @param totalrows Number of rows(one based)
	 * @param totalcolumns Number of columns(one based)
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return Boolean variable that indicates whether cells unmerged successfully  
	*/
	public boolean unmergeCellsInExcelWorksheet(int startrow, int startcolumn, int totalrows, int totalcolumns) throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		boolean isCellsUnMergedSuccessfully = false;
		
		if(fileName == null || fileName.length() == 0) {
			throw new IllegalArgumentException("File name cannot be null or empty");
		}
		
		if(worksheetName == null || worksheetName.length() == 0) {
			throw new IllegalArgumentException("Worksheet name cannot be null or empty");
		}
		
		String strURL = CELLS_URI + fileName + "/worksheets/" + worksheetName + "/cells/unmerge?startrow=" + startrow + "&startcolumn=" + 
				startcolumn + "&totalrows=" + totalrows + "&totalcolumns=" + totalcolumns;
        //sign URL
        String signedURL = Utils.sign(strURL);
        
		InputStream responseStream = Utils.processCommand(signedURL, "POST");
		String responseJSONString = Utils.streamToString(responseStream);
        
        //Parsing JSON
		Gson gson = new Gson();
		BaseResponse baseResponse = gson.fromJson(responseJSONString, BaseResponse.class);
		if (baseResponse.getCode().equals("200") && baseResponse.getStatus().equals("OK")) {
			isCellsUnMergedSuccessfully = true;
		}
		
		return isCellsUnMergedSuccessfully;
	}
	
	/**
	 * Set value for selected range in a worksheet
	 * @param cellArea Cell area
	 * @param value Value for the specified cells area
	 * @param type Value type for the specified cells area
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return Boolean variable that indicates whether value set for specified call area  
	*/
	public boolean setValueForSelectedRangeInAWorksheet(String cellArea, String value, String type) throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		boolean isValueSetSuccessfullyForRangeInAWorksheet = false;
		
		if(fileName == null || fileName.length() == 0) {
			throw new IllegalArgumentException("File name cannot be null or empty");
		}
		
		if(worksheetName == null || worksheetName.length() == 0) {
			throw new IllegalArgumentException("Worksheet name cannot be null or empty");
		}
		
		String strURL = CELLS_URI + fileName + "/worksheets/" + worksheetName + "/cells?cellarea=" + 
				Uri.encode(cellArea) + "&value=" + Uri.encode(value) + "&type=" + Uri.encode(type);
        //sign URL
        String signedURL = Utils.sign(strURL);
        
		InputStream responseStream = Utils.processCommand(signedURL, "POST");
		String responseJSONString = Utils.streamToString(responseStream);
        
        //Parsing JSON
		Gson gson = new Gson();
		BaseResponse baseResponse = gson.fromJson(responseJSONString, BaseResponse.class);
		if (baseResponse.getCode().equals("200") && baseResponse.getStatus().equals("OK")) {
			isValueSetSuccessfullyForRangeInAWorksheet = true;
		}
		
		return isValueSetSuccessfullyForRangeInAWorksheet;
	}
	
	/**
	 * Clear cells' formatting in a worksheet
	 * @param startRow Start row index
	 * @param startColumn Start column index
	 * @param endRow End row index
	 * @param endColumn End column index
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return Boolean variable that indicates whether cells formatting cleared successfully  
	*/
	public boolean clearCellsFormattingInExcelWorksheet(int startRow, int startColumn, int endRow, int endColumn) throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		boolean isCellFormattingClearedSuccessfully = false;
		
		if(fileName == null || fileName.length() == 0) {
			throw new IllegalArgumentException("File name cannot be null or empty");
		}
		
		if(worksheetName == null || worksheetName.length() == 0) {
			throw new IllegalArgumentException("Worksheet name cannot be null or empty");
		}
		
		String strURL = CELLS_URI + fileName + "/worksheets/" + worksheetName + "/cells/ClearFormats?startRow=" + startRow + "&startColumn=" + 
				startColumn + "&endRow=" + endRow + "&endColumn=" + endColumn;
		
        //sign URL
        String signedURL = Utils.sign(strURL);
        
		InputStream responseStream = Utils.processCommand(signedURL, "POST");
		String responseJSONString = Utils.streamToString(responseStream);
        
        //Parsing JSON
		Gson gson = new Gson();
		BaseResponse baseResponse = gson.fromJson(responseJSONString, BaseResponse.class);
		if (baseResponse.getCode().equals("200") && baseResponse.getStatus().equals("OK")) {
			isCellFormattingClearedSuccessfully = true;
		}
		
		return isCellFormattingClearedSuccessfully;
	}
}


