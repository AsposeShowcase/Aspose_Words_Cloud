package com.aspose.cloud.sdk.storage;

import java.io.InputStream;
import java.util.List;

import junit.framework.TestCase;
import com.aspose.cloud.sdk.common.Utils;
import com.aspose.cloud.sdk.storage.model.DiscUsageModel;
import com.aspose.cloud.sdk.storage.model.FileExist;
import com.aspose.cloud.sdk.storage.model.FileModel;
import com.aspose.cloud.sdk.storage.api.Folder;

public class FolderTest extends TestCase {
	
	public FolderTest(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testGetFileAndSaveToDevice() throws Exception {
		
		InputStream fileStream = Folder.getFile("myworkbook.xlsx");
		assertNotNull("Failed to get file from Aspose server", fileStream);
		String filePath = Utils.saveStreamToFile(fileStream, "myworkbook.xlsx");
		assertNotNull("Failed to save file to local device", filePath);
	}

	public void testGetDiscUsage() throws Exception {
		DiscUsageModel discUsage = Folder.getDiscUsage();
		assertNotNull("Failed to get disk usage from the Aspose cloud Storage", discUsage);
	}

	public void testFileExist() throws Exception {
		FileExist fileExist = Folder.fileExist("first.pdf");
		assertNotNull("Failed to check whether file or folder exists on the Aspose cloud Storage", fileExist);
	}

	public void testUploadFile() throws Exception {
		boolean isFileUploadedSuccessfully = Folder.uploadFile("/storage/emulated/0/AsposeFiles/ScreenShot.png", "");
		assertTrue("Failed to upload a file from your local machine to remote folder", isFileUploadedSuccessfully);
	}

	public void testDeleteFile() throws Exception {
		boolean isFileDeletedSuccessfully = Folder.deleteFile("test.png");
		assertTrue("Failed to delete a file from the storage", isFileDeletedSuccessfully);
	}

	public void testDeleteFolder() throws Exception {
		boolean isFolderDeletedSuccessfully = Folder.deleteFolder("Recipes");
		assertTrue("Failed to delete folder from the storage", isFolderDeletedSuccessfully);
	}

	public void testCreateFolder() throws Exception {
		boolean isFolderCreatedSuccessfully = Folder.createFolder("OSX");
		assertTrue("Failed to create folder under the specified path", isFolderCreatedSuccessfully);
	}

	public void testGetFilesList() throws Exception {
		List<FileModel> filesList = Folder.getFilesList("colleges");
		assertNotNull("Failed to get a list of all files and folders under the specified folder", filesList);
	}

	public void testCopyFile() throws Exception {
		boolean isFileCopiedToNewDestinationSuccessfully = Folder.copyFile("bookmark.png", "colleges");
		assertTrue("Failed to copy file to designated location", isFileCopiedToNewDestinationSuccessfully);
	}
	
	public void testMoveFileToAnotherLocation() throws Exception {
		boolean isFileMovedToAnotherLocationSuccessfully = Folder.moveFile("bookmark.png", "colleges", "universities");
		assertTrue("Failed to move file to another location", isFileMovedToAnotherLocationSuccessfully);
	}
}
