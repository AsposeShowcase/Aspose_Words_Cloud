package com.aspose.cloud.sdk.pdf.model;

import com.aspose.cloud.sdk.common.BaseResponse;
import com.aspose.cloud.sdk.common.LinkModel;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class DocumentResponse extends BaseResponse {
	@SerializedName("Document")
	public DocumentData document;
	public int Matches;
	
	public class DocumentData {
		public DocumentProperties DocumentProperties;
		public PagesData Pages;
		public ArrayList<LinkModel> Links; 
	}
	
	public class DocumentProperties {
		public ArrayList<LinkModel> Links;
	}
	
	public class PagesData {
		public ArrayList<LinkModel> Links;
	}
}
