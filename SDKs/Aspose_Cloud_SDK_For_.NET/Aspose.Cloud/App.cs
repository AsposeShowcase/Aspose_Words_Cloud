using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Newtonsoft.Json;
using Newtonsoft.Json.Linq;
using System.IO;
using System.Net;
using Aspose.Cloud.Common;

namespace Aspose.Cloud
{
    public class App : BaseService
    {
        public App(string appSid, string appKey)
            : base(appSid, appKey)
        {
            AppSid = appSid;
            AppKey = appKey;
        }

        public string Name { get; set; }
        public string Description { get; set; }
        public bool IsDefault { get; set; }
        public DateTime Created { get; set; }

        /// <summary>
        /// Get List of Applications
        /// </summary>
        /// <returns>List of all Applications</returns>
        public List<App> GetListOfApplications()
        {
            // GET 	app?appSid={appSid}

            string apiUrl = "app";
            JArray jArray = JArray.Parse(ServiceController.Get(apiUrl, AppSid, AppKey));
            List<App> appList = jArray.ToObject<List<App>>();
            return appList;
        }

        /// <summary>
        /// Create new Application
        /// </summary>
        /// <param name="app">Application to create</param>
        public void CreateNewApplication(App app)
        {
            // POST app?appSid={appSid} 

            string apiUrl = "app";
            ServiceController.Post(apiUrl, AppSid, AppKey, JsonConvert.SerializeObject(app));
        }

        /// <summary>
        /// Get Application by Name
        /// </summary>
        /// <param name="applicationName">Name of the application to get</param>
        /// <returns>Application matching the name, returns null if no match</returns>
        public App GetApplicationByName(string applicationName)
        {
            // GET 	app/{name}?appSid={appSid}

            string apiUrl = "app/" + applicationName;
            App app = null;

            try
            {
                JObject jObject = JObject.Parse(ServiceController.Get(apiUrl, AppSid, AppKey));
                app = jObject.ToObject<App>();
            }
            catch (WebException webException)
            {
                if (ExceptionController.CheckNotFoundResponse(webException) == null)
                    return null;

                throw;
            }
            return app;
        }

        /// <summary>
        /// Update Application Name
        /// </summary>
        /// <param name="existingApplicationName">Name of the existing application to update</param>
        /// <param name="app">Application to update</param>
        public void UpdateApplicationName(string applicationName, App app)
        {
            // PUT 	app/{name}?appSid={appSid}

            string apiUrl = "app/" + applicationName;
            ServiceController.Put(apiUrl, AppSid, AppKey, JsonConvert.SerializeObject(app));
        }

        /// <summary>
        /// Delete Application
        /// </summary>
        /// <param name="existingApplicationName">Name of the existing application to delete</param>
        public void DeleteApplication(string applicationName)
        {
            // DELETE 	app/{name}?appSid={appSid}

            string apiUrl = "app/" + applicationName;
            ServiceController.Delete(apiUrl, AppSid, AppKey);
        }

        /// <summary>
        /// Get Application Usage By Period
        /// </summary>
        /// <param name="existingApplicationName">Name of the existing application</param>
        /// <param name="startDate">Period start date</param>
        /// <param name="endDate">Period end date</param>
        /// <returns>Application usage details, reutrns null if no match</returns>
        public ApplicationUsageResponse GetApplicationUsageByPeriod(string applicationName, DateTime startDate, DateTime endDate)
        {
            // GET 	app/{name}/usage?start={start}&end={end}&appSid={appSid}

            string apiUrl = string.Format("app/{0}/usage?start={1}&end={2}", applicationName, startDate.ToString(), endDate.ToString());

            ApplicationUsageResponse applicationUsageResponse = null;
            try
            {
                JObject jObject = JObject.Parse(ServiceController.Get(apiUrl, AppSid, AppKey));
                applicationUsageResponse = jObject.ToObject<ApplicationUsageResponse>();
            }
            catch (WebException webException)
            {
                if (ExceptionController.CheckNotFoundResponse(webException) == null)
                    return null;

                throw;
            }            
            return applicationUsageResponse;
        }
    }

    public class ApplicationUsageResponse
    {
        public AppUsage AppUsage { get; set; }
        public int Code { get; set; }
        public string Status { get; set; }
    }

    public class AppUsage
    {
        public int Documents { get; set; }
        public int Barcodes { get; set; }
        public int Ocrs { get; set; }
        public int Calls { get; set; }
    }
}
