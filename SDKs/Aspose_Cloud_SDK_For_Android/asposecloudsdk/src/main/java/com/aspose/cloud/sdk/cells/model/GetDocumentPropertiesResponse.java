package com.aspose.cloud.sdk.cells.model;

import java.util.ArrayList;

import com.aspose.cloud.sdk.common.BaseResponse;
import com.aspose.cloud.sdk.common.LinkModel;
import com.google.gson.annotations.SerializedName;

public class GetDocumentPropertiesResponse extends BaseResponse {
	@SerializedName("DocumentProperties")
	public DocumentPropertiesModel documentProperties;
	
	public class DocumentPropertiesModel {
		@SerializedName("DocumentPropertyList")
		public ArrayList<DocumentPropertiesValues> documentPropertiesList;
		public LinkModel link;
	}
	
	public class DocumentPropertiesValues {
		@SerializedName("Name")
		public String name;
		@SerializedName("Value")
        public String value;
		@SerializedName("BuiltIn")
        public boolean builtIn;
        public LinkModel link;
	}
}