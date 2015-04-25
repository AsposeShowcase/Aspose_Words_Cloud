package com.aspose.cloud.sdk.barcode.api;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import android.net.Uri;

import com.aspose.cloud.sdk.common.AsposeApp;
import com.aspose.cloud.sdk.common.BaseResponse;
import com.aspose.cloud.sdk.common.Utils;
import com.aspose.cloud.sdk.barcode.model.*;

import com.google.gson.Gson;

/**
 * BarcodeGeneration --- Using this class you can generate image with barcode of specified type and parameters, Create barcode on the Aspose Cloud Storage, 
 * Generate barcode with appropriate code text location, Generate barcode with checksum option, 
 * Rotate barcode image with suitable angle, Set barcode image margin, Set barcode image resolution, \
 * Set height of the bars in the barcode image, Set X and Y dimensions of a barcode.
 * @author   M. Sohail Ismail
 */
public class BarcodeGeneration {
	
	private static final String BARCODE_URI = AsposeApp.BASE_PRODUCT_URI + "/barcode/";
	
	/**
	 * Generate image with barcode of specified type and parameters
	 * @param text Text to encode inside barcode
	 * @param type Type of barcode. See class BarcodeTypeEnum for valid values
	 * @param format Returns an image in specified format. See class ValidFormatsEnum for valid formats
	 * @param outputFileName Generated image will save on device with this name
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return Path to generated image saved on device
	*/
	public static String generateImageWithBarcodeOfSpecifiedTypeAndParameters(String text, BarcodeTypeEnum type, ValidFormatsEnum format, String outputFileName) throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		
		String outputFilePath = null;
		
		if(text == null || text.length() == 0) {
			throw new IllegalArgumentException("Text to encode cannot be null or empty");
		}
		
		if(type == null) {
			throw new IllegalArgumentException("Barcode type cannot be null");
		}
		
		String strURL = BARCODE_URI + "generate?text=" + Uri.encode(text) + "&type=" + type;
		if(format != null) {
			strURL = strURL + "&format=" + format;
		}
		
        //sign URL
        String signedURL = Utils.sign(strURL);
        
		InputStream responseStream = Utils.processCommand(signedURL, "GET");
		
		//Save the stream in response to the disk
		outputFilePath = Utils.saveStreamToFile(responseStream, outputFileName);
		
		return outputFilePath;
	}
	
	/**
	 * Create barcode on the Aspose Cloud Storage
	 * @param fileName Name of file stored on Aspose Cloud Storage
	 * @param text Text to encode inside barcode
	 * @param type Type of barcode. See class BarcodeTypeEnum for valid values
	 * @param format Returns an image in specified format. See class ValidFormatsEnum for valid formats
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return Boolean variable that indicates whether barcode created successfully
	*/
	public static boolean createBarcodeOnAsposeCloudStorage(String fileName, String text, BarcodeTypeEnum type, ValidFormatsEnum format) throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		
		boolean isBarcodeCreatedSuccessfully = false;
		
		if(text == null || text.length() == 0) {
			throw new IllegalArgumentException("Text to encode cannot be null or empty");
		}
		
		if(type == null) {
			throw new IllegalArgumentException("Barcode type cannot be null");
		}
		
		String strURL = BARCODE_URI + Uri.encode(fileName) + "/generate?text=" + Uri.encode(text) + "&type=" + type;
		if(format != null) {
			strURL = strURL + "&format=" + format;
		}
		
        //sign URL
        String signedURL = Utils.sign(strURL);
        
		InputStream responseStream = Utils.processCommand(signedURL, "PUT");
		String jsonStr = Utils.streamToString(responseStream);
		
		//Parsing JSON
		Gson gson = new Gson();
		BaseResponse baseResponse = gson.fromJson(jsonStr, BaseResponse.class);
		if(baseResponse.getCode().equals("200") && baseResponse.getStatus().equals("OK")) {
			isBarcodeCreatedSuccessfully = true;
		}
		
		return isBarcodeCreatedSuccessfully;
	}
	
	/**
	 * Generate barcode with appropriate code text location
	 * @param fileName Name of file stored on Aspose Cloud Storage
	 * @param text Text to encode inside barcode
	 * @param type Type of barcode. See class BarcodeTypeEnum for valid values
	 * @param format Returns an image in specified format. See class ValidFormatsEnum for valid formats
	 * @param codeLocation Location of the code. See class CodeLocationEnum for valid values
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return Boolean variable that indicates whether barcode generated successfully
	*/
	public static boolean generateBarcodeWithAppropriateCodeTextLocation(String fileName, String text, 
			BarcodeTypeEnum type, ValidFormatsEnum format, CodeLocationEnum codeLocation) throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		
		boolean isBarcodeGeneratedSuccessfully = false;
		
		if(text == null || text.length() == 0) {
			throw new IllegalArgumentException("Text to encode cannot be null or empty");
		}
		
		if(type == null) {
			throw new IllegalArgumentException("Barcode type cannot be null");
		}
		
		String strURL = BARCODE_URI + Uri.encode(fileName) + "/generate?text=" + Uri.encode(text) + "&type=" + type;
		if(format != null) {
			strURL = strURL + "&format=" + format;
		}
		if(codeLocation != null) {
			strURL = strURL + "&codeLocation=" + codeLocation;
		}
		
        //sign URL
        String signedURL = Utils.sign(strURL);
        
		InputStream responseStream = Utils.processCommand(signedURL, "PUT");
		String jsonStr = Utils.streamToString(responseStream);
		
		//Parsing JSON
		Gson gson = new Gson();
		BaseResponse baseResponse = gson.fromJson(jsonStr, BaseResponse.class);
		if(baseResponse.getCode().equals("200") && baseResponse.getStatus().equals("OK")) {
			isBarcodeGeneratedSuccessfully = true;
		}
		
		return isBarcodeGeneratedSuccessfully;
	}
	
	/**
	 * Generate barcode with checksum option
	 * @param fileName Name of file stored on Aspose Cloud Storage
	 * @param text Text to encode inside barcode
	 * @param type Type of barcode. See class BarcodeTypeEnum for valid values
	 * @param format Returns an image in specified format. See class ValidFormatsEnum for valid formats
	 * @param enableChecksum Sets if checksum will be added to barcode image
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return Boolean variable that indicates whether barcode generated successfully with checksum option
	*/
	public static boolean generateBarcodeWithChecksumOption(String fileName, String text, 
			BarcodeTypeEnum type, ValidFormatsEnum format, EnableChecksumEnum enableChecksum) throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		
		boolean isBarcodeGeneratedSuccessfully = false;
		
		if(text == null || text.length() == 0) {
			throw new IllegalArgumentException("Text to encode cannot be null or empty");
		}
		
		if(type == null) {
			throw new IllegalArgumentException("Barcode type cannot be null");
		}
		
		String strURL = BARCODE_URI + Uri.encode(fileName) + "/generate?text=" + Uri.encode(text) + "&type=" + type;
		if(format != null) {
			strURL = strURL + "&format=" + format;
		}
		if(enableChecksum != null) {
			strURL = strURL + "&enableChecksum=" + enableChecksum;
		}
		
        //sign URL
        String signedURL = Utils.sign(strURL);
        
		InputStream responseStream = Utils.processCommand(signedURL, "PUT");
		String jsonStr = Utils.streamToString(responseStream);
		
		//Parsing JSON
		Gson gson = new Gson();
		BaseResponse baseResponse = gson.fromJson(jsonStr, BaseResponse.class);
		if(baseResponse.getCode().equals("200") && baseResponse.getStatus().equals("OK")) {
			isBarcodeGeneratedSuccessfully = true;
		}
		
		return isBarcodeGeneratedSuccessfully;
	}
	
	/**
	 * Rotate barcode image with suitable angle
	 * @param fileName Name of file stored on Aspose Cloud Storage
	 * @param text Text to encode inside barcode
	 * @param type Type of barcode. See class BarcodeTypeEnum for valid values
	 * @param format Returns an image in specified format. See class ValidFormatsEnum for valid formats
	 * @param rotAngle Angle of barcode orientation
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return Boolean variable that indicates whether barcode rotated successfully with suitable angle
	*/
	public static boolean rotateBarcodeImageWithSuitableAngle(String fileName, String text, 
			BarcodeTypeEnum type, ValidFormatsEnum format, float rotAngle) throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		
		boolean isBarcodeRotatedSuccessfully = false;
		
		if(text == null || text.length() == 0) {
			throw new IllegalArgumentException("Text to encode cannot be null or empty");
		}
		
		if(type == null) {
			throw new IllegalArgumentException("Barcode type cannot be null");
		}
		
		String strURL = BARCODE_URI + Uri.encode(fileName) + "/generate?text=" + Uri.encode(text) + "&type=" + type;
		if(format != null) {
			strURL = strURL + "&format=" + format;
		}
		strURL = strURL + "&rotAngle=" + rotAngle;
		
        //sign URL
        String signedURL = Utils.sign(strURL);
        
		InputStream responseStream = Utils.processCommand(signedURL, "PUT");
		String jsonStr = Utils.streamToString(responseStream);
		
		//Parsing JSON
		Gson gson = new Gson();
		BaseResponse baseResponse = gson.fromJson(jsonStr, BaseResponse.class);
		if(baseResponse.getCode().equals("200") && baseResponse.getStatus().equals("OK")) {
			isBarcodeRotatedSuccessfully = true;
		}
		
		return isBarcodeRotatedSuccessfully;
	}
	
	/**
	 * Set barcode image margin
	 * @param fileName Name of file stored on Aspose Cloud Storage
	 * @param text Text to encode inside barcode
	 * @param type Type of barcode. See class BarcodeTypeEnum for valid values
	 * @param format Returns an image in specified format. See class ValidFormatsEnum for valid formats
	 * @param topMargin Margin between barcode and top image border
	 * @param bottomMargin Margin between barcode and bottom image border
	 * @param leftMargin Margin between barcode and left image border
	 * @param rightMargin Margin between barcode and right image border
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return Boolean variable that indicates whether barcode rotated successfully with suitable angle
	*/
	public static boolean setBarcodeImageMargin(String fileName, String text, BarcodeTypeEnum type, ValidFormatsEnum format, 
			int topMargin, int bottomMargin, int leftMargin, int rightMargin) throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		
		boolean isBarcodeImageMarginSetSuccessfully = false;
		
		if(text == null || text.length() == 0) {
			throw new IllegalArgumentException("Text to encode cannot be null or empty");
		}
		
		if(type == null) {
			throw new IllegalArgumentException("Barcode type cannot be null");
		}
		
		String strURL = BARCODE_URI + Uri.encode(fileName) + "/generate?text=" + Uri.encode(text) + "&type=" + type;
		if(format != null) {
			strURL = strURL + "&format=" + format;
		}
		strURL = strURL + "&topMargin=" + topMargin + "&bottomMargin=" + bottomMargin
                + "&leftMargin=" + leftMargin + "&rightMargin=" + rightMargin;
		
        //sign URL
        String signedURL = Utils.sign(strURL);
        
		InputStream responseStream = Utils.processCommand(signedURL, "PUT");
		String jsonStr = Utils.streamToString(responseStream);
		
		//Parsing JSON
		Gson gson = new Gson();
		BaseResponse baseResponse = gson.fromJson(jsonStr, BaseResponse.class);
		if(baseResponse.getCode().equals("200") && baseResponse.getStatus().equals("OK")) {
			isBarcodeImageMarginSetSuccessfully = true;
		}
		
		return isBarcodeImageMarginSetSuccessfully;
	}
	
	/**
	 * Set barcode image resolution
	 * @param text Text to encode inside barcode
	 * @param type Type of barcode. See class BarcodeTypeEnum for valid values
	 * @param format Returns an image in specified format. See class ValidFormatsEnum for valid formats
	 * @param resolutionX Resolution along X in dpi
	 * @param resolutionY Resolution along Y in dpi
	 * @param outputFileName Generated image will save on device with this name
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return Path to generated image saved on device
	*/
	public static String setBarcodeImageResolution(String text, BarcodeTypeEnum type, ValidFormatsEnum format, 
			double resolutionX, double resolutionY, String outputFileName) throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		
		String outputFilePath = null;
		
		if(text == null || text.length() == 0) {
			throw new IllegalArgumentException("Text to encode cannot be null or empty");
		}
		
		if(type == null) {
			throw new IllegalArgumentException("Barcode type cannot be null");
		}
		
		String strURL = BARCODE_URI + "generate?text=" + Uri.encode(text) + "&type=" + type;
		if(format != null) {
			strURL = strURL + "&format=" + format;
		}
		strURL = strURL + "&resolutionX=" + resolutionX + "&resolutionY=" + resolutionY;
		
        //sign URL
        String signedURL = Utils.sign(strURL);
        
		InputStream responseStream = Utils.processCommand(signedURL, "GET");
		
		//Save the stream in response to the disk
		outputFilePath = Utils.saveStreamToFile(responseStream, outputFileName);
		
		return outputFilePath;
	}
	
	/**
	 * Set height of the bars in the barcode image
	 * @param fileName Name of file stored on Aspose Cloud Storage
	 * @param text Text to encode inside barcode
	 * @param type Type of barcode. See class BarcodeTypeEnum for valid values
	 * @param format Returns an image in specified format. See class ValidFormatsEnum for valid formats
	 * @param barHeight Height of the bar
	 * @param grUnit Measurement of basic barcode parameters. Please see class GRUnitEnum for valid values
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return Boolean variable that indicates whether height of the bars set successfully in the Barcode image
	*/
	public static boolean setHeightOfTheBarsInTheBarcodeImage(String fileName, String text, 
			BarcodeTypeEnum type, ValidFormatsEnum format, double barHeight, GRUnitEnum grUnit) throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		
		boolean isBarcodeHeightSetSuccessfully = false;
		
		if(text == null || text.length() == 0) {
			throw new IllegalArgumentException("Text to encode cannot be null or empty");
		}
		
		if(type == null) {
			throw new IllegalArgumentException("Barcode type cannot be null");
		}
		
		String strURL = BARCODE_URI + Uri.encode(fileName) + "/generate?text=" + Uri.encode(text) + "&type=" + type;
		if(format != null) {
			strURL = strURL + "&format=" + format;
		}
		strURL = strURL + "&barHeight=" + barHeight + "&grUnit=" + grUnit;
		
        //sign URL
        String signedURL = Utils.sign(strURL);
        
		InputStream responseStream = Utils.processCommand(signedURL, "PUT");
		String jsonStr = Utils.streamToString(responseStream);
		
		//Parsing JSON
		Gson gson = new Gson();
		BaseResponse baseResponse = gson.fromJson(jsonStr, BaseResponse.class);
		if(baseResponse.getCode().equals("200") && baseResponse.getStatus().equals("OK")) {
			isBarcodeHeightSetSuccessfully = true;
		}
		
		return isBarcodeHeightSetSuccessfully;
	}
	
	/**
	 * Set X and Y dimensions of a barcode
	 * @param text Text to encode inside barcode
	 * @param type Type of barcode. See class BarcodeTypeEnum for valid values
	 * @param format Returns an image in specified format. See class ValidFormatsEnum for valid formats
	 * @param dimensionX Width of barcode unit (bar or space)
	 * @param dimensionY Height of barcode unit (for 2D barcodes)
	 * @param outputFileName Generated image will save on device with this name
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return Path to generated image saved on device
	*/
	public static String setXAndYDimensionsOfABarcode(String text, BarcodeTypeEnum type, ValidFormatsEnum format, 
			double dimensionX, double dimensionY, String outputFileName) throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		
		String outputFilePath = null;
		
		if(text == null || text.length() == 0) {
			throw new IllegalArgumentException("Text to encode cannot be null or empty");
		}
		
		if(type == null) {
			throw new IllegalArgumentException("Barcode type cannot be null");
		}
		
		String strURL = BARCODE_URI + "generate?text=" + Uri.encode(text) + "&type=" + type;
		if(format != null) {
			strURL = strURL + "&format=" + format;
		}
		strURL = strURL + "&dimensionX=" + dimensionX + "&dimensionY=" + dimensionY;
		
        //sign URL
        String signedURL = Utils.sign(strURL);
        
		InputStream responseStream = Utils.processCommand(signedURL, "GET");
		
		//Save the stream in response to the disk
		outputFilePath = Utils.saveStreamToFile(responseStream, outputFileName);
		
		return outputFilePath;
	}
}
