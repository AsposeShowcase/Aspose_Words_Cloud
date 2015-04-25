package com.aspose.cloud.sdk.pdf.api;

import android.net.Uri;

import com.aspose.cloud.sdk.common.AsposeApp;
import com.aspose.cloud.sdk.common.BaseResponse;
import com.aspose.cloud.sdk.common.Utils;
import com.aspose.cloud.sdk.pdf.model.ValidImageFormatsEnum;
import com.aspose.cloud.sdk.pdf.model.ImagesResponse;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * Images --- Using this class you can extract a particular image from a PDF page, replace image in a PDF file and 
 * get image count from a PDF page.
 * @author   M. Sohail Ismail
 */
public class Images {
	
	private static final String PDF_URI = AsposeApp.BASE_PRODUCT_URI + "/pdf/";
	
	/**
	 * Extract a particular image from a PDF page with default size
	 * @param fileName Name of the file on cloud
	 * @param pageIndex Index of page starting from 1
	 * @param imageIndex Index of image starting from 1
	 * @param designatedFormat Image will return in requested format
	 * @param outputFilePath Path at which extracted image will save
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return A path to the locally saved file
	*/ 
	public static String extractAParticularImageFromAPDFPageWithDefaultSize(String fileName, int pageIndex, int imageIndex, ValidImageFormatsEnum designatedFormat, String outputFilePath) throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		 
		String localFilePath = null;
		
		if(fileName == null || fileName.length() == 0) {
			throw new IllegalArgumentException("File name cannot be null or empty");
		}
		
		if(designatedFormat == null) {
			throw new IllegalArgumentException("Designated format cannot be null");
		}
		
		if(outputFilePath == null || outputFilePath.length() == 0) {
			throw new IllegalArgumentException("Output file path cannot be null or empty");
		}
		
		//build URL
		String strURL = PDF_URI + Uri.encode(fileName) + "/pages/" + pageIndex + "/images/" + imageIndex + "?format=" + designatedFormat;
		
		//sign URL
		String signedURL = Utils.sign(strURL);
		InputStream responseStream = Utils.processCommand(signedURL, "GET");
		
		//Save file on Disk
		localFilePath = Utils.saveStreamToFile(responseStream, outputFilePath);
		return localFilePath;
	}
	
	/**
	 * Extract a particular image from a PDF page with specified size
	 * @param fileName Name of the file on cloud
	 * @param pageIndex Index of page starting from 1
	 * @param imageIndex Index of image starting from 1
	 * @param width Width of the resulting image in pixels
	 * @param height Height of the resulting image in pixels
	 * @param designatedFormat Image will return in requested format
	 * @param outputFilePath Path at which extracted image will save
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return A path to the locally saved file
	*/ 
	public static String extractAParticularImageFromAPDFPageWithSpecifiedSize(String fileName, int pageIndex, 
			int imageIndex, ValidImageFormatsEnum designatedFormat, int width, int height, 
			String outputFilePath) throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		
		String localFilePath = null;
		
		if(fileName == null || fileName.length() == 0) {
			throw new IllegalArgumentException("File name cannot be null or empty");
		}
		
		if(designatedFormat == null) {
			throw new IllegalArgumentException("Designated format cannot be null");
		}
		
		if(outputFilePath == null || outputFilePath.length() == 0) {
			throw new IllegalArgumentException("Output file path cannot be null or empty");
		}
		
		//build URL
		String strURL = PDF_URI + Uri.encode(fileName) + "/pages/" + pageIndex + "/images/" + imageIndex + 
				"?format=" + designatedFormat + "&width=" + width + "&height=" + height;
		
		//sign URL
		String signedURL = Utils.sign(strURL);
		InputStream responseStream = Utils.processCommand(signedURL, "GET");
		
		//Save file on Disk
		localFilePath = Utils.saveStreamToFile(responseStream, outputFilePath);
		return localFilePath;
	}
	
	/**
	 * Replace image in a PDF file using image file
	 * @param fileName Name of the file on cloud
	 * @param pageIndex Index of page starting from 1
	 * @param imageIndex Index of image starting from 1
	 * @param imageFilePath Server side image file path
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return Boolean variable indicates whether image replaced successfully
	*/ 
	public static boolean replaceImageInAPDFFileUsingImageFile(String fileName, int pageIndex, int imageIndex, 
							String imageFilePath) throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		
		boolean isImageReplacedSuccessfully = false;
		
		if(fileName == null || fileName.length() == 0) {
			throw new IllegalArgumentException("File name cannot be null or empty");
		}
		
		if(imageFilePath == null || imageFilePath.length() == 0) {
			throw new IllegalArgumentException("Image file path cannot be null or empty");
		}
		
		//build URL
		String strURL = PDF_URI + Uri.encode(fileName) + "/pages/" + pageIndex + "/images/" + imageIndex + "?imageFile=" + Uri.encode(imageFilePath);
		
		//sign URL
		String signedURL = Utils.sign(strURL);
		InputStream responseStream = Utils.processCommand(signedURL, "POST");
		String responseJSONString = Utils.streamToString(responseStream);
		
        //Parsing JSON
		Gson gson = new Gson();
		BaseResponse baseResponse = gson.fromJson(responseJSONString, BaseResponse.class);
		if(baseResponse.getCode().equals("200") && baseResponse.getStatus().equals("OK")) {
			isImageReplacedSuccessfully = true;
		}
		
		return isImageReplacedSuccessfully;
	}
	
	/**
	 * Get image count from a PDF page
	 * @param fileName Name of the file on cloud
	 * @param pageIndex Index of page starting from 1
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return Number of images
	*/ 
	public static int getImageCountFromAPDFPage(String fileName, int pageIndex) throws InvalidKeyException, NoSuchAlgorithmException, IOException {

		int imageCount = -1;
		
		if(fileName == null || fileName.length() == 0) {
			throw new IllegalArgumentException("File name cannot be null or empty");
		}
		
		//build URL
		String strURL = PDF_URI + Uri.encode(fileName) + "/pages/" + pageIndex + "/images";
		
		//sign URL
		String signedURL = Utils.sign(strURL);
		InputStream responseStream = Utils.processCommand(signedURL, "GET");
		String responseJSONString = Utils.streamToString(responseStream);
		
		//Parsing JSON
		Gson gson = new Gson();
		ImagesResponse imageResponse = gson.fromJson(responseJSONString, ImagesResponse.class);
		if(imageResponse.getCode().equals("200") && imageResponse.getStatus().equals("OK")) {
			imageCount = imageResponse.Images.List.size();
		}
		
		return imageCount;
	}
}
