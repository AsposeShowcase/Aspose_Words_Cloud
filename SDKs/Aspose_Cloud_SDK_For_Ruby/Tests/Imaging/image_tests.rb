require 'test/unit'
require_relative '../../lib/asposecloud'

class ImageTests < Test::Unit::TestCase

  def setup
    file = File.read('../setup.json')
    data = JSON.parse(file)

    Aspose::Cloud::Common::AsposeApp.app_key = data['app_key']
    Aspose::Cloud::Common::AsposeApp.app_sid = data['app_sid']
    Aspose::Cloud::Common::AsposeApp.output_location = data['output_location']
    Aspose::Cloud::Common::Product.set_base_product_uri(data['product_uri'])
  end

  # Resize Image without Storage
  def test_resize_image
    folder = Aspose::Cloud::AsposeStorage::Folder.new
    response = folder.upload_file '../Data/barcodeQR.png'
    assert_equal true, response

    image = Aspose::Cloud::Imaging::Image.new('barcodeQR.png')
    assert_nothing_thrown 'Error' do
      image.resize_image(input_file_path='../Data/barcodeQR.png', new_width=10, new_height=10, output_filename='barcodeQR_resized.gif', save_format='gif')
    end
    assert_equal true, File.exist?('../Output/barcodeQR_resized.gif')
  end

  # Crop Image with Format Change
  def test_crop_image
    image = Aspose::Cloud::Imaging::Image.new('barcodeQR.png')
    assert_nothing_thrown 'Error' do
      image.crop_image(x=0, y=0, width=50, height=60, output_path='/', save_format='png')
    end
    assert_equal true, File.exist?('../Output/barcodeQR.png')
  end

  # RotateFlip Image on Storage
  def test_rotate_image
    image = Aspose::Cloud::Imaging::Image.new('barcodeQR.png')
    assert_nothing_thrown 'Error' do
      image.rotate_image(method='rotate270flipnone', output_path='/', save_format='png')
    end
    assert_equal true, File.exist?('../Output/barcodeQR.png')
  end

  # Merge Tiff images
  def test_append_tiff
    image = Aspose::Cloud::Imaging::Image.new('Test.tiff')
    assert_nothing_thrown 'Error' do
      image.append_tiff(append_file='input.tiff')
    end
    assert_equal true, File.exist?('../Output/Test.tiff')
  end  
end
