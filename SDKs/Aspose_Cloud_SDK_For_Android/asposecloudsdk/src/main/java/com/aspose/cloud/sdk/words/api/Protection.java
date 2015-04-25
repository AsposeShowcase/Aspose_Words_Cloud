package com.aspose.cloud.sdk.words.api;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import android.net.Uri;

import com.aspose.cloud.sdk.common.AsposeApp;
import com.aspose.cloud.sdk.common.BaseResponse;
import com.aspose.cloud.sdk.common.Utils;
import com.aspose.cloud.sdk.words.model.GetProtectionOfTheWordDocumentResponse;
import com.aspose.cloud.sdk.words.model.ProtectionRequestModel;
import com.aspose.cloud.sdk.words.model.ProtectionTypeEnum;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Protection --- Using this class you can get current protection of the word document, can protect/unprotect a word document and 
 * modify protection of the word document.
 * @author   M. Sohail Ismail
 */
public class Protection {
	
	private static final String WORD_URI = AsposeApp.BASE_PRODUCT_URI + "/words/";
	
	/**
	 * Get the Current Protection of the Word
	 * @param fileName Name of the MS Word document on cloud
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return An object that contains current protection type of the document
	*/
	public static GetProtectionOfTheWordDocumentResponse getTheCurrentProtectionOfTheWordDocument(String fileName) throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		
		GetProtectionOfTheWordDocumentResponse documentProtectionResponse = null;
		
		if(fileName == null || fileName.length() <= 3) {
			throw new IllegalArgumentException("File name cannot be null or empty");
		}
		
		//build URL
      	String strURL = WORD_URI + Uri.encode(fileName) + "/protection";
        //sign URL
        String signedURL = Utils.sign(strURL);
      
        InputStream responseStream = Utils.processCommand(signedURL, "GET");
        String responseJSONString = Utils.streamToString(responseStream);
        
        //Parsing JSON
      	Gson gson = new Gson();
        documentProtectionResponse = gson.fromJson(responseJSONString, GetProtectionOfTheWordDocumentResponse.class);
		if(documentProtectionResponse.getCode().equals("200") && documentProtectionResponse.getStatus().equals("OK")) {
			return documentProtectionResponse;
		} else {
			return null;
		}
	}
	
	/**
	 * Protect a Word Document 
	 * @param fileName Name of the MS Word document on cloud
	 * @param protectionType Document protection type. Valid values are AllowOnlyComments, AllowOnlyFormFields, AllowOnlyRevisions, ReadOnly, NoProtection.
	 * @param password Current document protection password
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return Boolean variable that indicates whether document protected successfully
	*/
	public static boolean protectAWordDocument(String fileName, ProtectionTypeEnum protectionType, String password) throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		
		boolean isDocumentProtectedSuccessfully = false;
		
		if(fileName == null || fileName.length() <= 3) {
			throw new IllegalArgumentException("File name cannot be null or empty");
		}
		
		if(password == null) {
			throw new IllegalArgumentException("Password cannot be null");
		}
		
		ProtectionRequestModel protectionRequest = new ProtectionRequestModel();
        protectionRequest.protectionType = protectionType;
        protectionRequest.password = password;
        
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        String requestJSONString = gson.toJson(protectionRequest, ProtectionRequestModel.class);
        
		//build URL
      	String strURL = WORD_URI + Uri.encode(fileName) + "/protection";
        //sign URL
        String signedURL = Utils.sign(strURL);
      
        InputStream responseStream = Utils.processCommand(signedURL, "PUT", requestJSONString);
        String responseJSONString = Utils.streamToString(responseStream);
        
        BaseResponse baseResponse = gson.fromJson(responseJSONString, BaseResponse.class);
		if(baseResponse.getCode().equals("200") && baseResponse.getStatus().equals("OK")) {
			isDocumentProtectedSuccessfully = true;
		}
		
		return isDocumentProtectedSuccessfully;
	}
	
	/**
	 * Unprotect a Word Document
	 * @param fileName Name of the MS Word document on cloud
	 * @param password Current document protection password
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return Boolean variable that indicates whether document unprotected successfully
	*/
	public static boolean unprotectAWordDocument(String fileName, String password) throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		boolean isDocumentUnProtectedSuccessfully = false;
		
		if(fileName == null || fileName.length() <= 3) {
			throw new IllegalArgumentException("File name cannot be null or empty");
		}
		
		if(password == null) {
			throw new IllegalArgumentException("Password cannot be null");
		}
		
		ProtectionRequestModel protectionRequest = new ProtectionRequestModel();
        protectionRequest.password = password;
        
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        String requestJSONString = gson.toJson(protectionRequest, ProtectionRequestModel.class);
        
		//build URL
      	String strURL = WORD_URI + Uri.encode(fileName) + "/protection";
        //sign URL
        String signedURL = Utils.sign(strURL);
      
        InputStream responseStream = Utils.processDeleteCommandWithBody(signedURL, requestJSONString);
        String responseJSONString = Utils.streamToString(responseStream);
        
        BaseResponse baseResponse = gson.fromJson(responseJSONString, BaseResponse.class);
		if(baseResponse.getCode().equals("200") && baseResponse.getStatus().equals("OK")) {
			isDocumentUnProtectedSuccessfully = true;
		}
		
		return isDocumentUnProtectedSuccessfully;
	}
	
	/**
	 * Modify Protection of the Word Document
	 * @param fileName Name of the MS Word document on cloud
	 * @param protectionType Document protection type. Valid values are AllowOnlyComments, AllowOnlyFormFields, AllowOnlyRevisions, ReadOnly, NoProtection.
	 * @param oldPassword Current document protection password
	 * @param newPassword New document protection password
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return An object that contains updated protection status of the document
	*/
	public static GetProtectionOfTheWordDocumentResponse modifyProtectionOfTheWordDocument(String fileName, ProtectionTypeEnum protectionType, 
			String oldPassword, String newPassword) throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		
		GetProtectionOfTheWordDocumentResponse modifyDocumentProtectionResponse = null;
		
		if(fileName == null || fileName.length() <= 3) {
			throw new IllegalArgumentException("File name cannot be null or empty");
		}
		
		if(oldPassword == null) {
			throw new IllegalArgumentException("Old password cannot be null");
		}
		
		if(newPassword == null) {
			throw new IllegalArgumentException("New password cannot be null");
		}
		
		ProtectionRequestModel protectionRequest = new ProtectionRequestModel();
        protectionRequest.protectionType = protectionType;
        protectionRequest.password = oldPassword;
        protectionRequest.newPassword = newPassword;
        
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        String requestJSONString = gson.toJson(protectionRequest, ProtectionRequestModel.class);
		
        //build URL
      	String strURL = WORD_URI + Uri.encode(fileName) + "/protection";
        //sign URL
        String signedURL = Utils.sign(strURL);
      
        InputStream responseStream = Utils.processCommand(signedURL, "POST", requestJSONString);
        String responseJSONString = Utils.streamToString(responseStream);
        
        //Parsing JSON
      	modifyDocumentProtectionResponse = gson.fromJson(responseJSONString, GetProtectionOfTheWordDocumentResponse.class);
		if(modifyDocumentProtectionResponse.getCode().equals("200") && modifyDocumentProtectionResponse.getStatus().equals("OK")) {
			return modifyDocumentProtectionResponse;
		} else {
			return null;
		}
	}
}
