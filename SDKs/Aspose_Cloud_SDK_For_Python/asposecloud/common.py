__author__ = 'AssadMahmood'
import requests
import hmac
import hashlib
import re
import string
import os
import json

from urlparse import urlparse
from asposecloud import AsposeApp
from asposecloud import Product


class Utils:
    """
    A common collection of utility function to perform various tasks.

    """

    def __init__(self):
        return

    @staticmethod
    def build_uri(path, qry_data=None):
        """
        URI Builder - Accept path and query string to generate a URI.

        :param path: e.g http://api.aspose.com/v1/testurl
        :param qry_data: a dictionary which holds query string data e.g {'param1': 'value1', 'param2': 'value2'}
        :return: returns a uri with query string e.g http://api.aspose.com/v1/testurl?param1=value1&param2=value2
        """
        qry_str = ''
        for key, value in qry_data.iteritems():
            qry_str += str(key) + '=' + str(value) + '&'

        if qry_str:
            uri = path + '?' + qry_str[:-1]
        else:
            uri = path
        return uri

    @staticmethod
    def validate_result(result):
        """
        Validates if an API call have any error in the body.

        :param result: body of the response
        :return: True or False
        """
        if type(result) == requests.Response:
            result = result.content
        elif type(result) == dict:
            result = json.dumps(result)

        results = ['Unknown file format', 'Unable to read beyond the end of the stream', 'Index was out of range',
                   'Cannot read that as a ZipFile', 'Not a Microsoft PowerPoint 2007 presentation',
                   'Index was outside the bounds of the array',
                   'An attempt was made to move the position before the beginning of the stream']
        for val in results:
            if re.search(val, result):
                return val

        return None

    @staticmethod
    def upload_file_binary(local_file, uri):
        """
        Upload a local file to provided url

        :param local_file: path/to/local/file on your local machine
        :param uri: target uri to upload file
        :return: Response object
        """
        with open(local_file, 'rb') as payload:
            return requests.put(uri, payload, stream=True)

    @staticmethod
    def sign(url_to_sign):
        """
        Add Signature to the url for authentication.

        :param url_to_sign:
        :return: Returns Signed URL
        """
        url_to_sign = url_to_sign.replace(" ", "%20")
        url = urlparse(url_to_sign)

        if url.query == "":
            url_part_to_sign = url.scheme + "://" + url.netloc + url.path + "?appSID=" + AsposeApp.app_sid
        else:
            url_part_to_sign = url.scheme + "://" + url.netloc + url.path + "?" + url.query + "&appSID=" + \
                AsposeApp.app_sid

        signature = hmac.new(AsposeApp.app_key, url_part_to_sign, hashlib.sha1).digest().encode('base64')[:-1]
        signature = re.sub('[=_-]', '', signature)

        if url.query == "":
            return url.scheme + "://" + url.netloc + url.path + "?appSID=" + AsposeApp.app_sid + "&signature=" + \
                signature
        else:
            return url.scheme + "://" + url.netloc + url.path + "?" + url.query + "&appSID=" + AsposeApp.app_sid + \
                "&signature=" + signature

    @staticmethod
    def append_storage(uri, remote_folder='', storage_type='Aspose', storage_name=''):
        """
        Utility function to help append storage parameters in URI

        :param uri:
        :param remote_folder:
        :param storage_type:
        :param storage_name:
        :return:
        """

        tmp_uri = None

        if remote_folder and not remote_folder.isspace():
            tmp_uri = "folder=" + remote_folder + "&"

        if storage_name and not storage_type == "Aspose":
            tmp_uri = tmp_uri + "storage=" + storage_name

        if tmp_uri:
            if string.find(tmp_uri, '?'):
                tmp_uri = "&" + tmp_uri
            else:
                tmp_uri = "?" + tmp_uri

        if tmp_uri:
            if tmp_uri[-1:] == '&':
                tmp_uri = tmp_uri[:-1]

        if tmp_uri:
            return uri + tmp_uri
        else:
            return uri

    @staticmethod
    def save_file(response_stream, filename):
        """
        Save a response stream as local file

        :param response_stream: File Stream
        :param filename: File name along with path to store
        :return: returns full path of newly created file
        """
        with open(filename, 'wb') as f:
            for chunk in response_stream.iter_content():
                f.write(chunk)
        return filename

    @staticmethod
    def get_filename(filename):
        """
        Extract filename without extension

        :param filename:
        :return: filename without extension
        """
        return os.path.splitext(os.path.basename(filename))[0]

    @staticmethod
    def download_file(remote_filename, output_filename, remote_folder='', storage_type='Aspose', storage_name=None):
        """
        Download a file from remote storage and save it on local storage.

        :param remote_filename: filename on remote storage
        :param output_filename: filename along with path to store on local storage
        :param remote_folder: storage path to operate
        :param storage_type: type of storage e.g Aspose, S3
        :param storage_name: name of storage e.g. MyAmazonS3
        :return: output location of the downloaded file
        """
        if remote_folder:
            remote_filename = remote_folder + '/' + remote_filename

        str_uri = Product.product_uri + 'storage/file/' + remote_filename
        str_uri = Utils.append_storage(str_uri, '', storage_type, storage_name)

        signed_uri = Utils.sign(str_uri)
        response = None
        try:
            response = requests.get(signed_uri, headers={
                'content-type': 'application/json', 'accept': 'application/json', 'x-aspose-client' : 'PYTHONSDK/v1.0'
            }, stream=True)
            response.raise_for_status()
        except requests.HTTPError as e:
            print e
            print response.content
            exit(1)

        Utils.save_file(response, AsposeApp.output_path + output_filename)
        return AsposeApp.output_path + output_filename
