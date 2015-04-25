
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

  # Get number of images in a specified page
  def test_get_image_count
    extractor = Aspose::Cloud::Pdf::Extractor.new('Test.pdf')
    response = extractor.get_image_count(page_number=1)
    assert_equal true, response>=0
  end

  # Get the particular image from the specified page with the default image size
  def test_get_image_default_size
    extractor = Aspose::Cloud::Pdf::Extractor.new('Test.pdf')
    response = extractor.get_image_default_size(page_number=1, image_index=1, image_format='png')
    assert_equal true, File.exist?('../Output/Test_1.png')
  end

  # Get the particular image from the specified page with the custom image size
  def test_get_image_custom_size
    extractor = Aspose::Cloud::Pdf::Extractor.new('Test.pdf')
    response = extractor.get_image_custom_size(page_number=1, image_index=1, image_format='png', width=100, height=100)
    assert_equal true, File.exist?('../Output/Test_1.png')
  end    
end