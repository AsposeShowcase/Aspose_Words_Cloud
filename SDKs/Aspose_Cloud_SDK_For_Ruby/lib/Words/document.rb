module Aspose
  module Cloud
    module Words
      class Document
        def initialize(filename)
          @filename = filename
          raise 'filename not specified.' if filename.empty?
          @base_uri = "#{Aspose::Cloud::Common::Product.product_uri}/words/#{@filename}"
        end

=begin
  Convert Word Documents to any Format with Additional Settings
  @param xml options_xml Provide options in xml format.
=end        
        def save_as(options_xml, folder_name = '', storage_type = 'Aspose', storage_name = '')
          raise 'options not specified.' if options_xml.empty?

          str_uri = "#{@base_uri}/saveAs"
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri, folder_name, storage_name, storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)
          response_stream = RestClient.post(signed_str_uri, options_xml, {:content_type=>'application/xml', :accept=>'application/json'})
          valid_output = Aspose::Cloud::Common::Utils.validate_output(response_stream)

          return valid_output unless valid_output.empty?

          json_response = JSON.parse(response_stream)
          Aspose::Cloud::Common::Utils.download_file(json_response['SaveResult']['DestDocument']['Href'],
                                                     json_response['SaveResult']['DestDocument']['Href'],
                                                     folder_name,storage_name,storage_type)
        end

=begin
  Split pages to new format
  @param number from The start page number for splitting.
  @param number to The last page number for splitting.
  @param string save_format Return file format.
=end
        def split_document(from='', to='', save_format='pdf', folder_name = '', storage_type = 'Aspose', storage_name = '')
          str_uri = "#{@base_uri}/split"
          qry = Hash.new
          qry[:from] = from
          qry[:to] = to
          qry[:format] = save_format

          str_uri = Aspose::Cloud::Common::Utils.build_uri(str_uri, qry)
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)
          
          JSON.parse(RestClient.post(signed_str_uri, nil, {:content_type=>:json, :accept=>'application/json'}))['SplitResult']
        end

=begin
  Extract Page Setup Information from a Word Document
  @param number section_index Index of the section.
=end
        def get_page_setup(section_index, folder_name = '', storage_type = 'Aspose', storage_name = '')
          raise 'section_index not specified.' if section_index.nil?

          str_uri = "#{@base_uri}/sections/#{section_index}/pageSetup"
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)
          JSON.parse(RestClient.get(signed_str_uri, {:accept=>'application/json'}))['PageSetup']
        end

=begin 
  Update Page Setup of a Section in a Word Document 
  @param number section_index Index of the section.
  @param xml options_xml Options to update.
=end
        def update_page_setup(section_index, options_xml, folder_name = '', storage_type = 'Aspose', storage_name = '')
          raise 'section_index not specified.' if section_index.nil?
          raise 'options_xml not specified.' if options_xml.empty?

          str_uri = "#{@base_uri}/sections/#{section_index}/pageSetup"
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)
          JSON.parse(RestClient.post(signed_str_uri, options_xml, {:content_type=>'application/xml', :accept=>'application/json'}))['PageSetup']
        end

=begin
  Appends a list of documents to this one.
  @param string append_docs Name of the file.
  @param import_format_modes Specify formatting modes.
=end
        def append_document(append_docs, import_format_modes, folder_name = '', storage_type = 'Aspose', storage_name = '')
          raise 'append_docs not specified.' if append_docs.empty?
          raise 'import_format_modes not specified.' if import_format_modes.empty?

          docs = Array.new
          append_docs.to_enum.with_index(0).each { |item,i|
            docs.push({ :Href => folder_name.empty? ? item : "#{folder_name}/#{item}", :ImportFormatMode => import_format_modes[i] })
          }
          json_data = JSON.generate({:DocumentEntries => docs})

          str_uri = "#{@base_uri}/appendDocument"
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,'',storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)
          response_stream = RestClient.post(signed_str_uri, json_data, {:content_type=>:json, :accept=>'application/json'})
          valid_output = Aspose::Cloud::Common::Utils.validate_output(response_stream)
          valid_output.empty? ? Aspose::Cloud::Common::Utils.download_file(@filename,@filename,folder_name,storage_name,storage_type) : valid_output
        end

=begin
  Get Resource Properties information like document source format, IsEncrypted, IsSigned and document properties   
=end

        def get_document_info(folder_name = '', storage_type = 'Aspose', storage_name = '')
          str_uri = Aspose::Cloud::Common::Utils.append_storage(@base_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)
          JSON.parse(RestClient.get(signed_str_uri, {:accept => 'application/json'}))['Document']
        end

=begin
   Get Resource Properties information like document source format, IsEncrypted, IsSigned and document properties
   @param string property_name Name of the property.
=end
        def get_property(property_name, folder_name = '', storage_type = 'Aspose', storage_name = '')
          raise 'property_name not specified.' if property_name.empty?

          str_uri = "#{@base_uri}/documentProperties/#{property_name}"
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)
          JSON.parse(RestClient.get(signed_str_uri, {:accept=>'application/json'}))['DocumentProperty']
        end

=begin
   Set document property
   @param string property_name Name of the property.
   @param string property_value Value of the property.
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
  Delete a document property
  @param string property_name Name of the property.
=end
        def delete_property(property_name, folder_name = '', storage_type = 'Aspose', storage_name = '')
          raise 'property_name not specified.' if property_name.empty?

          str_uri = "#{@base_uri}/documentProperties/#{property_name}"
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)
          JSON.parse(RestClient.delete(signed_str_uri, {:accept=>'application/json'}))['Code'] == 200 ? true : false
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
  Get the Current Protection of the Word document
=end
        def get_protection(folder_name = '', storage_type = 'Aspose', storage_name = '')
          str_uri = "#{@base_uri}/protection"
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)
          JSON.parse(RestClient.get(signed_str_uri, {:accept=>'application/json'}))['ProtectionData']['ProtectionType']
        end

=begin
  Protect a Word Document
  @param string password Document protection password.
  @param string protection_type Document protection type.
=end
        def protect_document(password, protection_type = 'AllowOnlyComments', folder_name = '', storage_type = 'Aspose', storage_name = '')
          raise 'password not specified.' if password.empty?

          str_uri = "#{@base_uri}/protection"
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,'',storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)

          data = Hash['Password' => password, 'ProtectionType' => protection_type]
          json_data = JSON.generate(data)

          response_stream = RestClient.put(signed_str_uri, json_data, {:content_type=>:json, :accept=>'application/json'})
          valid_output = Aspose::Cloud::Common::Utils.validate_output(response_stream)
          valid_output.empty? ? Aspose::Cloud::Common::Utils.download_file(@filename,@filename,folder_name,storage_name,storage_type) : valid_output
        end

=begin
  Unprotect a Word Document
  @param string password Document protection password.
  @param string protection_type Document protection type.
=end
        def unprotect_document(password, protection_type, folder_name = '', storage_type = 'Aspose', storage_name = '')
          raise 'password not specified.' if password.empty?
          raise 'protection_type not specified.' if protection_type.empty?

          str_uri = "#{@base_uri}/protection"
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,'',storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)

          data = Hash['Password' => password, 'ProtectionType' => protection_type]
          json_data = JSON.generate(data)

          #response_stream = RestClient.put(signed_str_uri, json_data, {:content_type=>:json, :accept=>'application/json'})
          response_stream = Aspose::Cloud::Common::Utils.process_command(signed_str_uri,'DELETE','JSON',json_data)
          valid_output = Aspose::Cloud::Common::Utils.validate_output(response_stream)
          valid_output.empty? ? Aspose::Cloud::Common::Utils.download_file(@filename,@filename,folder_name,storage_name,storage_type) : valid_output
        end

=begin
  Modify Protection of the Word Document
  @param string old_password Current document protection password.
  @param string new_password New document protection password.
  @param string protection_type Document protection type.
=end
        def update_protection(old_password, new_password, protection_type = 'AllowOnlyComments', folder_name = '', storage_type = 'Aspose', storage_name = '')
          raise 'old_password not specified.' if old_password.empty?
          raise 'new_password not specified.' if new_password.empty?

          str_uri = "#{@base_uri}/protection"
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,'',storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)

          data = Hash['Password' => old_password, 'NewPassword' => new_password, 'ProtectionType' => protection_type]
          json_data = JSON.generate(data)

          response_stream = RestClient.post(signed_str_uri, json_data, {:content_type=>:json, :accept=>'application/json'})
          valid_output = Aspose::Cloud::Common::Utils.validate_output(response_stream)
          valid_output.empty? ? Aspose::Cloud::Common::Utils.download_file(@filename,@filename,folder_name,storage_name,storage_type) : valid_output
        end

=begin
  Get all Hyperlinks from a Word
=end
        def get_hyperlinks(folder_name = '', storage_type = 'Aspose', storage_name = '')
          str_uri = "#{@base_uri}/hyperlinks"
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)
          JSON.parse(RestClient.get(signed_str_uri, {:accept=>'application/json'}))['Hyperlinks']['HyperlinkList']
        end

=begin
  Get a Particular Hyperlink from a Word
  @param number hyperlink_index Index of the hyperlink.
=end
        def get_hyperlink(hyperlink_index, folder_name = '', storage_type = 'Aspose', storage_name = '')
          raise 'Hyperlink index not provided.' if hyperlink_index.nil?

          str_uri = "#{@base_uri}/hyperlinks/#{hyperlink_index}"
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)
          JSON.parse(RestClient.get(signed_str_uri, {:accept=>'application/json'}))['Hyperlink']
        end

=begin
  Get Hyperlinks Count from a Word
=end
        def get_hyperlinks_count(folder_name = '', storage_type = 'Aspose', storage_name = '')
          str_uri = "#{@base_uri}/hyperlinks"
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)
          response = RestClient.get(signed_str_uri, {:accept=>'application/json'})
          json = JSON.parse(response)
          json['Code'] == 200 ? json['Hyperlinks']['HyperlinkList'].count : nil
        end

=begin
  Get all Bookmarks from a Word
=end
        def get_bookmarks(folder_name = '', storage_type = 'Aspose', storage_name = '')
          str_uri = "#{@base_uri}/bookmarks"
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)
          JSON.parse(RestClient.get(signed_str_uri, {:accept=>'application/json'}))['Bookmarks']['BookmarkList']
        end

=begin
  Get a Particular Bookmark from a Word
  @param string bookmark_name Name of the bookmark.
=end
        def get_bookmark(bookmark_name, folder_name = '', storage_type = 'Aspose', storage_name = '')
          raise 'Bookmark name not specified.' if bookmark_name.empty?

          str_uri = "#{@base_uri}/bookmarks/#{bookmark_name}"
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)
          JSON.parse(RestClient.get(signed_str_uri, {:accept=>'application/json'}))['Bookmark']
        end

=begin
  Get Bookmarks Count from a Word
=end
        def get_bookmarks_count(folder_name = '', storage_type = 'Aspose', storage_name = '')
          str_uri = "#{@base_uri}/bookmarks"
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)
          response = RestClient.get(signed_str_uri, {:accept=>'application/json'})
          json = JSON.parse(response)
          json['Code'] == 200 ? json['Bookmarks']['BookmarkList'].count : nil
        end

=begin
  Update Bookmark Text of a Word
  @param string bookmark_name Name of the bookmark.
  @param string bookmark_text New text of the bookmark.
=end
        def update_bookmark(bookmark_name, bookmark_text, folder_name = '', storage_type = 'Aspose', storage_name = '')
          raise 'bookmark_name not specified.' if bookmark_name.empty?
          raise 'bookmark_text not specified.' if bookmark_text.empty?

          str_uri = "#{@base_uri}/bookmarks/#{bookmark_name}"
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)
          
          json_data = JSON.generate({ "Text" => "#{bookmark_text}" })          
          
          response = Aspose::Cloud::Common::Utils.process_command(signed_str_uri,'POST','JSON',json_data)
          json = JSON.parse(response)
          json['Code'] == 200 ? json['Bookmark'] : nil
        end

=begin
  Remove all Headers and Footers
=end
        def delete_headers_footers(folder_name = '', storage_type = 'Aspose', storage_name = '')
          str_uri = "#{@base_uri}/headersFooters"
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)
          JSON.parse(RestClient.delete(signed_str_uri, {:accept=>'application/json'}))['Code'] == 200 ? true : false
        end

=begin
  Accept All Tracking Changes in the Document
=end
        def accept_tracking_changes(folder_name = '', storage_type = 'Aspose', storage_name = '')
          str_uri = "#{@base_uri}/revisions/acceptAll"
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,'',storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)

          response_stream = RestClient.post(signed_str_uri, {:content_type=>:json, :accept=>'application/json'})
          valid_output = Aspose::Cloud::Common::Utils.validate_output(response_stream)
          valid_output.empty? ? Aspose::Cloud::Common::Utils.download_file(@filename,@filename,folder_name,storage_name,storage_type) : valid_output
        end

=begin
  Reject All Tracking Changes in the Document
=end
        def reject_tracking_changes(folder_name = '', storage_type = 'Aspose', storage_name = '')
          str_uri = "#{@base_uri}/revisions/rejectAll"
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,'',storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)

          response_stream = RestClient.post(signed_str_uri, {:content_type=>:json, :accept=>'application/json'})
          valid_output = Aspose::Cloud::Common::Utils.validate_output(response_stream)
          valid_output.empty? ? Aspose::Cloud::Common::Utils.download_file(@filename,@filename,folder_name,storage_name,storage_type) : valid_output
        end

=begin
  Insert Page Number Field into the Document
  @param string alignment Alignment of page number.
  @param string format Specify format.
  @param boolean is_top
  @param boolean set_page_number_on_first_page
=end
        def insert_page_number(alignment, format, is_top = false, set_page_number_on_first_page = false, folder_name = '', storage_type = 'Aspose', storage_name = '')
          raise 'alignment not specified.' if alignment.empty?
          raise 'format not specified.' if format.empty?

          str_uri = "#{@base_uri}/insertPageNumbers"
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,'',storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)

          data = Hash['Format' => format, 'Alignment' => alignment, 'IsTop' => is_top, 'SetPageNumberOnFirstPage' => set_page_number_on_first_page]
          json_data = JSON.generate(data)

          response_stream = RestClient.post(signed_str_uri, json_data, {:content_type=>:json, :accept=>'application/json'})
          valid_output = Aspose::Cloud::Common::Utils.validate_output(response_stream)
          valid_output.empty? ? Aspose::Cloud::Common::Utils.download_file(@filename,@filename,folder_name,storage_name,storage_type) : valid_output
        end

=begin
  Update All Fields in the World Document
=end
        def update_fields(folder_name = '', storage_type = 'Aspose', storage_name = '')
          str_uri = "#{@base_uri}/updateFields"
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)
          response = RestClient.post(signed_str_uri, '', {:accept=>'application/json'})
          json = JSON.parse(response)
          json['Code'] == 200 ? true : false
        end

=begin
  Update Font of a Run in a Word Document
  @param number para_id ID of the paragraph.
  @param number run_index Index of the paragraph run.
  @param string font_name Name of the font.
=end
        def update_paragraph_run_font(para_id, run_index, font_name, folder_name = '', storage_type = 'Aspose', storage_name = '')
          raise 'para_id not specified.' if para_id.nil?
          raise 'run_index not specified.' if run_index.nil?
          raise 'font_name not specified.' if font_name.empty?

          str_uri = "#{@base_uri}/paragraphs/#{para_id}/runs/run_index/font"
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)

          str_xml = "<Font><Name>#{font_name}</Name></Font>"

          response = RestClient.post(signed_str_uri, str_xml, {:accept=>'application/json'})
          json = JSON.parse(response)
          json['Code'] == 200 ? json['Font'] : false
        end        
      end
    end
  end
end
