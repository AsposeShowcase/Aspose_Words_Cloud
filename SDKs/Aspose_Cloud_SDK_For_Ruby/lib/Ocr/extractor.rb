module Aspose
  module Cloud
    module OCR
      class Extractor

=begin
  Extract OCR or HOCR Text from Images
  @param string url URL of the document.
  @param string language Language of document to recogniize.
  @param boolean use_default_dictionaries Allows to correct text after recognition using default dictionaries.
  @param number rectX X position of rectangle.
  @param number rectY Y position of rectangle.
  @param number rectWidth Width of rectangle.
  @param number rectHeight Height of rectangle.
=end        
        def extract_text(*args)
          case args.size
          when 2
            image_file_name,folder = *args
              str_uri = ''
              if(folder=='' || folder==nil)
                str_uri += Aspose::Cloud::Common::Product.product_uri + '/ocr/' + image_file_name.to_s + '/recognize'
              else
                str_uri += Aspose::Cloud::Common::Product.product_uri + '/ocr/' + image_file_name.to_s + '/recognize?folder=' + folder.to_s
              end
          
              signed_uri = Aspose::Cloud::Common::Utils.sign(str_uri)

              response = RestClient.get(signed_uri, :accept=> 'application/json')
              json = JSON.parse(response)
              return json
          when 4
            image_file_name,folder,language,use_default_dictionaries = *args
              str_uri = ''
              if(folder=='' || folder==nil)
                str_uri += Aspose::Cloud::Common::Product.product_uri + '/ocr/' + image_file_name.to_s + '/recognize?language=' + language.to_s + '&useDefaultDictionaries=' + use_default_dictionaries.to_s
              else
                str_uri += Aspose::Cloud::Common::Product.product_uri + '/ocr/' + image_file_name.to_s + '/recognize?language=' + language.to_s + '&useDefaultDictionaries=' + use_default_dictionaries.to_s + '&folder=' + folder.to_s
              end
          
              signed_uri = Aspose::Cloud::Common::Utils.sign(str_uri)
        
              response = RestClient.get(signed_uri, :accept=> 'application/json')
              json = JSON.parse(response)
              return json
          when 1
            image_file_name = args[0]
              str_uri = Aspose::Cloud::Common::Product.product_uri + '/ocr/'	+ image_file_name + '/recognize?useDefaultDictionaries=true'
              signed_uri = Aspose::Cloud::Common::Utils.sign(str_uri)
              response = RestClient.get(signed_uri, :accept=> 'application/json')
              json = JSON.parse(response)
              return json
          when 8
            image_file_name, language , use_default_dictionaries, x , y, height, width, folder = *args
              str_uri = Aspose::Cloud::Common::Product.product_uri
              str_uri	+= '/ocr/'
              str_uri	+= image_file_name
              str_uri	+= '/recognize?language='
              str_uri	+= language
              str_uri	+= ((x >= 0 && y >= 0 && width > 0 && height > 0) ? '&rectX=' + x.to_s	+ '&rectY=' + y.to_s + '&rectWidth=' + width.to_s + '&rectHeight=' + height.to_s : '') 
              str_uri	+= '&useDefaultDictionaries='
              str_uri	+= ((use_default_dictionaries) ? 'true' : 'false')
              str_uri	+=((folder=='') ? '' : '&folder=' + folder)

              signed_uri = Aspose::Cloud::Common::Utils.sign(str_uri)

              response = RestClient.get(signed_uri, :accept=> 'application/json')
              json = JSON.parse(response)
              return json
          when 3
            stream,language,use_default_dictionaries = *args
              str_uri = Aspose::Cloud::Common::Product.product_uri + '/ocr/recognize?language=' + language.to_s + '&useDefaultDictionaries=' + use_default_dictionaries.to_s
              signed_uri = Aspose::Cloud::Common::Utils.sign(str_uri)
              response = RestClient.post(signed_uri,stream, :accept=> 'application/json')
              json = JSON.parse(response)
              return json
          end
        end

=begin
  Extract OCR or HOCR Text from Images without using Storage
  @param string local_file Path of the file.
  @param string language Language of document to recogniize.
  @param boolean use_default_dictionaries Allows to correct text after recognition using default dictionaries. 
=end
        def extract_text_from_local_file(local_file,language,use_default_dictionaries)
            str_uri = Aspose::Cloud::Common::Product.product_uri + '/ocr/recognize?language=' + language.to_s + '&useDefaultDictionaries=' + use_default_dictionaries.to_s
            file_stream = File.binread(local_file)
            signed_uri = Aspose::Cloud::Common::Utils.sign(str_uri)
            response = RestClient.post(signed_uri,file_stream, :accept=> 'application/json')
            json = JSON.parse(response)
            return json
        end

=begin
  Extract OCR or HOCR Text from Image URL
  @param string url URL of the document.
  @param string language Language of document to recogniize.
  @param boolean use_default_dictionaries Allows to correct text after recognition using default dictionaries. 
=end
        def extract_text_from_url(url,language,use_default_dictionaries)
            str_uri = Aspose::Cloud::Common::Product.product_uri + '/ocr/recognize?url=' + url.to_s + '&language=' + language.to_s + '&useDefaultDictionaries=' + use_default_dictionaries.to_s
            signed_uri = Aspose::Cloud::Common::Utils.sign(str_uri)
            response = RestClient.post(signed_uri, '', {:accept => 'application/json'})
            json=JSON.parse(response)
            return json
        end
      end
    end
  end
end