package com.aspose.cloud.sdk.words.model;

import java.util.ArrayList;

import com.aspose.cloud.sdk.common.BaseResponse;
import com.aspose.cloud.sdk.common.LinkModel;
import com.google.gson.annotations.SerializedName;

public class GetDrawingObjectsResponse extends BaseResponse {
	@SerializedName("DrawingObjects")
    public DrawingObjectsData drawingObjects;
	
	public class DrawingObjectsData {
		@SerializedName("List")
		public ArrayList<DrawingObjectLink> drawingObjectsList;
		public LinkModel link;
	}
	
	public class DrawingObjectLink {
		public LinkModel link;
	}
}
