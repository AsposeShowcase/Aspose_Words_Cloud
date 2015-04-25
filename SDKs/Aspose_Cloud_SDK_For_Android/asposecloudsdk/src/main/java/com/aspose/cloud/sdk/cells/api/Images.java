package com.aspose.cloud.sdk.cells.api;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import android.net.Uri;

import com.aspose.cloud.sdk.cells.model.ValidFormatsOfWorksheet;
import com.aspose.cloud.sdk.common.AsposeApp;
import com.aspose.cloud.sdk.common.Utils;

/**
 * Images --- Using this class you can convert an autoshape to image.
 * @author   M. Sohail Ismail
 */
public class Images {
	private static final String CELLS_URI = AsposeApp.BASE_PRODUCT_URI + "/cells/";
	
	private String fileName;
	private String worksheetName;
	
	public Images(String fileName, String worksheetName) {
		this.fileName = Uri.encode(fileName);
		this.worksheetName = Uri.encode(worksheetName);
	}
	
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	public void setWorksheetName(String worksheetName) {
		this.worksheetName = worksheetName;
	}
	
	/**
	 * Convert an autoshape to image
	 * @param autoShapeIndex Autoshape Index
	 * @param designatedFormat Convert the autoshape to this format
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return An object that contains cell attributes
	*/ 
	public String convertAutoShapeToImage(int autoShapeIndex, ValidFormatsOfWorksheet designatedFormat, String outFileName) throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		
		String localFilePath = null;
		
		if(fileName == null || fileName.length() == 0) {
			throw new IllegalArgumentException("File name cannot be null or empty");
		}
		
		if(worksheetName == null || worksheetName.length() == 0) {
			throw new IllegalArgumentException("Worksheet name cannot be null or empty");
		}
		
		String strURL = CELLS_URI + fileName + "/worksheets/" + worksheetName 
				+ "/autoshapes/" + autoShapeIndex + "?format=" + designatedFormat;
        //sign URL
        String signedURL = Utils.sign(strURL);
        
		InputStream responseStream = Utils.processCommand(signedURL, "GET");
        
		//Save file on Disk
		localFilePath = Utils.saveStreamToFile(responseStream, outFileName);
		return localFilePath;
	}
}
