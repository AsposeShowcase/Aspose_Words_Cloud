package com.aspose.cloud.sdk.pdf;

import com.aspose.cloud.sdk.pdf.model.FormFieldRequest;
import com.aspose.cloud.sdk.pdf.api.FormFields;
import com.aspose.cloud.sdk.pdf.model.FormFieldResponse.FieldResult;
import com.aspose.cloud.sdk.pdf.model.FormFieldsResponse.FieldsResult;

import junit.framework.TestCase;

public class FormFieldsTest extends TestCase {

	public FormFieldsTest(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void testGetAllFormFieldsFromThePDFDocument() throws Exception {
		FieldsResult fields = FormFields.getAllFormFieldsFromThePDFDocument("Bitcoin.pdf");
		assertNotNull("Failed to get all form fields from the PDF document", fields);
	}
	
	public void testGetAParticularFormFieldFromThePDFDocument() throws Exception {
		FieldResult field = FormFields.getAParticularFormFieldFromThePDFDocument("Bitcoin.pdf", "myFieldName");
		assertNotNull("Failed to get a particular form field from the PDF document", field);
	}
	
	public void testGetFormFieldCountFromAPDFDocument() throws Exception {
		int formFieldCount = FormFields.getFormFieldCountFromAPDFDocument("Bitcoin.pdf");
		assertEquals("Failed to get form field count from a PDF document", true, formFieldCount>=0);
	}
	
	public void testUpdateAFormFieldInAPDFDocument() throws Exception {
		FormFieldRequest fieldRequest = new FormFieldRequest();
		fieldRequest.Name = "textbox1";
		fieldRequest.Type = 0;
		fieldRequest.Values.add("Updated text value");
		FieldResult field = FormFields.updateAFormFieldInAPDFDocument("Bitcoin.pdf", "myFieldName", fieldRequest);
		assertNotNull("Failed to update form fields in a PDF document", field);
	}

}
