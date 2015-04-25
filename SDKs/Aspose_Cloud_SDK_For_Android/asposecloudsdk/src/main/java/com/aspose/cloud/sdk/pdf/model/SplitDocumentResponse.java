package com.aspose.cloud.sdk.pdf.model;

import com.aspose.cloud.sdk.common.BaseResponse;
import com.aspose.cloud.sdk.common.LinkModel;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class SplitDocumentResponse extends BaseResponse {
	@SerializedName("Result")
	public SplitResult result;
	
	public class SplitResult {
		public ArrayList<LinkResponse> Documents;
	}
	
	public class LinkResponse extends LinkModel {
		public int Id;
	}
}
