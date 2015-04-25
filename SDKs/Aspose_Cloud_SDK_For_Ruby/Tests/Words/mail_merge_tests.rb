require 'test/unit'
require_relative '../../lib/asposecloud'

class MailMergeTests < Test::Unit::TestCase

  def setup
    file = File.read('../setup.json')
    data = JSON.parse(file)

    Aspose::Cloud::Common::AsposeApp.app_key = data['app_key']
    Aspose::Cloud::Common::AsposeApp.app_sid = data['app_sid']
    Aspose::Cloud::Common::AsposeApp.output_location = data['output_location']
    Aspose::Cloud::Common::Product.set_base_product_uri(data['product_uri'])
  end

  # Executes mail merge with/without regions
  def test_execute_mail_merge
    # Create object of folder class
    folder = Aspose::Cloud::AsposeStorage::Folder.new
    response = folder.upload_file '../Data/mail_merge_regions.docx'
    assert_equal true, response

    str_xml = '<?xml version="1.0" encoding="utf-8"?>
                 <Orders xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:noNamespaceSchemaLocation="OrdersSchema.xsd">
                   <Item>
                     <Name>BBQ Chicken Pizza</Name>
                     <Price>6.00</Price>
                     <Quantity>1</Quantity>
                     <ItemTotal>6.00</ItemTotal>
                   </Item>
                   <Item>
                     <Name>1.5 Litre Coke</Name>
                     <Price>4.00</Price>
                     <Quantity>2</Quantity>
                     <ItemTotal>8.00</ItemTotal>
                   </Item>
                   <Item>
                     <Name>Hawaiian Pizza</Name>
                     <Price>4.00</Price>
                     <Quantity>1</Quantity>
                     <ItemTotal>4.00</ItemTotal>
                   </Item>
                   <Item>
                     <Name>Fries</Name>
                     <Price>1.00</Price>
                     <Quantity>2</Quantity>
                     <ItemTotal>2.00</ItemTotal>
                   </Item>
                 </Orders>'

    mail_merge = Aspose::Cloud::Words::MailMerge.new('mail_merge_regions.docx')
    assert_nothing_thrown 'Error' do
      mail_merge.execute_mail_merge(str_xml, with_regions=true)
    end
     assert_equal true, File.exist?('../Output/mail_merge_regions.docx')
  end

  # Executes mail merge with template
  def test_execute_template
    # Create object of folder class
    folder = Aspose::Cloud::AsposeStorage::Folder.new
    response = folder.upload_file '../Data/mail_merge_template.docx'
    assert_equal true, response

    str_xml = '<?xml version="1.0" encoding="utf-8" ?>
                <Orders xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:noNamespaceSchemaLocation="OrdersSchema.xsd">
                  <Order>
                  <Address>
                    <Number>23</Number>
                    <Street>Nelson Street</Street>
                    <Suburb>Howick</Suburb>
                    <City>Auckland</City>
                  </Address>
                  <PhoneNumber>543 1234</PhoneNumber>
                  <Date>03/01/2010</Date>
                  <Total>14.00</Total>
                   <Item>
                      <Name>BBQ Chicken Pizza</Name>
                      <Price>6.00</Price>
                      <Quantity>1</Quantity>
                      <ItemTotal>6.00</ItemTotal>
                    </Item>
                   <Item>
                      <Name>1.5 Litre Coke</Name>
                      <Price>4.00</Price>
                      <Quantity>2</Quantity>
                      <ItemTotal>8.00</ItemTotal>
                    </Item>
                     </Order>
                     <Order>
                  <Address>
                    <Number>10</Number>
                    <Street>Parkville Avenue</Street>
                    <Suburb>Pakuranga</Suburb>
                    <City>Auckland</City>
                  </Address>
                  <PhoneNumber>548 7342</PhoneNumber>
                  <Date>05/03/2010</Date>
                  <Total>6.00</Total>
                  <Item>
                    <Name>Hawaiian Pizza</Name>
                    <Price>4.00</Price>
                    <Quantity>1</Quantity>
                    <ItemTotal>4.00</ItemTotal>
                  </Item>
                  <Item>
                    <Name>Fries</Name>
                    <Price>1.00</Price>
                    <Quantity>2</Quantity>
                    <ItemTotal>2.00</ItemTotal>
                   </Item>
                  </Order>
                </Orders>'
    mail_merge = Aspose::Cloud::Words::MailMerge.new('mail_merge_template.docx')
    assert_nothing_thrown 'Error' do
      mail_merge.execute_template(str_xml)
    end
     assert_equal true, File.exist?('../Output/mail_merge_template.docx')
  end 
end
