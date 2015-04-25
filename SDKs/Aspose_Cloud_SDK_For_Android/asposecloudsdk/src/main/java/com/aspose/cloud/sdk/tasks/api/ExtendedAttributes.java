package com.aspose.cloud.sdk.tasks.api;

import android.net.Uri;

import com.aspose.cloud.sdk.common.AsposeApp;
import com.aspose.cloud.sdk.common.Utils;
import com.aspose.cloud.sdk.tasks.model.ExtendedAttributeItemModel;
import com.aspose.cloud.sdk.tasks.model.ProjectExtendedAttributesResponseModel;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

/**
 * ExtendedAttributes --- Using this class you can retrieve all extended attributes in a project
 * @author   M. Sohail Ismail
 */
public class ExtendedAttributes {
	
	private static final String TASKS_URI = AsposeApp.BASE_PRODUCT_URI + "/tasks/";
	
	/**
	 * Get all extended attributes in a project
	 * @param srcProjectName Name of the MS Project Binary File
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return An array of extended attributes in a project
	*/
	public static ArrayList<ExtendedAttributeItemModel> getProjectExtendedAttributes(String srcProjectName) throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		ArrayList<ExtendedAttributeItemModel> extendedAttributesArray = null;
		
		if(srcProjectName == null || srcProjectName.length() == 0) {
			throw new IllegalArgumentException("Source Project name cannot be null or empty");
		}
		
		//build URL
		String strURL = TASKS_URI + Uri.encode(srcProjectName) + "/extendedAttributes";
		String signedURL = Utils.sign(strURL);
		InputStream responseStream = Utils.processCommand(signedURL, "GET");
		String jsonStr = Utils.streamToString(responseStream);
		
		//Parsing JSON
		Gson gson = new Gson();
		ProjectExtendedAttributesResponseModel projectExtendedAttributesResponse = gson.fromJson(jsonStr, ProjectExtendedAttributesResponseModel.class);
		if(projectExtendedAttributesResponse.getCode().equals("200") && projectExtendedAttributesResponse.getStatus().equals("OK")) {
			extendedAttributesArray = projectExtendedAttributesResponse.extendedAttribute.extendedAttributesArray;
		}
		
		return extendedAttributesArray;
	}
	
}
