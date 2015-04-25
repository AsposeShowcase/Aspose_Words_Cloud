/**
 * 
 */
package com.aspose.cloud.sdk.slides.api;

import android.net.Uri;

import com.aspose.cloud.sdk.common.AsposeApp;
import com.aspose.cloud.sdk.common.Utils;
import com.aspose.cloud.sdk.slides.model.MergePresentationsRequest;
import com.aspose.cloud.sdk.slides.model.MergeSelectedSlidesOfPowerPointPresentationsRequest;
import com.aspose.cloud.sdk.slides.model.SplitPowerPointPresentationsResponse;
import com.aspose.cloud.sdk.slides.model.SplitPowerPointPresentationsResponse.SplitResult;
import com.aspose.cloud.sdk.slides.model.ValidSlidesFormats;
import com.aspose.cloud.sdk.slides.model.DocumentResponse;
import com.aspose.cloud.sdk.slides.model.DocumentResponse.DocumentModel;
import com.aspose.cloud.sdk.slides.model.ValidFormatsEnum;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

/**
 * Document --- Using this class you can create a new empty PowerPoint presentation, convert PowerPoint document to other File formats, 
 * merge multiple PowerPoint presentation files and split all or specific slides of a presentation file
 * @author   M. Sohail Ismail
 */
public class Document {
	
	private static final String SLIDES_URI = AsposeApp.BASE_PRODUCT_URI + "/slides/";
	public static final String TEXTCOMPRESSION_KEY = "TextCompression";
	public static final String EMBEDFULLFONTS_KEY = "EmbedFullFonts";
	public static final String COMPLIANCE_KEY = "Compliance";
	public static final String JPEGQUALITY_KEY = "JpegQuality";
	public static final String SAVEMETAFILESASPNG_KEY = "SaveMetafilesAsPng";
	public static final String PDFPASSWORD_KEY = "PdfPassword";
	public static final String EMBEDTRUETYPEFONTSFORASCII_KEY = "EmbedTrueTypeFontsForASCII";
	
	/**
	 * Create a new empty PowerPoint presentation
	 * @param fileName Name of the file stored on cloud
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return An object that contains URLs to document properties, slides and images and alternative links to download document in other formats
	*/ 
	public static DocumentModel createEmptyPowerPointPresentation(String fileName) throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		
		DocumentModel document = null;
		
		if(fileName == null || fileName.length() == 0) {
			throw new IllegalArgumentException("File name cannot be null or empty");
		}
		
		//build URL
		String strURL = SLIDES_URI + Uri.encode(fileName);
		//sign URL
		String signedURL = Utils.sign(strURL);
		InputStream responseStream = Utils.processCommand(signedURL, "PUT");
		String jsonStr = Utils.streamToString(responseStream);
		
		//Parsing JSON
		Gson gson = new Gson();
		DocumentResponse documentResponse = gson.fromJson(jsonStr, DocumentResponse.class);
		if(documentResponse.getCode().equals("201") && documentResponse.getStatus().equals("Created")) {
			document = documentResponse.document;
		}
		
		return document;
	}
	
	/**
	 * Convert PowerPoint document to other File formats 
	 * @param fileName Name of the file stored on cloud
	 * @param designatedFormat Valid formats are tiff, pdf, xps, odp, ppsx, pptm, ppsm, potx, potm and html
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return A path to converted document
	*/ 
	public static String convertPowerPointDocumentToOtherFileFormats(String fileName, ValidFormatsEnum designatedFormat) throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		
		String localFilePath = null;
		
		if(fileName == null || fileName.length() <= 3) {
			throw new IllegalArgumentException("File name cannot be null or empty");
		}
		
		if(designatedFormat == null) {
			throw new IllegalArgumentException("Designated format cannot be null");
		}
		
		//build URL
		String strURL = SLIDES_URI + Uri.encode(fileName) + "?format=" + designatedFormat;
		//sign URL
		String signedURL = Utils.sign(strURL);
		InputStream responseStream = Utils.processCommand(signedURL, "GET");
		
		//Replace fileName extension with designated format 
		String[] fileNameAndItsExtensionArray = fileName.split("\\.");
		fileName = fileNameAndItsExtensionArray[0] + "." + designatedFormat;
		
		//Save file on Disk
		localFilePath = Utils.saveStreamToFile(responseStream, fileName);
		return localFilePath;
	}
	
	/**
	 * Convert PowerPoint document to other file formats with additional settings
	 * @param fileName Name of the file stored on cloud
	 * @param designatedFormat Valid formats are tiff, pdf, xps, odp, ppsx, pptm, ppsm, potx, potm and html
	 * @param exportOptions Depends of parameter "format" service can receive export options
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return A path to converted document
	*/ 
	public static String convertPowerPointDocumentToOtherFileFormatsWithAdditionalSettings(String fileName, ValidFormatsEnum designatedFormat, HashMap<String, String> exportOptions) throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		
		String localFilePath = null;
		
		if(fileName == null || fileName.length() <= 3) {
			throw new IllegalArgumentException("File name cannot be null or empty");
		}
		
		if(designatedFormat == null) {
			throw new IllegalArgumentException("Designated format cannot be null");
		}
		
		//build URL
		StringBuilder strURL = new StringBuilder(SLIDES_URI + Uri.encode(fileName) + "?format=" + designatedFormat);
		if(exportOptions.get(TEXTCOMPRESSION_KEY) != null) {
			strURL.append("&TextCompression=" + exportOptions.get(TEXTCOMPRESSION_KEY));
		}
		if(exportOptions.get(EMBEDFULLFONTS_KEY) != null) {
			strURL.append("&EmbedFullFonts=" + exportOptions.get(EMBEDFULLFONTS_KEY));
		}
		if(exportOptions.get(COMPLIANCE_KEY) != null) {
			strURL.append("&Compliance=" + exportOptions.get(COMPLIANCE_KEY));
		}
		if(exportOptions.get(JPEGQUALITY_KEY) != null) {
			strURL.append("&JpegQuality=" + exportOptions.get(JPEGQUALITY_KEY));
		}
		if(exportOptions.get(SAVEMETAFILESASPNG_KEY) != null) {
			strURL.append("&SaveMetafilesAsPng=" + exportOptions.get(SAVEMETAFILESASPNG_KEY));
		}
		if(exportOptions.get(PDFPASSWORD_KEY) != null) {
			strURL.append("&PdfPassword=" + exportOptions.get(PDFPASSWORD_KEY));
		}
		if(exportOptions.get(EMBEDTRUETYPEFONTSFORASCII_KEY) != null) {
			strURL.append("&EmbedTrueTypeFontsForASCII=" + exportOptions.get(EMBEDTRUETYPEFONTSFORASCII_KEY));
		}
		
		//sign URL
		String signedURL = Utils.sign(strURL.toString());
		InputStream responseStream = Utils.processCommand(signedURL, "GET");
		
		//Replace fileName extension with designated format 
		String[] fileNameAndItsExtensionArray = fileName.split("\\.");
		fileName = fileNameAndItsExtensionArray[0] + "." + designatedFormat;
		
		//Save file on Disk
		localFilePath = Utils.saveStreamToFile(responseStream, fileName);
		return localFilePath;
	}

	/**
	 * Convert PowerPoint document stored on device to other file formats
	 * @param localFilePath Name of the file stored on device
	 * @param designatedFormat Valid formats are tiff, pdf, xps, odp, ppsx, pptm, ppsm, potx, potm and html
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return A path to converted document
	*/ 
	public static String convertLocallyStoredPowerPointDocumentToOtherFileFormats(String localFilePath, ValidFormatsEnum designatedFormat) throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		
		String updatedFilePath = null;
		
		if(localFilePath == null || localFilePath.length() == 0) {
			throw new IllegalArgumentException("Local file path cannot be null or empty");
		}
		
		if(designatedFormat == null) {
			throw new IllegalArgumentException("Designated format cannot be null");
		}
		
		//Build URI 
		String strURL = SLIDES_URI + "convert?format=" + designatedFormat;
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
	 * You can merge multiple PowerPoint presentation files
	 * @param fileName Name of the file stored on cloud
	 * @param mergePresentationsRequest Contains an array of PowerPoint presentations to be merged with
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return An object that contains URLs to document properties, slides and images and alternative links to download document in other formats
	*/ 
	public static DocumentModel mergePowerPointPresentations(String fileName, MergePresentationsRequest mergePresentationsRequest) throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		
		DocumentModel document = null;
		
		if(fileName == null || fileName.length() == 0) {
			throw new IllegalArgumentException("File name cannot be null or empty");
		}
		
		if(mergePresentationsRequest == null) {
			throw new IllegalArgumentException("Merge presentations request cannot be null");
		}
		
		GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        String requestJSONString = gson.toJson(mergePresentationsRequest, MergePresentationsRequest.class);
        
        //Build URI 
      	String strURL = SLIDES_URI + Uri.encode(fileName) + "/merge";
      	//Sign the request URI
      	String signedURL = Utils.sign(strURL);	
      		
        InputStream responseStream = Utils.processCommand(signedURL, "POST", requestJSONString);
        String responseJSONString = Utils.streamToString(responseStream);
		
        //Parsing JSON
		DocumentResponse documentResponse = gson.fromJson(responseJSONString, DocumentResponse.class);
		if(documentResponse.getCode().equals("200") && documentResponse.getStatus().equals("OK")) {
			document = documentResponse.document;
		}
		
		return document;
	}
	
	/**
	 * Take selected slides from multiple presentation files and merge into another presentation
	 * @param fileName Name of the file stored on cloud
	 * @param mergePresentationsRequest Contains an array of PowerPoint presentations to be merged with
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return An object that contains URLs to document properties, slides and images and alternative links to download document in other formats
	*/ 
	public static DocumentModel mergeSelectedSlidesOfPowerPointPresentations(String fileName, MergeSelectedSlidesOfPowerPointPresentationsRequest mergePresentationsRequest) throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		
		DocumentModel document = null;
		
		if(fileName == null || fileName.length() == 0) {
			throw new IllegalArgumentException("File name cannot be null or empty");
		}
		
		if(mergePresentationsRequest == null) {
			throw new IllegalArgumentException("Merge presentations request cannot be null");
		}
		
		GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        String requestJSONString = gson.toJson(mergePresentationsRequest, MergeSelectedSlidesOfPowerPointPresentationsRequest.class);
        
        //Build URI 
      	String strURL = SLIDES_URI + Uri.encode(fileName) + "/merge";
      	//Sign the request URI
      	String signedURL = Utils.sign(strURL);	
      		
        InputStream responseStream = Utils.processCommand(signedURL, "PUT", requestJSONString);
        String responseJSONString = Utils.streamToString(responseStream);
		
        //Parsing JSON
		DocumentResponse documentResponse = gson.fromJson(responseJSONString, DocumentResponse.class);
		if(documentResponse.getCode().equals("200") && documentResponse.getStatus().equals("OK")) {
			document = documentResponse.document;
		}
		
		return document;
	}
	
	/**
	 * Split all slides of a presentation file and save each slide as a new HTML or any supported image format
	 * @param fileName Name of the file stored on cloud
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return An object that contains URLs to slides
	*/ 
	public static SplitResult splitPowerPointPresentations(String fileName) throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		
		SplitResult splitResult = null;
		
		if(fileName == null || fileName.length() <= 3) {
			throw new IllegalArgumentException("File name cannot be null or empty");
		}
		
		//build URL
		String strURL = SLIDES_URI + Uri.encode(fileName) + "/split";
		//sign URL
		String signedURL = Utils.sign(strURL);
		
		InputStream responseStream = Utils.processCommand(signedURL, "POST");
		String responseJSONString = Utils.streamToString(responseStream);
		
		//Parsing JSON
		Gson gson = new Gson();
		SplitPowerPointPresentationsResponse splitPowerPointPresentationResponse = gson.fromJson(responseJSONString, SplitPowerPointPresentationsResponse.class);
		if(splitPowerPointPresentationResponse.getCode().equals("200") && splitPowerPointPresentationResponse.getStatus().equals("OK")) {
			splitResult = splitPowerPointPresentationResponse.splitResult;
		}
		
		return splitResult;
	}
	
	/**
	 * Split specific slides of a presentation file and save each slide as a new HTML or any supported image format
	 * @param fileName Name of the file stored on cloud
	 * @param from The start slide number for splitting
	 * @param to The last slide number for splitting
	 * @param designatedFormat Valid formats are tiff, jpeg, png, bmp and gif
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return An object that contains URLs to slides
	*/
	public static SplitResult splitPowerPointPresentations(String fileName, int from, int to, ValidSlidesFormats designatedFormat) throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		
		SplitResult splitResult = null;
		
		if(fileName == null || fileName.length() <= 3) {
			throw new IllegalArgumentException("File name cannot be null or empty");
		}
		
		if(designatedFormat == null) {
			throw new IllegalArgumentException("Designated format cannot be null");
		}
		
		//build URL
		String strURL = SLIDES_URI + Uri.encode(fileName) + "/split?from=" + from + "&to=" + to + "&format=" + designatedFormat;
		//sign URL
		String signedURL = Utils.sign(strURL);
		
		InputStream responseStream = Utils.processCommand(signedURL, "POST");
		String responseJSONString = Utils.streamToString(responseStream);
		
		//Parsing JSON
		Gson gson = new Gson();
		SplitPowerPointPresentationsResponse splitPowerPointPresentationResponse = gson.fromJson(responseJSONString, SplitPowerPointPresentationsResponse.class);
		if(splitPowerPointPresentationResponse.getCode().equals("200") && splitPowerPointPresentationResponse.getStatus().equals("OK")) {
			splitResult = splitPowerPointPresentationResponse.splitResult;
		}
		
		return splitResult;
	}
}