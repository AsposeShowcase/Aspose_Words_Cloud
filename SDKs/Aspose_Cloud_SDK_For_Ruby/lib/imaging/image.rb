module Aspose
  module Cloud
    module Imaging
      class Image
        def initialize(filename)
          @filename = filename
          raise 'filename not specified.' if filename.empty?
          @base_uri = "#{Aspose::Cloud::Common::Product.product_uri}/imaging/#{@filename}"
        end

=begin
  Resize Image without Storage
  @param string input_file_path Path of the input file.
  @param number new_width New width of the image.
  @param number new_height New height of the image.
  @param string output_filename Name of the output file.
  @param string save_format Output file format.
=end
        def resize_image(input_file_path, new_width, new_height, output_filename, save_format)
          raise 'input_file_path not specified.' if input_file_path.empty?
          raise 'output_filename not specified.' if output_filename.empty?
          raise 'save_format not specified.' if save_format.empty?

          str_uri = "#{Aspose::Cloud::Common::Product.product_uri}/imaging/resize"
          str_uri = Aspose::Cloud::Common::Utils.build_uri(str_uri, {:newWidth=> new_width,
                                                                     :newHeight=> new_height, :format=> save_format})
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)

          response_stream = RestClient.post(signed_str_uri, File.new(input_file_path, 'rb'), {:accept=>'application/json'})

          valid_output = Aspose::Cloud::Common::Utils.validate_output(response_stream)

          if valid_output.empty?
            save_format = 'zip' if save_format.eql?('html')
            output_path = "#{Aspose::Cloud::Common::AsposeApp.output_location}#{Aspose::Cloud::Common::Utils.get_filename(output_filename)}.#{save_format}"
            Aspose::Cloud::Common::Utils.save_file(response_stream,output_path)
          end
          valid_output
        end

=begin
  Crop Image with Format Change
  @param number x X position of start point for cropping rectangle.
  @param number y Y position of start point for cropping rectangle.
  @param number width New width of the image.
  @param number height New height of the image.
  @param string output_path Name of the output file.
  @param string save_format Output file format.
=end
        def crop_image(x, y, width, height, output_path, save_format)
          raise 'output_path not specified.' if output_path.empty?
          raise 'save_format not specified.' if save_format.empty?

          str_uri = "#{Aspose::Cloud::Common::Product.product_uri}/imaging/#{@filename}/crop"
          str_uri = Aspose::Cloud::Common::Utils.build_uri(str_uri, {:width=> width,
                                                                     :height=> height,
                                                                     :x=> x,
                                                                     :y=> y,
                                                                     :outputPath=> output_path,
                                                                     :format=> save_format})
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)

          response_stream = RestClient.get(signed_str_uri, {:accept=>'application/json'})

          valid_output = Aspose::Cloud::Common::Utils.validate_output(response_stream)

          if valid_output.empty?
            save_format = 'zip' if save_format.eql?('html')
            output_path = "#{Aspose::Cloud::Common::AsposeApp.output_location}#{Aspose::Cloud::Common::Utils.get_filename(@filename)}.#{save_format}"
            Aspose::Cloud::Common::Utils.save_file(response_stream,output_path)
          end
          valid_output
        end

=begin
  RotateFlip Image on Storage
  @param string method RotateFlip method.
  @param string output_path Name of the output file.
  @param string save_format Output file format.
=end
        def rotate_image(method, output_path, save_format)

          str_uri = "#{Aspose::Cloud::Common::Product.product_uri}/imaging/#{@filename}/rotateflip"
          str_uri = Aspose::Cloud::Common::Utils.build_uri(str_uri, {:method=> method,
                                                                     :outputPath=> output_path,
                                                                     :format=> save_format})
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)

          response_stream = RestClient.get(signed_str_uri, {:accept=>'application/json'})

          valid_output = Aspose::Cloud::Common::Utils.validate_output(response_stream)

          if valid_output.empty?
            save_format = 'zip' if save_format.eql?('html')
            output_path = "#{Aspose::Cloud::Common::AsposeApp.output_location}#{Aspose::Cloud::Common::Utils.get_filename(@filename)}.#{save_format}"
            Aspose::Cloud::Common::Utils.save_file(response_stream,output_path)
          end
          valid_output
        end

=begin
  Merge Tiff Images 
  @param string append_file Name of the file.
=end
        def append_tiff(append_file)
          raise 'append_file not specified.' if append_file.empty?

          str_uri = "#{Aspose::Cloud::Common::Product.product_uri}/imaging/tiff/#{@filename}/appendTiff?appendFile=#{append_file}"
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)

          response_stream = RestClient.post(signed_str_uri, '', {:accept=>'application/json'})
          json = JSON.parse(response_stream)
          json['Code'] == 200 ? Aspose::Cloud::Common::Utils.download_file(@filename,@filename) : nil
        end
      end
    end
  end
end