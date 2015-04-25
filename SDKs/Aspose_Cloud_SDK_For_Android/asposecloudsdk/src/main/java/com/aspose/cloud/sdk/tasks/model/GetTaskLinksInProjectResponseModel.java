package com.aspose.cloud.sdk.tasks.model;

import com.aspose.cloud.sdk.common.BaseResponse;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class GetTaskLinksInProjectResponseModel  extends BaseResponse {
	
	@SerializedName("TaskLinks")
	public ArrayList<TaskLinksModel> taskLinksArray;
}
