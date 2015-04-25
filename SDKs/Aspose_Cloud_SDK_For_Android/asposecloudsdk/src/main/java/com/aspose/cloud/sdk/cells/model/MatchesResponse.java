package com.aspose.cloud.sdk.cells.model;

import com.aspose.cloud.sdk.common.BaseResponse;
import com.aspose.cloud.sdk.common.LinkModel;

public class MatchesResponse extends BaseResponse {
	public int Matches;
	public WorkbookLink Workbook;
	
	public class WorkbookLink {
		public LinkModel link;
	}
}
