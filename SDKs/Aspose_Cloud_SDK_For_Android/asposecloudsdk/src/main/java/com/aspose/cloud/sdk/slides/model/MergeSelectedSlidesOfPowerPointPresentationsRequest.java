package com.aspose.cloud.sdk.slides.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class MergeSelectedSlidesOfPowerPointPresentationsRequest {
	@SerializedName("Presentations")
	public ArrayList<PathAndSlides> presentations = new ArrayList<PathAndSlides>();
	
	public class PathAndSlides {
		@SerializedName("Path")
		public String path;
		@SerializedName("Slides")
		public ArrayList<Integer> slides = new ArrayList<Integer>();
	}
}
