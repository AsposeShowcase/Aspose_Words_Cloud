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
    public class SlidesServiceTests
    {
        StorageService storageService = new StorageService(Utils.AppSid, Utils.AppKey);
        SlidesService slidesService = new SlidesService(Utils.AppSid, Utils.AppKey);

        [TestMethod()]
        public void Slides_Tests()
        {
            try
            {
                SlidesDocumentResponse slidesDocumentResponse = slidesService.ReadPresentationInfo("slide-sample.pptx", Utils.CloudStorage_Output_Folder);
                slidesService.ExportToSomeFormat("slide-sample.pptx", SlidesOutputFormat.Html, Utils.CloudStorage_Output_Folder, Utils.Local_Output_Path + "slides-html-out.html");
                System.Threading.Thread.Sleep(3000); // Just for testing
                slidesService.ExportToSomeFormat("slide-sample.pptx", SlidesOutputFormat.Html, Utils.CloudStorage_Output_Folder, Utils.CloudStorage_Output_Folder + "/slides-html-out.html");
                slidesService.ExportToSomeFormat("slide-sample.pptx", SlidesOutputFormat.Tiff, Utils.CloudStorage_Output_Folder, Utils.Local_Output_Path + "slides-tiff-out.tiff");
                System.Threading.Thread.Sleep(3000); // Just for testing
                slidesService.ExportToSomeFormat("slide-sample.pptx", SlidesOutputFormat.Tiff, Utils.CloudStorage_Output_Folder, Utils.CloudStorage_Output_Folder + "/slides-tiff-out.tiff");
                storageService.File.RemoveFile(Utils.CloudStorage_Output_Folder + "/slide-created1.pptx");
                slidesService.CreatePresentation("slide-created1.pptx", Utils.CloudStorage_Output_Folder, Utils.CloudStorage_Input_Folder + "/potx-sample.potx");
                System.Threading.Thread.Sleep(3000); // Just for testing
                slidesService.CreatePresentationDocumentFromHtml("slide-created2.pptx", string.Empty, Utils.CloudStorage_Output_Folder, Utils.Local_Input_Path + "html-sample.html");
                slidesService.ConvertPresentation(SlidesOutputFormat.Tiff, Utils.CloudStorage_Output_Folder + "/slides-converted.tiff", Utils.Local_Input_Path + "slide-sample.pptx");
                System.Threading.Thread.Sleep(3000); // Just for testing
                SlidesSplitResponse slidesSplitResponse = slidesService.SplitPresentations("slide-sample.pptx", 400, 400, Utils.CloudStorage_Output_Folder, Utils.CloudStorage_Input_Folder);
            }
            catch (Exception ex)
            {
                Assert.Fail(ex.Message);
            }
        }

        [TestMethod()]
        public void Slides_Images_Tests()
        {
            try
            {
                SlidesImageResponse slidesImageResponse = slidesService.Images.ReadPresentationImagesInfo("slide-sample.pptx", Utils.CloudStorage_Input_Folder);
                SlidesImageResponse slidesImageResponse2 = slidesService.Images.ReadSlideImagesInfo("slide-sample.pptx", 2, Utils.CloudStorage_Input_Folder);
            }
            catch (Exception ex)
            {
                Assert.Fail(ex.Message);
            }
        }

        [TestMethod()]
        public void Slides_Merge_Tests()
        {
            try
            {
                storageService.File.CopyFile(Utils.CloudStorage_Input_Folder + "/slide-sample.pptx", Utils.CloudStorage_Output_Folder + "/slide-sample.pptx");

                PresentationRequest presentationRequest = new PresentationRequest();
                Presentation presentation = new Presentation(Utils.CloudStorage_Input_Folder + "/slide-sample1.pptx");
                Presentation presentation2 = new Presentation(Utils.CloudStorage_Input_Folder + "/slide-sample2.pptx");
                presentationRequest.Presentations.Add(presentation);
                presentationRequest.Presentations.Add(presentation2);

                SlidesDocumentResponse slidesDocumentResponse = slidesService.MergeDocument.MergePresentations("slide-sample.pptx", Utils.CloudStorage_Output_Folder, presentationRequest);

                storageService.File.CopyFile(Utils.CloudStorage_Input_Folder + "/slide-sample.pptx", Utils.CloudStorage_Output_Folder + "/slide-sample.pptx");

                PresentationRequest presentationRequest2 = new PresentationRequest();

                List<int> slides = new List<int>();
                slides.Add(1);
                slides.Add(2);

                Presentation presentation3 = new Presentation(Utils.CloudStorage_Input_Folder + "/slide-sample1.pptx", slides);
                Presentation presentation4 = new Presentation(Utils.CloudStorage_Input_Folder + "/slide-sample2.pptx", slides);
                presentationRequest2.Presentations.Add(presentation3);
                presentationRequest2.Presentations.Add(presentation4);

                SlidesDocumentResponse slidesDocumentResponse2 = slidesService.MergeDocument.MergePresentationsWithSlides("slide-sample.pptx", Utils.CloudStorage_Output_Folder, presentationRequest2);

                storageService.File.DownloadFile(Utils.CloudStorage_Output_Folder + "/slide-sample.pptx", Utils.Local_Output_Path + "/slide-sample.pptx");
            }
            catch (Exception ex)
            {
                Assert.Fail(ex.Message);
            }
        }

        [TestMethod()]
        public void Slides_Placeholder_Tests()
        {
            try
            {
                PlaceholdersResponse placeholdersResponse = slidesService.Placeholders.ReadSlidePlaceholdersInfo("slide-sample.pptx", 1, Utils.CloudStorage_Input_Folder);
                PlaceholderResponse placeholderResponse = slidesService.Placeholders.ReadSlidePlaceholderInfo("slide-sample.pptx", 1, 1, Utils.CloudStorage_Input_Folder);
            }
            catch (Exception ex)
            {
                Assert.Fail(ex.Message);
            }
        }

        [TestMethod()]
        public void Slides_Properties_Tests()
        {
            try
            {
                storageService.File.CopyFile(Utils.CloudStorage_Input_Folder + "/slide-sample.pptx", Utils.CloudStorage_Output_Folder + "/slide-sample.pptx");

                SlidesDocumentPropertiesResponse slidesDocumentPropertiesResponse = slidesService.Properties.ReadPresentationDocumentProperties("slide-sample.pptx", Utils.CloudStorage_Output_Folder);
                System.Threading.Thread.Sleep(3000); // Just for testing
                SlidesDocumentProperty slidesDocumentProperty = new SlidesDocumentProperty("CustomProperty", "Aspose");
                SlidesDocumentPropertyResponse slidesDocumentPropertyResponse = slidesService.Properties.SetDocumentProperty("slide-sample.pptx", "CustomProperty", Utils.CloudStorage_Output_Folder, slidesDocumentProperty);
                System.Threading.Thread.Sleep(3000); // Just for testing
                SlidesDocumentPropertyRequest slidesDocumentPropertyRequest = new SlidesDocumentPropertyRequest();
                SlidesDocumentProperty slidesDocumentProperty1 = new SlidesDocumentProperty("CustomProperty1", "Aspose");
                SlidesDocumentProperty slidesDocumentProperty2 = new SlidesDocumentProperty("CustomProperty2", "Aspose");

                slidesDocumentPropertyRequest.List.Add(slidesDocumentProperty1);
                slidesDocumentPropertyRequest.List.Add(slidesDocumentProperty2);
                System.Threading.Thread.Sleep(3000); // Just for testing
                SlidesDocumentPropertiesResponse slidesDocumentPropertiesResponse2 = slidesService.Properties.SetDocumentProperties("slide-sample.pptx", Utils.CloudStorage_Output_Folder, slidesDocumentPropertyRequest);

                slidesService.Properties.DeleteDocumentProperty("slide-sample.pptx", "CustomProperty", Utils.CloudStorage_Output_Folder);
                System.Threading.Thread.Sleep(3000); // Just for testing

                slidesService.Properties.CleanDocumentProperties("slide-sample.pptx", Utils.CloudStorage_Output_Folder);
                SlidesDocumentPropertiesResponse slidesDocumentPropertiesResponse3 = slidesService.Properties.ReadPresentationDocumentProperties("slide-sample.pptx", Utils.CloudStorage_Output_Folder);
                System.Threading.Thread.Sleep(3000); // Just for testing
            }
            catch (Exception ex)
            {
                Assert.Fail(ex.Message);
            }
        }

        [TestMethod()]
        public void Slides_Shapes_Tests()
        {
            try
            {
                storageService.File.CopyFile(Utils.CloudStorage_Input_Folder + "/slide-sample.pptx", Utils.CloudStorage_Output_Folder + "/slide-sample.pptx");
                SlidesShapesResponse slidesShapesResponse = slidesService.Shapes.ReadSlidesShapesInfo("slide-sample.pptx", 1, Utils.CloudStorage_Output_Folder);
                SlideShapeResponse slideShapeResponse = slidesService.Shapes.ReadSlidesShapeInfo("slide-sample.pptx", 1, "1", Utils.CloudStorage_Output_Folder);

                slideShapeResponse.Shape.Height = 300;
                slideShapeResponse.Shape.Width = 300;

                slidesService.Shapes.UpdatesShapeProperties("slide-sample.pptx", 1, "3", Utils.CloudStorage_Output_Folder, slideShapeResponse.Shape);

                SlideParagraphsResponse slideParagraphsResponse = slidesService.Shapes.ReadListofParagraphsInShapeTextbody("slide-sample.pptx", 1, 3, Utils.CloudStorage_Output_Folder);
                ParagraphResponse paragraphResponse = slidesService.Shapes.ReadsParagraphInShapeTextbody("slide-sample.pptx", 1, 4, 1, Utils.CloudStorage_Output_Folder);
                SlidesParagraphPortionResponse slidesParagraphPortionResponse = slidesService.Shapes.ReadsParagraphPortionInShapeTextbody("slide-sample.pptx", 1, 4, 1, 1, Utils.CloudStorage_Output_Folder);

                slidesService.Shapes.UpdatesParagraphPortionProperties("slide-sample.pptx", 1, 4, 1, 1, Utils.CloudStorage_Output_Folder, slidesParagraphPortionResponse.Portion);
            }
            catch (Exception ex)
            {
                Assert.Fail(ex.Message);
            }
        }

        [TestMethod()]
        public void Slides_Slides_Tests()
        {
            try
            {
                storageService.File.CopyFile(Utils.CloudStorage_Input_Folder + "/slide-sample.pptx", Utils.CloudStorage_Output_Folder + "/slide-sample.pptx");
                SlidesResponse slidesResponse = slidesService.Slides.ReadPresentationSlidesInfo("slide-sample.pptx", Utils.CloudStorage_Output_Folder);
                System.Threading.Thread.Sleep(3000); // Just for testing
                slidesService.Slides.AddEmptySlideToTheEndOfPresentation("slide-sample.pptx", Utils.CloudStorage_Output_Folder);
                slidesService.Slides.AddCopyOfSlideToTheEndOfPresentation("slide-sample.pptx", 1, Utils.CloudStorage_Output_Folder);
                System.Threading.Thread.Sleep(3000); // Just for testing
                slidesService.Slides.AddEmptySlideToSpecifiedPosition("slide-sample.pptx", 1, Utils.CloudStorage_Output_Folder);
                slidesService.Slides.AddCopyOfSlideToSpecifiedPosition("slide-sample.pptx", 1, 2, Utils.CloudStorage_Output_Folder);
                System.Threading.Thread.Sleep(3000); // Just for testing
                slidesService.Slides.ChangeSlidePosition("slide-sample.pptx", 1, 2, Utils.CloudStorage_Output_Folder);
                slidesService.Slides.AddCopyOfSlideFromAnotherPresentaion("slide-sample.pptx", 1, Utils.CloudStorage_Input_Folder + "/slide-sample.pptx", 2, Utils.CloudStorage_Output_Folder);
                System.Threading.Thread.Sleep(3000); // Just for testing
                SlideResponse slideResponse = slidesService.Slides.ReadSlideInfo("slide-sample.pptx", 1, Utils.CloudStorage_Output_Folder);
                slidesService.Slides.ConvertSlideToSomeFormat("slide-sample.pptx", 1, SlidesImageFormat.Tiff, Utils.CloudStorage_Output_Folder, Utils.Local_Output_Path + "slide-out.tiff");
                System.Threading.Thread.Sleep(3000); // Just for testing
                SlidesBackgroundResponse slidesBackgroundResponse = slidesService.Slides.ReadPresentationSlideBackgroundColorType("slide-sample.pptx", 1, Utils.CloudStorage_Output_Folder);
                
                //slidesService.Slides.SetPresentationSlideBackgroundColor("slide-sample.pptx", 1, Utils.CloudStorage_Output_Folder, "black");
                slidesService.Slides.RemovePresentationSlideBackgroundColor("slide-sample.pptx", 1, Utils.CloudStorage_Output_Folder);
                System.Threading.Thread.Sleep(3000); // Just for testing
                SlidesCommentsResponse slidesCommentsResponse = slidesService.Slides.ReadPresentationSlideComments("slide-sample.pptx", 1, Utils.CloudStorage_Output_Folder);                
                slidesService.Slides.DeletePresentationSlideByItsIndex("slide-sample.pptx", 1, Utils.CloudStorage_Output_Folder);
                System.Threading.Thread.Sleep(3000); // Just for testing
                slidesService.Slides.DeletePresentationSlides("slide-sample.pptx", Utils.CloudStorage_Output_Folder);
            }
            catch (Exception ex)
            {
                Assert.Fail(ex.Message);
            }
        }

        [TestMethod()]
        public void Slides_Text_Tests()
        {
            try
            {
                SlidesTextItemsResponse slidesTextItemsResponse = slidesService.Text.ExtractPresentationTextItems("slide-sample.pptx", Utils.CloudStorage_Output_Folder);
                SlidesTextItemsResponse slidesTextItemsResponse2 = slidesService.Text.ExtractSlideTextItems("slide-sample.pptx", 1, Utils.CloudStorage_Output_Folder);
                
                storageService.File.CopyFile(Utils.CloudStorage_Input_Folder + "/slide-sample.pptx", Utils.CloudStorage_Output_Folder + "/slide-sample.pptx");

                SlidesDocumentResponse slidesDocumentResponse = slidesService.Text.ReplaceTextByNewValue("slide-sample.pptx", "Aspose", "Aspose Pty. Ltd", Utils.CloudStorage_Output_Folder);
                SlidesDocumentResponse slidesDocumentResponse2 = slidesService.Text.ReplaceTextByNewValue("slide-sample.pptx", 1, "Aspose", "Aspose Pty. Ltd", Utils.CloudStorage_Output_Folder);          
            }
            catch (Exception ex)
            {
                Assert.Fail(ex.Message);
            }
        }

        [TestMethod()]
        public void Slides_Theme_Tests()
        {
            try
            {
                SlidesThemeResponse slidesThemeResponse = slidesService.Theme.ReadSlideThemeInfo("slide-sample.pptx", 1, Utils.CloudStorage_Output_Folder);
                SlidesColorSchemeResponse slidesColorSchemeResponse = slidesService.Theme.ReadSlideThemeColorSchemeInfo("slide-sample.pptx", 1, Utils.CloudStorage_Output_Folder);
                SlidesFontSchemeResponse slidesFontSchemeResponse = slidesService.Theme.ReadSlideThemeFontSchemeInfo("slide-sample.pptx", 1, Utils.CloudStorage_Output_Folder);
                SlidesFormatSchemeResponse slidesFormatSchemeResponse = slidesService.Theme.ReadSlideThemeFormatSchemeInfo("slide-sample.pptx", 1, Utils.CloudStorage_Output_Folder);            
            }
            catch (Exception ex)
            {
                Assert.Fail(ex.Message);
            }
        }
    }
}
