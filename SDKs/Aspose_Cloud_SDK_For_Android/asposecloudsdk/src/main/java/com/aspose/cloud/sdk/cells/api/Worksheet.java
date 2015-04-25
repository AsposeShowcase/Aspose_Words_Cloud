package com.aspose.cloud.sdk.cells.api;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import android.net.Uri;

import com.aspose.cloud.sdk.cells.model.WorksheetMovingRequest;
import com.aspose.cloud.sdk.cells.model.WorksheetResponse;
import com.aspose.cloud.sdk.cells.model.CalculateFormulaResponse;
import com.aspose.cloud.sdk.cells.model.CalculateFormulaResponse.CalculateFormulaResult;
import com.aspose.cloud.sdk.cells.model.GetAutoshapeFromAWorksheetResponse;
import com.aspose.cloud.sdk.cells.model.GetAutoshapeFromAWorksheetResponse.AutoShape;
import com.aspose.cloud.sdk.cells.model.GetColumnFromAWorksheetResponse;
import com.aspose.cloud.sdk.cells.model.GetColumnFromAWorksheetResponse.Column;
import com.aspose.cloud.sdk.cells.model.GetCommentFromAWorksheetResponse;
import com.aspose.cloud.sdk.cells.model.GetCommentFromAWorksheetResponse.Comment;
import com.aspose.cloud.sdk.cells.model.GetValidationFromAWorksheetResponse;
import com.aspose.cloud.sdk.cells.model.GetValidationFromAWorksheetResponse.Validation;
import com.aspose.cloud.sdk.cells.model.WorksheetResponse.WorksheetResult;
import com.aspose.cloud.sdk.cells.model.DataSortModel;
import com.aspose.cloud.sdk.cells.model.PositionEnum;
import com.aspose.cloud.sdk.cells.model.SortKey;
import com.aspose.cloud.sdk.cells.model.ValidFormatsOfWorksheet;
import com.aspose.cloud.sdk.common.AsposeApp;
import com.aspose.cloud.sdk.common.BaseResponse;
import com.aspose.cloud.sdk.common.Utils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Worksheet --- Using this class you can convert a worksheet to image, add a new worksheet, get worksheet count, hide/unhide a worksheet, 
 * move a worksheet to a new location, remove a worksheet, get autoshape, comment or validation from a worksheet, calculate formula in a worksheet, 
 * sort worksheet data, get a specific column from a worksheet, rename a worksheet, update properties of a worksheet, set and remove background image 
 * and freeze/unfreeze panes in a worksheet. 
 * @author   M. Sohail Ismail
 */
public class Worksheet {
	
	private static final String CELLS_URI = AsposeApp.BASE_PRODUCT_URI + "/cells/";
	
	private String worksheetName;
	private String fileName;
	
	public Worksheet(String fileName, String worksheetName) {
		this.fileName = Uri.encode(fileName);
		this.worksheetName = Uri.encode(worksheetName);
	}
	
	/**
	 * Convert a worksheet to image
	 * @param designatedFormat Convert the worksheet in the specified format
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return Local Path to converted file
	*/
	public String convertWorksheetToImage(ValidFormatsOfWorksheet designatedFormat) throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		
		String localFilePath = null;
		
		if(designatedFormat == null) {
			throw new IllegalArgumentException("Designated format cannot be null");
		}
		
		//build URL
      	String strURL = CELLS_URI + fileName + "/worksheets/" + worksheetName + "?format=" + designatedFormat;
        //sign URL
        String signedURL = Utils.sign(strURL);
        
		InputStream responseStream = Utils.processCommand(signedURL, "GET");
		
		//Replace fileName extension with designated format 
		String[] fileNameAndItsExtensionArray = fileName.split("\\.");
		String outputFileName = fileNameAndItsExtensionArray[0] + "." + designatedFormat;
		
		//Save file on Disk
		localFilePath = Utils.saveStreamToFile(responseStream, outputFileName);
		
		return localFilePath;
	}

	/**
	 * Add a new worksheet in a workbook
	 * @param newWorksheetName Name of new worksheet
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return An object that contains hypertext references to all worksheets 
	*/
	public WorksheetResult addANewWorksheet(String newWorksheetName) throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		
		WorksheetResult worksheetResult = null;
		
		if(newWorksheetName == null || newWorksheetName.length() == 0) {
			throw new IllegalArgumentException("New worksheet name cannot be null or empty");
		}
		
		//build URL
      	String strURL = CELLS_URI + fileName + "/worksheets/" + Uri.encode(newWorksheetName);
        //sign URL
        String signedURL = Utils.sign(strURL);
        
        InputStream responseStream = Utils.processCommand(signedURL, "PUT");
        String responseJSONString = Utils.streamToString(responseStream);

		//Parsing JSON
		Gson gson = new Gson();
		WorksheetResponse worksheetResponse = gson.fromJson(responseJSONString, WorksheetResponse.class);
		if (worksheetResponse.getCode().equals("201") && worksheetResponse.getStatus().equals("Created")) {
			worksheetResult = worksheetResponse.worksheets;
		}
		
		return worksheetResult;
	}
	
	/**
	 * Get worksheet count
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return Count of worksheets 
	*/
	public int getWorksheetCount() throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		
		int worksheetCount = -1;
		
		//build URL
      	String strURL = CELLS_URI + fileName + "/worksheets";
        //sign URL
        String signedURL = Utils.sign(strURL);
        
        InputStream responseStream = Utils.processCommand(signedURL, "GET");
        String responseJSONString = Utils.streamToString(responseStream);
        
        //Parsing JSON
		Gson gson = new Gson();
		WorksheetResponse worksheetResponse = gson.fromJson(responseJSONString, WorksheetResponse.class);
		if (worksheetResponse.getCode().equals("200") && worksheetResponse.getStatus().equals("OK")) {
			worksheetCount = worksheetResponse.worksheets.worksheetList.size();
		}
		
		return worksheetCount;
	}
	
	/**
	 * Hide a worksheet in a workbook
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return Boolean variable that indicates whether worksheet hide successfully 
	*/
	public boolean hideAWorksheetInAWorkbook() throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		
		boolean isWorksheetHideSuccessfully = false;
		
		//build URL
      	String strURL = CELLS_URI + fileName + "/worksheets/" + worksheetName + "/visible?isVisible=false";
        //sign URL
        String signedURL = Utils.sign(strURL);
        
        InputStream responseStream = Utils.processCommand(signedURL, "PUT");
        String responseJSONString = Utils.streamToString(responseStream);
        
        //Parsing JSON
		Gson gson = new Gson();
		BaseResponse baseResponse = gson.fromJson(responseJSONString, BaseResponse.class);
		if (baseResponse.getCode().equals("200") && baseResponse.getStatus().equals("OK")) {
			isWorksheetHideSuccessfully = true;
		}
		
		return isWorksheetHideSuccessfully;
	}

	/**
	 * Unhide a worksheet in a workbook
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return Boolean variable that indicates whether worksheet becomes visible 
	*/
	public boolean unhideAWorksheetInAWorkbook() throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		
		boolean isWorksheetUnHideSuccessfully = false;
		
		//build URL
      	String strURL = CELLS_URI + fileName + "/worksheets/" + worksheetName + "/visible?isVisible=true";
        //sign URL
        String signedURL = Utils.sign(strURL);
        
        InputStream responseStream = Utils.processCommand(signedURL, "PUT");
        String responseJSONString = Utils.streamToString(responseStream);
        
        //Parsing JSON
		Gson gson = new Gson();
		BaseResponse baseResponse = gson.fromJson(responseJSONString, BaseResponse.class);
		if (baseResponse.getCode().equals("200") && baseResponse.getStatus().equals("OK")) {
			isWorksheetUnHideSuccessfully = true;
		}
		
		return isWorksheetUnHideSuccessfully;
	}

	/**
	 * Move a worksheet to a new location in a workbook
	 * @param destinationWorsheet Destination worksheet name
	 * @param position Relative position. Can be BEFORE or AFTER
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return Boolean variable that indicates whether worksheet moved to new location successfully
	*/
	public boolean moveAWorksheetToANewLocationInAWorkbook(String destinationWorsheet, PositionEnum position) throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		
		boolean isWorksheetMovedSuccessfully = false;
		
		if(destinationWorsheet == null || destinationWorsheet.length() == 0) {
			throw new IllegalArgumentException("Destination worsheet cannot be null or empty");
		}
		
		if(position == null) {
			throw new IllegalArgumentException("Position cannot be null");
		}
		
		//Serialize the JSON request content
		WorksheetMovingRequest worksheetMovingRequest = new WorksheetMovingRequest();
		worksheetMovingRequest.destinationWorksheet = destinationWorsheet;
		worksheetMovingRequest.position = position;
					
		GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        String requestJSONString = gson.toJson(worksheetMovingRequest, WorksheetMovingRequest.class);
        
        //build URI
        String strURI = CELLS_URI + fileName + "/worksheets/" + worksheetName + "/position";
        //sign URL
      	String signedURI = Utils.sign(strURI);

		InputStream responseStream = Utils.processCommand(signedURI, "POST", requestJSONString);
		String responseJSONString = Utils.streamToString(responseStream);

		//Parsing JSON
		BaseResponse baseResponse = gson.fromJson(responseJSONString, BaseResponse.class);
		if (baseResponse.getCode().equals("200") && baseResponse.getStatus().equals("OK")) {
			isWorksheetMovedSuccessfully = true;
		}
		
		return isWorksheetMovedSuccessfully;
	}
	
	/**
	 * Remove a worksheet from a workbook
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return Boolean variable that indicates whether worksheet deleted successfully from a workbook 
	*/
	public boolean removeAWorksheetFromAWorkbook() throws InvalidKeyException, NoSuchAlgorithmException, IOException {

		boolean isWorksheetRemovedSuccessfully = false;
		
		//build URL
      	String strURL = CELLS_URI + fileName + "/worksheets/" + worksheetName;
        //sign URL
        String signedURL = Utils.sign(strURL);
        
        InputStream responseStream = Utils.processCommand(signedURL, "DELETE");
        String responseJSONString = Utils.streamToString(responseStream);
        
        //Parsing JSON
		Gson gson = new Gson();
		BaseResponse baseResponse = gson.fromJson(responseJSONString, BaseResponse.class);
		if (baseResponse.getCode().equals("200") && baseResponse.getStatus().equals("OK")) {
			isWorksheetRemovedSuccessfully = true;
		}
		
		return isWorksheetRemovedSuccessfully;
	}
	
	/**
	 * Get autoshape from a worksheet
	 * @param autoshapeIndex Autoshape Index
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return An object that contains autoshape attributes
	*/
	public AutoShape getAutoshapeFromAWorksheet(int autoshapeIndex) throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		
		AutoShape autoShape = null;
		//build URL
      	String strURL = CELLS_URI + fileName + "/worksheets/" + worksheetName + "/autoshapes/" + autoshapeIndex;
        //sign URL
        String signedURL = Utils.sign(strURL);
        
        InputStream responseStream = Utils.processCommand(signedURL, "GET");
        String responseJSONString = Utils.streamToString(responseStream);
        
        //Parsing JSON
  		Gson gson = new Gson();
  		GetAutoshapeFromAWorksheetResponse getAutoshapeResponse = gson.fromJson(responseJSONString, GetAutoshapeFromAWorksheetResponse.class);
  		if (getAutoshapeResponse.getCode().equals("200") && getAutoshapeResponse.getStatus().equals("OK")) {
  			autoShape = getAutoshapeResponse.autoShape;
  		}
        
  		return autoShape;
	}
	
	/**
	 * Get comment from a worksheet
	 * @param cellName Cell name which defined this comment 
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return An object that contains comment attributes
	*/
	public Comment getCommentFromAWorksheet(String cellName) throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		
		Comment comment = null;
		
		if(cellName == null || cellName.length() == 0) {
			throw new IllegalArgumentException("Cell name cannot be null or empty");
		}
		
		//build URL
      	String strURL = CELLS_URI + fileName + "/worksheets/" + worksheetName + "/comments/" + Uri.encode(cellName);
        //sign URL
        String signedURL = Utils.sign(strURL);
        
        InputStream responseStream = Utils.processCommand(signedURL, "GET");
        String responseJSONString = Utils.streamToString(responseStream);
        
        //Parsing JSON
  		Gson gson = new Gson();
  		GetCommentFromAWorksheetResponse getCommentFromAWorksheetResponse = gson.fromJson(responseJSONString, GetCommentFromAWorksheetResponse.class);
  		if (getCommentFromAWorksheetResponse.getCode().equals("200") && getCommentFromAWorksheetResponse.getStatus().equals("OK")) {
  			comment = getCommentFromAWorksheetResponse.comment;
  		}
        
  		return comment;
	}
	
	/**
	 * Get validation from a worksheet
	 * @param validationIndex Validation index 
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return An object that contains validation attributes
	*/
	public Validation getValidationFromAWorksheet(int validationIndex) throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		
		Validation validation = null;
		
		//build URL
      	String strURL = CELLS_URI + fileName + "/worksheets/" + worksheetName + "/validations/" + validationIndex;
        //sign URL
        String signedURL = Utils.sign(strURL);
        
        InputStream responseStream = Utils.processCommand(signedURL, "GET");
        String responseJSONString = Utils.streamToString(responseStream);
        
        //Parsing JSON
  		Gson gson = new Gson();
  		GetValidationFromAWorksheetResponse getValidationResponse = gson.fromJson(responseJSONString, GetValidationFromAWorksheetResponse.class);
  		if (getValidationResponse.getCode().equals("200") && getValidationResponse.getStatus().equals("OK")) {
  			validation = getValidationResponse.validation;
  		}
        
  		return validation;
	}
	
	/**
	 * Calculate formula in a worksheet
	 * @param formula Formula e.g. formula=sum(B2:B6)  
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return An object that contains formula calculation result 
	*/
	public CalculateFormulaResult calculateFormulaInAWorksheet(String formula) throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		
		CalculateFormulaResult value = null;
		
		//build URL
      	String strURL = CELLS_URI + fileName + "/worksheets/" + worksheetName + "/formulaResult?formula=" + Uri.encode(formula);
        //sign URL
        String signedURL = Utils.sign(strURL);
        
        InputStream responseStream = Utils.processCommand(signedURL, "GET");
        String responseJSONString = Utils.streamToString(responseStream);
        
        //Parsing JSON
  		Gson gson = new Gson();
  		CalculateFormulaResponse calculateFormulaResponse = gson.fromJson(responseJSONString, CalculateFormulaResponse.class);
  		if (calculateFormulaResponse.getCode().equals("200") && calculateFormulaResponse.getStatus().equals("OK")) {
  			value = calculateFormulaResponse.value;
  		}
        
  		return value;
	}
	
	/**
	 * Sort worksheet data
	 * @param cellArea Cells area to sort
	 * @param hasHeaders Indicate whether the range has headers
	 * @param caseSensitive Indicate whether case sensitive when comparing string
	 * @param sortLeftToRight Indicate whether sorting orientation is from left to right 
	 * @param keyList Represents list of sorted column index and sort order 
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return Boolean variable that indicates whether worksheet data sorted successfully 
	*/
	public boolean sortWorksheetData(String cellArea, boolean hasHeaders, boolean caseSensitive, boolean sortLeftToRight, List<SortKey> keyList) throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		
		if(cellArea == null || cellArea.length() ==0) {
			throw new IllegalArgumentException("Cell area cannot be null or empty");
		}
		
		if(keyList == null) {
			throw new IllegalArgumentException("keyList cannot be null");
		}
		
		boolean isWorksheetDataSortedSuccessfully = false;
		
      	//Serialize the JSON request content
      	DataSortModel dataSort = new DataSortModel();
      	dataSort.HasHeaders = hasHeaders;
      	dataSort.CaseSensitive = caseSensitive;
      	dataSort.SortLeftToRight = sortLeftToRight;
      	dataSort.KeyList =  keyList;
      	
      	GsonBuilder builder = new GsonBuilder();
      	Gson gson = builder.create();
      	String requestJSONString = gson.toJson(dataSort, DataSortModel.class);
              
      	//build URL
      	String strURL = CELLS_URI + fileName + "/worksheets/" + worksheetName + "/sort?cellArea=" + Uri.encode(cellArea);
        //sign URL
        String signedURI = Utils.sign(strURL);

      	InputStream responseStream = Utils.processCommand(signedURI, "POST", requestJSONString);
      	String responseJSONString = Utils.streamToString(responseStream);
      	
      	//Parsing JSON
  		BaseResponse baseResponse = gson.fromJson(responseJSONString, BaseResponse.class);
  		if (baseResponse.getCode().equals("200") && baseResponse.getStatus().equals("OK")) {
  			isWorksheetDataSortedSuccessfully = true;
  		}
  		
  		return isWorksheetDataSortedSuccessfully;
	}
	
	/**
	 * Get a specific column from a worksheet
	 * @param columnIndex Column Index
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return An object that contains column attributes 
	*/
	public Column getColumnFromAWorksheet(int columnIndex) throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		
		Column column = null;
		
		//build URL
      	String strURL = CELLS_URI + fileName + "/worksheets/" + worksheetName + "/cells/columns/" + columnIndex;
        //sign URL
        String signedURL = Utils.sign(strURL);
        
        InputStream responseStream = Utils.processCommand(signedURL, "GET");
        String responseJSONString = Utils.streamToString(responseStream);
        
        //Parsing JSON
  		Gson gson = new Gson();
  		GetColumnFromAWorksheetResponse columnFromAWorksheetResponse = gson.fromJson(responseJSONString, GetColumnFromAWorksheetResponse.class);
  		if (columnFromAWorksheetResponse.getCode().equals("200") && columnFromAWorksheetResponse.getStatus().equals("OK")) {
  			column = columnFromAWorksheetResponse.column;
  		}
        
  		return column;
	}
	
	/**
	 * Copy source worksheet to our worksheet.
	 * @param sourceSheetName Source worksheet name
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return Boolean variable that indicates whether source worksheet copied to our worksheet
	*/
	public boolean copyAWorksheet(String sourceSheetName) throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		
		boolean isWorksheetCopiedSuccessfully = false;
		
		//build URL
      	String strURL = CELLS_URI + fileName + "/worksheets/" + worksheetName + "/copy?sourceSheet=" + Uri.encode(sourceSheetName);
        //sign URL
        String signedURL = Utils.sign(strURL);
        
        InputStream responseStream = Utils.processCommand(signedURL, "POST");
        String responseJSONString = Utils.streamToString(responseStream);
        
        //Parsing JSON
  		Gson gson = new Gson();
  		BaseResponse baseResponse = gson.fromJson(responseJSONString, BaseResponse.class);
  		if (baseResponse.getCode().equals("200") && baseResponse.getStatus().equals("OK")) {
  			isWorksheetCopiedSuccessfully = true;
  		}
        
  		return isWorksheetCopiedSuccessfully;
	}
	
	/**
	 * Rename a worksheet
	 * @param newSheetName Worksheet new name
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return Boolean variable that indicates whether worksheet renamed successfully
	*/
	public boolean renameAWorksheet(String newSheetName) throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		
		boolean isWorksheetRenamedSuccessfully = false;
		
		//build URL
      	String strURL = CELLS_URI + fileName + "/worksheets/" + worksheetName + "/Rename?newname=" + Uri.encode(newSheetName);
        //sign URL
        String signedURL = Utils.sign(strURL);
        
        InputStream responseStream = Utils.processCommand(signedURL, "POST");
        String responseJSONString = Utils.streamToString(responseStream);
        
        //Parsing JSON
  		Gson gson = new Gson();
  		BaseResponse baseResponse = gson.fromJson(responseJSONString, BaseResponse.class);
  		if (baseResponse.getCode().equals("200") && baseResponse.getStatus().equals("OK")) {
  			isWorksheetRenamedSuccessfully = true;
  		}
        
  		return isWorksheetRenamedSuccessfully;
	}
	
	/**
	 * Update properties of a worksheet
	 * @param xmlData Resquest body
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return Boolean variable that indicates whether properties of a worksheet updated successfully
	*/
	public boolean updatePropertiesOfAWorksheet(String xmlData) throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		
		boolean isWorksheetRenamedSuccessfully = false;
		
		//build URL
      	String strURL = CELLS_URI + fileName + "/worksheets/" + worksheetName;
        //sign URL
        String signedURL = Utils.sign(strURL);
        
        InputStream responseStream = Utils.processCommand(signedURL, "POST", xmlData, "xml");
        String responseJSONString = Utils.streamToString(responseStream);
        
        //Parsing JSON
  		Gson gson = new Gson();
  		BaseResponse baseResponse = gson.fromJson(responseJSONString, BaseResponse.class);
  		if (baseResponse.getCode().equals("200") && baseResponse.getStatus().equals("OK")) {
  			isWorksheetRenamedSuccessfully = true;
  		}
        
  		return isWorksheetRenamedSuccessfully;
	}
	
	/**
	 * Set a background image or watermark image for a worksheet
	 * @param backgroundImageName Name of background image saved on cloud
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return Boolean variable that indicates whether background image set successfully updated
	*/
	public boolean setABackgroundImageOrWatermarkImageForAWorksheet(String backgroundImageName) throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		
		boolean isBackgroundImageSetSuccessfully = false;
		
		//build URL
      	String strURL = CELLS_URI + fileName + "/worksheets/" + worksheetName + "/Background?imagefile=" + Uri.encode(backgroundImageName);
        //sign URL
        String signedURL = Utils.sign(strURL);
        
        InputStream responseStream = Utils.processCommand(signedURL, "PUT");
        String responseJSONString = Utils.streamToString(responseStream);
        
        //Parsing JSON
  		Gson gson = new Gson();
  		BaseResponse baseResponse = gson.fromJson(responseJSONString, BaseResponse.class);
  		if (baseResponse.getCode().equals("200") && baseResponse.getStatus().equals("OK")) {
  			isBackgroundImageSetSuccessfully = true;
  		}
        
  		return isBackgroundImageSetSuccessfully;
	}
	
	/**
	 * Freeze panes in a worksheet
	 * @param rowNumber
	 * @param columnNumber
	 * @param freezedRows
	 * @param freezedColumns 
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return Boolean variable that indicates whether specified area of worksheet get freezed successfully
	*/
	public boolean freezePanesInAWorksheet(int rowNumber, int columnNumber, int freezedRows, int freezedColumns) throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		
		boolean isPanesFreezedSuccessfully = false;
		
		//build URL
      	String strURL = CELLS_URI + fileName + "/worksheets/" + worksheetName + "/FreezePanes?row=" + rowNumber + "&column=" + columnNumber +
      			"&freezedRows=" + freezedRows + "&freezedColumns=" + freezedColumns;
        //sign URL
        String signedURL = Utils.sign(strURL);
        
        InputStream responseStream = Utils.processCommand(signedURL, "PUT");
        String responseJSONString = Utils.streamToString(responseStream);
        
        //Parsing JSON
  		Gson gson = new Gson();
  		BaseResponse baseResponse = gson.fromJson(responseJSONString, BaseResponse.class);
  		if (baseResponse.getCode().equals("200") && baseResponse.getStatus().equals("OK")) {
  			isPanesFreezedSuccessfully = true;
  		}
  		
  		return isPanesFreezedSuccessfully;
	}
	
	/**
	 * Unfreeze panes in a worksheet
	 * @param rowNumber
	 * @param columnNumber
	 * @param freezedRows
	 * @param freezedColumns 
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return Boolean variable that indicates whether specified area of worksheet get unfreezed successfully
	*/
	public boolean unFreezePanesInAWorksheet(int rowNumber, int columnNumber, int freezedRows, int freezedColumns) throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		
		boolean isPanesUnFreezedSuccessfully = false;
		
		//build URL
      	String strURL = CELLS_URI + fileName + "/worksheets/" + worksheetName + "/FreezePanes?row=" + rowNumber + "&column=" + columnNumber +
      			"&freezedRows=" + freezedRows + "&freezedColumns=" + freezedColumns;
        //sign URL
        String signedURL = Utils.sign(strURL);
        
        InputStream responseStream = Utils.processCommand(signedURL, "DELETE");
        String responseJSONString = Utils.streamToString(responseStream);
        
        //Parsing JSON
  		Gson gson = new Gson();
  		BaseResponse baseResponse = gson.fromJson(responseJSONString, BaseResponse.class);
  		if (baseResponse.getCode().equals("200") && baseResponse.getStatus().equals("OK")) {
  			isPanesUnFreezedSuccessfully = true;
  		}
  		
  		return isPanesUnFreezedSuccessfully;
	}
 
	/**
	 * Delete a background or watermark image of a excel worksheet
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return Boolean variable that indicates whether background image deleted successfully
	*/
	public boolean deleteABackgroundOrWatermarkImageOfAWorksheet() throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		
		boolean isBackgroundImageDeletedSuccessfully = false;
		
		//build URL
      	String strURL = CELLS_URI + fileName + "/worksheets/" + worksheetName + "/Background";
        //sign URL
        String signedURL = Utils.sign(strURL);
        
        InputStream responseStream = Utils.processCommand(signedURL, "DELETE");
        String responseJSONString = Utils.streamToString(responseStream);
        
        //Parsing JSON
  		Gson gson = new Gson();
  		BaseResponse baseResponse = gson.fromJson(responseJSONString, BaseResponse.class);
  		if (baseResponse.getCode().equals("200") && baseResponse.getStatus().equals("OK")) {
  			isBackgroundImageDeletedSuccessfully = true;
  		}
  		
  		return isBackgroundImageDeletedSuccessfully;
	}

}
