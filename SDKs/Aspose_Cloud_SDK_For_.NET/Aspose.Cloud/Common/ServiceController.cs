using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.IO;
using System.Net;
using System.Web;
using System.Text.RegularExpressions;
using System.Security.Cryptography;
using Newtonsoft.Json;
using Newtonsoft.Json.Linq;

namespace Aspose.Cloud.Common
{
    internal class ServiceController
    {
        private static string Sign(string url, string appSid, string appKey)
        {
            // Add AppSid parameter.
            UriBuilder uriBuilder = new UriBuilder(url);

            if (uriBuilder.Query != null && uriBuilder.Query.Length > 1)
                uriBuilder.Query = uriBuilder.Query.Substring(1) + "&appSID=" + appSid;
            else
                uriBuilder.Query = "appSID=" + appSid;

            // Remove final slash here as it can be added automatically.
            uriBuilder.Path = uriBuilder.Path.TrimEnd('/');

            // Compute the hash.
            byte[] privateKey = Encoding.UTF8.GetBytes(appKey);
            HMACSHA1 algorithm = new HMACSHA1(privateKey);

            byte[] sequence = ASCIIEncoding.ASCII.GetBytes(uriBuilder.Uri.AbsoluteUri);
            byte[] hash = algorithm.ComputeHash(sequence);
            string signature = Convert.ToBase64String(hash);

            // Remove invalid symbols.
            signature = signature.TrimEnd('=');
            signature = HttpUtility.UrlEncode(signature);

            // Convert codes to upper case as they can be updated automatically.
            signature = Regex.Replace(signature, "%[0-9a-f]{2}", e => e.Value.ToUpper());

            // Add the signature to query string.
            return string.Format("{0}&signature={1}", uriBuilder.Uri.AbsoluteUri, signature);
        }

        private static Stream GetResultStream(string apiUrl, string appSid, string appKey, string requestType, byte[] byteArray = null)
        {
            try
            {
                apiUrl = Constants.ServiceURL + apiUrl;
                Uri uri = new Uri(Sign(apiUrl, appSid, appKey));
                WebRequest request = WebRequest.Create(uri);
                request.Method = requestType;
                request.ContentType = "application/json";
                request.ContentLength = 0;
                request.Headers.Add("x-aspose-client", ".NETSDK/v2.0");

                if (byteArray != null)
                {
                    request.ContentLength = byteArray.Length;
                    using (Stream stream = request.GetRequestStream())
                    {
                        stream.Write(byteArray, 0, byteArray.Length);
                    }
                }

                WebResponse response = request.GetResponse();
                return response.GetResponseStream();
            }
            catch (WebException webException)
            {
                string message = ExceptionController.GetExceptionDetails(webException);

                if (ExceptionController.CheckNotFoundResponse(new WebException(message, webException)) == null)
                {
                    throw new WebException(message, webException);
                }
                else
                {
                    throw new WebException(message);
                }
            }
        }

        private static string GetResultString(string apiUrl, string appSid, string appKey, string requestType, byte[] byteArray = null)
        {
            string jSonResponse = string.Empty;
            using (StreamReader streamReader = new StreamReader(GetResultStream(apiUrl, appSid, appKey, requestType, byteArray)))
            {
                jSonResponse = streamReader.ReadToEnd();
            }
            return jSonResponse;
        }

        public static string Get(string apiUrl, string appSid, string appKey)
        {
            return GetResultString(apiUrl, appSid, appKey, "GET");
        }

        public static Stream GetStream(string apiUrl, string appSid, string appKey)
        {
            return GetResultStream(apiUrl, appSid, appKey, "GET");
        }

        public static Stream GetStream(string apiUrl, string appSid, string appKey, string requestBody)
        {
            byte[] byteArray = Encoding.UTF8.GetBytes(requestBody);
            return GetResultStream(apiUrl, appSid, appKey, "GET", byteArray);
        }

        public static Stream GetStreamWithPost(string apiUrl, string appSid, string appKey, byte[] byteArray)
        {
            return GetResultStream(apiUrl, appSid, appKey, "POST", byteArray);
        }

        public static string Post(string apiUrl, string appSid, string appKey)
        {
            return GetResultString(apiUrl, appSid, appKey, "POST");
        }

        public static string Post(string apiUrl, string appSid, string appKey, byte[] byteArray)
        {
            return GetResultString(apiUrl, appSid, appKey, "POST", byteArray);
        }

        public static string Post(string apiUrl, string appSid, string appKey, string requestBody)
        {
            byte[] byteArray = Encoding.UTF8.GetBytes(requestBody);
            return GetResultString(apiUrl, appSid, appKey, "POST", byteArray);
        }

        public static string Put(string apiUrl, string appSid, string appKey)
        {
            return GetResultString(apiUrl, appSid, appKey, "PUT");
        }

        public static Stream GetStreamWithPut(string apiUrl, string appSid, string appKey, byte[] byteArray)
        {
            return GetResultStream(apiUrl, appSid, appKey, "PUT", byteArray);
        }

        public static Stream GetStreamWithPut(string apiUrl, string appSid, string appKey)
        {
            return GetResultStream(apiUrl, appSid, appKey, "PUT");
        }

        public static string Put(string apiUrl, string appSid, string appKey, byte[] byteArray)
        {
            return GetResultString(apiUrl, appSid, appKey, "PUT", byteArray);
        }

        public static string Put(string apiUrl, string appSid, string appKey, string requestBody)
        {
            byte[] byteArray = Encoding.UTF8.GetBytes(requestBody);
            return GetResultString(apiUrl, appSid, appKey, "PUT", byteArray);
        }

        public static string Delete(string apiUrl, string appSid, string appKey)
        {
            return GetResultString(apiUrl, appSid, appKey, "DELETE");
        }

        public static string Delete(string apiUrl, string appSid, string appKey, byte[] byteArray)
        {
            return GetResultString(apiUrl, appSid, appKey, "DELETE", byteArray);
        }

        public static string Delete(string apiUrl, string appSid, string appKey, string requestBody)
        {
            byte[] byteArray = Encoding.UTF8.GetBytes(requestBody);
            return GetResultString(apiUrl, appSid, appKey, "DELETE", byteArray);
        }

        public static void CopyStream(Stream input, Stream output)
        {
            byte[] buffer = new byte[8 * 1024];
            int length;
            while ((length = input.Read(buffer, 0, buffer.Length)) > 0)
            {
                output.Write(buffer, 0, length);
            }
        }
    }
}
