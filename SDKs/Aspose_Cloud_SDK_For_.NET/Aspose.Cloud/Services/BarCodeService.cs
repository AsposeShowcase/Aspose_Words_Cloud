using System;
using System.Collections.Generic;
using System.Text;
using Aspose.Cloud.Common;
using Newtonsoft.Json;
using Newtonsoft.Json.Linq;
using System.IO;

namespace Aspose.Cloud
{
    public class BarCodeService : BaseService
    {
        public BarCodeService(string appSid, string appKey)
            : base(appSid, appKey)
        {
            AppSid = appSid;
            AppKey = appKey;
        }


        /// <summary>
        /// Generate barcode, the generated barcode image will be saved on provided disk location
        /// </summary>
        /// <param name="text">The code text</param>
        /// <param name="type">Barcode type</param>
        /// <param name="format">Result format</param>
        /// <param name="enableChecksum">Sets if checksum will be generated</param>
        /// <param name="localPathWithFileNameAndExtension">Local disk path with filename and extension to save the generated barcode image. It will overwrite the file if it already exists</param>
        /// <param name="resolutionX">Horizontal resolution in DPI. Default is 96</param>
        /// <param name="resolutionY">Vertical resolution in DPI. Default is 96</param>
        /// <param name="dimensionX">Smallest width of barcode unit (bar or space). Default is 0.7</param>
        /// <param name="dimensionY">Smallest height of barcode unit (for 2D barcodes). Default is 2</param>
        public void GenerateBarCode(string text, BarCodeType type, BarCodeImageFormat format, string localPathWithFileNameAndExtension,
                                    EnableChecksum enableChecksum = EnableChecksum.No, float resolutionX = 96, float resolutionY = 96, double dimensionX = 0.7, float dimensionY = 2)
        {
            // GET barcode/generate?appSid={appSid}&text={text}&type={type}&format={format}&resolutionX={resolutionX}
            //                                &resolutionY={resolutionY}&dimensionX={dimensionX}&dimensionY={dimensionY}&enableChecksum={enableChecksum} 

            string apiUrl = string.Format(@"barcode/generate?text={0}&type={1}&format={2}&resolutionX={3}&resolutionY={4}&dimensionX={5}&dimensionY={6}&enableChecksum={7}",
                                            text, type, format, resolutionX, resolutionY, dimensionX, dimensionY, enableChecksum);

            using (Stream responseStream = ServiceController.GetStream(apiUrl, AppSid, AppKey))
            using (Stream file = File.OpenWrite(localPathWithFileNameAndExtension))
            {
                ServiceController.CopyStream(responseStream, file);
            }
        }

        /// <summary>
        ///  Generate barcode and save on server
        /// </summary>
        /// <param name="name">The image name.</param>
        /// <param name="text">Barcode's text.</param>
        /// <param name="type">The barcode type.</param>
        /// <param name="format">The image format.</param>
        /// <param name="codeLocation">Property of the barcode.</param>
        /// <param name="grUnit">Measurement of barcode properties.</param>
        /// <param name="autoSize">Sets if barcode size will be updated automatically.</param>
        /// <param name="barHeight">Height of the bar.</param>
        /// <param name="imageHeight">Height of the image.</param>
        /// <param name="imageWidth">Width of the image.</param>
        /// <param name="imageQuality">Determines quality of the barcode image.</param>
        /// <param name="rotAngle">Angle of barcode orientation.</param>
        /// <param name="topMargin">Top margin.</param>
        /// <param name="bottomMargin">Bottom margin.</param>
        /// <param name="leftMargin">Left margin.</param>
        /// <param name="rightMargin">Right margin.</param>
        /// <param name="enableChecksum">Sets if checksum will be generated.</param>
        /// <param name="storage">Image's storage.</param>
        /// <param name="folder">Image's folder.</param>
        /// <param name="resolutionX">Horizontal resolution in DPI. Default is 96</param>
        /// <param name="resolutionY">Vertical resolution in DPI. Default is 96</param>
        /// <param name="dimensionX">Smallest width of barcode unit (bar or space). Default is 0.7</param>
        /// <param name="dimensionY">Smallest height of barcode unit (for 2D barcodes). Default is 2</param>
        public void GenerateBarCodeAndSaveOnServer(string name, string text, BarCodeType type,
                                                   float barHeight, float imageHeight, float imageWidth, float rotAngle,
                                                   float topMargin, float bottomMargin, float leftMargin, float rightMargin, string storage,
                                                   string folder,
                                                   BarCodeImageFormat format = BarCodeImageFormat.PNG, CodeLocation codeLocation = CodeLocation.Below, GrUnit grUnit = GrUnit.MM,
                                                   bool autoSize = true, ImageQuality imageQuality = ImageQuality.Default, EnableChecksum enableChecksum = EnableChecksum.No,
                                                   float resolutionX = 96, float resolutionY = 96, double dimensionX = 0.7, float dimensionY = 2)
        {
            // PUT 	barcode/{name}/generate?appSid={appSid}&text={text}&type={type}&format={format}&resolutionX={resolutionX}&resolutionY={resolutionY}&dimensionX={dimensionX}&dimensionY={dimensionY}&codeLocation={codeLocation}&grUnit={grUnit}&autoSize={autoSize}&barHeight={barHeight}&imageHeight={imageHeight}&imageWidth={imageWidth}&imageQuality={imageQuality}&rotAngle={rotAngle}&topMargin={topMargin}&bottomMargin={bottomMargin}&leftMargin={leftMargin}&rightMargin={rightMargin}&enableChecksum={enableChecksum}&storage={storage}&folder={folder}

            string apiUrl = string.Format(@"barcode/{0}/generate?text={1}&type={2}&format={3}&resolutionX={4}&resolutionY={5}&dimensionX={6}&dimensionY={7}&codeLocation={8}&grUnit={9}&autoSize={10}&barHeight={11}&imageHeight={12}&imageWidth={13}&imageQuality={14}&rotAngle={15}&topMargin={16}&bottomMargin={17}&leftMargin={18}&rightMargin={19}&enableChecksum={20}&storage={21}&folder={22}",
                                            name, text, type, format, resolutionX, resolutionY, dimensionX, dimensionY, codeLocation, grUnit, autoSize, barHeight,
                                            imageHeight, imageWidth, imageQuality, rotAngle, topMargin, bottomMargin, leftMargin, rightMargin, enableChecksum,
                                            storage, folder);

            ServiceController.Put(apiUrl, AppSid, AppKey);
        }


        /// <summary>
        /// 
        /// </summary>
        /// <param name="name">The image name.</param>
        /// <param name="type">The barcode type.</param>
        /// <param name="checksumValidation">Checksum validation parameter.</param>
        /// <param name="stripFNC">Allows to strip FNC symbol in recognition results.</param>
        /// <param name="barcodesCount">Count of barcodes to recognize.</param>
        /// <param name="rectX">Top left point X coordinate of  to recognize barcode inside.</param>
        /// <param name="rectY">Top left point Y coordinate of  to recognize barcode inside.</param>
        /// <param name="rectWidth">Width of  to recognize barcode inside.</param>
        /// <param name="rectHeight">Height of  to recognize barcode inside.</param>
        /// <param name="storage">The image storage.</param>
        /// <param name="folder">The image folder.</param>
        /// <param name="rotationAngle">Recogniton of rotated barcode. Possible angles are 90, 180, 270, default is 0</param>
        /// <returns>BarcodeResponse object with barcode data.</returns>
        public BarcodeResponse RecognizeBarCodeFromFileOnServer(string name, BarCodeType type, ChecksumValidation checksumValidation, bool stripFNC, int barcodesCount,
                                                                int rectX, int rectY, int rectWidth, int rectHeight, string storage, string folder, int rotationAngle = 0)
        {
            // GET 	barcode/{name}/recognize?appSid={appSid}&type={type}&checksumValidation={checksumValidation}&stripFNC={stripFNC}&rotationAngle={rotationAngle}&barcodesCount={barcodesCount}&rectX={rectX}&rectY={rectY}&rectWidth={rectWidth}&rectHeight={rectHeight}&storage={storage}&folder={folder} 

            string apiUrl = string.Format(@"barcode/{0}/recognize?type={1}&checksumValidation={2}&stripFNC={3}&rotationAngle={4}&barcodesCount={5}&rectX={6}&rectY={7}&rectWidth={8}&rectHeight={9}&storage={10}&folder={11}",
                                            name, type, checksumValidation, stripFNC, rotationAngle, barcodesCount, rectX, rectY, rectWidth, rectHeight, storage, folder);

            JObject jObject = JObject.Parse(ServiceController.Get(apiUrl, AppSid, AppKey));
            BarcodeResponse barcodeResponse = jObject.ToObject<BarcodeResponse>();
            return barcodeResponse;
        }

        /// <summary>
        /// Recognition of a barcode from file on server with parameters in body. 
        /// </summary>
        /// <param name="name">The image name.</param>
        /// <param name="barcodeReader">BarcodeReader object with parameters.</param>
        /// <param name="type">The barcode type.</param>
        /// <param name="folder">The image folder.</param>
        /// <returns>BarcodeResponse object with barcode data.</returns>
        public BarcodeResponse RecognizeBarCodeFromFileOnServer(string name, BarCodeReader barcodeReader, BarCodeType type, string folder)
        {
            // PUT 	barcode/{name}/recognize?appSid={appSid}&type={type}&folder={folder}

            string apiUrl = string.Format(@"barcode/{0}/recognize?type={1}&folder={2}", name, type, folder);
            JObject jObject = JObject.Parse(ServiceController.Put(apiUrl, AppSid, AppKey, JsonConvert.SerializeObject(barcodeReader)));
            BarcodeResponse barcodeResponse = jObject.ToObject<BarcodeResponse>();
            return barcodeResponse;
        }

        /// <summary>
        ///  Recognize barcode from a url. 
        /// </summary>
        /// <param name="type">Barcode type.</param>
        /// <param name="checksumValidation">Checksum validation parameter</param>
        /// <param name="stripFNC">Allows to strip FNC symbol in recognition results.</param>        
        /// <param name="url">The image file url.</param>
        /// <param name="rotationAngle">Recogniton of rotated barcode. Possible angles are 90, 180, 270, default is 0</param>
        /// <returns>BarcodeResponse object with barcode data.</returns>
        public BarcodeResponse RecognizeBarCodeFromUrl(BarCodeType type, ChecksumValidation checksumValidation, bool stripFNC, string url, int rotationAngle = 0)
        {
            // POST barcode/recognize?appSid={appSid}&type={type}&checksumValidation={checksumValidation}&stripFNC={stripFNC}&rotationAngle={rotationAngle}&url={url}

            string apiUrl = string.Format(@"barcode/recognize?type={0}&checksumValidation={1}&stripFNC={2}&rotationAngle={3}&url={4}",
                                            type, checksumValidation, stripFNC, rotationAngle, url);

            JObject jObject = JObject.Parse(ServiceController.Post(apiUrl, AppSid, AppKey));
            BarcodeResponse barcodeResponse = jObject.ToObject<BarcodeResponse>();
            return barcodeResponse;
        }
    }

    public class BarCodeReader
    {
        // Checksum validation parameter
        public ChecksumValidation ChecksumValidation { get; set; }

        // Allows to strip FNC symbol in recognition results.
        public bool StripFNC { get; set; }

        // Count of barcodes to recognize.
        public int BarcodesCount { get; set; }

        // Recogniton of rotated barcode. Possible angles are 90, 180, 270, default is 0
        public int RotationAngle { get; set; }

        // Binarization hints
        public BinarizationHints BinarizationHints { get; set; }
    }

    public class BarcodeResponse
    {
        public List<BarCode> Barcodes { get; set; }
        public int Code { get; set; }
        public string Status { get; set; }
    }

    public class BarCode
    {
        public string BarcodeValue { get; set; }
        public BarCodeType BarcodeType { get; set; }
        public List<string> Region { get; set; }
    }

    /// <summary>
    /// Supported types for generating barcodes
    /// </summary>
    public enum BarCodeType
    {
        Codabar,
        Code11,
        Code128,
        Code39Extended,
        Code93Extended,
        Interleaved2of5,
        Code39Standard,
        Code93Standard,
        MSI,
        Standard2of5,
        DataMatrix,
        GS1DataMatrix,
        EAN13,
        EAN8,
        ITF14,
        Pdf417,
        Planet,
        Postnet,
        QR,
        UPCA,
        UPCE,
        BooklandEAN,
        EAN128,
        Aztec,
        MacroPdf417,
        Patch,
        EAN14,
        SSCC18,
        OneCode,
        AustraliaPost,
        RM4SCC,
        Matrix2of5,
        DeutschePostIdentcode,
        PZN,
        ItalianPost25,
        IATA2of5,
        VIN,
        DeutschePostLeitcode,
        OPC,
        ITF6,
        AustralianPosteParcel
    }

    /// <summary>
    /// Supported image formats
    /// </summary>
    public enum BarCodeImageFormat
    {
        PNG, JPG, JPEG
    }

    public enum ChecksumValidation
    {
        Default, On, Off
    }

    public enum BinarizationHints
    {
        None, Grayscale, MedianSmoothing, InvertImage, ComplexBackground
    }

    public enum CodeLocation
    {
        Above, Below, None
    }

    public enum GrUnit
    {
        MM, PX, IN, PT
    }

    public enum ImageQuality
    {
        Default, Antialias
    }

    public enum EnableChecksum
    {
        Yes, No, Default 
    }
}
