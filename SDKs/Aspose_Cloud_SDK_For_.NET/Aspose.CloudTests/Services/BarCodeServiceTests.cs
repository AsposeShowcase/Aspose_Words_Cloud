using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Aspose.Cloud;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using System.Configuration;

namespace Aspose.CloudTests
{
    [TestClass()]
    public class BarCodeServiceTests
    {
        BarCodeService barCodeService = new BarCodeService(Utils.AppSid, Utils.AppKey);

        [TestMethod()]
        public void BarCode_Tests()
        {
            try
            {
                BarcodeResponse barcodeResponse = barCodeService.RecognizeBarCodeFromUrl(BarCodeType.QR, ChecksumValidation.Default, true, "http://cdn.aspose.com/tmp/test-barcode-image.png");
                if (!barcodeResponse.Barcodes[0].BarcodeValue.Equals("Aspose"))
                    Assert.Fail();

                barCodeService.GenerateBarCode("Aspose", BarCodeType.QR, BarCodeImageFormat.PNG, Utils.Local_Output_Path + "barcode-sample.png");

                string outFileName = @"GenerateBarCode-" + DateTime.Now.ToString().Replace(":", "-").Replace("/", "-").Replace(" ", "-") + ".bmp";
                barCodeService.GenerateBarCodeAndSaveOnServer(outFileName, "Apose Cloud", BarCodeType.QR, 30, 50, 40, 90, 2, 2, 2, 2, string.Empty, Utils.CloudStorage_Output_Folder);

                BarcodeResponse barcodeResponse2 = barCodeService.RecognizeBarCodeFromFileOnServer("barcode-sample.png", BarCodeType.QR, ChecksumValidation.Default, true, 1, 10, 10, 200, 100, string.Empty, Utils.CloudStorage_Input_Folder);
                if (!barcodeResponse2.Barcodes[0].BarcodeValue.Equals("Aspose"))
                    Assert.Fail();

                BarCodeReader barCodeReader = new BarCodeReader();
                barCodeReader.BarcodesCount = 1;
                barCodeReader.ChecksumValidation = 0;
                barCodeReader.StripFNC = true;
                barCodeReader.RotationAngle = 3;
                barCodeReader.BinarizationHints = BinarizationHints.None;

                BarcodeResponse barcodeResponse3 = barCodeService.RecognizeBarCodeFromFileOnServer("barcode-sample.png", barCodeReader, BarCodeType.QR, Utils.CloudStorage_Input_Folder);
                if (!barcodeResponse3.Barcodes[0].BarcodeValue.Equals("Aspose"))
                    Assert.Fail();
            }
            catch (Exception ex)
            {
                Assert.Fail(ex.Message);
            }
        }
    }
}
