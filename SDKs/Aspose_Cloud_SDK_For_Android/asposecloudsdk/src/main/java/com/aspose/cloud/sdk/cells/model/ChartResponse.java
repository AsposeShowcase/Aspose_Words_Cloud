package com.aspose.cloud.sdk.cells.model;

import com.aspose.cloud.sdk.common.BaseResponse;
import com.aspose.cloud.sdk.common.LinkModel;
import com.google.gson.annotations.SerializedName;

public class ChartResponse extends BaseResponse {
	@SerializedName("Chart")
	public ChartResult chart;
	
	public class ChartResult {
        public boolean AutoScaling;
        public ChartLink BackWall;
        public ChartLink CategoryAxis;
        public ChartLink ChartArea;
        public ChartLink ChartDataTable;
        public ChartLink ChartObject;
        public int DepthPercent;
        public int Elevation;
        public int FirstSliceAngle;
        public int GapDepth;
        public int GapWidth;
        public int HeightPercent;
        public boolean HidePivotFieldButtons;
        public boolean Is3D;
        public boolean IsRectangularCornered;
        public ChartLink Legend;
        public String Name;
        public ChartLink NSeries;
        public ChartLink PageSetup;
        public int Perspective;
        public String Placement;
        public ChartLink PlotArea;
        public String PlotEmptyCellsType;
        public boolean PlotVisibleCells;
        public String PrintSize;
        public boolean RightAngleAxes;
        public int RotationAngle;
        public ChartLink SecondCategoryAxis;
        public ChartLink SecondValueAxis;
        public ChartLink Shapes;
        public boolean ShowDataTable;
        public boolean ShowLegend;
        public ChartLink SideWall;
        public boolean SizeWithWindow;
        public int Style;
        public ChartLink Title;
        public String Type;
        public ChartLink ValueAxis;
        public boolean WallsAndGridlines2D;
        public LinkModel link;
	}
	
	public class ChartLink {
		public LinkModel link;
	}
}
