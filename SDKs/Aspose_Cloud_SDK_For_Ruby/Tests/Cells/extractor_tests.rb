
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

  def test_get_picture    
    extractor = Aspose::Cloud::Cells::Extractor.new('test_cells.xlsx')
    result = extractor.get_picture(worksheet_name='Sheet2', picture_index=0, image_format='png')
    assert_equal true, File.exist?('../Output/test_cells_Sheet2.png')   
  end

  def test_get_ole_object    
    extractor = Aspose::Cloud::Cells::Extractor.new('test_cells.xlsx')
    result = extractor.get_ole_object(worksheet_name='Sheet2', object_index=1, image_format='jpg')
    assert_equal true, File.exist?('../Output/test_cells_Sheet2.jpg')   
  end

  def test_get_chart    
    extractor = Aspose::Cloud::Cells::Extractor.new('test_cells.xlsx')
    result = extractor.get_chart(worksheet_name='Sheet2', chart_index=0, image_format='tiff')
    assert_equal true, File.exist?('../Output/test_cells_Sheet2.tiff')   
  end

  def test_get_auto_shape    
    extractor = Aspose::Cloud::Cells::Extractor.new('test_cells.xlsx')
    result = extractor.get_auto_shape(worksheet_name='Sheet2', shape_index=1, image_format='tiff')
    assert_equal true, File.exist?('../Output/test_cells_Sheet2.tiff')   
  end
end