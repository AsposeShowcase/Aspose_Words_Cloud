package com.aspose.cloud.sdk.pdf.api;

import android.net.Uri;

import com.aspose.cloud.sdk.common.AsposeApp;
import com.aspose.cloud.sdk.common.Utils;
import com.aspose.cloud.sdk.pdf.model.PdfLinksResponse;
import com.aspose.cloud.sdk.pdf.model.PdfLinksResponse.PdfLinksResult;
import com.aspose.cloud.sdk.pdf.model.LinkDetails;
import com.aspose.cloud.sdk.pdf.model.PdfLinkResponse;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * Links --- Using this class you can get all links, a specific link and links count. 
 * @author   M. Sohail Ismail
 */
public class Links {
	
	private static final String PDF_URI = AsposeApp.BASE_PRODUCT_URI + "/pdf/";
	
	/**
	 * Get all links from a PDF page
	 * @param fileName Name of the file on cloud
	 * @param pageIndex Index of page starting from 1
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return An object that contains details of all links  
	*/ 
	public static PdfLinksResult getAllLinksFromAPDFPage(String fileName, int pageIndex) throws InvalidKeyException, NoSuchAlgorithmException, IOException {

		PdfLinksResult links = null;
		
		if(fileName == null || fileName.length() == 0) {
			throw new IllegalArgumentException("File name cannot be null or empty");
		}
		
		//build URL
		String strURL = PDF_URI + Uri.encode(fileName) + "/pages/" + pageIndex + "/links";
		
		//sign URL
		String signedURL = Utils.sign(strURL);
		InputStream responseStream = Utils.processCommand(signedURL, "GET");
		String responseJSONString = Utils.streamToString(responseStream);
		
		//Parsing JSON
		Gson gson = new Gson();
		PdfLinksResponse pdfLinksResponse = gson.fromJson(responseJSONString, PdfLinksResponse.class);
		if(pdfLinksResponse.getCode().equals("200") && pdfLinksResponse.getStatus().equals("OK")) {
			links = pdfLinksResponse.links;
		}
		
		return links;
	}
	
	/**
	 * Get links count from a PDF page
	 * @param fileName Name of the file on cloud
	 * @param pageIndex Index of page starting from 1
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return Links count  
	*/ 
	public static int getLinkCountFromAPDFPage(String fileName, int pageIndex) throws InvalidKeyException, NoSuchAlgorithmException, IOException {

		int linkCount = -1;
		
		if(fileName == null || fileName.length() == 0) {
			throw new IllegalArgumentException("File name cannot be null or empty");
		}
		
		//build URL
		String strURL = PDF_URI + Uri.encode(fileName) + "/pages/" + pageIndex + "/links";
		
		//sign URL
		String signedURL = Utils.sign(strURL);
		InputStream responseStream = Utils.processCommand(signedURL, "GET");
		String responseJSONString = Utils.streamToString(responseStream);
		
		//Parsing JSON
		Gson gson = new Gson();
		PdfLinksResponse pdfLinksResponse = gson.fromJson(responseJSONString, PdfLinksResponse.class);
		if(pdfLinksResponse.getCode().equals("200") && pdfLinksResponse.getStatus().equals("OK")) {
			linkCount = pdfLinksResponse.links.List.size();
		}
		
		return linkCount;
	}
	
	/**
	 * Get a specific link from a PDF page
	 * @param fileName Name of the file on cloud
	 * @param pageIndex Index of page starting from 1
	 * @param linkIndex Index of link starting from 1
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return Requested link details   
	*/ 
	public static LinkDetails getASpecificLinkFromAPDFPage(String fileName, int pageIndex, int linkIndex) throws InvalidKeyException, NoSuchAlgorithmException, IOException {

		LinkDetails link = null;
		
		if(fileName == null || fileName.length() == 0) {
			throw new IllegalArgumentException("File name cannot be null or empty");
		}
		
		//build URL
		String strURL = PDF_URI + Uri.encode(fileName) + "/pages/" + pageIndex + "/links/" + linkIndex;
		
		//sign URL
		String signedURL = Utils.sign(strURL);
		InputStream responseStream = Utils.processCommand(signedURL, "GET");
		String responseJSONString = Utils.streamToString(responseStream);
		
		//Parsing JSON
		Gson gson = new Gson();
		PdfLinkResponse pdfLinkResponse = gson.fromJson(responseJSONString, PdfLinkResponse.class);
		if(pdfLinkResponse.getCode().equals("200") && pdfLinkResponse.getStatus().equals("OK")) {
			link = pdfLinkResponse.link;
		}
		
		return link;
	}
}
