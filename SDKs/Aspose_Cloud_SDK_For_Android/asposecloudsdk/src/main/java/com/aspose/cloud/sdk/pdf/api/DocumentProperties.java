package com.aspose.cloud.sdk.pdf.api;

import com.aspose.cloud.sdk.common.AsposeApp;
import com.aspose.cloud.sdk.common.BaseResponse;
import com.aspose.cloud.sdk.common.Utils;
import com.aspose.cloud.sdk.pdf.model.DocumentPropertiesResponse;
import com.aspose.cloud.sdk.pdf.model.DocumentPropertiesResponse.DocumentPropertiesResult;
import com.aspose.cloud.sdk.pdf.model.DocumentPropertyResponse;
import com.aspose.cloud.sdk.pdf.model.DocumentPropertyModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class DocumentProperties {
	
	private static final String PDF_URI = AsposeApp.BASE_PRODUCT_URI + "/pdf/";
	
	/**
	 * Get all document properties from a PDF
	 * @param fileName Name of the file on cloud
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return An object that contain details of all document properties
	*/ 
	public static DocumentPropertiesResult getAllDocumentPropertiesFromAPDF(String fileName) throws InvalidKeyException, NoSuchAlgorithmException, IOException {

		DocumentPropertiesResult documentProperties = null;
		
		if(fileName == null || fileName.length() == 0) {
			throw new IllegalArgumentException("File name cannot be null or empty");
		}
		
		//build URL
		String strURL = PDF_URI + fileName + "/documentProperties";
		
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
	 * Get a particular property from a PDF page
	 * @param fileName Name of the file on cloud
	 * @param propertyName Name of property
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return Details of requested document property   
	*/ 
	public static DocumentPropertyModel getAParticularDocumentPropertyFromAPDF(String fileName, String propertyName) throws InvalidKeyException, NoSuchAlgorithmException, IOException {

		DocumentPropertyModel documentProperty = null;
		
		if(fileName == null || fileName.length() == 0) {
			throw new IllegalArgumentException("File name cannot be null or empty");
		}
		
		//build URL
		String strURL = PDF_URI + fileName + "/documentProperties/" + propertyName;
		
		//sign URL
		String signedURL = Utils.sign(strURL);
		InputStream responseStream = Utils.processCommand(signedURL, "GET");
		String responseJSONString = Utils.streamToString(responseStream);
		
		//Parsing JSON
		Gson gson = new Gson();
		DocumentPropertyResponse documentPropertyResponse = gson.fromJson(responseJSONString, DocumentPropertyResponse.class);
		if(documentPropertyResponse.getCode().equals("200") && documentPropertyResponse.getStatus().equals("OK")) {
			documentProperty = documentPropertyResponse.documentProperty;
		}
		
		return documentProperty;
	}
	
	/**
	 * Remove all document properties from a PDF
	 * @param fileName Name of the file on cloud
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return Boolean variable indicates whether all properties deleted successfully   
	*/ 
	public static boolean removeAllDocumentPropertiesFromAPDF(String fileName) throws InvalidKeyException, NoSuchAlgorithmException, IOException {

		boolean isDocumentPropertiesDeletedSuccessfully = false;
		
		if(fileName == null || fileName.length() == 0) {
			throw new IllegalArgumentException("File name cannot be null or empty");
		}
		
		//build URL
		String strURL = PDF_URI + fileName + "/documentProperties";
		
		//sign URL
		String signedURL = Utils.sign(strURL);
		InputStream responseStream = Utils.processCommand(signedURL, "DELETE");
		String responseJSONString = Utils.streamToString(responseStream);
		
		//Parsing JSON
		Gson gson = new Gson();
		BaseResponse baseResponse = gson.fromJson(responseJSONString, BaseResponse.class);
		if(baseResponse.getCode().equals("200") && baseResponse.getStatus().equals("OK")) {
			isDocumentPropertiesDeletedSuccessfully = true;
		}
		
		return isDocumentPropertiesDeletedSuccessfully;
	}
	
	/**
	 * Set a single document property in a PDF
	 * @param fileName Name of the file on cloud
	 * @param propertyName Name of property
	 * @param propertyValue Value of property
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return Updated document property details
	*/ 
	public static DocumentPropertyModel setASingleDocumentPropertyInAPDF(String fileName, String propertyName, String propertyValue) throws InvalidKeyException, NoSuchAlgorithmException, IOException {

		DocumentPropertyModel documentProperty = null;
		
		if(fileName == null || fileName.length() == 0) {
			throw new IllegalArgumentException("File name cannot be null or empty");
		}
		
		DocumentPropertyModel documentPropertyRequest = new DocumentPropertyModel();
		documentPropertyRequest.Value = propertyValue;
		
		GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        String requestJSONString = gson.toJson(documentPropertyRequest, DocumentPropertyModel.class);
		
		//build URL
		String strURL = PDF_URI + fileName + "/documentProperties/" + propertyName;
		
		//sign URL
		String signedURL = Utils.sign(strURL);
		InputStream responseStream = Utils.processCommand(signedURL, "PUT", requestJSONString);
		String responseJSONString = Utils.streamToString(responseStream);
		
		//Parsing JSON
		DocumentPropertyResponse documentPropertyResponse = gson.fromJson(responseJSONString, DocumentPropertyResponse.class);
		if(documentPropertyResponse.getCode().equals("200") && documentPropertyResponse.getStatus().equals("OK")) {
			documentProperty = documentPropertyResponse.documentProperty;
		}
		
		return documentProperty;
	}
}
