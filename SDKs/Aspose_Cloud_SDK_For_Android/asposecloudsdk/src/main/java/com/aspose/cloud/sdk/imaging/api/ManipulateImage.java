package com.aspose.cloud.sdk.imaging.api;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;

import android.net.Uri;

import com.aspose.cloud.sdk.common.AsposeApp;
import com.aspose.cloud.sdk.common.BaseResponse;
import com.aspose.cloud.sdk.common.Utils;
import com.google.gson.Gson;

/**
 * ManipulateImage --- This class helps you in performing manipulation operations on image like Resize, Crop,
 * Rotate, Flip, Convert Image Format, Merge Tiff Images and Converts Tiff Image to Fax Compatible Format   
 * @author   M. Sohail Ismail
 */
public class ManipulateImage {

	private static final String IMAGING_URI = AsposeApp.BASE_PRODUCT_URI + "/imaging/";
	private static final List<String> validFormats = Arrays.asList("bmp", "png", "jpg", "tiff", "psd", "gif");
	private static final List<String> validRotateFlipMethods = Arrays.asList("Rotate180FlipNone", "Rotate180FlipX", 
			"Rotate180FlipXY", "Rotate180FlipY", "Rotate270FlipNone", "Rotate270FlipX", "Rotate270FlipXY", "Rotate270FlipY", 
			"Rotate90FlipNone", "Rotate90FlipX", "Rotate90FlipXY", "Rotate90FlipY", "RotateNoneFlipNone", 
			"RotateNoneFlipX", "RotateNoneFlipXY", "RotateNoneFlipY");
	
	/**
	 * Resize image stored on disk and optionally change format.
	 * @param localFilePath A path to Image on disk
	 * @param newWidth New width of the scaled image
	 * @param newHeight New height of the scaled image
	 * @param designatedFormat Output file format
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return A path to the scaled image
	*/ 
	public static String resizeLocallyStoredImageAndChangeFormat(String localFilePath, int newWidth, int newHeight, String designatedFormat) throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		String updatedFilePath = null;
		
		if(localFilePath == null || localFilePath.length() == 0) {
			throw new IllegalArgumentException("Local file path cannot be null or empty");
		}
		
		if(!validFormats.contains(designatedFormat)) {
			throw new IllegalArgumentException("Valid Image Formats are bmp, png, jpg, tiff, psd, gif");
		}
		
		//Build the request URI to resize image
		String strURL = IMAGING_URI + "resize?newWidth=" + newWidth + "&newHeight=" + newHeight + "&format=" + designatedFormat;
		//Sign the request URI
		String signedURL = Utils.sign(strURL);	
		//Convert the local file to InputStream
		InputStream fileStream = new FileInputStream(localFilePath);
		//Process the request on server
		InputStream responseStream = Utils.processCommand(signedURL, "POST", fileStream);
		//Get fileName from localFilePath
		String fileName;
		int index = localFilePath.lastIndexOf("/");
		if(index != -1) {
			fileName = localFilePath.substring(index+1);
		} else {
			fileName = localFilePath;
		}
		//Replace fileName extension with designated format 
		String[] fileNameAndItsExtensionArray = fileName.split("\\.");
		fileName = fileNameAndItsExtensionArray[0] + "." + designatedFormat;
				
		//Save the stream in response to the disk
		updatedFilePath = Utils.saveStreamToFile(responseStream, fileName);
		
		return updatedFilePath;
	}
	
	/**
	 * Resize image stored on Cloud and optionally change format.
	 * @param fileName Name of Image on cloud
	 * @param newWidth New width of the scaled image
	 * @param newHeight New height of the scaled image
	 * @param designatedFormat Output file format
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return A path to the scaled image
	*/ 
	public static String resizeImageStoredOnCloundAndChangeFormat(String fileName, int newWidth, int newHeight, String designatedFormat) throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		String updatedFilePath = null;
		
		if(fileName == null || fileName.length() == 0) {
			throw new IllegalArgumentException("File name cannot be null or empty");
		}
		
		if(!validFormats.contains(designatedFormat)) {
			throw new IllegalArgumentException("Valid Image Formats are bmp, png, jpg, tiff, psd, gif");
		}
		
		//Build the request URI to resize image
		String strURL = IMAGING_URI + Uri.encode(fileName) + "/resize?newWidth=" + newWidth + "&newHeight=" + newHeight + "&format=" + designatedFormat;
		//Sign the request URI
		String signedURL = Utils.sign(strURL);	
		//Process the request on server
		InputStream responseStream = Utils.processCommand(signedURL, "GET");
		
		//Replace fileName extension with designated format 
		String[] fileNameAndItsExtensionArray = fileName.split("\\.");
		fileName = fileNameAndItsExtensionArray[0] + "." + designatedFormat;
				
		//Save the stream in response to the disk
		updatedFilePath = Utils.saveStreamToFile(responseStream, fileName);
		
		return updatedFilePath;
	}
	
	/**
	 * Crop image stored on disk and optionally change format.
	 * @param localFilePath A path to Image on disk
	 * @param x X position of start point for cropping rectangle 
	 * @param y Y position of start point for cropping rectangle
	 * @param width Width of cropping rectangle 
	 * @param height Height of cropping rectangle 
	 * @param designatedFormat Output file format
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return A path to the cropped image
	*/ 
	public static String cropLocallyStoredImageAndChangeFormat(String localFilePath, int x, int y, int width, int height, String designatedFormat) throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		String updatedFilePath = null;
		
		if(localFilePath == null || localFilePath.length() == 0) {
			throw new IllegalArgumentException("Local file path cannot be null or empty");
		}
		
		if(!validFormats.contains(designatedFormat)) {
			throw new IllegalArgumentException("Valid Image Formats are bmp, png, jpg, tiff, psd, gif");
		}
		
		//Build the request URI to crop image
		String strURL = IMAGING_URI + "crop?format=" + designatedFormat + "&x=" + x + "&y=" + y + 
							"&width=" + width + "&height=" + height;
		//Sign the request URI
		String signedURL = Utils.sign(strURL);	
		//Convert the local file to InputStream
		InputStream fileStream = new FileInputStream(localFilePath);
		//Process the request on server
		InputStream responseStream = Utils.processCommand(signedURL, "POST", fileStream);
		//Get fileName from localFilePath
		String fileName;
		int index = localFilePath.lastIndexOf("/");
		if(index != -1) {
			fileName = localFilePath.substring(index+1);
		} else {
			fileName = localFilePath;
		}
		//Replace fileName extension with designated format 
		String[] fileNameAndItsExtensionArray = fileName.split("\\.");
		fileName = fileNameAndItsExtensionArray[0] + "." + designatedFormat;
				
		//Save the stream in response to the disk
		updatedFilePath = Utils.saveStreamToFile(responseStream, fileName);
		
		return updatedFilePath;
	}
	
	/**
	 * Crop image stored on cloud and optionally change format.
	 * @param fileName Name of Image on cloud
	 * @param x X position of start point for cropping rectangle 
	 * @param y Y position of start point for cropping rectangle
	 * @param width Width of cropping rectangle 
	 * @param height Height of cropping rectangle 
	 * @param designatedFormat Output file format
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return A path to the cropped image
	*/ 
	public static String cropImageStoredOnCloundAndChangeFormat(String fileName, int x, int y, int width, int height, String designatedFormat) throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		String localFilePath = null;
		
		if(fileName == null || fileName.length() == 0) {
			throw new IllegalArgumentException("File name cannot be null or empty");
		}
		
		if(!validFormats.contains(designatedFormat)) {
			throw new IllegalArgumentException("Valid Image Formats are bmp, png, jpg, tiff, psd, gif");
		}
		
		//Build the request URI to crop image
		String strURL = IMAGING_URI + Uri.encode(fileName) + "/crop?format=" + designatedFormat + "&x=" + x + "&y=" + y + 
									"&width=" + width + "&height=" + height;
		//Sign the request URI
		String signedURL = Utils.sign(strURL);	
		//Process the request on server
		InputStream responseStream = Utils.processCommand(signedURL, "GET");
		
		//Replace fileName extension with designated format 
		String[] fileNameAndItsExtensionArray = fileName.split("\\.");
		fileName = fileNameAndItsExtensionArray[0] + "." + designatedFormat;
				
		//Save the stream in response to the disk
		localFilePath = Utils.saveStreamToFile(responseStream, fileName);
		
		return localFilePath;
	}
	
	/**
	 * Rotate and Flip image stored on disk and optionally change format.
	 * @param localFilePath A path to Image on disk
	 * @param rotateFlipMethod RotateFlip method
	 * @param designatedFormat Output file format
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return A path to the rotated and flipped image
	*/ 
	public static String rotateFlipLocallyStoredImageAndChangeFormat(String localFilePath, String rotateFlipMethod, String designatedFormat) throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		String updatedFilePath = null;
		
		if(localFilePath == null || localFilePath.length() == 0) {
			throw new IllegalArgumentException("Local file path cannot be null or empty");
		}
		
		if(!validFormats.contains(designatedFormat)) {
			throw new IllegalArgumentException("Valid Image Formats are bmp, png, jpg, tiff, psd, gif");
		}
		
		if(!validRotateFlipMethods.contains(rotateFlipMethod)) {
			throw new IllegalArgumentException("Valid Rotate Flip methods are Rotate180FlipNone, Rotate180FlipX, " + 
					"Rotate180FlipXY, Rotate180FlipY, Rotate270FlipNone, Rotate270FlipX, Rotate270FlipXY, Rotate270FlipY, " + 
					"Rotate90FlipNone, Rotate90FlipX, Rotate90FlipXY, Rotate90FlipY, RotateNoneFlipNone, " + 
					"RotateNoneFlipX, RotateNoneFlipXY, RotateNoneFlipY");
		}
		//Build the request URI to crop image
		String strURL = IMAGING_URI + "rotateflip?format=" + designatedFormat + "&method=" + rotateFlipMethod;
		//Sign the request URI
		String signedURL = Utils.sign(strURL);	
		//Convert the local file to InputStream
		InputStream fileStream = new FileInputStream(localFilePath);
		//Process the request on server
		InputStream responseStream = Utils.processCommand(signedURL, "POST", fileStream);
		//Get fileName from localFilePath
		String fileName;
		int index = localFilePath.lastIndexOf("/");
		if(index != -1) {
			fileName = localFilePath.substring(index+1);
		} else {
			fileName = localFilePath;
		}
		//Replace fileName extension with designated format 
		String[] fileNameAndItsExtensionArray = fileName.split("\\.");
		fileName = fileNameAndItsExtensionArray[0] + "." + designatedFormat;
				
		//Save the stream in response to the disk
		updatedFilePath = Utils.saveStreamToFile(responseStream, fileName);
		
		return updatedFilePath;
	}
	
	/**
	 * Rotate and Flip image stored on cloud and optionally change format.
	 * @param fileName Name of image on cloud
	 * @param rotateFlipMethod RotateFlip method
	 * @param designatedFormat Output file format
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return A path to the rotated and flipped image
	*/ 
	public static String rotateFlipImageStoredOnCloundAndChangeFormat(String fileName, String rotateFlipMethod, String designatedFormat) throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		String localFilePath = null;
		
		if(fileName == null || fileName.length() == 0) {
			throw new IllegalArgumentException("File name cannot be null or empty");
		}
		
		if(!validFormats.contains(designatedFormat)) {
			throw new IllegalArgumentException("Valid Image Formats are bmp, png, jpg, tiff, psd, gif");
		}
		
		if(!validRotateFlipMethods.contains(rotateFlipMethod)) {
			throw new IllegalArgumentException("Valid Rotate Flip methods are Rotate180FlipNone, Rotate180FlipX, " + 
					"Rotate180FlipXY, Rotate180FlipY, Rotate270FlipNone, Rotate270FlipX, Rotate270FlipXY, Rotate270FlipY, " + 
					"Rotate90FlipNone, Rotate90FlipX, Rotate90FlipXY, Rotate90FlipY, RotateNoneFlipNone, " + 
					"RotateNoneFlipX, RotateNoneFlipXY, RotateNoneFlipY");
		}
		
		//Build the request URI to crop image
		String strURL = IMAGING_URI + Uri.encode(fileName) + "/rotateflip?format=" + designatedFormat + "&method=" + rotateFlipMethod;
		//Sign the request URI
		String signedURL = Utils.sign(strURL);	
		//Process the request on server
		InputStream responseStream = Utils.processCommand(signedURL, "GET");
		
		//Replace fileName extension with designated format 
		String[] fileNameAndItsExtensionArray = fileName.split("\\.");
		fileName = fileNameAndItsExtensionArray[0] + "." + designatedFormat;
				
		//Save the stream in response to the disk
		localFilePath = Utils.saveStreamToFile(responseStream, fileName);
		
		return localFilePath;
	}
	
	/**
	 * Change Image Format that is stored on disk
	 * @param localFilePath A path to Image on disk
	 * @param designatedFormat Output file format
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return A path to image in designated format
	*/ 
	public static String changeLocallyStoredImageFormat(String localFilePath, String designatedFormat) throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		String updatedFilePath = null;
		
		if(localFilePath == null || localFilePath.length() == 0) {
			throw new IllegalArgumentException("Local file path cannot be null or empty");
		}
		
		if(!validFormats.contains(designatedFormat)) {
			throw new IllegalArgumentException("Valid Image Formats are bmp, png, jpg, tiff, psd, gif");
		}
		
		//Build the request URI to change image format
		String strURL = IMAGING_URI + "saveAs?format=" + designatedFormat;
		//Sign the request URI
		String signedURL = Utils.sign(strURL);	
		//Convert the local file to InputStream
		InputStream fileStream = new FileInputStream(localFilePath);
		//Process the request on server
		InputStream responseStream = Utils.processCommand(signedURL, "POST", fileStream);
		//Get fileName from localFilePath
		String fileName;
		int index = localFilePath.lastIndexOf("/");
		if(index != -1) {
			fileName = localFilePath.substring(index+1);
		} else {
			fileName = localFilePath;
		}
		//Replace fileName extension with designated format 
		String[] fileNameAndItsExtensionArray = fileName.split("\\.");
		fileName = fileNameAndItsExtensionArray[0] + "." + designatedFormat;
				
		//Save the stream in response to the disk
		updatedFilePath = Utils.saveStreamToFile(responseStream, fileName);
		
		return updatedFilePath;
	}
	
	/**
	 * Change Image Format that is stored on cloud
	 * @param fileName Name of image on disk
	 * @param designatedFormat Output file format
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return A path to image in designated format
	*/ 
	public static String changeFormatOfImageStoredOnCloud(String fileName, String designatedFormat) throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		String localFilePath = null;
		
		if(fileName == null || fileName.length() == 0) {
			throw new IllegalArgumentException("File name cannot be null or empty");
		}
		
		if(!validFormats.contains(designatedFormat)) {
			throw new IllegalArgumentException("Valid Image Formats are bmp, png, jpg, tiff, psd, gif");
		}
		
		//Build the request URI to change image format
		String strURL = IMAGING_URI + Uri.encode(fileName) + "/saveAs?format=" + designatedFormat;
		//Sign the request URI
		String signedURL = Utils.sign(strURL);	
		//Process the request on server
		InputStream responseStream = Utils.processCommand(signedURL, "GET");
		
		//Replace fileName extension with designated format 
		String[] fileNameAndItsExtensionArray = fileName.split("\\.");
		fileName = fileNameAndItsExtensionArray[0] + "." + designatedFormat;
				
		//Save the stream in response to the disk
		localFilePath = Utils.saveStreamToFile(responseStream, fileName);
		
		return localFilePath;
	}
	
	/**
	 * Merge 2 TIFF files 
	 * @param srcTiffFile Name of source Tiff image
	 * @param appendTiffFile Name of Tiff image to append with
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return Whether 2 Tiff images merged successfully or not
	*/ 
	public static boolean mergeTiffImages(String srcTiffFile, String appendTiffFile) throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		boolean isTiffImagesMergedSuccessfully = false;
		
		if(srcTiffFile == null || srcTiffFile.length() == 0) {
			throw new IllegalArgumentException("Source File name cannot be null or empty");
		}
		
		if(appendTiffFile == null || appendTiffFile.length() == 0) {
			throw new IllegalArgumentException("Name of file to append cannot be null or empty");
		}
		
		//Build the request URI to change image format
		String strURL = IMAGING_URI + "tiff/" + Uri.encode(srcTiffFile) + "/appendTiff?appendFile=" + Uri.encode(appendTiffFile);
		//Sign the request URI
		String signedURL = Utils.sign(strURL);	
		//Process the request on server
		InputStream responseStream = Utils.processCommand(signedURL, "POST");
		String jsonStr = Utils.streamToString(responseStream);
		
		//Parsing JSON
		Gson gson = new Gson();
		BaseResponse baseResponse = gson.fromJson(jsonStr, BaseResponse.class);
		if(baseResponse.getCode().equals("200") && baseResponse.getStatus().equals("OK")) {
			isTiffImagesMergedSuccessfully = true;
		}
				
		return isTiffImagesMergedSuccessfully;
	}
	
	/**
	 * Converts Tiff Image to Fax Compatible Format 
	 * @param fileName Name of Image stored on Cloud
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return Path to image in Fax compatible format
	*/ 
	public static String convertTiffImageToFaxCompatibleFormat(String fileName) throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		String faxCompatibleFilePath = null;
		
		if(fileName == null || fileName.length() == 0) {
			throw new IllegalArgumentException("File name cannot be null or empty");
		}
		
		//Build the request URI to change image format
		String strURL = IMAGING_URI + "tiff/" + Uri.encode(fileName) + "/toFax";
		//Sign the request URI
		String signedURL = Utils.sign(strURL);	
		//Process the request on server
		InputStream responseStream = Utils.processCommand(signedURL, "GET");
		
		//Save the stream in response to the disk
		faxCompatibleFilePath = Utils.saveStreamToFile(responseStream, fileName);
		
		return faxCompatibleFilePath;
	}
}
