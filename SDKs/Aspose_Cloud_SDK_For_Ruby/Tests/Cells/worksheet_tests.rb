
require 'test/unit'
require_relative '../../lib/asposecloud'

class WorksheetTests < Test::Unit::TestCase

  def setup
    file = File.read('../setup.json')
    data = JSON.parse(file)

    Aspose::Cloud::Common::AsposeApp.app_key = data['app_key']
    Aspose::Cloud::Common::AsposeApp.app_sid = data['app_sid']
    Aspose::Cloud::Common::AsposeApp.output_location = data['output_location']
    Aspose::Cloud::Common::Product.set_base_product_uri(data['product_uri'])
  end

  def test_get_cells_list    
    worksheet = Aspose::Cloud::Cells::Worksheet.new('test_cells.xlsx', 'Sheet1')
    response = worksheet.get_cells_list(offset=0, count=10)
    assert_instance_of(Array, response)
  end

  def test_get_row_list   
    worksheet = Aspose::Cloud::Cells::Worksheet.new('test_cells.xlsx', 'Sheet1')
    response = worksheet.get_row_list(offset=0, count=10)
    assert_instance_of(Array, response)
  end
 
  def test_get_columns_list   
    worksheet = Aspose::Cloud::Cells::Worksheet.new('test_cells.xlsx', 'Sheet1')
    response = worksheet.get_columns_list(offset=0, count=10)
    assert_instance_of(Array, response)
  end

  def test_get_max_columns   
    worksheet = Aspose::Cloud::Cells::Worksheet.new('test_cells.xlsx', 'Sheet3')
    response = worksheet.get_max_columns(offset=0, count=10)
    assert_equal true, response>=0
  end

  def test_get_max_row  
    worksheet = Aspose::Cloud::Cells::Worksheet.new('test_cells.xlsx', 'Sheet3')
    response = worksheet.get_max_row(offset=0, count=10)
    assert_equal true, response>=0
  end

  def test_get_first_cell
    worksheet = Aspose::Cloud::Cells::Worksheet.new('test_cells.xlsx', 'Sheet1')
    response = worksheet.get_first_cell(offset=0, count=10)
    assert_instance_of(Hash, response)
  end

  def test_get_last_cell
    worksheet = Aspose::Cloud::Cells::Worksheet.new('test_cells.xlsx', 'Sheet1')
    response = worksheet.get_last_cell(offset=0, count=10)
    assert_instance_of(Hash, response)
  end

  def test_get_max_data_row
    worksheet = Aspose::Cloud::Cells::Worksheet.new('test_cells.xlsx', 'Sheet1')
    response = worksheet.get_max_data_row(offset=0, count=10)
    assert_equal true, response>=0.to_s
  end

  def test_get_max_data_column
    worksheet = Aspose::Cloud::Cells::Worksheet.new('test_cells.xlsx', 'Sheet3')
    response = worksheet.get_max_data_column(offset=0, count=10)
    assert_equal true, response>=0.to_s
  end

  def test_get_min_row
    worksheet = Aspose::Cloud::Cells::Worksheet.new('Test.xlsx', 'Sheet1')
    response = worksheet.get_min_row(offset=0, count=10)
    assert_equal true, response>=0.to_s
  end

  def test_get_min_data_row
    worksheet = Aspose::Cloud::Cells::Worksheet.new('Test.xlsx', 'Sheet1')
    response = worksheet.get_min_data_row(offset=0, count=100)
    assert_equal true, response>=0.to_s
  end

  def test_get_min_column
    worksheet = Aspose::Cloud::Cells::Worksheet.new('Test.xlsx', 'Sheet1')
    response = worksheet.get_min_column(offset=0, count=10)
    assert_equal true, response>=0.to_s
  end

  def test_get_min_data_column
    worksheet = Aspose::Cloud::Cells::Worksheet.new('test_cells.xlsx', 'Sheet1')
    response = worksheet.get_min_data_column(offset=0, count=10)
    assert_equal true, response>=0.to_s
  end

  def test_get_cells_count  
    worksheet = Aspose::Cloud::Cells::Worksheet.new('test_cells.xlsx', 'Sheet1')
    response = worksheet.get_cells_count(offset=0, count=10)    
    assert_equal true, response>=0
  end

  def test_get_cell
    worksheet = Aspose::Cloud::Cells::Worksheet.new('test_cells.xlsx', 'Sheet1')
    response = worksheet.get_cell(cell_name='A3')    
    assert_instance_of(Hash, response)
  end

  def test_get_cell_style
    worksheet = Aspose::Cloud::Cells::Worksheet.new('test_cells.xlsx', 'Sheet1')
    response = worksheet.get_cell_style(cell_name='A3')    
    assert_instance_of(Hash, response)
  end

  def test_set_cell_style
    worksheet = Aspose::Cloud::Cells::Worksheet.new('test_cells.xlsx', 'Sheet1')
    style='style data here'
    response = worksheet.set_cell_style(cell_name='A3', style)    
    assert_equal true, response
  end  
 
  def test_get_chart_by_index
    worksheet = Aspose::Cloud::Cells::Worksheet.new('test_cells.xlsx', 'Sheet2')
    response = worksheet.get_chart_by_index(chart_index=0)        
    assert_instance_of(Hash, response)
  end

  def test_get_hyperlink_by_index
    worksheet = Aspose::Cloud::Cells::Worksheet.new('Test.xlsx', 'Sheet1')
    response = worksheet.get_hyperlink_by_index(link_index=0)        
    assert_instance_of(Hash, response)
  end

  def test_add_hyperlink
    worksheet = Aspose::Cloud::Cells::Worksheet.new('test_cells.xlsx', 'Sheet1')
    response = worksheet.add_hyperlink(first_row=1, first_column=1, total_rows=1, total_colunms=1, url='www.google.com')        
    assert_equal true, File.exist?('../Output/test_cells.xlsx')
  end

  def test_update_hyperlink
    worksheet = Aspose::Cloud::Cells::Worksheet.new('test_cells.xlsx', 'Sheet1')
    response = worksheet.update_hyperlink(hyperlink_index=0, url='www.banckle.com', screen_tip='Welcome', display_text='Banckle')
    assert_equal true, File.exist?('../Output/test_cells.xlsx')
  end

  def test_delete_hyperlink
    worksheet = Aspose::Cloud::Cells::Worksheet.new('test_cells.xlsx', 'Sheet1')
    response = worksheet.delete_hyperlink(hyperlink_index=0)
    assert_equal true, File.exist?('../Output/test_cells.xlsx')
  end

  def test_get_comment
    worksheet = Aspose::Cloud::Cells::Worksheet.new('test_cells.xlsx', 'Sheet1')
    response = worksheet.get_comment(cell_name='A2')
    assert_instance_of(Hash, response)
  end

  def test_get_oleobject_by_index
    worksheet = Aspose::Cloud::Cells::Worksheet.new('test_cells.xlsx', 'Sheet2')
    response = worksheet.get_oleobject_by_index(ole_index=0)
    assert_instance_of(Hash, response)
  end

  def test_add_oleobject
    worksheet = Aspose::Cloud::Cells::Worksheet.new('test_cells.xlsx', 'Sheet2')
    response = worksheet.add_oleobject(ole_file='ole.docx', image_file='ole.png', upper_left_row=20, upper_left_column=20, height=200, width=200)
    assert_equal true, response
  end

  def test_update_oleobject
    worksheet = Aspose::Cloud::Cells::Worksheet.new('test_cells.xlsx', 'Sheet2')
    response = worksheet.update_oleobject(index=1, str_xml)
    assert_equal true, response
  end

  def test_delete_oleobject
    worksheet = Aspose::Cloud::Cells::Worksheet.new('test_cells.xlsx', 'Sheet2')
    response = worksheet.delete_oleobject(index=1)
    assert_equal true, response
  end

  def test_delete_all_oleobjects
    worksheet = Aspose::Cloud::Cells::Worksheet.new('test_cells.xlsx', 'Sheet2')
    response = worksheet.delete_all_oleobjects(index=1)
    assert_equal true, response
  end

  def test_get_picture_by_index
    worksheet = Aspose::Cloud::Cells::Worksheet.new('test_cells.xlsx', 'Sheet2')
    response = worksheet.get_picture_by_index(image_index=0)
    assert_instance_of(Hash, response)
  end

  def test_get_validation_by_index
    worksheet = Aspose::Cloud::Cells::Worksheet.new('test_cells.xlsx', 'Sheet1')
    response = worksheet.get_validation_by_index(index=0)    
    assert_instance_of(Hash, response)
  end  

  def test_get_validations_count
    worksheet = Aspose::Cloud::Cells::Worksheet.new('test_cells.xlsx', 'Sheet1')
    response = worksheet.get_validations_count()    
    assert_equal true, response>=0
  end

  def test_get_mergedcell_by_index
    worksheet = Aspose::Cloud::Cells::Worksheet.new('test_cells.xlsx', 'Sheet1')
    response = worksheet.get_mergedcell_by_index(index=0)    
    assert_instance_of(Hash, response)
  end  
  
  def test_get_mergedcells_count
    worksheet = Aspose::Cloud::Cells::Worksheet.new('test_cells.xlsx', 'Sheet1')
    response = worksheet.get_mergedcells_count()
    assert_equal true, response>=0
  end  

  def test_get_pictures_count
    worksheet = Aspose::Cloud::Cells::Worksheet.new('test_cells.xlsx', 'Sheet2')
    response = worksheet.get_pictures_count()
    assert_equal true, response>=0
  end

  def test_get_oleobjects_count
    worksheet = Aspose::Cloud::Cells::Worksheet.new('test_cells.xlsx', 'Sheet2')
    response = worksheet.get_oleobjects_count()
    assert_equal true, response>=0
  end  

  def test_get_charts_count
    worksheet = Aspose::Cloud::Cells::Worksheet.new('test_cells.xlsx', 'Sheet2')
    response = worksheet.get_charts_count()    
    assert_equal true, response>=0
  end  

  def test_get_comments_count
    worksheet = Aspose::Cloud::Cells::Worksheet.new('test_cells.xlsx', 'Sheet1')
    response = worksheet.get_comments_count()    
    assert_equal true, response>=0
  end  

  def test_get_hyperlinks_count
    worksheet = Aspose::Cloud::Cells::Worksheet.new('test_cells.xlsx', 'Sheet1')
    response = worksheet.get_hyperlinks_count()    
    assert_equal true, response>=0
  end  

  def test_hide_worksheet
    worksheet = Aspose::Cloud::Cells::Worksheet.new('test_cells.xlsx', 'Sheet1')
    response = worksheet.hide_worksheet()    
    assert_equal true, response
  end  

  def test_unhide_worksheet
    worksheet = Aspose::Cloud::Cells::Worksheet.new('test_cells.xlsx', 'Sheet1')
    response = worksheet.unhide_worksheet()    
    assert_equal true, response
  end

  def test_get_rows_count
    worksheet = Aspose::Cloud::Cells::Worksheet.new('test_cells.xlsx', 'Sheet1')
    response = worksheet.get_rows_count(offset=1, count=1)    
    assert_equal true, response>=0
  end

  def test_get_row
    worksheet = Aspose::Cloud::Cells::Worksheet.new('test_cells.xlsx', 'Sheet1')
    response = worksheet.get_row(index=1)    
    assert_instance_of(Hash, response)
  end  

  def test_delete_row
    worksheet = Aspose::Cloud::Cells::Worksheet.new('test_cells.xlsx', 'Sheet1')
    response = worksheet.delete_row(index=1)    
    assert_equal true, response
  end  

  def test_get_column
    worksheet = Aspose::Cloud::Cells::Worksheet.new('test_cells.xlsx', 'Sheet1')
    response = worksheet.get_column(index=1)    
    assert_instance_of(Hash, response)
  end  

  def test_move_worksheet
    worksheet = Aspose::Cloud::Cells::Worksheet.new('test_cells.xlsx', 'Sheet1')
    response = worksheet.move_worksheet(position=2)    
    assert_equal true, response
  end

  def test_calculate_formula
    worksheet = Aspose::Cloud::Cells::Worksheet.new('test_cells.xlsx', 'Sheet3')
    response = worksheet.calculate_formula(formula='SUM(A3:A4)')    
    assert_instance_of(Hash, response)
  end  

  def test_set_cell_value
    worksheet = Aspose::Cloud::Cells::Worksheet.new('test_cells.xlsx', 'Sheet1')
    response = worksheet.set_cell_value(cell_name='A1', value_type='string', value='123')    
    assert_equal true, response
  end  

  def test_add_picture
    worksheet = Aspose::Cloud::Cells::Worksheet.new('test_cells.xlsx', 'Sheet3')
    response = worksheet.add_picture(picture_path='watermark.png', upper_left_row=12, upper_left_column=12, lower_right_row=20, lower_right_column=20)    
    assert_equal true, response
  end  

  def test_update_picture
    worksheet = Aspose::Cloud::Cells::Worksheet.new('test_cells.xlsx', 'Sheet2')
    picture_index = 0
    picture_data = 'picture data is in xml format'    
    response = worksheet.update_picture(picture_index, picture_data)    
    assert_equal true, response
  end  

  def test_delete_pictures
    worksheet = Aspose::Cloud::Cells::Worksheet.new('test_cells.xlsx', 'Sheet2')
    response = worksheet.delete_pictures()    
    assert_equal true, File.exist?('../Output/test_cells.xlsx')
  end    

  def test_delete_picture
    worksheet = Aspose::Cloud::Cells::Worksheet.new('test_cells.xlsx', 'Sheet2')
    response = worksheet.delete_picture(picture_index=0)    
    assert_equal true, File.exist?('../Output/test_cells.xlsx')
  end    

  def test_copy_worksheet
    worksheet = Aspose::Cloud::Cells::Worksheet.new('test_cells.xlsx', 'Sheet3')
    response = worksheet.copy_worksheet(new_worksheet_name='New Sheet')    
    assert_equal true, File.exist?('../Output/test_cells.xlsx')
  end    

  def test_rename_worksheet
    worksheet = Aspose::Cloud::Cells::Worksheet.new('test_cells.xlsx', 'Sheet1')
    response = worksheet.rename_worksheet(new_worksheet_name='Rename Sheet')    
    assert_equal true, File.exist?('../Output/test_cells.xlsx')
  end    

  def test_set_background
    worksheet = Aspose::Cloud::Cells::Worksheet.new('test_cells.xlsx', 'Sheet1')
    response = worksheet.set_background(background_image='watermark.png')    
    assert_equal true, File.exist?('../Output/test_cells.xlsx')
  end    

  def test_delete_background
    worksheet = Aspose::Cloud::Cells::Worksheet.new('test_cells.xlsx', 'Sheet3')
    response = worksheet.delete_background()    
    assert_equal true, File.exist?('../Output/test_cells.xlsx')
  end    

  def test_freeze_panes
    worksheet = Aspose::Cloud::Cells::Worksheet.new('test_cells.xlsx', 'Sheet1')
    response = worksheet.freeze_panes(row=1, column=1, freezed_rows=1, freezed_columns=1)    
    assert_equal true, File.exist?('../Output/test_cells.xlsx')
  end

  def test_unfreeze_panes
    worksheet = Aspose::Cloud::Cells::Worksheet.new('test_cells.xlsx', 'Sheet1')
    response = worksheet.unfreeze_panes(row=1, column=1, freezed_rows=1, freezed_columns=1)    
    assert_equal true, File.exist?('../Output/test_cells.xlsx')
  end    

  def test_add_empty_row
    worksheet = Aspose::Cloud::Cells::Worksheet.new('test_cells.xlsx', 'Sheet1')
    response = worksheet.add_empty_row(row_id=0)    
    assert_equal true, File.exist?('../Output/test_cells.xlsx')
  end    

  def test_copy_row
    worksheet = Aspose::Cloud::Cells::Worksheet.new('test_cells.xlsx', 'Sheet1')
    response = worksheet.copy_row(source_row_index=2, destination_row_index=1, row_number=1)    
    assert_equal true, File.exist?('../Output/test_cells.xlsx')
  end

  def test_hide_rows
    worksheet = Aspose::Cloud::Cells::Worksheet.new('test_cells.xlsx', 'Sheet3')
    response = worksheet.hide_rows(start_row=2, total_rows=2)    
    assert_equal true, File.exist?('../Output/test_cells.xlsx')
  end

  def test_unhide_rows
    worksheet = Aspose::Cloud::Cells::Worksheet.new('test_cells.xlsx', 'Sheet3')
    response = worksheet.unhide_rows(start_row=2, total_rows=2)    
    assert_equal true, File.exist?('../Output/test_cells.xlsx')
  end

  def test_group_rows
    worksheet = Aspose::Cloud::Cells::Worksheet.new('test_cells.xlsx', 'Sheet1')
    response = worksheet.group_rows(first_index=1, last_index=5)    
    assert_equal true, File.exist?('../Output/test_cells.xlsx')
  end

  def test_ungroup_rows
    worksheet = Aspose::Cloud::Cells::Worksheet.new('test_cells.xlsx', 'Sheet1')
    response = worksheet.ungroup_rows(first_index=1, last_index=5)    
    assert_equal true, File.exist?('../Output/test_cells.xlsx')
  end

  def test_merge_cells
    worksheet = Aspose::Cloud::Cells::Worksheet.new('test_cells.xlsx', 'Sheet1')
    response = worksheet.merge_cells(start_row=1, start_column=1, total_rows=2, total_colunms=2)    
    assert_equal true, File.exist?('../Output/test_cells.xlsx')
  end

  def test_unmerge_cells
    worksheet = Aspose::Cloud::Cells::Worksheet.new('test_cells.xlsx', 'Sheet1')
    response = worksheet.unmerge_cells(start_row=1, start_column=1, total_rows=2, total_colunms=2)    
    assert_equal true, File.exist?('../Output/test_cells.xlsx')
  end

  def test_clear_cells_formatting
    worksheet = Aspose::Cloud::Cells::Worksheet.new('test_cells.xlsx', 'Sheet1')
    response = worksheet.clear_cells_formatting(start_row=1, start_column=1, end_row=1, end_column=1)    
    assert_equal true, File.exist?('../Output/test_cells.xlsx')
  end

  def test_clear_cells_contents
    worksheet = Aspose::Cloud::Cells::Worksheet.new('test_cells.xlsx', 'Sheet1')
    response = worksheet.clear_cells_contents(range='A1:C4')    
    assert_equal true, File.exist?('../Output/test_cells.xlsx')
  end

  def test_set_range_value
    worksheet = Aspose::Cloud::Cells::Worksheet.new('test_cells.xlsx', 'Sheet1')
    response = worksheet.set_range_value(cellarea='A10:B20', value='Sample', type='string')    
    assert_equal true, File.exist?('../Output/test_cells.xlsx')
  end

  def test_update_properties
    worksheet = Aspose::Cloud::Cells::Worksheet.new('test_cells.xlsx', 'Sheet1')
    assert_nothing_thrown 'Error' do
      worksheet.update_properties(worksheet_name='New Sheet Name', gridlines_visible = false, pagebreak_preview = false, ruler_visible = false)
    end
    assert_equal true, File.exist?('../Output/test_cells.tiff')
  end    
end