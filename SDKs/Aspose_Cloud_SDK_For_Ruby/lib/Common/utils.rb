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

# This class provides common methods to be repeatedly used by other wrapper classes
module Aspose
  module Cloud
    module Common
      class Utils
        def self.process_command(url, method='GET', header_type='XML', src='')

          uri = URI.parse(url)
          http = Net::HTTP.new(uri.host, uri.port)
          http.use_ssl = false
          http.verify_mode = OpenSSL::SSL::VERIFY_NONE

          request = case method
                      when 'GET' then Net::HTTP::Get.new(url)
                      when 'DELETE' then Net::HTTP::Delete.new(url)
                      when 'POST' then Net::HTTP::Post.new(url)
                      else nil
                    end

          request.body = src unless src.empty?

          if header_type == 'XML'
            request.add_field('Content-Type', 'application/xml')
            request.add_field('x-aspose-client', 'RubySDK/v1.0')
          elsif header_type == 'JSON'
            request.add_field('Content-Type', 'application/json')
            request.add_field('x-aspose-client', 'RubySDK/v1.0')
          end

          http.request(request).body
        end

        # Signs a URI with your appSID and Key.
        # * :url describes the URL to sign

        def self.sign(url)
          url = url[0..-2] if url[-1].eql? '/'
          url = URI.escape(url)
          parsed_url = URI.parse(url)

          url_to_sign = "#{parsed_url.scheme}://#{parsed_url.host}#{parsed_url.path}?appSID=#{Aspose::Cloud::Common::AsposeApp.app_sid}"
          url_to_sign += "&#{parsed_url.query}" if parsed_url.query


          # create a signature using the private key and the URL
          raw_signature = OpenSSL::HMAC.digest(OpenSSL::Digest.new('sha1'), Aspose::Cloud::Common::AsposeApp.app_key, url_to_sign)

          #Convert raw to encoded string
          signature = Base64.strict_encode64(raw_signature).tr('+/', '-_')

          #remove invalid character 
          signature = signature.gsub(/[=_-]/, '=' => '', '_' => '%2f', '-' => '%2b')

          #Define expression
          pat = Regexp.new('%[0-9a-f]{2}')

          #Replace the portion matched to the above pattern to upper case
          6.times do
            signature = signature.sub(pat, pat.match(signature).to_s.upcase)
          end

          # prepend the server and append the signature.
          url_to_sign + "&signature=#{signature}"

        end

        def self.validate_output(result)

          validate = ['Unknown file format.',
                      'Unable to read beyond the end of the stream',
                      'Index was out of range',
                      'Cannot read that as a ZipFile',
                      'Not a Microsoft PowerPoint 2007 presentation',
                      'Index was outside the bounds of the array',
                      'An attempt was made to move the position before the beginning of the stream',
          ]

          validate.each do |value|
            return result if result.index(value)
          end

          ''

        end

        # Parses JSON date value to a valid date format
        # * :datestring holds the JSON Date value
        def self.parse_date(date_string)
          seconds_since_epoch = date_string.scan(/[0-9]+/)[0].to_i
          Time.at((seconds_since_epoch-(21600000 + 18000000))/1000)
        end

        # Uploads a binary file from the client system
        # * :localfile holds the local file path along with name
        # * :url holds the required url to use while uploading the file to Aspose Storage		 
        def self.upload_file_binary(localfile, url)
          RestClient.put(url, File.new(localfile, 'rb'), :accept => 'application/json')
        end

        # Gets the count of a particular field in the response
        # * :localfile holds the local file path along with name
        # * :url holds the required url to use while uploading the file to Aspose Storage		 		 
        def self.get_field_count(url, field_name)
          response = RestClient.get(url, :accept => 'application/xml')
          doc = REXML::Document.new(response.body)
          pages = []
          doc.elements.each(field_name) do |ele|
            pages << ele.text
          end
          pages.size
        end

        # Saves the response stream to a local file.
        def self.save_file(response_stream, local_file)
          open(local_file, 'wb') do |file|
            file.write(response_stream.body)
          end
          
          return local_file
        end

        def self.get_filename(file)
          File.basename(file, File.extname(file))
        end

        # appends storage name to the uri
        def self.append_storage(uri, remote_folder='', storage_name='', storage_type='Aspose')
          tmp_uri = "folder=#{remote_folder}&" unless remote_folder.empty?
          tmp_uri = "#{tmp_uri}storage=#{storage_name}" unless storage_name.empty? unless storage_type.eql? "Aspose"
          tmp_uri = uri.include?('?') ? "&#{tmp_uri}" : "?#{tmp_uri}" unless tmp_uri.nil?
          tmp_uri = tmp_uri[0..-2] if tmp_uri[-1].eql?('&') unless tmp_uri.nil?
          tmp_uri.nil? ? uri : uri + tmp_uri
        end

        # build uri
        def self.build_uri(path,qry_data=nil)
          qry_str = ''
          qry_data.each { |key,value| qry_str = "#{qry_str}#{key}=#{value}&" }
          uri = qry_str.empty? ? "#{path}" : "#{path}?#{qry_str}"
          uri[-1].eql?('&') ? uri[0..-2] : uri
        end

        def self.download_file(remote_filename, output_filename, remote_folder='', storage_name='', storage_type='Aspose')
          folder          = Aspose::Cloud::AsposeStorage::Folder.new
          remote_filename = "#{remote_folder}/#{remote_filename}" unless remote_folder.empty?
          output_stream   = folder.get_file(remote_filename,storage_type,storage_name)
          dst_path        = "#{Aspose::Cloud::Common::AsposeApp.output_location}#{output_filename}"
          Aspose::Cloud::Common::Utils.save_file(output_stream, dst_path)
          return {
            local_path: dst_path,
            remote_path: remote_filename
          }
        end
      end
    end
  end
end
