
require 'test/unit'
require_relative '../../lib/asposecloud'

class AssignmentsTests < Test::Unit::TestCase

  def setup
    file = File.read('../setup.json')
    data = JSON.parse(file)

    Aspose::Cloud::Common::AsposeApp.app_key = data['app_key']
    Aspose::Cloud::Common::AsposeApp.app_sid = data['app_sid']
    Aspose::Cloud::Common::AsposeApp.output_location = data['output_location']
    Aspose::Cloud::Common::Product.set_base_product_uri(data['product_uri'])
  end

  # Get all Assignments
  def test_get_assignments
    assignments = Aspose::Cloud::Tasks::Assignments.new('test_tasks.mpp')
    response = assignments.get_assignments()
    assert_instance_of(Array, response)
  end

  # Get a particular Assignment
  def test_get_assignment
    assignments = Aspose::Cloud::Tasks::Assignments.new('test_tasks.mpp')
    response = assignments.get_assignment(assignment_id=1)
    assert_instance_of(Hash, response)
  end

  # Add Assignment to Project 
  def test_add_assignment
    assignments = Aspose::Cloud::Tasks::Assignments.new('test_tasks.mpp')
    response = assignments.add_assignment(task_id=1, resource_id=1, units=0.5)
    assert_instance_of(Hash, response)
  end

  # Delete an Assignment from a Project
  def test_delete_assignment
    assignments = Aspose::Cloud::Tasks::Assignments.new('test_tasks.mpp')
    response = assignments.delete_assignment(assignment_id=3)
    assert_equal true, response
  end  
end