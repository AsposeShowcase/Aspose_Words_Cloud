module Aspose
  module Cloud
    module Slides
      class Document
        def initialize(filename)
          @filename = filename
          raise 'filename not specified.' if filename.empty?
          @base_uri =  Aspose::Cloud::Common::Product.product_uri + '/slides/' + @filename
        end
=begin
  Add a New Slide in a PowerPoint Presentation
=end
        def add_slide(position, folder_name = '', storage_type = 'Aspose', storage_name = '')
          raise 'position not specified.' if position.nil?

          str_uri = "#{@base_uri}/slides?Position=#{position}"
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)

          response = RestClient.post(signed_str_uri, '', {:accept=>'application/json'})
          json = JSON.parse(response)
          json['Code'] == 200 ? json['Slides'] : nil
        end

=begin
  Create Empty PowerPoint Presentation
=end
        def create_empty_presentation(folder_name = '', storage_type = 'Aspose', storage_name = '')
          str_uri = "#{@base_uri}"
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)

          response = RestClient.put(signed_str_uri, '', {:accept=>'application/json'})
          json = JSON.parse(response)
          json['Code'] == 200 ? json['Document'] : nil
        end

=begin
  Copy Slides in a PowerPoint Presentation
=end
        def clone_slide(slide_number, position, folder_name = '', storage_type = 'Aspose', storage_name = '')
          raise 'slide_number not specified.' if slide_number <= 0
          raise 'position not specified.' if position.nil?

          str_uri = "#{@base_uri}/slides?SlideToClone=#{slide_number}&Position=#{position}"
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)

          response = RestClient.post(signed_str_uri, '', {:accept=>'application/json'})
          json = JSON.parse(response)
          json['Code'] == 200 ? json['Slides'] : nil
        end

=begin
  Change Position of Slides in a PowerPoint Presentation
=end
        def change_slide_position(old_position, new_position, folder_name = '', storage_type = 'Aspose', storage_name = '')
          raise 'old_position not specified.' if old_position.nil?
          raise 'new_position not specified.' if new_position.nil?

          str_uri = "#{@base_uri}/slides?OldPosition=#{old_position}&NewPosition=#{new_position}"
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)

          response = RestClient.post(signed_str_uri, '', {:accept=>'application/json'})
          json = JSON.parse(response)
          json['Code'] == 200 ? json['Slides'] : nil
        end

=begin
  Split PowerPoint Presentations
=end
        def split_presentation(from = '', to = '', destination = '', format = '', folder_name = '', storage_type = 'Aspose', storage_name = '')
          str_uri = "#{@base_uri}/split?"
          str_uri = "#{str_uri}&from=#{from}" unless from.empty?
          str_uri = "#{str_uri}&to=#{to}" unless to.empty?
          str_uri = "#{str_uri}&destFolder=#{destination}" unless destination.empty?
          str_uri = "#{str_uri}&format=#{format}" unless format.empty?
          str_uri = str_uri[0...-1]
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)

          response = RestClient.post(signed_str_uri, '', {:accept=>'application/json'})
          json = JSON.parse(response)
          if json['Code'] == 200
            json['SplitResult']['Slides'].each { |split_page|
              split_filename = File.basename(split_page['Href'])
              Aspose::Cloud::Common::Utils.download_file(split_filename,split_filename,folder_name,storage_name,storage_type)
            }
          end
        end

=begin
  Merge PowerPoint Presentations
  @param xml presentation_list
=end
        def merge_presentations(presentation_list, folder_name = '', storage_type = 'Aspose', storage_name = '')
          raise 'presentation_list not specified.' if presentation_list.empty?

          str_uri = "#{@base_uri}/merge"
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)

          response = RestClient.post(signed_str_uri, presentation_list, {:accept=>'application/json'})
          json = JSON.parse(response)
          json['Code'] == 200 ? json['Document'] : nil
        end

=begin
  Merge Selected Slides of PowerPoint Presentations
  @param xml presentation_list
=end
        def merge_selected_slides(presentation_list, folder_name = '', storage_type = 'Aspose', storage_name = '')
          raise 'presentation_list not specified.' if presentation_list.empty?

          str_uri = "#{@base_uri}/merge"
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)

          response = RestClient.put(signed_str_uri, presentation_list, {:accept=>'application/json'})
          json = JSON.parse(response)
          json['Code'] == 200 ? json['Document'] : nil
        end

=begin
  Finds the slide count of the specified PowerPoint document
=end
        def get_slide_count(folder_name = '', storage_type = 'Aspose', storage_name = '')
          str_uri = "#{@base_uri}/slides"
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)
          JSON.parse(RestClient.get(signed_str_uri, {:accept=>'application/json'}))['Slides']['SlideList'].length
        end

=begin
  Replaces all instances of old text with new text in a presentation or a particular slide
  @param string old_text
  @param string new_text
  @param number slide_number
=end    
        def replace_text(old_text, new_text, slide_number = 0, folder_name = '', storage_type = 'Aspose', storage_name = '')
          raise 'old_text not specified.' if old_text.empty?
          raise 'new_text not specified.' if new_text.empty?

          #str_uri = "#{@base_uri}#{ slide_number > 0 ? '/slides/' + slide_number : '' }/replaceText"
          str_uri = "#{@base_uri}"
          str_uri += "/slides/#{slide_number}" unless slide_number <= 0
          str_uri += "/replaceText"
          qry = Hash.new
          qry[:oldValue] = old_text
          qry[:newValue] = new_text
          str_uri = Aspose::Cloud::Common::Utils.build_uri(str_uri,qry)
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)

          response_stream = RestClient.post(signed_str_uri,'',{:accept=>'application/json'})
          valid_output = Aspose::Cloud::Common::Utils.validate_output(response_stream)
          valid_output.empty? ? Aspose::Cloud::Common::Utils.download_file(@filename,@filename,folder_name,storage_name,storage_type) : valid_output
        end

=begin
  Gets all the text items in a slide or presentation
  @param number slide_number
  @param boolean with_empty
=end
        def get_all_text_items(slide_number=0, with_empty=false, folder_name = '', storage_type = 'Aspose', storage_name = '')          
          str_uri = "#{@base_uri}"
          str_uri += "/slides/#{slide_number}" unless slide_number <= 0
          str_uri += "/textItems?withEmpty=#{with_empty}"          
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)

          JSON.parse(RestClient.get(signed_str_uri, {:accept=>'application/json'}))['TextItems']['Items']
        end

=begin
  Deletes all slides from a presentation
=end
        def delete_all_slides(folder_name = '', storage_type = 'Aspose', storage_name = '')
          str_uri = "#{@base_uri}/slides"
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)

          response_stream = RestClient.delete(signed_str_uri, {:accept=>'application/json'})
          valid_output = Aspose::Cloud::Common::Utils.validate_output(response_stream)
          valid_output.empty? ? Aspose::Cloud::Common::Utils.download_file(@filename,@filename,folder_name,storage_name,storage_type) : valid_output
        end

=begin
  Delete a Slide from a PowerPoint Presentation
=end
        def delete_slide(slide_number, folder_name = '', storage_type = 'Aspose', storage_name = '')
          raise 'slide_number not specified.' if slide_number <= 0

          str_uri = "#{@base_uri}/slides/#{slide_number}"
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)

          response_stream = RestClient.delete(signed_str_uri, {:accept=>'application/json'})
          valid_output = Aspose::Cloud::Common::Utils.validate_output(response_stream)
          valid_output.empty? ? Aspose::Cloud::Common::Utils.download_file(@filename,@filename,folder_name,storage_name,storage_type) : valid_output
        end

=begin
  Delete Background of a PowerPoint Slide
=end
        def delete_background(slide_number, folder_name = '', storage_type = 'Aspose', storage_name = '')
          raise 'slide_number not specified.' if slide_number <= 0

          str_uri = "#{@base_uri}/slides/#{slide_number}/background"
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)

          response_stream = RestClient.delete(signed_str_uri, {:accept=>'application/json'})
          valid_output = Aspose::Cloud::Common::Utils.validate_output(response_stream)
          valid_output.empty? ? Aspose::Cloud::Common::Utils.download_file(@filename,@filename,folder_name,storage_name,storage_type) : valid_output
        end

=begin
  Get Background of a PowerPoint Slide
=end
        def get_background(slide_number, folder_name = '', storage_type = 'Aspose', storage_name = '')
          raise 'slide_number not specified.' if slide_number <= 0

          str_uri = "#{@base_uri}/slides/#{slide_number}/background"
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)

          response = RestClient.get(signed_str_uri, {:accept=>'application/json'})
          json = JSON.parse(response)
          json['Code'] == 200 ? json['Background'] : nil
        end

=begin
   Get Document's properties
=end
        def get_properties(folder_name = '', storage_type = 'Aspose', storage_name = '')
          str_uri = "#{@base_uri}/documentProperties"
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)
          JSON.parse(RestClient.get(signed_str_uri, {:accept=>'application/json'}))['DocumentProperties']['List']
        end

=begin
   Set document property
   @param string property_name
   @param string property_value
=end
        def set_property(property_name, property_value, folder_name = '', storage_type = 'Aspose', storage_name = '')
          raise 'property_name not specified.' if property_name.empty?
          raise 'property_value not specified.' if property_value.empty?

          json_data = JSON.generate('Value'=>property_value)

          str_uri = "#{@base_uri}/documentProperties/#{property_name}"
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)
          JSON.parse(RestClient.put(signed_str_uri, json_data, {:content_type=>:json, :accept=>'application/json'}))['DocumentProperty']
        end

=begin
   Remove All Document's properties
=end
        def remove_all_properties(folder_name = '', storage_type = 'Aspose', storage_name = '')
          str_uri = "#{@base_uri}/documentProperties"
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)
          JSON.parse(RestClient.delete(signed_str_uri, {:accept=>'application/json'}))['Code'] == 200 ? true : false
        end

=begin
   Delete a document property
   @param string property_name
=end
        def delete_property(property_name, folder_name = '', storage_type = 'Aspose', storage_name = '')
          raise 'property_name not specified.' if property_name.empty?

          str_uri = "#{@base_uri}/documentProperties/#{property_name}"
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)
          JSON.parse(RestClient.delete(signed_str_uri, {:accept=>'application/json'}))['Code'] == 200 ? true : false
        end

=begin
     saves the document into various formats    
     @param string outputFilename
     @param string outputFormat
=end
    
        def save_as(output_filename, output_format, folder_name = '', storage_type = 'Aspose', storage_name = '')
          raise 'output_filename not specified.' if output_filename.empty?
          raise 'output_format not specified.' if output_format.empty?

          str_uri = "#{@base_uri}?format=#{output_format}"
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)

          response_stream = RestClient.get(signed_str_uri, {:accept=>'application/json'})
          valid_output = Aspose::Cloud::Common::Utils.validate_output(response_stream)
          output_path = "#{Aspose::Cloud::Common::AsposeApp.output_location}#{Aspose::Cloud::Common::Utils.get_filename(output_filename)}.#{output_format}"
          valid_output.empty? ? Aspose::Cloud::Common::Utils.save_file(response_stream,output_path) : valid_output
        end
    
=begin
  saves the document into various formats         
  @param number slide_number
  @param string outputFilename
  @param string outputFormat
=end
        def save_slide_as(slide_number, output_filename, output_format, folder_name = '', storage_type = 'Aspose', storage_name = '')
          raise 'slide_number not specified.' if slide_number <= 0
          raise 'output_filename not specified.' if output_filename.empty?
          raise 'output_format not specified.' if output_format.empty?

          str_uri = "#{@base_uri}/slides/#{slide_number}?format=#{output_format}"
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)          

          response_stream = RestClient.get(signed_str_uri, {:accept=>'application/json'})
          valid_output = Aspose::Cloud::Common::Utils.validate_output(response_stream)
          output_path = "#{Aspose::Cloud::Common::AsposeApp.output_location}#{Aspose::Cloud::Common::Utils.get_filename(output_filename)}_#{slide_number}.#{output_format}"
          valid_output.empty? ? Aspose::Cloud::Common::Utils.save_file(response_stream,output_path) : valid_output
        end

=begin
  Get Aspect Ratio of a PowerPoint Slide
  @param number slide_number
=end
        def aspect_ratio(slide_number, folder_name = '', storage_type = 'Aspose', storage_name = '')
          raise 'slide_number not specified.' if slide_number <= 0

          str_uri = "#{@base_uri}/slides/#{slide_number}"
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)          

          response = RestClient.get(signed_str_uri, {:accept=>'application/json'})
          json = JSON.parse(response)
          json['Slide']['Width']/json['Slide']['Height']
        end        
      end
    end
  end
end