module Aspose
  module Cloud
    module Barcode
      class Reader
        def initialize(filename)
          @filename = filename
          raise 'Base file is not specified' if @filename.empty?
        end

=begin
  Read Barcode from Aspose Cloud Storage
  @param string symbology Type of barcode.
=end
        def read(symbology='', remote_folder='', storage_type='Aspose', storage_name='')

            str_uri = "#{Aspose::Cloud::Common::Product.product_uri}/barcode/#{@filename}/recognize"
            str_uri = "#{str_uri}?type=#{symbology}" unless symbology.empty?
            str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,remote_folder,storage_name,storage_type)

            signed_uri = Aspose::Cloud::Common::Utils.sign(str_uri)
            response = RestClient.get(signed_uri, :accept => 'application/json')
            json = JSON.parse(response)
            json['Code'] == 200 ? json['Barcodes'] : nil
        end

=begin
  Read Barcode from Local Image
  @param string local_image Path of the local image.
  @param string symbology Type of barcode.
  @param string format Returns an image in specified format.  
=end
        def read_from_local_image(local_image, remote_folder='', symbology='', format='', storage_type='Aspose', storage_name='')
            raise 'local image file not provided.' if local_image.empty?

            folder = Aspose::Cloud::AsposeStorage::Folder.new
            folder.upload_file(local_image, remote_folder, storage_type, storage_name)

            readr(File.basename(local_image), remote_folder, symbology, format)
        end

=begin
  Read Barcode from Aspose Cloud Storage
  @param string remote_image_name Name of the image.
  @param string symbology Type of barcode.
  @param string format Returns an image in specified format.  
=end
        def readr(remote_image_name, remote_folder='', symbology='', format='', storage_type='Aspose', storage_name='')
          raise 'remote image file not provided.' if remote_image_name.empty?

          str_uri = "#{Aspose::Cloud::Common::Product.product_uri}/barcode/#{remote_image_name}/recognize"
          str_uri = "#{str_uri}?type=#{symbology}" unless symbology.empty?
          str_uri = "#{str_uri}?format=#{format}" unless format.empty?
          str_uri = Aspose::Cloud::Common::Utils.append_storage(str_uri,remote_folder,storage_name,storage_type)

          signed_uri = Aspose::Cloud::Common::Utils.sign(str_uri)
          response = RestClient.get(signed_uri, :accept => 'application/json')
          json = JSON.parse(response)
          json['Code'] == 200 ? json['Barcodes'] : nil
        end

=begin
  Read Barcode from External Image URL
  @param string url URL of the image.
  @param string symbology Type of barcode.
=end
        def read_from_url(url, symbology)
          raise 'URL not provided.' if url.empty?
          raise 'Symbology not provided.' if symbology.empty?

          str_uri = "#{Aspose::Cloud::Common::Product.product_uri}/barcode/recognize"
          str_uri = "#{str_uri}?type=#{symbology}&url=#{url}"

          signed_uri = Aspose::Cloud::Common::Utils.sign(str_uri)
          response = RestClient.post(signed_uri, '', {:accept => 'application/json'})
          json = JSON.parse(response)
          json['Code'] == 200 ? json['Barcodes'] : nil
        end

=begin
  Read Barcode from Specific Region of Image
  @param string symbology Type of barcode.
  @param number rectX X position of rectangle.
  @param number rectY Y position of rectangle.
  @param number rectWidth Width of rectangle.
  @param number rectX Height of rectangle.
=end
        def read_specific_region(symbology, rectX, rectY, rectWidth, rectHeight)
          raise 'Symbology not provided.' if symbology.empty?
          raise 'X position not provided.' if rectX.nil?
          raise 'Y position not provided.' if rectY.nil?
          raise 'Width not provided.' if rectWidth.nil?
          raise 'Height not provided.' if rectHeight.nil?

          str_uri = "#{Aspose::Cloud::Common::Product.product_uri}/barcode/#{@filename}/recognize"
          str_uri = "#{str_uri}?type=#{symbology}&rectX=#{rectX}&rectY=#{rectY}&rectWidth=#{rectWidth}&rectHeight=#{rectHeight}"

          signed_uri = Aspose::Cloud::Common::Utils.sign(str_uri)
          response = RestClient.get(signed_uri, {:accept => 'application/json'})
          json = JSON.parse(response)
          json['Code'] == 200 ? json['Barcodes'] : nil
        end

=begin
  Recognize Barcode with Checksum Option from Storage
  @param string symbology Type of barcode.
  @param string checksumValidation Sets checksum validation parameter.
=end
        def read_with_checksum(symbology, checksumValidation)
          raise 'Symbology not provided.' if symbology.empty?
          raise 'Checksum not provided.' if checksumValidation.empty?

          str_uri = "#{Aspose::Cloud::Common::Product.product_uri}/barcode/#{@filename}/recognize"
          str_uri = "#{str_uri}?type=#{symbology}&checksumValidation=#{checksumValidation}"

          signed_uri = Aspose::Cloud::Common::Utils.sign(str_uri)
          response = RestClient.get(signed_uri, {:accept => 'application/json'})
          json = JSON.parse(response)
          json['Code'] == 200 ? json['Barcodes'] : nil
        end

=begin
  Recognize Specified count of Barcodes
  @param string symbology Type of barcode.
  @param string barcodesCount Recognize specified count of barcodes.
=end
        def read_barcode_count(symbology, barcodesCount)
          raise 'Symbology not provided.' if symbology.empty?
          raise 'Barcode count not provided.' if barcodesCount.nil?

          str_uri = "#{Aspose::Cloud::Common::Product.product_uri}/barcode/#{@filename}/recognize"
          str_uri = "#{str_uri}?type=#{symbology}&barcodesCount=#{barcodesCount}"

          signed_uri = Aspose::Cloud::Common::Utils.sign(str_uri)
          response = RestClient.get(signed_uri, {:accept => 'application/json'})
          json = JSON.parse(response)
          json['Code'] == 200 ? json['Barcodes'] : nil
        end
      end
    end
  end
end