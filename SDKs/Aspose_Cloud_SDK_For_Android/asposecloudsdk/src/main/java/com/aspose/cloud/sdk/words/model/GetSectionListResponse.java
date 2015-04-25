package com.aspose.cloud.sdk.words.model;

import java.util.ArrayList;

import com.aspose.cloud.sdk.common.BaseResponse;
import com.aspose.cloud.sdk.common.LinkModel;
import com.google.gson.annotations.SerializedName;

public class GetSectionListResponse extends BaseResponse {
	@SerializedName("Sections")
	public SectionList sections;
	
	public class SectionList {
		public LinkModel link;
		@SerializedName("SectionLinkList")
		public ArrayList<Link> sectionLinkList;
	}
	
	public class Link {
		public LinkModel link;
	}
}
