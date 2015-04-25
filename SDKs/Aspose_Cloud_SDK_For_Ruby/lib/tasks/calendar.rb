module Aspose
  module Cloud
    module Tasks
      class Calendar
        def initialize(filename)
          @filename = filename
          raise 'filename not specified.' if filename.empty?
          @base_uri =  Aspose::Cloud::Common::Product.product_uri + '/tasks/' + @filename
        end

=begin
 Get all Calendars form Project 
=end
        def get_calendars(folder_name = '', storage_type = 'Aspose', storage_name = '')
          str_uri = "#{@base_uri}/calendars"
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)
          JSON.parse(RestClient.get(signed_str_uri, {:accept=>'application/json'}))['Calendars']['List']
        end

=begin
 Get a Particular Calendar form Project 
 @param number calendar_id The id of the calendar.
=end
        def get_calendar(calendar_id, folder_name = '', storage_type = 'Aspose', storage_name = '')
          raise 'calendar_id not specified.' if calendar_id.nil?

          str_uri = "#{@base_uri}/calendars/#{calendar_id}"
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)
          JSON.parse(RestClient.get(signed_str_uri, {:accept=>'application/json'}))['Calendar']
        end

=begin
 Add Calendar to Project
 @param json json_data Provide data in json format.
=end
        def add_calendar(json_data, folder_name = '', storage_type = 'Aspose', storage_name = '')
          raise 'json_data not specified.' if json_data.empty?

          str_uri = "#{@base_uri}/calendars"
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)
          json = JSON.parse(Aspose::Cloud::Common::Utils.process_command(signed_str_uri,'POST','JSON',json_data))
          json['Status'] == 'Created' ? json['CalendarItem'] : nil
        end

=begin
 Delete a Calendar from Project
 @param number calendar_id The id of the calendar.
=end
        def delete_calendar(calendar_id, folder_name = '', storage_type = 'Aspose', storage_name = '')
          raise 'calendar_id not specified.' if calendar_id.nil?

          str_uri = "#{@base_uri}/calendars/#{calendar_id}"
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)
          json = JSON.parse(RestClient.delete(signed_str_uri, {:accept=>'application/json'}))
          json['Code'] == 200 ? true : false
        end
      end
    end
  end
end