__author__ = 'AssadMahmood'
import unittest
import asposecloud
import os.path
import json

from asposecloud.imaging import Converter
from asposecloud.imaging import Image
from asposecloud.imaging import Document
from asposecloud.storage import Folder


class TestAsposeWords(unittest.TestCase):

    def setUp(self):
        with open('setup.json') as json_file:
            data = json.load(json_file)

        asposecloud.AsposeApp.app_key = str(data['app_key'])
        asposecloud.AsposeApp.app_sid = str(data['app_sid'])
        asposecloud.AsposeApp.output_path = str(data['output_location'])
        asposecloud.Product.product_uri = str(data['product_uri'])

    def test_update_psd_properties_local(self):
        document = Document('barcodeQR.psd')
        document.update_jpg_properties_local('./data/barcodeQR.psd',4,'raw')

        self.assertEqual(True, os.path.exists('./output/barcodeQR_updated.psd'))

    def test_update_psd_properties(self):
        fld = Folder()
        response = fld.upload_file('./data/barcodeQR.psd')
        self.assertEqual(response, True)

        document = Document('barcodeQR.psd')
        document.update_jpg_properties(4,'raw','')

        self.assertEqual(True, os.path.exists('./output/barcodeQR_updated.psd'))

    def test_update_jpg_properties_local(self):
        document = Document('barcodeQR.jpg')
        document.update_jpg_properties_local('./data/barcodeQR.jpg',45,'baseline')

        self.assertEqual(True, os.path.exists('./output/barcodeQR_updated.jpg'))

    def test_update_jpg_properties(self):
        fld = Folder()
        response = fld.upload_file('./data/barcodeQR.jpg')
        self.assertEqual(response, True)

        document = Document('barcodeQR.jpg')
        document.update_jpg_properties(45,'baseline','')

        self.assertEqual(True, os.path.exists('./output/barcodeQR_updated.jpg'))

    def test_update_gif_properties_local(self):
        document = Document('barcodeQR.gif')
        document.update_gif_properties_local('./data/barcodeQR.gif',32,3,True)

        self.assertEqual(True, os.path.exists('./output/barcodeQR_updated.gif'))

    def test_update_gif_properties(self):
        fld = Folder()
        response = fld.upload_file('./data/barcodeQR.gif')
        self.assertEqual(response, True)

        document = Document('barcodeQR.gif')
        document.update_gif_properties(32,3,True,'')

        self.assertEqual(True, os.path.exists('./output/barcodeQR_updated.bmp'))

    def test_update_bmp_properties_local(self):
        document = Document('barcodeQR.bmp')
        document.update_bmp_properties_local('./data/barcodeQR.bmp',24,100,100)

        self.assertEqual(True, os.path.exists('./output/barcodeQR_updated.bmp'))

    def test_update_bmp_properties(self):
        fld = Folder()
        response = fld.upload_file('./data/barcodeQR.bmp')
        self.assertEqual(response, True)

        document = Document('barcodeQR.bmp')
        document.update_bmp_properties(24,100,100,'')

        self.assertEqual(True, os.path.exists('./output/barcodeQR_updated.bmp'))


    def test_get_properties(self):
        fld = Folder()
        response = fld.upload_file('./data/barcodeQR.png')
        self.assertEqual(response, True)

        document = Document('barcodeQR.png')
        response = document.get_properties()

        self.assertEqual(dict, type(response))

    def test_rotate_image(self):
        fld = Folder()
        response = fld.upload_file('./data/barcodeQR.png')
        self.assertEqual(response, True)

        imageObj = Image('barcodeQR.png')
        imageObj.rotate_image('rotate270flipnone','/','png')
        self.assertEqual(True, os.path.exists('./output/barcodeQR.png'))

    def test_resize_image(self):
        fld = Folder()
        response = fld.upload_file('./data/barcodeQR.png')
        self.assertEqual(response, True)

        imageObj = Image('barcodeQR.png')
        imageObj.resize_image('./data/barcodeQR.png',10,10,'barcodeQR.png','png')
        self.assertEqual(True, os.path.exists('./output/barcodeQR.png'))

    def test_convert_local_file(self):
        converter = Converter('barcodeQR.png')
        converter.convert_local_file('./data/barcodeQR.png', 'psd')
        self.assertEqual(True, os.path.exists('./output/barcodeQR.psd'))

    def test_convert_tiff_to_fax(self):
        fld = Folder()
        response = fld.upload_file('./data/barcodeQR.tiff')
        self.assertEqual(response, True)

        converter = Converter('barcodeQR.tiff')
        converter.convert_tiff_to_fax()
        self.assertEqual(True, os.path.exists('./output/barcodeQR.tiff'))

if __name__ == '__main__':
    unittest.main()