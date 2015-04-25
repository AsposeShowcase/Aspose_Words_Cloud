module Aspose
  module Cloud
    module Email
      class Document
        def initialize(filename)
          @filename = filename
          raise 'filename not specified.' if filename.empty?
          @base_uri =  Aspose::Cloud::Common::Product.product_uri + '/email/' + @filename
        end

=begin
  Get property of the email document
  @param string property_name Name of the property.
=end
        def get_property(property_name, folder_name = '', storage_type = 'Aspose', storage_name = '')
          raise 'property_name not specified.' if property_name.empty?

          str_uri = "#{@base_uri}/properties/#{property_name}"
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)
          JSON.parse(RestClient.get(signed_str_uri, {:accept=>'application/json'}))['EmailProperty']['Value']
        end

=begin
  Set property of the email document
  @param string property_name Name of the property.
  @param string property_value Value of the property.
=end
        def set_property(property_name, property_value, folder_name = '', storage_type = 'Aspose', storage_name = '')
          raise 'property_name not specified.' if property_name.empty?
          raise 'property_value not specified.' if property_value.empty?

          json_data = JSON.generate('Value'=>property_value)

          str_uri = "#{@base_uri}/properties/#{property_name}"
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)
          JSON.parse(RestClient.put(signed_str_uri, json_data, {:content_type=>:json, :accept=>'application/json'}))['EmailProperty']['Value']
        end

=begin
  Get attachment from the email document
  @param string attachment_name Name of the attachment.
=end
        def get_attachment(attachment_name, folder_name = '', storage_type = 'Aspose', storage_name = '')
          raise 'attachment_name not specified.' if attachment_name.empty?

          str_uri = "#{@base_uri}/attachments/#{attachment_name}"
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)
          response_stream = RestClient.get(signed_str_uri, {:accept=>'application/json'})
          valid_output = Aspose::Cloud::Common::Utils.validate_output(response_stream)

          if valid_output.empty?
            output_path = "#{Aspose::Cloud::Common::AsposeApp.output_location}#{attachment_name}"
            Aspose::Cloud::Common::Utils.save_file(response_stream,output_path)
          end
          valid_output
        end

=begin
  Add email attachment
  @param string attachment_name Name of the attachment.
=end
        def add_attachment(attachment_name, folder_name = '', storage_type = 'Aspose', storage_name = '')
          raise 'attachment_name not specified.' if attachment_name.empty?

          str_uri = "#{@base_uri}/attachments/#{attachment_name}"
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)
          response_stream = RestClient.post(signed_str_uri, '', {:accept=>'application/json'})
          
          valid_output = Aspose::Cloud::Common::Utils.validate_output(response_stream)
          if valid_output.empty?
             Aspose::Cloud::Common::Utils.download_file(@filename,@filename)
          end
          valid_output
        end
      end
    end
  end
end