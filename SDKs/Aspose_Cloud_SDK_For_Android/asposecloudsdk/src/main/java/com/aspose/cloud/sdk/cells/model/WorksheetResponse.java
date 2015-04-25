package com.aspose.cloud.sdk.cells.model;

import java.util.List;

import com.aspose.cloud.sdk.common.BaseResponse;
import com.aspose.cloud.sdk.common.LinkModel;
import com.google.gson.annotations.SerializedName;
   
public class WorksheetResponse extends BaseResponse
{
	@SerializedName("Worksheets")
    public WorksheetResult worksheets;
	
    public class WorksheetResult {
    	@SerializedName("WorksheetList")
    	public List<WorksheetLink> worksheetList;
        public LinkModel link;
    }
    
    public class WorksheetLink {
    	public LinkModel link;
    }
}