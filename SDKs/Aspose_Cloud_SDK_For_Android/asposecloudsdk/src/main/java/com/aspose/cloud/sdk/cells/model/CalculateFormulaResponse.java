package com.aspose.cloud.sdk.cells.model;

import com.aspose.cloud.sdk.common.BaseResponse;
import com.google.gson.annotations.SerializedName;

public class CalculateFormulaResponse extends BaseResponse
{
	@SerializedName("Value")
	public CalculateFormulaResult value;
	
	public class CalculateFormulaResult {
		public int ValueType;
		public String Value;
    }
}