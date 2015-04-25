package com.aspose.cloud.sdk.slides.model;

import com.aspose.cloud.sdk.common.BaseResponse;
import com.aspose.cloud.sdk.common.LinkModel;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class DocumentPropertiesResponse extends BaseResponse {
	@SerializedName("DocumentProperties")
	public DocumentPropertiesResult documentProperties;
	
	public class DocumentPropertiesResult {
		public ArrayList<DocumentProperty> List;
		public LinkModel SelfUri;
		public ArrayList<LinkModel> AlternateLinks;
		public ArrayList<LinkModel> Links;
	}
	
	public class DocumentProperty {
		public String Name;
		public String Value;
		public boolean BuiltIn;
	}
}
