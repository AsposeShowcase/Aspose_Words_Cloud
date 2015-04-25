package com.aspose.cloud.sdk.words.model;

import com.aspose.cloud.sdk.common.BaseResponse;
import com.aspose.cloud.sdk.common.LinkModel;
import com.google.gson.annotations.SerializedName;

public class GetProtectionOfTheWordDocumentResponse extends BaseResponse {
	
	@SerializedName("ProtectionData")
    public ProtectionData protectionData;
	
	@SerializedName("DocumentLink")
    public LinkModel documentLink;
	
	public class ProtectionData {
		@SerializedName("ProtectionType")
        public ProtectionTypeEnum protectionType;
	}
}
