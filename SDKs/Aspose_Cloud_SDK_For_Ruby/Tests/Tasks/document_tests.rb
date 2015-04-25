
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

  # Retrieve Project Properties
  def test_get_properties
    document = Aspose::Cloud::Tasks::Document.new('test_tasks.mpp')
    response = document.get_properties()
    assert_instance_of(Array, response)
  end

  # Gell all tasks
  def test_get_tasks
    document = Aspose::Cloud::Tasks::Document.new('test_tasks.mpp')
    response = document.get_tasks()
    assert_instance_of(Array, response)
  end

  # Retrieve Task Information
  def test_get_task
    document = Aspose::Cloud::Tasks::Document.new('test_tasks.mpp')
    response = document.get_task(task_id=1)
    assert_instance_of(Hash, response)
  end

  # Add a Task to Project
  def test_add_task
    document = Aspose::Cloud::Tasks::Document.new('test_tasks.mpp')
    response = document.add_task(task_name='Blog Post', before_task_id=1)
    assert_instance_of(Hash, response)
  end

  # Delete a Task from Project
  def test_delete_task
    document = Aspose::Cloud::Tasks::Document.new('test_tasks.mpp')
    response = document.delete_task(task_id=1)
    assert_equal true, response
  end

  # Get all Links
  def test_get_links
    document = Aspose::Cloud::Tasks::Document.new('test_tasks.mpp')
    response = document.get_links()
    assert_instance_of(Array, response)
  end

  # Add a Task Link to Project
  def test_add_link
    document = Aspose::Cloud::Tasks::Document.new('test_tasks.mpp')
    response = document.add_link(link='NewProductDev.mpp/taskLinks/1', index=1, predecessor_uid=1, successor_uid=2, link_type='StartToStart', lag=0, lag_format='Day')
    assert_equal true, response
  end

  # Delete a Task Link
  def test_delete_link
    document = Aspose::Cloud::Tasks::Document.new('test_tasks.mpp')
    response = document.delete_link(link_index=1)
    assert_equal true, response
  end  

  # Get all Outline Codes
  def test_get_outline_codes
    document = Aspose::Cloud::Tasks::Document.new('test_tasks.mpp')
    response = document.get_outline_codes()
    assert_instance_of(Hash, response)
  end

  # Retrieve Outline Code Information
  def test_get_outline_code
    document = Aspose::Cloud::Tasks::Document.new('test_tasks.mpp')
    response = document.get_outline_code(outline_code_id=1)
    assert_instance_of(Hash, response)
  end

  # Delete Outline Code
  def test_delete_outline_code
    document = Aspose::Cloud::Tasks::Document.new('test_tasks.mpp')
    response = document.delete_outline_code(outline_code_id=1)
    assert_equal true, response
  end

  # Get all Extended Attributes
  def test_get_extended_attributes
    document = Aspose::Cloud::Tasks::Document.new('test_tasks.mpp')
    response = document.get_extended_attributes()
    assert_instance_of(Hash, response)
  end

  # Retrieve Extended Attribute Information
  def test_get_extended_attribute
    document = Aspose::Cloud::Tasks::Document.new('test_tasks.mpp')
    response = document.get_extended_attribute(attribute_id=1)
    assert_instance_of(Hash, response)
  end

  # Delete Extended Attribute
  def test_delete_extended_attribute
    document = Aspose::Cloud::Tasks::Document.new('test_tasks.mpp')
    response = document.delete_extended_attribute(attribute_id=1)
    assert_equal true, response
  end 
end