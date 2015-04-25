__author__ = 'AssadMahmood'
import unittest
import asposecloud
import os.path
import json

from asposecloud.storage import Folder
from asposecloud.slides import Converter
from asposecloud.slides import Document
from asposecloud.slides import Extractor

class TestAsposeSlides(unittest.TestCase):

    def setUp(self):
        with open('setup.json') as json_file:
            data = json.load(json_file)

        asposecloud.AsposeApp.app_key = str(data['app_key'])
        asposecloud.AsposeApp.app_sid = str(data['app_sid'])
        asposecloud.AsposeApp.output_path = str(data['output_location'])
        asposecloud.Product.product_uri = str(data['product_uri'])

    def test_convert_storage_file(self):
        folder = Folder()
        response = folder.upload_file('./data/test_convert_slide.pptx')
        self.assertEqual(True, response)

        converter = Converter('test_convert_slide.pptx')
        converter.convert(1,'png')
        self.assertTrue(os.path.exists('./output/test_convert_slide_1.png'))

    def test_get_shape(self):
        folder = Folder()
        response = folder.upload_file('./data/test_convert_slide.pptx')
        self.assertEqual(True, response)

        ex = Extractor('test_convert_slide.pptx')
        response = ex.get_shape(1,1)
        self.assertNotEquals(False, response)


if __name__ == '__main__':
    unittest.main()
