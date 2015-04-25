package com.aspose.cloud.sdk.cells.model;

import com.aspose.cloud.sdk.common.BaseResponse;
import com.aspose.cloud.sdk.common.LinkModel;
import com.google.gson.annotations.SerializedName;

public class GetCommentFromAWorksheetResponse extends BaseResponse
{   
	@SerializedName("Comment")
	public Comment comment;
	
	public class Comment {
        
		public LinkModel link;
		public String CellName;
		public String Author;
		public String HtmlNote;
		public String Note;
		public boolean AutoSize;
		public boolean IsVisible;
		public int Width;
		public int Height;
		public String TextHorizontalAlignment;
        public String TextOrientationType;
        public String TextVerticalAlignment;
    }
}