package com.aspose.cloud.sdk.slides.api;

import android.net.Uri;

import com.aspose.cloud.sdk.common.AsposeApp;
import com.aspose.cloud.sdk.common.BaseResponse;
import com.aspose.cloud.sdk.common.Utils;
import com.aspose.cloud.sdk.slides.model.ColorSchemeResponse;
import com.aspose.cloud.sdk.slides.model.ColorSchemeResponse.ColorScheme;
import com.aspose.cloud.sdk.slides.model.FontSchemeResponse;
import com.aspose.cloud.sdk.slides.model.FontSchemeResponse.FontScheme;
import com.aspose.cloud.sdk.slides.model.PlaceholderResponse;
import com.aspose.cloud.sdk.slides.model.PlaceholderResponse.PlaceholderResult;
import com.aspose.cloud.sdk.slides.model.PowerPointSlideBackgroundResponse;
import com.aspose.cloud.sdk.slides.model.PowerPointSlideBackgroundResponse.BackgroundResult;
import com.aspose.cloud.sdk.slides.model.SlideCommentsResponse;
import com.aspose.cloud.sdk.slides.model.SlideCommentsResponse.SlideComments;
import com.aspose.cloud.sdk.slides.model.SlidesResponse;
import com.aspose.cloud.sdk.slides.model.SlidesResponse.SlidesResult;
import com.aspose.cloud.sdk.slides.model.ValidSlidesFormats;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * Slides --- Using this class you can save a PowerPoint slide as image, add a new slide in a PowerPoint presentation, copy slides in a PowerPoint presentation, 
 * change position of slides in a PowerPoint presentation, delete slides from a PowerPoint presentation, delete background of a PowerPoint slide, 
 * get slide count of a PowerPoint document, get Placeholder from a PowerPoint slide, get Font and Color scheme of a PowerPoint slide, 
 * get background information of a PowerPoint slide and get comments of a PowerPoint slide
 * @author   M. Sohail Ismail
 */
public class Slides {
	private static final String SLIDES_URI = AsposeApp.BASE_PRODUCT_URI + "/slides/";
	
	/**
	 * Save a PowerPoint slide as image with default size
	 * @param fileName Name of the file stored on cloud
	 * @param slideIndex Index of slide starting from 1
	 * @param designatedFormat Valid formats are tiff, jpeg, png, bmp and gif
	 * @param outputSlideName An image will save on device with this name
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return Path to file stored on device
	*/ 
	public static String saveAPowerPointSlideAsImageWithDefaultSize(String fileName, int slideIndex, ValidSlidesFormats designatedFormat, String outputSlideName) throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		
		String localFilePath = null;
		
		if(fileName == null || fileName.length() == 0) {
			throw new IllegalArgumentException("File name cannot be null or empty");
		}
		
		if(designatedFormat == null) {
			throw new IllegalArgumentException("Designated format cannot be null");
		}
		
		if(outputSlideName == null || outputSlideName.length() == 0) {
			throw new IllegalArgumentException("Output slide name cannot be null or empty");
		}
		
		//build URL
		String strURL = SLIDES_URI + Uri.encode(fileName) + "/slides/" + slideIndex + "?format=" + designatedFormat;
		//sign URL
		String signedURL = Utils.sign(strURL);
		InputStream responseStream = Utils.processCommand(signedURL, "GET");
		//Save file on Disk
		localFilePath = Utils.saveStreamToFile(responseStream, outputSlideName);
		return localFilePath;
	}
	
	/**
	 * Save a PowerPoint slide as image with specified size
	 * @param fileName Name of the file stored on cloud
	 * @param slideIndex Index of slide starting from 1
	 * @param designatedFormat Valid formats are tiff, jpeg, png, bmp and gif
	 * @param width Width of resulting image in pixels
	 * @param height Height of resulting image in pixels
	 * @param outputSlideName An image will save on device with this name
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return Path to file stored on device
	*/ 
	public static String saveAPowerPointSlideAsImageWithSpecifiedSize(String fileName, int slideIndex, 
			ValidSlidesFormats designatedFormat, int width, int height, String outputSlideName) throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		
		String localFilePath = null;
		
		if(fileName == null || fileName.length() == 0) {
			throw new IllegalArgumentException("File name cannot be null or empty");
		}
		
		if(designatedFormat == null) {
			throw new IllegalArgumentException("Designated format cannot be null");
		}
		
		if(outputSlideName == null || outputSlideName.length() == 0) {
			throw new IllegalArgumentException("Output slide name cannot be null or empty");
		}
		
		//build URL
		String strURL = SLIDES_URI + Uri.encode(fileName) + "/slides/" + slideIndex + "?format=" + designatedFormat +
				"&width=" + width + "&height=" + height;
		//sign URL
		String signedURL = Utils.sign(strURL);
		InputStream responseStream = Utils.processCommand(signedURL, "GET");
		//Save file on Disk
		localFilePath = Utils.saveStreamToFile(responseStream, outputSlideName);
		return localFilePath;
	}
	
	/**
	 * Add a new slide in a PowerPoint presentation
	 * @param fileName Name of the file stored on cloud
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return An object that contains URLs to slides and alternative links to download document in other file formats
	*/ 
	public static SlidesResult addANewSlideInAPowerPointPresentation(String fileName) throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		
		SlidesResult slides = null;
		
		if(fileName == null || fileName.length() == 0) {
			throw new IllegalArgumentException("File name cannot be null or empty");
		}
		
		//build URL
		String strURL = SLIDES_URI + Uri.encode(fileName) + "/slides";
		//sign URL
		String signedURL = Utils.sign(strURL);
		InputStream responseStream = Utils.processCommand(signedURL, "POST");
		String responseJSONString = Utils.streamToString(responseStream);
		
		//Parsing JSON
		Gson gson = new Gson();
		SlidesResponse slidesResponse = gson.fromJson(responseJSONString, SlidesResponse.class);
		if(slidesResponse.getCode().equals("200") && slidesResponse.getStatus().equals("OK")) {
			slides = slidesResponse.slides;
		}
		
		return slides;
	}
	
	/**
	 * Add a new slide at a specified position in a PowerPoint presentation
	 * @param fileName Name of the file stored on cloud
	 * @param position Specified position where slide will be added. If position is greater then number of slides then slide will be added to the end of presentation
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return An object that contains URLs to slides and alternative links to download document in other file formats
	*/ 
	public static SlidesResult addANewSlideToASpecifiedPositionInAPowerPointPresentation(String fileName, int position) throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		
		SlidesResult slides = null;
		
		if(fileName == null || fileName.length() == 0) {
			throw new IllegalArgumentException("File name cannot be null or empty");
		}
		
		//build URL
		String strURL = SLIDES_URI + Uri.encode(fileName) + "/slides?Position=" + position;
		//sign URL
		String signedURL = Utils.sign(strURL);
		InputStream responseStream = Utils.processCommand(signedURL, "POST");
		String responseJSONString = Utils.streamToString(responseStream);
		
		//Parsing JSON
		Gson gson = new Gson();
		SlidesResponse slidesResponse = gson.fromJson(responseJSONString, SlidesResponse.class);
		if(slidesResponse.getCode().equals("200") && slidesResponse.getStatus().equals("OK")) {
			slides = slidesResponse.slides;
		}
		
		return slides;
	}
	
	/**
	 * Copy slides in a PowerPoint presentation and add at a specified position
	 * @param fileName Name of the file stored on cloud
	 * @param position Specified position where slide will be added. If position is greater then number of slides then slide will be added to the end of presentation
	 * @param slideToClone Index of slide to be copied
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return An object that contains URLs to slides and alternative links to download document in other file formats
	*/ 
	public static SlidesResult copySlidesInAPowerPointPresentation(String fileName, int position, int slideToClone) throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		
		SlidesResult slides = null;
		
		if(fileName == null || fileName.length() == 0) {
			throw new IllegalArgumentException("File name cannot be null or empty");
		}
		
		//build URL
		String strURL = SLIDES_URI + Uri.encode(fileName) + "/slides?Position=" + position + "&SlideToClone=" + slideToClone;
		//sign URL
		String signedURL = Utils.sign(strURL);
		InputStream responseStream = Utils.processCommand(signedURL, "POST");
		String responseJSONString = Utils.streamToString(responseStream);
		
		//Parsing JSON
		Gson gson = new Gson();
		SlidesResponse slidesResponse = gson.fromJson(responseJSONString, SlidesResponse.class);
		if(slidesResponse.getCode().equals("200") && slidesResponse.getStatus().equals("OK")) {
			slides = slidesResponse.slides;
		}
		
		return slides;
	}
	
	/**
	 * Change position of slides in a PowerPoint presentation
	 * @param fileName Name of the file stored on cloud
	 * @param oldPosition Slide old position
	 * @param newPosition Slide new position
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return An object that contains URLs to slides and alternative links to download document in other file formats
	*/ 
	public static SlidesResult changePositionOfSlidesInAPowerPointPresentation(String fileName, int oldPosition, int newPosition) throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		
		SlidesResult slides = null;
		
		if(fileName == null || fileName.length() == 0) {
			throw new IllegalArgumentException("File name cannot be null or empty");
		}
		
		//build URL
		String strURL = SLIDES_URI + Uri.encode(fileName) + "/slides?OldPosition=" + oldPosition + "&NewPosition=" + newPosition;
		//sign URL
		String signedURL = Utils.sign(strURL);
		InputStream responseStream = Utils.processCommand(signedURL, "POST");
		String responseJSONString = Utils.streamToString(responseStream);
		
		//Parsing JSON
		Gson gson = new Gson();
		SlidesResponse slidesResponse = gson.fromJson(responseJSONString, SlidesResponse.class);
		if(slidesResponse.getCode().equals("200") && slidesResponse.getStatus().equals("OK")) {
			slides = slidesResponse.slides;
		}
		
		return slides;
	}
	
	/**
	 * Delete all slides from a PowerPoint presentation
	 * @param fileName Name of the file stored on cloud
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return boolean variable indicates whether all slides deleted successfully 
	*/ 
	public static boolean deleteAllSlidesFromAPowerPointPresentation(String fileName) throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		
		boolean isAllSlidesDeletedSuccessfully = false;
		
		if(fileName == null || fileName.length() == 0) {
			throw new IllegalArgumentException("File name cannot be null or empty");
		}
		
		//build URL
		String strURL = SLIDES_URI + Uri.encode(fileName) + "/slides";
		//sign URL
		String signedURL = Utils.sign(strURL);
		InputStream responseStream = Utils.processCommand(signedURL, "DELETE");
		String responseJSONString = Utils.streamToString(responseStream);
		
		//Parsing JSON
		Gson gson = new Gson();
		BaseResponse baseResponse = gson.fromJson(responseJSONString, BaseResponse.class);
		if(baseResponse.getCode().equals("200") && baseResponse.getStatus().equals("OK")) {
			isAllSlidesDeletedSuccessfully = true;
		}
		
		return isAllSlidesDeletedSuccessfully;
	}
	
	/**
	 * Delete a specific slide from a PowerPoint presentation
	 * @param fileName Name of the file stored on cloud
	 * @param slideIndex Index of slide to be deleted
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return boolean variable indicates whether a slide deleted successfully 
	*/ 
	public static boolean deleteASlideFromAPowerPointPresentation(String fileName, int slideIndex) throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		
		boolean isASpecificSlidesDeletedSuccessfully = false;
		
		if(fileName == null || fileName.length() == 0) {
			throw new IllegalArgumentException("File name cannot be null or empty");
		}
		
		//build URL
		String strURL = SLIDES_URI + Uri.encode(fileName) + "/slides/" + slideIndex;
		//sign URL
		String signedURL = Utils.sign(strURL);
		InputStream responseStream = Utils.processCommand(signedURL, "DELETE");
		String responseJSONString = Utils.streamToString(responseStream);
		
		//Parsing JSON
		Gson gson = new Gson();
		BaseResponse baseResponse = gson.fromJson(responseJSONString, BaseResponse.class);
		if(baseResponse.getCode().equals("200") && baseResponse.getStatus().equals("OK")) {
			isASpecificSlidesDeletedSuccessfully = true;
		}
		
		return isASpecificSlidesDeletedSuccessfully;
	}
	
	/**
	 * Delete background of a particular PowerPoint slide
	 * @param fileName Name of the file stored on cloud
	 * @param slideIndex Index of slide whose background will be removed
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return boolean variable indicates whether background of slide deleted successfully 
	*/ 
	public static boolean deleteBackgroundOfAPowerPointSlide(String fileName, int slideIndex) throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		
		boolean isSlideBackgroundDeletedSuccessfully = false;
		
		if(fileName == null || fileName.length() == 0) {
			throw new IllegalArgumentException("File name cannot be null or empty");
		}
		
		//build URL
		String strURL = SLIDES_URI + Uri.encode(fileName) + "/slides/" + slideIndex + "/background";
		//sign URL
		String signedURL = Utils.sign(strURL);
		InputStream responseStream = Utils.processCommand(signedURL, "DELETE");
		String responseJSONString = Utils.streamToString(responseStream);
		
		//Parsing JSON
		Gson gson = new Gson();
		BaseResponse baseResponse = gson.fromJson(responseJSONString, BaseResponse.class);
		if(baseResponse.getCode().equals("200") && baseResponse.getStatus().equals("OK")) {
			isSlideBackgroundDeletedSuccessfully = true;
		}
		
		return isSlideBackgroundDeletedSuccessfully;
	}
	
	/**
	 * Get slide count of a PowerPoint document
	 * @param fileName Name of the file stored on cloud
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return Slide count 
	*/ 
	public static int getPowerPointDocumentSlideCount(String fileName) throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		
		int slideCount = -1;
		
		if(fileName == null || fileName.length() == 0) {
			throw new IllegalArgumentException("File name cannot be null or empty");
		}
		
		//build URL
		String strURL = SLIDES_URI + Uri.encode(fileName) + "/slides";
		//sign URL
		String signedURL = Utils.sign(strURL);
		InputStream responseStream = Utils.processCommand(signedURL, "GET");
		String responseJSONString = Utils.streamToString(responseStream);
		
		//Parsing JSON
		Gson gson = new Gson();
		SlidesResponse slidesResponse = gson.fromJson(responseJSONString, SlidesResponse.class);
		if(slidesResponse.getCode().equals("200") && slidesResponse.getStatus().equals("OK")) {
			slideCount = slidesResponse.slides.slideList.size();
		}
		
		return slideCount;
	}
	
	/**
	 * Get Placeholder from a PowerPoint slide
	 * @param fileName Name of the file stored on cloud
	 * @param slideIndex Index of slide starting from 1
	 * @param placeholderIndex Index of placeholder starting from 1
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return An object that contains placeholder details 
	*/ 
	public static PlaceholderResult getPlaceholderFromAPowerPointSlide(String fileName, int slideIndex, int placeholderIndex) throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		
		PlaceholderResult placeholder = null;
		
		if(fileName == null || fileName.length() == 0) {
			throw new IllegalArgumentException("File name cannot be null or empty");
		}
		
		//build URL
		String strURL = SLIDES_URI + Uri.encode(fileName) + "/slides/" + slideIndex + "/placeholders/" + placeholderIndex;
		//sign URL
		String signedURL = Utils.sign(strURL);
		InputStream responseStream = Utils.processCommand(signedURL, "GET");
		String responseJSONString = Utils.streamToString(responseStream);
		
		//Parsing JSON
		Gson gson = new Gson();
		PlaceholderResponse placeholderResponse = gson.fromJson(responseJSONString, PlaceholderResponse.class);
		if(placeholderResponse.getCode().equals("200") && placeholderResponse.getStatus().equals("OK")) {
			placeholder = placeholderResponse.placeholder;
		}
		
		return placeholder;
	}
	
	/**
	 * Get Placeholder count from a PowerPoint slide
	 * @param fileName Name of the file stored on cloud
	 * @param slideIndex Index of slide starting from 1
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return Placeholder count
	*/ 
	public static int getPlaceholderCountFromAPowerPointSlide(String fileName, int slideIndex) throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		
		int placeholdersCount = -1;
		
		if(fileName == null || fileName.length() == 0) {
			throw new IllegalArgumentException("File name cannot be null or empty");
		}
		
		//build URL
		String strURL = SLIDES_URI + Uri.encode(fileName) + "/slides/" + slideIndex + "/placeholders";
		//sign URL
		String signedURL = Utils.sign(strURL);
		InputStream responseStream = Utils.processCommand(signedURL, "GET");
		String responseJSONString = Utils.streamToString(responseStream);
		
		//Parsing JSON
		Gson gson = new Gson();
		PlaceholderResponse placeholderResponse = gson.fromJson(responseJSONString, PlaceholderResponse.class);
		if(placeholderResponse.getCode().equals("200") && placeholderResponse.getStatus().equals("OK")) {
			placeholdersCount = placeholderResponse.placeholder._links.size();
		}
		
		return placeholdersCount;
	}
	
	/**
	 * Get Font scheme of a PowerPoint slide
	 * @param fileName Name of the file stored on cloud
	 * @param slideIndex Index of slide starting from 1
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return An object that represents font scheme
	*/ 
	public static FontScheme getFontSchemeOfAPowerPointSlide(String fileName, int slideIndex) throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		
		FontScheme fontScheme = null;
		
		if(fileName == null || fileName.length() == 0) {
			throw new IllegalArgumentException("File name cannot be null or empty");
		}
		
		//build URL
		String strURL = SLIDES_URI + Uri.encode(fileName) + "/slides/" + slideIndex + "/theme/fontScheme";
		//sign URL
		String signedURL = Utils.sign(strURL);
		InputStream responseStream = Utils.processCommand(signedURL, "GET");
		String responseJSONString = Utils.streamToString(responseStream);
		
		//Parsing JSON
		Gson gson = new Gson();
		FontSchemeResponse fontSchemeResponse = gson.fromJson(responseJSONString, FontSchemeResponse.class);
		if(fontSchemeResponse.getCode().equals("200") && fontSchemeResponse.getStatus().equals("OK")) {
			fontScheme = fontSchemeResponse.fontScheme;
		}
		
		return fontScheme;
	}
	
	/**
	 * Get color scheme of a PowerPoint slide
	 * @param fileName Name of the file stored on cloud
	 * @param slideIndex Index of slide starting from 1
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return An object that represents color scheme
	*/ 
	public static ColorScheme getColorSchemeOfAPowerPointSlide(String fileName, int slideIndex) throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		
		ColorScheme colorScheme = null;
		
		if(fileName == null || fileName.length() == 0) {
			throw new IllegalArgumentException("File name cannot be null or empty");
		}
		
		//build URL
		String strURL = SLIDES_URI + Uri.encode(fileName) + "/slides/" + slideIndex + "/theme/colorScheme";
		//sign URL
		String signedURL = Utils.sign(strURL);
		InputStream responseStream = Utils.processCommand(signedURL, "GET");
		String responseJSONString = Utils.streamToString(responseStream);
		
		//Parsing JSON
		Gson gson = new Gson();
		ColorSchemeResponse colorSchemeResponse = gson.fromJson(responseJSONString, ColorSchemeResponse.class);
		if(colorSchemeResponse.getCode().equals("200") && colorSchemeResponse.getStatus().equals("OK")) {
			colorScheme = colorSchemeResponse.colorScheme;
		}
		
		return colorScheme;
	}
	
	/**
	 * Get Background information of a PowerPoint Slide
	 * @param fileName Name of the file stored on cloud
	 * @param slideIndex Index of slide starting from 1
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return An object that represents slide's background
	*/ 
	public static BackgroundResult getBackgroundOfAPowerPointSlide(String fileName, int slideIndex) throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		
		BackgroundResult background = null;
		
		if(fileName == null || fileName.length() == 0) {
			throw new IllegalArgumentException("File name cannot be null or empty");
		}
		
		//build URL
		String strURL = SLIDES_URI + Uri.encode(fileName) + "/slides/" + slideIndex + "/background";
		//sign URL
		String signedURL = Utils.sign(strURL);
		InputStream responseStream = Utils.processCommand(signedURL, "GET");
		String responseJSONString = Utils.streamToString(responseStream);
		
		//Parsing JSON
		Gson gson = new Gson();
		PowerPointSlideBackgroundResponse powerPointSlideBackgroundResponse = gson.fromJson(responseJSONString, PowerPointSlideBackgroundResponse.class);
		if(powerPointSlideBackgroundResponse.getCode().equals("200") && powerPointSlideBackgroundResponse.getStatus().equals("OK")) {
			background = powerPointSlideBackgroundResponse.background;
		}
		
		return background;
	}
	
	/**
	 * Get comments of a PowerPoint slide
	 * @param fileName Name of the file stored on cloud
	 * @param slideIndex Index of slide starting from 1
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return An object that contains a list of slide comments
	*/ 
	public static SlideComments getCommentsOfAPowerPointSlide(String fileName, int slideIndex) throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		
		SlideComments slideComments = null;
		
		if(fileName == null || fileName.length() == 0) {
			throw new IllegalArgumentException("File name cannot be null or empty");
		}
		
		//build URL
		String strURL = SLIDES_URI + Uri.encode(fileName) + "/slides/" + slideIndex + "/comments";
		//sign URL
		String signedURL = Utils.sign(strURL);
		InputStream responseStream = Utils.processCommand(signedURL, "GET");
		String responseJSONString = Utils.streamToString(responseStream);
		
		//Parsing JSON
		Gson gson = new Gson();
		SlideCommentsResponse slideCommentsResponse = gson.fromJson(responseJSONString, SlideCommentsResponse.class);
		if(slideCommentsResponse.getCode().equals("200") && slideCommentsResponse.getStatus().equals("OK")) {
			slideComments = slideCommentsResponse.slideComments;
		}
		
		return slideComments;
	}
}
