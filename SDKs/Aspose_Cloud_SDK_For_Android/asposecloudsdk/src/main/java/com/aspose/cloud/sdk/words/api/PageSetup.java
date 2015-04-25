package com.aspose.cloud.sdk.words.api;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import android.net.Uri;

import com.aspose.cloud.sdk.common.AsposeApp;
import com.aspose.cloud.sdk.common.Utils;
import com.aspose.cloud.sdk.words.model.PageSetupProperties;
import com.aspose.cloud.sdk.words.model.PageSetupResponse;
import com.google.gson.Gson;

/**
 * PageSetup --- Using this class you can get and update Page Setup information of a section in a Word document
 * @author   M. Sohail Ismail
 */
public class PageSetup {

	private static final String WORD_URI = AsposeApp.BASE_PRODUCT_URI + "/words/";
	
	/**
	 * Get Page Setup information of a section in a Word document
	 * @param fileName Name of the MS Word document on cloud
	 * @param sectionIndex Section index starting from 0
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return An object that page setup information
	*/ 
	public static PageSetupProperties extractPageSetupInformationOfASectionFromAWordDocument(String fileName, int sectionIndex) throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		PageSetupProperties pageSetupProperties = null;
		
		if(fileName == null || fileName.length() <= 3) {
			throw new IllegalArgumentException("File name cannot be null or empty");
		}
		
		//build URL
      	String strURL = WORD_URI + Uri.encode(fileName) + "/sections/" + sectionIndex + "/pageSetup";
        //sign URL
        String signedURL = Utils.sign(strURL);
        
        InputStream responseStream = Utils.processCommand(signedURL, "GET");
        String responseJSONString = Utils.streamToString(responseStream);
        
        //Parsing JSON
      	Gson gson = new Gson();
      	PageSetupResponse pageSetupResponse = gson.fromJson(responseJSONString, PageSetupResponse.class);
		if(pageSetupResponse.getCode().equals("200") && pageSetupResponse.getStatus().equals("OK")) {
			pageSetupProperties = pageSetupResponse.pageSetupProperties;
		}
		
		return pageSetupProperties;
	
	}
	
	/**
	 * Update page setup of a section in a Word document.
	 * @param fileName Name of the MS Word document on cloud
	 * @param sectionIndex Section index starting from 0
	 * @param pageSetupRequest Page setup properties to update
	 * @param contentType Can be either XML or JSON
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return An object that page setup information
	*/ 
	public static PageSetupProperties updatePageSetupOfASectionInAWordDocument(String fileName, int sectionIndex, String pageSetupRequest, String contentType) throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		PageSetupProperties pageSetupProperties = null;
		
		if(fileName == null || fileName.length() <= 3) {
			throw new IllegalArgumentException("File name cannot be null or empty");
		}
		
		//build URL
      	String strURL = WORD_URI + Uri.encode(fileName) + "/sections/" + sectionIndex + "/pageSetup";
        //sign URL
        String signedURL = Utils.sign(strURL);
        
        InputStream responseStream = Utils.processCommand(signedURL, "POST", pageSetupRequest, contentType);
        String responseJSONString = Utils.streamToString(responseStream);
        
        //Parsing JSON
        Gson gson = new Gson();
      	PageSetupResponse pageSetupResponse = gson.fromJson(responseJSONString, PageSetupResponse.class);
		if(pageSetupResponse.getCode().equals("200") && pageSetupResponse.getStatus().equals("OK")) {
			pageSetupProperties = pageSetupResponse.pageSetupProperties;
		}
		
		return pageSetupProperties;
	
	}
}
