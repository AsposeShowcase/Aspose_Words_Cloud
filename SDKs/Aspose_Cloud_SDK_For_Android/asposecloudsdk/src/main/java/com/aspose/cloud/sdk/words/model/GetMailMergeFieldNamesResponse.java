package com.aspose.cloud.sdk.words.model;

import java.util.ArrayList;

import com.aspose.cloud.sdk.common.BaseResponse;
import com.aspose.cloud.sdk.common.LinkModel;
import com.google.gson.annotations.SerializedName;

public class GetMailMergeFieldNamesResponse extends BaseResponse {
	@SerializedName("FieldNames")
	public FieldName fieldName;
	
	public class FieldName {
		@SerializedName("Names")
		public ArrayList<String> names;
		public LinkModel link;
	}
}
