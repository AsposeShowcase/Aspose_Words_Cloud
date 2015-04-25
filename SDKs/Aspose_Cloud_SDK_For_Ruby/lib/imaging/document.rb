module Aspose
  module Cloud
    module Imaging
      class Document
        def initialize(filename)
          @filename = filename
          raise 'filename not specified.' if filename.empty?
          @base_uri = "#{Aspose::Cloud::Common::Product.product_uri}/imaging/#{@filename}"
        end

=begin
   Get Document's properties
=end
        def get_properties(folder_name = '', storage_type = 'Aspose', storage_name = '')
          str_uri = "#{@base_uri}/properties"
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)
          JSON.parse(RestClient.get(signed_str_uri, {:accept=>'application/json'}))
        end

=begin
  Update BMP Image Properties
  @param number bits_per_px Color depth.
  @param number h_resolution New horizontal resolution.
  @param number v_resolution New vertical resolution.
  @param string output_path Path to updated file.
=end
        def update_bmp_properties(bits_per_px, h_resolution, v_resolution, output_path,
                                  folder_name = '', storage_type = 'Aspose', storage_name = '')
          str_uri = "#{@base_uri}/bmp"
          str_uri = Aspose::Cloud::Common::Utils.build_uri(str_uri, {:bitsPerPixel=> bits_per_px,
                                                                     :horizontalResolution=> h_resolution,
                                                                     :verticalResolution=> v_resolution,
                                                                     :outPath=> output_path})
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri, folder_name, storage_name, storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)

          response_stream = RestClient.get(signed_str_uri, {:content_type=>'application/json',
                                                            :accept=>'application/json'})
          valid_output = Aspose::Cloud::Common::Utils.validate_output(response_stream)

          if valid_output.empty?
            output_path = "#{Aspose::Cloud::Common::AsposeApp.output_location}#{Aspose::Cloud::Common::Utils.get_filename(@filename)}_updated.bmp"
            Aspose::Cloud::Common::Utils.save_file(response_stream,output_path)
          end
          valid_output
        end

=begin
  Update BMP Image Properties without Storage
  @param number input_file_path Path of the input file.
  @param number bits_per_px Color depth.
  @param number h_resolution New horizontal resolution.
  @param string v_resolution New vertical resolution.
=end
        def update_bmp_properties_local(input_file_path, bits_per_px, h_resolution, v_resolution,
                                  folder_name = '', storage_type = 'Aspose', storage_name = '')
          str_uri = "#{Aspose::Cloud::Common::Product.product_uri}/imaging/bmp"
          str_uri = Aspose::Cloud::Common::Utils.build_uri(str_uri, {:bitsPerPixel=> bits_per_px,
                                                                     :horizontalResolution=> h_resolution,
                                                                     :verticalResolution=> v_resolution})
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri, folder_name, storage_name, storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)

          response_stream = RestClient.post(signed_str_uri, File.new(input_file_path, 'rb'),
                                            {:content_type=>'application/json', :accept=>'application/json'})
          valid_output = Aspose::Cloud::Common::Utils.validate_output(response_stream)

          if valid_output.empty?
            output_path = "#{Aspose::Cloud::Common::AsposeApp.output_location}#{Aspose::Cloud::Common::Utils.get_filename(input_file_path)}_updated.bmp"
            Aspose::Cloud::Common::Utils.save_file(response_stream,output_path)
          end
          valid_output
        end

=begin
  Update GIF Image Properties
  @param number bg_color_index Index of the background color.
  @param number px_aspect_ratio Pixel aspect ratio. 
  @param boolean interlaced Specifies if image is interlaced.
  @param string output_path Path to updated file.
=end
        def update_gif_properties(bg_color_index, px_aspect_ratio, interlaced, output_path,
                                  folder_name = '', storage_type = 'Aspose', storage_name = '')
          str_uri = "#{@base_uri}/gif"
          str_uri = Aspose::Cloud::Common::Utils.build_uri(str_uri, {:backgroundColorIndex=> bg_color_index,
                                                                     :pixelAspectRatio=> px_aspect_ratio,
                                                                     :interlaced=> interlaced,
                                                                     :outPath=> output_path})
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri, folder_name, storage_name, storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)

          response_stream = RestClient.get(signed_str_uri, {:content_type=>'application/json',
                                                            :accept=>'application/json'})
          valid_output = Aspose::Cloud::Common::Utils.validate_output(response_stream)

          if valid_output.empty?
            output_path = "#{Aspose::Cloud::Common::AsposeApp.output_location}#{Aspose::Cloud::Common::Utils.get_filename(@filename)}_updated.gif"
            Aspose::Cloud::Common::Utils.save_file(response_stream,output_path)
          end
          valid_output
        end

=begin
  Update GIF Image Properties without Storage
  @param string input_file_path Path of the input file.
  @param number bg_color_index Index of the background color.
  @param number px_aspect_ratio Pixel aspect ratio. 
  @param boolean interlaced Specifies if image is interlaced.
=end
        def update_gif_properties_local(input_file_path, bg_color_index, px_aspect_ratio, interlaced,
                                        folder_name = '', storage_type = 'Aspose', storage_name = '')
          str_uri = "#{Aspose::Cloud::Common::Product.product_uri}/imaging/gif"
          str_uri = Aspose::Cloud::Common::Utils.build_uri(str_uri, {:backgroundColorIndex=> bg_color_index,
                                                                     :pixelAspectRatio=> px_aspect_ratio,
                                                                     :interlaced=> interlaced})
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri, folder_name, storage_name, storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)

          response_stream = RestClient.post(signed_str_uri, File.new(input_file_path, 'rb'),
                                            {:content_type=>'application/json', :accept=>'application/json'})
          valid_output = Aspose::Cloud::Common::Utils.validate_output(response_stream)

          if valid_output.empty?
            output_path = "#{Aspose::Cloud::Common::AsposeApp.output_location}#{Aspose::Cloud::Common::Utils.get_filename(input_file_path)}_updated.gif"
            Aspose::Cloud::Common::Utils.save_file(response_stream,output_path)
          end
          valid_output
        end

=begin
  Update JPG Image Properties
  @param number quality Quality of image. From 0 to 100
  @param string compression_type Compression type. 
  @param string output_path Path to updated file.
=end
        def update_jpg_properties(quality, compression_type, output_path,
                                  folder_name = '', storage_type = 'Aspose', storage_name = '')
          str_uri = "#{@base_uri}/jpg"
          str_uri = Aspose::Cloud::Common::Utils.build_uri(str_uri, {:quality=> quality,
                                                                     :compressionType=> compression_type,
                                                                     :outPath=> output_path})
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri, folder_name, storage_name, storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)

          response_stream = RestClient.get(signed_str_uri, {:content_type=>'application/json',
                                                            :accept=>'application/json'})
          valid_output = Aspose::Cloud::Common::Utils.validate_output(response_stream)

          if valid_output.empty?
            output_path = "#{Aspose::Cloud::Common::AsposeApp.output_location}#{Aspose::Cloud::Common::Utils.get_filename(@filename)}_updated.jpg"
            Aspose::Cloud::Common::Utils.save_file(response_stream,output_path)
          end
          valid_output
        end

=begin
  Update JPG Image Properties without Storage
  @param string input_file_path Path of the file.
  @param number quality Quality of image. From 0 to 100
  @param string compression_type Compression type. 
=end
        def update_jpg_properties_local(input_file_path, quality, compression_type,
                                        folder_name = '', storage_type = 'Aspose', storage_name = '')
          str_uri = "#{Aspose::Cloud::Common::Product.product_uri}/imaging/jpg"
          str_uri = Aspose::Cloud::Common::Utils.build_uri(str_uri, {:quality=> quality,
                                                                     :compressionType=> compression_type})
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri, folder_name, storage_name, storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)

          response_stream = RestClient.post(signed_str_uri, File.new(input_file_path, 'rb'),
                                            {:content_type=>'application/json', :accept=>'application/json'})
          valid_output = Aspose::Cloud::Common::Utils.validate_output(response_stream)

          if valid_output.empty?
            output_path = "#{Aspose::Cloud::Common::AsposeApp.output_location}#{Aspose::Cloud::Common::Utils.get_filename(input_file_path)}_updated.jpg"
            Aspose::Cloud::Common::Utils.save_file(response_stream,output_path)
          end
          valid_output
        end

=begin
  Update TIFF Image Properties
  @param string compression Type of the compression.
  @param string resolution_unit Resolution unit: none, inch, centimeter.
  @param number new_width New image width.
  @param number new_height New image height.
  @param number horizontal_resolution Horizontal resolution.
  @param number vertical_resolution Vertical resolution.
  @param string output_path Path to updated file.
=end
        def update_tiff_properties(compression, resolution_unit, new_width, new_height, horizontal_resolution, vertical_resolution, output_path,
                                  folder_name = '', storage_type = 'Aspose', storage_name = '')
          str_uri = "#{@base_uri}/tiff"
          str_uri = Aspose::Cloud::Common::Utils.build_uri(str_uri, {:resolutionUnit=> resolution_unit,
                                                                     :compression=> compression,
                                                                     :newWidth=> new_width,
                                                                     :newHeight=> new_height,
                                                                     :horizontalResolution=> horizontal_resolution,
                                                                     :verticalResolution=> vertical_resolution,
                                                                     :outPath=> output_path})
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri, folder_name, storage_name, storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)

          response_stream = RestClient.post(signed_str_uri, '', {:content_type=>'application/json',
                                                            :accept=>'application/json'})
          valid_output = Aspose::Cloud::Common::Utils.validate_output(response_stream)

          if valid_output.empty?
            output_path = "#{Aspose::Cloud::Common::AsposeApp.output_location}#{Aspose::Cloud::Common::Utils.get_filename(@filename)}_updated.tiff"
            Aspose::Cloud::Common::Utils.save_file(response_stream,output_path)
          end
          valid_output
        end

=begin
  Update TIFF Image Properties without Storage
  @param string input_file_path Path of the input file.
  @param string resolution_unit Resolution unit: none, inch, centimeter.
  @param number new_width New image width.
  @param number new_height New image height.
  @param number horizontal_resolution Horizontal resolution.
  @param number vertical_resolution Vertical resolution.
=end
        def update_tiff_properties_local(input_file_path, resolution_unit, new_width, new_height, horizontal_resolution, vertical_resolution, output_path,
                                         folder_name = '', storage_type = 'Aspose', storage_name = '')
          str_uri = "#{Aspose::Cloud::Common::Product.product_uri}/imaging/tiff"
          str_uri = Aspose::Cloud::Common::Utils.build_uri(str_uri, {:resolutionUnit=> resolution_unit,
                                                                     :newWidth=> new_width,
                                                                     :newHeight=> new_height,
                                                                     :horizontalResolution=> horizontal_resolution,
                                                                     :verticalResolution=> vertical_resolution,
                                                                     :outPath=> output_path})
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri, folder_name, storage_name, storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)

          response_stream = RestClient.post(signed_str_uri, File.new(input_file_path, 'rb'),
                                            {:content_type=>'application/json', :accept=>'application/json'})
          valid_output = Aspose::Cloud::Common::Utils.validate_output(response_stream)

          if valid_output.empty?
            output_path = "#{Aspose::Cloud::Common::AsposeApp.output_location}#{Aspose::Cloud::Common::Utils.get_filename(input_file_path)}_updated.tiff"
            Aspose::Cloud::Common::Utils.save_file(response_stream,output_path)
          end
          valid_output
        end

=begin
  Update PSD Image Properties
  @param number channels_count Count of channels.
  @param string compression_method Compression method.
  @param string output_path Path to updated file.
=end
        def update_psd_properties(channels_count, compression_method, output_path,
                                  folder_name = '', storage_type = 'Aspose', storage_name = '')
          str_uri = "#{@base_uri}/psd"
          str_uri = Aspose::Cloud::Common::Utils.build_uri(str_uri, {:channelsCount=> channels_count,
                                                                     :compressionMethod=> compression_method,
                                                                     :outPath=> output_path})
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri, folder_name, storage_name, storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)
puts signed_str_uri
          response_stream = RestClient.get(signed_str_uri, {:content_type=>'application/json',
                                                            :accept=>'application/json'})
          valid_output = Aspose::Cloud::Common::Utils.validate_output(response_stream)

          if valid_output.empty?
            output_path = "#{Aspose::Cloud::Common::AsposeApp.output_location}#{Aspose::Cloud::Common::Utils.get_filename(@filename)}_updated.psd"
            Aspose::Cloud::Common::Utils.save_file(response_stream,output_path)
          end
          valid_output
        end

=begin
  Update PSD Image Properties without Storage
  @param string input_file_path Path of the input file.
  @param number channels_count Count of channels.
  @param string compression_method Compression method.
=end
        def update_psd_properties_local(input_file_path, channels_count, compression_method,
                                        folder_name = '', storage_type = 'Aspose', storage_name = '')
          str_uri = "#{Aspose::Cloud::Common::Product.product_uri}/imaging/psd"
          str_uri = Aspose::Cloud::Common::Utils.build_uri(str_uri, {:channelsCount=> channels_count,
                                                                     :compressionMethod=> compression_method})
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri, folder_name, storage_name, storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)

          response_stream = RestClient.post(signed_str_uri, File.new(input_file_path, 'rb'),
                                            {:content_type=>'application/json', :accept=>'application/json'})
          valid_output = Aspose::Cloud::Common::Utils.validate_output(response_stream)

          if valid_output.empty?
            output_path = "#{Aspose::Cloud::Common::AsposeApp.output_location}#{Aspose::Cloud::Common::Utils.get_filename(input_file_path)}_updated.psd"
            Aspose::Cloud::Common::Utils.save_file(response_stream,output_path)
          end
          valid_output
        end
      end
    end
  end
end