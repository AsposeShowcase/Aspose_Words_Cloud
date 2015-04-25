package com.aspose.cloud.sdk.cells.model;

import com.aspose.cloud.sdk.cells.model.ColorModel;

import java.util.List;

public class GradientFillModel {
	public GradientFillType FillType;
	public GradientDirectionType DirectionType;
	public int Angle;
	public List<GradientFillStop> GradientFillStops;
	
	public enum GradientFillType {
	    Linear, 
	    Radial, 
	    Rectangle,
	    Path 
	}

	public enum GradientDirectionType {
	    FromUpperLeftCorner, 
	    FromUpperRightCorner, 
	    FromLowerLeftCorner,
	    FromLowerRightCorner, 
	    FromCenter, 
	    Unknown 
	}

	public class GradientFillStop {
	    public ColorModel Color;
	    public double Position;
	    public double Transperancy;
	}
	
}
