require 'sinatra'
require 'saasposesdk'
require 'json'

get '/' do
  # Set the Saaspose AppSID and App key
  Common::Product.setBaseProductUri(ENV['SAASPOSE-COM_URL'])
  Common::SaasposeApp.new(ENV['SAASPOSE-COM_APPSID'], ENV['SAASPOSE-COM_APPKEY'])
  
  # Create a barcode on Saaspose storage
  codetext = "Ruby test: " + (Time.now.to_s)
  barcodeType = "pdf417"
  urlDoc = $productURI + '/barcode/rubytest.png/generate?text=' + codetext + '&type=' + barcodeType + '&format=png'
  signedURL = Common::Utils.sign(urlDoc)
  responseBarcode = RestClient.put signedURL, 'data', :accept => 'application/json'
  data = JSON.parse(responseBarcode.body)
  @responseBarcode = 'Status: ' + data['Status'] + "<br>"
  @responseBarcode = @responseBarcode + 'Code: ' + data['Code'].to_s + "<br>"
  
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
  
  # Get document properties using Saaspose.Words
  docFileName = 'Dinner-Invitation.pdf'
  urlDoc = $productURI + '/pdf/' + docFileName + '/documentproperties?folder='
  signedURL = Common::Utils.sign(urlDoc)
  responsePdf = RestClient.get(signedURL, :accept => 'application/json')
  data = JSON.parse(responsePdf.body)
  @responsePdf = 'Status: ' + data['Status'] + "<br>"
  @responsePdf = @responsePdf + 'Code: ' + data['Code'].to_s + "<br>"
  @responsePdf = @responsePdf + "<br/>Document properties:<br/>"
  data['DocumentProperties']['List'].each do |item|
    @responsePdf = @responsePdf + item['Name'] + ': ' + item['Value'].to_s + "<br/>"
  end
  
  # Get document properties using Saaspose.Words
  docFileName = 'ProductsList.xlsx'
  urlDoc = $productURI + '/cells/' + docFileName + '/documentproperties?folder='
  signedURL = Common::Utils.sign(urlDoc)
  responseCells = RestClient.get(signedURL, :accept => 'application/json')
  data = JSON.parse(responseCells.body)
  @responseCells = 'Status: ' + data['Status'] + "<br>"
  @responseCells = @responseCells + 'Code: ' + data['Code'].to_s + "<br>"
  @responseCells = @responseCells + "<br/>Document properties:<br/>"
  data['DocumentProperties']['DocumentPropertyList'].each do |item|
    @responseCells = @responseCells + item['Name'] + ': ' + item['Value'].to_s + "<br/>"
  end
  
  # Get document properties using Saaspose.Words
  docFileName = 'Saaspose.pptx'
  urlDoc = $productURI + '/slides/' + docFileName + '/documentproperties?folder='
  signedURL = Common::Utils.sign(urlDoc)
  responseSlides = RestClient.get(signedURL, :accept => 'application/json')
  data = JSON.parse(responseSlides.body)
  @responseSlides = 'Status: ' + data['Status'] + "<br>"
  @responseSlides = @responseSlides + 'Code: ' + data['Code'].to_s + "<br>"
  @responseSlides = @responseSlides + "<br/>Document properties:<br/>"
  data['DocumentProperties']['List'].each do |item|
    @responseSlides = @responseSlides + item['Name'] + ': ' + item['Value'].to_s + "<br/>"
  end
  
  erb :welcome
end

__END__
@@ layout
<html>
  <HEAD><TITLE>Saaspose Heroku Examples for Ruby</TITLE></HEAD>
  <body>
   <%= yield %>
  </body>
</html>

@@ welcome
<h3>Saaspose Heroku Examples for Ruby</h3>
1. Saaspose.Barcode<br/><hr/>
Response: <%= @responseBarcode %> <br><br>
2. Saaspose.Words<br/><hr/>
Response: <%= @responseWords %> <br><br>
3. Saaspose.Pdf<br/><hr/>
Response: <%= @responsePdf %> <br><br>
4. Saaspose.Cells<br/><hr/>
Response: <%= @responseCells %> <br><br>
5. Saaspose.Slides<br/><hr/>
Response: <%= @responseSlides %> <br><br>

