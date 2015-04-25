require 'test/unit'
require_relative '../../lib/asposecloud'

class ExtractorTests < Test::Unit::TestCase

  def setup
    file = File.read('../setup.json')
    data = JSON.parse(file)

    Aspose::Cloud::Common::AsposeApp.app_key = data['app_key']
    Aspose::Cloud::Common::AsposeApp.app_sid = data['app_sid']
    Aspose::Cloud::Common::AsposeApp.output_location = data['output_location']
    Aspose::Cloud::Common::Product.set_base_product_uri(data['product_uri'])
  end

  def test_get_sections
    # Create object of folder class
    folder = Aspose::Cloud::AsposeStorage::Folder.new
    response = folder.upload_file '../Data/test_multi_pages.docx'
    assert_equal true, response

    extractor = Aspose::Cloud::Words::Extractor.new('test_multi_pages.docx')
    response = extractor.get_sections()
    assert_instance_of(Hash, response)
  end

  def test_get_section
    extractor = Aspose::Cloud::Words::Extractor.new('test_multi_pages.docx')
    response = extractor.get_section(section_id=1)
    assert_instance_of(Hash, response)
  end

  def test_get_paragraphs
    extractor = Aspose::Cloud::Words::Extractor.new('test_multi_pages.docx')
    response = extractor.get_paragraphs()
    assert_instance_of(Hash, response)
  end

  def test_get_paragraph
    extractor = Aspose::Cloud::Words::Extractor.new('test_multi_pages.docx')
    response = extractor.get_paragraph(para_id=1)
    assert_instance_of(Hash, response)
  end  

  def test_get_paragraph_run
    extractor = Aspose::Cloud::Words::Extractor.new('test_multi_pages.docx')
    response = extractor.get_paragraph_run(para_id=1, run_index=1)
    assert_instance_of(Hash, response)
  end  

  def test_get_paragraph_run_font
    extractor = Aspose::Cloud::Words::Extractor.new('test_multi_pages.docx')
    response = extractor.get_paragraph_run_font(para_id=1, run_index=1)
    assert_instance_of(Hash, response)
  end  

  def test_get_mail_merge_fields
    extractor = Aspose::Cloud::Words::Extractor.new('test_multi_pages.docx')
    response = extractor.get_mail_merge_fields()
    assert_instance_of(Hash, response)
  end  

  def test_get_text
    extractor = Aspose::Cloud::Words::Extractor.new('test_multi_pages.docx')
    response = extractor.get_text()
    assert_instance_of(Array, response)
  end  
=end
  def test_get_ole_data
    extractor = Aspose::Cloud::Words::Extractor.new('test_multi_pages.docx')
    assert_nothing_thrown 'Error' do
      extractor.get_ole_data(ole_index=0, ole_format='png')
    end
    assert_equal true, File.exist?('../Output/test_multi_pages_1.png')
  end  

  def test_get_image_data
    extractor = Aspose::Cloud::Words::Extractor.new('test_multi_pages.docx')
    assert_nothing_thrown 'Error' do
      extractor.get_image_data(image_index=1, image_format='png')
    end
    assert_equal true, File.exist?('../Output/test_multi_pages_1.png')
  end  

  def test_convert_drawing_object
    extractor = Aspose::Cloud::Words::Extractor.new('test_multi_pages.docx')
    assert_nothing_thrown 'Error' do
      extractor.convert_drawing_object(object_index=1, render_format='png')
    end
    assert_equal true, File.exist?('../Output/test_multi_pages_1.png')
  end  

  def test_get_drawing_object_list
    extractor = Aspose::Cloud::Words::Extractor.new('test_multi_pages.docx')
    response = extractor.get_drawing_object_list()
    assert_instance_of(Array, response)
  end  

  def test_get_drawing_object
    extractor = Aspose::Cloud::Words::Extractor.new('test_multi_pages.docx')
    assert_nothing_thrown 'Error' do
      extractor.get_drawing_object(object_uri='', output_path='')
    end
    assert_equal true, File.exist?('../Output/test_multi_pages_1.png')
  end  

  def test_get_drawing_objects
    extractor = Aspose::Cloud::Words::Extractor.new('test_multi_pages.docx')
    assert_nothing_thrown 'Error' do
      extractor.get_drawing_objects(object_uri='', output_path='')
    end
    assert_equal true, File.exist?('../Output/test_multi_pages_1.png')
  end  

  def test_get_stats
    extractor = Aspose::Cloud::Words::Extractor.new('test_multi_pages.docx')
    response = extractor.get_stats()
    assert_instance_of(Hash, response)
  end  
end
