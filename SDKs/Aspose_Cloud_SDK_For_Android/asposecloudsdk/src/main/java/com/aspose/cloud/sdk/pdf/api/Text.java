package com.aspose.cloud.sdk.pdf.api;

import android.net.Uri;

import com.aspose.cloud.sdk.common.AsposeApp;
import com.aspose.cloud.sdk.common.Utils;
import com.aspose.cloud.sdk.pdf.model.MultipleTextReplacesRequest;
import com.aspose.cloud.sdk.pdf.model.TextFormatResponse;
import com.aspose.cloud.sdk.pdf.model.TextFormatResponse.TextFormat;
import com.aspose.cloud.sdk.pdf.model.TextItemsResponse;
import com.aspose.cloud.sdk.pdf.model.TextItemsResponse.TextItems;
import com.aspose.cloud.sdk.pdf.model.DocumentResponse;
import com.aspose.cloud.sdk.pdf.model.TextReplace;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * Text --- Using this class you can get all text items, get fragment count and segment count, 
 * get text format and replace text in a PDF file.
 * @author   M. Sohail Ismail
 */
public class Text {
	
	private static final String PDF_URI = AsposeApp.BASE_PRODUCT_URI + "/pdf/";
	
	/**
	 * Get all text items from a particular fragment of a PDF
	 * @param fileName Name of the file stored on cloud
	 * @param pageIndex Index of page starting from 1
	 * @param fragmentIndex Index of fragment starting from 1
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return An object that contains details of all text items of a particular fragment of a PDF
	*/ 
	public static TextItems getAllTextItemsFromAParticularFragmentOfAPDF(String fileName, int pageIndex, int fragmentIndex) throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		
		TextItems textItems = null;
		
		if(fileName == null || fileName.length() == 0) {
			throw new IllegalArgumentException("File name cannot be null or empty");
		}
		
		//build URL
		String strURL = PDF_URI + Uri.encode(fileName) + "/pages/" + pageIndex + "/fragments/" + fragmentIndex + "/textItems";
		
		//sign URL
		String signedURL = Utils.sign(strURL);
		InputStream responseStream = Utils.processCommand(signedURL, "GET");
		String responseJSONString = Utils.streamToString(responseStream);
		
        //Parsing JSON
		Gson gson = new Gson();
		TextItemsResponse textItemsResponse = gson.fromJson(responseJSONString, TextItemsResponse.class);
		if(textItemsResponse.getCode().equals("200") && textItemsResponse.getStatus().equals("OK")) {
			textItems = textItemsResponse.textItems;
		}
		
		return textItems;
	}
	
	/**
	 * Get all text items from a particular PDF document
	 * @param fileName Name of the file stored on cloud
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return An object that contains details of all text items
	*/ 
	public static TextItems getAllTextItemsFromAPDFDocument(String fileName) throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		
		TextItems textItems = null;
		
		if(fileName == null || fileName.length() == 0) {
			throw new IllegalArgumentException("File name cannot be null or empty");
		}
		
		//build URL
		String strURL = PDF_URI + Uri.encode(fileName) + "/textItems";
		
		//sign URL
		String signedURL = Utils.sign(strURL);
		InputStream responseStream = Utils.processCommand(signedURL, "GET");
		String responseJSONString = Utils.streamToString(responseStream);
		
        //Parsing JSON
		Gson gson = new Gson();
		TextItemsResponse textItemsResponse = gson.fromJson(responseJSONString, TextItemsResponse.class);
		if(textItemsResponse.getCode().equals("200") && textItemsResponse.getStatus().equals("OK")) {
			textItems = textItemsResponse.textItems;
		}
		
		return textItems;
	}
	
	/**
	 * Get all text items from a PDF page
	 * @param fileName Name of the file stored on cloud
	 * @param pageIndex Index of page starting from 1
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return An object that contains details of all text items of a particular PDF page
	*/ 
	public static TextItems getAllTextItemsFromAPDFPage(String fileName, int pageIndex) throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		
		TextItems textItems = null;
		
		if(fileName == null || fileName.length() == 0) {
			throw new IllegalArgumentException("File name cannot be null or empty");
		}
		
		//build URL
		String strURL = PDF_URI + Uri.encode(fileName) + "/pages/" + pageIndex + "/textItems";
		
		//sign URL
		String signedURL = Utils.sign(strURL);
		InputStream responseStream = Utils.processCommand(signedURL, "GET");
		String responseJSONString = Utils.streamToString(responseStream);
		
        //Parsing JSON
		Gson gson = new Gson();
		TextItemsResponse textItemsResponse = gson.fromJson(responseJSONString, TextItemsResponse.class);
		if(textItemsResponse.getCode().equals("200") && textItemsResponse.getStatus().equals("OK")) {
			textItems = textItemsResponse.textItems;
		}
		
		return textItems;
	}
	
	/**
	 * Get fragment count from a PDF page
	 * @param fileName Name of the file stored on cloud
	 * @param pageIndex Index of page starting from 1
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return Number of fragments in a requested page
	*/ 
	public static int getFragmentCountFromAPDFPage(String fileName, int pageIndex) throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		
		int fragmentsCount = -1;
		
		if(fileName == null || fileName.length() == 0) {
			throw new IllegalArgumentException("File name cannot be null or empty");
		}
		
		//build URL
		String strURL = PDF_URI + Uri.encode(fileName) + "/pages/" + pageIndex + "/fragments";
		
		//sign URL
		String signedURL = Utils.sign(strURL);
		InputStream responseStream = Utils.processCommand(signedURL, "GET");
		String responseJSONString = Utils.streamToString(responseStream);
		
        //Parsing JSON
		Gson gson = new Gson();
		TextItemsResponse textItemsResponse = gson.fromJson(responseJSONString, TextItemsResponse.class);
		if(textItemsResponse.getCode().equals("200") && textItemsResponse.getStatus().equals("OK")) {
			fragmentsCount = textItemsResponse.textItems.List.size();
		}
		
		return fragmentsCount;
	}
	
	/**
	 * Get segment count from a PDF fragment
	 * @param fileName Name of the file stored on cloud
	 * @param pageIndex Index of page starting from 1
	 * @param fragmentIndex Index of fragment starting from 1
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return Number of segments in a requested fragment
	*/ 
	public static int getSegmentCountFromAPDFFragment(String fileName, int pageIndex, int fragmentIndex) throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		
		int segmentCount = -1;
		
		if(fileName == null || fileName.length() == 0) {
			throw new IllegalArgumentException("File name cannot be null or empty");
		}
		
		//build URL
		String strURL = PDF_URI + Uri.encode(fileName) + "/pages/" + pageIndex + "/fragments/" + fragmentIndex;
		
		//sign URL
		String signedURL = Utils.sign(strURL);
		InputStream responseStream = Utils.processCommand(signedURL, "GET");
		String responseJSONString = Utils.streamToString(responseStream);
		
        //Parsing JSON
		Gson gson = new Gson();
		TextItemsResponse textItemsResponse = gson.fromJson(responseJSONString, TextItemsResponse.class);
		if(textItemsResponse.getCode().equals("200") && textItemsResponse.getStatus().equals("OK")) {
			segmentCount = textItemsResponse.textItems.List.size();
		}
		
		return segmentCount;
	}
	
	/**
	 * Get text format of a particular segment
	 * @param fileName Name of the file stored on cloud
	 * @param pageIndex Index of page starting from 1
	 * @param fragmentIndex Index of fragment starting from 1
	 * @param segmentIndex Index of segment starting from 1
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return Requested text format details
	*/ 
	public static TextFormat getTextFormatOfAParticularSegment(String fileName, int pageIndex, int fragmentIndex, int segmentIndex) throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		
		TextFormat textFormat = null;
		
		if(fileName == null || fileName.length() == 0) {
			throw new IllegalArgumentException("File name cannot be null or empty");
		}
		
		//build URL
		String strURL = PDF_URI + Uri.encode(fileName) + "/pages/" + pageIndex + "/fragments/" + fragmentIndex + 
				"/segments/" + segmentIndex + "/textformat";
		//sign URL
		String signedURL = Utils.sign(strURL);
		
		InputStream responseStream = Utils.processCommand(signedURL, "GET");
		String responseJSONString = Utils.streamToString(responseStream);
		
        //Parsing JSON
		Gson gson = new Gson();
		TextFormatResponse textFormatResponse = gson.fromJson(responseJSONString, TextFormatResponse.class);
		if(textFormatResponse.getCode().equals("200") && textFormatResponse.getStatus().equals("OK")) {
			textFormat = textFormatResponse.textFormat;
		}
		
		return textFormat;
	}
	
	/**
	 * Get text format of a PDF fragment
	 * @param fileName Name of the file stored on cloud
	 * @param pageIndex Index of page starting from 1
	 * @param fragmentIndex Index of fragment starting from 1
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return Requested text format details
	*/ 
	public static TextFormat getTextFormatOfAPDFFragment(String fileName, int pageIndex, int fragmentIndex) throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		
		TextFormat textFormat = null;
		
		if(fileName == null || fileName.length() == 0) {
			throw new IllegalArgumentException("File name cannot be null or empty");
		}
		
		//build URL
		String strURL = PDF_URI + Uri.encode(fileName) + "/pages/" + pageIndex + "/fragments/" + fragmentIndex + "/textformat";
		//sign URL
		String signedURL = Utils.sign(strURL);
		
		InputStream responseStream = Utils.processCommand(signedURL, "GET");
		String responseJSONString = Utils.streamToString(responseStream);
		
        //Parsing JSON
		Gson gson = new Gson();
		TextFormatResponse textFormatResponse = gson.fromJson(responseJSONString, TextFormatResponse.class);
		if(textFormatResponse.getCode().equals("200") && textFormatResponse.getStatus().equals("OK")) {
			textFormat = textFormatResponse.textFormat;
		}
		
		return textFormat;
	}
	
	/**
	 * Replace multiple texts in a PDF
	 * @param fileName Name of the file stored on cloud
	 * @param multipleTextReplacesRequest Texts to be replaced
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return Number of matches
	*/ 
	public static int replaceMultipleTextsInAPDF(String fileName, MultipleTextReplacesRequest multipleTextReplacesRequest) throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		
		int matchesCount = -1;
		
		if(fileName == null || fileName.length() == 0) {
			throw new IllegalArgumentException("File name cannot be null or empty");
		}
		
		GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        String requestJSONString = gson.toJson(multipleTextReplacesRequest, MultipleTextReplacesRequest.class);
        
		//build URL
		String strURL = PDF_URI + Uri.encode(fileName) + "/replaceTextList";
		//sign URL
		String signedURL = Utils.sign(strURL);
		
		InputStream responseStream = Utils.processCommand(signedURL, "POST", requestJSONString);
		String responseJSONString = Utils.streamToString(responseStream);
		
        //Parsing JSON
		DocumentResponse documentResponse = gson.fromJson(responseJSONString, DocumentResponse.class);
		if(documentResponse.getCode().equals("200") && documentResponse.getStatus().equals("OK")) {
			matchesCount = documentResponse.Matches;
		}
		
		return matchesCount;
	}
	
	/**
	 * Replace multiple texts in a PDF page
	 * @param fileName Name of the file stored on cloud
	 * @param pageIndex Index of page starting from 1
	 * @param multipleTextReplacesRequest Texts to be replaced
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return Number of matches
	*/ 
	public static int replaceMultipleTextsInAPDFPage(String fileName, int pageIndex, MultipleTextReplacesRequest multipleTextReplacesRequest) throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		
		int matchesCount = -1;
		
		if(fileName == null || fileName.length() == 0) {
			throw new IllegalArgumentException("File name cannot be null or empty");
		}
		
		GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        String requestJSONString = gson.toJson(multipleTextReplacesRequest, MultipleTextReplacesRequest.class);
        
		//build URL
		String strURL = PDF_URI + Uri.encode(fileName) + "/pages/" + pageIndex + "/replaceTextList";
		//sign URL
		String signedURL = Utils.sign(strURL);
		
		InputStream responseStream = Utils.processCommand(signedURL, "POST", requestJSONString);
		String responseJSONString = Utils.streamToString(responseStream);
		
        //Parsing JSON
		DocumentResponse documentResponse = gson.fromJson(responseJSONString, DocumentResponse.class);
		if(documentResponse.getCode().equals("200") && documentResponse.getStatus().equals("OK")) {
			matchesCount = documentResponse.Matches;
		}
		
		return matchesCount;
	}
	
	/**
	 * Replace text in a PDF file
	 * @param fileName Name of the file stored on cloud
	 * @param textReplace Text to be replaced
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return Number of matches
	*/ 
	public static int replaceTextInAPDFFile(String fileName, TextReplace textReplace) throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		
		int matchesCount = -1;
		
		if(fileName == null || fileName.length() == 0) {
			throw new IllegalArgumentException("File name cannot be null or empty");
		}
		
		GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        String requestJSONString = gson.toJson(textReplace, TextReplace.class);
        
		//build URL
		String strURL = PDF_URI + Uri.encode(fileName) + "/replaceText";
		//sign URL
		String signedURL = Utils.sign(strURL);
		
		InputStream responseStream = Utils.processCommand(signedURL, "POST", requestJSONString);
		String responseJSONString = Utils.streamToString(responseStream);
		
        //Parsing JSON
		DocumentResponse documentResponse = gson.fromJson(responseJSONString, DocumentResponse.class);
		if(documentResponse.getCode().equals("200") && documentResponse.getStatus().equals("OK")) {
			matchesCount = documentResponse.Matches;
		}
		
		return matchesCount;
	}
	
	/**
	 * Replace text in a PDF page
	 * @param fileName Name of the file stored on cloud
	 * @param pageIndex Index of page starting from 1
	 * @param textReplace Text to be replaced
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return Number of matches
	*/ 
	public static int replaceTextInAPDFPage(String fileName, int pageIndex, TextReplace textReplace) throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		
		int matchesCount = -1;
		
		if(fileName == null || fileName.length() == 0) {
			throw new IllegalArgumentException("File name cannot be null or empty");
		}
		
		GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        String requestJSONString = gson.toJson(textReplace, TextReplace.class);
        
		//build URL
		String strURL = PDF_URI + Uri.encode(fileName) + "/pages/" + pageIndex + "/replaceText";
		//sign URL
		String signedURL = Utils.sign(strURL);
		
		InputStream responseStream = Utils.processCommand(signedURL, "POST", requestJSONString);
		String responseJSONString = Utils.streamToString(responseStream);
		
        //Parsing JSON
		DocumentResponse documentResponse = gson.fromJson(responseJSONString, DocumentResponse.class);
		if(documentResponse.getCode().equals("200") && documentResponse.getStatus().equals("OK")) {
			matchesCount = documentResponse.Matches;
		}
		
		return matchesCount;
	}
}
