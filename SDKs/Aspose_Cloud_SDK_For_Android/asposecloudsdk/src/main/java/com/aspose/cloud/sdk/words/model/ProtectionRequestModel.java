package com.aspose.cloud.sdk.words.model;

import com.google.gson.annotations.SerializedName;

public class ProtectionRequestModel {
	@SerializedName("ProtectionType")
	public ProtectionTypeEnum protectionType;
	@SerializedName("Password")
    public String password;
	@SerializedName("NewPassword")
	public String newPassword;
}
