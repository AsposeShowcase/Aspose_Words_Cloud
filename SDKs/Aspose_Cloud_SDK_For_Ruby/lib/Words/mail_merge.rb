module Aspose
  module Cloud
    module Words
      class MailMerge
        def initialize(filename)
          @filename = filename
          raise 'filename not specified.' if filename.empty?
          @base_uri = "#{Aspose::Cloud::Common::Product.product_uri}/words/#{@filename}"
        end
    
=begin
   Executes mail merge with/without regions.
   @param string filename
   @param string str_xml
=end
        def execute_mail_merge(str_xml, with_regions=false, folder_name = '', storage_type = 'Aspose', storage_name = '')
          raise 'str_xml not specified.' if str_xml.empty?

          str_uri = "#{@base_uri}/executeMailMerge"
          str_uri = "#{str_uri}?withRegions=true" if with_regions
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)
          response_stream = RestClient.post(signed_str_uri, str_xml, {:accept=>'application/json'})

          valid_output = Aspose::Cloud::Common::Utils.validate_output(response_stream)
          valid_output.empty? ? Aspose::Cloud::Common::Utils.download_file(JSON.parse(response_stream)['Document']['FileName'],@filename,folder_name,storage_name,storage_type) : valid_output
        end

=begin
  Executes mail merge with template.
  @param string str_xml
=end
        def execute_template(str_xml, folder_name = '', storage_type = 'Aspose', storage_name = '')
          raise 'str_xml not specified.' if str_xml.empty?

          str_uri = "#{@base_uri}/executeTemplate"
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)
          response_stream = RestClient.post(signed_str_uri, str_xml, {:accept=>'application/json'})

          valid_output = Aspose::Cloud::Common::Utils.validate_output(response_stream)
          valid_output.empty? ? Aspose::Cloud::Common::Utils.download_file(JSON.parse(response_stream)['Document']['FileName'],@filename,folder_name,storage_name,storage_type) : valid_output
        end
      end
    end
  end
end