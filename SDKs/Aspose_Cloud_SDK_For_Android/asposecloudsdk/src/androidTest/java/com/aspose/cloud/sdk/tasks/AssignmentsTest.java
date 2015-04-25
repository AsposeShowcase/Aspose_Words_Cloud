package com.aspose.cloud.sdk.tasks;

import com.aspose.cloud.sdk.tasks.api.Assignments;
import com.aspose.cloud.sdk.tasks.model.AssignmentItemModel;

import junit.framework.TestCase;

import java.util.ArrayList;

public class AssignmentsTest extends TestCase {

	public AssignmentsTest(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testAssignments() throws Exception {
		ArrayList<AssignmentItemModel> assignmentsArray = Assignments.getAllAssignmentItemsInProject("NewProductDev.mpp");
		assertNotNull("Failed to retrieve assignments information from Project", assignmentsArray);
	}
}
