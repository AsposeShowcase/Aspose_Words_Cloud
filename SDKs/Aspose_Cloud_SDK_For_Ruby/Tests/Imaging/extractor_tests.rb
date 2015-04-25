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

  # Get TIFF Frame Properties
  def test_get_tiff_frame_properties
    extractor = Aspose::Cloud::Imaging::Extractor.new('Test.tiff')
    response = extractor.get_tiff_frame_properties(frame_id=1)
    assert_instance_of(Hash, response)
  end

  # Extract Frame from a Multi-Frame TIFF Image
  def test_extract_frame
    extractor = Aspose::Cloud::Imaging::Extractor.new('Test.tiff')
    assert_nothing_thrown 'Error' do
      extractor.extract_frame(frame_id=1, output_filename='extract_frame.tiff')
    end
    assert_equal true, File.exist?('../Output/extract_frame_updated.tiff')
  end

  # Resize a TIFF Frame
  def test_resize_frame
    extractor = Aspose::Cloud::Imaging::Extractor.new('Test.tiff')
    assert_nothing_thrown 'Error' do
      extractor.resize_frame(frame_id=1, new_width=100, new_height=100, output_filename='resize_frame.tiff')
    end
    assert_equal true, File.exist?('../Output/resize_frame_updated.tiff')
  end

  # Crop a TIFF Frame
  def test_crop_frame
    extractor = Aspose::Cloud::Imaging::Extractor.new('Test.tiff')
    assert_nothing_thrown 'Error' do
      extractor.crop_frame(frame_id=1, x_position=20, y_position=20, rect_width=200, rect_height=200, output_filename='crop_frame.tiff')
    end
    assert_equal true, File.exist?('../Output/crop_frame_updated.tiff')
  end

  # Rotate a TIFF Frame
  def test_rotate_frame
    extractor = Aspose::Cloud::Imaging::Extractor.new('Test.tiff')
    assert_nothing_thrown 'Error' do
      extractor.rotate_frame(frame_id=1, rotate_flip_method='Rotate180FlipX', output_filename='rotate_frame.tiff')
    end
    assert_equal true, File.exist?('../Output/rotate_frame_updated.tiff')
  end

  # Manipulate a Frame and Save the Modified Frame Along with Unmodified Frames
  def test_manipulate_frame
    extractor = Aspose::Cloud::Imaging::Extractor.new('Test.tiff')
    assert_nothing_thrown 'Error' do
      extractor.manipulate_frame(frame_id=1, rotate_flip_method='Rotate180FlipX', new_width=300, new_height=300, x_position=20, y_position=20, rect_width=200, rect_height=200, output_filename='manipulate_frame.tiff')
    end
    assert_equal true, File.exist?('../Output/manipulate_frame_updated.tiff')
  end
end
