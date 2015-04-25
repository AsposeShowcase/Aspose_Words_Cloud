package com.aspose.cloud.sdk.pdf.api;

import android.net.Uri;

import com.aspose.cloud.sdk.common.AsposeApp;
import com.aspose.cloud.sdk.common.Utils;
import com.aspose.cloud.sdk.pdf.model.FormFieldRequest;
import com.aspose.cloud.sdk.pdf.model.FormFieldResponse;
import com.aspose.cloud.sdk.pdf.model.FormFieldResponse.FieldResult;
import com.aspose.cloud.sdk.pdf.model.FormFieldsResponse;
import com.aspose.cloud.sdk.pdf.model.FormFieldsResponse.FieldsResult;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * FormFields --- Using this class you can get all or a particular form field from a PDF document, 
 * get form field count from a PDF document and update a form field in a PDF document
 * @author   M. Sohail Ismail
 */
public class FormFields {
	
	private static final String PDF_URI = AsposeApp.BASE_PRODUCT_URI + "/pdf/";
	
	/**
	 * Get all form fields from the PDF document
	 * @param fileName Name of the file stored on cloud
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return An object that contains all form fields details
	*/ 
	public static FieldsResult getAllFormFieldsFromThePDFDocument(String fileName) throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		
		FieldsResult fields = null;
		
		if(fileName == null || fileName.length() == 0) {
			throw new IllegalArgumentException("File name cannot be null or empty");
		}
		
		//build URL
		String strURL = PDF_URI + Uri.encode(fileName) + "/fields";
		//sign URL
		String signedURL = Utils.sign(strURL);
		InputStream responseStream = Utils.processCommand(signedURL, "GET");
		String responseJSONString = Utils.streamToString(responseStream);
		
        //Parsing JSON
		Gson gson = new Gson();
		FormFieldsResponse formFieldsResponse = gson.fromJson(responseJSONString, FormFieldsResponse.class);
		if(formFieldsResponse.getCode().equals("200") && formFieldsResponse.getStatus().equals("OK")) {
			fields = formFieldsResponse.fields;
		}
		
		return fields;
	}
	
	/**
	 * Get a particular form field from the PDF document
	 * @param fileName Name of the file stored on cloud
	 * @param fieldName The name of the field
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return An object that contains requested form field details
	*/ 
	public static FieldResult getAParticularFormFieldFromThePDFDocument(String fileName, String fieldName) throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		
		FieldResult field = null;
		
		if(fileName == null || fileName.length() == 0) {
			throw new IllegalArgumentException("File name cannot be null or empty");
		}
		
		if(fieldName == null || fieldName.length() == 0) {
			throw new IllegalArgumentException("Field name cannot be null or empty");
		}
		
		//build URL
		String strURL = PDF_URI + Uri.encode(fileName) + "/fields/" + Uri.encode(fieldName);
		//sign URL
		String signedURL = Utils.sign(strURL);
		InputStream responseStream = Utils.processCommand(signedURL, "GET");
		String responseJSONString = Utils.streamToString(responseStream);
		
        //Parsing JSON
		Gson gson = new Gson();
		FormFieldResponse formFieldResponse = gson.fromJson(responseJSONString, FormFieldResponse.class);
		if(formFieldResponse.getCode().equals("200") && formFieldResponse.getStatus().equals("OK")) {
			field = formFieldResponse.field;
		}
		
		return field;
	}
	
	/**
	 * Get form field count from a PDF document
	 * @param fileName Name of the file stored on cloud
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return Number of form fields
	*/ 
	public static int getFormFieldCountFromAPDFDocument(String fileName) throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		
		int formFieldCount = -1;
		
		if(fileName == null || fileName.length() == 0) {
			throw new IllegalArgumentException("File name cannot be null or empty");
		}
		
		//build URL
		String strURL = PDF_URI + Uri.encode(fileName) + "/fields";
		//sign URL
		String signedURL = Utils.sign(strURL);
		InputStream responseStream = Utils.processCommand(signedURL, "GET");
		String responseJSONString = Utils.streamToString(responseStream);
		
        //Parsing JSON
		Gson gson = new Gson();
		FormFieldsResponse formFieldsResponse = gson.fromJson(responseJSONString, FormFieldsResponse.class);
		if(formFieldsResponse.getCode().equals("200") && formFieldsResponse.getStatus().equals("OK")) {
			formFieldCount = formFieldsResponse.fields.List.size();
		}
		
		return formFieldCount;
	}
	
	/**
	 * Update a form field in a PDF document
	 * @param fileName Name of the file stored on cloud
	 * @param fieldName The name of the field
	 * @param formFieldRequest An object that contains form field new values
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return An object that contains updated form field details
	*/ 
	public static FieldResult updateAFormFieldInAPDFDocument(String fileName, String fieldName, FormFieldRequest formFieldRequest) throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		
		FieldResult field = null;
		
		if(fileName == null || fileName.length() == 0) {
			throw new IllegalArgumentException("File name cannot be null or empty");
		}
		
		if(fieldName == null || fieldName.length() == 0) {
			throw new IllegalArgumentException("Field name cannot be null or empty");
		}
		
		if(formFieldRequest == null) {
			throw new IllegalArgumentException("Form field request cannot be null");
		}
		
		GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        String requestJSONString = gson.toJson(formFieldRequest, FormFieldRequest.class);
        
		//build URL
		String strURL = PDF_URI + Uri.encode(fileName) + "/fields/" + Uri.encode(fieldName);
		//sign URL
		String signedURL = Utils.sign(strURL);
		InputStream responseStream = Utils.processCommand(signedURL, "PUT", requestJSONString);
		String responseJSONString = Utils.streamToString(responseStream);
		
        //Parsing JSON
		FormFieldResponse formFieldResponse = gson.fromJson(responseJSONString, FormFieldResponse.class);
		if(formFieldResponse.getCode().equals("200") && formFieldResponse.getStatus().equals("OK")) {
			field = formFieldResponse.field;
		}
		
		return field;
	}
}
