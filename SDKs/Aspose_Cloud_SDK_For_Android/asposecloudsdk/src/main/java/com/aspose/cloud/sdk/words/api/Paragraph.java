package com.aspose.cloud.sdk.words.api;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import android.net.Uri;

import com.aspose.cloud.sdk.common.AsposeApp;
import com.aspose.cloud.sdk.common.Utils;
import com.aspose.cloud.sdk.words.model.GetFontInformationOfARunResponse;
import com.aspose.cloud.sdk.words.model.GetFontInformationOfARunResponse.Font;
import com.aspose.cloud.sdk.words.model.GetParagraphListResponse;
import com.aspose.cloud.sdk.words.model.GetParagraphListResponse.ParagraphsTextAndLink;
import com.aspose.cloud.sdk.words.model.GetRunOfAParagraphResponse;
import com.aspose.cloud.sdk.words.model.GetRunOfAParagraphResponse.Run;
import com.aspose.cloud.sdk.words.model.GetSpecificParagraphResponse;
import com.aspose.cloud.sdk.words.model.GetSpecificParagraphResponse.ParagraphDetail;
import com.google.gson.Gson;

/**
 * Paragraph --- Using this class you can get a list of all paragraphs from a word document, get a specific paragraph from a word document, 
 * get a specific run of a paragraph from a word document, get font information of a Run from a word document and update 
 * font of a run in a Word Document.  
 * @author   M. Sohail Ismail
 */
public class Paragraph {
	
	private static final String WORD_URI = AsposeApp.BASE_PRODUCT_URI + "/words/";
	
	/**
	 * Get a list of all paragraphs present in a Word document
	 * @param fileName Name of the MS Word document on cloud
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return An object that contains array of paragraph content
	*/
	public static ParagraphsTextAndLink getListOfAllParagraphsPresentInAWordDocument(String fileName) throws IOException, InvalidKeyException, NoSuchAlgorithmException {
		
		ParagraphsTextAndLink paragraphsList = null;
		
		if(fileName == null || fileName.length() <= 3) {
			throw new IllegalArgumentException("File name cannot be null or empty");
		}
		
		//build URL
      	String strURL = WORD_URI + Uri.encode(fileName) + "/paragraphs";
        //sign URL
        String signedURL = Utils.sign(strURL);
        
        InputStream responseStream = Utils.processCommand(signedURL, "GET");
        String responseJSONString = Utils.streamToString(responseStream);
        
        //Parsing JSON
      	Gson gson = new Gson();
      	GetParagraphListResponse paragraphListResponse = gson.fromJson(responseJSONString, GetParagraphListResponse.class);
		if(paragraphListResponse.getCode().equals("200") && paragraphListResponse.getStatus().equals("OK")) {
			paragraphsList = paragraphListResponse.paragraphs;
		}
		
		return paragraphsList;
	}
	
	/**
	 * Get a specific paragraph present in a Word document.
	 * @param fileName Name of the MS word document on cloud
	 * @param paragraphIndex Index of the paragraph in a document
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return An object that contains a paragraph content
	*/
	public static ParagraphDetail getASpecificParagraphPresentInAWordDocument(String fileName, int paragraphIndex) throws IOException, InvalidKeyException, NoSuchAlgorithmException {
		
		ParagraphDetail paragraph = null;
		
		if(fileName == null || fileName.length() <= 3) {
			throw new IllegalArgumentException("File name cannot be null or empty");
		}
		
		//build URL
		String strURL = WORD_URI + Uri.encode(fileName) + "/paragraphs/" + paragraphIndex;
        //sign URL
        String signedURL = Utils.sign(strURL);
        
        InputStream responseStream = Utils.processCommand(signedURL, "GET");
        String responseJSONString = Utils.streamToString(responseStream);
        
        //Parsing JSON
      	Gson gson = new Gson();
      	GetSpecificParagraphResponse specificParagraphResponse = gson.fromJson(responseJSONString, GetSpecificParagraphResponse.class);
		if(specificParagraphResponse.getCode().equals("200") && specificParagraphResponse.getStatus().equals("OK")) {
			paragraph = specificParagraphResponse.paragraph;
		}
		
		return paragraph;
	}
	
	/**
	 * Get a specific run of a paragraph present in a Word document
	 * @param fileName Name of the MS word document on cloud
	 * @param paragraphIndex Index of the paragraph in a document
	 * @param runIndex Index of run
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return An object that contains returned run text and link
	*/
	public static Run getASpecificRunOfAParagraphPresentInAWordDocument(String fileName, int paragraphIndex, int runIndex) throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		
		Run run = null;
		if(fileName == null || fileName.length() <= 3) {
			throw new IllegalArgumentException("File name cannot be null or empty");
		}
		
		//build URL
      	String strURL = WORD_URI + Uri.encode(fileName) + "/paragraphs/" + paragraphIndex + "/runs/" + runIndex;
        //sign URL
        String signedURL = Utils.sign(strURL);
        
        InputStream responseStream = Utils.processCommand(signedURL, "GET");
        String responseJSONString = Utils.streamToString(responseStream);
        
        //Parsing JSON
      	Gson gson = new Gson();
      	GetRunOfAParagraphResponse runOfAParagraphResponse = gson.fromJson(responseJSONString, GetRunOfAParagraphResponse.class);
		if(runOfAParagraphResponse.getCode().equals("200") && runOfAParagraphResponse.getStatus().equals("OK")) {
			run = runOfAParagraphResponse.run;
		}
		
		return run;
	}
	
	/**
	 * Get a font related information of a specific run of a paragraph present in a Word document
	 * @param fileName Name of the MS word document on cloud
	 * @param paragraphIndex Index of the paragraph in a document
	 * @param runIndex Index of run
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return An object that contains detailed font information
	*/
	public static Font getFontInformationOfARunFromAWordDocument(String fileName, int paragraphIndex, int runIndex) throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		Font font = null;
		
		if(fileName == null || fileName.length() <= 3) {
			throw new IllegalArgumentException("File name cannot be null or empty");
		}
		
		//build URL
      	String strURL = WORD_URI + Uri.encode(fileName) + "/paragraphs/" + paragraphIndex + "/runs/" + runIndex + "/font";
        //sign URL
        String signedURL = Utils.sign(strURL);
        
        InputStream responseStream = Utils.processCommand(signedURL, "GET");
        String responseJSONString = Utils.streamToString(responseStream);
        
        //Parsing JSON
      	Gson gson = new Gson();
      	GetFontInformationOfARunResponse fontInformationResponse = gson.fromJson(responseJSONString, GetFontInformationOfARunResponse.class);
		if(fontInformationResponse.getCode().equals("200") && fontInformationResponse.getStatus().equals("OK")) {
			font = fontInformationResponse.font;
		}
		
		return font;
	}
	
	/**
	 * Update font of a specific run of a paragraph present in a Word document
	 * @param fileName Name of the MS word document on cloud
	 * @param paragraphIndex Index of the paragraph in a document
	 * @param runIndex Index of run
	 * @param xmlData Font data in XML format used to update font of a specific run
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return An object that contains updated font data
	*/
	public static Font updateFontOfARunInAWordDocument(String fileName, int paragraphIndex, int runIndex, String xmlData) throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		Font font = null;
		
		if(fileName == null || fileName.length() <= 3) {
			throw new IllegalArgumentException("File name cannot be null or empty");
		}
		
		if(xmlData == null || xmlData.length() == 0) {
			throw new IllegalArgumentException("XML data cannot be null or empty");
		}
		
		//build URL
      	String strURL = WORD_URI + Uri.encode(fileName) + "/paragraphs/" + paragraphIndex + "/runs/" + runIndex + "/font";
        //sign URL
        String signedURL = Utils.sign(strURL);
        
        InputStream responseStream = Utils.processCommand(signedURL, "POST", xmlData, "xml");
        String responseJSONString = Utils.streamToString(responseStream);
        
        //Parsing JSON
      	Gson gson = new Gson();
      	GetFontInformationOfARunResponse updateFontInformationResponse = gson.fromJson(responseJSONString, GetFontInformationOfARunResponse.class);
		if(updateFontInformationResponse.getCode().equals("200") && updateFontInformationResponse.getStatus().equals("OK")) {
			font = updateFontInformationResponse.font;
		}
		
		return font;
	}
	
}
