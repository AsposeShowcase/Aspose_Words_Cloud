require 'test/unit'
require_relative '../../lib/asposecloud'

class ExtractorTests < Test::Unit::TestCase

  def setup
    file = File.read('../setup.json')
    data = JSON.parse(file)

    Aspose::Cloud::Common::AsposeApp.app_key = data['app_key']
    Aspose::Cloud::Common::AsposeApp.app_sid = data['app_sid']
    Aspose::Cloud::Common::AsposeApp.output_location = data['output_location']
    Aspose::Cloud::Common::Product.set_base_product_uri(data['product_uri'])
  end

  def test_extract_text
    extractor = Aspose::Cloud::OCR::Extractor.new
    response = extractor.extract_text(image_file_name='ocr.png', folder='')
    assert_instance_of(Hash, response)
  end
  
  def test_extract_text_from_local_file
    extractor = Aspose::Cloud::OCR::Extractor.new
    response = extractor.extract_text_from_local_file(local_file='../Data/barcodeQR.png',language='english',use_default_dictionaries=true)
    assert_instance_of(Hash, response)
  end

  def test_extract_text_from_url
    extractor = Aspose::Cloud::OCR::Extractor.new
    url = "http://cdns2.freepik.com/free-photo/colorful-curves-sample-text-vector_21-14522518.jpg"
    response = extractor.extract_text_from_url(url,language='english',use_default_dictionaries=false)
    assert_instance_of(Hash, response)
  end  
end