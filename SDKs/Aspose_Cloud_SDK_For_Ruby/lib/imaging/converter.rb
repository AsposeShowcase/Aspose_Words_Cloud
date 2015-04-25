module Aspose
  module Cloud
    module Imaging
      class Converter
        def initialize(filename)
          @filename = filename
          raise 'filename not specified.' if filename.empty?
          @base_uri =  Aspose::Cloud::Common::Product.product_uri + '/imaging/' + @filename
        end

=begin
  convert an image file to a different format
  @param string input_file_path Path of the input file.
  @param string output_filename Name of the output file.
  @param string save_format Output file format.
=end
        def convert_local_file(input_file_path, output_filename, save_format)
          raise 'input_file_path not specified.' if input_file_path.empty?
          raise 'output_filename not specified.' if output_filename.empty?
          raise 'save_format not specified.' if save_format.empty?

          str_uri = "#{Aspose::Cloud::Common::Product.product_uri}/imaging/#{@filename}/saveAs?format=#{save_format}"
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)
          response_stream = RestClient.get(signed_str_uri, {:accept=>'application/json', :payload=>File.new(input_file_path, 'rb')})

          valid_output = Aspose::Cloud::Common::Utils.validate_output(response_stream)

          if valid_output.empty?
            save_format = 'zip' if save_format.eql?('html')
            output_path = "#{Aspose::Cloud::Common::AsposeApp.output_location}#{Aspose::Cloud::Common::Utils.get_filename(output_filename)}.#{save_format}"
            Aspose::Cloud::Common::Utils.save_file(response_stream,output_path)
          end
          valid_output
        end

=begin
  convert an image file to a fax
=end
        def convert_tiff_to_fax(folder_name = '', storage_type = 'Aspose', storage_name = '')
          str_uri = "#{Aspose::Cloud::Common::Product.product_uri}/imaging/tiff/#{@filename}/toFax"
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)
          response_stream = RestClient.get(signed_str_uri, {:accept=>'application/json'})

          valid_output = Aspose::Cloud::Common::Utils.validate_output(response_stream)

          if valid_output.empty?
            output_path = "#{Aspose::Cloud::Common::AsposeApp.output_location}#{@filename}"
            Aspose::Cloud::Common::Utils.save_file(response_stream,output_path)
          end
          valid_output
        end
      end
    end
  end
end
