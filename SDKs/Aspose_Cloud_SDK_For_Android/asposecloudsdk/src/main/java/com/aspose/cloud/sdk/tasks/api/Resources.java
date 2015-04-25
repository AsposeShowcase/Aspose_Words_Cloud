package com.aspose.cloud.sdk.tasks.api;

import android.net.Uri;

import com.aspose.cloud.sdk.common.AsposeApp;
import com.aspose.cloud.sdk.common.BaseResponse;
import com.aspose.cloud.sdk.common.Utils;
import com.aspose.cloud.sdk.tasks.model.AddNewTaskResponseModel;
import com.aspose.cloud.sdk.tasks.model.GetProjectResourcesResponseModel;
import com.aspose.cloud.sdk.tasks.model.GetResourceInfoResponseModel;
import com.aspose.cloud.sdk.tasks.model.ResourceItemModel;
import com.aspose.cloud.sdk.tasks.model.ResourceModel;
import com.aspose.cloud.sdk.tasks.model.TaskItemModel;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

/**
 * Resources --- Using this class you can retrieve all resources in a project, get details of a particular resource, 
 * add new resource and delete existing resource.
 * @author   M. Sohail Ismail
 */
public class Resources {
	
	private static final String TASKS_URI = AsposeApp.BASE_PRODUCT_URI + "/tasks/";
	
	/**
	 * Get all resources in a project
	 * @param srcProjectName Name of the MS Project Binary File
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return An array of project resources
	*/
	public static ArrayList<ResourceItemModel> getAllResourceItemsOfProject(String srcProjectName) throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		ArrayList<ResourceItemModel> resourcesArray = null;
		
		if(srcProjectName == null || srcProjectName.length() == 0) {
			throw new IllegalArgumentException("Source Project name cannot be null or empty");
		}
		
		//build URL
		String strURL = TASKS_URI + Uri.encode(srcProjectName) + "/resources";
		String signedURL = Utils.sign(strURL);
		InputStream responseStream = Utils.processCommand(signedURL, "GET");
		String jsonStr = Utils.streamToString(responseStream);
		
		//Parsing JSON
		Gson gson = new Gson();
		GetProjectResourcesResponseModel getProjRscItems = gson.fromJson(jsonStr, GetProjectResourcesResponseModel.class);
		if(getProjRscItems.getCode().equals("200") && getProjRscItems.getStatus().equals("OK")) {
			resourcesArray = getProjRscItems.resources.resourceItemsArray;
		}
		
		return resourcesArray;
	}
	
	/**
	 * Get detail information of a particular resource
	 * @param srcProjectName Name of the MS Project Binary File
	 * @param resourceUID UID of the resource
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return Details of the resource
	*/
	public static ResourceModel getResourceInformation(String srcProjectName, int resourceUID) throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		
		ResourceModel resource = null;
		
		if(srcProjectName == null || srcProjectName.length() == 0) {
			throw new IllegalArgumentException("Source Project name cannot be null or empty");
		}
		
		//build URL
		String strURL = TASKS_URI + Uri.encode(srcProjectName) + "/resources/" + resourceUID;
		//sign URL
		String signedURL = Utils.sign(strURL);
		InputStream responseStream = Utils.processCommand(signedURL, "GET");
		String jsonStr = Utils.streamToString(responseStream);
		
		//Parsing JSON
		Gson gson = new Gson();
		GetResourceInfoResponseModel getResourceInfo = gson.fromJson(jsonStr, GetResourceInfoResponseModel.class);
		if(getResourceInfo.getCode().equals("200") && getResourceInfo.getStatus().equals("OK")) {
			resource = getResourceInfo.resource;
		}
		
		return resource;
	}
	
	/**
	 * Add new resource to project
	 * @param srcProjectName Name of the MS Project Binary File
	 * @param resourceName The Name of the new resource
	 * @param beforeResourceId The id of the resource to insert the new resource before. 
	 * @param changedProjectName The name of the project document to save changes to
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return Details of newly added resource item
	*/
	public static TaskItemModel addAResourceToProject(String srcProjectName, String resourceName, int beforeResourceId, String changedProjectName) throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		TaskItemModel taskItem = null;
		
		if(srcProjectName == null || srcProjectName.length() == 0) {
			throw new IllegalArgumentException("Source Project name cannot be null or empty");
		}
		
		if(resourceName == null || resourceName.length() == 0) {
			throw new IllegalArgumentException("Resource name cannot be null or empty");
		}
		
		String strURL = TASKS_URI + Uri.encode(srcProjectName) + "/resources?resourceName=" + Uri.encode(resourceName) + "&beforeResourceId=" + beforeResourceId;
		//changedProjectName is an optional parameter
		if(changedProjectName != null && changedProjectName.length() != 0) {
			//Changes will save to changedProjectName project document
			strURL += "&fileName=" + Uri.encode(changedProjectName);
		}
		
		//sign URL
		String signedURL = Utils.sign(strURL);
		InputStream responseStream = Utils.processCommand(signedURL, "POST");
		String jsonStr = Utils.streamToString(responseStream);
		
		//Parsing JSON
		Gson gson = new Gson();
		AddNewTaskResponseModel addNewItemResponse = gson.fromJson(jsonStr, AddNewTaskResponseModel.class);
		if(addNewItemResponse.getCode().equals("201") && addNewItemResponse.getStatus().equals("Created")) {
			taskItem = addNewItemResponse.taskItem;
		}
		
		return taskItem;
	}
	
	/**
	 * Delete resource from project
	 * @param srcProjectName Name of the MS Project Binary File
	 * @param resourceId The Id of the resource
	 * @param changedProjectName The name of the project document to save changes to
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return Boolean variable indicates whether resource deleted successfully
	*/
	public static boolean deleteAResourceFromProject(String srcProjectName, int resourceId, String changedProjectName) throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		boolean isResourceDeletedSuccessfully = false;
		
		if(srcProjectName == null || srcProjectName.length() == 0) {
			throw new IllegalArgumentException("Source Project name cannot be null or empty");
		}
		
		//build URL
		String strURL = TASKS_URI + Uri.encode(srcProjectName) + "/resources/" + resourceId;
		//changedProjectName is an optional parameter
		if(changedProjectName != null && changedProjectName.length() != 0) {
			//Changes will save to changedProjectName project document
			strURL += "?fileName=" + Uri.encode(changedProjectName);
		}
		String signedURL = Utils.sign(strURL);
		InputStream responseStream = Utils.processCommand(signedURL, "DELETE");
		String jsonStr = Utils.streamToString(responseStream);
		
		//Parsing JSON
		Gson gson = new Gson();
		BaseResponse baseResponse = gson.fromJson(jsonStr, BaseResponse.class);
		if(baseResponse.getCode().equals("200") && baseResponse.getStatus().equals("OK")) {
			isResourceDeletedSuccessfully = true;
		}
		return isResourceDeletedSuccessfully;
	}
}
