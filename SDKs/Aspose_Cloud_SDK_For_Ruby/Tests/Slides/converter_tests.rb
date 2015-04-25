
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

  def test_convert_to_image
    # Create Object of folder class
    folder = Aspose::Cloud::AsposeStorage::Folder.new
    response = folder.upload_file '../Data/test_convert_slide.pptx'
    assert_equal true, response

    # Create object of slides converter class
    slides_converter = Aspose::Cloud::Slides::Converter.new('test_convert_slide.pptx')
    assert_nothing_thrown 'Error' do
      slides_converter.convert_to_image(slide_number=1, image_format='tiff', width=100, height=100)
    end

    assert_equal true, File.exist?('../Output/test_convert_slide.tiff')
  end

  def test_convert
    slides_converter = Aspose::Cloud::Slides::Converter.new('test_convert_slide.pptx')
    assert_nothing_thrown 'Error' do
      slides_converter.convert(slide_number=1, save_format='png')
    end

    assert_equal true, File.exist?('../Output/test_convert_slide.png')
  end

  def test_convert_local_file
    slides_converter = Aspose::Cloud::Slides::Converter.new('test_convert_slide.pptx')
    assert_nothing_thrown 'Error' do
      input_file_path = '../Data/test_convert_slide.pptx';
      slides_converter.convert_local_file(input_file_path, output_filename='output.pdf', save_format='pdf')
    end

    assert_equal true, File.exist?('../Output/output.pdf')
  end

  def test_convert_with_additional_settings
    slides_converter = Aspose::Cloud::Slides::Converter.new('test_convert_slide.pptx')
    assert_nothing_thrown 'Error' do
      slides_converter.convert_with_additional_settings(save_format = 'pdf', text_compression = 'Flat', embed_full_fonts = false, compliance ='Pdf15', jpeg_quality = 50, save_metafiles_as_png = false, pdf_password = '123456', embed_true_type_fonts_for_ascii = false)
    end

    assert_equal true, File.exist?('../Output/test_convert_slide.pdf')
  end
end