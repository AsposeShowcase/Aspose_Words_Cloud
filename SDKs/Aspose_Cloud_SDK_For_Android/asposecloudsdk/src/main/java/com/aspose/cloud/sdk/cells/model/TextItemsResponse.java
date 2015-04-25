package com.aspose.cloud.sdk.cells.model;

import java.util.ArrayList;

import com.aspose.cloud.sdk.common.BaseResponse;
import com.aspose.cloud.sdk.common.LinkModel;
import com.google.gson.annotations.SerializedName;

public class TextItemsResponse extends BaseResponse {
	@SerializedName("TextItems")
	public TextItemsData textItems;
	
	public class TextItemsData {
		public ArrayList<TextItemDetails> TextItemList;
		public LinkModel link;
	}
	
	public class TextItemDetails {
		public String Text;
		public LinkModel link; 
	}
}
