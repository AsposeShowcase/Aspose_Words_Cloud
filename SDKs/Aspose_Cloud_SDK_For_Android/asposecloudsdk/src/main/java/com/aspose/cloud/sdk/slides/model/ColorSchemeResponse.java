package com.aspose.cloud.sdk.slides.model;

import com.aspose.cloud.sdk.common.BaseResponse;
import com.aspose.cloud.sdk.common.LinkModel;
import com.google.gson.annotations.SerializedName;

public class ColorSchemeResponse extends BaseResponse {
	
	@SerializedName("ColorScheme")
	public ColorScheme colorScheme;
	
	public class ColorScheme {
      
        public LinkModel SelfUri;
        public String Accent1;
        public String Accent2;
        public String Accent3;
        public String Accent4;
        public String Accent5;
        public String Accent6;
        public String Dark1;
        public String Dark2;
        public String FollowedHyperlink;
        public String Hyperlink;
        public String Light1;
        public String Light2; 
    }
}
