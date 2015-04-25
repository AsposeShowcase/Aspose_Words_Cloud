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
    public class OCRServiceTests
    {
        OCRService ocrService = new OCRService(Utils.AppSid, Utils.AppKey);

        [TestMethod()]
        public void OCR_Tests()
        {
            OCRResponse ocrResponse = ocrService.RecognizeImageText("ocr-sample.bmp", OCRLanguages.English, 0, 0, 500, 300, false, Utils.CloudStorage_Input_Folder);
            OCRResponse ocrResponse2 = ocrService.RecognizeImageTextFromUrl("http://cdn.aspose.com/tmp/ocr-sample.bmp", OCRLanguages.English, false);
            OCRResponse ocrResponse3 = ocrService.RecognizeImageText(Utils.Local_Input_Path + "ocr-sample.bmp", OCRLanguages.English, false);
        }
    }
}
