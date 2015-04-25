package com.aspose.cloud.sdk.storage.model;

import java.util.Date;

import com.google.gson.annotations.SerializedName;

public class FileModel {
	
	@SerializedName("Name")
	public String fileName; //Represents file name
	@SerializedName("Path")
	public String path; //Relative file path
	
	@SerializedName("ModifiedDate")
	public Date modifiedDate; //Represents last modification date of file or folder
	
	@SerializedName("IsFolder")
	public boolean isFolder; //Indicates whether a folder or file
	@SerializedName("Size")
	public long size; //Represents the size of the File or Folder
}
