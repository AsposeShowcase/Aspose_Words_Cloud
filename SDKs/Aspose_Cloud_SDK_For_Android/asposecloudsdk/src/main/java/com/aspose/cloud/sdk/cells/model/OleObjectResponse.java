package com.aspose.cloud.sdk.cells.model;

import com.aspose.cloud.sdk.common.BaseResponse;
import com.aspose.cloud.sdk.common.LinkModel;
import com.google.gson.annotations.SerializedName;

public class OleObjectResponse extends BaseResponse {
	@SerializedName("OleObject")
	public OleObjectData oleObject;
	
	
	public class OleObjectData {
		public String Name; //The name of the oleobject
	    public String Placement; //The way the drawing object is attached to the cells below it
	    public int UpperLeftRow; //Represents upper left corner row index 	RW
	    public int Top; //Represents the vertical offset of oleobject from its top row, in unit of pixels
	    public int UpperLeftColumn; //Represents upper left corner column index
	    public int Left; //Represents the horizontal offset of oleobject from its left column, in unit of pixels
	    public int LowerRightRow; //Represents lower right corner row index
	    public int Bottom; //Represents the width of the oleobject's vertical offset from its lower bottom corner row, in unit of pixels
	    public int LowerRightColumn; //Represents lower right corner column index
	    public int Right; //Represents the width of the oleobject's horizontal  offset from its lower right corner column, in unit of pixels
	    public int Width; //Represents the width of oleobject, in unit of pixels
	    public int Height; //Represents the height of oleobject, in unit of pixel
	    public int X; //The horizonal offset of oleobject from worksheet left border,in unit of pixels
	    public int Y; //The vertical offset of oleobject from worksheet top border,in unit of pixels
	    public boolean DisplayAsIcon; //Indicates whether the specified object is displayed as an icon and the image will not be auto changed
	    public String FileType; //Represents the file type of the embedded ole object data 
	    public String ImageSourceFullName; //Represents the path and name of the source file for the linked image
	    public boolean IsAutoSize; //Indicates whether the size of the ole object will be auto changed as the size of snapshop of the embedded content when the ole object is activated 	RW
	    public boolean IsLink; //Indicates whether the OleObject is linked
	    public String ProgID; //Represents the ProgID of the OLE object
	    public String SourceFullName; //Represents the source full name of the source file for the linked OLE object
	    public String FileFormatType;
	    public String MsoDrawingType;
	    public String AutoShapeType;
	    public double RotationAngle;
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
