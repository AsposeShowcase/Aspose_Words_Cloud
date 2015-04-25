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

  def test_save_as
    document = Aspose::Cloud::Words::Document.new('test_multi_pages.docx')
    assert_nothing_thrown 'Error' do
      options_xml = '<PdfSaveOptions>
                       <SaveFormat>pdf</SaveFormat>
                       <FileName>Output.pdf</FileName>
                       <ImageCompression>Jpeg</ImageCompression>
                       <JpegQuality>70</JpegQuality>
                       <TextCompression>Flate</TextCompression>
                    </PdfSaveOptions>'
      document.save_as(options_xml)
    end
     assert_equal true, File.exist?('../Output/Output.pdf')
  end

  def test_split_document
    document = Aspose::Cloud::Words::Document.new('test_multi_pages.docx')
    assert_nothing_thrown 'Error' do
      response = document.split_document(from=1, to=2, save_format='pdf')
    end
      assert_instance_of(Array, response)
  end

  def test_get_page_setup
    document = Aspose::Cloud::Words::Document.new('test_multi_pages.docx')
    response = document.get_page_setup(section_index=1)
    assert_instance_of(Hash, response)
  end

  def test_update_page_setup
    document = Aspose::Cloud::Words::Document.new('test_multi_pages.docx')
    assert_nothing_thrown 'Error' do
      options_xml = '<PageSetup>        
                      <LeftMargin>99</LeftMargin>    
                      <Orientation>Landscape</Orientation>    
                      <PaperSize>A5</PaperSize>
                    </PageSetup>'
      document.update_page_setup(section_index=1, options_xml)
    end  
  end

  def test_append_document
    document = Aspose::Cloud::Words::Document.new('test_multi_pages.docx')
    assert_nothing_thrown 'Error' do
      response = document.append_document(append_docs=["Test.docx"], import_format_modes=["KeepSourceFormatting", "UseDestinationStyles"])
    end
     assert_equal true, File.exist?('../Output/test_multi_pages.docx')
  end

  def test_get_document_info
    document = Aspose::Cloud::Words::Document.new('test_multi_pages.docx')
    response = document.get_document_info()
    assert_instance_of(Hash, response)
  end

  def test_get_property
    document = Aspose::Cloud::Words::Document.new('test_multi_pages.docx')
    response = document.get_property(property_name='Author')
    assert_instance_of(Hash, response)
  end

  def test_set_property
    document = Aspose::Cloud::Words::Document.new('test_multi_pages.docx')
    response = document.set_property(property_name='Test', property_value='123')
    assert_instance_of(Hash, response)
  end

  def test_delete_property
    document = Aspose::Cloud::Words::Document.new('test_multi_pages.docx')
    response = document.delete_property(property_name='Test')
    assert_equal true, response
  end

  def test_get_properties
    document = Aspose::Cloud::Words::Document.new('test_multi_pages.docx')
    response = document.get_properties()
    assert_instance_of(Array, response)
  end

  def test_get_protection
    document = Aspose::Cloud::Words::Document.new('test_multi_pages.docx')
    response = document.get_protection()
    assert_equal 'NoProtection', response
  end

  def test_protect_document
    document = Aspose::Cloud::Words::Document.new('test_multi_pages.docx')
    assert_nothing_thrown 'Error' do
      document.protect_document(password='123456', protection_type = 'AllowOnlyComments')
    end
     assert_equal true, File.exist?('../Output/test_multi_pages.docx')
  end

  def test_unprotect_document
    document = Aspose::Cloud::Words::Document.new('test_multi_pages.docx')
    assert_nothing_thrown 'Error' do
      document.unprotect_document(password='123456', protection_type = 'AllowOnlyComments')
    end
     assert_equal true, File.exist?('../Output/test_multi_pages.docx')
  end

  def test_update_protection
    document = Aspose::Cloud::Words::Document.new('test_multi_pages.docx')
    assert_nothing_thrown 'Error' do
      document.update_protection(old_password='123456', new_password='123456789', protection_type = 'AllowOnlyComments')
    end
     assert_equal true, File.exist?('../Output/test_multi_pages.docx')
  end

  def test_get_hyperlinks
    document = Aspose::Cloud::Words::Document.new('test_multi_pages.docx')
    response = document.get_hyperlinks()
    assert_instance_of(Array, response)
  end

  def test_get_hyperlink
    document = Aspose::Cloud::Words::Document.new('test_multi_pages.docx')
    response = document.get_hyperlink(hyperlink_index=1)
    assert_instance_of(Hash, response)
  end

  def test_get_hyperlinks_count
    document = Aspose::Cloud::Words::Document.new('test_multi_pages.docx')
    response = document.get_hyperlinks_count()
    assert_equal true, response>=0
  end

  def test_get_bookmarks
    document = Aspose::Cloud::Words::Document.new('test_multi_pages.docx')
    response = document.get_bookmarks()
    assert_instance_of(Array, response)
  end

  def test_get_bookmark
    document = Aspose::Cloud::Words::Document.new('test_multi_pages.docx')
    response = document.get_bookmark(bookmark_name='First')
    assert_instance_of(Hash, response)
  end

  def test_get_bookmarks_count
    document = Aspose::Cloud::Words::Document.new('test_multi_pages.docx')
    response = document.get_bookmarks_count()
    assert_equal true, response>=0
  end

  def test_update_bookmark
    document = Aspose::Cloud::Words::Document.new('test_multi_pages.docx')
    response = document.update_bookmark(bookmark_name='First', bookmark_text='This is new text')
    assert_instance_of(Hash, response)
  end

  def test_delete_headers_footers
    document = Aspose::Cloud::Words::Document.new('test_multi_pages.docx')
    response = document.delete_headers_footers()
    assert_equal true, response
  end

  def test_accept_tracking_changes
    document = Aspose::Cloud::Words::Document.new('test_multi_pages.docx')
    assert_nothing_thrown 'Error' do
      document.accept_tracking_changes()
    end
     assert_equal true, File.exist?('../Output/test_multi_pages.docx')
  end

  def test_reject_tracking_changes
    document = Aspose::Cloud::Words::Document.new('test_multi_pages.docx')
    assert_nothing_thrown 'Error' do
      document.reject_tracking_changes()
    end
     assert_equal true, File.exist?('../Output/test_multi_pages.docx')
  end

  def test_insert_page_number
    document = Aspose::Cloud::Words::Document.new('test_multi_pages.docx')
    assert_nothing_thrown 'Error' do
      document.insert_page_number(alignment='right', format='{PAGE} of {NUMPAGES}', is_top = false, set_page_number_on_first_page = false)
    end
     assert_equal true, File.exist?('../Output/test_multi_pages.docx')
  end

  def test_update_fields
    document = Aspose::Cloud::Words::Document.new('test_multi_pages.docx')
    response = document.update_fields()
    assert_equal true, response
  end

  def test_update_paragraph_run_font
    document = Aspose::Cloud::Words::Document.new('test_multi_pages.docx')
    response = document.update_paragraph_run_font(para_id=1, run_index=1, font_name='Calibri')
    assert_instance_of(Hash, response)
  end 
end
