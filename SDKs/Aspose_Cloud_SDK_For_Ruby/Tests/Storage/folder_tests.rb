require 'test/unit'
require_relative '../../lib/asposecloud'

class FolderTests < Test::Unit::TestCase

  def setup
    file = File.read('../setup.json')
    data = JSON.parse(file)

    Aspose::Cloud::Common::AsposeApp.app_key = data['app_key']
    Aspose::Cloud::Common::AsposeApp.app_sid = data['app_sid']
    Aspose::Cloud::Common::AsposeApp.output_location = data['output_location']
    Aspose::Cloud::Common::Product.set_base_product_uri(data['product_uri'])
  end

  def test_upload_file
    folder = Aspose::Cloud::AsposeStorage::Folder.new
    response = folder.upload_file(local_file='../Data/test_uploadfile.docx')
    assert_equal true, response
  end

  def test_get_files
    folder = Aspose::Cloud::AsposeStorage::Folder.new
    response = folder.get_files(remote_folder_path='testing123')
    assert_instance_of(Array, response)
  end

  def test_file_exists
    folder = Aspose::Cloud::AsposeStorage::Folder.new
    response = folder.file_exists(filename='test_uploadfile.docx')
    assert_equal true, response
  end

  def test_delete_file
    folder = Aspose::Cloud::AsposeStorage::Folder.new
    response = folder.delete_file(filename='test_uploadfile.docx')
    assert_equal true, response
  end

  def test_create_folder    
    folder = Aspose::Cloud::AsposeStorage::Folder.new
    response = folder.create_folder('testing123')
    assert_equal true, response
  end

  def test_delete_folder
    folder = Aspose::Cloud::AsposeStorage::Folder.new
    response = folder.delete_folder('testing123')
    assert_equal true, response
  end

  def test_get_disc_usage
    folder = Aspose::Cloud::AsposeStorage::Folder.new
    response = folder.get_disc_usage()
    assert_instance_of(Hash, response)
  end

  def test_get_file    
    folder = Aspose::Cloud::AsposeStorage::Folder.new
    response = folder.get_file(file_name='watermark.png')
    Aspose::Cloud::Common::Utils.save_file response, Aspose::Cloud::Common::AsposeApp.output_location + 'watermark.png'
    assert_equal true, File.exist?('../Output/watermark.png')
  end
end