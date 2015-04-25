using System;
using Aspose.Cloud.Common;
using Newtonsoft.Json;
using Newtonsoft.Json.Linq;
using System.IO;

namespace Aspose.Cloud
{
    public class StatisticsService : BaseService
    {
        public StatisticsService(string appSid, string appKey)
            : base(appSid, appKey)
        {
            AppSid = appSid;
            AppKey = appKey;
        }

        /// <summary>
        /// Get service statistics in HTML form.
        /// </summary>
        /// <param name="outputPath">Local path where the log file will be saved e.g c:\stats.html</param>
        public void GetServiceStatisticsInHtmlForm(string outputPath)
        {
            // GET 	statistics?appSID={appSID}

            string apiUrl = "statistics?";

            using (Stream responseStream = ServiceController.GetStream(apiUrl, AppSid, AppKey))
            using (Stream file = File.OpenWrite(outputPath))
            {
                ServiceController.CopyStream(responseStream, file);
            }
        }

        /// <summary>
        /// Get statistics for specific dates by hours.
        /// </summary>
        /// <param name="startDate">The start date.</param>
        /// <param name="endDate">The end date.</param>
        /// <param name="outputPath">Local path where the log file will be saved e.g c:\stats.html</param>
        public void GetStatisticsForSpecificDatesByHours(DateTime startDate, DateTime endDate, string outputPath)
        {
            // GET 	statistics/hours?startDate={startDate}&appSID={appSID}&endDate={endDate}

            string apiUrl = string.Format(@"statistics/hours?startDate={0}&endDate={1}", startDate, endDate);

            using (Stream responseStream = ServiceController.GetStream(apiUrl, AppSid, AppKey))
            using (Stream file = File.OpenWrite(outputPath))
            {
                ServiceController.CopyStream(responseStream, file);
            }
        }

        /// <summary>
        /// Get statistics for last 30 days by hours.
        /// </summary>
        /// <param name="outputPath">Local path where the log file will be saved e.g c:\stats.html</param>
        public void GetStatisticsForLast30DaysByHours(string outputPath)
        {
            // GET 	statistics/hours/last30Days?appSID={appSID}

            string apiUrl = "statistics/hours/last30Days?";

            using (Stream responseStream = ServiceController.GetStream(apiUrl, AppSid, AppKey))
            using (Stream file = File.OpenWrite(outputPath))
            {
                ServiceController.CopyStream(responseStream, file);
            }
        }

        /// <summary>
        /// Get service statistics in HTML form like GroupDocs.
        /// </summary>
        /// <param name="outputPath">Local path where the log file will be saved e.g c:\stats.html</param>
        public void GetServiceStatisticsInHTMLFormLikeGroupDocs(string outputPath)
        {	
            // GET 	statistics/likeGD?appSID={appSID} 

            string apiUrl = "statistics/likeGD?";

            using (Stream responseStream = ServiceController.GetStream(apiUrl, AppSid, AppKey))
            using (Stream file = File.OpenWrite(outputPath))
            {
                ServiceController.CopyStream(responseStream, file);
            }
        }

        /// <summary>
        /// Get users with plans statistics.
        /// </summary>
        /// <param name="outputPath">Local path where the log file will be saved e.g c:\stats.html</param>
        public void GetUsersWithPlansStatistics(string outputPath)
        {	
            // GET 	statistics/plans?appSID={appSID} 

            string apiUrl = "statistics/plans?";

            using (Stream responseStream = ServiceController.GetStream(apiUrl, AppSid, AppKey))
            using (Stream file = File.OpenWrite(outputPath))
            {
                ServiceController.CopyStream(responseStream, file);
            }
        }

        /// <summary>
        /// Get last errors statistics.
        /// </summary>
        /// <param name="outputPath">Local path where the log file will be saved e.g c:\stats.html</param>
        public void GetLastErrorsStatistics(string outputPath)
        {	
            // GET 	statistics/errors?appSID={appSID} 

            string apiUrl = "statistics/errors?";

            using (Stream responseStream = ServiceController.GetStream(apiUrl, AppSid, AppKey))
            using (Stream file = File.OpenWrite(outputPath))
            {
                ServiceController.CopyStream(responseStream, file);
            }
        }

        /// <summary>
        /// Get paid clients statistics.
        /// </summary>
        /// <param name="outputPath">Local path where the log file will be saved e.g c:\stats.html</param>
        public void GetPaidClientsStatistics(string outputPath)
        {	
            // GET 	statistics/paid?appSID={appSID} 

            string apiUrl = "statistics/paid?";

            using (Stream responseStream = ServiceController.GetStream(apiUrl, AppSid, AppKey))
            using (Stream file = File.OpenWrite(outputPath))
            {
                ServiceController.CopyStream(responseStream, file);
            }
        }

        /// <summary>
        /// Get free users statistics.
        /// </summary>
        /// <param name="outputPath">Local path where the log file will be saved e.g c:\stats.html</param>
        public void GetFreeUsersStatistics(string outputPath)
        {	
            // GET 	statistics/free?appSID={appSID} 

            string apiUrl = "statistics/free?";

            using (Stream responseStream = ServiceController.GetStream(apiUrl, AppSid, AppKey))
            using (Stream file = File.OpenWrite(outputPath))
            {
                ServiceController.CopyStream(responseStream, file);
            }
        }

        /// <summary>
        /// Get API calls statistics.
        /// </summary>
        /// <param name="outputPath">Local path where the log file will be saved e.g c:\stats.html</param>
        public void GetAPICallsStatistics(string outputPath)
        {	
            // GET 	statistics/calls?appSID={appSID} 

            string apiUrl = "statistics/calls?";

            using (Stream responseStream = ServiceController.GetStream(apiUrl, AppSid, AppKey))
            using (Stream file = File.OpenWrite(outputPath))
            {
                ServiceController.CopyStream(responseStream, file);
            }
        }

        /// <summary>
        /// Get clients with overdraft statistics.
        /// </summary>
        /// <param name="outputPath">Local path where the log file will be saved e.g c:\stats.html</param>
        public void GetClientsWithOverDraftStatistics(string outputPath)
        {	
            // GET 	statistics/overdrafts?appSID={appSID} 

            string apiUrl = "statistics/overdrafts?";

            using (Stream responseStream = ServiceController.GetStream(apiUrl, AppSid, AppKey))
            using (Stream file = File.OpenWrite(outputPath))
            {
                ServiceController.CopyStream(responseStream, file);
            }
        }

        /// <summary>
        /// Get current user statistics.
        /// </summary>
        /// <param name="outputPath">Local path where the log file will be saved e.g c:\stats.html</param>
        public void GetCurrentUsersStatistics(string outputPath)
        {
            // GET 	statistics/currentUsers?appSID={appSID}

            string apiUrl = "statistics/currentUsers?";

            using (Stream responseStream = ServiceController.GetStream(apiUrl, AppSid, AppKey))
            using (Stream file = File.OpenWrite(outputPath))
            {
                ServiceController.CopyStream(responseStream, file);
            }
        }

    }
}
