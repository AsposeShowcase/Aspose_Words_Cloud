module Aspose
  module Cloud
    module Pdf
      class Converter
        def initialize(filename)
          @filename = filename
          raise 'filename not specified.' if filename.empty?
          @base_uri =  Aspose::Cloud::Common::Product.product_uri + '/pdf/' + @filename
        end
=begin
convert a particular page to image with default size
=end
        def convert_to_image(page_number, image_format, folder_name = '', storage_type = 'Aspose', storage_name = '')
          raise 'page_number not specified.' if page_number.nil?
          raise 'image_format not specified.' if image_format.empty?

          qry = Hash.new

          str_uri = "#{@base_uri}/pages/#{page_number}"
          qry[:format] = image_format
          str_uri = Aspose::Cloud::Common::Utils.build_uri(str_uri,qry)
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)

          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)
          response_stream = RestClient.get(signed_str_uri, {:accept=>'application/json'})
          valid_output = Aspose::Cloud::Common::Utils.validate_output(response_stream)


          if valid_output.empty?
            output_path = "#{Aspose::Cloud::Common::AsposeApp.output_location}#{Aspose::Cloud::Common::Utils.get_filename(@filename)}_#{page_number}.#{image_format}"
            Aspose::Cloud::Common::Utils.save_file(response_stream,output_path)
          end
          valid_output
        end

=begin
convert a particular page to image with specified size
=end
        def convert_to_image_by_size(page_number, image_format, width=0, height=0, folder_name = '', storage_type = 'Aspose', storage_name = '')
          raise 'page_number not specified.' if page_number.nil?
          raise 'image_format not specified.' if image_format.empty?

          qry = Hash.new

          str_uri = "#{@base_uri}/pages/#{page_number}"
          qry[:format] = image_format
          qry[:width] = width unless width <= 0
          qry[:height] = width unless height <= 0
          str_uri = Aspose::Cloud::Common::Utils.build_uri(str_uri,qry)
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)

          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)
          response_stream = RestClient.get(signed_str_uri, {:accept=>'application/json'})
          valid_output = Aspose::Cloud::Common::Utils.validate_output(response_stream)


          if valid_output.empty?
            output_path = "#{Aspose::Cloud::Common::AsposeApp.output_location}#{Aspose::Cloud::Common::Utils.get_filename(@filename)}_#{page_number}.#{image_format}"
            Aspose::Cloud::Common::Utils.save_file(response_stream,output_path)
          end
          valid_output
        end

=begin
convert a pdf document to given format
=end
        def convert(save_format, folder_name = '', storage_type = 'Aspose', storage_name = '')
          raise 'save_format not specified.' if save_format.empty?

          str_uri = "#{@base_uri}?format=#{save_format}"
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)

          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)
          response_stream = RestClient.get(signed_str_uri, {:accept=>'application/json'})
          valid_output = Aspose::Cloud::Common::Utils.validate_output(response_stream)

          save_format = 'zip' if save_format.eql?('html')

          if valid_output.empty?
            output_path = "#{Aspose::Cloud::Common::AsposeApp.output_location}#{Aspose::Cloud::Common::Utils.get_filename(@filename)}.#{save_format}"
            Aspose::Cloud::Common::Utils.save_file(response_stream,output_path)
          end
          valid_output
        end

=begin
convert a local pdf document to given format without use of storage
=end
        def convert_local_file(input_file, output_file, save_format)
          raise 'input_file not specified.' if input_file.empty?
          raise 'output_file not specified.' if output_file.empty?
          raise 'save_format not specified.' if save_format.empty?

          str_uri = "#{Aspose::Cloud::Common::Product.product_uri}/pdf/convert?format=#{save_format}"
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)
          response_stream = Aspose::Cloud::Common::Utils.upload_file_binary(input_file, signed_str_uri)
          valid_output = Aspose::Cloud::Common::Utils.validate_output(response_stream)

          save_format = 'zip' if save_format.eql?('html')

          if valid_output.empty?
            output_path = "#{Aspose::Cloud::Common::AsposeApp.output_location}#{Aspose::Cloud::Common::Utils.get_filename(output_file)}.#{save_format}"
            Aspose::Cloud::Common::Utils.save_file(response_stream,output_path)
          end
          valid_output
        end

=begin
Convert PDF from Remote Server to other Formats
=end
        def convert_by_url(url, save_format, output_file, folder_name = '', storage_type = 'Aspose', storage_name = '')
          raise 'url not specified.' if url.empty?
          raise 'save_format not specified.' if save_format.empty?
          raise 'output_file not specified.' if output_file.empty?          

          str_uri = Aspose::Cloud::Common::Product.product_uri + "/pdf/convert?url=#{url}&format=#{save_format}"
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)

          response_stream = RestClient.put(signed_str_uri, {:accept=>'application/json'})
          valid_output = Aspose::Cloud::Common::Utils.validate_output(response_stream)

          save_format = 'zip' if save_format.eql?('html')

          if valid_output.empty?
            output_path = "#{Aspose::Cloud::Common::AsposeApp.output_location}#{Aspose::Cloud::Common::Utils.get_filename(output_file)}.#{save_format}"
            Aspose::Cloud::Common::Utils.save_file(response_stream,output_path)
          end
          valid_output
        end

      end
    end
  end
end