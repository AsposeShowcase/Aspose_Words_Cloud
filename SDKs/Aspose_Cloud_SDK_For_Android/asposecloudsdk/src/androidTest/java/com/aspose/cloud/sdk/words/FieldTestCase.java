package com.aspose.cloud.sdk.words;

import com.aspose.cloud.sdk.words.api.Field;
import com.aspose.cloud.sdk.words.model.DocumentResponse.Document;
import com.aspose.cloud.sdk.words.model.GetMailMergeFieldNamesResponse.FieldName;

import junit.framework.TestCase;

public class FieldTestCase extends TestCase {

	public FieldTestCase(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testGetAllMergeFieldNamesFromAWordDocument() throws Exception {
 		FieldName fieldName = Field.getAllMergeFieldNamesFromAWordDocument("Envelope3.docx");
		assertNotNull("Failed to get all merge field names from a word document", fieldName);
	}
	
	public void testInsertPageNumberFieldIntoTheWordDocument() throws Exception {
		Document document = Field.insertPageNumberFieldIntoTheWordDocument("myworddocument.docx", "{PAGE} of {NUMPAGES}", "right", false, true);
		assertNotNull("Failed to insert page number field into the word document", document);
	}
	
	public void testUpdateAllFieldsInTheWorldDocument() throws Exception {
		Document document = Field.updateAllFieldsInTheWordDocument("myworddocument.docx", "updatefieldsworddocument.docx");
		assertNotNull("Failed to update all fields in the word document", document);
	}
}
