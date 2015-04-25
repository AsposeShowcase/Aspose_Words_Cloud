package com.aspose.cloud.sdk.cells.model;

import com.aspose.cloud.sdk.common.BaseResponse;
import com.aspose.cloud.sdk.common.LinkModel;
import com.google.gson.annotations.SerializedName;

public class CellsResponse extends BaseResponse {
	
	@SerializedName("Cell")
	public CellData cell;
    
    public class CellData {   
    	public LinkModel link;
    	public String Name;
    	public String Type;
    	public int Row;
    	public int Column;
    	public String Value;
    	public String Formula;
    	public boolean IsFormula;
    	public boolean IsMerged;
    	@SerializedName("Style")
    	public Style style;
    }   
    
    public class Style {
    	public LinkModel link;
    }
}