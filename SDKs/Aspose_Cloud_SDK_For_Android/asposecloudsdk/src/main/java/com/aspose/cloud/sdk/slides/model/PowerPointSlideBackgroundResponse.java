package com.aspose.cloud.sdk.slides.model;

import com.aspose.cloud.sdk.common.BaseResponse;
import com.aspose.cloud.sdk.common.LinkModel;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class PowerPointSlideBackgroundResponse extends BaseResponse {
	@SerializedName("Background")
    public BackgroundResult background;
	
	public class BackgroundResult {
		public String Type;
		public LinkModel SelfUri;
		public ArrayList<LinkModel> AlternateLinks;
		public ArrayList<LinkModel> Links;
	}
}
