package com.aspose.cloud.sdk.words.api;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import android.net.Uri;

import com.aspose.cloud.sdk.common.AsposeApp;
import com.aspose.cloud.sdk.common.Utils;
import com.aspose.cloud.sdk.words.model.GetAllHyperlinksResponse;
import com.aspose.cloud.sdk.words.model.GetAllHyperlinksResponse.HypelinkData;
import com.aspose.cloud.sdk.words.model.GetSpecificHyperlinkResponse;
import com.aspose.cloud.sdk.words.model.GetSpecificHyperlinkResponse.HyperlinkDetail;
import com.google.gson.Gson;

/**
 * Hyperlink --- Using this class you can get all Hyperlinks from a Word document or or a particular Hyperlink from a Word document  
 * @author   M. Sohail Ismail
 */
public class Hyperlink {
	
	private static final String WORD_URI = AsposeApp.BASE_PRODUCT_URI + "/words/";
	
	/**
	 * Get all Hyperlinks from a Word document 
	 * @param fileName Name of the MS Word document on cloud
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return An object that contains array of Hyperlinks
	*/
	public static HypelinkData getAllHyperlinksFromAWordDocument(String fileName) throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		HypelinkData hyperlinks = null;
		
		if(fileName == null || fileName.length() <= 3) {
			throw new IllegalArgumentException("File name cannot be null or empty");
		}
		
		//build URL
      	String strURL = WORD_URI + Uri.encode(fileName) + "/hyperlinks";
        //sign URL
        String signedURL = Utils.sign(strURL);
        
        InputStream responseStream = Utils.processCommand(signedURL, "GET");
        String responseJSONString = Utils.streamToString(responseStream);
        
        //Parsing JSON
      	Gson gson = new Gson();
      	GetAllHyperlinksResponse hyperlinkResponse = gson.fromJson(responseJSONString, GetAllHyperlinksResponse.class);
		if(hyperlinkResponse.getCode().equals("200") && hyperlinkResponse.getStatus().equals("OK")) {
			hyperlinks = hyperlinkResponse.hyperlinks;
		}
		
		return hyperlinks;
	}
	
	/**
	 * Get a particular Hyperlink from a Word document
	 * @param fileName Name of the MS Word document on cloud
	 * @param hyperlinkIndex Index of the Hyperlink
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return An object that contains Hyperlink details like displayText, value and link
	*/
	public static HyperlinkDetail getAParticularHyperlinkFromAWordDocument(String fileName, int hyperlinkIndex) throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		
		HyperlinkDetail hyperlink = null;
		
		if(fileName == null || fileName.length() <= 3) {
			throw new IllegalArgumentException("File name cannot be null or empty");
		}
		
		//build URL
      	String strURL = WORD_URI + Uri.encode(fileName) + "/hyperlinks/" + hyperlinkIndex;
        //sign URL
        String signedURL = Utils.sign(strURL);
        
        InputStream responseStream = Utils.processCommand(signedURL, "GET");
        String responseJSONString = Utils.streamToString(responseStream);
        
        //Parsing JSON
      	Gson gson = new Gson();
      	GetSpecificHyperlinkResponse hyperlinkResponse = gson.fromJson(responseJSONString, GetSpecificHyperlinkResponse.class);
		if(hyperlinkResponse.getCode().equals("200") && hyperlinkResponse.getStatus().equals("OK")) {
			hyperlink = hyperlinkResponse.hyperlink;
		}
		
		return hyperlink;
	}
}
