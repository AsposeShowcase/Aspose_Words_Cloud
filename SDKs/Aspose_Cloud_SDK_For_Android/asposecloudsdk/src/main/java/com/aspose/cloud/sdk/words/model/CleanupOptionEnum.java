package com.aspose.cloud.sdk.words.model;

public enum CleanupOptionEnum {
	None, //without any cleanup
	EmptyParagraphs, //Specifies whether paragraphs that contained mail merge fields with no data should be removed from the document
	UnusedRegions, //Specifies whether unused mail merge regions should be removed from the document
	UnusedFields, //Specifies whether unused merge fields should be removed from the document
	ContainingFields //Specifies whether fields that contain merge fields (for example, IFs) should be removed from the document if the nested merge fields are removed
}