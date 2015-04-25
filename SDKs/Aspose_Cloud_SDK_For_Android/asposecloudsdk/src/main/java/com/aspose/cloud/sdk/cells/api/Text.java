package com.aspose.cloud.sdk.cells.api;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import android.net.Uri;

import com.aspose.cloud.sdk.cells.model.TextItemsResponse;
import com.aspose.cloud.sdk.cells.model.TextItemsResponse.TextItemsData;
import com.aspose.cloud.sdk.cells.model.MatchesResponse;
import com.aspose.cloud.sdk.common.AsposeApp;
import com.aspose.cloud.sdk.common.Utils;
import com.google.gson.Gson;

/**
 * Text --- Using this class you can find text in a workbook or in a worksheet, Get all text items from a workbook or from a worksheet,
 * Replace text in a workbook or in a worksheet
 * @author   M. Sohail Ismail
 */
public class Text {
	private static final String CELLS_URI = AsposeApp.BASE_PRODUCT_URI + "/cells/";
	
	/**
	 * Find text in a workbook
	 * @param workbookName Name of workbook stored on cloud
	 * @param textToFind The text to find 
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return An object that contains attributes of text item
	*/ 
	public static TextItemsData findTextInAWorkbook(String workbookName, String textToFind) throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		
		TextItemsData textItems = null;
		
		if(workbookName == null || workbookName.length() == 0) {
			throw new IllegalArgumentException("Workbook name cannot be null or empty");
		}
		
		if(textToFind == null || textToFind.length() == 0) {
			throw new IllegalArgumentException("Text to find cannot be null or empty");
		}
		
		String strURL = CELLS_URI + Uri.encode(workbookName) + "/findText?text=" + Uri.encode(textToFind);
        //sign URL
        String signedURL = Utils.sign(strURL);
        
		InputStream responseStream = Utils.processCommand(signedURL, "POST");
		String responseJSONString = Utils.streamToString(responseStream);
        
        //Parsing JSON
		Gson gson = new Gson();
		TextItemsResponse textItemsResponse = gson.fromJson(responseJSONString, TextItemsResponse.class);
		if (textItemsResponse.getCode().equals("200") && textItemsResponse.getStatus().equals("OK")) {
			textItems = textItemsResponse.textItems;
		}
		
		return textItems;
	}
	
	/**
	 * Find text in a worksheet
	 * @param workbookName Name of workbook stored on cloud
	 * @param worksheetName Worksheet name
	 * @param textToFind The text to find 
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return An object that contains attributes of text item
	*/ 
	public static TextItemsData findTextInAWorksheet(String workbookName, String worksheetName, String textToFind) throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		
		TextItemsData textItems = null;
		
		if(workbookName == null || workbookName.length() == 0) {
			throw new IllegalArgumentException("Workbook name cannot be null or empty");
		}
		
		if(worksheetName == null || worksheetName.length() == 0) {
			throw new IllegalArgumentException("Worksheet name cannot be null or empty");
		}
		
		if(textToFind == null || textToFind.length() == 0) {
			throw new IllegalArgumentException("Text to find cannot be null or empty");
		}
		
		String strURL = CELLS_URI + Uri.encode(workbookName) + "/worksheets/" + Uri.encode(worksheetName) + "/findText?text=" + Uri.encode(textToFind);
        //sign URL
        String signedURL = Utils.sign(strURL);
        
		InputStream responseStream = Utils.processCommand(signedURL, "POST");
		String responseJSONString = Utils.streamToString(responseStream);
        
        //Parsing JSON
		Gson gson = new Gson();
		TextItemsResponse textItemsResponse = gson.fromJson(responseJSONString, TextItemsResponse.class);
		if (textItemsResponse.getCode().equals("200") && textItemsResponse.getStatus().equals("OK")) {
			textItems = textItemsResponse.textItems;
		}
		
		return textItems;
	}
	
	/**
	 * Get all text items from a workbook
	 * @param workbookName Name of workbook stored on cloud
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return An object that contains list of text items
	*/ 
	public static TextItemsData getTextItemsFromAWorkbook(String workbookName) throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		
		TextItemsData textItems = null;
		
		if(workbookName == null || workbookName.length() == 0) {
			throw new IllegalArgumentException("Workbook name cannot be null or empty");
		}
		
		String strURL = CELLS_URI + Uri.encode(workbookName) + "/textItems";
        //sign URL
        String signedURL = Utils.sign(strURL);
        
		InputStream responseStream = Utils.processCommand(signedURL, "GET");
		String responseJSONString = Utils.streamToString(responseStream);
        
        //Parsing JSON
		Gson gson = new Gson();
		TextItemsResponse textItemsResponse = gson.fromJson(responseJSONString, TextItemsResponse.class);
		if (textItemsResponse.getCode().equals("200") && textItemsResponse.getStatus().equals("OK")) {
			textItems = textItemsResponse.textItems;
		}
		
		return textItems;
	}
	
	/**
	 * Get all text items from a worksheet
	 * @param workbookName Name of workbook stored on cloud
	 * @param worksheetName Worksheet name
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return An object that contains list of text items
	*/ 
	public static TextItemsData getTextItemsFromAWorksheet(String workbookName, String worksheetName) throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		
		TextItemsData textItems = null;
		
		if(workbookName == null || workbookName.length() == 0) {
			throw new IllegalArgumentException("Workbook name cannot be null or empty");
		}
		
		if(worksheetName == null || worksheetName.length() == 0) {
			throw new IllegalArgumentException("Worksheet name cannot be null or empty");
		}
		
		String strURL = CELLS_URI + Uri.encode(workbookName) + "/worksheets/" + Uri.encode(worksheetName) + "/textItems";
        //sign URL
        String signedURL = Utils.sign(strURL);
        
		InputStream responseStream = Utils.processCommand(signedURL, "GET");
		String responseJSONString = Utils.streamToString(responseStream);
        
        //Parsing JSON
		Gson gson = new Gson();
		TextItemsResponse textItemsResponse = gson.fromJson(responseJSONString, TextItemsResponse.class);
		if (textItemsResponse.getCode().equals("200") && textItemsResponse.getStatus().equals("OK")) {
			textItems = textItemsResponse.textItems;
		}
		
		return textItems;
	}
	
	/**
	 * Replace text in a workbook
	 * @param workbookName Name of workbook stored on cloud
	 * @param oldValue The string to be replaced
	 * @param newValue The string to replace all occurrences of oldValue
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return Number of matches
	*/ 
	public static int replaceTextInAWorkbook(String workbookName, String oldValue, String newValue) throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		
		int matchesCount = -1;
		
		if(workbookName == null || workbookName.length() == 0) {
			throw new IllegalArgumentException("Workbook name cannot be null or empty");
		}
		
		if(oldValue == null || oldValue.length() == 0) {
			throw new IllegalArgumentException("Old value cannot be null or empty");
		}
		
		if(newValue == null || newValue.length() == 0) {
			throw new IllegalArgumentException("New value cannot be null or empty");
		}
		
		String strURL = CELLS_URI + Uri.encode(workbookName) + "/replaceText?oldValue=" + Uri.encode(oldValue) + "&newValue=" + Uri.encode(newValue);
        //sign URL
        String signedURL = Utils.sign(strURL);
        
		InputStream responseStream = Utils.processCommand(signedURL, "POST");
		String responseJSONString = Utils.streamToString(responseStream);
        
        //Parsing JSON
		Gson gson = new Gson();
		MatchesResponse matchesResponse = gson.fromJson(responseJSONString, MatchesResponse.class);
		if (matchesResponse.getCode().equals("200") && matchesResponse.getStatus().equals("OK")) {
			matchesCount = matchesResponse.Matches;
		}
		
		return matchesCount;
	}
		
	/**
	 * Replace text in a worksheet
	 * @param workbookName Name of workbook stored on cloud
	 * @param worksheetName Worksheet name
	 * @param oldValue The string to be replaced
	 * @param newValue The string to replace all occurrences of oldValue
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return Number of matches
	*/ 
	public static int replaceTextInAWorksheet(String workbookName, String worksheetName, String oldValue, String newValue) throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		
		int matchesCount = -1;
		
		if(workbookName == null || workbookName.length() == 0) {
			throw new IllegalArgumentException("Workbook name cannot be null or empty");
		}
		
		if(worksheetName == null || worksheetName.length() == 0) {
			throw new IllegalArgumentException("Worksheet name cannot be null or empty");
		}
		
		if(oldValue == null || oldValue.length() == 0) {
			throw new IllegalArgumentException("Old value cannot be null or empty");
		}
		
		if(newValue == null || newValue.length() == 0) {
			throw new IllegalArgumentException("New value cannot be null or empty");
		}
		
		String strURL = CELLS_URI + Uri.encode(workbookName) + "/worksheets/" + Uri.encode(worksheetName) + "/replaceText?oldValue=" + Uri.encode(oldValue) + "&newValue=" + Uri.encode(newValue);
        //sign URL
        String signedURL = Utils.sign(strURL);
        
		InputStream responseStream = Utils.processCommand(signedURL, "POST");
		String responseJSONString = Utils.streamToString(responseStream);
        
        //Parsing JSON
		Gson gson = new Gson();
		MatchesResponse matchesResponse = gson.fromJson(responseJSONString, MatchesResponse.class);
		if (matchesResponse.getCode().equals("200") && matchesResponse.getStatus().equals("OK")) {
			matchesCount = matchesResponse.Matches;
		}
		
		return matchesCount;
	}
}
