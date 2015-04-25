module Aspose
  module Cloud
    module Pdf
      class Document
        def initialize(filename)
          @filename = filename
          raise 'filename not specified.' if filename.empty?
          @base_uri =  Aspose::Cloud::Common::Product.product_uri + '/pdf/' + @filename
        end

        def get_page_count(folder_name = '', storage_type = 'Aspose', storage_name = '')
          str_uri = "#{@base_uri}/pages"
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)

          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)
          JSON.parse(RestClient.get(signed_str_uri, {:accept=>'application/json'}))['Pages']['List'].length
        end
    
        def append_document(append_file, startpage = 0, endpage = 0, folder_name = '', storage_type = 'Aspose', storage_name = '')
          raise 'append_file not specified.' if append_file.empty?
          str_uri = "#{@base_uri}/appendDocument"
          qry = Hash.new
          qry[:appendFile] = append_file
          qry[:startPage] = startpage unless startpage == 0
          qry[:endPage] = endpage unless endpage == 0
          str_uri = Aspose::Cloud::Common::Utils.build_uri(str_uri,qry)
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)

          response = RestClient.post(signed_str_uri, '', {:accept=>'application/json'})
          json = JSON.parse(response)
          json['Code'] == 200 ? true : false          
        end

        def merge_documents(merged_filename, source_files, folder_name = '', storage_type = 'Aspose', storage_name = '')
          raise 'merged_filename not specified.' if merged_filename.empty?
          raise 'source_files not specified.' if source_files.nil? || source_files.length < 2

          json_data = JSON.generate( 'List'=>source_files )
          str_uri = "#{Aspose::Cloud::Common::Product.product_uri}/pdf/#{merged_filename}/merge"
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)

          JSON.parse(RestClient.put(signed_str_uri,json_data,{ :content_type=>:json, :accept=>'application/json' }))['Status'] == 'OK' ? true : false
        end
    
        def get_form_field_count(folder_name = '', storage_type = 'Aspose', storage_name = '')
          str_uri = "#{@base_uri}/fields"
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)
          JSON.parse(RestClient.get(signed_str_uri, {:accept=>'application/json'}))['Fields']['List'].length
        end

        def get_form_fields(folder_name = '', storage_type = 'Aspose', storage_name = '')
          str_uri = "#{@base_uri}/fields"
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)
          JSON.parse(RestClient.get(signed_str_uri, {:accept=>'application/json'}))['Fields']['List']
        end

        def get_form_field(field_name, folder_name = '', storage_type = 'Aspose', storage_name = '')
          raise 'field_name not specified.' if field_name.empty?

          str_uri = "#{@base_uri}/fields/#{field_name}"
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)
          JSON.parse(RestClient.get(signed_str_uri, {:accept=>'application/json'}))['Field']
        end

        def update_form_field(field_name, field_type, field_value, folder_name = '', storage_type = 'Aspose', storage_name = '')
          raise 'field_name not specified.' if field_name.empty?
          raise 'field_type not specified.' if field_type.empty?
          raise 'field_value not specified.' if field_value.empty?

          str_uri = "#{@base_uri}/fields/#{field_name}"
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)

          data = Hash['Name' => field_name, 'Type' => field_type, 'Values' => Array.new('field_value')]
          json_data = JSON.generate(data)

          response = RestClient.put(signed_str_uri, json_data, {:content_type=>:json, :accept=>'application/json'})
          json = JSON.parse(response)
          json['Code'] == 200 ? json['Field'] : nil
        end

        def create_from_html (html_filename, folder_name = '', storage_type = 'Aspose', storage_name = '')
          raise 'html_filename not specified.' if html_filename.empty?

          str_uri = "#{@base_uri}"
          qry = Hash.new
          qry[:templatefile] = html_filename
          qry[:templatetype] = 'html'
          str_uri = Aspose::Cloud::Common::Utils.build_uri(str_uri,qry)
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)
          response = RestClient.put(signed_str_uri,'',{:accept=>'application/json'})          
          json = JSON.parse(response)
          json['Code'] == 200 ? true : false
        end

        def create_from_xml (xslt_filename, xml_filename, folder_name = '', storage_type = 'Aspose', storage_name = '')
          raise 'xslt_filename not specified.' if xslt_filename.empty?
          raise 'xml_filename not specified.' if xml_filename.empty?

          str_uri = "#{@base_uri}"
          qry = Hash.new
          qry[:templatefile] = xslt_filename
          qry[:datafile] = xml_filename
          qry[:templatetype] = 'xml'
          str_uri = Aspose::Cloud::Common::Utils.build_uri(str_uri,qry)
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)
          response = RestClient.put(signed_str_uri,'',{:accept=>'application/json'})
          json = JSON.parse(response)
          json['Code'] == 200 ? true : false          
        end

        def create_from_jpeg(jpeg_filename, folder_name = '', storage_type = 'Aspose', storage_name = '')
          raise 'jpeg_filename not specified.' if jpeg_filename.empty?

          str_uri = "#{@base_uri}"
          qry = Hash.new
          qry[:templatefile] = jpeg_filename
          qry[:templatetype] = 'jpeg'
          str_uri = Aspose::Cloud::Common::Utils.build_uri(str_uri,qry)
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)
          response_stream = RestClient.put(signed_str_uri,'',{:accept=>'application/json'})
          valid_output = Aspose::Cloud::Common::Utils.validate_output(response_stream)
          valid_output.empty? ? Aspose::Cloud::Common::Utils.download_file(@filename,@filename,folder_name,storage_name,storage_type) : valid_output
        end

        def create_from_svg(svg_filename, folder_name = '', storage_type = 'Aspose', storage_name = '')
          raise 'svg_filename not specified.' if svg_filename.empty?

          str_uri = "#{@base_uri}"
          qry = Hash.new
          qry[:templatefile] = svg_filename
          qry[:templatetype] = 'svg'
          str_uri = Aspose::Cloud::Common::Utils.build_uri(str_uri,qry)
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)
          response_stream = RestClient.put(signed_str_uri,'',{:accept=>'application/json'})
          valid_output = Aspose::Cloud::Common::Utils.validate_output(response_stream)
          valid_output.empty? ? Aspose::Cloud::Common::Utils.download_file(@filename,@filename,folder_name,storage_name,storage_type) : valid_output
        end

        def create_from_tiff(tiff_filename, folder_name = '', storage_type = 'Aspose', storage_name = '')
          raise 'tiff_filename not specified.' if tiff_filename.empty?

          str_uri = "#{@base_uri}"
          qry = Hash.new
          qry[:templatefile] = tiff_filename
          qry[:templatetype] = 'tiff'
          str_uri = Aspose::Cloud::Common::Utils.build_uri(str_uri,qry)
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)
          response_stream = RestClient.put(signed_str_uri,'',{:accept=>'application/json'})
          valid_output = Aspose::Cloud::Common::Utils.validate_output(response_stream)
          valid_output.empty? ? Aspose::Cloud::Common::Utils.download_file(@filename,@filename,folder_name,storage_name,storage_type) : valid_output
        end

        def create_empty_pdf(folder_name = '', storage_type = 'Aspose', storage_name = '')
          str_uri = "#{@base_uri}"
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)
          response_stream = RestClient.put(signed_str_uri,'',{:accept=>'application/json'})
          valid_output = Aspose::Cloud::Common::Utils.validate_output(response_stream)
          valid_output.empty? ? Aspose::Cloud::Common::Utils.download_file(@filename,@filename,folder_name,storage_name,storage_type) : valid_output
        end

        def add_new_page(folder_name = '', storage_type = 'Aspose', storage_name = '')
          str_uri = "#{@base_uri}/pages"
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)
          response_stream = RestClient.put(signed_str_uri,'',{:accept=>'application/json'})
          valid_output = Aspose::Cloud::Common::Utils.validate_output(response_stream)
          valid_output.empty? ? Aspose::Cloud::Common::Utils.download_file(@filename,@filename,folder_name,storage_name,storage_type) : valid_output
        end

        def delete_page(page_number, folder_name = '', storage_type = 'Aspose', storage_name = '')
          raise 'page_number not specified.' if page_number.nil?

          str_uri = "#{@base_uri}/pages/#{page_number}"
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)
          response_stream = RestClient.delete(signed_str_uri,{:accept=>'application/json'})
          valid_output = Aspose::Cloud::Common::Utils.validate_output(response_stream)
          valid_output.empty? ? Aspose::Cloud::Common::Utils.download_file(@filename,@filename,folder_name,storage_name,storage_type) : valid_output
        end

        def move_page(page_number, new_location, folder_name = '', storage_type = 'Aspose', storage_name = '')
          raise 'page_number not specified.' if page_number.nil?
          raise 'new_location not specified.' if new_location.nil?

          str_uri = "#{@base_uri}/pages/#{page_number}/movepage?newindex=#{new_location}"
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)
          response_stream = RestClient.post(signed_str_uri, '', {:accept=>'application/json'})
          valid_output = Aspose::Cloud::Common::Utils.validate_output(response_stream)
          valid_output.empty? ? Aspose::Cloud::Common::Utils.download_file(@filename,@filename,folder_name,storage_name,storage_type) : valid_output
        end

        def replace_image_stream(page_number, image_index, image_stream, folder_name = '', storage_type = 'Aspose', storage_name = '')
          raise 'page_number not specified.' if page_number.nil?
          raise 'image_index not specified.' if image_index.nil?
          raise 'image_stream not specified.' if image_stream.nil?

          str_uri = "#{@base_uri}/pages/#{page_number}/images/#{image_index}"
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)
          response_stream = RestClient.post(signed_str_uri, image_stream, {:accept=>'application/json'})
          valid_output = Aspose::Cloud::Common::Utils.validate_output(response_stream)
          valid_output.empty? ? Aspose::Cloud::Common::Utils.download_file(@filename,@filename,folder_name,storage_name,storage_type) : valid_output
        end

        def replace_image_file(page_number, image_index, image_file, folder_name = '', storage_type = 'Aspose', storage_name = '')
          raise 'page_number not specified.' if page_number.nil?
          raise 'image_index not specified.' if image_index.nil?
          raise 'image_file not specified.' if image_file.empty?

          str_uri = "#{@base_uri}/pages/#{page_number}/images/#{image_index}?imagefile=#{image_file}"
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)
          response_stream = RestClient.post(signed_str_uri, '', {:accept=>'application/json'})
          valid_output = Aspose::Cloud::Common::Utils.validate_output(response_stream)
          valid_output.empty? ? Aspose::Cloud::Common::Utils.download_file(@filename,@filename,folder_name,storage_name,storage_type) : valid_output
        end

        def get_document_property(property_name, folder_name = '', storage_type = 'Aspose', storage_name = '')
          raise 'property_name not specified.' if property_name.empty?

          str_uri = "#{@base_uri}/documentProperties/#{property_name}"
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)
          JSON.parse(RestClient.get(signed_str_uri, {:accept=>'application/json'}))['DocumentProperty']
        end

        def get_document_properties(folder_name = '', storage_type = 'Aspose', storage_name = '')
          str_uri = "#{@base_uri}/documentProperties"
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)
          JSON.parse(RestClient.get(signed_str_uri, {:accept=>'application/json'}))['DocumentProperties']['List']
        end

        def set_document_property(property_name, property_value, folder_name = '', storage_type = 'Aspose', storage_name = '')
          raise 'property_name not specified.' if property_name.empty?
          raise 'property_value not specified.' if property_value.empty?

          json_data = JSON.generate('Value'=>property_value)

          str_uri = "#{@base_uri}/documentProperties/#{property_name}"
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)
          JSON.parse(RestClient.put(signed_str_uri, json_data, {:content_type=>:json, :accept=>'application/json'}))['DocumentProperty']
        end

        def remove_all_properties(folder_name = '', storage_type = 'Aspose', storage_name = '')
          str_uri = "#{@base_uri}/documentProperties"
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)
          JSON.parse(RestClient.delete(signed_str_uri, {:accept=>'application/json'}))['Code'] == 200 ? true : false
        end

        def split_all_pages(folder_name = '', storage_type = 'Aspose', storage_name = '')
          str_uri = "#{@base_uri}/split"
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)
          response = RestClient.post(signed_str_uri, '', {:content_type=>:json, :accept=>'application/json'})
          json = JSON.parse(response)
          i=1
          json['Result']['Documents'].each { |split_page|
            source_filename = Aspose::Cloud::Common::Utils.get_filename(@filename)
            split_filename = File.basename(split_page['Href'])
            str_uri = Aspose::Cloud::Common::Product.product_uri + '/storage/file/' + split_filename
            signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)
            response_stream = RestClient.get(signed_str_uri, {:accept=>'application/json'})
            filename = "#{source_filename}_#{i}.pdf"
            output_path = "#{Aspose::Cloud::Common::AsposeApp.output_location}#{filename}"
            Aspose::Cloud::Common::Utils.save_file(response_stream,output_path)
            i += 1
          }
        end

        def split_pages(from, to, folder_name = '', storage_type = 'Aspose', storage_name = '')
          raise 'from not specified.' if from.nil?
          raise 'to not specified.' if to.nil?

          str_uri = "#{@base_uri}/split?from=#{from}&to=#{to}"
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)
          response = RestClient.post(signed_str_uri, '', {:content_type=>:json, :accept=>'application/json'})
          json = JSON.parse(response)
          i=1
          json['Result']['Documents'].each { |split_page|
            source_filename = Aspose::Cloud::Common::Utils.get_filename(@filename)
            split_filename = File.basename(split_page['Href'])
            str_uri = Aspose::Cloud::Common::Product.product_uri + '/storage/file/' + split_filename
            signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)
            response_stream = RestClient.get(signed_str_uri, {:accept=>'application/json'})
            filename = "#{source_filename}_#{i}.pdf"
            output_path = "#{Aspose::Cloud::Common::AsposeApp.output_location}#{filename}"
            Aspose::Cloud::Common::Utils.save_file(response_stream,output_path)
            i += 1
          }
        end

        def split_pages_to_any_format(from, to, save_format, folder_name = '', storage_type = 'Aspose', storage_name = '')
          raise 'from not specified.' if from.nil?
          raise 'to not specified.' if to.nil?
          raise 'save_format not specified.' if save_format.empty?

          str_uri = "#{@base_uri}/split?from=#{from}&to=#{to}&format=#{save_format}"
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)
          response = RestClient.post(signed_str_uri, '', {:content_type=>:json, :accept=>'application/json'})
          json = JSON.parse(response)
          i=1
          json['Result']['Documents'].each { |split_page|
            source_filename = Aspose::Cloud::Common::Utils.get_filename(@filename)
            split_filename = File.basename(split_page['Href'])
            str_uri = Aspose::Cloud::Common::Product.product_uri + '/storage/file/' + split_filename
            signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)
            response_stream = RestClient.get(signed_str_uri, {:accept=>'application/json'})
            filename = "#{source_filename}_#{i}.#{save_format}"
            output_path = "#{Aspose::Cloud::Common::AsposeApp.output_location}#{filename}"
            Aspose::Cloud::Common::Utils.save_file(response_stream,output_path)
            i += 1
          }
        end

        def add_stamp(page_number, post_data, folder_name = '', storage_type = 'Aspose', storage_name = '')
          raise 'page_number not specified.' if page_number.nil?
          raise 'post_data not specified.' if post_data.empty?

          str_uri = "#{@base_uri}/pages/#{page_number}/stamp"
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,folder_name,storage_name,storage_type)
          signed_str_uri = Aspose::Cloud::Common::Utils.sign(str_uri)

          response = RestClient.put(signed_str_uri, post_data, {:content_type=>:json, :accept=>'application/json'})
          json = JSON.parse(response)
          if json['Code'] == 200
            Aspose::Cloud::Common::Utils.download_file(@filename,@filename,folder_name,storage_name,storage_type)
          end
        end
      end
    end
  end
end