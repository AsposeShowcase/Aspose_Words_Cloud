__author__ = 'AssadMahmood'
import requests
import os

from asposecloud import Product
from asposecloud.common import Utils


class Builder:
    """
    Wrapper class for Aspose.Barcode for Cloud API.
    The Aspose.Barcode for Cloud let's you generate Barcodes.
    """

    def __init__(self):
        self.base_uri = Product.product_uri + 'barcode'

    def generate(self, code_text, symbology='QR', image_format='png', x_res=None, y_res=None, x_dim=None,
                 y_dim=None, remote_folder='', storage_type='Aspose', storage_name=None):
        """
        Generate Barcode

        :param code_text:
        :param symbology:
        :param image_format:
        :param x_res:
        :param y_res:
        :param x_dim:
        :param y_dim:
        :param remote_folder: storage path to operate
        :param storage_type: type of storage e.g Aspose, S3
        :param storage_name: name of storage e.g. MyAmazonS3
        :return:
        """
        if not code_text:
            raise ValueError("code_text not specified.")

        if symbology == '':
            raise ValueError("symbology can not be empty.")

        if image_format == '':
            raise ValueError("image_format can not be empty.")

        str_uri = self.base_uri + '/generate?text=' + code_text + '&type=' + symbology + '&format=' + image_format
        if x_res:
            str_uri += '&resolutionX=' + x_res
        if y_res:
            str_uri += '&resolutionY=' + y_res
        if x_dim:
            str_uri += '&dimensionX=' + x_dim
        if y_dim:
            str_uri += '&dimensionY=' + y_dim

        str_uri = Utils.append_storage(str_uri, remote_folder, storage_type, storage_name)

        signed_uri = Utils.sign(str_uri)
        response = requests.get(signed_uri, headers={
            'content-type': 'application/json', 'accept': 'application/json', 'x-aspose-client' : 'PYTHONSDK/v1.0'
        }, stream=True)

        return response


class Reader:
    """
    Wrapper class for Aspose.Barcode for Cloud API.
    The Aspose.Barcode for Cloud let's you read Barcodes.
    """

    def __init__(self, filename):
        self.filename = filename

        if not filename:
            raise ValueError("filename not specified")

        self.base_uri = Product.product_uri + 'barcode'

    def read(self, symbology=None, remote_folder='', storage_type='Aspose', storage_name=None):
        """
        Read a Barcode

        :param symbology:
        :param remote_folder: storage path to operate
        :param storage_type: type of storage e.g Aspose, S3
        :param storage_name: name of storage e.g. MyAmazonS3
        :return:
        """
        str_uri = self.base_uri + '/' + self.filename + '/recognize'
        if symbology:
            str_uri += '?type=' + symbology

        str_uri = Utils.append_storage(str_uri, remote_folder, storage_type, storage_name)

        signed_uri = Utils.sign(str_uri)
        response = requests.get(signed_uri, headers={
            'content-type': 'application/json', 'accept': 'application/json'
        }).json()
        return response['Barcodes'] if response['Code'] == 200 else False

    def read_region(self, x, y, w, h, symbology=None, remote_folder='', storage_type='Aspose', storage_name=None):
        """
        Read a Barcode

        :param x:
        :param y:
        :param w:
        :param h:
        :param symbology:
        :param remote_folder: storage path to operate
        :param storage_type: type of storage e.g Aspose, S3
        :param storage_name: name of storage e.g. MyAmazonS3
        :return:
        """
        str_uri = self.base_uri + '/' + self.filename + '/recognize?rectX=' + str(x) + '&rectY=' + str(y)
        str_uri += '&rectWidth=' + str(w) + '&rectHeight=' + str(h)
        if symbology:
            str_uri += '&type=' + symbology

        str_uri = Utils.append_storage(str_uri, remote_folder, storage_type, storage_name)

        signed_uri = Utils.sign(str_uri)
        response = requests.get(signed_uri, headers={
            'content-type': 'application/json', 'accept': 'application/json', 'x-aspose-client' : 'PYTHONSDK/v1.0'
        }).json()
        return response['Barcodes'] if response['Code'] == 200 else False

    def read_with_checksum(self, checksum_validation, symbology=None, remote_folder='', storage_type='Aspose', storage_name=None):
        """
        Read a Barcode

        :param checksum_validation:
        :param symbology:
        :param remote_folder: storage path to operate
        :param storage_type: type of storage e.g Aspose, S3
        :param storage_name: name of storage e.g. MyAmazonS3
        :return:
        """
        str_uri = self.base_uri + '/' + self.filename + '/recognize?checksumValidation=' + checksum_validation
        if symbology:
            str_uri += '&type=' + symbology

        str_uri = Utils.append_storage(str_uri, remote_folder, storage_type, storage_name)

        signed_uri = Utils.sign(str_uri)
        response = requests.get(signed_uri, headers={
            'content-type': 'application/json', 'accept': 'application/json', 'x-aspose-client' : 'PYTHONSDK/v1.0'
        }).json()
        return response['Barcodes'] if response['Code'] == 200 else False

    def read_barcode_count(self, barcodes_count, symbology=None,
                           remote_folder='', storage_type='Aspose', storage_name=None):
        """
        Read a Barcode

        :param barcodes_count:
        :param symbology:
        :param remote_folder: storage path to operate
        :param storage_type: type of storage e.g Aspose, S3
        :param storage_name: name of storage e.g. MyAmazonS3
        :return:
        """
        str_uri = self.base_uri + '/' + self.filename + '/recognize?barcodesCount=' + barcodes_count
        if symbology:
            str_uri += '&type=' + symbology

        str_uri = Utils.append_storage(str_uri, remote_folder, storage_type, storage_name)

        signed_uri = Utils.sign(str_uri)
        response = requests.get(signed_uri, headers={
            'content-type': 'application/json', 'accept': 'application/json', 'x-aspose-client' : 'PYTHONSDK/v1.0'
        }).json()
        return response['Barcodes'] if response['Code'] == 200 else False

    def read_from_url(self, url, symbology=None, remote_folder='', storage_type='Aspose', storage_name=None):
        """
        Read a Barcode

        :param url:
        :param symbology:
        :param remote_folder: storage path to operate
        :param storage_type: type of storage e.g Aspose, S3
        :param storage_name: name of storage e.g. MyAmazonS3
        :return:
        """

        if not url:
            raise ValueError("url not specified")

        str_uri = self.base_uri + '/' + self.filename + '/recognize?url=' + url
        if symbology:
            str_uri += '&type=' + symbology

        str_uri = Utils.append_storage(str_uri, remote_folder, storage_type, storage_name)

        signed_uri = Utils.sign(str_uri)
        response = requests.get(signed_uri, headers={
            'content-type': 'application/json', 'accept': 'application/json', 'x-aspose-client' : 'PYTHONSDK/v1.0'
        }).json()
        return response['Barcodes'] if response['Code'] == 200 else False

    @staticmethod
    def read_from_local_image(local_image, symbology):
        """
        Read barcode from local image

        :param local_image:
        :param symbology:
        :return: Text of barcode
        """
        if not local_image:
            raise ValueError("local_image not specified")

        filename = os.path.basename(local_image)

        str_uri = Product.product_uri + 'storage/file/' + filename
        signed_uri = Utils.sign(str_uri)
        Utils.upload_file_binary(local_image, signed_uri)

        str_uri = Product.product_uri + 'barcode/' + filename + '/recognize'
        if symbology:
            str_uri += '?type=' + symbology

        signed_uri = Utils.sign(str_uri)
        response = requests.get(signed_uri, headers={
            'content-type': 'application/json', 'accept': 'application/json', 'x-aspose-client' : 'PYTHONSDK/v1.0'
        }).json()
        return response['Barcodes'] if response['Code'] == 200 else False
