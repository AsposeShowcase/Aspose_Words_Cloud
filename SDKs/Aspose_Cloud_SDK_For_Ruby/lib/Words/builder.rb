module Aspose
  module Cloud
    module Words
      class Builder
        def initialize(filename)
          @filename = filename
          raise 'filename not specified.' if filename.empty?
          @base_uri =  Aspose::Cloud::Common::Product.product_uri + '/words/' + @filename
        end

=begin
  Inserts water mark text into the document.
  @param string text Watermark text.
  @param string rotation_angle Watermark angle.
=end
        def insert_watermark_text(text, rotation_angle, folder_name = '', storage_type = 'Aspose', storage_name = '')
          raise 'text not specified.' if text.empty?
          raise 'rotation_angle not specified.' if rotation_angle.nil?

          json_data = {:Text => text, :RotationAngle => rotation_angle}.to_json
          str_uri = "#{@base_uri}/insertWatermarkText"
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)
          response_stream = RestClient.post(signed_str_uri, json_data, {:content_type => :json, :accept => 'application/json'})
          valid_output = Aspose::Cloud::Common::Utils.validate_output(response_stream)
          valid_output.empty? ? Aspose::Cloud::Common::Utils.download_file(@filename,@filename,folder_name,storage_name,storage_type) : valid_output
        end

=begin
  Inserts water mark image into the document.
  @param string image_file Name of the watermark image.
  @param string rotation_angle Watermark angle.
=end
        def insert_watermark_image(image_file, rotation_angle, folder_name = '', storage_type = 'Aspose', storage_name = '')
          raise 'image_file not specified.' if image_file.empty?
          raise 'rotation_angle not specified.' if rotation_angle.nil?

          str_uri = "#{@base_uri}/watermark/insertImage?image=#{image_file}&rotationAngle=#{rotation_angle}"
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)
          response_stream = RestClient.post(signed_str_uri, '', {:content_type => :json, :accept => 'application/json'})
          valid_output = Aspose::Cloud::Common::Utils.validate_output(response_stream)
          valid_output.empty? ? Aspose::Cloud::Common::Utils.download_file(@filename,@filename,folder_name,storage_name,storage_type) : valid_output
        end

=begin
  Remove Watermark from a Word Document
=end
        def remove_watermark(folder_name = '', storage_type = 'Aspose', storage_name = '')
          str_uri = "#{@base_uri}/watermark"
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)
          response_stream = RestClient.delete(signed_str_uri, {:content_type => :json, :accept => 'application/json'})
          valid_output = Aspose::Cloud::Common::Utils.validate_output(response_stream)
          valid_output.empty? ? Aspose::Cloud::Common::Utils.download_file(@filename,@filename,folder_name,storage_name,storage_type) : valid_output
        end        

=begin
  Replace a text with the new value in the document
  @param string old_value Old text.
  @param string new_value New text.
  @param boolean is_match_case
  @param boolean is_match_whole_word   
=end
        def replace_text(old_text, new_text, is_match_case = false, is_match_whole_word = false, folder_name = '', storage_type = 'Aspose', storage_name = '')
          raise 'old_text not specified.' if old_text.empty?
          raise 'new_text not specified.' if new_text.nil?

          json_data = {:OldValue => old_text, :NewValue => new_text, :IsMatchCase => is_match_case, :IsMatchWholeWord => is_match_whole_word}.to_json
          str_uri = "#{@base_uri}/replaceText"
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)
          response_stream = RestClient.post(signed_str_uri, json_data, {:content_type => :json, :accept => 'application/json'})
          valid_output = Aspose::Cloud::Common::Utils.validate_output(response_stream)
          valid_output.empty? ? Aspose::Cloud::Common::Utils.download_file(@filename,@filename,folder_name,storage_name,storage_type) : valid_output
        end
      end
    end
  end
end