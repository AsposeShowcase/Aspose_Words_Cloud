module Aspose
  module Cloud
    module Words
      class Extractor
        def initialize(filename)
          @filename = filename
          raise 'filename not specified.' if filename.empty?
          @base_uri = "#{Aspose::Cloud::Common::Product.product_uri}/words/#{@filename}"
        end

=begin
  Get a List of Sections from a Word Document
=end
        def get_sections(folder_name = '', storage_type = 'Aspose', storage_name = '')
          str_uri = "#{@base_uri}/sections"
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)
          JSON.parse(RestClient.get(signed_str_uri, {:accept=>'application/json'}))['Sections']
        end

=begin
  Get a specific Section from a Word Document
  @param number section_id ID of the section.
=end
        def get_section(section_id, folder_name = '', storage_type = 'Aspose', storage_name = '')
          raise 'section_id not specified.' if section_id.nil?

          str_uri = "#{@base_uri}/sections/#{section_id}"
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)
          JSON.parse(RestClient.get(signed_str_uri, {:accept=>'application/json'}))['Section']
        end

=begin
  Get a List of Paragraphs from a Word Document
=end
        def get_paragraphs(folder_name = '', storage_type = 'Aspose', storage_name = '')
          str_uri = "#{@base_uri}/paragraphs"
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)
          JSON.parse(RestClient.get(signed_str_uri, {:accept=>'application/json'}))['Paragraphs']
        end

=begin
  Get a Paragraph from a Word Document
  @param number para_id ID of the paragraph.
=end
        def get_paragraph(para_id, folder_name = '', storage_type = 'Aspose', storage_name = '')
          raise 'para_id not specified.' if para_id.nil?

          str_uri = "#{@base_uri}/paragraphs/#{para_id}"
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)
          JSON.parse(RestClient.get(signed_str_uri, {:accept=>'application/json'}))['Paragraph']
        end

=begin
  Get a specific Run of a Paragraph from a Word Document
  @param number para_id ID of the paragraph.
  @param number run_index Index of the praragraph run.
=end
        def get_paragraph_run(para_id, run_index, folder_name = '', storage_type = 'Aspose', storage_name = '')
          raise 'para_id not specified.' if para_id.nil?
          raise 'run_index not specified.' if run_index.nil?

          str_uri = "#{@base_uri}/paragraphs/#{para_id}/runs/#{run_index}"
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)
          JSON.parse(RestClient.get(signed_str_uri, {:accept=>'application/json'}))['Run']
        end

=begin
  Get Font Information of a Run from a Word Document
  @param number para_id ID of the paragraph.
  @param number run_index Index of the praragraph run.
=end
        def get_paragraph_run_font(para_id, run_index, folder_name = '', storage_type = 'Aspose', storage_name = '')
          raise 'para_id not specified.' if para_id.nil?
          raise 'run_index not specified.' if run_index.nil?

          str_uri = "#{@base_uri}/paragraphs/#{para_id}/runs/#{run_index}/font"
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)
          JSON.parse(RestClient.get(signed_str_uri, {:accept=>'application/json'}))['Font']
        end

=begin
  Get All Merge Field Names from a Word Document
=end
        def get_mail_merge_fields(folder_name = '', storage_type = 'Aspose', storage_name = '')
          str_uri = "#{@base_uri}/mailMergeFieldNames"
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)
          JSON.parse(RestClient.get(signed_str_uri, {:accept=>'application/json'}))['FieldNames']
        end

=begin
  Gets Text items list from document
=end
        def get_text(folder_name = '', storage_type = 'Aspose', storage_name = '')
          str_uri = "#{@base_uri}/textItems"
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)
          JSON.parse(RestClient.get(signed_str_uri, {:accept=>'application/json'}))['TextItems']['List']
        end

=begin
  Get the OLE drawing object from document
  @param number index Index of he OLE object.
  @param string ole_format Return file format.
=end

        def get_ole_data(ole_index, ole_format, folder_name = '', storage_type = 'Aspose', storage_name = '')
          raise 'ole_index not specified.' if ole_index.nil?
          raise 'ole_format not specified.' if ole_format.empty?

          str_uri = "#{@base_uri}/drawingObjects/#{ole_index}/oleData"
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)
          response_stream = RestClient.get(signed_str_uri, {:accept=>'application/json'})

          valid_output = Aspose::Cloud::Common::Utils.validate_output(response_stream)
          output_path = "#{Aspose::Cloud::Common::AsposeApp.output_location}#{Aspose::Cloud::Common::Utils.get_filename(@filename)}_#{ole_index}.#{ole_format}"
          valid_output.empty? ? Aspose::Cloud::Common::Utils.save_file(response_stream,output_path) : valid_output
        end

=begin
  Get the Image drawing object from document
  @param number image_index Index of the image.
  @param string image_format Return image format.
=end
        def get_image_data(image_index, image_format, folder_name = '', storage_type = 'Aspose', storage_name = '')
          raise 'image_index not specified.' if image_index.nil?
          raise 'image_format not specified.' if image_format.empty?

          str_uri = "#{@base_uri}/drawingObjects/#{image_index}/imagedata"
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)
          response_stream = RestClient.get(signed_str_uri, {:accept=>'application/json'})

          valid_output = Aspose::Cloud::Common::Utils.validate_output(response_stream)
          output_path = "#{Aspose::Cloud::Common::AsposeApp.output_location}#{Aspose::Cloud::Common::Utils.get_filename(@filename)}_#{image_index}.#{image_format}"
          valid_output.empty? ? Aspose::Cloud::Common::Utils.save_file(response_stream,output_path) : valid_output
        end

=begin
  Convert drawing object to image
  @param number object_index Index of the object.
  @param string render_format Return file format.
=end
        def convert_drawing_object(object_index, render_format, folder_name = '', storage_type = 'Aspose', storage_name = '')
          raise 'object_index not specified.' if object_index.nil?
          raise 'render_format not specified.' if render_format.empty?

          str_uri = "#{@base_uri}/drawingObjects/#{object_index}?format=#{render_format}"
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)
          response_stream = RestClient.get(signed_str_uri, {:accept=>'application/json'})

          valid_output = Aspose::Cloud::Common::Utils.validate_output(response_stream)
          output_path = "#{Aspose::Cloud::Common::AsposeApp.output_location}#{Aspose::Cloud::Common::Utils.get_filename(@filename)}_#{object_index}.#{render_format}"
          valid_output.empty? ? Aspose::Cloud::Common::Utils.save_file(response_stream,output_path) : valid_output
        end

=begin
  Get the List of drawing object from document	
=end
        def get_drawing_object_list(folder_name = '', storage_type = 'Aspose', storage_name = '')
          str_uri = "#{@base_uri}/drawingObjects"
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)
          JSON.parse(RestClient.get(signed_str_uri, {:accept=>'application/json'}))['DrawingObjects']['List']
        end

=begin
  Get the drawing object from document	
  @param string object_uri URI of the object.
  @param string output_path Path of the output file.
=end

        def get_drawing_object(object_uri, output_path, folder_name = '', storage_type = 'Aspose', storage_name = '')
          raise 'object_uri not specified.' if object_uri.empty?
          raise 'output_path not specified.' if output_path.empty?

          object_index = object_uri[-1]
          str_uri =  "#{Aspose::Cloud::Common::Product.product_uri}/words/#{object_uri}"
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)
          object_info = JSON.parse(RestClient.get(signed_str_uri, {:accept=>'application/json'}))

          str_uri = "#{Aspose::Cloud::Common::Product.product_uri}/words/#{object_uri}?format=jpeg"
          output_path = "#{output_path}/DrawingObject_#{object_index}.jpeg"

          str_uri = "#{Aspose::Cloud::Common::Product.product_uri}/words/#{object_uri}/imageData" unless object_info['DrawingObject']['ImageDataLink'].nil?
          output_path = "#{output_path}/DrawingObject_#{object_index}.jpeg" unless object_info['DrawingObject']['ImageDataLink'].nil?

          str_uri = "#{Aspose::Cloud::Common::Product.product_uri}/words/#{object_uri}/oleData" unless object_info['DrawingObject']['OLEDataLink'].nil?
          output_path = "#{output_path}/DrawingObject_#{object_index}.xlsx" unless object_info['DrawingObject']['OLEDataLink'].nil?

          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)
          response_stream = RestClient.get(signed_str_uri, {:accept=>'application/json'})

          valid_output = Aspose::Cloud::Common::Utils.validate_output(response_stream)
          valid_output.empty? ? Aspose::Cloud::Common::Utils.save_file(response_stream,output_path) : valid_output
        end

=begin
  Get the List of drawing object from document
  @param string output_path Path of the output file.
=end
        def get_drawing_objects(output_path, folder_name = '', storage_type = 'Aspose', storage_name = '')
          raise 'output_path not specified.' if output_path.empty?

          get_drawing_object_list(folder_name,storage_type,storage_name).each{ |obj|
            self.get_drawing_object(obj['link']['Href'],output_path)
          }
        end

=begin
  Get Word and Paragraph Count from Document
=end
        def get_stats(folder_name = '', storage_type = 'Aspose', storage_name = '')
          str_uri = "#{@base_uri}/statistics"
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)
          JSON.parse(RestClient.get(signed_str_uri, {:accept=>'application/json'}))['StatData']
        end
      end
    end
  end
end
