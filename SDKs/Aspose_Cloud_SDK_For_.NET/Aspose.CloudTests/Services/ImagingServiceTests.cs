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
    public class ImagingServiceTests
    {
        ImagingService imagingService = new ImagingService(Utils.AppSid, Utils.AppKey);
        StorageService storageService = new StorageService(Utils.AppSid, Utils.AppKey);

        [TestMethod()]
        public void Imaging_Bmp_Tests()
        {
            try
            {
                System.Threading.Thread.Sleep(3000); // Just for testing
                imagingService.Bmp.UpdateParametersOfBmpImage("bmp-sample.bmp", 24, 300, 300, Utils.CloudStorage_Output_Folder + "/test-bmp-updated222.bmp", Utils.CloudStorage_Input_Folder, string.Empty);
                imagingService.Bmp.UpdateParametersOfBmpImage("bmp-sample.bmp", 24, 300, 300, Utils.Local_Output_Path + "test-bmp-updated222.bmp", Utils.CloudStorage_Input_Folder, string.Empty);
                System.Threading.Thread.Sleep(3000); // Just for testing
                imagingService.Bmp.UpdateParametersOfBmpImage(24, 400, 400, Utils.CloudStorage_Output_Folder + "/test-bmp-updated444.bmp", Utils.Local_Input_Path + "bmp-sample.bmp");
                imagingService.Bmp.UpdateParametersOfBmpImage(24, 400, 400, Utils.Local_Output_Path + "test-bmp-updated444.bmp", Utils.Local_Input_Path + "bmp-sample.bmp");
            }
            catch (Exception ex)
            {
                Assert.Fail(ex.Message);
            }
        }

        [TestMethod()]
        public void Imaging_Crop_Tests()
        {
            try
            {
                System.Threading.Thread.Sleep(3000); // Just for testing
                imagingService.Crop.CropExistingImage("crop-sample.bmp", ImageFormat.Bmp, 0, 0, 100, 100, Utils.Local_Output_Path + "cropped-image-output.bmp", Utils.CloudStorage_Input_Folder);
                imagingService.Crop.CropExistingImage("crop-sample.bmp", ImageFormat.Bmp, 0, 0, 100, 100, Utils.CloudStorage_Output_Folder + "/cropped-image-output.bmp", Utils.CloudStorage_Input_Folder);
                System.Threading.Thread.Sleep(3000); // Just for testing
                imagingService.Crop.CropImage(ImageFormat.Bmp, 0, 0, 100, 100, Utils.Local_Output_Path + "cropped-image2-output.bmp", Utils.Local_Input_Path + "crop-sample.bmp");
                imagingService.Crop.CropImage(ImageFormat.Bmp, 0, 0, 100, 100, Utils.CloudStorage_Output_Folder + "/cropped-image2-output.bmp", Utils.Local_Input_Path + "crop-sample.bmp");

            }
            catch (Exception ex)
            {
                Assert.Fail(ex.Message);
            }
        }

        [TestMethod()]
        public void Imaging_Frame_Tests()
        {
            try
            {
                System.Threading.Thread.Sleep(3000); // Just for testing
                TiffPropertiesResponse tiffPropertiesResponse = imagingService.Frame.GetPropertiesOfTiffFrame("tiff-sample.tiff", 0, Utils.CloudStorage_Input_Folder);

                imagingService.Frame.GetSeparateFrameOfTiffImage("tiff-sample.tiff", 0, Utils.Local_Output_Path + "tiff-output.tiff", Utils.CloudStorage_Input_Folder);
                imagingService.Frame.GetSeparateFrameOfTiffImage("tiff-sample.tiff", 0, Utils.CloudStorage_Output_Folder + "/tiff-output.tiff", Utils.CloudStorage_Input_Folder);
                System.Threading.Thread.Sleep(3000); // Just for testing
                imagingService.Frame.UpdatePropertiesOfFrameInExistingTiffImage("tiff-sample.tiff", 0, 100, 100, 0, 0, 100, 100, true, Utils.Local_Output_Path + "tiff-output2.tiff", Utils.CloudStorage_Input_Folder);
                imagingService.Frame.UpdatePropertiesOfFrameInExistingTiffImage("tiff-sample.tiff", 0, 100, 100, 0, 0, 100, 100, true, Utils.CloudStorage_Output_Folder + "/tiff-output2.tiff", Utils.CloudStorage_Input_Folder);

            }
            catch (Exception ex)
            {
                Assert.Fail(ex.Message);
            }
        }

        [TestMethod()]
        public void Imaging_Gif_Tests()
        {
            try
            {
                System.Threading.Thread.Sleep(3000); // Just for testing
                imagingService.Gif.UpdateParametersOfGifImage("gif-sample.gif", 32, 3, true, false, false, 3, Utils.Local_Output_Path + "gif-output.gif", Utils.CloudStorage_Input_Folder);
                imagingService.Gif.UpdateParametersOfGifImage("gif-sample.gif", 32, 3, true, false, false, 3, Utils.CloudStorage_Output_Folder + "/gif-output.gif", Utils.CloudStorage_Input_Folder);
                System.Threading.Thread.Sleep(3000); // Just for testing
                imagingService.Gif.UpdateParametersOfGifImage(32, 3, true, false, false, 3, Utils.Local_Output_Path + "gif-output2.gif", Utils.Local_Input_Path + "gif-sample.gif");
                imagingService.Gif.UpdateParametersOfGifImage(32, 3, true, false, false, 3, Utils.CloudStorage_Output_Folder + "/gif-output2.gif", Utils.Local_Input_Path + "gif-sample.gif");
            }
            catch (Exception ex)
            {
                Assert.Fail(ex.Message);
            }
        }

        [TestMethod()]
        public void Imaging_Jpg_Tests()
        {
            try
            {
                System.Threading.Thread.Sleep(3000); // Just for testing
                imagingService.Jpg.UpdateParametersOfJpgImage("jpg-sample.jpg", Utils.Local_Output_Path + "jpg-output.jpg", Utils.CloudStorage_Input_Folder);
                imagingService.Jpg.UpdateParametersOfJpgImage("jpg-sample.jpg", Utils.CloudStorage_Output_Folder + "/jpg-output.jpg", Utils.CloudStorage_Input_Folder);
                System.Threading.Thread.Sleep(3000); // Just for testing
                imagingService.Jpg.UpdateParametersOfJpgImage(Utils.Local_Output_Path + "jpg-output2.jpg", Utils.Local_Input_Path + "jpg-sample.jpg");
                imagingService.Jpg.UpdateParametersOfJpgImage(Utils.CloudStorage_Output_Folder + "/jpg-output2.jpg", Utils.Local_Input_Path + "jpg-sample.jpg");
            }
            catch (Exception ex)
            {
                Assert.Fail(ex.Message);
            }
        }

        [TestMethod()]
        public void Imaging_Png_Tests()
        {
            try
            {
                imagingService.Png.UpdateParametersOfPngImage("png-sample.png", Utils.Local_Output_Path + "png-output.png", Utils.CloudStorage_Input_Folder);
                System.Threading.Thread.Sleep(3000); // Just for testing
                imagingService.Png.UpdateParametersOfPngImage("png-sample.png", Utils.CloudStorage_Output_Folder + "/png-output.png", Utils.CloudStorage_Input_Folder);
                System.Threading.Thread.Sleep(3000); // Just for testing
                imagingService.Png.UpdateParametersOfPngImage(Utils.Local_Output_Path + "png-output2.png", Utils.Local_Input_Path + "png-sample.png");
                System.Threading.Thread.Sleep(3000); // Just for testing
                imagingService.Png.UpdateParametersOfPngImage(Utils.CloudStorage_Output_Folder + "/png-output2.png", Utils.Local_Input_Path + "png-sample.png");
            }
            catch (Exception ex)
            {
                Assert.Fail(ex.Message);
            }
        }

        [TestMethod()]
        public void Imaging_Psd_Tests()
        {
            try
            {
                imagingService.Psd.UpdateParametersOfPsdImage("psd-sample.psd", 4, Utils.Local_Output_Path + "psd-output.psd", Utils.CloudStorage_Input_Folder);
                System.Threading.Thread.Sleep(3000); // Just for testing
                imagingService.Psd.UpdateParametersOfPsdImage("psd-sample.psd", 4, Utils.CloudStorage_Output_Folder + "/png-output.psd", Utils.CloudStorage_Input_Folder);
                System.Threading.Thread.Sleep(3000); // Just for testing
                imagingService.Psd.UpdateParametersOfPsdImage(4, Utils.Local_Output_Path + "psd-output2.psd", Utils.Local_Input_Path + "psd-sample.psd");
                System.Threading.Thread.Sleep(3000); // Just for testing
                imagingService.Psd.UpdateParametersOfPsdImage(4, Utils.CloudStorage_Output_Folder + "/psd-output2.psd", Utils.Local_Input_Path + "psd-sample.psd");
            }
            catch (Exception ex)
            {
                Assert.Fail(ex.Message);
            }
        }

        [TestMethod()]
        public void Imaging_Properties()
        {
            try
            {
                System.Threading.Thread.Sleep(3000); // Just for testing
                ImagePropertiesResponse ips1 = imagingService.Properties.GetPropertiesOfAnImage("bmp-sample.bmp", Utils.CloudStorage_Input_Folder);
                ImagePropertiesResponse ips2 = imagingService.Properties.GetPropertiesOfAnImage("gif-sample.gif", Utils.CloudStorage_Input_Folder);
                ImagePropertiesResponse ips3 = imagingService.Properties.GetPropertiesOfAnImage("jpg-sample.jpg", Utils.CloudStorage_Input_Folder);
                System.Threading.Thread.Sleep(3000); // Just for testing
                ImagePropertiesResponse ips4 = imagingService.Properties.GetPropertiesOfAnImage("png-sample.png", Utils.CloudStorage_Input_Folder);
                ImagePropertiesResponse ips5 = imagingService.Properties.GetPropertiesOfAnImage("psd-sample.psd", Utils.CloudStorage_Input_Folder);
            }
            catch (Exception ex)
            {
                Assert.Fail(ex.Message);
            }
        }

        [TestMethod()]
        public void Imaging_Resize_Tests()
        {
            try
            {
                System.Threading.Thread.Sleep(3000); // Just for testing
                imagingService.Resize.ChangeScaleOfAnExistingImage("jpg-sample.jpg", ImageFormat.Jpg, 1200, 1200, Utils.Local_Output_Path + "jpg-resize-output.jpg", Utils.CloudStorage_Input_Folder);
                imagingService.Resize.ChangeScaleOfAnExistingImage("jpg-sample.jpg", ImageFormat.Jpg, 1200, 1200, Utils.CloudStorage_Output_Folder + "/jpg-resize-output.jpg", Utils.CloudStorage_Input_Folder);
                System.Threading.Thread.Sleep(3000); // Just for testing
                imagingService.Resize.ChangeScaleOfAnImage(ImageFormat.Jpg, 1200, 1200, Utils.Local_Output_Path + "jpg-resize-output2.jpg", Utils.Local_Input_Path + "jpg-sample.jpg");
                imagingService.Resize.ChangeScaleOfAnImage(ImageFormat.Jpg, 1200, 1200, Utils.CloudStorage_Output_Folder + "/jpg-resize-output2.jpg", Utils.Local_Input_Path + "jpg-sample.jpg");
            }
            catch (Exception ex)
            {
                Assert.Fail(ex.Message);
            }
        }

        [TestMethod()]
        public void Imaging_RotateFlip_Tests()
        {
            try
            {
                imagingService.RotateFlip.RotateFlipExistingImage("jpg-sample.jpg", ImageFormat.Jpg, RotateFlipMethod.Rotate180FlipX, Utils.Local_Output_Path + "jpg-rotateFlip-output.jpg", Utils.CloudStorage_Input_Folder);
                System.Threading.Thread.Sleep(3000); // Just for testing
                imagingService.RotateFlip.RotateFlipExistingImage("jpg-sample.jpg", ImageFormat.Jpg, RotateFlipMethod.Rotate180FlipX, Utils.CloudStorage_Output_Folder + "/jpg-rotateFlip-output.jpg", Utils.CloudStorage_Input_Folder);
                System.Threading.Thread.Sleep(3000); // Just for testing
                imagingService.RotateFlip.RotateFlipImage(ImageFormat.Jpg, RotateFlipMethod.Rotate180FlipX, Utils.Local_Output_Path + "jpg-rotateFlip-output2.jpg", Utils.Local_Input_Path + "jpg-sample.jpg");
                System.Threading.Thread.Sleep(3000); // Just for testing
                imagingService.RotateFlip.RotateFlipImage(ImageFormat.Jpg, RotateFlipMethod.Rotate180FlipX, Utils.CloudStorage_Output_Folder + "/jpg-rotateFlip-output2.jpg", Utils.Local_Input_Path + "jpg-sample.jpg");
            }
            catch (Exception ex)
            {
                Assert.Fail(ex.Message);
            }
        }

        [TestMethod()]
        public void Imaging_SaveAs_Tests()
        {
            try
            {
                imagingService.SaveAs.ExportExistingImageToAnotherFormat("jpg-sample.jpg", ImageFormat.Png, Utils.Local_Output_Path + "png-saveAs-output.png", Utils.CloudStorage_Input_Folder);
                System.Threading.Thread.Sleep(3000); // Just for testing
                imagingService.SaveAs.ExportExistingImageToAnotherFormat("jpg-sample.jpg", ImageFormat.Png, Utils.CloudStorage_Output_Folder + "/png-saveAs-output.png", Utils.CloudStorage_Input_Folder);
                System.Threading.Thread.Sleep(3000); // Just for testing
                imagingService.SaveAs.ExportImageToAnotherFormat(ImageFormat.Png, Utils.Local_Output_Path + "png-saveAs-output2.png", Utils.Local_Input_Path + "jpg-sample.jpg");
                System.Threading.Thread.Sleep(3000); // Just for testing
                imagingService.SaveAs.ExportImageToAnotherFormat(ImageFormat.Png, Utils.CloudStorage_Output_Folder + "/png-saveAs-output2.png", Utils.Local_Input_Path + "jpg-sample.jpg");
            }
            catch (Exception ex)
            {
                Assert.Fail(ex.Message);
            }
        }

        [TestMethod()]
        public void Imaging_Tiff_Tests()
        {
            try
            {
                imagingService.Tiff.UpdateTiffImage(TiffFacCompressionType.CcittFax3, ResolutionUnit.Inch, 200, 200, Utils.CloudStorage_Output_Folder + "/tiff-sample-updated.tiff", Utils.Local_Input_Path + "tiff-sample.tiff");
                System.Threading.Thread.Sleep(3000); // Just for testing
                storageService.File.DownloadFile(Utils.CloudStorage_Output_Folder + "/tiff-sample-updated.tiff", Utils.Local_Output_Path + "tiff-sample-updated.tiff");
                System.Threading.Thread.Sleep(3000); // Just for testing
                imagingService.Tiff.GetTiffImageForFax("tiff-sample.tiff", Utils.CloudStorage_Input_Folder, Utils.CloudStorage_Output_Folder + "/tiff-image-for-fax.tiff");
                System.Threading.Thread.Sleep(3000); // Just for testing
                storageService.File.DownloadFile(Utils.CloudStorage_Output_Folder + "/tiff-image-for-fax.tiff", Utils.Local_Output_Path + "tiff-image-for-fax.tiff");
                System.Threading.Thread.Sleep(3000); // Just for testing
                imagingService.Tiff.AppendTiffImage("tiff-sample2.tif", "tiff-sample2.tif", Utils.CloudStorage_Input_Folder);
                System.Threading.Thread.Sleep(3000); // Just for testing
                storageService.File.DownloadFile(Utils.CloudStorage_Input_Folder + "/tiff-sample2.tif", Utils.Local_Output_Path + "tiff-append-output.tiff");
            }
            catch (Exception ex)
            {
                Assert.Fail(ex.Message);
            }
        }

        [TestMethod()]
        public void Imaging_UpdateImage_Tests()
        {
            try
            {
                System.Threading.Thread.Sleep(3000); // Just for testing
                imagingService.UpdateImage.PerformScalingCroppingAndFlippingOfAnImage("jpg-sample.jpg", ImageFormat.Jpg, 200, 200, 0, 0, 200, 200, Utils.Local_Output_Path + "jpg-sample-updateImage.jpg", Utils.CloudStorage_Input_Folder);
                imagingService.UpdateImage.PerformScalingCroppingAndFlippingOfAnImage("jpg-sample.jpg", ImageFormat.Png, 200, 200, 0, 0, 200, 200, Utils.CloudStorage_Output_Folder + "/jpg-sample-updateImage.jpg", Utils.CloudStorage_Input_Folder);
                System.Threading.Thread.Sleep(3000); // Just for testing
                imagingService.UpdateImage.PerformScalingCroppingAndFlippingOfAnImage(ImageFormat.Png, 200, 200, 0, 0, 200, 200, Utils.Local_Output_Path + "jpg-sample-updateImage2.jpg", Utils.Local_Input_Path + "jpg-sample.jpg");
                imagingService.UpdateImage.PerformScalingCroppingAndFlippingOfAnImage(ImageFormat.Png, 200, 200, 0, 0, 200, 200, Utils.CloudStorage_Output_Folder + "/jpg-sample-updateImage2.jpg", Utils.Local_Input_Path + "jpg-sample.jpg");        
            }
            catch (Exception ex)
            {
                Assert.Fail(ex.Message);
            }
        }
    }
}
