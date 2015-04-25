module Aspose
  module Cloud
    module Barcode
      class Builder

=begin
  Create Barcode with different options
  @param string code_text Text to encode inside barcode.
  @param string symbology Type of barcode.
  @param string image_format Returns an image in specified format.
  @param string remote_folder Folder with barcode image.
  @param string x_resolution Resolution along X in dpi.
  @param string y_resolution Resolution along Y in dpi.
  @param string x_dimension Width of barcode unit (bar or space).
  @param string y_dimension Height of barcode unit (for 2D barcodes).
  @param string code_location Location of the code.
  @param string gr_unit Measurement of basic barcode parameters.
  @param string auto_size Sets if barcode's size will be updated automatically.
  @param string bar_height Height of the bar.
  @param string image_height Sets height of the image. 
  @param string image_width Sets width of the image. 
  @param string image_quality Sets quality of the image. 
  @param string rotation_angle Angle of barcode orientation.
  @param string top_margin Margin between barcode and top image border.
  @param string bottom_margin Margin between barcode and bottom image border.
  @param string left_margin Margin between barcode and left image border.
  @param string right_margin Margin between barcode and right image border.
  @param string enable_checksum Sets if checksum will be added to barcode image.
=end       
        def save(code_text, symbology='QR', image_format='png', remote_folder='', storage_type='Aspose', storage_name='', x_resolution=nil, y_resolution=nil, x_dimension=nil, y_dimension=nil, code_location='', gr_unit='', auto_size='', bar_height=nil, image_height=nil, image_width=nil, image_quality=nil, rotation_angle=nil, top_margin=nil, bottom_margin=nil, left_margin=nil, right_margin=nil, enable_checksum='')
          raise 'code_text can not be empty.' if code_text.empty?
          raise 'symbology can not be empty.' if symbology.empty?
          raise 'image_format can not be empty.' if image_format.empty?

          str_uri = "#{Aspose::Cloud::Common::Product.product_uri}/barcode/generate?"
          str_uri = "#{str_uri}text=#{code_text}&type=#{symbology}&format=#{image_format}"
          str_uri = "#{str_uri}&resolutionX=#{x_resolution}" unless x_resolution.nil?
          str_uri = "#{str_uri}&resolutionY=#{y_resolution}" unless y_resolution.nil?
          str_uri = "#{str_uri}&dimensionX=#{x_dimension}" unless x_dimension.nil?
          str_uri = "#{str_uri}&dimensionY=#{y_dimension}" unless y_dimension.nil?          
          str_uri = "#{str_uri}&codeLocation=#{code_location}" unless code_location.empty?
          str_uri = "#{str_uri}&grUnit=#{gr_unit}" unless gr_unit.empty?
          str_uri = "#{str_uri}&barHeight=#{bar_height}" unless bar_height.nil?
          str_uri = "#{str_uri}&imageHeight=#{image_height}" unless image_height.nil?
          str_uri = "#{str_uri}&imageWidth=#{image_width}" unless image_width.nil?
          str_uri = "#{str_uri}&imageQuality=#{image_quality}" unless image_quality.nil?
          str_uri = "#{str_uri}&rotAngle=#{rotation_angle}" unless rotation_angle.nil?
          str_uri = "#{str_uri}&topMargin=#{top_margin}" unless top_margin.nil?
          str_uri = "#{str_uri}&bottomMargin=#{bottom_margin}" unless bottom_margin.nil?
          str_uri = "#{str_uri}&leftMargin=#{left_margin}" unless left_margin.nil?
          str_uri = "#{str_uri}&rightMargin=#{right_margin}" unless right_margin.nil?
          str_uri = "#{str_uri}&enableChecksum=#{enable_checksum}" unless enable_checksum.empty?
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,remote_folder,storage_name,storage_type)          

          signed_uri = Aspose::Cloud::Common::Utils.sign(str_uri)
          response = RestClient.get(signed_uri, :accept => 'application/json')
          output_path = "#{Aspose::Cloud::Common::AsposeApp.output_location}barcode#{symbology}.#{image_format}"
          Aspose::Cloud::Common::Utils.save_file(response, output_path)
          output_path
        end

=begin
  Create Barcode and Get Image as Stream
  @param string code_text Text to encode inside barcode.
  @param string symbology Type of barcode.
  @param string image_format Returns an image in specified format.
  @param string remote_folder Folder with barcode image.
=end

        def generate_barcode_image_stream(code_text, symbology='QR', image_format='png', remote_folder='', storage_type='Aspose', storage_name='')
          raise 'code_text can not be empty.' if code_text.empty?
          raise 'symbology can not be empty.' if symbology.empty?
          raise 'image_format can not be empty.' if image_format.empty?

          str_uri = "#{Aspose::Cloud::Common::Product.product_uri}/barcode/generate?"
          str_uri = "#{str_uri}text=#{code_text}&type=#{symbology}&format=#{image_format}"          
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,remote_folder,storage_name,storage_type)

          signed_uri = Aspose::Cloud::Common::Utils.sign(str_uri)
          response_stream = RestClient.get(signed_uri, :accept => 'application/json')
        end

=begin
  Generate Barcode with Appropriate Code Text Location
  @param string code_text Text to encode inside barcode.
  @param string symbology Type of barcode.
  @param string image_format Returns an image in specified format.
  @param string code_location Location of the code.
  @param string remote_folder Folder with barcode image.
=end
        def set_barcode_code_location(code_text, code_location, symbology='QR', image_format='png', remote_folder='', storage_type='Aspose', storage_name='')
          raise 'code_text can not be empty.' if code_text.empty?
          raise 'symbology can not be empty.' if symbology.empty?
          raise 'image_format can not be empty.' if image_format.empty?
          raise 'code_location can not be empty.' if code_location.empty?

          str_uri = "#{Aspose::Cloud::Common::Product.product_uri}/barcode/generate?"
          str_uri = "#{str_uri}text=#{code_text}&type=#{symbology}&format=#{image_format}&codeLocation=#{code_location}"                    
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,remote_folder,storage_name,storage_type)          
          signed_uri = Aspose::Cloud::Common::Utils.sign(str_uri)
          response = RestClient.get(signed_uri, :accept => 'application/json')

          output_path = "#{Aspose::Cloud::Common::AsposeApp.output_location}barcode#{symbology}.#{image_format}"
          Aspose::Cloud::Common::Utils.save_file(response, output_path)          
        end
=begin
  Generate Barcode with Checksum Option
  @param string code_text Text to encode inside barcode.
  @param string symbology Type of barcode.
  @param string image_format Returns an image in specified format.
  @param string checksum Sets if checksum will be added to barcode image.
  @param string remote_folder Folder with barcode image.
=end
        def set_barcode_checksum(code_text, checksum, symbology='QR', image_format='png', remote_folder='', storage_type='Aspose', storage_name='')
          raise 'code_text can not be empty.' if code_text.empty?
          raise 'symbology can not be empty.' if symbology.empty?
          raise 'image_format can not be empty.' if image_format.empty?
          raise 'checksum can not be empty.' if checksum.empty?

          str_uri = "#{Aspose::Cloud::Common::Product.product_uri}/barcode/generate?"
          str_uri = "#{str_uri}text=#{code_text}&type=#{symbology}&format=#{image_format}&enableChecksum=#{checksum}"          
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,remote_folder,storage_name,storage_type)
          signed_uri = Aspose::Cloud::Common::Utils.sign(str_uri)

          response = RestClient.get(signed_uri, :accept => 'application/json')
          output_path = "#{Aspose::Cloud::Common::AsposeApp.output_location}barcode#{symbology}.#{image_format}"
          Aspose::Cloud::Common::Utils.save_file(response, output_path)          
        end

=begin
  Set Barcode Image Height, Width and Quality along with Auto Size Option
  @param string code_text Text to encode inside barcode.
  @param string symbology Type of barcode.
  @param string image_format Returns an image in specified format.
  @param string image_height Sets height of the image. 
  @param string image_width Sets width of the image. 
  @param string image_quality Sets quality of the image. 
  @param string rotation_angle Angle of barcode orientation.
  @param string auto_size Sets if barcode's size will be updated automatically.
  @param string remote_folder Folder with barcode image.
=end
        def set_barcode_height_width(code_text, height, width, quality, auto_size, symbology='QR', image_format='png', remote_folder='', storage_type='Aspose', storage_name='')
          raise 'code_text can not be empty.' if code_text.empty?
          raise 'symbology can not be empty.' if symbology.empty?
          raise 'image_format can not be empty.' if image_format.empty?
          raise 'height can not be empty.' if height.nil?
          raise 'width can not be empty.' if width.nil?
          raise 'quality can not be empty.' if quality.nil?
          raise 'auto_size can not be empty.' if auto_size.nil?

          str_uri = "#{Aspose::Cloud::Common::Product.product_uri}/barcode/generate?"
          str_uri = "#{str_uri}text=#{code_text}&type=#{symbology}&format=#{image_format}&imageHeight=#{height}&imageWidth=#{width}&imageQuality=#{quality}&autoSize=#{auto_size}"          
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,remote_folder,storage_name,storage_type)
          signed_uri = Aspose::Cloud::Common::Utils.sign(str_uri)

          response = RestClient.get(signed_uri, :accept => 'application/json')
          output_path = "#{Aspose::Cloud::Common::AsposeApp.output_location}barcode#{symbology}.#{image_format}"
          Aspose::Cloud::Common::Utils.save_file(response, output_path)          
        end        

=begin
  Rotate Barcode Image with Suitable Angle
  @param string code_text Text to encode inside barcode.
  @param string symbology Type of barcode.
  @param string image_format Returns an image in specified format.
  @param number rotation_angle Angle of barcode orientation.
  @param string remote_folder Folder with barcode image.
=end
        def set_barcode_angle(code_text, rotation_angle, symbology='QR', image_format='png', remote_folder='', storage_type='Aspose', storage_name='')
          raise 'code_text can not be empty.' if code_text.empty?
          raise 'symbology can not be empty.' if symbology.empty?
          raise 'image_format can not be empty.' if image_format.empty?
          raise 'rotation_angle can not be empty.' if rotation_angle.nil?

          str_uri = "#{Aspose::Cloud::Common::Product.product_uri}/barcode/generate?"
          str_uri = "#{str_uri}text=#{code_text}&type=#{symbology}&format=#{image_format}&rotAngle=#{rotation_angle}"          
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,remote_folder,storage_name,storage_type)
          signed_uri = Aspose::Cloud::Common::Utils.sign(str_uri)

          response = RestClient.get(signed_uri, :accept => 'application/json')
          output_path = "#{Aspose::Cloud::Common::AsposeApp.output_location}barcode#{symbology}.#{image_format}"
          Aspose::Cloud::Common::Utils.save_file(response, output_path)          
        end

=begin
  Set Barcode Image Margin
  @param string code_text Text to encode inside barcode.
  @param string symbology Type of barcode.
  @param string image_format Returns an image in specified format.
  @param string top_margin Margin between barcode and top image border.
  @param string bottom_margin Margin between barcode and bottom image border.
  @param string left_margin Margin between barcode and left image border.
  @param string right_margin Margin between barcode and right image border.
  @param string remote_folder Folder with barcode image.
=end
        def set_barcode_margin(code_text, top_margin, bottom_margin, left_margin, right_margin, symbology='QR', image_format='png', remote_folder='', storage_type='Aspose', storage_name='')
          raise 'code_text can not be empty.' if code_text.empty?
          raise 'symbology can not be empty.' if symbology.empty?
          raise 'image_format can not be empty.' if image_format.empty?
          raise 'top_margin can not be empty.' if top_margin.nil?
          raise 'bottom_margin can not be empty.' if bottom_margin.nil?
          raise 'left_margin can not be empty.' if left_margin.nil?
          raise 'right_margin can not be empty.' if right_margin.nil?

          str_uri = "#{Aspose::Cloud::Common::Product.product_uri}/barcode/generate?"
          str_uri = "#{str_uri}text=#{code_text}&type=#{symbology}&format=#{image_format}&topMargin=#{top_margin}&bottomMargin=#{bottom_margin}&leftMargin=#{left_margin}&rightMargin=#{right_margin}"          
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,remote_folder,storage_name,storage_type)
          signed_uri = Aspose::Cloud::Common::Utils.sign(str_uri)

          response_stream = RestClient.get(signed_uri, :accept => 'application/json')
          output_path = "#{Aspose::Cloud::Common::AsposeApp.output_location}barcode#{symbology}.#{image_format}"
          Aspose::Cloud::Common::Utils.save_file(response_stream, output_path)          
        end

=begin
  Set Barcode Image Resolution 
  @param string code_text Text to encode inside barcode.
  @param string symbology Type of barcode.
  @param string image_format Returns an image in specified format.
  @param string x_resolution Resolution along X in dpi.
  @param string y_resolution Resolution along Y in dpi.
  @param string remote_folder Folder with barcode image.
=end
        def set_barcode_resolution(code_text, x_resolution, y_resolution, symbology='QR', image_format='png', remote_folder='', storage_type='Aspose', storage_name='')
          raise 'code_text can not be empty.' if code_text.empty?
          raise 'symbology can not be empty.' if symbology.empty?
          raise 'image_format can not be empty.' if image_format.empty?
          raise 'x_resolution can not be empty.' if x_resolution.nil?
          raise 'y_resolution can not be empty.' if y_resolution.nil?

          str_uri = "#{Aspose::Cloud::Common::Product.product_uri}/barcode/generate?"
          str_uri = "#{str_uri}text=#{code_text}&type=#{symbology}&format=#{image_format}&resolutionX=#{x_resolution}&resolutionY=#{y_resolution}"          
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,remote_folder,storage_name,storage_type)
          signed_uri = Aspose::Cloud::Common::Utils.sign(str_uri)

          response_stream = RestClient.get(signed_uri, :accept => 'application/json')
          output_path = "#{Aspose::Cloud::Common::AsposeApp.output_location}barcode#{symbology}.#{image_format}"
          Aspose::Cloud::Common::Utils.save_file(response_stream, output_path)          
        end

=begin
  Set Height of the Bars in the Barcode Image
  @param string code_text Text to encode inside barcode.
  @param string symbology Type of barcode.
  @param string image_format Returns an image in specified format.
  @param number height Height of the bar.
  @param string gr_unit Measurement of basic barcode parameters.
  @param string remote_folder Folder with barcode image.
=end
        def set_barcode_height(code_text, height, gr_unit, symbology='QR', image_format='png', remote_folder='', storage_type='Aspose', storage_name='')
          raise 'code_text can not be empty.' if code_text.empty?
          raise 'symbology can not be empty.' if symbology.empty?
          raise 'image_format can not be empty.' if image_format.empty?
          raise 'height can not be empty.' if height.nil?
          raise 'gr_unit can not be empty.' if gr_unit.empty?

          str_uri = "#{Aspose::Cloud::Common::Product.product_uri}/barcode/generate?"
          str_uri = "#{str_uri}text=#{code_text}&type=#{symbology}&format=#{image_format}&barHeight=#{height}&grUnit=#{gr_unit}"          
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,remote_folder,storage_name,storage_type)
          signed_uri = Aspose::Cloud::Common::Utils.sign(str_uri)

          response_stream = RestClient.get(signed_uri, :accept => 'application/json')
          output_path = "#{Aspose::Cloud::Common::AsposeApp.output_location}barcode#{symbology}.#{image_format}"
          Aspose::Cloud::Common::Utils.save_file(response_stream, output_path)          
        end

=begin
  Set X and Y Dimensions of a Barcode
  @param string code_text Text to encode inside barcode.
  @param string symbology Type of barcode.
  @param string image_format Returns an image in specified format.
  @param number x_dimension Width of barcode unit (bar or space).
  @param number y_dimension Height of barcode unit (for 2D barcodes).
  @param string remote_folder Folder with barcode image.
=end
        def set_barcode_dimensions(code_text, x_dimension, y_dimension, symbology='QR', image_format='png', remote_folder='', storage_type='Aspose', storage_name='')
          raise 'code_text can not be empty.' if code_text.empty?
          raise 'symbology can not be empty.' if symbology.empty?
          raise 'image_format can not be empty.' if image_format.empty?
          raise 'x_dimension can not be empty.' if x_dimension.nil?
          raise 'y_dimension can not be empty.' if y_dimension.nil?

          str_uri = "#{Aspose::Cloud::Common::Product.product_uri}/barcode/generate?"
          str_uri = "#{str_uri}text=#{code_text}&type=#{symbology}&format=#{image_format}&dimensionX=#{x_dimension}&dimensionY=#{y_dimension}"          
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,remote_folder,storage_name,storage_type)
          signed_uri = Aspose::Cloud::Common::Utils.sign(str_uri)

          response_stream = RestClient.get(signed_uri, :accept => 'application/json')
          output_path = "#{Aspose::Cloud::Common::AsposeApp.output_location}barcode#{symbology}.#{image_format}"
          Aspose::Cloud::Common::Utils.save_file(response_stream, output_path)          
        end        
      end
    end
  end
end