package com.aspose.cloud.sdk.words.model;

import com.google.gson.annotations.SerializedName;

public class DocumentEntryModel {
	
	@SerializedName("Href")
	private String href;
	@SerializedName("ImportFormatMode")
	private String importFormatMode;
	
	public DocumentEntryModel(String href, String importFormatMode) {
		this.href = href;
		this.importFormatMode = importFormatMode;
	}
}
