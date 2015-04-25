using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Aspose.Cloud;
using Microsoft.VisualStudio.TestTools.UnitTesting;

namespace Aspose.CloudTests
{
    [TestClass()]
    public class TasksServiceTests
    {
        StorageService storageService = new StorageService(Utils.AppSid, Utils.AppKey);
        TasksService tasksService = new TasksService(Utils.AppSid, Utils.AppKey);
        
        [TestMethod()]
        public void Tasks_Tests()
        {
            try
            {
                TasksDocumentResponse tasksDocumentResponse = tasksService.GetProjectDocument("sample-mpp.mpp", Utils.CloudStorage_Input_Folder);
                tasksService.GetProjectDocumentWithFormat("sample-mpp.mpp", TasksFileFormat.Jpeg, Utils.CloudStorage_Input_Folder, Utils.Local_Output_Path + "\\mpp-out.jpg");
            }
            catch (Exception ex)
            {
                Assert.Fail(ex.Message);
            }
        }            

        [TestMethod()]
        public void Tasks_TasksAssignments_Tests()
        {
            try
            {
                storageService.File.CopyFile(Utils.CloudStorage_Input_Folder + "/sample-mpp.mpp", Utils.CloudStorage_Output_Folder + "/sample-mpp.mpp");
                AssignmentsResponse assignmentsResponse = tasksService.TasksAssignments.ReadProjectAssignmentItems("sample-mpp.mpp", Utils.CloudStorage_Output_Folder);
                AssignmentItemResponse assignmentItemResponse = tasksService.TasksAssignments.AddNewAssignment("sample-mpp.mpp", 8, 1, string.Empty, Utils.CloudStorage_Output_Folder);
                AssignmentResponse assignmentResponse = tasksService.TasksAssignments.ReadProjectAssignment("sample-mpp.mpp", 7, Utils.CloudStorage_Output_Folder);
                tasksService.TasksAssignments.DeletesProjectAssignmentWithAllReferences("sample-mpp.mpp", 7, string.Empty, Utils.CloudStorage_Output_Folder);
            }
            catch (Exception ex)
            {
                Assert.Fail(ex.Message);
            }
        }
        
        [TestMethod()]
        public void Tasks_TasksCalendar_Tests()
        {
            try
            {
                storageService.File.CopyFile(Utils.CloudStorage_Input_Folder + "/sample-mpp.mpp", Utils.CloudStorage_Output_Folder + "/sample-mpp.mpp");

                CalendarsResponse calendarsResponse = tasksService.TasksCalendar.ReadProjectCalendarItems("sample-mpp.mpp", Utils.CloudStorage_Output_Folder);
                CalendarResponse calendarResponse = tasksService.TasksCalendar.GetProjectCalendar("sample-mpp.mpp", 1, Utils.CloudStorage_Output_Folder);
                calendarResponse.Calendar.Name = "Aspose Calendar";
                CalendarItemResponse CalendarItemResponse = tasksService.TasksCalendar.AddNewCalendarToProjectFile("sample-mpp.mpp", string.Empty, Utils.CloudStorage_Output_Folder, calendarResponse.Calendar);
                tasksService.TasksCalendar.DeleteProjectCalendar("sample-mpp.mpp", 5, string.Empty, Utils.CloudStorage_Output_Folder);

                CalendarExceptionsResponse calendarExceptionsResponse = tasksService.TasksCalendar.GetListOfCalendarExceptions("sample-mpp.mpp", 1, Utils.CloudStorage_Output_Folder);
                calendarExceptionsResponse.CalendarExceptions[0].Month = 9;
                tasksService.TasksCalendar.AddNewCalendarExceptionToACalendar("sample-mpp.mpp", 1, string.Empty, Utils.CloudStorage_Output_Folder, calendarExceptionsResponse.CalendarExceptions[0]);

                tasksService.TasksCalendar.UpdatesCalendarException("sample-mpp.mpp", 1, 1, string.Empty, Utils.CloudStorage_Output_Folder, calendarExceptionsResponse.CalendarExceptions[0]);
                tasksService.TasksCalendar.DeletesCalendarException("sample-mpp.mpp", 1, 1, string.Empty, Utils.CloudStorage_Output_Folder);
            }
            catch (Exception ex)
            {
                Assert.Fail(ex.Message);
            }
        }

        [TestMethod()]
        public void Tasks_TasksCriticalPath_Tests()
        {
            try
            {
                TasksResponse tasksResponse = tasksService.TasksCriticalPath.ReturnsCreatedReportInPdfFormat("sample-mpp.mpp", Utils.CloudStorage_Output_Folder);            
            }
            catch (Exception ex)
            {
                Assert.Fail(ex.Message);
            }
        }
        
        [TestMethod()]
        public void Tasks_TasksDocumentProperties_Tests()
        {
            try
            {
                storageService.File.CopyFile(Utils.CloudStorage_Input_Folder + "/sample-mpp.mpp", Utils.CloudStorage_Output_Folder + "/sample-mpp.mpp");

                TasksPropertiesResponse tasksPropertiesResponse = tasksService.TasksDocumentProperties.ReadDocumentProperties("sample-mpp.mpp", Utils.CloudStorage_Output_Folder);

                TasksPropertyResponse tasksPropertyResponse = tasksService.TasksDocumentProperties.ReadDocumentPropertyByName("sample-mpp.mpp", "Title", Utils.CloudStorage_Output_Folder);

                tasksPropertyResponse.Property.Name = "Aspose";
                tasksService.TasksDocumentProperties.CreateDocumentProperty("sample-mpp.mpp", "Title2", tasksPropertyResponse.Property, string.Empty, Utils.CloudStorage_Output_Folder);

                TasksPropertyResponse tasksPropertyResponse2 = tasksService.TasksDocumentProperties.ReadDocumentPropertyByName("sample-mpp.mpp", "Aspose", Utils.CloudStorage_Output_Folder);

                tasksPropertyResponse.Property.Value = "Aspose";
                tasksService.TasksDocumentProperties.SetDocumentProperty("sample-mpp.mpp", "Title", tasksPropertyResponse.Property, string.Empty, Utils.CloudStorage_Output_Folder);

                TasksPropertyResponse tasksPropertyResponse3 = tasksService.TasksDocumentProperties.ReadDocumentPropertyByName("sample-mpp.mpp", "Title", Utils.CloudStorage_Output_Folder);

            }
            catch (Exception ex)
            {
                Assert.Fail(ex.Message);
            }
        }

        [TestMethod()]
        public void Tasks_TasksExtendedAttributes_Tests()
        {
            try
            {
                storageService.File.CopyFile(Utils.CloudStorage_Input_Folder + "/sample-mpp.mpp", Utils.CloudStorage_Output_Folder + "/sample-mpp.mpp");
                System.Threading.Thread.Sleep(3000); // Just for testing
                ExtendedAttributesResponse extendedAttributesResponse = tasksService.TasksExtendedAttributes.GetExtendedAttributes("sample-mpp.mpp", Utils.CloudStorage_Output_Folder);
                System.Threading.Thread.Sleep(3000); // Just for testing
                ExtendedAttributeResponse extendedAttributeResponse = tasksService.TasksExtendedAttributes.GetExtendedAttributeDefinition("sample-mpp.mpp", 1, Utils.CloudStorage_Output_Folder);
                System.Threading.Thread.Sleep(3000); // Just for testing
                tasksService.TasksExtendedAttributes.DeleteProjectExtendedAttribute("sample-mpp.mpp", 1, Utils.CloudStorage_Output_Folder);
            }
            catch (Exception ex)
            {
                Assert.Fail(ex.Message);
            }
        }

        [TestMethod()]
        public void Tasks_TasksOutlineCodes_Tests()
        {
            try
            {
                storageService.File.CopyFile(Utils.CloudStorage_Input_Folder + "/sample-mpp.mpp", Utils.CloudStorage_Output_Folder + "/sample-mpp.mpp");

                OutlineCodesResponse outlineCodesResponse = tasksService.TasksOutlineCodes.ReadOutlineCodes("sample-mpp.mpp", Utils.CloudStorage_Output_Folder);
                OutlineCodeResponse outlineCodeResponse = tasksService.TasksOutlineCodes.GetOutlineCodeByIndex("sample-mpp.mpp", 1, Utils.CloudStorage_Output_Folder);
                tasksService.TasksOutlineCodes.DeleteProjectOutlineCode("sample-mpp.mpp", 1, Utils.CloudStorage_Output_Folder);
            }
            catch (Exception ex)
            {
                Assert.Fail(ex.Message);
            }
        }

        [TestMethod()]
        public void Tasks_TasksReport_Tests()
        {
            try
            {
                tasksService.TasksReport.CreateReportInPdfFormat("sample-mpp.mpp", TasksReportType.WorkOverview, Utils.CloudStorage_Output_Folder, Utils.Local_Output_Path + "tasks-report.pdf");
            }
            catch (Exception ex)
            {
                Assert.Fail(ex.Message);
            }
        }

        [TestMethod()]
        public void Tasks_TasksResources_Tests()
        {
            try
            {
                storageService.File.CopyFile(Utils.CloudStorage_Input_Folder + "/sample-mpp.mpp", Utils.CloudStorage_Output_Folder + "/sample-mpp.mpp");

                TasksResourcesResponse tasksResourcesResponse = tasksService.TasksResources.ReadProjectResources("sample-mpp.mpp", Utils.CloudStorage_Output_Folder);
                TasksResourceItemResponse tasksResourceItemResponse = tasksService.TasksResources.AddNewResourceToProject("sample-mpp.mpp", "Danny", 1, string.Empty, Utils.CloudStorage_Output_Folder);
                TasksResourceResponse tasksResourceResponse = tasksService.TasksResources.GetProjectResource("sample-mpp.mpp", 1, Utils.CloudStorage_Output_Folder);
                TasksAssignmentsResponse tasksAssignmentsResponse = tasksService.TasksResources.GetResourceAssignments("sample-mpp.mpp", 1, Utils.CloudStorage_Output_Folder);

                tasksService.TasksResources.DeleteProjectResource("sample-mpp.mpp", 1, string.Empty, Utils.CloudStorage_Output_Folder);
            }
            catch (Exception ex)
            {
                Assert.Fail(ex.Message);
            }
        }            

        [TestMethod()]
        public void Tasks_TasksTask_Tests()
        {
            try
            {
                storageService.File.CopyFile(Utils.CloudStorage_Input_Folder + "/sample-mpp.mpp", Utils.CloudStorage_Output_Folder + "/sample-mpp.mpp");

                TasksResponse tasksResponse = tasksService.TasksTask.ReadProjectTaskItems("sample-mpp.mpp", Utils.CloudStorage_Output_Folder);
                TaskItemResponse taskItemResponse = tasksService.TasksTask.AddANewTaskToAProject("sample-mpp.mpp", "Team Meeting", 1, string.Empty, Utils.CloudStorage_Output_Folder);
                TaskResponse taskResponse = tasksService.TasksTask.ReadProjectTask("sample-mpp.mpp", 28, Utils.CloudStorage_Output_Folder);
                TasksAssignmentsResponse tasksAssignmentsResponse = tasksService.TasksTask.GetTaskAssignments("sample-mpp.mpp", 28, Utils.CloudStorage_Output_Folder);

                tasksService.TasksTask.DeleteProjectTask("sample-mpp.mpp", 28, string.Empty, Utils.CloudStorage_Output_Folder);
            }
            catch (Exception ex)
            {
                Assert.Fail(ex.Message);
            }
        }

        [TestMethod()]
        public void Tasks_TasksTaskLinks_Tests()
        {
            try
            {
                storageService.File.CopyFile(Utils.CloudStorage_Input_Folder + "/sample-mpp.mpp", Utils.CloudStorage_Output_Folder + "/sample-mpp.mpp");
                TaskLinkResponse taskLinkResponse = tasksService.TasksTaskLinks.ReadTaskLinks("sample-mpp.mpp", Utils.CloudStorage_Output_Folder);

                //taskLinkResponse.TaskLinks[0].Lag = 4;
                //tasksService.TasksTaskLinks.AddNewTaskLinkToAProject("sample-mpp.mpp", string.Empty, Utils.CloudStorage_Output_Folder, taskLinkResponse.TaskLinks[0]);

                taskLinkResponse.TaskLinks[0].Lag = 2;
                tasksService.TasksTaskLinks.UpdatesTaskLink("sample-mpp.mpp", 1, string.Empty, Utils.CloudStorage_Output_Folder, taskLinkResponse.TaskLinks[0]);
                tasksService.TasksTaskLinks.DeleteTaskLink("sample-mpp.mpp", 1, string.Empty, Utils.CloudStorage_Output_Folder);
            }
            catch (Exception ex)
            {
                Assert.Fail(ex.Message);
            }
        }            
    }
}
