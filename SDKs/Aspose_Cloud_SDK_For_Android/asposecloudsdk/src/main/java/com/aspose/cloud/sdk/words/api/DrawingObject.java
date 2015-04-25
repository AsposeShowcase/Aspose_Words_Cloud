package com.aspose.cloud.sdk.words.api;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import android.net.Uri;

import com.aspose.cloud.sdk.common.AsposeApp;
import com.aspose.cloud.sdk.common.Utils;
import com.aspose.cloud.sdk.words.model.GetDrawingObjectsResponse;
import com.aspose.cloud.sdk.words.model.GetDrawingObjectsResponse.DrawingObjectsData;
import com.google.gson.Gson;

/**
 * DrawingObject --- Using this class you can get all drawing objects from a Word document, convert drawing object to 
 * an image and get image and OLE data of a particular drawing object of the document.
 * accept/reject revisions in source document and get statistical data of the document.
 * @author   M. Sohail Ismail
 */
public class DrawingObject {
	
	private static final String WORD_URI = AsposeApp.BASE_PRODUCT_URI + "/words/";
	 
	/**
	 * Get all drawing objects from a Word document
	 * @param fileName Name of the MS Word document on cloud
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return An object that contains array of links where each link points to a drawing object
	*/
	public static DrawingObjectsData getAllDrawingObjects(String fileName) throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		
		DrawingObjectsData drawingObjects = null;
		
		if(fileName == null || fileName.length() <= 3) {
			throw new IllegalArgumentException("File name cannot be null or empty");
		}
		
		//build URL
      	String strURL = WORD_URI + Uri.encode(fileName) + "/drawingObjects/";
        //sign URL
        String signedURL = Utils.sign(strURL);
        
        InputStream responseStream = Utils.processCommand(signedURL, "GET");
        String responseJSONString = Utils.streamToString(responseStream);
        
        //Parsing JSON
      	Gson gson = new Gson();
      	GetDrawingObjectsResponse drawingObjectsResponse = gson.fromJson(responseJSONString, GetDrawingObjectsResponse.class);
		if(drawingObjectsResponse.getCode().equals("200") && drawingObjectsResponse.getStatus().equals("OK")) {
			drawingObjects = drawingObjectsResponse.drawingObjects;
		}
		
		return drawingObjects;
	}
	
	/**
	 * Convert drawing object to an image
	 * @param fileName Name of the MS Word document on cloud
	 * @param index Index of drawing object
	 * @param outputFileFormat The format in which drawing object will be saved
	 * @param outputFileName Name of the output file
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return A path to output file saved on device
	*/
	public static String convertDrawingObjectToImage(String fileName, int index, String outputFileFormat, String outputFileName) throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		
		String localFilePath = null;
		
		if(fileName == null || fileName.length() <= 3) {
			throw new IllegalArgumentException("File name cannot be null or empty");
		}
		
		if(outputFileFormat == null || outputFileFormat.length() == 0) {
			throw new IllegalArgumentException("OutputFileFormat cannot be null or empty");
		}
		
		if(outputFileName == null || outputFileName.length() <= 3) {
			throw new IllegalArgumentException("outputFileName cannot be null or empty");
		}
		
		//build URL
      	String strURL = WORD_URI + Uri.encode(fileName) + "/drawingObjects/" + index + "?format=" + outputFileFormat;
        //sign URL
        String signedURL = Utils.sign(strURL);
        
        InputStream responseStream = Utils.processCommand(signedURL, "GET");
        
        //Save the stream in response to the disk
        localFilePath = Utils.saveStreamToFile(responseStream, outputFileName);
        return localFilePath;
	}
	
	/**
	 * Get image data of a particular drawing object of the document.
	 * @param fileName Name of the MS Word document on cloud
	 * @param index Index of drawing object
	 * @param outputFileName Name of the output file
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return A path to output file saved on device
	*/
	public static String getTheImageDrawingObjectFromDocument(String fileName, int index, String outputFileName) throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		
		String localFilePath = null;
		
		if(fileName == null || fileName.length() <= 3) {
			throw new IllegalArgumentException("File name cannot be null or empty");
		}
		
		if(outputFileName == null || outputFileName.length() <= 3) {
			throw new IllegalArgumentException("OutputFileName cannot be null or empty");
		}
		
		//build URL
      	String strURL = WORD_URI + Uri.encode(fileName) + "/drawingObjects/" + index + "/imageData";
        //sign URL
        String signedURL = Utils.sign(strURL);
        
        InputStream responseStream = Utils.processCommand(signedURL, "GET");
        //Save the stream in response to the disk
        localFilePath = Utils.saveStreamToFile(responseStream, outputFileName);
        
        return localFilePath;
	}
	
	/**
	 * Get embedded OLE file of the drawing object of the document. Returns an error if the drawing object does not have embedded OLE file.
	 * @param fileName Name of the MS Word document on cloud
	 * @param index Index of drawing object
	 * @param outputFileName Name of the output file
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return A path to output file saved on device
	*/
	public static String getTheOLEDrawingObjectFromAWordDocument(String fileName, int index, String outputFileName) throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		
		String localFilePath = null;
		
		if(fileName == null || fileName.length() <= 3) {
			throw new IllegalArgumentException("File name cannot be null or empty");
		}
		
		if(outputFileName == null || outputFileName.length() <= 3) {
			throw new IllegalArgumentException("OutputFileName cannot be null or empty");
		}
		
		//build URL
      	String strURL = WORD_URI + Uri.encode(fileName) + "/drawingObjects/" + index + "/oleData";
        //sign URL
        String signedURL = Utils.sign(strURL);
        
        InputStream responseStream = Utils.processCommand(signedURL, "GET");
        //Save the stream in response to the disk
        localFilePath = Utils.saveStreamToFile(responseStream, outputFileName);
        
        return localFilePath;
	}
}
