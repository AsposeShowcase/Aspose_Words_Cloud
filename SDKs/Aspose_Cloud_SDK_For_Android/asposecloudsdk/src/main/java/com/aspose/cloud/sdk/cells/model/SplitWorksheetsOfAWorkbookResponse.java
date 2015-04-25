package com.aspose.cloud.sdk.cells.model;

import java.util.ArrayList;

import com.aspose.cloud.sdk.common.BaseResponse;
import com.aspose.cloud.sdk.common.LinkModel;
import com.google.gson.annotations.SerializedName;

public class SplitWorksheetsOfAWorkbookResponse extends BaseResponse {
	@SerializedName("Result")
	public SplitWorksheetsOfAWorkbookResult result;
	
	public class SplitWorksheetsOfAWorkbookResult {
		@SerializedName("Documents")
        public ArrayList<DocumentLink> documents;
	}
	
	public class DocumentLink {
		@SerializedName("Id")
        public int id;
        public LinkModel link;
	}
}
