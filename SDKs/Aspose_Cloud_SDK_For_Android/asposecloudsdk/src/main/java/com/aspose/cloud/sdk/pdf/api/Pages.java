package com.aspose.cloud.sdk.pdf.api;

import android.net.Uri;

import com.aspose.cloud.sdk.common.AsposeApp;
import com.aspose.cloud.sdk.common.BaseResponse;
import com.aspose.cloud.sdk.common.Utils;
import com.aspose.cloud.sdk.pdf.model.PagesResponse;
import com.aspose.cloud.sdk.pdf.model.ValidImageFormatsEnum;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * Pages --- Using this class you can convert a PDF page to image with default or specified size, 
 * add a new page in PDF, delete page from PDF, move PDF page to new location in a PDF file and get PDF document page count
 * @author   M. Sohail Ismail
 */
public class Pages {
	
	private static final String PDF_URI = AsposeApp.BASE_PRODUCT_URI + "/pdf/";
	
	/**
	 * Convert a PDF page to image with default size
	 * @param fileName Name of the file stored on cloud
	 * @param pageIndex Index of page starting from 1
	 * @param designatedFormat A format to which PDF document will be converted
	 * @param outputFilePath Output file will save on device with this name
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return Path to converted file stored on device
	*/ 
	public static String convertAPDFPageToImageWithDefaultSize(String fileName, int pageIndex, ValidImageFormatsEnum designatedFormat, String outputFilePath) throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		
		String localFilePath = null;
		
		if(fileName == null || fileName.length() == 0) {
			throw new IllegalArgumentException("File name cannot be null or empty");
		}
		
		if(designatedFormat == null) {
			throw new IllegalArgumentException("Designated format cannot be null");
		}
		//build URL
		String strURL = PDF_URI + Uri.encode(fileName) + "/pages/" + pageIndex + "?format=" + designatedFormat;
		//sign URL
		String signedURL = Utils.sign(strURL);
		InputStream responseStream = Utils.processCommand(signedURL, "GET");
		
		//Save file on Disk
		localFilePath = Utils.saveStreamToFile(responseStream, outputFilePath);
		return localFilePath;
	}
	
	/**
	 * Convert a PDF page to image with specified size
	 * @param fileName Name of the file stored on cloud
	 * @param pageIndex Index of page starting from 1
	 * @param designatedFormat A format to which PDF document will be converted
	 * @param width Width of resulting image in pixels
	 * @param height Height of resulting image in pixels
	 * @param outputFilePath Output file will save on device with this name
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return Path to converted file stored on device
	*/ 
	public static String convertAPDFPageToImageWithSpecifiedSize(String fileName, int pageIndex, ValidImageFormatsEnum designatedFormat, int width, int height, String outputFilePath) throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		
		String localFilePath = null;
		
		if(fileName == null || fileName.length() == 0) {
			throw new IllegalArgumentException("File name cannot be null or empty");
		}
		
		if(designatedFormat == null) {
			throw new IllegalArgumentException("Designated format cannot be null");
		}
		
		//build URL
		String strURL = PDF_URI + Uri.encode(fileName) + "/pages/" + pageIndex + "?format=" + designatedFormat + "&width=" + width + "&height=" + height;
		//sign URL
		String signedURL = Utils.sign(strURL);
		InputStream responseStream = Utils.processCommand(signedURL, "GET");
		
		//Save file on Disk
		localFilePath = Utils.saveStreamToFile(responseStream, outputFilePath);
		return localFilePath;
	}
	
	/**
	 * Add a new page in PDF
	 * @param fileName Name of the file stored on cloud
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return Boolean variable indicates whether new page added successfully to PDF
	*/ 
	public static boolean addANewPageInPDF(String fileName) throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		
		boolean isNewPageAddedSuccessfully = false;
		
		if(fileName == null || fileName.length() == 0) {
			throw new IllegalArgumentException("File name cannot be null or empty");
		}
		
		//build URL
		String strURL = PDF_URI + Uri.encode(fileName) + "/pages/";
		//sign URL
		String signedURL = Utils.sign(strURL);
		
		InputStream responseStream = Utils.processCommand(signedURL, "PUT");
		String responseJSONString = Utils.streamToString(responseStream);
		
        //Parsing JSON
		Gson gson = new Gson();
		BaseResponse baseResponse = gson.fromJson(responseJSONString, BaseResponse.class);
		if(baseResponse.getCode().equals("200") && baseResponse.getStatus().equals("OK")) {
			isNewPageAddedSuccessfully = true;
		}
		
		return isNewPageAddedSuccessfully;
	}
	
	/**
	 * Delete page from PDF
	 * @param fileName Name of the file stored on cloud
	 * @param pageIndex Index of page starting from 1
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return Boolean variable indicates whether page deleted successfully from PDF
	*/ 
	public static boolean deletePageFromPDF(String fileName, int pageIndex) throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		
		boolean isPageDeletedSuccessfully = false;
		
		if(fileName == null || fileName.length() == 0) {
			throw new IllegalArgumentException("File name cannot be null or empty");
		}
		
		//build URL
		String strURL = PDF_URI + Uri.encode(fileName) + "/pages/" + pageIndex;
		//sign URL
		String signedURL = Utils.sign(strURL);
		
		InputStream responseStream = Utils.processCommand(signedURL, "DELETE");
		String responseJSONString = Utils.streamToString(responseStream);
		
        //Parsing JSON
		Gson gson = new Gson();
		BaseResponse baseResponse = gson.fromJson(responseJSONString, BaseResponse.class);
		if(baseResponse.getCode().equals("200") && baseResponse.getStatus().equals("OK")) {
			isPageDeletedSuccessfully = true;
		}
		
		return isPageDeletedSuccessfully;
	}
	
	/**
	 * Move PDF page to new location in a PDF file
	 * @param fileName Name of the file stored on cloud
	 * @param pageIndex Old position of page
	 * @param newPageIndex New location of page
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return Boolean variable indicates whether page moved to new location in a PDF file
	*/ 
	public static boolean movePDFPageToNewLocationInAPDFFile(String fileName, int pageIndex, int newPageIndex) throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		
		boolean isPageMovedToNewLocationSuccessfully = false;
		
		if(fileName == null || fileName.length() == 0) {
			throw new IllegalArgumentException("File name cannot be null or empty");
		}
		
		//build URL
		String strURL = PDF_URI + Uri.encode(fileName) + "/pages/" + pageIndex + "/movePage?newIndex=" + newPageIndex;
		//sign URL
		String signedURL = Utils.sign(strURL);
		
		InputStream responseStream = Utils.processCommand(signedURL, "POST");
		String responseJSONString = Utils.streamToString(responseStream);
		
        //Parsing JSON
		Gson gson = new Gson();
		BaseResponse baseResponse = gson.fromJson(responseJSONString, BaseResponse.class);
		if(baseResponse.getCode().equals("200") && baseResponse.getStatus().equals("OK")) {
			isPageMovedToNewLocationSuccessfully = true;
		}
		
		return isPageMovedToNewLocationSuccessfully;
	}
	
	/**
	 * Get PDF document page count
	 * @param fileName Name of the file stored on cloud
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return Page count
	*/ 
	public static int getPDFDocumentPageCount(String fileName) throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		
		int pageCount = -1;
		
		if(fileName == null || fileName.length() == 0) {
			throw new IllegalArgumentException("File name cannot be null or empty");
		}
		
		//build URL
		String strURL = PDF_URI + Uri.encode(fileName) + "/pages/";
		//sign URL
		String signedURL = Utils.sign(strURL);
		
		InputStream responseStream = Utils.processCommand(signedURL, "GET");
		String responseJSONString = Utils.streamToString(responseStream);
		
        //Parsing JSON
		Gson gson = new Gson();
		PagesResponse pagesResponse = gson.fromJson(responseJSONString, PagesResponse.class);
		if(pagesResponse.getCode().equals("200") && pagesResponse.getStatus().equals("OK")) {
			pageCount = pagesResponse.pages.List.size();
		}
		
		return pageCount;
	}
	
}
