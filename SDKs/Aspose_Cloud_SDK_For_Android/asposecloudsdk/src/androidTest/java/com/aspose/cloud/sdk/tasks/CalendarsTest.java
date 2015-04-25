package com.aspose.cloud.sdk.tasks;

import com.aspose.cloud.sdk.tasks.model.CalendarItemModel;
import com.aspose.cloud.sdk.tasks.api.Calendars;

import junit.framework.TestCase;

import java.util.ArrayList;

public class CalendarsTest extends TestCase {

	public CalendarsTest(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testGetAllCalendarItemsInProject() throws Exception {
		ArrayList<CalendarItemModel> calendarItemsArray = Calendars.getAllCalendarItemsInProject("NewProductDev.mpp");
		assertNotNull("Failed to retrieve calendar information from a project", calendarItemsArray);
	}
}
