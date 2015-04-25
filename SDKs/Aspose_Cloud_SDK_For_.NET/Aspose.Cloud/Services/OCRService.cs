using System;
using System.Collections.Generic;
using System.Text;
using Aspose.Cloud.Common;
using Newtonsoft.Json;
using Newtonsoft.Json.Linq;
using System.IO;

namespace Aspose.Cloud
{
    public class OCRService : BaseService
    {
        public OCRService(string appSid, string appKey)
            : base(appSid, appKey)
        {
            AppSid = appSid;
            AppKey = appKey;
        }

        /// <summary>
        /// Recognize image text, language and text region can be selected, default dictionaries can be used for correction.
        /// </summary>
        /// <param name="name">Name of the file to recognize. BMP, TIFF formats are supported now.</param>
        /// <param name="language">Language of the document. e.g. language=english</param>
        /// <param name="rectX">Top left point X coordinate of  to recognize text inside.</param>
        /// <param name="rectY">Top left point Y coordinate of  to recognize text inside.</param>
        /// <param name="rectWidth">Width of  to recognize text inside.</param>
        /// <param name="rectHeight">Height of  to recognize text inside.</param>
        /// <param name="useDefaultDictionaries">Use default dictionaries for result correction.</param>
        /// <param name="folder">Image's folder.</param>
        /// <param name="storage">Image's storage.</param>
        /// <returns>OCRResponse object</returns>
        public OCRResponse RecognizeImageText(string name, OCRLanguages language, int rectX, int rectY, int rectWidth, int rectHeight, bool useDefaultDictionaries, string folder, string storage = "")
        {
            // GET 	ocr/{name}/recognize?appSID={appSID}&language={language}&rectX={rectX}&rectY={rectY}&rectWidth={rectWidth}&rectHeight={rectHeight}&useDefaultDictionaries={useDefaultDictionaries}&storage={storage}&folder={folder}

            string apiUrl = string.Format(@"ocr/{0}/recognize?language={1}&rectX={2}&rectY={3}&rectWidth={4}&rectHeight={5}&useDefaultDictionaries={6}&storage={7}&folder={8}",
                                            name, language, rectX, rectY, rectWidth, rectHeight, useDefaultDictionaries, storage, folder);

            JObject jObject = JObject.Parse(ServiceController.Get(apiUrl, AppSid, AppKey));
            OCRResponse ocrResponse = jObject.ToObject<OCRResponse>();
            return ocrResponse;
        }

        /// <summary>
        /// Recognize image text. BMP, TIFF formats are supported now.
        /// </summary>
        /// <param name="inputFilePath">Input BMP or TIFF file that will be passed as request body</param>
        /// <param name="language">Language of document to recogniize.</param>
        /// <param name="useDefaultDictionaries">Use default dictionaries for result correction.</param>
        /// <returns>OCRResponse object</returns>
        public OCRResponse RecognizeImageText(string inputFilePath, OCRLanguages language, bool useDefaultDictionaries)
        {
            string apiUrl = string.Format(@"ocr/recognize?language={0}&useDefaultDictionaries={1}", language, useDefaultDictionaries);

            JObject jObject = JObject.Parse(ServiceController.Post(apiUrl, AppSid, AppKey, File.ReadAllBytes(inputFilePath)));
            OCRResponse ocrResponse = jObject.ToObject<OCRResponse>();
            return ocrResponse;
        }

        /// <summary>
        /// Recognize image text from some url. BMP, TIFF formats are supported now.
        /// </summary>
        /// <param name="url">The image file url.</param>
        /// <param name="language">Language of document to recogniize.</param>
        /// <param name="useDefaultDictionaries">Use default dictionaries for result correction.</param>
        /// <returns>OCRResponse object</returns>
        public OCRResponse RecognizeImageTextFromUrl(string url, OCRLanguages language, bool useDefaultDictionaries)
        {
            string apiUrl = string.Format(@"ocr/recognize?url={0}&language={1}&useDefaultDictionaries={2}", url, language, useDefaultDictionaries);

            JObject jObject = JObject.Parse(ServiceController.Post(apiUrl, AppSid, AppKey));
            OCRResponse ocrResponse = jObject.ToObject<OCRResponse>();
            return ocrResponse;
        }
    }

    public enum OCRLanguages
    {
         English, French, Russian, Spanish
    }

    public class Part
    {
        public string Text { get; set; }
        public double FontSize { get; set; }
        public string FontName { get; set; }
        public bool Bold { get; set; }
        public bool Italic { get; set; }
        public bool Underline { get; set; }
    }

    public class PartsInfo
    {
        public List<Part> Parts { get; set; }
    }

    public class OCRResponse
    {
        public string Text { get; set; }
        public PartsInfo PartsInfo { get; set; }
        public int Code { get; set; }
        public string Status { get; set; }
    }
}