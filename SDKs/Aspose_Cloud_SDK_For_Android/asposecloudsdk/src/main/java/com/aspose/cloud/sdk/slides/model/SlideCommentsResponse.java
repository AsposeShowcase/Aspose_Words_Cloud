package com.aspose.cloud.sdk.slides.model;

import com.aspose.cloud.sdk.common.BaseResponse;
import com.aspose.cloud.sdk.common.LinkModel;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class SlideCommentsResponse extends BaseResponse {
	@SerializedName("SlideComments")
    public SlideComments slideComments;
	
	public class SlideComments {
        public ArrayList<LinkModel> _links;
        public ArrayList<LinkModel> List;
	}
}
