__author__ = 'AssadMahmood'
import requests
import json

from asposecloud import AsposeApp
from asposecloud import Product
from asposecloud.common import Utils

# ========================================================================
# WORKSHEET CLASS
# ========================================================================


class Worksheet:

    def __init__(self, filename, worksheet_name):
        self.filename = filename
        self.worksheet_name = worksheet_name

        if not filename:
            raise ValueError("filename not specified")

        self.base_uri = Product.product_uri + 'cells/' + self.filename + '/worksheets/' + self.worksheet_name

    def get_pictures_count(self, remote_folder='', storage_type='Aspose', storage_name=None):
        """

        :param remote_folder: storage path to operate
        :param storage_type: type of storage e.g Aspose, S3
        :param storage_name: name of storage e.g. MyAmazonS3
        :return:
        """
        str_uri = self.base_uri + '/pictures'
        str_uri = Utils.append_storage(str_uri, remote_folder, storage_type, storage_name)

        signed_uri = Utils.sign(str_uri)
        response = None
        try:
            response = requests.get(signed_uri, headers={
                'content-type': 'application/json', 'accept': 'application/json', 'x-aspose-client' : 'PYTHONSDK/v1.0'
            })
            response.raise_for_status()
            response = response.json()
        except requests.HTTPError as e:
            print e
            print response.content
            exit(1)

        return len(response['Pictures']['PictureList'])

    def get_ole_objects_count(self, remote_folder='', storage_type='Aspose', storage_name=None):
        """

        :param remote_folder: storage path to operate
        :param storage_type: type of storage e.g Aspose, S3
        :param storage_name: name of storage e.g. MyAmazonS3
        :return:
        """
        str_uri = self.base_uri + '/oleobjects'
        str_uri = Utils.append_storage(str_uri, remote_folder, storage_type, storage_name)

        signed_uri = Utils.sign(str_uri)
        response = None
        try:
            response = requests.get(signed_uri, headers={
                'content-type': 'application/json', 'accept': 'application/json', 'x-aspose-client' : 'PYTHONSDK/v1.0'
            })
            response.raise_for_status()
            response = response.json()
        except requests.HTTPError as e:
            print e
            print response.content
            exit(1)

        return len(response['OleObjects']['OleObjectList'])

    def get_charts_count(self, remote_folder='', storage_type='Aspose', storage_name=None):
        """

        :param remote_folder: storage path to operate
        :param storage_type: type of storage e.g Aspose, S3
        :param storage_name: name of storage e.g. MyAmazonS3
        :return:
        """
        str_uri = self.base_uri + '/charts'
        str_uri = Utils.append_storage(str_uri, remote_folder, storage_type, storage_name)

        signed_uri = Utils.sign(str_uri)
        response = None
        try:
            response = requests.get(signed_uri, headers={
                'content-type': 'application/json', 'accept': 'application/json', 'x-aspose-client' : 'PYTHONSDK/v1.0'
            })
            response.raise_for_status()
            response = response.json()
        except requests.HTTPError as e:
            print e
            print response.content
            exit(1)

        return len(response['Charts']['ChartList'])

    def get_comments_count(self, remote_folder='', storage_type='Aspose', storage_name=None):
        """

        :param remote_folder: storage path to operate
        :param storage_type: type of storage e.g Aspose, S3
        :param storage_name: name of storage e.g. MyAmazonS3
        :return:
        """
        str_uri = self.base_uri + '/comments'
        str_uri = Utils.append_storage(str_uri, remote_folder, storage_type, storage_name)

        signed_uri = Utils.sign(str_uri)
        response = None
        try:
            response = requests.get(signed_uri, headers={
                'content-type': 'application/json', 'accept': 'application/json', 'x-aspose-client' : 'PYTHONSDK/v1.0'
            })
            response.raise_for_status()
            response = response.json()
        except requests.HTTPError as e:
            print e
            print response.content
            exit(1)

        return len(response['Comments']['CommentList'])

    def get_hyperlinks_count(self, remote_folder='', storage_type='Aspose', storage_name=None):
        """

        :param remote_folder: storage path to operate
        :param storage_type: type of storage e.g Aspose, S3
        :param storage_name: name of storage e.g. MyAmazonS3
        :return:
        """
        str_uri = self.base_uri + '/comments'
        str_uri = Utils.append_storage(str_uri, remote_folder, storage_type, storage_name)

        signed_uri = Utils.sign(str_uri)
        response = None
        try:
            response = requests.get(signed_uri, headers={
                'content-type': 'application/json', 'accept': 'application/json', 'x-aspose-client' : 'PYTHONSDK/v1.0'
            })
            response.raise_for_status()
            response = response.json()
        except requests.HTTPError as e:
            print e
            print response.content
            exit(1)

        return len(response['Hyperlinks']['HyperlinkList'])

    def get_mergedcells_count(self, remote_folder='', storage_type='Aspose', storage_name=None):
        """

        :param remote_folder: storage path to operate
        :param storage_type: type of storage e.g Aspose, S3
        :param storage_name: name of storage e.g. MyAmazonS3
        :return:
        """
        str_uri = self.base_uri + '/mergedCells'
        str_uri = Utils.append_storage(str_uri, remote_folder, storage_type, storage_name)

        signed_uri = Utils.sign(str_uri)
        response = None
        try:
            response = requests.get(signed_uri, headers={
                'content-type': 'application/json', 'accept': 'application/json', 'x-aspose-client' : 'PYTHONSDK/v1.0'
            })
            response.raise_for_status()
            response = response.json()
        except requests.HTTPError as e:
            print e
            print response.content
            exit(1)

        return response['MergedCells']['Count']

    def get_validations_count(self, remote_folder='', storage_type='Aspose', storage_name=None):
        """

        :param remote_folder: storage path to operate
        :param storage_type: type of storage e.g Aspose, S3
        :param storage_name: name of storage e.g. MyAmazonS3
        :return:
        """
        str_uri = self.base_uri + '/validations'
        str_uri = Utils.append_storage(str_uri, remote_folder, storage_type, storage_name)

        signed_uri = Utils.sign(str_uri)
        response = None
        try:
            response = requests.get(signed_uri, headers={
                'content-type': 'application/json', 'accept': 'application/json', 'x-aspose-client' : 'PYTHONSDK/v1.0'
            })
            response.raise_for_status()
            response = response.json()
        except requests.HTTPError as e:
            print e
            print response.content
            exit(1)

        return response['Validations']['Count']

    def get_cell_style(self, cell_name, remote_folder='', storage_type='Aspose', storage_name=None):
        """

        :param cell_name:
        :param remote_folder: storage path to operate
        :param storage_type: type of storage e.g Aspose, S3
        :param storage_name: name of storage e.g. MyAmazonS3
        :return:
        """
        str_uri = self.base_uri + '/cells/' + cell_name + '/style'
        str_uri = Utils.append_storage(str_uri, remote_folder, storage_type, storage_name)

        signed_uri = Utils.sign(str_uri)
        response = None
        try:
            response = requests.get(signed_uri, headers={
                'content-type': 'application/json', 'accept': 'application/json', 'x-aspose-client' : 'PYTHONSDK/v1.0'
            })
            response.raise_for_status()
            response = response.json()
        except requests.HTTPError as e:
            print e
            print response.content
            exit(1)

        return response['Style']

    def get_cell(self, cell_name, remote_folder='', storage_type='Aspose', storage_name=None):
        """

        :param cell_name:
        :param remote_folder: storage path to operate
        :param storage_type: type of storage e.g Aspose, S3
        :param storage_name: name of storage e.g. MyAmazonS3
        :return:
        """
        str_uri = self.base_uri + '/cells/' + cell_name
        str_uri = Utils.append_storage(str_uri, remote_folder, storage_type, storage_name)

        signed_uri = Utils.sign(str_uri)
        response = None
        try:
            response = requests.get(signed_uri, headers={
                'content-type': 'application/json', 'accept': 'application/json'
            })
            response.raise_for_status()
            response = response.json()
        except requests.HTTPError as e:
            print e
            print response.content
            exit(1)

        return response['Cell']

    def get_first_cell(self, offset, count, remote_folder='', storage_type='Aspose', storage_name=None):
        """

        :param offset:
        :param count:
        :param remote_folder: storage path to operate
        :param storage_type: type of storage e.g Aspose, S3
        :param storage_name: name of storage e.g. MyAmazonS3
        :return:
        """
        str_uri = self.base_uri + '/cells/firstcell'
        qry = {'offset': offset, 'count': count}
        str_uri = Utils.build_uri(str_uri, qry)
        str_uri = Utils.append_storage(str_uri, remote_folder, storage_type, storage_name)

        signed_uri = Utils.sign(str_uri)
        response = None
        try:
            response = requests.get(signed_uri, headers={
                'content-type': 'application/json', 'accept': 'application/json'
            })
            response.raise_for_status()
            response = response.json()
        except requests.HTTPError as e:
            print e
            print response.content
            exit(1)

        return response['Cell']

    def get_last_cell(self, offset, count, remote_folder='', storage_type='Aspose', storage_name=None):
        """

        :param offset:
        :param count:
        :param remote_folder: storage path to operate
        :param storage_type: type of storage e.g Aspose, S3
        :param storage_name: name of storage e.g. MyAmazonS3
        :return:
        """
        str_uri = self.base_uri + '/cells/endcell'
        qry = {'offset': offset, 'count': count}
        str_uri = Utils.build_uri(str_uri, qry)
        str_uri = Utils.append_storage(str_uri, remote_folder, storage_type, storage_name)

        signed_uri = Utils.sign(str_uri)
        response = None
        try:
            response = requests.get(signed_uri, headers={
                'content-type': 'application/json', 'accept': 'application/json'
            })
            response.raise_for_status()
            response = response.json()
        except requests.HTTPError as e:
            print e
            print response.content
            exit(1)

        return response['Cell']

    def get_autoshape(self, index, remote_folder='', storage_type='Aspose', storage_name=None):
        """

        :param index:
        :param remote_folder: storage path to operate
        :param storage_type: type of storage e.g Aspose, S3
        :param storage_name: name of storage e.g. MyAmazonS3
        :return:
        """
        str_uri = self.base_uri + '/autoshapes/' + str(index)
        str_uri = Utils.append_storage(str_uri, remote_folder, storage_type, storage_name)

        signed_uri = Utils.sign(str_uri)
        response = None
        try:
            response = requests.get(signed_uri, headers={
                'content-type': 'application/json', 'accept': 'application/json', 'x-aspose-client' : 'PYTHONSDK/v1.0'
            })
            response.raise_for_status()
            response = response.json()
        except requests.HTTPError as e:
            print e
            print response.content
            exit(1)

        return response['AutoShapes']

    def get_chart(self, index, remote_folder='', storage_type='Aspose', storage_name=None):
        """

        :param index:
        :param remote_folder: storage path to operate
        :param storage_type: type of storage e.g Aspose, S3
        :param storage_name: name of storage e.g. MyAmazonS3
        :return:
        """
        str_uri = self.base_uri + '/charts/' + str(index)
        str_uri = Utils.append_storage(str_uri, remote_folder, storage_type, storage_name)

        signed_uri = Utils.sign(str_uri)
        response = None
        try:
            response = requests.get(signed_uri, headers={
                'content-type': 'application/json', 'accept': 'application/json', 'x-aspose-client' : 'PYTHONSDK/v1.0'
            })
            response.raise_for_status()
            response = response.json()
        except requests.HTTPError as e:
            print e
            print response.content
            exit(1)

        return response['Chart']

    def get_hyperlink(self, index, remote_folder='', storage_type='Aspose', storage_name=None):
        """

        :param index:
        :param remote_folder: storage path to operate
        :param storage_type: type of storage e.g Aspose, S3
        :param storage_name: name of storage e.g. MyAmazonS3
        :return:
        """
        str_uri = self.base_uri + '/hyperlinks/' + str(index)
        str_uri = Utils.append_storage(str_uri, remote_folder, storage_type, storage_name)

        signed_uri = Utils.sign(str_uri)
        response = None
        try:
            response = requests.get(signed_uri, headers={
                'content-type': 'application/json', 'accept': 'application/json', 'x-aspose-client' : 'PYTHONSDK/v1.0'
            })
            response.raise_for_status()
            response = response.json()
        except requests.HTTPError as e:
            print e
            print response.content
            exit(1)

        return response['Hyperlink']

    def get_ole_object(self, index, remote_folder='', storage_type='Aspose', storage_name=None):
        """

        :param index:
        :param remote_folder: storage path to operate
        :param storage_type: type of storage e.g Aspose, S3
        :param storage_name: name of storage e.g. MyAmazonS3
        :return:
        """
        str_uri = self.base_uri + '/oleobjects/' + str(index)
        str_uri = Utils.append_storage(str_uri, remote_folder, storage_type, storage_name)

        signed_uri = Utils.sign(str_uri)
        response = None
        try:
            response = requests.get(signed_uri, headers={
                'content-type': 'application/json', 'accept': 'application/json', 'x-aspose-client' : 'PYTHONSDK/v1.0'
            })
            response.raise_for_status()
            response = response.json()
        except requests.HTTPError as e:
            print e
            print response.content
            exit(1)

        return response['OleObject']

    def get_picture(self, index, remote_folder='', storage_type='Aspose', storage_name=None):
        """

        :param index:
        :param remote_folder: storage path to operate
        :param storage_type: type of storage e.g Aspose, S3
        :param storage_name: name of storage e.g. MyAmazonS3
        :return:
        """
        str_uri = self.base_uri + '/pictures/' + str(index)
        str_uri = Utils.append_storage(str_uri, remote_folder, storage_type, storage_name)

        signed_uri = Utils.sign(str_uri)
        response = None
        try:
            response = requests.get(signed_uri, headers={
                'content-type': 'application/json', 'accept': 'application/json', 'x-aspose-client' : 'PYTHONSDK/v1.0'
            })
            response.raise_for_status()
            response = response.json()
        except requests.HTTPError as e:
            print e
            print response.content
            exit(1)

        return response['Picture']

    def get_validation(self, index, remote_folder='', storage_type='Aspose', storage_name=None):
        """

        :param index:
        :param remote_folder: storage path to operate
        :param storage_type: type of storage e.g Aspose, S3
        :param storage_name: name of storage e.g. MyAmazonS3
        :return:
        """
        str_uri = self.base_uri + '/validations/' + str(index)
        str_uri = Utils.append_storage(str_uri, remote_folder, storage_type, storage_name)

        signed_uri = Utils.sign(str_uri)
        response = None
        try:
            response = requests.get(signed_uri, headers={
                'content-type': 'application/json', 'accept': 'application/json', 'x-aspose-client' : 'PYTHONSDK/v1.0'
            })
            response.raise_for_status()
            response = response.json()
        except requests.HTTPError as e:
            print e
            print response.content
            exit(1)

        return response['Validation']

    def get_merged_cells(self, index, remote_folder='', storage_type='Aspose', storage_name=None):
        """

        :param index:
        :param remote_folder: storage path to operate
        :param storage_type: type of storage e.g Aspose, S3
        :param storage_name: name of storage e.g. MyAmazonS3
        :return:
        """
        str_uri = self.base_uri + '/mergedCells/' + str(index)
        str_uri = Utils.append_storage(str_uri, remote_folder, storage_type, storage_name)

        signed_uri = Utils.sign(str_uri)
        response = None
        try:
            response = requests.get(signed_uri, headers={
                'content-type': 'application/json', 'accept': 'application/json', 'x-aspose-client' : 'PYTHONSDK/v1.0'
            })
            response.raise_for_status()
            response = response.json()
        except requests.HTTPError as e:
            print e
            print response.content
            exit(1)

        return response['MergedCell']

    def get_comment(self, cell_name, remote_folder='', storage_type='Aspose', storage_name=None):
        """

        :param cell_name:
        :param remote_folder:
        :param storage_type:
        :param storage_name:
        :return:
        """
        str_uri = self.base_uri + '/comments/' + cell_name
        str_uri = Utils.append_storage(str_uri, remote_folder, storage_type, storage_name)

        signed_uri = Utils.sign(str_uri)
        response = None
        try:
            response = requests.get(signed_uri, headers={
                'content-type': 'application/json', 'accept': 'application/json', 'x-aspose-client' : 'PYTHONSDK/v1.0'
            })
            response.raise_for_status()
            response = response.json()
        except requests.HTTPError as e:
            print e
            print response.content
            exit(1)

        return response['Comment']

    def get_autoshapes_count(self, remote_folder='', storage_type='Aspose', storage_name=None):
        """

        :param remote_folder: storage path to operate
        :param storage_type: type of storage e.g Aspose, S3
        :param storage_name: name of storage e.g. MyAmazonS3
        :return:
        """
        str_uri = self.base_uri + '/autoshapes'
        str_uri = Utils.append_storage(str_uri, remote_folder, storage_type, storage_name)

        signed_uri = Utils.sign(str_uri)
        response = None
        try:
            response = requests.get(signed_uri, headers={
                'content-type': 'application/json', 'accept': 'application/json', 'x-aspose-client' : 'PYTHONSDK/v1.0'
            })
            response.raise_for_status()
            response = response.json()
        except requests.HTTPError as e:
            print e
            print response.content
            exit(1)

        return len(response['AutoShapes']['AutoShapeList'])

    def get_cells_count(self, offset, count, remote_folder='', storage_type='Aspose', storage_name=None):
        """

        :param offset:
        :param count:
        :param remote_folder: storage path to operate
        :param storage_type: type of storage e.g Aspose, S3
        :param storage_name: name of storage e.g. MyAmazonS3
        :return:
        """
        str_uri = self.base_uri + '/cells'
        qry = {'offset': offset, 'count': count}
        str_uri = Utils.build_uri(str_uri, qry)
        str_uri = Utils.append_storage(str_uri, remote_folder, storage_type, storage_name)

        signed_uri = Utils.sign(str_uri)
        response = None
        try:
            response = requests.get(signed_uri, headers={
                'content-type': 'application/json', 'accept': 'application/json', 'x-aspose-client' : 'PYTHONSDK/v1.0'
            })
            response.raise_for_status()
            response = response.json()
        except requests.HTTPError as e:
            print e
            print response.content
            exit(1)

        return response['Cells']['CellCount']

    def get_max_column(self, offset, count, remote_folder='', storage_type='Aspose', storage_name=None):
        """

        :param offset:
        :param count:
        :param remote_folder: storage path to operate
        :param storage_type: type of storage e.g Aspose, S3
        :param storage_name: name of storage e.g. MyAmazonS3
        :return:
        """
        str_uri = self.base_uri + '/cells'
        qry = {'offset': offset, 'count': count}
        str_uri = Utils.build_uri(str_uri, qry)
        str_uri = Utils.append_storage(str_uri, remote_folder, storage_type, storage_name)

        signed_uri = Utils.sign(str_uri)
        response = None
        try:
            response = requests.get(signed_uri, headers={
                'content-type': 'application/json', 'accept': 'application/json', 'x-aspose-client' : 'PYTHONSDK/v1.0'
            })
            response.raise_for_status()
            response = response.json()
        except requests.HTTPError as e:
            print e
            print response.content
            exit(1)

        return response['Cells']['MaxColumn']

    def get_max_data_column(self, offset, count, remote_folder='', storage_type='Aspose', storage_name=None):
        """

        :param offset:
        :param count:
        :param remote_folder: storage path to operate
        :param storage_type: type of storage e.g Aspose, S3
        :param storage_name: name of storage e.g. MyAmazonS3
        :return:
        """
        str_uri = self.base_uri + '/cells/maxdatacolumn'
        qry = {'offset': offset, 'count': count}
        str_uri = Utils.build_uri(str_uri, qry)
        str_uri = Utils.append_storage(str_uri, remote_folder, storage_type, storage_name)

        signed_uri = Utils.sign(str_uri)
        response = None
        try:
            response = requests.get(signed_uri, headers={
                'content-type': 'application/json', 'accept': 'application/json'
            })
            response.raise_for_status()
            response = response.json()
        except requests.HTTPError as e:
            print e
            print response.content
            exit(1)

        return response

    def get_min_column(self, offset, count, remote_folder='', storage_type='Aspose', storage_name=None):
        """

        :param offset:
        :param count:
        :param remote_folder: storage path to operate
        :param storage_type: type of storage e.g Aspose, S3
        :param storage_name: name of storage e.g. MyAmazonS3
        :return:
        """
        str_uri = self.base_uri + '/cells/mincolumn'
        qry = {'offset': offset, 'count': count}
        str_uri = Utils.build_uri(str_uri, qry)
        str_uri = Utils.append_storage(str_uri, remote_folder, storage_type, storage_name)

        signed_uri = Utils.sign(str_uri)
        response = None
        try:
            response = requests.get(signed_uri, headers={
                'content-type': 'application/json', 'accept': 'application/json'
            })
            response.raise_for_status()
            response = response.json()
        except requests.HTTPError as e:
            print e
            print response.content
            exit(1)

        return response

    def get_min_data_column(self, offset, count, remote_folder='', storage_type='Aspose', storage_name=None):
        """

        :param offset:
        :param count:
        :param remote_folder: storage path to operate
        :param storage_type: type of storage e.g Aspose, S3
        :param storage_name: name of storage e.g. MyAmazonS3
        :return:
        """
        str_uri = self.base_uri + '/cells/mindatacolumn'
        qry = {'offset': offset, 'count': count}
        str_uri = Utils.build_uri(str_uri, qry)
        str_uri = Utils.append_storage(str_uri, remote_folder, storage_type, storage_name)

        signed_uri = Utils.sign(str_uri)
        response = None
        try:
            response = requests.get(signed_uri, headers={
                'content-type': 'application/json', 'accept': 'application/json'
            })
            response.raise_for_status()
            response = response.json()
        except requests.HTTPError as e:
            print e
            print response.content
            exit(1)

        return response

    def get_max_row(self, offset, count, remote_folder='', storage_type='Aspose', storage_name=None):
        """

        :param offset:
        :param count:
        :param remote_folder: storage path to operate
        :param storage_type: type of storage e.g Aspose, S3
        :param storage_name: name of storage e.g. MyAmazonS3
        :return:
        """
        str_uri = self.base_uri + '/cells'
        qry = {'offset': offset, 'count': count}
        str_uri = Utils.build_uri(str_uri, qry)
        str_uri = Utils.append_storage(str_uri, remote_folder, storage_type, storage_name)

        signed_uri = Utils.sign(str_uri)
        response = None
        try:
            response = requests.get(signed_uri, headers={
                'content-type': 'application/json', 'accept': 'application/json', 'x-aspose-client' : 'PYTHONSDK/v1.0'
            })
            response.raise_for_status()
            response = response.json()
        except requests.HTTPError as e:
            print e
            print response.content
            exit(1)

        return response['Cells']['MaxRow']

    def get_max_data_row(self, offset, count, remote_folder='', storage_type='Aspose', storage_name=None):
        """

        :param offset:
        :param count:
        :param remote_folder: storage path to operate
        :param storage_type: type of storage e.g Aspose, S3
        :param storage_name: name of storage e.g. MyAmazonS3
        :return:
        """
        str_uri = self.base_uri + '/cells/maxdatarow'
        qry = {'offset': offset, 'count': count}
        str_uri = Utils.build_uri(str_uri, qry)
        str_uri = Utils.append_storage(str_uri, remote_folder, storage_type, storage_name)

        signed_uri = Utils.sign(str_uri)
        response = None
        try:
            response = requests.get(signed_uri, headers={
                'content-type': 'application/json', 'accept': 'application/json'
            })
            response.raise_for_status()
            response = response.json()
        except requests.HTTPError as e:
            print e
            print response.content
            exit(1)

        return response

    def get_min_row(self, offset, count, remote_folder='', storage_type='Aspose', storage_name=None):
        """

        :param offset:
        :param count:
        :param remote_folder: storage path to operate
        :param storage_type: type of storage e.g Aspose, S3
        :param storage_name: name of storage e.g. MyAmazonS3
        :return:
        """
        str_uri = self.base_uri + '/cells/minrow'
        qry = {'offset': offset, 'count': count}
        str_uri = Utils.build_uri(str_uri, qry)
        str_uri = Utils.append_storage(str_uri, remote_folder, storage_type, storage_name)

        signed_uri = Utils.sign(str_uri)
        response = None
        try:
            response = requests.get(signed_uri, headers={
                'content-type': 'application/json', 'accept': 'application/json'
            })
            response.raise_for_status()
            response = response.json()
        except requests.HTTPError as e:
            print e
            print response.content
            exit(1)

        return response

    def get_min_data_row(self, offset, count, remote_folder='', storage_type='Aspose', storage_name=None):
        """

        :param offset:
        :param count:
        :param remote_folder: storage path to operate
        :param storage_type: type of storage e.g Aspose, S3
        :param storage_name: name of storage e.g. MyAmazonS3
        :return:
        """
        str_uri = self.base_uri + '/cells/mindatarow'
        qry = {'offset': offset, 'count': count}
        str_uri = Utils.build_uri(str_uri, qry)
        str_uri = Utils.append_storage(str_uri, remote_folder, storage_type, storage_name)

        signed_uri = Utils.sign(str_uri)
        response = None
        try:
            response = requests.get(signed_uri, headers={
                'content-type': 'application/json', 'accept': 'application/json'
            })
            response.raise_for_status()
            response = response.json()
        except requests.HTTPError as e:
            print e
            print response.content
            exit(1)

        return response

    def get_cells_list(self, offset, count, remote_folder='', storage_type='Aspose', storage_name=None):
        """

        :param offset:
        :param count:
        :param remote_folder: storage path to operate
        :param storage_type: type of storage e.g Aspose, S3
        :param storage_name: name of storage e.g. MyAmazonS3
        :return:
        """
        str_uri = self.base_uri + '/cells'
        qry = {'offset': offset, 'count': count}
        str_uri = Utils.build_uri(str_uri, qry)
        str_uri = Utils.append_storage(str_uri, remote_folder, storage_type, storage_name)

        signed_uri = Utils.sign(str_uri)
        response = None
        try:
            response = requests.get(signed_uri, headers={
                'content-type': 'application/json', 'accept': 'application/json', 'x-aspose-client' : 'PYTHONSDK/v1.0'
            })
            response.raise_for_status()
            response = response.json()
        except requests.HTTPError as e:
            print e
            print response.content
            exit(1)

        return response['Cells']['CellList']

    def get_row_list(self, offset, count, remote_folder='', storage_type='Aspose', storage_name=None):
        """

        :param offset:
        :param count:
        :param remote_folder: storage path to operate
        :param storage_type: type of storage e.g Aspose, S3
        :param storage_name: name of storage e.g. MyAmazonS3
        :return:
        """
        str_uri = self.base_uri + '/cells/rows'
        qry = {'offset': offset, 'count': count}
        str_uri = Utils.build_uri(str_uri, qry)
        str_uri = Utils.append_storage(str_uri, remote_folder, storage_type, storage_name)

        signed_uri = Utils.sign(str_uri)
        response = None
        try:
            response = requests.get(signed_uri, headers={
                'content-type': 'application/json', 'accept': 'application/json', 'x-aspose-client' : 'PYTHONSDK/v1.0'
            })
            response.raise_for_status()
            response = response.json()
        except requests.HTTPError as e:
            print e
            print response.content
            exit(1)

        return response['Rows']['RowsList']

    def get_columns_list(self, offset, count, remote_folder='', storage_type='Aspose', storage_name=None):
        """

        :param offset:
        :param count:
        :param remote_folder: storage path to operate
        :param storage_type: type of storage e.g Aspose, S3
        :param storage_name: name of storage e.g. MyAmazonS3
        :return:
        """
        str_uri = self.base_uri + '/cells/columns'
        qry = {'offset': offset, 'count': count}
        str_uri = Utils.build_uri(str_uri, qry)
        str_uri = Utils.append_storage(str_uri, remote_folder, storage_type, storage_name)

        signed_uri = Utils.sign(str_uri)
        response = None
        try:
            response = requests.get(signed_uri, headers={
                'content-type': 'application/json', 'accept': 'application/json', 'x-aspose-client' : 'PYTHONSDK/v1.0'
            })
            response.raise_for_status()
            response = response.json()
        except requests.HTTPError as e:
            print e
            print response.content
            exit(1)

        return response['Columns']['ColumnsList']

    def add_ole_object(self, ole_file, image_file, upper_left_row, upper_left_col, height, width,
                       remote_folder='', storage_type='Aspose', storage_name=None):
        """

        :param ole_file:
        :param image_file:
        :param upper_left_row:
        :param upper_left_col:
        :param height:
        :param width:
        :param remote_folder: storage path to operate
        :param storage_type: type of storage e.g Aspose, S3
        :param storage_name: name of storage e.g. MyAmazonS3
        :return:
        """

        str_uri = self.base_uri + '/oleobjects'
        qry = {'oleFile': ole_file, 'imageFile': image_file, 'upperLeftRow': upper_left_row,
               'upperLeftColumn': upper_left_col, 'height': height, 'width': width}
        str_uri = Utils.build_uri(str_uri, qry)
        str_uri = Utils.append_storage(str_uri, remote_folder, storage_type, storage_name)

        signed_uri = Utils.sign(str_uri)
        response = None
        try:
            response = requests.put(signed_uri, None, headers={
                'content-type': 'application/json', 'accept': 'application/json',
            })
            response.raise_for_status()
            response = response.json()
        except requests.HTTPError as e:
            print e
            print response.content
            exit(1)

        return True if response['Code'] == 200 else False

    def update_ole_object(self, index, xml_str, remote_folder='', storage_type='Aspose', storage_name=None):
        """

        :param index:
        :param xml_str:
        :param remote_folder: storage path to operate
        :param storage_type: type of storage e.g Aspose, S3
        :param storage_name: name of storage e.g. MyAmazonS3
        :return:
        """

        str_uri = self.base_uri + '/oleobjects/' + str(index)
        str_uri = Utils.append_storage(str_uri, remote_folder, storage_type, storage_name)

        signed_uri = Utils.sign(str_uri)
        response = None
        try:
            response = requests.post(signed_uri, xml_str, headers={
                'content-type': 'application/json', 'accept': 'application/json',
            })
            response.raise_for_status()
            response = response.json()
        except requests.HTTPError as e:
            print e
            print response.content
            exit(1)

        return True if response['Code'] == 200 else False

    def delete_ole_object(self, index, remote_folder='', storage_type='Aspose', storage_name=None):
        """

        :param index:
        :param remote_folder: storage path to operate
        :param storage_type: type of storage e.g Aspose, S3
        :param storage_name: name of storage e.g. MyAmazonS3
        :return:
        """

        str_uri = self.base_uri + '/oleobjects/' + str(index)
        str_uri = Utils.append_storage(str_uri, remote_folder, storage_type, storage_name)

        signed_uri = Utils.sign(str_uri)
        response = None
        try:
            response = requests.delete(signed_uri, headers={
                'content-type': 'application/json', 'accept': 'application/json',
            })
            response.raise_for_status()
            response = response.json()
        except requests.HTTPError as e:
            print e
            print response.content
            exit(1)

        return True if response['Code'] == 200 else False

    def delete_all_ole_objects(self, remote_folder='', storage_type='Aspose', storage_name=None):
        """

        :param remote_folder: storage path to operate
        :param storage_type: type of storage e.g Aspose, S3
        :param storage_name: name of storage e.g. MyAmazonS3
        :return:
        """

        str_uri = self.base_uri + '/oleobjects'
        str_uri = Utils.append_storage(str_uri, remote_folder, storage_type, storage_name)

        signed_uri = Utils.sign(str_uri)
        response = None
        try:
            response = requests.delete(signed_uri, headers={
                'content-type': 'application/json', 'accept': 'application/json',
            })
            response.raise_for_status()
            response = response.json()
        except requests.HTTPError as e:
            print e
            print response.content
            exit(1)

        return True if response['Code'] == 200 else False

    def update_properties(self, worksheet_name, gridlines=False, pagebreak=False, ruler=False,
                  remote_folder='', storage_type='Aspose', storage_name=None):
        """

        :param worksheet_name:
        :param gridlines:
        :param pagebreak:
        :param ruler:
        :param remote_folder: storage path to operate
        :param storage_type: type of storage e.g Aspose, S3
        :param storage_name: name of storage e.g. MyAmazonS3
        :return:
        """

        json_data = json.dumps({'Name': worksheet_name, 'IsGridlinesVisible': gridlines, 'IsPageBreakPreview': pagebreak,
               'IsRulerVisible': ruler})

        str_uri = self.base_uri
        str_uri = Utils.append_storage(str_uri, remote_folder, storage_type, storage_name)

        signed_uri = Utils.sign(str_uri)
        response = None
        try:
            response = requests.post(signed_uri, data=json_data, headers={
                'content-type': 'application/json', 'accept': 'application/json'
            })
            response.raise_for_status()
            response = response.json()
        except requests.HTTPError as e:
            print e
            print response.content
            exit(1)

        validate_output = Utils.validate_result(response)
        if not validate_output:
            return Utils.download_file(self.filename, self.filename, remote_folder, storage_type, storage_name)
        else:
            return validate_output

    def set_cell_value(self, cell_name, value_type, value, remote_folder='', storage_type='Aspose', storage_name=None):
        """

        :param cell_name:
        :param value_type:
        :param value:
        :param remote_folder: storage path to operate
        :param storage_type: type of storage e.g Aspose, S3
        :param storage_name: name of storage e.g. MyAmazonS3
        :return:
        """
        str_uri = self.base_uri + '/cells/' + cell_name

        qry = {'value': value, 'type': value_type}
        str_uri = Utils.build_uri(str_uri, qry)

        str_uri = Utils.append_storage(str_uri, remote_folder, storage_type, storage_name)

        signed_uri = Utils.sign(str_uri)
        response = None
        try:
            response = requests.post(signed_uri, None, headers={
                'content-type': 'application/json', 'accept': 'application/json'
            })
            response.raise_for_status()
            response = response.json()
        except requests.HTTPError as e:
            print e
            print response.content
            exit(1)

        return True if response['Code'] == 200 else False

    def set_formula(self, cell_name, formula, remote_folder='', storage_type='Aspose', storage_name=None):
        """

        :param cell_name:
        :param formula:
        :param remote_folder: storage path to operate
        :param storage_type: type of storage e.g Aspose, S3
        :param storage_name: name of storage e.g. MyAmazonS3
        :return:
        """
        str_uri = self.base_uri + '/cells/' + cell_name

        qry = {'formula': formula}
        str_uri = Utils.build_uri(str_uri, qry)

        str_uri = Utils.append_storage(str_uri, remote_folder, storage_type, storage_name)

        signed_uri = Utils.sign(str_uri)
        response = None
        try:
            response = requests.post(signed_uri, None, headers={
                'content-type': 'application/json', 'accept': 'application/json'
            })
            response.raise_for_status()
            response = response.json()
        except requests.HTTPError as e:
            print e
            print response.content
            exit(1)

        return True if response['Code'] == 200 else False

    def calculate_formula(self, formula, remote_folder='', storage_type='Aspose', storage_name=None):
        """

        :param formula:
        :param remote_folder: storage path to operate
        :param storage_type: type of storage e.g Aspose, S3
        :param storage_name: name of storage e.g. MyAmazonS3
        :return:
        """
        str_uri = self.base_uri + '/formulaResult'

        qry = {'formula': formula}
        str_uri = Utils.build_uri(str_uri, qry)
        str_uri = Utils.append_storage(str_uri, remote_folder, storage_type, storage_name)

        signed_uri = Utils.sign(str_uri)
        response = None
        try:
            response = requests.get(signed_uri, headers={
                'content-type': 'application/json', 'accept': 'application/json'
            })
            response.raise_for_status()
            response = response.json()
        except requests.HTTPError as e:
            print e
            print response.content
            exit(1)

        return response['Value']

# ========================================================================
# WORKBOOK CLASS
# ========================================================================


class Workbook:

    def __init__(self, filename):
        self.filename = filename

        if not filename:
            raise ValueError("filename not specified")

        self.base_uri = Product.product_uri + 'cells/' + self.filename

    def add_worksheet(self, worksheet_name, remote_folder='', storage_type='Aspose', storage_name=None):
        """

        :param worksheet_name:
        :param remote_folder: storage path to operate
        :param storage_type: type of storage e.g Aspose, S3
        :param storage_name: name of storage e.g. MyAmazonS3
        :return:
        """
        str_uri = self.base_uri + '/worksheets/' + worksheet_name
        str_uri = Utils.append_storage(str_uri, remote_folder, storage_type, storage_name)

        signed_uri = Utils.sign(str_uri)
        response = None
        try:
            response = requests.put(signed_uri, None, headers={
                'content-type': 'application/json', 'accept': 'application/json', 'x-aspose-client' : 'PYTHONSDK/v1.0'
            })
            response.raise_for_status()
            response = response.json()
        except requests.HTTPError as e:
            print e
            print response.content
            exit(1)

        return True if response['Code'] == 200 else False

    def remove_worksheet(self, worksheet_name, remote_folder='', storage_type='Aspose', storage_name=None):
        """

        :param worksheet_name:
        :param remote_folder: storage path to operate
        :param storage_type: type of storage e.g Aspose, S3
        :param storage_name: name of storage e.g. MyAmazonS3
        :return:
        """
        str_uri = self.base_uri + '/worksheets/' + worksheet_name
        str_uri = Utils.append_storage(str_uri, remote_folder, storage_type, storage_name)

        signed_uri = Utils.sign(str_uri)
        response = None
        try:
            response = requests.delete(signed_uri, headers={
                'content-type': 'application/json', 'accept': 'application/json', 'x-aspose-client' : 'PYTHONSDK/v1.0'
            })
            response.raise_for_status()
            response = response.json()
        except requests.HTTPError as e:
            print e
            print response.content
            exit(1)

        return True if response['Code'] == 200 else False

    def clear_modify_password(self, password, remote_folder='', storage_type='Aspose', storage_name=None):
        """

        :param password:
        :param remote_folder: storage path to operate
        :param storage_type: type of storage e.g Aspose, S3
        :param storage_name: name of storage e.g. MyAmazonS3
        :return:
        """
        str_uri = self.base_uri + '/writeProtection'
        str_uri = Utils.append_storage(str_uri, remote_folder, storage_type, storage_name)

        json_data = json.dumps({'Password': password})

        signed_uri = Utils.sign(str_uri)
        response = None
        try:
            response = requests.delete(signed_uri, data=json_data, headers={
                'content-type': 'application/json', 'accept': 'application/json', 'x-aspose-client' : 'PYTHONSDK/v1.0'
            })
            response.raise_for_status()
            response = response.json()
        except requests.HTTPError as e:
            print e
            print response.content
            exit(1)

        return True if response['Code'] == 200 else False

    def set_modify_password(self, password, remote_folder='', storage_type='Aspose', storage_name=None):
        """

        :param password:
        :param remote_folder: storage path to operate
        :param storage_type: type of storage e.g Aspose, S3
        :param storage_name: name of storage e.g. MyAmazonS3
        :return:
        """
        str_uri = self.base_uri + '/writeProtection'
        str_uri = Utils.append_storage(str_uri, remote_folder, storage_type, storage_name)

        json_data = json.dumps({'Password': password})

        signed_uri = Utils.sign(str_uri)
        response = None
        try:
            response = requests.put(signed_uri, json_data, headers={
                'content-type': 'application/json', 'accept': 'application/json', 'x-aspose-client' : 'PYTHONSDK/v1.0'
            })
            response.raise_for_status()
            response = response.json()
        except requests.HTTPError as e:
            print e
            print response.content
            exit(1)

        return True if response['Code'] == 200 else False

    def unprotect_workbook(self, password, remote_folder='', storage_type='Aspose', storage_name=None):
        """

        :param password:
        :param remote_folder: storage path to operate
        :param storage_type: type of storage e.g Aspose, S3
        :param storage_name: name of storage e.g. MyAmazonS3
        :return:
        """
        str_uri = self.base_uri + '/protection'
        str_uri = Utils.append_storage(str_uri, remote_folder, storage_type, storage_name)

        json_data = json.dumps({'Password': password})

        signed_uri = Utils.sign(str_uri)
        response = None
        try:
            response = requests.delete(signed_uri, data=json_data, headers={
                'content-type': 'application/json', 'accept': 'application/json', 'x-aspose-client' : 'PYTHONSDK/v1.0'
            })
            response.raise_for_status()
            response = response.json()
        except requests.HTTPError as e:
            print e
            print response.content
            exit(1)

        return True if response['Code'] == 200 else False

    def protect_workbook(self, protection_type, password,
                         remote_folder='', storage_type='Aspose', storage_name=None):
        """

        :param protection_type:
        :param password:
        :param remote_folder: storage path to operate
        :param storage_type: type of storage e.g Aspose, S3
        :param storage_name: name of storage e.g. MyAmazonS3
        :return:
        """
        str_uri = self.base_uri + '/protection'
        str_uri = Utils.append_storage(str_uri, remote_folder, storage_type, storage_name)

        json_data = json.dumps({'ProtectionType': protection_type, 'Password': password})

        signed_uri = Utils.sign(str_uri)
        response = None
        try:
            response = requests.post(signed_uri, json_data, headers={
                'content-type': 'application/json', 'accept': 'application/json', 'x-aspose-client' : 'PYTHONSDK/v1.0'
            })
            response.raise_for_status()
            response = response.json()
        except requests.HTTPError as e:
            print e
            print response.content
            exit(1)

        return True if response['Code'] == 200 else False

    def encrypt_workbook(self, encryption_type, password, key_length,
                         remote_folder='', storage_type='Aspose', storage_name=None):
        """

        :param encryption_type:
        :param password:
        :param key_length:
        :param remote_folder: storage path to operate
        :param storage_type: type of storage e.g Aspose, S3
        :param storage_name: name of storage e.g. MyAmazonS3
        :return:
        """
        str_uri = self.base_uri + '/encryption'
        str_uri = Utils.append_storage(str_uri, remote_folder, storage_type, storage_name)

        json_data = json.dumps({'EncryptionType': encryption_type, 'KeyLength': key_length, 'Password': password})

        signed_uri = Utils.sign(str_uri)
        response = None
        try:
            response = requests.post(signed_uri, json_data, headers={
                'content-type': 'application/json', 'accept': 'application/json'
            })
            response.raise_for_status()
            response = response.json()
        except requests.HTTPError as e:
            print e
            print response.content
            exit(1)

        return True if response['Code'] == 200 else False

    def decrypt_workbook(self, encryption_type, password, key_length,
                         remote_folder='', storage_type='Aspose', storage_name=None):
        """

        :param encryption_type:
        :param password:
        :param key_length:
        :param remote_folder: storage path to operate
        :param storage_type: type of storage e.g Aspose, S3
        :param storage_name: name of storage e.g. MyAmazonS3
        :return:
        """
        str_uri = self.base_uri + '/encryption'
        str_uri = Utils.append_storage(str_uri, remote_folder, storage_type, storage_name)

        json_data = json.dumps({'EncryptionType': encryption_type, 'KeyLength': key_length, 'Password': password})

        signed_uri = Utils.sign(str_uri)
        response = None
        try:
            response = requests.delete(signed_uri, data=json_data, headers={
                'content-type': 'application/json', 'accept': 'application/json'
            })
            response.raise_for_status()
            response = response.json()
        except requests.HTTPError as e:
            print e
            print response.content
            exit(1)

        return True if response['Code'] == 200 else False

    def get_default_style(self, remote_folder='', storage_type='Aspose', storage_name=None):
        """

        :param remote_folder: storage path to operate
        :param storage_type: type of storage e.g Aspose, S3
        :param storage_name: name of storage e.g. MyAmazonS3
        :return:
        """
        str_uri = self.base_uri + '/defaultStyle'
        str_uri = Utils.append_storage(str_uri, remote_folder, storage_type, storage_name)

        signed_uri = Utils.sign(str_uri)
        response = None
        try:
            response = requests.get(signed_uri, headers={
                'content-type': 'application/json', 'accept': 'application/json', 'x-aspose-client' : 'PYTHONSDK/v1.0'
            })
            response.raise_for_status()
            response = response.json()
        except requests.HTTPError as e:
            print e
            print response.content
            exit(1)

        return response['Style']

    def get_name_count(self, remote_folder='', storage_type='Aspose', storage_name=None):
        """

        :param remote_folder: storage path to operate
        :param storage_type: type of storage e.g Aspose, S3
        :param storage_name: name of storage e.g. MyAmazonS3
        :return:
        """
        str_uri = self.base_uri + '/names'
        str_uri = Utils.append_storage(str_uri, remote_folder, storage_type, storage_name)

        signed_uri = Utils.sign(str_uri)
        response = None
        try:
            response = requests.get(signed_uri, headers={
                'content-type': 'application/json', 'accept': 'application/json', 'x-aspose-client' : 'PYTHONSDK/v1.0'
            })
            response.raise_for_status()
            response = response.json()
        except requests.HTTPError as e:
            print e
            print response.content
            exit(1)

        return len(response['Names'])

    def get_worksheets_count(self, remote_folder='', storage_type='Aspose', storage_name=None):
        """

        :param remote_folder: storage path to operate
        :param storage_type: type of storage e.g Aspose, S3
        :param storage_name: name of storage e.g. MyAmazonS3
        :return:
        """
        str_uri = self.base_uri + '/worksheets'
        str_uri = Utils.append_storage(str_uri, remote_folder, storage_type, storage_name)

        signed_uri = Utils.sign(str_uri)
        response = None
        try:
            response = requests.get(signed_uri, headers={
                'content-type': 'application/json', 'accept': 'application/json', 'x-aspose-client' : 'PYTHONSDK/v1.0'
            })
            response.raise_for_status()
            response = response.json()
        except requests.HTTPError as e:
            print e
            print response.content
            exit(1)

        return len(response['Worksheets']['WorksheetList'])

    @staticmethod
    def process_smart_marker(workbook_name, data_filename,
                             remote_folder='', storage_type='Aspose', storage_name=None):
        """

        :param workbook_name:
        :param data_filename:
        :param remote_folder: storage path to operate
        :param storage_type: type of storage e.g Aspose, S3
        :param storage_name: name of storage e.g. MyAmazonS3
        :return:
        """
        str_uri = Product.product_uri + 'cells/' + workbook_name
        qry = {'xmlFile': data_filename}
        str_uri = Utils.build_uri(str_uri, qry)
        str_uri = Utils.append_storage(str_uri, remote_folder, storage_type, storage_name)

        signed_uri = Utils.sign(str_uri)
        response = None
        try:
            response = requests.put(signed_uri, None, headers={
                'content-type': 'application/json', 'accept': 'application/json', 'x-aspose-client' : 'PYTHONSDK/v1.0'
            })
            response.raise_for_status()
            response = response.json()
        except requests.HTTPError as e:
            print e
            print response.content
            exit(1)

        return response

    @staticmethod
    def create_from_smart_template(workbook_name, template_filename, data_filename,
                                   remote_folder='', storage_type='Aspose', storage_name=None):
        """

        :param workbook_name:
        :param template_filename:
        :param data_filename:
        :param remote_folder: storage path to operate
        :param storage_type: type of storage e.g Aspose, S3
        :param storage_name: name of storage e.g. MyAmazonS3
        :return:
        """
        str_uri = Product.product_uri + 'cells/' + workbook_name
        qry = {'templateFile': template_filename, 'dataFile': data_filename}
        str_uri = Utils.build_uri(str_uri, qry)
        str_uri = Utils.append_storage(str_uri, remote_folder, storage_type, storage_name)

        signed_uri = Utils.sign(str_uri)
        response = None
        try:
            response = requests.put(signed_uri, None, headers={
                'content-type': 'application/json', 'accept': 'application/json', 'x-aspose-client' : 'PYTHONSDK/v1.0'
            })
            response.raise_for_status()
            response = response.json()
        except requests.HTTPError as e:
            print e
            print response.content
            exit(1)

        return response

    @staticmethod
    def create_workbook_from_template(workbook_name, template_filename,
                                      remote_folder='', storage_type='Aspose', storage_name=None):
        """

        :param workbook_name:
        :param template_filename:
        :param remote_folder: storage path to operate
        :param storage_type: type of storage e.g Aspose, S3
        :param storage_name: name of storage e.g. MyAmazonS3
        :return:
        """
        str_uri = Product.product_uri + 'cells/' + workbook_name + '?templateFile=' + template_filename
        str_uri = Utils.append_storage(str_uri, remote_folder, storage_type, storage_name)

        signed_uri = Utils.sign(str_uri)
        response = None
        try:
            response = requests.put(signed_uri, None, headers={
                'content-type': 'application/json', 'accept': 'application/json', 'x-aspose-client' : 'PYTHONSDK/v1.0'
            })
            response.raise_for_status()
            response = response.json()
        except requests.HTTPError as e:
            print e
            print response.content
            exit(1)

        return response

    @staticmethod
    def create_empty_workbook(workbook_name, remote_folder='', storage_type='Aspose', storage_name=None):
        """

        :param workbook_name:
        :param remote_folder: storage path to operate
        :param storage_type: type of storage e.g Aspose, S3
        :param storage_name: name of storage e.g. MyAmazonS3
        :return:
        """
        str_uri = Product.product_uri + 'cells/' + workbook_name
        str_uri = Utils.append_storage(str_uri, remote_folder, storage_type, storage_name)

        signed_uri = Utils.sign(str_uri)
        response = None
        try:
            response = requests.put(signed_uri, None, headers={
                'content-type': 'application/json', 'accept': 'application/json', 'x-aspose-client' : 'PYTHONSDK/v1.0'
            })
            response.raise_for_status()
            response = response.json()
        except requests.HTTPError as e:
            print e
            print response.content
            exit(1)

        return response

    def split_workbook(self, save_format, remote_folder='', storage_type='Aspose', storage_name=None):
        """

        :param save_format:
        :param remote_folder: storage path to operate
        :param storage_type: type of storage e.g Aspose, S3
        :param storage_name: name of storage e.g. MyAmazonS3
        :return:
        """
        str_uri = self.base_uri + '/split?format=' + save_format
        str_uri = Utils.append_storage(str_uri, remote_folder, storage_type, storage_name)

        signed_uri = Utils.sign(str_uri)
        response = None
        try:
            response = requests.post(signed_uri, None, headers={
                'content-type': 'application/json', 'accept': 'application/json'
            })
            response.raise_for_status()
            response = response.json()
        except requests.HTTPError as e:
            print e
            print response.content
            exit(1)

        return response['Result']['Documents']

    def get_properties(self, remote_folder='', storage_type='Aspose', storage_name=None):
        """

        :param remote_folder: storage path to operate
        :param storage_type: type of storage e.g Aspose, S3
        :param storage_name: name of storage e.g. MyAmazonS3
        :return:
        """
        str_uri = self.base_uri + '/documentProperties'
        str_uri = Utils.append_storage(str_uri, remote_folder, storage_type, storage_name)

        signed_uri = Utils.sign(str_uri)
        response = None
        try:
            response = requests.get(signed_uri, headers={
                'content-type': 'application/json', 'accept': 'application/json', 'x-aspose-client' : 'PYTHONSDK/v1.0'
            })
            response.raise_for_status()
            response = response.json()
        except requests.HTTPError as e:
            print e
            print response.content
            exit(1)

        return response['DocumentProperties']['DocumentPropertyList']

    def get_property(self, property_name, remote_folder='', storage_type='Aspose', storage_name=None):
        """

        :param property_name:
        :param remote_folder: storage path to operate
        :param storage_type: type of storage e.g Aspose, S3
        :param storage_name: name of storage e.g. MyAmazonS3
        :return:
        """
        str_uri = self.base_uri + '/documentProperties/' + property_name
        str_uri = Utils.append_storage(str_uri, remote_folder, storage_type, storage_name)

        signed_uri = Utils.sign(str_uri)
        response = None
        try:
            response = requests.get(signed_uri, headers={
                'content-type': 'application/json', 'accept': 'application/json', 'x-aspose-client' : 'PYTHONSDK/v1.0'
            })
            response.raise_for_status()
            response = response.json()
        except requests.HTTPError as e:
            print e
            print response.content
            exit(1)

        return response['DocumentProperty']

    def set_property(self, property_name, property_value, remote_folder='', storage_type='Aspose', storage_name=None):
        """

        :param property_name:
        :param property_value:
        :param remote_folder: storage path to operate
        :param storage_type: type of storage e.g Aspose, S3
        :param storage_name: name of storage e.g. MyAmazonS3
        :return:
        """
        str_uri = self.base_uri + '/documentProperties/' + property_name
        str_uri = Utils.append_storage(str_uri, remote_folder, storage_type, storage_name)

        json_data = json.dumps({'Value': property_value})

        signed_uri = Utils.sign(str_uri)
        response = None
        try:
            response = requests.put(signed_uri, json_data, headers={
                'content-type': 'application/json', 'accept': 'application/json', 'x-aspose-client' : 'PYTHONSDK/v1.0'
            })
            response.raise_for_status()
            response = response.json()
        except requests.HTTPError as e:
            print e
            print response.content
            exit(1)

        return response['DocumentProperty']

    def delete_property(self, property_name, remote_folder='', storage_type='Aspose', storage_name=None):
        """

        :param property_name:
        :param remote_folder: storage path to operate
        :param storage_type: type of storage e.g Aspose, S3
        :param storage_name: name of storage e.g. MyAmazonS3
        :return:
        """
        str_uri = self.base_uri + '/documentProperties/' + property_name
        str_uri = Utils.append_storage(str_uri, remote_folder, storage_type, storage_name)

        signed_uri = Utils.sign(str_uri)
        response = None
        try:
            response = requests.delete(signed_uri, headers={
                'content-type': 'application/json', 'accept': 'application/json', 'x-aspose-client' : 'PYTHONSDK/v1.0'
            })
            response.raise_for_status()
            response = response.json()
        except requests.HTTPError as e:
            print e
            print response.content
            exit(1)

        return True if response['Code'] == 200 else False

# ========================================================================
# TEXT EDITOR CLASS
# ========================================================================


class ChartEditor:

    def __init__(self, filename):
        self.filename = filename

        if not filename:
            raise ValueError("filename not specified")

        self.base_uri = Product.product_uri + 'cells/' + self.filename

    def get_border(self, worksheet_name, chart_index, remote_folder='', storage_type='Aspose', storage_name=None):
        """

        :param worksheet_name:
        :param chart_index:
        :param remote_folder: storage path to operate
        :param storage_type: type of storage e.g Aspose, S3
        :param storage_name: name of storage e.g. MyAmazonS3
        :return:
        """
        str_uri = self.base_uri + '/worksheets/' + worksheet_name + \
            '/charts/' + str(chart_index) + '/chartArea/border'
        str_uri = Utils.append_storage(str_uri, remote_folder, storage_type, storage_name)

        signed_uri = Utils.sign(str_uri)
        response = None
        try:
            response = requests.get(signed_uri, headers={
                'content-type': 'application/json', 'accept': 'application/json', 'x-aspose-client' : 'PYTHONSDK/v1.0'
            })
            response.raise_for_status()
            response = response.json()
        except requests.HTTPError as e:
            print e
            print response.content
            exit(1)

        validate_output = Utils.validate_result(response)
        if not validate_output:
            return response['Line']
        else:
            return validate_output

    def get_fill_format(self, worksheet_name, chart_index, remote_folder='', storage_type='Aspose', storage_name=None):
        """

        :param worksheet_name:
        :param chart_index:
        :param remote_folder: storage path to operate
        :param storage_type: type of storage e.g Aspose, S3
        :param storage_name: name of storage e.g. MyAmazonS3
        :return:
        """
        str_uri = self.base_uri + '/worksheets/' + worksheet_name + \
            '/charts/' + str(chart_index) + '/chartArea/fillFormat'
        str_uri = Utils.append_storage(str_uri, remote_folder, storage_type, storage_name)

        signed_uri = Utils.sign(str_uri)
        response = None
        try:
            response = requests.get(signed_uri, headers={
                'content-type': 'application/json', 'accept': 'application/json', 'x-aspose-client' : 'PYTHONSDK/v1.0'
            })
            response.raise_for_status()
            response = response.json()
        except requests.HTTPError as e:
            print e
            print response.content
            exit(1)

        validate_output = Utils.validate_result(response)
        if not validate_output:
            return response['FillFormat']
        else:
            return validate_output

    def get_chart_area(self, worksheet_name, chart_index, remote_folder='', storage_type='Aspose', storage_name=None):
        """

        :param worksheet_name:
        :param chart_index:
        :param remote_folder: storage path to operate
        :param storage_type: type of storage e.g Aspose, S3
        :param storage_name: name of storage e.g. MyAmazonS3
        :return:
        """
        str_uri = self.base_uri + '/worksheets/' + worksheet_name + '/charts/' + str(chart_index) + '/chartArea'
        str_uri = Utils.append_storage(str_uri, remote_folder, storage_type, storage_name)

        signed_uri = Utils.sign(str_uri)
        response = None
        try:
            response = requests.get(signed_uri, headers={
                'content-type': 'application/json', 'accept': 'application/json', 'x-aspose-client' : 'PYTHONSDK/v1.0'
            })
            response.raise_for_status()
            response = response.json()
        except requests.HTTPError as e:
            print e
            print response.content
            exit(1)

        validate_output = Utils.validate_result(response)
        if not validate_output:
            return response['ChartArea']
        else:
            return validate_output

    def add_chart(self, worksheet_name, chart_type, upper_left_row, upper_left_column,
                  lower_right_row, lower_right_column, remote_folder='', storage_type='Aspose', storage_name=None):
        """

        :param worksheet_name:
        :param chart_type:
        :param upper_left_row:
        :param upper_left_column:
        :param lower_right_row:
        :param lower_right_column:
        :param remote_folder: storage path to operate
        :param storage_type: type of storage e.g Aspose, S3
        :param storage_name: name of storage e.g. MyAmazonS3
        :return:
        """
        str_uri = self.base_uri + '/worksheets/' + worksheet_name + '/charts'
        qry = {'chartType': chart_type, 'upperLeftRow': upper_left_row, 'upperLeftColumn': upper_left_column,
               'lowerRightRow': lower_right_row, 'lowerRightColumn': lower_right_column}
        str_uri = Utils.build_uri(str_uri, qry)
        str_uri = Utils.append_storage(str_uri, remote_folder, storage_type, storage_name)

        signed_uri = Utils.sign(str_uri)
        response = None
        try:
            response = requests.put(signed_uri, None, headers={
                'content-type': 'application/json', 'accept': 'application/json', 'x-aspose-client' : 'PYTHONSDK/v1.0'
            })
            response.raise_for_status()
            response = response.json()
        except requests.HTTPError as e:
            print e
            print response.content
            exit(1)

        validate_output = Utils.validate_result(response)
        if not validate_output:
            return Utils.download_file(self.filename, self.filename, remote_folder, storage_type, storage_name)
        else:
            return validate_output

    def delete_chart(self, worksheet_name, chart_index, remote_folder='', storage_type='Aspose', storage_name=None):
        """

        :param worksheet_name:
        :param chart_index:
        :param remote_folder: storage path to operate
        :param storage_type: type of storage e.g Aspose, S3
        :param storage_name: name of storage e.g. MyAmazonS3
        :return:
        """
        str_uri = self.base_uri + '/worksheets/' + worksheet_name + '/charts/' + str(chart_index)
        str_uri = Utils.append_storage(str_uri, remote_folder, storage_type, storage_name)

        signed_uri = Utils.sign(str_uri)
        response = None
        try:
            response = requests.delete(signed_uri, headers={
                'content-type': 'application/json', 'accept': 'application/json', 'x-aspose-client' : 'PYTHONSDK/v1.0'
            })
            response.raise_for_status()
            response = response.json()
        except requests.HTTPError as e:
            print e
            print response.content
            exit(1)

        validate_output = Utils.validate_result(response)
        if not validate_output:
            return Utils.download_file(self.filename, self.filename, remote_folder, storage_type, storage_name)
        else:
            return validate_output


# ========================================================================
# TEXT EDITOR CLASS
# ========================================================================


class TextEditor:

    def __init__(self, filename):
        self.filename = filename

        if not filename:
            raise ValueError("filename not specified")

        self.base_uri = Product.product_uri + 'cells/' + self.filename

    def get_text_items(self, worksheet_name=None, remote_folder='', storage_type='Aspose', storage_name=None):
        """

        :param worksheet_name:
        :param remote_folder: storage path to operate
        :param storage_type: type of storage e.g Aspose, S3
        :param storage_name: name of storage e.g. MyAmazonS3
        :return:
        """
        if worksheet_name is None:
            str_uri = self.base_uri + '/textItems'
        else:
            str_uri = self.base_uri + '/worksheets/' + worksheet_name + '/textItems'
        str_uri = Utils.append_storage(str_uri, remote_folder, storage_type, storage_name)

        signed_uri = Utils.sign(str_uri)
        response = None
        try:
            response = requests.get(signed_uri, headers={
                'content-type': 'application/json', 'accept': 'application/json', 'x-aspose-client' : 'PYTHONSDK/v1.0'
            })
            response.raise_for_status()
            response = response.json()
        except requests.HTTPError as e:
            print e
            print response.content
            exit(1)

        validate_output = Utils.validate_result(response)
        if not validate_output:
            return response['TextItems']['TextItemList']
        else:
            return validate_output

    def find_text(self, text, worksheet_name=None, remote_folder='', storage_type='Aspose', storage_name=None):
        """

        :param text:
        :param worksheet_name:
        :param remote_folder: storage path to operate
        :param storage_type: type of storage e.g Aspose, S3
        :param storage_name: name of storage e.g. MyAmazonS3
        :return:
        """
        if worksheet_name is None:
            str_uri = self.base_uri + '/findText'
        else:
            str_uri = self.base_uri + '/worksheets/' + worksheet_name + '/findText'
        qry = {'text': text}
        str_uri = Utils.build_uri(str_uri, qry)
        str_uri = Utils.append_storage(str_uri, remote_folder, storage_type, storage_name)

        signed_uri = Utils.sign(str_uri)
        response = None
        try:
            response = requests.post(signed_uri, None, headers={
                'content-type': 'application/json', 'accept': 'application/json', 'x-aspose-client' : 'PYTHONSDK/v1.0'
            })
            response.raise_for_status()
            response = response.json()
        except requests.HTTPError as e:
            print e
            print response.content
            exit(1)

        validate_output = Utils.validate_result(response)
        if not validate_output:
            return response['TextItems']['TextItemList']
        else:
            return validate_output

    def replace_text(self, old_text, new_text, worksheet_name=None,
                     remote_folder='', storage_type='Aspose', storage_name=None):
        """

        :param old_text:
        :param new_text:
        :param worksheet_name:
        :param remote_folder: storage path to operate
        :param storage_type: type of storage e.g Aspose, S3
        :param storage_name: name of storage e.g. MyAmazonS3
        :return:
        """
        if worksheet_name is None:
            str_uri = self.base_uri + '/replaceText'
        else:
            str_uri = self.base_uri + '/worksheets/' + worksheet_name + '/replaceText'
        qry = {'OldValue': old_text, 'NewValue': new_text}
        str_uri = Utils.build_uri(str_uri, qry)
        str_uri = Utils.append_storage(str_uri, remote_folder, storage_type, storage_name)

        signed_uri = Utils.sign(str_uri)
        response = None
        try:
            response = requests.post(signed_uri, None, headers={
                'content-type': 'application/json', 'accept': 'application/json', 'x-aspose-client' : 'PYTHONSDK/v1.0'
            })
            response.raise_for_status()
            response = response.json()
        except requests.HTTPError as e:
            print e
            print response.content
            exit(1)

        validate_output = Utils.validate_result(response)
        if not validate_output:
            return True
        else:
            return validate_output

# ========================================================================
# EXTRACTOR CLASS
# ========================================================================


class Extractor:

    def __init__(self, filename):
        self.filename = filename

        if not filename:
            raise ValueError("filename not specified")

        self.base_uri = Product.product_uri + 'cells/' + self.filename

    def get_autoshape(self, shape_index, worksheet_name, save_format, password=None,
                      remote_folder='', storage_type='Aspose', storage_name=None):
        """

        :param shape_index:
        :param worksheet_name:
        :param save_format:
        :param password:
        :param remote_folder: storage path to operate
        :param storage_type: type of storage e.g Aspose, S3
        :param storage_name: name of storage e.g. MyAmazonS3
        :return:
        """
        str_uri = self.base_uri + '/worksheets/' + worksheet_name + '/autoshapes/' + str(shape_index)
        qry = {'format': save_format}
        if not password is None:
            qry['password'] = password
        str_uri = Utils.build_uri(str_uri, qry)
        str_uri = Utils.append_storage(str_uri, remote_folder, storage_type, storage_name)

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
        validate_output = Utils.validate_result(response)
        if not validate_output:
            save_format = 'zip' if save_format == 'html' else save_format
            output_path = AsposeApp.output_path + Utils.get_filename(self.filename) + '_shape_' + str(shape_index)\
                + '.' + save_format
            Utils.save_file(response, output_path)
            return output_path
        else:
            return validate_output

    def get_chart(self, chart_index, worksheet_name, save_format, password=None,
                  remote_folder='', storage_type='Aspose', storage_name=None):
        """

        :param chart_index:
        :param worksheet_name:
        :param save_format:
        :param password:
        :param remote_folder: storage path to operate
        :param storage_type: type of storage e.g Aspose, S3
        :param storage_name: name of storage e.g. MyAmazonS3
        :return:
        """
        str_uri = self.base_uri + '/worksheets/' + worksheet_name + '/charts/' + str(chart_index)
        qry = {'format': save_format}
        if not password is None:
            qry['password'] = password
        str_uri = Utils.build_uri(str_uri, qry)
        str_uri = Utils.append_storage(str_uri, remote_folder, storage_type, storage_name)

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
        validate_output = Utils.validate_result(response)
        if not validate_output:
            save_format = 'zip' if save_format == 'html' else save_format
            output_path = AsposeApp.output_path + Utils.get_filename(self.filename) + '_chart_' + str(chart_index)\
                + '.' + save_format
            Utils.save_file(response, output_path)
            return output_path
        else:
            return validate_output

    def get_oleobject(self, ole_index, worksheet_name, save_format, password=None,
                      remote_folder='', storage_type='Aspose', storage_name=None):
        """

        :param ole_index:
        :param worksheet_name:
        :param save_format:
        :param password:
        :param remote_folder: storage path to operate
        :param storage_type: type of storage e.g Aspose, S3
        :param storage_name: name of storage e.g. MyAmazonS3
        :return:
        """
        str_uri = self.base_uri + '/worksheets/' + worksheet_name + '/oleobjects/' + str(ole_index)
        qry = {'format': save_format}
        if not password is None:
            qry['password'] = password
        str_uri = Utils.build_uri(str_uri, qry)
        str_uri = Utils.append_storage(str_uri, remote_folder, storage_type, storage_name)

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
        validate_output = Utils.validate_result(response)
        if not validate_output:
            save_format = 'zip' if save_format == 'html' else save_format
            output_path = AsposeApp.output_path + Utils.get_filename(self.filename) + '_ole_' + str(ole_index)\
                + '.' + save_format
            Utils.save_file(response, output_path)
            return output_path
        else:
            return validate_output

    def get_picture(self, picture_index, worksheet_name, save_format, password=None,
                    remote_folder='', storage_type='Aspose', storage_name=None):
        """

        :param picture_index:
        :param worksheet_name:
        :param save_format:
        :param password:
        :param remote_folder: storage path to operate
        :param storage_type: type of storage e.g Aspose, S3
        :param storage_name: name of storage e.g. MyAmazonS3
        :return:
        """
        str_uri = self.base_uri + '/worksheets/' + worksheet_name + '/pictures/' + str(picture_index)
        qry = {'format': save_format}
        if not password is None:
            qry['password'] = password
        str_uri = Utils.build_uri(str_uri, qry)
        str_uri = Utils.append_storage(str_uri, remote_folder, storage_type, storage_name)

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
        validate_output = Utils.validate_result(response)
        if not validate_output:
            save_format = 'zip' if save_format == 'html' else save_format
            output_path = AsposeApp.output_path + Utils.get_filename(self.filename) + '_pic_' + str(picture_index)\
                + '.' + save_format
            Utils.save_file(response, output_path)
            return output_path
        else:
            return validate_output

# ========================================================================
# CONVERTER CLASS
# ========================================================================


class Converter:

    def __init__(self, filename):
        self.filename = filename

        if not filename:
            raise ValueError("filename not specified")

        self.base_uri = Product.product_uri + 'cells/' + self.filename

    def autoshape_to_image(self, shape_index, worksheet_name, save_format, password=None, stream_out=False,
                           output_filename=None, remote_folder='', storage_type='Aspose', storage_name=None):
        """

        :param shape_index:
        :param worksheet_name:
        :param save_format:
        :param password:
        :param stream_out:
        :param output_filename:
        :param remote_folder: storage path to operate
        :param storage_type: type of storage e.g Aspose, S3
        :param storage_name: name of storage e.g. MyAmazonS3
        :return:
        """
        str_uri = self.base_uri + '/worksheets/' + worksheet_name + '/autoshapes/' + str(shape_index)
        qry = {'format': save_format}
        if not password is None:
            qry['password'] = password
        str_uri = Utils.build_uri(str_uri, qry)
        str_uri = Utils.append_storage(str_uri, remote_folder, storage_type, storage_name)

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
        validate_output = Utils.validate_result(response)
        if not validate_output:
            if not stream_out:
                if output_filename is None:
                    output_filename = self.filename
                save_format = 'zip' if save_format == 'html' else save_format
                output_path = AsposeApp.output_path + Utils.get_filename(output_filename) + '_shape_' + str(shape_index)\
                    + '.' + save_format
                Utils.save_file(response, output_path)
                return output_path
            else:
                return response.content
        else:
            return validate_output

    def chart_to_image(self, chart_index, worksheet_name, save_format, password=None, stream_out=False,
                       output_filename=None, remote_folder='', storage_type='Aspose', storage_name=None):
        """

        :param chart_index:
        :param worksheet_name:
        :param save_format:
        :param password:
        :param stream_out:
        :param output_filename:
        :param remote_folder: storage path to operate
        :param storage_type: type of storage e.g Aspose, S3
        :param storage_name: name of storage e.g. MyAmazonS3
        :return:
        """
        str_uri = self.base_uri + '/worksheets/' + worksheet_name + '/charts/' + str(chart_index)
        qry = {'format': save_format}
        if not password is None:
            qry['password'] = password
        str_uri = Utils.build_uri(str_uri, qry)
        str_uri = Utils.append_storage(str_uri, remote_folder, storage_type, storage_name)

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
        validate_output = Utils.validate_result(response)
        if not validate_output:
            if not stream_out:
                if output_filename is None:
                    output_filename = self.filename
                save_format = 'zip' if save_format == 'html' else save_format
                output_path = AsposeApp.output_path + Utils.get_filename(output_filename) + '_chart_' + str(chart_index)\
                    + '.' + save_format
                Utils.save_file(response, output_path)
                return output_path
            else:
                return response.content
        else:
            return validate_output

    def oleobject_to_image(self, ole_index, worksheet_name, save_format, password=None, stream_out=False,
                           output_filename=None, remote_folder='', storage_type='Aspose', storage_name=None):
        """

        :param ole_index:
        :param worksheet_name:
        :param save_format:
        :param password:
        :param stream_out:
        :param output_filename:
        :param remote_folder: storage path to operate
        :param storage_type: type of storage e.g Aspose, S3
        :param storage_name: name of storage e.g. MyAmazonS3
        :return:
        """
        str_uri = self.base_uri + '/worksheets/' + worksheet_name + '/oleobjects/' + str(ole_index)
        qry = {'format': save_format}
        if not password is None:
            qry['password'] = password
        str_uri = Utils.build_uri(str_uri, qry)
        str_uri = Utils.append_storage(str_uri, remote_folder, storage_type, storage_name)

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
        validate_output = Utils.validate_result(response)
        if not validate_output:
            if not stream_out:
                if output_filename is None:
                    output_filename = self.filename
                save_format = 'zip' if save_format == 'html' else save_format
                output_path = AsposeApp.output_path + Utils.get_filename(output_filename) + '_ole_' + str(ole_index)\
                    + '.' + save_format
                Utils.save_file(response, output_path)
                return output_path
            else:
                return response.content
        else:
            return validate_output

    def picture_to_image(self, picture_index, worksheet_name, save_format, password=None, stream_out=False,
                         output_filename=None, remote_folder='', storage_type='Aspose', storage_name=None):
        """

        :param picture_index:
        :param worksheet_name:
        :param save_format:
        :param password:
        :param stream_out:
        :param output_filename:
        :param remote_folder: storage path to operate
        :param storage_type: type of storage e.g Aspose, S3
        :param storage_name: name of storage e.g. MyAmazonS3
        :return:
        """
        str_uri = self.base_uri + '/worksheets/' + worksheet_name + '/pictures/' + str(picture_index)
        qry = {'format': save_format}
        if not password is None:
            qry['password'] = password
        str_uri = Utils.build_uri(str_uri, qry)
        str_uri = Utils.append_storage(str_uri, remote_folder, storage_type, storage_name)

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
        validate_output = Utils.validate_result(response)
        if not validate_output:
            if not stream_out:
                if output_filename is None:
                    output_filename = self.filename
                save_format = 'zip' if save_format == 'html' else save_format
                output_path = AsposeApp.output_path + Utils.get_filename(output_filename) + '_pic_' + str(picture_index)\
                    + '.' + save_format
                Utils.save_file(response, output_path)
                return output_path
            else:
                return response.content
        else:
            return validate_output

    def convert_to_image(self, worksheet_name, save_format, password=None, stream_out=False,
                         output_filename=None, remote_folder='', storage_type='Aspose', storage_name=None):
        """

        :param worksheet_name:
        :param save_format:
        :param password:
        :param stream_out:
        :param remote_folder: storage path to operate
        :param storage_type: type of storage e.g Aspose, S3
        :param storage_name: name of storage e.g. MyAmazonS3
        :return:
        """
        if not save_format:
            raise ValueError("save_format not specified")

        str_uri = self.base_uri + '/worksheets/' + worksheet_name
        qry = {'format': save_format}
        if not password is None:
            qry['password'] = password
        str_uri = Utils.build_uri(str_uri, qry)
        str_uri = Utils.append_storage(str_uri, remote_folder, storage_type, storage_name)

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
        validate_output = Utils.validate_result(response)
        if not validate_output:
            if not stream_out:
                if output_filename is None:
                    output_filename = self.filename
                save_format = 'zip' if save_format == 'html' else save_format
                output_path = AsposeApp.output_path + Utils.get_filename(output_filename) + '.' + save_format
                Utils.save_file(response, output_path)
                return output_path
            else:
                return response.content
        else:
            return validate_output

    def convert(self, save_format, stream_out=False, output_filename=None,
                remote_folder='', storage_type='Aspose', storage_name=None):
        """

        :param save_format:
        :param stream_out:
        :param output_filename:
        :param remote_folder: storage path to operate
        :param storage_type: type of storage e.g Aspose, S3
        :param storage_name: name of storage e.g. MyAmazonS3
        :return:
        """
        if not save_format:
            raise ValueError("save_format not specified")

        str_uri = self.base_uri + '?format=' + save_format
        str_uri = Utils.append_storage(str_uri, remote_folder, storage_type, storage_name)

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
        validate_output = Utils.validate_result(response)
        if not validate_output:
            if not stream_out:
                if output_filename is None:
                    output_filename = self.filename
                save_format = 'zip' if save_format == 'html' else save_format
                output_path = AsposeApp.output_path + Utils.get_filename(output_filename) + '.' + save_format
                Utils.save_file(response, output_path)
                return output_path
            else:
                return response.content
        else:
            return validate_output

    @staticmethod
    def convert_local_file(input_file, save_format, stream_out=False, output_filename=None):
        """

        :param input_file:
        :param save_format:
        :param stream_out:
        :param output_filename:
        :return:
        """
        if not input_file:
            raise ValueError("input_file not specified")

        if not save_format:
            raise ValueError("save_format not specified")

        str_uri = Product.product_uri + 'cells/convert?format=' + save_format

        signed_uri = Utils.sign(str_uri)
        response = None
        try:
            response = Utils.upload_file_binary(input_file, signed_uri)
            response.raise_for_status()
        except requests.HTTPError as e:
            print e
            print response.content
            exit(1)

        validate_output = Utils.validate_result(response)
        if not validate_output:
            if not stream_out:
                if output_filename is None:
                    output_filename = input_file
                save_format = 'zip' if save_format == 'html' else save_format
                output_path = AsposeApp.output_path + Utils.get_filename(output_filename) + '.' + save_format
                Utils.save_file(response, output_path)
                return output_path
            else:
                return response.content
        else:
            return validate_output
