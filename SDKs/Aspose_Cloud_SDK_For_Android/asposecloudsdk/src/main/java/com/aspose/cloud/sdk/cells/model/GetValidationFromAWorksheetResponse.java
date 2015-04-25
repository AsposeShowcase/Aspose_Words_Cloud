package com.aspose.cloud.sdk.cells.model;

import java.util.List;

import com.aspose.cloud.sdk.common.BaseResponse;
import com.aspose.cloud.sdk.common.LinkModel;
import com.google.gson.annotations.SerializedName;
   
public class GetValidationFromAWorksheetResponse extends BaseResponse
{       
	@SerializedName("Validation")
	public Validation validation;
	
	public class Validation {
		public LinkModel link;
		public String AlertStyle;
		public List<CellArea> AreaList;
		public String ErrorMessage;
		public String ErrorTitle;
		public String Formula1;
		public String Formula2;
		public boolean IgnoreBlank;
		public boolean InCellDropDown;
		public String InputMessage;
		public String InputTitle;
		public String Operator;
		public boolean ShowError;
		public boolean ShowInput;
		public String Type;
    }
	
	public class CellArea {
		public int StartRow;
        public int EndRow;
        public int StartColumn;
        public int EndColumn;
    }
}