__author__ = 'AssadMahmood'
import requests

from asposecloud import Product
from asposecloud.common import Utils


class Extractor:
    """
    Wrapper class for Aspose.OCR for Cloud API.
    The Aspose.OCR for Cloud let's you extract text from image.
    """

    def __init__(self, filename):
        self.filename = filename

        if not filename:
            raise ValueError("filename not specified")

        self.base_uri = Product.product_uri + 'ocr/' + self.filename + '/recognize'

    def extract(self, remote_folder='', storage_type='Aspose', storage_name=None, **kwargs):
        """
        Extract text from image

        :param remote_folder: storage path to operate
        :param storage_type: type of storage e.g Aspose, S3
        :param storage_name: name of storage e.g. MyAmazonS3
        :param kwargs:
        :return: Text
        """

        language = kwargs.get('language')
        use_default_dictionaries = kwargs.get('use_default_dictionaries')
        x = kwargs.get('x')
        y = kwargs.get('y')
        height = kwargs.get('height')
        width = kwargs.get('width')

        str_uri = self.base_uri + '?'
        if language:
            str_uri += '&language=' + language
        if use_default_dictionaries:
            str_uri += '&useDefaultDictionaries=true'
        if x:
            str_uri += 'rectX=' + x
        if y:
            str_uri += 'rectY=' + y
        if height:
            str_uri += 'rectHeight=' + height
        if width:
            str_uri += 'rectWidth=' + width

        str_uri = Utils.append_storage(str_uri, remote_folder, storage_type, storage_name)

        signed_uri = Utils.sign(str_uri)
        response = requests.get(signed_uri, headers={
            'content-type': 'application/json', 'accept': 'application/json', 'x-aspose-client' : 'PYTHONSDK/v1.0'
        }).json()

        return response['Text'] if response['Code'] == 200 else False

    @staticmethod
    def extract_from_local_file(local_file):
        """
        Extract text from image using a local file.

        :param local_file:
        :return: Text
        """
        str_uri = Product.product_uri + 'ocr/recognize'

        signed_uri = Utils.sign(str_uri)
        with open(local_file, 'rb') as payload:
            response = requests.post(signed_uri, payload, headers={
                'content-type': 'application/json', 'accept': 'application/json', 'x-aspose-client' : 'PYTHONSDK/v1.0'
            }).json()

            return response['Text'] if response['Code'] == 200 else False

    @staticmethod
    def extract_from_url(url):
        """
        Extract text from image using url

        :param url:
        :return: Text
        """
        str_uri = Product.product_uri + 'ocr/recognize?url=' + url

        signed_uri = Utils.sign(str_uri)
        response = requests.post(signed_uri, None, headers={
            'content-type': 'application/json', 'accept': 'application/json', 'x-aspose-client' : 'PYTHONSDK/v1.0'
        }).json()

        return response['Text'] if response['Code'] == 200 else False

