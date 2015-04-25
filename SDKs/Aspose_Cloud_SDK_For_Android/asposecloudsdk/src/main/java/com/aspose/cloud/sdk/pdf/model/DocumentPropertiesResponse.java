package com.aspose.cloud.sdk.pdf.model;

import com.aspose.cloud.sdk.common.BaseResponse;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class DocumentPropertiesResponse extends BaseResponse {
	@SerializedName("DocumentProperties")
	public DocumentPropertiesResult documentProperties;
	
	public class DocumentPropertiesResult {
        public ArrayList<DocumentPropertyModel> List;
	}

}
