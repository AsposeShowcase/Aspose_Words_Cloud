package com.aspose.cloud.sdk.tasks.model;

import com.aspose.cloud.sdk.common.BaseResponse;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class GetAllTasksInProjectResponse extends BaseResponse {
	@SerializedName("Tasks")
	public Tasks tasks;
	
	public class Tasks {
		@SerializedName("TaskItem")
		public ArrayList<TaskItemModel> taskItemsArray;
	}
}
