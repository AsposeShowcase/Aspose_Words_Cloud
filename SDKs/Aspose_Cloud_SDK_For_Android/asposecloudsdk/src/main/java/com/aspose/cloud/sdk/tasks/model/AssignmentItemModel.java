package com.aspose.cloud.sdk.tasks.model;

import com.aspose.cloud.sdk.common.LinkModel;
import com.google.gson.annotations.SerializedName;

public class AssignmentItemModel {
	
	@SerializedName("Link")
	public LinkModel link;
	@SerializedName("Uid")
	public int uid;
	@SerializedName("TaskUid")
	public int taskUid;
	@SerializedName("ResourceUid")
	public int resourceUid;
}
