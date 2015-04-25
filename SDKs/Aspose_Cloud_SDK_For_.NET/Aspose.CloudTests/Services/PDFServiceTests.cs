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
    public class PDFServiceTests
    {
        PDFService pdfService = new PDFService(Utils.AppSid, Utils.AppKey);
        StorageService storageService = new StorageService(Utils.AppSid, Utils.AppKey);

        [TestMethod()]
        public void Pdf_Tests()
        {
            try
            {
                PDFDocumentResponse pdfDocumentResponse = pdfService.ReadCommonDocumentInfo("pdf-sample.pdf", Utils.CloudStorage_Input_Folder);
                PDFDocumentSplitResponse fDocumentSplitResponse = pdfService.SplitDocumentToParts("pdf-sample.pdf", Utils.CloudStorage_Input_Folder, PDFOutputFormat.Pdf);

                pdfService.ConvertDocumentFromURL(PDFDocumentConvertFormat.Doc, "http://cdn.aspose.com/tmp/pdf-sample.pdf", Utils.Local_Output_Path + "pdf-out.doc");

                PDFDocumentResponse pdfDocumentResponse2 = pdfService.CreateEmptyNewDocument("pdf-created.pdf", Utils.CloudStorage_Output_Folder);
                PDFDocumentResponse pdfDocumentResponse3 = pdfService.CreateNewDocument("pdf-created.pdf", PDFTemplateType.Html, Utils.CloudStorage_Input_Folder + "/html-sample.html", string.Empty, Utils.CloudStorage_Output_Folder); 
                
                pdfService.ConvertDocument(PDFDocumentConvertFormat.Doc, Utils.Local_Input_Path + "pdf-sample.pdf", Utils.Local_Output_Path + "pdf-out.doc");

            }
            catch (Exception ex)
            {
                Assert.Fail(ex.Message);
            }
        }

        [TestMethod()]
        public void Pdf_ConvertAndSign_Tests()
        {
            try
            {
                pdfService.ConvertToSomeFormat("pdf-sample.pdf", PDFDocumentConvertFormat.Html, Utils.CloudStorage_Input_Folder, Utils.Local_Output_Path + "pdf-html-out.html");
                pdfService.ConvertToSomeFormat("pdf-sample.pdf", PDFDocumentConvertFormat.Html, Utils.CloudStorage_Input_Folder, Utils.CloudStorage_Output_Folder + "/pdf-html-out.html");

                pdfService.ConvertToSomeFormat("pdf-sample.pdf", PDFDocumentConvertFormat.Doc, Utils.CloudStorage_Input_Folder, Utils.Local_Output_Path + "pdf-doc-out.doc");
                pdfService.ConvertToSomeFormat("pdf-sample.pdf", PDFDocumentConvertFormat.Doc, Utils.CloudStorage_Input_Folder, Utils.CloudStorage_Output_Folder + "/pdf-doc-out.doc");

                #region Sign document

                storageService.File.CopyFile(Utils.CloudStorage_Input_Folder + "/pdf-sample.pdf", Utils.CloudStorage_Output_Folder + "/pdf-signed.pdf");

                PDFSignature signature = new PDFSignature();
                signature.SignaturePath = Utils.CloudStorage_Input_Folder + "/signature.pfx";
                signature.SignatureType = "PKCS7";
                signature.Password = "signature";
                signature.Appearance = Utils.CloudStorage_Input_Folder + "/signature.jpg";
                signature.Reason = "Success";

                signature.Contact = "marketplace@aspose.com";
                signature.Location = "Australia";
                signature.Visible = true;
                signature.FormFieldName = "Signature1";
                signature.Authority = "Aspose Marketplace";
                signature.Rectangle = new Rectangle(100, 100, 200, 200);
                signature.Date = DateTime.Now.ToString();

                pdfService.SignDocument("pdf-signed.pdf", Utils.CloudStorage_Output_Folder, signature);

                storageService.File.DownloadFile(Utils.CloudStorage_Output_Folder + "/pdf-signed.pdf", Utils.Local_Output_Path + "/pdf-signed.pdf");


                #endregion Sign document
            }
            catch (Exception ex)
            {
                Assert.Fail(ex.Message);
            }
        }

        [TestMethod()]
        public void Pdf_Barcode_Tests()
        {
            try
            {
                BarcodeResponse barcodeResponse = pdfService.Barcodes.RecognizeBarcodes("pdf-sample.pdf", 1, 1, Utils.CloudStorage_Input_Folder);
            }
            catch (Exception ex)
            {
                Assert.Fail(ex.Message);
            }
        }

        [TestMethod()]
        public void Pdf_Images_Tests()
        {
            try
            {
                PDFImagesResponse pdfImagesResponse = pdfService.Images.ReadDocumentImages("pdf-sample.pdf", 1, Utils.CloudStorage_Input_Folder);
                pdfService.Images.ReadDocumentImageByNumber("pdf-sample.pdf", 1, 1, 200, 200, PDFImageFormat.Png, Utils.CloudStorage_Input_Folder, Utils.Local_Output_Path + "pdf-image-out.png");

                storageService.File.CopyFile(Utils.CloudStorage_Input_Folder + "/pdf-sample.pdf", Utils.CloudStorage_Output_Folder + "/pdf-imageReplace.pdf");
                pdfService.Images.ReplaceDocumentImage("pdf-imageReplace.pdf", 1, 1, Utils.CloudStorage_Input_Folder + "/png-sample.png", Utils.CloudStorage_Output_Folder);
                storageService.File.DownloadFile(Utils.CloudStorage_Output_Folder + "/pdf-imageReplace.pdf", Utils.Local_Output_Path + "pdf-imageReplace.pdf");

                pdfService.Images.ReplaceDocumentImage("pdf-imageReplace.pdf", 1, 2, Utils.Local_Input_Path + "/png-sample.png", Utils.CloudStorage_Output_Folder);
                storageService.File.DownloadFile(Utils.CloudStorage_Output_Folder + "/pdf-imageReplace.pdf", Utils.Local_Output_Path + "pdf-imageReplace2.pdf");

            }
            catch (Exception ex)
            {
                Assert.Fail(ex.Message);
            }
        }

        [TestMethod()]
        public void Pdf_SaveAs_Tests()
        {
            try
            {
                pdfService.SaveAs.SaveDocumentAsTiffImage("pdf-sample.pdf", "pdf-out-tiff.tiff", Utils.CloudStorage_Input_Folder);
                storageService.File.DownloadFile(Utils.CloudStorage_Input_Folder + "/pdf-out-tiff.tiff", Utils.Local_Output_Path + "pdf-out-tiff.tiff");
            }
            catch (Exception ex)
            {
                Assert.Fail(ex.Message);
            }
        }

        [TestMethod()]
        public void Pdf_Annotations_Tests()
        {
            try
            {
                AnnotationsResponse annotationsResponse = pdfService.Annotations.ReadDocumantPageAnnotations("pdf-sample.pdf", 1, Utils.CloudStorage_Input_Folder);
                AnnotationResponse annotationResponse2 = pdfService.Annotations.ReadDocumentPageAnnotationByItsNumber("pdf-sample.pdf", 1, 1, Utils.CloudStorage_Input_Folder);
            }
            catch (Exception ex)
            {
                Assert.Fail(ex.Message);
            }
        }

        [TestMethod()]
        public void Pdf_Attachments_Tests()
        {
            try
            {
                AttachmentsResponse attachmentsResponse = pdfService.Attachments.ReadDocumentAttachmentsInfo("pdf-sample.pdf", Utils.CloudStorage_Input_Folder);
                AttachmentResponse AttachmentResponse = pdfService.Attachments.ReadDocumentAttachmentInfoByItsIndex("pdf-sample.pdf", 1, Utils.CloudStorage_Input_Folder);

                pdfService.Attachments.DownloadDocumentAttachmentContentByItsIndex("pdf-sample.pdf", 1, Utils.CloudStorage_Input_Folder, Utils.Local_Output_Path + "pdf-out-attach.png");
            }
            catch (Exception ex)
            {
                Assert.Fail(ex.Message);
            }
        }

        [TestMethod()]
        public void Pdf_Bookmarks_Tests()
        {
            try
            {
                BookmarksResponse bookmarksResponse = pdfService.Bookmarks.ReadAllDocumentBookmarks("pdf-sample.pdf", Utils.CloudStorage_Input_Folder);
                BookmarkResponse bookmarkResponse = pdfService.Bookmarks.ReadDocumentBookmark("pdf-sample.pdf", 1, Utils.CloudStorage_Input_Folder);
            }
            catch (Exception ex)
            {
                Assert.Fail(ex.Message);
            }
        }

        [TestMethod()]
        public void Pdf_AppendMerge_Tests()
        {
            try
            {
                storageService.File.CopyFile(Utils.CloudStorage_Input_Folder + "/pdf-sample.pdf", Utils.CloudStorage_Output_Folder + "/pdf-merge1.pdf");
                storageService.File.CopyFile(Utils.CloudStorage_Input_Folder + "/pdf-sample.pdf", Utils.CloudStorage_Output_Folder + "/pdf-merge2.pdf");

                PDFDocumentsList list = new PDFDocumentsList();
                list.List.Add(Utils.CloudStorage_Output_Folder + "/pdf-merge1.pdf");
                list.List.Add(Utils.CloudStorage_Output_Folder + "/pdf-merge2.pdf");
                list.List.Add(Utils.CloudStorage_Output_Folder + "/pdf-merge1.pdf");

                pdfService.AppendMerge.MergeAListOfDocuments("pdf-merged.pdf", Utils.CloudStorage_Output_Folder, list);

                storageService.File.DownloadFile(Utils.CloudStorage_Output_Folder + "/pdf-merged.pdf", Utils.Local_Output_Path + "/pdf-merged.pdf");
            }
            catch (Exception ex)
            {
                Assert.Fail(ex.Message);
            }
        }

        [TestMethod()]
        public void Pdf_Fields_Tests()
        {
            try
            {
                #region Create and single update field

                storageService.File.CopyFile(Utils.CloudStorage_Input_Folder + "/pdf-sample1.pdf", Utils.CloudStorage_Output_Folder + "/pdf-fields.pdf");

                Field field = new Field();

                field.Name = "checkBoxField2";
                field.Type = "Boolean";
                field.Values.Add("Signature22");

                field.Rect = new Rectangle(50, 50, 100, 100);

                pdfService.Fields.CreateField("pdf-fields.pdf", 1, field, Utils.CloudStorage_Output_Folder);

                FieldResponse fieldResponse = pdfService.Fields.GetDocumentFieldByName("pdf-fields.pdf", "checkBoxField2", Utils.CloudStorage_Output_Folder);

                fieldResponse.Field.Values.RemoveAt(0);
                fieldResponse.Field.Values.Add("1");

                pdfService.Fields.UpdateField("pdf-fields.pdf", "checkBoxField2", fieldResponse.Field, Utils.CloudStorage_Output_Folder);


                #endregion Create and update field

                storageService.File.CopyFile(Utils.CloudStorage_Input_Folder + "/pdf-sample1.pdf", Utils.CloudStorage_Output_Folder + "/pdf-fields.pdf");

                Field field2 = new Field();

                field2.Name = "checkBoxField1";
                field2.Type = "Boolean";
                field2.Values.Add("0");
                field2.Rect = new Rectangle(200, 200, 50, 50);

                pdfService.Fields.CreateField("pdf-fields.pdf", 1, field2, Utils.CloudStorage_Output_Folder);

                field2.Name = "text1";
                field2.Type = "text";
                field2.Rect = new Rectangle(300, 300, 200, 25);

                pdfService.Fields.CreateField("pdf-fields.pdf", 1, field2, Utils.CloudStorage_Output_Folder);

                FieldsResponse fieldsResponse = pdfService.Fields.GetDocumentFields("pdf-fields.pdf", Utils.CloudStorage_Output_Folder);

                fieldsResponse.Fields.List[0].Values.RemoveAt(0);
                fieldsResponse.Fields.List[1].Values.RemoveAt(0);

                fieldsResponse.Fields.List[0].Values.Add("1");
                fieldsResponse.Fields.List[1].Values.Add("some dummy text");

                pdfService.Fields.UpdateFields("pdf-fields.pdf", fieldsResponse.Fields, Utils.CloudStorage_Output_Folder);

            }
            catch (Exception ex)
            {
                Assert.Fail(ex.Message);
            }
        }

        [TestMethod()]
        public void Pdf_Links_Tests()
        {
            try
            {
                LinksResponse linksResponse = pdfService.Links.ReadDocumentPageLinkAnnotations("pdf-sample.pdf", 1, Utils.CloudStorage_Input_Folder);
                LinkResponse linkResponse = pdfService.Links.ReadDocumentPageLinkAnnotationByItsIndex("pdf-sample.pdf", 1, 1, Utils.CloudStorage_Input_Folder);
            }
            catch (Exception ex)
            {
                Assert.Fail(ex.Message);
            }
        }

        [TestMethod()]
        public void Pdf_Properties_Tests()
        {
            try
            {
                storageService.File.CopyFile(Utils.CloudStorage_Input_Folder + "/pdf-sample.pdf", Utils.CloudStorage_Output_Folder + "/pdf-props.pdf");

                DocumentPropertiesResponse documentPropertiesResponse = pdfService.Properties.ReadDocumentProperties("pdf-props.pdf", Utils.CloudStorage_Output_Folder);

                DocumentProperty documentProperty = new DocumentProperty();
                documentProperty.Name = "Owner";
                documentProperty.Value = "Aspose";
                pdfService.Properties.AddUpdateDocumentProperty("pdf-props.pdf", "Owner", Utils.CloudStorage_Output_Folder, documentProperty);

                documentPropertiesResponse = pdfService.Properties.ReadDocumentProperties("pdf-props.pdf", Utils.CloudStorage_Output_Folder);

                DocumentPropertyResponse documentPropertyResponse = pdfService.Properties.ReadDocumentPropertyByName("pdf-props.pdf", "Owner", Utils.CloudStorage_Output_Folder);

                pdfService.Properties.DeleteDocumentProperty("pdf-props.pdf", "Owner", Utils.CloudStorage_Output_Folder);

                pdfService.Properties.DeleteDocumentProperties("pdf-props.pdf", Utils.CloudStorage_Output_Folder);
            }
            catch (Exception ex)
            {
                Assert.Fail(ex.Message);
            }
        }

        [TestMethod()]
        public void Pdf_TextItems_Tests()
        {
            try
            {
                TextItemsResponse textItemsResponse = pdfService.TextItems.ReadPageTextItems("pdf-sample.pdf", 1, Utils.CloudStorage_Input_Folder);
                TextItemsResponse textItemsResponse2 = pdfService.TextItems.ReadDocumentTextItems("pdf-sample.pdf", Utils.CloudStorage_Input_Folder);
            }
            catch (Exception ex)
            {
                Assert.Fail(ex.Message);
            }
        }

        [TestMethod()]
        public void Pdf_TextReplace_Tests()
        {
            try
            {
                storageService.File.CopyFile(Utils.CloudStorage_Input_Folder + "/pdf-sample.pdf", Utils.CloudStorage_Output_Folder + "/pdf-textReplace.pdf");

                TextReplace textReplace = new TextReplace("Aspose", "Aspose Pty Ltd.", true);
                DocumentReplaceTextResponse documentReplaceTextResponse = pdfService.TextReplace.ReplaceText("pdf-textReplace.pdf", Utils.CloudStorage_Output_Folder, textReplace);

                storageService.File.DownloadFile(Utils.CloudStorage_Output_Folder + "/pdf-textReplace.pdf", Utils.Local_Output_Path + "/pdf-textReplace.pdf");

                //------------------------------

                storageService.File.CopyFile(Utils.CloudStorage_Input_Folder + "/pdf-sample.pdf", Utils.CloudStorage_Output_Folder + "/pdf-textReplace2.pdf");

                TextReplace textReplace2 = new TextReplace("Aspose", "Aspose Pty Ltd.", true);
                PageReplaceTextResponse pageTextReplaceResponse = pdfService.TextReplace.ReplaceText("pdf-textReplace2.pdf", 1, Utils.CloudStorage_Output_Folder, textReplace2);

                storageService.File.DownloadFile(Utils.CloudStorage_Output_Folder + "/pdf-textReplace2.pdf", Utils.Local_Output_Path + "/pdf-textReplace2.pdf");

                //========================================

                storageService.File.CopyFile(Utils.CloudStorage_Input_Folder + "/pdf-sample.pdf", Utils.CloudStorage_Output_Folder + "/pdf-textReplaceList.pdf");

                TextReplaceList textReplaceList = new TextReplaceList();
                textReplaceList.TextReplaces.Add(new TextReplace("Aspose", "Aspose Pty Ltd.", true));
                textReplaceList.TextReplaces.Add(new TextReplace("MS Office", "Microsoft Office", true));

                DocumentReplaceTextResponse textReplaceResponse = pdfService.TextReplace.ReplaceTextList("pdf-textReplaceList.pdf", Utils.CloudStorage_Output_Folder, textReplaceList);

                storageService.File.DownloadFile(Utils.CloudStorage_Output_Folder + "/pdf-textReplaceList.pdf", Utils.Local_Output_Path + "/pdf-textReplaceList.pdf");

                //-------------------------------------------------

                storageService.File.CopyFile(Utils.CloudStorage_Input_Folder + "/pdf-sample.pdf", Utils.CloudStorage_Output_Folder + "/pdf-textReplaceList2.pdf");

                TextReplaceList textReplaceList2 = new TextReplaceList();
                textReplaceList2.TextReplaces.Add(new TextReplace("Aspose", "Aspose Pty Ltd.", true));
                textReplaceList2.TextReplaces.Add(new TextReplace("MS Office", "Microsoft Office", true));
                PageReplaceTextResponse pageTextReplaceResponse2 = pdfService.TextReplace.ReplaceTextList("pdf-textReplaceList2.pdf", 1, Utils.CloudStorage_Output_Folder, textReplaceList2);

                storageService.File.DownloadFile(Utils.CloudStorage_Output_Folder + "/pdf-textReplaceList2.pdf", Utils.Local_Output_Path + "/pdf-textReplaceList2.pdf");

            }
            catch (Exception ex)
            {
                Assert.Fail(ex.Message);
            }
        }

        [TestMethod()]
        public void Pdf_Pages_Tests()
        {
            try
            {
                storageService.File.CopyFile(Utils.CloudStorage_Input_Folder + "/pdf-sample.pdf", Utils.CloudStorage_Output_Folder + "/pdf-pages.pdf");
                PagesResponse pagesResponse = pdfService.Pages.ReadDocumentPagesInfo("pdf-pages.pdf", Utils.CloudStorage_Output_Folder);
                System.Threading.Thread.Sleep(3000); // Just for testing
                PagesResponse pagesResponse2 = pdfService.Pages.AddNewPageToEndOfTheDocument("pdf-pages.pdf", Utils.CloudStorage_Output_Folder);
                PageResponse pageResponse = pdfService.Pages.ReadDocumentPageInfo("pdf-pages.pdf", 1, Utils.CloudStorage_Output_Folder);
                System.Threading.Thread.Sleep(3000); // Just for testing

                pdfService.Pages.ConvertToSomeFormat("pdf-pages.pdf", 1, PDFPageConvertFormat.Png, Utils.Local_Output_Path + "\\pdf-page-out.png", Utils.CloudStorage_Output_Folder);
                WordsPerPageResponse wordsPerPageResponse = pdfService.Pages.GetNumberOfWordsPerDocumentPage("pdf-sample.pdf", Utils.CloudStorage_Input_Folder);
                System.Threading.Thread.Sleep(3000); // Just for testing

                pdfService.Pages.DeleteDocumentPageByItsNumber("pdf-pages.pdf", 1, Utils.CloudStorage_Output_Folder);
                pdfService.Pages.MovePageToNewPosition("pdf-pages.pdf", 1, 3, Utils.CloudStorage_Output_Folder);

                System.Threading.Thread.Sleep(3000); // Just for testing

                //---------------------------------------------------------------------

                storageService.File.CopyFile(Utils.CloudStorage_Input_Folder + "/pdf-sample.pdf", Utils.CloudStorage_Output_Folder + "/pdf-pages.pdf");

                PDFSignature signature = new PDFSignature();
                signature.SignaturePath = Utils.CloudStorage_Input_Folder + "/signature.pfx";
                signature.SignatureType = "PKCS7";
                signature.Password = "signature";
                signature.Appearance = Utils.CloudStorage_Input_Folder + "/signature.jpg";
                signature.Reason = "Success";

                signature.Contact = "marketplace@aspose.com";
                signature.Location = "Australia";
                signature.Visible = true;
                signature.FormFieldName = "Signature1";
                signature.Authority = "Aspose Marketplace";
                signature.Rectangle = new Rectangle(100, 100, 200, 200);
                signature.Date = DateTime.Now.ToString();

                pdfService.Pages.SignPage("pdf-pages.pdf", 1, Utils.CloudStorage_Output_Folder, signature);

                //---------------------------------------------------------------------
                System.Threading.Thread.Sleep(3000); // Just for testing
                storageService.File.CopyFile(Utils.CloudStorage_Input_Folder + "/pdf-sample.pdf", Utils.CloudStorage_Output_Folder + "/pdf-pages.pdf");

                PageStamp stamp = new PageStamp();
                stamp.Type = 0;
                stamp.Background = true;
                stamp.BottomMargin = 2.1;
                stamp.HorizontalAlignment = 0;
                stamp.LeftMargin = 3.1;
                stamp.Opacity = 4.1;
                stamp.RightMargin = 5.1;
                stamp.Rotate = 0;
                stamp.RotateAngle = 6.1;
                stamp.TopMargin = 7.1;
                stamp.VerticalAlignment = 0;
                stamp.XIndent = 8.1;
                stamp.YIndent = 9.1;
                stamp.Zoom = 10.1;
                stamp.TextAlignment = 0;
                stamp.Value = "sample string 11";

                Color backGroundColor = new Color(64, 64, 64, 64);
                TextState textState = new TextState();
                textState.BackgroundColor = backGroundColor;
                textState.ForegroundColor = backGroundColor;
                textState.FontSize = 12;
                textState.Font = "Tahoma";
                textState.FontStyle = 1;
                stamp.TextState = textState;

                stamp.FileName = Utils.CloudStorage_Input_Folder + "/signature.jpg";
                stamp.Width = 13.1;
                stamp.Height = 14.1;
                stamp.PageIndex = 1;
                stamp.StartingNumber = 1;

                System.Threading.Thread.Sleep(3000); // Just for testing

                pdfService.Pages.AddPageStamp("pdf-pages.pdf", 1, Utils.CloudStorage_Output_Folder, stamp);

                System.Threading.Thread.Sleep(3000); // Just for testing

                storageService.File.DownloadFile(Utils.CloudStorage_Output_Folder + "/pdf-pages.pdf", Utils.Local_Output_Path + "/pdf-pages.pdf");
            }
            catch (Exception ex)
            {
                Assert.Fail(ex.Message);
            }
        }

        [TestMethod()]
        public void Pdf_FragmentsAndSegments_Tests()
        {
            try
            {
                TextItemsResponse textItemsResponse = pdfService.FragmentsAndSegments.ReadPageFragments("pdf-sample.pdf", 1, true, Utils.CloudStorage_Input_Folder);
                TextItemsResponse textItemsResponse2 = pdfService.FragmentsAndSegments.ReadPageFragment("pdf-sample.pdf", 1, 1, true, Utils.CloudStorage_Input_Folder);
                TextFormatResponse textFormatResponse = pdfService.FragmentsAndSegments.ReadPageFragmentTextFormat("pdf-sample.pdf", 1, 1, Utils.CloudStorage_Input_Folder);
                TextItemsResponse textItemsResponse3 = pdfService.FragmentsAndSegments.ReadFragmentSegments("pdf-sample.pdf", 1, 1, true, Utils.CloudStorage_Input_Folder);
                TextItemResponse textItemResponse = pdfService.FragmentsAndSegments.ReadSegment("pdf-sample.pdf", 1, 1, 1, Utils.CloudStorage_Input_Folder);
                TextFormatResponse textFormatResponse2 = pdfService.FragmentsAndSegments.ReadSegmentTextFormat("pdf-sample.pdf", 1, 1, 1, Utils.CloudStorage_Input_Folder);
            }
            catch (Exception ex)
            {
                Assert.Fail(ex.Message);
            }
        }
    }
}
