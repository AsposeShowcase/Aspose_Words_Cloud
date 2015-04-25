package com.aspose.cloud.sdk.pdf.model;

import com.aspose.cloud.sdk.common.BaseResponse;
import com.aspose.cloud.sdk.common.LinkModel;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class PagesResponse extends BaseResponse {
	@SerializedName("Pages")
	public PagesResult pages;
	
	public class PagesResult {
		public ArrayList<PageDetails> List;
		public ArrayList<LinkModel> Links;
	}
	
	public class PageDetails {
		public int Id;
		public ArrayList<LinkModel> Links;
	}
}
