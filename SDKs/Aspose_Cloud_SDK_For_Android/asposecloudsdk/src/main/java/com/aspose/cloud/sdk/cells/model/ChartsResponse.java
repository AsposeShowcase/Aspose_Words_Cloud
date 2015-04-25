package com.aspose.cloud.sdk.cells.model;
import java.util.ArrayList;

import com.aspose.cloud.sdk.common.BaseResponse;
import com.aspose.cloud.sdk.common.LinkModel;
import com.google.gson.annotations.SerializedName;

    
public class ChartsResponse extends BaseResponse {        
	@SerializedName("Charts")
	public ChartsResult charts;
	
	public class ChartsResult {
		public ArrayList<ChartLink> ChartList;
		public LinkModel link;
	}
	
	public class ChartLink {
		public LinkModel link;
	}
}