package com.aspose.cloud.sdk.words.api;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import android.net.Uri;

import com.aspose.cloud.sdk.common.AsposeApp;
import com.aspose.cloud.sdk.common.LinkModel;
import com.aspose.cloud.sdk.common.Utils;
import com.aspose.cloud.sdk.storage.api.Folder;
import com.aspose.cloud.sdk.words.model.AppendDocumentResponse;
import com.aspose.cloud.sdk.words.model.DocumentEntryListModel;
import com.aspose.cloud.sdk.words.model.DocumentEntryModel;
import com.aspose.cloud.sdk.words.model.SplitDocumentResponse;
import com.aspose.cloud.sdk.words.model.StatisticsOfDocumentResponse;
import com.aspose.cloud.sdk.words.model.TrackingChangesResponse;
import com.aspose.cloud.sdk.words.model.TrackingChangesResponse.TrackChangesResult;
import com.aspose.cloud.sdk.words.model.ValidFormatsEnum;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Document --- Using this class you can append documents to source document, convert individual page to new format, 
 * accept/reject revisions in source document and get statistical data of the document.
 * @author   M. Sohail Ismail
 */
public class Document {
	
	private static final String WORD_URI = AsposeApp.BASE_PRODUCT_URI + "/words/";
	
	/**
	 * Append a document or documents specified in the list to the original resource document
	 * @param fileName Name of the document to which list of documents will be appended
	 * @param appendDocs List of documents to be appended
	 * @param importFormatsModes Defines which formatting will be used: appended or destination document. 
	 * Can be KeepSourceFormatting or UseDestinationStyles.
	 * @param folderName Path to document to append at the server
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return Boolean variable indicates whether documents appended successfully
	*/ 
	public static boolean appendDocument(String fileName, String[] appendDocs, String[] importFormatsModes, String folderName) throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		boolean isDocumentAppendedSuccessfully = false;
		
		if(fileName == null || fileName.length() <= 3) {
			throw new IllegalArgumentException("File name cannot be null or empty");
		}
	
		//check whether required information is complete
        if (appendDocs.length != importFormatsModes.length) {
            throw new IllegalArgumentException("Please specify complete documents and import format modes");
        }
        
        DocumentEntryListModel documentEntryList = new DocumentEntryListModel();
        //Create DocumentEntryList object
        for(int i=0; i<appendDocs.length; i++) {
        	String appendDoc = appendDocs[i];
        	String docServerPath = (folderName != null  && folderName.length() != 0) ? folderName+"\\"+appendDoc : appendDoc;
        	documentEntryList.documentEntries.add(new DocumentEntryModel(docServerPath, importFormatsModes[i]));
        }
        
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        String requestJSONString = gson.toJson(documentEntryList, DocumentEntryListModel.class);
        
        //build URL
      	String strURL = WORD_URI + Uri.encode(fileName) + "/appendDocument";
        //sign URL
        String signedURL = Utils.sign(strURL);
        
        InputStream responseStream = Utils.processCommand(signedURL, "POST", requestJSONString);
        String responseJSONString = Utils.streamToString(responseStream);
        
        AppendDocumentResponse appendDocumentResponse = gson.fromJson(responseJSONString, AppendDocumentResponse.class);
		if(appendDocumentResponse.getCode().equals("200") && appendDocumentResponse.getStatus().equals("OK")) {
			isDocumentAppendedSuccessfully = true;
		}
		
		return isDocumentAppendedSuccessfully;
	}
	
	/**
	 * Split all pages to new PDFs
	 * @param fileName Name of the MS Word document on cloud
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return List of paths to new PDF files
	*/
	public static ArrayList<String> splitAllPagesToNewPDFs(String fileName) throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		ArrayList<String> localFilesPaths = null;
		
		if(fileName == null || fileName.length() <= 3) {
			throw new IllegalArgumentException("File name cannot be null or empty");
		}
		
		//build URL
      	String strURL = WORD_URI + Uri.encode(fileName) + "/split?format=pdf";
        //sign URL
        String signedURL = Utils.sign(strURL);
        
        InputStream responseStream = Utils.processCommand(signedURL, "POST");
        String responseJSONString = Utils.streamToString(responseStream);
        
        //Parsing JSON
      	Gson gson = new Gson();
      	SplitDocumentResponse splitDocumentResponse = gson.fromJson(responseJSONString, SplitDocumentResponse.class);
		if(splitDocumentResponse.getCode().equals("200") && splitDocumentResponse.getStatus().equals("OK")) {
			localFilesPaths = new ArrayList<String>();
			for(LinkModel page : splitDocumentResponse.splitResult.pages)
            {
                //build URI to download a particular file
				responseStream = Folder.getFile(page.href);
				String filePath = Utils.saveStreamToFile(responseStream, page.href);
				localFilesPaths.add(filePath);
                responseStream.close();
            }
		}
		
		return localFilesPaths;
	}
	
	/**
	 * Split specific pages to any supported format
	 * @param fileName Name of the MS Word document on cloud
	 * @param designatedFormat A format to which word document will be converted
	 * @param fromPage The start page number for splitting, if is not specified splitting starts from the first page of the document.
	 * @param toPage The last page number for splitting, if is not specified splitting ends at the last page of the document.
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return List of paths to formatted files
	*/
	public static ArrayList<String> splitSpecificPagesToFormat(String fileName, ValidFormatsEnum designatedFormat,
			int fromPage, int toPage) throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		
		ArrayList<String> localFilesPaths = null;
		
		if(fileName == null || fileName.length() <= 3) {
			throw new IllegalArgumentException("File name cannot be null or empty");
		}
		
		if(designatedFormat == null) {
			throw new IllegalArgumentException("Designated format cannot be null");
		}
		
		//build URL
      	String strURL = WORD_URI + Uri.encode(fileName) + "/split?format=" + designatedFormat + 
      			"&from=" + fromPage + "&to=" + toPage;
        //sign URL
        String signedURL = Utils.sign(strURL);
        
        InputStream responseStream = Utils.processCommand(signedURL, "POST");
        String responseJSONString = Utils.streamToString(responseStream);
        
        //Parsing JSON
      	Gson gson = new Gson();
      	SplitDocumentResponse splitDocumentResponse = gson.fromJson(responseJSONString, SplitDocumentResponse.class);
		if(splitDocumentResponse.getCode().equals("200") && splitDocumentResponse.getStatus().equals("OK")) {
			localFilesPaths = new ArrayList<String>();
			for(LinkModel page : splitDocumentResponse.splitResult.pages)
            {
                //build URI to download a particular file
				responseStream = Folder.getFile(page.href);
				String filePath = Utils.saveStreamToFile(responseStream, page.href);
				localFilesPaths.add(filePath);
                responseStream.close();
            }
		}
		return localFilesPaths;
	}
	
	/**
	 * Accept all revisions in source document
	 * @param srcfileName Name of the MS Word document on cloud
	 * @param destFileName Result name of the document after the operation. If this parameter is omitted 
	 * then result of the operation will be saved as the source document
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return An object that contains URLs to source and destination document
	*/
	public static TrackChangesResult acceptAllTrackingChanges(String srcfileName, String destFileName) throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		TrackChangesResult acceptTrackChangesResult = null;
		
		if(srcfileName == null || srcfileName.length() <= 3) {
			throw new IllegalArgumentException("File name cannot be null or empty");
		}
		
		//build URL
		String strURL;
		if(destFileName.equals("")) {
			strURL = WORD_URI + Uri.encode(srcfileName) + "/revisions/acceptAll";
		} else {
			strURL = WORD_URI + Uri.encode(srcfileName) + "/revisions/acceptAll?filename=" + Uri.encode(destFileName);
		}
        //sign URL
        String signedURL = Utils.sign(strURL);
        
        InputStream responseStream = Utils.processCommand(signedURL, "POST");
        String responseJSONString = Utils.streamToString(responseStream);
        
        //Parsing JSON
      	Gson gson = new Gson();
      	TrackingChangesResponse acceptTrackingChangesResponse = gson.fromJson(responseJSONString, TrackingChangesResponse.class);
		if(acceptTrackingChangesResponse.getCode().equals("200") && acceptTrackingChangesResponse.getStatus().equals("OK")) {
			acceptTrackChangesResult = acceptTrackingChangesResponse.result;
		}
		return acceptTrackChangesResult;
	}
	
	/**
	 * Reject all revisions in source document
	 * @param srcfileName Name of the source MS Word document stored on the cloud
	 * @param destFileName Result name of the document after the operation. If this parameter is omitted 
	 * then result of the operation will be saved as the source document
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return An object that contains URLs to source and destination document
	*/
	public static TrackChangesResult rejectAllTrackingChanges(String srcfileName, String destFileName) throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		TrackChangesResult rejectTrackChangesResult = null;
		
		if(srcfileName == null || srcfileName.length() <= 3) {
			throw new IllegalArgumentException("File name cannot be null or empty");
		}
		
		//build URL
		String strURL;
		if(destFileName.equals("")) {
			strURL = WORD_URI + Uri.encode(srcfileName) + "/revisions/rejectAll";
		} else {
			strURL = WORD_URI + Uri.encode(srcfileName) + "/revisions/rejectAll?filename=" + Uri.encode(destFileName);
		}
        //sign URL
        String signedURL = Utils.sign(strURL);
        
        InputStream responseStream = Utils.processCommand(signedURL, "POST");
        String responseJSONString = Utils.streamToString(responseStream);
        
        //Parsing JSON
      	Gson gson = new Gson();
      	TrackingChangesResponse acceptTrackingChangesResponse = gson.fromJson(responseJSONString, TrackingChangesResponse.class);
		if(acceptTrackingChangesResponse.getCode().equals("200") && acceptTrackingChangesResponse.getStatus().equals("OK")) {
			rejectTrackChangesResult = acceptTrackingChangesResponse.result;
		}
		return rejectTrackChangesResult;
	}
	
	/**
	 * Get statistical data of the document like word and paragraph count
	 * @param fileName Name of the MS Word document stored on cloud
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return An object that contains stats of document as whole as well as of each page.
	*/
	public static StatisticsOfDocumentResponse statisticsOfDocument(String fileName) throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		
		StatisticsOfDocumentResponse statisticsOfDocument = null;
		
		if(fileName == null || fileName.length() <= 3) {
			throw new IllegalArgumentException("File name cannot be null or empty");
		}
		
		//build URL
		String strURL = WORD_URI + Uri.encode(fileName) + "/statistics";
		//sign URL
        String signedURL = Utils.sign(strURL);
        
        InputStream responseStream = Utils.processCommand(signedURL, "GET");
        String responseJSONString = Utils.streamToString(responseStream);
        
        //Parsing JSON
      	Gson gson = new Gson();
      	statisticsOfDocument = gson.fromJson(responseJSONString, StatisticsOfDocumentResponse.class);
		if(statisticsOfDocument.getCode().equals("200") && statisticsOfDocument.getStatus().equals("OK")) {
			return statisticsOfDocument;
		} else {
			return null;
		}
	}
}
