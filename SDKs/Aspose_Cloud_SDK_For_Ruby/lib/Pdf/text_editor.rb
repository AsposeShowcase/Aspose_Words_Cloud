module Aspose
  module Cloud
    module Pdf
      class TextEditor
        def initialize(filename)
          @filename = filename
          raise 'filename not specified.' if filename.empty?
          @base_uri =  Aspose::Cloud::Common::Product.product_uri + '/pdf/' + @filename
        end

=begin
   Gets raw text from the whole PDF file or a specific page
   @param number page_number [optinal]
=end    
        def get_text(page_number = 0, folder_name='', storage_type = 'Aspose', storage_name = '')
          str_uri = "#{@base_uri}/#{ page_number > 0 ? 'pages/' + page_number.to_s + '/' : '' }textitems"
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)
          response = JSON.parse(RestClient.get(signed_str_uri, {:accept=>'application/json'}))
          output_text = ''
          response['TextItems']['List'].each { |item| output_text.concat(item['Text'])  }
          output_text
        end

=begin
   Gets text items from the whole PDF file or a specific page 
   @param number page_number
=end    
        def get_text_items(page_number, fragment_number, folder_name = '', storage_type = 'Aspose', storage_name = '')
          raise 'page_number not specified.' if page_number.nil?
          raise 'fragment_number not specified.' if fragment_number.nil?
          str_uri = "#{@base_uri}/pages/#{page_number}/fragments/#{fragment_number}"
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)
          JSON.parse(RestClient.get(signed_str_uri, {:accept=>'application/json'}))['TextItems']['List']
        end

=begin
   Gets count of the fragments from a particular page 
   @param number page_number 
=end    
        def get_fragment_count(page_number, folder_name = '', storage_type = 'Aspose', storage_name = '')
          raise 'page_number not specified.' if page_number.nil?
          str_uri = "#{@base_uri}/pages/#{page_number}/fragments"
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)

          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)
          JSON.parse(RestClient.get(signed_str_uri, {:accept=>'application/json'}))['TextItems']['List'].length
        end

=begin
   Gets TextFormat of a particular Fragment
   @param number page_number 
   @param number fragment_number 
=end
        def get_text_format(page_number, fragment_number, folder_name = '', storage_type = 'Aspose', storage_name = '')
          raise 'page_number not specified.' if page_number.nil?
          raise 'fragment_number not specified.' if fragment_number.nil?
          str_uri = "#{@base_uri}/pages/#{page_number}/fragments/#{fragment_number}/textformat"
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)
          JSON.parse(RestClient.get(signed_str_uri, {:accept=>'application/json'}))['TextFormat']
        end

=begin
   Get Text Format of a Particular Segment
   @param number page_number 
   @param number fragment_number
   @param number segment_number
=end
        def get_segment_text_format(page_number, fragment_number, segment_number, folder_name = '', storage_type = 'Aspose', storage_name = '')
          raise 'page_number not specified.' if page_number.nil?
          raise 'fragment_number not specified.' if fragment_number.nil?
          raise 'segment_number not specified.' if segment_number.nil?

          str_uri = "#{@base_uri}/pages/#{page_number}/fragments/#{fragment_number}/segments/#{segment_number}/textformat"
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)
          JSON.parse(RestClient.get(signed_str_uri, {:accept=>'application/json'}))['TextFormat']
        end        

=begin
    Replaces all instances of old text with new text in a PDF file or a particular page
    @param string old_text
    @param string new_text 
=end    
        def replace_text(old_text, new_text, is_regular_expression = false, page_number = 0, folder_name = '', storage_type = 'Aspose', storage_name = '')
          raise 'old_text not specified.' if old_text.empty?
          raise 'new_text not specified.' if new_text.empty?

          json_data = { 'OldValue' => old_text, 'NewValue'=> new_text, 'Regex'=> is_regular_expression }.to_json
          str_uri = page_number > 0 ? "#{@base_uri}/pages/#{page_number}/replaceText" : "#{@base_uri}/replaceText"
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)

          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)
          response_stream = RestClient.post(signed_str_uri,json_data,{ :content_type => 'application/json', :accept => 'application/json' })
          valid_output = Aspose::Cloud::Common::Utils.validate_output(response_stream)
          valid_output.empty? ? true : valid_output
        end

=begin
  Gets count of the segments in a fragment
  @param number pageNumber
  @param number fragmentNumber
=end
        def get_segment_count(page_number, fragment_number, folder_name = '', storage_type = 'Aspose', storage_name = '')
          raise 'page_number not specified.' if page_number.nil?
          raise 'fragment_number not specified.' if fragment_number.nil?

          str_uri = "#{@base_uri}/pages/#{page_number}/fragments/#{fragment_number}"
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)

          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)
          JSON.parse(RestClient.get(signed_str_uri, {:accept=>'application/json'}))['TextItems']['List'].length
        end
      end
    end
  end
end