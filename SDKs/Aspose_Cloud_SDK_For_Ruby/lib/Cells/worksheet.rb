# Copyright (c) Aspose 2002-2014. All Rights Reserved.
#
# LICENSE: This program is free software; you can redistribute it and/or
# modify it under the terms of the GNU General Public License
# as published by the Free Software Foundation; either version 3
# of the License, or (at your option) any later version.
# This program is distributed in the hope that it will be useful,
# but WITHOUT ANY WARRANTY; without even the implied warranty of
# MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
# GNU General Public License for more details.
# You should have received a copy of the GNU General Public License
# along with this program. If not, see <http://opensource.org/licenses/gpl-3.0.html>;.
#
# @package Aspose_Cloud_SDK_For_Ruby
# @author  Assad Mahmood Qazi <assad.mahmood@aspose.com>
# @link    https://github.com/asposeforcloud/Aspose_Cloud_SDK_For_Ruby/tree/revamp

module Aspose
  module Cloud
    module Cells
      class Worksheet
        def initialize(filename, worksheet_name)
          @filename = filename
          @worksheet_name = worksheet_name
          raise 'filename not specified.' if filename.empty?
          raise 'worksheet_name not specified.' if worksheet_name.empty?
          @base_uri = "#{Aspose::Cloud::Common::Product.product_uri}/cells/#{@filename}/worksheets/#{@worksheet_name}"
        end

        def get_cells_list(offset, count, folder_name = '', storage_type = 'Aspose', storage_name = '')
          raise 'offset not specified.' if offset.nil?
          raise 'count not specified.' if count.nil?

          str_uri = "#{@base_uri}/cells"
          qry = { :offset => offset, :count => count}
          str_uri = Aspose::Cloud::Common::Utils.build_uri(str_uri,qry)
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)
          JSON.parse(RestClient.get(signed_str_uri, {:accept=>'application/json'}))['Cells']['CellList']
        end

        def get_row_list(offset, count, folder_name = '', storage_type = 'Aspose', storage_name = '')
          raise 'offset not specified.' if offset.nil?
          raise 'count not specified.' if count.nil?

          str_uri = "#{@base_uri}/cells/rows"
          qry = { :offset => offset, :count => count}
          str_uri = Aspose::Cloud::Common::Utils.build_uri(str_uri,qry)
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)
          JSON.parse(RestClient.get(signed_str_uri, {:accept=>'application/json'}))['Rows']['RowsList']
        end

        def get_columns_list(offset, count, folder_name = '', storage_type = 'Aspose', storage_name = '')
          raise 'offset not specified.' if offset.nil?
          raise 'count not specified.' if count.nil?

          str_uri = "#{@base_uri}/cells/columns"
          qry = { :offset => offset, :count => count}
          str_uri = Aspose::Cloud::Common::Utils.build_uri(str_uri,qry)
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)
          JSON.parse(RestClient.get(signed_str_uri, {:accept=>'application/json'}))['Columns']['ColumnsList']
        end

        def get_max_columns(offset, count, folder_name = '', storage_type = 'Aspose', storage_name = '')
          raise 'offset not specified.' if offset.nil?
          raise 'count not specified.' if count.nil?

          str_uri = "#{@base_uri}/cells"
          qry = { :offset => offset, :count => count}
          str_uri = Aspose::Cloud::Common::Utils.build_uri(str_uri,qry)
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)
          JSON.parse(RestClient.get(signed_str_uri, {:accept=>'application/json'}))['Cells']['MaxColumn']
        end

        def get_max_row(offset, count, folder_name = '', storage_type = 'Aspose', storage_name = '')
          raise 'offset not specified.' if offset.nil?
          raise 'count not specified.' if count.nil?

          str_uri = "#{@base_uri}/cells"
          qry = { :offset => offset, :count => count}
          str_uri = Aspose::Cloud::Common::Utils.build_uri(str_uri,qry)
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)
          JSON.parse(RestClient.get(signed_str_uri, {:accept=>'application/json'}))['Cells']['MaxRow']
        end

        def get_first_cell(offset, count, folder_name = '', storage_type = 'Aspose', storage_name = '')
          raise 'offset not specified.' if offset.nil?
          raise 'count not specified.' if count.nil?

          str_uri = "#{@base_uri}/cells/firstcell"
          qry = { :offset => offset, :count => count}
          str_uri = Aspose::Cloud::Common::Utils.build_uri(str_uri,qry)
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)
          JSON.parse(RestClient.get(signed_str_uri, {:accept=>'application/json'}))['Cell']
        end

        def get_last_cell(offset, count, folder_name = '', storage_type = 'Aspose', storage_name = '')
          raise 'offset not specified.' if offset.nil?
          raise 'count not specified.' if count.nil?

          str_uri = "#{@base_uri}/cells/endcell"
          qry = { :offset => offset, :count => count}
          str_uri = Aspose::Cloud::Common::Utils.build_uri(str_uri,qry)
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)
          JSON.parse(RestClient.get(signed_str_uri, {:accept=>'application/json'}))['Cell']
        end

        def get_max_data_row(offset, count, folder_name = '', storage_type = 'Aspose', storage_name = '')
          raise 'offset not specified.' if offset.nil?
          raise 'count not specified.' if count.nil?

          str_uri = "#{@base_uri}/cells/maxdatarow"
          qry = { :offset => offset, :count => count}
          str_uri = Aspose::Cloud::Common::Utils.build_uri(str_uri,qry)
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)
          RestClient.get(signed_str_uri, {:accept=>'application/json'})
        end

        def get_max_data_column(offset, count, folder_name = '', storage_type = 'Aspose', storage_name = '')
          raise 'offset not specified.' if offset.nil?
          raise 'count not specified.' if count.nil?

          str_uri = "#{@base_uri}/cells/maxdatacolumn"
          qry = { :offset => offset, :count => count}
          str_uri = Aspose::Cloud::Common::Utils.build_uri(str_uri,qry)
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)          
          RestClient.get(signed_str_uri, {:accept=>'application/json'})
        end

        def get_min_row(offset, count, folder_name = '', storage_type = 'Aspose', storage_name = '')
          raise 'offset not specified.' if offset.nil?
          raise 'count not specified.' if count.nil?

          str_uri = "#{@base_uri}/cells/minrow"
          qry = { :offset => offset, :count => count}
          str_uri = Aspose::Cloud::Common::Utils.build_uri(str_uri,qry)
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)          
          RestClient.get(signed_str_uri, {:accept=>'application/json'})
        end

        def get_min_data_row(offset, count, folder_name = '', storage_type = 'Aspose', storage_name = '')
          raise 'offset not specified.' if offset.nil?
          raise 'count not specified.' if count.nil?

          str_uri = "#{@base_uri}/cells/mindatarow"
          qry = { :offset => offset, :count => count}
          str_uri = Aspose::Cloud::Common::Utils.build_uri(str_uri,qry)
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)          
          RestClient.get(signed_str_uri, {:accept=>'application/json'})
        end

        def get_min_column(offset, count, folder_name = '', storage_type = 'Aspose', storage_name = '')
          raise 'offset not specified.' if offset.nil?
          raise 'count not specified.' if count.nil?

          str_uri = "#{@base_uri}/cells/mincolumn"
          qry = { :offset => offset, :count => count}
          str_uri = Aspose::Cloud::Common::Utils.build_uri(str_uri,qry)
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)          
          RestClient.get(signed_str_uri, {:accept=>'application/json'})
        end

        def get_min_data_column(offset, count, folder_name = '', storage_type = 'Aspose', storage_name = '')
          raise 'offset not specified.' if offset.nil?
          raise 'count not specified.' if count.nil?

          str_uri = "#{@base_uri}/cells/mindatacolumn"
          qry = { :offset => offset, :count => count}
          str_uri = Aspose::Cloud::Common::Utils.build_uri(str_uri,qry)
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)          
          RestClient.get(signed_str_uri, {:accept=>'application/json'})
        end

        def get_cells_count(offset, count, folder_name = '', storage_type = 'Aspose', storage_name = '')
          raise 'offset not specified.' if offset.nil?
          raise 'count not specified.' if count.nil?

          str_uri = "#{@base_uri}/cells"
          qry = { :offset => offset, :count => count}
          str_uri = Aspose::Cloud::Common::Utils.build_uri(str_uri,qry)
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)
          JSON.parse(RestClient.get(signed_str_uri, {:accept=>'application/json'}))['Cells']['CellCount']
        end

        def get_auto_shapes_count(folder_name = '', storage_type = 'Aspose', storage_name = '')
          str_uri = "#{@base_uri}/autoshapes"
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)
          JSON.parse(RestClient.get(signed_str_uri, {:accept=>'application/json'}))['AutoShapes']['AuotShapeList'].length
        end

        def get_auto_shapes_by_index(shape_index, folder_name = '', storage_type = 'Aspose', storage_name = '')
          raise 'shape_index not specified.' if shape_index.nil?

          str_uri = "#{@base_uri}/autoshapes/#{shape_index}"
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)
          JSON.parse(RestClient.get(signed_str_uri, {:accept=>'application/json'}))['AutoShapes']
        end

        def get_cell(cell_name, folder_name = '', storage_type = 'Aspose', storage_name = '')
          raise 'cell_name not specified.' if cell_name.empty?

          str_uri = "#{@base_uri}/cells/#{cell_name}"
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)
          JSON.parse(RestClient.get(signed_str_uri, {:accept=>'application/json'}))['Cell']
        end

        def get_cell_style(cell_name, folder_name = '', storage_type = 'Aspose', storage_name = '')
          raise 'cell_name not specified.' if cell_name.empty?

          str_uri = "#{@base_uri}/cells/#{cell_name}/style"
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)
          JSON.parse(RestClient.get(signed_str_uri, {:accept=>'application/json'}))['Style']
        end

        def set_cell_style(cell_name, style, folder_name = '', storage_type = 'Aspose', storage_name = '')
          raise 'cell_name not specified.' if cell_name.empty?
          raise 'style not specified.' if style.empty?

          str_uri = "#{@base_uri}/cells/#{cell_name}/style"
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)
          JSON.parse(RestClient.post(signed_str_uri, style.to_json, { :content_type => :json, :accept=>'application/json'}))['Code'] == 200 ? true : false
        end

        def get_chart_by_index(chart_index, folder_name = '', storage_type = 'Aspose', storage_name = '')
          raise 'chart_index not specified.' if chart_index.nil?

          str_uri = "#{@base_uri}/charts/#{chart_index}"
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)
          JSON.parse(RestClient.get(signed_str_uri, {:accept=>'application/json'}))['Chart']
        end

        def get_hyperlink_by_index(link_index, folder_name = '', storage_type = 'Aspose', storage_name = '')
          raise 'link_index not specified.' if link_index.nil?

          str_uri = "#{@base_uri}/hyperlinks/#{link_index}"
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)          
          JSON.parse(RestClient.get(signed_str_uri, {:accept=>'application/json'}))['Hyperlink']
        end

        def add_hyperlink(first_row, first_column, total_rows, total_colunms, url, folder_name = '', storage_type = 'Aspose', storage_name = '')
          raise 'first_row not specified.' if first_row.nil?
          raise 'first_column not specified.' if first_column.nil?
          raise 'total_rows not specified.' if total_rows.nil?
          raise 'total_colunms not specified.' if total_colunms.nil?
          raise 'url not specified.' if url.empty?

          str_uri = "#{@base_uri}/hyperlinks?firstRow=#{first_row}&firstColumn=#{first_column}&totalRows=#{total_rows}&totalColumns=#{total_colunms}&address=#{url}"
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)
          
          response_stream = RestClient.put(signed_str_uri, '', {:accept=>'application/json'})
          valid_output = Aspose::Cloud::Common::Utils.validate_output(response_stream)
          valid_output.empty? ? Aspose::Cloud::Common::Utils.download_file(@filename,@filename,folder_name,storage_name,storage_type) : valid_output
        end

        def update_hyperlink(hyperlink_index, url, screen_tip, display_text, folder_name = '', storage_type = 'Aspose', storage_name = '')
          raise 'hyperlink_index not specified.' if hyperlink_index.nil?
          raise 'url not specified.' if url.empty?
          raise 'screen_tip not specified.' if screen_tip.empty?
          raise 'display_text not specified.' if display_text.empty?          

          str_uri = "#{@base_uri}/hyperlinks/#{hyperlink_index}"
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)
          
          json_data = JSON.generate('address' => url, 'ScreenTip' => screen_tip, 'TextToDisplay' => display_text)

          response_stream = RestClient.post(signed_str_uri, json_data, {:accept=>'application/json', :content_type=>'application/json'})
          valid_output = Aspose::Cloud::Common::Utils.validate_output(response_stream)
          valid_output.empty? ? Aspose::Cloud::Common::Utils.download_file(@filename,@filename,folder_name,storage_name,storage_type) : valid_output
        end

        def delete_hyperlink(hyperlink_index, folder_name='', storage_type = 'Aspose', storage_name = '')          
          raise 'hyperlink_index not specified.' if hyperlink_index.nil?

          str_uri = "#{@base_uri}/hyperlinks/#{hyperlink_index}"
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)
          response_stream = RestClient.delete(signed_str_uri, {:accept=>'application/json'})
          valid_output = Aspose::Cloud::Common::Utils.validate_output(response_stream)
          valid_output.empty? ? Aspose::Cloud::Common::Utils.download_file(@filename,@filename,folder_name,storage_name,storage_type) : valid_output
        end

        def get_comment(cell_name, folder_name = '', storage_type = 'Aspose', storage_name = '')
          raise 'cell_name not specified.' if cell_name.empty?

          str_uri = "#{@base_uri}/comments/#{cell_name}"
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)
          JSON.parse(RestClient.get(signed_str_uri, {:accept=>'application/json'}))['Comment']
        end

        def get_oleobject_by_index(ole_index, folder_name = '', storage_type = 'Aspose', storage_name = '')
          raise 'ole_index not specified.' if ole_index.nil?

          str_uri = "#{@base_uri}/oleobjects/#{ole_index}"
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)
          JSON.parse(RestClient.get(signed_str_uri, {:accept=>'application/json'}))['OleObject']
        end

        def add_oleobject(ole_file, image_file, upper_left_row, upper_left_column, height, width, folder_name = '', storage_type = 'Aspose', storage_name = '')
          raise 'ole_file not specified.' if ole_file.empty?
          raise 'image_file not specified.' if image_file.empty?
          raise 'upper_left_row not specified.' if upper_left_row.nil?
          raise 'upper_left_column not specified.' if upper_left_column.nil?
          raise 'height not specified.' if height.nil?
          raise 'width not specified.' if width.nil?

          str_uri = "#{@base_uri}/oleobjects"
          qry = Hash.new
          qry[:oleFile] = ole_file
          qry[:imageFile] = image_file
          qry[:upperLeftRow] = upper_left_row
          qry[:upperLeftColumn] = upper_left_column
          qry[:height] = height
          qry[:width] = width
          str_uri = Aspose::Cloud::Common::Utils.build_uri(str_uri,qry)
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)
          JSON.parse(RestClient.put(signed_str_uri, '', {:accept=>'application/json'}))['Code'] == 200 ? true : false
        end

        def update_oleobject(index, str_xml, folder_name = '', storage_type = 'Aspose', storage_name = '')
          raise 'index not specified.' if index.nil?
          raise 'str_xml not specified.' if str_xml.empty?          

          str_uri = "#{@base_uri}/oleobjects/#{index}"          
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)
          JSON.parse(RestClient.post(signed_str_uri, str_xml, {:accept=>'application/json'}))['Code'] == 200 ? true : false
        end

        def delete_oleobject(index, folder_name = '', storage_type = 'Aspose', storage_name = '')
          raise 'index not specified.' if index.nil?

          str_uri = "#{@base_uri}/oleobjects/#{index}"
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)
          JSON.parse(RestClient.delete(signed_str_uri, {:accept=>'application/json'}))['Code'] == 200 ? true : false
        end

        def delete_all_oleobjects(folder_name = '', storage_type = 'Aspose', storage_name = '')
          str_uri = "#{@base_uri}/oleobjects"
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)
          JSON.parse(RestClient.delete(signed_str_uri, {:accept=>'application/json'}))['Code'] == 200 ? true : false
        end

        def get_picture_by_index(image_index, folder_name = '', storage_type = 'Aspose', storage_name = '')
          raise 'image_index not specified.' if image_index.nil?

          str_uri = "#{@base_uri}/pictures/#{image_index}"
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)
          JSON.parse(RestClient.get(signed_str_uri, {:accept=>'application/json'}))['Picture']
        end

        def get_validation_by_index(index, folder_name = '', storage_type = 'Aspose', storage_name = '')
          raise 'index not specified.' if index.nil?

          str_uri = "#{@base_uri}/validations/#{index}"
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)          
          JSON.parse(RestClient.get(signed_str_uri, {:accept=>'application/json'}))['Validation']
        end

        def get_mergedcell_by_index(index, folder_name = '', storage_type = 'Aspose', storage_name = '')
          raise 'index not specified.' if index.nil?

          str_uri = "#{@base_uri}/mergedCells/#{index}"
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)
          JSON.parse(RestClient.get(signed_str_uri, {:accept=>'application/json'}))['MergedCell']
        end

        def get_mergedcells_count(folder_name = '', storage_type = 'Aspose', storage_name = '')
          str_uri = "#{@base_uri}/mergedCells"
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)
          JSON.parse(RestClient.get(signed_str_uri, {:accept=>'application/json'}))['MergedCells']['Count']
        end

        def get_validations_count(folder_name = '', storage_type = 'Aspose', storage_name = '')
          str_uri = "#{@base_uri}/validations"
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)
          JSON.parse(RestClient.get(signed_str_uri, {:accept=>'application/json'}))['Validations']['Count']
        end

        def get_pictures_count(folder_name = '', storage_type = 'Aspose', storage_name = '')
          str_uri = "#{@base_uri}/pictures"
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)
          JSON.parse(RestClient.get(signed_str_uri, {:accept=>'application/json'}))['Pictures']['PictureList'].length
        end

        def get_oleobjects_count(folder_name = '', storage_type = 'Aspose', storage_name = '')
          str_uri = "#{@base_uri}/oleobjects"
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)
          JSON.parse(RestClient.get(signed_str_uri, {:accept=>'application/json'}))['OleObjects']['OleOjectList'].length
        end

        def get_charts_count(folder_name = '', storage_type = 'Aspose', storage_name = '')
          str_uri = "#{@base_uri}/charts"
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)
          JSON.parse(RestClient.get(signed_str_uri, {:accept=>'application/json'}))['Charts']['ChartList'].length
        end

        def get_comments_count(folder_name = '', storage_type = 'Aspose', storage_name = '')
          str_uri = "#{@base_uri}/comments"
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)
          JSON.parse(RestClient.get(signed_str_uri, {:accept=>'application/json'}))['Comments']['CommentList'].length
        end

        def get_hyperlinks_count(folder_name = '', storage_type = 'Aspose', storage_name = '')
          str_uri = "#{@base_uri}/hyperlinks"
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)
          JSON.parse(RestClient.get(signed_str_uri, {:accept=>'application/json'}))['Hyperlinks']['HyperlinkList'].length
        end

        def hide_worksheet(folder_name = '', storage_type = 'Aspose', storage_name = '')
          str_uri = "#{@base_uri}/visible?isVisible=false"
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)
          JSON.parse(RestClient.put(signed_str_uri, '', {:accept=>'application/json'}))['Code'] == 200 ? true : false
        end

        def unhide_worksheet(folder_name = '', storage_type = 'Aspose', storage_name = '')
          str_uri = "#{@base_uri}/visible?isVisible=true"
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)
          JSON.parse(RestClient.put(signed_str_uri, '', {:accept=>'application/json'}))['Code'] == 200 ? true : false
        end

        def get_rows_count(offset, count, folder_name = '', storage_type = 'Aspose', storage_name = '')
          raise 'offset not specified.' if offset.nil?
          raise 'count not specified.' if count.nil?

          str_uri = "#{@base_uri}/cells/rows"
          qry = { :offset => offset, :count => count}
          str_uri = Aspose::Cloud::Common::Utils.build_uri(str_uri,qry)
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)
          JSON.parse(RestClient.get(signed_str_uri, {:accept=>'application/json'}))['Rows']['RowsCount']
        end

        def get_row(index, folder_name = '', storage_type = 'Aspose', storage_name = '')
          raise 'index not specified.' if index.nil?

          str_uri = "#{@base_uri}/cells/rows/#{index}"
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)
          JSON.parse(RestClient.get(signed_str_uri, {:accept=>'application/json'}))['Row']
        end

        def delete_row(index, folder_name = '', storage_type = 'Aspose', storage_name = '')
          raise 'index not specified.' if index.nil?

          str_uri = "#{@base_uri}/cells/rows/#{index}"
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)
          JSON.parse(RestClient.delete(signed_str_uri, {:accept=>'application/json'}))['Code'] == 200 ? true : false
        end

        def sort_data(data_sort, cell_area, folder_name = '', storage_type = 'Aspose', storage_name = '')
          raise 'data_sort not specified.' if data_sort.nil?
          raise 'cell_area not specified.' if cell_area.nil?

          str_uri = "#{@base_uri}/sort"
          qry = { :CellArea => cell_area }

          str_uri = Aspose::Cloud::Common::Utils.build_uri(str_uri,qry)
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)
          JSON.parse(RestClient.post(signed_str_uri, data_sort.to_json, { :content_type => :json, :accept=>'application/json'}))['Code'] == 200 ? true : false
        end

        def get_column(index, folder_name = '', storage_type = 'Aspose', storage_name = '')
          raise 'index not specified.' if index.nil?

          str_uri = "#{@base_uri}/cells/columns/#{index}"
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)
          JSON.parse(RestClient.get(signed_str_uri, {:accept=>'application/json'}))['Column']
        end


        def move_worksheet(position, folder_name = '', storage_type = 'Aspose', storage_name = '')
          raise 'position not specified.' if position.nil?

          json_data = { :DestinationWorksheet => @worksheet_name, :Position => position }
          str_uri = "#{@base_uri}/position"
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)
          JSON.parse(RestClient.post(signed_str_uri, json_data, { :content_type => :json, :accept=>'application/json'}))['Code'] == 200 ? true : false
        end

        def calculate_formula(formula, folder_name = '', storage_type = 'Aspose', storage_name = '')
          raise 'formula not specified.' if formula.empty?

          str_uri = "#{@base_uri}/formulaResult?formula=#{formula}"
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)
          JSON.parse(RestClient.get(signed_str_uri, {:accept=>'application/json'}))['Value']
        end

        def set_formula(cell_name, formula, folder_name = '', storage_type = 'Aspose', storage_name = '')
          raise 'cell_name not specified.' if cell_name.empty?
          raise 'formula not specified.' if formula.empty?          

          str_uri = "#{@base_uri}/cells/#{cell_name}?formula=#{formula}"
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)
          JSON.parse(RestClient.post(signed_str_uri, '', { :accept=>'application/json'}))['Code'] == 200 ? true : false
        end

        def set_cell_value(cell_name, value_type, value, folder_name = '', storage_type = 'Aspose', storage_name = '')
          raise 'cell_name not specified.' if cell_name.empty?
          raise 'value_type not specified.' if value_type.empty?
          raise 'value not specified.' if value.empty?

          str_uri = "#{@base_uri}/cells/#{cell_name}"
          qry = { :value => value, :type => value_type }
          str_uri = Aspose::Cloud::Common::Utils.build_uri(str_uri,qry)
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)
          JSON.parse(RestClient.post(signed_str_uri, '', { :accept=>'application/json'}))['Code'] == 200 ? true : false
        end

        def add_picture(picture_path, upper_left_row, upper_left_column, lower_right_row, lower_right_column, folder_name = '', storage_type = 'Aspose', storage_name = '')
          raise 'picture_path not specified.' if picture_path.empty?
          raise 'upper_left_row not specified.' if upper_left_row.nil?
          raise 'upper_left_column not specified.' if upper_left_column.nil?
          raise 'lower_right_row not specified.' if lower_right_row.nil?
          raise 'lower_right_column not specified.' if lower_right_column.nil?

          qry = { :upperLeftRow => upper_left_row, :upperLeftColumn => upper_left_column, :lowerRightRow => lower_right_row,
          :lowerRightColumn => lower_right_column, :picturePath => picture_path}
          str_uri = "#{@base_uri}/pictures"
          str_uri = Aspose::Cloud::Common::Utils.build_uri(str_uri,qry)
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)
          JSON.parse(RestClient.put(signed_str_uri, '', { :accept=>'application/json'}))['Code'] == 200 ? true : false
        end

        def update_picture(picture_index, picture_data, folder_name = '', storage_type = 'Aspose', storage_name = '')
          raise 'picture_index not specified.' if picture_index.nil?
          raise 'picture_data not specified.' if picture_data.empty?  

          str_uri = "#{@base_uri}/pictures/#{picture_index}"          
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)
          JSON.parse(RestClient.post(signed_str_uri, picture_data, { :accept=>'application/json'}))['Code'] == 200 ? true : false
        end

        def delete_pictures(folder_name = '', storage_type = 'Aspose', storage_name = '')
          str_uri = "#{@base_uri}/pictures"
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)

          response_stream = RestClient.delete(signed_str_uri, {:accept=>'application/json'})
          valid_output = Aspose::Cloud::Common::Utils.validate_output(response_stream)
          valid_output.empty? ? Aspose::Cloud::Common::Utils.download_file(@filename,@filename,folder_name,storage_name,storage_type) : valid_output
        end

        def delete_picture(picture_index, folder_name = '', storage_type = 'Aspose', storage_name = '')
          raise 'picture_index not specified.' if picture_index.nil?

          str_uri = "#{@base_uri}/pictures/#{picture_index}"
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)

          response_stream = RestClient.delete(signed_str_uri, {:accept=>'application/json'})
          valid_output = Aspose::Cloud::Common::Utils.validate_output(response_stream)
          valid_output.empty? ? Aspose::Cloud::Common::Utils.download_file(@filename,@filename,folder_name,storage_name,storage_type) : valid_output
        end

        def copy_worksheet(new_worksheet_name, folder_name = '', storage_type = 'Aspose', storage_name = '')
          raise 'new_worksheet_name not specified.' if new_worksheet_name.empty?      
        
          str_uri = "#{Aspose::Cloud::Common::Product.product_uri}/cells/#{@filename}/worksheets/#{new_worksheet_name}/copy?sourcesheet=#{@worksheet_name}"          
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)

          response_stream = RestClient.post(signed_str_uri, '', {:accept=>'application/json'})
          valid_output = Aspose::Cloud::Common::Utils.validate_output(response_stream)
          valid_output.empty? ? Aspose::Cloud::Common::Utils.download_file(@filename,@filename,folder_name,storage_name,storage_type) : valid_output
        end

        def rename_worksheet(new_worksheet_name, folder_name = '', storage_type = 'Aspose', storage_name = '')
          raise 'new_worksheet_name not specified.' if new_worksheet_name.empty?      
        
          str_uri = "#{@base_uri}/Rename?newname=#{new_worksheet_name}"
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)

          response_stream = RestClient.post(signed_str_uri, '', {:accept=>'application/json'})
          valid_output = Aspose::Cloud::Common::Utils.validate_output(response_stream)
          valid_output.empty? ? Aspose::Cloud::Common::Utils.download_file(@filename,@filename,folder_name,storage_name,storage_type) : valid_output
        end

        def set_background(background_image, folder_name = '', storage_type = 'Aspose', storage_name = '')
          raise 'background_image not specified.' if background_image.empty?      
        
          str_uri = "#{@base_uri}/Background?imageFile=#{background_image}"
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)

          response_stream = RestClient.post(signed_str_uri, '', {:accept=>'application/json'})
          valid_output = Aspose::Cloud::Common::Utils.validate_output(response_stream)
          valid_output.empty? ? Aspose::Cloud::Common::Utils.download_file(@filename,@filename,folder_name,storage_name,storage_type) : valid_output
        end

        def delete_background(folder_name = '', storage_type = 'Aspose', storage_name = '')
          str_uri = "#{@base_uri}/Background"
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)

          response_stream = RestClient.delete(signed_str_uri, {:accept=>'application/json'})
          valid_output = Aspose::Cloud::Common::Utils.validate_output(response_stream)
          valid_output.empty? ? Aspose::Cloud::Common::Utils.download_file(@filename,@filename,folder_name,storage_name,storage_type) : valid_output
        end

        def freeze_panes(row, column, freezed_rows, freezed_columns, folder_name = '', storage_type = 'Aspose', storage_name = '')
          raise 'row not specified.' if row.nil?
          raise 'column not specified.' if column.nil?
          raise 'freezed_rows not specified.' if freezed_rows.nil?
          raise 'freezed_columns not specified.' if freezed_columns.nil?
        
          str_uri = "#{@base_uri}/FreezePanes?row=#{row}&column=#{column}&freezedRows=#{freezed_rows}&freezedColumns=#{freezed_columns}"
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)

          response_stream = RestClient.put(signed_str_uri, '', {:accept=>'application/json'})
          valid_output = Aspose::Cloud::Common::Utils.validate_output(response_stream)
          valid_output.empty? ? Aspose::Cloud::Common::Utils.download_file(@filename,@filename,folder_name,storage_name,storage_type) : valid_output
        end

        def unfreeze_panes(row, column, freezed_rows, freezed_columns, folder_name = '', storage_type = 'Aspose', storage_name = '')
          raise 'row not specified.' if row.nil?
          raise 'column not specified.' if column.nil?
          raise 'freezed_rows not specified.' if freezed_rows.nil?
          raise 'freezed_columns not specified.' if freezed_columns.nil?
        
          str_uri = "#{@base_uri}/FreezePanes?row=#{row}&column=#{column}&freezedRows=#{freezed_rows}&freezedColumns=#{freezed_columns}"
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)

          response_stream = RestClient.delete(signed_str_uri, {:accept=>'application/json'})
          valid_output = Aspose::Cloud::Common::Utils.validate_output(response_stream)
          valid_output.empty? ? Aspose::Cloud::Common::Utils.download_file(@filename,@filename,folder_name,storage_name,storage_type) : valid_output
        end

        def add_empty_row(row_id, folder_name = '', storage_type = 'Aspose', storage_name = '')
          raise 'row_id not specified.' if row_id.nil?

          str_uri = "#{@base_uri}/cells/rows/#{row_id}"
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)

          response_stream = RestClient.put(signed_str_uri, '', {:accept=>'application/json'})
          valid_output = Aspose::Cloud::Common::Utils.validate_output(response_stream)
          valid_output.empty? ? Aspose::Cloud::Common::Utils.download_file(@filename,@filename,folder_name,storage_name,storage_type) : valid_output
        end

        def copy_row(source_row_index, destination_row_index, row_number, folder_name = '', storage_type = 'Aspose', storage_name = '')
          raise 'source_row_index not specified.' if source_row_index.nil?
          raise 'destination_row_index not specified.' if destination_row_index.nil?
          raise 'row_number not specified.' if row_number.nil?

          str_uri = "#{@base_uri}/cells/rows/copy?sourceRowIndex=#{source_row_index}&destinationRowIndex=#{destination_row_index}&rowNumber=#{row_number}"
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)

          response_stream = RestClient.post(signed_str_uri, '', {:accept=>'application/json'})
          valid_output = Aspose::Cloud::Common::Utils.validate_output(response_stream)
          valid_output.empty? ? Aspose::Cloud::Common::Utils.download_file(@filename,@filename,folder_name,storage_name,storage_type) : valid_output
        end

        def hide_rows(start_row, total_rows, folder_name = '', storage_type = 'Aspose', storage_name = '')
          raise 'start_row not specified.' if start_row.nil?
          raise 'total_rows not specified.' if total_rows.nil?          

          str_uri = "#{@base_uri}/cells/rows/hide?startrow=#{start_row}&totalRows=#{total_rows}"
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)

          response_stream = RestClient.post(signed_str_uri, '', {:accept=>'application/json'})
          valid_output = Aspose::Cloud::Common::Utils.validate_output(response_stream)
          valid_output.empty? ? Aspose::Cloud::Common::Utils.download_file(@filename,@filename,folder_name,storage_name,storage_type) : valid_output
        end

        def unhide_rows(start_row, total_rows, folder_name = '', storage_type = 'Aspose', storage_name = '')
          raise 'start_row not specified.' if start_row.nil?
          raise 'total_rows not specified.' if total_rows.nil?          

          str_uri = "#{@base_uri}/cells/rows/unhide?startrow=#{start_row}&totalRows=#{total_rows}"
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)

          response_stream = RestClient.post(signed_str_uri, '', {:accept=>'application/json'})
          valid_output = Aspose::Cloud::Common::Utils.validate_output(response_stream)
          valid_output.empty? ? Aspose::Cloud::Common::Utils.download_file(@filename,@filename,folder_name,storage_name,storage_type) : valid_output
        end

        def group_rows(first_index, last_index, folder_name = '', storage_type = 'Aspose', storage_name = '')
          raise 'first_index not specified.' if first_index.nil?
          raise 'last_index not specified.' if last_index.nil?          

          str_uri = "#{@base_uri}/cells/rows/group?firstIndex=#{first_index}&lastIndex=#{last_index}"
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)

          response_stream = RestClient.post(signed_str_uri, '', {:accept=>'application/json'})
          valid_output = Aspose::Cloud::Common::Utils.validate_output(response_stream)
          valid_output.empty? ? Aspose::Cloud::Common::Utils.download_file(@filename,@filename,folder_name,storage_name,storage_type) : valid_output
        end

        def ungroup_rows(first_index, last_index, folder_name = '', storage_type = 'Aspose', storage_name = '')
          raise 'first_index not specified.' if first_index.nil?
          raise 'last_index not specified.' if last_index.nil?          

          str_uri = "#{@base_uri}/cells/rows/ungroup?firstIndex=#{first_index}&lastIndex=#{last_index}"
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)

          response_stream = RestClient.post(signed_str_uri, '', {:accept=>'application/json'})
          valid_output = Aspose::Cloud::Common::Utils.validate_output(response_stream)
          valid_output.empty? ? Aspose::Cloud::Common::Utils.download_file(@filename,@filename,folder_name,storage_name,storage_type) : valid_output
        end

        def merge_cells(start_row, start_column, total_rows, total_colunms, folder_name = '', storage_type = 'Aspose', storage_name = '')
          raise 'start_row not specified.' if start_row.nil?
          raise 'start_column not specified.' if start_column.nil?
          raise 'total_rows not specified.' if total_rows.nil?
          raise 'total_colunms not specified.' if total_colunms.nil?

          str_uri = "#{@base_uri}/cells/merge?startrow=#{start_row}&startcolumn=#{start_column}&totalrows=#{total_rows}&totalcolumns=#{total_colunms}"
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)

          response_stream = RestClient.post(signed_str_uri, '', {:accept=>'application/json'})
          valid_output = Aspose::Cloud::Common::Utils.validate_output(response_stream)
          valid_output.empty? ? Aspose::Cloud::Common::Utils.download_file(@filename,@filename,folder_name,storage_name,storage_type) : valid_output
        end

        def unmerge_cells(start_row, start_column, total_rows, total_colunms, folder_name = '', storage_type = 'Aspose', storage_name = '')
          raise 'start_row not specified.' if start_row.nil?
          raise 'start_column not specified.' if start_column.nil?
          raise 'total_rows not specified.' if total_rows.nil?
          raise 'total_colunms not specified.' if total_colunms.nil?

          str_uri = "#{@base_uri}/cells/unmerge?startrow=#{start_row}&startcolumn=#{start_column}&totalrows=#{total_rows}&totalcolumns=#{total_colunms}"
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)

          response_stream = RestClient.post(signed_str_uri, '', {:accept=>'application/json'})
          valid_output = Aspose::Cloud::Common::Utils.validate_output(response_stream)
          valid_output.empty? ? Aspose::Cloud::Common::Utils.download_file(@filename,@filename,folder_name,storage_name,storage_type) : valid_output
        end

        def clear_cells_formatting(start_row, start_column, end_row, end_column, folder_name = '', storage_type = 'Aspose', storage_name = '')
          raise 'start_row not specified.' if start_row.nil?
          raise 'start_column not specified.' if start_column.nil?
          raise 'end_row not specified.' if end_row.nil?
          raise 'end_column not specified.' if end_column.nil?

          str_uri = "#{@base_uri}/cells/ClearFormats?startRow=#{start_row}&startColumn=#{start_column}&endRow=#{end_row}&endColumn=#{end_column}"
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)

          response_stream = RestClient.post(signed_str_uri, '', {:accept=>'application/json'})
          valid_output = Aspose::Cloud::Common::Utils.validate_output(response_stream)
          valid_output.empty? ? Aspose::Cloud::Common::Utils.download_file(@filename,@filename,folder_name,storage_name,storage_type) : valid_output
        end

        def clear_cells_contents(range, folder_name = '', storage_type = 'Aspose', storage_name = '')
          raise 'range not specified.' if range.empty?          

          str_uri = "#{@base_uri}/cells/clearcontents?range=#{range}"
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)

          response_stream = RestClient.post(signed_str_uri, '', {:accept=>'application/json'})
          valid_output = Aspose::Cloud::Common::Utils.validate_output(response_stream)
          valid_output.empty? ? Aspose::Cloud::Common::Utils.download_file(@filename,@filename,folder_name,storage_name,storage_type) : valid_output
        end
        
        def set_range_value(cellarea, value, type, folder_name = '', storage_type = 'Aspose', storage_name = '')
          raise 'cellarea not specified.' if cellarea.empty?
          raise 'value not specified.' if value.empty?
          raise 'type not specified.' if type.empty?

          str_uri = "#{@base_uri}/cells?cellarea=#{cellarea}&value=#{value}&type=#{type}"
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)

          response_stream = RestClient.post(signed_str_uri, '', {:accept=>'application/json'})
          valid_output = Aspose::Cloud::Common::Utils.validate_output(response_stream)
          valid_output.empty? ? Aspose::Cloud::Common::Utils.download_file(@filename,@filename,folder_name,storage_name,storage_type) : valid_output
        end

        def update_properties(worksheet_name, gridlines_visible = false, pagebreak_preview = false, ruler_visible = false, folder_name = '', storage_type = 'Aspose', storage_name = '')
          raise 'worksheet_name not specified.' if worksheet_name.empty?

          json_data = { :Name=>worksheet_name, :IsGridlinesVisible=>gridlines_visible, :IsPageBreakPreview=>pagebreak_preview, :IsRulerVisible=>ruler_visible }.to_json
          str_uri = "#{@base_uri}"
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)
          response = RestClient.post(signed_str_uri, json_data, {:content_type=>:json, :accept=>'application/json'})
          json = JSON.parse(response)
          if json['Code'] == 200
            Aspose::Cloud::Common::Utils.download_file(@filename,@filename,folder_name,storage_name,storage_type)
          end
        end
      end
    end
  end
end