package com.aspose.cloud.sdk.imaging.model;

import com.aspose.cloud.sdk.common.BaseResponse;
import com.google.gson.annotations.SerializedName;

public class ImagePropertyResponse extends BaseResponse {
	@SerializedName("BitsPerPixel")
	public int bitsPerPixel;
	@SerializedName("BmpProperties")
	public BmpProperties bmpProperties;
	@SerializedName("GifProperties")
	public GifProperties gifProperties;
	@SerializedName("Height")
	public int height;
	@SerializedName("HorizontalResolution")
	public double horizontalResolution;
	@SerializedName("IsCached")
	public boolean isCached;
	@SerializedName("JpegProperties")
	public JpegProperties jpegProperties; 
	@SerializedName("PngProperties")
	public PngProperties pngProperties;
	@SerializedName("PsdProperties")
	public PsdProperties psdProperties;
	@SerializedName("TiffProperties")
	public TiffProperties tiffProperties;
	@SerializedName("VerticalResolution")
	public double verticalResolution;
	@SerializedName("Width")
	public int width;
}
