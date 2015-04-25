package com.aspose.cloud.sdk.ocr.api;

import android.net.Uri;

import com.aspose.cloud.sdk.common.AsposeApp;
import com.aspose.cloud.sdk.common.Utils;
import com.aspose.cloud.sdk.ocr.model.LanguageEnum;
import com.aspose.cloud.sdk.ocr.model.OCRResponse;
import com.google.gson.Gson;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * OCR --- Using this class you can extract text from a BMP or TIFF image, Extract text from a specific block of an image, 
 *    Extract image text from local file  
 * @author   M. Sohail Ismail
 */
public class OCR {
	private static final String BARCODE_URI = AsposeApp.BASE_PRODUCT_URI + "/ocr/";
	
	/**
	 * Extract text from a BMP or TIFF image 
	 * @param fileName Name of file stored on Aspose Cloud Storage
	 * @param useDefaultDictionaries Allows to correct text after recognition using default dictionaries
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return An object that contains extracted text from a BMP or TIFF image
	*/
	public static OCRResponse extractOCRorHOCRTextFromImages(String fileName, LanguageEnum language, boolean useDefaultDictionaries) throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		
		OCRResponse ocrResponse = null;
		
		if(fileName == null || fileName.length() == 0) {
			throw new IllegalArgumentException("Filename cannot be null or empty");
		}
		
		String strURL = BARCODE_URI + Uri.encode(fileName) + "/recognize?useDefaultDictionaries=" + useDefaultDictionaries;
        //sign URL
        String signedURL = Utils.sign(strURL);
        
		InputStream responseStream = Utils.processCommand(signedURL, "GET");
		String jsonStr = Utils.streamToString(responseStream);
		
		//Parsing JSON
		Gson gson = new Gson();
		ocrResponse = gson.fromJson(jsonStr, OCRResponse.class);
		if(ocrResponse.getCode().equals("200") && ocrResponse.getStatus().equals("OK")) {
			return ocrResponse;
		}
		
		return null;
	}
	
	/**
	 * Extract text from a specific block of an image 
	 * @param fileName Name of file stored on Aspose Cloud Storage
	 * @param language Language of document to recogniize. English, french, russian and spanish are supported now
	 * @param rectX, rectY, rectWidth, rectHeight Recognition of text inside specified Rectangle region
	 * @param useDefaultDictionaries Allows to correct text after recognition using default dictionaries
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return An object that contains extracted text from a BMP or TIFF image
	*/
	public static OCRResponse extractOCRorHOCRTextFromASpecificBlock(String fileName, LanguageEnum language, 
			int rectX, int rectY, int rectWidth, int rectHeight, boolean useDefaultDictionaries) throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		
		OCRResponse ocrResponse = null;
		
		if(fileName == null || fileName.length() == 0) {
			throw new IllegalArgumentException("Filename cannot be null or empty");
		}
		
		String strURL = BARCODE_URI + Uri.encode(fileName) + "/recognize?language=" + language + "&rectX=" + rectX + "&rectY=" + rectY +
				"&rectWidth=" + rectWidth + "&rectHeight=" + rectHeight + "&useDefaultDictionaries=" + useDefaultDictionaries;
        //sign URL
        String signedURL = Utils.sign(strURL);
        
		InputStream responseStream = Utils.processCommand(signedURL, "GET");
		String jsonStr = Utils.streamToString(responseStream);
		
		//Parsing JSON
		Gson gson = new Gson();
		ocrResponse = gson.fromJson(jsonStr, OCRResponse.class);
		if(ocrResponse.getCode().equals("200") && ocrResponse.getStatus().equals("OK")) {
			return ocrResponse;
		}
		
		return null;
	}
	
	/**
	 * Extract image text from local file 
	 * @param localFilePath Filename of image stored locally
	 * @param language Language of document to recogniize. English, french, russian and spanish are supported now
	 * @param useDefaultDictionaries Allows to correct text after recognition using default dictionaries
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return An object that contains extracted text from a BMP or TIFF image
	*/
	public static OCRResponse extractImageTextFromlocalFile(String localFilePath, LanguageEnum language, boolean useDefaultDictionaries) throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		
		OCRResponse ocrResponse = null;
		
		if(localFilePath == null || localFilePath.length() == 0) {
			throw new IllegalArgumentException("Local file path cannot be null or empty");
		}
		
		String strURL = BARCODE_URI + "recognize?language=" + language + "&useDefaultDictionaries=" + useDefaultDictionaries;
        //sign URL
        String signedURL = Utils.sign(strURL);
        
        //Convert the local file to InputStream
      	InputStream fileStream = new FileInputStream(localFilePath);
      	//Process the request on server
      	InputStream responseStream = Utils.processCommand(signedURL, "POST", fileStream);
		String jsonStr = Utils.streamToString(responseStream);
		
		//Parsing JSON
		Gson gson = new Gson();
		ocrResponse = gson.fromJson(jsonStr, OCRResponse.class);
		if(ocrResponse.getCode().equals("200") && ocrResponse.getStatus().equals("OK")) {
			return ocrResponse;
		}
		
		return null;
	}
}
