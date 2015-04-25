__author__ = 'AssadMahmood'
import unittest
import asposecloud
import os.path
import json

from asposecloud.storage import Folder
from asposecloud.cells import Converter
from asposecloud.cells import Workbook
from asposecloud.cells import Worksheet


class TestAsposeCells(unittest.TestCase):

    def setUp(self):
        with open('setup.json') as json_file:
            data = json.load(json_file)

        asposecloud.AsposeApp.app_key = str(data['app_key'])
        asposecloud.AsposeApp.app_sid = str(data['app_sid'])
        asposecloud.AsposeApp.output_path = str(data['output_location'])
        asposecloud.Product.product_uri = str(data['product_uri'])

    def test_convert_storage_file(self):
        folder = Folder()
        response = folder.upload_file('./data/test_convert_cell.xlsx')
        self.assertEqual(True, response)

        converter = Converter('test_convert_cell.xlsx')
        converter.convert('tiff')
        self.assertTrue(os.path.exists('./output/test_convert_cell.tiff'))

    def test_split_workbook(self):
        folder = Folder()
        response = folder.upload_file('./data/split_workbook.xlsx')
        self.assertEqual(True, response)

        wb = Workbook('split_workbook.xlsx')
        response = wb.split_workbook('xlsx')
        self.assertEqual(list, type(response))

    def test_get_first_cell(self):
        folder = Folder()
        response = folder.upload_file('./data/split_workbook.xlsx')
        self.assertEqual(True, response)

        ws = Worksheet('split_workbook.xlsx','Sheet1')
        response = ws.get_first_cell(0,0);
        self.assertEqual(dict, type(response))

    def test_get_last_cell(self):
        folder = Folder()
        response = folder.upload_file('./data/split_workbook.xlsx')
        self.assertEqual(True, response)

        ws = Worksheet('split_workbook.xlsx','Sheet1')
        response = ws.get_last_cell(0,0);
        self.assertEqual(dict, type(response))

    def test_get_max_row(self):
        folder = Folder()
        response = folder.upload_file('./data/split_workbook.xlsx')
        self.assertEqual(True, response)

        ws = Worksheet('split_workbook.xlsx','Sheet1')
        response = ws.get_max_row(0,0);
        self.assertEqual(int, type(response))

    def test_get_max_data_row(self):
        folder = Folder()
        response = folder.upload_file('./data/split_workbook.xlsx')
        self.assertEqual(True, response)

        ws = Worksheet('split_workbook.xlsx','Sheet1')
        response = ws.get_max_data_row(0,0);
        self.assertEqual(int, type(response))

    def test_get_max_column(self):
        folder = Folder()
        response = folder.upload_file('./data/split_workbook.xlsx')
        self.assertEqual(True, response)

        ws = Worksheet('split_workbook.xlsx','Sheet1')
        response = ws.get_max_column(0,0);
        self.assertEqual(int, type(response))

    def test_get_max_data_column(self):
        folder = Folder()
        response = folder.upload_file('./data/split_workbook.xlsx')
        self.assertEqual(True, response)

        ws = Worksheet('split_workbook.xlsx','Sheet1')
        response = ws.get_max_data_column(0,0);
        self.assertEqual(int, type(response))

    def test_get_min_row(self):
        folder = Folder()
        response = folder.upload_file('./data/split_workbook.xlsx')
        self.assertEqual(True, response)

        ws = Worksheet('split_workbook.xlsx','Sheet1')
        response = ws.get_min_row(0,0);
        self.assertEqual(int, type(response))

    def test_get_min_data_row(self):
        folder = Folder()
        response = folder.upload_file('./data/split_workbook.xlsx')
        self.assertEqual(True, response)

        ws = Worksheet('split_workbook.xlsx','Sheet1')
        response = ws.get_min_data_row(0,0);
        self.assertEqual(int, type(response))

    def test_get_min_column(self):
        folder = Folder()
        response = folder.upload_file('./data/split_workbook.xlsx')
        self.assertEqual(True, response)

        ws = Worksheet('split_workbook.xlsx','Sheet1')
        response = ws.get_min_column(0,0);
        self.assertEqual(int, type(response))

    def test_get_min_data_column(self):
        folder = Folder()
        response = folder.upload_file('./data/split_workbook.xlsx')
        self.assertEqual(True, response)

        ws = Worksheet('split_workbook.xlsx','Sheet1')
        response = ws.get_min_data_column(0,0);
        self.assertEqual(int, type(response))

if __name__ == '__main__':
    unittest.main()
