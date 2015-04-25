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
    public class EmailServiceTests
    {
        EmailService emailService = new EmailService(Utils.AppSid, Utils.AppKey);
        StorageService storageService = new StorageService(Utils.AppSid, Utils.AppKey);

        [TestMethod()]
        public void Email_Tests()
        {
            try
            {
                emailService.ConvertMailMessageToTargetFormat("email-sample.mht", EmailFormat.Msg, Utils.CloudStorage_Input_Folder, Utils.Local_Output_Path + "email-output.msg");
                emailService.ConvertMailMessageToTargetFormat("email-sample.mht", EmailFormat.Msg, Utils.CloudStorage_Input_Folder, Utils.CloudStorage_Output_Folder + "/email-output.msg");

                EmailDocumentPropertiesResponse emailDocumentPropertiesResponse = emailService.GetMailCommonInfo("email-sample.msg", Utils.CloudStorage_Input_Folder);
                storageService.File.CopyFile(Utils.CloudStorage_Input_Folder + "/email-sample.msg", Utils.CloudStorage_Output_Folder + "/email-sample.msg");

                EmailDocumentPropertiesRequest emailDocumentPropertiesRequest = new EmailDocumentPropertiesRequest();
                emailDocumentPropertiesRequest.DocumentProperties = emailDocumentPropertiesResponse.DocumentProperties;

                // Removing attachments as it is not supported by this method
                emailDocumentPropertiesRequest.DocumentProperties.List.RemoveAt(emailDocumentPropertiesRequest.DocumentProperties.List.Count - 1);

                emailService.AddNewEmail("email-sample.msg", Utils.CloudStorage_Output_Folder, emailDocumentPropertiesRequest);

                //storageService.File.DownloadFile(Utils.CloudStorage_Output_Folder + "/email-sample.msg", Utils.Local_Output_Path + "email-created.msg");


                EmailProperty emailProperty = new EmailProperty();
                emailProperty.Name = "Subject";
                emailProperty.Value = "My test subject";
                emailService.SetDocumentProperty("email-sample.mht", "Subject", emailProperty, Utils.CloudStorage_Input_Folder);

                EmailPropertyResponse emailPropertyResponse = emailService.ReadDocumentPropertyByName("email-sample.mht", "Subject", Utils.CloudStorage_Input_Folder);

                emailService.GetEmailAttachmentByName("email-sample.eml", "barcode-sample.png", Utils.CloudStorage_Input_Folder, Utils.Local_Output_Path + "email-attach-out.png");


                storageService.File.CopyFile(Utils.CloudStorage_Input_Folder + "/email-sample.eml", Utils.CloudStorage_Output_Folder + "/email-addAttach.eml");
                storageService.File.CopyFile(Utils.CloudStorage_Input_Folder + "/barcode-sample.png", Utils.CloudStorage_Output_Folder + "/barcode-sample2.png");
                emailService.AddEmailAttachment("email-addAttach.eml", Utils.CloudStorage_Output_Folder + "/barcode-sample2.png", Utils.CloudStorage_Output_Folder);
                emailService.GetEmailAttachmentByName("email-addAttach.eml", "barcode-sample2.png", Utils.CloudStorage_Output_Folder, Utils.Local_Output_Path + "/email-attach-out2.png");

            }
            catch (Exception ex)
            {
                Assert.Fail(ex.Message);
            }
        }
    }
}
