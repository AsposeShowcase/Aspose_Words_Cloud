using System;
using System.Collections.Generic;
using System.Text;
using Aspose.Cloud.Common;
using Newtonsoft.Json;
using Newtonsoft.Json.Linq;
using System.IO;

namespace Aspose.Cloud
{
    public class ImagingService : BaseService
    {
        public ImagingService(string appSid, string appKey)
            : base(appSid, appKey)
        {
            AppSid = appSid;
            AppKey = appKey;
            Bmp = new BmpClass(AppSid, AppKey);
            Crop = new CropClass(AppSid, AppKey);
            Frame = new FramesClass(AppSid, AppKey);
            Gif = new GifClass(AppSid, AppKey);
            Jpg = new JpgClass(AppSid, AppKey);
            Png = new PngClass(AppSid, AppKey);
            Properties = new PropertiesClass(AppSid, AppKey);
            Psd = new PsdClass(AppSid, AppKey);
            Resize = new ResizeClass(AppSid, AppKey);
            RotateFlip = new RotateFlipClass(AppSid, AppKey);
            SaveAs = new SaveAsClass(AppSid, AppKey);
            Tiff = new TiffClass(AppSid, AppKey);
            UpdateImage = new UpdateImageClass(AppSid, AppKey);
        }

        public BmpClass Bmp { get; set; }
        public CropClass Crop { get; set; }
        public FramesClass Frame { get; set; }
        public GifClass Gif { get; set; }
        public JpgClass Jpg { get; set; }
        public PngClass Png { get; set; }
        public PropertiesClass Properties { get; set; }
        public PsdClass Psd { get; set; }
        public ResizeClass Resize { get; set; }
        public RotateFlipClass RotateFlip { get; set; }
        public SaveAsClass SaveAs { get; set; }
        public TiffClass Tiff { get; set; }
        public UpdateImageClass UpdateImage { get; set; }

        public class BmpClass
        {
            public string AppKey { get; set; }
            public string AppSid { get; set; }

            internal BmpClass(string appSid, string appKey) { AppSid = appSid; AppKey = appKey; }

            /// <summary>
            /// Update parameters of existing BMP image on Aspose Cloud Storage
            /// </summary>
            /// <param name="name">Filename of existing image on Aspose Cloud Storage.</param>
            /// <param name="bitsPerPixel">Color depth.</param>
            /// <param name="horizontalResolution">New horizontal resolution.</param>
            /// <param name="verticalResolution">New vertical resolution.</param>
            /// <param name="outPath">Path to updated file. It can be a Local Path or a path on cloud storage e.g c:\out.jpg or MyFolder/out.jpg</param>
            /// <param name="folder">Folder with image to process.</param>
            /// <param name="storage">Image storage</param>
            /// <param name="fromScratch">Specifies where additional parameters we do not support should be taken from. If this is true – they will be taken from default values for standard image, if it is false – they will be saved from current image. Default is false.</param>
            public void UpdateParametersOfBmpImage(string name, int bitsPerPixel, int horizontalResolution, int verticalResolution, string outPath, string folder,
                                                   string storage = "", bool fromScratch = false)
            {
                // GET 	imaging/{name}/bmp?appSid={appSid}&bitsPerPixel={bitsPerPixel}&horizontalResolution={horizontalResolution}&verticalResolution={verticalResolution}&fromScratch={fromScratch}&outPath={outPath}&folder={folder}&storage={storage} 

                string apiUrl = string.Format(@"imaging/{0}/bmp?bitsPerPixel={1}&horizontalResolution={2}&verticalResolution={3}&fromScratch={4}&outPath={5}&folder={6}&storage={7}",
                    name, bitsPerPixel, horizontalResolution, verticalResolution, fromScratch, (outPath.Contains(@":\") ? string.Empty : outPath), folder, storage);

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
            /// Update parameters of local BMP image.
            /// </summary>
            /// <param name="bitsPerPixel">Color depth.</param>
            /// <param name="horizontalResolution">New horizontal resolution.</param>
            /// <param name="verticalResolution">New vertical resolution.</param>
            /// <param name="outPath">Path to updated file. It can be a Local Path or a path on cloud storage</param>
            /// <param name="inputFilePath">Input BMP file that will be passed as request body</param>
            /// <param name="fromScratch">Specifies where additional parameters we do not support should be taken from. If this is true – they will be taken from default values for standard image, if it is false – they will be saved from current image. Default is false.</param>
            public void UpdateParametersOfBmpImage(int bitsPerPixel, int horizontalResolution, int verticalResolution, string outPath, string inputFilePath, bool fromScratch = false)
            {
                // POST	imaging/bmp?appSid={appSid}&bitsPerPixel={bitsPerPixel}&horizontalResolution={horizontalResolution}&verticalResolution={verticalResolution}&fromScratch={fromScratch}&outPath={outPath} 

                string apiUrl = string.Format(@"imaging/bmp?bitsPerPixel={0}&horizontalResolution={1}&verticalResolution={2}&fromScratch={3}&outPath={4}",
                                                bitsPerPixel, horizontalResolution, verticalResolution, fromScratch, (outPath.Contains(@":\") ? string.Empty : outPath));

                if (!string.IsNullOrEmpty(outPath) && Directory.Exists(Path.GetDirectoryName(outPath)))
                {
                    using (Stream responseStream = ServiceController.GetStreamWithPost(apiUrl, AppSid, AppKey, File.ReadAllBytes(inputFilePath)))
                    using (Stream file = File.OpenWrite(outPath))
                    {
                        ServiceController.CopyStream(responseStream, file);
                    }
                }
                else
                {
                    ServiceController.GetStreamWithPost(apiUrl, AppSid, AppKey, File.ReadAllBytes(inputFilePath));
                }
            }
        }

        public class CropClass
        {
            public string AppKey { get; set; }
            public string AppSid { get; set; }

            internal CropClass(string appSid, string appKey) { AppSid = appSid; AppKey = appKey; }

            /// <summary>
            /// Crop existing image 
            /// </summary>
            /// <param name="name">The image name.</param>
            /// <param name="format">Output file format. Valid Formats: Bmp, png, jpg, tiff, psd, gif.</param>
            /// <param name="x">X position of start point for cropping rectangle</param>
            /// <param name="y">Y position of start point for cropping rectangle</param>
            /// <param name="width">Width of cropping rectangle</param>
            /// <param name="height">Height of cropping rectangle</param>
            /// <param name="outPath">Path to updated file. It can be a Local Path or a path on cloud storage e.g c:\out.jpg or MyFolder/out.jpg</param>
            /// <param name="folder">Folder with image to process.</param>
            /// <param name="storage">Image storage</param>
            public void CropExistingImage(string name, ImageFormat format, int x, int y, int width, int height, string outPath, string folder, string storage = "")
            {
                // GET 	 imaging/{name}/crop?appSid={appSid}&format={format}&x={x}&y={y}&width={width}&height={height}&outPath={outPath}&folder={folder}&storage={storage}

                string apiUrl = string.Format(@"imaging/{0}/crop?format={1}&x={2}&y={3}&width={4}&height={5}&outPath={6}&folder={7}&storage={8}",
                                                name, format, x, y, width, height, (outPath.Contains(@":\") ? string.Empty : outPath), folder, storage);

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
            /// Crop image
            /// </summary>
            /// <param name="format">Output file format. Valid Formats: Bmp, png, jpg, tiff, psd, gif.</param>
            /// <param name="x">X position of start point for cropping rectangle</param>
            /// <param name="y">Y position of start point for cropping rectangle</param>
            /// <param name="width">Width of cropping rectangle</param>
            /// <param name="height">Height of cropping rectangle</param>
            /// <param name="outPath">Path to updated file. It can be a Local Path or a path on cloud storage e.g c:\out.jpg or MyFolder/out.jpg</param>
            /// <param name="inputFilePath">Input file path</param>
            public void CropImage(ImageFormat format, int x, int y, int width, int height, string outPath, string inputFilePath)
            {
                // POST  imaging/crop?appSid={appSid}&format={format}&x={x}&y={y}&width={width}&height={height}&outPath={outPath}

                string apiUrl = string.Format(@"imaging/crop?format={0}&x={1}&y={2}&width={3}&height={4}&outPath={5}",
                                                format, x, y, width, height, (outPath.Contains(@":\") ? string.Empty : outPath));

                if (!string.IsNullOrEmpty(outPath) && Directory.Exists(Path.GetDirectoryName(outPath)))
                {
                    using (Stream responseStream = ServiceController.GetStreamWithPost(apiUrl, AppSid, AppKey, File.ReadAllBytes(inputFilePath)))
                    using (Stream file = File.OpenWrite(outPath))
                    {
                        ServiceController.CopyStream(responseStream, file);
                    }
                }
                else
                {
                    ServiceController.GetStreamWithPost(apiUrl, AppSid, AppKey, File.ReadAllBytes(inputFilePath));
                }
            }

        }

        public class FramesClass
        {
            public string AppKey { get; set; }
            public string AppSid { get; set; }

            internal FramesClass(string appSid, string appKey) { AppSid = appSid; AppKey = appKey; }

            /// <summary>
            /// Get separate frame of tiff image
            /// </summary>
            /// <param name="name">The image name.</param>
            /// <param name="frameId">Number of frame.</param>
            /// <param name="outPath">Path to updated file. It can be a Local Path or a path on cloud storage e.g c:\out.tiff or MyFolder/out.tiff</param>
            /// <param name="folder">Folder with image to process.</param>
            /// <param name="storage">Image storage</param>
            public void GetSeparateFrameOfTiffImage(string name, int frameId, string outPath, string folder, string storage = "")
            {
                // GET 	imaging/{name}/frames/{frameId}?appSid={appSid}&newWidth={newWidth}&newHeight={newHeight}&x={x}&y={y}&rectWidth={rectWidth}&rectHeight={rectHeight}&rotateFlipMethod={rotateFlipMethod}&saveOtherFrames={saveOtherFrames}&outPath={outPath}&folder={folder}&storage={storage} 

                string apiUrl = string.Format(@"imaging/{0}/frames/{1}?outPath={2}&folder={3}&storage={4}",
                                                name, frameId, (outPath.Contains(@":\") ? string.Empty : outPath), folder, storage);

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
            ///  Update properties of frame in existing TIFF image.
            /// </summary>
            /// <param name="name">The image name.</param>
            /// <param name="frameId">Number of frame.</param>
            /// <param name="newWidth">New width of the scaled image.</param>
            /// <param name="newHeight">New height of the scaled image.</param>
            /// <param name="x">X position of start point for cropping rectangle</param>
            /// <param name="y">Y position of start point for cropping rectangle</param>
            /// <param name="rectWidth">Width of cropping rectangle</param>
            /// <param name="rectHeight">Height of cropping rectangle</param>
            /// <param name="saveOtherFrames">Include all other frames or just specified frame in response.</param>
            /// <param name="outPath">Path to updated file. It can be a Local Path or a path on cloud storage e.g c:\out.tiff or MyFolder/out.tiff</param>
            /// <param name="folder">Folder with image to process.</param>
            /// <param name="rotateFlipMethod">RotateFlip method</param>            
            /// <param name="storage">Image storage</param>
            public void UpdatePropertiesOfFrameInExistingTiffImage(string name, int frameId, int newWidth, int newHeight, int x, int y, int rectWidth, int rectHeight, bool saveOtherFrames, string outPath, string folder, RotateFlipMethod rotateFlipMethod = RotateFlipMethod.RotateNoneFlipNone, string storage = "")
            {
                // GET 	imaging/{name}/frames/{frameId}?appSid={appSid}&newWidth={newWidth}&newHeight={newHeight}&x={x}&y={y}&rectWidth={rectWidth}&rectHeight={rectHeight}&rotateFlipMethod={rotateFlipMethod}&saveOtherFrames={saveOtherFrames}&outPath={outPath}&folder={folder}&storage={storage} 

                string apiUrl = string.Format(@"imaging/{0}/frames/{1}?newWidth={2}&newHeight={3}&x={4}&y={5}&rectWidth={6}&rectHeight={7}&rotateFlipMethod={8}&outPath={9}&folder={10}&storage={11}",
                                                name, frameId, newWidth, newHeight, x, y, rectWidth, rectHeight, rotateFlipMethod, (outPath.Contains(@":\") ? string.Empty : outPath), folder, storage);

                if (saveOtherFrames)
                {
                    apiUrl = apiUrl + string.Format("&saveOtherFrames={0}", saveOtherFrames);
                }

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
            /// Get properties of a tiff frame.
            /// </summary>
            /// <param name="name">The image name.</param>
            /// <param name="frameId">Number of frame.</param>
            /// <param name="folder">Folder with image to process.</param>
            /// <param name="storage">Image storage</param>
            /// <returns>TiffPropertiesResponse object</returns>
            public TiffPropertiesResponse GetPropertiesOfTiffFrame(string name, int frameId, string folder, string storage = "")
            {
                // GET 	imaging/{name}/frames/{frameId}/properties?appSid={appSid}&folder={folder}&storage={storage} 
                string apiUrl = string.Format(@"imaging/{0}/frames/{1}/properties?folder={2}&storage={3}",
                                                name, frameId, folder, storage);

                JObject jObject = JObject.Parse(ServiceController.Get(apiUrl, AppSid, AppKey));
                TiffPropertiesResponse tiffPropertiesResponse = jObject.ToObject<TiffPropertiesResponse>();
                return tiffPropertiesResponse;
            }

        }

        public class GifClass
        {
            public string AppKey { get; set; }
            public string AppSid { get; set; }

            internal GifClass(string appSid, string appKey) { AppSid = appSid; AppKey = appKey; }

            /// <summary>
            /// Update parameters of Gif image.	
            /// </summary>
            /// <param name="name">The image name.</param>
            /// <param name="backgroundColorIndex">Index of the background color. e.g 32</param>
            /// <param name="colorResolution">Color resolution. e.g 3</param>
            /// <param name="hasTrailer">Specifies if image has trailer.</param>
            /// <param name="interlaced">Specifies if image is interlaced.</param>
            /// <param name="isPaletteSorted">Specifies if palette is sorted.</param>
            /// <param name="pixelAspectRatio">Pixel aspect ratio. e.g 3</param>
            /// <param name="outPath">Path to updated file. It can be a Local Path or a path on cloud storage e.g c:\out.gif or MyFolder/out.gif</param>
            /// <param name="folder">Folder with image to process.</param>
            /// <param name="fromScratch">Specifies where additional parameters we do not support should be taken from. If this is true – they will be taken from default values for standard image, if it is false – they will be saved from current image.</param>
            /// <param name="storage">Image storage</param>
            public void UpdateParametersOfGifImage(string name, byte backgroundColorIndex, byte colorResolution, bool hasTrailer, bool interlaced, bool isPaletteSorted, byte pixelAspectRatio, string outPath, string folder, bool fromScratch = false, string storage = "")
            {
                // GET 	imaging/{name}/gif?appSid={appSid}&backgroundColorIndex={backgroundColorIndex}&colorResolution={colorResolution}&hasTrailer={hasTrailer}&interlaced={interlaced}&isPaletteSorted={isPaletteSorted}&pixelAspectRatio={pixelAspectRatio}&fromScratch={fromScratch}&outPath={outPath}&folder={folder}&storage={storage}

                string apiUrl = string.Format(@"imaging/{0}/gif?backgroundColorIndex={1}&colorResolution={2}&hasTrailer={3}&interlaced={4}&isPaletteSorted={5}&pixelAspectRatio={6}&fromScratch={7}&outPath={8}&folder={9}&storage={10}",
                                                name, backgroundColorIndex, colorResolution, hasTrailer, interlaced, isPaletteSorted, pixelAspectRatio, fromScratch, (outPath.Contains(@":\") ? string.Empty : outPath), folder, storage);

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

            //Update parameters of gif image.
            /// <summary>
            /// 
            /// </summary>
            /// <param name="backgroundColorIndex">Index of the background color. e.g 32</param>
            /// <param name="colorResolution">Color resolution. e.g 3</param>
            /// <param name="hasTrailer">Specifies if image has trailer.</param>
            /// <param name="interlaced">Specifies if image is interlaced.</param>
            /// <param name="isPaletteSorted">Specifies if palette is sorted.</param>
            /// <param name="pixelAspectRatio">Pixel aspect ratio. e.g 3</param>
            /// <param name="outPath">Path to updated file. It can be a Local Path or a path on cloud storage e.g c:\out.gif or MyFolder/out.gif</param>
            /// <param name="inputFilePath">Input Gif file that will be passed as request body</param>
            /// <param name="fromScratch">Specifies where additional parameters we do not support should be taken from. If this is true – they will be taken from default values for standard image, if it is false – they will be saved from current image.</param>
            public void UpdateParametersOfGifImage(byte backgroundColorIndex, byte colorResolution, bool hasTrailer, bool interlaced, bool isPaletteSorted, byte pixelAspectRatio, string outPath, string inputFilePath, bool fromScratch = false)
            {
                // POST imaging/gif?appSid={appSid}&backgroundColorIndex={backgroundColorIndex}&colorResolution={colorResolution}&hasTrailer={hasTrailer}&interlaced={interlaced}&isPaletteSorted={isPaletteSorted}&pixelAspectRatio={pixelAspectRatio}&fromScratch={fromScratch}&outPath={outPath}

                string apiUrl = string.Format(@"imaging/gif?backgroundColorIndex={0}&colorResolution={1}&hasTrailer={2}&interlaced={3}&isPaletteSorted={4}&pixelAspectRatio={5}&fromScratch={6}&outPath={7}",
                                                backgroundColorIndex, colorResolution, hasTrailer, interlaced, isPaletteSorted, pixelAspectRatio, fromScratch, (outPath.Contains(@":\") ? string.Empty : outPath));

                if (!string.IsNullOrEmpty(outPath) && Directory.Exists(Path.GetDirectoryName(outPath)))
                {
                    using (Stream responseStream = ServiceController.GetStreamWithPost(apiUrl, AppSid, AppKey, File.ReadAllBytes(inputFilePath)))
                    using (Stream file = File.OpenWrite(outPath))
                    {
                        ServiceController.CopyStream(responseStream, file);
                    }
                }
                else
                {
                    ServiceController.GetStreamWithPost(apiUrl, AppSid, AppKey, File.ReadAllBytes(inputFilePath));
                }
            }
        }

        public class JpgClass
        {
            public string AppKey { get; set; }
            public string AppSid { get; set; }

            internal JpgClass(string appSid, string appKey) { AppSid = appSid; AppKey = appKey; }

            /// <summary>
            /// Update parameters of jpg image.
            /// </summary>
            /// <param name="name">The image name.</param>
            /// <param name="outPath">Path to updated file. It can be a Local Path or a path on cloud storage e.g c:\out.jpg or MyFolder/out.jpg</param>
            /// <param name="folder">Folder with image to process.</param>
            /// <param name="quality">Quality of image. From 0 to 100. Default is 75. </param>
            /// <param name="compressionType"> Compression type. </param>
            /// <param name="fromScratch">Specifies where additional parameters we do not support should be taken from. If this is true – they will be taken from default values for standard image, if it is false – they will be saved from current image. Default is false.</param>
            /// <param name="storage">Image storage</param>
            public void UpdateParametersOfJpgImage(string name, string outPath, string folder, int quality = 75, CompressionType compressionType = CompressionType.Baseline, bool fromScratch = false, string storage = "")
            {
                // GET 	imaging/{name}/jpg?appSid={appSid}&quality={quality}&compressionType={compressionType}&fromScratch={fromScratch}&outPath={outPath}&folder={folder}&storage={storage} 

                string apiUrl = string.Format(@"imaging/{0}/jpg?quality={1}&compressionType={2}&fromScratch={3}&outPath={4}&folder={5}&storage={6}",
                                                name, quality, compressionType, fromScratch, (outPath.Contains(@":\") ? string.Empty : outPath), folder, storage);

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
            /// Update parameters of jpg image.
            /// </summary>
            /// <param name="outPath">Path to updated file. It can be a Local Path or a path on cloud storage e.g c:\out.jpg or MyFolder/out.jpg</param>
            /// <param name="inputFilePath">Input Jpg file that will be passed as request body</param>
            /// <param name="quality">Quality of image. From 0 to 100. Default is 75. </param>
            /// <param name="compressionType"> Compression type. </param>
            /// <param name="fromScratch">Specifies where additional parameters we do not support should be taken from. If this is true – they will be taken from default values for standard image, if it is false – they will be saved from current image. Default is false.</param>
            public void UpdateParametersOfJpgImage(string outPath, string inputFilePath, int quality = 75, CompressionType compressionType = CompressionType.Baseline, bool fromScratch = false)
            {
                // POST imaging/jpg?appSid={appSid}&quality={quality}&compressionType={compressionType}&fromScratch={fromScratch}&outPath={outPath}

                string apiUrl = string.Format(@"imaging/jpg?quality={0}&compressionType={1}&fromScratch={2}&outPath={3}",
                                quality, compressionType, fromScratch, (outPath.Contains(@":\") ? string.Empty : outPath));

                if (!string.IsNullOrEmpty(outPath) && Directory.Exists(Path.GetDirectoryName(outPath)))
                {
                    using (Stream responseStream = ServiceController.GetStreamWithPost(apiUrl, AppSid, AppKey, File.ReadAllBytes(inputFilePath)))
                    using (Stream file = File.OpenWrite(outPath))
                    {
                        ServiceController.CopyStream(responseStream, file);
                    }
                }
                else
                {
                    ServiceController.GetStreamWithPost(apiUrl, AppSid, AppKey, File.ReadAllBytes(inputFilePath));
                }
            }
        }

        public class PngClass
        {
            public string AppKey { get; set; }
            public string AppSid { get; set; }

            internal PngClass(string appSid, string appKey) { AppSid = appSid; AppKey = appKey; }

            //Update parameters of png image.	
            /// <summary>
            /// 
            /// </summary>
            /// <param name="name">The image name.</param>
            /// <param name="outPath">Path to updated file. It can be a Local Path or a path on cloud storage e.g c:\out.png or MyFolder/out.png</param>
            /// <param name="folder">Folder with image to process.</param>
            /// <param name="storage">Image storage</param>
            /// <param name="fromScratch">Specifies where additional parameters we do not support should be taken from. If this is true – they will be taken from default values for standard image, if it is false – they will be saved from current image. Default is false.</param>                        
            public void UpdateParametersOfPngImage(string name, string outPath, string folder, string storage = "", bool fromScratch = false)
            {
                // GET 	imaging/{name}/png?appSid={appSid}&fromScratch={fromScratch}&outPath={outPath}&folder={folder}&storage={storage} 

                string apiUrl = string.Format(@"imaging/{0}/png?fromScratch={1}&outPath={2}&folder={3}&storage={4}",
                                                name, fromScratch, (outPath.Contains(@":\") ? string.Empty : outPath), folder, storage);

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
            /// Update parameters of png image.
            /// </summary>            
            /// <param name="outPath">Path to updated file. It can be a Local Path or a path on cloud storage e.g c:\out.png or MyFolder/out.png</param>
            /// <param name="inputFilePath">Input Jpg file that will be passed as request body</param>
            /// <param name="fromScratch">Specifies where additional parameters we do not support should be taken from. If this is true – they will be taken from default values for standard image, if it is false – they will be saved from current image. Default is false.</param>                        
            public void UpdateParametersOfPngImage(string outPath, string inputFilePath, bool fromScratch = false)
            {
                // POST imaging/png?appSid={appSid}&fromScratch={fromScratch}&outPath={outPath}

                string apiUrl = string.Format(@"imaging/png?fromScratch={0}&outPath={1}", fromScratch, (outPath.Contains(@":\") ? string.Empty : outPath));

                if (!string.IsNullOrEmpty(outPath) && Directory.Exists(Path.GetDirectoryName(outPath)))
                {
                    using (Stream responseStream = ServiceController.GetStreamWithPost(apiUrl, AppSid, AppKey, File.ReadAllBytes(inputFilePath)))
                    using (Stream file = File.OpenWrite(outPath))
                    {
                        ServiceController.CopyStream(responseStream, file);
                    }
                }
                else
                {
                    ServiceController.GetStreamWithPost(apiUrl, AppSid, AppKey, File.ReadAllBytes(inputFilePath));
                }
            }
        }

        public class PropertiesClass
        {
            public string AppKey { get; set; }
            public string AppSid { get; set; }

            internal PropertiesClass(string appSid, string appKey) { AppSid = appSid; AppKey = appKey; }

            //Get properties of an image.	
            /// <summary>
            /// 
            /// </summary>
            /// <param name="name">The image name.</param>
            /// <param name="folder">Folder with image to process.</param>
            /// <param name="storage">Image storage</param>
            public ImagePropertiesResponse GetPropertiesOfAnImage(string name, string folder, string storage = "")
            {
                // GET 	imaging/{name}/properties?appSid={appSid}&folder={folder}&storage={storage} 

                string apiUrl = string.Format(@"imaging/{0}/properties?folder={1}&storage={2}",
                                                name, folder, storage);

                JObject jObject = JObject.Parse(ServiceController.Get(apiUrl, AppSid, AppKey));
                ImagePropertiesResponse imagePropertiesResponse = jObject.ToObject<ImagePropertiesResponse>();
                return imagePropertiesResponse;
            }
        }

        public class PsdClass
        {
            public string AppKey { get; set; }
            public string AppSid { get; set; }

            internal PsdClass(string appSid, string appKey) { AppSid = appSid; AppKey = appKey; }

            //Update parameters of psd image.	
            /// <summary>
            /// 
            /// </summary>
            /// <param name="name">The image name.</param>
            /// <param name="channelsCount">Count of channels. e.g. 4</param>
            /// <param name="outPath">Path to updated file. It can be a Local Path or a path on cloud storage e.g c:\out.psd or MyFolder/out.psd</param>
            /// <param name="folder">Folder with image to process.</param>
            /// <param name="compressionMethod">Compression method.  raw, rle. Default is raw.</param>
            /// <param name="fromScratch">Specifies where additional parameters we do not support should be taken from. If this is true – they will be taken from default values for standard image, if it is false – they will be saved from current image. Default is false.</param>                        
            /// <param name="storage">Image storage</param>
            public void UpdateParametersOfPsdImage(string name, int channelsCount, string outPath, string folder, CompressionMethod compressionMethod = CompressionMethod.RAW, bool fromScratch = false, string storage = "")
            {
                // GET 	imaging/{name}/psd?appSid={appSid}&channelsCount={channelsCount}&compressionMethod={compressionMethod}&fromScratch={fromScratch}&outPath={outPath}&folder={folder}&storage={storage} 

                string apiUrl = string.Format(@"imaging/{0}/psd?channelsCount={1}&compressionMethod={2}&fromScratch={3}&outPath={4}&folder={5}&storage={6}",
                                                name, channelsCount, compressionMethod, fromScratch, (outPath.Contains(@":\") ? string.Empty : outPath), folder, storage);

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
            /// Update parameters of psd image.
            /// </summary>
            /// <param name="channelsCount">Count of channels. e.g. 4</param>
            /// <param name="outPath">Path to updated file. It can be a Local Path or a path on cloud storage e.g c:\out.psd or MyFolder/out.psd</param>
            /// <param name="inputFilePath">Input Jpg file that will be passed as request body</param>
            /// <param name="compressionMethod">Compression method.  raw, rle. Default is raw.</param>
            /// <param name="fromScratch">Specifies where additional parameters we do not support should be taken from. If this is true – they will be taken from default values for standard image, if it is false – they will be saved from current image. Default is false.</param>                        
            public void UpdateParametersOfPsdImage(int channelsCount, string outPath, string inputFilePath, CompressionMethod compressionMethod = CompressionMethod.RAW, bool fromScratch = false)
            {
                // POST 	imaging/psd?appSid={appSid}&channelsCount={channelsCount}&compressionMethod={compressionMethod}&fromScratch={fromScratch}&outPath={outPath} 
                string apiUrl = string.Format(@"imaging/psd?channelsCount={0}&compressionMethod={1}&fromScratch={2}&outPath={3}",
                                                channelsCount, compressionMethod, fromScratch, (outPath.Contains(@":\") ? string.Empty : outPath));

                if (!string.IsNullOrEmpty(outPath) && Directory.Exists(Path.GetDirectoryName(outPath)))
                {
                    using (Stream responseStream = ServiceController.GetStreamWithPost(apiUrl, AppSid, AppKey, File.ReadAllBytes(inputFilePath)))
                    using (Stream file = File.OpenWrite(outPath))
                    {
                        ServiceController.CopyStream(responseStream, file);
                    }
                }
                else
                {
                    ServiceController.GetStreamWithPost(apiUrl, AppSid, AppKey, File.ReadAllBytes(inputFilePath));
                }
            }
        }

        public class ResizeClass
        {
            public string AppKey { get; set; }
            public string AppSid { get; set; }

            internal ResizeClass(string appSid, string appKey) { AppSid = appSid; AppKey = appKey; }

            /// <summary>
            /// Change scale of an existing image	 
            /// </summary>
            /// <param name="name">The image name.</param>
            /// <param name="format">Output file format. Valid Formats: Bmp, png, jpg, tiff, psd, gif.</param>
            /// <param name="newWidth">New width of the scaled image.</param>
            /// <param name="newHeight">New height of the scaled image.</param>
            /// <param name="outPath">Path to updated file. It can be a Local Path or a path on cloud storage e.g c:\out.jpg or MyFolder/out.jpg</param>
            /// <param name="folder">Folder with image to process.</param>
            /// <param name="storage">Image storage</param>
            public void ChangeScaleOfAnExistingImage(string name, ImageFormat format, int newWidth, int newHeight, string outPath, string folder, string storage = "")
            {
                // GET 	imaging/{name}/resize?appSid={appSid}&format={format}&newWidth={newWidth}&newHeight={newHeight}&outPath={outPath}&folder={folder}&storage={storage} 

                string apiUrl = string.Format(@"imaging/{0}/resize?format={1}&newWidth={2}&newHeight={3}&outPath={4}&folder={5}&storage={6}",
                                                name, format, newWidth, newHeight, (outPath.Contains(@":\") ? string.Empty : outPath), folder, storage);

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
            /// Change scale of an image
            /// </summary>
            /// <param name="format">Output file format. Valid Formats: Bmp, png, jpg, tiff, psd, gif.</param>
            /// <param name="newWidth">New width of the scaled image.</param>
            /// <param name="newHeight">New height of the scaled image.</param>
            /// <param name="outPath">Path to updated file. It can be a Local Path or a path on cloud storage e.g c:\out.jpg or MyFolder/out.jpg</param>
            /// <param name="inputFilePath">Input Jpg file that will be passed as request body</param>
            public void ChangeScaleOfAnImage(ImageFormat format, int newWidth, int newHeight, string outPath, string inputFilePath)
            {
                // POST imaging/resize?appSid={appSid}&format={format}&newWidth={newWidth}&newHeight={newHeight}&outPath={outPath} 

                string apiUrl = string.Format(@"imaging/resize?format={0}&newWidth={1}&newHeight={2}&outPath={3}",
                                                format, newWidth, newHeight, (outPath.Contains(@":\") ? string.Empty : outPath));

                if (!string.IsNullOrEmpty(outPath) && Directory.Exists(Path.GetDirectoryName(outPath)))
                {
                    using (Stream responseStream = ServiceController.GetStreamWithPost(apiUrl, AppSid, AppKey, File.ReadAllBytes(inputFilePath)))
                    using (Stream file = File.OpenWrite(outPath))
                    {
                        ServiceController.CopyStream(responseStream, file);
                    }
                }
                else
                {
                    ServiceController.GetStreamWithPost(apiUrl, AppSid, AppKey, File.ReadAllBytes(inputFilePath));
                }
            }
        }

        public class RotateFlipClass
        {
            public string AppKey { get; set; }
            public string AppSid { get; set; }

            internal RotateFlipClass(string appSid, string appKey) { AppSid = appSid; AppKey = appKey; }

            /// <summary>
            /// Rotate and flip existing image	
            /// </summary>
            /// <param name="name">The image name.</param>
            /// <param name="format">Output file format.</param>
            /// <param name="method">RotateFlip method.</param>
            /// <param name="outPath">Path to updated file. It can be a Local Path or a path on cloud storage e.g c:\out.jpg or MyFolder/out.jpg</param>
            /// <param name="folder">Folder with image to process.</param>
            /// <param name="storage">Image storage</param>
            public void RotateFlipExistingImage(string name, ImageFormat format, RotateFlipMethod method, string outPath, string folder, string storage = "")
            {
                // GET 	imaging/{name}/rotateflip?format={format}&appSid={appSid}&method={method}&outPath={outPath}&folder={folder}&storage={storage} 

                string apiUrl = string.Format(@"imaging/{0}/rotateflip?format={1}&method={2}&outPath={3}&folder={4}&storage={5}",
                                                name, format, method, (outPath.Contains(@":\") ? string.Empty : outPath), folder, storage);

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
            /// Rotate and flip an image
            /// </summary>
            /// <param name="format">Output file format.</param>
            /// <param name="method">RotateFlip method.</param>
            /// <param name="outPath">Path to updated file. It can be a Local Path or a path on cloud storage e.g c:\out.jpg or MyFolder/out.jpg</param>
            /// <param name="inputFilePath">Input Jpg file that will be passed as request body</param>
            public void RotateFlipImage(ImageFormat format, RotateFlipMethod method, string outPath, string inputFilePath)
            {
                // POST imaging/rotateflip?format={format}&appSid={appSid}&method={method}&outPath={outPath} 

                string apiUrl = string.Format(@"imaging/rotateflip?format={0}&method={1}&outPath={2}",
                                                format, method, (outPath.Contains(@":\") ? string.Empty : outPath));

                if (!string.IsNullOrEmpty(outPath) && Directory.Exists(Path.GetDirectoryName(outPath)))
                {
                    using (Stream responseStream = ServiceController.GetStreamWithPost(apiUrl, AppSid, AppKey, File.ReadAllBytes(inputFilePath)))
                    using (Stream file = File.OpenWrite(outPath))
                    {
                        ServiceController.CopyStream(responseStream, file);
                    }
                }
                else
                {
                    ServiceController.GetStreamWithPost(apiUrl, AppSid, AppKey, File.ReadAllBytes(inputFilePath));
                }
            }
        }

        public class SaveAsClass
        {
            public string AppKey { get; set; }
            public string AppSid { get; set; }

            internal SaveAsClass(string appSid, string appKey) { AppSid = appSid; AppKey = appKey; }

            /// <summary>
            /// Export existing image to another format	
            /// </summary>
            /// <param name="name">The image name.</param>
            /// <param name="format">Output file format. (Bmp, png, jpg, tiff, psd, gif.)</param>
            /// <param name="outPath">Path to updated file. It can be a Local Path or a path on cloud storage e.g c:\out.jpg or MyFolder/out.jpg</param>
            /// <param name="folder">Folder with image to process.</param>
            /// <param name="storage">Image storage</param>
            public void ExportExistingImageToAnotherFormat(string name, ImageFormat format, string outPath, string folder, string storage = "")
            {

                // GET 	imaging/{name}/saveAs?appSid={appSid}&format={format}&outPath={outPath}&folder={folder}&storage={storage} 

                string apiUrl = string.Format(@"imaging/{0}/saveAs?format={1}&outPath={2}&folder={3}&storage={4}",
                                                name, format, (outPath.Contains(@":\") ? string.Empty : outPath), folder, storage);

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
            /// Export image to another format. Image is passed as request body.	
            /// </summary>
            /// <param name="format">Output file format. (Bmp, png, jpg, tiff, psd, gif.)</param>
            /// <param name="outPath">Path to updated file. It can be a Local Path or a path on cloud storage e.g c:\out.jpg or MyFolder/out.jpg</param>
            /// <param name="inputFilePath">Input Jpg file that will be passed as request body</param>
            public void ExportImageToAnotherFormat(ImageFormat format, string outPath, string inputFilePath)
            {
                // POST imaging/saveAs?appSid={appSid}&format={format}&outPath={outPath} 

                string apiUrl = string.Format(@"imaging/saveAs?format={0}&outPath={1}",
                                                format, (outPath.Contains(@":\") ? string.Empty : outPath));

                if (!string.IsNullOrEmpty(outPath) && Directory.Exists(Path.GetDirectoryName(outPath)))
                {
                    using (Stream responseStream = ServiceController.GetStreamWithPost(apiUrl, AppSid, AppKey, File.ReadAllBytes(inputFilePath)))
                    using (Stream file = File.OpenWrite(outPath))
                    {
                        ServiceController.CopyStream(responseStream, file);
                    }
                }
                else
                {
                    ServiceController.GetStreamWithPost(apiUrl, AppSid, AppKey, File.ReadAllBytes(inputFilePath));
                }
            }
        }

        public class TiffClass
        {
            public string AppKey { get; set; }
            public string AppSid { get; set; }

            internal TiffClass(string appSid, string appKey) { AppSid = appSid; AppKey = appKey; }

            /// <summary>
            /// Update tiff image.
            /// </summary>
            /// <param name="compression">Tiff compression</param>
            /// <param name="resolutionUnit">Resolution unit: none, inch, centimeter. </param>
            /// <param name="horizontalResolution">Horizontal resolution</param>
            /// <param name="verticalResolution">Vertical resolution</param>
            /// <param name="outPath">Path to updated file. Only cloud storage path is supported e.g MyFolder/tiff.jpg</param>
            /// <param name="inputFilePath">Input Jpg file that will be passed as request body</param>
            /// <param name="bitDepth">Color depth. Currently bitDepth = 1 is fully tested only. </param>            
            /// <param name="fromScratch">Specifies where additional parameters we do not support should be taken from. If this is true – they will be taken from default values for standard image, if it is false – they will be saved from current image. Default is false.</param>            
            public void UpdateTiffImage(TiffFacCompressionType compression, ResolutionUnit resolutionUnit, int horizontalResolution, int verticalResolution, string outPath, string inputFilePath, short bitDepth = 1, bool fromScratch = false)
            {
                // POST 	imaging/tiff?appSid={appSid}&compression={compression}&resolutionUnit={resolutionUnit}&bitDepth={bitDepth}&fromScratch={fromScratch}&horizontalResolution={horizontalResolution}&verticalResolution={verticalResolution}&outPath={outPath} 

                string apiUrl = string.Format(@"imaging/tiff?compression={0}&resolutionUnit={1}&bitDepth={2}&fromScratch={3}&horizontalResolution={4}&verticalResolution={5}&outPath={6}",
                                                compression, resolutionUnit, bitDepth, fromScratch, horizontalResolution, verticalResolution, outPath);

                if (!string.IsNullOrEmpty(outPath) && Directory.Exists(Path.GetDirectoryName(outPath)))
                {
                    using (Stream responseStream = ServiceController.GetStreamWithPost(apiUrl, AppSid, AppKey, File.ReadAllBytes(inputFilePath)))
                    using (Stream file = File.OpenWrite(outPath))
                    {
                        ServiceController.CopyStream(responseStream, file);
                    }
                }
                else
                {
                    ServiceController.GetStreamWithPost(apiUrl, AppSid, AppKey, File.ReadAllBytes(inputFilePath));
                }
            }

            /// <summary>
            /// Get tiff image for fax.	
            /// </summary>
            /// <param name="name">The image name.</param>
            /// <param name="storage">Image storage</param>
            /// <param name="folder">Folder with image to process.</param>
            /// <param name="outPath">Path to updated file. Only cloud storage path is supported e.g MyFolder/tiff.jpg</param>
            public void GetTiffImageForFax(string name, string folder, string outPath, string storage = "")
            {
                // GET 	imaging/tiff/{name}/toFax?appSid={appSid}&storage={storage}&folder={folder}&outPath={outPath} 

                string apiUrl = string.Format(@"imaging/tiff/{0}/toFax?storage={1}&folder={2}&outPath={3}",
                                                name, storage, folder, outPath);

                ServiceController.Get(apiUrl, AppSid, AppKey);
            }

            /// <summary>
            /// Append tiff image.
            /// </summary>
            /// <param name="name">The image name. e.g Folder/tiff1.tff</param>
            /// <param name="appendFile">Second image file name. e.g Folder/tiff2.tff</param>
            /// <param name="storage">Image storage</param>
            /// <param name="folder">Folder with image to process.</param>
            public void AppendTiffImage(string name, string appendFile, string folder, string storage = "")
            {
                // POST 	imaging/tiff/{name}/appendTiff?appSid={appSid}&appendFile={appendFile}&storage={storage}&folder={folder} 

                string apiUrl = string.Format(@"imaging/tiff/{0}/appendTiff?appendFile={1}&storage={2}&folder={3}",
                                                name, appendFile, storage, folder);

                ServiceController.Post(apiUrl, AppSid, AppKey);
            }
        }

        public class UpdateImageClass
        {
            public string AppKey { get; set; }
            public string AppSid { get; set; }

            internal UpdateImageClass(string appSid, string appKey) { AppSid = appSid; AppKey = appKey; }

            /// <summary>
            /// Perform scaling, cropping and flipping of an image in single request.	
            /// </summary>
            /// <param name="name">The image name.</param>
            /// <param name="format">Save image in another format. By default format remains the same</param>
            /// <param name="newWidth">New Width of the scaled image.</param>
            /// <param name="newHeight">New height of the scaled image.</param>
            /// <param name="x">X position of start point for cropping rectangle</param>
            /// <param name="y">Y position of start point for cropping rectangle</param>
            /// <param name="rectWidth">Width of cropping rectangle</param>
            /// <param name="rectHeight">Height of cropping rectangle</param>
            /// <param name="outPath">Path to updated file. It can be a Local Path or a path on cloud storage e.g c:\out.jpg or MyFolder/out.jpg</param>
            /// <param name="folder">Folder with image to process.</param>
            /// <param name="rotateFlipMethod">RotateFlip method. Default is RotateNoneFlipNone.</param>
            /// <param name="storage">Image storage</param>
            public void PerformScalingCroppingAndFlippingOfAnImage(string name, ImageFormat format, int newWidth, int newHeight, int x, int y, int rectWidth, int rectHeight, string outPath, string folder, RotateFlipMethod rotateFlipMethod = RotateFlipMethod.RotateNoneFlipNone, string storage = "")
            {
                // GET 	imaging/{name}/updateImage?appSid={appSid}&format={format}&newWidth={newWidth}&newHeight={newHeight}&x={x}&y={y}&rectWidth={rectWidth}&rectHeight={rectHeight}&rotateFlipMethod={rotateFlipMethod}&outPath={outPath}&folder={folder}&storage={storage} 

                string apiUrl = string.Format(@"imaging/{0}/updateImage?format={1}&newWidth={2}&newHeight={3}&x={4}&y={5}&rectWidth={6}&rectHeight={7}&rotateFlipMethod={8}&outPath={9}&folder={10}&storage={11}",
                                                name, format, newWidth, newHeight, x, y, rectWidth, rectHeight, rotateFlipMethod, (outPath.Contains(@":\") ? string.Empty : outPath), folder, storage);

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
            /// Perform scaling, cropping and flipping of an image in single request. Image is passed as request body.
            /// </summary>
            /// <param name="format">Save image in another format. By default format remains the same</param>
            /// <param name="newWidth">New Width of the scaled image.</param>
            /// <param name="newHeight">New height of the scaled image.</param>
            /// <param name="x">X position of start point for cropping rectangle</param>
            /// <param name="y">Y position of start point for cropping rectangle</param>
            /// <param name="rectWidth">Width of cropping rectangle</param>
            /// <param name="rectHeight">Height of cropping rectangle</param>
            /// <param name="outPath">Path to updated file. It can be a Local Path or a path on cloud storage e.g c:\out.jpg or MyFolder/out.jpg</param>
            /// <param name="inputFilePath">Input Jpg file that will be passed as request body</param>
            /// <param name="rotateFlipMethod">RotateFlip method. Default is RotateNoneFlipNone.</param>
            public void PerformScalingCroppingAndFlippingOfAnImage(ImageFormat format, int newWidth, int newHeight, int x, int y, int rectWidth, int rectHeight, string outPath, string inputFilePath, RotateFlipMethod rotateFlipMethod = RotateFlipMethod.RotateNoneFlipNone)
            {
                // POST 	imaging/updateImage?appSid={appSid}&format={format}&newWidth={newWidth}&newHeight={newHeight}&x={x}&y={y}&rectWidth={rectWidth}&rectHeight={rectHeight}&rotateFlipMethod={rotateFlipMethod}&outPath={outPath} 

                string apiUrl = string.Format(@"imaging/updateImage?format={0}&newWidth={1}&newHeight={2}&x={3}&y={4}&rectWidth={5}&rectHeight={6}&rotateFlipMethod={7}&outPath={8}",
                                                format, newWidth, newHeight, x, y, rectWidth, rectHeight, rotateFlipMethod, (outPath.Contains(@":\") ? string.Empty : outPath));

                if (!string.IsNullOrEmpty(outPath) && Directory.Exists(Path.GetDirectoryName(outPath)))
                {
                    using (Stream responseStream = ServiceController.GetStreamWithPost(apiUrl, AppSid, AppKey, File.ReadAllBytes(inputFilePath)))
                    using (Stream file = File.OpenWrite(outPath))
                    {
                        ServiceController.CopyStream(responseStream, file);
                    }
                }
                else
                {
                    ServiceController.GetStreamWithPost(apiUrl, AppSid, AppKey, File.ReadAllBytes(inputFilePath));
                }
            }
        }
    }

    public enum CompressionMethod
    {
        RAW, RLE
    }

    public enum ResolutionUnit
    {
        None, Inch, Centimeter
    }

    public enum TiffFacCompressionType
    {
        CcittFax3, CcittFax4
    }

    public enum CompressionType
    {
        Baseline, Progressive
    }

    public enum ImageFormat
    {
        Bmp, Png, Jpg, Tiff, Psd, Gif
    }

    public enum RotateFlipMethod
    {
        Rotate180FlipNone, Rotate180FlipX, Rotate180FlipXY, Rotate180FlipY,
        Rotate270FlipNone, Rotate270FlipX, Rotate270FlipXY, Rotate270FlipY, Rotate90FlipNone, Rotate90FlipX, Rotate90FlipXY,
        Rotate90FlipY, RotateNoneFlipNone, RotateNoneFlipX, RotateNoneFlipXY, RotateNoneFlipY
    }

    public class BmpProperties
    {
        public string Compression { get; set; }
    }

    public class GifProperties
    {
        public string BackgroundColor { get; set; }
        public bool HasTrailer { get; set; }
        public int PixelAspectRatio { get; set; }
    }

    

    public class JpegJfifData
    {
        public string DensityUnits { get; set; }
        public int Version { get; set; }
        public int XDensity { get; set; }
        public int YDensity { get; set; }
    }

    public class JpegProperties
    {
        public List<string> Comment { get; set; }
        public JpegExifData JpegExifData { get; set; }
        public JpegJfifData JpegJfifData { get; set; }
    }

    public class PngProperties
    {
    }

    public class JpegExifData
    {
        public string Artist { get; set; }
        public string Copyright { get; set; }
        public string DateTime { get; set; }
        public string ImageDescription { get; set; }
        public string Make { get; set; }
        public string Model { get; set; }
        public string Orientation { get; set; }
        public List<double> PrimaryChromaticities { get; set; }
        public List<double> ReferenceBlackWhite { get; set; }
        public string ResolutionUnit { get; set; }
        public string Software { get; set; }
        public List<int> TransferFunction { get; set; }
        public double XResolution { get; set; }
        public List<double> YCbCrCoefficients { get; set; }
        public string YCbCrPositioning { get; set; }
        public double YResolution { get; set; }
        public double ApertureValue { get; set; }
        public string BodySerialNumber { get; set; }
        public double BrightnessValue { get; set; }
        public string CFAPattern { get; set; }
        public string CameraOwnerName { get; set; }
        public string ColorSpace { get; set; }
        public string ComponentsConfiguration { get; set; }
        public double CompressedBitsPerPixel { get; set; }
        public string Contrast { get; set; }
        public string CustomRendered { get; set; }
        public string DateTimeDigitized { get; set; }
        public string DateTimeOriginal { get; set; }
        public string DeviceSettingDescription { get; set; }
        public double DigitalZoomRatio { get; set; }
        public string ExifVersion { get; set; }
        public double ExposureBiasValue { get; set; }
        public double ExposureIndex { get; set; }
        public string ExposureMode { get; set; }
        public string ExposureProgram { get; set; }
        public double ExposureTime { get; set; }
        public double FNumber { get; set; }
        public string FileSource { get; set; }
        public string Flash { get; set; }
        public double FlashEnergy { get; set; }
        public string FlashpixVersion { get; set; }
        public double FocalLength { get; set; }
        public int FocalLengthIn35MmFilm { get; set; }
        public string FocalPlaneResolutionUnit { get; set; }
        public double FocalPlaneXResolution { get; set; }
        public double FocalPlaneYResolution { get; set; }
        public double GPSAltitude { get; set; }
        public string GPSAltitudeRef { get; set; }
        public string GPSAreaInformation { get; set; }
        public double GPSDOP { get; set; }
        public double GPSDestBearing { get; set; }
        public string GPSDestBearingRef { get; set; }
        public double GPSDestDistance { get; set; }
        public string GPSDestDistanceRef { get; set; }
        public object GPSDestLatitude { get; set; }
        public string GPSDestLatitudeRef { get; set; }
        public object GPSDestLongitude { get; set; }
        public string GPSDestLongitudeRef { get; set; }
        public int GPSDifferential { get; set; }
        public double GPSImgDirection { get; set; }
        public string GPSImgDirectionRef { get; set; }
        public string GPSDateStamp { get; set; }
        public object GPSLatitude { get; set; }
        public string GPSLatitudeRef { get; set; }
        public object GPSLongitude { get; set; }
        public string GPSLongitudeRef { get; set; }
        public string GPSMapDatum { get; set; }
        public string GPSMeasureMode { get; set; }
        public string GPSProcessingMethod { get; set; }
        public string GPSSatellites { get; set; }
        public double GPSSpeed { get; set; }
        public string GPSSpeedRef { get; set; }
        public string GPSStatus { get; set; }
        public object GPSTimestamp { get; set; }
        public string GPSTrack { get; set; }
        public string GPSTrackRef { get; set; }
        public string GPSVersionID { get; set; }
        public string GainControl { get; set; }
        public double Gamma { get; set; }
        public int ISOSpeed { get; set; }
        public int ISOSpeedLatitudeYYY { get; set; }
        public int ISOSpeedLatitudeZZZ { get; set; }
        public int PhotographicSensitivity { get; set; }
        public string ImageUniqueID { get; set; }
        public string LensMake { get; set; }
        public string LensModel { get; set; }
        public string LensSerialNumber { get; set; }
        public List<double> LensSpecification { get; set; }
        public string LightSource { get; set; }
        public string MakerNoteRawData { get; set; }
        public double MaxApertureValue { get; set; }
        public string MeteringMode { get; set; }
        public string OECF { get; set; }
        public int PixelXDimension { get; set; }
        public int PixelYDimension { get; set; }
        public int RecommendedExposureIndex { get; set; }
        public string RelatedSoundFile { get; set; }
        public string Saturation { get; set; }
        public string SceneCaptureType { get; set; }
        public int SceneType { get; set; }
        public string SensingMethod { get; set; }
        public int SensitivityType { get; set; }
        public int Sharpness { get; set; }
        public double ShutterSpeedValue { get; set; }
        public string SpatialFrequencyResponse { get; set; }
        public string SpectralSensitivity { get; set; }
        public int StandardOutputSensitivity { get; set; }
        public List<int> SubjectArea { get; set; }
        public double SubjectDistance { get; set; }
        public string SubjectDistanceRange { get; set; }
        public object SubjectLocation { get; set; }
        public string SubsecTime { get; set; }
        public string SubsecTimeDigitized { get; set; }
        public string SubsecTimeOriginal { get; set; }
        public string UserComment { get; set; }
        public string WhiteBalance { get; set; }
        public List<double> WhitePoint { get; set; }
    }

    public class ExifData
    {
        public double ApertureValue { get; set; }
        public string BodySerialNumber { get; set; }
        public double BrightnessValue { get; set; }
        public string CFAPattern { get; set; }
        public string CameraOwnerName { get; set; }
        public string ColorSpace { get; set; }
        public string ComponentsConfiguration { get; set; }
        public double CompressedBitsPerPixel { get; set; }
        public string Contrast { get; set; }
        public string CustomRendered { get; set; }
        public string DateTimeDigitized { get; set; }
        public string DateTimeOriginal { get; set; }
        public string DeviceSettingDescription { get; set; }
        public double DigitalZoomRatio { get; set; }
        public string ExifVersion { get; set; }
        public double ExposureBiasValue { get; set; }
        public double ExposureIndex { get; set; }
        public string ExposureMode { get; set; }
        public string ExposureProgram { get; set; }
        public double ExposureTime { get; set; }
        public double FNumber { get; set; }
        public string FileSource { get; set; }
        public string Flash { get; set; }
        public double FlashEnergy { get; set; }
        public string FlashpixVersion { get; set; }
        public double FocalLength { get; set; }
        public int FocalLengthIn35MmFilm { get; set; }
        public string FocalPlaneResolutionUnit { get; set; }
        public double FocalPlaneXResolution { get; set; }
        public double FocalPlaneYResolution { get; set; }
        public double GPSAltitude { get; set; }
        public string GPSAltitudeRef { get; set; }
        public string GPSAreaInformation { get; set; }
        public double GPSDOP { get; set; }
        public double GPSDestBearing { get; set; }
        public string GPSDestBearingRef { get; set; }
        public double GPSDestDistance { get; set; }
        public string GPSDestDistanceRef { get; set; }
        public double GPSDestLatitude { get; set; }
        public string GPSDestLatitudeRef { get; set; }
        public double GPSDestLongitude { get; set; }
        public string GPSDestLongitudeRef { get; set; }
        public int GPSDifferential { get; set; }
        public double GPSImgDirection { get; set; }
        public string GPSImgDirectionRef { get; set; }
        public string GPSDateStamp { get; set; }
        public double GPSLatitude { get; set; }
        public string GPSLatitudeRef { get; set; }
        public double GPSLongitude { get; set; }
        public string GPSLongitudeRef { get; set; }
        public string GPSMapDatum { get; set; }
        public string GPSMeasureMode { get; set; }
        public string GPSProcessingMethod { get; set; }
        public string GPSSatellites { get; set; }
        public double GPSSpeed { get; set; }
        public string GPSSpeedRef { get; set; }
        public string GPSStatus { get; set; }
        public double GPSTimestamp { get; set; }
        public string GPSTrack { get; set; }
        public string GPSTrackRef { get; set; }
        public string GPSVersionID { get; set; }
        public string GainControl { get; set; }
        public double Gamma { get; set; }
        public int ISOSpeed { get; set; }
        public int ISOSpeedLatitudeYYY { get; set; }
        public int ISOSpeedLatitudeZZZ { get; set; }
        public int PhotographicSensitivity { get; set; }
        public string ImageUniqueID { get; set; }
        public string LensMake { get; set; }
        public string LensModel { get; set; }
        public string LensSerialNumber { get; set; }
        public List<double> LensSpecification { get; set; }
        public string LightSource { get; set; }
        public string MakerNoteRawData { get; set; }
        public double MaxApertureValue { get; set; }
        public string MeteringMode { get; set; }
        public string OECF { get; set; }
        public int PixelXDimension { get; set; }
        public int PixelYDimension { get; set; }
        public int RecommendedExposureIndex { get; set; }
        public string RelatedSoundFile { get; set; }
        public string Saturation { get; set; }
        public string SceneCaptureType { get; set; }
        public int SceneType { get; set; }
        public string SensingMethod { get; set; }
        public int SensitivityType { get; set; }
        public int Sharpness { get; set; }
        public double ShutterSpeedValue { get; set; }
        public string SpatialFrequencyResponse { get; set; }
        public string SpectralSensitivity { get; set; }
        public int StandardOutputSensitivity { get; set; }
        public List<int> SubjectArea { get; set; }
        public double SubjectDistance { get; set; }
        public string SubjectDistanceRange { get; set; }
        public int SubjectLocation { get; set; }
        public string SubsecTime { get; set; }
        public string SubsecTimeDigitized { get; set; }
        public string SubsecTimeOriginal { get; set; }
        public string UserComment { get; set; }
        public string WhiteBalance { get; set; }
        public List<double> WhitePoint { get; set; }
    }

    public class PsdProperties
    {
        public int BitsPerChannel { get; set; }
        public int ChannelsCount { get; set; }
        public string ColorMode { get; set; }
        public string Compression { get; set; }
    }

    public class ImagePropertiesResponse
    {
        public int Height { get; set; }
        public int Width { get; set; }
        public int BitsPerPixel { get; set; }
        public BmpProperties BmpProperties { get; set; }
        public GifProperties GifProperties { get; set; }
        public JpegProperties JpegProperties { get; set; }
        public PngProperties PngProperties { get; set; }
        public TiffProperties TiffProperties { get; set; }
        public PsdProperties PsdProperties { get; set; }
        public double HorizontalResolution { get; set; }
        public double VerticalResolution { get; set; }
        public bool IsCached { get; set; }
        public int Code { get; set; }
        public string Status { get; set; }
    }

    public class FrameOptions
    {
        public bool IsValid { get; set; }
        public object Artist { get; set; }
        public string ByteOrder { get; set; }
        public List<int> BitsPerSample { get; set; }
        public string Compression { get; set; }
        public object Copyright { get; set; }
        public object ColorMap { get; set; }
        public object DateTime { get; set; }
        public object DocumentName { get; set; }
        public string AlphaStorage { get; set; }
        public string FillOrder { get; set; }
        public object HalfToneHints { get; set; }
        public object ImageDescription { get; set; }
        public object InkNames { get; set; }
        public object ScannerManufacturer { get; set; }
        public List<int> MaxSampleValue { get; set; }
        public List<int> MinSampleValue { get; set; }
        public object ScannerModel { get; set; }
        public object PageName { get; set; }
        public string Orientation { get; set; }
        public object PageNumber { get; set; }
        public string Photometric { get; set; }
        public string PlanarConfiguration { get; set; }
        public string ResolutionUnit { get; set; }
        public int RowsPerStrip { get; set; }
        public List<string> SampleFormat { get; set; }
        public int SamplesPerPixel { get; set; }
        public List<long> SmaxSampleValue { get; set; }
        public List<int> SminSampleValue { get; set; }
        public object SoftwareType { get; set; }
        public List<int> StripByteCounts { get; set; }
        public List<int> StripOffsets { get; set; }
        public string SubFileType { get; set; }
        public object TargetPrinter { get; set; }
        public string Threshholding { get; set; }
        public int TotalPages { get; set; }
        public double Xposition { get; set; }
        public double Xresolution { get; set; }
        public double Yposition { get; set; }
        public double Yresolution { get; set; }
        public string FaxT4Options { get; set; }
        public string Predictor { get; set; }
        public int ImageLength { get; set; }
        public int ImageWidth { get; set; }
        public int ValidTagCount { get; set; }
        public int BitsPerPixel { get; set; }
    }

    public class Frame
    {
        public FrameOptions FrameOptions { get; set; }
        public int Height { get; set; }
        public int Width { get; set; }
        public object ExifData { get; set; }
    }

    public class TiffProperties
    {
        public List<Frame> Frames { get; set; }
        public string ByteOrder { get; set; }
        public object ExifData { get; set; }
    }

    public class TiffPropertiesResponse
    {
        public int Height { get; set; }
        public int Width { get; set; }
        public int BitsPerPixel { get; set; }
        public object BmpProperties { get; set; }
        public object GifProperties { get; set; }
        public object JpegProperties { get; set; }
        public object PngProperties { get; set; }
        public TiffProperties TiffProperties { get; set; }
        public object PsdProperties { get; set; }
        public double HorizontalResolution { get; set; }
        public double VerticalResolution { get; set; }
        public bool IsCached { get; set; }
        public int Code { get; set; }
        public string Status { get; set; }
    }

}
