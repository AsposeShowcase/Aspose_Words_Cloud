
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

  # Get PDF Document Page Count
  def test_get_page_count
    document = Aspose::Cloud::Pdf::Document.new('Test.pdf')
    response = document.get_page_count()
    assert_equal true, response>=0
  end

  # Append PDF Files
  def test_append_document
    document = Aspose::Cloud::Pdf::Document.new('Test.pdf')
    response = document.append_document(append_file='Testing.pdf')
    assert_equal true, response
  end

  # Merge multiple PDF files
  def test_merge_documents
    document = Aspose::Cloud::Pdf::Document.new('Test.pdf')
    response = document.merge_documents(merged_filename='Test.pdf', source_files=['input1.pdf', 'input2.pdf'])
    assert_equal true, response
  end  

  # Get Form Field Count from a PDF Document 
  def test_get_form_field_count
    document = Aspose::Cloud::Pdf::Document.new('Test.pdf')
    response = document.get_form_field_count()
    assert_equal true, response>=0
  end  

  # Get all Form Fields from the PDF Document
  def test_get_form_fields
    document = Aspose::Cloud::Pdf::Document.new('Test.pdf')
    response = document.get_form_fields()
    assert_instance_of(Array, response)
  end

  # Get a Particular Form Field from the PDF Document
  def test_get_form_field
    document = Aspose::Cloud::Pdf::Document.new('complaintform.pdf')
    response = document.get_form_field(field_name='last')
    assert_instance_of(Hash, response)
  end  

  # Update a Form Field in a PDF Document
  def test_update_form_field
    document = Aspose::Cloud::Pdf::Document.new('complaintform.pdf')
    response = document.update_form_field(field_name='last', field_type=0, field_value='Tom')
    assert_instance_of(Hash, response)
  end  

  # Create PDF from HTML
  def test_create_from_html
    document = Aspose::Cloud::Pdf::Document.new('create_from_html.pdf')
    response = document.create_from_html(html_filename='index.html')
    assert_equal true, response
  end

  # Create PDF from XML
  def test_create_from_xml
    document = Aspose::Cloud::Pdf::Document.new('create_from_xml.pdf')
    response = document.create_from_xml(xslt_filename='template.xslt', xml_filename='template-data.xml')
    assert_equal true, response
  end

  # Create PDF from JPEG
  def test_create_from_jpeg
    document = Aspose::Cloud::Pdf::Document.new('create_from_jpeg.pdf')
    assert_nothing_thrown 'Error' do
      document.create_from_jpeg(jpeg_filename='Lighthouse.jpg')
    end
    assert_equal true, File.exist?('../Output/create_from_jpeg.pdf')
  end

  # Create PDF from SVG
  def test_create_from_svg
    document = Aspose::Cloud::Pdf::Document.new('create_from_svg.pdf')
    assert_nothing_thrown 'Error' do
      response = document.create_from_svg(svg_filename='input.svg')
    end  
    assert_equal true, File.exist?('../Output/create_from_svg.pdf')
  end

  # Create PDF from TIFF
  def test_create_from_tiff
    document = Aspose::Cloud::Pdf::Document.new('create_from_tiff.pdf')
    assert_nothing_thrown 'Error' do
      response = document.create_from_tiff(tiff_filename='input.tiff')
    end
    assert_equal true, File.exist?('../Output/create_from_tiff.pdf')
  end

  # Create Empty PDF
  def test_create_empty_pdf
    document = Aspose::Cloud::Pdf::Document.new('Empty.pdf')
    assert_nothing_thrown 'Error' do
      document.create_empty_pdf()
    end
    assert_equal true, File.exist?('../Output/Empty.pdf')
  end

  # Add a new page in PDF
  def test_add_new_page
    document = Aspose::Cloud::Pdf::Document.new('Test.pdf')
    assert_nothing_thrown 'Error' do
      document.add_new_page()
    end  
    assert_equal true, File.exist?('../Output/Test.pdf')
  end  

  # Delete page from PDF
  def test_delete_page
    document = Aspose::Cloud::Pdf::Document.new('Test.pdf')
    assert_nothing_thrown 'Error' do
      document.delete_page(page_number=2)
    end
    assert_equal true, File.exist?('../Output/Test.pdf')
  end  

  # Replace Image in a PDF file using Local Image Stream
  def test_replace_image_stream
    document = Aspose::Cloud::Pdf::Document.new('Test.pdf')
    input_file = 'path/to/inputfile'
    image_stream = File.read(input_file, nil, nil, {"mode"=>"b"})
    response = document.replace_image_stream(page_number=1, image_index=1, image_stream)
    assert_equal true, File.exist?('../Output/Test.pdf')
  end  

  # Replace Image in a PDF File using Image File
  def test_replace_image_file
    document = Aspose::Cloud::Pdf::Document.new('Test.pdf')
    assert_nothing_thrown 'Error' do
      document.replace_image_file(page_number=1, image_index=1, image_file='watermark.png')
    end
    assert_equal true, File.exist?('../Output/Test.pdf')
  end  

  # Get a Particular Document Property from a PDF 
  def test_get_document_property
    document = Aspose::Cloud::Pdf::Document.new('Test.pdf')
    response = document.get_document_property(property_name='Author')
    assert_instance_of(Hash, response)
  end  

  # Get All Document Properties from a PDF
  def test_get_document_properties
    document = Aspose::Cloud::Pdf::Document.new('Test.pdf')
    response = document.get_document_properties()
    assert_instance_of(Array, response)
  end  

  # Set document property
  def test_set_document_property
    document = Aspose::Cloud::Pdf::Document.new('Test.pdf')
    response = document.set_document_property(property_name='Test', property_value='123')
    assert_instance_of(Hash, response)
  end  

  # Remove All Document Properties from a PDF
  def test_remove_all_properties
    document = Aspose::Cloud::Pdf::Document.new('Test.pdf')
    response = document.remove_all_properties()
    assert_equal true, response
  end  

  # Split all pages of a PDF document
  def test_split_all_pages
    document = Aspose::Cloud::Pdf::Document.new('Test.pdf')
    assert_nothing_thrown 'Error' do
      document.split_all_pages()
    end
    assert_equal true, File.exist?('../Output/Test_1.pdf')
    assert_equal true, File.exist?('../Output/Test_2.pdf')
  end  

  # Split specfied pages of a PDF document
  def test_split_pages
    document = Aspose::Cloud::Pdf::Document.new('Test.pdf')
    assert_nothing_thrown 'Error' do
      document.split_pages(from=1, to=2)
    end
    assert_equal true, File.exist?('../Output/Test_1.pdf')
    assert_equal true, File.exist?('../Output/Test_2.pdf')
  end  

  # Split all pages of a PDF document to specified format
  def test_split_pages_to_any_format
    document = Aspose::Cloud::Pdf::Document.new('Test.pdf')
    assert_nothing_thrown 'Error' do
      document.split_pages_to_any_format(from=1, to=2, save_format='png')
    end
    assert_equal true, File.exist?('../Output/Test_1.png')
    assert_equal true, File.exist?('../Output/Test_2.png')
  end  

  # Add Text Stamp (Watermark) to a PDF Page
  def test_add_stamp
    document = Aspose::Cloud::Pdf::Document.new('Test.pdf')
    assert_nothing_thrown 'Error' do
      post_data = '{
                    "Type": 0,
                    "Background": true,
                    "BottomMargin": 2.0,
                    "HorizontalAlignment": 1,
                    "LeftMargin": 3.0,
                    "Opacity": 0.5,
                    "RightMargin": 0.0,
                    "Rotate": 3,
                    "RotateAngle": 45.0,
                    "TopMargin": 4.0,
                    "VerticalAlignment": 3,
                    "XIndent": 2.0,
                    "YIndent": 2.5,
                    "Zoom": 1.5,
                    "TextAlignment": 0,
                    "Value": "STAMP TEXT",
                    "TextState": {
                      "FontSize": 14.0,
                      "Font": "Arial",
                      "ForegroundColor": {
                        "A": 0,
                        "R": 255,
                        "G": 0,
                        "B": 0
                      },
                      "BackgroundColor": {
                        "A": 0,
                        "R": 0,
                        "G": 0,
                        "B": 255
                      },
                      "FontStyle": 2
                    },
                    "FileName": null,
                    "Width": 0.0,
                    "Height": 0.0,
                    "PageIndex": 0,
                    "StartingNumber": 0
                  }'
      document.add_stamp(page_number=2, post_data)
    end
    assert_equal true, File.exist?('../Output/Test.pdf')
  end    
end