__author__ = 'AssadMahmood'
import os
import requests

from asposecloud import Product
from asposecloud.common import Utils


class Folder:
    """
    Wrapper class for Aspose for Cloud Storage API.
    The Aspose for Cloud File Storage API let's you upload and download files for use with our Product APIs.
    """

    def __init__(self):
        self.str_uri_folder = Product.product_uri + 'storage/folder/'
        self.str_uri_file = Product.product_uri + 'storage/file/'
        self.str_uri_exist = Product.product_uri + 'storage/exist/'
        self.str_uri_disc = Product.product_uri + 'storage/disc'

    def upload_file(self, local_file, remote_folder='', storage_type='Aspose', storage_name=None):
        """
        Upload a local file to cloud storage.

        :param local_file:
        :param remote_folder: storage path to operate
        :param storage_type: type of storage e.g Aspose, S3
        :param storage_name: name of storage e.g. MyAmazonS3
        :return: returns True or False
        """
        if not local_file:
            raise ValueError("local_file not specified.")

        filename = os.path.basename(local_file)
        str_uri = self.str_uri_file
        if remote_folder:
            str_uri = str_uri + remote_folder + '/'

        str_uri += filename
        str_uri = Utils.append_storage(str_uri, '', storage_type, storage_name)
        signed_uri = Utils.sign(str_uri)
        response = Utils.upload_file_binary(local_file, signed_uri).json()
        if response['Status'] == 'OK':
            return True
        else:
            return False

    def get_files(self, remote_folder='', storage_type='Aspose', storage_name=None):
        """
        Download the file list of specified folder

        :param remote_folder: storage path to operate
        :param storage_type: type of storage e.g Aspose, S3
        :param storage_name: name of storage e.g. MyAmazonS3
        :return: list of files
        """
        str_uri = self.str_uri_folder + remote_folder
        str_uri = str_uri[:-1] if str_uri[-1] == '/' else str_uri
        str_uri = Utils.append_storage(str_uri, '', storage_type, storage_name)

        signed_uri = Utils.sign(str_uri)
        response = requests.get(signed_uri, headers={
            'content-type': 'application/json', 'accept': 'application/json', 'x-aspose-client' : 'PYTHONSDK/v1.0'
        }).json()
        return response['Files']

    def get_file(self, filename, storage_type='Aspose', storage_name=None):
        """
        Download the file to your local storage from remote storage

        :param filename:
        :param storage_type: type of storage e.g Aspose, S3
        :param storage_name: name of storage e.g. MyAmazonS3
        :return: returns file stream
        """
        if not filename:
            raise ValueError("filename not specified.")

        str_uri = self.str_uri_file + filename
        str_uri = Utils.append_storage(str_uri, '', storage_type, storage_name)

        signed_uri = Utils.sign(str_uri)
        response = requests.get(signed_uri, headers={
            'content-type': 'application/json', 'accept': 'application/json', 'x-aspose-client' : 'PYTHONSDK/v1.0'
        }, stream=True)
        return response

    def file_exists(self, filename, storage_type='Aspose', storage_name=None):
        """
        Check if a file already exist on specified storage.

        :param filename:
        :param storage_type: type of storage e.g Aspose, S3
        :param storage_name: name of storage e.g. MyAmazonS3
        :return: True or False
        """
        if not filename:
            raise ValueError("filename not specified.")

        str_uri = self.str_uri_exist + filename
        str_uri = Utils.append_storage(str_uri, '', storage_type, storage_name)

        signed_uri = Utils.sign(str_uri)
        response = requests.get(signed_uri, headers={
            'content-type': 'application/json', 'accept': 'application/json', 'x-aspose-client' : 'PYTHONSDK/v1.0'
        }).json()
        return response['FileExist']['IsExist']

    def delete_file(self, filename, storage_type='Aspose', storage_name=None):
        """
        Delete a file from specified storage.

        :param filename:
        :param storage_type: type of storage e.g Aspose, S3
        :param storage_name: name of storage e.g. MyAmazonS3
        :return: True or False
        """
        if not filename:
            raise ValueError("filename not specified.")

        str_uri = self.str_uri_file + filename
        str_uri = Utils.append_storage(str_uri, '', storage_type, storage_name)

        signed_uri = Utils.sign(str_uri)
        response = requests.delete(signed_uri, headers={
            'content-type': 'application/json', 'accept': 'application/json', 'x-aspose-client' : 'PYTHONSDK/v1.0'
        }).json()
        return True if response['Code'] == 200 else False

    def create_folder(self, folder_name, storage_type='Aspose', storage_name=None):
        """
        Create a new folder on specified storage

        :param folder_name: name of the folder to operate
        :param storage_type: type of storage e.g Aspose, S3
        :param storage_name: name of storage e.g. MyAmazonS3
        :return: Ture or False
        """
        if not folder_name:
            raise ValueError("folder_name not specified.")

        str_uri = self.str_uri_folder + folder_name
        str_uri = Utils.append_storage(str_uri, '', storage_type, storage_name)

        signed_uri = Utils.sign(str_uri)
        response = requests.put(signed_uri, '', headers={
            'content-type': 'application/json', 'accept': 'application/json', 'x-aspose-client' : 'PYTHONSDK/v1.0'
        }).json()
        return True if response['Code'] == 200 else False

    def delete_folder(self, folder_name, storage_type='Aspose', storage_name=None):
        """
        Delete a folder from specified storage

        :param folder_name: name of the folder to operate
        :param storage_type: type of storage e.g Aspose, S3
        :param storage_name: name of storage e.g. MyAmazonS3
        :return: True or False
        """
        if not folder_name:
            raise ValueError("folder_name not specified.")

        str_uri = self.str_uri_folder + folder_name
        str_uri = Utils.append_storage(str_uri, '', storage_type, storage_name)

        signed_uri = Utils.sign(str_uri)
        response = requests.delete(signed_uri, headers={
            'content-type': 'application/json', 'accept': 'application/json', 'x-aspose-client' : 'PYTHONSDK/v1.0'
        }).json()
        return True if response['Code'] == 200 else False

    def get_disc_usage(self, storage_type='Aspose', storage_name=None):
        """
        Get the disc usage details of specified storage

        :param storage_type: type of storage e.g Aspose, S3
        :param storage_name: name of storage e.g. MyAmazonS3
        :return: list with disc usage details
        """
        str_uri = Utils.append_storage(self.str_uri_disc, '', storage_type, storage_name)

        signed_uri = Utils.sign(str_uri)
        response = requests.get(signed_uri, headers={
            'content-type': 'application/json', 'accept': 'application/json', 'x-aspose-client' : 'PYTHONSDK/v1.0'
        }).json()
        return response['DiscUsage']
