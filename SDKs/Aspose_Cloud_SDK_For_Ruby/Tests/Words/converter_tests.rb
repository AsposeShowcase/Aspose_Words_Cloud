require 'test/unit'
require_relative '../../lib/asposecloud'

class ConverterTests < Test::Unit::TestCase

  def setup
    file = File.read('../setup.json')
    data = JSON.parse(file)

    Aspose::Cloud::Common::AsposeApp.app_key = data['app_key']
    Aspose::Cloud::Common::AsposeApp.app_sid = data['app_sid']
    Aspose::Cloud::Common::AsposeApp.output_location = data['output_location']
    Aspose::Cloud::Common::Product.set_base_product_uri(data['product_uri'])
  end

  # Convert document to other formats using storage
  def test_convert
    # Create object of folder class
    folder = Aspose::Cloud::AsposeStorage::Folder.new
    response = folder.upload_file '../Data/test_multi_pages.docx'
    assert_equal true, response

    converter = Aspose::Cloud::Words::Converter.new('test_multi_pages.docx')
    assert_nothing_thrown 'Error' do
      converter.convert(save_format='pdf')
    end
     assert_equal true, File.exist?('../Output/test_multi_pages.pdf')
  end

  # Convert document to other formats without storage
  def test_convert_local_file
    converter = Aspose::Cloud::Words::Converter.new('test_multi_pages.docx')
    assert_nothing_thrown 'Error' do
      converter.convert_local_file(input_file_path='../Data/test_multi_pages.docx', output_filename='test_multi_pages.pdf', save_format='pdf')
    end
     assert_equal true, File.exist?('../Output/test_multi_pages.pdf')
  end

  # Convert web pages to Word Documents
  def test_convert_web_pages
    converter = Aspose::Cloud::Words::Converter.new('test_multi_pages.docx')
    assert_nothing_thrown 'Error' do
      str_xml = '<LoadWebDocumentData>
                  <LoadingDocumentUrl>http://google.com</LoadingDocumentUrl>
                  <DocSaveOptionsData>
                    <SaveFormat>doc</SaveFormat>
                    <FileName>google.doc</FileName>
                  </DocSaveOptionsData>
                </LoadWebDocumentData>'
      converter.convert_web_pages(str_xml)
    end
     assert_equal true, File.exist?('../Output/google.doc')
  end  
end
