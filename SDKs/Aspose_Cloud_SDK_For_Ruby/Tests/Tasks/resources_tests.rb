
require 'test/unit'
require_relative '../../lib/asposecloud'

class ResourcesTests < Test::Unit::TestCase

  def setup
    file = File.read('../setup.json')
    data = JSON.parse(file)

    Aspose::Cloud::Common::AsposeApp.app_key = data['app_key']
    Aspose::Cloud::Common::AsposeApp.app_sid = data['app_sid']
    Aspose::Cloud::Common::AsposeApp.output_location = data['output_location']
    Aspose::Cloud::Common::Product.set_base_product_uri(data['product_uri'])
  end

  # Get all Resources
  def test_get_resources
    resources = Aspose::Cloud::Tasks::Resources.new('test_tasks.mpp')
    response = resources.get_resources()
    assert_instance_of(Array, response)
  end

  # Retrieve Resource Information
  def test_get_resource
    resources = Aspose::Cloud::Tasks::Resources.new('test_tasks.mpp')
    response = resources.get_resource(resource_id=1)
    assert_instance_of(Hash, response)
  end

  # Add a Resource To Project
  def test_add_resource
    resources = Aspose::Cloud::Tasks::Resources.new('test_tasks.mpp')
    response = resources.add_resource(resource_name='Mark', after_resource_id=1)
    assert_instance_of(Hash, response)
  end

  # Delete Resource From Project
  def test_delete_resource
    resources = Aspose::Cloud::Tasks::Resources.new('test_tasks.mpp')
    response = resources.delete_resource(resource_id=3)
    assert_equal true, response
  end
end