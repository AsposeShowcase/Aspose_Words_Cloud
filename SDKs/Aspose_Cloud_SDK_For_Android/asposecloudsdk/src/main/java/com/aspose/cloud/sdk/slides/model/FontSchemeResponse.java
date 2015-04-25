package com.aspose.cloud.sdk.slides.model;

import com.aspose.cloud.sdk.common.BaseResponse;
import com.aspose.cloud.sdk.common.LinkModel;
import com.google.gson.annotations.SerializedName;

public class FontSchemeResponse extends BaseResponse {
	
	@SerializedName("FontScheme")
	public FontScheme fontScheme;
	
    public class FontScheme {
        public LinkModel SelfUri;
        public Major Major;
        public Minor Minor;
        public String Name;
    }

    public class Major {
        public String ComplexScript;
        public String EastAsian;
        public String Latin;
    }

    public class Minor {
        public String ComplexScript;
        public String EastAsian;
        public String Latin;
    }

}
