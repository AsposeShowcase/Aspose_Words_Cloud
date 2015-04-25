package com.aspose.cloud.sdk.words.model;

import com.aspose.cloud.sdk.common.BaseResponse;
import com.aspose.cloud.sdk.common.LinkModel;
import com.google.gson.annotations.SerializedName;

public class GetSpecificSectionResponse extends BaseResponse {
	@SerializedName("Section")
    public SectionDetails section;
	
	public class SectionDetails {
		@SerializedName("Paragraphs")
        public Link paragraphs;
		@SerializedName("PageSetup")
        public Link pageSetup;
	}
	
	public class Link {
        public LinkModel link;
	}
}
