using System;
using System.Collections.Generic;
using System.Text;
using Aspose.Cloud.Common;
using Newtonsoft.Json;
using Newtonsoft.Json.Linq;
using System.IO;

namespace Aspose.Cloud
{
    public class PDFService : BaseService
    {
        public PDFService(string appSid, string appKey)
            : base(appSid, appKey)
        {
            AppSid = appSid;
            AppKey = appKey;
            Annotations = new PDFAnnotationsClass(AppSid, AppKey);
            Attachments = new PDFAttachmentsClass(AppSid, AppKey);
            Barcodes = new PDFBarcodesClass(AppSid, AppKey);
            Bookmarks = new PDFBookmarksClass(AppSid, AppKey);
            AppendMerge = new PDFDocumentAppendMergeClass(AppSid, AppKey);
            SaveAs = new PDFDocumentSaveAsClass(AppSid, AppKey);
            Fields = new PDFFieldsClass(AppSid, AppKey);
            FragmentsAndSegments = new PDFFragmentsAndSegmentsClass(AppSid, AppKey);
            Images = new PDFImagesClass(AppSid, AppKey);
            Links = new PDFLinksClass(AppSid, AppKey);
            Pages = new PDFPagesClass(AppSid, AppKey);
            Properties = new PDFPropertiesClass(AppSid, AppKey);
            TextItems = new PDFTextItemsClass(AppSid, AppKey);
            TextReplace = new PDFTextReplaceClass(AppSid, AppKey);

        }

        public PDFAnnotationsClass Annotations { get; set; }
        public PDFAttachmentsClass Attachments { get; set; }
        public PDFBarcodesClass Barcodes { get; set; }
        public PDFBookmarksClass Bookmarks { get; set; }
        public PDFDocumentAppendMergeClass AppendMerge { get; set; }
        public PDFDocumentSaveAsClass SaveAs { get; set; }
        public PDFFieldsClass Fields { get; set; }
        public PDFFragmentsAndSegmentsClass FragmentsAndSegments { get; set; }
        public PDFImagesClass Images { get; set; }
        public PDFLinksClass Links { get; set; }
        public PDFPagesClass Pages { get; set; }
        public PDFPropertiesClass Properties { get; set; }
        public PDFTextItemsClass TextItems { get; set; }
        public PDFTextReplaceClass TextReplace { get; set; }
        
        /// <summary>
        /// Read common document info
        /// </summary>
        /// <param name="name">The document name.</param>
        /// <param name="folder">The document folder.</param>
        /// <param name="storage">The document storage.</param>
        /// <returns>PDFDocumentResponse object</returns>
        public PDFDocumentResponse ReadCommonDocumentInfo(string name, string folder, string storage = "")
        {
            // GET 	pdf/{name}?appSid={appSid}&format={format}&storage={storage}&folder={folder}&outPath={outPath} 

            string apiUrl = string.Format(@"pdf/{0}?storage={1}&folder={2}&outPath={3}",
                                            name, storage, folder, string.Empty);

            JObject jObject = JObject.Parse(ServiceController.Get(apiUrl, AppSid, AppKey));
            PDFDocumentResponse pdfDocumentResponse = jObject.ToObject<PDFDocumentResponse>();
            return pdfDocumentResponse;
        }

        /// <summary>
        /// convert document to some format if the format specified.	
        /// </summary>
        /// <param name="name">The document name.</param>
        /// <param name="format">PDF Document convert format</param>
        /// <param name="folder">The document folder.</param>
        /// <param name="outPath">Path to save result. It can be local (e.g. c:\out.doc) or cloud storage path (MyFolder\out.doc). The conversion result for TeX and HTML formats can contain multiple files so it is returned as zip archive.</param>
        /// <param name="storage">The document storage.</param>
        public void ConvertToSomeFormat(string name, PDFDocumentConvertFormat format, string folder, string outPath, string storage = "")
        {
            // GET 	pdf/{name}?appSid={appSid}&format={format}&storage={storage}&folder={folder}&outPath={outPath} 

            if (format == PDFDocumentConvertFormat.Tex || format == PDFDocumentConvertFormat.Html)
                outPath = outPath.Replace(Path.GetExtension(outPath), ".zip");

            string apiUrl = string.Format(@"pdf/{0}?format={1}&storage={2}&folder={3}&outPath={4}",
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
        /// Create default empty PDF document.
        /// </summary>
        /// <param name="name">The document name.</param>
        /// <param name="folder">The document folder.</param>
        /// <param name="storage">The document storage.</param>
        public PDFDocumentResponse CreateEmptyNewDocument(string name, string folder, string storage = "")
        {
            // PUT 	pdf/{name}?appSid={appSid}&templateFile={templateFile}&dataFile={dataFile}&templateType={templateType}&storage={storage}&folder={folder} 

            string apiUrl = string.Format(@"pdf/{0}?storage={1}&folder={2}",
                                            name, storage, folder);

            JObject jObject = JObject.Parse(ServiceController.Put(apiUrl, AppSid, AppKey));
            PDFDocumentResponse pdfDocumentResponse = jObject.ToObject<PDFDocumentResponse>();
            return pdfDocumentResponse;
        }

        /// <summary>
        /// Create new document.
        /// </summary>
        /// <param name="name">The document name.</param>
        /// <param name="templateType">The template type, can be xml, html, pcl, tiff, jpeg, svg or xps</param>
        /// <param name="folder">The document folder.</param>
        /// <param name="templateFile">The template file server path</param>
        /// <param name="dataFile">The data file server path, used for xml template type only</param>
        /// <param name="storage">The document storage.</param>
        public PDFDocumentResponse CreateNewDocument(string name, PDFTemplateType templateType, string templateFile, string dataFile, string folder, string storage = "")
        {
            // PUT 	pdf/{name}?appSid={appSid}&templateFile={templateFile}&dataFile={dataFile}&templateType={templateType}&storage={storage}&folder={folder} 

            string apiUrl = string.Format(@"pdf/{0}?templateFile={1}&dataFile={2}&templateType={3}&storage={4}&folder={5}",
                                            name, templateFile, dataFile, templateType, storage, folder);

            JObject jObject = JObject.Parse(ServiceController.Put(apiUrl, AppSid, AppKey));
            PDFDocumentResponse pdfDocumentResponse = jObject.ToObject<PDFDocumentResponse>();
            return pdfDocumentResponse;
        }

        /// <summary>
        /// Convert document from URL.
        /// </summary>
        /// <param name="format">PDF Document convert format</param>
        /// <param name="url">URL of the pdf file</param>
        /// <param name="outPath">Path to save result. It must be a local path (e.g. c:\out.doc).</param>
        public void ConvertDocumentFromURL(PDFDocumentConvertFormat format, string url, string outPath)
        {
            // PUT 	pdf/convert?appSid={appSid}&format={format}&url={url}&outPath={outPath}

            if (format == PDFDocumentConvertFormat.Tex || format == PDFDocumentConvertFormat.Html)
                outPath = outPath.Replace(Path.GetExtension(outPath), ".zip");

            string apiUrl = string.Format(@"pdf/convert?format={0}&url={1}&outPath={2}",
                                            format, url, (outPath.Contains(@":\") ? string.Empty : outPath));

            if (!string.IsNullOrEmpty(outPath) && Directory.Exists(Path.GetDirectoryName(outPath)))
            {
                using (Stream responseStream = ServiceController.GetStreamWithPut(apiUrl, AppSid, AppKey))
                using (Stream file = File.OpenWrite(outPath))
                {
                    ServiceController.CopyStream(responseStream, file);
                }
            }           
        }

        /// <summary>
        /// Convert document from URL.
        /// </summary>
        /// <param name="format">PDF Document convert format</param>
        /// <param name="inputFilePath">URL of the pdf file</param>
        /// <param name="outPath">Path to save result. It must be a local path (e.g. c:\out.doc).</param>
        public void ConvertDocument(PDFDocumentConvertFormat format, string inputFilePath, string outPath)
        {
            // PUT 	pdf/convert?appSid={appSid}&format={format}&url={url}&outPath={outPath}

            if (format == PDFDocumentConvertFormat.Tex || format == PDFDocumentConvertFormat.Html)
                outPath = outPath.Replace(Path.GetExtension(outPath), ".zip");

            string apiUrl = string.Format(@"pdf/convert?format={0}&outPath={1}",
                                            format, (outPath.Contains(@":\") ? string.Empty : outPath));

            if (!string.IsNullOrEmpty(outPath) && Directory.Exists(Path.GetDirectoryName(outPath)))
            {
                using (Stream responseStream = ServiceController.GetStreamWithPut(apiUrl, AppSid, AppKey, File.ReadAllBytes(inputFilePath)))
                using (Stream file = File.OpenWrite(outPath))
                {
                    ServiceController.CopyStream(responseStream, file);
                }
            }
        }
        
        /// <summary>
        /// Sign document.
        /// </summary>
        /// <param name="name">The document name.</param>
        /// <param name="storage">The document storage.</param>
        /// <param name="folder">The document folder.</param>
        public void SignDocument(string name, string folder, PDFSignature singature, string storage = "")
        {
            // POST 	pdf/{name}/sign?appSid={appSid}&storage={storage}&folder={folder} 

            string apiUrl = string.Format(@"pdf/{0}/sign?storage={1}&folder={2}",
                                            name, storage, folder);

            ServiceController.Post(apiUrl, AppSid, AppKey, JsonConvert.SerializeObject(singature));
        }

        /// <summary>
        /// Split document to parts.
        /// </summary>
        /// <param name="name">The document name.</param>
        /// <param name="format">Resulting documents format.</param>
        /// <param name="from">The start page number for splitting, if is not specified splitting starts from the first page of the document. </param>
        /// <param name="to">The last page number for splitting, if is not specified splitting ends at the last page of the document.</param>
        /// <param name="storage">The document storage.</param>
        /// <param name="folder">The document folder.</param>
        public PDFDocumentSplitResponse SplitDocumentToParts(string name, string folder, int from = 0, int to = 0, string storage = "")
        {
            // POST 	pdf/{name}/split?appSid={appSid}&format={format}&from={from}&to={to}&storage={storage}&folder={folder} 

            string apiUrl = string.Format(@"pdf/{0}/split?storage={1}&folder={2}",
                                            name, storage, folder);

            if (from > 0) apiUrl = apiUrl + string.Format("&from={0}", from);
            if (to > 0) apiUrl = apiUrl + string.Format("&to={0}", to);

            JObject jObject = JObject.Parse(ServiceController.Post(apiUrl, AppSid, AppKey));
            PDFDocumentSplitResponse fDocumentSplitResponse = jObject.ToObject<PDFDocumentSplitResponse>();
            return fDocumentSplitResponse;
        }

        /// <summary>
        /// Split document to parts.
        /// </summary>
        /// <param name="name">The document name.</param>
        /// <param name="format">Resulting documents format.</param>
        /// <param name="from">The start page number for splitting, if is not specified splitting starts from the first page of the document. </param>
        /// <param name="to">The last page number for splitting, if is not specified splitting ends at the last page of the document.</param>
        /// <param name="storage">The document storage.</param>
        /// <param name="folder">The document folder.</param>
        public PDFDocumentSplitResponse SplitDocumentToParts(string name, string folder, PDFOutputFormat format, int from = 0, int to = 0, string storage = "")
        {
            // POST 	pdf/{name}/split?appSid={appSid}&format={format}&from={from}&to={to}&storage={storage}&folder={folder} 

            string apiUrl = string.Format(@"pdf/{0}/split?format={1}&storage={2}&folder={3}",
                                            name, format, storage, folder);

            if (from > 0) apiUrl = apiUrl + string.Format("&from={0}", from);
            if (to > 0) apiUrl = apiUrl + string.Format("&to={0}", to);

            JObject jObject = JObject.Parse(ServiceController.Post(apiUrl, AppSid, AppKey));
            PDFDocumentSplitResponse fDocumentSplitResponse = jObject.ToObject<PDFDocumentSplitResponse>();
            return fDocumentSplitResponse;
        }

        public class PDFAnnotationsClass
        {
            public string AppKey { get; set; }
            public string AppSid { get; set; }

            internal PDFAnnotationsClass(string appSid, string appKey) { AppSid = appSid; AppKey = appKey; }

            /// <summary>
            /// Read documant page annotations.	
            /// </summary>
            /// <param name="name">The document name.</param>
            /// <param name="pageNumber">The page number.</param>
            /// <param name="folder">The document folder.</param>
            /// <param name="storage">The document storage.</param>
            /// <returns>AnnotationsResponse object</returns>
            public AnnotationsResponse ReadDocumantPageAnnotations(string name, int pageNumber, string folder, string storage = "")
            {
                // GET 	pdf/{name}/pages/{pageNumber}/annotations?appSid={appSid}&storage={storage}&folder={folder}

                string apiUrl = string.Format(@"pdf/{0}/pages/{1}/annotations?storage={2}&folder={3}",
                                                name, pageNumber, storage, folder);

                JObject jObject = JObject.Parse(ServiceController.Get(apiUrl, AppSid, AppKey));
                AnnotationsResponse annotationsResponse = jObject.ToObject<AnnotationsResponse>();
                return annotationsResponse;

            }

            /// <summary>
            /// Read document page annotation by its number.	
            /// </summary>
            /// <param name="name">The document name.</param>
            /// <param name="pageNumber">The page number.</param>
            /// <param name="annotationNumber">The annotation number.</param>
            /// <param name="folder">The document folder.</param>
            /// <param name="storage">The document storage.</param>
            /// <returns>AnnotationsResponse object</returns>
            public AnnotationResponse ReadDocumentPageAnnotationByItsNumber(string name, int pageNumber, int annotationNumber, string folder, string storage = "")
            {
                // GET 	pdf/{name}/pages/{pageNumber}/annotations/{annotationNumber}?appSid={appSid}&storage={storage}&folder={folder} 
                string apiUrl = string.Format(@"pdf/{0}/pages/{1}/annotations/{2}?storage={3}&folder={4}",
                                                name, pageNumber, annotationNumber, storage, folder);

                JObject jObject = JObject.Parse(ServiceController.Get(apiUrl, AppSid, AppKey));
                AnnotationResponse annotationResponse = jObject.ToObject<AnnotationResponse>();
                return annotationResponse;
            }
        }

        public class PDFAttachmentsClass
        {
            public string AppKey { get; set; }
            public string AppSid { get; set; }

            internal PDFAttachmentsClass(string appSid, string appKey) { AppSid = appSid; AppKey = appKey; }

            /// <summary>
            /// Read document attachments info.	
            /// </summary>
            /// <param name="name">The document name.</param>
            /// <param name="storage">The document storage.</param>
            /// <param name="folder">The document folder.</param>
            public AttachmentsResponse ReadDocumentAttachmentsInfo(string name, string folder, string storage = "")
            {
                // GET 	pdf/{name}/attachments?appSid={appSid}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"pdf/{0}/attachments?storage={1}&folder={2}",
                                                name, storage, folder);

                JObject jObject = JObject.Parse(ServiceController.Get(apiUrl, AppSid, AppKey));
                AttachmentsResponse AttachmentsResponse = jObject.ToObject<AttachmentsResponse>();
                return AttachmentsResponse;

            }

            /// <summary>
            /// Read document attachment info by its index.	
            /// </summary>
            /// <param name="name">The document name.</param>
            /// <param name="attachmentIndex"></param>
            /// <param name="storage">The document storage.</param>
            /// <param name="folder">The document folder.</param>
            public AttachmentResponse ReadDocumentAttachmentInfoByItsIndex(string name, int attachmentIndex, string folder, string storage = "")
            {
                // GET 	pdf/{name}/attachments/{attachmentIndex}?appSid={appSid}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"pdf/{0}/attachments/{1}?storage={2}&folder={3}",
                                                name, attachmentIndex, storage, folder);

                JObject jObject = JObject.Parse(ServiceController.Get(apiUrl, AppSid, AppKey));
                AttachmentResponse AttachmentResponse = jObject.ToObject<AttachmentResponse>();
                return AttachmentResponse;

            }

            /// <summary>
            /// Download document attachment content by its index.
            /// </summary>
            /// <param name="name">The document name.</param>
            /// <param name="attachmentIndex">The attachment index.</param>
            /// <param name="folder">The document folder.</param>
            /// <param name="outPath">Local path with file name and extension to save attachment.</param>
            /// <param name="storage">The document storage.</param>
            public void DownloadDocumentAttachmentContentByItsIndex(string name, int attachmentIndex, string folder, string outPath, string storage = "")
            {
                // GET 	pdf/{name}/attachments/{attachmentIndex}/download?appSid={appSid}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"pdf/{0}/attachments/{1}/download?storage={2}&folder={3}",
                                                name, attachmentIndex, storage, folder);

                using (Stream responseStream = ServiceController.GetStream(apiUrl, AppSid, AppKey))
                using (Stream file = File.OpenWrite(outPath))
                {
                    ServiceController.CopyStream(responseStream, file);
                }
            }
        }

        public class PDFBarcodesClass
        {
            public string AppKey { get; set; }
            public string AppSid { get; set; }

            internal PDFBarcodesClass(string appSid, string appKey) { AppSid = appSid; AppKey = appKey; }

            /// <summary>
            /// Recognize barcodes.	
            /// </summary>
            /// <param name="name">The document name.</param>
            /// <param name="pageNumber">Page number.</param>
            /// <param name="imageNumber">Image number.</param>
            /// <param name="storage">The document storage.</param>
            /// <param name="folder">The document folder.</param>
            public BarcodeResponse RecognizeBarcodes(string name, int pageNumber, int imageNumber, string folder, string storage = "")
            {
                // GET 	pdf/{name}/pages/{pageNumber}/images/{imageNumber}/recognize?appSid={appSid}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"pdf/{0}/pages/{1}/images/{2}/recognize?storage={3}&folder={4}",
                                                name, pageNumber, imageNumber, storage, folder);

                JObject jObject = JObject.Parse(ServiceController.Get(apiUrl, AppSid, AppKey));
                BarcodeResponse barcodeResponse = jObject.ToObject<BarcodeResponse>();
                return barcodeResponse;
            }
        }

        public class PDFBookmarksClass
        {
            public string AppKey { get; set; }
            public string AppSid { get; set; }

            internal PDFBookmarksClass(string appSid, string appKey) { AppSid = appSid; AppKey = appKey; }

            /// <summary>
            /// Read document bookmarks.	
            /// </summary>
            /// <param name="name">The document name.</param>
            /// <param name="storage">The document storage.</param>
            /// <param name="folder">The document folder.</param>
            public BookmarksResponse ReadAllDocumentBookmarks(string name, string folder, string storage = "")
            {
                // GET 	pdf/{name}/bookmarks?appSid={appSid}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"pdf/{0}/bookmarks?storage={1}&folder={2}",
                                                name, storage, folder);

                JObject jObject = JObject.Parse(ServiceController.Get(apiUrl, AppSid, AppKey));
                BookmarksResponse bookmarksResponse = jObject.ToObject<BookmarksResponse>();
                return bookmarksResponse;
            }

            /// <summary>
            /// Read document bookmark/bookmarks (including children).	
            /// </summary>
            /// <param name="name">The document name.</param>
            /// <param name="bookmarkIndex">The bookmark index.</param>
            /// <param name="storage">The document storage.</param>
            /// <param name="folder">The document folder.</param>
            public BookmarkResponse ReadDocumentBookmark(string name, int bookmarkIndex, string folder, string storage = "")
            {
                // GET 	pdf/{name}/bookmarks/{bookmarkPath}?appSid={appSid}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"pdf/{0}/bookmarks/{1}?storage={2}&folder={3}",
                                                name, bookmarkIndex, storage, folder);

                JObject jObject = JObject.Parse(ServiceController.Get(apiUrl, AppSid, AppKey));
                BookmarkResponse bookmarkResponse = jObject.ToObject<BookmarkResponse>();
                return bookmarkResponse;
            }
        }

        public class PDFDocumentAppendMergeClass
        {
            public string AppKey { get; set; }
            public string AppSid { get; set; }

            internal PDFDocumentAppendMergeClass(string appSid, string appKey) { AppSid = appSid; AppKey = appKey; }

            /// <summary>
            /// Append document to existing one.	
            /// </summary>
            /// <param name="name">The original document name.</param>
            /// <param name="appendFile">Append file server path.</param>
            /// <param name="startPage">Appending start page.</param>
            /// <param name="endPage">Appending end page.</param>            
            /// <param name="folder">The document folder.</param>
            /// <param name="storage">The document storage.</param>
            public PDFDocumentResponse AppendDocumentToExistingOne(string name, string appendFile, string folder, int startPage = 0, int endPage = 0, string storage = "")
            {
                // POST 	pdf/{name}/appendDocument?appSid={appSid}&appendFile={appendFile}&startPage={startPage}&endPage={endPage}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"pdf/{0}/appendDocument?appendFile={1}&startPage={2}&endPage={3}&storage={4}&folder={5}",
                                                name, appendFile, startPage, endPage, storage, folder);

                JObject jObject = JObject.Parse(ServiceController.Post(apiUrl, AppSid, AppKey));
                PDFDocumentResponse pdfDocumentResponse = jObject.ToObject<PDFDocumentResponse>();
                return pdfDocumentResponse;
            }

            //
            /// <summary>
            /// Merge a list of documents.	
            /// </summary>
            /// <param name="name">Resulting documen name.</param>
            /// <param name="storage">The document storage.</param>
            /// <param name="folder">The document folder.</param>
            public void MergeAListOfDocuments(string name, string folder, PDFDocumentsList list, string storage = "")
            {
                // PUT 	pdf/{name}/merge?appSid={appSid}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"pdf/{0}/merge?storage={1}&folder={2}",
                                                name, storage, folder);

                ServiceController.Put(apiUrl, AppSid, AppKey, JsonConvert.SerializeObject(list));
            }
        }

        public class PDFDocumentSaveAsClass
        {
            public string AppKey { get; set; }
            public string AppSid { get; set; }

            internal PDFDocumentSaveAsClass(string appSid, string appKey) { AppSid = appSid; AppKey = appKey; }

            /// <summary>
            /// Save document as Tiff image.	
            /// </summary>
            /// <param name="name">The document name.</param>
            /// <param name="resultFile">The resulting file. Resulting image saved to the same folder and storage where the original document is. Relative path can be used here for some subfolder of the document folder.</param>
            /// <param name="brightness">The image brightness.</param>
            /// <param name="compression">Tiff compression. Possible values are: LZW, CCITT4, CCITT3, RLE, None.</param>
            /// <param name="colorDepth">Image color depth. Possible valuse are: Default, Format8bpp, Format4bpp, Format1bpp.</param>
            /// <param name="leftMargin">Left image margin.</param>
            /// <param name="rightMargin">Right image margin.</param>
            /// <param name="topMargin">Top image margin.</param>
            /// <param name="bottomMargin">Bottom image margin.</param>
            /// <param name="orientation">Image orientation. Possible values are: None, Landscape, Portait.</param>
            /// <param name="skipBlankPages">Skip blank pages flag.</param>
            /// <param name="width">Image width.</param>
            /// <param name="height">Image height.</param>
            /// <param name="xResolution">Horizontal resolution.</param>
            /// <param name="yResolution">Vertical resolution.</param>
            /// <param name="pageIndex">Start page to export.</param>
            /// <param name="pageCount">Number of pages to export.</param>
            /// <param name="storage">The document storage.</param>
            /// <param name="folder">The document folder.</param>
            public void SaveDocumentAsTiffImage(string name, string resultFile, string folder, double brightness = 0.0, TiffCompression compression = TiffCompression.None, ImageColorDepth colorDepth = ImageColorDepth.Default, int leftMargin = 0, int rightMargin = 0, int topMargin = 0, int bottomMargin = 0, Orientation orientation = Orientation.None, bool skipBlankPages = true, int width = 0, int height = 0, int xResolution = 0, int yResolution = 0, int pageIndex = 0, int pageCount = 0, string storage = "")
            {
                // PUT 	pdf/{name}/SaveAs/tiff?appSid={appSid}&resultFile={resultFile}&brightness={brightness}&compression={compression}&colorDepth={colorDepth}&leftMargin={leftMargin}&rightMargin={rightMargin}&topMargin={topMargin}&bottomMargin={bottomMargin}&orientation={orientation}&skipBlankPages={skipBlankPages}&width={width}&height={height}&xResolution={xResolution}&yResolution={yResolution}&pageIndex={pageIndex}&pageCount={pageCount}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"pdf/{0}/SaveAs/tiff?resultFile={1}&skipBlankPages={2}&storage={3}&folder={4}",
                                                name, resultFile, skipBlankPages, storage, folder);

                if (brightness > 0) apiUrl += string.Format("&brightness={0}", brightness);
                if (compression != TiffCompression.None) apiUrl += string.Format("&compression={0}", compression);
                if (colorDepth > 0) apiUrl += string.Format("&colorDepth={0}", colorDepth);
                if (leftMargin > 0) apiUrl += string.Format("&leftMargin={0}", leftMargin);
                if (rightMargin > 0) apiUrl += string.Format("&rightMargin={0}", rightMargin);
                if (topMargin > 0) apiUrl += string.Format("&topMargin={0}", topMargin);
                if (bottomMargin > 0) apiUrl += string.Format("&bottomMargin={0}", bottomMargin);
                if (orientation != Orientation.None) apiUrl += string.Format("&orientation={0}", orientation);

                if (width > 0) apiUrl += string.Format("&width={0}", width);
                if (height > 0) apiUrl += string.Format("&height={0}", height);
                if (rightMargin > 0) apiUrl += string.Format("&rightMargin={0}", rightMargin);
                if (yResolution > 0) apiUrl += string.Format("&yResolution={0}", yResolution);
                if (pageIndex > 0) apiUrl += string.Format("&pageIndex={0}", pageIndex);
                if (pageCount > 0) apiUrl += string.Format("&pageCount={0}", pageCount);

                ServiceController.Put(apiUrl, AppSid, AppKey);
            }
        }

        public class PDFFieldsClass
        {
            public string AppKey { get; set; }
            public string AppSid { get; set; }

            internal PDFFieldsClass(string appSid, string appKey) { AppSid = appSid; AppKey = appKey; }

            //	
            /// <summary>
            /// Get document fields.
            /// </summary>
            /// <param name="name">The document name.</param>
            /// <param name="storage">The document storage.</param>
            /// <param name="folder">The document folder.</param>
            public FieldsResponse GetDocumentFields(string name, string folder, string storage = "")
            {
                // GET 	pdf/{name}/fields?appSid={appSid}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"pdf/{0}/fields?storage={1}&folder={2}",
                                                name, storage, folder);

                JObject jObject = JObject.Parse(ServiceController.Get(apiUrl, AppSid, AppKey));
                FieldsResponse fieldsResponse = jObject.ToObject<FieldsResponse>();
                return fieldsResponse;
            }

            /// <summary>
            /// Update fields.	
            /// </summary>
            /// <param name="name">The document name.</param>            
            /// <param name="fields">Fields object.</param>            
            /// <param name="folder">The document folder.</param>
            /// <param name="storage">The document storage.</param>
            public void UpdateFields(string name, Fields fields, string folder, string storage = "")
            {
                // PUT 	pdf/{name}/fields?appSid={appSid}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"pdf/{0}/fields?storage={1}&folder={2}",
                                                name, storage, folder);

                ServiceController.Put(apiUrl, AppSid, AppKey, JsonConvert.SerializeObject(fields));
            }

            /// <summary>
            /// Create field.	
            /// </summary>
            /// <param name="name">The document name.</param>
            /// <param name="page">Document page number.</param>
            /// <param name="field">Field object.</param>
            /// <param name="folder">The document folder.</param>
            /// <param name="storage">The document storage.</param>
            public void CreateField(string name, int pageNumber, Field field, string folder, string storage = "")
            {
                // POST 	pdf/{name}/fields?appSid={appSid}&page={page}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"pdf/{0}/fields?page={1}&storage={2}&folder={3}",
                                                name, pageNumber, storage, folder);

                ServiceController.Post(apiUrl, AppSid, AppKey, JsonConvert.SerializeObject(field));
            }

            /// <summary>
            /// Get document field by name.	
            /// </summary>
            /// <param name="name">The document name.</param>
            /// <param name="fieldName">The name of the field.</param>
            /// <param name="folder">The document folder.</param>
            /// <param name="storage">The document storage.</param>
            public FieldResponse GetDocumentFieldByName(string name, string fieldName, string folder, string storage = "")
            {
                // GET 	pdf/{name}/fields/{fieldName}?appSid={appSid}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"pdf/{0}/fields/{1}?storage={2}&folder={3}",
                                                name, fieldName, storage, folder);

                JObject jObject = JObject.Parse(ServiceController.Get(apiUrl, AppSid, AppKey));
                FieldResponse fieldResponse = jObject.ToObject<FieldResponse>();
                return fieldResponse;

            }

            /// <summary>
            /// Update field.	
            /// </summary>
            /// <param name="name">The document name.</param>
            /// <param name="fieldName">The name of the field.</param>
            /// <param name="field">Field object.</param>
            /// <param name="folder">The document folder.</param>
            /// <param name="storage">The document storage.</param>
            public void UpdateField(string name, string fieldName, Field field, string folder, string storage = "")
            {
                // PUT 	pdf/{name}/fields/{fieldName}?appSid={appSid}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"pdf/{0}/fields/{1}?storage={2}&folder={3}",
                                                name, fieldName, storage, folder);

                ServiceController.Put(apiUrl, AppSid, AppKey, JsonConvert.SerializeObject(field));
            }
        }

        public class PDFFragmentsAndSegmentsClass
        {
            public string AppKey { get; set; }
            public string AppSid { get; set; }

            internal PDFFragmentsAndSegmentsClass(string appSid, string appKey) { AppSid = appSid; AppKey = appKey; }

            /// <summary>
            /// Read page fragments.	
            /// </summary>
            /// <param name="name">The document name.</param>
            /// <param name="pageNumber">The page number.</param>
            /// <param name="withEmpty"></param>
            /// <param name="folder">The document folder.</param>
            /// <param name="storage">The document storage.</param>
            public TextItemsResponse ReadPageFragments(string name, int pageNumber, bool withEmpty, string folder, string storage = "")
            {
                // GET 	pdf/{name}/pages/{pageNumber}/fragments?appSid={appSid}&withEmpty={withEmpty}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"pdf/{0}/pages/{1}/fragments?withEmpty={2}&storage={3}&folder={4}",
                                                name, pageNumber, withEmpty, storage, folder);

                JObject jObject = JObject.Parse(ServiceController.Get(apiUrl, AppSid, AppKey));
                TextItemsResponse textItemsResponse = jObject.ToObject<TextItemsResponse>();
                return textItemsResponse;

            }

            /// <summary>
            /// Read page fragment.	
            /// </summary>
            /// <param name="name">The document name.</param>
            /// <param name="pageNumber">The page number.</param>
            /// <param name="fragmentNumber"></param>
            /// <param name="withEmpty"></param>
            /// <param name="storage">The document storage.</param>
            /// <param name="folder">The document folder.</param>
            public TextItemsResponse ReadPageFragment(string name, int pageNumber, int fragmentNumber, bool withEmpty, string folder, string storage = "")
            {
                // GET 	pdf/{name}/pages/{pageNumber}/fragments/{fragmentNumber}?appSid={appSid}&withEmpty={withEmpty}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"pdf/{0}/pages/{1}/fragments/{2}?withEmpty={3}&storage={4}&folder={5}",
                                                name, pageNumber, fragmentNumber, withEmpty, storage, folder);

                JObject jObject = JObject.Parse(ServiceController.Get(apiUrl, AppSid, AppKey));
                TextItemsResponse textItemsResponse = jObject.ToObject<TextItemsResponse>();
                return textItemsResponse;
            }

            /// <summary>
            /// Read page fragment text format.	
            /// </summary>
            /// <param name="name">The document name.</param>
            /// <param name="pageNumber">The page number.</param>
            /// <param name="fragmentNumber"></param>
            /// <param name="folder">The document folder.</param>
            /// <param name="storage">The document storage.</param>
            public TextFormatResponse ReadPageFragmentTextFormat(string name, int pageNumber, int fragmentNumber, string folder, string storage = "")
            {
                // GET 	pdf/{name}/pages/{pageNumber}/fragments/{fragmentNumber}/textFormat?appSid={appSid}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"pdf/{0}/pages/{1}/fragments/{2}/textFormat?storage={3}&folder={4}",
                                                name, pageNumber, fragmentNumber, storage, folder);

                JObject jObject = JObject.Parse(ServiceController.Get(apiUrl, AppSid, AppKey));
                TextFormatResponse textFormatResponse = jObject.ToObject<TextFormatResponse>();
                return textFormatResponse;
            }

            /// <summary>
            /// Read fragment segments.	
            /// </summary>
            /// <param name="name">The document name.</param>
            /// <param name="pageNumber">The page number.</param>
            /// <param name="fragmentNumber"></param>
            /// <param name="withEmpty"></param>
            /// <param name="storage">The document storage.</param>
            /// <param name="folder">The document folder.</param>
            public TextItemsResponse ReadFragmentSegments(string name, int pageNumber, int fragmentNumber, bool withEmpty, string folder, string storage = "")
            {
                // GET 	pdf/{name}/pages/{pageNumber}/fragments/{fragmentNumber}/segments?appSid={appSid}&withEmpty={withEmpty}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"pdf/{0}/pages/{1}/fragments/{2}/segments?withEmpty={3}&storage={4}&folder={5}",
                                                name, pageNumber, fragmentNumber, withEmpty, storage, folder);

                JObject jObject = JObject.Parse(ServiceController.Get(apiUrl, AppSid, AppKey));
                TextItemsResponse textItemsResponse = jObject.ToObject<TextItemsResponse>();
                return textItemsResponse;
            }

            /// <summary>
            /// Read segment.	
            /// </summary>
            /// <param name="name">The document name.</param>
            /// <param name="pageNumber">The page number.</param>
            /// <param name="fragmentNumber"></param>
            /// <param name="segmentNumber"></param>
            /// <param name="storage">The document storage.</param>
            /// <param name="folder">The document folder.</param>
            public TextItemResponse ReadSegment(string name, int pageNumber, int fragmentNumber, int segmentNumber, string folder, string storage = "")
            {
                // GET 	pdf/{name}/pages/{pageNumber}/fragments/{fragmentNumber}/segments/{segmentNumber}?appSid={appSid}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"pdf/{0}/pages/{1}/fragments/{2}/segments/{3}?storage={4}&folder={5}",
                                                name, pageNumber, fragmentNumber, segmentNumber, storage, folder);

                JObject jObject = JObject.Parse(ServiceController.Get(apiUrl, AppSid, AppKey));
                TextItemResponse textItemResponse = jObject.ToObject<TextItemResponse>();
                return textItemResponse;                
            }

            /// <summary>
            /// Read segment text format.	
            /// </summary>
            /// <param name="name">The document name.</param>
            /// <param name="pageNumber">The page number.</param>
            /// <param name="fragmentNumber"></param>
            /// <param name="segmentNumber"></param>
            /// <param name="storage">The document storage.</param>
            /// <param name="folder">The document folder.</param>
            public TextFormatResponse ReadSegmentTextFormat(string name, int pageNumber, int fragmentNumber, int segmentNumber, string folder, string storage = "")
            {
                // GET 	pdf/{name}/pages/{pageNumber}/fragments/{fragmentNumber}/segments/{segmentNumber}/textformat?appSid={appSid}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"pdf/{0}/pages/{1}/fragments/{2}/segments/{3}/textformat?storage={4}&folder={5}",
                                                name, pageNumber, fragmentNumber, segmentNumber, storage, folder);

                JObject jObject = JObject.Parse(ServiceController.Get(apiUrl, AppSid, AppKey));
                TextFormatResponse textFormatResponse = jObject.ToObject<TextFormatResponse>();
                return textFormatResponse;
            }

        }

        public class PDFImagesClass
        {
            public string AppKey { get; set; }
            public string AppSid { get; set; }

            internal PDFImagesClass(string appSid, string appKey) { AppSid = appSid; AppKey = appKey; }

            /// <summary>
            /// Read document images.
            /// </summary>
            /// <param name="name">The document name.</param>
            /// <param name="pageNumber">The page number.</param>
            /// <param name="folder">The document folder.</param>
            /// <param name="storage">The document storage.</param>
            /// <returns></returns>
            public PDFImagesResponse ReadDocumentImages(string name, int pageNumber, string folder, string storage = "")
            {
                // GET 	pdf/{name}/pages/{pageNumber}/images?appSid={appSid}&storage={storage}&folder={folder} 
                string apiUrl = string.Format(@"pdf/{0}/pages/{1}/images?storage={2}&folder={3}",
                                                name, pageNumber, storage, folder);

                JObject jObject = JObject.Parse(ServiceController.Get(apiUrl, AppSid, AppKey));
                PDFImagesResponse pdfImagesResponse = jObject.ToObject<PDFImagesResponse>();
                return pdfImagesResponse;

            }

            /// <summary>
            /// Read document image by number.	
            /// </summary>
            /// <param name="name">The document name.</param>
            /// <param name="pageNumber">The page number.</param>
            /// <param name="imageNumber">The image number.</param>
            /// <param name="width">The converted image width.</param>
            /// <param name="height">The converted image height.</param>
            /// <param name="format">Image format to convert, if not specified the common image data is read.</param>
            /// <param name="folder">The document folder.</param>
            /// <param name="outPath">The output path to save the image.</param>
            /// <param name="storage">The document storage.</param>
            /// <returns></returns>
            public void ReadDocumentImageByNumber(string name, int pageNumber, int imageNumber, int width, int height, PDFImageFormat format, string folder, string outPath, string storage = "")
            {
                // GET 	pdf/{name}/pages/{pageNumber}/images/{imageNumber}?appSid={appSid}&width={width}&height={height}&format={format}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"pdf/{0}/pages/{1}/images/{2}?width={3}&height={4}&format={5}&storage={6}&folder={7}",
                                                name, pageNumber, imageNumber, width, height, format, storage, folder);

                using (Stream responseStream = ServiceController.GetStream(apiUrl, AppSid, AppKey))
                using (Stream file = File.OpenWrite(outPath))
                {
                    ServiceController.CopyStream(responseStream, file);
                }
            }

            /// <summary>
            /// Replace document image.
            /// </summary>
            /// <param name="name">The document name.</param>
            /// <param name="pageNumber">The page number.</param>
            /// <param name="imageNumber">The image number.</param>
            /// <param name="imageFile">Path to image file if specified. Request content is used otherwise.</param>
            /// <param name="storage">The document storage.</param>
            /// <param name="folder">The document folder.</param>
            public void ReplaceDocumentImage(string name, int pageNumber, int imageNumber, string imageFile, string folder, string storage = "")
            {
                // POST 	pdf/{name}/pages/{pageNumber}/images/{imageNumber}?appSid={appSid}&imageFile={imageFile}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"pdf/{0}/pages/{1}/images/{2}?imageFile={3}&storage={4}&folder={5}",
                                                name, pageNumber, imageNumber, (imageFile.Contains(@":\") ? string.Empty : imageFile), storage, folder);

                if (!string.IsNullOrEmpty(imageFile) && Directory.Exists(Path.GetDirectoryName(imageFile)))
                {
                    ServiceController.Post(apiUrl, AppSid, AppKey, File.ReadAllBytes(imageFile));
                }
                else
                {
                    ServiceController.Post(apiUrl, AppSid, AppKey);
                }
            }
        }

        public class PDFLinksClass
        {
            public string AppKey { get; set; }
            public string AppSid { get; set; }

            internal PDFLinksClass(string appSid, string appKey) { AppSid = appSid; AppKey = appKey; }

            /// <summary>
            /// Read document page link annotations.	
            /// </summary>
            /// <param name="name">The document name.</param>
            /// <param name="pageNumber">The page number.</param>
            /// <param name="folder">The document folder.</param>
            /// <param name="storage">The document storage.</param>
            public LinksResponse ReadDocumentPageLinkAnnotations(string name, int pageNumber, string folder, string storage = "")
            {
                // GET 	pdf/{name}/pages/{pageNumber}/links?appSid={appSid}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"pdf/{0}/pages/{1}/links?storage={2}&folder={3}",
                                                name, pageNumber, storage, folder);

                JObject jObject = JObject.Parse(ServiceController.Get(apiUrl, AppSid, AppKey));
                LinksResponse linksResponse = jObject.ToObject<LinksResponse>();
                return linksResponse;
            }

            /// <summary>
            /// Read document page link annotation by its index.	
            /// </summary>
            /// <param name="name">The document name.</param>
            /// <param name="pageNumber">The page number.</param>
            /// <param name="linkIndex">Link Index number</param>
            /// <param name="folder">The document folder.</param>
            /// <param name="storage">The document storage.</param>
            public LinkResponse ReadDocumentPageLinkAnnotationByItsIndex(string name, int pageNumber, int linkIndex, string folder, string storage = "")
            {
                // GET 	pdf/{name}/pages/{pageNumber}/links/{linkIndex}?appSid={appSid}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"pdf/{0}/pages/{1}/links/{2}?storage={3}&folder={4}",
                                                name, pageNumber, linkIndex, storage, folder);

                JObject jObject = JObject.Parse(ServiceController.Get(apiUrl, AppSid, AppKey));
                LinkResponse linkResponse = jObject.ToObject<LinkResponse>();
                return linkResponse;
            }

        }

        public class PDFPagesClass
        {
            public string AppKey { get; set; }
            public string AppSid { get; set; }

            internal PDFPagesClass(string appSid, string appKey) { AppSid = appSid; AppKey = appKey; }

            /// <summary>
            /// Read document pages info.	
            /// </summary>
            /// <param name="name">The document name.</param>
            /// <param name="storage">The document storage.</param>
            /// <param name="folder">The document folder.</param>
            public PagesResponse ReadDocumentPagesInfo(string name, string folder, string storage = "")
            {
                // GET 	pdf/{name}/pages?appSid={appSid}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"pdf/{0}/pages?storage={1}&folder={2}",
                                                name, storage, folder);

                JObject jObject = JObject.Parse(ServiceController.Get(apiUrl, AppSid, AppKey));
                PagesResponse pagesResponse = jObject.ToObject<PagesResponse>();
                return pagesResponse;
            }

            /// <summary>
            /// Add new page to end of the document.
            /// </summary>
            /// <param name="name">The document name.</param>
            /// <param name="storage">The document storage.</param>
            /// <param name="folder">The document folder.</param>
            public PagesResponse AddNewPageToEndOfTheDocument(string name, string folder, string storage = "")
            {
                // PUT 	pdf/{name}/pages?appSid={appSid}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"pdf/{0}/pages?storage={1}&folder={2}",
                                                name, storage, folder);

                JObject jObject = JObject.Parse(ServiceController.Put(apiUrl, AppSid, AppKey));
                PagesResponse pagesResponse = jObject.ToObject<PagesResponse>();
                return pagesResponse;
            }

            /// <summary>
            /// Read document page info
            /// </summary>
            /// <param name="name">The document name.</param>
            /// <param name="pageNumber">The page number.</param>
            /// <param name="folder">The document folder.</param>
            /// <param name="storage">The document storage.</param>
            public PageResponse ReadDocumentPageInfo(string name, int pageNumber, string folder, string storage = "")
            {
                // GET 	pdf/{name}/pages/{pageNumber}?appSid={appSid}&format={format}&width={width}&height={height}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"pdf/{0}/pages/{1}?storage={2}&folder={3}",
                                                name, pageNumber, storage, folder);

                JObject jObject = JObject.Parse(ServiceController.Get(apiUrl, AppSid, AppKey));
                PageResponse pageResponse = jObject.ToObject<PageResponse>();
                return pageResponse;
            }

            /// <summary>
            /// Convert to some format if the format specified.	
            /// </summary>
            /// <param name="name">The document name.</param>
            /// <param name="pageNumber">The page number.</param>
            /// <param name="format">The format to convert if specified.</param>  
            /// <param name="outPath">Path to save result. Must be a local path e.g c:\out.jpg</param>   
            /// <param name="folder">The document folder.</param>
            /// <param name="width">The converted image width.</param>
            /// <param name="height">The converted image height.</param> 
            /// <param name="storage">The document storage.</param>
            public void ConvertToSomeFormat(string name, int pageNumber, PDFPageConvertFormat format, string outPath, string folder, int width = 0, int height = 0, string storage = "")
            {
                // GET 	pdf/{name}/pages/{pageNumber}?appSid={appSid}&format={format}&width={width}&height={height}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"pdf/{0}/pages/{1}?format={2}&storage={3}&folder={4}",
                                name, pageNumber, format, storage, folder);

                if (width > 0) apiUrl+= string.Format("&width={0}", width);
                if (height> 0) apiUrl+= string.Format("&height={0}", height);

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
            /// Get number of words per document page.	
            /// </summary>
            /// <param name="name">The document name.</param>
            /// <param name="folder">The document folder.</param>
            /// <param name="storage">The document storage.</param>
            public WordsPerPageResponse GetNumberOfWordsPerDocumentPage(string name, string folder, string storage = "")
            {
                // GET 	pdf/{name}/pages/wordCount?appSid={appSid}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"pdf/{0}/pages/wordCount?storage={1}&folder={2}",
                                                name, storage, folder);

                JObject jObject = JObject.Parse(ServiceController.Get(apiUrl, AppSid, AppKey));
                WordsPerPageResponse wordsPerPageResponse = jObject.ToObject<WordsPerPageResponse>();
                return wordsPerPageResponse;
            }

            /// <summary>
            /// Delete document page by its number.	
            /// </summary>
            /// <param name="name">The document name.</param>
            /// <param name="pageNumber">The page number.</param>
            /// <param name="folder">The document folder.</param>
            /// <param name="storage">The document storage.</param>
            public void DeleteDocumentPageByItsNumber(string name, int pageNumber, string folder, string storage = "")
            {
                // DELETE 	pdf/{name}/pages/{pageNumber}?appSid={appSid}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"pdf/{0}/pages/{1}?storage={2}&folder={3}",
                                                name, pageNumber, storage, folder);

                ServiceController.Delete(apiUrl, AppSid, AppKey);
            }

            /// <summary>
            /// Move page to new position.	
            /// </summary>
            /// <param name="name">The document name.</param>
            /// <param name="pageNumber">The page number.</param>
            /// <param name="newIndex">The new page position/index.</param>
            /// <param name="folder">The document folder.</param>
            /// <param name="storage">The document storage.</param>
            public void MovePageToNewPosition(string name, int pageNumber, int newIndex, string folder, string storage = "")
            {
                // POST 	pdf/{name}/pages/{pageNumber}/movePage?newIndex={newIndex}&appSid={appSid}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"pdf/{0}/pages/{1}/movePage?newIndex={2}&storage={3}&folder={4}",
                                                name, pageNumber, newIndex, storage, folder);

                ServiceController.Post(apiUrl, AppSid, AppKey);
            }

            /// <summary>
            /// Sign page.
            /// </summary>
            /// <param name="name">The document name.</param>
            /// <param name="pageNumber">The page number.</param>
            /// <param name="signature">Signature object</param>            
            /// <param name="folder">The document folder.</param>
            /// <param name="storage">The document storage.</param>
            public void SignPage(string name, int pageNumber, string folder, PDFSignature signature, string storage = "")
            {
                // POST 	pdf/{name}/pages/{pageNumber}/sign?appSid={appSid}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"pdf/{0}/pages/{1}/sign?storage={2}&folder={3}",
                                                name, pageNumber, storage, folder);

                ServiceController.Post(apiUrl, AppSid, AppKey, JsonConvert.SerializeObject(signature));
            }

            /// <summary>
            /// Add page stamp.	
            /// </summary>
            /// <param name="name">The document name.</param>
            /// <param name="pageNumber">The page number.</param>
            /// <param name="stamp">Stamp object with data.</param>            
            /// <param name="folder">The document folder.</param>
            /// <param name="storage">The document storage.</param>
            public void AddPageStamp(string name, int pageNumber, string folder, PageStamp stamp, string storage = "")
            {
                // PUT 	pdf/{name}/pages/{pageNumber}/stamp?appSid={appSid}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"pdf/{0}/pages/{1}/stamp?storage={2}&folder={3}",
                                                name, pageNumber, storage, folder);

                ServiceController.Put(apiUrl, AppSid, AppKey, JsonConvert.SerializeObject(stamp));                
            }
        }

        public class PDFPropertiesClass
        {
            public string AppKey { get; set; }
            public string AppSid { get; set; }

            internal PDFPropertiesClass(string appSid, string appKey) { AppSid = appSid; AppKey = appKey; }

            /// <summary>
            /// Read document properties.	
            /// </summary>
            /// <param name="name">The document name.</param>
            /// <param name="storage">The document storage.</param>
            /// <param name="folder">The document folder.</param>
            public DocumentPropertiesResponse ReadDocumentProperties(string name, string folder, string storage = "")
            {
                // GET 	pdf/{name}/documentproperties?appSid={appSid}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"pdf/{0}/documentproperties?storage={1}&folder={2}",
                                                name, storage, folder);

                JObject jObject = JObject.Parse(ServiceController.Get(apiUrl, AppSid, AppKey));
                DocumentPropertiesResponse documentPropertiesResponse = jObject.ToObject<DocumentPropertiesResponse>();
                return documentPropertiesResponse;
            }

            /// <summary>
            /// Delete document properties.	
            /// </summary>
            /// <param name="name">The document name.</param>
            /// <param name="storage">The document storage.</param>
            /// <param name="folder">The document folder.</param>
            public void DeleteDocumentProperties(string name, string folder, string storage = "")
            {
                // DELETE 	pdf/{name}/documentproperties?appSid={appSid}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"pdf/{0}/documentproperties?storage={1}&folder={2}",
                                                name, storage, folder);

                ServiceController.Delete(apiUrl, AppSid, AppKey);

            }

            /// <summary>
            /// Read document property by name.
            /// </summary>
            /// <param name="name">The document name.</param>
            /// <param name="propertyName">Document property name</param>
            /// <param name="storage">The document storage.</param>
            /// <param name="folder">The document folder.</param>
            public DocumentPropertyResponse ReadDocumentPropertyByName(string name, string propertyName, string folder, string storage = "")
            {
                // GET 	pdf/{name}/documentproperties/{propertyName}?appSid={appSid}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"pdf/{0}/documentproperties/{1}?storage={2}&folder={3}",
                                                name, propertyName, storage, folder);

                JObject jObject = JObject.Parse(ServiceController.Get(apiUrl, AppSid, AppKey));
                DocumentPropertyResponse documentPropertyResponse = jObject.ToObject<DocumentPropertyResponse>();
                return documentPropertyResponse;
            }

            /// <summary>
            /// Add/update document property.	
            /// </summary>
            /// <param name="name">The document name.</param>
            /// <param name="propertyName">Document property name</param>
            /// <param name="storage">The document storage.</param>
            /// <param name="folder">The document folder.</param>
            public void AddUpdateDocumentProperty(string name, string propertyName, string folder, DocumentProperty property, string storage = "")
            {
                // PUT 	pdf/{name}/documentproperties/{propertyName}?appSid={appSid}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"pdf/{0}/documentproperties/{1}?storage={2}&folder={3}",
                                                name, propertyName, storage, folder);

                ServiceController.Put(apiUrl, AppSid, AppKey, JsonConvert.SerializeObject(property));
            }

            /// <summary>
            /// Delete document property.	
            /// </summary>
            /// <param name="name">The document name.</param>
            /// <param name="propertyName">Document property name</param>
            /// <param name="storage">The document storage.</param>
            /// <param name="folder">The document folder.</param>
            public void DeleteDocumentProperty(string name, string propertyName, string folder, string storage = "")
            {
                // DELETE 	pdf/{name}/documentproperties/{propertyName}?appSid={appSid}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"pdf/{0}/documentproperties/{1}?storage={2}&folder={3}",
                                                name, propertyName, storage, folder);

                ServiceController.Delete(apiUrl, AppSid, AppKey);
            }
        }

        public class PDFTextItemsClass
        {
            public string AppKey { get; set; }
            public string AppSid { get; set; }

            internal PDFTextItemsClass(string appSid, string appKey) { AppSid = appSid; AppKey = appKey; }

            /// <summary>
            /// Read document text items.	
            /// </summary>
            /// <param name="name">The document name.</param>
            /// <param name="withEmpty">Set this to true to include segments without text or with whitespaces. </param>
            /// <param name="storage">The document storage.</param>
            /// <param name="folder">The document folder.</param>
            public TextItemsResponse ReadDocumentTextItems(string name, string folder, bool withEmpty = false, string storage = "")
            {
                // GET 	pdf/{name}/textItems?appSid={appSid}&withEmpty={withEmpty}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"pdf/{0}/textItems?withEmpty={1}&storage={2}&folder={3}",
                                                name, withEmpty, storage, folder);

                JObject jObject = JObject.Parse(ServiceController.Get(apiUrl, AppSid, AppKey));
                TextItemsResponse textItemsResponse = jObject.ToObject<TextItemsResponse>();
                return textItemsResponse;
            }

            /// <summary>
            /// Read page text items.
            /// </summary>
            /// <param name="name">The document name.</param>
            /// <param name="pageNumber">The page number.</param>
            /// <param name="withEmpty">Set this to true to include segments without text or with whitespaces. </param>
            /// <param name="storage">The document storage.</param>
            /// <param name="folder">The document folder.</param>
            public TextItemsResponse ReadPageTextItems(string name, int pageNumber, string folder, bool withEmpty = false, string storage = "")
            {
                // GET 	pdf/{name}/pages/{pageNumber}/textItems?appSid={appSid}&withEmpty={withEmpty}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"pdf/{0}/pages/{1}/textItems?withEmpty={2}&storage={3}&folder={4}",
                                                name, pageNumber, withEmpty, storage, folder);

                JObject jObject = JObject.Parse(ServiceController.Get(apiUrl, AppSid, AppKey));
                TextItemsResponse textItemsResponse = jObject.ToObject<TextItemsResponse>();
                return textItemsResponse;
            }
        }

        public class PDFTextReplaceClass
        {
            public string AppKey { get; set; }
            public string AppSid { get; set; }

            internal PDFTextReplaceClass(string appSid, string appKey) { AppSid = appSid; AppKey = appKey; }

            /// <summary>
            /// Document's replace text method.	
            /// </summary>
            /// <param name="name">The document name.</param>
            /// <param name="folder">The document folder.</param>
            /// <param name="storage">The document storage.</param>
            public DocumentReplaceTextResponse ReplaceText(string name, string folder, TextReplace textReplace, string storage = "")
            {
                // POST 	pdf/{name}/replaceText?appSid={appSid}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"pdf/{0}/replaceText?storage={1}&folder={2}",
                                                name, storage, folder);

                JObject jObject = JObject.Parse(ServiceController.Post(apiUrl, AppSid, AppKey, JsonConvert.SerializeObject(textReplace)));
                DocumentReplaceTextResponse documentReplaceTextResponse = jObject.ToObject<DocumentReplaceTextResponse>();
                return documentReplaceTextResponse;
            }

            /// <summary>
            /// Page's replace text method.	
            /// </summary>
            /// <param name="name">The document name.</param>
            /// <param name="pageNumber">The page number.</param>
            /// <param name="folder">The document folder.</param>
            /// <param name="storage">The document storage.</param>
            public PageReplaceTextResponse ReplaceText(string name, int pageNumber, string folder, TextReplace textReplace, string storage = "")
            {
                // POST 	pdf/{name}/pages/{pageNumber}/replaceText?appSid={appSid}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"pdf/{0}/pages/{1}/replaceText?storage={2}&folder={3}",
                                                name, pageNumber, storage, folder);

                JObject jObject = JObject.Parse(ServiceController.Post(apiUrl, AppSid, AppKey, JsonConvert.SerializeObject(textReplace)));
                PageReplaceTextResponse pageReplaceTextResponse = jObject.ToObject<PageReplaceTextResponse>();
                return pageReplaceTextResponse;
            }


            /// <summary>
            /// Document's replace text method.	
            /// </summary>
            /// <param name="name">The document name.</param>
            /// <param name="storage">The document storage.</param>
            /// <param name="folder">The document folder.</param>
            public DocumentReplaceTextResponse ReplaceTextList(string name, string folder, TextReplaceList textReplaceList, string storage = "")
            {
                // POST 	pdf/{name}/replaceTextList?appSid={appSid}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"pdf/{0}/replaceTextList?storage={1}&folder={2}",
                                                name, storage, folder);

                JObject jObject = JObject.Parse(ServiceController.Post(apiUrl, AppSid, AppKey, JsonConvert.SerializeObject(textReplaceList)));
                DocumentReplaceTextResponse documentReplaceTextResponse = jObject.ToObject<DocumentReplaceTextResponse>();
                return documentReplaceTextResponse;
            }

            /// <summary>
            /// Page's replace text method.
            /// </summary>
            /// <param name="name">The document name.</param>
            /// <param name="pageNumber">The page number.</param>
            /// <param name="folder">The document folder.</param>
            /// <param name="storage">The document storage.</param>
            public PageReplaceTextResponse ReplaceTextList(string name, int pageNumber, string folder, TextReplaceList textReplaceList, string storage = "")
            {
                // POST 	pdf/{name}/pages/{pageNumber}/replaceTextList?appSid={appSid}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"pdf/{0}/pages/{1}/replaceTextList?storage={2}&folder={3}",
                                                name, pageNumber, storage, folder);

                JObject jObject = JObject.Parse(ServiceController.Post(apiUrl, AppSid, AppKey, JsonConvert.SerializeObject(textReplaceList)));
                PageReplaceTextResponse pageReplaceTextResponse = jObject.ToObject<PageReplaceTextResponse>();
                return pageReplaceTextResponse;
            }
        }
    }

    public enum TiffCompression
    {
        LZW, CCITT4, CCITT3, RLE, None
    }

    public enum ImageColorDepth
    {
        Default, Format8bpp, Format4bpp, Format1bpp
    }

    public enum PDFOutputFormat
    {
        Pdf, Pdfa1a, Pdfa1b, Xps, Doc, Tiff, Jpeg, Png, Emf, Bmp, Gif
    }

    public enum PDFPageConvertFormat
    {
        Tiff, Jpeg, Png, Emf, Bmp, Gif
    }

    public enum PDFDocumentConvertFormat
    {
        Tiff, Pdf, Pdfa1a, Pdfa1b, Xps, Doc, Tex, Html
    }

    public enum PDFTemplateType
    {
        Xml, Html, Pcl, Tiff, Jpeg, Svg, Xps
    }

    public enum PDFImageFormat
    {
        Jpeg, Tiff, Gif, Png
    }

    public enum Orientation
    {
        None, Landscape, Portait
    }

    public class Fields
    {
        public Fields()
        {
            List = new List<Field>();
            Links = new List<Link>();
        }

        public List<Field> List { get; set; }
        public List<Link> Links { get; set; }
    }

    public class FieldsResponse
    {
        public Fields Fields { get; set; }
        public int Code { get; set; }
        public string Status { get; set; }
    }

    public class Annotation
    {
        public Color Color { get; set; }
        public string Contents { get; set; }
        public string CreationDate { get; set; }
        public string Subject { get; set; }
        public string Title { get; set; }
        public string Modified { get; set; }
        public List<Link> Links { get; set; }
    }

    public class AnnotationResponse
    {
        public Annotation Annotation { get; set; }
        public int Code { get; set; }
        public string Status { get; set; }
    }

    public class PDFAttachment
    {
        public string Description { get; set; }
        public string MimeType { get; set; }
        public string Name { get; set; }
        public string CreationDate { get; set; }
        public string ModificationDate { get; set; }
        public int Size { get; set; }
        public string CheckSum { get; set; }
        public List<Link> Links { get; set; }
    }

    public class AttachmentResponse
    {
        public PDFAttachment Attachment { get; set; }
        public int Code { get; set; }
        public string Status { get; set; }
    }

    public class Attachments
    {
        public List<LinksList> List { get; set; }
        public List<Link> Links { get; set; }
    }

    public class AttachmentsResponse
    {
        public Attachments Attachments { get; set; }
        public int Code { get; set; }
        public string Status { get; set; }
    }

    public class Annotations
    {
        public List<LinksList> List { get; set; }
        public List<Link> Links { get; set; }
    }

    public class AnnotationsResponse
    {
        public Annotations Annotations { get; set; }
        public int Code { get; set; }
        public string Status { get; set; }
    }

    public class PDFSplitDocument
    {
        public int Id { get; set; }
        public string Href { get; set; }
        public string Rel { get; set; }
        public object Type { get; set; }
        public object Title { get; set; }
    }

    public class Bookmark
    {
        public string Title { get; set; }
        public bool Italic { get; set; }
        public bool Bold { get; set; }
        public object Color { get; set; }
        public LinksList Bookmarks { get; set; }
        public List<Link> Links { get; set; }
    }

    public class BookmarkResponse
    {
        public Bookmark Bookmark { get; set; }
        public int Code { get; set; }
        public string Status { get; set; }
    }

    public class Result
    {
        public List<PDFSplitDocument> Documents { get; set; }
    }

    public class PDFDocumentSplitResponse
    {
        public Result Result { get; set; }
        public int Code { get; set; }
        public string Status { get; set; }
    }

    public class Bookmarks
    {
        public List<LinksList> List { get; set; }
        public List<Link> Links { get; set; }
    }

    public class BookmarksResponse
    {
        public Bookmarks Bookmarks { get; set; }
        public int Code { get; set; }
        public string Status { get; set; }
    }



    public class PDFImagesResponse
    {
        public Images Images { get; set; }
        public int Code { get; set; }
        public string Status { get; set; }
    }

    public class Rectangle
    {
        public Rectangle(int x, int y, int width, int height)
        {
            X = x; Y = y; Width = width; Height = height;
        }
        public int X { get; set; }
        public int Y { get; set; }
        public int Width { get; set; }
        public int Height { get; set; }
    }

    public class PDFSignature
    {
        public string SignaturePath { get; set; }
        public string SignatureType { get; set; }
        public string Password { get; set; }
        public string Appearance { get; set; }
        public string Reason { get; set; }
        public string Contact { get; set; }
        public string Location { get; set; }
        public bool Visible { get; set; }
        public Rectangle Rectangle { get; set; }
        public string FormFieldName { get; set; }
        public string Authority { get; set; }
        public string Date { get; set; }
    }

    public class Field
    {
        public Field()
        {
            Values = new List<string>();
            SelectedItems = new List<int>();
            Links = new List<Link>();
        }

        /// <summary>
        ///  The unique name of the field. 
        /// </summary>
        public string Name { get; set; }
        
        /// <summary>
        ///  A value indicating the type of the form field. Available values - text, boolean, integer, list. 
        /// </summary>
        public string Type { get; set; }

        /// <summary>
        ///  List of values of the form field. Contains one (for textbox, checkbox) or more (for listbox) values. 
        /// </summary>
        public List<string> Values { get; set; }
        
        public List<int> SelectedItems { get; set; }
        public Rectangle Rect { get; set; }
        public List<Link> Links { get; set; }
    }

    public class FieldResponse
    {
        public Field Field { get; set; }
        public int Code { get; set; }
        public string Status { get; set; }
    }

    public class PDFDocumentsList
    {
        public PDFDocumentsList()
        {
            List = new List<string>();
        }

        public List<string> List { get; set; }
    }

    public class PDFDocumentResponse
    {
        public Document Document { get; set; }
        public int Code { get; set; }
        public string Status { get; set; }
    }


    public class PDFLinks
    {
        public List<LinksList> List { get; set; }
        public List<Link> Links { get; set; }
    }

    public class LinksResponse
    {
        public PDFLinks Links { get; set; }
        public int Code { get; set; }
        public string Status { get; set; }
    }

    public class PDFLink
    {
        public int ActionType { get; set; }
        public string Action { get; set; }
        public int Highlighting { get; set; }
        public Color Color { get; set; }
        public List<Link> Links { get; set; }
    }

    public class LinkResponse
    {
        public PDFLink Link { get; set; }
        public int Code { get; set; }
        public string Status { get; set; }
    }






    public class DocumentPropertiesResponse
    {
        public DocumentProperties DocumentProperties { get; set; }
        public int Code { get; set; }
        public string Status { get; set; }
    }




    public class DocumentPropertyResponse
    {
        public DocumentProperty DocumentProperty { get; set; }
        public int Code { get; set; }
        public string Status { get; set; }
    }


    public class Format
    {
        public Color Color { get; set; }
        public double FontSize { get; set; }
        public string FontName { get; set; }
        public List<Link> Links { get; set; }
    }

    public class TextItem
    {
        public string Text { get; set; }
        public Format Format { get; set; }
        public List<Link> Links { get; set; }
    }

    public class TextItems
    {
        public List<TextItem> List { get; set; }
        public List<Link> Links { get; set; }
    }

    public class TextItemsResponse
    {
        public TextItems TextItems { get; set; }
        public int Code { get; set; }
        public string Status { get; set; }
    }

    public class DocumentProperties
    {
        public List<DocumentProperty> List { get; set; }
        public List<Link> Links { get; set; }
        public List<DocumentProperty> DocumentPropertyList { get; set; }
    }

    public class LinksList
    {
        public int Width { get; set; }
        public int Height { get; set; }
        public List<Link> Links { get; set; }
    }

    public class Images
    {
        public List<LinksList> List { get; set; }
        public List<Link> Links { get; set; }
    }

    public class PageReplaceTextResponse
    {
        public Page Page { get; set; }
        public int Matches { get; set; }
        public int Code { get; set; }
        public string Status { get; set; }
    }

    public class TextReplace
    {
        public TextReplace(string oldValue, string newValue, bool regex)
        {
            OldValue = oldValue;
            NewValue = newValue;
            Regex = regex;
        }

        public string OldValue { get; set; }
        public string NewValue { get; set; }
        public bool Regex { get; set; }
    }

    public class TextReplaceList
    {
        public TextReplaceList()
        {
            TextReplaces = new List<TextReplace>();
        }

        public List<TextReplace> TextReplaces { get; set; }
    }

    public class DocumentProperty
    {
        public DocumentProperty()
        {
            Links = new List<Link>();
        }

        public string Name { get; set; }
        public string Value { get; set; }
        public bool BuiltIn { get; set; }
        public List<Link> Links { get; set; }
        public Link link { get; set; }
    }


    public class Document
    {
        public DocumentProperties DocumentProperties { get; set; }
        public Pages Pages { get; set; }
        public List<Link> Links { get; set; }
    }

    public class DocumentReplaceTextResponse
    {
        public Document Document { get; set; }
        public int Matches { get; set; }
        public int Code { get; set; }
        public string Status { get; set; }
    }


    public class Pages
    {
        public List<DocumentProperty> List { get; set; }
        public List<Link> Links { get; set; }
    }

    public class PagesResponse
    {
        public Pages Pages { get; set; }
        public int Code { get; set; }
        public string Status { get; set; }
    }

    public class Page
    {
        public int Id { get; set; }
        public Images Images { get; set; }
        public Rectangle Rectangle { get; set; }
        public List<Link> Links { get; set; }
    }

    public class PageResponse
    {
        public Page Page { get; set; }
        public int Code { get; set; }
        public string Status { get; set; }
    }

    public class PageNumberNCountList
    {
        public int PageNumber { get; set; }
        public int Count { get; set; }
    }

    public class WordsPerPage
    {
        public List<PageNumberNCountList> List { get; set; }
    }

    public class WordsPerPageResponse
    {
        public WordsPerPage WordsPerPage { get; set; }
        public int Code { get; set; }
        public string Status { get; set; }
    }

    public class TextState
    {
        public double FontSize { get; set; }
        public string Font { get; set; }
        public Color ForegroundColor { get; set; }
        public Color BackgroundColor { get; set; }
        public int FontStyle { get; set; }
    }

    public class PageStamp
    {
        public int Type { get; set; }
        public bool Background { get; set; }
        public double BottomMargin { get; set; }
        public int HorizontalAlignment { get; set; }
        public double LeftMargin { get; set; }
        public double Opacity { get; set; }
        public double RightMargin { get; set; }
        public int Rotate { get; set; }
        public double RotateAngle { get; set; }
        public double TopMargin { get; set; }
        public int VerticalAlignment { get; set; }
        public double XIndent { get; set; }
        public double YIndent { get; set; }
        public double Zoom { get; set; }
        public int TextAlignment { get; set; }
        public string Value { get; set; }
        public TextState TextState { get; set; }
        public string FileName { get; set; }
        public double Width { get; set; }
        public double Height { get; set; }
        public int PageIndex { get; set; }
        public int StartingNumber { get; set; }
    }

    public class TextFormat
    {
        public Color Color { get; set; }
        public double FontSize { get; set; }
        public string FontName { get; set; }
        public List<Link> Links { get; set; }
    }

    public class TextFormatResponse
    {
        public TextFormat TextFormat { get; set; }
        public int Code { get; set; }
        public string Status { get; set; }
    }

    public class TextItemResponse
    {
        public TextItem TextItem { get; set; }
        public int Code { get; set; }
        public string Status { get; set; }
    }
}

