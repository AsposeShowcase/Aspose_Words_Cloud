package com.aspose.cloud.sdk.words.model;

import com.aspose.cloud.sdk.common.BaseResponse;
import com.aspose.cloud.sdk.common.LinkModel;
import com.google.gson.annotations.SerializedName;

public class TrackingChangesResponse extends BaseResponse {
	
	@SerializedName("Result")
	public TrackChangesResult result;
	
	public class TrackChangesResult {
		@SerializedName("Source")
		public LinkModel source;
		@SerializedName("Dest")
		public LinkModel destination;
	}
}
