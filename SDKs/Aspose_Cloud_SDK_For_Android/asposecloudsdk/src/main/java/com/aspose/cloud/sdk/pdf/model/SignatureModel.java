package com.aspose.cloud.sdk.pdf.model;

public class SignatureModel {
	
    public String SignaturePath; //The signature path
    public SignatureTypeEnum SignatureType; // The type of the signature
    public String Password; // The password
    public String Appearance; // A graphic appearance for the signature. Property value represents an image file name
    public String Reason; // The reason
    public String Contact; // The contact
    public String Location; // The location
    public boolean Visible; // Gets or sets a value indicating whether this Signature is visible. Supports only when signing particular page.
    public Rectangle Rectangle; //The visible rectangle of the signature. Supports only when signing particular page
    public String FormFieldName; //The name of the signature field. Supports only when signing document with particular form field
    public String Authority; //The name of the person or authority signing the document
    
    public class Rectangle {
    	
    	public Rectangle(int X, int Y, int Width, int Height) {
    		this.X = X;
    		this.Y = Y;
    		this.Width = Width;
    		this.Height = Height;
    	}
    	
    	int X;
        int Y;
        int Width;
        int Height;
    }
}
