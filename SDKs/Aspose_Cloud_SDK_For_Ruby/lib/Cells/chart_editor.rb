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
      class ChartEditor
        def initialize(filename)
          @filename = filename
          raise 'filename not specified.' if filename.empty?
          @base_uri = "#{Aspose::Cloud::Common::Product.product_uri}/cells/#{@filename}"
        end

        def add_chart(worksheet_name, chart_type, upper_left_row, upper_left_column, lower_right_row, lower_right_column, folder_name='', storage_type = 'Aspose', storage_name = '')
          raise 'worksheet_name not specified.' if worksheet_name.empty?
          raise 'chart_type not specified.' if chart_type.empty?
          raise 'upper_left_row not specified.' if upper_left_row.nil?
          raise 'upper_left_column not specified.' if upper_left_column.nil?
          raise 'lower_right_row not specified.' if lower_right_row.nil?
          raise 'lower_right_column not specified.' if lower_right_column.nil?

          str_uri = "#{@base_uri}/worksheets/#{worksheet_name}/charts"
          qry = { :chartType => chart_type, :upperLeftRow => upper_left_row, :upperLeftColumn => upper_left_column,
                  :lowerRightRow => lower_right_row, :lowerRightColumn => lower_right_column }
          str_uri = Aspose::Cloud::Common::Utils.build_uri(str_uri,qry)
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)
          response_stream = RestClient.put(signed_str_uri,'',{:accept=>'application/json'})
          valid_output = Aspose::Cloud::Common::Utils.validate_output(response_stream)
          valid_output.empty? ? Aspose::Cloud::Common::Utils.download_file(@filename,@filename,folder_name,storage_name,storage_type) : valid_output
        end

        def delete_charts(worksheet_name, folder_name='', storage_type = 'Aspose', storage_name = '')
          raise 'worksheet_name not specified.' if worksheet_name.empty?          

          str_uri = "#{@base_uri}/worksheets/#{worksheet_name}/charts"
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)
          response_stream = RestClient.delete(signed_str_uri, {:accept=>'application/json'})
          valid_output = Aspose::Cloud::Common::Utils.validate_output(response_stream)
          valid_output.empty? ? Aspose::Cloud::Common::Utils.download_file(@filename,@filename,folder_name,storage_name,storage_type) : valid_output
        end

        def delete_chart(worksheet_name, chart_index, folder_name='', storage_type = 'Aspose', storage_name = '')
          raise 'worksheet_name not specified.' if worksheet_name.empty?
          raise 'chart_index not specified.' if chart_index.nil?

          str_uri = "#{@base_uri}/worksheets/#{worksheet_name}/charts/#{chart_index}"
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)
          response_stream = RestClient.delete(signed_str_uri, {:accept=>'application/json'})
          valid_output = Aspose::Cloud::Common::Utils.validate_output(response_stream)
          valid_output.empty? ? Aspose::Cloud::Common::Utils.download_file(@filename,@filename,folder_name,storage_name,storage_type) : valid_output
        end

        def show_chart_legend(worksheet_name, chart_index, folder_name='', storage_type = 'Aspose', storage_name = '')
          raise 'worksheet_name not specified.' if worksheet_name.empty?
          raise 'chart_index not specified.' if chart_index.nil?          

          str_uri = "#{@base_uri}/worksheets/#{worksheet_name}/charts/#{chart_index}/legend"
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)

          response_stream = RestClient.put(signed_str_uri, '', {:accept=>'application/json'})
          valid_output = Aspose::Cloud::Common::Utils.validate_output(response_stream)
          valid_output.empty? ? Aspose::Cloud::Common::Utils.download_file(@filename,@filename,folder_name,storage_name,storage_type) : valid_output
        end

        def hide_chart_legend(worksheet_name, chart_index, folder_name='', storage_type = 'Aspose', storage_name = '')
          raise 'worksheet_name not specified.' if worksheet_name.empty?
          raise 'chart_index not specified.' if chart_index.nil?          

          str_uri = "#{@base_uri}/worksheets/#{worksheet_name}/charts/#{chart_index}/legend"
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)

          response_stream = RestClient.delete(signed_str_uri, {:accept=>'application/json', :content_type=>'application/json'})
          valid_output = Aspose::Cloud::Common::Utils.validate_output(response_stream)
          valid_output.empty? ? Aspose::Cloud::Common::Utils.download_file(@filename,@filename,folder_name,storage_name,storage_type) : valid_output
        end

        def update_chart_legend(worksheet_name, chart_index, str_xml, folder_name='', storage_type = 'Aspose', storage_name = '')
          raise 'worksheet_name not specified.' if worksheet_name.empty?
          raise 'chart_index not specified.' if chart_index.nil?
          raise 'str_xml not specified.' if str_xml.empty?          

          str_uri = "#{@base_uri}/worksheets/#{worksheet_name}/charts/#{chart_index}/legend"
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)

          response_stream = RestClient.post(signed_str_uri, str_xml, {:accept=>'application/json'})
          valid_output = Aspose::Cloud::Common::Utils.validate_output(response_stream)
          valid_output.empty? ? Aspose::Cloud::Common::Utils.download_file(@filename,@filename,folder_name,storage_name,storage_type) : valid_output
        end

        def read_chart_legend(worksheet_name, chart_index, folder_name='', storage_type = 'Aspose', storage_name = '')
          raise 'worksheet_name not specified.' if worksheet_name.empty?
          raise 'chart_index not specified.' if chart_index.nil?

          str_uri = "#{@base_uri}/worksheets/#{worksheet_name}/charts/#{chart_index}/legend"
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)
          JSON.parse(RestClient.get(signed_str_uri, {:accept=>'application/json'}))['Legend']
        end

        def get_chart_area(worksheet_name, chart_index, folder_name='', storage_type = 'Aspose', storage_name = '')
          raise 'worksheet_name not specified.' if worksheet_name.empty?
          raise 'chart_index not specified.' if chart_index.nil?

          str_uri = "#{@base_uri}/worksheets/#{worksheet_name}/charts/#{chart_index}/chartArea"
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)
          JSON.parse(RestClient.get(signed_str_uri, {:accept=>'application/json'}))['ChartArea']
        end

        def get_fill_format(worksheet_name, chart_index, folder_name='', storage_type = 'Aspose', storage_name = '')
          raise 'worksheet_name not specified.' if worksheet_name.empty?
          raise 'chart_index not specified.' if chart_index.nil?

          str_uri = "#{@base_uri}/worksheets/#{worksheet_name}/charts/#{chart_index}/chartArea/fillFormat"
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)
          JSON.parse(RestClient.get(signed_str_uri, {:accept=>'application/json'}))['FillFormat']
        end

        def get_border(worksheet_name, chart_index, folder_name='', storage_type = 'Aspose', storage_name = '')
          raise 'worksheet_name not specified.' if worksheet_name.empty?
          raise 'chart_index not specified.' if chart_index.nil?

          str_uri = "#{@base_uri}/worksheets/#{worksheet_name}/charts/#{chart_index}/chartArea/border"
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)
          JSON.parse(RestClient.get(signed_str_uri, {:accept=>'application/json'}))['Line']
        end

        def set_chart_title(worksheet_name, chart_index, str_xml, folder_name='', storage_type = 'Aspose', storage_name = '')
          raise 'worksheet_name not specified.' if worksheet_name.empty?
          raise 'chart_index not specified.' if chart_index.nil?
          raise 'str_xml not specified.' if str_xml.empty?

          str_uri = "#{@base_uri}/worksheets/#{worksheet_name}/charts/#{chart_index}/title"
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)

          response_stream = RestClient.put(signed_str_uri, str_xml, {:accept=>'application/json'})
          valid_output = Aspose::Cloud::Common::Utils.validate_output(response_stream)
          valid_output.empty? ? Aspose::Cloud::Common::Utils.download_file(@filename,@filename,folder_name,storage_name,storage_type) : valid_output
        end

        def update_chart_title(worksheet_name, chart_index, str_xml, folder_name='', storage_type = 'Aspose', storage_name = '')
          raise 'worksheet_name not specified.' if worksheet_name.empty?
          raise 'chart_index not specified.' if chart_index.nil?
          raise 'str_xml not specified.' if str_xml.empty?

          str_uri = "#{@base_uri}/worksheets/#{worksheet_name}/charts/#{chart_index}/title"
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)

          response_stream = RestClient.post(signed_str_uri, str_xml, {:accept=>'application/json'})
          valid_output = Aspose::Cloud::Common::Utils.validate_output(response_stream)
          valid_output.empty? ? Aspose::Cloud::Common::Utils.download_file(@filename,@filename,folder_name,storage_name,storage_type) : valid_output
        end

        def delete_chart_title(worksheet_name, chart_index, folder_name='', storage_type = 'Aspose', storage_name = '')
          raise 'worksheet_name not specified.' if worksheet_name.empty?
          raise 'chart_index not specified.' if chart_index.nil?

          str_uri = "#{@base_uri}/worksheets/#{worksheet_name}/charts/#{chart_index}/title"
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)

          response_stream = RestClient.delete(signed_str_uri, {:accept=>'application/json'})
          valid_output = Aspose::Cloud::Common::Utils.validate_output(response_stream)
          valid_output.empty? ? Aspose::Cloud::Common::Utils.download_file(@filename,@filename,folder_name,storage_name,storage_type) : valid_output
        end
      end
    end
  end
end