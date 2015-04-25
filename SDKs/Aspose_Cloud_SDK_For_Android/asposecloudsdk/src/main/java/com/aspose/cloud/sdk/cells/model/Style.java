package com.aspose.cloud.sdk.cells.model;

import java.util.List;

import com.aspose.cloud.sdk.cells.model.BorderModel;
import com.aspose.cloud.sdk.cells.model.ColorModel;
import com.aspose.cloud.sdk.cells.model.FontModel;
import com.aspose.cloud.sdk.common.LinkModel;

public class Style {  
	public LinkModel link;
	public FontModel Font;
	public String Name;
	public String CultureCustom;
	public String Custom;
	public ColorModel BackgroundColor;
	public ColorModel ForegroundColor;
	public String HorizontalAlignment;
	public String VerticalAlignment;
	public boolean IsFormulaHidden;
	public boolean IsDateTime;
	public boolean IsTextWrapped;
	public boolean IsGradient;
	public boolean IsLocked;
	public boolean IsPercent;
	public boolean ShrinkToFit;
	public int IndentLevel;
	public int Number;
	public int RotationAngle;
	public int Rotation;
	public String Pattern;
	public String TextDirection;
	public List<BorderModel> BorderCollection;
}