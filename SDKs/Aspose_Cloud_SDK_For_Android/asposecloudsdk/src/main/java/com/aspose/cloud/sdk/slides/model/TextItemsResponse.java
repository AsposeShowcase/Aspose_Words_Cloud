package com.aspose.cloud.sdk.slides.model;

import com.aspose.cloud.sdk.common.BaseResponse;
import com.aspose.cloud.sdk.common.LinkModel;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class TextItemsResponse extends BaseResponse {
	@SerializedName("TextItems")
	public TextItemsData textItems;
	
	public class TextItemsData {
		public ArrayList<TextAndUri> Items;
		public ArrayList<LinkModel> _links;
	}
	
	public class TextAndUri {
        public LinkModel Uri;
        public String Text;
	}
}
