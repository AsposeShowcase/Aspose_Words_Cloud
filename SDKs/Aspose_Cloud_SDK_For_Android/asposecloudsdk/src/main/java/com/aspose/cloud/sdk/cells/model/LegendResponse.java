package com.aspose.cloud.sdk.cells.model;

import com.aspose.cloud.sdk.common.BaseResponse;
import com.aspose.cloud.sdk.common.LinkModel;
import com.google.gson.annotations.SerializedName;

public class LegendResponse extends BaseResponse {
	@SerializedName("Legend")
	public LegendData legend;
	
	public class LegendData {
		public LegendPositionEnum Position;
		public LegendLink LegendEntries;
		public AreaModel Area;
		public boolean AutoScaleFont;
	    public String BackgroundMode;
	    public BorderModel Border;
	    public FontModel Font;
	    public boolean IsAutomaticSize;
	    public boolean Shadow;
	    public int Width;
	    public int Height;
	    public int X;
	    public int Y;
	    public LinkModel link;
	    
	    public class LegendLink {
	    	public LinkModel link;
	    }
	}
	
}
