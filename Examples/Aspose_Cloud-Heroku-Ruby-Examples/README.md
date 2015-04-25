Aspose Cloud Heroku Examples for Ruby
=====================================
[Saaspose](http://addons.heroku.com/saaspose) is an [add-on](http://addons.heroku.com) for providing document automation as a service. [Aspose for Cloud](http://aspose.com/) is the easiest API to create, convert and automate documents in the cloud.

Aspose for Cloud's REST API allows you to add support for all kinds of common tasks to your Heroku web app: document assembly, [mail merge](http://www.aspose.com/cloud/word-api/key-features.aspx), reporting, [file conversion](http://www.aspose.com/cloud/word-api/key-features.aspx), [text and image extraction](http://www.aspose.com/cloud/pdf-api/key-features.aspx), metadata removal, [barcode generation and recognition](http://www.aspose.com/cloud/barcode-api.aspx), email tracking and [creation](http://www.aspose.com/cloud/email-api.aspx), targeting content to different devices.

Aspose for Cloud is accessible via an API and has supported client libraries for [.NET](https://github.com/asposeforcloud/Aspose_Cloud_SDK_For_.NET), [Java](https://github.com/asposeforcloud/Aspose_Cloud_SDK_For_Java), [Ruby](https://github.com/asposeforcloud/Aspose_Cloud_SDK_For_Ruby) and [PHP](https://github.com/asposeforcloud/Aspose_Cloud_SDK_For_Php).

## Using with Ruby

Currently, work on the Ruby SDK is in progress. Only few features of each product are implemented. But we can still use the Aspose Cloud REST API to utilize ALL the features that Aspose products offer. The necessary methods for authentication and response parsing etc are implemented as [Ruby Gem](http://rubygems.org/gems/saasposesdk). This gem is required in our sample application.

In Gemfile, add the following lines to include the Aspose SDK gem and dependencies.

	gem 'saasposesdk'
	gem 'rest-client'
	gem 'mime-types'

The sample code for getting properties of a Word document would be

	# Set the Aspose APPSID and App key
	Common::Product.setBaseProductUri(ENV['SAASPOSE_URL'])
	Common::SaasposeApp.new(ENV['SAASPOSE_APPSID'], ENV['SAASPOSE_APPKEY'])
	
	# Get document properties using Saaspose.Words
	docFileName = 'DinnerInvitation.docx'
	urlDoc = $productURI + '/words/' + docFileName + '/documentproperties?folder='
	signedURL = Common::Utils.sign(urlDoc)
	responseWords = RestClient.get(signedURL, :accept => 'application/json')
	data = JSON.parse(responseWords.body)
	@responseWords = 'Status: ' + data['Status'] + "<br>"
	@responseWords = @responseWords + 'Code: ' + data['Code'].to_s + "<br>"
	@responseWords = @responseWords + "<br/>Document properties:<br/>"
	data['DocumentProperties']['List'].each do |item|
		@responseWords = @responseWords + item['Name'] + ': ' + item['Value'].to_s + "<br/>"
	end
