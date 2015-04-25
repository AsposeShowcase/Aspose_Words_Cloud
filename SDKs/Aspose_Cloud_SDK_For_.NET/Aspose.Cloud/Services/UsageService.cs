using System;
using System.Collections.Generic;
using System.Text;
using Aspose.Cloud.Common;
using Newtonsoft.Json;
using Newtonsoft.Json.Linq;
using System.IO;

namespace Aspose.Cloud
{
    public class UsageService : BaseService
    {
        public UsageService(string appSid, string appKey)
            : base(appSid, appKey)
        {
            AppSid = appSid;
            AppKey = appKey;
        }

        /// <summary>
        /// Get usage logs for some period. Returns logs for the period required as XLSX workbook.
        /// </summary>
        /// <param name="start">Start date and time for logs generated.</param>
        /// <param name="end">End date and time for logs generated.</param>
        /// <param name="outputPath">Local path where the log file will be saved e.g c:\logFile.xlsx</param>
        public void GetUsageLogsForSomePeriod(DateTime start, DateTime end, string outputPath)
        {
            //	GET  usage?start={start}&end={end}&appSID={appSID}

            string apiUrl = string.Format(@"usage?start={0}&end={1}", start, end);

            using (Stream responseStream = ServiceController.GetStream(apiUrl, AppSid, AppKey))
            using (Stream file = System.IO.File.OpenWrite(outputPath))
            {
                ServiceController.CopyStream(responseStream, file);
            }
        }

        /// <summary>
        /// Get usage for current subscription period. Returns logs for the current period as XLSX workbook.
        /// </summary>
        /// <param name="outputPath">Local path where the log file will be saved e.g c:\logFile.xlsx</param>
        public void GetUsageForCurrentSubscriptionPeriod(string outputPath)
        {
            // GET 	usage/currentPeriod?appSID={appSID}

            string apiUrl = "usage/currentPeriod";

            using (Stream responseStream = ServiceController.GetStream(apiUrl, AppSid, AppKey))
            using (Stream file = System.IO.File.OpenWrite(outputPath))
            {
                ServiceController.CopyStream(responseStream, file);
            }
        }

        /// <summary>
        /// Get usage for the previous subscription period. Returns logs for the previous period as XLSX workbook.
        /// </summary>
        /// <param name="outputPath">Local path where the log file will be saved e.g c:\logFile.xlsx</param>
        public void GetUsageForPreviousSubscriptionPeriod(string outputPath)
        {
            // GET 	usage/previousPeriod?appSID={appSID}

            string apiUrl = "usage/currentPeriod";

            using (Stream responseStream = ServiceController.GetStream(apiUrl, AppSid, AppKey))
            using (Stream file = System.IO.File.OpenWrite(outputPath))
            {
                ServiceController.CopyStream(responseStream, file);
            }
        }

        /// <summary>
        /// Get summary usage for current subscription period. Returns logs for the current subscription period as XLSX workbook.
        /// </summary>
        /// <param name="outputPath">Local path where the log file will be saved e.g c:\logFile.xlsx</param>
        public void GetSummaryUsageForCurrentSubscriptionPeriod(string outputPath)
        {
            // GET 	usage/summary?appSID={appSID}

            string apiUrl = "usage/currentPeriod";

            using (Stream responseStream = ServiceController.GetStream(apiUrl, AppSid, AppKey))
            using (Stream file = System.IO.File.OpenWrite(outputPath))
            {
                ServiceController.CopyStream(responseStream, file);
            }
        }

    }
}
