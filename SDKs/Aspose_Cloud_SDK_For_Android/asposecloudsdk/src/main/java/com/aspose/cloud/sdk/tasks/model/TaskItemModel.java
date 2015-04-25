package com.aspose.cloud.sdk.tasks.model;

import com.aspose.cloud.sdk.common.LinkModel;
import com.google.gson.annotations.SerializedName;

public class TaskItemModel {
	@SerializedName("Link")
	public LinkModel link;
	@SerializedName("Uid")
	public int uid;
	@SerializedName("Id")
	public int id;
	@SerializedName("Name")
	public String name;
	@SerializedName("Start")
	public String start;
	@SerializedName("Finish")
	public String finish;
	@SerializedName("Duration")
	public String duration;
}
