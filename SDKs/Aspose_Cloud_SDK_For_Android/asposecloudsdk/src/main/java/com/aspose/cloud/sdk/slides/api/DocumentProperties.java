package com.aspose.cloud.sdk.slides.api;

import com.aspose.cloud.sdk.common.AsposeApp;
import com.aspose.cloud.sdk.common.Utils;
import com.aspose.cloud.sdk.slides.model.DocumentPropertiesResponse;
import com.aspose.cloud.sdk.slides.model.DocumentPropertiesResponse.DocumentPropertiesResult;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * DocumentProperties --- Using this class you can get, set and remove document properties
 * @author   M. Sohail Ismail
 */
public class DocumentProperties {
	
	private static final String SLIDES_URI = AsposeApp.BASE_PRODUCT_URI + "/slides/";
	
	/**
	 * Get properties of a PowerPoint document
	 * @param fileName Name of the file stored on cloud
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return An object that contains document properties
	*/ 
	public static DocumentPropertiesResult getDocumentProperties(String fileName) throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		
		DocumentPropertiesResult documentProperties = null;
		
		if(fileName == null || fileName.length() == 0) {
			throw new IllegalArgumentException("File name cannot be null or empty");
		}
		
		//build URL
		String strURL = SLIDES_URI + fileName + "/documentProperties";
		//sign URL
		String signedURL = Utils.sign(strURL);
		InputStream responseStream = Utils.processCommand(signedURL, "GET");
		String responseJSONString = Utils.streamToString(responseStream);
		
		//Parsing JSON
		Gson gson = new Gson();
		DocumentPropertiesResponse documentPropertiesResponse = gson.fromJson(responseJSONString, DocumentPropertiesResponse.class);
		if(documentPropertiesResponse.getCode().equals("200") && documentPropertiesResponse.getStatus().equals("OK")) {
			documentProperties = documentPropertiesResponse.documentProperties;
		}
		
		return documentProperties;
	}
	
	/**
	 * Delete all document properties
	 * @param fileName Name of the file stored on cloud
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return An object that contains document properties
	*/ 
	public static DocumentPropertiesResult removeAllProperties(String fileName) throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		
		DocumentPropertiesResult documentProperties = null;
		
		if(fileName == null || fileName.length() == 0) {
			throw new IllegalArgumentException("File name cannot be null or empty");
		}
		
		//build URL
		String strURL = SLIDES_URI + fileName + "/documentProperties";
		//sign URL
		String signedURL = Utils.sign(strURL);
		InputStream responseStream = Utils.processCommand(signedURL, "DELETE");
		String responseJSONString = Utils.streamToString(responseStream);
		
		//Parsing JSON
		Gson gson = new Gson();
		DocumentPropertiesResponse documentPropertiesResponse = gson.fromJson(responseJSONString, DocumentPropertiesResponse.class);
		if(documentPropertiesResponse.getCode().equals("200") && documentPropertiesResponse.getStatus().equals("OK")) {
			documentProperties = documentPropertiesResponse.documentProperties;
		}
		
		return documentProperties;
	}
}
