
require 'test/unit'
require_relative '../../lib/asposecloud'

class ChartEditorTests < Test::Unit::TestCase

  def setup
    file = File.read('../setup.json')
    data = JSON.parse(file)

    Aspose::Cloud::Common::AsposeApp.app_key = data['app_key']
    Aspose::Cloud::Common::AsposeApp.app_sid = data['app_sid']
    Aspose::Cloud::Common::AsposeApp.output_location = data['output_location']
    Aspose::Cloud::Common::Product.set_base_product_uri(data['product_uri'])
  end

  def test_add_chart
    chart_editor = Aspose::Cloud::Cells::ChartEditor.new('test_cells.xlsx')
    assert_nothing_thrown 'Error' do
      chart_editor.add_chart(worksheet_name='Sheet1', chart_type='bar', upper_left_row=12, upper_left_column=12, lower_right_row=20, lower_right_column=20)
    end

    assert_equal true, File.exist?('../Output/test_cells.xlsx')
  end

  def test_delete_charts    
    chart_editor = Aspose::Cloud::Cells::ChartEditor.new('test_cells.xlsx')
    assert_nothing_thrown 'Error' do
      chart_editor.delete_charts(worksheet_name='Sheet1')
    end

    assert_equal true, File.exist?('../Output/test_cells.xlsx')
  end

  def test_delete_chart    
    chart_editor = Aspose::Cloud::Cells::ChartEditor.new('test_cells.xlsx')
    assert_nothing_thrown 'Error' do
      chart_editor.delete_chart(worksheet_name='Sheet1', chart_index=0)
    end

    assert_equal true, File.exist?('../Output/test_cells.xlsx')
  end

  def test_show_chart_legend    
    chart_editor = Aspose::Cloud::Cells::ChartEditor.new('test_cells.xlsx')
    assert_nothing_thrown 'Error' do
      chart_editor.show_chart_legend(worksheet_name='Sheet1', chart_index=1)
    end

    assert_equal true, File.exist?('../Output/test_cells.xlsx')
  end

  def test_hide_chart_legend   
    chart_editor = Aspose::Cloud::Cells::ChartEditor.new('test_cells.xlsx')
    assert_nothing_thrown 'Error' do
      chart_editor.hide_chart_legend(worksheet_name='Sheet1', chart_index=1)
    end

    assert_equal true, File.exist?('../Output/test_cells.xlsx')
  end

  def test_update_chart_legend
    chart_editor = Aspose::Cloud::Cells::ChartEditor.new('test_cells.xlsx')
    str_xml = '<Legend>
                <Position>Right</Position>
                <Font>
                  <Color>
                    <A>0</A>
                    <R>255</R>
                    <G>0</G>
                    <B>0</B>
                  </Color>
                  <DoubleSize>10</DoubleSize>
                  <IsBold>True</IsBold>
                  <IsItalic>False</IsItalic>
                  <IsStrikeout>False</IsStrikeout>
                  <IsSubscript>False</IsSubscript>
                  <IsSuperscript>False</IsSuperscript>
                  <Name>Arial</Name>
                  <Size>16</Size>
                  <Underline>None</Underline>
                </Font>
                <Shadow>True</Shadow>
              </Legend>'
      assert_nothing_thrown 'Error' do
      chart_editor.update_chart_legend(worksheet_name='Sheet1', chart_index=1, str_xml)
    end

    assert_equal true, File.exist?('../Output/test_cells.xlsx')
  end

  def test_read_chart_legend    
    chart_editor = Aspose::Cloud::Cells::ChartEditor.new('test_cells.xlsx')
    response = chart_editor.read_chart_legend(worksheet_name='Sheet1', chart_index=1)
    assert_instance_of(Hash, response)
  end

  def test_get_chart_area
    chart_editor = Aspose::Cloud::Cells::ChartEditor.new('test_cells.xlsx')
    response = chart_editor.get_chart_area(worksheet_name='Sheet1', chart_index=1)
    assert_instance_of(Hash, response)
  end

  def test_get_fill_format
    chart_editor = Aspose::Cloud::Cells::ChartEditor.new('test_cells.xlsx')
    response = chart_editor.get_fill_format(worksheet_name='Sheet1', chart_index=1)
    assert_instance_of(Hash, response)
  end

  def test_get_border
    chart_editor = Aspose::Cloud::Cells::ChartEditor.new('test_cells.xlsx')
    response = chart_editor.get_border(worksheet_name='Sheet1', chart_index=1)
    assert_instance_of(Hash, response)
  end

  def test_set_chart_title
    chart_editor = Aspose::Cloud::Cells::ChartEditor.new('test_cells.xlsx')
    str_xml = '<Title>
                <Text>Sales Chart</Text>
               </Title>'
    response = chart_editor.set_chart_title(worksheet_name='Sheet1', chart_index=1, str_xml)
    assert_equal true, File.exist?('../Output/test_cells.xlsx')
  end

  def test_update_chart_title
    chart_editor = Aspose::Cloud::Cells::ChartEditor.new('test_cells.xlsx')
    str_xml = '<Title>
                <Text>Sales Chart</Text>
               </Title>'
    response = chart_editor.update_chart_title(worksheet_name='Sheet1', chart_index=1, str_xml)
    assert_equal true, File.exist?('../Output/test_cells.xlsx')
  end  

  def test_delete_chart_title
    chart_editor = Aspose::Cloud::Cells::ChartEditor.new('test_cells.xlsx')    
    response = chart_editor.delete_chart_title(worksheet_name='Sheet1', chart_index=1)
    assert_equal true, File.exist?('../Output/test_cells.xlsx')
  end    
end