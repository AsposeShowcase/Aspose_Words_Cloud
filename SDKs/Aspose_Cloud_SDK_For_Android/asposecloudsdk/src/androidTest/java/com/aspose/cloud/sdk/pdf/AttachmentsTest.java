package com.aspose.cloud.sdk.pdf;

import com.aspose.cloud.sdk.pdf.model.AttachmentDetails;
import com.aspose.cloud.sdk.pdf.api.Attachments;
import com.aspose.cloud.sdk.pdf.model.AttachmentsResponse.AttachmentsResult;

import junit.framework.TestCase;

import java.io.File;

public class AttachmentsTest extends TestCase {

	public AttachmentsTest(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testGetAllAttachmentsFromAPDF() throws Exception {
		AttachmentsResult attachments = Attachments.getAllAttachmentsFromAPDF("Bitcoin.pdf");
		assertNotNull("Failed to get all attachments from a PDF", attachments);
	}

	public void testDownloadASpecificAttachmentFromAPDF() throws Exception {
		String localFilePath = Attachments.downloadASpecificAttachmentFromAPDF("Bitcoin.pdf", 1, "/storage/emulated/0/AsposeFiles/");
		File file = new File(localFilePath);
		assertEquals("Failed to download a specific attachment from a PDF", true, file.exists());
	}
	
	public void testGetAttachmentCountFromAPDF() throws Exception {
		int attachmentCount = Attachments.getAttachmentCountFromAPDF("Bitcoin.pdf");
		assertEquals("Failed to get attachments count from a PDF", true, attachmentCount>=0);
	}
	
	public void testGetASpecificAttachmentFromAPDF() throws Exception {
		AttachmentDetails attachment = Attachments.getASpecificAttachmentFromAPDF("Bitcoin.pdf", 1);
		assertNotNull("Failed to get a specific attachment from a PDF", attachment);
	}
}
