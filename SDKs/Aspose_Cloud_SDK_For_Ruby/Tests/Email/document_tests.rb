require 'test/unit'
require_relative '../../lib/asposecloud'

class DocumentTests < Test::Unit::TestCase

  def setup
    file = File.read('../setup.json')
    data = JSON.parse(file)

    Aspose::Cloud::Common::AsposeApp.app_key = data['app_key']
    Aspose::Cloud::Common::AsposeApp.app_sid = data['app_sid']
    Aspose::Cloud::Common::AsposeApp.output_location = data['output_location']
    Aspose::Cloud::Common::Product.set_base_product_uri(data['product_uri'])
  end

  def test_get_property
    # Create Object of folder class
    folder = Aspose::Cloud::AsposeStorage::Folder.new
    response = folder.upload_file '../Data/email_test.eml'
    assert_equal true, response

    # Create object of email document class
    document = Aspose::Cloud::Email::Document.new('email_test.eml')
    response = document.get_property(property_name='Subject')
    assert_equal 'test', response
  end

  def test_set_property
    document = Aspose::Cloud::Email::Document.new('email_test.eml')
    response = document.set_property(property_name='Subject', property_value='Testing')
    assert_equal 'Testing', response
  end

  def test_get_attachment
    folder = Aspose::Cloud::AsposeStorage::Folder.new
    response = folder.upload_file '../Data/email_test_attach.eml'
    assert_equal true, response

    document = Aspose::Cloud::Email::Document.new('email_test_attach.eml')
    assert_nothing_thrown 'Error' do
      document.get_attachment(attachment_name='readme.txt')
    end

    assert_equal true, File.exist?('../Output/readme.txt')
  end

  def test_add_attachment
    document = Aspose::Cloud::Email::Document.new('email_test.eml')
    assert_nothing_thrown 'Error' do
      document.add_attachment(attachment_name='watermark.png')
    end

    assert_equal true, File.exist?('../Output/email_test.eml')
  end
end