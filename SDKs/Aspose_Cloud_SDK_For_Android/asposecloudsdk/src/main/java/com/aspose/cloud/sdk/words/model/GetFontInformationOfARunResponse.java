package com.aspose.cloud.sdk.words.model;

import com.aspose.cloud.sdk.common.BaseResponse;
import com.aspose.cloud.sdk.common.LinkModel;
import com.google.gson.annotations.SerializedName;

public class GetFontInformationOfARunResponse extends BaseResponse {
	@SerializedName("Font")
	public Font font;
	
	public class Font {
		public boolean AllCaps;
		public boolean Bidi;
		public boolean Bold;
		public boolean BoldBi;
		public Border Border;
        public Color Color;
	    public boolean ComplexScript;
	    public boolean DoubleStrikeThrough;
	    public boolean Emboss;
	    public boolean Engrave;
	    public boolean Hidden;
	    public Color HighlightColor;
	    public boolean Italic;
	    public boolean ItalicBi;
	    public float Kerning;
	    public int LocaleId;
	    public int LocaleIdBi;
	    public int LocaleIdFarEast;
	    public String Name;
	    public String NameAscii;
	    public String NameBi;
	    public String NameFarEast;
	    public String NameOther;
	    public boolean NoProofing;
	    public boolean Outline;
	    public float Position;
	    public int Scaling;
	    public boolean Shadow;
	    public float Size;
	    public float SizeBi;
	    public boolean SmallCaps;
	    public float Spacing;
	    public boolean StrikeThrough;
	    public String StyleIdentifier;
	    public String StyleName;
	    public boolean Subscript;
	    public boolean Superscript;
	    public String TextEffect;
	    public String Underline;
        public Color UnderlineColor;
	    LinkModel link;
	}

	public class Color {
		public String Web;
	    public int Alpha;
	}
	
	public class Border {
		public String LineStyle;
	    public float LineWidth;
	    public Color Color;
	    public float DistanceFromText;
	    public boolean Shadow;
	}
}
