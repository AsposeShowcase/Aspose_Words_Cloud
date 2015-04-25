Aspose Cloud Heroku Examples for Python
=====================================
[Saaspose](http://addons.heroku.com/saaspose) is an [add-on](http://addons.heroku.com) for providing document automation as a service. [Aspose for Cloud](http://aspose.com/) is the easiest API to create, convert and automate documents in the cloud.

Aspose for Cloud's REST API allows you to add support for all kinds of common tasks to your Heroku web app: document assembly, [mail merge](http://www.aspose.com/cloud/word-api/key-features.aspx), reporting, [file conversion](http://www.aspose.com/cloud/word-api/key-features.aspx), [text and image extraction](http://www.aspose.com/cloud/pdf-api/key-features.aspx), metadata removal, [barcode generation and recognition](http://www.aspose.com/cloud/barcode-api.aspx), email tracking and [creation](http://www.aspose.com/cloud/email-api.aspx), targeting content to different devices.

Aspose for Cloud is accessible via an API and has supported client libraries for [.NET](https://github.com/asposeforcloud/Aspose_Cloud_SDK_For_.NET), [Java](https://github.com/asposeforcloud/Aspose_Cloud_SDK_For_Java), [Ruby](https://github.com/asposeforcloud/Aspose_Cloud_SDK_For_Ruby) and [PHP](https://github.com/asposeforcloud/Aspose_Cloud_SDK_For_Php).

## Using with Python

Currently SDK for Python is not stable. But the add-on can easily be used with Python because Aspose is a REST API can be used in any language/platform that can send and receive web request (XML/JSON formats). The necessary methods for authentication and response parsing are included with the sample application in the form of [Saaspose.Common package](https://github.com/asposeforcloud/asposecloud-heroku-examples-for-python/tree/master/saaspose/common).

The sample code for getting properties of a Word document would be

	def docProperties():
		# Initialize the URI, App key and SID
		Product.BaseProductUri = os.environ.get('SAASPOSE_URL', '')
		SaasposeApp.APPKEY = os.environ.get('SAASPOSE_APPKEY', '')
		SaasposeApp.APPSID = os.environ.get('SAASPOSE_APPSID', '')
		
		responseText = "2. Saaspose.Words<br/><hr/>"
		responseText = responseText + "Sending request to Saaspose for reading document properties....<br/>"

		# Set the file name on Saaspose storage which we want to read
		filename = "DinnerInvitation.docx"
		
		# Build the URI for sending request
		uri = Product.BaseProductUri + "/words/"
		# Add the file name of Word document
		uri = uri + filename
		# resource
		uri = uri + "/documentproperties"
		# folder
		uri = uri + "?folder="
		
		# Get the json response
		result = Utils.processCommand(uri, 'GET')
		data = json.loads(result)
		
		responseText = responseText + "response received.<br/>"
		
		# Parse the response
		responseText = responseText + "Response from Saaspose: <br/>"
		responseText = responseText + 'Status: ' + data['Status'] + "<br/>"
		responseText = responseText + 'Code: ' + str(data['Code']) + "<br/>"
		
		responseText = responseText + "<br/>Document properties:<br/>"
		for item in data['DocumentProperties']['List']:
			responseText = responseText + item['Name'] + ': ' + str(item['Value']) + "<br/>"
		
		responseText = responseText + "<br/><br/>"
		
		return responseText
