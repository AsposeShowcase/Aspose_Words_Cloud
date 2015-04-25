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
    //Methods not tested

    //wordsService.WordsParagraph.UpdateDocumentFont

    [TestClass()]
    public class WordsServiceTests
    {
        WordsService wordsService = new WordsService(Utils.AppSid, Utils.AppKey);
        StorageService storageService = new StorageService(Utils.AppSid, Utils.AppKey);


        [TestMethod()]
        public void Words_Tests()
        {
            try
            {
                storageService.File.CopyFile(Utils.CloudStorage_Input_Folder + "/doc-sample.doc", Utils.CloudStorage_Output_Folder + "/doc-sample.doc");

                WordDocumentResponse wordDocumentResponse = wordsService.ReadDocumentCommonInfo("doc-sample.doc", Utils.CloudStorage_Output_Folder);
                wordsService.ConvertDocument("doc-sample.doc", WordOutputFormat.Pdf, Utils.CloudStorage_Output_Folder, Utils.CloudStorage_Output_Folder + "/doc-out-pdf.pdf");
                wordsService.ConvertDocument("doc-sample.doc", WordOutputFormat.Pdf, Utils.CloudStorage_Output_Folder, Utils.Local_Output_Path + "\\doc-out-pdf.pdf");
                wordsService.ConvertDocument("doc-sample.doc", WordOutputFormat.Html, Utils.CloudStorage_Output_Folder, Utils.Local_Output_Path + "\\doc-out-html.html");
                wordsService.ConvertDocument(WordOutputFormat.Tiff, Utils.CloudStorage_Output_Folder + "/doc-out-tiff.tiff", Utils.Local_Input_Path + "doc-sample.doc");

                PageNumberSettings pageNumberSettings = new PageNumberSettings("{PAGE} of {NUMPAGES}", "right", true, true);
                wordsService.InsertDocumentPageNumbers("doc-sample.doc", string.Empty, Utils.CloudStorage_Output_Folder, pageNumberSettings);

                wordsService.InsertDocumentWatermarkImage("doc-sample.doc", string.Empty, 45.0, Utils.CloudStorage_Input_Folder + "/signature.jpg", Utils.CloudStorage_Output_Folder);
                wordsService.InsertDocumentWatermarkImage("doc-sample.doc", string.Empty, 135.0, Utils.Local_Input_Path + "signature.jpg", Utils.CloudStorage_Output_Folder);
                wordsService.InsertDocumentWatermarkText("doc-sample.doc", "Watermarked by Aspose", 45.0, string.Empty, Utils.CloudStorage_Output_Folder);



                //                string jSon = @"<?xml version=\""1.0\"" encoding=\""utf-8\""?>
                //                            <Orders>
                //                              <Order>
                //                                <Number>23</Number>
                //                                <Address>Nelson Street</Address>
                //                                <Suburb>Howick</Suburb>
                //                                <City>Auckland</City>
                //                                <Phonenumber>543 1234</Phonenumber>
                //                                <Date>03/01/2010</Date>
                //                                <Total>14.00</Total>
                //                                <Item>
                //                                  <Name>BBQ Chicken Pizza</Name>
                //                                  <Price>6.00</Price>
                //                                  <Quantity>1</Quantity>
                //                                  <ItemTotal>6.00</ItemTotal>
                //                                </Item>
                //                                <Item>
                //                                  <Name>1.5 Litre Coke</Name>
                //                                  <Price>4.00</Price>
                //                                  <Quantity>2</Quantity>
                //                                  <ItemTotal>8.00</ItemTotal>
                //                                </Item>
                //                              </Order>
                //                              <Order>
                //                                <Number>10</Number>
                //                                <Address>Parkville Avenue</Address>
                //                                <Suburb>Pakuranga</Suburb>
                //                                <City>Auckland</City>
                //                                <Phonenumber>548 7342</Phonenumber>
                //                                <Date>05/03/2010</Date>
                //                                <Total>6.00</Total>
                //                                <Item>
                //                                  <Name>Hawaiian Pizza</Name>
                //                                  <Price>4.00</Price>
                //                                  <Quantity>1</Quantity>
                //                                  <ItemTotal>4.00</ItemTotal>
                //                                </Item>
                //                                <Item>
                //                                  <Name>Fries</Name>
                //                                  <Price>1.00</Price>
                //                                  <Quantity>2</Quantity>
                //                                  <ItemTotal>2.00</ItemTotal>
                //                                </Item>
                //                              </Order>
                //                            </Orders>";

                //wordsService.PopulateDocumentTemplateWithData("doc-sample2.doc", WordCleanupOptions.ContainingFields, string.Empty, Utils.CloudStorage_Output_Folder, jSon);


                WordDocumentEntryRequest wordDocumentEntryRequest = new WordDocumentEntryRequest();
                wordDocumentEntryRequest.DocumentEntries.Add(new WordDocumentEntry(Utils.CloudStorage_Input_Folder + "/doc-sample.doc", WordImportFormatMode.KeepSourceFormatting));
                wordDocumentEntryRequest.DocumentEntries.Add(new WordDocumentEntry(Utils.CloudStorage_Input_Folder + "/doc-sample.doc", WordImportFormatMode.KeepSourceFormatting));
                wordsService.SplitDocument("doc-sample.doc", SplitDocumentFormat.Jpeg, 1, 2, Utils.CloudStorage_Output_Folder);
                WordsDocumentResponse wordsDocumentResponse = wordsService.AppendDocumentsToOriginalDocument("doc-sample.doc", wordDocumentEntryRequest, string.Empty, Utils.CloudStorage_Output_Folder);

                WordsSplitResultResponse wordsSplitResultResponse = wordsService.SplitDocument("doc-sample.doc", SplitDocumentFormat.Jpeg, 1, 2, Utils.CloudStorage_Output_Folder);

                // Please check http://www.aspose.com/docs/display/wordscloud/loadWebDocument+%28Controller+resource%29 for details
                //string requestData = @"<?xml version=""\1.0""\ encoding=""\utf-16""\?>
                //                    <LoadWebDocumentData xmlns:xsd=""\http://www.w3.org/2001/XMLSchema""\ xmlns:xsi=""\http://www.w3.org/2001/XMLSchema-instance""\>
                //                        <LoadingDocumentUrl>http://www.aspose.com</LoadingDocumentUrl>
                //                        <DocSaveOptionsData>
                //                        <SaveFormat>doc</SaveFormat>
                //                        <FileName>google.doc</FileName>
                //                        <SaveRoutingSlip>true</SaveRoutingSlip>
                //                        </DocSaveOptionsData>
                //                    </LoadWebDocumentData>";

                //WordsSaveResultResponse wordsSaveResultResponse = wordsService.LoadNewDocumentFromWebIntoFile(requestData);
            }
            catch (Exception ex)
            {
                Assert.Fail(ex.Message);
            }
        }

        [TestMethod()]
        public void Words_Bookmarks_Tests()
        {
            try
            {
                storageService.File.CopyFile(Utils.CloudStorage_Input_Folder + "/doc-sample.doc", Utils.CloudStorage_Output_Folder + "/doc-sample.doc");

                WordsBookmarkRequest wordsBookmarkRequest = new WordsBookmarkRequest("AsposePdf", "Aspose.Pdf for .NET");
                wordsService.WordsBookmarks.UpdateDocumentBookmark("doc-sample.doc", wordsBookmarkRequest.Name, wordsBookmarkRequest, string.Empty, Utils.CloudStorage_Output_Folder);
                WordsBookmarksResponse wordsBookmarksResponse = wordsService.WordsBookmarks.ReadDocumentBookmarksCommonInfo("doc-sample.doc", Utils.CloudStorage_Output_Folder);
                WordsBookmarkResponse wordsBookmarkResponse = wordsService.WordsBookmarks.ReadDocumentBookmarkDataByItsName("doc-sample.doc", wordsBookmarkRequest.Name, Utils.CloudStorage_Output_Folder);
            }
            catch (Exception ex)
            {
                Assert.Fail(ex.Message);
            }
        }

        [TestMethod()]
        public void Words_DocumentFields_Tests()
        {
            try
            {
                storageService.File.CopyFile(Utils.CloudStorage_Input_Folder + "/doc-sample.doc", Utils.CloudStorage_Output_Folder + "/doc-sample.doc");
                WordsDocumentResponse wordsDocumentResponse = wordsService.WordsDocumentFields.UpdateFieldsInDocument("doc-sample.doc", string.Empty, Utils.CloudStorage_Output_Folder);
            }
            catch (Exception ex)
            {
                Assert.Fail(ex.Message);
            }
        }

        [TestMethod()]
        public void Words_DocumentProperties_Tests()
        {
            try
            {
                storageService.File.CopyFile(Utils.CloudStorage_Input_Folder + "/doc-sample.doc", Utils.CloudStorage_Output_Folder + "/doc-sample.doc");

                WordsDocumentPropertiesResponse wordsDocumentPropertiesResponse = wordsService.WordsDocumentProperties.ReadDocumentPropertiesInfo("doc-sample.doc", Utils.CloudStorage_Output_Folder);

                WordsDocumentProperty wordsDocumentProperty = new WordsDocumentProperty();
                wordsDocumentProperty.Name = "CustomAuthor";
                wordsDocumentProperty.Value = "Aspose";

                WordsDocumentPropertyResponse wordsDocumentPropertyResponse2 = wordsService.WordsDocumentProperties.AddOrUpdateDocumentProperty("doc-sample.doc", wordsDocumentProperty.Name, wordsDocumentProperty, string.Empty, Utils.CloudStorage_Output_Folder);

                wordsDocumentProperty.Value = "Aspose Pty Ltd.";
                WordsDocumentPropertyResponse wordsDocumentPropertyResponse3 = wordsService.WordsDocumentProperties.AddOrUpdateDocumentProperty("doc-sample.doc", wordsDocumentProperty.Name, wordsDocumentProperty, string.Empty, Utils.CloudStorage_Output_Folder);
                WordsDocumentPropertyResponse wordsDocumentPropertyResponse = wordsService.WordsDocumentProperties.ReadDocumentPropertyInfoByPropertyName("doc-sample.doc", wordsDocumentProperty.Name, Utils.CloudStorage_Output_Folder);

                wordsService.WordsDocumentProperties.DeleteDocumentProperty("doc-sample.doc", wordsDocumentProperty.Name, string.Empty, Utils.CloudStorage_Output_Folder);

                //storageService.File.DownloadFile(Utils.CloudStorage_Output_Folder + "/doc-sample.doc", Utils.Local_Output_Path + "/doc-sample.doc");
            }
            catch (Exception ex)
            {
                Assert.Fail(ex.Message);
            }
        }

        [TestMethod()]
        public void Words_DocumentProtection_Tests()
        {
            try
            {
                storageService.File.CopyFile(Utils.CloudStorage_Input_Folder + "/doc-sample.doc", Utils.CloudStorage_Output_Folder + "/doc-sample.doc");

                WordsProtectionDataResponse wordsProtectionDataResponse = wordsService.WordsDocumentProtection.ReadDocumentProtectionCommonInfo("doc-sample.doc", Utils.CloudStorage_Output_Folder);

                WordsProtectionRequest wordsProtectionRequest = new WordsProtectionRequest("aspose", "aspose", ProtectionType.ReadOnly);
                wordsService.WordsDocumentProtection.ProtectDocument("doc-sample.doc", string.Empty, wordsProtectionRequest, Utils.CloudStorage_Output_Folder);

                WordsProtectionRequest wordsProtectionRequest2 = new WordsProtectionRequest("aspose", "aspose2", ProtectionType.ReadOnly);
                wordsService.WordsDocumentProtection.ChangeDocumentProtection("doc-sample.doc", string.Empty, wordsProtectionRequest2, Utils.CloudStorage_Output_Folder);

                WordsProtectionRequest wordsProtectionRequest3 = new WordsProtectionRequest("aspose2", string.Empty, ProtectionType.NoProtection);
                wordsService.WordsDocumentProtection.UnprotectDocument("doc-sample.doc", string.Empty, wordsProtectionRequest3, Utils.CloudStorage_Output_Folder);

                //storageService.File.DownloadFile(Utils.CloudStorage_Output_Folder + "/doc-sample.doc", Utils.Local_Output_Path + "/doc-sample.doc");
            }
            catch (Exception ex)
            {
                Assert.Fail(ex.Message);
            }
        }

        [TestMethod()]
        public void Words_DocumentSaveAs_Tests()
        {
            try
            {
                storageService.File.CopyFile(Utils.CloudStorage_Input_Folder + "/doc-sample.doc", Utils.CloudStorage_Output_Folder + "/doc-sample.doc");
                wordsService.WordsDocumentSaveAs.ConvertDocumentToTiff("doc-sample.doc", "word-tiff-out.tiff", Utils.CloudStorage_Output_Folder);
                storageService.File.DownloadFile(Utils.CloudStorage_Output_Folder + "/word-tiff-out.tiff", Utils.Local_Output_Path + "/word-tiff-out.tiff");
            }
            catch (Exception ex)
            {
                Assert.Fail(ex.Message);
            }
        }

        [TestMethod()]
        public void Words_DocumentStatistics_Tests()
        {
            try
            {
                WordsStatDataResponse WordsStatDataResponse = wordsService.WordsDocumentStatistics.ReadDocumentStatistics("doc-sample.doc", Utils.CloudStorage_Input_Folder);
            }
            catch (Exception ex)
            {
                Assert.Fail(ex.Message);
            }
        }

        [TestMethod()]
        public void Words_DocumentWatermark_Tests()
        {
            try
            {
                storageService.File.CopyFile(Utils.CloudStorage_Input_Folder + "/doc-sample.doc", Utils.CloudStorage_Output_Folder + "/doc-sample.doc");

                wordsService.WordsDocumentWatermark.InsertDocumentWatermarkImage("doc-sample.doc", string.Empty, 45.0, Utils.CloudStorage_Input_Folder + "/signature.jpg", Utils.CloudStorage_Output_Folder);
                wordsService.WordsDocumentWatermark.InsertDocumentWatermarkImage("doc-sample.doc", string.Empty, 135.0, Utils.Local_Input_Path + "signature.jpg", Utils.CloudStorage_Output_Folder);
                wordsService.WordsDocumentWatermark.InsertDocumentWatermarkText("doc-sample.doc", "Watermarked by Aspose", 45.0, string.Empty, Utils.CloudStorage_Output_Folder);
                wordsService.WordsDocumentWatermark.DeleteWatermark("doc-sample.doc", string.Empty, Utils.CloudStorage_Output_Folder);

                //storageService.File.DownloadFile(Utils.CloudStorage_Output_Folder + "/doc-sample.doc", Utils.Local_Output_Path + "/doc-sample.doc");
            }
            catch (Exception ex)
            {
                Assert.Fail(ex.Message);
            }
        }

        [TestMethod()]
        public void Words_DrawingObjects_Tests()
        {
            try
            {
                WordsDrawingObjectsResponse wordsDrawingObjectsResponse = wordsService.WordsDrawingObjects.ReadDocumentDrawingObjectsCommonInfo("doc-sample.doc", Utils.CloudStorage_Input_Folder);
                WordsDrawingObjectResponse wordsDrawingObjectResponse = wordsService.WordsDrawingObjects.ReadDrawingObjectCommonInfoByItsIndex("doc-sample.doc", 1, Utils.CloudStorage_Input_Folder);
                wordsService.WordsDrawingObjects.ConvertToFormatSpecified("doc-sample.doc", 1, WordDrawingObjectsFormat.Png, Utils.Local_Output_Path + "word-drawing-out.png", Utils.CloudStorage_Input_Folder);

                wordsService.WordsDrawingObjects.ReadDrawingObjectImageData("doc-sample.doc", 1, Utils.CloudStorage_Input_Folder, Utils.Local_Output_Path + "word-out2.png");
                wordsService.WordsDrawingObjects.GetDrawingObjectOleData("doc-sample.doc", 18, Utils.CloudStorage_Input_Folder, Utils.Local_Output_Path + "word-out3.png");
            }
            catch (Exception ex)
            {
                Assert.Fail(ex.Message);
            }
        }

        [TestMethod()]
        public void Words_HeadersFooters_Tests()
        {
            try
            {
                storageService.File.CopyFile(Utils.CloudStorage_Input_Folder + "/doc-sample.doc", Utils.CloudStorage_Output_Folder + "/doc-sample.doc");
                wordsService.WordsHeadersFooters.DeleteDocumentHeadersAndFooters("doc-sample.doc", string.Empty, string.Empty, Utils.CloudStorage_Output_Folder);

                //storageService.File.DownloadFile(Utils.CloudStorage_Output_Folder + "/doc-sample.doc", Utils.Local_Output_Path + "/doc-sample.doc");
            }
            catch (Exception ex)
            {
                Assert.Fail(ex.Message);
            }
        }

        [TestMethod()]
        public void Words_Hyperlinks_Tests()
        {
            try
            {
                WordsHyperlinksResponse wordsHyperlinksResponse = wordsService.WordsHyperlinks.ReadDocumentHyperlinksCommonInfo("doc-sample.doc", Utils.CloudStorage_Input_Folder);
                WordsHyperlinkResponse wordsHyperlinkResponse = wordsService.WordsHyperlinks.ReadDocumentHyperlinkByItsIndex("doc-sample.doc", 0, Utils.CloudStorage_Input_Folder);
            }
            catch (Exception ex)
            {
                Assert.Fail(ex.Message);
            }
        }

        [TestMethod()]
        public void Words_MailMerge_Tests()
        {
            try
            {
                storageService.File.CopyFile(Utils.CloudStorage_Input_Folder + "/doc-sample3.doc", Utils.CloudStorage_Output_Folder + "/doc-sample3.doc");
                WordsFieldNamesResponse wordsFieldNamesResponse = wordsService.WordsMailMerge.ReadDocumentFieldNames("doc-sample3.doc", true, Utils.CloudStorage_Output_Folder);

                WordsDocumentResponse wordsDocumentResponse = wordsService.WordsMailMerge.ExecuteDocumentMailMergeOperation("doc-sample3.doc", true, Utils.CloudStorage_Input_Folder + "/word-mailmerger-data.xml", string.Empty, string.Empty, Utils.CloudStorage_Output_Folder);

                storageService.File.DownloadFile(Utils.CloudStorage_Output_Folder + "/" + wordsDocumentResponse.Document.FileName, Utils.Local_Output_Path + "/" + wordsDocumentResponse.Document.FileName);
            }
            catch (Exception ex)
            {
                Assert.Fail(ex.Message);
            }
        }

        [TestMethod()]
        public void Words_Paragraph_Tests()
        {
            try
            {
                storageService.File.CopyFile(Utils.CloudStorage_Input_Folder + "/doc-sample.doc", Utils.CloudStorage_Output_Folder + "/doc-sample.doc");

                WordsParagraphsResponse wordsParagraphsResponse = wordsService.WordsParagraph.GetParagraphs("doc-sample.doc", Utils.CloudStorage_Output_Folder);
                WordsParagraphResponse wordsParagraphResponse = wordsService.WordsParagraph.GetParagraphByIndex("doc-sample.doc", 1, Utils.CloudStorage_Output_Folder);
                WordsRunResponse wordsRunResponse = wordsService.WordsParagraph.GetParagraphByIndexAndRun("doc-sample.doc", 1, 1, Utils.CloudStorage_Output_Folder);
                WordsFontResponse WordsFontResponse = wordsService.WordsParagraph.GetDocumentFontByParagraphAndRun("doc-sample.doc", 1, 1, Utils.CloudStorage_Output_Folder);
                //wordsService.WordsParagraph.UpdateDocumentFont("doc-sample.doc", 1, 1, string.Empty, WordsFontResponse.Font, Utils.CloudStorage_Output_Folder);
            }
            catch (Exception ex)
            {
                Assert.Fail(ex.Message);
            }
        }

        [TestMethod()]
        public void Words_Revisions_Tests()
        {
            try
            {
                storageService.File.CopyFile(Utils.CloudStorage_Input_Folder + "/doc-sample.doc", Utils.CloudStorage_Output_Folder + "/doc-sample.doc");

                WordRevisionResponse wordRevisionResponse = wordsService.WordsRevisions.AcceptAllRevisionsInDocument("doc-sample.doc", string.Empty, Utils.CloudStorage_Output_Folder);
                WordRevisionResponse wordRevisionResponse2 = wordsService.WordsRevisions.RejectAllRevisionsInDocument("doc-sample.doc", string.Empty, Utils.CloudStorage_Output_Folder);

            }
            catch (Exception ex)
            {
                Assert.Fail(ex.Message);
            }
        }

        [TestMethod()]
        public void Words_Sections_Tests()
        {
            try
            {
                storageService.File.CopyFile(Utils.CloudStorage_Input_Folder + "/doc-sample.doc", Utils.CloudStorage_Output_Folder + "/doc-sample.doc");

                WordsSectionsResponse wordsSectionsResponse = wordsService.WordsSections.GetSectionsList("doc-sample.doc", Utils.CloudStorage_Output_Folder);
                WordsSectionResponse wordsSectionResponse = wordsService.WordsSections.GetSectionByIndex("doc-sample.doc", 0, Utils.CloudStorage_Output_Folder);
                PageSetupResponse pageSetupResponse = wordsService.WordsSections.GetPageSetupOfSection("doc-sample.doc", 0, Utils.CloudStorage_Output_Folder);
                wordsService.WordsSections.UpdatePageSetupOfSection("doc-sample.doc", 0, string.Empty, pageSetupResponse.PageSetup, Utils.CloudStorage_Output_Folder);

                //storageService.File.DownloadFile(Utils.CloudStorage_Output_Folder + "/doc-sample.doc", Utils.Local_Output_Path + "/doc-sample.doc");
            }
            catch (Exception ex)
            {
                Assert.Fail(ex.Message);
            }
        }

        [TestMethod()]
        public void Words_TextItems_Tests()
        {
            try
            {
                storageService.File.CopyFile(Utils.CloudStorage_Input_Folder + "/doc-sample.doc", Utils.CloudStorage_Output_Folder + "/doc-sample.doc");

                WordsTextItemsResponse wordsTextItemsResponse = wordsService.WordsTextItems.ReadDocumentTextItems("doc-sample.doc", Utils.CloudStorage_Output_Folder);

                WordsReplaceText replaceText = new WordsReplaceText("Aspose", "Aspose Pty Ltd", true, false);
                wordsService.WordsTextItems.ReplaceDocumentText("doc-sample.doc", string.Empty, replaceText, Utils.CloudStorage_Output_Folder);

                //storageService.File.DownloadFile(Utils.CloudStorage_Output_Folder + "/doc-sample.doc", Utils.Local_Output_Path + "/doc-sample.doc");
            }
            catch (Exception ex)
            {
                Assert.Fail(ex.Message);
            }
        }
    }
}
