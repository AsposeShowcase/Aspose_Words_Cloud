package com.aspose.cloud.sdk.words.model;

import java.util.ArrayList;

import com.aspose.cloud.sdk.common.BaseResponse;
import com.aspose.cloud.sdk.common.LinkModel;
import com.google.gson.annotations.SerializedName;

public class GetAllHyperlinksResponse extends BaseResponse {
	
	@SerializedName("Hyperlinks")
	public HypelinkData hyperlinks;
	
	public class HypelinkData {
		@SerializedName("HyperlinkList")
		public ArrayList<HyperlinkDetail> hyperlinkList;
		public LinkModel link;
	}
	
	public class HyperlinkDetail {
		@SerializedName("DisplayText")
		public String displayText;
		@SerializedName("Value")
		public String value;
		public LinkModel link;
	}
}
