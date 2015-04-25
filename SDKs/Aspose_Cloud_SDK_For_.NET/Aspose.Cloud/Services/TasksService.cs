using System;
using System.Collections.Generic;
using System.Text;
using Aspose.Cloud.Common;
using Newtonsoft.Json;
using Newtonsoft.Json.Linq;
using System.IO;

namespace Aspose.Cloud
{
    public enum TasksFileFormat
    {
        Mpp, Xml, Html, Bmp, Png, Jpeg, Pdf, Tiff, Xps, Xaml, Svg, Csv, Txt, Spreadsheet2003, Xlsx, Primaverap6xml, Primaveraxer
    }

    public class TasksService : BaseService
    {
        public TasksService(string appSid, string appKey)
            : base(appSid, appKey)
        {
            AppSid = appSid;
            AppKey = appKey;
            TasksAssignments = new TasksAssignmentsClass(appSid, appKey);
            TasksCalendar = new TasksCalendarClass(appSid, appKey);
            TasksCriticalPath = new TasksCriticalPathClass(appSid, appKey);
            TasksDocumentProperties = new TasksDocumentPropertiesClass(appSid, appKey);
            TasksExtendedAttributes = new TasksExtendedAttributesClass(appSid, appKey);
            TasksOutlineCodes = new TasksOutlineCodesClass(appSid, appKey);
            TasksReport = new TasksReportClass(appSid, appKey);
            TasksResources = new TasksResourcesClass(appSid, appKey);
            TasksTask = new TasksTaskClass(appSid, appKey);
            TasksTaskLinks = new TasksTaskLinksClass(appSid, appKey);
        }

        public TasksAssignmentsClass TasksAssignments { get; set; }
        public TasksCalendarClass TasksCalendar { get; set; }
        public TasksCriticalPathClass TasksCriticalPath { get; set; }
        public TasksDocumentPropertiesClass TasksDocumentProperties { get; set; }
        public TasksExtendedAttributesClass TasksExtendedAttributes { get; set; }
        public TasksOutlineCodesClass TasksOutlineCodes { get; set; }
        public TasksReportClass TasksReport { get; set; }
        public TasksResourcesClass TasksResources { get; set; }
        public TasksTaskClass TasksTask { get; set; }
        public TasksTaskLinksClass TasksTaskLinks { get; set; }

        /// <summary>
        /// Represents a project document.	
        /// </summary>
        /// <param name="name">The name of the file.</param>
        /// <param name="format"></param>
        /// <param name="folder">The document folder.</param>
        /// <param name="storage">The document storage.</param>
        public TasksDocumentResponse GetProjectDocument(string name, string folder, string storage = "")
        {
            // GET 	tasks/{name}?appSid={appSid}&format={format}&storage={storage}&folder={folder} 

            string apiUrl = string.Format(@"tasks/{0}?storage={1}&folder={2}",
                                            name, storage, folder);

            JObject jObject = JObject.Parse(ServiceController.Get(apiUrl, AppSid, AppKey));
            TasksDocumentResponse tasksDocumentResponse = jObject.ToObject<TasksDocumentResponse>();
            return tasksDocumentResponse;
        }

        /// <summary>
        ///  Represents a project document with format 
        /// </summary>
        /// <param name="name">The name of the file.</param>
        /// <param name="format">If the format is specified the response contains the conversion data (see a table below for valid formats)</param>
        /// <param name="folder">The document folder.</param>
        /// <param name="outPath">Path to save result. It must be local (e.g. c:\out.doc)</param>
        /// <param name="storage">The document storage.</param>
        public void GetProjectDocumentWithFormat(string name, TasksFileFormat format, string folder, string outPath, string storage = "")
        {
            // GET 	tasks/{name}?appSid={appSid}&format={format}&storage={storage}&folder={folder} 

            string apiUrl = string.Format(@"tasks/{0}?format={1}&storage={2}&folder={3}",
                                            name, format, storage, folder);
            if (!string.IsNullOrEmpty(outPath) && Directory.Exists(Path.GetDirectoryName(outPath)))
            {
                using (Stream responseStream = ServiceController.GetStream(apiUrl, AppSid, AppKey))
                using (Stream file = File.OpenWrite(outPath))
                {
                    ServiceController.CopyStream(responseStream, file);
                }
            }
        }

        public class TasksAssignmentsClass
        {
            public string AppKey { get; set; }
            public string AppSid { get; set; }

            internal TasksAssignmentsClass(string appSid, string appKey) { AppSid = appSid; AppKey = appKey; }

            /// <summary>
            /// Read project assignment items.	
            /// </summary>
            /// <param name="name">The name of the file.</param>
            /// <param name="folder">The document folder.</param>
            /// <param name="storage">The document storage.</param>
            public AssignmentsResponse ReadProjectAssignmentItems(string name, string folder, string storage = "")
            {
                // GET 	tasks/{name}/assignments?appSid={appSid}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"tasks/{0}/assignments?storage={1}&folder={2}",
                                                name, storage, folder);

                JObject jObject = JObject.Parse(ServiceController.Get(apiUrl, AppSid, AppKey));
                AssignmentsResponse assignmentsResponse = jObject.ToObject<AssignmentsResponse>();
                return assignmentsResponse;
            }

            /// <summary>
            /// Adds a new assignment to a project and returns assignment item in a response.	
            /// </summary>
            /// <param name="name">The name of the file.</param>
            /// <param name="taskUid">The unique id of the task to be assigned.</param>
            /// <param name="resourceUid">The unique id of the resource to be assigned.</param>
            /// <param name="fileName">The name of the project document to save changes to. If this parameter is omitted then the changes will be saved to the source project document.</param>
            /// <param name="folder">The document folder.</param>
            /// <param name="units"> The units for the new assignment. Default value is 1. </param>
            /// <param name="storage">The document storage.</param>
            public AssignmentItemResponse AddNewAssignment(string name, int taskUid, int resourceUid, string fileName, string folder, double units = 1, string storage = "")
            {
                // POST 	tasks/{name}/assignments?appSid={appSid}&taskUid={taskUid}&resourceUid={resourceUid}&units={units}&fileName={fileName}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"tasks/{0}/assignments?taskUid={1}&resourceUid={2}&units={3}&fileName={4}&storage={5}&folder={6}",
                                                name, taskUid, resourceUid, units, fileName, storage, folder);

                JObject jObject = JObject.Parse(ServiceController.Post(apiUrl, AppSid, AppKey));
                AssignmentItemResponse assignmentItemResponse = jObject.ToObject<AssignmentItemResponse>();
                return assignmentItemResponse;
            }

            /// <summary>
            /// Read project assignment.	
            /// </summary>
            /// <param name="name">The name of the file.</param>
            /// <param name="assignmentUid">Assignment Uid</param>
            /// <param name="folder">The document folder.</param>
            /// <param name="storage">The document storage.</param>
            public AssignmentResponse ReadProjectAssignment(string name, int assignmentUid, string folder, string storage = "")
            {
                // GET 	tasks/{name}/assignments/{assignmentUid}?appSid={appSid}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"tasks/{0}/assignments/{1}?storage={2}&folder={3}",
                                                name, assignmentUid, storage, folder);
                
                JObject jObject = JObject.Parse(ServiceController.Get(apiUrl, AppSid, AppKey));
                AssignmentResponse assignmentResponse = jObject.ToObject<AssignmentResponse>();
                return assignmentResponse;
            }

            /// <summary>
            /// Deletes a project assignment with all references to it.	
            /// </summary>
            /// <param name="name">The name of the file.</param>
            /// <param name="assignmentUid">Assignment Uid</param>`
            /// <param name="fileName">The name of the project document to save changes to. If this parameter is omitted then the changes will be saved to the source project document.</param>
            /// <param name="folder">The document folder.</param>
            /// <param name="storage">The document storage.</param>
            public void DeletesProjectAssignmentWithAllReferences(string name, int assignmentUid, string fileName, string folder, string storage = "")
            {
                // DELETE 	tasks/{name}/assignments/{assignmentUid}?appSid={appSid}&storage={storage}&folder={folder}&fileName={fileName} 

                string apiUrl = string.Format(@"tasks/{0}/assignments/{1}?storage={2}&folder={3}&fileName={4}",
                                                name, assignmentUid, storage, folder, fileName);

                ServiceController.Delete(apiUrl, AppSid, AppKey);

            }
        }

        public class TasksCalendarClass
        {
            public string AppKey { get; set; }
            public string AppSid { get; set; }

            internal TasksCalendarClass(string appSid, string appKey) { AppSid = appSid; AppKey = appKey; }

            /// <summary>
            /// Read project calendar items.	
            /// </summary>
            /// <param name="name">The name of the file.</param>
            /// <param name="folder">The document folder.</param>
            /// <param name="storage">The document storage.</param>
            public CalendarsResponse ReadProjectCalendarItems(string name, string folder, string storage = "")
            {
                // GET 	tasks/{name}/calendars?appSid={appSid}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"tasks/{0}/calendars?storage={1}&folder={2}",
                                                name, storage, folder);

                JObject jObject = JObject.Parse(ServiceController.Get(apiUrl, AppSid, AppKey));
                CalendarsResponse calendarsResponse = jObject.ToObject<CalendarsResponse>();
                return calendarsResponse;
            }

            /// <summary>
            /// Adds a new calendar to project file.	
            /// </summary>
            /// <param name="name">The name of the file.</param>
            /// <param name="fileName">The name of the project document to save changes to. If this parameter is omitted then the changes will be saved to the source project document.</param>
            /// <param name="folder">The document folder.</param>
            /// <param name="calendar">Calendar object.</param>
            /// <param name="storage">The document storage.</param>
            public CalendarItemResponse AddNewCalendarToProjectFile(string name, string fileName, string folder, Calendar calendar, string storage = "")
            {
                // POST 	tasks/{name}/calendars?appSid={appSid}&fileName={fileName}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"tasks/{0}/calendars?fileName={1}&storage={2}&folder={3}",
                                                name, fileName, storage, folder);

                JObject jObject = JObject.Parse(ServiceController.Post(apiUrl, AppSid, AppKey, JsonConvert.SerializeObject(calendar)));
                CalendarItemResponse CalendarItemResponse = jObject.ToObject<CalendarItemResponse>();
                return CalendarItemResponse;
            }

            /// <summary>
            /// Represents a project calendar.	
            /// </summary>
            /// <param name="name">The name of the file.</param>
            /// <param name="calendarUid">Calendar Uid</param>
            /// <param name="folder">The document folder.</param>
            /// <param name="storage">The document storage.</param>
            public CalendarResponse GetProjectCalendar(string name, int calendarUid, string folder, string storage = "")
            {
                // GET 	tasks/{name}/calendars/{calendarUid}?appSid={appSid}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"tasks/{0}/calendars/{1}?storage={2}&folder={3}",
                                                name, calendarUid, storage, folder);

                JObject jObject = JObject.Parse(ServiceController.Get(apiUrl, AppSid, AppKey));
                CalendarResponse calendarResponse = jObject.ToObject<CalendarResponse>();
                return calendarResponse;
            }

            /// <summary>
            /// Deletes a project calendar	
            /// </summary>
            /// <param name="name">The name of the file.</param>
            /// <param name="calendarUid">Calendar Uid</param>
            /// <param name="fileName">The name of the project document to save changes to. If this parameter is omitted then the changes will be saved to the source project document.</param>
            /// <param name="folder">The document folder.</param>
            /// <param name="storage">The document storage.</param>
            public void DeleteProjectCalendar(string name, int calendarUid, string fileName, string folder, string storage = "")
            {
                // DELETE 	tasks/{name}/calendars/{calendarUid}?appSid={appSid}&storage={storage}&folder={folder}&fileName={fileName} 

                string apiUrl = string.Format(@"tasks/{0}/calendars/{1}?storage={2}&folder={3}&fileName={4}",
                                                name, calendarUid, storage, folder, fileName);

                ServiceController.Delete(apiUrl, AppSid, AppKey);
            }

            /// <summary>
            /// Get list of calendar exceptions.	
            /// </summary>
            /// <param name="name">The name of the file.</param>
             /// <param name="calendarUid">Calendar Uid</param>
            /// <param name="folder">The document folder.</param>
            /// <param name="storage">The document storage.</param>
            public CalendarExceptionsResponse GetListOfCalendarExceptions(string name, int calendarUid, string folder, string storage = "")
            {
                // GET 	tasks/{name}/calendars/{calendarUid}/calendarExceptions?appSid={appSid}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"tasks/{0}/calendars/{1}/calendarExceptions?storage={2}&folder={3}",
                                                name, calendarUid, storage, folder);

                JObject jObject = JObject.Parse(ServiceController.Get(apiUrl, AppSid, AppKey));
                CalendarExceptionsResponse calendarExceptionsResponse = jObject.ToObject<CalendarExceptionsResponse>();
                return calendarExceptionsResponse;
            }

            /// <summary>
            /// Adds a new calendar exception to a calendar.	
            /// </summary>
            /// <param name="name">The name of the file.</param>
             /// <param name="calendarUid">Calendar Uid</param>
            /// <param name="fileName">The name of the project document to save changes to. If this parameter is omitted then the changes will be saved to the source project document.</param>
            /// <param name="folder">The document folder.</param>
            /// <param name="storage">The document storage.</param>
            public void AddNewCalendarExceptionToACalendar(string name, int calendarUid, string fileName, string folder, CalendarException calendarException, string storage = "")
            {
                // POST 	tasks/{name}/calendars/{calendarUid}/calendarExceptions?appSid={appSid}&fileName={fileName}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"tasks/{0}/calendars/{1}/calendarExceptions?fileName={2}&storage={3}&folder={4}",
                                                name, calendarUid, fileName, storage, folder);

                ServiceController.Post(apiUrl, AppSid, AppKey, JsonConvert.SerializeObject(calendarException));
            }

            /// <summary>
            /// Updates calendar exception.	
            /// </summary>
            /// <param name="name">The name of the file.</param>
             /// <param name="calendarUid">Calendar Uid</param>
            /// <param name="index">Calendar exception index</param>
            /// <param name="fileName">The name of the project document to save changes to. If this parameter is omitted then the changes will be saved to the source project document.</param>
            /// <param name="folder">The document folder.</param>
            /// <param name="storage">The document storage.</param>
            public void UpdatesCalendarException(string name, int calendarUid, int index, string fileName, string folder, CalendarException calendarException, string storage = "")
            {
                // PUT 	tasks/{name}/calendars/{calendarUid}/calendarExceptions/{index}?appSid={appSid}&fileName={fileName}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"tasks/{0}/calendars/{1}/calendarExceptions/{2}?fileName={3}&storage={4}&folder={5}",
                                                name, calendarUid, index, fileName, storage, folder);


                ServiceController.Put(apiUrl, AppSid, AppKey, JsonConvert.SerializeObject(calendarException));
            }

            /// <summary>
            /// Deletes calendar exception from calendar exceptions collection.	
            /// </summary>
            /// <param name="name">The name of the file.</param>
            /// <param name="calendarUid">Calendar Uid</param>
            /// <param name="index">Calendar exception index</param>
            /// <param name="fileName">The name of the project document to save changes to. If this parameter is omitted then the changes will be saved to the source project document.</param>
            /// <param name="folder">The document folder.</param>
            /// <param name="storage">The document storage.</param>
            public void DeletesCalendarException(string name, int calendarUid, int index, string fileName, string folder, string storage = "")
            {
                // DELETE 	tasks/{name}/calendars/{calendarUid}/calendarExceptions/{index}?appSid={appSid}&fileName={fileName}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"tasks/{0}/calendars/{1}/calendarExceptions/{2}?fileName={3}&storage={4}&folder={5}",
                                                name, calendarUid, index, fileName, storage, folder);

                ServiceController.Delete(apiUrl, AppSid, AppKey);
            }
        }

        public class TasksCriticalPathClass
        {
            public string AppKey { get; set; }
            public string AppSid { get; set; }

            internal TasksCriticalPathClass(string appSid, string appKey) { AppSid = appSid; AppKey = appKey; }

            /// <summary>
            /// Returns created report in PDF format.	
            /// </summary>
            /// <param name="name">The name of the file.</param>
            /// <param name="folder">The document folder.</param>
            /// <param name="storage">The document storage.</param>
            public TasksResponse ReturnsCreatedReportInPdfFormat(string name, string folder, string storage = "")
            {
                // GET 	tasks/{name}/criticalPath?appSid={appSid}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"tasks/{0}/criticalPath?storage={1}&folder={2}",
                                                name, storage, folder);

                JObject jObject = JObject.Parse(ServiceController.Get(apiUrl, AppSid, AppKey));
                TasksResponse tasksResponse = jObject.ToObject<TasksResponse>();
                return tasksResponse;
            }
        }

        public class TasksDocumentPropertiesClass
        {
            public string AppKey { get; set; }
            public string AppSid { get; set; }

            internal TasksDocumentPropertiesClass(string appSid, string appKey) { AppSid = appSid; AppKey = appKey; }

            /// <summary>
            /// Read document properties.	
            /// </summary>
            /// <param name="name">The name of the file.</param>
            /// <param name="folder">The document folder.</param>
            /// <param name="storage">The document storage.</param>
            public TasksPropertiesResponse ReadDocumentProperties(string name, string folder, string storage = "")
            {
                // GET 	tasks/{name}/documentproperties?appSid={appSid}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"tasks/{0}/documentproperties?storage={1}&folder={2}",
                                                name, storage, folder);

                JObject jObject = JObject.Parse(ServiceController.Get(apiUrl, AppSid, AppKey));
                TasksPropertiesResponse tasksPropertiesResponse = jObject.ToObject<TasksPropertiesResponse>();
                return tasksPropertiesResponse;
            }

            /// <summary>
            /// Read document property by name.	
            /// </summary>
            /// <param name="name">The name of the file.</param>
            /// <param name="propertyName">The property name.</param>
            /// <param name="folder">The document folder.</param>
            /// <param name="storage">The document storage.</param>
            public TasksPropertyResponse ReadDocumentPropertyByName(string name, string propertyName, string folder, string storage = "")
            {
                // GET 	tasks/{name}/documentproperties/{propertyName}?appSid={appSid}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"tasks/{0}/documentproperties/{1}?storage={2}&folder={3}",
                                                name, propertyName, storage, folder);

                JObject jObject = JObject.Parse(ServiceController.Get(apiUrl, AppSid, AppKey));
                TasksPropertyResponse tasksPropertyResponse = jObject.ToObject<TasksPropertyResponse>();
                return tasksPropertyResponse;
            }

            /// <summary>
            /// Create document property.	
            /// </summary>
            /// <param name="name">The name of the file.</param>
            /// <param name="propertyName">The property name.</param>
            /// <param name="fileName">The name of the project document to save changes to. If this parameter is omitted then the changes will be saved to the source project document.</param>
            /// <param name="folder">The document folder.</param>
            /// <param name="storage">The document storage.</param>
            public void CreateDocumentProperty(string name, string propertyName, TasksProperty tasksProperty, string filename, string folder, string storage = "")
            {
                // PUT 	tasks/{name}/documentproperties/{propertyName}?appSid={appSid}&storage={storage}&folder={folder}&filename={filename} 

                string apiUrl = string.Format(@"tasks/{0}/documentproperties/{1}?storage={2}&folder={3}&filename={4}",
                                                name, propertyName, storage, folder, filename);

                ServiceController.Post(apiUrl, AppSid, AppKey, JsonConvert.SerializeObject(tasksProperty));
            }

            /// <summary>
            /// Set document property.	
            /// </summary>
            /// <param name="name">The name of the file.</param>
            /// <param name="propertyName">The property name.</param>
            /// <param name="fileName">The name of the project document to save changes to. If this parameter is omitted then the changes will be saved to the source project document.</param>
            /// <param name="folder">The document folder.</param>
            /// <param name="storage">The document storage.</param>
            public void SetDocumentProperty(string name, string propertyName, TasksProperty tasksProperty, string filename, string folder, string storage = "")
            {
                // PUT 	tasks/{name}/documentproperties/{propertyName}?appSid={appSid}&storage={storage}&folder={folder}&filename={filename} 

                string apiUrl = string.Format(@"tasks/{0}/documentproperties/{1}?storage={2}&folder={3}&filename={4}",
                                                name, propertyName, storage, folder, filename);

                ServiceController.Post(apiUrl, AppSid, AppKey, JsonConvert.SerializeObject(tasksProperty));
            }
        }

        public class TasksExtendedAttributesClass
        {
            public string AppKey { get; set; }
            public string AppSid { get; set; }

            internal TasksExtendedAttributesClass(string appSid, string appKey) { AppSid = appSid; AppKey = appKey; }

            /// <summary>
            /// Represents a project document.	
            /// </summary>
            /// <param name="name">The name of the file.</param>
            /// <param name="folder">The document folder.</param>
            /// <param name="storage">The document storage.</param>
            public ExtendedAttributesResponse GetExtendedAttributes(string name, string folder, string storage = "")
            {
                // GET 	tasks/{name}/extendedAttributes?appSid={appSid}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"tasks/{0}/extendedAttributes?storage={1}&folder={2}",
                                                name, storage, folder);

                JObject jObject = JObject.Parse(ServiceController.Get(apiUrl, AppSid, AppKey));
                ExtendedAttributesResponse extendedAttributesResponse = jObject.ToObject<ExtendedAttributesResponse>();
                return extendedAttributesResponse;
            }

            /// <summary>
            /// Represents a project extended attribute definition.	
            /// </summary>
            /// <param name="name">The name of the file.</param>
            /// <param name="index">Index of extended attribute</param>
            /// <param name="folder">The document folder.</param>
            /// <param name="storage">The document storage.</param>
            public ExtendedAttributeResponse GetExtendedAttributeDefinition(string name, int index, string folder, string storage = "")
            {
                // GET 	tasks/{name}/extendedAttributes/{index}?appSid={appSid}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"tasks/{0}/extendedAttributes/{1}?storage={2}&folder={3}",
                                                name, index, storage, folder);

                JObject jObject = JObject.Parse(ServiceController.Get(apiUrl, AppSid, AppKey));
                ExtendedAttributeResponse extendedAttributeResponse = jObject.ToObject<ExtendedAttributeResponse>();
                return extendedAttributeResponse;
            }

            /// <summary>
            /// Delete a project extended attribute.	
            /// </summary>
            /// <param name="name">The name of the file.</param>
            /// <param name="index"></param>
            /// <param name="folder">The document folder.</param>
            /// <param name="storage">The document storage.</param>
            public void DeleteProjectExtendedAttribute(string name, int index, string folder, string storage = "")
            {
                // DELETE 	tasks/{name}/extendedAttributes/{index}?appSid={appSid}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"tasks/{0}/extendedAttributes/{1}?storage={2}&folder={3}",
                                                name, index, storage, folder);

                ServiceController.Delete(apiUrl, AppSid, AppKey);
            }
        }

        public class TasksOutlineCodesClass
        {
            public string AppKey { get; set; }
            public string AppSid { get; set; }

            internal TasksOutlineCodesClass(string appSid, string appKey) { AppSid = appSid; AppKey = appKey; }

            /// <summary>
            /// Read outline codes.	
            /// </summary>
            /// <param name="name">The name of the file.</param>
            /// <param name="folder">The document folder.</param>
            /// <param name="storage">The document storage.</param>
            public OutlineCodesResponse ReadOutlineCodes(string name, string folder, string storage = "")
            {
                // GET 	tasks/{name}/outlineCodes?appSid={appSid}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"tasks/{0}/outlineCodes?storage={1}&folder={2}",
                                                name, storage, folder);

                JObject jObject = JObject.Parse(ServiceController.Get(apiUrl, AppSid, AppKey));
                OutlineCodesResponse outlineCodesResponse = jObject.ToObject<OutlineCodesResponse>();
                return outlineCodesResponse;
            }

            /// <summary>
            /// Get outline code by index.	
            /// </summary>
            /// <param name="name">The name of the file.</param>
            /// <param name="index">Outline code index</param>
            /// <param name="folder">The document folder.</param>
            /// <param name="storage">The document storage.</param>
            public OutlineCodeResponse GetOutlineCodeByIndex(string name, int index, string folder, string storage = "")
            {
                // GET 	tasks/{name}/outlineCodes/{index}?appSid={appSid}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"tasks/{0}/outlineCodes/{1}?storage={2}&folder={3}",
                                                name, index, storage, folder);

                JObject jObject = JObject.Parse(ServiceController.Get(apiUrl, AppSid, AppKey));
                OutlineCodeResponse outlineCodeResponse = jObject.ToObject<OutlineCodeResponse>();
                return outlineCodeResponse;
            }

            /// <summary>
            /// Deletes a project outline code	
            /// </summary>
            /// <param name="name">The name of the file.</param>
            /// <param name="index">Outline code index</param>
            /// <param name="folder">The document folder.</param>
            /// <param name="storage">The document storage.</param>
            public void DeleteProjectOutlineCode(string name, int index, string folder, string storage = "")
            {
                // DELETE 	tasks/{name}/outlineCodes/{index}?appSid={appSid}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"tasks/{0}/outlineCodes/{1}?storage={2}&folder={3}",
                                                name, index, storage, folder);

                ServiceController.Delete(apiUrl, AppSid, AppKey);
            }
        }

        public class TasksReportClass
        {
            public string AppKey { get; set; }
            public string AppSid { get; set; }

            internal TasksReportClass(string appSid, string appKey) { AppSid = appSid; AppKey = appKey; }

            /// <summary>
            /// Returns created report in PDF format.	
            /// </summary>
            /// <param name="name">The name of the file.</param>
            /// <param name="type">A type of the project's graphical report.</param>
            /// <param name="folder">The document folder.</param>
            /// <param name="storage">The document storage.</param>
            public void CreateReportInPdfFormat(string name, TasksReportType type, string folder, string localPathWithFileNameAndExtension, string storage = "")
            {
                // GET 	tasks/{name}/report?appSid={appSid}&type={type}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"tasks/{0}/report?type={1}&storage={2}&folder={3}",
                                                name, type.GetHashCode(), storage, folder);

                using (Stream responseStream = ServiceController.GetStream(apiUrl, AppSid, AppKey))
                using (Stream file = File.OpenWrite(localPathWithFileNameAndExtension))
                {
                    ServiceController.CopyStream(responseStream, file);
                }
            }
        }

        public class TasksResourcesClass
        {
            public string AppKey { get; set; }
            public string AppSid { get; set; }

            internal TasksResourcesClass(string appSid, string appKey) { AppSid = appSid; AppKey = appKey; }

            /// <summary>
            /// Read project resources.	
            /// </summary>
            /// <param name="name">The name of the file.</param>
            /// <param name="folder">The document folder.</param>
            /// <param name="storage">The document storage.</param>
            public TasksResourcesResponse ReadProjectResources(string name, string folder, string storage = "")
            {
                // GET 	tasks/{name}/resources?appSid={appSid}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"tasks/{0}/resources?storage={1}&folder={2}",
                                                name, storage, folder);

                JObject jObject = JObject.Parse(ServiceController.Get(apiUrl, AppSid, AppKey));
                TasksResourcesResponse tasksResourcesResponse = jObject.ToObject<TasksResourcesResponse>();
                return tasksResourcesResponse;
            }

            /// <summary>
            /// Add a new resource to a project.	
            /// </summary>
            /// <param name="name">The name of the file.</param>
            /// <param name="resourceName">The name of the new resource. The default value is an empty string</param>
            /// <param name="beforeResourceId">The id of the resource to insert the new resource before. The default value is the id of the last resource in a project file.</param>
            /// <param name="fileName">The name of the project document to save changes to. If this parameter is omitted then the changes will be saved to the source project document.</param>
            /// <param name="folder">The document folder.</param>
            /// <param name="storage">The document storage.</param>
            public TasksResourceItemResponse AddNewResourceToProject(string name, string resourceName, int beforeResourceId, string fileName, string folder, string storage = "")
            {
                // POST 	tasks/{name}/resources?appSid={appSid}&resourceName={resourceName}&beforeResourceId={beforeResourceId}&fileName={fileName}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"tasks/{0}/resources?resourceName={1}&beforeResourceId={2}&fileName={3}&storage={4}&folder={5}",
                                                name, resourceName, beforeResourceId, fileName, storage, folder);

                
                JObject jObject = JObject.Parse(ServiceController.Post(apiUrl, AppSid, AppKey));
                TasksResourceItemResponse tasksResourceItemResponse = jObject.ToObject<TasksResourceItemResponse>();
                return tasksResourceItemResponse;
            }

            /// <summary>
            /// Get project resource.	
            /// </summary>
            /// <param name="name">The name of the file.</param>
            /// <param name="resourceUid">The unique id of the resource to be assigned. </param>
            /// <param name="folder">The document folder.</param>
            /// <param name="storage">The document storage.</param>
            public TasksResourceResponse  GetProjectResource(string name, int resourceUid, string folder, string storage = "")
            {
                // GET 	tasks/{name}/resources/{resourceUid}?appSid={appSid}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"tasks/{0}/resources/{1}?storage={2}&folder={3}",
                                                name, resourceUid, storage, folder);

                JObject jObject = JObject.Parse(ServiceController.Get(apiUrl, AppSid, AppKey));
                TasksResourceResponse tasksResourceResponse = jObject.ToObject<TasksResourceResponse>();
                return tasksResourceResponse;
            }

            /// <summary>
            /// Deletes a project resource with all references to it	
            /// </summary>
            /// <param name="name">The name of the file.</param>
            /// <param name="resourceUid">The unique id of the resource to be assigned.</param>
            /// <param name="fileName">The name of the project document to save changes to. If this parameter is omitted then the changes will be saved to the source project document.</param>
            /// <param name="folder">The document folder.</param>
            /// <param name="storage">The document storage.</param>
            public void DeleteProjectResource(string name, int resourceUid, string fileName, string folder, string storage = "")
            {
                // DELETE 	tasks/{name}/resources/{resourceUid}?appSid={appSid}&storage={storage}&folder={folder}&fileName={fileName} 

                string apiUrl = string.Format(@"tasks/{0}/resources/{1}?storage={2}&folder={3}&fileName={4}",
                                                name, resourceUid, storage, folder, fileName);

                ServiceController.Delete(apiUrl, AppSid, AppKey);
            }

            /// <summary>
            /// Get resource assignments.	
            /// </summary>
            /// <param name="name">The name of the file.</param>
            /// <param name="resourceUid">The unique id of the resource to be assigned.</param>
            /// <param name="folder">The document folder.</param>
            /// <param name="storage">The document storage.</param>
            public TasksAssignmentsResponse GetResourceAssignments(string name, int resourceUid, string folder, string storage = "")
            {
                // GET 	tasks/{name}/resources/{resourceUid}/assignments?appSid={appSid}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"tasks/{0}/resources/{1}/assignments?storage={2}&folder={3}",
                                                name, resourceUid, storage, folder);

                JObject jObject = JObject.Parse(ServiceController.Get(apiUrl, AppSid, AppKey));
                TasksAssignmentsResponse tasksAssignmentsResponse = jObject.ToObject<TasksAssignmentsResponse>();
                return tasksAssignmentsResponse;
            }
        }

        public class TasksTaskClass
        {
            public string AppKey { get; set; }
            public string AppSid { get; set; }

            internal TasksTaskClass(string appSid, string appKey) { AppSid = appSid; AppKey = appKey; }

            /// <summary>
            /// Read project task items.	
            /// </summary>
            /// <param name="name">The name of the file.</param>
            /// <param name="folder">The document folder.</param>
            /// <param name="storage">The document storage.</param>
            public TasksResponse ReadProjectTaskItems(string name, string folder, string storage = "")
            {
                // GET 	tasks/{name}/tasks?appSid={appSid}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"tasks/{0}/tasks?storage={1}&folder={2}",
                                                name, storage, folder);

                JObject jObject = JObject.Parse(ServiceController.Get(apiUrl, AppSid, AppKey));
                TasksResponse tasksResponse = jObject.ToObject<TasksResponse>();
                return tasksResponse;
            }

            /// <summary>
            /// Add a new task to a project.	
            /// </summary>
            /// <param name="name">The name of the file.</param>
            /// <param name="taskName">The name of the new task. The default value is an empty string</param>
            /// <param name="beforeTaskId">The id of the task to insert the new task before. The default value is the id of the last task in a project file.</param>
            /// <param name="fileName">The name of the project document to save changes to. If this parameter is omitted then the changes will be saved to the source project document.</param>
            /// <param name="folder">The document folder.</param>
            /// <param name="storage">The document storage.</param>
            public TaskItemResponse AddANewTaskToAProject(string name, string taskName, int beforeTaskId, string fileName, string folder, string storage = "")
            {
                // POST 	tasks/{name}/tasks?appSid={appSid}&taskName={taskName}&beforeTaskId={beforeTaskId}&fileName={fileName}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"tasks/{0}/tasks?taskName={1}&beforeTaskId={2}&fileName={3}&storage={4}&folder={5}",
                                                name, taskName, beforeTaskId, fileName, storage, folder);

                JObject jObject = JObject.Parse(ServiceController.Post(apiUrl, AppSid, AppKey));
                TaskItemResponse taskItemResponse = jObject.ToObject<TaskItemResponse>();
                return taskItemResponse;
            }

            /// <summary>
            /// Read project task.	
            /// </summary>
            /// <param name="name">The name of the file.</param>
            /// <param name="taskUid">The unique id of the task to be assigned.</param>
            /// <param name="folder">The document folder.</param>
            /// <param name="storage">The document storage.</param>
            public TaskResponse ReadProjectTask(string name, int taskUid, string folder, string storage = "")
            {
                // GET 	tasks/{name}/tasks/{taskUid}?appSid={appSid}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"tasks/{0}/tasks/{1}?storage={2}&folder={3}",
                                                name, taskUid, storage, folder);

                JObject jObject = JObject.Parse(ServiceController.Get(apiUrl, AppSid, AppKey));
                TaskResponse taskResponse = jObject.ToObject<TaskResponse>();
                return taskResponse;
            }

            /// <summary>
            /// Deletes a project task with all references to it and rebuilds tasks tree.	
            /// </summary>
            /// <param name="name">The name of the file.</param>
            /// <param name="taskUid">The unique id of the task to be assigned.</param>            
            /// <param name="fileName">The name of the project document to save changes to. If this parameter is omitted then the changes will be saved to the source project document.</param>
            /// <param name="folder">The document folder.</param>
            /// <param name="storage">The document storage.</param>
            public void DeleteProjectTask(string name, int taskUid, string fileName, string folder, string storage = "")
            {
                // DELETE 	tasks/{name}/tasks/{taskUid}?appSid={appSid}&storage={storage}&folder={folder}&fileName={fileName} 

                string apiUrl = string.Format(@"tasks/{0}/tasks/{1}?storage={2}&folder={3}&fileName={4}",
                                                name, taskUid, storage, folder, fileName);

                ServiceController.Delete(apiUrl, AppSid, AppKey);
            }

            /// <summary>
            /// Get task assignments.	
            /// </summary>
            /// <param name="name">The name of the file.</param>
            /// <param name="taskUid">The unique id of the task to be assigned.</param>
            /// <param name="folder">The document folder.</param>
            /// <param name="storage">The document storage.</param>
            public TasksAssignmentsResponse GetTaskAssignments(string name, int taskUid, string folder, string storage = "")
            {
                // GET 	tasks/{name}/tasks/{taskUid}/assignments?appSid={appSid}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"tasks/{0}/tasks/{1}/assignments?storage={2}&folder={3}",
                                                name, taskUid, storage, folder);

                JObject jObject = JObject.Parse(ServiceController.Get(apiUrl, AppSid, AppKey));
                TasksAssignmentsResponse tasksAssignmentsResponse = jObject.ToObject<TasksAssignmentsResponse>();
                return tasksAssignmentsResponse;
            }
        }

        public class TasksTaskLinksClass
        {
            public string AppKey { get; set; }
            public string AppSid { get; set; }

            internal TasksTaskLinksClass(string appSid, string appKey) { AppSid = appSid; AppKey = appKey; }

            /// <summary>
            /// Read task links.	
            /// </summary>
            /// <param name="name">The name of the file.</param>
            /// <param name="folder">The document folder.</param>
            /// <param name="storage">The document storage.</param>
            public TaskLinkResponse ReadTaskLinks(string name, string folder, string storage = "")
            {
                // GET 	tasks/{name}/taskLinks?appSid={appSid}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"tasks/{0}/taskLinks?storage={1}&folder={2}",
                                                name, storage, folder);

                JObject jObject = JObject.Parse(ServiceController.Get(apiUrl, AppSid, AppKey));
                TaskLinkResponse taskLinkResponse = jObject.ToObject<TaskLinkResponse>();
                return taskLinkResponse;
            }

            /// <summary>
            /// Adds a new task link to a project.	
            /// </summary>
            /// <param name="name">The name of the file.</param>            
            /// <param name="fileName">The name of the project document to save changes to. If this parameter is omitted then the changes will be saved to the source project document.</param>
            /// <param name="folder">The document folder.</param>
            /// <param name="storage">The document storage.</param>
            public void AddNewTaskLinkToAProject(string name, string fileName, string folder, TaskLink taskLink, string storage = "")
            {
                // POST 	tasks/{name}/taskLinks?appSid={appSid}&storage={storage}&folder={folder}&fileName={fileName} 

                string apiUrl = string.Format(@"tasks/{0}/taskLinks?storage={1}&folder={2}&fileName={3}",
                                                name, storage, folder, fileName);

                ServiceController.Post(apiUrl, AppSid, AppKey, JsonConvert.SerializeObject(taskLink));
            }

            /// <summary>
            /// Updates task link.	
            /// </summary>
            /// <param name="name">The name of the file.</param>
            /// <param name="index">Task link index</param>
            /// <param name="fileName">The name of the project document to save changes to. If this parameter is omitted then the changes will be saved to the source project document.</param>
            /// <param name="folder">The document folder.</param>
            /// <param name="storage">The document storage.</param>
            public void UpdatesTaskLink(string name, int index, string fileName, string folder, TaskLink taskLink, string storage = "")
            {
                // PUT 	tasks/{name}/taskLinks/{index}?appSid={appSid}&storage={storage}&folder={folder}&fileName={fileName} 

                string apiUrl = string.Format(@"tasks/{0}/taskLinks/{1}?storage={2}&folder={3}&fileName={4}",
                                                name, index, storage, folder, fileName);

                ServiceController.Put(apiUrl, AppSid, AppKey, JsonConvert.SerializeObject(taskLink));
            }

            /// <summary>
            /// Delete task link.	
            /// </summary>
            /// <param name="name">The name of the file.</param>
            /// <param name="index">Task link index</param>
            /// <param name="fileName">The name of the project document to save changes to. If this parameter is omitted then the changes will be saved to the source project document.</param>
            /// <param name="folder">The document folder.</param>
            /// <param name="storage">The document storage.</param>
            public void DeleteTaskLink(string name, int index, string fileName, string folder, string storage = "")
            {
                // DELETE 	tasks/{name}/taskLinks/{index}?appSid={appSid}&storage={storage}&folder={folder}&fileName={fileName} 

                string apiUrl = string.Format(@"tasks/{0}/taskLinks/{1}?storage={2}&folder={3}&fileName={4}",
                                                name, index, storage, folder, fileName);

                ServiceController.Delete(apiUrl, AppSid, AppKey);
            }
        }
    }

    public class TaskLink
    {
        public Link Link { get; set; }
        public int Index { get; set; }
        public int PredecessorUid { get; set; }
        public int SuccessorUid { get; set; }
        public int LinkType { get; set; }
        public int Lag { get; set; }
        public int LagFormat { get; set; }
    }

    public class TaskLinkResponse
    {
        public List<TaskLink> TaskLinks { get; set; }
        public int Code { get; set; }
        public string Status { get; set; }
    }

    public class Task
    {
        public int Uid { get; set; }
        public int Id { get; set; }
        public string Name { get; set; }
        public string Duration { get; set; }
        public string Start { get; set; }
        public string Finish { get; set; }
        public int PercentComplete { get; set; }
        public int PercentWorkComplete { get; set; }
        public bool IsActive { get; set; }
        public double ActualCost { get; set; }
        public string ActualDuration { get; set; }
        public string ActualFinish { get; set; }
        public double ActualOvertimeCost { get; set; }
        public string ActualOvertimeWork { get; set; }
        public string ActualStart { get; set; }
        public string BudgetWork { get; set; }
        public double BudgetCost { get; set; }
        public string ConstraintDate { get; set; }
        public int ConstraintType { get; set; }
        public object Contact { get; set; }
        public double Cost { get; set; }
        public double Cv { get; set; }
        public string Deadline { get; set; }
        public string DurationVariance { get; set; }
        public string EarlyFinish { get; set; }
        public string EarlyStart { get; set; }
        public bool IsEffortDriven { get; set; }
        public bool IsExternalTask { get; set; }
        public int FinishSlack { get; set; }
        public int FinishVariance { get; set; }
        public double FixedCost { get; set; }
        public int FixedCostAccrual { get; set; }
        public int FreeSlack { get; set; }
        public string GUID { get; set; }
        public bool HideBar { get; set; }
        public bool IgnoreResourceCalendar { get; set; }
        public string LateFinish { get; set; }
        public string LateStart { get; set; }
        public bool IsLevelAssignments { get; set; }
        public bool CanLevelingSplit { get; set; }
        public int LevelingDelay { get; set; }
        public bool IsMarked { get; set; }
        public bool IsMilestone { get; set; }
        public bool IsCritical { get; set; }
        public bool IsSubproject { get; set; }
        public bool IsSubprojectReadOnly { get; set; }
        public object SubprojectName { get; set; }
        public bool IsSummary { get; set; }
        public List<object> SubtasksUids { get; set; }
        public int OutlineLevel { get; set; }
        public bool IsOverAllocated { get; set; }
        public bool IsEstimated { get; set; }
        public double OvertimeCost { get; set; }
        public string OvertimeWork { get; set; }
        public int PhysicalPercentComplete { get; set; }
        public string PreLeveledFinish { get; set; }
        public string PreLeveledStart { get; set; }
        public bool IsRecurring { get; set; }
        public string RegularWork { get; set; }
        public double RemainingCost { get; set; }
        public string RemainingDuration { get; set; }
        public double RemainingOvertimeCost { get; set; }
        public string RemainingOvertimeWork { get; set; }
        public string RemainingWork { get; set; }
        public string Resume { get; set; }
        public bool IsRollup { get; set; }
        public int StartSlack { get; set; }
        public int StartVariance { get; set; }
        public int CalendarUid { get; set; }
        public bool IsManual { get; set; }
        public int TotalSlack { get; set; }
        public int Type { get; set; }
        public string Wbs { get; set; }
        public int Priority { get; set; }
        public string Work { get; set; }
        public double WorkVariance { get; set; }
        public object NotesText { get; set; }
        public double Acwp { get; set; }
        public double Bcws { get; set; }
        public double Bcwp { get; set; }
        public int LevelingDelayFromat { get; set; }
        public string BaselineStart { get; set; }
        public string BaselineFinish { get; set; }
        public string BaselineDuration { get; set; }
        public double BaselineFixedCost { get; set; }
        public int BaselineDurationFormat { get; set; }
        public bool BaselineEstimatedDuration { get; set; }
        public string BaselineWork { get; set; }
        public double BaselineCost { get; set; }
        public double BaselineBcws { get; set; }
        public double BaselineBcwp { get; set; }
        public string Baseline1Start { get; set; }
        public string Baseline1Finish { get; set; }
        public string Baseline1Duration { get; set; }
        public double Baseline1FixedCost { get; set; }
        public int Baseline1DurationFormat { get; set; }
        public bool Baseline1EstimatedDuration { get; set; }
        public string Baseline1Work { get; set; }
        public double Baseline1Cost { get; set; }
        public double Baseline1Bcws { get; set; }
        public double Baseline1Bcwp { get; set; }
        public string Baseline2Start { get; set; }
        public string Baseline2Finish { get; set; }
        public string Baseline2Duration { get; set; }
        public double Baseline2FixedCost { get; set; }
        public int Baseline2DurationFormat { get; set; }
        public bool Baseline2EstimatedDuration { get; set; }
        public string Baseline2Work { get; set; }
        public double Baseline2Cost { get; set; }
        public double Baseline2Bcws { get; set; }
        public double Baseline2Bcwp { get; set; }
        public string Baseline3Start { get; set; }
        public string Baseline3Finish { get; set; }
        public string Baseline3Duration { get; set; }
        public double Baseline3FixedCost { get; set; }
        public int Baseline3DurationFormat { get; set; }
        public bool Baseline3EstimatedDuration { get; set; }
        public string Baseline3Work { get; set; }
        public double Baseline3Cost { get; set; }
        public double Baseline3Bcws { get; set; }
        public double Baseline3Bcwp { get; set; }
        public string Baseline4Start { get; set; }
        public string Baseline4Finish { get; set; }
        public string Baseline4Duration { get; set; }
        public double Baseline4FixedCost { get; set; }
        public int Baseline4DurationFormat { get; set; }
        public bool Baseline4EstimatedDuration { get; set; }
        public string Baseline4Work { get; set; }
        public double Baseline4Cost { get; set; }
        public double Baseline4Bcws { get; set; }
        public double Baseline4Bcwp { get; set; }
        public string Baseline5Start { get; set; }
        public string Baseline5Finish { get; set; }
        public string Baseline5Duration { get; set; }
        public double Baseline5FixedCost { get; set; }
        public int Baseline5DurationFormat { get; set; }
        public bool Baseline5EstimatedDuration { get; set; }
        public string Baseline5Work { get; set; }
        public double Baseline5Cost { get; set; }
        public double Baseline5Bcws { get; set; }
        public double Baseline5Bcwp { get; set; }
        public string Baseline6Start { get; set; }
        public string Baseline6Finish { get; set; }
        public string Baseline6Duration { get; set; }
        public double Baseline6FixedCost { get; set; }
        public int Baseline6DurationFormat { get; set; }
        public bool Baseline6EstimatedDuration { get; set; }
        public string Baseline6Work { get; set; }
        public double Baseline6Cost { get; set; }
        public double Baseline6Bcws { get; set; }
        public double Baseline6Bcwp { get; set; }
        public string Baseline7Start { get; set; }
        public string Baseline7Finish { get; set; }
        public string Baseline7Duration { get; set; }
        public double Baseline7FixedCost { get; set; }
        public int Baseline7DurationFormat { get; set; }
        public bool Baseline7EstimatedDuration { get; set; }
        public string Baseline7Work { get; set; }
        public double Baseline7Cost { get; set; }
        public double Baseline7Bcws { get; set; }
        public double Baseline7Bcwp { get; set; }
        public string Baseline8Start { get; set; }
        public string Baseline8Finish { get; set; }
        public string Baseline8Duration { get; set; }
        public double Baseline8FixedCost { get; set; }
        public int Baseline8DurationFormat { get; set; }
        public bool Baseline8EstimatedDuration { get; set; }
        public string Baseline8Work { get; set; }
        public double Baseline8Cost { get; set; }
        public double Baseline8Bcws { get; set; }
        public double Baseline8Bcwp { get; set; }
        public string Baseline9Start { get; set; }
        public string Baseline9Finish { get; set; }
        public string Baseline9Duration { get; set; }
        public double Baseline9FixedCost { get; set; }
        public int Baseline9DurationFormat { get; set; }
        public bool Baseline9EstimatedDuration { get; set; }
        public string Baseline9Work { get; set; }
        public double Baseline9Cost { get; set; }
        public double Baseline9Bcws { get; set; }
        public double Baseline9Bcwp { get; set; }
        public string Baseline10Start { get; set; }
        public string Baseline10Finish { get; set; }
        public string Baseline10Duration { get; set; }
        public double Baseline10FixedCost { get; set; }
        public int Baseline10DurationFormat { get; set; }
        public bool BaselineEstimated10Duration { get; set; }
        public string Baseline10Work { get; set; }
        public double Baseline10Cost { get; set; }
        public double Baseline10Bcws { get; set; }
        public double Baseline10Bcwp { get; set; }
        public List<object> ExtendedAttributes { get; set; }
        public List<object> OutlineCodes { get; set; }
    }

    public class TaskResponse
    {
        public Task Task { get; set; }
        public int Code { get; set; }
        public string Status { get; set; }
    }

    public class TaskItemResponse
    {
        public TaskItem TaskItem { get; set; }
        public int Code { get; set; }
        public string Status { get; set; }
    }

    public class TaskItem
    {
        public Link Link { get; set; }
        public int Uid { get; set; }
        public int Id { get; set; }
        public string Name { get; set; }
        public string Start { get; set; }
        public string Finish { get; set; }
        public string Duration { get; set; }
    }

    public class Tasks
    {
        public List<TaskItem> TaskItem { get; set; }
        public Link link { get; set; }
    }

    public class TasksResponse
    {
        public Tasks Tasks { get; set; }
        public int Code { get; set; }
        public string Status { get; set; }
    }
    
    public class TasksResourceAssignmnetList
    {
        public int TaskUid { get; set; }
        public int ResourceUid { get; set; }
        public int Uid { get; set; }
        public int PercentWorkComplete { get; set; }
        public double ActualCost { get; set; }
        public string ActualFinish { get; set; }
        public double ActualOvertimeCost { get; set; }
        public string ActualOvertimeWork { get; set; }
        public string ActualStart { get; set; }
        public string ActualWork { get; set; }
        public double Acwp { get; set; }
        public bool Confirmed { get; set; }
        public double Cost { get; set; }
        public int CostRateTableType { get; set; }
        public double CostVariance { get; set; }
        public double Cv { get; set; }
        public int Delay { get; set; }
        public string Finish { get; set; }
        public int FinishVariance { get; set; }
        public object Hyperlink { get; set; }
        public object HyperlinkAddress { get; set; }
        public object HyperlinkSubAddress { get; set; }
        public double WorkVariance { get; set; }
        public bool HasFixedRateUnits { get; set; }
        public bool FixedMaterial { get; set; }
        public int LevelingDelay { get; set; }
        public int LevelingDelayFormat { get; set; }
        public bool LinkedFields { get; set; }
        public bool Milestone { get; set; }
        public object Notes { get; set; }
        public bool Overallocated { get; set; }
        public double OvertimeCost { get; set; }
        public string OvertimeWork { get; set; }
        public double PeakUnits { get; set; }
        public string RegularWork { get; set; }
        public double RemainingCost { get; set; }
        public double RemainingOvertimeCost { get; set; }
        public string RemainingOvertimeWork { get; set; }
        public string RemainingWork { get; set; }
        public bool ResponsePending { get; set; }
        public string Start { get; set; }
        public string Stop { get; set; }
        public string Resume { get; set; }
        public int StartVariance { get; set; }
        public bool Summary { get; set; }
        public double Sv { get; set; }
        public double Units { get; set; }
        public bool UpdateNeeded { get; set; }
        public double Vac { get; set; }
        public string Work { get; set; }
        public int WorkContour { get; set; }
        public double Bcws { get; set; }
        public double Bcwp { get; set; }
        public int BookingType { get; set; }
        public string ActualWorkProtected { get; set; }
        public string ActualOvertimeWorkProtected { get; set; }
        public string CreationDate { get; set; }
        public object AssnOwner { get; set; }
        public object AssnOwnerGuid { get; set; }
        public double BudgetCost { get; set; }
        public string BudgetWork { get; set; }
        public int RateScale { get; set; }
        public string BaselineStart { get; set; }
        public string BaselineFinish { get; set; }
        public string BaselineWork { get; set; }
        public double BaselineCost { get; set; }
        public double BaselineBcws { get; set; }
        public double BaselineBcwp { get; set; }
        public string Baseline1Start { get; set; }
        public string Baseline1Finish { get; set; }
        public string Baseline1Work { get; set; }
        public double Baseline1Cost { get; set; }
        public double Baseline1Bcws { get; set; }
        public double Baseline1Bcwp { get; set; }
        public string Baseline2Start { get; set; }
        public string Baseline2Finish { get; set; }
        public string Baseline2Work { get; set; }
        public double Baseline2Cost { get; set; }
        public double Baseline2Bcws { get; set; }
        public double Baseline2Bcwp { get; set; }
        public string Baseline3Start { get; set; }
        public string Baseline3Finish { get; set; }
        public string Baseline3Work { get; set; }
        public double Baseline3Cost { get; set; }
        public double Baseline3Bcws { get; set; }
        public double Baseline3Bcwp { get; set; }
        public string Baseline4Start { get; set; }
        public string Baseline4Finish { get; set; }
        public string Baseline4Work { get; set; }
        public double Baseline4Cost { get; set; }
        public double Baseline4Bcws { get; set; }
        public double Baseline4Bcwp { get; set; }
        public string Baseline5Start { get; set; }
        public string Baseline5Finish { get; set; }
        public string Baseline5Work { get; set; }
        public double Baseline5Cost { get; set; }
        public double Baseline5Bcws { get; set; }
        public double Baseline5Bcwp { get; set; }
        public string Baseline6Start { get; set; }
        public string Baseline6Finish { get; set; }
        public string Baseline6Work { get; set; }
        public double Baseline6Cost { get; set; }
        public double Baseline6Bcws { get; set; }
        public double Baseline6Bcwp { get; set; }
        public string Baseline7Start { get; set; }
        public string Baseline7Finish { get; set; }
        public string Baseline7Work { get; set; }
        public double Baseline7Cost { get; set; }
        public double Baseline7Bcws { get; set; }
        public double Baseline7Bcwp { get; set; }
        public string Baseline8Start { get; set; }
        public string Baseline8Finish { get; set; }
        public string Baseline8Work { get; set; }
        public double Baseline8Cost { get; set; }
        public double Baseline8Bcws { get; set; }
        public double Baseline8Bcwp { get; set; }
        public string Baseline9Start { get; set; }
        public string Baseline9Finish { get; set; }
        public string Baseline9Work { get; set; }
        public double Baseline9Cost { get; set; }
        public double Baseline9Bcws { get; set; }
        public double Baseline9Bcwp { get; set; }
        public string Baseline10Start { get; set; }
        public string Baseline10Finish { get; set; }
        public string Baseline10Work { get; set; }
        public double Baseline10Cost { get; set; }
        public double Baseline10Bcws { get; set; }
        public double Baseline10Bcwp { get; set; }
        public List<object> ExtendedAttributes { get; set; }
    }

    public class TasksAssignments
    {
        public List<TasksResourceAssignmnetList> List { get; set; }
        public Link link { get; set; }
    }

    public class TasksAssignmentsResponse
    {
        public TasksAssignments Assignments { get; set; }
        public int Code { get; set; }
        public string Status { get; set; }
    }
    
    public class TasksResource
    {
        public string Name { get; set; }
        public int Uid { get; set; }
        public int Id { get; set; }
        public int Type { get; set; }
        public bool IsNull { get; set; }
        public string Initials { get; set; }
        public object Phonetics { get; set; }
        public object NtAccount { get; set; }
        public object MaterialLabel { get; set; }
        public object Code { get; set; }
        public object Group { get; set; }
        public object EmailAddress { get; set; }
        public object Hyperlink { get; set; }
        public object HyperlinkAddress { get; set; }
        public object HyperlinkSubAddress { get; set; }
        public double MaxUnits { get; set; }
        public double PeakUnits { get; set; }
        public bool OverAllocated { get; set; }
        public string AvailableFrom { get; set; }
        public string AvailableTo { get; set; }
        public string Start { get; set; }
        public string Finish { get; set; }
        public bool CanLevel { get; set; }
        public int AccrueAt { get; set; }
        public string Work { get; set; }
        public string RegularWork { get; set; }
        public string OvertimeWork { get; set; }
        public string ActualWork { get; set; }
        public string RemainingWork { get; set; }
        public string ActualOvertimeWork { get; set; }
        public string RemainingOvertimeWork { get; set; }
        public int PercentWorkComplete { get; set; }
        public double StandardRate { get; set; }
        public int StandardRateFormat { get; set; }
        public double Cost { get; set; }
        public double Overtime { get; set; }
        public int OvertimeRateFormat { get; set; }
        public double OvertimeCost { get; set; }
        public double CostPerUse { get; set; }
        public double ActualCost { get; set; }
        public double ActualOvertimeCost { get; set; }
        public double RemainingCost { get; set; }
        public double RemainingOvertimeCost { get; set; }
        public double WorkVariance { get; set; }
        public double CostVariance { get; set; }
        public double Sv { get; set; }
        public double Cv { get; set; }
        public double Acwp { get; set; }
        public int CalendarUid { get; set; }
        public object NotesText { get; set; }
        public double Bcws { get; set; }
        public double Bcwp { get; set; }
        public bool IsGeneric { get; set; }
        public bool IsInactive { get; set; }
        public bool IsEnterprise { get; set; }
        public int BookingType { get; set; }
        public string ActualWorkProtected { get; set; }
        public string ActualOvertimeWorkProtected { get; set; }
        public object ActiveDirectoryGuid { get; set; }
        public string CreationDate { get; set; }
        public object CostCenter { get; set; }
        public bool IsCostResource { get; set; }
        public bool TeamAssignmentPool { get; set; }
        public object AssignmentOwner { get; set; }
        public object AssignmentOwnerGuid { get; set; }
        public bool IsBudget { get; set; }
        public string BudgetWork { get; set; }
        public double BudgetCost { get; set; }
        public double OvertimeRate { get; set; }
        public string BaselineWork { get; set; }
        public double BaselineCost { get; set; }
        public double BaselineBcws { get; set; }
        public double BaselineBcwp { get; set; }
        public string Baseline1Work { get; set; }
        public double Baseline1Cost { get; set; }
        public double Baseline1Bcws { get; set; }
        public double Baseline1Bcwp { get; set; }
        public string Baseline2Work { get; set; }
        public double Baseline2Cost { get; set; }
        public double Baseline2Bcws { get; set; }
        public double Baseline2Bcwp { get; set; }
        public string Baseline3Work { get; set; }
        public double Baseline3Cost { get; set; }
        public double Baseline3Bcws { get; set; }
        public double Baseline3Bcwp { get; set; }
        public string Baseline4Work { get; set; }
        public double Baseline4Cost { get; set; }
        public double Baseline4Bcws { get; set; }
        public double Baseline4Bcwp { get; set; }
        public string Baseline5Work { get; set; }
        public double Baseline5Cost { get; set; }
        public double Baseline5Bcws { get; set; }
        public double Baseline5Bcwp { get; set; }
        public string Baseline6Work { get; set; }
        public double Baseline6Cost { get; set; }
        public double Baseline6Bcws { get; set; }
        public double Baseline6Bcwp { get; set; }
        public string Baseline7Work { get; set; }
        public double Baseline7Cost { get; set; }
        public double Baseline7Bcws { get; set; }
        public double Baseline7Bcwp { get; set; }
        public string Baseline8Work { get; set; }
        public double Baseline8Cost { get; set; }
        public double Baseline8Bcws { get; set; }
        public double Baseline8Bcwp { get; set; }
        public string Baseline9Work { get; set; }
        public double Baseline9Cost { get; set; }
        public double Baseline9Bcws { get; set; }
        public double Baseline9Bcwp { get; set; }
        public string Baseline10Work { get; set; }
        public double Baseline10Cost { get; set; }
        public double Baseline10Bcws { get; set; }
        public double Baseline10Bcwp { get; set; }
        public List<object> ExtendedAttributes { get; set; }
        public List<object> OutlineCodes { get; set; }
    }

    public class TasksResourceResponse
    {
        public TasksResource Resource { get; set; }
        public int Code { get; set; }
        public string Status { get; set; }
    }

    public class TasksResourceItemResponse
    {
        public TasksResourceItem ResourceItem { get; set; }
        public int Code { get; set; }
        public string Status { get; set; }
    }
    
    public class TasksResourceItem
    {
        public Link Link { get; set; }
        public int Uid { get; set; }
        public int Id { get; set; }
        public string Name { get; set; }
    }

    public class TasksResources
    {
        public List<TasksResourceItem> ResourceItem { get; set; }
        public Link link { get; set; }
    }

    public class TasksResourcesResponse
    {
        public TasksResources Resources { get; set; }
        public int Code { get; set; }
        public string Status { get; set; }
    }
    
    public class TasksOutlineValue
    {
        public int ValueId { get; set; }
        public string FieldGuid { get; set; }
        public int Type { get; set; }
        public int ParentValueId { get; set; }
        public string Value { get; set; }
        public string Description { get; set; }
        public bool IsCollapsed { get; set; }
    }

    public class Mask
    {
        public int Level { get; set; }
        public int Type { get; set; }
        public int Length { get; set; }
        public string Separator { get; set; }
    }

    public class OutlineCode
    {
        public string Guid { get; set; }
        public string FieldId { get; set; }
        public string FieldName { get; set; }
        public string Alias { get; set; }
        public string PhoneticAlias { get; set; }
        public List<TasksOutlineValue> Values { get; set; }
        public bool Enterprise { get; set; }
        public int EnterpriseOutlineCodeAlias { get; set; }
        public bool ResourceSubstitutionEnabled { get; set; }
        public bool LeafOnly { get; set; }
        public bool AllLevelsRequired { get; set; }
        public bool OnlyTableValuesAllowed { get; set; }
        public List<Mask> Masks { get; set; }
        public bool ShowIndent { get; set; }
    }

    public class OutlineCodeResponse
    {
        public OutlineCode OutlineCode { get; set; }
        public int Code { get; set; }
        public string Status { get; set; }
    }
    
    public class OutlineCodes
    {
        public List<TasksList> List { get; set; }
        public Link link { get; set; }
    }

    public class OutlineCodesResponse
    {
        public OutlineCodes OutlineCodes { get; set; }
        public int Code { get; set; }
        public string Status { get; set; }
    }

    public class ExtendedAttributes
    {
        public List<TasksList> List { get; set; }
        public Link link { get; set; }
    }

    public class ExtendedAttributesResponse
    {
        public ExtendedAttributes ExtendedAttributes { get; set; }
        public int Code { get; set; }
        public string Status { get; set; }
    }
    
    public class ValueList
    {
        public int Id { get; set; }
        public string Val { get; set; }
        public string Description { get; set; }
        public string Phonetic { get; set; }
    }

    public class ExtendedAttribute
    {
        public string FieldId { get; set; }
        public string FieldName { get; set; }
        public int CfType { get; set; }
        public string Guid { get; set; }
        public int ElementType { get; set; }
        public int MaxMultiValues { get; set; }
        public bool UserDef { get; set; }
        public string Alias { get; set; }
        public string SecondaryPid { get; set; }
        public bool AutoRollDown { get; set; }
        public string DefaultGuid { get; set; }
        public string LookupUid { get; set; }
        public string PhoneticsAlias { get; set; }
        public int RollupType { get; set; }
        public int CalculationType { get; set; }
        public string Formula { get; set; }
        public bool RestrictValues { get; set; }
        public int ValuelistSortOrder { get; set; }
        public bool AppendNewValues { get; set; }
        public string Default { get; set; }
        public List<ValueList> ValueList { get; set; }
        public string SecondaryGuid { get; set; }
    }

    public class ExtendedAttributeResponse
    {
        public ExtendedAttribute ExtendedAttribute { get; set; }
        public int Code { get; set; }
        public string Status { get; set; }
    }
    
    public class TasksProperty
    {
        public string Name { get; set; }
        public string Value { get; set; }
        public Link link { get; set; }
    }

    public class TasksPropertyResponse
    {
        public TasksProperty Property { get; set; }
        public int Code { get; set; }
        public string Status { get; set; }
    }

    public class TasksProperties
    {
        public List<TasksList> List { get; set; }
        public Link link { get; set; }
    }

    public class TasksPropertiesResponse
    {
        public TasksProperties Properties { get; set; }
        public int Code { get; set; }
        public string Status { get; set; }
    }
    
    public class TasksDocumentProperties
    {
        public object List { get; set; }
        public Link link { get; set; }
    }

    public class TasksDocument
    {
        public List<Link> Links { get; set; }
        public string FileName { get; set; }
        public int FileFormat { get; set; }
        public TasksDocumentProperties DocumentProperties { get; set; }
    }

    public class TasksDocumentResponse
    {
        public TasksDocument Document { get; set; }
        public int Code { get; set; }
        public string Status { get; set; }
    }

    public class WorkingTime
    {
        public string FromTime { get; set; }
        public string ToTime { get; set; }
    }

    public class CalendarException
    {
        public bool EnteredByOccurrences { get; set; }
        public string FromDate { get; set; }
        public string ToDate { get; set; }
        public int Occurrences { get; set; }
        public string Name { get; set; }
        public int Type { get; set; }
        public int Period { get; set; }
        public List<int> DaysOfWeek { get; set; }
        public int MonthItem { get; set; }
        public int MonthPosition { get; set; }
        public int Month { get; set; }
        public int MonthDay { get; set; }
        public bool DayWorking { get; set; }
        public List<WorkingTime> WorkingTimes { get; set; }
    }

    public class CalendarExceptionsResponse
    {
        public List<CalendarException> CalendarExceptions { get; set; }
        public int Code { get; set; }
        public string Status { get; set; }
    }

    public class Day
    {
        public int DayType { get; set; }
        public bool DayWorking { get; set; }
        public string FromDate { get; set; }
        public string ToDate { get; set; }
        public List<object> WorkingTimes { get; set; }
    }

    public class Calendar
    {
        public string Name { get; set; }
        public int Uid { get; set; }
        public List<Day> Days { get; set; }
        public List<object> Exceptions { get; set; }
        public bool IsBaseCalendar { get; set; }
        public object BaseCalendar { get; set; }
        public bool IsBaselineCalendar { get; set; }
    }

    public class CalendarResponse
    {
        public Calendar Calendar { get; set; }
        public int Code { get; set; }
        public string Status { get; set; }
    }
    
    public class CalendarItem
    {
        public Link Link { get; set; }
        public int Uid { get; set; }
        public string Name { get; set; }
    }

    public class CalendarItemResponse
    {
        public CalendarItem CalendarItem { get; set; }
        public int Code { get; set; }
        public string Status { get; set; }
    }

    public class Assignments
    {
        public List<AssignmentItem> AssignmentItem { get; set; }
        public Link link { get; set; }
    }

    public class AssignmentsResponse
    {
        public Assignments Assignments { get; set; }
        public int Code { get; set; }
        public string Status { get; set; }
    }

    public class AssignmentItem
    {
        public Link Link { get; set; }
        public int Uid { get; set; }
        public int TaskUid { get; set; }
        public int ResourceUid { get; set; }
    }

    public class AssignmentItemResponse
    {
        public AssignmentItem AssignmentItem { get; set; }
        public int Code { get; set; }
        public string Status { get; set; }
    }
    
    public class Assignment
    {
        public int TaskUid { get; set; }
        public int ResourceUid { get; set; }
        public int Uid { get; set; }
        public int PercentWorkComplete { get; set; }
        public double ActualCost { get; set; }
        public string ActualFinish { get; set; }
        public double ActualOvertimeCost { get; set; }
        public string ActualOvertimeWork { get; set; }
        public string ActualStart { get; set; }
        public string ActualWork { get; set; }
        public double Acwp { get; set; }
        public bool Confirmed { get; set; }
        public double Cost { get; set; }
        public int CostRateTableType { get; set; }
        public double CostVariance { get; set; }
        public double Cv { get; set; }
        public int Delay { get; set; }
        public string Finish { get; set; }
        public int FinishVariance { get; set; }
        public object Hyperlink { get; set; }
        public object HyperlinkAddress { get; set; }
        public object HyperlinkSubAddress { get; set; }
        public double WorkVariance { get; set; }
        public bool HasFixedRateUnits { get; set; }
        public bool FixedMaterial { get; set; }
        public int LevelingDelay { get; set; }
        public int LevelingDelayFormat { get; set; }
        public bool LinkedFields { get; set; }
        public bool Milestone { get; set; }
        public object Notes { get; set; }
        public bool Overallocated { get; set; }
        public double OvertimeCost { get; set; }
        public string OvertimeWork { get; set; }
        public double PeakUnits { get; set; }
        public string RegularWork { get; set; }
        public double RemainingCost { get; set; }
        public double RemainingOvertimeCost { get; set; }
        public string RemainingOvertimeWork { get; set; }
        public string RemainingWork { get; set; }
        public bool ResponsePending { get; set; }
        public string Start { get; set; }
        public string Stop { get; set; }
        public string Resume { get; set; }
        public int StartVariance { get; set; }
        public bool Summary { get; set; }
        public double Sv { get; set; }
        public double Units { get; set; }
        public bool UpdateNeeded { get; set; }
        public double Vac { get; set; }
        public string Work { get; set; }
        public int WorkContour { get; set; }
        public double Bcws { get; set; }
        public double Bcwp { get; set; }
        public int BookingType { get; set; }
        public string ActualWorkProtected { get; set; }
        public string ActualOvertimeWorkProtected { get; set; }
        public string CreationDate { get; set; }
        public object AssnOwner { get; set; }
        public object AssnOwnerGuid { get; set; }
        public double BudgetCost { get; set; }
        public string BudgetWork { get; set; }
        public int RateScale { get; set; }
        public string BaselineStart { get; set; }
        public string BaselineFinish { get; set; }
        public string BaselineWork { get; set; }
        public double BaselineCost { get; set; }
        public double BaselineBcws { get; set; }
        public double BaselineBcwp { get; set; }
        public string Baseline1Start { get; set; }
        public string Baseline1Finish { get; set; }
        public string Baseline1Work { get; set; }
        public double Baseline1Cost { get; set; }
        public double Baseline1Bcws { get; set; }
        public double Baseline1Bcwp { get; set; }
        public string Baseline2Start { get; set; }
        public string Baseline2Finish { get; set; }
        public string Baseline2Work { get; set; }
        public double Baseline2Cost { get; set; }
        public double Baseline2Bcws { get; set; }
        public double Baseline2Bcwp { get; set; }
        public string Baseline3Start { get; set; }
        public string Baseline3Finish { get; set; }
        public string Baseline3Work { get; set; }
        public double Baseline3Cost { get; set; }
        public double Baseline3Bcws { get; set; }
        public double Baseline3Bcwp { get; set; }
        public string Baseline4Start { get; set; }
        public string Baseline4Finish { get; set; }
        public string Baseline4Work { get; set; }
        public double Baseline4Cost { get; set; }
        public double Baseline4Bcws { get; set; }
        public double Baseline4Bcwp { get; set; }
        public string Baseline5Start { get; set; }
        public string Baseline5Finish { get; set; }
        public string Baseline5Work { get; set; }
        public double Baseline5Cost { get; set; }
        public double Baseline5Bcws { get; set; }
        public double Baseline5Bcwp { get; set; }
        public string Baseline6Start { get; set; }
        public string Baseline6Finish { get; set; }
        public string Baseline6Work { get; set; }
        public double Baseline6Cost { get; set; }
        public double Baseline6Bcws { get; set; }
        public double Baseline6Bcwp { get; set; }
        public string Baseline7Start { get; set; }
        public string Baseline7Finish { get; set; }
        public string Baseline7Work { get; set; }
        public double Baseline7Cost { get; set; }
        public double Baseline7Bcws { get; set; }
        public double Baseline7Bcwp { get; set; }
        public string Baseline8Start { get; set; }
        public string Baseline8Finish { get; set; }
        public string Baseline8Work { get; set; }
        public double Baseline8Cost { get; set; }
        public double Baseline8Bcws { get; set; }
        public double Baseline8Bcwp { get; set; }
        public string Baseline9Start { get; set; }
        public string Baseline9Finish { get; set; }
        public string Baseline9Work { get; set; }
        public double Baseline9Cost { get; set; }
        public double Baseline9Bcws { get; set; }
        public double Baseline9Bcwp { get; set; }
        public string Baseline10Start { get; set; }
        public string Baseline10Finish { get; set; }
        public string Baseline10Work { get; set; }
        public double Baseline10Cost { get; set; }
        public double Baseline10Bcws { get; set; }
        public double Baseline10Bcwp { get; set; }
        public List<object> ExtendedAttributes { get; set; }
    }

    public class AssignmentResponse
    {
        public Assignment Assignment { get; set; }
        public int Code { get; set; }
        public string Status { get; set; }
    }

    public class TasksList
    {
        public Link Link { get; set; }
        public int Uid { get; set; }
        public string Name { get; set; }
        public string Value { get; set; }
        public int Index { get; set; }
        public string FieldName { get; set; }
    }

    public class Calendars
    {
        public List<TasksList> List { get; set; }
        public Link link { get; set; }
    }

    public class CalendarsResponse
    {
        public Calendars Calendars { get; set; }
        public int Code { get; set; }
        public string Status { get; set; }
    }

    public enum TasksDocumentFormat
    {
        Mpp, Xml, Html, Bmp, Png, Jpeg, Pdf, Tiff, Xps, Xaml, Svg, Csv, Txt, Spreadsheet2003, Xlsx, Primaverap6xml, Primaveraxer
    }

    public enum TasksReportType
    {
        ProjectOverview = 0,
        CostOverview = 1,
        WorkOverview = 2,
        ResourceOverview = 3,
        ResourceCostOverview = 4,
        CriticalTasks = 5,
        LateTasks = 6,
        Milestones = 7,
        UpcomingTask = 8,
        CostOverruns = 9,
        TaskCostOverview = 10,
        OverallocatedResources = 11,
        SlippingTasks = 12,
        BestPracticeAnalyzer = 13,
        Burndown = 14,
        CashFlow = 15
    }
}
