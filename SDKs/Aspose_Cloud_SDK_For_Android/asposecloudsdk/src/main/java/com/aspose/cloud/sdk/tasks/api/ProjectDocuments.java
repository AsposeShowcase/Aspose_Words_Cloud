package com.aspose.cloud.sdk.tasks.api;

import android.net.Uri;

import com.aspose.cloud.sdk.common.AsposeApp;
import com.aspose.cloud.sdk.common.Utils;
import com.aspose.cloud.sdk.tasks.model.GetDocumentPropertiesResponseModel;
import com.aspose.cloud.sdk.tasks.model.ProjectPropertyModel;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * ProjectDocuments --- Using this class you can read MS Project properties and can convert project document to other formats.
 * @author   M. Sohail Ismail
 */
public class ProjectDocuments {
	
	private static final String TASKS_URI = AsposeApp.BASE_PRODUCT_URI + "/tasks/";
	private static final List<String> validFormats = Arrays.asList("mpp", "xml", "html", "bmp", "png", "jpeg", 
			"pdf", "tiff", "xps", "xaml", "svg", "csv", "txt", "Spreadsheet2003", "XLSX", "PrimaveraP6XML", "PrimaveraXER");
	
	/**
	 * Retrieve a Project's Properties
	 * @param projectName Name of the MS Project Binary File
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return An array of project properties
	*/ 
	public static ArrayList<ProjectPropertyModel> retrieveProjectProperties(String projectName) throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		ArrayList<ProjectPropertyModel> documentPropertiesList = null;
		
		if(projectName == null || projectName.length() == 0) {
			throw new IllegalArgumentException("Project name cannot be null or empty");
		}
		
		//build URL
		String strURL = TASKS_URI + Uri.encode(projectName) + "/documentProperties";
		//sign URL
		String signedURL = Utils.sign(strURL);
		InputStream responseStream = Utils.processCommand(signedURL, "GET");
		String jsonStr = Utils.streamToString(responseStream);
		
		//Parsing JSON
		Gson gson = new Gson();
		GetDocumentPropertiesResponseModel documentPropertyResponse = gson.fromJson(jsonStr, GetDocumentPropertiesResponseModel.class);
		if(documentPropertyResponse.getCode().equals("200") && documentPropertyResponse.getStatus().equals("OK")) {
			documentPropertiesList = documentPropertyResponse.properties.list;
		}
		
		return documentPropertiesList;
	}
	
	/**
	 * Convert Project document to other formats and valid formats are mpp, xml, html, bmp, png, jpeg, 
			pdf, tiff, xps, xaml, svg, csv, txt, Spreadsheet2003, XLSX, PrimaveraP6XML and PrimaveraXER.
	 * @param projectName Name of the MS Project Binary File
	 * @param designatedFormat A format to which project file will be converted
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return A path to locally saved file
	*/ 
	public static String convertProjectDataToFormat(String projectName, String designatedFormat) throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		
		String localFilePath = null;
		
		if(projectName == null || projectName.length() == 0) {
			throw new IllegalArgumentException("Project name cannot be null or empty");
		}
		
		if(!validFormats.contains(designatedFormat)) {
			throw new IllegalArgumentException("Valid Formats are mpp, xml, html, bmp, png, jpeg, " +
			"pdf, tiff, xps, xaml, svg, csv, txt, Spreadsheet2003, XLSX, PrimaveraP6XML, PrimaveraXER");
		}
		
		//build URL
		String strURL = TASKS_URI + Uri.encode(projectName) + "?format=" + designatedFormat;
		//sign URL
		String signedURL = Utils.sign(strURL);
		InputStream responseStream = Utils.processCommand(signedURL, "GET");
		
		//Replace fileName extension with designated format 
		String[] fileNameAndItsExtensionArray = projectName.split("\\.");
		projectName = fileNameAndItsExtensionArray[0] + "." + designatedFormat;
		
		//Save file on Disk
		localFilePath = Utils.saveStreamToFile(responseStream, projectName);
		
		return localFilePath;
	}
}
