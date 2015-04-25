__author__ = 'AssadMahmood'
import unittest
import asposecloud
import os.path
import json

from asposecloud.storage import Folder
from asposecloud.pdf import Converter
from asposecloud.pdf import TextEditor
from asposecloud.pdf import Document


class TestAsposePdf(unittest.TestCase):

    def setUp(self):
        with open('setup.json') as json_file:
            data = json.load(json_file)

        asposecloud.AsposeApp.app_key = str(data['app_key'])
        asposecloud.AsposeApp.app_sid = str(data['app_sid'])
        asposecloud.AsposeApp.output_path = str(data['output_location'])
        asposecloud.Product.product_uri = str(data['product_uri'])

    def test_create_pdf(self):
        folder = Folder()
        response = folder.upload_file('./data/create_pdf_test.svg')
        response = folder.upload_file('./data/create_pdf_test.jpg')

        # Create object of pdf document class
        document = Document('create_pdf_svg.pdf')

        document.create_pdf('create_pdf_test.svg','svg')
        self.assertEqual(True, os.path.exists('./output/create_pdf_svg.pdf'))

        # Create object of pdf document class
        documenta = Document('create_pdf_jpeg.pdf')

        documenta.create_pdf('create_pdf_test.jpg','jpeg')
        self.assertEqual(True, os.path.exists('./output/create_pdf_jpeg.pdf'))


    def test_convert_local_file(self):
        # Create object of pdf converter class
        converter = Converter('file_on_storage.pdf')
        converter.convert_local_file('./data/test_convertlocal.pdf', 'doc')
        self.assertEqual(True, os.path.exists('./output/test_convertlocal.doc'))

    def test_convert_storage_file(self):
        folder = Folder()
        response = folder.upload_file('./data/test_file_on_storage.pdf')
        self.assertEqual(True, response)

        converter = Converter('test_file_on_storage.pdf')
        raised = False
        try:
            response = converter.convert('doc')
        except:
            raised = True
        self.assertFalse(raised)

    def test_replace_text(self):
        folder = Folder()
        response = folder.upload_file('./data/test_replace_text.pdf')
        self.assertEqual(True, response)

        editor = TextEditor('test_replace_text.pdf')
        raised = False
        try:
            response = editor.replace_text(1, 'Kevin', 'Nick', False)
        except:
            raised = True
        self.assertFalse(raised)

if __name__ == '__main__':
    unittest.main()
