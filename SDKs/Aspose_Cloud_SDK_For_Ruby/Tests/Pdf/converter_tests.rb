
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

  # Convert a particular page to image with default size
  def test_convert_to_image
    # Create Object of folder class
    folder = Aspose::Cloud::AsposeStorage::Folder.new
    response = folder.upload_file '../Data/test_convert.pdf'
    assert_equal true, response

    # Create object of slides converter class
    converter = Aspose::Cloud::Pdf::Converter.new('test_convert.pdf')
    assert_nothing_thrown 'Error' do
      converter.convert_to_image(page_number=1, image_format='png')
    end

    assert_equal true, File.exist?('../Output/test_convert_1.png')
  end

  # Convert a particular page to image with specified size
  def test_convert_to_image_by_size
    converter = Aspose::Cloud::Pdf::Converter.new('test_convert.pdf')
    assert_nothing_thrown 'Error' do
      converter.convert_to_image_by_size(page_number=1, image_format='png', width=100, height=100)
    end

    assert_equal true, File.exist?('../Output/test_convert_1.png')
  end

  # Convert a pdf document to specified format
  def test_convert
    converter = Aspose::Cloud::Pdf::Converter.new('Test.pdf')
    assert_nothing_thrown 'Error' do
      converter.convert(save_format='tiff')
    end

    assert_equal true, File.exist?('../Output/Test.tiff')
  end

  # Convert a local pdf document to specified format without using storage
  def test_convert_local_file
    converter = Aspose::Cloud::Pdf::Converter.new('Test.pdf')
    assert_nothing_thrown 'Error' do
      input_file = '../Data/test_file_on_storage.pdf'
      converter.convert_local_file(input_file, output_file='output.tiff', save_format='tiff')
    end

    assert_equal true, File.exist?('../Output/test_file_on_storage.tiff')
  end

  # Convert PDF from Remote Server to other Formats
  def test_convert_by_url
    converter = Aspose::Cloud::Pdf::Converter.new('Test.pdf')
    assert_nothing_thrown 'Error' do
      url = 'http://linux.hanski.info/tests/scribus_form_en.pdf'
      converter.convert_by_url(url, save_format='tiff', output_file='output.tiff')
    end

    assert_equal true, File.exist?('../Output/output.tiff')
  end  
end