require 'test/unit'
require_relative '../../lib/asposecloud'

class BuilderTests < Test::Unit::TestCase

  def setup
    file = File.read('../setup.json')
    data = JSON.parse(file)

    Aspose::Cloud::Common::AsposeApp.app_key = data['app_key']
    Aspose::Cloud::Common::AsposeApp.app_sid = data['app_sid']
    Aspose::Cloud::Common::AsposeApp.output_location = data['output_location']
    Aspose::Cloud::Common::Product.set_base_product_uri(data['product_uri'])
  end

  def test_save
    builder = Aspose::Cloud::Barcode::Builder.new
    assert_nothing_thrown 'Error' do
      builder.save(code_text='Text to generate barcode', symbology='QR', image_format='png')
    end
    assert_equal true, File.exist?('../Output/barcodeQR.png')
  end

  def test_generate_barcode_image_stream
    builder = Aspose::Cloud::Barcode::Builder.new
    assert_nothing_thrown 'Error' do
      response = builder.generate_barcode_image_stream(code_text='Text to generate barcode', symbology='QR', image_format='png')
    end
    assert_equal true, response.to_s
  end

  def test_set_barcode_code_location
    builder = Aspose::Cloud::Barcode::Builder.new
    assert_nothing_thrown 'Error' do
      builder.set_barcode_code_location(code_text='Text to generate barcode', code_location='Above', symbology='QR', image_format='png')
    end
    assert_equal true, File.exist?('../Output/barcodeQR.png')
  end

  def test_set_barcode_checksum
    builder = Aspose::Cloud::Barcode::Builder.new
    assert_nothing_thrown 'Error' do
      builder.set_barcode_checksum(code_text='Text to generate barcode', checksum='Default', symbology='QR', image_format='png')
    end
    assert_equal true, File.exist?('../Output/barcodeQR.png')
  end

  def test_set_barcode_height_width
    builder = Aspose::Cloud::Barcode::Builder.new
    assert_nothing_thrown 'Error' do
      builder.set_barcode_height_width(code_text='Text to generate barcode', height=40, width=50, quality='AntiAlias', auto_size=true, symbology='QR', image_format='png')
    end
    assert_equal true, File.exist?('../Output/barcodeQR.png')
  end

  def test_set_barcode_angle
    builder = Aspose::Cloud::Barcode::Builder.new
    assert_nothing_thrown 'Error' do
      builder.set_barcode_angle(code_text='Text to generate barcode', rotation_angle=180, symbology='QR', image_format='png')
    end
    assert_equal true, File.exist?('../Output/barcodeQR.png')
  end

  def test_set_barcode_margin
    builder = Aspose::Cloud::Barcode::Builder.new
    assert_nothing_thrown 'Error' do
      builder.set_barcode_margin(code_text='Text to generate barcode', top_margin=2, bottom_margin=2, left_margin=2, right_margin=2, symbology='QR', image_format='png')
    end
    assert_equal true, File.exist?('../Output/barcodeQR.png')
  end

  def test_set_barcode_resolution
    builder = Aspose::Cloud::Barcode::Builder.new
    assert_nothing_thrown 'Error' do
      builder.set_barcode_resolution(code_text='Text to generate barcode', x_resolution=200, y_resolution=200, symbology='QR', image_format='png')
    end
    assert_equal true, File.exist?('../Output/barcodeQR.png')
  end

  def test_set_barcode_height
    builder = Aspose::Cloud::Barcode::Builder.new
    assert_nothing_thrown 'Error' do
      builder.set_barcode_height(code_text='Text to generate barcode', height=20, gr_unit='pt', symbology='barcode128', image_format='png')
    end
    assert_equal true, File.exist?('../Output/barcodeQR.png')
  end

  def test_set_barcode_dimensions
    builder = Aspose::Cloud::Barcode::Builder.new
    assert_nothing_thrown 'Error' do
      builder.set_barcode_dimensions(code_text='Text to generate barcode', x_dimension=1.0, y_dimension=0.5, symbology='QR', image_format='png')
    end
    assert_equal true, File.exist?('../Output/barcodeQR.png')
  end
end