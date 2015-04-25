# Copyright (c) Aspose 2002-2014. All Rights Reserved.
#
# LICENSE: This program is free software; you can redistribute it and/or
# modify it under the terms of the GNU General Public License
# as published by the Free Software Foundation; either version 3
# of the License, or (at your option) any later version.
# This program is distributed in the hope that it will be useful,
# but WITHOUT ANY WARRANTY; without even the implied warranty of
# MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
# GNU General Public License for more details.
# You should have received a copy of the GNU General Public License
# along with this program. If not, see <http://opensource.org/licenses/gpl-3.0.html>;.
#
# @package Aspose_Cloud_SDK_For_Ruby
# @author  Assad Mahmood Qazi <assad.mahmood@aspose.com>
# @link    https://github.com/asposeforcloud/Aspose_Cloud_SDK_For_Ruby/tree/revamp

# This class allows you to set the AppSID and AppKey values you get upon signing up
module Aspose
  module Cloud
    module Common
      class AsposeApp

        def self.app_sid
          @@app_sid
        end

        def self.app_sid= app_sid
          @@app_sid = app_sid
        end

        def self.app_key
          @@app_key
        end

        def self.app_key= app_key
          @@app_key = app_key
        end

        def self.output_location
          @@output_location
        end

        def self.output_location= output_location
          @@output_location = output_location
        end

      end
    end
  end
end