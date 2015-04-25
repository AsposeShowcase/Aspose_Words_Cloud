package com.aspose.cloud.sdk.cells.model;

import com.aspose.cloud.sdk.common.BaseResponse;
import com.aspose.cloud.sdk.common.LinkModel;
import com.google.gson.annotations.SerializedName;
    
public class MergedCellResponse extends BaseResponse {   
	@SerializedName("MergedCell")
	public MergedCell mergedCell;
	
	public class MergedCell {
		public LinkModel link;
		public int StartRow;
		public int EndRow;
		public int StartColumn;
		public int EndColumn;
    }
	
}