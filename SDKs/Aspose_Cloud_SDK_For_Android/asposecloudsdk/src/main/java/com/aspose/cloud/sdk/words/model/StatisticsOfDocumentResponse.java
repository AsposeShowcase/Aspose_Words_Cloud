package com.aspose.cloud.sdk.words.model;

import java.util.ArrayList;

import com.aspose.cloud.sdk.common.BaseResponse;
import com.aspose.cloud.sdk.common.LinkModel;
import com.google.gson.annotations.SerializedName;

public class StatisticsOfDocumentResponse extends BaseResponse {
	
	@SerializedName("StatData")
	public StatData statData;
	@SerializedName("DocumentLink")
	public LinkModel documentLink;	
	
	public class StatData {
		@SerializedName("WordCount")
		public int wordCount;
		@SerializedName("ParagraphCount")
		public int paragraphCount;
		@SerializedName("PageCount")
		public int pageCount;
		@SerializedName("PageStatData")
		public ArrayList<PageStat> pageStatData;
	}
	
	public class PageStat {
		@SerializedName("PageNumber")
		public int pageNumber;
		@SerializedName("WordCount")
		public int wordCount;
		@SerializedName("ParagraphCount")
		public int paragraphCount;
	}
}
