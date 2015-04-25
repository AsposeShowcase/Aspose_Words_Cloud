package com.aspose.cloud.sdk.ocr.model;

import com.aspose.cloud.sdk.common.BaseResponse;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class OCRResponse extends BaseResponse {
	@SerializedName("PartsInfo")
	public OCREnvelop partsInfo;
	@SerializedName("Text")
    public String text;
    
	//Here is the OCREnvelop class
    public class OCREnvelop {
    	@SerializedName("Parts")
        public ArrayList<Part> parts;
    }
    
    //Here is the Part class
    public class Part {
        public boolean Bold;
        public String FontName;
        public float FontSize;
        public boolean Italic;
        public String Text;
        public boolean Underline;
    }
}
