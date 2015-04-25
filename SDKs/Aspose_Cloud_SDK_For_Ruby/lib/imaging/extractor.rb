module Aspose
  module Cloud
    module Imaging
      class Extractor
        def initialize(filename)
          @filename = filename
          raise 'filename not specified.' if filename.empty?
          @base_uri = "#{Aspose::Cloud::Common::Product.product_uri}/imaging/#{@filename}"
        end

=begin
  Get TIFF Frame Properties
  @param number frame_id ID of the frame.
=end
        def get_tiff_frame_properties(frame_id, folder_name = '', storage_type = 'Aspose', storage_name = '')
          raise 'frame_id not specified.' if frame_id.nil?

          str_uri = "#{@base_uri}/frames/#{frame_id}/properties"
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)

          response = JSON.parse(RestClient.get(signed_str_uri, {:accept=>'application/json'}))
        end

        def extract_frame(frame_id, output_filename, folder_name = '', storage_type = 'Aspose', storage_name = '')
          raise 'frame_id not specified.' if frame_id.nil?
          raise 'output_filename not specified.' if output_filename.empty?

          str_uri = "#{@base_uri}/frames/#{frame_id}?saveOtherFrames=false&outPath=#{output_filename}"
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)

          response_stream = RestClient.get(signed_str_uri, {:accept=>'application/json'})
          valid_output = Aspose::Cloud::Common::Utils.validate_output(response_stream)

          if valid_output.empty?
            output_path = "#{Aspose::Cloud::Common::AsposeApp.output_location}#{Aspose::Cloud::Common::Utils.get_filename(output_filename)}_updated.tiff"
            Aspose::Cloud::Common::Utils.save_file(response_stream,output_path)
          end
          valid_output
        end

=begin
  Resize a TIFF Frame
  @param number frame_id ID of the frame.
  @param number new_width New width of the frame.
  @param number new_height New height of the frame.
  @param string output_filename Name of the output file.
=end
        def resize_frame(frame_id, new_width, new_height, output_filename, folder_name = '', storage_type = 'Aspose', storage_name = '')
          raise 'frame_id not specified.' if frame_id.nil?
          raise 'new_width not specified.' if new_width.nil?
          raise 'new_height not specified.' if new_height.nil?
          raise 'output_filename not specified.' if output_filename.empty?

          str_uri = "#{@base_uri}/frames/#{frame_id}?saveOtherFrames=false&newWidth=#{new_width}&newHeight=#{new_height}&outPath=#{output_filename}"
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)

          response_stream = RestClient.get(signed_str_uri, {:accept=>'application/json'})
          valid_output = Aspose::Cloud::Common::Utils.validate_output(response_stream)

          if valid_output.empty?
            output_path = "#{Aspose::Cloud::Common::AsposeApp.output_location}#{Aspose::Cloud::Common::Utils.get_filename(output_filename)}_updated.tiff"
            Aspose::Cloud::Common::Utils.save_file(response_stream,output_path)
          end
          valid_output
        end

=begin
  Crop a TIFF Frame
  @param number frame_id ID of the frame.
  @param number x_position X position of start point for cropping rectangle.
  @param number y_position Y position of start point for cropping rectangle.
  @param number rect_width New width of the scaled image.
  @param number rect_height New height of the scaled image.
  @param string output_filename Name of the output file.
=end
        def crop_frame(frame_id, x_position, y_position, rect_width, rect_height, output_filename, folder_name = '', storage_type = 'Aspose', storage_name = '')
          raise 'frame_id not specified.' if frame_id.nil?
          raise 'x_position not specified.' if x_position.nil?
          raise 'y_position not specified.' if y_position.nil?
          raise 'rect_width not specified.' if rect_width.nil?
          raise 'rect_height not specified.' if rect_height.nil?
          raise 'output_filename not specified.' if output_filename.empty?

          str_uri = "#{@base_uri}/frames/#{frame_id}?saveOtherFrames=false&x=#{x_position}&y=#{y_position}&rectWidth=#{rect_width}&rectHeight=#{rect_height}&outPath=#{output_filename}"
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)

          response_stream = RestClient.get(signed_str_uri, {:accept=>'application/json'})
          valid_output = Aspose::Cloud::Common::Utils.validate_output(response_stream)

          if valid_output.empty?
            output_path = "#{Aspose::Cloud::Common::AsposeApp.output_location}#{Aspose::Cloud::Common::Utils.get_filename(output_filename)}_updated.tiff"
            Aspose::Cloud::Common::Utils.save_file(response_stream,output_path)
          end
          valid_output
        end

=begin
  RotateFlip a TIFF Frame
  @param number frame_id ID of the frame.
  @param string rotate_flip_method RotateFlip method.
  @param string output_filename Name of the output file.
=end
        def rotate_frame(frame_id, rotate_flip_method, output_filename, folder_name = '', storage_type = 'Aspose', storage_name = '')
          raise 'frame_id not specified.' if frame_id.nil?
          raise 'rotate_flip_method not specified.' if rotate_flip_method.empty?
          raise 'output_filename not specified.' if output_filename.empty?

          str_uri = "#{@base_uri}/frames/#{frame_id}?saveOtherFrames=false&rotateFlipMethod=#{rotate_flip_method}&outPath=#{output_filename}"
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)

          response_stream = RestClient.get(signed_str_uri, {:accept=>'application/json'})
          valid_output = Aspose::Cloud::Common::Utils.validate_output(response_stream)

          if valid_output.empty?
            output_path = "#{Aspose::Cloud::Common::AsposeApp.output_location}#{Aspose::Cloud::Common::Utils.get_filename(output_filename)}_updated.tiff"
            Aspose::Cloud::Common::Utils.save_file(response_stream,output_path)
          end
          valid_output
        end

=begin
  Manipulate a Frame and Save the Modified Frame Along with Unmodified Frames 
  @param number frame_id ID of the frame.
  @param string rotate_flip_method RotateFlip method.
  @param number rect_width New width of the scaled image.
  @param number rect_height New height of the scaled image.
  @param number x_position X position of start point for cropping rectangle.
  @param number y_position Y position of start point for cropping rectangle.
  @param number rect_width Width of cropping rectangle.
  @param number rect_height Height of cropping rectangle.
  @param string output_filename Name of the output file.
=end
        def manipulate_frame(frame_id, rotate_flip_method, new_width, new_height, x_position, y_position, rect_width, rect_height, output_filename, folder_name = '', storage_type = 'Aspose', storage_name = '')
          raise 'frame_id not specified.' if frame_id.nil?
          raise 'rotate_flip_method not specified.' if rotate_flip_method.empty?
          raise 'new_width not specified.' if new_width.nil?
          raise 'new_height not specified.' if new_height.nil?
          raise 'x_position not specified.' if x_position.nil?
          raise 'y_position not specified.' if y_position.nil?
          raise 'rect_width not specified.' if rect_width.nil?
          raise 'rect_height not specified.' if rect_height.nil?
          raise 'output_filename not specified.' if output_filename.empty?

          str_uri = "#{@base_uri}/frames/#{frame_id}?saveOtherFrames=true&rotateFlipMethod=#{rotate_flip_method}&newWidth=#{new_width}&newHeight=#{new_height}&x=#{x_position}&y=#{x_position}&rectWidth=#{rect_width}&rectHeight=#{rect_height}&outPath=#{output_filename}"
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)

          response_stream = RestClient.get(signed_str_uri, {:accept=>'application/json'})
          valid_output = Aspose::Cloud::Common::Utils.validate_output(response_stream)

          if valid_output.empty?
            output_path = "#{Aspose::Cloud::Common::AsposeApp.output_location}#{Aspose::Cloud::Common::Utils.get_filename(output_filename)}_updated.tiff"
            Aspose::Cloud::Common::Utils.save_file(response_stream,output_path)
          end
          valid_output
        end
      end
    end
  end
end
