package com.aspose.cloud.sdk.words.model;

import com.google.gson.annotations.SerializedName;

public class ReplaceTextModel {
	@SerializedName("OldValue")
	public String oldValue;
	@SerializedName("NewValue")
	public String newValue;
	@SerializedName("IsMatchCase")
    public boolean isMatchCase;
	@SerializedName("IsMatchWholeWord")
    public boolean isMatchWholeWord;
}
