using System;
using System.Collections.Generic;
using System.Web;
using System.Web.UI;
using System.IO;
using System.Web.UI.WebControls;
using System.Configuration;
using Aspose.Cloud.Common;
using Aspose.Cloud;
using Aspose.CloudTests;
//using Newtonsoft.Json;
//using Newtonsoft.Json.Linq;

namespace Aspose.CloudTestWebApp
{
    public partial class _Default : System.Web.UI.Page
    {
        App obj = new App(Utils.AppSid, Utils.AppKey);
        BarCodeService barCodeService = new BarCodeService(Utils.AppSid, Utils.AppKey);
        ImagingService imagingService = new ImagingService(Utils.AppSid, Utils.AppKey);
        CellsService cellsService = new CellsService(Utils.AppSid, Utils.AppKey);
        OCRService ocrService = new OCRService(Utils.AppSid, Utils.AppKey);

        StorageService storageService = new StorageService(Utils.AppSid, Utils.AppKey);
        UsageService usageService = new UsageService(Utils.AppSid, Utils.AppKey);
        SDKService sdkService = new SDKService(Utils.AppSid, Utils.AppKey);
        HerokuService herokuService = new HerokuService(Utils.AppSid, Utils.AppKey);
        StatisticsService statisticsService = new StatisticsService(Utils.AppSid, Utils.AppKey);
        EmailService emailService = new EmailService(Utils.AppSid, Utils.AppKey);
        PDFService pdfService = new PDFService(Utils.AppSid, Utils.AppKey);
        SlidesService slidesService = new SlidesService(Utils.AppSid, Utils.AppKey);
        TasksService tasksService = new TasksService(Utils.AppSid, Utils.AppKey);
        WordsService wordsService = new WordsService(Utils.AppSid, Utils.AppKey);

        protected void Page_Load(object sender, EventArgs e)
        {
            // Put your testing code here
        }
    }
}