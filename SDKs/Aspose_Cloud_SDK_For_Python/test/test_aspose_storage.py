__author__ = 'AssadMahmood'
import unittest
import asposecloud
import os.path
import json

from asposecloud.storage import Folder
from asposecloud.common import Utils


class TestAsposeStorage(unittest.TestCase):

    def setUp(self):
        with open('setup.json') as json_file:
            data = json.load(json_file)

        asposecloud.AsposeApp.app_key = str(data['app_key'])
        asposecloud.AsposeApp.app_sid = str(data['app_sid'])
        asposecloud.AsposeApp.output_path = str(data['output_location'])
        asposecloud.Product.product_uri = str(data['product_uri'])

    def test_get_files(self):
        fld = Folder()
        files = fld.get_files()
        assert(isinstance(files, list))

    def test_upload_file(self):
        fld = Folder()
        response = fld.upload_file('./data/test_uploadfile.docx')
        self.assertEqual(response, True)

    def test_get_file(self):
        fld = Folder()
        response = fld.get_file('test_uploadfile.docx')
        Utils.save_file(response, asposecloud.AsposeApp.output_path + 'test_uploadfile.docx')
        self.assertEqual(True, os.path.exists(asposecloud.AsposeApp.output_path + 'test_uploadfile.docx'))

if __name__ == '__main__':
    unittest.main()
