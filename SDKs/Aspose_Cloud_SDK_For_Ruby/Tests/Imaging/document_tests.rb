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

  # Get Image Properties 
  def test_get_properties
    document = Aspose::Cloud::Imaging::Document.new('barcodeQR.png')
    response = document.get_properties()
    assert_kind_of(Hash, response)
  end

  # Update BMP Image Properties
  def test_update_bmp_properties
    folder = Aspose::Cloud::AsposeStorage::Folder.new
    response = folder.upload_file '../Data/barcodeQR.bmp'
    assert_equal true, response

    document = Aspose::Cloud::Imaging::Document.new('barcodeQR.bmp')
    assert_nothing_thrown 'Error' do
      document.update_bmp_properties(bits_per_px=24, h_resolution=100, v_resolution=100, output_path='barcodeQR_updated.bmp')
    end
    assert_equal true, File.exist?('../Output/barcodeQR_updated.bmp')
  end

  # Update BMP Image Properties Without Storage
  def test_update_bmp_properties_local
    document = Aspose::Cloud::Imaging::Document.new('barcodeQR.bmp')
    assert_nothing_thrown 'Error' do
      document.update_bmp_properties_local(input_file_path='../Data/barcodeQR.bmp', bits_per_px=24, h_resolution=100, v_resolution=100)
    end
    assert_equal true, File.exist?('../Output/barcodeQR_updated.bmp')
  end

  # Update GIF Image Properties
  def test_update_gif_properties
    folder = Aspose::Cloud::AsposeStorage::Folder.new
    response = folder.upload_file '../Data/macbook.gif'
    assert_equal true, response

    document = Aspose::Cloud::Imaging::Document.new('macbook.gif')
    assert_nothing_thrown 'Error' do
      document.update_gif_properties(bg_color_index=32, px_aspect_ratio=3, interlaced=true, 'macbook.gif')
    end
    assert_equal true, File.exist?('../Output/macbook_updated.gif')
  end

  # Update GIF Image Properties Without Storage
  def test_update_gif_properties_local
    document = Aspose::Cloud::Imaging::Document.new('macbook.gif')
    assert_nothing_thrown 'Error' do
      document.update_gif_properties_local(input_file_path='../Data/macbook.gif', bg_color_index=32, px_aspect_ratio=3, interlaced=true)
    end
    assert_equal true, File.exist?('../Output/macbook_updated.gif')
  end

  # Update JPG Image Properties
  def test_update_jpg_properties
    folder = Aspose::Cloud::AsposeStorage::Folder.new
    response = folder.upload_file '../Data/barcodeQR.jpg'
    assert_equal true, response

    document = Aspose::Cloud::Imaging::Document.new('barcodeQR.jpg')
    assert_nothing_thrown 'Error' do
      document.update_jpg_properties(quality=45, compression_type='baseline', output_path='barcodeQR.jpg')
    end
    assert_equal true, File.exist?('../Output/barcodeQR_updated.jpg')
  end

  # Update JPG Image Properties Without Storage
  def test_update_jpg_properties_local
    document = Aspose::Cloud::Imaging::Document.new('barcodeQR.jpg')
    assert_nothing_thrown 'Error' do
      document.update_jpg_properties_local(input_file_path='../Data/barcodeQR.jpg', quality=45, compression_type='baseline')
    end
    assert_equal true, File.exist?('../Output/barcodeQR_updated.jpg')
  end

  # Update PSD Image Properties
  def test_update_psd_properties (later)
    folder = Aspose::Cloud::AsposeStorage::Folder.new
    response = folder.upload_file '../Data/bizcard.psd'
    assert_equal true, response

    document = Aspose::Cloud::Imaging::Document.new('bizcard.psd')
    assert_nothing_thrown 'Error' do
      document.update_psd_properties(channels_count=3, compression_method='rle', output_path='bizcard.psd')
    end
    assert_equal true, File.exist?('../Output/bizcard_updated.psd')
  end

  # Update PSD Image Properties Without Storage
  def test_update_psd_properties_local (later)
    document = Aspose::Cloud::Imaging::Document.new('bizcard.psd')
    assert_nothing_thrown 'Error' do
      document.update_psd_properties_local(input_file_path='../Data/bizcard.psd', channels_count=4, compression_method='raw')
    end
    assert_equal true, File.exist?('../Output/bizcard_updated.psd')
  end

  # Update TIFF Image Properties
  def test_update_tiff_properties
    folder = Aspose::Cloud::AsposeStorage::Folder.new
    response = folder.upload_file '../Data/Test.tiff'
    assert_equal true, response

    document = Aspose::Cloud::Imaging::Document.new('Test.tiff')
    assert_nothing_thrown 'Error' do
      document.update_tiff_properties(compression='CcittFax3', resolution_unit='inch', new_width=200, new_height=200, horizontal_resolution=96, vertical_resolution=96, output_path='/')
    end
    assert_equal true, File.exist?('../Output/Test_updated.tiff')
  end

  # Update TIFF Image Properties Without Storage
  def test_update_tiff_properties_local
    document = Aspose::Cloud::Imaging::Document.new('Test.tiff')
    assert_nothing_thrown 'Error' do
      document.update_tiff_properties_local(input_file_path='../Data/Test.tiff', compression='ccittfax3', resolution_unit='inch', new_width=200, new_height=200, horizontal_resolution=96, vertical_resolution=96, output_path='/')
    end
    assert_equal true, File.exist?('../Output/Test_updated.tiff')
  end
end
