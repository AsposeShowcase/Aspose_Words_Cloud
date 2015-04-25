package com.aspose.cloud.sdk.pdf.model;

import com.aspose.cloud.sdk.common.BaseResponse;
import com.aspose.cloud.sdk.common.LinkModel;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class TextFormatResponse extends BaseResponse {
	@SerializedName("TextFormat")
	public TextFormat textFormat;
	
	public class TextFormat {
		public ColorModel Color;
		public int FontSize;
		public String FontName;
		public ArrayList<LinkModel> Links;
	}
}
