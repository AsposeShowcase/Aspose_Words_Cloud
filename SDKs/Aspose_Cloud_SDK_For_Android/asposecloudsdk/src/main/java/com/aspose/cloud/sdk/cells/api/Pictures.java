package com.aspose.cloud.sdk.cells.api;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import android.net.Uri;

import com.aspose.cloud.sdk.cells.model.PicturesResponse;
import com.aspose.cloud.sdk.cells.model.PicturesResponse.PictureData;
import com.aspose.cloud.sdk.cells.model.ValidFormatsOfWorksheet;
import com.aspose.cloud.sdk.common.AsposeApp;
import com.aspose.cloud.sdk.common.BaseResponse;
import com.aspose.cloud.sdk.common.Utils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Pictures --- Using this class you can get a specific picture from a worksheet, Convert a picture to designated format, 
 * Add a picture to a worksheet, Update a specific picture from a worksheet and Delete pictures from a worksheet.
 * @author   M. Sohail Ismail
 */
public class Pictures {
	private static final String CELLS_URI = AsposeApp.BASE_PRODUCT_URI + "/cells/";
	
	private String fileName;
	private String worksheetName;
	
	public Pictures(String fileName, String worksheetName) {
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
	 * Get a specific picture from a worksheet
	 * @param pictureIndex Picture index 
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return An object that contains attributes of picture
	*/ 
	public PictureData getPictureFromAWorksheet(int pictureIndex) throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		
		PictureData picture = null;
		
		if(fileName == null || fileName.length() == 0) {
			throw new IllegalArgumentException("File name cannot be null or empty");
		}
		
		if(worksheetName == null || worksheetName.length() == 0) {
			throw new IllegalArgumentException("Worksheet name cannot be null or empty");
		}
		
		String strURL = CELLS_URI + fileName + "/worksheets/" + worksheetName + "/pictures/" + pictureIndex;
        //sign URL
        String signedURL = Utils.sign(strURL);
        
		InputStream responseStream = Utils.processCommand(signedURL, "GET");
		String responseJSONString = Utils.streamToString(responseStream);
        
        //Parsing JSON
		Gson gson = new Gson();
		PicturesResponse picturesResponse = gson.fromJson(responseJSONString, PicturesResponse.class);
		if (picturesResponse.getCode().equals("200") && picturesResponse.getStatus().equals("OK")) {
			picture = picturesResponse.picture;
		}
		
		return picture;
	}
	
	/**
	 * Convert a picture to image
	 * @param pictureIndex Picture index 
	 * @param designatedFormat Convert image to this specified format
	 * @param outputFileName Converted image will save on device with this name
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return Path to converted picture saved on device
	*/ 
	public String convertPictureToImage(int pictureIndex, ValidFormatsOfWorksheet designatedFormat, String outputFileName) throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		
		String localFilePath = null;
		
		if(fileName == null || fileName.length() == 0) {
			throw new IllegalArgumentException("File name cannot be null or empty");
		}
		
		if(worksheetName == null || worksheetName.length() == 0) {
			throw new IllegalArgumentException("Worksheet name cannot be null or empty");
		}
		
		if(designatedFormat == null) {
			throw new IllegalArgumentException("Designated format cannot be null");
		}
		
		if(outputFileName == null || outputFileName.length() == 0) {
			throw new IllegalArgumentException("Output file name cannot be null or empty");
		}
		
		String strURL = CELLS_URI + fileName + "/worksheets/" + worksheetName + 
				"/pictures/" + pictureIndex + "?format=" + designatedFormat;
        //sign URL
        String signedURL = Utils.sign(strURL);
        
		InputStream responseStream = Utils.processCommand(signedURL, "GET");
		
		//Save the stream in response to the disk
		localFilePath = Utils.saveStreamToFile(responseStream, outputFileName);
				
		return localFilePath;
	}
	
	/**
	 * Add a picture to a worksheet
	 * @param upperLeftRow New image left row position
	 * @param upperLeftColumn New image left column position
	 * @param lowerRightRow New image right row position
	 * @param lowerRightColumn New image right column position
	 * @param picturePath The picture file path at storage
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return An object that contains picture attributes
	*/ 
	public PictureData addPicturesToExcelWorksheet(int upperLeftRow, int upperLeftColumn, int lowerRightRow, 
			int lowerRightColumn, String picturePath) throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		
		PictureData picture = null;
		
		if(fileName == null || fileName.length() == 0) {
			throw new IllegalArgumentException("File name cannot be null or empty");
		}
		
		if(worksheetName == null || worksheetName.length() == 0) {
			throw new IllegalArgumentException("Worksheet name cannot be null or empty");
		}
		
		if(picturePath == null || picturePath.length() == 0) {
			throw new IllegalArgumentException("Picture path cannot be null or empty");
		}
		
		String strURL = CELLS_URI + fileName + "/worksheets/" + worksheetName + "/pictures?" + 
				"upperLeftRow=" + upperLeftRow + "&upperLeftColumn=" + upperLeftColumn + "&lowerRightRow=" + lowerRightRow + 
				"&lowerRightColumn=" + lowerRightColumn + "&picturePath=" + Uri.encode(picturePath);
		
        //sign URL
        String signedURL = Utils.sign(strURL);
        
		InputStream responseStream = Utils.processCommand(signedURL, "PUT");
		String responseJSONString = Utils.streamToString(responseStream);
        
        //Parsing JSON
		Gson gson = new Gson();
		PicturesResponse picturesResponse = gson.fromJson(responseJSONString, PicturesResponse.class);
		if (picturesResponse.getCode().equals("200") && picturesResponse.getStatus().equals("OK")) {
			picture = picturesResponse.picture;
		}
		
		return picture;
	}
	
	/**
	 * Update a specific picture from a worksheet
	 * @param pictureIndex Picture index
	 * @param pictureRequest Request for picture
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return An object that contains picture attributes
	*/
	public PictureData updateASpecificPictureFromExcelWorksheet(int pictureIndex, PicturesResponse pictureRequest) throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		
		PictureData picture = null;
		
		if(fileName == null || fileName.length() == 0) {
			throw new IllegalArgumentException("File name cannot be null or empty");
		}
		
		if(worksheetName == null || worksheetName.length() == 0) {
			throw new IllegalArgumentException("Worksheet name cannot be null or empty");
		}
		
		String strURL = CELLS_URI + fileName + "/worksheets/" + worksheetName + "/pictures/" + pictureIndex;
        //sign URL
        String signedURL = Utils.sign(strURL);
        
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        String requestJSONString = gson.toJson(pictureRequest, PicturesResponse.class);
        
		InputStream responseStream = Utils.processCommand(signedURL, "POST", requestJSONString);
		String responseJSONString = Utils.streamToString(responseStream);
        
        //Parsing JSON
		PicturesResponse picturesResponse = gson.fromJson(responseJSONString, PicturesResponse.class);
		if (picturesResponse.getCode().equals("200") && picturesResponse.getStatus().equals("OK")) {
			picture = picturesResponse.picture;
		}
		
		return picture;
	}
	
	/**
	 * Delete all pictures from a worksheet
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return Boolean variable that indicates whether all pictures deleted successfully from a worksheet
	*/
	public boolean deleteAllPicturesFromExcelWorksheet() throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		
		boolean isAllPicturesDeletedSuccessfully = false;
		
		if(fileName == null || fileName.length() == 0) {
			throw new IllegalArgumentException("File name cannot be null or empty");
		}
		
		if(worksheetName == null || worksheetName.length() == 0) {
			throw new IllegalArgumentException("Worksheet name cannot be null or empty");
		}
		
		String strURL = CELLS_URI + fileName + "/worksheets/" + worksheetName + "/pictures";
        //sign URL
        String signedURL = Utils.sign(strURL);
        
		InputStream responseStream = Utils.processCommand(signedURL, "DELETE");
		String responseJSONString = Utils.streamToString(responseStream);
        
        //Parsing JSON
		Gson gson = new Gson();
		BaseResponse baseResponse = gson.fromJson(responseJSONString, BaseResponse.class);
		if (baseResponse.getCode().equals("200") && baseResponse.getStatus().equals("OK")) {
			isAllPicturesDeletedSuccessfully = true;
		}
		
		return isAllPicturesDeletedSuccessfully;
	}
	
	/**
	 * Delete a specific picture from a worksheet
	 * @param pictureIndex Picture index
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return Boolean variable that indicates whether a picture deleted successfully from a worksheet
	*/
	public boolean deleteASpecificPictureFromExcelWorksheet(int pictureIndex) throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		
		boolean isAPictureDeletedSuccessfully = false;
		
		if(fileName == null || fileName.length() == 0) {
			throw new IllegalArgumentException("File name cannot be null or empty");
		}
		
		if(worksheetName == null || worksheetName.length() == 0) {
			throw new IllegalArgumentException("Worksheet name cannot be null or empty");
		}
		
		String strURL = CELLS_URI + fileName + "/worksheets/" + worksheetName + "/pictures/" + pictureIndex;
        //sign URL
        String signedURL = Utils.sign(strURL);
        
		InputStream responseStream = Utils.processCommand(signedURL, "DELETE");
		String responseJSONString = Utils.streamToString(responseStream);
        
        //Parsing JSON
		Gson gson = new Gson();
		BaseResponse baseResponse = gson.fromJson(responseJSONString, BaseResponse.class);
		if (baseResponse.getCode().equals("200") && baseResponse.getStatus().equals("OK")) {
			isAPictureDeletedSuccessfully = true;
		}
		
		return isAPictureDeletedSuccessfully;
	}
}
