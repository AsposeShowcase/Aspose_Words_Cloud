package com.aspose.cloud.sdk.storage.api;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;

import android.net.Uri;

import com.aspose.cloud.sdk.common.AsposeApp;
import com.aspose.cloud.sdk.common.BaseResponse;
import com.aspose.cloud.sdk.common.Utils;
import com.aspose.cloud.sdk.storage.model.DateDeserializer;
import com.aspose.cloud.sdk.storage.model.DiscUsageModel;
import com.aspose.cloud.sdk.storage.model.DiscUsageResponse;
import com.aspose.cloud.sdk.storage.model.FileExist;
import com.aspose.cloud.sdk.storage.model.FileExistResponse;
import com.aspose.cloud.sdk.storage.model.FileModel;
import com.aspose.cloud.sdk.storage.model.FolderResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Folder {

	private static final String FOLDER_URI = AsposeApp.BASE_PRODUCT_URI + "/storage/folder/";
	private static final String FILE_URI = AsposeApp.BASE_PRODUCT_URI + "/storage/file/";
	private static final String EXIST_URI = AsposeApp.BASE_PRODUCT_URI + "/storage/exist/";
	private static final String DISC_URI = AsposeApp.BASE_PRODUCT_URI + "/storage/disc/";
	
	/**
	 * Get a list of all files and folders under the specified folder. Use empty string to specify root folder.
	 * @param folderPath Folder path
	 * @return Array of File Objects
	*/ 
	public static List<FileModel> getFilesList(String folderPath) throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		List<FileModel> filesList = null;
			
		if(folderPath == null) {
			throw new IllegalArgumentException("Folder path cannot be null");
		}
		
		//build URL
      	String strURL = FOLDER_URI + Uri.encode(folderPath);
        //sign URL
        String signedURL = Utils.sign(strURL);
        
        InputStream responseStream = Utils.processCommand(signedURL, "GET");
        String responseJSONString = Utils.streamToString(responseStream);
        
        //Parsing JSON
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Date.class, new DateDeserializer());
      	Gson gson = gsonBuilder.create();
      	FolderResponse folderResponse = gson.fromJson(responseJSONString, FolderResponse.class);
		if(folderResponse.getCode().equals("200") && folderResponse.getStatus().equals("OK")) {
			filesList = folderResponse.files;
		}
		
		return filesList;
	}

	/**
	 * Creates a folder under the specified path. If no path specified, creates a folder under the root folder.
	 * @param folderPath Folder path
	 * @return Boolean variable indicates whether folder created successfully
	*/ 
	public static boolean createFolder(String folderPath) throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		
		boolean isFolderCreatedSuccessfully = false;
		
		if(folderPath == null) {
			throw new IllegalArgumentException("Folder path cannot be null");
		}
		
		//build URL
      	String strURL = FOLDER_URI + Uri.encode(folderPath);
        //sign URL
        String signedURL = Utils.sign(strURL);
        
        InputStream responseStream = Utils.processCommand(signedURL, "PUT");
        String responseJSONString = Utils.streamToString(responseStream);
        
        //Parsing JSON
      	Gson gson = new Gson();
      	BaseResponse baseResponse = gson.fromJson(responseJSONString, BaseResponse.class);
		if(baseResponse.getCode().equals("200") && baseResponse.getStatus().equals("OK")) {
			isFolderCreatedSuccessfully = true;
		}
		
		return isFolderCreatedSuccessfully;
	}
	
	/**
	 * Delete an empty folder from the storage. Use "FolderName/SubFolderName" for sub folders.
	 * @param folderPath Folder path
	 * @return Boolean variable indicates whether folder deleted successfully
	*/ 
	public static boolean deleteFolder(String folderPath) throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		
		boolean isFolderDeletedSuccessfully = false;
		
		if(folderPath == null || folderPath.length() == 0) {
			throw new IllegalArgumentException("Folder path cannot be null or empty");
		}
		
		//build URL
      	String strURL = FOLDER_URI + Uri.encode(folderPath);
        //sign URL
        String signedURL = Utils.sign(strURL);
        
        InputStream responseStream = Utils.processCommand(signedURL, "DELETE");
        String responseJSONString = Utils.streamToString(responseStream);
        
        //Parsing JSON
      	Gson gson = new Gson();
      	BaseResponse baseResponse = gson.fromJson(responseJSONString, BaseResponse.class);
		if(baseResponse.getCode().equals("200") && baseResponse.getStatus().equals("OK")) {
			isFolderDeletedSuccessfully = true;
		}
		
		return isFolderDeletedSuccessfully;	
		
	}
	
	/**
	 * Delete a file from the storage. Use "FolderName/FileName" to specify a file under specific folder
	 * @param filePath File path
	 * @return Boolean variable indicates whether file deleted successfully
	*/ 
	public static boolean deleteFile(String filePath) throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		boolean isFileDeletedSuccessfully = false;
		
		if(filePath == null || filePath.length() == 0) {
			throw new IllegalArgumentException("File path cannot be null or empty");
		}
		
		//build URL
      	String strURL = FILE_URI + Uri.encode(filePath);
        //sign URL
        String signedURL = Utils.sign(strURL);
        
        InputStream responseStream = Utils.processCommand(signedURL, "DELETE");
        String responseJSONString = Utils.streamToString(responseStream);
        
        //Parsing JSON
      	Gson gson = new Gson();
      	BaseResponse baseResponse = gson.fromJson(responseJSONString, BaseResponse.class);
		if(baseResponse.getCode().equals("200") && baseResponse.getStatus().equals("OK")) {
			isFileDeletedSuccessfully = true;
		}
		
		return isFileDeletedSuccessfully;	
	}

	/**
	 * Upload a file from your local machine to remote folder
	 * @param folderPath Folder path
	 * @param filePath File path on device
	 * @return Boolean variable that indicates whether file uploaded successfully
	*/ 
	public static boolean uploadFile(String localFilePath, String remoteFolderPath) throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		
		Boolean isFileUploadedSuccessfully = false;
		
		if(localFilePath == null || localFilePath.length() == 0) {
			throw new IllegalArgumentException("Local file path cannot be null or empty");
		}
		
		if(remoteFolderPath == null) {
			throw new IllegalArgumentException("Remote folder path cannot be null or empty");
		}
		
		File localFile = new File(localFilePath);
		String fileName = localFile.getName();
		
		String remoteFilePath = remoteFolderPath.length() != 0? remoteFolderPath + "/" + fileName : fileName;
		
		//build URL
      	String strURL = FILE_URI + Uri.encode(remoteFilePath);
        //sign URL
        String signedURL = Utils.sign(strURL);
        
		String responseJSONString = Utils.uploadFileBinary(localFile, signedURL, "PUT");

		//Parsing JSON
      	Gson gson = new Gson();
      	BaseResponse baseResponse = gson.fromJson(responseJSONString, BaseResponse.class);
		if(baseResponse.getCode().equals("200") && baseResponse.getStatus().equals("OK")) {
			isFileUploadedSuccessfully = true;
		}
		
		return isFileUploadedSuccessfully;
	}

	/**
	 * Checks whether file or folder exists on the Aspose storage
	 * @param fileName File name
	*/ 
	public static FileExist fileExist(String fileName) throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		FileExist fileExist = null;
		
		if(fileName == null || fileName.length() == 0) {
			throw new IllegalArgumentException("File name cannot be null or empty");
		}
		
		//build URL
      	String strURL = EXIST_URI + Uri.encode(fileName);
        //sign URL
        String signedURL = Utils.sign(strURL);
        
        InputStream responseStream = Utils.processCommand(signedURL, "GET");
        String responseJSONString = Utils.streamToString(responseStream);
        
        //Parsing JSON
      	Gson gson = new Gson();
      	FileExistResponse existResponse = gson.fromJson(responseJSONString, FileExistResponse.class);
		if(existResponse.getCode().equals("200") && existResponse.getStatus().equals("OK")) {
			fileExist = existResponse.fileExist;
		}
		
		return fileExist;
	}

	/**
	 * Provides the total and free disc size in bytes for your app
	*/ 
	public static DiscUsageModel getDiscUsage() throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		DiscUsageModel discUsage = null;
		
        //sign URL
        String signedURL = Utils.sign(DISC_URI);
        
        InputStream responseStream = Utils.processCommand(signedURL, "GET");
        String responseJSONString = Utils.streamToString(responseStream);
        
        //Parsing JSON
      	Gson gson = new Gson();
      	DiscUsageResponse existResponse = gson.fromJson(responseJSONString, DiscUsageResponse.class);
		if(existResponse.getCode().equals("200") && existResponse.getStatus().equals("OK")) {
			discUsage = existResponse.discUsage;
		}
		
		return discUsage;
	}

	/**
	 * Get file from Aspose server
	 * @param fileName File name
	*/ 
	public static InputStream getFile(String fileName) throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		
		InputStream fileStream = null;
		
		if(fileName == null || fileName.length() == 0) {
			throw new IllegalArgumentException("File name cannot be null or empty");
		}
		
		//build URL
      	String strURL = FILE_URI + Uri.encode(fileName);
        //sign URL
        String signedURL = Utils.sign(strURL);
        
        fileStream = Utils.processCommand(signedURL, "GET");
        
        return fileStream;
	}
	
	/**
	 * Move file to designated location
	 * @param fileName File name
	 * @param srcFolderName Source folder name
	 * @param destFolderName Destination folder name
	 * @return Boolean variable that indicates whether file moved successfully to designated location
	*/ 
	public static boolean moveFile(String fileName, String srcFolderName, String destFolderName) throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		
		boolean isFileMovedToAnotherLocationSuccessfully = false;
		
		if(fileName == null || fileName.length() == 0) {
			throw new IllegalArgumentException("File name cannot be null or empty");
		}
		
		if(destFolderName == null || destFolderName.length() == 0) {
			throw new IllegalArgumentException("Destination folder name cannot be null or empty");
		}
		
		String sourceFilePath;
		if(srcFolderName == null || srcFolderName.length() == 0) {
			sourceFilePath = Uri.encode(fileName);
		} else {
			sourceFilePath = Uri.encode(srcFolderName) + "/" + Uri.encode(fileName);
		}
		
		//build URL
      	String strURL = FILE_URI + sourceFilePath + "?dest=" + Uri.encode(destFolderName) + "/" + Uri.encode(fileName);
        //sign URL
        String signedURL = Utils.sign(strURL);
        
        InputStream responseStream = Utils.processCommand(signedURL, "POST");
        String responseJSONString = Utils.streamToString(responseStream);
		
        //Parsing JSON
      	Gson gson = new Gson();
      	BaseResponse baseResponse = gson.fromJson(responseJSONString, BaseResponse.class);
		if(baseResponse.getCode().equals("200") && baseResponse.getStatus().equals("OK")) {
			isFileMovedToAnotherLocationSuccessfully = true;
		}
        
		return isFileMovedToAnotherLocationSuccessfully;
	}
	
	/**
	 * Copy file to designated location
	 * @param fileName File name
	 * @param newDest Destination path
	 * @return Boolean variable that indicates whether file copied successfully to designated location
	*/ 
	public static boolean copyFile(String fileName, String newDest) throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		boolean isFileCopiedToNewDestinationSuccessfully = false;
		
		if(fileName == null || fileName.length() == 0) {
			throw new IllegalArgumentException("File name cannot be null or empty");
		}
		
		if(newDest == null || newDest.length() == 0) {
			throw new IllegalArgumentException("newDest cannot be null or empty");
		}
		
		//build URL
      	String strURL = FILE_URI + Uri.encode(fileName) + "?newdest=" + Uri.encode(newDest) + "/" + Uri.encode(fileName);
        //sign URL
        String signedURL = Utils.sign(strURL);
        
        InputStream responseStream = Utils.processCommand(signedURL, "PUT");
        String responseJSONString = Utils.streamToString(responseStream);
		
        //Parsing JSON
      	Gson gson = new Gson();
      	BaseResponse baseResponse = gson.fromJson(responseJSONString, BaseResponse.class);
		if(baseResponse.getCode().equals("200") && baseResponse.getStatus().equals("OK")) {
			isFileCopiedToNewDestinationSuccessfully = true;
		}
		
		return isFileCopiedToNewDestinationSuccessfully;
	}
	
}
