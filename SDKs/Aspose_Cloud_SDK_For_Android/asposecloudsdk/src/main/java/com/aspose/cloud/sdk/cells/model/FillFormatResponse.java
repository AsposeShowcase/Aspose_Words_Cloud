package com.aspose.cloud.sdk.cells.model;

import com.aspose.cloud.sdk.common.BaseResponse;
import com.google.gson.annotations.SerializedName;

public class FillFormatResponse extends BaseResponse {
	@SerializedName("FillFormat")
	public FillFormatResult fillFormat;
	
	public class FillFormatResult {
		public LinkResponse link;
		public FillType Type;
	    public SolidFill SolidFill;
	    public PatternFill PatternFill;
	    public TextureFill TextureFill;
	    public GradientFillModel GradientFill;
	}
	
    public enum FillType {
    	Automatic, 
        None, 
        Solid, 
        Gradient, 
        Texture, 
        Pattern
    }
    
    public class SolidFill {
    	public ColorModel Color;
    	public double Transparency;
    }
    
    public class PatternFill {
    	public FillPattern Pattern;
    	public ColorModel ForegroundColor;
        public ColorModel BackgroundColor;
    }
    
    public enum FillPattern {
        Gray5, 
        Gray10, 
        Gray20, 
        Gray30, 
        Gray40, 
        Gray50, 
        Gray60, 
        Gray70, 
        Gray75, 
        Gray80, 
        Gray90, 
        Gray25,
        LightDownwardDiagonal, 
        LightUpwardDiagonal, 
        DarkDownwardDiagonal, 
        DarkUpwardDiagonal, 
        WideDownwardDiagonal,
        WideUpwardDiagonal, 
        LightVertical, 
        LightHorizontal, 
        NarrowVertical, 
        NarrowHorizontal, 
        DarkVertical, 
        DarkHorizontal,
        DashedDownwardDiagonal, 
        DashedUpwardDiagonal, 
        DashedVertical, 
        DashedHorizontal, 
        SmallConfetti, 
        LargeConfetti,
        ZigZag, 
        Wave, 
        DiagonalBrick, 
        HorizontalBrick, 
        Weave, 
        Plaid, 
        Divot, 
        DottedGrid, 
        DottedDiamond, 
        Shingle, 
        Trellis, 
        Sphere,
        SmallGrid, 
        LargeGrid, 
        SmallCheckerBoard, 
        LargeCheckerBoard, 
        OutlinedDiamond, 
        SolidDiamond 
    }
    
    public class TextureFill {
        public TextureType Type;
        public double Transperancy;
        public TilePicOption TilePicOption;
        public PicFormatOption PicFormatOption;
        public String Image;
    }
    
    public enum TextureType {
        BlueTissuePaper, 
        Bouquet, 
        BrownMarble, 
        Canvas, 
        Cork, 
        Denim, 
        FishFossil, 
        Granite, 
        GreenMarble, 
        MediumWood,
        Newsprint, 
        Oak, 
        PaperBag, 
        Papyrus, 
        Parchment, 
        PinkTissuePaper, 
        PurpleMesh, 
        RecycledPaper, 
        Sand, 
        Stationery, 
        Walnut,
        WaterDroplets, 
        WhiteMarble, 
        WovenMat, 
        Unknown 
    }
    
    public class TilePicOption {
    	public double OffsetX;
    	public double offsetY;
    	public double ScaleX;
    	public double ScaleY;
    	public RectangleAlignmentTypeEnum AlignmentType;
    	public MirrorType MirrorType; 
    }
    
    public enum RectangleAlignmentTypeEnum {
        Bottom, 
        BottomLeft, 
        BottomRight, 
        Center, 
        Left, 
        Right, 
        Top, 
        TopLeft, 
        TopRight
    }
    
    public enum MirrorType {
    	None,
    	Horizonal,
    	Vertical,
    	Both
    }
    
    public class PicFormatOption {
    	public FillPictureType Type;
    	public double Scale;
    	public double Left;
    	public double Right;
    	public double Top;
    	public double Bottom;
    }
    
    public enum FillPictureType {
        Stretch, 
        Stack, 
        StackAndScale
    }
}