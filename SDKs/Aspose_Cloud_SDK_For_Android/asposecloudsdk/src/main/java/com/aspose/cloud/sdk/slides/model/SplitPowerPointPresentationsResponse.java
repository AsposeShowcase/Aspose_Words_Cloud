package com.aspose.cloud.sdk.slides.model;

import com.aspose.cloud.sdk.common.BaseResponse;
import com.aspose.cloud.sdk.common.LinkModel;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class SplitPowerPointPresentationsResponse extends BaseResponse {
	@SerializedName("SplitResult")
    public SplitResult splitResult;
	
	public class SplitResult {
		@SerializedName("Slides")
        public ArrayList<LinkModel> slides;
		@SerializedName("SelfUri")
        public LinkModel selfUri;
	}
}
