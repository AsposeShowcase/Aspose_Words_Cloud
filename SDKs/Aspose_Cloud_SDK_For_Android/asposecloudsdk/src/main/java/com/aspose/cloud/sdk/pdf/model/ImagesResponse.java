package com.aspose.cloud.sdk.pdf.model;

import com.aspose.cloud.sdk.common.BaseResponse;
import com.aspose.cloud.sdk.common.LinkModel;

import java.util.ArrayList;

public class ImagesResponse extends BaseResponse {
	public ImagesResult Images;
	
	public class ImagesResult {
		public ArrayList<LinkModel> Links;
        public ArrayList<ImageDetails> List;
	}
	
	public class ImageDetails {
		public ArrayList<LinkModel> Links;
    	public int Height;
    	public int Width;
	}
}
