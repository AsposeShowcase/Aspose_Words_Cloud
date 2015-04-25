package com.aspose.cloud.sdk.pdf.api;

import android.net.Uri;

import com.aspose.cloud.sdk.common.AsposeApp;
import com.aspose.cloud.sdk.common.BaseResponse;
import com.aspose.cloud.sdk.common.Utils;
import com.aspose.cloud.sdk.pdf.model.DocumentResponse.DocumentData;
import com.aspose.cloud.sdk.pdf.model.MergeDocumentsRequest;
import com.aspose.cloud.sdk.pdf.model.SignatureModel;
import com.aspose.cloud.sdk.pdf.model.SplitDocumentResponse;
import com.aspose.cloud.sdk.pdf.model.SplitDocumentResponse.SplitResult;
import com.aspose.cloud.sdk.pdf.model.ValidOutputFormatsEnum;
import com.aspose.cloud.sdk.pdf.model.ValidTemplateTypeEnum;
import com.aspose.cloud.sdk.pdf.model.DocumentResponse;
import com.aspose.cloud.sdk.pdf.model.ValidFormatsForPresentationEnum;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * Document --- Using this class you can create empty PDF file, convert PDF to images, TIFF, DOC, HTML, and other formats, 
 * merge multiple PDF files, split all or specific pages of a PDF file and sign PDF documents.
 * @author   M. Sohail Ismail
 */
public class Document {

	private static final String PDF_URI = AsposeApp.BASE_PRODUCT_URI + "/pdf/";
	
	/**
	 * Create empty PDF file
	 * @param fileName Name of the file stored on cloud
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return An object that contains URLs to document properties and pages
	*/ 
	public static DocumentData createEmptyPDF(String fileName) throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		
		DocumentData document = null;
		
		if(fileName == null || fileName.length() == 0) {
			throw new IllegalArgumentException("File name cannot be null or empty");
		}
		
		//build URL
		String strURL = PDF_URI + Uri.encode(fileName);
		//sign URL
		String signedURL = Utils.sign(strURL);
		InputStream responseStream = Utils.processCommand(signedURL, "PUT");
		String jsonStr = Utils.streamToString(responseStream);
		
		//Parsing JSON
		Gson gson = new Gson();
		DocumentResponse documentResponse = gson.fromJson(jsonStr, DocumentResponse.class);
		if(documentResponse.getCode().equals("200") && documentResponse.getStatus().equals("OK")) {
			document = documentResponse.document;
		}
		
		return document;
	}
	
	/**
	 * Create PDF file from template
	 * @param fileName Name of the file stored on cloud
	 * @param templateFile The template file server path
	 * @param templateType Valid templates types are html, xml, pcl, svg, xps, jpeg and tiff 
	 * @param dataFile The data file server path, used for XML template type only 
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return An object that contains URLs to document properties and pages
	*/ 
	public static DocumentData createPDFFromTemplate(String fileName, String templateFile, ValidTemplateTypeEnum templateType, String dataFile) throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		
		DocumentData document = null;
		
		if(fileName == null || fileName.length() == 0) {
			throw new IllegalArgumentException("File name cannot be null or empty");
		}
		
		if(templateFile == null || templateFile.length() == 0) {
			throw new IllegalArgumentException("Template file cannot be null or empty");
		}
		
		if(templateType == null) {
			throw new IllegalArgumentException("Template type cannot be null");
		}
		
		//build URL
		String strURL = PDF_URI + Uri.encode(fileName) + "?templateFile=" + Uri.encode(templateFile) + "&templateType=" + templateType;
		if(dataFile != null && dataFile.length() !=0) {
			strURL += ("&dataFile=" + dataFile);
		}
		
		//sign URL
		String signedURL = Utils.sign(strURL);
		InputStream responseStream = Utils.processCommand(signedURL, "PUT");
		String jsonStr = Utils.streamToString(responseStream);
		
		//Parsing JSON
		Gson gson = new Gson();
		DocumentResponse documentResponse = gson.fromJson(jsonStr, DocumentResponse.class);
		if(documentResponse.getCode().equals("200") && documentResponse.getStatus().equals("OK")) {
			document = documentResponse.document;
		}
		
		return document;
	}
	
	/**
	 * Convert PDF to images, TIFF, DOC, HTML, and other formats
	 * @param fileName Name of the file stored on cloud
	 * @param designatedFormat Valid designated formats are tiff, pdf, pdfa1a, pdfa1b, xps, doc, tex, html 
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return Path to converted file stored on device
	*/ 
	public static String convertPDFDocumentToOtherFileFormats(String fileName, ValidFormatsForPresentationEnum designatedFormat) throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		
		String localFilePath = null;
		
		if(fileName == null || fileName.length() == 0) {
			throw new IllegalArgumentException("File name cannot be null or empty");
		}
		
		if(designatedFormat == null) {
			throw new IllegalArgumentException("Designated format cannot be null");
		}
		
		//build URL
		String strURL = PDF_URI + Uri.encode(fileName) + "?format=" + designatedFormat;
		
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
	 * Convert PDF file stored on device to images, TIFF, DOC, HTML, and other formats
	 * @param localFilePath Name of the file stored on device
	 * @param designatedFormat Valid designated formats are tiff, pdf, pdfa1a, pdfa1b, xps, doc, tex, html 
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return Path to converted file stored on device
	*/ 
	public static String convertLocallyStoredPDFDocumentToOtherFileFormats(String localFilePath, ValidFormatsForPresentationEnum designatedFormat) throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		
		String updatedFilePath = null;
		
		if(localFilePath == null || localFilePath.length() == 0) {
			throw new IllegalArgumentException("Local file path cannot be null or empty");
		}
		
		if(designatedFormat == null) {
			throw new IllegalArgumentException("Designated format cannot be null");
		}
		
		//build URL
		String strURL = PDF_URI +"/convert?format=" + designatedFormat;
		//sign URL
		String signedURL = Utils.sign(strURL);
		//Convert the local file to InputStream
		InputStream fileStream = new FileInputStream(localFilePath);
		//Process the request on server
		InputStream responseStream = Utils.processCommand(signedURL, "PUT", fileStream);
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
	 * Convert PDF documents uploaded at a remote server to other file formats
	 * @param url URL where input PDF is present
	 * @param designatedFormat Valid designated formats are tiff, pdf, pdfa1a, pdfa1b, xps, doc, tex, html 
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return Path to converted file stored on device
	*/ 
	public static String convertPDFFromRemoteServerToOtherFileFormats(String url, ValidFormatsForPresentationEnum designatedFormat, String outputPath) throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		
		String localFilePath = null;
		
		if(url == null || url.length() == 0) {
			throw new IllegalArgumentException("URL cannot be null or empty");
		}
		
		if(designatedFormat == null) {
			throw new IllegalArgumentException("Designated format cannot be null");
		}
		
		if(outputPath == null || outputPath.length() == 0) {
			throw new IllegalArgumentException("outputPath cannot be null or empty");
		}
		
		//build URL
		String strURL = PDF_URI + "convert?url=" + url + "&format=" + designatedFormat;
		
		//sign URL
		String signedURL = Utils.sign(strURL);
		InputStream responseStream = Utils.processCommand(signedURL, "PUT");
		
		//Save file on Disk
		localFilePath = Utils.saveStreamToFile(responseStream, outputPath);
		return localFilePath;
	}
	
	/**
	 * Merge multiple PDF files
	 * @param fileName Name of the file stored on cloud
	 * @param mergeDocumentsRequest An object that contains list of PDF files to be merged with 
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return An object that contains URLs to document properties and pages
	*/
	public static DocumentData mergeMultiplePDFFiles(String fileName, MergeDocumentsRequest mergeDocumentsRequest) throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		
		DocumentData document = null;
		
		if(fileName == null || fileName.length() == 0) {
			throw new IllegalArgumentException("File name cannot be null or empty");
		}
		
		if(mergeDocumentsRequest == null) {
			throw new IllegalArgumentException("Merge documents request cannot be null");
		}
		
		GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        String requestJSONString = gson.toJson(mergeDocumentsRequest, MergeDocumentsRequest.class);
        
        //Build URI 
      	String strURL = PDF_URI + Uri.encode(fileName) + "/merge";
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
	 * Split all pages of a PDF file
	 * @param fileName Name of the file stored on cloud
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return An object that contains URLs to splitted pages
	*/
	public static SplitResult splitAllPagesOfAPDFFile(String fileName) throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		
		SplitResult result = null;
		
		if(fileName == null || fileName.length() <= 3) {
			throw new IllegalArgumentException("File name cannot be null or empty");
		}
		
		//build URL
		String strURL = PDF_URI + Uri.encode(fileName) + "/split";
		//sign URL
		String signedURL = Utils.sign(strURL);
		
		InputStream responseStream = Utils.processCommand(signedURL, "POST");
		String responseJSONString = Utils.streamToString(responseStream);
		
		//Parsing JSON
		Gson gson = new Gson();
		SplitDocumentResponse splitDocumentResponse = gson.fromJson(responseJSONString, SplitDocumentResponse.class);
		if(splitDocumentResponse.getCode().equals("200") && splitDocumentResponse.getStatus().equals("OK")) {
			result = splitDocumentResponse.result;
		}
		
		return result;
	}
	
	/**
	 * Split specific pages of a PDF file
	 * @param fileName Name of the file stored on cloud
	 * @param from The start page number for splitting
	 * @param to The last page number for splitting
	 * @param designatedFormat Valid formats are pdf, pdfa1a, pdfa1b, xps, doc,	tiff, jpeg, png, emf, bmp, gif 	
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return An object that contains URLs to splitted pages
	*/
	public static SplitResult splitSpecificPagesOfAPDFFile(String fileName, int from, int to, ValidOutputFormatsEnum designatedFormat) throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		
		SplitResult result = null;
		
		if(fileName == null || fileName.length() <= 3) {
			throw new IllegalArgumentException("File name cannot be null or empty");
		}
		
		//build URL
		String strURL = designatedFormat != null? PDF_URI + Uri.encode(fileName) + "/split?from=" + from + "&to=" + to + "&format=" + designatedFormat :
			PDF_URI + Uri.encode(fileName) + "/split?from=" + from + "&to=" + to;
		
		//sign URL
		String signedURL = Utils.sign(strURL);
		
		InputStream responseStream = Utils.processCommand(signedURL, "POST");
		String responseJSONString = Utils.streamToString(responseStream);
		
		//Parsing JSON
		Gson gson = new Gson();
		SplitDocumentResponse splitDocumentResponse = gson.fromJson(responseJSONString, SplitDocumentResponse.class);
		if(splitDocumentResponse.getCode().equals("200") && splitDocumentResponse.getStatus().equals("OK")) {
			result = splitDocumentResponse.result;
		}
		
		return result;
	}
	
	/**
	 * Sign PDF documents
	 * @param fileName Name of the file stored on cloud
	 * @param signature An object that contains signature details like signature path, signature type, password, appearance etc	
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return Boolean variable indicated whether PDF document signed successfully
	*/
	public static boolean signPDFDocuments(String fileName, SignatureModel signature) throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		
		boolean isPDFSignedSuccessfully = false;
		
		if(fileName == null || fileName.length() <= 3) {
			throw new IllegalArgumentException("File name cannot be null or empty");
		}
		
		GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        String requestJSONString = gson.toJson(signature, SignatureModel.class);
        
		//build URL
		String strURL = PDF_URI + Uri.encode(fileName) + "/sign";
		
		//sign URL
		String signedURL = Utils.sign(strURL);
		
		InputStream responseStream = Utils.processCommand(signedURL, "POST", requestJSONString);
        String responseJSONString = Utils.streamToString(responseStream);
		
        //Parsing JSON
		BaseResponse baseResponse = gson.fromJson(responseJSONString, BaseResponse.class);
		if(baseResponse.getCode().equals("200") && baseResponse.getStatus().equals("OK")) {
			isPDFSignedSuccessfully = true;
		}
		
		return isPDFSignedSuccessfully;
	}
	
	/**
	 * Append PDF documents
	 * @param fileName Name of the file stored on cloud
	 * @param startPage The first page to append
	 * @param endPage The last page to append
	 * @param appendFile The file to append server path
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return An object that contains URLs to document properties and pages
	*/
	public static DocumentData appendPDFFiles(String fileName, int startPage, int endPage, String appendFile) throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		
		DocumentData document = null;
		
		if(fileName == null || fileName.length() <= 3) {
			throw new IllegalArgumentException("File name cannot be null or empty");
		}
		
		if(appendFile == null || appendFile.length() <= 3) {
			throw new IllegalArgumentException("Append file cannot be null or empty");
		}
        
		//build URL
		String strURL = PDF_URI + Uri.encode(fileName) + "/appendDocument?appendFile=" + Uri.encode(appendFile) + "&startPage=" + startPage + "&endPage=" + endPage;
		//sign URL
		String signedURL = Utils.sign(strURL);
		
		InputStream responseStream = Utils.processCommand(signedURL, "POST");
        String responseJSONString = Utils.streamToString(responseStream);
		
        //Parsing JSON
        Gson gson = new Gson();
        DocumentResponse documentResponse = gson.fromJson(responseJSONString, DocumentResponse.class);
		if(documentResponse.getCode().equals("200") && documentResponse.getStatus().equals("OK")) {
			document = documentResponse.document;
		}
		
		return document;
	}
}
