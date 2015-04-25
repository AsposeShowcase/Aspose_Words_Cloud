using System;
using System.Collections.Generic;
using System.Text;
using Aspose.Cloud.Common;
using Newtonsoft.Json;
using Newtonsoft.Json.Linq;
using System.IO;

namespace Aspose.Cloud
{
    public class EmailService : BaseService
    {
        public EmailService(string appSid, string appKey)
            : base(appSid, appKey)
        {
            AppSid = appSid;
            AppKey = appKey;
        }

        /// <summary>
        /// Convert mail message to target format
        /// </summary>
        /// <param name="name">File name. e.g. myEmail.msg</param>
        /// <param name="format">Email format. e.g Eml, Msg, Mht</param>
        /// <param name="folder">Folder path.</param>
        /// <param name="outPath">Path to save result. It can be a local path e.g c:\file.eml or cloud storage path e.g. MyFolder/file.eml</param>
        /// <param name="storage">The document storage.</param>
        public void ConvertMailMessageToTargetFormat(string name, EmailFormat format, string folder, string outPath, string storage = "")
        {
            // GET 	email/{name}?appSid={appSid}&format={format}&storage={storage}&folder={folder}&outPath={outPath}

            string apiUrl = string.Format(@"email/{0}?format={1}&storage={2}&folder={3}&outPath={4}",
                                            name, format, storage, folder, (outPath.Contains(@":\") ? string.Empty : outPath));

            if (!string.IsNullOrEmpty(outPath) && Directory.Exists(Path.GetDirectoryName(outPath)))
            {
                using (Stream responseStream = ServiceController.GetStream(apiUrl, AppSid, AppKey))
                using (Stream file = File.OpenWrite(outPath))
                {
                    ServiceController.CopyStream(responseStream, file);
                }
            }
            else
            {
                ServiceController.Get(apiUrl, AppSid, AppKey);
            }

        }

        /// <summary>
        /// Get mail common info
        /// </summary>
        /// <param name="name">File name. e.g. myEmail.msg</param>
        /// <param name="folder">Folder path.</param>
        /// <param name="storage">The document storage.</param>        
        /// <returns>EmailDocumentPropertiesResponse object</returns>
        public EmailDocumentPropertiesResponse GetMailCommonInfo(string name, string folder, string storage = "")
        {
            // GET 	email/{name}?appSid={appSid}&format={format}&storage={storage}&folder={folder}

            string apiUrl = string.Format(@"email/{0}?storage={1}&folder={2}",
                                            name, storage, folder);

            JObject jObject = JObject.Parse(ServiceController.Get(apiUrl, AppSid, AppKey));
            EmailDocumentPropertiesResponse emailDocumentPropertiesResponse = jObject.ToObject<EmailDocumentPropertiesResponse>();
            return emailDocumentPropertiesResponse;
        }

        /// <summary>
        /// Add new email.	
        /// </summary>
        /// <param name="name">File name. e.g. myEmail.msg</param>
        /// <param name="folder">Folder path.</param>
        /// <param name="folder">EmailDocument object.</param>
        /// <param name="storage">The document storage.</param>
        public void AddNewEmail(string name, string folder, EmailDocumentPropertiesRequest emailDocumentPropertiesRequest, string storage = "")
        {
            // PUT 	email/{name}?appSid={appSid}&storage={storage}&folder={folder} 

            string apiUrl = string.Format(@"email/{0}?storage={1}&folder={2}",
                                            name, storage, folder);

            ServiceController.Put(apiUrl, AppSid, AppKey, JsonConvert.SerializeObject(emailDocumentPropertiesRequest));
        }

        /// <summary>
        /// Read document property by name.	
        /// </summary>
        /// <param name="name">File name. e.g. myEmail.msg</param>
        /// <param name="propertyName">Property Name</param>
        /// <param name="folder">Folder path.</param>
        /// <param name="storage">The document storage.</param>
        public EmailPropertyResponse ReadDocumentPropertyByName(string name, string propertyName, string folder, string storage = "")
        {
            // GET 	email/{name}/properties/{propertyName}?appSid={appSid}&storage={storage}&folder={folder} 

            string apiUrl = string.Format(@"email/{0}/properties/{1}?storage={2}&folder={3}",
                                            name, propertyName, storage, folder);

            JObject jObject = JObject.Parse(ServiceController.Get(apiUrl, AppSid, AppKey));
            EmailPropertyResponse emailPropertyResponse = jObject.ToObject<EmailPropertyResponse>();
            return emailPropertyResponse;
        }

        /// <summary>
        /// Set document property.
        /// </summary>
        /// <param name="name">File name. e.g. myEmail.msg</param>
        /// <param name="propertyName">Property Name</param>
        /// <param name="property">EmailProperty object</param>
        /// <param name="folder">Folder path.</param>
        /// <param name="storage">The document storage.</param>
        public void SetDocumentProperty(string name, string propertyName, EmailProperty property, string folder, string storage = "")
        {
            // PUT 	email/{name}/properties/{propertyName}?appSid={appSid}&storage={storage}&folder={folder} 

            string apiUrl = string.Format(@"email/{0}/properties/{1}?storage={2}&folder={3}",
                                            name, propertyName, storage, folder);

            ServiceController.Put(apiUrl, AppSid, AppKey, JsonConvert.SerializeObject(property));

        }

        /// <summary>
        /// Get email attachment by name.	
        /// </summary>
        /// <param name="name">File name. e.g. myEmail.msg</param>
        /// <param name="attachName">Attachment name</param>
        /// <param name="folder">Folder path.</param>
        /// <param name="outPath">Local outpath to save the attachment. e.g. c:\test.doc</param>
        /// <param name="storage">The document storage.</param>
        public void GetEmailAttachmentByName(string name, string attachName, string folder, string outPath, string storage = "")
        {
            // GET 	email/{name}/attachments/{attachName}?appSid={appSid}&storage={storage}&folder={folder} 

            string apiUrl = string.Format(@"email/{0}/attachments/{1}?storage={2}&folder={3}",
                                            name, attachName, storage, folder);

            using (Stream responseStream = ServiceController.GetStream(apiUrl, AppSid, AppKey))
            using (Stream file = File.OpenWrite(outPath))
            {
                ServiceController.CopyStream(responseStream, file);
            }
        }

        /// <summary>
        /// Add email attachment.	
        /// </summary>
        /// <param name="name">File name. e.g. myEmail.msg</param>
        /// <param name="attachName">Attachment name</param>
        /// <param name="folder">Folder path.</param>
        /// <param name="storage">The document storage.</param>
        public void AddEmailAttachment(string name, string attachName, string folder, string storage = "")
        {
            // POST 	email/{name}/attachments/{attachName}?appSid={appSid}&storage={storage}&folder={folder} 

            string apiUrl = string.Format(@"email/{0}/attachments/{1}?storage={2}&folder={3}",
                                            name, attachName, storage, folder);

            ServiceController.Post(apiUrl, AppSid, AppKey);
        }
    }

    public enum EmailFormat
    {
        Eml, Msg, Mht
    }

    public class EmailProperty
    {
        public Link Link { get; set; }
        public string Name { get; set; }
        public string Value { get; set; }
    }

    public class EmailPropertyResponse
    {
        public EmailProperty EmailProperty { get; set; }
        public int Code { get; set; }
        public string Status { get; set; }
    }

    public class EmailDocument
    {
        public List<Link> Links { get; set; }
        public List<BccAddress> Bcc { get; set; }
        public string Body { get; set; }
        public List<CCAddress> CC { get; set; }
        public DateTime Date { get; set; }
        public int DeliveryNotificationOptions { get; set; }
        public FromAddress From { get; set; }
        public List<ToAddress> To { get; set; }
        public string HtmlBody { get; set; }
        public bool IsBodyHtml { get; set; }
        public string MessageId { get; set; }
        public int Priority { get; set; }
        public string Subject { get; set; }
        public string TextBody { get; set; }
        public List<Attachment> Attachments { get; set; }
    }

    public class EmailDocumentPropertiesResponse
    {
        public object Links { get; set; }
        public EmailDocumentProperties DocumentProperties { get; set; }
        public object Data { get; set; }
        public object Format { get; set; }
    }


    public class EmailResponse
    {
        public Link Link { get; set; }
        public EmailDocument EmailDocument { get; set; }
        public int Code { get; set; }
        public string Status { get; set; }
    }

    public class BccAddress
    {
        public BccAddress(string address)
        {
            Address = address;
        }
        public string Address { get; set; }
    }

    public class CCAddress
    {
        public CCAddress(string address)
        {
            Address = address;
        }
        public string Address { get; set; }
    }

    public class FromAddress
    {
        public FromAddress(string address)
        {
            Address = address;
        }
        public string Address { get; set; }
    }

    public class ToAddress
    {
        public ToAddress(string address)
        {
            Address = address;
        }
        public string Address { get; set; }
    }

    public class Attachment
    {
        public string Name { get; set; }
        public object FileStream { get; set; }
    }

    public class EmailDocumentList
    {
        public Link Link { get; set; }
        public string Name { get; set; }
        public object Value { get; set; }
    }

    public class EmailDocumentProperties
    {
        public Link Link { get; set; }
        public List<EmailDocumentList> List { get; set; }
    }

    public class EmailDocumentPropertiesRequest
    {
        public List<Link> Links { get; set; }
        public EmailDocumentProperties DocumentProperties { get; set; }
        public object Data { get; set; }
        public string Format { get; set; }
    }
}
