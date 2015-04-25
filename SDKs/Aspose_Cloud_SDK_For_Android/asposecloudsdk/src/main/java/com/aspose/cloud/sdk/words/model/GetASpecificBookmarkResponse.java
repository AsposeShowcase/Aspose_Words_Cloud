package com.aspose.cloud.sdk.words.model;

import com.aspose.cloud.sdk.common.BaseResponse;
import com.aspose.cloud.sdk.common.LinkModel;
import com.google.gson.annotations.SerializedName;

public class GetASpecificBookmarkResponse extends BaseResponse {
	
	@SerializedName("Bookmark")
	public BookmarkEnvelop bookmark;
	
	public class BookmarkEnvelop {
		public LinkModel link;
		@SerializedName("Name")
		public String name;
		@SerializedName("Text")
		public String text;
	}
}
