package com.aspose.cloud.sdk.tasks;

import com.aspose.cloud.sdk.tasks.api.TaskLinks;
import com.aspose.cloud.sdk.tasks.model.TaskLinksModel;

import junit.framework.TestCase;

import java.util.ArrayList;

public class TaskLinksTest extends TestCase {

	public TaskLinksTest(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void testGetAllTaskLinksInProject() throws Exception {
		ArrayList<TaskLinksModel> taskLinksArray = TaskLinks.getAllTaskLinksInProject("NewProductDev.mpp");
		assertNotNull("Failed to retrieve task links information from a project", taskLinksArray);
	}
}
