using System;
using System.Collections.Generic;
using System.Text;
using Aspose.Cloud.Common;
using Newtonsoft.Json;
using Newtonsoft.Json.Linq;
using System.IO;

namespace Aspose.Cloud
{
    public enum WordOutputFormat
    {
        Doc, Dot, Docx, Docm, Dotx, Dotm, Flatopc, Rtf, Wml, Odt, Ott, Txt, Mhtml, Epub, Pdf, Xps, Swf, Tiff, Png, Jpeg, Bmp, Svg, Html
    }

    public enum WordCleanupOptions
    {
        None, EmptyParagraphs, UnusedRegions, UnusedFields, ContainingFields
    }

    public enum WordDrawingObjectsFormat
    {
        Tiff, Png, Bmp, Jpg, Jpeg
    }

    public enum WordTiffImageColorMode
    {
        None, Grayscale, BlackAndWhite
    }

    public enum WordTiffNumeralFormat
    {
        European, ArabicIndic, EasternArabicIndic, Context, System
    }

    public enum WordTiffPixelFormat
    {
        Format16BppRgb555, Format16BppRgb565, Format16BppArgb1555, Format24BppRgb, Format32BppRgb, Format32BppArgb, Format32BppPArgb, Format48BppRgb, Format64BppArgb, Format64BppPArgb
    }

    public enum DmlRenderingMode { Fallback, DrawingML }
    public enum DmlEffectsRenderingMode { Simplified, None, Fine }
    public enum TiffBinarizationMethod { FloydSteinbergDithering, Threshold }
    public enum ProtectionType { AllowOnlyComments, AllowOnlyFormFields, AllowOnlyRevisions, ReadOnly, NoProtection }
    public enum WordImportFormatMode { KeepSourceFormatting, UseDestinationStyles }

    public enum SplitDocumentFormat
    {
        Bmp,
        Doc,
        Docm,
        Docx,
        Dot,
        Dotm,
        Dotx,
        Emf,
        Epub,
        Flatopc,
        Flatopc_macro,
        Flatopc_template,
        Flatopc_template_macro,
        Jpeg,
        Html,
        Mhtml,
        Odt,
        Openxps,
        Ott,
        Pdf,
        Png,
        Ps,
        Rtf,
        Svg,
        Swf,
        Text,
        Tiff,
        Wordml,
        Wml,
        Xps,
        Xamlfixed
    }


    public class WordsService : BaseService
    {
        public WordsService(string appSid, string appKey)
            : base(appSid, appKey)
        {
            AppSid = appSid;
            AppKey = appKey;

            WordsBookmarks = new WordsBookmarksClass(appSid, appKey);
            WordsDocumentFields = new WordsDocumentFieldsClass(appSid, appKey);
            WordsDocumentProperties = new WordsDocumentPropertiesClass(appSid, appKey);
            WordsDocumentProtection = new WordsDocumentProtectionClass(appSid, appKey);
            WordsDocumentSaveAs = new WordsDocumentSaveAsClass(appSid, appKey);
            WordsDocumentStatistics = new WordsDocumentStatisticsClass(appSid, appKey);
            WordsDocumentWatermark = new WordsDocumentWatermarkClass(appSid, appKey);
            WordsDrawingObjects = new WordsDrawingObjectsClass(appSid, appKey);
            WordsHeadersFooters = new WordsHeadersFootersClass(appSid, appKey);
            WordsHyperlinks = new WordsHyperlinksClass(appSid, appKey);
            WordsMailMerge = new WordsMailMergeClass(appSid, appKey);
            WordsParagraph = new WordsParagraphClass(appSid, appKey);
            WordsRevisions = new WordsRevisionsClass(appSid, appKey);
            WordsSections = new WordsSectionsClass(appSid, appKey);
            WordsTextItems = new WordsTextItemsClass(appSid, appKey);
        }

        public WordsBookmarksClass WordsBookmarks { get; set; }
        public WordsDocumentFieldsClass WordsDocumentFields { get; set; }
        public WordsDocumentPropertiesClass WordsDocumentProperties { get; set; }
        public WordsDocumentProtectionClass WordsDocumentProtection { get; set; }
        public WordsDocumentSaveAsClass WordsDocumentSaveAs { get; set; }
        public WordsDocumentStatisticsClass WordsDocumentStatistics { get; set; }
        public WordsDocumentWatermarkClass WordsDocumentWatermark { get; set; }
        public WordsDrawingObjectsClass WordsDrawingObjects { get; set; }
        public WordsHeadersFootersClass WordsHeadersFooters { get; set; }
        public WordsHyperlinksClass WordsHyperlinks { get; set; }
        public WordsMailMergeClass WordsMailMerge { get; set; }
        public WordsParagraphClass WordsParagraph { get; set; }
        public WordsRevisionsClass WordsRevisions { get; set; }
        public WordsSectionsClass WordsSections { get; set; }
        public WordsTextItemsClass WordsTextItems { get; set; }


        /// <summary>
        /// Read document common info
        /// </summary>
        /// <param name="name">The file name.</param>
        /// <param name="folder">The document folder.</param>
        /// <param name="storage">The document storage.</param>
        /// <returns>WordDocumentResponse object</returns>
        public WordDocumentResponse ReadDocumentCommonInfo(string name, string folder, string storage = "")
        {
            // GET 	words/{name}?appSid={appSid}&format={format}&storage={storage}&folder={folder}&outPath={outPath} 

            string apiUrl = string.Format(@"words/{0}?storage={1}&folder={2}",
                                            name, storage, folder);

            JObject jObject = JObject.Parse(ServiceController.Get(apiUrl, AppSid, AppKey));
            WordDocumentResponse wordDocumentResponse = jObject.ToObject<WordDocumentResponse>();
            return wordDocumentResponse;
        }

        /// <summary>
        /// Convert document to format specified.	
        /// </summary>
        /// <param name="name">The file name.</param>
        /// <param name="format">The destination format.</param>
        /// <param name="folder">The document folder.</param>
        /// <param name="outPath">Path to save result. It can be local (e.g. c:\out.doc) or cloud storage path (MyFolder\out.doc).</param>
        /// <param name="storage">The document storage.</param>
        public void ConvertDocument(string name, WordOutputFormat format, string folder, string outPath, string storage = "")
        {
            // GET 	words/{name}?appSid={appSid}&format={format}&storage={storage}&folder={folder}&outPath={outPath} 

            string apiUrl = string.Format(@"words/{0}?format={1}&storage={2}&folder={3}&outPath={4}",
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
        /// Convert document from request content to format specified.	
        /// </summary>
        /// <param name="format">The destination format.</param>
        /// <param name="outPath">Path to save result. Must be a cloud storage path (MyFolder\out.doc).</param>
        /// <param name="inputFilePath">Input file e.g. c:\input.doc).</param>
        public void ConvertDocument(WordOutputFormat format, string outPath, string inputFilePath)
        {
            // PUT 	words/convert?appSid={appSid}&format={format}&outPath={outPath} 

            string apiUrl = string.Format(@"words/convert?format={0}&outPath={1}",
                                            format, outPath);

            ServiceController.Put(apiUrl, AppSid, AppKey, File.ReadAllBytes(inputFilePath));
        }

        /// <summary>
        /// Insert document page numbers.	
        /// </summary>
        /// <param name="name">The file name.</param>
        /// <param name="filename">Result name of the document after the operation. If this parameter is omitted then result of the operation will be saved as the source document</param>
        /// <param name="folder">The document folder.</param>
        /// <param name="storage">The document storage.</param>
        public void InsertDocumentPageNumbers(string name, string filename, string folder, PageNumberSettings pageNumberSettings, string storage = "")
        {
            // POST 	words/{name}/insertPageNumbers?appSid={appSid}&filename={filename}&storage={storage}&folder={folder} 

            string apiUrl = string.Format(@"words/{0}/insertPageNumbers?filename={1}&storage={2}&folder={3}",
                                                name, filename, storage, folder);

            ServiceController.Post(apiUrl, AppSid, AppKey, JsonConvert.SerializeObject(pageNumberSettings));
        }

        /// <summary>
        /// Insert document watermark image.	
        /// </summary>
        /// <param name="name">The file name.</param>
        /// <param name="filename">Result name of the document after the operation. If this parameter is omitted then result of the operation will be saved as the source document</param>
        /// <param name="rotationAngle">The watermark rotation angle.</param>
        /// <param name="image">The image file server full name. It can be local image path (c:\watermark.jpg) or cloud storage Folder\watermark.jpg</param>
        /// <param name="folder">The document folder.</param>
        /// <param name="storage">The document storage.</param>
        public void InsertDocumentWatermarkImage(string name, string filename, double rotationAngle, string image, string folder, string storage = "")
        {
            // POST 	words/{name}/insertWatermarkImage?appSid={appSid}&filename={filename}&rotationAngle={rotationAngle}&image={image}&storage={storage}&folder={folder} 

            string apiUrl = string.Format(@"words/{0}/insertWatermarkImage?filename={1}&rotationAngle={2}&image={3}&storage={4}&folder={5}",
                                            name, filename, rotationAngle, (image.Contains(@":\") ? string.Empty : image), storage, folder);

            if (!string.IsNullOrEmpty(image) && Directory.Exists(Path.GetDirectoryName(image)))
            {
                ServiceController.Post(apiUrl, AppSid, AppKey, File.ReadAllBytes(image));
            }
            else
            {
                ServiceController.Post(apiUrl, AppSid, AppKey);
            }
        }

        /// <summary>
        /// Insert document watermark text.	
        /// </summary>
        /// <param name="name">The file name.</param>
        /// <param name="text">The text to insert.</param>
        /// <param name="rotationAngle">The watermark rotation angle.</param>
        /// <param name="filename">Result name of the document after the operation. If this parameter is omitted then result of the operation will be saved as the source document</param>
        /// <param name="folder">The document folder.</param>
        /// <param name="storage">The document storage.</param>
        public void InsertDocumentWatermarkText(string name, string text, double rotationAngle, string filename, string folder, string storage = "")
        {
            // POST 	words/{name}/insertWatermarkText?appSid={appSid}&text={text}&rotationAngle={rotationAngle}&filename={filename}&storage={storage}&folder={folder} 

            string apiUrl = string.Format(@"words/{0}/insertWatermarkText?text={1}&rotationAngle={2}&filename={3}&storage={4}&folder={5}",
                                            name, text, rotationAngle, filename, storage, folder);

            ServiceController.Post(apiUrl, AppSid, AppKey);
        }

        /// <summary>
        /// Populate document template with data.	
        /// </summary>
        /// <param name="name">The file name.</param>
        /// <param name="cleanup">Clean up options.</param>
        /// <param name="filename">Result name of the document after the operation. If this parameter is omitted then result of the operation will be saved as the source document</param>
        /// <param name="folder">The document folder.</param>
        /// <param name="storage">The document storage.</param>
        public void PopulateDocumentTemplateWithData(string name, WordCleanupOptions cleanup, string filename, string folder, string jsonData, string storage = "")
        {
            // POST 	words/{name}/executeTemplate?appSid={appSid}&cleanup={cleanup}&filename={filename}&storage={storage}&folder={folder} 

            string apiUrl = string.Format(@"words/{0}/executeTemplate?cleanup={1}&filename={2}&storage={3}&folder={4}",
                                            name, cleanup, filename, storage, folder);

            ServiceController.Post(apiUrl, AppSid, AppKey, jsonData);
        }

        /// <summary>
        /// Append documents to original document.	
        /// </summary>
        /// <param name="name">The file name.</param>
        /// <param name="filename">Result name of the document after the operation. If this parameter is omitted then result of the operation will be saved as the source document</param>
        /// <param name="folder">The document folder.</param>
        /// <param name="storage">The document storage.</param>
        public WordsDocumentResponse AppendDocumentsToOriginalDocument(string name, WordDocumentEntryRequest documentEntryRequest, string filename, string folder, string storage = "")
        {
            // POST 	words/{name}/appendDocument?appSid={appSid}&filename={filename}&storage={storage}&folder={folder} 

            string apiUrl = string.Format(@"words/{0}/appendDocument?filename={1}&storage={2}&folder={3}",
                                                name, filename, storage, folder);

            JObject jObject = JObject.Parse(ServiceController.Post(apiUrl, AppSid, AppKey, JsonConvert.SerializeObject(documentEntryRequest)));
            WordsDocumentResponse wordsDocumentResponse = jObject.ToObject<WordsDocumentResponse>();
            return wordsDocumentResponse;
        }

        /// <summary>
        /// Split document.	
        /// </summary>
        /// <param name="name">The file name.</param>
        /// <param name="format">Format to split.</param>
        /// <param name="from">Start page.</param>
        /// <param name="to">End page.</param>
        /// <param name="folder">The document folder.</param>
        /// <param name="storage">The document storage.</param>
        public WordsSplitResultResponse SplitDocument(string name, SplitDocumentFormat format, int from, int to, string folder, string storage = "")
        {
            // POST 	words/{name}/split?appSid={appSid}&format={format}&from={from}&to={to}&storage={storage}&folder={folder} 

            string apiUrl = string.Format(@"words/{0}/split?format={1}&from={2}&to={3}&storage={4}&folder={5}",
                                                name, format, from, to, storage, folder);

            JObject jObject = JObject.Parse(ServiceController.Post(apiUrl, AppSid, AppKey));
            WordsSplitResultResponse wordsSplitResultResponse = jObject.ToObject<WordsSplitResultResponse>();
            return wordsSplitResultResponse;
        }

        /// <summary>
        /// Execute document mail merge online. 
        /// </summary>
        /// <param name="withRegions">With regions flag.</param>
        /// <param name="cleanup">cleanup=ContainingFields, EmptyParagraphs, UnusedFields, UnusedRegions</param>
        /// <param name="mailMergeDataFile">mailMergeDataFile=SomeFolder\dataFile.xml </param>
        /// <param name="filename">filename=MailMergeResult.doc</param>
        /// <param name="folder">The document folder.</param>
        /// <param name="storage">The document storage.</param>
        public void ExecuteDocumentMailMergeOnline(string requestXMLorJson, bool withRegions, string cleanup, string mailMergeDataFile, string filename, string folder, string storage = "")
        {
            // PUT 	words/executeMailMerge?withRegions={withRegions}&appSid={appSid}&cleanup={cleanup} 

            string apiUrl = string.Format(@"words/executeMailMerge?withRegions={0}&cleanup={1}&filename={2}&storage={3}&folder={4}",
                                            withRegions, cleanup, filename, storage, folder);

            apiUrl += string.Format("&mailMergeDataFile={0}", mailMergeDataFile);

            ServiceController.Put(apiUrl, AppSid, AppKey, requestXMLorJson);
        }

        /// <summary>
        /// Load new document from web into the file with any supported format of data.
        /// </summary>
        public WordsSaveResultResponse LoadNewDocumentFromWebIntoFile(string requestXMLorJson)
        {
            // POST 	words/loadWebDocument?appSid={appSid} 	

            string apiUrl = "words/loadWebDocument";

            JObject jObject = JObject.Parse(ServiceController.Post(apiUrl, AppSid, AppKey, requestXMLorJson));
            WordsSaveResultResponse wordsSaveResultResponse = jObject.ToObject<WordsSaveResultResponse>();
            return wordsSaveResultResponse;
        }


        public class WordsBookmarksClass
        {
            public string AppKey { get; set; }
            public string AppSid { get; set; }

            internal WordsBookmarksClass(string appSid, string appKey) { AppSid = appSid; AppKey = appKey; }


            /// <summary>
            /// Read document bookmarks common info.	
            /// </summary>
            /// <param name="name">The file name.</param>
            /// <param name="folder">The document folder.</param>
            /// <param name="storage">The document storage.</param>
            public WordsBookmarksResponse ReadDocumentBookmarksCommonInfo(string name, string folder, string storage = "")
            {
                // GET 	words/{name}/bookmarks?appSid={appSid}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"words/{0}/bookmarks?storage={1}&folder={2}",
                                                name, storage, folder);

                JObject jObject = JObject.Parse(ServiceController.Get(apiUrl, AppSid, AppKey));
                WordsBookmarksResponse wordsBookmarksResponse = jObject.ToObject<WordsBookmarksResponse>();
                return wordsBookmarksResponse;
            }


            /// <summary>
            /// Read document bookmark data by its name.	
            /// </summary>
            /// <param name="name">The file name.</param>
            /// <param name="bookmarkName">The bookmark name.</param>
            /// <param name="folder">The document folder.</param>
            /// <param name="storage">The document storage.</param>
            public WordsBookmarkResponse ReadDocumentBookmarkDataByItsName(string name, string bookmarkName, string folder, string storage = "")
            {
                // GET 	words/{name}/bookmarks/{bookmarkName}?appSid={appSid}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"words/{0}/bookmarks/{1}?storage={2}&folder={3}",
                                                name, bookmarkName, storage, folder);

                JObject jObject = JObject.Parse(ServiceController.Get(apiUrl, AppSid, AppKey));
                WordsBookmarkResponse wordsBookmarkResponse = jObject.ToObject<WordsBookmarkResponse>();
                return wordsBookmarkResponse;
            }


            /// <summary>
            /// Update document bookmark.	
            /// </summary>
            /// <param name="name">The file name.</param>
            /// <param name="bookmarkName">The bookmark name.</param>
            /// <param name="filename">Result name of the document after the operation. If this parameter is omitted then result of the operation will be saved as the source document</param>
            /// <param name="folder">The document folder.</param>
            /// <param name="storage">The document storage.</param>
            public void UpdateDocumentBookmark(string name, string bookmarkName, WordsBookmarkRequest bookmarkData, string filename, string folder, string storage = "")
            {
                // POST 	words/{name}/bookmarks/{bookmarkName}?appSid={appSid}&filename={filename}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"words/{0}/bookmarks/{1}?filename={2}&storage={3}&folder={4}",
                                                name, bookmarkName, filename, storage, folder);

                ServiceController.Post(apiUrl, AppSid, AppKey, JsonConvert.SerializeObject(bookmarkData));
            }
        }

        public class WordsDocumentFieldsClass
        {
            public string AppKey { get; set; }
            public string AppSid { get; set; }

            internal WordsDocumentFieldsClass(string appSid, string appKey) { AppSid = appSid; AppKey = appKey; }


            /// <summary>
            /// Update (reevaluate) fields in document.	
            /// </summary>
            /// <param name="name">The file name.</param>
            /// <param name="filename">Result name of the document after the operation. If this parameter is omitted then result of the operation will be saved as the source document</param>
            /// <param name="folder">The document folder.</param>
            /// <param name="storage">The document storage.</param>
            public WordsDocumentResponse UpdateFieldsInDocument(string name, string filename, string folder, string storage = "")
            {
                // POST 	words/{name}/updateFields?appSid={appSid}&filename={filename}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"words/{0}/updateFields?filename={1}&storage={2}&folder={3}",
                                                               name, filename, storage, folder);

                JObject jObject = JObject.Parse(ServiceController.Post(apiUrl, AppSid, AppKey));
                WordsDocumentResponse wordsDocumentResponse = jObject.ToObject<WordsDocumentResponse>();
                return wordsDocumentResponse;
            }

        }

        public class WordsDocumentPropertiesClass
        {
            public string AppKey { get; set; }
            public string AppSid { get; set; }

            internal WordsDocumentPropertiesClass(string appSid, string appKey) { AppSid = appSid; AppKey = appKey; }


            /// <summary>
            /// Read document properties info.
            /// </summary>
            /// <param name="name">The file name.</param>
            /// <param name="folder">The document folder.</param>
            /// <param name="storage">The document storage.</param>
            public WordsDocumentPropertiesResponse ReadDocumentPropertiesInfo(string name, string folder, string storage = "")
            {
                // GET 	words/{name}/documentProperties?appSid={appSid}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"words/{0}/documentProperties?storage={1}&folder={2}",
                                                name, storage, folder);

                JObject jObject = JObject.Parse(ServiceController.Get(apiUrl, AppSid, AppKey));
                WordsDocumentPropertiesResponse wordsDocumentPropertiesResponse = jObject.ToObject<WordsDocumentPropertiesResponse>();
                return wordsDocumentPropertiesResponse;
            }


            /// <summary>
            /// Read document property info by the property name.	
            /// </summary>
            /// <param name="name">The file name.</param>
            /// <param name="propertyName">The property name.</param>
            /// <param name="folder">The document folder.</param>
            /// <param name="storage">The document storage.</param>
            public WordsDocumentPropertyResponse ReadDocumentPropertyInfoByPropertyName(string name, string propertyName, string folder, string storage = "")
            {
                // GET 	words/{name}/documentProperties/{propertyName}?appSid={appSid}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"words/{0}/documentProperties/{1}?storage={2}&folder={3}",
                                                name, propertyName, storage, folder);

                JObject jObject = JObject.Parse(ServiceController.Get(apiUrl, AppSid, AppKey));
                WordsDocumentPropertyResponse wordsDocumentPropertyResponse = jObject.ToObject<WordsDocumentPropertyResponse>();
                return wordsDocumentPropertyResponse;
            }


            /// <summary>
            /// Add new or update existing document property.	
            /// </summary>
            /// <param name="name">The file name.</param>
            /// <param name="propertyName">The property name.</param>
            /// <param name="filename">Result name of the document after the operation. If this parameter is omitted then result of the operation will be saved as the source document</param>
            /// <param name="folder">The document folder.</param>
            /// <param name="storage">The document storage.</param>
            public WordsDocumentPropertyResponse AddOrUpdateDocumentProperty(string name, string propertyName, WordsDocumentProperty documentProperty, string filename, string folder, string storage = "")
            {
                // PUT 	words/{name}/documentProperties/{propertyName}?appSid={appSid}&filename={filename}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"words/{0}/documentProperties/{1}?filename={2}&storage={3}&folder={4}",
                                                name, propertyName, filename, storage, folder);

                JObject jObject = JObject.Parse(ServiceController.Put(apiUrl, AppSid, AppKey, JsonConvert.SerializeObject(documentProperty)));
                WordsDocumentPropertyResponse wordsDocumentPropertyResponse = jObject.ToObject<WordsDocumentPropertyResponse>();
                return wordsDocumentPropertyResponse;
            }


            /// <summary>
            /// Delete document property.	
            /// </summary>
            /// <param name="name">The file name.</param>
            /// <param name="propertyName">The property name.</param>
            /// <param name="filename">Result name of the document after the operation. If this parameter is omitted then result of the operation will be saved as the source document</param>
            /// <param name="folder">The document folder.</param>
            /// <param name="storage">The document storage.</param>
            public void DeleteDocumentProperty(string name, string propertyName, string filename, string folder, string storage = "")
            {
                // DELETE 	words/{name}/documentProperties/{propertyName}?appSid={appSid}&filename={filename}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"words/{0}/documentProperties/{1}?filename={2}&storage={3}&folder={4}",
                                                name, propertyName, filename, storage, folder);

                ServiceController.Delete(apiUrl, AppSid, AppKey);
            }
        }

        public class WordsDocumentProtectionClass
        {
            public string AppKey { get; set; }
            public string AppSid { get; set; }

            internal WordsDocumentProtectionClass(string appSid, string appKey) { AppSid = appSid; AppKey = appKey; }


            /// <summary>
            /// Read document protection common info.	
            /// </summary>
            /// <param name="name">The file name.</param>
            /// <param name="folder">The document folder.</param>
            /// <param name="storage">The document storage.</param>
            public WordsProtectionDataResponse ReadDocumentProtectionCommonInfo(string name, string folder, string storage = "")
            {
                // GET 	words/{name}/protection?appSid={appSid}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"words/{0}/protection?storage={1}&folder={2}",
                                                name, storage, folder);

                JObject jObject = JObject.Parse(ServiceController.Get(apiUrl, AppSid, AppKey));
                WordsProtectionDataResponse wordsProtectionDataResponse = jObject.ToObject<WordsProtectionDataResponse>();
                return wordsProtectionDataResponse;
            }


            /// <summary>
            /// Protect document.	
            /// </summary>
            /// <param name="name">The file name.</param>
            /// <param name="filename">Result name of the document after the operation. If this parameter is omitted then result of the operation will be saved as the source document</param>
            /// <param name="folder">The document folder.</param>
            /// <param name="storage">The document storage.</param>
            public void ProtectDocument(string name, string filename, WordsProtectionRequest protectionRequest, string folder, string storage = "")
            {
                // PUT 	words/{name}/protection?appSid={appSid}&filename={filename}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"words/{0}/protection?filename={1}&storage={2}&folder={3}",
                                                name, filename, storage, folder);

                ServiceController.Put(apiUrl, AppSid, AppKey, JsonConvert.SerializeObject(protectionRequest));
            }


            /// <summary>
            /// Unprotect document.	
            /// </summary>
            /// <param name="name">The file name.</param>
            /// <param name="filename">Result name of the document after the operation. If this parameter is omitted then result of the operation will be saved as the source document</param>
            /// <param name="folder">The document folder.</param>
            /// <param name="storage">The document storage.</param>
            public void UnprotectDocument(string name, string filename, WordsProtectionRequest protectionRequest, string folder, string storage = "")
            {
                // DELETE 	words/{name}/protection?appSid={appSid}&filename={filename}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"words/{0}/protection?filename={1}&storage={2}&folder={3}",
                                                name, filename, storage, folder);

                ServiceController.Delete(apiUrl, AppSid, AppKey, JsonConvert.SerializeObject(protectionRequest));
            }


            /// <summary>
            /// Change document protection.	
            /// </summary>
            /// <param name="name">The file name.</param>
            /// <param name="filename">Result name of the document after the operation. If this parameter is omitted then result of the operation will be saved as the source document</param>
            /// <param name="folder">The document folder.</param>
            /// <param name="storage">The document storage.</param>
            public void ChangeDocumentProtection(string name, string filename, WordsProtectionRequest protectionRequest, string folder, string storage = "")
            {
                // POST 	words/{name}/protection?appSid={appSid}&filename={filename}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"words/{0}/protection?filename={1}&storage={2}&folder={3}",
                                                name, filename, storage, folder);

                ServiceController.Post(apiUrl, AppSid, AppKey, JsonConvert.SerializeObject(protectionRequest));
            }

        }

        public class WordsDocumentSaveAsClass
        {
            public string AppKey { get; set; }
            public string AppSid { get; set; }

            internal WordsDocumentSaveAsClass(string appSid, string appKey) { AppSid = appSid; AppKey = appKey; }

            /// <summary>
            /// Convert document to tiff with detailed settings and save result to storage.	
            /// </summary>
            /// <param name="name">The file name.</param>
            /// <param name="resultFile">Required parameter. Name of the resulting image. e.g. outFile.tiff The file will be saved in same folder as source file</param>
            /// <param name="useAntiAliasing">Optional, default is false.</param>
            /// <param name="useHighQualityRendering">Optional, default is false.</param>
            /// <param name="imageBrightness">Optional, from  0.0 (dimmest) to 1.0 (brightest).</param>
            /// <param name="imageColorMode">Optional, default is None, possible values are: None (preserves color), Grayscale, BlackAndWhite.</param>
            /// <param name="imageContrast">Optional, from 0.0 (the least contrast) to 1.0 (the greatest contrast).</param>
            /// <param name="numeralFormat">Optional, supported values are: European, ArabicIndic, EasternArabicIndic, Context, System.</param>
            /// <param name="pageCount">Optional, number of page to save. Can be used to save just a part of document. Default 0 means all pages</param>
            /// <param name="pageIndex">Optional, page index to start exporting (from 0). Can be used to save just a part of document.</param>
            /// <param name="paperColor">Optional, image background color. Well known or RGB (e.g. r0g255b128) color.</param>
            /// <param name="pixelFormat">Optional, specifies pixel format for generated image. Accepted values are: Format16BppRgb555, Format16BppRgb565, Format16BppArgb1555, Format24BppRgb, Format32BppRgb, Format32BppArgb, Format32BppPArgb, Format48BppRgb, Format64BppArgb, Format64BppPArgb.</param>
            /// <param name="resolution">Optional, default is 96 DPI.</param>
            /// <param name="scale">Optional, zoom factor.</param>
            /// <param name="tiffCompression">Optional, Tiff compression, possible values are: None, Rle, Lzw, Ccitt3, Ccitt4.</param>
            /// <param name="dmlRenderingMode">Optional, default is Fallback, possible values are: Fallback, DrawingML.</param>
            /// <param name="dmlEffectsRenderingMode">Optional, default is Simplified, possible values are: Simplified, None, Fine</param>
            /// <param name="tiffBinarizationMethod">Optional, default is Threshold. Optional values are: FloydSteinbergDithering, Threshold</param>
            /// <param name="folder">The document folder.</param>
            /// <param name="storage">The document storage.</param>
            public void ConvertDocumentToTiff(string name, string resultFile, string folder, bool useAntiAliasing = false, bool useHighQualityRendering = false, double imageBrightness = 0.5, WordTiffImageColorMode imageColorMode = WordTiffImageColorMode.None, double imageContrast = 0.9, WordTiffNumeralFormat numeralFormat = WordTiffNumeralFormat.System, int pageCount = 0, int pageIndex = 0, string paperColor = "", WordTiffPixelFormat pixelFormat = WordTiffPixelFormat.Format16BppRgb555, double resolution = 96, double scale = 0.0, TiffCompression tiffCompression = TiffCompression.None, DmlRenderingMode dmlRenderingMode = DmlRenderingMode.Fallback, DmlEffectsRenderingMode dmlEffectsRenderingMode = DmlEffectsRenderingMode.Simplified, TiffBinarizationMethod tiffBinarizationMethod = TiffBinarizationMethod.Threshold, string storage = "")
            {
                // PUT 	words/{name}/SaveAs/tiff?appSid={appSid}&resultFile={resultFile}&useAntiAliasing={useAntiAliasing}&useHighQualityRendering={useHighQualityRendering}&imageBrightness={imageBrightness}&imageColorMode={imageColorMode}&imageContrast={imageContrast}&numeralFormat={numeralFormat}&pageCount={pageCount}&pageIndex={pageIndex}&paperColor={paperColor}&pixelFormat={pixelFormat}&resolution={resolution}&scale={scale}&tiffCompression={tiffCompression}&dmlRenderingMode={dmlRenderingMode}&dmlEffectsRenderingMode={dmlEffectsRenderingMode}&tiffBinarizationMethod={tiffBinarizationMethod}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"words/{0}/SaveAs/tiff?resultFile={1}&useAntiAliasing={2}&useHighQualityRendering={3}&imageBrightness={4}&imageColorMode={5}&imageContrast={6}&numeralFormat={7}&pageIndex={8}&paperColor={9}&pixelFormat={10}&resolution={11}&dmlRenderingMode={12}&dmlEffectsRenderingMode={13}&tiffBinarizationMethod={14}&storage={15}&folder={16}",
                                                name, resultFile, useAntiAliasing, useHighQualityRendering, imageBrightness, imageColorMode, imageContrast, numeralFormat, pageIndex, paperColor, pixelFormat, resolution, dmlRenderingMode, dmlEffectsRenderingMode, tiffBinarizationMethod, storage, folder);

                if (pageCount > 0) apiUrl += string.Format("&pageCount={0}", pageCount);
                if (scale > 0) apiUrl += string.Format("&scale={0}", scale);
                if (tiffCompression != TiffCompression.None) apiUrl += string.Format("&tiffCompression={0}", tiffCompression);

                ServiceController.Put(apiUrl, AppSid, AppKey);
            }


            /// <summary>
            /// Convert document to tiff with detailed settings and save result to storage.	
            /// </summary>
            /// <param name="name">The file name. e.g. MyFolder/outFile.tiff</param>
            /// <param name="saveOptionsXML">Please check http://www.aspose.com/docs/display/wordscloud/saveAs+%28Controller+resource%29 for SaveOptions and corresponding xml</param>
            /// <param name="folder">The document folder.</param>
            /// <param name="storage">The document storage.</param>
            public void ConvertDocument(string name, string saveOptionsXML, string folder, string storage = "")
            {
                // POST 	words/{name}/SaveAs?appSid={appSid}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"words/{0}/SaveAs?storage={1}&folder={2}",
                                                name, storage, folder);

                ServiceController.Post(apiUrl, AppSid, AppKey, saveOptionsXML);
            }
        }

        public class WordsDocumentStatisticsClass
        {
            public string AppKey { get; set; }
            public string AppSid { get; set; }

            internal WordsDocumentStatisticsClass(string appSid, string appKey) { AppSid = appSid; AppKey = appKey; }

            /// <summary>
            /// Read document statistics.	
            /// </summary>
            /// <param name="name">The file name.</param>
            /// <param name="folder">The document folder.</param>
            /// <param name="storage">The document storage.</param>
            public WordsStatDataResponse ReadDocumentStatistics(string name, string folder, string storage = "")
            {
                // GET 	words/{name}/statistics?appSid={appSid}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"words/{0}/statistics?storage={1}&folder={2}",
                                                name, storage, folder);

                JObject jObject = JObject.Parse(ServiceController.Get(apiUrl, AppSid, AppKey));
                WordsStatDataResponse WordsStatDataResponse = jObject.ToObject<WordsStatDataResponse>();
                return WordsStatDataResponse;
            }
        }

        public class WordsDocumentWatermarkClass
        {
            public string AppKey { get; set; }
            public string AppSid { get; set; }

            internal WordsDocumentWatermarkClass(string appSid, string appKey) { AppSid = appSid; AppKey = appKey; }

            /// <summary>
            /// Insert document watermark image.	
            /// </summary>
            /// <param name="name">The file name.</param>
            /// <param name="filename">Result name of the document after the operation. If this parameter is omitted then result of the operation will be saved as the source document</param>
            /// <param name="rotationAngle">The watermark rotation angle.</param>
            /// <param name="image">The image file server full name. It can be local image path (c:\watermark.jpg) or cloud storage Folder\watermark.jpg</param>
            /// <param name="folder">The document folder.</param>
            /// <param name="storage">The document storage.</param>
            public void InsertDocumentWatermarkImage(string name, string filename, double rotationAngle, string image, string folder, string storage = "")
            {
                // POST 	words/{name}/watermark/insertImage?appSid={appSid}&filename={filename}&rotationAngle={rotationAngle}&image={image}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"words/{0}/watermark/insertImage?filename={1}&rotationAngle={2}&image={3}&storage={4}&folder={5}",
                                                name, filename, rotationAngle, (image.Contains(@":\") ? string.Empty : image), storage, folder);

                if (!string.IsNullOrEmpty(image) && Directory.Exists(Path.GetDirectoryName(image)))
                {
                    ServiceController.Post(apiUrl, AppSid, AppKey, File.ReadAllBytes(image));
                }
                else
                {
                    ServiceController.Post(apiUrl, AppSid, AppKey);
                }
            }

            /// <summary>
            /// Insert document watermark text.	
            /// </summary>
            /// <param name="name">The file name.</param>
            /// <param name="filename">Result name of the document after the operation. If this parameter is omitted then result of the operation will be saved as the source document</param>
            /// <param name="text">The text to insert.</param>
            /// <param name="rotationAngle">The text rotation angle.</param>
            /// <param name="folder">The document folder.</param>
            /// <param name="storage">The document storage.</param>
            public void InsertDocumentWatermarkText(string name, string text, double rotationAngle, string filename, string folder, string storage = "")
            {
                // POST 	words/{name}/watermark/insertText?appSid={appSid}&filename={filename}&text={text}&rotationAngle={rotationAngle}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"words/{0}/watermark/insertText?filename={1}&text={2}&rotationAngle={3}&storage={4}&folder={5}",
                                                name, filename, text, rotationAngle, storage, folder);

                ServiceController.Post(apiUrl, AppSid, AppKey);

            }

            /// <summary>
            /// Delete watermark (for deleting last watermark from the document).	
            /// </summary>
            /// <param name="name">The file name.</param>
            /// <param name="filename">Result name of the document after the operation. If this parameter is omitted then result of the operation will be saved as the source document</param>
            /// <param name="folder">The document folder.</param>
            /// <param name="storage">The document storage.</param>
            public void DeleteWatermark(string name, string filename, string folder, string storage = "")
            {
                // DELETE 	words/{name}/watermark?appSid={appSid}&filename={filename}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"words/{0}/watermark?filename={1}&storage={2}&folder={3}",
                                                name, filename, storage, folder);

                ServiceController.Delete(apiUrl, AppSid, AppKey);
            }
        }

        public class WordsDrawingObjectsClass
        {
            public string AppKey { get; set; }
            public string AppSid { get; set; }

            internal WordsDrawingObjectsClass(string appSid, string appKey) { AppSid = appSid; AppKey = appKey; }


            /// <summary>
            /// Read document drawing objects common info.	
            /// </summary>
            /// <param name="name">The file name.</param>
            /// <param name="folder">The document folder.</param>
            /// <param name="storage">The document storage.</param>
            public WordsDrawingObjectsResponse ReadDocumentDrawingObjectsCommonInfo(string name, string folder, string storage = "")
            {
                // GET 	words/{name}/drawingObjects?appSid={appSid}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"words/{0}/drawingObjects?storage={1}&folder={2}",
                                                name, storage, folder);

                JObject jObject = JObject.Parse(ServiceController.Get(apiUrl, AppSid, AppKey));
                WordsDrawingObjectsResponse wordsDrawingObjectsResponse = jObject.ToObject<WordsDrawingObjectsResponse>();
                return wordsDrawingObjectsResponse;
            }

            /// <summary>
            /// Read document drawing object common info by its index
            /// </summary>
            /// <param name="name">The file name.</param>
            /// <param name="objectIndex">The drawing object index.</param>
            /// <param name="folder">The document folder.</param>
            /// <param name="storage">The document storage.</param>
            public WordsDrawingObjectResponse ReadDrawingObjectCommonInfoByItsIndex(string name, int objectIndex, string folder, string storage = "")
            {
                // GET 	words/{name}/drawingObjects/{objectIndex}?appSid={appSid}&format={format}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"words/{0}/drawingObjects/{1}?storage={2}&folder={3}",
                                                name, objectIndex, storage, folder);

                JObject jObject = JObject.Parse(ServiceController.Get(apiUrl, AppSid, AppKey));
                WordsDrawingObjectResponse wordsDrawingObjectResponse = jObject.ToObject<WordsDrawingObjectResponse>();
                return wordsDrawingObjectResponse;
            }

            /// <summary>
            /// Read document drawing object common info by its index or convert to format specified.	
            /// </summary>
            /// <param name="name">The file name.</param>
            /// <param name="objectIndex">The drawing object index.</param>
            /// <param name="format">The format to convert. One of TIFF, PNG, BMP, JPG/JPEG formats. </param>
            /// <param name="outPath">Path to save result. It must be local (e.g. c:\out.tiff) path.</param>
            /// <param name="folder">The document folder.</param>
            /// <param name="storage">The document storage.</param>
            public void ConvertToFormatSpecified(string name, int objectIndex, WordDrawingObjectsFormat format, string outPath, string folder, string storage = "")
            {
                // GET 	words/{name}/drawingObjects/{objectIndex}?appSid={appSid}&format={format}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"words/{0}/drawingObjects/{1}?format={2}&storage={3}&folder={4}",
                                                name, objectIndex, format, storage, folder);

                if (!string.IsNullOrEmpty(outPath) && Directory.Exists(Path.GetDirectoryName(outPath)))
                {
                    using (Stream responseStream = ServiceController.GetStream(apiUrl, AppSid, AppKey))
                    using (Stream file = File.OpenWrite(outPath))
                    {
                        ServiceController.CopyStream(responseStream, file);
                    }
                }
            }

            /// <summary>
            /// Read drawing object image data.	
            /// </summary>
            /// <param name="name">The file name.</param>
            /// <param name="objectIndex">The drawing object index.</param>
            /// <param name="folder">The document folder.</param>
            /// <param name="outPath">Path to save result. It must be local (e.g. c:\out.tiff) path.</param>
            /// <param name="storage">The document storage.</param>
            public void ReadDrawingObjectImageData(string name, int objectIndex, string folder, string outPath, string storage = "")
            {
                // GET 	words/{name}/drawingObjects/{objectIndex}/imageData?appSid={appSid}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"words/{0}/drawingObjects/{1}/imageData?storage={2}&folder={3}",
                                                name, objectIndex, storage, folder);

                if (!string.IsNullOrEmpty(outPath) && Directory.Exists(Path.GetDirectoryName(outPath)))
                {
                    using (Stream responseStream = ServiceController.GetStream(apiUrl, AppSid, AppKey))
                    using (Stream file = File.OpenWrite(outPath))
                    {
                        ServiceController.CopyStream(responseStream, file);
                    }
                }
            }

            /// <summary>
            /// Get drawing object OLE data.	
            /// </summary>
            /// <param name="name">The file name.</param>
            /// <param name="objectIndex">The drawing object index.</param>
            /// <param name="folder">The document folder.</param>
            /// <param name="storage">The document storage.</param>
            public void GetDrawingObjectOleData(string name, int objectIndex, string folder, string outPath, string storage = "")
            {
                // GET 	words/{name}/drawingObjects/{objectIndex}/oleData?appSid={appSid}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"words/{0}/drawingObjects/{1}/oleData?storage={2}&folder={3}",
                                                name, objectIndex, storage, folder);

                if (!string.IsNullOrEmpty(outPath) && Directory.Exists(Path.GetDirectoryName(outPath)))
                {
                    using (Stream responseStream = ServiceController.GetStream(apiUrl, AppSid, AppKey))
                    using (Stream file = File.OpenWrite(outPath))
                    {
                        ServiceController.CopyStream(responseStream, file);
                    }
                }
            }

        }

        public class WordsHeadersFootersClass
        {
            public string AppKey { get; set; }
            public string AppSid { get; set; }

            internal WordsHeadersFootersClass(string appSid, string appKey) { AppSid = appSid; AppKey = appKey; }

            /// <summary>
            /// Delete document headers and footers.	
            /// </summary>
            /// <param name="name">The file name.</param>
            /// <param name="headersFootersTypes"></param>
            /// <param name="filename">Result name of the document after the operation. If this parameter is omitted then result of the operation will be saved as the source document</param>
            /// <param name="folder">The document folder.</param>
            /// <param name="storage">The document storage.</param>
            public void DeleteDocumentHeadersAndFooters(string name, string headersFootersTypes, string filename, string folder, string storage = "")
            {
                // DELETE 	words/{name}/headersfooters?appSid={appSid}&headersFootersTypes={headersFootersTypes}&filename={filename}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"words/{0}/headersfooters?headersFootersTypes={1}&filename={2}&storage={3}&folder={4}",
                                                name, headersFootersTypes, filename, storage, folder);


                ServiceController.Delete(apiUrl, AppSid, AppKey);
            }
        }

        public class WordsHyperlinksClass
        {
            public string AppKey { get; set; }
            public string AppSid { get; set; }

            internal WordsHyperlinksClass(string appSid, string appKey) { AppSid = appSid; AppKey = appKey; }

            /// <summary>
            /// Read document hyperlinks common info.	
            /// </summary>
            /// <param name="name">The file name.</param>
            /// <param name="folder">The document folder.</param>
            /// <param name="storage">The document storage.</param>
            public WordsHyperlinksResponse ReadDocumentHyperlinksCommonInfo(string name, string folder, string storage = "")
            {
                // GET 	words/{name}/hyperlinks?appSid={appSid}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"words/{0}/hyperlinks?storage={1}&folder={2}",
                                                name, storage, folder);

                JObject jObject = JObject.Parse(ServiceController.Get(apiUrl, AppSid, AppKey));
                WordsHyperlinksResponse wordsHyperlinksResponse = jObject.ToObject<WordsHyperlinksResponse>();
                return wordsHyperlinksResponse;
            }

            /// <summary>
            /// Read document hyperlink by its index.	
            /// </summary>
            /// <param name="name">The file name.</param>
            /// <param name="hyperlinkIndex">The hyperlink index.</param>
            /// <param name="folder">The document folder.</param>
            /// <param name="storage">The document storage.</param>
            public WordsHyperlinkResponse ReadDocumentHyperlinkByItsIndex(string name, int hyperlinkIndex, string folder, string storage = "")
            {
                // GET 	words/{name}/hyperlinks/{hyperlinkIndex}?appSid={appSid}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"words/{0}/hyperlinks/{1}?storage={2}&folder={3}",
                                                name, hyperlinkIndex, storage, folder);

                JObject jObject = JObject.Parse(ServiceController.Get(apiUrl, AppSid, AppKey));
                WordsHyperlinkResponse wordsHyperlinkResponse = jObject.ToObject<WordsHyperlinkResponse>();
                return wordsHyperlinkResponse;
            }
        }

        public class WordsMailMergeClass
        {
            public string AppKey { get; set; }
            public string AppSid { get; set; }

            internal WordsMailMergeClass(string appSid, string appKey) { AppSid = appSid; AppKey = appKey; }

            /// <summary>
            /// Read document field names.	
            /// </summary>
            /// <param name="name">The file name.</param>
            /// <param name="name">Use NonMerge fields</param>            
            /// <param name="folder">The document folder.</param>
            /// <param name="storage">The document storage.</param>
            public WordsFieldNamesResponse ReadDocumentFieldNames(string name, bool useNonMergeFields, string folder, string storage = "")
            {
                // GET 	words/{name}/mailMergeFieldNames?appSid={appSid}&useNonMergeFields={useNonMergeFields}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"words/{0}/mailMergeFieldNames?storage={1}&folder={2}",
                                                name, storage, folder);

                JObject jObject = JObject.Parse(ServiceController.Get(apiUrl, AppSid, AppKey));
                WordsFieldNamesResponse wordsFieldNamesResponse = jObject.ToObject<WordsFieldNamesResponse>();
                return wordsFieldNamesResponse;
            }

            /// <summary>
            /// Execute document mail merge operation.	
            /// </summary>
            /// <param name="name">The file name.</param>
            /// <param name="withRegions">With regions flag.</param>
            /// <param name="mailMergeDataFile">mailMerge Data File</param>
            /// <param name="cleanup">Clean up options.</param>
            /// <param name="filename">Result name of the document after the operation. If this parameter is omitted then result of the operation will be saved as the source document</param>
            /// <param name="folder">The document folder.</param>
            /// <param name="storage">The document storage.</param>
            public WordsDocumentResponse ExecuteDocumentMailMergeOperation(string name, bool withRegions, string mailMergeDataFile, string cleanup, string filename, string folder, string storage = "")
            {
                // POST 	words/{name}/executeMailMerge/{withRegions}?appSid={appSid}&mailMergeDataFile={mailMergeDataFile}&cleanup={cleanup}&filename={filename}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"words/{0}/executeMailMerge/{1}?mailMergeDataFile={2}&cleanup={3}&filename={4}&storage={5}&folder={6}",
                                                name, withRegions, mailMergeDataFile, cleanup, filename, storage, folder);

                JObject jObject = JObject.Parse(ServiceController.Post(apiUrl, AppSid, AppKey));
                WordsDocumentResponse wordsDocumentResponse = jObject.ToObject<WordsDocumentResponse>();
                return wordsDocumentResponse;

            }
        }

        public class WordsParagraphClass
        {
            public string AppKey { get; set; }
            public string AppSid { get; set; }

            internal WordsParagraphClass(string appSid, string appKey) { AppSid = appSid; AppKey = appKey; }

            /// <summary>
            /// Return a list of paragraphs that are contained in the document.	
            /// </summary>
            /// <param name="name">The file name.</param>
            /// <param name="folder">The document folder.</param>
            /// <param name="storage">The document storage.</param>
            public WordsParagraphsResponse GetParagraphs(string name, string folder, string storage = "")
            {
                // GET 	words/{name}/paragraphs?appSid={appSid}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"words/{0}/paragraphs?storage={1}&folder={2}",
                                                name, storage, folder);

                JObject jObject = JObject.Parse(ServiceController.Get(apiUrl, AppSid, AppKey));
                WordsParagraphsResponse wordsParagraphsResponse = jObject.ToObject<WordsParagraphsResponse>();
                return wordsParagraphsResponse;
            }

            /// <summary>
            /// Get paragraph by index
            /// </summary>
            /// <param name="name">The file name.</param>
            /// <param name="index">Paragraph index</param>
            /// <param name="folder">The document folder.</param>
            /// <param name="storage">The document storage.</param>
            public WordsParagraphResponse GetParagraphByIndex(string name, int index, string folder, string storage = "")
            {
                // GET 	words/{name}/paragraphs/{index}?appSid={appSid}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"words/{0}/paragraphs/{1}?storage={2}&folder={3}",
                                                name, index, storage, folder);

                JObject jObject = JObject.Parse(ServiceController.Get(apiUrl, AppSid, AppKey));
                WordsParagraphResponse wordsParagraphResponse = jObject.ToObject<WordsParagraphResponse>();
                return wordsParagraphResponse;
            }

            /// <summary>
            /// Get paragraph by index and by run index
            /// </summary>
            /// <param name="name">The file name.</param>
            /// <param name="index">Paragraph index</param>
            /// <param name="runIndex">Run index</param>
            /// <param name="folder">The document folder.</param>
            /// <param name="storage">The document storage.</param>
            public WordsRunResponse GetParagraphByIndexAndRun(string name, int index, int runIndex, string folder, string storage = "")
            {
                // GET 	words/{name}/paragraphs/{index}/runs/{runIndex}?appSid={appSid}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"words/{0}/paragraphs/{1}/runs/{2}?storage={3}&folder={4}",
                                                name, index, runIndex, storage, folder);

                JObject jObject = JObject.Parse(ServiceController.Get(apiUrl, AppSid, AppKey));
                WordsRunResponse wordsRunResponse = jObject.ToObject<WordsRunResponse>();
                return wordsRunResponse;
            }

            /// <summary>
            /// Get document font by paragraph and run
            /// </summary>
            /// <param name="name">The file name.</param>
            /// <param name="index">Paragraph index</param>
            /// <param name="runIndex">Run index</param>
            /// <param name="folder">The document folder.</param>
            /// <param name="storage">The document storage.</param>
            public WordsFontResponse GetDocumentFontByParagraphAndRun(string name, int index, int runIndex, string folder, string storage = "")
            {
                // GET 	words/{name}/paragraphs/{index}/runs/{runIndex}/font?appSid={appSid}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"words/{0}/paragraphs/{1}/runs/{2}/font?storage={3}&folder={4}",
                                                name, index, runIndex, storage, folder);

                JObject jObject = JObject.Parse(ServiceController.Get(apiUrl, AppSid, AppKey));
                WordsFontResponse WordsFontResponse = jObject.ToObject<WordsFontResponse>();
                return WordsFontResponse;
            }

            /// <summary>
            /// This resource represents one of the paragraphs contained in the document.	
            /// </summary>
            /// <param name="name">The file name.</param>
            /// <param name="index">Paragraph index</param>
            /// <param name="runIndex">Run index</param>
            /// <param name="folder">The document folder.</param>
            /// <param name="filename">Result name of the document after the operation. If this parameter is omitted then result of the operation will be saved as the source document</param>
            /// <param name="storage">The document storage.</param>
            public void UpdateDocumentFont(string name, int index, int runIndex, string filename, WordsFont wordFont, string folder, string storage = "")
            {
                // POST 	words/{name}/paragraphs/{index}/runs/{runIndex}/font?appSid={appSid}&storage={storage}&folder={folder}&filename={filename} 

                string apiUrl = string.Format(@"words/{0}/paragraphs/{1}/runs/{2}/font?storage={3}&folder={4}&filename={5}",
                                                name, index, runIndex, storage, folder, filename);

                ServiceController.Post(apiUrl, AppSid, AppKey, JsonConvert.SerializeObject(wordFont));
            }
        }

        public class WordsRevisionsClass
        {
            public string AppKey { get; set; }
            public string AppSid { get; set; }

            internal WordsRevisionsClass(string appSid, string appKey) { AppSid = appSid; AppKey = appKey; }

            /// <summary>
            /// Accept all revisions in document	
            /// </summary>
            /// <param name="name">The file name.</param>
            /// <param name="filename">Result name of the document after the operation. If this parameter is omitted then result of the operation will be saved as the source document</param>
            /// <param name="folder">The document folder.</param>
            /// <param name="storage">The document storage.</param>
            public WordRevisionResponse AcceptAllRevisionsInDocument(string name, string filename, string folder, string storage = "")
            {
                // POST 	words/{name}/revisions/acceptAll?appSid={appSid}&filename={filename}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"words/{0}/revisions/acceptAll?filename={1}&storage={2}&folder={3}",
                                                name, filename, storage, folder);

                JObject jObject = JObject.Parse(ServiceController.Post(apiUrl, AppSid, AppKey));
                WordRevisionResponse wordRevisionResponse = jObject.ToObject<WordRevisionResponse>();
                return wordRevisionResponse;

            }

            /// <summary>
            /// Reject all revisions in document	
            /// </summary>
            /// <param name="name">The file name.</param>
            /// <param name="filename">Result name of the document after the operation. If this parameter is omitted then result of the operation will be saved as the source document</param>
            /// <param name="folder">The document folder.</param>
            /// <param name="storage">The document storage.</param>
            public WordRevisionResponse RejectAllRevisionsInDocument(string name, string filename, string folder, string storage = "")
            {
                // POST 	words/{name}/revisions/rejectAll?appSid={appSid}&filename={filename}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"words/{0}/revisions/rejectAll?filename={1}&storage={2}&folder={3}",
                                                name, filename, storage, folder);

                JObject jObject = JObject.Parse(ServiceController.Post(apiUrl, AppSid, AppKey));
                WordRevisionResponse wordRevisionResponse = jObject.ToObject<WordRevisionResponse>();
                return wordRevisionResponse;

            }

        }

        public class WordsSectionsClass
        {
            public string AppKey { get; set; }
            public string AppSid { get; set; }

            internal WordsSectionsClass(string appSid, string appKey) { AppSid = appSid; AppKey = appKey; }

            /// <summary>
            /// Return a list of sections that are contained in the document.
            /// </summary>
            /// <param name="name">The file name.</param>
            /// <param name="folder">The document folder.</param>
            /// <param name="storage">The document storage.</param>
            /// <returns>WordsSectionsResponse object</returns>
            public WordsSectionsResponse GetSectionsList(string name, string folder, string storage = "")
            {
                // GET 	words/{name}/sections?appSid={appSid}&storage={storage}&folder={folder} 	

                string apiUrl = string.Format(@"words/{0}/sections?storage={1}&folder={2}",
                                                name, storage, folder);

                JObject jObject = JObject.Parse(ServiceController.Get(apiUrl, AppSid, AppKey));
                WordsSectionsResponse wordsSectionsResponse = jObject.ToObject<WordsSectionsResponse>();
                return wordsSectionsResponse;
            }

            /// <summary>
            /// Get document section by index.
            /// </summary>
            /// <param name="name">The file name.</param>
            /// <param name="sectionIndex">Section index</param>
            /// <param name="folder">The document folder.</param>
            /// <param name="storage">The document storage.</param>
            /// <returns>WordsSectionResponse object</returns>
            public WordsSectionResponse GetSectionByIndex(string name, int sectionIndex, string folder, string storage = "")
            {
                // GET 	words/{name}/sections/{sectionIndex}?appSid={appSid}&storage={storage}&folder={folder} 	

                string apiUrl = string.Format(@"words/{0}/sections/{1}?storage={2}&folder={3}",
                                                name, sectionIndex, storage, folder);

                JObject jObject = JObject.Parse(ServiceController.Get(apiUrl, AppSid, AppKey));
                WordsSectionResponse wordsSectionResponse = jObject.ToObject<WordsSectionResponse>();
                return wordsSectionResponse;
            }

            /// <summary>
            /// Get page setup of section.
            /// </summary>
            /// <param name="name">The file name.</param>
            /// <param name="sectionIndex">Section index</param>
            /// <param name="folder">The document folder.</param>
            /// <param name="storage">The document storage.</param>
            /// <returns>PageSetupResponse object</returns>
            public PageSetupResponse GetPageSetupOfSection(string name, int sectionIndex, string folder, string storage = "")
            {
                // GET 	words/{name}/sections/{sectionIndex}/pageSetup?appSid={appSid}&storage={storage}&folder={folder} 	

                string apiUrl = string.Format(@"words/{0}/sections/{1}/pageSetup?storage={2}&folder={3}",
                                                name, sectionIndex, storage, folder);

                JObject jObject = JObject.Parse(ServiceController.Get(apiUrl, AppSid, AppKey));
                PageSetupResponse pageSetupResponse = jObject.ToObject<PageSetupResponse>();
                return pageSetupResponse;
            }

            /// <summary>
            /// Update page setup of section.
            /// </summary>
            /// <param name="name">The file name.</param>
            /// <param name="sectionIndex">Section index</param>
            /// <param name="filename">Result name of the document after the operation. If this parameter is omitted then result of the operation will be saved as the source document.</param>
            /// <param name="pageSetup">Page setup properties dto</param>
            /// <param name="folder">The document folder.</param>
            /// <param name="storage">The document storage.</param>
            public void UpdatePageSetupOfSection(string name, int sectionIndex, string filename, WordsPageSetup pageSetup, string folder, string storage = "")
            {
                // POST 	words/{name}/sections/{sectionIndex}/pageSetup?appSid={appSid}&storage={storage}&folder={folder}&filename={filename} 	

                string apiUrl = string.Format(@"words/{0}/sections/{1}/pageSetup?storage={2}&folder={3}&filename={4}",
                                                               name, sectionIndex, storage, folder, filename);

                ServiceController.Post(apiUrl, AppSid, AppKey, JsonConvert.SerializeObject(pageSetup));
            }
        }

        public class WordsTextItemsClass
        {
            public string AppKey { get; set; }
            public string AppSid { get; set; }

            internal WordsTextItemsClass(string appSid, string appKey) { AppSid = appSid; AppKey = appKey; }

            /// <summary>
            /// Read document text items.	
            /// </summary>
            /// <param name="name">The file name.</param>
            /// <param name="folder">The document folder.</param>
            /// <param name="storage">The document storage.</param>
            public WordsTextItemsResponse ReadDocumentTextItems(string name, string folder, string storage = "")
            {
                // GET 	words/{name}/textItems?appSid={appSid}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"words/{0}/textItems?storage={1}&folder={2}",
                                                name, storage, folder);

                JObject jObject = JObject.Parse(ServiceController.Get(apiUrl, AppSid, AppKey));
                WordsTextItemsResponse wordsTextItemsResponse = jObject.ToObject<WordsTextItemsResponse>();
                return wordsTextItemsResponse;

            }

            /// <summary>
            /// Replace document text.	
            /// </summary>
            /// <param name="name">The file name.</param>
            /// <param name="filename">Result name of the document after the operation. If this parameter is omitted then result of the operation will be saved as the source document</param>
            /// <param name="replaceText">WordsReplaceText object with the replace operation settings.</param>
            /// <param name="folder">The document folder.</param>
            /// <param name="storage">The document storage.</param>
            public void ReplaceDocumentText(string name, string filename, WordsReplaceText replaceText, string folder, string storage = "")
            {
                // POST 	words/{name}/replaceText?appSid={appSid}&filename={filename}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"words/{0}/replaceText?filename={1}&storage={2}&folder={3}",
                                                name, filename, storage, folder);

                ServiceController.Post(apiUrl, AppSid, AppKey, JsonConvert.SerializeObject(replaceText));
            }
        }
    }


    public class WordsDocumentProperties
    {
        public List<WordsDocumentProperty> List { get; set; }
        public Link link { get; set; }
    }

    public class WordsDocument
    {
        public List<Link> Links { get; set; }
        public string FileName { get; set; }
        public int SourceFormat { get; set; }
        public bool IsEncrypted { get; set; }
        public bool IsSigned { get; set; }
        public WordsDocumentProperties DocumentProperties { get; set; }
    }

    public class WordsDocumentResponse
    {
        public WordsDocument Document { get; set; }
        public int Code { get; set; }
        public string Status { get; set; }
    }

    public class WordDocumentEntry
    {
        /// <summary>
        /// WordDocumentEntry
        /// </summary>
        /// <param name="href">Document path</param>
        /// <param name="importFormatMode">KeepSourceFormatting or UseDestinationStyles</param>
        public WordDocumentEntry(string href, WordImportFormatMode importFormatMode)
        {
            Href = href;
            ImportFormatMode = importFormatMode.ToString();
        }

        public string Href { get; set; }
        public string ImportFormatMode { get; set; }
    }

    public class WordDocumentEntryRequest
    {
        public WordDocumentEntryRequest() { DocumentEntries = new List<WordDocumentEntry>(); }
        public List<WordDocumentEntry> DocumentEntries { get; set; }
    }

    public class WordsSplitResult
    {
        public Link SourceDocument { get; set; }
        public List<Link> Pages { get; set; }
    }

    public class WordsSplitResultResponse
    {
        public WordsSplitResult SplitResult { get; set; }
        public int Code { get; set; }
        public string Status { get; set; }
    }

    public class WordsSaveResult
    {
        public Link DestDocument { get; set; }
        public List<object> AdditionalItems { get; set; }
    }

    public class WordsSaveResultResponse
    {
        public WordsSaveResult SaveResult { get; set; }
        public int Code { get; set; }
        public string Status { get; set; }
    }

    public class WordsBookmarkRequest
    {
        public WordsBookmarkRequest(string name, string text)
        {
            Name = name;
            Text = text;
        }

        public string Name { get; set; }
        public string Text { get; set; }
    }

    public class WordsBookmark
    {
        public string Name { get; set; }
        public string Text { get; set; }
        public Link link { get; set; }
    }

    public class WordsBookmarkResponse
    {
        public WordsBookmark Bookmark { get; set; }
        public int Code { get; set; }
        public string Status { get; set; }
    }

    public class WordsBookmarkList
    {
        public string Name { get; set; }
        public string Text { get; set; }
        public Link link { get; set; }
    }

    public class WordsBookmarks
    {
        public List<WordsBookmarkList> BookmarkList { get; set; }
        public Link link { get; set; }
    }

    public class WordsBookmarksResponse
    {
        public WordsBookmarks Bookmarks { get; set; }
        public int Code { get; set; }
        public string Status { get; set; }
    }

    public class WordsDocumentProperty
    {
        public string Text { get; set; }
        public string Name { get; set; }
        public string Value { get; set; }
        public bool BuiltIn { get; set; }
        public Link link { get; set; }
    }

    public class WordsDocumentPropertyResponse
    {
        public WordsDocumentProperty DocumentProperty { get; set; }
        public int Code { get; set; }
        public string Status { get; set; }
    }

    public class WordsDocumentPropertiesResponse
    {
        public WordsDocumentProperties DocumentProperties { get; set; }
        public int Code { get; set; }
        public string Status { get; set; }
    }

    public class WordsProtectionRequest
    {
        public WordsProtectionRequest(string password, string newPassword, ProtectionType protectionType)
        {
            Password = password;
            NewPassword = newPassword;
            ProtectionType = protectionType.ToString();
        }

        public string Password { get; set; }
        public string NewPassword { get; set; }
        public string ProtectionType { get; set; }
    }

    public class WordsProtectionData
    {
        public string ProtectionType { get; set; }
    }

    public class WordsProtectionDataResponse
    {
        public WordsProtectionData ProtectionData { get; set; }
        public Link DocumentLink { get; set; }
        public int Code { get; set; }
        public string Status { get; set; }
    }

    public class WordsPageStatData
    {
        public int PageNumber { get; set; }
        public int WordCount { get; set; }
        public int ParagraphCount { get; set; }
    }

    public class WordsStatData
    {
        public int WordCount { get; set; }
        public int ParagraphCount { get; set; }
        public int PageCount { get; set; }
        public List<WordsPageStatData> PageStatData { get; set; }
    }

    public class WordsStatDataResponse
    {
        public WordsStatData StatData { get; set; }
        public Link DocumentLink { get; set; }
        public int Code { get; set; }
        public string Status { get; set; }
    }

    public class WordsDrawingObject
    {
        public List<Link> RenderLinks { get; set; }
        public double Width { get; set; }
        public double Height { get; set; }
        public object OleDataLink { get; set; }
        public Link ImageDataLink { get; set; }
        public Link link { get; set; }
    }

    public class WordsDrawingObjectResponse
    {
        public WordsDrawingObject DrawingObject { get; set; }
        public int Code { get; set; }
        public string Status { get; set; }
    }

    public class WordDrawingObjectsLinkList
    {
        public Link link { get; set; }
    }

    public class WordsDrawingObjects
    {
        public List<WordDrawingObjectsLinkList> List { get; set; }
        public Link link { get; set; }
    }

    public class WordsDrawingObjectsResponse
    {
        public WordsDrawingObjects DrawingObjects { get; set; }
        public int Code { get; set; }
        public string Status { get; set; }
    }

    public class WordsHyperlink
    {
        public string DisplayText { get; set; }
        public string Value { get; set; }
        public Link link { get; set; }
    }

    public class WordsHyperlinkResponse
    {
        public WordsHyperlink Hyperlink { get; set; }
        public int Code { get; set; }
        public string Status { get; set; }
    }

    public class WordsHyperlinks
    {
        public List<WordsHyperlink> HyperlinkList { get; set; }
        public Link link { get; set; }
    }

    public class WordsHyperlinksResponse
    {
        public WordsHyperlinks Hyperlinks { get; set; }
        public int Code { get; set; }
        public string Status { get; set; }
    }

    public class WordsFieldNames
    {
        public List<string> Names { get; set; }
        public Link link { get; set; }
    }

    public class WordsFieldNamesResponse
    {
        public WordsFieldNames FieldNames { get; set; }
        public int Code { get; set; }
        public string Status { get; set; }
    }

    public class WordsBorder
    {
        public string LineStyle { get; set; }
        public double LineWidth { get; set; }
        public WordsColor Color { get; set; }
        public double DistanceFromText { get; set; }
        public bool Shadow { get; set; }
    }

    public class WordsColor
    {
        public string Web { get; set; }
        public int Alpha { get; set; }
    }

    public class WordsFont
    {
        public WordsFont() { }
        public bool AllCaps { get; set; }
        public bool Bidi { get; set; }
        public bool Bold { get; set; }
        public bool BoldBi { get; set; }
        public WordsBorder Border { get; set; }
        public WordsColor Color { get; set; }
        public bool ComplexScript { get; set; }
        public bool DoubleStrikeThrough { get; set; }
        public bool Emboss { get; set; }
        public bool Engrave { get; set; }
        public bool Hidden { get; set; }
        public WordsColor HighlightColor { get; set; }
        public bool Italic { get; set; }
        public bool ItalicBi { get; set; }
        public double Kerning { get; set; }
        public int LocaleId { get; set; }
        public int LocaleIdBi { get; set; }
        public int LocaleIdFarEast { get; set; }
        public string Name { get; set; }
        public string NameAscii { get; set; }
        public string NameBi { get; set; }
        public string NameFarEast { get; set; }
        public string NameOther { get; set; }
        public bool NoProofing { get; set; }
        public bool Outline { get; set; }
        public double Position { get; set; }
        public int Scaling { get; set; }
        public bool Shadow { get; set; }
        public double Size { get; set; }
        public double SizeBi { get; set; }
        public bool SmallCaps { get; set; }
        public double Spacing { get; set; }
        public bool StrikeThrough { get; set; }
        public string StyleIdentifier { get; set; }
        public string StyleName { get; set; }
        public bool Subscript { get; set; }
        public bool Superscript { get; set; }
        public string TextEffect { get; set; }
        public string Underline { get; set; }
        public WordsColor UnderlineColor { get; set; }
        public Link link { get; set; }
    }

    public class WordsFontResponse
    {
        public WordsFont Font { get; set; }
        public int Code { get; set; }
        public string Status { get; set; }
    }

    public class WordsRun
    {
        public string Text { get; set; }
        public Link link { get; set; }
    }

    public class WordsRunResponse
    {
        public WordsRun Run { get; set; }
        public int Code { get; set; }
        public string Status { get; set; }
    }

    public class ChildNode
    {
        public string Text { get; set; }
        public Link link { get; set; }
    }

    public class WordsParagraph
    {
        public List<ChildNode> ChildNodes { get; set; }
        public Link link { get; set; }
    }

    public class WordsParagraphResponse
    {
        public WordsParagraph Paragraph { get; set; }
        public int Code { get; set; }
        public string Status { get; set; }
    }

    public class WordsParagraphLinkList
    {
        public string Text { get; set; }
        public Link link { get; set; }
    }

    public class WordsParagraphs
    {
        public List<WordsParagraphLinkList> ParagraphLinkList { get; set; }
        public Link link { get; set; }
    }

    public class WordsParagraphsResponse
    {
        public WordsParagraphs Paragraphs { get; set; }
        public int Code { get; set; }
        public string Status { get; set; }
    }

    public class WordsPageSetup
    {
        public bool Bidi { get; set; }
        public bool BorderAlwaysInFront { get; set; }
        public string BorderAppliesTo { get; set; }
        public string BorderDistanceFrom { get; set; }
        public double BottomMargin { get; set; }
        public bool DifferentFirstPageHeaderFooter { get; set; }
        public int FirstPageTray { get; set; }
        public double FooterDistance { get; set; }
        public double Gutter { get; set; }
        public double HeaderDistance { get; set; }
        public double LeftMargin { get; set; }
        public int LineNumberCountBy { get; set; }
        public double LineNumberDistanceFromText { get; set; }
        public string LineNumberRestartMode { get; set; }
        public int LineStartingNumber { get; set; }
        public string Orientation { get; set; }
        public int OtherPagesTray { get; set; }
        public double PageHeight { get; set; }
        public string PageNumberStyle { get; set; }
        public int PageStartingNumber { get; set; }
        public double PageWidth { get; set; }
        public string PaperSize { get; set; }
        public bool RestartPageNumbering { get; set; }
        public double RightMargin { get; set; }
        public bool RtlGutter { get; set; }
        public string SectionStart { get; set; }
        public bool SuppressEndnotes { get; set; }
        public double TopMargin { get; set; }
        public string VerticalAlignment { get; set; }
        public Link link { get; set; }
    }

    public class PageSetupResponse
    {
        public WordsPageSetup PageSetup { get; set; }
        public int Code { get; set; }
        public string Status { get; set; }
    }

    public class WordsSection
    {
        public WordsParagraphs Paragraphs { get; set; }
        public WordsPageSetup PageSetup { get; set; }
        public Link link { get; set; }
    }

    public class WordsSectionResponse
    {
        public WordsSection Section { get; set; }
        public int Code { get; set; }
        public string Status { get; set; }
    }

    public class SectionLinkList
    {
        public Link link { get; set; }
    }

    public class WordsSections
    {
        public List<SectionLinkList> SectionLinkList { get; set; }
        public Link link { get; set; }
    }

    public class WordsSectionsResponse
    {
        public WordsSections Sections { get; set; }
        public int Code { get; set; }
        public string Status { get; set; }
    }

    public class WordRevisionResult
    {
        public Link Source { get; set; }
        public Link Dest { get; set; }
    }

    public class WordRevisionResponse
    {
        public WordRevisionResult Result { get; set; }
        public int Code { get; set; }
        public string Status { get; set; }
    }

    public class WordsReplaceText
    {
        public WordsReplaceText(string oldValue, string newValue, bool isMatchCase, bool isMatchWholeWord)
        {
            OldValue = oldValue; NewValue = newValue; IsMatchCase = IsMatchCase; IsMatchCase = isMatchCase;
        }

        public string OldValue { get; set; }
        public string NewValue { get; set; }
        public bool IsMatchCase { get; set; }
        public bool IsMatchWholeWord { get; set; }
    }

    public class WordsTextItems
    {
        public List<WordsDocumentProperty> List { get; set; }
        public Link link { get; set; }
    }

    public class WordsTextItemsResponse
    {
        public WordsTextItems TextItems { get; set; }
        public int Code { get; set; }
        public string Status { get; set; }
    }

    public class WordDocumentProperties
    {
        public List<object> List { get; set; }
        public Link link { get; set; }
    }

    public class WordDocument
    {
        public List<Link> Links { get; set; }
        public string FileName { get; set; }
        public int SourceFormat { get; set; }
        public bool IsEncrypted { get; set; }
        public bool IsSigned { get; set; }
        public DocumentProperties DocumentProperties { get; set; }
    }

    public class WordDocumentResponse
    {
        public WordDocument Document { get; set; }
        public int Code { get; set; }
        public string Status { get; set; }
    }

    public class PageNumberSettings
    {
        /// <summary>
        /// PageNumberSettings
        /// </summary>
        /// <param name="format">e.g. {PAGE} of {NUMPAGES}</param>
        /// <param name="alignment">left or right</param>
        /// <param name="istop"></param>
        /// <param name="setPageNo"></param>
        public PageNumberSettings(string format, string alignment, bool isTop, bool setPageNumberOnFirstPage)
        {
            Format = format; Alignment = alignment; IsTop = isTop; SetPageNumberOnFirstPage = setPageNumberOnFirstPage;
        }

        public string Format { get; set; }
        public string Alignment { get; set; }
        public bool IsTop { get; set; }
        public bool SetPageNumberOnFirstPage { get; set; }
    }
}
