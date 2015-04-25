
require 'test/unit'
require_relative '../../lib/asposecloud'

class CalendarTests < Test::Unit::TestCase

  def setup
    file = File.read('../setup.json')
    data = JSON.parse(file)

    Aspose::Cloud::Common::AsposeApp.app_key = data['app_key']
    Aspose::Cloud::Common::AsposeApp.app_sid = data['app_sid']
    Aspose::Cloud::Common::AsposeApp.output_location = data['output_location']
    Aspose::Cloud::Common::Product.set_base_product_uri(data['product_uri'])
  end

  # Get all Calendars from Project
  def test_get_calendars
    calendar = Aspose::Cloud::Tasks::Calendar.new('test_tasks.mpp')
    response = calendar.get_calendars()
    assert_instance_of(Array, response)
  end

  # Get a particular Calendar from Project
  def test_get_calendar
    calendar = Aspose::Cloud::Tasks::Calendar.new('test_tasks.mpp')
    response = calendar.get_calendar(calendar_id=5)
    assert_instance_of(Hash, response)
  end

  # Add a Calendar to Project
  def test_add_calendar
    json_data = '{"Name":"ADDED CALENDAR","Uid":0,"Days":[{"DayType":1,"DayWorking":false,"FromDate":"0001-01-01T00:00:00","ToDate":"0001-01-01T00:00:00","WorkingTimes":[]},{"DayType":2,"DayWorking":true,"FromDate":"0001-01-01T00:00:00","ToDate":"0001-01-01T00:00:00","WorkingTimes":[{"FromTime":"2010-01-01T09:00:00","ToTime":"2010-01-01T12:00:00"},{"FromTime":"2010-01-01T13:00:00","ToTime":"2010-01-01T18:00:00"}]},{"DayType":3,"DayWorking":true,"FromDate":"0001-01-01T00:00:00","ToDate":"0001-01-01T00:00:00","WorkingTimes":[{"FromTime":"2010-01-01T09:00:00","ToTime":"2010-01-01T12:00:00"},{"FromTime":"2010-01-01T13:00:00","ToTime":"2010-01-01T18:00:00"}]},{"DayType":4,"DayWorking":true,"FromDate":"0001-01-01T00:00:00","ToDate":"0001-01-01T00:00:00","WorkingTimes":[{"FromTime":"2010-01-01T09:00:00","ToTime":"2010-01-01T12:00:00"},{"FromTime":"2010-01-01T13:00:00","ToTime":"2010-01-01T18:00:00"}]},{"DayType":5,"DayWorking":true,"FromDate":"0001-01-01T00:00:00","ToDate":"0001-01-01T00:00:00","WorkingTimes":[{"FromTime":"2010-01-01T09:00:00","ToTime":"2010-01-01T12:00:00"},{"FromTime":"2010-01-01T13:00:00","ToTime":"2010-01-01T18:00:00"}]},{"DayType":6,"DayWorking":true,"FromDate":"0001-01-01T00:00:00","ToDate":"0001-01-01T00:00:00","WorkingTimes":[{"FromTime":"2010-01-01T09:00:00","ToTime":"2010-01-01T12:00:00"},{"FromTime":"2010-01-01T13:00:00","ToTime":"2010-01-01T18:00:00"}]},{"DayType":7,"DayWorking":true,"FromDate":"0001-01-01T00:00:00","ToDate":"0001-01-01T00:00:00","WorkingTimes":[{"FromTime":"2010-01-01T09:00:00","ToTime":"2010-01-01T13:00:00"}]}],"Exceptions":[],"IsBaseCalendar":true,"BaseCalendar":null,"IsBaselineCalendar":false}'
    calendar = Aspose::Cloud::Tasks::Calendar.new('test_tasks.mpp')
    response = calendar.add_calendar(json_data)
    assert_instance_of(Hash, response)
  end

  # Delete a Calendar from Project
  def test_delete_calendar
    calendar = Aspose::Cloud::Tasks::Calendar.new('test_tasks.mpp')
    response = calendar.delete_calendar(calendar_id=5)
    assert_equal true, response
  end    
end