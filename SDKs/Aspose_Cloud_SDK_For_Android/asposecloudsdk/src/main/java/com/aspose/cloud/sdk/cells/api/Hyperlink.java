package com.aspose.cloud.sdk.cells.api;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import android.net.Uri;

import com.aspose.cloud.sdk.cells.model.HyperlinkResponse;
import com.aspose.cloud.sdk.cells.model.HyperlinkResponse.HyperlinkData;
import com.aspose.cloud.sdk.common.AsposeApp;
import com.aspose.cloud.sdk.common.BaseResponse;
import com.aspose.cloud.sdk.common.Utils;
import com.google.gson.Gson;

/**
 * Hyperlink --- Using this class you can get a specific hyperlink from a worksheet, Add a hyperlink to a worksheet, 
 * Update a hyperlink in a worksheet and Delete hyperlinks from a worksheet.
 * @author   M. Sohail Ismail
 */
public class Hyperlink {
	
	private static final String CELLS_URI = AsposeApp.BASE_PRODUCT_URI + "/cells/";
	
	private String fileName;
	private String worksheetName;
	
	public Hyperlink(String fileName, String worksheetName) {
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
	 * Get a specific hyperlink from a worksheet
	 * @param hyperlinkIndex Hyperlink index 
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return An object that contains attributes of hyperlink
	*/ 
	public HyperlinkData getHyperlinkFromExcelWorksheet(int hyperlinkIndex) throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		
		HyperlinkData hyperlink = null;
		
		if(fileName == null || fileName.length() == 0) {
			throw new IllegalArgumentException("File name cannot be null or empty");
		}
		
		if(worksheetName == null || worksheetName.length() == 0) {
			throw new IllegalArgumentException("Worksheet name cannot be null or empty");
		}
		
		String strURL = CELLS_URI + fileName + "/worksheets/" + worksheetName + 
				"/hyperlinks/" + hyperlinkIndex;
						
        //sign URL
        String signedURL = Utils.sign(strURL);
        
		InputStream responseStream = Utils.processCommand(signedURL, "GET");
		String responseJSONString = Utils.streamToString(responseStream);
        
        //Parsing JSON
		Gson gson = new Gson();
		HyperlinkResponse hyperlinkResponse = gson.fromJson(responseJSONString, HyperlinkResponse.class);
		if (hyperlinkResponse.getCode().equals("200") && hyperlinkResponse.getStatus().equals("OK")) {
			hyperlink = hyperlinkResponse.hyperlink;
		}
		
		return hyperlink;
	}
	
	/**
	 * Add a hyperlink to a worksheet
	 * @param firstRow Starting row index
	 * @param firstColumn Starting column index
	 * @param totalRows Hyperlink area span to this number of rows 
	 * @param totalColumns Hyperlink area span to this number of columns
	 * @param address Hyperlink address e.g. address=www.aspose.com
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return An object that contains attributes of hyperlink
	*/ 
	public HyperlinkData addHyperlinksToExcelWorksheet(int firstRow, int firstColumn, 
			int totalRows, int totalColumns, String address) throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		
		HyperlinkData hyperlink = null;
		
		if(fileName == null || fileName.length() == 0) {
			throw new IllegalArgumentException("File name cannot be null or empty");
		}
		
		if(worksheetName == null || worksheetName.length() == 0) {
			throw new IllegalArgumentException("Worksheet name cannot be null or empty");
		}
		
		String strURL = CELLS_URI + fileName + "/worksheets/" + worksheetName + 
				"/hyperlinks?firstRow=" + firstRow + "&firstColumn=" + firstColumn + "&totalRows=" + totalRows + 
				"&totalColumns=" + totalColumns + "&address=" + Uri.encode(address);
						
        //sign URL
        String signedURL = Utils.sign(strURL);
        
		InputStream responseStream = Utils.processCommand(signedURL, "PUT");
		String responseJSONString = Utils.streamToString(responseStream);
        
        //Parsing JSON
		Gson gson = new Gson();
		HyperlinkResponse hyperlinkResponse = gson.fromJson(responseJSONString, HyperlinkResponse.class);
		if (hyperlinkResponse.getCode().equals("200") && hyperlinkResponse.getStatus().equals("OK")) {
			hyperlink = hyperlinkResponse.hyperlink;
		}
		
		return hyperlink;
	}
	
	/**
	 * Update a hyperlink in a worksheet
	 * @param hyperlinkIndex Hyperlink index
	 * @param hyperlinkRequest Hyperlink request
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return An object that contains updated attributes of hyperlink
	*/ 
	public HyperlinkData updateHyperlinksInExcelWorksheet(int hyperlinkIndex, String hyperlinkRequest) throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		
		HyperlinkData hyperlink = null;
		
		if(fileName == null || fileName.length() == 0) {
			throw new IllegalArgumentException("File name cannot be null or empty");
		}
		
		if(worksheetName == null || worksheetName.length() == 0) {
			throw new IllegalArgumentException("Worksheet name cannot be null or empty");
		}
		
		String strURL = CELLS_URI + fileName + "/worksheets/" + worksheetName + 
				"/hyperlinks/" + hyperlinkIndex;
						
        //sign URL
        String signedURL = Utils.sign(strURL);
        
		InputStream responseStream = Utils.processCommand(signedURL, "POST", hyperlinkRequest, "xml");
		String responseJSONString = Utils.streamToString(responseStream);
        
        //Parsing JSON
		Gson gson = new Gson();
		HyperlinkResponse hyperlinkResponse = gson.fromJson(responseJSONString, HyperlinkResponse.class);
		if (hyperlinkResponse.getCode().equals("200") && hyperlinkResponse.getStatus().equals("OK")) {
			hyperlink = hyperlinkResponse.hyperlink;
		}
		
		return hyperlink;
	}
	
	/**
	 * Delete all hyperlinks from excel worksheet
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return Boolean variable that indicates whether all hyperlinks deleted successfully from a worksheet
	*/ 
	public boolean deleteAllHyperlinksFromExcelWorksheet() throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		
		boolean isAllHyperlinksDeletedSuccessfully = false;
		
		if(fileName == null || fileName.length() == 0) {
			throw new IllegalArgumentException("File name cannot be null or empty");
		}
		
		if(worksheetName == null || worksheetName.length() == 0) {
			throw new IllegalArgumentException("Worksheet name cannot be null or empty");
		}
		
		String strURL = CELLS_URI + fileName + "/worksheets/" + worksheetName + 
				"/hyperlinks";
						
        //sign URL
        String signedURL = Utils.sign(strURL);
        
		InputStream responseStream = Utils.processCommand(signedURL, "DELETE");
		String responseJSONString = Utils.streamToString(responseStream);
        
        //Parsing JSON
		Gson gson = new Gson();
		BaseResponse baseResponse = gson.fromJson(responseJSONString, BaseResponse.class);
		if (baseResponse.getCode().equals("200") && baseResponse.getStatus().equals("OK")) {
			isAllHyperlinksDeletedSuccessfully = true;
		}
		
		return isAllHyperlinksDeletedSuccessfully;
	}
	
	/**
	 * Delete a specific hyperlink from a worksheet
	 * @param hyperlinkIndex Hyperlink index
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return Boolean variable that indicates whether a hyperlink deleted successfully from a worksheet
	*/
	public boolean deleteASpecificHyperlinkFromExcelWorksheet(int hyperlinkIndex) throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		
		boolean isAHyperlinkDeletedSuccessfully = false;
		
		if(fileName == null || fileName.length() == 0) {
			throw new IllegalArgumentException("File name cannot be null or empty");
		}
		
		if(worksheetName == null || worksheetName.length() == 0) {
			throw new IllegalArgumentException("Worksheet name cannot be null or empty");
		}
		
		String strURL = CELLS_URI + fileName + "/worksheets/" + worksheetName + 
				"/hyperlinks/" + hyperlinkIndex;
						
        //sign URL
        String signedURL = Utils.sign(strURL);
        
		InputStream responseStream = Utils.processCommand(signedURL, "DELETE");
		String responseJSONString = Utils.streamToString(responseStream);
        
        //Parsing JSON
		Gson gson = new Gson();
		BaseResponse baseResponse = gson.fromJson(responseJSONString, BaseResponse.class);
		if (baseResponse.getCode().equals("200") && baseResponse.getStatus().equals("OK")) {
			isAHyperlinkDeletedSuccessfully = true;
		}
		
		return isAHyperlinkDeletedSuccessfully;
	}
}
