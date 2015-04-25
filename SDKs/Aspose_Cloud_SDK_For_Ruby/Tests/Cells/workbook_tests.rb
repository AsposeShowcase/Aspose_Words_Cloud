
require 'test/unit'
require_relative '../../lib/asposecloud'

class WorkbookTests < Test::Unit::TestCase

  def setup
    file = File.read('../setup.json')
    data = JSON.parse(file)

    Aspose::Cloud::Common::AsposeApp.app_key = data['app_key']
    Aspose::Cloud::Common::AsposeApp.app_sid = data['app_sid']
    Aspose::Cloud::Common::AsposeApp.output_location = data['output_location']
    Aspose::Cloud::Common::Product.set_base_product_uri(data['product_uri'])
  end

  def test_get_properties    
    workbook = Aspose::Cloud::Cells::Workbook.new('test_cells.xlsx')
    response = workbook.get_properties()    
    assert_instance_of(Array,response)
  end

  def test_get_property    
    workbook = Aspose::Cloud::Cells::Workbook.new('test_cells.xlsx')
    response = workbook.get_property(property_name='Author')    
    assert_instance_of(Hash,response)
  end

  def test_set_property    
    workbook = Aspose::Cloud::Cells::Workbook.new('test_cells.xlsx')
    response = workbook.set_property(property_name='Test', property_value='123')    
    assert_instance_of(Hash,response)
  end

  def test_remove_all_properties   
    workbook = Aspose::Cloud::Cells::Workbook.new('test_cells.xlsx')
    response = workbook.remove_all_properties()    
    assert_equal true, response
  end

  def test_remove_property    
    workbook = Aspose::Cloud::Cells::Workbook.new('test_cells.xlsx')
    response = workbook.remove_property(property_name='Test')    
    assert_equal true, response
  end 

  def test_create_empty_workbook    
    workbook = Aspose::Cloud::Cells::Workbook.new('test_cells_empty.xlsx')
    response = workbook.create_empty_workbook()    
    assert_instance_of(Hash,response)
  end 

  def test_create_workbook_from_template    
    workbook = Aspose::Cloud::Cells::Workbook.new('create_workbook_from_template.xlsx')
    response = workbook.create_workbook_from_template(template_file_name='test_cells.xlsx')    
    assert_instance_of(Hash, response)
  end 

  def test_get_name_count    
    workbook = Aspose::Cloud::Cells::Workbook.new('test_cells.xlsx')
    response = workbook.get_name_count()
    assert_equal true, response>0
  end

  def test_get_worksheets_count    
    workbook = Aspose::Cloud::Cells::Workbook.new('test_cells.xlsx')
    response = workbook.get_worksheets_count()
    assert_equal true, response>0
  end
    
  def test_get_default_style    
    workbook = Aspose::Cloud::Cells::Workbook.new('test_cells.xlsx')
    response = workbook.get_default_style()    
    assert_instance_of(Hash,response)
  end

  def test_encrypt_workbook   
    workbook = Aspose::Cloud::Cells::Workbook.new('test_cells.xlsx')
    response = workbook.encrypt_workbook(encryption_type='XOR', password='123456', key_length=128)    
    assert_equal true, response
  end

  def test_decrypt_workbook   
    workbook = Aspose::Cloud::Cells::Workbook.new('test_cells.xlsx')
    response = workbook.decrypt_workbook(password='123456')    
    assert_equal true, response
  end

  def test_protect_workbook    
    workbook = Aspose::Cloud::Cells::Workbook.new('test_cells.xlsx')
    response = workbook.protect_workbook(protection_type='all', password='123456')    
    assert_equal true, response
  end

  def test_unprotect_workbook    
    workbook = Aspose::Cloud::Cells::Workbook.new('test_cells.xlsx')
    response = workbook.unprotect_workbook(password='123456')    
    assert_equal true, response
  end

  def test_set_modify_password   
    workbook = Aspose::Cloud::Cells::Workbook.new('test_cells.xlsx')
    response = workbook.set_modify_password(password='123456')    
    assert_equal true, response
  end

  def test_clear_modify_password    
    workbook = Aspose::Cloud::Cells::Workbook.new('test_cells.xlsx')
    response = workbook.clear_modify_password(password='123456')    
    assert_equal true, response
  end

  def test_decrypt_password    
    workbook = Aspose::Cloud::Cells::Workbook.new('test_cells.xlsx')
    response = workbook.decrypt_password(password='123456')    
    assert_equal true, response
  end

  def test_add_worksheet    
    workbook = Aspose::Cloud::Cells::Workbook.new('test_cells.xlsx')
    response = workbook.add_worksheet(worksheet_name='My Worksheet')    
    assert_equal true, response
  end

  def test_remove_worksheet    
    workbook = Aspose::Cloud::Cells::Workbook.new('test_cells.xlsx')
    response = workbook.remove_worksheet(worksheet_name='My Worksheet')    
    assert_equal true, response
  end  

  def test_merge_workbook    
    workbook = Aspose::Cloud::Cells::Workbook.new('test_cells.xlsx')
    response = workbook.merge_workbook(merge_filename='Test.xlsx')    
    assert_equal true, response
  end

  def test_split_workbook    
    workbook = Aspose::Cloud::Cells::Workbook.new('test_cells.xlsx')
    assert_nothing_thrown 'Error' do
      response = workbook.split_workbook(save_format='tiff')
    end
    assert_equal true, File.exist?('../Output/test_cells_1.tiff')
    assert_equal true, File.exist?('../Output/test_cells_2.tiff')
  end
end