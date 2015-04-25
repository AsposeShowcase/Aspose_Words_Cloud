package com.aspose.cloud.sdk.words.model;

import java.util.ArrayList;

import com.aspose.cloud.sdk.common.BaseResponse;
import com.aspose.cloud.sdk.common.LinkModel;
import com.google.gson.annotations.SerializedName;

public class GetSpecificParagraphResponse extends BaseResponse {
	@SerializedName("Paragraph")
	public ParagraphDetail paragraph;
	
	public class ParagraphDetail {
		@SerializedName("ChildNodes")
		public ArrayList<ChildNode> childNodes;
		public LinkModel link;
	}
	
	public class ChildNode {
		@SerializedName("Text")
		public String text;
		public LinkModel link;
	}
}
