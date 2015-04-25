package com.aspose.cloud.sdk.slides.model;

import com.aspose.cloud.sdk.common.BaseResponse;
import com.aspose.cloud.sdk.common.LinkModel;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ShapesResponse extends BaseResponse {
	
	@SerializedName("ShapeList")
	public ShapeList shapeList;
	
	public class ShapeList {
        public ArrayList<LinkModel> _links;
        public ArrayList<ShapeURI> ShapesLinks;
	}
	
	public class ShapeURI {
        public LinkModel Uri;
	}
}
