package com.aspose.cloud.sdk.slides.api;

import android.net.Uri;

import com.aspose.cloud.sdk.common.AsposeApp;
import com.aspose.cloud.sdk.common.Utils;
import com.aspose.cloud.sdk.slides.model.ShapeResponse;
import com.aspose.cloud.sdk.slides.model.ShapeResponse.ShapeData;
import com.aspose.cloud.sdk.slides.model.ShapesResponse;
import com.aspose.cloud.sdk.slides.model.ShapesResponse.ShapeList;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * Shapes --- Using this class you can extract all shapes or a particular shape from a slide
 * @author   M. Sohail Ismail
 */
public class Shapes {
	
	private static final String SLIDES_URI = AsposeApp.BASE_PRODUCT_URI + "/slides/";
	
	/**
	 * Extract shapes from a particular slide
	 * @param fileName Name of the file stored on cloud
	 * @param slideIndex Index of slide starting from 1
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return An object that contains URLs to shapes
	*/ 
	public static ShapeList extractShapesFromASlide(String fileName, int slideIndex) throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		
		ShapeList shapeList = null;
		
		if(fileName == null || fileName.length() == 0) {
			throw new IllegalArgumentException("File name cannot be null or empty");
		}
		
		//build URL
		String strURL = SLIDES_URI + Uri.encode(fileName) + "/slides/" + slideIndex +  "/shapes";
		//sign URL
		String signedURL = Utils.sign(strURL);
		InputStream responseStream = Utils.processCommand(signedURL, "GET");
		String responseJSONString = Utils.streamToString(responseStream);
		
		//Parsing JSON
		Gson gson = new Gson();
		ShapesResponse shapesResponse = gson.fromJson(responseJSONString, ShapesResponse.class);
		if(shapesResponse.getCode().equals("200") && shapesResponse.getStatus().equals("OK")) {
			shapeList = shapesResponse.shapeList;
		}
		
		return shapeList;
	}
	
	/**
	 * Get a particular shape from the slide 
	 * @param fileName Name of the file stored on cloud
	 * @param slideIndex Index of slide starting from 1
	 * @param shapeIndex Index of shape starting from 1
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return An object that contains shapes data
	*/ 
	public static ShapeData getAParticularShapeFromTheSlide(String fileName, int slideIndex, int shapeIndex) throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		
		ShapeData shape = null;
		
		if(fileName == null || fileName.length() == 0) {
			throw new IllegalArgumentException("File name cannot be null or empty");
		}
		
		//build URL
		String strURL = SLIDES_URI + Uri.encode(fileName) + "/slides/" + slideIndex +  "/shapes/" + shapeIndex;
		//sign URL
		String signedURL = Utils.sign(strURL);
		InputStream responseStream = Utils.processCommand(signedURL, "GET");
		String responseJSONString = Utils.streamToString(responseStream);
		
		//Parsing JSON
		Gson gson = new Gson();
		ShapeResponse shapeResponse = gson.fromJson(responseJSONString, ShapeResponse.class);
		if(shapeResponse.getCode().equals("200") && shapeResponse.getStatus().equals("OK")) {
			shape = shapeResponse.shape;
		}
		
		return shape;
	}
}
