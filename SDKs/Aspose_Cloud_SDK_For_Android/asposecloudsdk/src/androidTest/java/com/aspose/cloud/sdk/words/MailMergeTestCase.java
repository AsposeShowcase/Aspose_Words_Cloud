package com.aspose.cloud.sdk.words;

import java.io.InputStream;

import com.aspose.cloud.sdk.common.Utils;
import com.aspose.cloud.sdk.storage.api.Folder;
import com.aspose.cloud.sdk.words.model.CleanupOptionEnum;
import com.aspose.cloud.sdk.words.api.MailMerge;
import com.aspose.cloud.sdk.words.model.DocumentResponse.Document;

import junit.framework.TestCase;

public class MailMergeTestCase extends TestCase {

	public MailMergeTestCase(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void testExecuteTemplateAndPopulateAWordDocumentFromXMLData() throws Exception {
		String xmlData = 
				"<Orders xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:noNamespaceSchemaLocation=\"OrdersSchema.xsd\">" +
						"<Order>" +
						"<Address>" +
							"<Number>23</Number>" +
							"<Street>Nelson Street</Street>" +
							"<Suburb>Howick</Suburb>" +
							"<City>Auckland</City>" +
						"</Address>" +
						"<PhoneNumber>543 1234</PhoneNumber>" +
						"<Date>03/01/2010</Date>" +
						"<Total>14.00</Total>" +
						"<Item>" +
							"<Name>BBQ Chicken Pizza</Name>" +
							"<Price>6.00</Price>" +
							"<Quantity>1</Quantity>" +
							"<ItemTotal>6.00</ItemTotal>" +
						"</Item>" +
						"<Item>" +
							"<Name>1.5 Litre Coke</Name>" +
							"<Price>4.00</Price>" +
							"<Quantity>2</Quantity>" +
							"<ItemTotal>8.00</ItemTotal>" +
						"</Item>" +
						"</Order>" +
						"<Order>" +
						"<Address>" +
							"<Number>10</Number>" +
							"<Street>Parkville Avenue</Street>" +
							"<Suburb>Pakuranga</Suburb>" +
							"<City>Auckland</City>" +
						"</Address>" +
						"<PhoneNumber>548 7342</PhoneNumber>" +
						"<Date>05/03/2010</Date>" +
						"<Total>6.00</Total>" +
						"<Item>" +
							"<Name>Hawaiian Pizza</Name>" +
							"<Price>4.00</Price>" +
							"<Quantity>1</Quantity>" +
							"<ItemTotal>4.00</ItemTotal>" +
						"</Item>" +
						"<Item>" +
							"<Name>Fries</Name>" +
							"<Price>1.00</Price>" +
							"<Quantity>2</Quantity>" +
							"<ItemTotal>2.00</ItemTotal>" +
						"</Item>" +
						"</Order>" +
					"</Orders>"; 

		Document document = MailMerge.executeTemplateAndPopulateAWordDocumentFromXMLData("TestExecuteTemplate.doc", 
														xmlData, CleanupOptionEnum.None);
		assertNotNull("Mail merge failed to execute template and populate a Word document from XML data", document);
		
		//Save resultant document on device
		InputStream fileStream = Folder.getFile(document.fileName);
		assertNotNull("Failed to get file from Aspose server", fileStream);
		String filePath = Utils.saveStreamToFile(fileStream, document.fileName);
		assertNotNull("Failed to save file to local device", filePath);
	}
	
	public void testExecuteTemplateAndPopulateAWordDocumentWithDataAndImages() throws Exception {
		String xmlData = 
				"<Items xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">" +
					"<Item>" +
						"<Caption>This is the first caption !!!</Caption>" +
						"<Img>TempWords\\bookmark.png</Img>" +
					"</Item>" +
					"<Item>" +
						"<Caption>This is the second caption !!!</Caption>" +
						"<Img width=\"60%\" height=\"105%\">TempWords\\sample.jpg</Img>" +
					"</Item>" +
				"</Items>"; 
		
		Document document = MailMerge.executeTemplateAndPopulateAWordDocumentFromXMLData("ImageandDataTemplate.docx", 
				xmlData, CleanupOptionEnum.None);
		assertNotNull("Mail merge failed to execute template and populate a Word document with data and images", document);
		
		//Save resultant document on device
		InputStream fileStream = Folder.getFile(document.fileName);
		assertNotNull("Failed to get file from Aspose server", fileStream);
		String filePath = Utils.saveStreamToFile(fileStream, document.fileName);
		assertNotNull("Failed to save file to local device", filePath);
	}
}
