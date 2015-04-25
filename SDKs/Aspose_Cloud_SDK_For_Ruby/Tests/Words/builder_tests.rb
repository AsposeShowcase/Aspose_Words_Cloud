require 'test/unit'
require_relative '../../lib/asposecloud'

class BuilderTests < Test::Unit::TestCase

  def setup
    file = File.read('../setup.json')
    data = JSON.parse(file)

    Aspose::Cloud::Common::AsposeApp.app_key = data['app_key']
    Aspose::Cloud::Common::AsposeApp.app_sid = data['app_sid']
    Aspose::Cloud::Common::AsposeApp.output_location = data['output_location']
    Aspose::Cloud::Common::Product.set_base_product_uri(data['product_uri'])
  end

  # Insert water mark text into the document
  def test_insert_watermark_text
    # Create object of folder class
    folder = Aspose::Cloud::AsposeStorage::Folder.new
    response = folder.upload_file '../Data/test_multi_pages.docx'
    assert_equal true, response

    builder = Aspose::Cloud::Words::Builder.new('test_multi_pages.docx')
    assert_nothing_thrown 'Error' do
      builder.insert_watermark_text(text='Watermark Text', rotation_angle=180)
    end
     assert_equal true, File.exist?('../Output/test_multi_pages.docx')
  end

  # Insert water mark image into the document
  def test_insert_watermark_image
    builder = Aspose::Cloud::Words::Builder.new('test_multi_pages.docx')
    assert_nothing_thrown 'Error' do
      builder.insert_watermark_image(image_file='watermark.png', rotation_angle=90)
    end
     assert_equal true, File.exist?('../Output/test_multi_pages.docx')
  end

  # Remove Watermark from a Word Document
  def test_remove_watermark
    builder = Aspose::Cloud::Words::Builder.new('test_multi_pages.docx')
    assert_nothing_thrown 'Error' do
      builder.remove_watermark()
    end
     assert_equal true, File.exist?('../Output/test_multi_pages.docx')
  end

  # Replace a text with the new value in the document
  def test_replace_text
    builder = Aspose::Cloud::Words::Builder.new('test_multi_pages.docx')
    assert_nothing_thrown 'Error' do
      builder.replace_text(old_text='Testing Page 1', new_text='First Page', is_match_case = false, is_match_whole_word = false)
    end
     assert_equal true, File.exist?('../Output/test_multi_pages.docx')
  end  
end
