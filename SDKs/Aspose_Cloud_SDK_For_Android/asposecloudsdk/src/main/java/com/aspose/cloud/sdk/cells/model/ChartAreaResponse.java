package com.aspose.cloud.sdk.cells.model;

import com.aspose.cloud.sdk.common.BaseResponse;
import com.google.gson.annotations.SerializedName;

public class ChartAreaResponse extends BaseResponse {
	@SerializedName("ChartArea")
	public ChartAreaResult chartArea;
	
	public class ChartAreaResult {
		public AreaModel Area;
		public boolean AutoScaleFont;
		public BackgroundModeEnum BackgroundMode;
		public BorderModel Border;
		public FontModel Font;
		public boolean IsAutomaticSize;
		public boolean IsInnerMode;
		public boolean Shadow;
		public int Height;
		public int Width;
		public int X;
		public int Y;
		public LinkResponse link;
	}
}
