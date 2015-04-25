Aspose Cloud Heroku Examples for Java
=====================================
[Saaspose](http://addons.heroku.com/saaspose) is an [add-on](http://addons.heroku.com) for providing document automation as a service. [Aspose for Cloud](http://aspose.com/) is the easiest API to create, convert and automate documents in the cloud.

Aspose for Cloud's REST API allows you to add support for all kinds of common tasks to your Heroku web app: document assembly, [mail merge](http://www.aspose.com/cloud/word-api/key-features.aspx), reporting, [file conversion](http://www.aspose.com/cloud/word-api/key-features.aspx), [text and image extraction](http://www.aspose.com/cloud/pdf-api/key-features.aspx), metadata removal, [barcode generation and recognition](http://www.aspose.com/cloud/barcode-api.aspx), email tracking and [creation](http://www.aspose.com/cloud/email-api.aspx), targeting content to different devices.

Aspose for Cloud is accessible via an API and has supported client libraries for [.NET](https://github.com/asposeforcloud/Aspose_Cloud_SDK_For_.NET), [Java](https://github.com/asposeforcloud/Aspose_Cloud_SDK_For_Java), [Ruby](https://github.com/asposeforcloud/Aspose_Cloud_SDK_For_Ruby) and [PHP](https://github.com/asposeforcloud/Aspose_Cloud_SDK_For_Php).

## Using with Java

Aspose for Cloud has an [SDK for Java](http://www.aspose.com/java/total-component.aspx) which implements the common features like authentication, send request, receive response, storage and other features.

Add the Aspose SDK for Java as a dependency in your pom.xml file.

    <dependency>
		<groupId>com.saaspose</groupId>
		<artifactId>saaspose.sdk</artifactId>
		<version>1.0.0.1</version>
	</dependency>
	
Complete pom.xml file can be downloaded from [here](https://github.com/asposeforcloud/asposecloud-heroku-examples-for-java/blob/master/pom.xml). 

The sample code to create a barcode is given below.

	// Create the barcode and save to local disk
	//specify product URI
	Product.setBaseProductUri(System.getenv("SAASPOSE_URL"));

	//specify App Key and App SID
	SaasposeApp.setAPPKEY(System.getenv("SAASPOSE_APPKEY"));
	SaasposeApp.setAPPSID(System.getenv("SAASPOSE_APPSID"));
	
	// Create an instance of BarCodeBuilder class
	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	Date date = new Date();
	String codetext = "Heroku test -  " + dateFormat.format(date);
	BarCodeBuilder builder = new BarCodeBuilder(codetext, BarCodeType.Pdf417);
	// Save to server
	GenerationResponse response = builder.Save(
		SaveLocation.Local, "barcode.png", ImageFormat.PNG);
