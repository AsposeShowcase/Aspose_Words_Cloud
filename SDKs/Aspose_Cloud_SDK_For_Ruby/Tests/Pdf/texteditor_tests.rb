
require 'test/unit'
require_relative '../../lib/asposecloud'

class TextEditorTests < Test::Unit::TestCase

  def setup
    file = File.read('../setup.json')
    data = JSON.parse(file)

    Aspose::Cloud::Common::AsposeApp.app_key = data['app_key']
    Aspose::Cloud::Common::AsposeApp.app_sid = data['app_sid']
    Aspose::Cloud::Common::AsposeApp.output_location = data['output_location']
    Aspose::Cloud::Common::Product.set_base_product_uri(data['product_uri'])
  end

  # Get text from the whole PDF file or a specific page
  def test_get_text
    texteditor = Aspose::Cloud::Pdf::TextEditor.new('Test.pdf')
    response = texteditor.get_text(page_number=1)
    assert_instance_of(String, response)
  end

  # Get text items from the whole PDF file or a specific page
  def test_get_text_items
    texteditor = Aspose::Cloud::Pdf::TextEditor.new('Test.pdf')
    response = texteditor.get_text_items(page_number=1, fragment_number=1)
    assert_instance_of(Array, response)
  end  

  # Get count of the fragments from a particular page
  def test_get_fragment_count
    texteditor = Aspose::Cloud::Pdf::TextEditor.new('Test.pdf')
    response = texteditor.get_fragment_count(page_number=1)
    assert_equal true, response>=0
  end

  # Get TextFormat of a particular Fragment
  def test_get_text_format
    texteditor = Aspose::Cloud::Pdf::TextEditor.new('Test.pdf')
    response = texteditor.get_text_format(page_number=1, fragment_number=1)
    assert_instance_of(Hash, response)
  end  

  # Get Text Format of a Particular Segment
  def test_get_segment_text_format
    texteditor = Aspose::Cloud::Pdf::TextEditor.new('Test.pdf')
    response = texteditor.get_segment_text_format(page_number=1, fragment_number=1, segment_number=1)
    assert_instance_of(Hash, response)
  end  
  
  # Replace all instances of old text with new text in a PDF file or a particular page
  def test_replace_text
    texteditor = Aspose::Cloud::Pdf::TextEditor.new('Test.pdf')
    response = texteditor.replace_text(old_text='This', new_text='It', is_regular_expression = false, page_number = 0)
    assert_equal true, response
  end

  # Get count of the segments in a fragment
  def test_get_segment_count
    texteditor = Aspose::Cloud::Pdf::TextEditor.new('Test.pdf')
    response = texteditor.get_segment_count(page_number=1, fragment_number=1)
    assert_equal true, response>=0
  end
end