package com.aspose.cloud.sdk.tasks.model;

import com.aspose.cloud.sdk.common.BaseResponse;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ProjectExtendedAttributesResponseModel extends BaseResponse {
	
	@SerializedName("ExtendedAttributes")
	public ExtendedAttribute extendedAttribute;
	
	public class ExtendedAttribute {
		@SerializedName("List")
		public ArrayList<ExtendedAttributeItemModel> extendedAttributesArray;
	}
}
