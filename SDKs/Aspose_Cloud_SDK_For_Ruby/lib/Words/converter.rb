module Aspose
  module Cloud
    module Words
      class Converter
        def initialize(filename)
          @filename = filename
          raise 'filename not specified.' if filename.empty?
          @base_uri =  Aspose::Cloud::Common::Product.product_uri + '/words/' + @filename
        end

=begin
  Convert Word to Images, Multipage TIFF, HTML, PDF and other File Format using Cloud Storage
  @param string save_format Return file format.
=end
        def convert(save_format, folder_name = '', storage_type = 'Aspose', storage_name = '')
          raise 'save_format not specified.' if save_format.empty?

          str_uri = "#{@base_uri}?format=#{save_format}"
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)
          response_stream = RestClient.get(signed_str_uri, {:accept=>'application/json'})

          valid_output = Aspose::Cloud::Common::Utils.validate_output(response_stream)

          if valid_output.empty?
            output_path = "#{Aspose::Cloud::Common::AsposeApp.output_location}#{Aspose::Cloud::Common::Utils.get_filename(@filename)}.#{save_format}"
            Aspose::Cloud::Common::Utils.save_file(response_stream,output_path)
          else
            valid_output
          end
        end

=begin
  Convert Word to other File Formats without using the Cloud Storage
  @param string input_file_path Path of the input file.
  @param string output_filename Name of the return file.
  @param string save_format Return file format.
=end
        def convert_local_file(input_file_path, output_filename, save_format)
          raise 'input_file_path not specified.' if input_file_path.empty?
          raise 'output_filename not specified.' if output_filename.empty?
          raise 'save_format not specified.' if save_format.empty?

          str_uri = "#{Aspose::Cloud::Common::Product.product_uri}/words/convert?format=#{save_format}"
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)
          response_stream = Aspose::Cloud::Common::Utils.upload_file_binary(input_file_path, signed_str_uri)

          valid_output = Aspose::Cloud::Common::Utils.validate_output(response_stream)

          if valid_output.empty?
            save_format = 'zip' if save_format.eql?('html')
            output_path = "#{Aspose::Cloud::Common::AsposeApp.output_location}#{Aspose::Cloud::Common::Utils.get_filename(output_filename)}.#{save_format}"
            Aspose::Cloud::Common::Utils.save_file(response_stream,output_path)
          end
          valid_output
        end

=begin
  Convert web pages to Word Documents
  @param xml str_xml Provide xml data.
=end
        def convert_web_pages(str_xml, folder_name = '', storage_type = 'Aspose', storage_name = '')
          raise 'str_xml not specified.' if str_xml.empty?
 
          str_uri = "#{Aspose::Cloud::Common::Product.product_uri}/words/loadWebDocument"
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)

          response = Aspose::Cloud::Common::Utils.process_command(signed_str_uri,'POST','XML',str_xml)
          json = JSON.parse(response)
          
          if json['Code'] == 200
              filename = json['SaveResult']['DestDocument']['Href']
              Aspose::Cloud::Common::Utils.download_file(filename,filename,folder_name,storage_name,storage_type)
          end
        end
      end
    end
  end
end
