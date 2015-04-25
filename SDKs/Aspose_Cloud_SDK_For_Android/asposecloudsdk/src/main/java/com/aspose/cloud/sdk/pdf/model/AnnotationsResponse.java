package com.aspose.cloud.sdk.pdf.model;

import com.aspose.cloud.sdk.common.BaseResponse;
import com.aspose.cloud.sdk.common.LinkModel;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class AnnotationsResponse extends BaseResponse {
	@SerializedName("Annotations")
	public AnnotationsResult annotations;
	
	public class AnnotationsResult {
		public ArrayList<LinkModel> Links;
        public ArrayList<AnnotationDetails> List;
	}
}
