using System;
using System.Collections.Generic;
using System.Text;
using System.IO;
using System.Reflection;

namespace Aspose.Cloud
{
    public class BaseService
    {
        internal string AppKey { get; set; }
        internal string AppSid { get; set; }

        /// <summary>
        /// Aspose for Cloud Web App resource controller
        /// </summary>
        /// <param name="appSid">App SID - You can get it from https://cloud.aspose.com/</param>
        /// <param name="appKey">App key - You can get it from https://cloud.aspose.com/</param>
        protected BaseService(string appSid, string appKey)
        {
            AppSid = appSid;
            AppKey = appKey;
            AppDomain.CurrentDomain.AssemblyResolve += new ResolveEventHandler(CurrentDomain_AssemblyResolve);
        }

        static Assembly CurrentDomain_AssemblyResolve(object sender, ResolveEventArgs args)
        {
            using (Stream stream = Assembly.GetExecutingAssembly().GetManifestResourceStream("Aspose.Cloud.Newtonsoft.Json.dll"))
            {
                byte[] assemblyData = new byte[stream.Length];
                stream.Read(assemblyData, 0, assemblyData.Length);
                return Assembly.Load(assemblyData);
            }
        }
    }
}
