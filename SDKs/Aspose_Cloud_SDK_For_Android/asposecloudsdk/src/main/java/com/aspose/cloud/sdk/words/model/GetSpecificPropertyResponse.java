package com.aspose.cloud.sdk.words.model;

import com.aspose.cloud.sdk.common.BaseResponse;
import com.aspose.cloud.sdk.common.LinkModel;
import com.google.gson.annotations.SerializedName;

public class GetSpecificPropertyResponse extends BaseResponse {
	
	@SerializedName("DocumentProperty")
    public DocumentPropertyValue documentProperty;
	
	public class DocumentPropertyValue {
		//The name of the property. Two properties cannot have the same name; attempts to create one would return a 400 BadRequest. 
		//BuiltIn property names cannot be changed; attempts to create one would return a 400 BadRequest 
		@SerializedName("Name")
		public String name; 
		@SerializedName("Value")
        public String value;
		@SerializedName("BuiltIn")
        public boolean builtIn; //A value indicating whether the property is a built-in type
        public LinkModel link;
	}
	
}
