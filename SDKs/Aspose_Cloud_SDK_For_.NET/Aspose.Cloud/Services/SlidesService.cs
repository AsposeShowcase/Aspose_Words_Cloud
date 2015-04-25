using System;
using System.Collections.Generic;
using System.Text;
using Aspose.Cloud.Common;
using Newtonsoft.Json;
using Newtonsoft.Json.Linq;
using System.IO;

namespace Aspose.Cloud
{
    public enum SlidesOutputFormat
    {
        Tiff, Pdf, Xps, Pptx, Odp, Ppsx, Pptm, Ppsm, Potx, Potm, Html
    }

    public enum SlidesImageFormat
    {
        Tiff, Jpeg, Png, Bmp, Gif
    }

    public class SlidesService : BaseService
    {
        public SlidesService(string appSid, string appKey)
            : base(appSid, appKey)
        {
            AppSid = appSid;
            AppKey = appKey;

            Images = new SlidesImagesClass(AppSid, AppKey);
            MergeDocument = new SlidesMergeDocumentClass(AppSid, AppKey);
            Placeholders = new SlidesPlaceholdersClass(AppSid, AppKey);
            Properties = new SlidesPropertiesClass(AppSid, AppKey);
            Shapes = new SlidesShapesClass(AppSid, AppKey);
            Slides = new SlidesClass(AppSid, AppKey);
            Text = new SlidesTextClass(AppSid, AppKey);
            Theme = new SlidesThemeClass(AppSid, AppKey);
        }


        public SlidesImagesClass Images { get; set; }
        public SlidesMergeDocumentClass MergeDocument { get; set; }
        public SlidesPlaceholdersClass Placeholders { get; set; }
        public SlidesPropertiesClass Properties { get; set; }
        public SlidesShapesClass Shapes { get; set; }
        public SlidesClass Slides { get; set; }
        public SlidesTextClass Text { get; set; }
        public SlidesThemeClass Theme { get; set; }


        /// <summary>
        /// Read presentation info
        /// </summary>
        /// <param name="name">The document name.</param>
        /// <param name="folder">Document's folder.</param>
        /// <param name="password">The document password.</param>
        /// <param name="storage">Document's storage.</param>
        /// <returns>SlidesDocumentResponse object</returns>
        public SlidesDocumentResponse ReadPresentationInfo(string name, string folder, string password = "", string storage = "")
        {
            // GET 	slides/{name}?appSid={appSid}&jpegQuality={jpegQuality}&password={password}&format={format}&storage={storage}&folder={folder}&outPath={outPath} 

            string apiUrl = string.Format(@"slides/{0}?password={1}&storage={2}&folder={3}",
                                            name, password, storage, folder);

            JObject jObject = JObject.Parse(ServiceController.Get(apiUrl, AppSid, AppKey));
            SlidesDocumentResponse slidesDocumentResponse = jObject.ToObject<SlidesDocumentResponse>();
            return slidesDocumentResponse;

        }

        /// <summary>
        /// Export to some format if the format specified.	
        /// </summary>
        /// <param name="name">The document name.</param>
        /// <param name="format">The conversion format.</param>
        /// <param name="folder">Document's folder.</param>
        /// <param name="outPath">Path to save result. It can be local (e.g. c:\out.tiff) or cloud storage path (MyFolder\out.tiff).</param>
        /// <param name="jpegQuality">Quality of the JPEG images inside slides document.</param>
        /// <param name="password">The document password.</param>
        /// <param name="storage">Document's storage.</param>
        public void ExportToSomeFormat(string name, SlidesOutputFormat format, string folder, string outPath, byte jpegQuality = 50, string password = "", string storage = "")
        {
            // GET 	slides/{name}?appSid={appSid}&jpegQuality={jpegQuality}&password={password}&format={format}&storage={storage}&folder={folder}&outPath={outPath} 

            if (format == SlidesOutputFormat.Html)
                outPath = outPath.Replace(Path.GetExtension(outPath), ".zip");

            string apiUrl = string.Format(@"slides/{0}?jpegQuality={1}&password={2}&format={3}&storage={4}&folder={5}&outPath={6}",
                                            name, jpegQuality, password, format, storage, folder, (outPath.Contains(@":\") ? string.Empty : outPath));

            if (format == SlidesOutputFormat.Html)
                apiUrl += "&saveAsZip=1";

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
        /// Create presentation
        /// </summary>
        /// <param name="name">The document name.</param>
        /// <param name="folder">Document's folder.</param>
        /// <param name="templatePath">Template file path.</param>
        /// <param name="templateStorage">Template storage name.</param>
        /// <param name="password">The document password.</param>
        /// <param name="storage">Document's storage.</param>
        public void CreatePresentation(string name, string folder, string templatePath = "", string templateStorage = "", string password = "", string storage = "")
        {
            // PUT 	slides/{name}?appSid={appSid}&templatePath={templatePath}&templateStorage={templateStorage}&password={password}&storage={storage}&folder={folder} 

            string apiUrl = string.Format(@"slides/{0}?templatePath={1}&templateStorage={2}&password={3}&storage={4}&folder={5}",
                                            name, templatePath, templateStorage, password, storage, folder);

            ServiceController.Put(apiUrl, AppSid, AppKey);
        }

        /// <summary>
        /// Create presentation
        /// </summary>
        /// <param name="name">The document name.</param>
        /// <param name="isImageDataEmbedded">Is Image Data Embedded</param>
        /// <param name="templatePath">Template file path.</param>
        /// <param name="templateStorage">Template storage name.</param>
        /// <param name="password">The document password.</param>
        /// <param name="folder">Document's folder.</param>
        /// <param name="storage">Document's storage.</param>
        public void CreatePresentation(string name, bool isImageDataEmbedded, string templatePath, string templateStorage, string folder, string password = "", string storage = "")
        {
            // POST 	slides/{name}?appSid={appSid}&isImageDataEmbeeded={isImageDataEmbeeded}&templatePath={templatePath}&templateStorage={templateStorage}&password={password}&storage={storage}&folder={folder} 

            string apiUrl = string.Format(@"slides/{0}?isImageDataEmbeeded={1}&templatePath={2}&templateStorage={3}&password={4}&storage={5}&folder={6}",
                                            name, isImageDataEmbedded, templatePath, templateStorage, password, storage, folder);

            ServiceController.Post(apiUrl, AppSid, AppKey);
        }

        /// <summary>
        /// Convert presentation from request content to format specified.
        /// </summary>
        /// <param name="password">The document password.</param>
        /// <param name="format">The format.</param>
        /// <param name="outPath">Path to save result. Must be path on cloud storage e.g. Folder/file.tiff</param>
        /// <param name="outPath">Input file path</param>
        public void ConvertPresentation(SlidesOutputFormat format, string outPath, string inputFilePath, string password = "")
        {
            // PUT 	slides/convert?appSid={appSid}&password={password}&format={format}&outPath={outPath} 

            if (format == SlidesOutputFormat.Html)
                outPath = outPath.Replace(Path.GetExtension(outPath), ".zip");

            string apiUrl = string.Format(@"slides/convert?password={0}&format={1}&outPath={2}",
                                                password, format, outPath);

            ServiceController.Put(apiUrl, AppSid, AppKey, File.ReadAllBytes(inputFilePath));
        }

        /// <summary>
        /// Create presentation document from html
        /// </summary>
        /// <param name="name">The document name.</param>
        /// <param name="password">The document password.</param>
        /// <param name="folder">Document's folder.</param>
        /// <param name="htmlFilePath">Path to local html file.</param>
        /// <param name="storage">Document's storage.</param>
        public void CreatePresentationDocumentFromHtml(string name, string password, string folder, string htmlFilePath, string storage = "")
        {
            // PUT 	slides/{name}/fromHtml?appSid={appSid}&password={password}&storage={storage}&folder={folder} 

            string apiUrl = string.Format(@"slides/{0}/fromHtml?password={1}&storage={2}&folder={3}",
                                                name, password, storage, folder);

            ServiceController.Put(apiUrl, AppSid, AppKey, File.ReadAllBytes(htmlFilePath));
        }

        /// <summary>
        /// Splitting presentations. Create one image per slide.	
        /// </summary>
        /// <param name="name">The document name.</param>
        /// <param name="width">The width of created images.</param>
        /// <param name="height">The height of created images.</param>
        /// <param name="destFolder">Folder on storage where images are going to be uploaded. If not specified then images are uploaded to same folder as presentation.</param>
        /// <param name="format">The format. Default value is jpeg.</param>
        /// <param name="folder">Document's folder.</param>
        /// <param name="from">The start slide number for splitting, if is not specified splitting starts from the first slide of the presentation.</param>
        /// <param name="to">The last slide number for splitting, if is not specified splitting ends at the last slide of the document.</param>
        /// <param name="storage">Document's storage.</param>
        public SlidesSplitResponse SplitPresentations(string name, int width, int height, string destFolder, string folder, SlidesImageFormat format = SlidesImageFormat.Jpeg, int from = 0, int to = 0, string storage = "")
        {
            // POST 	slides/{name}/split?appSid={appSid}&width={width}&height={height}&to={to}&from={from}&destFolder={destFolder}&format={format}&storage={storage}&folder={folder} 

            string apiUrl = string.Format(@"slides/{0}/split?width={1}&height={2}&destFolder={3}&format={4}&storage={5}&folder={6}",
                                            name, width, height, destFolder, format, storage, folder);

            if (from > 0) apiUrl += string.Format("&from={0}", from);
            if (to > 0) apiUrl += string.Format("&to={0}", to);

            JObject jObject = JObject.Parse(ServiceController.Post(apiUrl, AppSid, AppKey));
            SlidesSplitResponse slidesSplitResponse = jObject.ToObject<SlidesSplitResponse>();
            return slidesSplitResponse;
        }


        public class SlidesImagesClass
        {
            public string AppKey { get; set; }
            public string AppSid { get; set; }

            internal SlidesImagesClass(string appSid, string appKey) { AppSid = appSid; AppKey = appKey; }

            /// <summary>
            /// Read presentation images info.	
            /// </summary>
            /// <param name="name">The document name.</param>
            /// <param name="folder">Document's folder.</param>
            /// <param name="storage">Document's storage.</param>
            /// <returns>SlidesImageResponse object</returns>
            public SlidesImageResponse ReadPresentationImagesInfo(string name, string folder, string storage = "")
            {
                // GET 	slides/{name}/images?appSid={appSid}&folder={folder}&storage={storage} 

                string apiUrl = string.Format(@"slides/{0}/images?folder={1}&storage={2}",
                                                name, folder, storage);

                JObject jObject = JObject.Parse(ServiceController.Get(apiUrl, AppSid, AppKey));
                SlidesImageResponse slidesImageResponse = jObject.ToObject<SlidesImageResponse>();
                return slidesImageResponse;

            }

            /// <summary>
            /// Read slide images info.
            /// </summary>
            /// <param name="name">The document name.</param>
            /// <param name="slideIndex">The slide index.</param>
            /// <param name="folder">Document's folder.</param>
            /// <param name="storage">Document's storage.</param>
            /// <returns>SlidesImageResponse object</returns>
            public SlidesImageResponse ReadSlideImagesInfo(string name, int slideIndex, string folder, string storage = "")
            {
                // GET 	slides/{name}/slides/{slideIndex}/images?appSid={appSid}&folder={folder}&storage={storage} 

                string apiUrl = string.Format(@"slides/{0}/slides/{1}/images?folder={2}&storage={3}",
                                                name, slideIndex, folder, storage);

                JObject jObject = JObject.Parse(ServiceController.Get(apiUrl, AppSid, AppKey));
                SlidesImageResponse slidesImageResponse = jObject.ToObject<SlidesImageResponse>();
                return slidesImageResponse;
            }

        }

        public class SlidesMergeDocumentClass
        {
            public string AppKey { get; set; }
            public string AppSid { get; set; }

            internal SlidesMergeDocumentClass(string appSid, string appKey) { AppSid = appSid; AppKey = appKey; }

            /// <summary>
            /// Merge presentations.	
            /// </summary>
            /// <param name="name">The document name.</param>
            /// <param name="folder">Document's folder.</param>
            /// <param name="storage">Document's storage.</param>
            public SlidesDocumentResponse MergePresentations(string name, string folder, PresentationRequest presentationRequest, string storage = "")
            {
                // POST 	slides/{name}/merge?appSid={appSid}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"slides/{0}/merge?storage={1}&folder={2}",
                                                name, storage, folder);

                JObject jObject = JObject.Parse(ServiceController.Post(apiUrl, AppSid, AppKey, JsonConvert.SerializeObject(presentationRequest)));
                SlidesDocumentResponse slidesDocumentResponse = jObject.ToObject<SlidesDocumentResponse>();
                return slidesDocumentResponse;
            }

            /// <summary>
            /// Merge presentations.	
            /// </summary>
            /// <param name="name">The document name.</param>
            /// <param name="folder">Document's folder.</param>
            /// <param name="storage">Document's storage.</param>
            public SlidesDocumentResponse MergePresentationsWithSlides(string name, string folder, PresentationRequest presentationRequest, string storage = "")
            {
                // PUT 	slides/{name}/merge?appSid={appSid}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"slides/{0}/merge?storage={1}&folder={2}",
                                                name, storage, folder);

                JObject jObject = JObject.Parse(ServiceController.Put(apiUrl, AppSid, AppKey, JsonConvert.SerializeObject(presentationRequest)));
                SlidesDocumentResponse slidesDocumentResponse = jObject.ToObject<SlidesDocumentResponse>();
                return slidesDocumentResponse;
            }
        }

        public class SlidesPlaceholdersClass
        {
            public string AppKey { get; set; }
            public string AppSid { get; set; }

            internal SlidesPlaceholdersClass(string appSid, string appKey) { AppSid = appSid; AppKey = appKey; }

            /// <summary>
            /// Read slide placeholders info.	
            /// </summary>
            /// <param name="name">The document name.</param>
            /// <param name="slideIndex">Slide index.</param>
            /// <param name="folder">Document's folder.</param>
            /// <param name="storage">Document's storage.</param>
            public PlaceholdersResponse ReadSlidePlaceholdersInfo(string name, int slideIndex, string folder, string storage = "")
            {
                // GET 	slides/{name}/slides/{slideIndex}/placeholders?appSid={appSid}&folder={folder}&storage={storage} 

                string apiUrl = string.Format(@"slides/{0}/slides/{1}/placeholders?folder={2}&storage={3}",
                                                                name, slideIndex, folder, storage);

                JObject jObject = JObject.Parse(ServiceController.Get(apiUrl, AppSid, AppKey));
                PlaceholdersResponse placeholdersResponse = jObject.ToObject<PlaceholdersResponse>();
                return placeholdersResponse;
            }

            /// <summary>
            /// Read slide placeholder info.	
            /// </summary>
            /// <param name="name">The document name.</param>
            /// <param name="slideIndex">Slide index.</param>
            /// <param name="placeholderIndex">Placeholder index.</param>
            /// <param name="folder">Document's folder.</param>
            /// <param name="storage">Document's storage.</param>
            public PlaceholderResponse ReadSlidePlaceholderInfo(string name, int slideIndex, int placeholderIndex, string folder, string storage = "")
            {
                // GET 	slides/{name}/slides/{slideIndex}/placeholders/{placeholderIndex}?appSid={appSid}&folder={folder}&storage={storage} 

                string apiUrl = string.Format(@"slides/{0}/slides/{1}/placeholders/{2}?folder={3}&storage={4}",
                                                                name, slideIndex, placeholderIndex, folder, storage);

                JObject jObject = JObject.Parse(ServiceController.Get(apiUrl, AppSid, AppKey));
                PlaceholderResponse placeholderResponse = jObject.ToObject<PlaceholderResponse>();
                return placeholderResponse;
            }
        }

        public class SlidesPropertiesClass
        {
            public string AppKey { get; set; }
            public string AppSid { get; set; }

            internal SlidesPropertiesClass(string appSid, string appKey) { AppSid = appSid; AppKey = appKey; }

            /// <summary>
            /// Read presentation document properties.	
            /// </summary>
            /// <param name="name">The document name.</param>
            /// <param name="folder">Document's folder.</param>
            /// <param name="storage">Document's storage.</param>
            public SlidesDocumentPropertiesResponse ReadPresentationDocumentProperties(string name, string folder, string storage = "")
            {
                // GET 	slides/{name}/documentproperties?appSid={appSid}&folder={folder}&storage={storage} 

                string apiUrl = string.Format(@"slides/{0}/documentproperties?folder={1}&storage={2}",
                                                                name, folder, storage);

                JObject jObject = JObject.Parse(ServiceController.Get(apiUrl, AppSid, AppKey));
                SlidesDocumentPropertiesResponse slidesDocumentPropertiesResponse = jObject.ToObject<SlidesDocumentPropertiesResponse>();
                return slidesDocumentPropertiesResponse;
            }

            /// <summary>
            /// Set document properties.	
            /// </summary>
            /// <param name="name">The document name.</param>
            /// <param name="folder">Document's folder.</param>
            /// <param name="storage">Document's storage.</param>
            public SlidesDocumentPropertiesResponse SetDocumentProperties(string name, string folder, SlidesDocumentPropertyRequest slidesDocumentPropertyRequest, string storage = "")
            {
                // POST 	slides/{name}/documentproperties?appSid={appSid}&folder={folder}&storage={storage} 

                string apiUrl = string.Format(@"slides/{0}/documentproperties?folder={1}&storage={2}",
                                                name, folder, storage);

                JObject jObject = JObject.Parse(ServiceController.Post(apiUrl, AppSid, AppKey, JsonConvert.SerializeObject(slidesDocumentPropertyRequest)));
                SlidesDocumentPropertiesResponse slidesDocumentPropertiesResponse = jObject.ToObject<SlidesDocumentPropertiesResponse>();
                return slidesDocumentPropertiesResponse;
            }

            /// <summary>
            /// Clean document properties.	
            /// </summary>
            /// <param name="name">The document name.</param>
            /// <param name="folder">Document's folder.</param>
            /// <param name="storage">Document's storage.</param>
            public void CleanDocumentProperties(string name, string folder, string storage = "")
            {
                // DELETE 	slides/{name}/documentproperties?appSid={appSid}&folder={folder}&storage={storage} 

                string apiUrl = string.Format(@"slides/{0}/documentproperties?folder={1}&storage={2}",
                                                name, folder, storage);

                ServiceController.Delete(apiUrl, AppSid, AppKey);
            }

            /// <summary>
            /// Set document property.
            /// </summary>
            /// <param name="name">The document name.</param>
            /// <param name="propertyName">The property name.</param>
            /// <param name="folder">Document's folder.</param>
            /// <param name="storage">Document's storage.</param>
            public SlidesDocumentPropertyResponse SetDocumentProperty(string name, string propertyName, string folder, SlidesDocumentProperty slidesDocumentProperty, string storage = "")
            {
                // PUT 	slides/{name}/documentproperties/{propertyName}?appSid={appSid}&folder={folder}&storage={storage} 

                string apiUrl = string.Format(@"slides/{0}/documentproperties/{1}?folder={2}&storage={3}",
                                                name, propertyName, folder, storage);

                JObject jObject = JObject.Parse(ServiceController.Put(apiUrl, AppSid, AppKey, JsonConvert.SerializeObject(slidesDocumentProperty)));
                SlidesDocumentPropertyResponse slidesDocumentPropertyResponse = jObject.ToObject<SlidesDocumentPropertyResponse>();
                return slidesDocumentPropertyResponse;
            }

            /// <summary>
            /// Delete document property.	
            /// </summary>
            /// <param name="name">The document name.</param>
            /// <param name="propertyName">The property name.</param>
            /// <param name="folder">Document's folder.</param>
            /// <param name="storage">Document's storage.</param>
            public void DeleteDocumentProperty(string name, string propertyName, string folder, string storage = "")
            {
                // DELETE 	slides/{name}/documentproperties/{propertyName}?appSid={appSid}&folder={folder}&storage={storage} 

                string apiUrl = string.Format(@"slides/{0}/documentproperties/{1}?folder={2}&storage={3}",
                                                                name, propertyName, folder, storage);

                ServiceController.Delete(apiUrl, AppSid, AppKey);
            }
        }

        public class SlidesShapesClass
        {
            public string AppKey { get; set; }
            public string AppSid { get; set; }

            internal SlidesShapesClass(string appSid, string appKey) { AppSid = appSid; AppKey = appKey; }

            /// <summary>
            /// Read slides shapes info.	
            /// </summary>
            /// <param name="name">The document name.</param>
            /// <param name="slideIndex">Slide index.</param>
            /// <param name="folder">Document's folder.</param>
            /// <param name="storage">Document's storage.</param>
            public SlidesShapesResponse ReadSlidesShapesInfo(string name, int slideIndex, string folder, string storage = "")
            {
                // GET 	slides/{name}/slides/{slideIndex}/shapes?appSid={appSid}&folder={folder}&storage={storage} 

                string apiUrl = string.Format(@"slides/{0}/slides/{1}/shapes?folder={2}&storage={3}",
                                                name, slideIndex, folder, storage);

                JObject jObject = JObject.Parse(ServiceController.Get(apiUrl, AppSid, AppKey));
                SlidesShapesResponse slidesShapesResponse = jObject.ToObject<SlidesShapesResponse>();
                return slidesShapesResponse;
            }

            /// <summary>
            /// Read slide shapes or shape info.	
            /// </summary>
            /// <param name="name">The document name.</param>
            /// <param name="slideIndex">Slide index.</param>
            /// <param name="shapePath">Shape path.</param>
            /// <param name="folder">Document's folder.</param>
            /// <param name="storage">Document's storage.</param>
            public SlideShapeResponse ReadSlidesShapeInfo(string name, int slideIndex, string shapePath, string folder, string storage = "")
            {
                // GET 	slides/{name}/slides/{slideIndex}/shapes/{shapePath}?appSid={appSid}&folder={folder}&storage={storage} 

                string apiUrl = string.Format(@"slides/{0}/slides/{1}/shapes/{2}?folder={3}&storage={4}",
                                                name, slideIndex, shapePath, folder, storage);

                JObject jObject = JObject.Parse(ServiceController.Get(apiUrl, AppSid, AppKey));
                SlideShapeResponse slideShapeResponse = jObject.ToObject<SlideShapeResponse>();
                return slideShapeResponse;
            }

            /// <summary>
            /// Updates shape properties.	
            /// </summary>
            /// <param name="name">The document name.</param>
            /// <param name="slideIndex">Slide index.</param>
            /// <param name="shapePath">Shape path.</param>
            /// <param name="folder">Document's folder.</param>
            /// <param name="storage">Document's storage.</param>
            public void UpdatesShapeProperties(string name, int slideIndex, string shapePath, string folder, SlideShape slideShape, string storage = "")
            {
                // PUT 	slides/{name}/slides/{slideIndex}/shapes/{shapePath}?appSid={appSid}&folder={folder}&storage={storage} 

                string apiUrl = string.Format(@"slides/{0}/slides/{1}/shapes/{2}?folder={3}&storage={4}",
                                                                name, slideIndex, shapePath, folder, storage);

                ServiceController.Put(apiUrl, AppSid, AppKey, JsonConvert.SerializeObject(slideShape));
            }

            /// <summary>
            /// Reads a list of paragraphs in shape's textBody.	
            /// </summary>
            /// <param name="name">The document name.</param>
            /// <param name="slideIndex">Slide index.</param>
            /// <param name="shapeIndex">Shape index.</param>
            /// <param name="folder">Document's folder.</param>
            /// <param name="storage">Document's storage.</param>
            public SlideParagraphsResponse ReadListofParagraphsInShapeTextbody(string name, int slideIndex, int shapeIndex, string folder, string storage = "")
            {
                // GET 	slides/{name}/slides/{slideIndex}/shapes/{shapeIndex}/paragraphs?appSid={appSid}&folder={folder}&storage={storage} 

                string apiUrl = string.Format(@"slides/{0}/slides/{1}/shapes/{2}/paragraphs?folder={3}&storage={4}",
                                                name, slideIndex, shapeIndex, folder, storage);

                JObject jObject = JObject.Parse(ServiceController.Get(apiUrl, AppSid, AppKey));
                SlideParagraphsResponse slideParagraphsResponse = jObject.ToObject<SlideParagraphsResponse>();
                return slideParagraphsResponse;
            }

            /// <summary>
            /// Reads paragraph in shape's textBody.	
            /// </summary>
            /// <param name="name">The document name.</param>
            /// <param name="slideIndex">Slide index.</param>
            /// <param name="shapeIndex">Shape index.</param>
            /// <param name="paragraphIndex">Paragraph index.</param>
            /// <param name="folder">Document's folder.</param>
            /// <param name="storage">Document's storage.</param>
            public ParagraphResponse ReadsParagraphInShapeTextbody(string name, int slideIndex, int shapeIndex, int paragraphIndex, string folder, string storage = "")
            {
                // GET 	slides/{name}/slides/{slideIndex}/shapes/{shapeIndex}/paragraphs/{paragraphIndex}?appSid={appSid}&folder={folder}&storage={storage} 

                string apiUrl = string.Format(@"slides/{0}/slides/{1}/shapes/{2}/paragraphs/{3}?folder={4}&storage={5}",
                                                name, slideIndex, shapeIndex, paragraphIndex, folder, storage);

                JObject jObject = JObject.Parse(ServiceController.Get(apiUrl, AppSid, AppKey));
                ParagraphResponse paragraphResponse = jObject.ToObject<ParagraphResponse>();
                return paragraphResponse;
            }

            /// <summary>
            /// Reads paragraph portion in shape's textBody.	
            /// </summary>
            /// <param name="name">The document name.</param>
            /// <param name="slideIndex">Slide index.</param>
            /// <param name="shapeIndex">Shape index.</param>
            /// <param name="paragraphIndex">Paragraph index.</param>
            /// <param name="portionIndex">Portion index.</param>
            /// <param name="folder">Document's folder.</param>
            /// <param name="storage">Document's storage.</param>
            public SlidesParagraphPortionResponse ReadsParagraphPortionInShapeTextbody(string name, int slideIndex, int shapeIndex, int paragraphIndex, int portionIndex, string folder, string storage = "")
            {
                // GET 	slides/{name}/slides/{slideIndex}/shapes/{shapeIndex}/paragraphs/{paragraphIndex}/portions/{portionIndex}?appSid={appSid}&folder={folder}&storage={storage} 

                string apiUrl = string.Format(@"slides/{0}/slides/{1}/shapes/{2}/paragraphs/{3}/portions/{4}?folder={5}&storage={6}",
                                                name, slideIndex, shapeIndex, paragraphIndex, portionIndex, folder, storage);

                JObject jObject = JObject.Parse(ServiceController.Get(apiUrl, AppSid, AppKey));
                SlidesParagraphPortionResponse slidesParagraphPortionResponse = jObject.ToObject<SlidesParagraphPortionResponse>();
                return slidesParagraphPortionResponse;
            }

            /// <summary>
            /// Updates paragraph portion properties.	
            /// </summary>
            /// <param name="name">The document name.</param>
            /// <param name="slideIndex">Slide index.</param>
            /// <param name="shapeIndex">Shape index.</param>
            /// <param name="paragraphIndex">Paragraph index.</param>
            /// <param name="portionIndex">Portion index.</param>
            /// <param name="folder">Document's folder.</param>
            /// <param name="storage">Document's storage.</param>
            public void UpdatesParagraphPortionProperties(string name, int slideIndex, int shapeIndex, int paragraphIndex, int portionIndex, string folder, SlidesParagraphPortion slidesParagraphPortion, string storage = "")
            {
                // PUT 	slides/{name}/slides/{slideIndex}/shapes/{shapeIndex}/paragraphs/{paragraphIndex}/portions/{portionIndex}?appSid={appSid}&folder={folder}&storage={storage} 

                string apiUrl = string.Format(@"slides/{0}/slides/{1}/shapes/{2}/paragraphs/{3}/portions/{4}?folder={5}&storage={6}",
                                                name, slideIndex, shapeIndex, paragraphIndex, portionIndex, folder, storage);


                ServiceController.Put(apiUrl, AppSid, AppKey, JsonConvert.SerializeObject(slidesParagraphPortion));
            }
        }

        public class SlidesClass
        {
            public string AppKey { get; set; }
            public string AppSid { get; set; }

            internal SlidesClass(string appSid, string appKey) { AppSid = appSid; AppKey = appKey; }

            /// <summary>
            /// Read presentation slides info.	
            /// </summary>
            /// <param name="name">The document name.</param>
            /// <param name="folder">Document's folder.</param>
            /// <param name="storage">Document's storage.</param>
            public SlidesResponse ReadPresentationSlidesInfo(string name, string folder, string storage = "")
            {
                // GET 	slides/{name}/slides?appSid={appSid}&folder={folder}&storage={storage} 

                string apiUrl = string.Format(@"slides/{0}/slides?folder={1}&storage={2}",
                                                                name, folder, storage);

                JObject jObject = JObject.Parse(ServiceController.Get(apiUrl, AppSid, AppKey));
                SlidesResponse slidesResponse = jObject.ToObject<SlidesResponse>();
                return slidesResponse;
            }

            /// <summary>
            /// Delete presentation slides.	
            /// </summary>
            /// <param name="name">The document name.</param>
            /// <param name="folder">Document's folder.</param>
            /// <param name="storage">Document's storage.</param>
            public void DeletePresentationSlides(string name, string folder, string storage = "")
            {
                // DELETE 	slides/{name}/slides?appSid={appSid}&folder={folder}&storage={storage} 

                string apiUrl = string.Format(@"slides/{0}/slides?folder={1}&storage={2}",
                                                name, folder, storage);

                ServiceController.Delete(apiUrl, AppSid, AppKey);
            }

            /// <summary>
            /// Adding empty slide to the presentation.
            /// </summary>
            /// <param name="name">The document name.</param>
            /// <param name="folder">Document's folder.</param>
            /// <param name="storage">Document's storage.</param>
            public void AddEmptySlideToTheEndOfPresentation(string name, string folder, string storage = "")
            {
                // POST 	slides/{name}/slides?appSid={appSid}&folder={folder}&storage={storage} 

                string apiUrl = string.Format(@"slides/{0}/slides?folder={1}&storage={2}",
                                                name, folder, storage);

                ServiceController.Post(apiUrl, AppSid, AppKey);
            }

            /// <summary>
            /// Adding copy of slide to the end of presentation
            /// </summary>
            /// <param name="name">The document name.</param>
            /// <param name="slideToClone">The presentation slide to clone.</param>
            /// <param name="folder">Document's folder.</param>
            /// <param name="storage">Document's storage.</param>
            public void AddCopyOfSlideToTheEndOfPresentation(string name, int slideToClone, string folder, string storage = "")
            {
                // POST 	slides/{name}/slides?appSid={appSid}&folder={folder}&storage={storage} 

                string apiUrl = string.Format(@"slides/{0}/slides?SlideToClone={1}&folder={2}&storage={3}",
                                                name, slideToClone, folder, storage);

                ServiceController.Post(apiUrl, AppSid, AppKey);
            }

            /// <summary>
            /// Adding slide to specified position. If position is greater then number of slides then slide will be added to the end of presentation.
            /// </summary>
            /// <param name="name">The document name.</param>
            /// <param name="position">The presentation slide position.</param>
            /// <param name="folder">Document's folder.</param>
            /// <param name="storage">Document's storage.</param>
            public void AddEmptySlideToSpecifiedPosition(string name, int position, string folder, string storage = "")
            {
                // POST 	slides/{name}/slides?appSid={appSid}&folder={folder}&storage={storage} 

                string apiUrl = string.Format(@"slides/{0}/slides?Position={1}&folder={2}&storage={3}",
                                                name, position, folder, storage);

                ServiceController.Post(apiUrl, AppSid, AppKey);
            }

            /// <summary>
            /// Adding copy of slide to specified position
            /// </summary>
            /// <param name="name">The document name.</param>
            /// <param name="slideToClone">The presentation slide to clone.</param>
            /// <param name="position">The presentation slide position.</param>
            /// <param name="folder">Document's folder.</param>
            /// <param name="storage">Document's storage.</param>
            public void AddCopyOfSlideToSpecifiedPosition(string name, int slideToClone, int position, string folder, string storage = "")
            {
                // POST 	slides/{name}/slides?appSid={appSid}&folder={folder}&storage={storage} 

                string apiUrl = string.Format(@"slides/{0}/slides?Position={1}&SlideToClone={2}&folder={3}&storage={4}",
                                                name, position, slideToClone, folder, storage);

                ServiceController.Post(apiUrl, AppSid, AppKey);
            }

            /// <summary>
            /// Change slide position
            /// </summary>
            /// <param name="name">The document name.</param>
            /// <param name="oldPosition">The new presentation slide position.</param>
            /// <param name="newPosition">The new presentation slide position.</param>
            /// <param name="folder">Document's folder.</param>
            /// <param name="storage">Document's storage.</param>
            public void ChangeSlidePosition(string name, int oldPosition, int newPosition, string folder, string storage = "")
            {
                // POST 	slides/{name}/slides?appSid={appSid}&oldPosition={oldPosition}&newPosition={newPosition}&folder={folder}&storage={storage} 

                string apiUrl = string.Format(@"slides/{0}/slides?oldPosition={1}&newPosition={2}&folder={3}&storage={4}",
                                                name, oldPosition, newPosition, folder, storage);

                ServiceController.Post(apiUrl, AppSid, AppKey);
            }

            /// <summary>
            /// Add copy of second slide from another presentation e.g. "reports/sales.ppt" at given position
            /// </summary>
            /// <param name="name">The document name.</param>
            /// <param name="slideToCopy">The presentation slide to copy.</param>
            /// <param name="source">The source presentation.</param>
            /// <param name="position">The presentation slide position.</param>
            /// <param name="folder">Document's folder.</param>
            /// <param name="storage">Document's storage.</param>
            public void AddCopyOfSlideFromAnotherPresentaion(string name, int slideToCopy, string source, int position, string folder, string storage = "")
            {
                // POST 	slides/{name}/slides?appSid={appSid}&slideToCopy={slideToCopy}&source={source}&position={position}&folder={folder}&storage={storage} 

                string apiUrl = string.Format(@"slides/{0}/slides?slideToCopy={1}&source={2}&position={3}&folder={4}&storage={5}",
                                                name, slideToCopy, source, position, folder, storage);

                ServiceController.Post(apiUrl, AppSid, AppKey);
            }

            /// <summary>
            /// Delete presentation slide by its index.	
            /// </summary>
            /// <param name="name">The document name.</param>
            /// <param name="slideIndex">Slide index</param>
            /// <param name="folder">Document's folder.</param>
            /// <param name="storage">Document's storage.</param>
            public void DeletePresentationSlideByItsIndex(string name, int slideIndex, string folder, string storage = "")
            {
                // DELETE 	slides/{name}/slides/{slideIndex}?appSid={appSid}&folder={folder}&storage={storage} 

                string apiUrl = string.Format(@"slides/{0}/slides/{1}?folder={2}&storage={3}",
                                                name, slideIndex, folder, storage);

                ServiceController.Delete(apiUrl, AppSid, AppKey);
            }

            /// <summary>
            /// Read slide info
            /// </summary>
            /// <param name="name">The document name.</param>
            /// <param name="slideIndex">Slide index</param>
            /// <param name="folder">Document's folder.</param>
            /// <param name="storage">Document's storage.</param>
            public SlideResponse ReadSlideInfo(string name, int slideIndex, string folder, string storage = "")
            {
                // GET 	slides/{name}/slides/{slideIndex}?appSid={appSid}&format={format}&width={width}&height={height}&folder={folder}&storage={storage} 

                string apiUrl = string.Format(@"slides/{0}/slides/{1}?folder={2}&storage={3}",
                                                name, slideIndex, folder, storage);

                JObject jObject = JObject.Parse(ServiceController.Get(apiUrl, AppSid, AppKey));
                SlideResponse slideResponse = jObject.ToObject<SlideResponse>();
                return slideResponse;
            }

            /// <summary>
            /// Convert slide to some format	
            /// </summary>
            /// <param name="name">The document name.</param>
            /// <param name="slideIndex">Slide index</param>
            /// <param name="format">Return the presentation in the specified format. Available values: jpeg, gif, tiff, bmp and png.</param>
            /// <param name="folder">Document's folder.</param>
            /// <param name="localOutPath">Local path to save output e.g. c:\out.tiff</param>
            /// <param name="width"> Width of resulting image in pixels. Valid only if accept parameter specified. Default value is 720 </param>
            /// <param name="height"> Height of resulting image in pixels. Valid only if accept parameter specified. Default value is 540 </param>            
            /// <param name="storage">Document's storage.</param>
            public void ConvertSlideToSomeFormat(string name, int slideIndex, SlidesImageFormat format, string folder, string localOutPath, int width = 720, int height = 540, string storage = "")
            {
                // GET 	slides/{name}/slides/{slideIndex}?appSid={appSid}&format={format}&width={width}&height={height}&folder={folder}&storage={storage} 

                string apiUrl = string.Format(@"slides/{0}/slides/{1}?format={2}&width={3}&height={4}&folder={5}&storage={6}",
                                                name, slideIndex, format, width, height, folder, storage);

                if (!string.IsNullOrEmpty(localOutPath) && Directory.Exists(Path.GetDirectoryName(localOutPath)))
                {
                    using (Stream responseStream = ServiceController.GetStream(apiUrl, AppSid, AppKey))
                    using (Stream file = File.OpenWrite(localOutPath))
                    {
                        ServiceController.CopyStream(responseStream, file);
                    }
                }
            }

            /// <summary>
            /// Read presentation slide background color type.	
            /// </summary>
            /// <param name="name">The document name.</param>
            /// <param name="slideIndex">Slide index</param>
            /// <param name="folder">Document's folder.</param>
            /// <param name="storage">Document's storage.</param>
            public SlidesBackgroundResponse ReadPresentationSlideBackgroundColorType(string name, int slideIndex, string folder, string storage = "")
            {
                // GET 	slides/{name}/slides/{slideIndex}/background?appSid={appSid}&folder={folder}&storage={storage} 

                string apiUrl = string.Format(@"slides/{0}/slides/{1}/background?folder={2}&storage={3}",
                                                name, slideIndex, folder, storage);

                JObject jObject = JObject.Parse(ServiceController.Get(apiUrl, AppSid, AppKey));
                SlidesBackgroundResponse slidesBackgroundResponse = jObject.ToObject<SlidesBackgroundResponse>();
                return slidesBackgroundResponse;
            }

            /// <summary>
            /// Set presentation slide background color.	
            /// </summary>
            /// <param name="name">The document name.</param>
            /// <param name="slideIndex">Slide index</param>
            /// <param name="folder">Document's folder.</param>
            /// <param name="color">Background color.</param>
            /// <param name="storage">Document's storage.</param>
            public void SetPresentationSlideBackgroundColor(string name, int slideIndex, string folder, string color, string storage = "")
            {
                // PUT 	slides/{name}/slides/{slideIndex}/background?appSid={appSid}&folder={folder}&storage={storage} 

                string apiUrl = string.Format(@"slides/{0}/slides/{1}/background?folder={2}&storage={3}",
                                                name, slideIndex, folder, storage);

                ServiceController.Put(apiUrl, AppSid, AppKey, color);
            }

            /// <summary>
            /// Remove presentation slide background color.	
            /// </summary>
            /// <param name="name">The document name.</param>
            /// <param name="slideIndex">Slide index</param>
            /// <param name="folder">Document's folder.</param>
            /// <param name="storage">Document's storage.</param>
            public void RemovePresentationSlideBackgroundColor(string name, int slideIndex, string folder, string storage = "")
            {
                // DELETE 	slides/{name}/slides/{slideIndex}/background?appSid={appSid}&folder={folder}&storage={storage} 

                string apiUrl = string.Format(@"slides/{0}/slides/{1}/background?folder={2}&storage={3}",
                                                name, slideIndex, folder, storage);

                ServiceController.Delete(apiUrl, AppSid, AppKey);
            }

            /// <summary>
            /// Read presentation slide comments.	
            /// </summary>
            /// <param name="name">The document name.</param>
            /// <param name="slideIndex">Slide index</param>
            /// <param name="folder">Document's folder.</param>
            /// <param name="storage">Document's storage.</param>
            public SlidesCommentsResponse ReadPresentationSlideComments(string name, int slideIndex, string folder, string storage = "")
            {
                // GET 	slides/{name}/slides/{slideIndex}/comments?appSid={appSid}&folder={folder}&storage={storage} 

                string apiUrl = string.Format(@"slides/{0}/slides/{1}/comments?folder={2}&storage={3}",
                                                name, slideIndex, folder, storage);

                JObject jObject = JObject.Parse(ServiceController.Get(apiUrl, AppSid, AppKey));
                SlidesCommentsResponse slidesCommentsResponse = jObject.ToObject<SlidesCommentsResponse>();
                return slidesCommentsResponse;
            }
        }

        public class SlidesTextClass
        {
            public string AppKey { get; set; }
            public string AppSid { get; set; }

            internal SlidesTextClass(string appSid, string appKey) { AppSid = appSid; AppKey = appKey; }

            /// <summary>
            /// Extract presentation text items.	
            /// </summary>
            /// <param name="name">The document name.</param>
            /// <param name="folder">Document's folder.</param>
            /// <param name="withEmpty">Set this to true to include items for shapes without text. (useful when you want to add text to shape using POST method). Default: false </param>
            /// <param name="storage">Document's storage.</param>
            public SlidesTextItemsResponse ExtractPresentationTextItems(string name, string folder, bool withEmpty = false, string storage = "")
            {
                // GET 	slides/{name}/textItems?appSid={appSid}&withEmpty={withEmpty}&folder={folder}&storage={storage} 

                string apiUrl = string.Format(@"slides/{0}/textItems?withEmpty={1}&folder={2}&storage={3}",
                                                                name, withEmpty, folder, storage);

                JObject jObject = JObject.Parse(ServiceController.Get(apiUrl, AppSid, AppKey));
                SlidesTextItemsResponse slidesTextItemsResponse = jObject.ToObject<SlidesTextItemsResponse>();
                return slidesTextItemsResponse;
            }

            /// <summary>
            /// Extract slide text items.	
            /// </summary>
            /// <param name="name">The document name.</param>
            /// <param name="slideIndex">Slide index</param>
            /// <param name="folder">Document's folder.</param>
            /// <param name="withEmpty">Set this to true to include items for shapes without text. (useful when you want to add text to shape using POST method). Default: false </param>
            /// <param name="storage">Document's storage.</param>
            public SlidesTextItemsResponse ExtractSlideTextItems(string name, int slideIndex, string folder, bool withEmpty = false, string storage = "")
            {
                // GET 	slides/{name}/slides/{slideIndex}/textItems?appSid={appSid}&withEmpty={withEmpty}&folder={folder}&storage={storage} 

                string apiUrl = string.Format(@"slides/{0}/slides/{1}/textItems?withEmpty={2}&folder={3}&storage={4}",
                                                                name, slideIndex, withEmpty, folder, storage);

                JObject jObject = JObject.Parse(ServiceController.Get(apiUrl, AppSid, AppKey));
                SlidesTextItemsResponse slidesTextItemsResponse = jObject.ToObject<SlidesTextItemsResponse>();
                return slidesTextItemsResponse;
            }

            /// <summary>
            /// Replace text by a new value.	
            /// </summary>
            /// <param name="name">The document name.</param>
            /// <param name="oldValue">Text value to replace.</param>
            /// <param name="newValue">The new text value.</param>
            /// <param name="ignoreCase">If false then case-sensitive search will be used. Default: true </param>
            /// <param name="folder">Document's folder.</param>
            /// <param name="storage">Document's storage.</param>
            public SlidesDocumentResponse ReplaceTextByNewValue(string name, string oldValue, string newValue, string folder, bool ignoreCase = true, string storage = "")
            {
                // POST 	slides/{name}/replaceText?oldValue={oldValue}&newValue={newValue}&appSid={appSid}&ignoreCase={ignoreCase}&folder={folder}&storage={storage} 

                string apiUrl = string.Format(@"slides/{0}/replaceText?oldValue={1}&newValue={2}&ignoreCase={3}&folder={4}&storage={5}",
                                                                name, oldValue, newValue, ignoreCase, folder, storage);

                JObject jObject = JObject.Parse(ServiceController.Post(apiUrl, AppSid, AppKey));
                SlidesDocumentResponse slidesDocumentResponse = jObject.ToObject<SlidesDocumentResponse>();
                return slidesDocumentResponse;
            }

            /// <summary>
            /// Replace text by a new value.	
            /// </summary>
            /// <param name="name">The document name.</param>
            /// <param name="slideIndex">Slide index</param>
            /// <param name="oldValue">Text value to replace.</param>
            /// <param name="newValue">The new text value.</param>
            /// <param name="ignoreCase">If false then case-sensitive search will be used. Default: true </param>
            /// <param name="folder">Document's folder.</param>
            /// <param name="storage">Document's storage.</param>
            public SlidesDocumentResponse ReplaceTextByNewValue(string name, int slideIndex, string oldValue, string newValue, string folder, bool ignoreCase = true, string storage = "")
            {
                // POST 	slides/{name}/slides/{slideIndex}/replaceText?oldValue={oldValue}&newValue={newValue}&appSid={appSid}&ignoreCase={ignoreCase}&folder={folder}&storage={storage} 

                string apiUrl = string.Format(@"slides/{0}/slides/{1}/replaceText?oldValue={2}&newValue={3}&ignoreCase={4}&folder={5}&storage={6}",
                                                                name, slideIndex, oldValue, newValue, ignoreCase, folder, storage);

                JObject jObject = JObject.Parse(ServiceController.Post(apiUrl, AppSid, AppKey));
                SlidesDocumentResponse slidesDocumentResponse = jObject.ToObject<SlidesDocumentResponse>();
                return slidesDocumentResponse;
            }
        }

        public class SlidesThemeClass
        {
            public string AppKey { get; set; }
            public string AppSid { get; set; }

            internal SlidesThemeClass(string appSid, string appKey) { AppSid = appSid; AppKey = appKey; }

            /// <summary>
            /// Read slide theme info.	
            /// </summary>
            /// <param name="name">The document name.</param>
            /// <param name="slideIndex">Slide index</param>
            /// <param name="folder">Document's folder.</param>
            /// <param name="storage">Document's storage.</param>
            public SlidesThemeResponse ReadSlideThemeInfo(string name, int slideIndex, string folder, string storage = "")
            {
                // GET 	slides/{name}/slides/{slideIndex}/theme?appSid={appSid}&folder={folder}&storage={storage} 

                string apiUrl = string.Format(@"slides/{0}/slides/{1}/theme?folder={2}&storage={3}",
                                                name, slideIndex, folder, storage);

                JObject jObject = JObject.Parse(ServiceController.Get(apiUrl, AppSid, AppKey));
                SlidesThemeResponse slidesThemeResponse = jObject.ToObject<SlidesThemeResponse>();
                return slidesThemeResponse;
            }

            /// <summary>
            /// Read slide theme color scheme info.	
            /// </summary>
            /// <param name="name">The document name.</param>
            /// <param name="slideIndex">Slide index</param>
            /// <param name="folder">Document's folder.</param>
            /// <param name="storage">Document's storage.</param>
            public SlidesColorSchemeResponse ReadSlideThemeColorSchemeInfo(string name, int slideIndex, string folder, string storage = "")
            {
                // GET 	slides/{name}/slides/{slideIndex}/theme/colorScheme?appSid={appSid}&folder={folder}&storage={storage} 

                string apiUrl = string.Format(@"slides/{0}/slides/{1}/theme/colorScheme?folder={2}&storage={3}",
                                                name, slideIndex, folder, storage);

                JObject jObject = JObject.Parse(ServiceController.Get(apiUrl, AppSid, AppKey));
                SlidesColorSchemeResponse slidesColorSchemeResponse = jObject.ToObject<SlidesColorSchemeResponse>();
                return slidesColorSchemeResponse;
            }

            /// <summary>
            /// Read slide theme font scheme info.	
            /// </summary>
            /// <param name="name">The document name.</param>
            /// <param name="slideIndex">Slide index</param>
            /// <param name="folder">Document's folder.</param>
            /// <param name="storage">Document's storage.</param>
            public SlidesFontSchemeResponse ReadSlideThemeFontSchemeInfo(string name, int slideIndex, string folder, string storage = "")
            {
                // GET 	slides/{name}/slides/{slideIndex}/theme/fontScheme?appSid={appSid}&folder={folder}&storage={storage} 

                string apiUrl = string.Format(@"slides/{0}/slides/{1}/theme/fontScheme?folder={2}&storage={3}",
                                                name, slideIndex, folder, storage);

                JObject jObject = JObject.Parse(ServiceController.Get(apiUrl, AppSid, AppKey));
                SlidesFontSchemeResponse slidesFontSchemeResponse = jObject.ToObject<SlidesFontSchemeResponse>();
                return slidesFontSchemeResponse;
            }

            /// <summary>
            /// Read slide theme color scheme info.	
            /// </summary>
            /// <param name="name">The document name.</param>
            /// <param name="slideIndex">Slide index</param>
            /// <param name="folder">Document's folder.</param>
            /// <param name="storage">Document's storage.</param>
            public SlidesFormatSchemeResponse ReadSlideThemeFormatSchemeInfo(string name, int slideIndex, string folder, string storage = "")
            {
                // GET 	slides/{name}/slides/{slideIndex}/theme/formatScheme?appSid={appSid}&folder={folder}&storage={storage} 

                string apiUrl = string.Format(@"slides/{0}/slides/{1}/theme/formatScheme?folder={2}&storage={3}",
                                                name, slideIndex, folder, storage);

                JObject jObject = JObject.Parse(ServiceController.Get(apiUrl, AppSid, AppKey));
                SlidesFormatSchemeResponse slidesFormatSchemeResponse = jObject.ToObject<SlidesFormatSchemeResponse>();
                return slidesFormatSchemeResponse;
            }
        }
    }


    public class SlidesSplitResult
    {
        public List<SlidesLink> Slides { get; set; }
        public SlidesLink SelfUri { get; set; }
        public List<SlidesLink> AlternateLinks { get; set; }
        public List<SlidesLink> Links { get; set; }
    }

    public class SlidesSplitResponse
    {
        public SlidesSplitResult SplitResult { get; set; }
        public int Code { get; set; }
        public string Status { get; set; }
    }

    public class SlidesLink
    {
        public string Href { get; set; }
        public string LinkType { get; set; }
        public string Relation { get; set; }
        public string Title { get; set; }
    }

    public class SlideImages
    {
        public SlidesLink Uri { get; set; }
        public List<SlidesList> List { get; set; }
        public SlidesLink SelfUri { get; set; }
        public List<SlidesLink> AlternateLinks { get; set; }
        public List<SlidesLink> Links { get; set; }
    }

    public class SlidesImageResponse
    {
        public SlideImages Images { get; set; }
        public int Code { get; set; }
        public string Status { get; set; }
    }

    public class Presentation
    {
        public Presentation(string path)
        {
            Path = path;
            Slides = new List<int>();
        }

        public Presentation(string path, List<int> slides)
        {
            Path = path;
            Slides = slides;
        }

        public string Path { get; set; }
        public List<int> Slides { get; set; }
    }

    public class PresentationRequest
    {
        public PresentationRequest()
        {
            Presentations = new List<Presentation>();
        }
        public List<Presentation> Presentations { get; set; }
    }

    public class Placeholders
    {
        public List<SlidesLink> PlaceholderLinks { get; set; }
        public SlidesLink SelfUri { get; set; }
        public List<SlidesLink> AlternateLinks { get; set; }
        public List<Link> Links { get; set; }
    }

    public class PlaceholdersResponse
    {
        public Placeholders Placeholders { get; set; }
        public int Code { get; set; }
        public string Status { get; set; }
    }



    public class SldiesShape
    {
        public SlidesLink Uri { get; set; }
    }


    public class Placeholder
    {
        public int Index { get; set; }
        public int Orientation { get; set; }
        public int Size { get; set; }
        public int Type { get; set; }
        public SldiesShape Shape { get; set; }
        public SlidesLink SelfUri { get; set; }
        public List<SlidesLink> AlternateLinks { get; set; }
        public List<SlidesLink> Links { get; set; }
    }

    public class PlaceholderResponse
    {
        public Placeholder Placeholder { get; set; }
        public int Code { get; set; }
        public string Status { get; set; }
    }



    public class SlidesList
    {
        public string Name { get; set; }
        public string Value { get; set; }
        public bool BuiltIn { get; set; }
        public int Width { get; set; }
        public int Height { get; set; }
        public SlidesLink SelfUri { get; set; }
        public List<SlidesLink> AlternateLinks { get; set; }
        public List<SlidesLink> Links { get; set; }
    }

    public class SlidesDocumentPropertiesResponse
    {
        public SlidesDocumentProperties DocumentProperties { get; set; }
        public int Code { get; set; }
        public string Status { get; set; }
    }


    public class SlidesDocumentProperty
    {
        public SlidesDocumentProperty()
        {
        }

        public SlidesDocumentProperty(string name, string value)
        {
            Name = name;
            Value = value;
        }

        public string Name { get; set; }
        public string Value { get; set; }
        public bool BuiltIn { get; set; }
    }

    public class SlidesDocumentPropertyResponse
    {
        public SlidesDocumentProperty DocumentProperty { get; set; }
        public int Code { get; set; }
        public string Status { get; set; }
    }

    public class SlidesDocumentPropertyRequest
    {
        public SlidesDocumentPropertyRequest()
        {
            List = new List<SlidesDocumentProperty>();
        }

        public List<SlidesDocumentProperty> List { get; set; }
        public SlidesLink SelfUri { get; set; }
        public List<SlidesLink> AlternateLinks { get; set; }
        public List<SlidesLink> Links { get; set; }
    }

    public class ShapesLink
    {
        public SlidesLink Uri { get; set; }
    }

    public class SlideShapeList
    {
        public List<ShapesLink> ShapesLinks { get; set; }
        public SlidesLink SelfUri { get; set; }
        public List<SlidesLink> AlternateLinks { get; set; }
        public List<SlidesLink> Links { get; set; }
    }

    public class SlidesShapesResponse
    {
        public SlideShapeList ShapeList { get; set; }
        public int Code { get; set; }
        public string Status { get; set; }
    }

    public class Paragraphs
    {
        public SlidesLink Uri { get; set; }
    }

    public class LineFormat
    {
        public SlidesLink Uri { get; set; }
    }

    public class SlideShape
    {
        public string Text { get; set; }
        public Paragraphs Paragraphs { get; set; }
        public int ShapeType { get; set; }
        public string Name { get; set; }
        public double Width { get; set; }
        public double Height { get; set; }
        public string AlternativeText { get; set; }
        public bool Hidden { get; set; }
        public double X { get; set; }
        public double Y { get; set; }
        public int ZOrderPosition { get; set; }
        public object Shapes { get; set; }
        public object FillFormat { get; set; }
        public LineFormat LineFormat { get; set; }
        public SlidesLink SelfUri { get; set; }
        public List<object> AlternateLinks { get; set; }
        public List<SlidesLink> Links { get; set; }
    }

    public class SlideShapeResponse
    {
        public SlideShape Shape { get; set; }
        public int Code { get; set; }
        public string Status { get; set; }
    }


    public class ParagraphLink
    {
        public SlidesLink Uri { get; set; }
    }


    public class SlideParagraphs
    {
        public List<ParagraphLink> ParagraphLinks { get; set; }
        public SlidesLink SelfUri { get; set; }
        public List<SlidesLink> AlternateLinks { get; set; }
        public List<SlidesLink> Links { get; set; }
    }

    public class SlideParagraphsResponse
    {
        public SlideParagraphs Paragraphs { get; set; }
        public int Code { get; set; }
        public string Status { get; set; }
    }


    public class PortionList
    {
        public SlidesLink Uri { get; set; }
    }

    public class SlideParagraph
    {
        public List<PortionList> PortionList { get; set; }
        public SlidesLink SelfUri { get; set; }
        public List<SlidesLink> AlternateLinks { get; set; }
        public List<SlidesLink> Links { get; set; }
    }

    public class ParagraphResponse
    {
        public SlideParagraph Paragraph { get; set; }
        public int Code { get; set; }
        public string Status { get; set; }
    }

    public class SlidesParagraphPortion
    {
        public string Text { get; set; }
        public string FontColor { get; set; }
        public double FontHeight { get; set; }
        public SlidesLink SelfUri { get; set; }
        public List<SlidesLink> AlternateLinks { get; set; }
        public List<SlidesLink> Links { get; set; }
    }

    public class SlidesParagraphPortionResponse
    {
        public SlidesParagraphPortion Portion { get; set; }
        public int Code { get; set; }
        public string Status { get; set; }
    }

    public class SlideList
    {
        public SlidesLink Uri { get; set; }
    }

    public class SlideSlides
    {
        public List<SlideList> SlideList { get; set; }
        public SlidesLink SelfUri { get; set; }
        public List<SlidesLink> AlternateLinks { get; set; }
        public List<SlidesLink> Links { get; set; }
    }

    public class SlidesResponse
    {
        public SlideSlides Slides { get; set; }
        public int Code { get; set; }
        public string Status { get; set; }
    }

    public class SlidesImages
    {
        public SlidesLink Uri { get; set; }
    }

    public class SlidesPlaceholders
    {
        public SlidesLink Uri { get; set; }
    }

    public class SlidesShapes
    {
        public SlidesLink Uri { get; set; }
    }

    public class Slide
    {
        public SlidesImages Images { get; set; }
        public SlidesPlaceholders Placeholders { get; set; }
        public SlidesShapes Shapes { get; set; }
        public SlidesTheme Theme { get; set; }
        public SlidesComments Comments { get; set; }
        public int Width { get; set; }
        public int Height { get; set; }
        public List<SlidesLink> _links { get; set; }
    }

    public class SlideResponse
    {
        public Slide Slide { get; set; }
        public int Code { get; set; }
        public string Status { get; set; }
    }

    public class SlidesBackground
    {
        public string Type { get; set; }
        public SlidesLink SelfUri { get; set; }
        public List<SlidesLink> AlternateLinks { get; set; }
        public List<SlidesLink> Links { get; set; }
    }

    public class SlidesBackgroundResponse
    {
        public SlidesBackground Background { get; set; }
        public int Code { get; set; }
        public string Status { get; set; }
    }

    public class SlidesComments
    {
        public List<SlidesLink> List { get; set; }
        public SlidesLink SelfUri { get; set; }
        public List<SlidesLink> AlternateLinks { get; set; }
        public List<SlidesLink> Links { get; set; }
        public SlidesLink Uri { get; set; }
    }

    public class SlidesCommentsResponse
    {
        public SlidesComments SlideComments { get; set; }
        public int Code { get; set; }
        public string Status { get; set; }
    }

    public class SlidesItem
    {
        public object Uri { get; set; }
        public string Text { get; set; }
    }

    public class SlidesTextItems
    {
        public List<SlidesItem> Items { get; set; }
        public SlidesLink SelfUri { get; set; }
        public List<SlidesLink> AlternateLinks { get; set; }
        public List<SlidesLink> Links { get; set; }
    }

    public class SlidesTextItemsResponse
    {
        public SlidesTextItems TextItems { get; set; }
        public int Code { get; set; }
        public string Status { get; set; }
    }







    public class SlidesDocumentProperties
    {
        public SlidesLink Uri { get; set; }
        public List<SlidesList> List { get; set; }
        public SlidesLink SelfUri { get; set; }
        public List<SlidesLink> AlternateLinks { get; set; }
        public List<SlidesLink> Links { get; set; }
    }


    public class Slides
    {
        public SlidesLink Uri { get; set; }
    }

    public class SlidesDocument
    {
        public SlidesDocumentProperties DocumentProperties { get; set; }
        public Slides Slides { get; set; }
        public SlideImages Images { get; set; }
        public SlidesLink SelfUri { get; set; }
        public List<SlidesLink> AlternateLinks { get; set; }
        public List<SlidesLink> Links { get; set; }
        public List<SlidesLink> _links { get; set; }
    }

    public class SlidesDocumentResponse
    {
        public SlidesDocument Document { get; set; }
        public int Matches { get; set; }
        public int Code { get; set; }
        public string Status { get; set; }
    }

    public class SlidesTheme
    {
        public string Name { get; set; }
        public SlidesColorScheme ColorScheme { get; set; }
        public SlidesFontScheme FontScheme { get; set; }
        public SlidesFormatScheme FormatScheme { get; set; }
        public SlidesLink SelfUri { get; set; }
        public List<SlidesLink> AlternateLinks { get; set; }
        public List<Link> Links { get; set; }
        public SlidesLink Uri { get; set; }
    }

    public class SlidesThemeResponse
    {
        public SlidesTheme Theme { get; set; }
        public int Code { get; set; }
        public string Status { get; set; }
    }

    public class SlidesColorScheme
    {
        public SlidesLink Uri { get; set; }
        public string Accent1 { get; set; }
        public string Accent2 { get; set; }
        public string Accent3 { get; set; }
        public string Accent4 { get; set; }
        public string Accent5 { get; set; }
        public string Accent6 { get; set; }
        public string Dark1 { get; set; }
        public string Dark2 { get; set; }
        public string FollowedHyperlink { get; set; }
        public string Hyperlink { get; set; }
        public string Light1 { get; set; }
        public string Light2 { get; set; }
        public SlidesLink SelfUri { get; set; }
        public List<SlidesLink> AlternateLinks { get; set; }
        public List<SlidesLink> Links { get; set; }
    }

    public class SlidesColorSchemeResponse
    {
        public SlidesColorScheme ColorScheme { get; set; }
        public int Code { get; set; }
        public string Status { get; set; }
    }

    public class SlidesMajor
    {
        public string ComplexScript { get; set; }
        public string EastAsian { get; set; }
        public string Latin { get; set; }
    }

    public class SlidesMinor
    {
        public string ComplexScript { get; set; }
        public string EastAsian { get; set; }
        public string Latin { get; set; }
    }

    public class SlidesFormatScheme
    {
        public List<SlidesLink> BackgroundStyles { get; set; }
        public List<SlidesLink> EffectStyles { get; set; }
        public List<SlidesLink> FillStyles { get; set; }
        public List<SlidesLink> LineStyles { get; set; }
        public SlidesLink SelfUri { get; set; }
        public List<SlidesLink> AlternateLinks { get; set; }
        public List<SlidesLink> Links { get; set; }
    }

    public class SlidesFormatSchemeResponse
    {
        public SlidesFormatScheme FormatScheme { get; set; }
        public int Code { get; set; }
        public string Status { get; set; }
    }

    public class SlidesFontScheme
    {
        public SlidesMajor Major { get; set; }
        public SlidesMinor Minor { get; set; }
        public string Name { get; set; }
        public SlidesLink SelfUri { get; set; }
        public List<SlidesLink> AlternateLinks { get; set; }
        public List<SlidesLink> Links { get; set; }
    }

    public class SlidesFontSchemeResponse
    {
        public SlidesFontScheme FontScheme { get; set; }
        public int Code { get; set; }
        public string Status { get; set; }
    }

}
