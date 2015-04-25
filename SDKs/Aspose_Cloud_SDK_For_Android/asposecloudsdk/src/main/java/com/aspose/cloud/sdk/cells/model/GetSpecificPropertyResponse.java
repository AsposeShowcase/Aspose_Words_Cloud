package com.aspose.cloud.sdk.cells.model;

import com.aspose.cloud.sdk.common.BaseResponse;
import com.aspose.cloud.sdk.common.LinkModel;
import com.google.gson.annotations.SerializedName;

public class GetSpecificPropertyResponse extends BaseResponse {
	
	@SerializedName("DocumentProperty")
	public DocumentPropertyValue documentProperty;
	
	public class DocumentPropertyValue {
		@SerializedName("Name")
		public String name;
		@SerializedName("Value")
        public String value;
		@SerializedName("BuiltIn")
        public boolean builtIn;
        public LinkModel link;
	}
	
}
