package com.aspose.cloud.sdk.tasks.api;

import android.net.Uri;

import com.aspose.cloud.sdk.common.AsposeApp;
import com.aspose.cloud.sdk.common.Utils;
import com.aspose.cloud.sdk.tasks.model.AssignmentItemModel;
import com.aspose.cloud.sdk.tasks.model.GetProjectAssignmentsResponseModel;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

/**
 * Assignments --- Using this class you can retrieve all calendar items in a project 
 * @author   M. Sohail Ismail
 */
public class Assignments {
	
	private static final String TASKS_URI = AsposeApp.BASE_PRODUCT_URI + "/tasks/";
	
	/**
	 * Get all calendar items in a project
	 * @param srcProjectName Name of the MS Project Binary File
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return An array of calendar items in a project
	*/
	public static ArrayList<AssignmentItemModel> getAllAssignmentItemsInProject(String srcProjectName) throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		ArrayList<AssignmentItemModel> assignmentsArray = null;
		
		if(srcProjectName == null || srcProjectName.length() == 0) {
			throw new IllegalArgumentException("Source Project name cannot be null or empty");
		}
		
		//build URL
		String strURL = TASKS_URI + Uri.encode(srcProjectName) + "/assignments";
		String signedURL = Utils.sign(strURL);
		InputStream responseStream = Utils.processCommand(signedURL, "GET");
		String jsonStr = Utils.streamToString(responseStream);
		
		//Parsing JSON
		Gson gson = new Gson();
		GetProjectAssignmentsResponseModel assignmentItemsResponse = gson.fromJson(jsonStr, GetProjectAssignmentsResponseModel.class);
		if(assignmentItemsResponse.getCode().equals("200") && assignmentItemsResponse.getStatus().equals("OK")) {
			assignmentsArray = assignmentItemsResponse.assignment.assignmentsArray;
		}
		
		return assignmentsArray;
	}
	
}
