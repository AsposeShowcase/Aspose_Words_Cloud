package com.aspose.cloud.sdk.tasks;

import com.aspose.cloud.sdk.tasks.model.TaskItemModel;
import com.aspose.cloud.sdk.tasks.api.Resources;
import com.aspose.cloud.sdk.tasks.model.ResourceItemModel;
import com.aspose.cloud.sdk.tasks.model.ResourceModel;

import junit.framework.TestCase;

import java.util.ArrayList;

public class ResourcesTest extends TestCase {

	public ResourcesTest(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void testGetAllResourceItemsOfProject() throws Exception {
		ArrayList<ResourceItemModel> resourcesArray = Resources.getAllResourceItemsOfProject("NewProductDev.mpp");
		assertNotNull("Failed to retrieve all resources information in a project", resourcesArray);
	}
	
	public void testGetResourceInformation() throws Exception {
		ResourceModel resource = Resources.getResourceInformation("NewProductDev.mpp", 1);
		assertNotNull("Failed to retrieve detail information of a particular resource", resource);
	}

	public void testAddAResourceToProject() throws Exception {
		TaskItemModel taskItem = Resources.addAResourceToProject("NewProductDev.mpp", "Technology", 2, "UpdatedProductDev.mpp");
		assertNotNull("Failed to add a resource to a Project", taskItem);
	}
	
	public void testDeleteAResourceFromProject() throws Exception {
		boolean isResourceDeletedSuccessfully = Resources.deleteAResourceFromProject("NewProductDev.mpp", 19, "UpdatedProductDev.mpp");
		assertEquals("Failed to delete resource from a project", true, isResourceDeletedSuccessfully);
	}
}
