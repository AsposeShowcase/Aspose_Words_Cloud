
require 'test/unit'
require_relative '../../lib/asposecloud'

class AnnotationEditorTests < Test::Unit::TestCase

  def setup
    file = File.read('../setup.json')
    data = JSON.parse(file)

    Aspose::Cloud::Common::AsposeApp.app_key = data['app_key']
    Aspose::Cloud::Common::AsposeApp.app_sid = data['app_sid']
    Aspose::Cloud::Common::AsposeApp.output_location = data['output_location']
    Aspose::Cloud::Common::Product.set_base_product_uri(data['product_uri'])
  end

  # Get number of annotations on a specified document page
  def test_get_annotations_count
    annotation_editor = Aspose::Cloud::Pdf::AnnotationEditor.new('Test.pdf')
    response = annotation_editor.get_annotations_count(page_number=1)
    assert_equal true, response>=0
  end

  # Get a specfied annotation on a specified document page
  def test_get_annotation
    annotation_editor = Aspose::Cloud::Pdf::AnnotationEditor.new('Test.pdf')
    response = annotation_editor.get_annotation(page_number=1, annotation_index=1)
    assert_instance_of(Hash, response)
  end

  # Get list of all the annotations on a specified document page
  def test_get_all_annotation
    annotation_editor = Aspose::Cloud::Pdf::AnnotationEditor.new('Test.pdf')
    response = annotation_editor.get_all_annotation(page_number=1)
    assert_instance_of(Array, response)
  end    

  # Get total number of Bookmarks in a Pdf document
  def test_get_bookmarks_count
    annotation_editor = Aspose::Cloud::Pdf::AnnotationEditor.new('test_annotation.pdf')
    response = annotation_editor.get_bookmarks_count()
    assert_equal true, response>=0
  end

  # Get number of child bookmarks in a specfied parent bookmark
  def test_get_child_bookmarks_count
    annotation_editor = Aspose::Cloud::Pdf::AnnotationEditor.new('test_annotation.pdf')
    response = annotation_editor.get_child_bookmarks_count(parent=1)
    assert_equal true, response>=0
  end

  # Get a specfied Bookmark from a PDF document
  def test_get_bookmark
    annotation_editor = Aspose::Cloud::Pdf::AnnotationEditor.new('test_annotation.pdf')
    response = annotation_editor.get_bookmark(bookmark_index=1)    
    assert_instance_of(Hash, response)
  end

  # Get a specfied Child Bookmark from a PDF document
  def test_get_child_bookmark
    annotation_editor = Aspose::Cloud::Pdf::AnnotationEditor.new('test_annotation.pdf')
    response = annotation_editor.get_child_bookmark(parent_index=1, child_index=1)
    assert_instance_of(Hash, response)
  end

  # Get list of all the bookmarks in pdf
  def test_get_all_bookmarks
    annotation_editor = Aspose::Cloud::Pdf::AnnotationEditor.new('Test.pdf')
    response = annotation_editor.get_all_bookmarks()
    assert_instance_of(Array, response)
  end

  # Get total number of Attachments in a Pdf document
  def test_get_attachments_count
    annotation_editor = Aspose::Cloud::Pdf::AnnotationEditor.new('attachment.pdf')
    response = annotation_editor.get_attachments_count()
    assert_equal true, response>=0
  end

  # Get a specfied Attachment from a PDF document
  def test_get_attachment
    annotation_editor = Aspose::Cloud::Pdf::AnnotationEditor.new('attachment.pdf')
    response = annotation_editor.get_attachment(attachment_index=1)
    assert_instance_of(Hash, response)
  end

  # Get list of all the attachments in pdf
  def test_get_all_attachments
    annotation_editor = Aspose::Cloud::Pdf::AnnotationEditor.new('attachment.pdf')
    response = annotation_editor.get_all_attachments()
    assert_instance_of(Array, response)
  end

  # Download a specfied Attachment from a PDF document
  def test_download_attachment
    annotation_editor = Aspose::Cloud::Pdf::AnnotationEditor.new('attachment.pdf')
    response = annotation_editor.download_attachment(attachment_index=1)
    assert_equal true, File.exist?('../Output/License.rtf')
  end

  # Get total number of Links in a Pdf document
  def test_get_links_count
    annotation_editor = Aspose::Cloud::Pdf::AnnotationEditor.new('Test.pdf')
    response = annotation_editor.get_links_count(page_number=1)
    assert_equal true, response>=0
  end

  # Get a specfied link on a specified document page
  def test_get_link
    annotation_editor = Aspose::Cloud::Pdf::AnnotationEditor.new('Test.pdf')
    response = annotation_editor.get_link(page_number=2, link_index=1)
    assert_instance_of(Hash, response)
  end

  # Get list of all the links on a specified document page
  def test_get_all_links
    annotation_editor = Aspose::Cloud::Pdf::AnnotationEditor.new('Test.pdf')
    response = annotation_editor.get_all_links(page_number=1)
    assert_instance_of(Array, response)
  end

  # Checks whether selected bookmark is parent or child Gets a specfied child Bookmark for selected parent bookmark in Pdf document
  def test_is_child_bookmark
    annotation_editor = Aspose::Cloud::Pdf::AnnotationEditor.new('test_annotation.pdf')
    response = annotation_editor.is_child_bookmark(bookmark_index=1)
    assert_instance_of(Hash, response)
  end
end