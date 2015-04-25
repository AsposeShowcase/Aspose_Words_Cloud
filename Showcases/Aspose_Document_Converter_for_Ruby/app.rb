require "sinatra"
require "asposecloudsdk"


get "/" do
  erb :index
end

post "/convert" do
  unless params[:input_file] && (tmpfile = params[:input_file][:tempfile]) && (name = params[:input_file][:filename])
    return "No file selected"
  end

  app_sid = "67xxxxxd-xxx2-7xx3-1xx7-2xxxxxxxxxxd"
  app_key = "xxxxxxxxxxxxxxxxxxxxxxxxxxx"
  Aspose::Cloud::Common::AsposeApp.new(app_sid, app_key)

  request_url = "http://api.aspose.com/v1.1/"
  if /^.+\.(docx|doc|rtf)$/ =~ params[:input_file][:filename]
    request_url += "words/convert"
  elsif /^.+\.(xlsx|xls)$/ =~ params[:input_file][:filename]
    request_url += "cells/convert"
  elsif /^.+\.(pptx|ppt)$/ =~ params[:input_file][:filename]
    request_url += "slides/convert"
  elsif /^.+\.(pdf)$/ =~ params[:input_file][:filename]
    request_url += "pdf/convert"
  else
    return "Error: wrong file selected"
  end
  request_url += "?format=" + params[:format]
  signed_request_url = Aspose::Cloud::Common::Utils.sign(request_url)

  converted_file_stream = RestClient.put(signed_request_url, params[:input_file][:tempfile])
  response.headers["Content-Type"] = "application/octect-stream"
  response.headers["Content-Disposition"] = "attachment; filename=" + params[:input_file][:filename] + "." + params[:format]
  return converted_file_stream

end
