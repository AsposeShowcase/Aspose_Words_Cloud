package com.aspose.cloud.sdk.words.model;

import com.aspose.cloud.sdk.common.BaseResponse;
import com.aspose.cloud.sdk.common.LinkModel;
import com.google.gson.annotations.SerializedName;

public class GetRunOfAParagraphResponse extends BaseResponse {
	@SerializedName("Run")
    public Run run;
	
	public class Run {
		@SerializedName("Text")
		public String text;
		public LinkModel link;
	}
}
