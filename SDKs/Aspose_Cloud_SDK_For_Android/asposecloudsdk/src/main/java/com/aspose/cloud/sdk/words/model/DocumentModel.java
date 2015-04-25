package com.aspose.cloud.sdk.words.model;

import java.util.ArrayList;

import com.aspose.cloud.sdk.common.LinkModel;
import com.google.gson.annotations.SerializedName;

public class DocumentModel {
	@SerializedName("FileName")
	public String fileName;
	@SerializedName("SourceFormat")
    public int sourceFormat;
	@SerializedName("IsEncrypted")
	public boolean isEncrypted;
	@SerializedName("IsSigned")
	public boolean isSigned;
	@SerializedName("Links")
	public ArrayList<LinkModel> links;
}
