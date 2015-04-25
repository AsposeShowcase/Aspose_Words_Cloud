module Aspose
  module Cloud
    module Slides
      class Converter
        def initialize(filename)
          @filename = filename
          raise 'filename not specified.' if filename.empty?
          @base_uri =  Aspose::Cloud::Common::Product.product_uri + '/slides/' + @filename
        end

=begin
  Saves a particular slide into various formats with specified width and height
=end    
        def convert_to_image(slide_number, image_format, width, height, folder_name = '', storage_type = 'Aspose', storage_name = '')
            raise 'slide_number not specified.' if slide_number.nil?
            raise 'image_format not specified.' if image_format.empty?
            raise 'width not specified.' if width <= 0
            raise 'height not specified.' if height <= 0

            str_uri = "#{@base_uri}/slides/#{slide_number}"
            qry = Hash.new
            qry[:format] = image_format
            qry[:width] = width
            qry[:height] = height
            str_uri = Aspose::Cloud::Common::Utils.build_uri(str_uri,qry)
            str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
            signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)
            response_stream = RestClient.get(signed_str_uri, {:accept=>'application/json'})

            valid_output = Aspose::Cloud::Common::Utils.validate_output(response_stream)

            if valid_output.empty?
              output_path = "#{Aspose::Cloud::Common::AsposeApp.output_location}#{Aspose::Cloud::Common::Utils.get_filename(@filename)}.#{image_format}"
              Aspose::Cloud::Common::Utils.save_file(response_stream,output_path)
            end
            valid_output
        end

=begin
  convert a document to SaveFormat
=end    
        def convert(slide_number, save_format, folder_name = '', storage_type = 'Aspose', storage_name = '')
          raise 'slide_number not specified.' if slide_number.nil?
          raise 'save_format not specified.' if save_format.empty?

          str_uri = "#{@base_uri}/slides/#{slide_number}?format=#{save_format}"
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)          
          response_stream = RestClient.get(signed_str_uri, {:accept=>'application/json'})

          valid_output = Aspose::Cloud::Common::Utils.validate_output(response_stream)

          if valid_output.empty?
            output_path = "#{Aspose::Cloud::Common::AsposeApp.output_location}#{Aspose::Cloud::Common::Utils.get_filename(@filename)}.#{save_format}"
            Aspose::Cloud::Common::Utils.save_file(response_stream,output_path)
          end
          valid_output
        end

=begin
  convert a document to other formats without using storage
=end
        def convert_local_file(input_file_path, output_filename, save_format)
          raise 'input_file_path not specified.' if input_file_path.empty?
          raise 'output_filename not specified.' if output_filename.empty?
          raise 'save_format not specified.' if save_format.empty?

          str_uri = "#{Aspose::Cloud::Common::Product.product_uri}/slides/convert?format=#{save_format}"
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
  Convert PowerPoint Documents to other File Formats with Additional Settings
=end
        def convert_with_additional_settings(save_format = 'pdf', text_compression = '', embed_full_fonts = '', compliance ='', jpeg_quality = '', save_metafiles_as_png = '', pdf_password = '', embed_true_type_fonts_for_ascii = '',  folder_name = '', storage_type = 'Aspose', storage_name = '')
          str_uri = "#{@base_uri}?format=#{save_format}"
          str_uri += "&TextCompression=#{text_compression}" unless text_compression.empty?
          str_uri += "&EmbedFullFonts=#{embed_full_fonts}" unless embed_full_fonts
          str_uri += "&Compliance=#{compliance}" unless compliance.empty?
          str_uri += "&JpegQuality=#{jpeg_quality}" unless jpeg_quality.nil?
          str_uri += "&SaveMetafilesAsPng=#{save_metafiles_as_png}" unless save_metafiles_as_png
          str_uri += "&PdfPassword=#{pdf_password}" unless pdf_password.empty?
          str_uri += "&EmbedTrueTypeFontsForASCII=#{embed_true_type_fonts_for_ascii}" unless embed_true_type_fonts_for_ascii
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)          
          response_stream = RestClient.get(signed_str_uri, {:accept=>'application/json'})

          valid_output = Aspose::Cloud::Common::Utils.validate_output(response_stream)

          if valid_output.empty?
            output_path = "#{Aspose::Cloud::Common::AsposeApp.output_location}#{Aspose::Cloud::Common::Utils.get_filename(@filename)}.#{save_format}"
            Aspose::Cloud::Common::Utils.save_file(response_stream,output_path)
          end
          valid_output
        end        
      end
    end
  end
end