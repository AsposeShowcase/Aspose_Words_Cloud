package com.aspose.cloud.sdk.pdf.api;

import android.net.Uri;

import com.aspose.cloud.sdk.common.AsposeApp;
import com.aspose.cloud.sdk.common.Utils;
import com.aspose.cloud.sdk.pdf.model.AttachmentDetails;
import com.aspose.cloud.sdk.pdf.model.AttachmentResponse;
import com.aspose.cloud.sdk.pdf.model.AttachmentsResponse;
import com.aspose.cloud.sdk.pdf.model.AttachmentsResponse.AttachmentsResult;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * Attachments --- Using this class you can get all or a specific attachment, download a specific attachment and 
 * attachments count. 
 * @author   M. Sohail Ismail
 */
public class Attachments {
	
	private static final String PDF_URI = AsposeApp.BASE_PRODUCT_URI + "/pdf/";
	
	/**
	 * Get all attachments from a PDF
	 * @param fileName Name of the file on cloud
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return An object that contains details of all attachments  
	*/ 
	public static AttachmentsResult getAllAttachmentsFromAPDF(String fileName) throws InvalidKeyException, NoSuchAlgorithmException, IOException {

		AttachmentsResult attachments = null;
		
		if(fileName == null || fileName.length() == 0) {
			throw new IllegalArgumentException("File name cannot be null or empty");
		}
		
		//build URL
		String strURL = PDF_URI + Uri.encode(fileName) + "/attachments";
		
		//sign URL
		String signedURL = Utils.sign(strURL);
		InputStream responseStream = Utils.processCommand(signedURL, "GET");
		String responseJSONString = Utils.streamToString(responseStream);
		
		//Parsing JSON
		Gson gson = new Gson();
		AttachmentsResponse attachmentsResponse = gson.fromJson(responseJSONString, AttachmentsResponse.class);
		if(attachmentsResponse.getCode().equals("200") && attachmentsResponse.getStatus().equals("OK")) {
			attachments = attachmentsResponse.attachments;
		}
		
		return attachments;
	}
	
	/**
	 * Download a specific attachment from a PDF
	 * @param fileName Name of the file on cloud
	 * @param attachmentIndex Index of attachment starting from 1
	 * @param outputFilePath Downloaded attachment will be saved at this path
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return Path to downloaded attachment 
	*/ 
	public static String downloadASpecificAttachmentFromAPDF(String fileName, int attachmentIndex, String outputFilePath) throws InvalidKeyException, NoSuchAlgorithmException, IOException {

		String localFilePath = null;
		
		//Get attachment name
		AttachmentDetails attachment = getASpecificAttachmentFromAPDF(fileName, attachmentIndex);
		
		if(fileName == null || fileName.length() == 0) {
			throw new IllegalArgumentException("File name cannot be null or empty");
		}
		
		//build URL
		String strURL = PDF_URI + Uri.encode(fileName) + "/attachments/" + attachmentIndex + "/download";
		
		//sign URL
		String signedURL = Utils.sign(strURL);
		InputStream responseStream = Utils.processCommand(signedURL, "GET");
		
		//Save file on Disk
		localFilePath = Utils.saveStreamToFile(responseStream, outputFilePath + attachment.Name);
		return localFilePath;
	}
	
	/**
	 * Get a specific attachment from a PDF
	 * @param fileName Name of the file on cloud
	 * @param attachmentIndex Index of attachment starting from 1
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return Requested attachment details   
	*/ 
	public static AttachmentDetails getASpecificAttachmentFromAPDF(String fileName, int attachmentIndex) throws InvalidKeyException, NoSuchAlgorithmException, IOException {

		AttachmentDetails attachment = null;
		
		if(fileName == null || fileName.length() == 0) {
			throw new IllegalArgumentException("File name cannot be null or empty");
		}
		
		//build URL
		String strURL = PDF_URI + Uri.encode(fileName) + "/attachments/" + attachmentIndex;
		
		//sign URL
		String signedURL = Utils.sign(strURL);
		InputStream responseStream = Utils.processCommand(signedURL, "GET");
		String responseJSONString = Utils.streamToString(responseStream);
		
		//Parsing JSON
		Gson gson = new Gson();
		AttachmentResponse attachmentResponse = gson.fromJson(responseJSONString, AttachmentResponse.class);
		if(attachmentResponse.getCode().equals("200") && attachmentResponse.getStatus().equals("OK")) {
			attachment = attachmentResponse.attachment;
		}
		
		return attachment;
	}
	
	/**
	 * Get attachment count from a PDF
	 * @param fileName Name of the file on cloud
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return Attachments count  
	*/ 
	public static int getAttachmentCountFromAPDF(String fileName) throws InvalidKeyException, NoSuchAlgorithmException, IOException {

		int attachmentCount = -1;
		
		if(fileName == null || fileName.length() == 0) {
			throw new IllegalArgumentException("File name cannot be null or empty");
		}
		
		//build URL
		String strURL = PDF_URI + Uri.encode(fileName) + "/attachments";
		
		//sign URL
		String signedURL = Utils.sign(strURL);
		InputStream responseStream = Utils.processCommand(signedURL, "GET");
		String responseJSONString = Utils.streamToString(responseStream);
		
		//Parsing JSON
		Gson gson = new Gson();
		AttachmentsResponse attachmentsResponse = gson.fromJson(responseJSONString, AttachmentsResponse.class);
		if(attachmentsResponse.getCode().equals("200") && attachmentsResponse.getStatus().equals("OK")) {
			attachmentCount = attachmentsResponse.attachments.List.size();
		}
		
		return attachmentCount;
	}
}
