package com.aspose.cloud.sdk.tasks.api;

import android.net.Uri;

import com.aspose.cloud.sdk.common.AsposeApp;
import com.aspose.cloud.sdk.common.BaseResponse;
import com.aspose.cloud.sdk.common.Utils;
import com.aspose.cloud.sdk.tasks.model.AddNewTaskResponseModel;
import com.aspose.cloud.sdk.tasks.model.GetAllTasksInProjectResponse;
import com.aspose.cloud.sdk.tasks.model.GetTaskInfoResponseModel;
import com.aspose.cloud.sdk.tasks.model.TaskItemModel;
import com.aspose.cloud.sdk.tasks.model.TaskModel;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

/**
 * Tasks --- Using this class you can retrieve all tasks in a project, get details of a particular task, 
 * add new task and delete existing task.
 * @author   M. Sohail Ismail
 */
public class Tasks {
	
	private static final String TASKS_URI = AsposeApp.BASE_PRODUCT_URI + "/tasks/";
	
	/**
	 * Get all tasks in a project
	 * @param srcProjectName Name of the MS Project Binary File
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return An array of project tasks
	*/
	public static ArrayList<TaskItemModel> getAllTasksInProject(String srcProjectName) throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		ArrayList<TaskItemModel> tasksArray = null;
		
		if(srcProjectName == null || srcProjectName.length() == 0) {
			throw new IllegalArgumentException("Source Project name cannot be null or empty");
		}
		
		//build URL
		String strURL = TASKS_URI + Uri.encode(srcProjectName) + "/tasks";
		//sign URL
		String signedURL = Utils.sign(strURL);
		InputStream responseStream = Utils.processCommand(signedURL, "GET");
		String jsonStr = Utils.streamToString(responseStream);
		
		//Parsing JSON
		Gson gson = new Gson();
		GetAllTasksInProjectResponse getTasksResponse = gson.fromJson(jsonStr, GetAllTasksInProjectResponse.class);
		if(getTasksResponse.getCode().equals("200") && getTasksResponse.getStatus().equals("OK")) {
			tasksArray = getTasksResponse.tasks.taskItemsArray;
		}
		
		return tasksArray;
	}
	
	/**
	 * Get detail information of a particular task
	 * @param srcProjectName Name of the MS Project Binary File
	 * @param taskUID UID of the task
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return Details of the task
	*/
	public static TaskModel getTaskInformation(String srcProjectName, int taskUID) throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		TaskModel taskInfo = null;
		
		if(srcProjectName == null || srcProjectName.length() == 0) {
			throw new IllegalArgumentException("Source Project name cannot be null or empty");
		}
		
		//build URL
		String strURL = TASKS_URI + Uri.encode(srcProjectName) + "/tasks/" + taskUID;
		//sign URL
		String signedURL = Utils.sign(strURL);
		InputStream responseStream = Utils.processCommand(signedURL, "GET");
		String jsonStr = Utils.streamToString(responseStream);
		
		//Parsing JSON
		Gson gson = new Gson();
		GetTaskInfoResponseModel getTaskInfo = gson.fromJson(jsonStr, GetTaskInfoResponseModel.class);
		if(getTaskInfo.getCode().equals("200") && getTaskInfo.getStatus().equals("OK")) {
			taskInfo = getTaskInfo.task;
		}
		
		return taskInfo;
	}
	
	/**
	 * Add new task to project
	 * @param srcProjectName Name of the MS Project Binary File
	 * @param taskName The Name of the new task
	 * @param beforeTaskId The id of the task to insert the new task before. 
	 * @param changedProjectName The name of the project document to save changes to
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return Details of newly added task
	*/
	public static TaskItemModel addATaskToProject(String srcProjectName, String taskName, int beforeTaskId, String changedProjectName) throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		TaskItemModel taskItem = null;
		
		if(srcProjectName == null || srcProjectName.length() == 0) {
			throw new IllegalArgumentException("Source Project name cannot be null or empty");
		}
		
		if(taskName == null || taskName.length() == 0) {
			throw new IllegalArgumentException("Task name cannot be null or empty");
		}
		
		String strURL;
		//changedProjectName is an optional parameter
		if(changedProjectName != null && changedProjectName.length() != 0) {
			//Changes will save to changedProjectName project document
			strURL = TASKS_URI + Uri.encode(srcProjectName) + "/tasks?taskName=" + Uri.encode(taskName) + "&beforeTaskId=" + beforeTaskId + "&fileName=" + Uri.encode(changedProjectName);
		} else {
			//changedProjectName is omitted so the changes will be saved to the source project document
			strURL = TASKS_URI + Uri.encode(srcProjectName) + "/tasks?taskName=" + Uri.encode(taskName) + "&beforeTaskId=" + beforeTaskId;
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
	 * Delete task from project
	 * @param srcProjectName Name of the MS Project Binary File
	 * @param taskId The Id of the task
	 * @param changedProjectName The name of the project document to save changes to
	 * @throws java.security.InvalidKeyException If initialization fails because the provided key is null.
	 * @throws java.security.NoSuchAlgorithmException If the specified algorithm (HmacSHA1) is not available by any provider.
	 * @throws java.io.IOException If there is an IO error
	 * @return Boolean variable indicates whether task deleted successfully
	*/
	public static boolean deleteATaskFromProject(String srcProjectName, int taskId, String changedProjectName) throws InvalidKeyException, NoSuchAlgorithmException, IOException {
		boolean isTaskDeletedSuccessfully = false;
		
		if(srcProjectName == null || srcProjectName.length() == 0) {
			throw new IllegalArgumentException("Source Project name cannot be null or empty");
		}
		
		//build URL
		String strURL = TASKS_URI + Uri.encode(srcProjectName) + "/tasks/" + taskId;
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
			isTaskDeletedSuccessfully = true;
		}
		return isTaskDeletedSuccessfully;
	}
}
