'''
Created on Jul 31, 2012

@author: saqib
'''

from string import replace
from saaspose.common import SaasposeApp
import urlparse
import base64
import hmac
import hashlib
import urllib
import json
import urllib2
import httplib


def sign(data):
    # replace space char with %20
    #data = data.replace(" ", "%20")
    data = urllib.quote(data, safe="%/:=&?~#+!$,;'@()*[]")
    # add the app sid
    data = data + "&appSID=" + SaasposeApp.AppSID
    
    url = urlparse.urlparse(data)
    privateKey = SaasposeApp.AppKey
    
    # We only need to sign the path+query part of the string
    urlToSign = url.path + "?" + url.query

    # Decode the private key into its binary format
    decodedKey = base64.urlsafe_b64decode(privateKey)
    
    # Create a signature using the private key and the URL-encoded
    # string using HMAC SHA1. This signature will be binary.
    signature = hmac.new(decodedKey, urlToSign, hashlib.sha1)
    
    # Encode the binary signature into base64 for use within a URL
    encodedSignature = base64.urlsafe_b64encode(signature.digest())
    originalUrl = url.scheme + "://" + url.netloc + url.path + "?" + url.query
    #print("Full URL: " + originalUrl + "&signature=" + encodedSignature)
    
    return originalUrl + "&signature=" + encodedSignature

def processCommand(uri, command):
    uri = sign(uri)
    #print uri
    headers = {"Content-Type":"application/json"}

    request = urllib2.Request(uri, data='')
    for key,value in headers.items():
        request.add_header(key,value)
    if command == 'PUT':
        request.get_method = lambda: 'PUT'
    else:
        request.get_method = lambda: 'GET'
    response = (urllib2.urlopen(request))
 
    #print response.info().headers
    #print response.read()

    return response.read()

#def uploadFileBinary(localFile, uploadURL):
    