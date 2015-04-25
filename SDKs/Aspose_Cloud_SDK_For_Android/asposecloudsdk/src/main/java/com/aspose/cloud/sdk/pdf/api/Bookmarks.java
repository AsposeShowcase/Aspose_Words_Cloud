package com.aspose.cloud.sdk.pdf.api;

import android.net.Uri;

import com.aspose.cloud.sdk.common.AsposeApp;
import com.aspose.cloud.sdk.common.Utils;
import com.aspose.cloud.sdk.pdf.model.BookmarkDetails;
import com.aspose.cloud.sdk.pdf.model.BookmarkResponse;
import com.aspose.cloud.sdk.pdf.model.BookmarksResponse;
import com.aspose.cloud.sdk.pdf.model.BookmarksResponse.BookmarksResult;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * Bookmarks --- Using this class you can get all bookmarks, a specific bookmark and bookmarks count. 
 * @author   M. Sohail Ismail
 */
public class Bookmarks {
	
private static final String PDF_URI = AsposeApp.BASE_PRODUCT_URI + "/pdf/";
	
	/**
	 * Get all bookmarks from a PDF
	 * @param fileName Name of the file on cloud
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return An object that contains all bookmarks  
	*/ 
	public static BookmarksResult getAllBookmarksFromAPDF(String fileName) throws InvalidKeyException, NoSuchAlgorithmException, IOException {

		BookmarksResult bookmarks = null;
		
		if(fileName == null || fileName.length() == 0) {
			throw new IllegalArgumentException("File name cannot be null or empty");
		}
		
		//build URL
		String strURL = PDF_URI + Uri.encode(fileName) + "/bookmarks";
		
		//sign URL
		String signedURL = Utils.sign(strURL);
		InputStream responseStream = Utils.processCommand(signedURL, "GET");
		String responseJSONString = Utils.streamToString(responseStream);
		
		//Parsing JSON
		Gson gson = new Gson();
		BookmarksResponse bookmarksResponse = gson.fromJson(responseJSONString, BookmarksResponse.class);
		if(bookmarksResponse.getCode().equals("200") && bookmarksResponse.getStatus().equals("OK")) {
			bookmarks = bookmarksResponse.bookmarks;
		}
		
		return bookmarks;
	}
	
	/**
	 * Get bookmarks count from a PDF
	 * @param fileName Name of the file on cloud
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return Bookmarks count  
	*/ 
	public static int getBookmarkCountFromAPDF(String fileName) throws InvalidKeyException, NoSuchAlgorithmException, IOException {

		int bookmarkCount = -1;
		
		if(fileName == null || fileName.length() == 0) {
			throw new IllegalArgumentException("File name cannot be null or empty");
		}
		
		//build URL
		String strURL = PDF_URI + Uri.encode(fileName) + "/bookmarks";
		
		//sign URL
		String signedURL = Utils.sign(strURL);
		InputStream responseStream = Utils.processCommand(signedURL, "GET");
		String responseJSONString = Utils.streamToString(responseStream);
		
		//Parsing JSON
		Gson gson = new Gson();
		BookmarksResponse bookmarksResponse = gson.fromJson(responseJSONString, BookmarksResponse.class);
		if(bookmarksResponse.getCode().equals("200") && bookmarksResponse.getStatus().equals("OK")) {
			bookmarkCount = bookmarksResponse.bookmarks.List.size();
		}
		
		return bookmarkCount;
	}
	
	/**
	 * Get a specific bookmark from a PDF
	 * @param fileName Name of the file on cloud
	 * @param bookmarkIndex Index of bookmark starting from 1
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return Requested bookmark details  
	*/ 
	public static BookmarkDetails getASpecificBookmarkFromAPDF(String fileName, int bookmarkIndex) throws InvalidKeyException, NoSuchAlgorithmException, IOException {

		BookmarkDetails bookmark = null;
		
		if(fileName == null || fileName.length() == 0) {
			throw new IllegalArgumentException("File name cannot be null or empty");
		}
		
		//build URL
		String strURL = PDF_URI + Uri.encode(fileName) + "/bookmarks/" + bookmarkIndex;
		
		//sign URL
		String signedURL = Utils.sign(strURL);
		InputStream responseStream = Utils.processCommand(signedURL, "GET");
		String responseJSONString = Utils.streamToString(responseStream);
		
		//Parsing JSON
		Gson gson = new Gson();
		BookmarkResponse bookmarkResponse = gson.fromJson(responseJSONString, BookmarkResponse.class);
		if(bookmarkResponse.getCode().equals("200") && bookmarkResponse.getStatus().equals("OK")) {
			bookmark = bookmarkResponse.bookmark;
		}
		
		return bookmark;
	}
}
