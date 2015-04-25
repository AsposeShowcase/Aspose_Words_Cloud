module Aspose
  module Cloud
    module Pdf
      class Extractor
        def initialize(filename)
          @filename = filename
          raise 'filename not specified.' if filename.empty?
          @base_uri =  Aspose::Cloud::Common::Product.product_uri + '/pdf/' + @filename
        end
=begin
  Gets number of images in a specified page
  @param  number page_number
  @param  string folder_name
  @param  string storage_type
  @param  string storage_name
=end
        def get_image_count(page_number=1, folder_name='', storage_type = 'Aspose', storage_name = '')
          str_uri = "#{@base_uri}/pages/#{page_number}/images"
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)

          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)

          response_stream = RestClient.get(signed_str_uri, {:accept=>'application/json'})
          stream_hash = JSON.parse(response_stream)
          stream_hash['Images']['List'].length
        end
=begin
  Get the particular image from the specified page with the default image size
	@param number page_number
	@param number image_index
	@param string image_format
  @param  string folder_name
  @param  string storage_type
  @param  string storage_name
=end    
        def get_image_default_size(page_number=1, image_index=0, image_format='png', folder_name='', storage_type = 'Aspose', storage_name = '')
          str_uri = "#{@base_uri}/pages/#{page_number}/images/#{image_index}?"
          str_uri = "#{str_uri}format=#{image_format}" unless image_format.empty?
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          str_uri = str_uri[0..-2] if str_uri[-1].eql?('?')

          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)

          response_stream = RestClient.get(signed_str_uri, {:accept=>'application/json'})
          valid_output = Aspose::Cloud::Common::Utils.validate_output(response_stream)

          if valid_output.empty?
            output_path = "#{Aspose::Cloud::Common::AsposeApp.output_location}#{Aspose::Cloud::Common::Utils.get_filename(@filename)}_#{image_index}.#{image_format}"
            Aspose::Cloud::Common::Utils.save_file(response_stream,output_path)
          end
          valid_output
        end

=begin
  Get the particular image from the specified page with the default image size
	@param  int page_number
	@param  int image_index
	@param  string image_format
	@param  int width
	@param  int height
  @param  string folder_name
  @param  string storage_type
  @param  string storage_name
=end    
        def get_image_custom_size(page_number=1, image_index=0, image_format='png', width=100, height=100, folder_name='', storage_type = 'Aspose', storage_name = '')
          qry = Hash.new
          str_uri = "#{@base_uri}/pages/#{page_number}/images/#{image_index}"
          qry[:format] = image_format unless image_format.empty?
          qry[:width] = width
          qry[:height] = height
          str_uri = Aspose::Cloud::Common::Utils.build_uri(str_uri,qry)
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          str_uri = str_uri[0..-2] if str_uri[-1].eql?('?')

          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)

          response_stream = RestClient.get(signed_str_uri, {:accept=>'application/json'})
          valid_output = Aspose::Cloud::Common::Utils.validate_output(response_stream)

          if valid_output.empty?
            output_path = "#{Aspose::Cloud::Common::AsposeApp.output_location}#{Aspose::Cloud::Common::Utils.get_filename(@filename)}_#{image_index}.#{image_format}"
            Aspose::Cloud::Common::Utils.save_file(response_stream,output_path)
          end
          valid_output
        end      
      end
    end
    
  end
end