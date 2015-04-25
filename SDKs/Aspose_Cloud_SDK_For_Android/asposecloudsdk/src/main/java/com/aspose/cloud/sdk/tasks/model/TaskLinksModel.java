package com.aspose.cloud.sdk.tasks.model;

import com.aspose.cloud.sdk.common.LinkModel;
import com.google.gson.annotations.SerializedName;

public class TaskLinksModel {
	@SerializedName("Link")
	public LinkModel link;
	@SerializedName("Index")
	public int index;
	@SerializedName("PredecessorUid")
	public int predecessorUid;
	@SerializedName("SuccessorUid")
	public int successorUid;
	@SerializedName("LinkType")
	public int linkType;
	@SerializedName("Lag")
	public int lag;
	@SerializedName("LagFormat")
	public int lagFormat;
}
