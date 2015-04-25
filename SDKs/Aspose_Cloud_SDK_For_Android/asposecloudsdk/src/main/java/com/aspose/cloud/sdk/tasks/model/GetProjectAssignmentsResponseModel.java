package com.aspose.cloud.sdk.tasks.model;

import com.aspose.cloud.sdk.common.BaseResponse;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class GetProjectAssignmentsResponseModel extends BaseResponse {
	
	@SerializedName("Assignments")
	public Assignment assignment;
	
	public class Assignment {
		@SerializedName("AssignmentItem")
		public ArrayList<AssignmentItemModel> assignmentsArray;
	}
}
