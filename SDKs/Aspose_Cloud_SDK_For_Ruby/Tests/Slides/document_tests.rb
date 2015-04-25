
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

  # Add a New Slide in a PowerPoint Presentation
  def test_add_slide
    # Create Object of folder class
    folder = Aspose::Cloud::AsposeStorage::Folder.new
    response = folder.upload_file '../Data/test_slides.pptx'
    assert_equal true, response

    # Create object of document class
    document = Aspose::Cloud::Slides::Document.new('test_slides.pptx')
    response = document.add_slide(position=1)
    assert_instance_of(Hash, response)
  end

  # Create Empty PowerPoint Presentation
  def test_create_empty_presentation
    document = Aspose::Cloud::Slides::Document.new('create_presentation1.pptx')
    response = document.create_empty_presentation()
    assert_instance_of(Hash, response)
  end

  # Copy Slides in a PowerPoint Presentation
  def test_clone_slide
    document = Aspose::Cloud::Slides::Document.new('test_slides.pptx')
    response = document.clone_slide(slide_number=1, position=2)
    assert_instance_of(Hash, response)
  end

  # Change Position of Slides in a PowerPoint Presentation
  def test_change_slide_position
    document = Aspose::Cloud::Slides::Document.new('test_slides.pptx')
    response = document.change_slide_position(old_position=1, new_position=2)
    assert_instance_of(Hash, response)
  end

  # Split PowerPoint Presentations
  def test_split_presentation
    document = Aspose::Cloud::Slides::Document.new('test_slides.pptx')
    response = document.split_presentation()
    assert_instance_of(Array, response)
  end

  # Merge PowerPoint Presentations
  def test_merge_presentations
    document = Aspose::Cloud::Slides::Document.new('test_slides.pptx')
    presentation_list = '<PresentationsMergeRequest>
                          <Path>demo.pptx</Path>
                          <Path>Test.pptx</Path>
                        </PresentationsMergeRequest>'
    response = document.merge_presentations(presentation_list)
    assert_instance_of(Hash, response)
  end

  # Merge Selected Slides of PowerPoint Presentations
  def test_merge_selected_slides
    document = Aspose::Cloud::Slides::Document.new('test_slides.pptx')
    presentation_list = '<OrderedMergeRequest>
                          <Presentation>
                            <Path>demo.pptx</Path>
                            <Slide>2</Slide>
                            <Slide>1</Slide>
                          </Presentation>
                        </OrderedMergeRequest>'
    response = document.merge_selected_slides(presentation_list)
    assert_instance_of(Hash, response)
  end

  # Finds the slide count of the specified PowerPoint document
  def test_get_slide_count
    document = Aspose::Cloud::Slides::Document.new('test_slides.pptx')
    response = document.get_slide_count()
    assert_equal true, response>=0
  end

  # Replaces all instances of old text with new text in a presentation or a particular slide
  def test_replace_text
    document = Aspose::Cloud::Slides::Document.new('test_slides.pptx')
    document.replace_text(old_text='Aspose', new_text='Banckle', slide_number = 3)
    assert_equal true, File.exist?('../Output/test_slides.pptx')
  end

  # Gets all the text items in a slide or presentation
  def test_get_all_text_items
    document = Aspose::Cloud::Slides::Document.new('test_slides.pptx')
    response = document.get_all_text_items(slide_number = 3)
    assert_instance_of(Array, response)
  end

  # Deletes all slides from a presentation (Test it later)
  def test_delete_all_slides
    document = Aspose::Cloud::Slides::Document.new('test_slides.pptx')
    rresponse = document.delete_all_slides()
    assert_equal true, File.exist?('../Output/test_slides.pptx')
  end

  # Delete a Slide from a PowerPoint Presentation
  def test_delete_slide
    document = Aspose::Cloud::Slides::Document.new('test_slides.pptx')
    document.delete_slide(slide_number = 3)
    assert_equal true, File.exist?('../Output/test_slides.pptx')
  end

  # Delete Background of a PowerPoint Slide
  def test_delete_background
    document = Aspose::Cloud::Slides::Document.new('test_slides.pptx')
    document.delete_background(slide_number = 3)
    assert_equal true, File.exist?('../Output/test_slides.pptx')
  end

  # Get Background of a PowerPoint Slide
  def test_get_background
    document = Aspose::Cloud::Slides::Document.new('test_slides.pptx')
    response = document.get_background(slide_number = 2)
    assert_instance_of(Hash, response)
  end

  # Get Document's properties
  def test_get_properties
    document = Aspose::Cloud::Slides::Document.new('test_slides.pptx')
    response = document.get_properties()
    assert_instance_of(Array, response)
  end  

  # Set document property
  def test_set_property
    document = Aspose::Cloud::Slides::Document.new('test_slides.pptx')
    response = document.set_property(property_name='Test', property_value='123')
    assert_instance_of(Hash, response)
  end

  # Remove All Document's properties
  def test_remove_all_properties
    document = Aspose::Cloud::Slides::Document.new('test_slides.pptx')
    response = document.remove_all_properties()
    assert_equal true, response
  end  

  # Remove a particular document property
  def test_delete_property
    document = Aspose::Cloud::Slides::Document.new('test_slides.pptx')
    response = document.delete_property(property_name='Test')
    assert_equal true, response
  end  

  # saves the document into various formats
  def test_save_as
    document = Aspose::Cloud::Slides::Document.new('test_slides.pptx')
    assert_nothing_thrown 'Error' do
      document.save_as(output_filename='test_slides.pdf', output_format='pdf')
    end

    assert_equal true, File.exist?('../Output/test_slides.pdf')
  end

  # saves the document into various formats
  def test_save_slide_as
    document = Aspose::Cloud::Slides::Document.new('test_slides.pptx')
    assert_nothing_thrown 'Error' do
      document.save_slide_as(slide_number=1, output_filename='test_slides.tiff', output_format='tiff')
    end

    assert_equal true, File.exist?('../Output/test_slides_1.tiff')
  end

  # Get Aspect Ratio of a PowerPoint Slide
  def test_aspect_ratio
    document = Aspose::Cloud::Slides::Document.new('test_slides.pptx')
    response = document.aspect_ratio(slide_number=1)
    assert_equal true, response >= 0
  end
  
end