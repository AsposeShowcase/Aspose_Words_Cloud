package com.aspose.cloud.sdk.cells.model;

import com.aspose.cloud.sdk.common.BaseResponse;
import com.aspose.cloud.sdk.common.LinkModel;
import com.google.gson.annotations.SerializedName;
   
public class GetAutoshapeFromAWorksheetResponse extends BaseResponse {
	@SerializedName("AutoShape")
	public AutoShape autoShape;
	
	public class AutoShape {
		public String Name;
		public String MsoDrawingType;
		public String AutoShapeType;
		public String Placement;
		public int UpperLeftRow;
		public int Top;
		public int UpperLeftColumn;
		public int Left;
		public int LowerRightRow;
		public int Bottom;
		public int LowerRightColumn;
		public int Right;
		public int Width;
		public int Height;
		public int X;
		public int Y;
		public float RotationAngle;
	    public String HtmlText;
	    public String Text;
	    public String AlternativeText;
	    public String TextHorizontalAlignment;
	    public String TextHorizontalOverflow;
	    public String TextOrientationType;
	    public String TextVerticalAlignment;
	    public String TextVerticalOverflow;
	    public boolean IsGroup;
	    public boolean IsHidden;
	    public boolean IsLockAspectRatio;
	    public boolean IsLocked;
	    public boolean IsPrintable;
	    public boolean IsTextWrapped;
	    public boolean IsWordArt;
	    public int ZOrderPosition;
	    public LinkModel link;
	    
    }
}