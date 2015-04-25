package com.aspose.cloud.sdk.cells.model;

import com.aspose.cloud.sdk.common.BaseResponse;
import com.aspose.cloud.sdk.common.LinkModel;
import com.google.gson.annotations.SerializedName;

public class HyperlinkResponse extends BaseResponse {
	@SerializedName("Hyperlink")
	public HyperlinkData hyperlink;
	
	public class HyperlinkData {
		public String Address;
	    public CellArea Area;
	    public String TextToDisplay;
	    public String ScreenTip;
	    public LinkModel link;
	}
	
	public class CellArea {
		public int EndColumn;
	    public int EndRow;
	    public int StartColumn;
	    public int StartRow;
	}
}
