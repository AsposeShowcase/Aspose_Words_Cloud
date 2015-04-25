
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

  def test_find_text    
    text_editor = Aspose::Cloud::Cells::TextEditor.new('test_cells.xlsx')
    response = text_editor.find_text(text='Aspose')    
    assert_instance_of(Array,response)    
  end

  def test_find_text_in_worksheet    
    text_editor = Aspose::Cloud::Cells::TextEditor.new('test_cells.xlsx')
    response = text_editor.find_text_in_worksheet(text='Aspose', worksheet_name='Sheet3')
    assert_instance_of(Array,response)    
  end

  def test_get_text_items    
    text_editor = Aspose::Cloud::Cells::TextEditor.new('test_cells.xlsx')
    response = text_editor.get_text_items()    
    assert_instance_of(Array,response)    
  end
 
  def test_get_text_items_in_worksheet
    text_editor = Aspose::Cloud::Cells::TextEditor.new('test_cells.xlsx')
    response = text_editor.get_text_items_in_worksheet(worksheet_name='Sheet3')    
    assert_instance_of(Array,response)    
  end

  def test_replace_text    
    text_editor = Aspose::Cloud::Cells::TextEditor.new('test_cells.xlsx')
    result = text_editor.replace_text(old_text='Aspose', new_text='Banckle')
    assert_equal true, result    
  end

  def test_replace_text_in_worksheet    
    text_editor = Aspose::Cloud::Cells::TextEditor.new('test_cells.xlsx')
    result = text_editor.replace_text_in_worksheet(worksheet_name='Sheet3', old_text='Banckle', new_text='Aspose')
    assert_equal true, result    
  end  
end