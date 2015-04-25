module Aspose
  module Cloud
    module Pdf
      class AnnotationEditor
        def initialize(filename)
          @filename = filename
          raise 'filename not specified.' if filename.empty?
          @base_uri =  Aspose::Cloud::Common::Product.product_uri + '/pdf/' + @filename
        end
=begin
   Gets number of annotations on a specified document page
   @param number page_number
=end
        def get_annotations_count(page_number, folder_name='', storage_type = 'Aspose', storage_name = '')
          raise 'page_number not specified.' if page_number.nil?

          str_uri = "#{@base_uri}/pages/#{page_number}/annotations"
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)

          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)
          JSON.parse(RestClient.get(signed_str_uri, {:accept=>'application/json'}))['Annotations']['List'].length
        end
    
=begin
   Gets a specfied annotation on a specified document page	   
   @param number page_number
   @param number annotation_index
=end
        def get_annotation(page_number, annotation_index, folder_name='', storage_type = 'Aspose', storage_name = '')
          raise 'page_number not specified.' if page_number.nil?
          raise 'annotation_index not specified.' if annotation_index.nil?

          str_uri = "#{@base_uri}/pages/#{page_number}/annotations/#{annotation_index}"
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)

          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)
          JSON.parse(RestClient.get(signed_str_uri, {:accept=>'application/json'}))['Annotation']
        end

=begin
   Gets list of all the annotations on a specified document page	   
   @param number page_number  
=end
        def get_all_annotation(page_number, folder_name='', storage_type = 'Aspose', storage_name = '')
          raise 'page_number not specified.' if page_number.nil?

          annotations = Array.new
          total_annotations = self.get_annotations_count(page_number,folder_name,storage_type,storage_name)
          total_annotations.times do |i|
            annotations.push(self.get_annotation(page_number,i+1,folder_name,storage_type,storage_name))
          end
          annotations
        end

=begin
   Gets total number of Bookmarks in a Pdf document   
=end
        def get_bookmarks_count(folder_name='', storage_type = 'Aspose', storage_name = '')
          str_uri = "#{@base_uri}/bookmarks"
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)

          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)
          JSON.parse(RestClient.get(signed_str_uri, {:accept=>'application/json'}))['Bookmarks']['List'].length
        end

=begin
   Gets number of child bookmarks in a specfied parent bookmark   
   @param number parent
=end
    
        def get_child_bookmarks_count(parent, folder_name='', storage_type = 'Aspose', storage_name = '')
          raise 'parent not specified.' if parent.nil?

          str_uri = "#{@base_uri}/bookmarks/#{parent}/bookmarks"
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)

          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)
          JSON.parse(RestClient.get(signed_str_uri, {:accept=>'application/json'}))['Bookmarks']['List'].length
        end

=begin
   Gets a specfied Bookmark from a PDF document
   @param number bookmark_index
=end
    
        def get_bookmark(bookmark_index, folder_name='', storage_type = 'Aspose', storage_name = '')
          raise 'bookmark_index not specified.' if bookmark_index.nil?

          str_uri = "#{@base_uri}/bookmarks/#{bookmark_index}"
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)

          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)
          JSON.parse(RestClient.get(signed_str_uri, {:accept=>'application/json'}))['Bookmark']
        end

=begin
   Gets a specfied Child Bookmark from a PDF document
   @param number parent_index
   @param number child_index
=end
        def get_child_bookmark(parent_index, child_index, folder_name='', storage_type = 'Aspose', storage_name = '')
          raise 'parent_index not specified.' if parent_index.nil?
          raise 'child_index not specified.' if child_index.nil?

          str_uri = "#{@base_uri}/bookmarks/#{parent_index}/bookmarks/#{child_index}"
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)

          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)
          JSON.parse(RestClient.get(signed_str_uri, {:accept=>'application/json'}))['Bookmark']
        end

=begin
   Gets list of all the bookmarks in pdf     
=end
        def get_all_bookmarks(folder_name='', storage_type = 'Aspose', storage_name = '')
          bookmarks = Array.new
          total_bookmarks = self.get_bookmarks_count(folder_name,storage_type,storage_name)
          total_bookmarks.times do |i|
            bookmarks.push(self.get_bookmark(i+1,folder_name,storage_type,storage_name))
          end
          bookmarks
        end

=begin
   Gets total number of Attachments in a Pdf document   
=end
        def get_attachments_count(folder_name='', storage_type = 'Aspose', storage_name = '')
          str_uri = "#{@base_uri}/attachments"
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)

          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)
          JSON.parse(RestClient.get(signed_str_uri, {:accept=>'application/json'}))['Attachments']['List'].length
        end

=begin
   Gets a specfied Attachment from a PDF document
   @param number attachment_index
=end
        def get_attachment(attachment_index, folder_name='', storage_type = 'Aspose', storage_name = '')
          raise 'attachment_index not specified.' if attachment_index.nil?

          str_uri = "#{@base_uri}/attachments/#{attachment_index}"
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)

          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)
          JSON.parse(RestClient.get(signed_str_uri, {:accept=>'application/json'}))['Attachment']
        end

=begin
   Gets list of all the attachments in pdf     
=end
        def get_all_attachments(folder_name='', storage_type = 'Aspose', storage_name = '')
          attachments = Array.new
          total_attachments = self.get_attachments_count(folder_name,storage_type,storage_name)
          total_attachments.times do |i|
            attachments.push(self.get_attachment(i+1,folder_name,storage_type,storage_name))
          end
          attachments
        end

=begin
   Download a specfied Attachment from a PDF document
   @param number attachment_index
=end
        def download_attachment(attachment_index, folder_name='', storage_type = 'Aspose', storage_name = '')
          raise 'attachment_index not specified.' if attachment_index.nil?
          attachment = self.get_attachment(attachment_index,folder_name,storage_type,storage_name)

          str_uri = "#{@base_uri}/attachments/#{attachment_index}/download"
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)

          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)
          response_stream = RestClient.get(signed_str_uri, {:accept=>'application/json'})

          valid_output = Aspose::Cloud::Common::Utils.validate_output(response_stream)

          if valid_output.empty?
            output_path = "#{Aspose::Cloud::Common::AsposeApp.output_location}#{attachment['Name']}"
            Aspose::Cloud::Common::Utils.save_file(response_stream,output_path)
          end
          valid_output
        end

=begin
   Gets total number of Links in a Pdf document
   @param number page_number
=end

        def get_links_count(page_number, folder_name='', storage_type = 'Aspose', storage_name = '')
          raise 'page_number not specified.' if page_number.nil?

          str_uri = "#{@base_uri}/pages/#{page_number}/links"
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)

          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)
          JSON.parse(RestClient.get(signed_str_uri, {:accept=>'application/json'}))['Links']['List'].length
        end

=begin
   Gets a specfied link on a specified document page	   
   @param number page_number
   @param number annotation_index
=end
        def get_link(page_number, link_index, folder_name='', storage_type = 'Aspose', storage_name = '')
          raise 'page_number not specified.' if page_number.nil?
          raise 'link_index not specified.' if link_index.nil?

          str_uri = "#{@base_uri}/pages/#{page_number}/links/#{link_index}"
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)

          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)
          JSON.parse(RestClient.get(signed_str_uri, {:accept=>'application/json'}))['Link']
        end

=begin
   Gets list of all the links on a specified document page	   
   @param number page_number  
=end
        def get_all_links(page_number, folder_name='', storage_type = 'Aspose', storage_name = '')
          raise 'page_number not specified.' if page_number.nil?

          links = Array.new
          total_links = self.get_links_count(page_number,folder_name,storage_type,storage_name)
          total_links.times do |i|
            links.push(self.get_link(page_number,i+1,folder_name,storage_type,storage_name))
          end
          links
        end

=begin
   Checks whether selected bookmark is parent or child Gets a specfied child Bookmark for selected parent bookmark in Pdf document   
   @param number bookmark_index
=end
        def is_child_bookmark(bookmark_index, folder_name='', storage_type = 'Aspose', storage_name = '')
          raise 'bookmark_index not specified.' if bookmark_index.nil?

          str_uri = "#{@base_uri}/bookmarks/#{bookmark_index}"
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)

          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)
          JSON.parse(RestClient.get(signed_str_uri, {:accept=>'application/json'}))['Bookmark']
        end
      end
    end
  end
end