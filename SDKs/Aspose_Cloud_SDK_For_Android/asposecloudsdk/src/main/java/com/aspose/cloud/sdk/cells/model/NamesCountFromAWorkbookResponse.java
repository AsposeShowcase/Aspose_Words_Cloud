package com.aspose.cloud.sdk.cells.model;
import java.util.List;

import com.aspose.cloud.sdk.common.BaseResponse;
import com.aspose.cloud.sdk.common.LinkModel;
import com.google.gson.annotations.SerializedName;

public class NamesCountFromAWorkbookResponse extends BaseResponse {
	
	@SerializedName("Names")
	public NamesCountFromAWorkbookResult names;
	
	public class NamesCountFromAWorkbookResult {
		public LinkModel link;
		@SerializedName("Count")
		public int count;
		@SerializedName("NameList")
		public List<NameLink> nameList;
	}
	
	public class NameLink {
		public LinkModel link;
	}
}