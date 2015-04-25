package com.aspose.cloud.sdk.slides.model;

import com.aspose.cloud.sdk.common.BaseResponse;
import com.aspose.cloud.sdk.common.LinkModel;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class DocumentResponse extends BaseResponse {
	@SerializedName("Document")
	public DocumentModel document;
	
	public class DocumentModel {
		@SerializedName("DocumentProperties")
		public DocumentProperties documentProperties;
		@SerializedName("Slides")
		public SlidesModel slides;
		@SerializedName("Images")
		public ImagesModel images;
		@SerializedName("SelfUri")
		public LinkModel selfUri;
		@SerializedName("AlternateLinks")
		public ArrayList<LinkModel> alternateLinks;
		@SerializedName("Links")
		public ArrayList<LinkModel> links;
		@SerializedName("Matches")
		public int matches;
	}
	
	public class DocumentProperties {
		public LinkModel Uri;
	}
	
	public class SlidesModel {
		public LinkModel Uri;
	}
	
	public class ImagesModel {
		public LinkModel Uri;
	}
}
