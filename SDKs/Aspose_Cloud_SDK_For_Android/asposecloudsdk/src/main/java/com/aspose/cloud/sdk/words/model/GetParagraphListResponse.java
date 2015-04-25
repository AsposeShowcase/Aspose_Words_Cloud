package com.aspose.cloud.sdk.words.model;

import java.util.ArrayList;

import com.aspose.cloud.sdk.common.BaseResponse;
import com.aspose.cloud.sdk.common.LinkModel;
import com.google.gson.annotations.SerializedName;

public class GetParagraphListResponse extends BaseResponse {
	@SerializedName("Paragraphs")
	public ParagraphsTextAndLink paragraphs;
	
	public class ParagraphsTextAndLink {
		@SerializedName("ParagraphLinkList")
		public ArrayList<ParagraphContent> paragraphLinkList;
		public LinkModel link;
	}
	
	public class ParagraphContent {
		@SerializedName("Text")
		public String text;
		public LinkModel link;
	}
}
