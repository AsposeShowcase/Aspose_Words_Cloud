using System;
using System.Collections.Generic;
using System.Text;
using Aspose.Cloud.Common;
using Newtonsoft.Json;
using Newtonsoft.Json.Linq;

namespace Aspose.Cloud
{
    public class BodyParameter
    {
        public string Name { get; set; }
        public string Type { get; set; }
        public string Description { get; set; }
        public bool IsOptional { get; set; }
        public string DefaultValue { get; set; }
        public string ParamType { get; set; }
    }

    public class ActionMethod
    {
        public string Type { get; set; }
        public string Name { get; set; }
        public string Url { get; set; }
        public List<BodyParameter> UrlParameters { get; set; }
        public BodyParameter BodyParameter { get; set; }
        public string Description { get; set; }
        public string ReturnType { get; set; }
        public string Consumes { get; set; }
        public string Produces { get; set; }
    }

    public class Model
    {
        public string Name { get; set; }
        public List<object> Properties { get; set; }
    }

    public class ProductDescriptor
    {
        public string ProductName { get; set; }
        public List<ActionMethod> ActionMethods { get; set; }
        public List<Model> Models { get; set; }
    }

    public class SDKMetaDataResponse
    {
        public string Host { get; set; }
        public List<ProductDescriptor> ProductDescriptors { get; set; }
    }

    public class SDKService : BaseService
    {
        public SDKService(string appSid, string appKey)
            : base(appSid, appKey)
        {
            AppSid = appSid;
            AppKey = appKey;
        }

        /// <summary>
        /// Returns service metadata info 
        /// </summary>
        /// <returns></returns>
        public SDKMetaDataResponse GetSDKMetaData()
        {
            // GET 	sdk/metadata 

            string apiUrl = "sdk/metadata";

            JObject jObject = JObject.Parse(ServiceController.Get(apiUrl, AppSid, AppKey));
            SDKMetaDataResponse sdkMetaDataResponse = jObject.ToObject<SDKMetaDataResponse>();
            return sdkMetaDataResponse;
        }
    }
}
