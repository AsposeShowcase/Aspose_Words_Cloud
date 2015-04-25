require 'test/unit'
require_relative '../../lib/asposecloud'

class ConverterTests < Test::Unit::TestCase

  def setup
    file = File.read('../setup.json')
    data = JSON.parse(file)

    Aspose::Cloud::Common::AsposeApp.app_key = data['app_key']
    Aspose::Cloud::Common::AsposeApp.app_sid = data['app_sid']
    Aspose::Cloud::Common::AsposeApp.output_location = data['output_location']
    Aspose::Cloud::Common::Product.set_base_product_uri(data['product_uri'])
  end

  def test_convert
    # Create Object of folder class
    folder = Aspose::Cloud::AsposeStorage::Folder.new
    response = folder.upload_file '../Data/email_test.eml'
    assert_equal true, response

    # Create object of email converter class
    converter = Aspose::Cloud::Email::Converter.new('email_test.eml')
    assert_nothing_thrown 'Error' do
      converter.convert(save_format='msg')
    end

    assert_equal true, File.exist?('../Output/email_test.msg')
  end
end