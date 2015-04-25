import os
from saaspose.common import Product, SaasposeApp, Utils
from time import gmtime, strftime
import json

from flask import Flask, make_response, render_template
app = Flask(__name__)

@app.route('/')
def saasposeExamples():
    retVal = ""
    retVal = retVal + barcodeExample()
    retVal = retVal + docProperties()
    retVal = retVal + pdfProperties()
    retVal = retVal + excelProperties()
    retVal = retVal + pptProperties()
    return render_template('welcome.html', retVal=retVal)

def barcodeExample():
    # Initialize the URI, App key and SID
    Product.BaseProductUri = os.environ.get('SAASPOSE-COM_URL', '')
    SaasposeApp.AppKey = os.environ.get('SAASPOSE-COM_APPKEY', '')
    SaasposeApp.AppSID = os.environ.get('SAASPOSE-COM_APPSID', '')
    
    responseText = "1. Saaspose.Barcode<br/><hr/>"
    responseText = responseText + "Sending request to Saaspose for creating barcode....\n"

    # Set what kind of barcode we want to create
    barcodeType = "pdf417"
    codetext = "Python test: " + strftime("%Y-%m-%d %H:%M:%S", gmtime())
    filename = "pythontest.png"
    imageFormat = "png"
    #responseText = responseText + "Variables set<br/>"

    # Build the URI for sending request for creating the barcode
    uri = Product.BaseProductUri + "/barcode/"
    # Add the file name of barcode image
    uri = uri + filename + "/"
    # Add the generate resource
    uri = uri + "generate?"
    # Codetext of the barcode
    uri = uri + "text=" + codetext
    # Type of barcode
    uri = uri + "&type=" + barcodeType
    # Image format
    uri = uri + "&format=" + imageFormat

    result = Utils.processCommand(uri, "PUT")
    data = json.loads(result)
    
    responseText = responseText + "response received.<br/>"
    
    # Parse the response
    responseText = responseText + 'Status: ' + data['Status'] + "<br/>"
    responseText = responseText + 'Code: ' + str(data['Code']) + "<br/>"
    responseText = responseText + "Created " + filename + " file on Saaspose storage<br/>"
    #responseText = responseText + "URL: " + Utils.sign(uri) + "<br/>"

    responseText = responseText + "<br/><br/>"
    
    return responseText

def docProperties():
    # Initialize the URI, App key and SID
    Product.BaseProductUri = os.environ.get('SAASPOSE-COM_URL', '')
    SaasposeApp.AppKey = os.environ.get('SAASPOSE-COM_APPKEY', '')
    SaasposeApp.AppSID = os.environ.get('SAASPOSE-COM_APPSID', '')
    
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

def pdfProperties():
    # Initialize the URI, App key and SID
    Product.BaseProductUri = os.environ.get('SAASPOSE-COM_URL', '')
    SaasposeApp.AppKey = os.environ.get('SAASPOSE-COM_APPKEY', '')
    SaasposeApp.AppSID = os.environ.get('SAASPOSE-COM_APPSID', '')
    
    responseText = "3. Saaspose.Pdf<br/><hr/>"
    responseText = responseText + "Sending request to Saaspose for reading document properties....<br/>"

    # Set the file name on Saaspose storage which we want to read
    filename = "Dinner-Invitation.pdf"
    
    # Build the URI for sending request
    uri = Product.BaseProductUri + "/pdf/"
    # Add the file name of Pdf document
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

def excelProperties():
    # Initialize the URI, App key and SID
    Product.BaseProductUri = os.environ.get('SAASPOSE-COM_URL', '')
    SaasposeApp.AppKey = os.environ.get('SAASPOSE-COM_APPKEY', '')
    SaasposeApp.AppSID = os.environ.get('SAASPOSE-COM_APPSID', '')
    
    responseText = "4. Saaspose.Cells<br/><hr/>"
    responseText = responseText + "Sending request to Saaspose for reading document properties....<br/>"

    # Set the file name on Saaspose storage which we want to read
    filename = "ProductsList.xlsx"
    
    # Build the URI for sending request
    uri = Product.BaseProductUri + "/cells/"
    # Add the file name of Excel document
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
    for item in data['DocumentProperties']['DocumentPropertyList']:
        responseText = responseText + item['Name'] + ': ' + str(item['Value']) + "<br/>"
    
    responseText = responseText + "<br/><br/>"
    
    return responseText

def pptProperties():
    # Initialize the URI, App key and SID
    Product.BaseProductUri = os.environ.get('SAASPOSE-COM_URL', '')
    SaasposeApp.AppKey = os.environ.get('SAASPOSE-COM_APPKEY', '')
    SaasposeApp.AppSID = os.environ.get('SAASPOSE-COM_APPSID', '')
    
    responseText = "5. Saaspose.Slides<br/><hr/>"
    responseText = responseText + "Sending request to Saaspose for reading document properties....<br/>"

    # Set the file name on Saaspose storage which we want to read
    filename = "Saaspose.pptx"
    
    # Build the URI for sending request
    uri = Product.BaseProductUri + "/slides/"
    # Add the file name of Excel document
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

if __name__ == '__main__':
    # Bind to PORT if defined, otherwise default to 5000.
    port = int(os.environ.get('PORT', 5000))
    app.run(host='0.0.0.0', port=port)

    
