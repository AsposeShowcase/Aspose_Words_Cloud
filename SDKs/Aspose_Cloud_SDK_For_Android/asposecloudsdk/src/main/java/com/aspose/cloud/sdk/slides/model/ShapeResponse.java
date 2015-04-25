package com.aspose.cloud.sdk.slides.model;

import com.aspose.cloud.sdk.common.BaseResponse;
import com.aspose.cloud.sdk.common.LinkModel;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ShapeResponse extends BaseResponse {
	@SerializedName("Shape")
	public ShapeData shape;
	
	public class ShapeData {
		public String AlternativeText;
		public float Height;
		public boolean Hidden;
		public String Name;
		public float Width;
		public float X;
		public float Y;
		public List<LinkModel> Shapes;
		public ShapeURI FillFormat;
		public ShapeURI LineFormat; 
		public String ShapeType;
	}
	
	public class ShapeURI {
        public LinkModel Uri;
	}
}
