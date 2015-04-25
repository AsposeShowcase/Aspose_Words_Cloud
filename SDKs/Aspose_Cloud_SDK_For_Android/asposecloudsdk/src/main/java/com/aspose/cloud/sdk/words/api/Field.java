package com.aspose.cloud.sdk.words.api;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import android.net.Uri;

import com.aspose.cloud.sdk.common.AsposeApp;
import com.aspose.cloud.sdk.common.Utils;
import com.aspose.cloud.sdk.words.model.DocumentResponse;
import com.aspose.cloud.sdk.words.model.DocumentResponse.Document;
import com.aspose.cloud.sdk.words.model.GetMailMergeFieldNamesResponse;
import com.aspose.cloud.sdk.words.model.GetMailMergeFieldNamesResponse.FieldName;
import com.aspose.cloud.sdk.words.model.InsertPageNumberFieldModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Field --- Using this class you can get all merge field names from a word document, insert page number field into the Word document
 * and update all fields in the word document. 
 * @author   M. Sohail Ismail
 */
public class Field {
	
	private static final String WORD_URI = AsposeApp.BASE_PRODUCT_URI + "/words/";
	
	/**
	 * Get all merge field names from a word document
	 * @param fileName Name of the MS Word document on cloud
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return An object that contains field names
	*/
	public static FieldName getAllMergeFieldNamesFromAWordDocument(String fileName) throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		
		FieldName fieldName = null;
		
		if(fileName == null || fileName.length() <= 3) {
			throw new IllegalArgumentException("File name cannot be null or empty");
		}
		
		//build URL
      	String strURL = WORD_URI + Uri.encode(fileName) + "/mailMergeFieldNames";
        //sign URL
        String signedURL = Utils.sign(strURL);
        
        InputStream responseStream = Utils.processCommand(signedURL, "GET");
        String responseJSONString = Utils.streamToString(responseStream);
        
        //Parsing JSON
      	Gson gson = new Gson();
      	GetMailMergeFieldNamesResponse mergeFieldNameRes = gson.fromJson(responseJSONString, GetMailMergeFieldNamesResponse.class);
		if(mergeFieldNameRes.getCode().equals("200") && mergeFieldNameRes.getStatus().equals("OK")) {
			fieldName = mergeFieldNameRes.fieldName;
		}
		
		return fieldName;
	}
	
	/**
	 * Insert page number field into the Word document 
	 * @param fileName Name of the MS Word document on cloud
	 * @param format Field text format {PAGE} of {NUMPAGES}
	 * @param alignment Alignment of the field
	 * @param isTop Field position
	 * @param setPageNumberOnFirstPage Set page number on first page
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return An document object
	*/
	public static Document insertPageNumberFieldIntoTheWordDocument(String fileName, String format, String alignment, Boolean isTop, Boolean setPageNumberOnFirstPage) throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		
		Document document = null;
		
		if(fileName == null || fileName.length() <= 3) {
			throw new IllegalArgumentException("File name cannot be null or empty");
		}
		
		if(format == null || format.length() == 0) {
			throw new IllegalArgumentException("Format cannot be null or empty");
		}
		
		if(alignment == null || alignment.length() == 0) {
			throw new IllegalArgumentException("Alignment cannot be null or empty");
		}
		
		InsertPageNumberFieldModel insertPageNumberFieldData = new InsertPageNumberFieldModel();
		insertPageNumberFieldData.format = format;
		insertPageNumberFieldData.alignment = alignment;
		insertPageNumberFieldData.isTop = isTop;
		insertPageNumberFieldData.setPageNumberOnFirstPage = setPageNumberOnFirstPage;
		
		GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        String requestJSONString = gson.toJson(insertPageNumberFieldData, InsertPageNumberFieldModel.class);
        
		//build URL
      	String strURL = WORD_URI + Uri.encode(fileName) + "/insertPageNumbers";
        //sign URL
        String signedURL = Utils.sign(strURL);
        
        InputStream responseStream = Utils.processCommand(signedURL, "POST", requestJSONString);
        String responseJSONString = Utils.streamToString(responseStream);
        
        //Parsing JSON
        DocumentResponse insertPageNumberFieldResponse = gson.fromJson(responseJSONString, DocumentResponse.class);
		if(insertPageNumberFieldResponse.getCode().equals("200") && insertPageNumberFieldResponse.getStatus().equals("OK")) {
			document = insertPageNumberFieldResponse.document;
		}
		
		return document;
	}
	
	/**
	 * Update all fields in the word document
	 * @param fileName Name of the MS Word document on cloud
	 * @param destDocumentName Result name of the document after the operation. If this parameter is omitted then result of the operation will be saved as the source document 
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return An document object
	*/
	public static Document updateAllFieldsInTheWordDocument(String fileName, String destDocumentName) throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		
		Document document = null;
		
		if(fileName == null || fileName.length() <= 3) {
			throw new IllegalArgumentException("File name cannot be null or empty");
		}
		
		//build URL
      	String strURL;
      	if(destDocumentName != null && destDocumentName.length() != 0) {
      		strURL = WORD_URI + Uri.encode(fileName) + "/updateFields?filename=" + Uri.encode(destDocumentName);
      	} else {
      		strURL = WORD_URI + Uri.encode(fileName) + "/updateFields";
      	}
        //sign URL
        String signedURL = Utils.sign(strURL);
        
        InputStream responseStream = Utils.processCommand(signedURL, "POST");
        String responseJSONString = Utils.streamToString(responseStream);
        
        //Parsing JSON
        Gson gson = new Gson();
        DocumentResponse updateFieldsResponse = gson.fromJson(responseJSONString, DocumentResponse.class);
		if(updateFieldsResponse.getCode().equals("200") && updateFieldsResponse.getStatus().equals("OK")) {
			document = updateFieldsResponse.document;
		}
		
		return document;
	}
}
