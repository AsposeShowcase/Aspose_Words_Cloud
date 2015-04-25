package com.aspose.cloud.sdk.cells.model;
import java.lang.String;

import com.google.gson.annotations.SerializedName;

public class EncryptionModel {
	@SerializedName("EncryptionType")
	public EncryptionTypeEnum encryptionType;
	@SerializedName("Password")
	public String password;
	@SerializedName("KeyLength")
	public int keyLength;     
 }