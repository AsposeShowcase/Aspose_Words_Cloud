
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

  # Gets total number of images in a presentation
  def test_get_image_count
    # Create Object of folder class
    folder = Aspose::Cloud::AsposeStorage::Folder.new
    response = folder.upload_file '../Data/test_slides.pptx'
    assert_equal true, response

    # Create object of slides extractor class
    extractor = Aspose::Cloud::Slides::Extractor.new('test_slides.pptx')
    response = extractor.get_image_count()
    assert_equal true, response>=0
  end

  # Gets number of images in the specified slide
  def test_get_slide_image_count
    extractor = Aspose::Cloud::Slides::Extractor.new('test_slides.pptx')
    response = extractor.get_slide_image_count(slide_number=3)
    assert_equal true, response>=0
  end

  # Extract Shapes from a Slide
  def test_get_shapes
    extractor = Aspose::Cloud::Slides::Extractor.new('test_slides.pptx')
    response = extractor.get_shapes(slide_number=2)
    assert_instance_of(Array, response)
  end

  # Get a Particular Shape from the Slide
  def test_get_shape
    extractor = Aspose::Cloud::Slides::Extractor.new('test_slides.pptx')
    response = extractor.get_shape(slide_number=2, shape_index=1)
    assert_instance_of(Hash, response)
  end

  # Get color scheme from the specified slide
  def test_get_color_scheme
    extractor = Aspose::Cloud::Slides::Extractor.new('test_slides.pptx')
    response = extractor.get_color_scheme(slide_number=2)
    assert_instance_of(Hash, response)
  end

  # Get font scheme from the specified slide
  def test_get_font_scheme
    extractor = Aspose::Cloud::Slides::Extractor.new('test_slides.pptx')
    response = extractor.get_font_scheme(slide_number=2)
    assert_instance_of(Hash, response)
  end

  # Get format scheme from the specified slide
  def test_get_format_scheme
    extractor = Aspose::Cloud::Slides::Extractor.new('test_slides.pptx')
    response = extractor.get_format_scheme(slide_number=2)
    assert_instance_of(Hash, response)
  end

  # Gets placeholder count from a particular slide
  def test_get_placeholder_count
    extractor = Aspose::Cloud::Slides::Extractor.new('test_slides.pptx')
    response = extractor.get_placeholder_count(slide_number=2)
    assert_equal true, response>=0
  end

  # Gets placeholder from a particular slide
  def test_get_placeholder
    extractor = Aspose::Cloud::Slides::Extractor.new('test_slides.pptx')
    response = extractor.get_placeholder(slide_number=2, placeholder_index=0)
    assert_instance_of(Hash, response)
  end

  # Get Comments of a PowerPoint Slide
  def test_get_comments
    extractor = Aspose::Cloud::Slides::Extractor.new('test_slides.pptx')
    response = extractor.get_comments(slide_number=4)
    assert_instance_of(Hash, response)
  end  
end