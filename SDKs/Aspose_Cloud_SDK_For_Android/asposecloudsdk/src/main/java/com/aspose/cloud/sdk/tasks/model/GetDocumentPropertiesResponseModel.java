package com.aspose.cloud.sdk.tasks.model;

import com.aspose.cloud.sdk.common.BaseResponse;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class GetDocumentPropertiesResponseModel extends BaseResponse{
	@SerializedName("Properties")
	public PropertiesList properties;
	
	public class PropertiesList {
		@SerializedName("List")
        public ArrayList<ProjectPropertyModel> list;
	}
}
