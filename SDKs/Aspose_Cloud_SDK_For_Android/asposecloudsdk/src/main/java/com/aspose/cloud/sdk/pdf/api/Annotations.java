package com.aspose.cloud.sdk.pdf.api;

import android.net.Uri;

import com.aspose.cloud.sdk.common.AsposeApp;
import com.aspose.cloud.sdk.common.Utils;
import com.aspose.cloud.sdk.pdf.model.AnnotationDetails;
import com.aspose.cloud.sdk.pdf.model.AnnotationResponse;
import com.aspose.cloud.sdk.pdf.model.AnnotationsResponse;
import com.aspose.cloud.sdk.pdf.model.AnnotationsResponse.AnnotationsResult;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * Annotations --- Using this class you can get all annotations, a specific annotation and annotation count. 
 * @author   M. Sohail Ismail
 */
public class Annotations {
	
	private static final String PDF_URI = AsposeApp.BASE_PRODUCT_URI + "/pdf/";
	
	/**
	 * Get all annotations from a PDF page
	 * @param fileName Name of the file on cloud
	 * @param pageIndex Index of page starting from 1
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return An object that contains all annotations  
	*/ 
	public static AnnotationsResult getAllAnnotationsFromAPDFPage(String fileName, int pageIndex) throws InvalidKeyException, NoSuchAlgorithmException, IOException {

		AnnotationsResult annotations = null;
		
		if(fileName == null || fileName.length() == 0) {
			throw new IllegalArgumentException("File name cannot be null or empty");
		}
		
		//build URL
		String strURL = PDF_URI + Uri.encode(fileName) + "/pages/" + pageIndex + "/annotations";
		
		//sign URL
		String signedURL = Utils.sign(strURL);
		InputStream responseStream = Utils.processCommand(signedURL, "GET");
		String responseJSONString = Utils.streamToString(responseStream);
		
		//Parsing JSON
		Gson gson = new Gson();
		AnnotationsResponse annotationResponse = gson.fromJson(responseJSONString, AnnotationsResponse.class);
		if(annotationResponse.getCode().equals("200") && annotationResponse.getStatus().equals("OK")) {
			annotations = annotationResponse.annotations;
		}
		
		return annotations;
	}
	
	/**
	 * Get annotations count from a PDF page
	 * @param fileName Name of the file on cloud
	 * @param pageIndex Index of page starting from 1
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return Annotations count  
	*/ 
	public static int getAnnotationsCountFromAPDFPage(String fileName, int pageIndex) throws InvalidKeyException, NoSuchAlgorithmException, IOException {

		int annotationsCount = -1;
		
		if(fileName == null || fileName.length() == 0) {
			throw new IllegalArgumentException("File name cannot be null or empty");
		}
		
		//build URL
		String strURL = PDF_URI + Uri.encode(fileName) + "/pages/" + pageIndex + "/annotations";
		
		//sign URL
		String signedURL = Utils.sign(strURL);
		InputStream responseStream = Utils.processCommand(signedURL, "GET");
		String responseJSONString = Utils.streamToString(responseStream);
		
		//Parsing JSON
		Gson gson = new Gson();
		AnnotationsResponse annotationResponse = gson.fromJson(responseJSONString, AnnotationsResponse.class);
		if(annotationResponse.getCode().equals("200") && annotationResponse.getStatus().equals("OK")) {
			annotationsCount = annotationResponse.annotations.List.size();
		}
		
		return annotationsCount;
	}
	
	/**
	 * Get a specific annotation from a PDF page
	 * @param fileName Name of the file on cloud
	 * @param pageIndex Index of page starting from 1
	 * @param annotationIndex Index of annotation starting from 1
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return Requested annotation   
	*/ 
	public static AnnotationDetails getASpecificAnnotationFromAPDFPage(String fileName, int pageIndex, int annotationIndex) throws InvalidKeyException, NoSuchAlgorithmException, IOException {

		AnnotationDetails annotation = null;
		
		if(fileName == null || fileName.length() == 0) {
			throw new IllegalArgumentException("File name cannot be null or empty");
		}
		
		//build URL
		String strURL = PDF_URI + Uri.encode(fileName) + "/pages/" + pageIndex + "/annotations/" + annotationIndex;
		
		//sign URL
		String signedURL = Utils.sign(strURL);
		InputStream responseStream = Utils.processCommand(signedURL, "GET");
		String responseJSONString = Utils.streamToString(responseStream);
		
		//Parsing JSON
		Gson gson = new Gson();
		AnnotationResponse annotationResponse = gson.fromJson(responseJSONString, AnnotationResponse.class);
		if(annotationResponse.getCode().equals("200") && annotationResponse.getStatus().equals("OK")) {
			annotation = annotationResponse.annotation;
		}
		
		return annotation;
	}
}
