module Aspose
  module Cloud
    module Tasks
      class Resources
        def initialize(filename)
          @filename = filename
          raise 'filename not specified.' if filename.empty?
          @base_uri =  Aspose::Cloud::Common::Product.product_uri + '/tasks/' + @filename
        end

=begin
 Get all Resources form Project 
=end
        def get_resources(folder_name = '', storage_type = 'Aspose', storage_name = '')
          str_uri = "#{@base_uri}/resources"
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)
          JSON.parse(RestClient.get(signed_str_uri, {:accept=>'application/json'}))['Resources']['ResourceItem']
        end

=begin
 Get a Particular Resource form Project 
 @param number resource_id The id of the resource.
=end
        def get_resource(resource_id, folder_name = '', storage_type = 'Aspose', storage_name = '')
          raise 'resource_id not specified.' if resource_id.nil?

          str_uri = "#{@base_uri}/resources/#{resource_id}"
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)
          JSON.parse(RestClient.get(signed_str_uri, {:accept=>'application/json'}))['Resource']
        end

=begin
 Add a Resource to Project 
 @param number resource_name The Name of the new resource.
 @param number after_resource_id The id of the resource to insert the new resource after.
=end
        def add_resource(resource_name, after_resource_id, folder_name = '', storage_type = 'Aspose', storage_name = '')
          raise 'resource_name not specified.' if resource_name.empty?
          raise 'after_resource_id not specified.' if after_resource_id.nil?

          str_uri = "#{@base_uri}/resources"
          qry = { :resourceName => resource_name, :afterResourceId => after_resource_id }
          str_uri = Aspose::Cloud::Common::Utils.build_uri(str_uri,qry)

          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)

          JSON.parse(RestClient.post(signed_str_uri, '', {:accept=>'application/json'}))['ResourceItem']
        end

=begin
 Delete Resource form Project 
 @param number resource_id The id of the resource.
=end
        def delete_resource(resource_id, folder_name = '', storage_type = 'Aspose', storage_name = '')
          raise 'resource_id not specified.' if resource_id.nil?

          str_uri = "#{@base_uri}/resources/#{resource_id}"
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)
          json = JSON.parse(RestClient.delete(signed_str_uri, {:accept=>'application/json'}))
          json['Code'] == 200 ? true : false
        end
      end
    end
  end
end