package com.aspose.cloud.sdk.pdf.model;

import com.aspose.cloud.sdk.common.BaseResponse;
import com.aspose.cloud.sdk.common.LinkModel;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class PdfLinksResponse extends BaseResponse {
	@SerializedName("Links")
	public PdfLinksResult links;
	
	public class PdfLinksResult {
		public ArrayList<LinkModel> Links;
        public ArrayList<LinkDetails> List;
	}
}
