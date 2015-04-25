require 'test/unit'
require_relative '../../lib/asposecloud'

class ReaderTests < Test::Unit::TestCase

  def setup
    file = File.read('../setup.json')
    data = JSON.parse(file)

    Aspose::Cloud::Common::AsposeApp.app_key = data['app_key']
    Aspose::Cloud::Common::AsposeApp.app_sid = data['app_sid']
    Aspose::Cloud::Common::AsposeApp.output_location = data['output_location']
    Aspose::Cloud::Common::Product.set_base_product_uri(data['product_uri'])
  end

  def test_read
    # Create Object of folder class
    folder = Aspose::Cloud::AsposeStorage::Folder.new
    response = folder.upload_file '../Data/barcodeQR.png'
    assert_equal true, response

    # Creae object of Reader class
    reader = Aspose::Cloud::Barcode::Reader.new('barcodeQR.png')
    response = reader.read()
    assert_instance_of(Array, response)
  end

  def test_read_barcode_local_image
    reader = Aspose::Cloud::Barcode::Reader.new('barcodeQR.jpg')
    response = reader.read_from_local_image(local_image = '../Data/barcodeQR.jpg')
    assert_instance_of(Array, response)
  end

  def test_readr
    reader = Aspose::Cloud::Barcode::Reader.new('barcodeQR.png')
    response = reader.readr(remote_image_name='barcodeQR.png')
    assert_instance_of(Array, response)
  end

  def test_read_from_url
    reader = Aspose::Cloud::Barcode::Reader.new('barcodeQR.png')
    url = "http://upload.wikimedia.org/wikipedia/commons/c/ce/WikiQRCode.png"
    response = reader.read_from_url(url, symbology='QR')
    assert_instance_of(Array, response)
  end

  def test_read_specific_region
    reader = Aspose::Cloud::Barcode::Reader.new('barcodeQR.png')
    response = reader.read_specific_region(symbology='QR', rectX=20, rectY=20, rectWidth=100, rectHeight=100)
    assert_instance_of(Array, response)
  end

  def test_read_with_checksum
    reader = Aspose::Cloud::Barcode::Reader.new('barcodeQR.png')
    response = reader.read_with_checksum(symbology='QR', checksumValidation='Default')
    assert_instance_of(Array, response)
  end

  def test_read_barcode_count
    reader = Aspose::Cloud::Barcode::Reader.new('barcodeQR.png')
    response = reader.read_barcode_count(symbology='QR', barcodesCount=2)
    assert_instance_of(Array, response)
  end 
end