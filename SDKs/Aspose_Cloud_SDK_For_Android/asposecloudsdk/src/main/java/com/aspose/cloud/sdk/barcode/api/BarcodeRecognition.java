package com.aspose.cloud.sdk.barcode.api;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import android.net.Uri;

import com.aspose.cloud.sdk.barcode.model.RecognitionResponse.RecognizedBarCode;
import com.aspose.cloud.sdk.barcode.model.*;
import com.aspose.cloud.sdk.common.AsposeApp;
import com.aspose.cloud.sdk.common.Utils;
import com.google.gson.Gson;

/**
 * BarcodeRecognition --- Using this class you can read barcode from Aspose Cloud Storage, Read barcode from external image URL, 
 * Read barcode from specific region of image, Recognize barcode with checksum option from storage and Recognize specified count of barcodes
 * @author   M. Sohail Ismail
 */
public class BarcodeRecognition {
	
	private static final String BARCODE_URI = AsposeApp.BASE_PRODUCT_URI + "/barcode/";
	
	/**
	 * Read barcode from Aspose Cloud Storage
	 * @param fileName Name of file stored on Aspose Cloud Storage
	 * @param type Type of barcode. See class BarcodeTypeEnum for valid values
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return List of recognized barcode objects
	*/
	public static ArrayList<RecognizedBarCode> readBarcodeFromAsposeCloudStorage(String fileName, BarcodeTypeEnum type) throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		
		ArrayList<RecognizedBarCode> barcodes = null;
		
		if(fileName == null || fileName.length() == 0) {
			throw new IllegalArgumentException("Filename cannot be null or empty");
		}
		
		String strURL = BARCODE_URI + Uri.encode(fileName) + "/recognize?type=" + type;
        //sign URL
        String signedURL = Utils.sign(strURL);
        
		InputStream responseStream = Utils.processCommand(signedURL, "GET");
		String jsonStr = Utils.streamToString(responseStream);
		
		//Parsing JSON
		Gson gson = new Gson();
		RecognitionResponse recognitionResponse = gson.fromJson(jsonStr, RecognitionResponse.class);
		if(recognitionResponse.getCode().equals("200") && recognitionResponse.getStatus().equals("OK")) {
			barcodes = recognitionResponse.barcodes;
		}
		
		return barcodes;
	}
	
	/**
	 * Read barcode from external image URL
	 * @param url URL to file
	 * @param type Type of barcode. See class BarcodeTypeEnum for valid values
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return List of recognized barcode objects
	*/
	public static ArrayList<RecognizedBarCode> readBarcodeFromExternalImageURL(String url, BarcodeTypeEnum type) throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		
		ArrayList<RecognizedBarCode> barcodes = null;
		
		if(url == null || url.length() == 0) {
			throw new IllegalArgumentException("URL to file cannot be null or empty");
		}
		
		String strURL = BARCODE_URI + "recognize?type=" + type + "&url=" + url;
        //sign URL
        String signedURL = Utils.sign(strURL);
        
		InputStream responseStream = Utils.processCommand(signedURL, "POST");
		String jsonStr = Utils.streamToString(responseStream);
		
		//Parsing JSON
		Gson gson = new Gson();
		RecognitionResponse recognitionResponse = gson.fromJson(jsonStr, RecognitionResponse.class);
		if(recognitionResponse.getCode().equals("200") && recognitionResponse.getStatus().equals("OK")) {
			barcodes = recognitionResponse.barcodes;
		}
		
		return barcodes;
	}
	
	/**
	 * Read barcode from specific region of image
	 * @param fileName Name of file stored on Aspose Cloud Storage
	 * @param type Type of barcode. See class BarcodeTypeEnum for valid values
	 * @param rectX Rectangular region starting X position
	 * @param rectY Rectangular region starting Y position
	 * @param rectWidth Rectangle width
	 * @param rectHeight Rectange height
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return List of recognized barcode objects
	*/
	public static ArrayList<RecognizedBarCode> readBarcodeFromSpecificRegionOfImage(String fileName, BarcodeTypeEnum type, 
			int rectX, int rectY, int rectWidth, int rectHeight) throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		
		ArrayList<RecognizedBarCode> barcodes = null;
		
		if(fileName == null || fileName.length() == 0) {
			throw new IllegalArgumentException("Filename cannot be null or empty");
		}
		
		String strURL = BARCODE_URI + Uri.encode(fileName) + "/recognize?type=" + type+ "&rectX=" + rectX + "&rectY=" + rectY +
				"&rectWidth=" + rectWidth + "&rectHeight=" + rectHeight;
        //sign URL
        String signedURL = Utils.sign(strURL);
        
		InputStream responseStream = Utils.processCommand(signedURL, "GET");
		String jsonStr = Utils.streamToString(responseStream);
		
		//Parsing JSON
		Gson gson = new Gson();
		RecognitionResponse recognitionResponse = gson.fromJson(jsonStr, RecognitionResponse.class);
		if(recognitionResponse.getCode().equals("200") && recognitionResponse.getStatus().equals("OK")) {
			barcodes = recognitionResponse.barcodes;
		}
		
		return barcodes;
	}
	
	/**
	 * Recognize barcode with checksum option from storage
	 * @param fileName Name of file stored on Aspose Cloud Storage
	 * @param type Type of barcode. See class BarcodeTypeEnum for valid values
	 * @param checksumValidation Sets checksum validation parameter
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return List of recognized barcode objects
	*/
	public static ArrayList<RecognizedBarCode> recognizeBarcodeWithChecksumOptionFromStorage(String fileName, BarcodeTypeEnum type, 
			EnableChecksumEnum checksumValidation) throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		
		ArrayList<RecognizedBarCode> barcodes = null;
		
		if(fileName == null || fileName.length() == 0) {
			throw new IllegalArgumentException("Filename cannot be null or empty");
		}
		
		String strURL = BARCODE_URI + Uri.encode(fileName) + "/recognize?type=" + type + "&checksumValidation=" + checksumValidation;
        //sign URL
        String signedURL = Utils.sign(strURL);
        
		InputStream responseStream = Utils.processCommand(signedURL, "GET");
		String jsonStr = Utils.streamToString(responseStream);
		
		//Parsing JSON
		Gson gson = new Gson();
		RecognitionResponse recognitionResponse = gson.fromJson(jsonStr, RecognitionResponse.class);
		if(recognitionResponse.getCode().equals("200") && recognitionResponse.getStatus().equals("OK")) {
			barcodes = recognitionResponse.barcodes;
		}
		
		return barcodes;
	}
	
	/**
	 * Recognize specified count of barcodes
	 * @param fileName Name of file stored on Aspose Cloud Storage
	 * @param type Type of barcode. See class BarcodeTypeEnum for valid values
	 * @param barcodesCount Sets exact number of barcodes to recognize
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return List of recognized barcode objects
	*/
	public static ArrayList<RecognizedBarCode> recognizeSpecifiedCountOfBarcodes(String fileName, BarcodeTypeEnum type, 
			int barcodesCount) throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		
		ArrayList<RecognizedBarCode> barcodes = null;
		
		if(fileName == null || fileName.length() == 0) {
			throw new IllegalArgumentException("Filename cannot be null or empty");
		}
		
		String strURL = BARCODE_URI + Uri.encode(fileName) + "/recognize?type=" + type + "&barcodesCount=" + barcodesCount;
        //sign URL
        String signedURL = Utils.sign(strURL);
        
		InputStream responseStream = Utils.processCommand(signedURL, "GET");
		String jsonStr = Utils.streamToString(responseStream);
		
		//Parsing JSON
		Gson gson = new Gson();
		RecognitionResponse recognitionResponse = gson.fromJson(jsonStr, RecognitionResponse.class);
		if(recognitionResponse.getCode().equals("200") && recognitionResponse.getStatus().equals("OK")) {
			barcodes = recognitionResponse.barcodes;
		}
		
		return barcodes;
	}
}
