__author__ = 'AssadMahmood'
import requests
import json

from asposecloud import AsposeApp
from asposecloud import Product
from asposecloud.common import Utils


# ========================================================================
# EXTRACTOR CLASS
# ========================================================================


class Document:

    def __init__(self, filename):
        self.filename = filename

        if not filename:
            raise ValueError("filename not specified")

        self.base_uri = Product.product_uri + 'slides/' + self.filename

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
        return response['DocumentProperties']['List']

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

        json_data = json.dumps({'Value': property_value})

        str_uri = self.base_uri + '/documentProperties/' + property_name
        str_uri = Utils.append_storage(str_uri, remote_folder, storage_type, storage_name)

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
                'content-type': 'application/json', 'accept': 'application/json'
            })
            response.raise_for_status()
            response = response.json()
        except requests.HTTPError as e:
            print e
            print response.content
            exit(1)
        return True if response['Code'] == 200 else False

    def delete_all_properties(self, remote_folder='', storage_type='Aspose', storage_name=None):
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

    def add_custom_property(self, properties_list, remote_folder='', storage_type='Aspose', storage_name=None):
        """

        :param properties_list:
        :param remote_folder: storage path to operate
        :param storage_type: type of storage e.g Aspose, S3
        :param storage_name: name of storage e.g. MyAmazonS3
        :return:
        """

        json_data = json.dumps(properties_list)

        str_uri = self.base_uri + '/documentProperties'
        str_uri = Utils.append_storage(str_uri, remote_folder, storage_type, storage_name)

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
        return response

    def get_background(self, slide_number, remote_folder='', storage_type='Aspose', storage_name=None):
        """

        :param slide_number:
        :param remote_folder: storage path to operate
        :param storage_type: type of storage e.g Aspose, S3
        :param storage_name: name of storage e.g. MyAmazonS3
        :return:
        """

        if not slide_number:
            raise ValueError("slide_number not specified")

        str_uri = self.base_uri + '/slides/' + str(slide_number) + '/background'
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
        return response['Background']

    def delete_background(self, slide_number, remote_folder='', storage_type='Aspose', storage_name=None):
        """

        :param slide_number:
        :param remote_folder: storage path to operate
        :param storage_type: type of storage e.g Aspose, S3
        :param storage_name: name of storage e.g. MyAmazonS3
        :return:
        """

        if not slide_number:
            raise ValueError("slide_number not specified")

        str_uri = self.base_uri + '/slides/' + str(slide_number) + '/background'
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

    def delete_slide(self, slide_number, remote_folder='', storage_type='Aspose', storage_name=None):
        """

        :param slide_number:
        :param remote_folder: storage path to operate
        :param storage_type: type of storage e.g Aspose, S3
        :param storage_name: name of storage e.g. MyAmazonS3
        :return:
        """

        if not slide_number:
            raise ValueError("slide_number not specified")

        str_uri = self.base_uri + '/slides/' + str(slide_number)
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

    def get_slide_count(self, remote_folder='', storage_type='Aspose', storage_name=None):
        """

        :param remote_folder: storage path to operate
        :param storage_type: type of storage e.g Aspose, S3
        :param storage_name: name of storage e.g. MyAmazonS3
        :return:
        """
        str_uri = self.base_uri + '/slides'
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

        return len(response['Slides']['SlideList']) if response['Slides']['SlideList'] else 0

    def replace_text(self, slide_number, old_text, new_text,
                     remote_folder='', storage_type='Aspose', storage_name=None):
        """

        :param slide_number:
        :param old_text:
        :param new_text:
        :param remote_folder: storage path to operate
        :param storage_type: type of storage e.g Aspose, S3
        :param storage_name: name of storage e.g. MyAmazonS3
        :return:
        """
        str_uri = self.base_uri + '/slides/' + str(slide_number) + '/replaceText'
        str_uri = Utils.append_storage(str_uri, remote_folder, storage_type, storage_name)

        json_data = json.dumps({'OldValue': old_text, 'NewValue': new_text})

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

        validate_output = Utils.validate_result(response)
        if not validate_output:
            return True
        else:
            return validate_output

    def get_text_items(self, slide_number, remote_folder='', storage_type='Aspose', storage_name=None):
        """

        :param slide_number:
        :param remote_folder: storage path to operate
        :param storage_type: type of storage e.g Aspose, S3
        :param storage_name: name of storage e.g. MyAmazonS3
        :return:
        """
        str_uri = self.base_uri + '/slides/' + str(slide_number) + '/textItems'
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

        return response['TextItems']['Items'] if response['TextItems']['Items'] else False

# ========================================================================
# EXTRACTOR CLASS
# ========================================================================


class Extractor:

    def __init__(self, filename):
        self.filename = filename

        if not filename:
            raise ValueError("filename not specified")

        self.base_uri = Product.product_uri + 'slides/' + self.filename

    def get_image_count(self, remote_folder='', storage_type='Aspose', storage_name=None):
        """

        :param remote_folder: storage path to operate
        :param storage_type: type of storage e.g Aspose, S3
        :param storage_name: name of storage e.g. MyAmazonS3
        :return:
        """

        str_uri = self.base_uri + '/images'
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
        return len(response['Images']['List']) if response['Images']['List'] else 0

    def get_slide_image_count(self, slide_number, remote_folder='', storage_type='Aspose', storage_name=None):
        """

        :param slide_number:
        :param remote_folder: storage path to operate
        :param storage_type: type of storage e.g Aspose, S3
        :param storage_name: name of storage e.g. MyAmazonS3
        :return:
        """

        if not slide_number:
            raise ValueError("slide_number not specified")

        str_uri = self.base_uri + '/slides/' + str(slide_number) + '/images'
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
        return len(response['Images']['List']) if response['Images']['List'] else 0

    def get_shapes(self, slide_number, remote_folder='', storage_type='Aspose', storage_name=None):
        """

        :param slide_number:
        :param remote_folder: storage path to operate
        :param storage_type: type of storage e.g Aspose, S3
        :param storage_name: name of storage e.g. MyAmazonS3
        :return:
        """

        if not slide_number:
            raise ValueError("slide_number not specified")

        str_uri = self.base_uri + '/slides/' + str(slide_number) + '/shapes'
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
        return response['ShapeList']['ShapesLinks']

    def get_shape(self, slide_number, shape_index, remote_folder='', storage_type='Aspose', storage_name=None):
        """

        :param slide_number:
        :param shape_index:
        :param remote_folder: storage path to operate
        :param storage_type: type of storage e.g Aspose, S3
        :param storage_name: name of storage e.g. MyAmazonS3
        :return:
        """

        if not slide_number:
            raise ValueError("slide_number not specified")

        if not shape_index:
            raise ValueError("shape_index not specified")

        str_uri = self.base_uri + '/slides/' + str(slide_number) + '/shapes/' + str(shape_index)
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
        return response['Shape']

    def get_color_scheme(self, slide_number, remote_folder='', storage_type='Aspose', storage_name=None):
        """

        :param slide_number:
        :param remote_folder: storage path to operate
        :param storage_type: type of storage e.g Aspose, S3
        :param storage_name: name of storage e.g. MyAmazonS3
        :return:
        """

        if not slide_number:
            raise ValueError("slide_number not specified")

        str_uri = self.base_uri + '/slides/' + str(slide_number) + '/theme/colorScheme'
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
        return response['ColorScheme']

    def get_font_scheme(self, slide_number, remote_folder='', storage_type='Aspose', storage_name=None):
        """

        :param slide_number:
        :param remote_folder: storage path to operate
        :param storage_type: type of storage e.g Aspose, S3
        :param storage_name: name of storage e.g. MyAmazonS3
        :return:
        """

        if not slide_number:
            raise ValueError("slide_number not specified")

        str_uri = self.base_uri + '/slides/' + str(slide_number) + '/theme/fontScheme'
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
        return response['FontScheme']

    def get_format_scheme(self, slide_number, remote_folder='', storage_type='Aspose', storage_name=None):
        """

        :param slide_number:
        :param remote_folder: storage path to operate
        :param storage_type: type of storage e.g Aspose, S3
        :param storage_name: name of storage e.g. MyAmazonS3
        :return:
        """

        if not slide_number:
            raise ValueError("slide_number not specified")

        str_uri = self.base_uri + '/slides/' + str(slide_number) + '/theme/formatScheme'
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
        return response['FormatScheme']

    def get_placeholder(self, slide_number, placeholder_index,
                        remote_folder='', storage_type='Aspose', storage_name=None):
        """

        :param slide_number:
        :param placeholder_index:
        :param remote_folder: storage path to operate
        :param storage_type: type of storage e.g Aspose, S3
        :param storage_name: name of storage e.g. MyAmazonS3
        :return:
        """

        if not slide_number:
            raise ValueError("slide_number not specified")

        if not placeholder_index:
            raise ValueError("placeholder_index not specified")

        str_uri = self.base_uri + '/slides/' + str(slide_number) + '/placeholders/' + str(placeholder_index)
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
        return response['Placeholder']

    def get_placeholder_count(self, slide_number, remote_folder='', storage_type='Aspose', storage_name=None):
        """

        :param slide_number:
        :param remote_folder: storage path to operate
        :param storage_type: type of storage e.g Aspose, S3
        :param storage_name: name of storage e.g. MyAmazonS3
        :return:
        """

        if not slide_number:
            raise ValueError("slide_number not specified")

        str_uri = self.base_uri + '/slides/' + str(slide_number) + '/placeholders'
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
        return len(response['Placeholders']['PlaceholderLinks']) if response['Placeholders']['PlaceholderLinks'] else 0

# ========================================================================
# CONVERTER CLASS
# ========================================================================


class Converter:

    def __init__(self, filename):
        self.filename = filename

        if not filename:
            raise ValueError("filename not specified")

        self.base_uri = Product.product_uri + 'slides/' + self.filename

    def convert(self, slide_number, save_format, stream_out=False, output_filename=None,
                remote_folder='', storage_type='Aspose', storage_name=None):
        """

        :param slide_number:
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

        if not slide_number:
            raise ValueError("slide_number not specified")

        str_uri = self.base_uri + '/slides/' + str(slide_number) + '?format=' + save_format
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
                output_path = AsposeApp.output_path + Utils.get_filename(output_filename) + '_' + str(slide_number) + '.' + \
                    save_format
                Utils.save_file(response, output_path)
                return output_path
            else:
                return response.content
        else:
            return validate_output

    @staticmethod
    def convert_local_file(input_file, save_format, stream_out=False, output_filename=None):
        """
        Convert a local pdf file to any supported format

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

        str_uri = Product.product_uri + 'slides/convert?format=' + save_format

        signed_uri = Utils.sign(str_uri)
        response = None
        try:
            response = Utils.upload_file_binary(input_file, signed_uri)
            response.raise_for_status()
        except requests.HTTPError as e:
            print e
            print response.content
            exit(1)

        if not stream_out:
            if output_filename is None:
                output_filename = input_file
            save_format = 'zip' if save_format == 'html' else save_format
            output_path = AsposeApp.output_path + Utils.get_filename(output_filename) + '.' + save_format
            Utils.save_file(response, output_path)
            return output_path
        else:
            return response.content

    def convert_to_image(self, slide_number, save_format, width=None, height=None, stream_out=False,
                         output_filename=None, remote_folder='', storage_type='Aspose', storage_name=None):
        """

        :param slide_number:
        :param save_format:
        :param width:
        :param height:
        :param stream_out:
        :param output_filename:
        :param remote_folder: storage path to operate
        :param storage_type: type of storage e.g Aspose, S3
        :param storage_name: name of storage e.g. MyAmazonS3
        :return:
        """
        if not save_format:
            raise ValueError("save_format not specified")

        if not slide_number:
            raise ValueError("slide_number not specified")

        str_uri = self.base_uri + '/slides/' + str(slide_number)
        qry = {'format': save_format}
        if width and height:
            qry['width'] = width
            qry['height'] = height
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
                output_path = AsposeApp.output_path + Utils.get_filename(output_filename) + '_' + str(slide_number) + '.' + \
                    save_format
                Utils.save_file(response, output_path)
                return output_path
            else:
                return response.content
        else:
            return validate_output
