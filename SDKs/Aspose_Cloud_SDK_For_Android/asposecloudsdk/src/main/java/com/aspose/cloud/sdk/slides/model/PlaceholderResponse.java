package com.aspose.cloud.sdk.slides.model;

import com.aspose.cloud.sdk.common.BaseResponse;
import com.aspose.cloud.sdk.common.LinkModel;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class PlaceholderResponse extends BaseResponse {
	@SerializedName("Placeholder")
    public PlaceholderResult placeholder;
	
	public class PlaceholderResult {
        public int Index;
        public int Orientation;
        public int Size;
        public int Type;
        public ShapeUri Shape;
        public ArrayList<LinkModel> _links;
	}
	
	public class ShapeUri {
        public LinkModel Uri;
	}
}
