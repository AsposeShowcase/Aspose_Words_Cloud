package com.aspose.cloud.sdk.cells.model;

import com.aspose.cloud.sdk.common.BaseResponse;
import com.google.gson.annotations.SerializedName;

public class GetColumnFromAWorksheetResponse extends BaseResponse {
	@SerializedName("Column")
	public Column column;
    
    public class Column {
    	public LinkResponse link;
    	public int Index;
    	public int GroupLevel;
    	public double Width;
    	public boolean IsHidden;
    }
}