module Aspose
  module Cloud
    module Slides
      class Extractor
        def initialize (filename)
          @filename = filename
          raise 'filename not specified.' if filename.empty?
          @base_uri =  Aspose::Cloud::Common::Product.product_uri + '/slides/' + @filename
        end
=begin
  Gets total number of images in a presentation
=end
        def get_image_count(folder_name = '', storage_type = 'Aspose', storage_name = '')
          str_uri = "#{@base_uri}/images"
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)
          JSON.parse(RestClient.get(signed_str_uri, {:accept=>'application/json'}))['Images']['List'].length
        end

=begin
  Gets number of images in the specified slide
	@param number slide_number
=end
        def get_slide_image_count(slide_number, folder_name = '', storage_type = 'Aspose', storage_name = '')
          raise 'slide_number not specified.' if slide_number <= 0

          str_uri = "#{@base_uri}/slides/#{slide_number}/images"
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)
          JSON.parse(RestClient.get(signed_str_uri, {:accept=>'application/json'}))['Images']['List'].length
        end
=begin
  Gets all shapes from the specified slide
	@param number slide_number
=end
        def get_shapes(slide_number, folder_name = '', storage_type = 'Aspose', storage_name = '')
          raise 'slide_number not specified.' if slide_number <= 0

          str_uri = "#{@base_uri}/slides/#{slide_number}/shapes"
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)
          shapes = Array.new
          JSON.parse(RestClient.get(signed_str_uri, {:accept=>'application/json'}))['ShapeList']['ShapesLinks'].each { |item|
            signed_str_uri = Aspose::Cloud::Common::Utils.sign(item['Uri']['Href'])
            shapes.push(JSON.parse(RestClient.get(signed_str_uri, {:accept=>'application/json'})))
          }
          shapes
        end

=begin
  Get a Particular Shape from the Slide
	@param number slide_number
  @param number shape_index
=end
        def get_shape(slide_number, shape_index, folder_name = '', storage_type = 'Aspose', storage_name = '')
          raise 'slide_number not specified.' if slide_number <= 0
          raise 'shape_index not specified.' if shape_index.nil?

          str_uri = "#{@base_uri}/slides/#{slide_number}/shapes/#{shape_index}"
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)

          response = RestClient.get(signed_str_uri, {:accept=>'application/json'})
          json = JSON.parse(response)
          json['Code'] == 200 ? json['Shape'] : nil
        end

=begin
  Get color scheme from the specified slide
	@param number slide_number
=end
        def get_color_scheme(slide_number, folder_name = '', storage_type = 'Aspose', storage_name = '')
          raise 'slide_number not specified.' if slide_number <= 0

          str_uri = "#{@base_uri}/slides/#{slide_number}/theme/colorScheme"
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)
          JSON.parse(RestClient.get(signed_str_uri, {:accept=>'application/json'}))['ColorScheme']
        end
=begin
  Get font scheme from the specified slide
	@param number slide_number
=end
        def get_font_scheme(slide_number, folder_name = '', storage_type = 'Aspose', storage_name = '')
          raise 'slide_number not specified.' if slide_number <= 0

          str_uri = "#{@base_uri}/slides/#{slide_number}/theme/fontScheme"
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)
          JSON.parse(RestClient.get(signed_str_uri, {:accept=>'application/json'}))['FontScheme']
        end

=begin
  Get format scheme from the specified slide
	@param number slide_number
=end
        def get_format_scheme(slide_number, folder_name = '', storage_type = 'Aspose', storage_name = '')
          raise 'slide_number not specified.' if slide_number <= 0

          str_uri = "#{@base_uri}/slides/#{slide_number}/theme/formatScheme"
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)
          JSON.parse(RestClient.get(signed_str_uri, {:accept=>'application/json'}))['FormatScheme']
        end

=begin
  Gets placeholder count from a particular slide
	@param number $slideNumber
=end
        def get_placeholder_count(slide_number, folder_name = '', storage_type = 'Aspose', storage_name = '')
          raise 'slide_number not specified.' if slide_number <= 0

          str_uri = "#{@base_uri}/slides/#{slide_number}/placeholders"
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)
          JSON.parse(RestClient.get(signed_str_uri, {:accept=>'application/json'}))['Placeholders']['PlaceholderLinks'].length
        end

=begin
  Gets placeholder count from a particular slide
	@param number $slideNumber
	@param number $placeholderIndex
=end
        def get_placeholder(slide_number, placeholder_index, folder_name = '', storage_type = 'Aspose', storage_name = '')
          raise 'slide_number not specified.' if slide_number <= 0
          raise 'placeholder_index not specified.' if placeholder_index.nil?

          str_uri = "#{@base_uri}/slides/#{slide_number}/placeholders/#{placeholder_index}"
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)
          JSON.parse(RestClient.get(signed_str_uri, {:accept=>'application/json'}))['Placeholder']
        end

=begin
  Get Comments of a PowerPoint Slide
  @param number slide_number
=end
        def get_comments(slide_number, folder_name = '', storage_type = 'Aspose', storage_name = '')
          raise 'slide_number not specified.' if slide_number <= 0

          str_uri = "#{@base_uri}/slides/#{slide_number}/comments"
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)

          response = RestClient.get(signed_str_uri, {:accept=>'application/json'})
          json = JSON.parse(response)
          json['Code'] == 200 ? json['SlideComments'] : nil
        end
      end
    end
  end
end