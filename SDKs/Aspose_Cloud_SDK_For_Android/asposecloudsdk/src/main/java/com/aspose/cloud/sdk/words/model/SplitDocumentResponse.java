package com.aspose.cloud.sdk.words.model;

import java.util.ArrayList;

import com.aspose.cloud.sdk.common.BaseResponse;
import com.aspose.cloud.sdk.common.LinkModel;
import com.google.gson.annotations.SerializedName;

public class SplitDocumentResponse extends BaseResponse {
	@SerializedName("SplitResult")
	public SplitResultModel splitResult;
	
	public class SplitResultModel {
		@SerializedName("SourceDocument")
		public LinkModel sourceDocument;
		@SerializedName("Pages")
		public ArrayList<LinkModel> pages;
	}
}
