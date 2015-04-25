package com.aspose.cloud.sdk.words.model;

import java.util.ArrayList;

import com.aspose.cloud.sdk.common.BaseResponse;
import com.aspose.cloud.sdk.common.LinkModel;
import com.google.gson.annotations.SerializedName;

public class DocumentResponse extends BaseResponse {
	@SerializedName("Document")
	public Document document;
	
	public class Document {
		@SerializedName("Links")
		public ArrayList<LinkModel> links;
		@SerializedName("FileName")
		public String fileName;
		@SerializedName("SourceFormat")
		public int sourceFormat;
		@SerializedName("IsEncrypted")
		public boolean isEncrypted;
		@SerializedName("IsSigned")
		public boolean isSigned;
	}
}
