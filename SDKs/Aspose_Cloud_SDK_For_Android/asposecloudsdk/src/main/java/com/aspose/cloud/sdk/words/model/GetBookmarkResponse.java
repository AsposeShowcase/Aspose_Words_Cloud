package com.aspose.cloud.sdk.words.model;

import java.util.ArrayList;

import com.aspose.cloud.sdk.common.BaseResponse;
import com.aspose.cloud.sdk.common.LinkModel;
import com.google.gson.annotations.SerializedName;

public class GetBookmarkResponse extends BaseResponse {
	@SerializedName("Bookmarks")
	public BookmarksEnvelop bookmarks;
	
	public class BookmarksEnvelop {
		@SerializedName("BookmarkList")
		public ArrayList<BookmarkDetail> bookmarkList; 
		public LinkModel link;
	}
	
	public class BookmarkDetail {
		public LinkModel link;
		@SerializedName("Name")
		public String name;
		@SerializedName("Text")
		public String text;
	}
}
