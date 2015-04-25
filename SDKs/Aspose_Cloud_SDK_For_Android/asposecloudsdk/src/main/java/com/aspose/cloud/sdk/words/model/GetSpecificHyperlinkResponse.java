package com.aspose.cloud.sdk.words.model;

import com.aspose.cloud.sdk.common.BaseResponse;
import com.aspose.cloud.sdk.common.LinkModel;
import com.google.gson.annotations.SerializedName;

public class GetSpecificHyperlinkResponse extends BaseResponse {
	
	@SerializedName("Hyperlink")
    public HyperlinkDetail hyperlink;
	
	public class HyperlinkDetail {
		@SerializedName("DisplayText")
		public String displayText;
		@SerializedName("Value")
		public String value;
		public LinkModel link;
	}
}
