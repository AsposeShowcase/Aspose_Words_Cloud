module Aspose
  module Cloud
    module AsposeStorage
      # This class provides functionality to manage files in a Remote Aspose Folder
      class Folder

        def initialize
          @str_uri_folder = Aspose::Cloud::Common::Product.product_uri + '/storage/folder/'
          @str_uri_file = Aspose::Cloud::Common::Product.product_uri + '/storage/file/'
          @str_uri_exist = Aspose::Cloud::Common::Product.product_uri + '/storage/exist/'
          @str_uri_disc = Aspose::Cloud::Common::Product.product_uri + '/storage/disc/'
        end

=begin
  Uploads file from the local path to the remote folder
  @param string localFilePath represents full local file path and name.
=end        		 
        def upload_file(local_file, remote_folder='', storage_type='Aspose', storage_name='')
          raise 'Local file not specified' if local_file.empty?

          filename = File.basename(local_file)
          str_uri = "#{ Aspose::Cloud::Common::Product.product_uri }/storage/file/#{ remote_folder + '/' unless remote_folder.empty? }#{ filename }"
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,'',storage_name,storage_type)

          signeduri = Aspose::Cloud::Common::Utils.sign(str_uri)
          response = JSON.parse(Aspose::Cloud::Common::Utils.upload_file_binary(local_file, signeduri))
          response['Status'].eql? 'OK'
        end

=begin
  Retrieves Files and Folder information from a remote folder
=end        
        def get_files(remote_folder_path='', storage_type='Aspose', storage_name='')
          str_uri = @str_uri_folder + remote_folder_path
          str_uri = str_uri[0..-2] if str_uri[-1].eql? '/'
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,'',storage_name,storage_type)

          signed_uri = Aspose::Cloud::Common::Utils.sign(str_uri)
          response = RestClient.get(signed_uri, :accept => 'application/json')

          JSON.parse(response)['Files']
        end

=begin
  Check if a file exists on the storage
  @param string filename Name of the file.
=end
        def file_exists(filename, storage_type = 'Aspose', storage_name = '')
            raise('Filename cannot be empty') if filename.empty?

            str_uri = @str_uri_exist + filename
            str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,'',storage_name,storage_type)

            signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)

            response_stream = RestClient.get(signed_str_uri, {:accept => 'application/json'})
            JSON.parse(response_stream)['FileExist']['IsExist']
        end

=begin
  Delete a Particular File
  @param string filename Name of the file.
=end
        def delete_file(filename, storage_type = 'Aspose', storage_name = '')
            raise 'File name cannot be empty' if filename.empty?

            str_uri = @str_uri_file + filename
            str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,'',storage_name,storage_type)

            signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)

            response_stream = RestClient.delete(signed_str_uri, {:accept => 'application/json'})
            JSON.parse(response_stream)['Code'].eql? 200
        end

=begin
  Create a New Folder
  @param string folder_name Name of the folder.
=end
        def create_folder (folder_name, storage_type = 'Aspose', storage_name='')
            raise 'Folder name cannot be empty' if folder_name.empty?
            str_uri = @str_uri_folder + folder_name
            str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,'',storage_name,storage_type)

            signed_uri = Aspose::Cloud::Common::Utils.sign(str_uri)
            response = RestClient.put(signed_uri, '', :accept => :json)
            JSON.parse(response)['Code'].eql? 200
        end

=begin
  Delete a Particular Folder
  @param string folder_name Name of the folder.
=end
        def delete_folder (folder_name, storage_type = 'Aspose', storage_name='')
            raise 'Folder name cannot be empty' if folder_name.empty?
            str_uri = @str_uri_folder + folder_name
            str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,'',storage_name,storage_type)

            signed_uri = Aspose::Cloud::Common::Utils.sign(str_uri)
            response = RestClient.delete(signed_uri, :accept => 'application/json')
            JSON.parse(response)['Code'].eql? 200
        end

=begin
  Get Disk Usage
=end
        def get_disc_usage (storage_type = 'Aspose', storage_name = '')
            str_uri = @str_uri_disc
            str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,'',storage_name,storage_type)

            signed_uri = Aspose::Cloud::Common::Utils.sign(str_uri)
            response = RestClient.get(signed_uri, :accept => 'application/json')
            JSON.parse(response)['DiscUsage']
        end

=begin
  Get file from storage
  @param string file_name Name of the file.
=end
        def get_file (file_name, storage_type = 'Aspose', storage_name = '')
            raise 'Filename cannot be empty' if file_name.empty?

            str_uri = @str_uri_file + file_name
            str_uri += append_storage(storage_name) unless storage_type.eql? 'Aspose'
            str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,'',storage_name,storage_type)

            signed_uri = Aspose::Cloud::Common::Utils.sign(str_uri)
            RestClient.get(signed_uri, :accept => 'application/json')
        end
      end #Class Ends Here
    end
  end
end
