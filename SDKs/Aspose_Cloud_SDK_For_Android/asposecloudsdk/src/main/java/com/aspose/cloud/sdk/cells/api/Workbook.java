package com.aspose.cloud.sdk.cells.api;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import android.net.Uri;

import com.aspose.cloud.sdk.cells.model.NamesCountFromAWorkbookResponse;
import com.aspose.cloud.sdk.cells.model.NamesCountFromAWorkbookResponse.NamesCountFromAWorkbookResult;
import com.aspose.cloud.sdk.cells.model.SplitWorksheetsOfAWorkbookResponse;
import com.aspose.cloud.sdk.cells.model.SplitWorksheetsOfAWorkbookResponse.SplitWorksheetsOfAWorkbookResult;
import com.aspose.cloud.sdk.cells.model.EncryptionModel;
import com.aspose.cloud.sdk.cells.model.EncryptionTypeEnum;
import com.aspose.cloud.sdk.cells.model.ProtectionModel;
import com.aspose.cloud.sdk.cells.model.ProtectionTypeEnum;
import com.aspose.cloud.sdk.cells.model.ValidFormatsForDocumentEnum;
import com.aspose.cloud.sdk.cells.model.ValidFormatsForPresentationEnum;
import com.aspose.cloud.sdk.common.AsposeApp;
import com.aspose.cloud.sdk.common.BaseResponse;
import com.aspose.cloud.sdk.common.Utils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Workbook --- Using this class you can create excel workbook, convert excel workbook to different file formats, 
 * merge multiple workbooks, split worksheets of a workbook file, protect, unprotect, encrypt and decrypt workbook, 
 * set and remove write protection and get names count from excel workbook
 * @author   M. Sohail Ismail
 */
public class Workbook {

	private static final String CELLS_URI = AsposeApp.BASE_PRODUCT_URI + "/cells/";
	
	/**
	 * Create an empty excel workbook
	 * @param fileName Name of the file on cloud
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return Boolean variable that indicates whether excel workbook created successfully
	*/
	public static boolean createAnEmptyExcelWorkbook(String fileName) throws InvalidKeyException, NoSuchAlgorithmException, IOException {

		boolean isWorkbookCreatedSuccessfully = false;
		
		if(fileName == null || fileName.length() <= 3) {
			throw new IllegalArgumentException("File name cannot be null or empty");
		}
		
		//build URL
      	String strURL = CELLS_URI + Uri.encode(fileName);
        //sign URL
        String signedURL = Utils.sign(strURL);
        
		InputStream responseStream = Utils.processCommand(signedURL, "PUT");
		String responseJSONString = Utils.streamToString(responseStream);

		//Parsing JSON
		Gson gson = new Gson();
		BaseResponse baseResponse = gson.fromJson(responseJSONString, BaseResponse.class);
		if (baseResponse.getCode().equals("200") && baseResponse.getStatus().equals("OK")) {
			isWorkbookCreatedSuccessfully = true;
		}
		
		return isWorkbookCreatedSuccessfully;
	}
	
	/**
	 * Create excel workbook from a template file
	 * @param fileName Name of the file on cloud
	 * @param templateFileName Template server file (including path)
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return Boolean variable that indicates whether excel workbook created successfully from a template file
	*/
	public static boolean createExcelWorkbookFromATemplateFile(String fileName, String templateFileName) throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		
		boolean isWorkbookCreatedSuccessfullyFromTemplate = false;
		
		if(fileName == null || fileName.length() <= 3) {
			throw new IllegalArgumentException("File name cannot be null or empty");
		}
		
		if(templateFileName == null || templateFileName.length() == 0) {
			throw new IllegalArgumentException("Template file name cannot be null or empty");
		}
		
		//build URL
      	String strURL = CELLS_URI + Uri.encode(fileName) + "?templatefile=" + Uri.encode(templateFileName);
        //sign URL
        String signedURL = Utils.sign(strURL);
        
		InputStream responseStream = Utils.processCommand(signedURL, "PUT");
		String responseJSONString = Utils.streamToString(responseStream);
		
		//Parsing JSON
		Gson gson = new Gson();
		BaseResponse baseResponse = gson.fromJson(responseJSONString, BaseResponse.class);
		if (baseResponse.getCode().equals("200") && baseResponse.getStatus().equals("OK")) {
			isWorkbookCreatedSuccessfullyFromTemplate = true;
		}
		
		return isWorkbookCreatedSuccessfullyFromTemplate;
	}
	
	/**
	 * Create excel workbook from a smartMarker template
	 * @param fileName Name of the file on cloud
	 * @param templateFileName Template server file (including path)
	 * @param dataFile Smart marker data server path
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return Boolean variable that indicates whether excel workbook created successfully from a smart marker template
	*/
	public static boolean createExcelWorkbookFromASmartMarkerTemplate(String fileName, String templateFileName, String dataFile) throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		
		boolean isWorkbookCreatedSuccesfullyFromSmartMarkerTemplate = false;
		
		if(fileName == null || fileName.length() <= 3) {
			throw new IllegalArgumentException("File name cannot be null or empty");
		}
		
		if(templateFileName == null || templateFileName.length() == 0) {
			throw new IllegalArgumentException("Template file name cannot be null or empty");
		}
		
		if(dataFile == null || dataFile.length() == 0) {
			throw new IllegalArgumentException("Data file cannot be null or empty");
		}
			
		//build URL
      	String strURL = CELLS_URI + Uri.encode(fileName) + "?templatefile=" + Uri.encode(templateFileName) 
      			+ "&dataFile=" + Uri.encode(dataFile);
        //sign URL
        String signedURL = Utils.sign(strURL);
        
		InputStream responseStream = Utils.processCommand(signedURL, "PUT");
		String responseJSONString = Utils.streamToString(responseStream);
		
		//Parsing JSON
		Gson gson = new Gson();
		BaseResponse baseResponse = gson.fromJson(responseJSONString, BaseResponse.class);
		if (baseResponse.getCode().equals("200") && baseResponse.getStatus().equals("OK")) {
			isWorkbookCreatedSuccesfullyFromSmartMarkerTemplate = true;
		}
		
		return isWorkbookCreatedSuccesfullyFromSmartMarkerTemplate;
	}
	
	/**
	 * Convert excel workbook to different file formats
	 * @param fileName Name of the file on cloud
	 * @param designatedFormat Convert the document to the specified format
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return Path to converted file saved on device
	*/
	public static String convertExcelWorkbookToDifferentFileFormats(String fileName, ValidFormatsForPresentationEnum designatedFormat) throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		
		String localFilePath = null;
		
		if(fileName == null || fileName.length() <= 3) {
			throw new IllegalArgumentException("File name cannot be null or empty");
		}
		
		if(designatedFormat == null) {
			throw new IllegalArgumentException("Designated format cannot be null");
		}
		
		//build URL
      	String strURL = CELLS_URI + fileName + "?format=" + designatedFormat; 
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
	 * Convert workbook to different file formats without uploading to any storage 
	 * @param localFilePath Path to file stored on device
	 * @param designatedFormat Convert the document to the specified format
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return Path to converted file saved on device
	*/
	public static String convertExcelWorkbookToDifferentFileFormatsWithoutUsingStorage(String localFilePath, ValidFormatsForPresentationEnum designatedFormat) throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		
		String updatedFilePath = null;
		
		if(localFilePath == null || localFilePath.length() == 0) {
			throw new IllegalArgumentException("Local file path cannot be null or empty");
		}
		
		if(designatedFormat == null) {
			throw new IllegalArgumentException("Designated format cannot be null");
		}
		
		//build URL
      	String strURL = CELLS_URI + "convert?format=" + designatedFormat; 
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
	 * Convert a workbook to other formats with additional settings  
	 * @param fileName Name of file stored on cloud
	 * @param xmlData Additional save options
	 * @param outputFileName Name use for saving converted file
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return Path to converted file saved on device
	*/
	public static String convertExcelWorkbookWithAdditionalSettings(String fileName, String xmlData, String outputFileName) throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		
		String localFilePath = null;
		
		if(fileName == null || fileName.length() == 0) {
			throw new IllegalArgumentException("File name cannot be null or empty");
		}
		
		if(xmlData == null || xmlData.length() == 0) {
			throw new IllegalArgumentException("XML Data cannot be null or empty");
		}
		
		if(outputFileName == null || outputFileName.length() == 0) {
			throw new IllegalArgumentException("Output file name cannot be null or empty");
		}
		
		//build URL
      	String strURL = CELLS_URI + Uri.encode(fileName) + "/saveAs"; 
        //sign URL
        String signedURL = Utils.sign(strURL);
		
        //Process the request on server
      	InputStream responseStream = Utils.processCommand(signedURL, "POST", xmlData, "xml");
      	
      	//Save the stream in response to the disk
      	localFilePath = Utils.saveStreamToFile(responseStream, outputFileName);
            		
      	return localFilePath;
	}
	
	/**
	 * Merge multiple workbooks into a single workbook 
	 * @param fileName Name of file stored on cloud
	 * @param mergeWithFileName Name of file to be merged with
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return Boolean variable that indicates whether two files merged successfully
	*/
	public static boolean mergeExcelWorkbooks(String fileName, String mergeWithFileName) throws InvalidKeyException, NoSuchAlgorithmException, IOException {

		boolean isExcelFilesMergedSuccessfully = false;
		
		if(fileName == null || fileName.length() == 0) {
			throw new IllegalArgumentException("File name cannot be null or empty");
		}
		
		if(mergeWithFileName == null || mergeWithFileName.length() == 0) {
			throw new IllegalArgumentException("Merge with file name cannot be null or empty");
		}
		
		//build URI
		String strURI = CELLS_URI + Uri.encode(fileName) + "/merge?mergeWith=" + Uri.encode(mergeWithFileName);
		//sign URI
		String signedURI = Utils.sign(strURI);

		//Process the request on server
		InputStream responseStream = Utils.processCommand(signedURI, "POST");
		//Further process JSON response
		String responseJSONString = Utils.streamToString(responseStream);

		//Parsing JSON
		Gson gson = new Gson();
		BaseResponse baseResponse = gson.fromJson(responseJSONString, BaseResponse.class);
		if (baseResponse.getCode().equals("200") && baseResponse.getStatus().equals("OK")) {
			isExcelFilesMergedSuccessfully = true;
		}
		
		return isExcelFilesMergedSuccessfully;
	}
	
	/**
	 * Split all or specific worksheets of a workbook file and save each worksheet as a new workbook
	 * @param fileName Name of file stored on cloud
	 * @param designatedFormat Convert the document to the specified format
	 * @param fromWorksheet The start page number for splitting
	 * @param toWorksheet The last page number for splitting
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return An object that contains hypertext references to splitted worksheets
	*/
	public static SplitWorksheetsOfAWorkbookResult splitWorksheetsOfAWorkbook
		(String fileName, ValidFormatsForDocumentEnum designatedFormat, int fromWorksheet, int toWorksheet) throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		
		SplitWorksheetsOfAWorkbookResult splitWorksheetsOfAWorkbookResult = null;
		
		if(fileName == null || fileName.length() == 0) {
			throw new IllegalArgumentException("File name cannot be null or empty");
		}
		
		if(designatedFormat == null) {
			throw new IllegalArgumentException("Designated format cannot be null");
		}
		
		//build URI
		String strURI;
		if(fromWorksheet > 0 && toWorksheet > 0) {
			strURI = CELLS_URI + Uri.encode(fileName) + "/split?from=" +  fromWorksheet + "&to=" + toWorksheet + "&format=" + designatedFormat;
		} else {
			strURI = CELLS_URI + Uri.encode(fileName) + "/split?format=" + designatedFormat;
		}
		
		//sign URI
		String signedURI = Utils.sign(strURI);
		
		//Process the request on server
		InputStream responseStream = Utils.processCommand(signedURI, "POST");
		//Further process JSON response
		String responseJSONString = Utils.streamToString(responseStream);
		
		//Parsing JSON
		Gson gson = new Gson();
		SplitWorksheetsOfAWorkbookResponse splitWorksheetsofAWorkbookResponse = gson.fromJson(responseJSONString, SplitWorksheetsOfAWorkbookResponse.class);
		if (splitWorksheetsofAWorkbookResponse.getCode().equals("200") && splitWorksheetsofAWorkbookResponse.getStatus().equals("OK")) {
			splitWorksheetsOfAWorkbookResult = splitWorksheetsofAWorkbookResponse.result;
		}
		
		return splitWorksheetsOfAWorkbookResult;
	}
	
	/**
	 * Protect excel workbooks
	 * @param fileName Name of file stored on cloud
	 * @param protectionType Represents workbook protection type.Valid values are All, Structure, Windows and None
	 * @param password Protection password 
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return Boolean variable that indicates whether a workbook protected successfully
	*/
	public static boolean protectAWorkbook(String fileName, ProtectionTypeEnum protectionType, String password) throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		
		boolean isWorkbookProtectedSuccessfully = false;
		
		if(fileName == null || fileName.length() == 0) {
			throw new IllegalArgumentException("File name cannot be null or empty");
		}
		
		if(protectionType == null) {
			throw new IllegalArgumentException("Protection type cannot be null");
		}
		
		if(password == null || password.length() == 0) {
			throw new IllegalArgumentException("Password cannot be null or empty");
		}
		
		//Serialize the JSON request content
		ProtectionModel protectionObj = new ProtectionModel();
		protectionObj.protectionType = protectionType;
		protectionObj.password = password;

		GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        String requestJSONString = gson.toJson(protectionObj, ProtectionModel.class);
		
        //build URI
        String strURI = CELLS_URI + Uri.encode(fileName) + "/protection";
        //sign URL
      	String signedURI = Utils.sign(strURI);

		InputStream responseStream = Utils.processCommand(signedURI, "POST", requestJSONString);
		String responseJSONString = Utils.streamToString(responseStream);

		//Parsing JSON
		BaseResponse baseResponse = gson.fromJson(responseJSONString, BaseResponse.class);
		if (baseResponse.getCode().equals("200") && baseResponse.getStatus().equals("OK")) {
			isWorkbookProtectedSuccessfully = true;
		}
		
		return isWorkbookProtectedSuccessfully;
	}

	/**
	 * Unprotect excel workbooks
	 * @param fileName Name of file stored on cloud
	 * @param password Protection password 
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return Boolean variable that indicates whether a workbook unprotected successfully
	*/
	public static boolean unprotectAWorkbook(String fileName, String password) throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		
		boolean isWorkbookUnProtectedSuccessfully = false;
		
		if(fileName == null || fileName.length() == 0) {
			throw new IllegalArgumentException("File name cannot be null or empty");
		}
		
		if(password == null || password.length() == 0) {
			throw new IllegalArgumentException("Password cannot be null or empty");
		}
		
		//Serialize the JSON request content
		ProtectionModel protectionObj = new ProtectionModel();
		protectionObj.password = password;

		GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        String requestJSONString = gson.toJson(protectionObj, ProtectionModel.class);
		
        //build URI
        String strURI = CELLS_URI + Uri.encode(fileName) + "/protection";
        //sign URL
      	String signedURI = Utils.sign(strURI);

		InputStream responseStream = Utils.processDeleteCommandWithBody(signedURI, requestJSONString);
		String responseJSONString = Utils.streamToString(responseStream);

		//Parsing JSON
		BaseResponse baseResponse = gson.fromJson(responseJSONString, BaseResponse.class);
		if (baseResponse.getCode().equals("200") && baseResponse.getStatus().equals("OK")) {
			isWorkbookUnProtectedSuccessfully = true;
		}
		
		return isWorkbookUnProtectedSuccessfully;
		
	}
	
	/**
	 * Encrypt a workbook
	 * @param fileName Name of file stored on cloud
	 * @param encryptionType The encryption algorithm type. Valid values are XOR, EnhancedCryptographicProviderV1 and StrongCryptographicProvider.
	 *  This parameter is only for Excel97~2003 format 
	 * @param password Encryption password 
	 * @param keyLength The key length. This parameter is only for Excel97~2003 format
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return Boolean variable that indicates whether a workbook encrypted successfully
	*/
	public static boolean encryptAWorkbook (String fileName, EncryptionTypeEnum encryptionType, String password, int keyLength) throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		
		boolean isWorkbookEncryptedSuccessfully = false;
		
		if(fileName == null || fileName.length() == 0) {
			throw new IllegalArgumentException("File name cannot be null or empty");
		}
		
		if(encryptionType == null) {
			throw new IllegalArgumentException("Encryption type cannot be null");
		}
		
		if(password == null || password.length() == 0) {
			throw new IllegalArgumentException("Password cannot be null or empty");
		}
		
		//Serialize the JSON request content
		EncryptionModel encryptionObj = new EncryptionModel();
		encryptionObj.encryptionType = encryptionType;
		encryptionObj.password = password;
		encryptionObj.keyLength = keyLength;
		
		GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        String requestJSONString = gson.toJson(encryptionObj, EncryptionModel.class);
		
        //build URI
        String strURI = CELLS_URI + Uri.encode(fileName) + "/encryption";
        //sign URL
      	String signedURI = Utils.sign(strURI);

		InputStream responseStream = Utils.processCommand(signedURI, "POST", requestJSONString);
		String responseJSONString = Utils.streamToString(responseStream);

		//Parsing JSON
		BaseResponse baseResponse = gson.fromJson(responseJSONString, BaseResponse.class);
		if (baseResponse.getCode().equals("200") && baseResponse.getStatus().equals("OK")) {
			isWorkbookEncryptedSuccessfully = true;
		}
		
		return isWorkbookEncryptedSuccessfully;
	}
	
	/**
	 * Decrypt a workbook
	 * @param fileName Name of file stored on cloud
	 * @param password Encryption password 
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return Boolean variable that indicates whether a workbook decrypted successfully
	*/
	public static boolean decryptAWorkbook(String fileName, String password) throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		
		boolean isWorkbookDecryptedSuccessfully = false;
		
		if(fileName == null || fileName.length() == 0) {
			throw new IllegalArgumentException("File name cannot be null or empty");
		}
		
		if(password == null || password.length() == 0) {
			throw new IllegalArgumentException("Password cannot be null or empty");
		}
		
		//Serialize the JSON request content
		EncryptionModel encryptionObj = new EncryptionModel();
		encryptionObj.password = password;
		
		GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        String requestJSONString = gson.toJson(encryptionObj, EncryptionModel.class);
		
        //build URI
        String strURI = CELLS_URI + Uri.encode(fileName) + "/encryption";
        //sign URL
      	String signedURI = Utils.sign(strURI);

		InputStream responseStream = Utils.processDeleteCommandWithBody(signedURI, requestJSONString);
		String responseJSONString = Utils.streamToString(responseStream);

		//Parsing JSON
		BaseResponse baseResponse = gson.fromJson(responseJSONString, BaseResponse.class);
		if (baseResponse.getCode().equals("200") && baseResponse.getStatus().equals("OK")) {
			isWorkbookDecryptedSuccessfully = true;
		}
		
		return isWorkbookDecryptedSuccessfully;
	}
	
	/**
	 * Set modify password of excel workbooks (Write protection)
	 * @param fileName Name of file stored on cloud
	 * @param password Modify password 
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return Boolean variable that indicates whether write protection set successfully on workbook
	*/
	public static boolean setModifyPasswordOfAWorkbook(String fileName, String password) throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		
		boolean modifyPasswordSetSuccessfullyOfAWorkbook = false;
		
		if(fileName == null || fileName.length() == 0) {
			throw new IllegalArgumentException("File name cannot be null or empty");
		}
		
		if(password == null || password.length() == 0) {
			throw new IllegalArgumentException("Password cannot be null or empty");
		}
		
		//Serialize the JSON request content
		ProtectionModel protectionObj = new ProtectionModel();
		protectionObj.password = password;

		GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        String requestJSONString = gson.toJson(protectionObj, ProtectionModel.class);
		
        //build URI
        String strURI = CELLS_URI + Uri.encode(fileName) + "/writeProtection";
        //sign URL
      	String signedURI = Utils.sign(strURI);

		InputStream responseStream = Utils.processCommand(signedURI, "PUT", requestJSONString);
		String responseJSONString = Utils.streamToString(responseStream);

		//Parsing JSON
		BaseResponse baseResponse = gson.fromJson(responseJSONString, BaseResponse.class);
		if (baseResponse.getCode().equals("200") && baseResponse.getStatus().equals("OK")) {
			modifyPasswordSetSuccessfullyOfAWorkbook = true;
		}
		
		return modifyPasswordSetSuccessfullyOfAWorkbook;
	}

	/**
	 * Clear modify password of excel workbook (Remove write protection)
	 * @param fileName Name of file stored on cloud
	 * @param password Modify password 
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return Boolean variable that indicates whether write protection removed successfully on workbook
	*/
	public static boolean clearModifyPasswordOfAWorkbook(String fileName, String password) throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		
		boolean modifyPasswordClearedSuccessfullyOfAWorkbook = false;
		
		if(fileName == null || fileName.length() == 0) {
			throw new IllegalArgumentException("File name cannot be null or empty");
		}
		
		if(password == null || password.length() == 0) {
			throw new IllegalArgumentException("Password cannot be null or empty");
		}
		
		//Serialize the JSON request content
		ProtectionModel protectionObj = new ProtectionModel();
		protectionObj.password = password;

		GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        String requestJSONString = gson.toJson(protectionObj, ProtectionModel.class);
		
        //build URI
        String strURI = CELLS_URI + Uri.encode(fileName) + "/writeProtection";
        //sign URL
      	String signedURI = Utils.sign(strURI);

		InputStream responseStream = Utils.processDeleteCommandWithBody(signedURI, requestJSONString);
		String responseJSONString = Utils.streamToString(responseStream);

		//Parsing JSON
		BaseResponse baseResponse = gson.fromJson(responseJSONString, BaseResponse.class);
		if (baseResponse.getCode().equals("200") && baseResponse.getStatus().equals("OK")) {
			modifyPasswordClearedSuccessfullyOfAWorkbook = true;
		}
		
		return modifyPasswordClearedSuccessfullyOfAWorkbook;
	}
	
	/**
	 * Get names count from excel workbooks
	 * @param fileName Name of file stored on cloud
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return An object that contains names count
	*/
	public static NamesCountFromAWorkbookResult getNamesCountFromAWorkbook(String fileName) throws InvalidKeyException, NoSuchAlgorithmException, IOException{

		NamesCountFromAWorkbookResult namesCountFromAWorkbookResult = null;
		
		if(fileName == null || fileName.length() == 0) {
			throw new IllegalArgumentException("File name cannot be null or empty");
		}
		
		//build URL
      	String strURL = CELLS_URI + Uri.encode(fileName) + "/names";
        //sign URL
        String signedURL = Utils.sign(strURL);
        
		InputStream responseStream = Utils.processCommand(signedURL, "GET");
		String responseJSONString = Utils.streamToString(responseStream);
		
		//Parsing JSON
		Gson gson = new Gson();
		NamesCountFromAWorkbookResponse namesCountFromAWorkbookResponse = gson.fromJson(responseJSONString, NamesCountFromAWorkbookResponse.class);
		if (namesCountFromAWorkbookResponse.getCode().equals("200") && namesCountFromAWorkbookResponse.getStatus().equals("OK")) {
			namesCountFromAWorkbookResult = namesCountFromAWorkbookResponse.names;
		}

		return namesCountFromAWorkbookResult;
	}
}