package com.aspose.cloud.sdk.cells.model;

import java.util.ArrayList;

import com.aspose.cloud.sdk.common.BaseResponse;
import com.aspose.cloud.sdk.common.LinkModel;
import com.google.gson.annotations.SerializedName;

public class RowsResponse extends BaseResponse {
	@SerializedName("Rows")
	public RowsData rows;
	
	public class RowsData {
    	public int RowsCount;
    	public int MaxRow;
    	public LinkModel link;
    	public ArrayList<RowLink> RowsList;
    }
	
	public class RowLink {
		public LinkModel link;
	}
}
