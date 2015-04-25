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
      class Workbook
        def initialize(filename)
          @filename = filename
          raise 'filename not specified.' if filename.empty?
          @base_uri = "#{Aspose::Cloud::Common::Product.product_uri}/cells/#{@filename}"
        end

        def get_properties(folder_name = '', storage_type = 'Aspose', storage_name = '')
          str_uri = "#{@base_uri}/documentProperties"
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)
          JSON.parse(RestClient.get(signed_str_uri, {:accept=>'application/json'}))['DocumentProperties']['DocumentPropertyList']
        end

        def get_property(property_name, folder_name = '', storage_type = 'Aspose', storage_name = '')
          raise 'property_name not specified.' if property_name.empty?

          str_uri = "#{@base_uri}/documentProperties/#{property_name}"
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)
          JSON.parse(RestClient.get(signed_str_uri, {:accept=>'application/json'}))['DocumentProperty']
        end

        def set_property(property_name, property_value, folder_name = '', storage_type = 'Aspose', storage_name = '')
          raise 'property_name not specified.' if property_name.empty?
          raise 'property_value not specified.' if property_value.empty?

          json_data = JSON.generate('Value'=>property_value)

          str_uri = "#{@base_uri}/documentProperties/#{property_name}"
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)
          JSON.parse(RestClient.put(signed_str_uri, json_data, {:content_type=>:json, :accept=>'application/json'}))['DocumentProperty']
        end

        def remove_all_properties(folder_name = '', storage_type = 'Aspose', storage_name = '')
          str_uri = "#{@base_uri}/documentProperties"
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)
          JSON.parse(RestClient.delete(signed_str_uri, {:accept=>'application/json'}))['Code'] == 200 ? true : false
        end

        def remove_property(property_name, folder_name = '', storage_type = 'Aspose', storage_name = '')
          raise 'property_name not specified.' if property_name.empty?

          str_uri = "#{@base_uri}/documentProperties/#{property_name}"
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)
          JSON.parse(RestClient.delete(signed_str_uri, {:accept=>'application/json'}))['Code'] == 200 ? true : false
        end
    
        def create_empty_workbook(folder_name = '', storage_type = 'Aspose', storage_name = '')
          str_uri = "#{@base_uri}"
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)
          JSON.parse(RestClient.put(signed_str_uri, '', {:accept=>'application/json'}))
        end

        def create_workbook_from_template(template_file_name, folder_name = '', storage_type = 'Aspose', storage_name = '')
          raise 'template_file_name not specified.' if template_file_name.empty?

          str_uri = "#{@base_uri}?templatefile=#{template_file_name}"
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)
          JSON.parse(RestClient.put(signed_str_uri, '', {:accept=>'application/json'}))
        end

        def create_from_smart_template(template_file_name, data_file, folder_name = '', storage_type = 'Aspose', storage_name = '')
          raise 'template_file_name not specified.' if template_file_name.empty?
          raise 'data_file not specified.' if data_file.empty?

          str_uri = "#{@base_uri}"
          qry = { :templateFile => template_file_name, :dataFile => data_file}
          str_uri = Aspose::Cloud::Common::Utils.build_uri(str_uri,qry)
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)
          JSON.parse(RestClient.put(signed_str_uri, '', {:accept=>'application/json'}))
        end

        def process_smart_marker(data_file, folder_name = '', storage_type = 'Aspose', storage_name = '')
          raise 'data_file not specified.' if data_file.empty?

          str_uri = "#{@base_uri}"
          qry = { :xmlFile => data_file}
          str_uri = Aspose::Cloud::Common::Utils.build_uri(str_uri,qry)
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)
          JSON.parse(RestClient.post(signed_str_uri, '', {:accept=>'application/json'}))
        end

        def get_worksheets_count(folder_name = '', storage_type = 'Aspose', storage_name = '')
          str_uri = "#{@base_uri}/worksheets"
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)
          JSON.parse(RestClient.get(signed_str_uri, {:accept=>'application/json'}))['Worksheets']['WorksheetList'].length
        end

        def get_name_count(folder_name = '', storage_type = 'Aspose', storage_name = '')
          str_uri = "#{@base_uri}/names"
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)
          JSON.parse(RestClient.get(signed_str_uri, {:accept=>'application/json'}))['Names'].length
        end

        def get_default_style(folder_name = '', storage_type = 'Aspose', storage_name = '')
          str_uri = "#{@base_uri}/defaultStyle"
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)
          JSON.parse(RestClient.get(signed_str_uri, {:accept=>'application/json'}))['Style']
        end

        def encrypt_workbook(encryption_type, password, key_length, folder_name = '', storage_type = 'Aspose', storage_name = '')
          raise 'encryption_type not specified.' if encryption_type.empty?
          raise 'password not specified.' if password.empty?
          raise 'key_length not specified.' if key_length.nil?

          json_data = { :EncryptionType => encryption_type, :KeyLength => key_length, :Password => password }.to_json
          str_uri = "#{@base_uri}/encryption"
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)
          JSON.parse(RestClient.post(signed_str_uri, json_data, {:content_type=>:json, :accept=>'application/json'}))['Code'] == 200 ? true : false
        end

        def decrypt_workbook(password, folder_name = '', storage_type = 'Aspose', storage_name = '')
          raise 'password not specified.' if password.empty?

          json_data = { :Password => password }.to_json
          str_uri = "#{@base_uri}/encryption"
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)
          JSON.parse(Aspose::Cloud::Common::Utils.process_command(signed_str_uri,'DELETE','JSON',json_data))['Code'] == 200 ? true : false
        end

        def protect_workbook(protection_type, password, folder_name = '', storage_type = 'Aspose', storage_name = '')
          raise 'protection_type not specified.' if protection_type.empty?
          raise 'password not specified.' if password.empty?

          json_data = { :ProtectionType => protection_type, :Password => password }.to_json
          str_uri = "#{@base_uri}/protection"
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)
          JSON.parse(RestClient.post(signed_str_uri, json_data, {:content_type=>:json, :accept=>'application/json'}))['Code'] == 200 ? true : false
        end

        def unprotect_workbook(password, folder_name = '', storage_type = 'Aspose', storage_name = '')
          raise 'password not specified.' if password.empty?

          json_data = { :Password => password }.to_json
          str_uri = "#{@base_uri}/protection"
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)
          JSON.parse(Aspose::Cloud::Common::Utils.process_command(signed_str_uri,'DELETE','JSON',json_data))['Code'] == 200 ? true : false
        end

        def set_modify_password(password, folder_name = '', storage_type = 'Aspose', storage_name = '')
          raise 'password not specified.' if password.empty?

          json_data = { :Password => password }.to_json
          str_uri = "#{@base_uri}/writeProtection"
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)
          JSON.parse(RestClient.put(signed_str_uri, json_data, {:content_type=>:json, :accept=>'application/json'}))['Code'] == 200 ? true : false
        end

        def clear_modify_password(password, folder_name = '', storage_type = 'Aspose', storage_name = '')
          raise 'password not specified.' if password.empty?

          json_data = { :Password => password }.to_json
          str_uri = "#{@base_uri}/writeProtection"
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)
          JSON.parse(Aspose::Cloud::Common::Utils.process_command(signed_str_uri,'DELETE','JSON',json_data))['Code'] == 200 ? true : false
        end

        def decrypt_password(password, folder_name = '', storage_type = 'Aspose', storage_name = '')
          raise 'password not specified.' if password.empty?

          json_data = { :Password => password }.to_json
          str_uri = "#{@base_uri}/encryption"
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)
          JSON.parse(Aspose::Cloud::Common::Utils.process_command(signed_str_uri,'DELETE','JSON',json_data))['Code'] == 200 ? true : false
        end

        def add_worksheet(worksheet_name, folder_name = '', storage_type = 'Aspose', storage_name = '')
          raise 'worksheet_name not specified.' if worksheet_name.empty?

          str_uri = "#{@base_uri}/worksheets/#{worksheet_name}"
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)
          JSON.parse(RestClient.put(signed_str_uri, '', {:accept=>'application/json'}))['Code'] == 201 ? true : false
        end

        def remove_worksheet(worksheet_name, folder_name = '', storage_type = 'Aspose', storage_name = '')
          raise 'worksheet_name not specified.' if worksheet_name.empty?

          str_uri = "#{@base_uri}/worksheets/#{worksheet_name}"
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)          
          JSON.parse(RestClient.delete(signed_str_uri, {:accept=>'application/json'}))['Code'] == 200 ? true : false
        end

        def merge_workbook(merge_filename, folder_name = '', storage_type = 'Aspose', storage_name = '')
          raise 'merge_filename not specified.' if merge_filename.empty?

          str_uri = "#{@base_uri}/merge?mergeWith=#{merge_filename}"
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)
          JSON.parse(RestClient.post(signed_str_uri, '', {:accept=>'application/json'}))['Code'] == 200 ? true : false
        end

        def split_workbook(save_format, folder_name = '', storage_type = 'Aspose', storage_name = '')
          raise 'save_format not specified.' if save_format.empty?

          str_uri = "#{@base_uri}/split?format=#{save_format}"          
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)          
          response = RestClient.post(signed_str_uri, '', {:accept=>'application/json'})
          json = JSON.parse(response)
          
          i = 1
          json['Result']['Documents'].each { |split_page|     
            source_filename = Aspose::Cloud::Common::Utils.get_filename(@filename)       
            split_filename = File.basename(split_page['link']['Href'])
            str_uri = Aspose::Cloud::Common::Product.product_uri + '/storage/file/' + split_filename
            signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)
            response_stream = RestClient.get(signed_str_uri, {:accept=>'application/json'})
            filename = "#{source_filename}_#{i}.#{save_format}"
            output_path = "#{Aspose::Cloud::Common::AsposeApp.output_location}#{filename}"
            Aspose::Cloud::Common::Utils.save_file(response_stream,output_path)
            i += 1
          }
        end       
      end
    end
  end
end