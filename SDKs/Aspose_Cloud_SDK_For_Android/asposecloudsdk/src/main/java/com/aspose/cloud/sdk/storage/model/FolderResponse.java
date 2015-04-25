package com.aspose.cloud.sdk.storage.model;

import java.util.List;

import com.aspose.cloud.sdk.common.BaseResponse;
import com.google.gson.annotations.SerializedName;

public class FolderResponse extends BaseResponse {
	@SerializedName("Files")
	public List<FileModel> files;
}
