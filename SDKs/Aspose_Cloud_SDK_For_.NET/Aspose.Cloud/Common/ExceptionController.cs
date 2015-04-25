using System;
using System.Collections.Generic;
using System.Text;
using System.Net;
using System.IO;

namespace Aspose.Cloud.Common
{
    internal class ExceptionController
    {
        public static string GetExceptionDetails(WebException webException)
        {
            string message = webException.Message;
            if (webException.Response != null)
            {
                if (webException.Response.ContentLength != 0)
                {
                    using (Stream stream = webException.Response.GetResponseStream())
                    {
                        using (StreamReader streamReader = new StreamReader(stream))
                        {
                            message = streamReader.ReadToEnd();
                        }
                    }
                }
            }
            return message;
        }

        public static Exception CheckNotFoundResponse(WebException exception)
        {
            if (exception.InnerException != null)
            {
                WebException webException = (WebException)exception.InnerException;

                if (webException.Status == WebExceptionStatus.ProtocolError)
                {
                    if (((HttpWebResponse)webException.Response).StatusCode == HttpStatusCode.NotFound)
                    {
                        return null; // Return null if the response is 'Not Found'
                    }
                }
            }
            return exception;
        }
    }
}
