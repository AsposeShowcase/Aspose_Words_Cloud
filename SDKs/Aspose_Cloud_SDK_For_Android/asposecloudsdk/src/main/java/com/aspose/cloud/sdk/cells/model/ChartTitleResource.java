package com.aspose.cloud.sdk.cells.model;

import com.aspose.cloud.sdk.common.BaseResponse;
import com.google.gson.annotations.SerializedName;

public class ChartTitleResource extends BaseResponse {
	@SerializedName("Title")
	public ChartTitleData chartTitleData;
	
	public class ChartTitleData {
		public String LinkedSource;
		public int RotationAngle;
		public String Text;
		public String TextDirection;
		public String TextHorizontalAlignment;
		public String TextVerticalAlignment;
		public int X;
		public int Y;
		public int Width;
		public int Height;
		public boolean AutoScaleFont;
		public String BackgroundMode;
		public FontModel Font;
		public boolean IsAutomaticSize;
		public boolean Shadow;
		public boolean IsVisible;
	}
}
