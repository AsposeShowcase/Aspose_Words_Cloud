package com.aspose.cloud.sdk.barcode.model;

import java.util.ArrayList;

import com.aspose.cloud.sdk.common.BaseResponse;
import com.google.gson.annotations.SerializedName;

public class RecognitionResponse extends BaseResponse {
	@SerializedName("Barcodes")
	public ArrayList<RecognizedBarCode> barcodes;
	
	public class RecognizedBarCode {
		public String BarcodeType ;
		public BarcodeTypeEnum BarcodeValue;
		public ArrayList<String> Region;
		public String Checksum;
    }
}
