package com.aspose.cloud.sdk.tasks.model;

import com.aspose.cloud.sdk.common.BaseResponse;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class GetProjectResourcesResponseModel extends BaseResponse {
	@SerializedName("Resources")
	public Resources resources;
	
	public class Resources {
		@SerializedName("ResourceItem")
		public ArrayList<ResourceItemModel> resourceItemsArray;
	}
}
