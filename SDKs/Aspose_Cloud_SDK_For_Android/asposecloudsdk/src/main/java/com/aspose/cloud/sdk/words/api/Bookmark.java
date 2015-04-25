package com.aspose.cloud.sdk.words.api;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import android.net.Uri;

import com.aspose.cloud.sdk.common.AsposeApp;
import com.aspose.cloud.sdk.common.Utils;
import com.aspose.cloud.sdk.words.model.BookmarkDataModel;
import com.aspose.cloud.sdk.words.model.GetASpecificBookmarkResponse;
import com.aspose.cloud.sdk.words.model.GetASpecificBookmarkResponse.BookmarkEnvelop;
import com.aspose.cloud.sdk.words.model.GetBookmarkResponse;
import com.aspose.cloud.sdk.words.model.GetBookmarkResponse.BookmarksEnvelop;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Bookmark --- Using this class you can get all Bookmarks from a Word document, a specific Bookmark from a Word document
 *  and you can also update Bookmark text of a Word document.
 * @author   M. Sohail Ismail
 */
public class Bookmark {
	
	private static final String WORD_URI = AsposeApp.BASE_PRODUCT_URI + "/words/";
	
	/**
	 * Get all Bookmarks from a Word document.
	 * @param fileName Name of the MS Word document on cloud
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return An object that contains list of Bookmarks
	*/ 
	public static BookmarksEnvelop getAllBookmarksFromAWordDocument(String fileName) throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		BookmarksEnvelop bookmarks = null;
		
		if(fileName == null || fileName.length() <= 3) {
			throw new IllegalArgumentException("File name cannot be null or empty");
		}
		
		//build URL
      	String strURL = WORD_URI + Uri.encode(fileName) + "/bookmarks";
        //sign URL
        String signedURL = Utils.sign(strURL);
        
        InputStream responseStream = Utils.processCommand(signedURL, "GET");
        String responseJSONString = Utils.streamToString(responseStream);
        
        //Parsing JSON
      	Gson gson = new Gson();
      	GetBookmarkResponse bookmarkResponse = gson.fromJson(responseJSONString, GetBookmarkResponse.class);
		if(bookmarkResponse.getCode().equals("200") && bookmarkResponse.getStatus().equals("OK")) {
			bookmarks = bookmarkResponse.bookmarks;
		}
		
		return bookmarks;
	}
	
	/**
	 * Get a specific Bookmark from a Word document.
	 * @param fileName Name of the MS Word document on cloud
	 * @param bookmarkName Name of the Bookmark
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return An object that contains Bookmark details
	*/ 
	public static BookmarkEnvelop getASpecificBookmarkFromAWord(String fileName, String bookmarkName) throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		BookmarkEnvelop bookmark = null;
		
		if(fileName == null || fileName.length() <= 3) {
			throw new IllegalArgumentException("File name cannot be null or empty");
		}
		
		if(bookmarkName == null || bookmarkName.length() == 0) {
			throw new IllegalArgumentException("BookmarkName is not specified");
		}
		
		//build URL
      	String strURL = WORD_URI + Uri.encode(fileName) + "/bookmarks/" + Uri.encode(bookmarkName);
        //sign URL
        String signedURL = Utils.sign(strURL);
        
        InputStream responseStream = Utils.processCommand(signedURL, "GET");
        String responseJSONString = Utils.streamToString(responseStream);
        
        //Parsing JSON
      	Gson gson = new Gson();
      	GetASpecificBookmarkResponse specificBookmarkResponse = gson.fromJson(responseJSONString, GetASpecificBookmarkResponse.class);
		if(specificBookmarkResponse.getCode().equals("200") && specificBookmarkResponse.getStatus().equals("OK")) {
			bookmark = specificBookmarkResponse.bookmark;
		}
		
		return bookmark;
	}
	
	/**
	 * Update Bookmark text of a Word document.
	 * @param fileName Name of the MS Word document on cloud
	 * @param bookmarkName Name of the Bookmark
	 * @param bookmarkText New value of Bookmark Text
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return An object that contains updated Bookmark details
	*/ 
	public static BookmarkEnvelop updateBookmarkTextOfAWordDocument(String fileName, String bookmarkName, String bookmarkText) throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		
		BookmarkEnvelop bookmark = null;
		
		if(fileName == null || fileName.length() <= 3) {
			throw new IllegalArgumentException("File name cannot be null or empty");
		}
		
		if(bookmarkName == null || bookmarkName.length() == 0) {
			throw new IllegalArgumentException("BookmarkName cannot be null or empty");
		}
		
		if(bookmarkText == null || bookmarkText.length() == 0) {
			throw new IllegalArgumentException("BookmarkText cannot be null or empty");
		}
		
		BookmarkDataModel bookmarkModelObj = new BookmarkDataModel();
		bookmarkModelObj.text = bookmarkText;
		
		GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        String requestJSONString = gson.toJson(bookmarkModelObj, BookmarkDataModel.class);
        
		//build URL
      	String strURL = WORD_URI + Uri.encode(fileName) + "/bookmarks/" + Uri.encode(bookmarkName);
        //sign URL
        String signedURL = Utils.sign(strURL);
        
        InputStream responseStream = Utils.processCommand(signedURL, "POST", requestJSONString);
        String responseJSONString = Utils.streamToString(responseStream);
        
        //Parsing JSON
      	GetASpecificBookmarkResponse updateBookmarkTextResponse = gson.fromJson(responseJSONString, GetASpecificBookmarkResponse.class);
		if(updateBookmarkTextResponse.getCode().equals("200") && updateBookmarkTextResponse.getStatus().equals("OK")) {
			bookmark = updateBookmarkTextResponse.bookmark;
		}
		
		return bookmark;
	}
	
}
