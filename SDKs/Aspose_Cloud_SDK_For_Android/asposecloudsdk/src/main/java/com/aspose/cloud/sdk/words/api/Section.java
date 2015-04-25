package com.aspose.cloud.sdk.words.api;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import android.net.Uri;

import com.aspose.cloud.sdk.common.AsposeApp;
import com.aspose.cloud.sdk.common.BaseResponse;
import com.aspose.cloud.sdk.common.Utils;
import com.aspose.cloud.sdk.words.model.GetSectionListResponse;
import com.aspose.cloud.sdk.words.model.GetSectionListResponse.SectionList;
import com.aspose.cloud.sdk.words.model.GetSpecificSectionResponse;
import com.aspose.cloud.sdk.words.model.GetSpecificSectionResponse.SectionDetails;
import com.google.gson.Gson;

/**
 * Section --- Using this class you can get a list of sections from a word document or a specific section from a word document.
 * You can also remove all headers and footers of a Word document.
 * @author   M. Sohail Ismail
 */
public class Section {
	
	private static final String WORD_URI = AsposeApp.BASE_PRODUCT_URI + "/words/";
	
	/**
	 * Get a List of Sections from a Word Document
	 * @param fileName Name of the MS Word document on cloud
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return An object that contains an array of links where each link points to an individual section 
	*/
	public static SectionList getListOfSectionsFromAWordDocument(String fileName) throws IOException, InvalidKeyException, NoSuchAlgorithmException {
		
		SectionList sectionsList = null;
		
		if(fileName == null || fileName.length() <= 3) {
			throw new IllegalArgumentException("File name cannot be null or empty");
		}
		
		//build URL
      	String strURL = WORD_URI + Uri.encode(fileName) + "/sections";
        //sign URL
        String signedURL = Utils.sign(strURL);
        
        InputStream responseStream = Utils.processCommand(signedURL, "GET");
        String responseJSONString = Utils.streamToString(responseStream);
        
        //Parsing JSON
      	Gson gson = new Gson();
        GetSectionListResponse sectionListResponse = gson.fromJson(responseJSONString, GetSectionListResponse.class);
		if(sectionListResponse.getCode().equals("200") && sectionListResponse.getStatus().equals("OK")) {
			sectionsList = sectionListResponse.sections;
		}
		
		return sectionsList;
	}
	
	/**
	 * Get a specific Section from a Word Document
	 * @param fileName Name of the MS Word document on cloud
	 * @param sectionIndex Index of the section in a word document
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return An object that contains requested section details 
	*/
	public static SectionDetails getASpecificSectionFromAWordDocument(String fileName, int sectionIndex) throws IOException, InvalidKeyException, NoSuchAlgorithmException {
		
		SectionDetails section = null;
		
		if(fileName == null || fileName.length() <= 3) {
			throw new IllegalArgumentException("File name cannot be null or empty");
		}
		
		//build URL
      	String strURL = WORD_URI + Uri.encode(fileName) + "/sections/" + sectionIndex;
        //sign URL
        String signedURL = Utils.sign(strURL);
        
        InputStream responseStream = Utils.processCommand(signedURL, "GET");
        String responseJSONString = Utils.streamToString(responseStream);
        
        //Parsing JSON
      	Gson gson = new Gson();
      	GetSpecificSectionResponse specificSectionResponse = gson.fromJson(responseJSONString, GetSpecificSectionResponse.class);
		if(specificSectionResponse.getCode().equals("200") && specificSectionResponse.getStatus().equals("OK")) {
			section = specificSectionResponse.section;
		}
		
		return section;
	}
	
	/**
	 * Remove all headers and footers of a Word document
	 * @param fileName Name of the MS Word document on cloud
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return Boolean variable that indicates whether headers and footers deleted successfully
	*/
	public static boolean deleteAllHeadersAndFootersFromTheDocument(String fileName) throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		boolean isAllHeadersAndFootersDeletedSuccessfully = false;
		
		if(fileName == null || fileName.length() <= 3) {
			throw new IllegalArgumentException("File name cannot be null or empty");
		}
		
		//build URL
      	String strURL = WORD_URI + Uri.encode(fileName) + "/headersFooters";
        //sign URL
        String signedURL = Utils.sign(strURL);
        
        InputStream responseStream = Utils.processCommand(signedURL, "DELETE");
        String responseJSONString = Utils.streamToString(responseStream);
        
        //Parsing JSON
      	Gson gson = new Gson();
      	BaseResponse baseResponse = gson.fromJson(responseJSONString, BaseResponse.class);
		if(baseResponse.getCode().equals("200") && baseResponse.getStatus().equals("OK")) {
			isAllHeadersAndFootersDeletedSuccessfully = true;
		}
		
		return isAllHeadersAndFootersDeletedSuccessfully;
	}
}
