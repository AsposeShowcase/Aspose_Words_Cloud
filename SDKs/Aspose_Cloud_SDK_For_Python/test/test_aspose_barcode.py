__author__ = 'AssadMahmood'
import unittest
import json
import asposecloud

from asposecloud.barcode import Builder
from asposecloud.barcode import Reader


class TestAsposeBarcode(unittest.TestCase):

    def setUp(self):
        with open('setup.json') as json_file:
            data = json.load(json_file)

        asposecloud.AsposeApp.app_key = str(data['app_key'])
        asposecloud.AsposeApp.app_sid = str(data['app_sid'])
        asposecloud.AsposeApp.output_path = str(data['output_location'])
        asposecloud.Product.product_uri = str(data['product_uri'])

    def test_save_barcode(self):
        builder = Builder()
        response = builder.generate('Text to generate barcode', 'QR', 'png')
        self.assertEqual(response.status_code, 200)

    def test_read_barcode_local(self):
        reader = Reader('barcodeQR.png')
        response = reader.read_from_local_image('./data/barcodeQR.png', 'QR')
        self.assertEqual(list, type(response))

    def test_read_from_url(self):
        reader = Reader('barcodeQR.png')
        response = reader.read_from_url('http://www.qrstuff.com/images/default_qrcode.png');
        self.assertEqual(list, type(response))

    def test_read_region(self):
        reader = Reader('barcodeQR.png')
        response = reader.read_region(0,0,175,175)
        self.assertEqual(list, type(response))

if __name__ == '__main__':
    unittest.main()
