__author__ = 'AssadMahmood'
import unittest
import asposecloud
import os.path
import json

from asposecloud.words import Converter
from asposecloud.words import Document
from asposecloud.storage import Folder
from asposecloud.words import Extractor
from asposecloud.words import MailMerge

class TestAsposeWords(unittest.TestCase):

    def setUp(self):
        with open('setup.json') as json_file:
            data = json.load(json_file)

        asposecloud.AsposeApp.app_key = str(data['app_key'])
        asposecloud.AsposeApp.app_sid = str(data['app_sid'])
        asposecloud.AsposeApp.output_path = str(data['output_location'])
        asposecloud.Product.product_uri = str(data['product_uri'])

    def test_mailmerge_execute(self):
        fld = Folder()
        response = fld.upload_file('./data/SimpleMerge/SimpleMerge.docx')
        self.assertEqual(response, True)

        f = open('./data/SimpleMerge/SimpleMerge.xml')
        xml = f.read()

        mailmerge = MailMerge('SimpleMerge.docx')
        response = mailmerge.execute(xml)
        self.assertEqual(True, os.path.exists('./output/SimpleMerge.docx'))

    def test_mailmerge_execute_template(self):
        fld = Folder()
        response = fld.upload_file('./data/ExecuteTemplate/ExecuteTemplate.doc')
        self.assertEqual(response, True)

        f = open('./data/ExecuteTemplate/ExecuteTemplate.xml')
        xml = f.read()

        mailmerge = MailMerge('ExecuteTemplate.doc')
        response = mailmerge.execute_template(xml)
        self.assertEqual(True, os.path.exists('./output/ExecuteTemplate.doc'))

    def test_get_paragraph_run_font(self):
        fld = Folder()
        response = fld.upload_file('./data/test_multi_pages.docx')
        self.assertEqual(response, True)

        extractor = Extractor('test_multi_pages.docx')
        response = extractor.get_paragraph_run_font(0,0)

        self.assertEqual(dict, type(response))

    def test_get_paragraph_run(self):
        fld = Folder()
        response = fld.upload_file('./data/test_multi_pages.docx')
        self.assertEqual(response, True)

        extractor = Extractor('test_multi_pages.docx')
        response = extractor.get_paragraph_run(0,0)

        self.assertEqual(dict, type(response))

    def test_get_paragraph(self):
        fld = Folder()
        response = fld.upload_file('./data/test_multi_pages.docx')
        self.assertEqual(response, True)

        extractor = Extractor('test_multi_pages.docx')
        response = extractor.get_paragraph(0)

        self.assertEqual(dict, type(response))

    def test_get_paragraphs(self):
        fld = Folder()
        response = fld.upload_file('./data/test_multi_pages.docx')
        self.assertEqual(response, True)

        extractor = Extractor('test_multi_pages.docx')
        response = extractor.get_paragraphs()

        self.assertEqual(dict, type(response))

    def test_get_section(self):
        fld = Folder()
        response = fld.upload_file('./data/test_multi_pages.docx')
        self.assertEqual(response, True)

        extractor = Extractor('test_multi_pages.docx')
        response = extractor.get_sections(0)

        self.assertEqual(dict, type(response))

    def test_get_sections(self):
        fld = Folder()
        response = fld.upload_file('./data/test_multi_pages.docx')
        self.assertEqual(response, True)

        extractor = Extractor('test_multi_pages.docx')
        response = extractor.get_sections()

        self.assertEqual(dict, type(response))


    def test_update_page_setup(self):
        fld = Folder()
        response = fld.upload_file('./data/test_multi_pages.docx')
        self.assertEqual(response, True)

        document = Document('test_multi_pages.docx')
        options_xml = '<PageSetup>\
    <LeftMargin>99</LeftMargin>\
    <Orientation>Landscape</Orientation>\
    <PaperSize>A5</PaperSize>\
  </PageSetup>'
        response = document.update_page_setup(0,options_xml)

        self.assertEqual(dict, type(response))

    def test_get_bookmarks(self):
        fld = Folder()
        response = fld.upload_file('./data/test_multi_pages.docx')
        self.assertEqual(response, True)

        document = Document('test_multi_pages.docx')
        response = document.get_bookmarks()

        self.assertEqual(list, type(response))

    def test_get_page_setup(self):
        fld = Folder()
        response = fld.upload_file('./data/test_multi_pages.docx')
        self.assertEqual(response, True)

        document = Document('test_multi_pages.docx')
        response = document.get_page_setup(0)

        self.assertEqual(dict, type(response))


    def test_split_document(self):
        fld = Folder()
        response = fld.upload_file('./data/test_multi_pages.docx')
        self.assertEqual(response, True)

        document = Document('test_multi_pages.docx')
        response = document.split_document(1,2,'pdf')

        self.assertEqual(dict, type(response))

    def test_save_as(self):
        fld = Folder()
        response = fld.upload_file('./data/test_multi_pages.docx')
        self.assertEqual(response, True)

        document = Document('test_multi_pages.docx')
        xml_options = '<PdfSaveOptions>\
                 <SaveFormat>pdf</SaveFormat>\
                 <FileName>test_file_options.pdf</FileName>\
                 <ImageCompression>Jpeg</ImageCompression>\
                 <JpegQuality>70</JpegQuality>\
                 <TextCompression>Flate</TextCompression>\
                </PdfSaveOptions>'
        document.save_as(xml_options)

        self.assertEqual(True, os.path.exists('./output/test_file_options.pdf'))

    def test_convert_local_file(self):
        # Create object of words converter class
        converter = Converter('file_on_storage.docx')
        converter.convert_local_file('./data/test_convertlocal.docx', 'doc')
        self.assertEqual(True, os.path.exists('./output/test_convertlocal.doc'))

if __name__ == '__main__':
    unittest.main()
