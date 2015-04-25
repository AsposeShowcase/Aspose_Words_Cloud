
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

  def test_convert_storage_file    
    folder = Aspose::Cloud::AsposeStorage::Folder.new
    response = folder.upload_file '../Data/test_convert_cell.xlsx'
    assert_equal true, response
    
    converter = Aspose::Cloud::Cells::Converter.new('test_convert_cell.xlsx')
    assert_nothing_thrown 'Error' do
      converter.convert('../Output/converted_file.tiff','tiff')
    end

    assert_equal true, File.exist?('../Output/converted_file.tiff')
  end

  def test_convert_to_image    
    converter = Aspose::Cloud::Cells::Converter.new('test_cells.xlsx')
    assert_nothing_thrown 'Error' do
      converter.convert_to_image(output_filename='convert_to_image', worksheet_name='Sheet2', save_format='tiff')
    end

    assert_equal true, File.exist?('../Output/convert_to_image.tiff')
  end

  def test_picture_to_image    
    converter = Aspose::Cloud::Cells::Converter.new('test_cells.xlsx')
    assert_nothing_thrown 'Error' do
      converter.picture_to_image(picture_index=0, worksheet_name='Sheet2', save_format='tiff')
    end

    assert_equal true, File.exist?('../Output/test_cells.tiff')
  end

  def test_oleobject_to_image    
    converter = Aspose::Cloud::Cells::Converter.new('test_cells.xlsx')
    assert_nothing_thrown 'Error' do
      converter.oleobject_to_image(object_index=0, worksheet_name='Sheet2', save_format='png')
    end

    assert_equal true, File.exist?('../Output/test_cells.png')
  end

  def test_chart_to_image    
    converter = Aspose::Cloud::Cells::Converter.new('test_cells.xlsx')
    assert_nothing_thrown 'Error' do
      converter.chart_to_image(chart_index=0, worksheet_name='Sheet2', save_format='gif')
    end

    assert_equal true, File.exist?('../Output/test_cells.gif')
  end

  def test_autoshape_to_image    
    converter = Aspose::Cloud::Cells::Converter.new('test_cells.xlsx')
    assert_nothing_thrown 'Error' do
      converter.autoshape_to_image(shape_index=0, worksheet_name='Sheet2', save_format='tiff')
    end

    assert_equal true, File.exist?('../Output/test_cells.tiff')
  end

  def test_convert_local_file
    converter = Aspose::Cloud::Cells::Converter.new('test_cells.xlsx')
    assert_nothing_thrown 'Error' do
      converter.convert_local_file(input_file='../Data/test_convert_cell.xlsx', output_filename='convert-local.tiff', save_format='tiff')
    end

    assert_equal true, File.exist?('../Output/convert-local.tiff')
  end
end