package com.aspose.cloud.sdk.pdf.model;

import com.aspose.cloud.sdk.common.BaseResponse;
import com.aspose.cloud.sdk.common.LinkModel;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class BookmarksResponse extends BaseResponse {
	
	@SerializedName("Bookmarks")
	public BookmarksResult bookmarks;
	
	public class BookmarksResult {
		public ArrayList<LinkModel> Links;
        public ArrayList<BookmarkDetails> List;
	}
}
