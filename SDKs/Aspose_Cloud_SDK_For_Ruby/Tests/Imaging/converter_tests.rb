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

  # Convert Image Format
  def test_convert_local_file
    converter = Aspose::Cloud::Imaging::Converter.new('barcodeQR.png')
    assert_nothing_thrown 'Error' do
      converter.convert_local_file(input_file_path='../Data/barcodeQR.png', output_filename='barcodeQR.bmp', save_format='bmp')
    end
    assert_equal true, File.exist?('../Output/barcodeQR.bmp')
  end

  def test_convert_tiff_to_fax
    converter = Aspose::Cloud::Imaging::Converter.new('barcodeQR.png')
    assert_nothing_thrown 'Error' do
      converter.convert_tiff_to_fax()
    end
    assert_equal true, File.exist?('../Output/barcodeQR.png')
  end
end
