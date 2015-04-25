package com.aspose.cloud.sdk.cells.model;
import java.lang.String;

import com.aspose.cloud.sdk.cells.model.PositionEnum;
import com.google.gson.annotations.SerializedName;

public class WorksheetMovingRequest {
	@SerializedName("DestinationWorksheet")
	public String destinationWorksheet;
	@SerializedName("Position")
	public PositionEnum position;
}