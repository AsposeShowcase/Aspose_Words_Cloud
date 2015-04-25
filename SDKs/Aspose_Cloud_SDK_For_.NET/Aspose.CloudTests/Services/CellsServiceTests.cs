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
    public class CellsServiceTests
    {
        CellsService cellsService = new CellsService(Utils.AppSid, Utils.AppKey);
        StorageService storageService = new StorageService(Utils.AppSid, Utils.AppKey);
        
        string name = "cells-sample.xlsx"; 
        string folder = Utils.CloudStorage_Input_Folder;

        [TestMethod()]
        public void Cells_WorksheetColumns_Tests()
        {
            try
            {
                storageService.File.CopyFile(Utils.CloudStorage_Input_Folder + "/cells-sample.xlsx", Utils.CloudStorage_Output_Folder + "/cells-sample.xlsx");

                WorksheetColumnsResponse worksheetColumnsResponse = cellsService.WorksheetColumns.ReadWorksheetColumnsInfo(name, "sheet1", Utils.CloudStorage_Output_Folder);
                WorksheetColumnResponse worksheetColumnResponse = cellsService.WorksheetColumns.SetWorksheetColumnWidth(name, "sheet1", 1, 500, Utils.CloudStorage_Output_Folder);
                WorksheetColumnResponse worksheetColumnResponse2 = cellsService.WorksheetColumns.ReadWorksheetColumnDataByColumnIndex(name, "sheet1", 1, Utils.CloudStorage_Output_Folder);

                cellsService.WorksheetColumns.HideWorksheetColumns(name, "sheet1", 0, 5, Utils.CloudStorage_Output_Folder);
                cellsService.WorksheetColumns.UnhideWorksheetColumns(name, "sheet1", 0, 5, 150, Utils.CloudStorage_Output_Folder);
                cellsService.WorksheetColumns.GroupWorksheetColumns(name, "sheet1", 0, 3, true, Utils.CloudStorage_Output_Folder);
                cellsService.WorksheetColumns.UngroupWorksheetColumns(name, "sheet1", 0, 3, Utils.CloudStorage_Output_Folder);

                cellsService.WorksheetColumns.ClearCellsContents(name, "sheet1", "A1:F5", 1, 1, 5, 5, Utils.CloudStorage_Output_Folder);
                cellsService.WorksheetColumns.ClearCellsFormats(name, "sheet1", "A1:F5", 1, 1, 5, 5, Utils.CloudStorage_Output_Folder);
                cellsService.WorksheetColumns.MergeCells(name, "sheet1", 0, 0, 5, 5, Utils.CloudStorage_Output_Folder);
                cellsService.WorksheetColumns.UnmergeCells(name, "sheet1", 0, 0, 5, 5, Utils.CloudStorage_Output_Folder);
                cellsService.WorksheetColumns.CopyWorksheetColumns(name, "sheet1", 1, 2, 2, "sheet1", Utils.CloudStorage_Output_Folder);

                WorksheetRowResponse WorksheetRowResponse = cellsService.WorksheetColumns.ReadWorksheetRowDataByRowIndex(name, "sheet1", 0, Utils.CloudStorage_Output_Folder);
                cellsService.WorksheetColumns.DeleteWorksheetRow(name, "sheet1", 1, Utils.CloudStorage_Output_Folder);
                cellsService.WorksheetColumns.InsertNewWorksheetRow(name, "sheet1", 2, Utils.CloudStorage_Output_Folder);
                cellsService.WorksheetColumns.UpdateWorksheetRow(name, "sheet1", 2, 120, Utils.CloudStorage_Output_Folder);

                cellsService.WorksheetColumns.HideWorksheetRows(name, "sheet1", 1, 2, Utils.CloudStorage_Output_Folder);
                cellsService.WorksheetColumns.UnhideWorksheetRows(name, "sheet1", 1, 5, 120, Utils.CloudStorage_Output_Folder);
                cellsService.WorksheetColumns.GroupWorksheetRows(name, "sheet1", 1, 3, true, Utils.CloudStorage_Output_Folder);
                cellsService.WorksheetColumns.UngroupWorksheetRows(name, "sheet1", 1, 4, true, Utils.CloudStorage_Output_Folder);
                cellsService.WorksheetColumns.CopyWorksheetRows(name, "sheet1", 1, 5, 5, "sheet1", Utils.CloudStorage_Output_Folder);

                CellsResponse cellsResponse = cellsService.WorksheetColumns.GetCellsInfo(name, "sheet1", 1, 10, Utils.CloudStorage_Output_Folder);
                WorkbookCellResponse workbookCellResponse1 = cellsService.WorksheetColumns.ReadCellDataByCellName(name, "sheet1", "C4", Utils.CloudStorage_Output_Folder);
                WorksheetRowsResponse worksheetRowsResponse = cellsService.WorksheetColumns.ReadWorksheetRowsInfo(name, "sheet1", Utils.CloudStorage_Output_Folder);

                cellsService.WorksheetColumns.SetHtmlstringValueIntoCell(name, "sheet1", "A1", Utils.CloudStorage_Output_Folder);
                cellsService.WorksheetColumns.DeleteSeveralWorksheetRows(name, "sheet1", 1, 10, false, Utils.CloudStorage_Output_Folder);

                cellsService.WorksheetColumns.CopyCellIntoCell(name, "sheet1", "A1", "sheet1", "C4", 5, 3, Utils.CloudStorage_Output_Folder);
                cellsService.WorksheetColumns.InsertSeveralNewWorksheetRows(name, "sheet1", 1, 10, true, Utils.CloudStorage_Output_Folder);
                cellsService.WorksheetColumns.SetCellRangeValue(name, "sheet1", "A1", "Aspose", "string", Utils.CloudStorage_Output_Folder);
                WorkbookCellResponse workbookCellResponse = cellsService.WorksheetColumns.SetCellValue(name, "sheet1", "A1", "Aspose", "string", string.Empty, Utils.CloudStorage_Output_Folder);
                WorksheetColumnsResponse worksheetColumnsResponse3 = cellsService.WorksheetColumns.InsertWorksheetColumns(name, "sheet1", 1, 5, false, Utils.CloudStorage_Output_Folder);
                cellsService.WorksheetColumns.DeleteWorksheetColumns(name, "sheet1", 1, 5, false, Utils.CloudStorage_Output_Folder);

                WorkbookStyleResponse workbookStyleResponse = cellsService.WorksheetColumns.ReadCellStyleInfo(name, "sheet1", "B5", Utils.CloudStorage_Output_Folder);
                cellsService.WorksheetColumns.UpdateCellStyle(name, "sheet1", "A1", workbookStyleResponse.Style, Utils.CloudStorage_Output_Folder);
                cellsService.WorksheetColumns.UpdateCellRangeStyle(name, "sheet1", "A1:F5", workbookStyleResponse.Style, Utils.CloudStorage_Output_Folder);

                storageService.File.DownloadFile(Utils.CloudStorage_Output_Folder + "/cells-sample.xlsx", Utils.Local_Output_Path + "/cells-sample.xlsx");
            }
            catch (Exception ex)
            {
                Assert.Fail(ex.Message);
            }
        }

        [TestMethod()]
        public void Cells_Autoshapes_Tests()
        {
            try
            {
                AutoShapesResponse autoShapesResponse = cellsService.Autoshapes.GetWorksheetAutoshapesInfo(name, "sheet2", Utils.CloudStorage_Input_Folder);
                AutoShapeResponse autoShapeResponse = cellsService.Autoshapes.GetAutoshapeInfo(name, "sheet2", 0, Utils.CloudStorage_Input_Folder);
                cellsService.Autoshapes.GetAutoshapeInSomeFormat(name, "sheet2", 0, CellsAutoshapeFormat.Jpeg, Utils.CloudStorage_Input_Folder, Utils.Local_Output_Path + "\\out.jpeg");
            }
            catch (Exception ex)
            {
                Assert.Fail(ex.Message);
            }
        }

        [TestMethod()]
        public void Cells_BarCodes_Tests()
        {
            try
            {
                BarcodeResponse barcodeResponse = cellsService.BarCodes.ExtractBarCodesFromWorksheetPicture(name, "sheet3", 0, Utils.CloudStorage_Input_Folder);
            }
            catch (Exception ex)
            {
                Assert.Fail(ex.Message);
            }
        }

        [TestMethod()]
        public void Cells_ChartArea_Tests()
        {
            try
            {
                ChartAreaResponse chartAreaResponse = cellsService.ChartArea.GetChartAreaInfo(name, "sheet4", 0, Utils.CloudStorage_Input_Folder);
                LineResponse lineResponse = cellsService.ChartArea.GetChartAreaBorderInfo(name, "sheet4", 0, Utils.CloudStorage_Input_Folder);
                FillFormatResponse fillFormatResponse = cellsService.ChartArea.GetChartAreaFillFormatInfo(name, "sheet4", 0, Utils.CloudStorage_Input_Folder);
            }
            catch (Exception ex)
            {
                Assert.Fail(ex.Message);
            }
        }

        [TestMethod()]
        public void Cells_Charts_Tests()
        {
            try
            {
                storageService.File.CopyFile(Utils.CloudStorage_Input_Folder + "/cells-sample.xlsx", Utils.CloudStorage_Output_Folder + "/cells-sample.xlsx");

                CellsChartsResponse CellsChartsResponse = cellsService.Charts.GetWorksheetChartsInfo(name, "sheet4", Utils.CloudStorage_Output_Folder);
                CellsChartResponse cellsChartResponse = cellsService.Charts.GetChartInfo(name, "sheet4", 0, Utils.CloudStorage_Output_Folder);

                cellsService.Charts.AddChartTitle(name, "sheet4", 0, cellsChartResponse.Chart, Utils.CloudStorage_Output_Folder);
                cellsService.Charts.GetChartInSomeFormat(name, "sheet4", 0, CellsChartOutputFormat.Bmp, Utils.CloudStorage_Output_Folder, Utils.Local_Output_Path + "cells-chart-out.bmp");
                cellsService.Charts.ShowLegendInChart(name, "sheet4", 0, Utils.CloudStorage_Output_Folder);
                cellsService.Charts.HideLegendInChart(name, "sheet4", 0, Utils.CloudStorage_Output_Folder);
                cellsService.Charts.HideTitleInChart(name, "sheet4", 0, Utils.CloudStorage_Output_Folder);

                CellsChartLegendResponse cellsChartLegendResponse = cellsService.Charts.GetChartLegend(name, "sheet4", 0, Utils.CloudStorage_Output_Folder);
                //cellsService.Charts.UpdateChartLegend(name, "sheet4", 0, Utils.CloudStorage_Output_Folder, cellsChartLegendResponse.Legend);

                cellsService.Charts.SetChartTitleVisible(name, "sheet4", 0, cellsChartResponse.Chart, Utils.CloudStorage_Output_Folder);
                cellsService.Charts.UpdateChartTitle(name, "sheet4", 0, cellsChartResponse.Chart, Utils.CloudStorage_Output_Folder);
                cellsService.Charts.AddNewChartToWorksheet(name, "sheet1", ChartType.Bar, 1, 1, 1, 1, "A1:F100", true, "", true, "Aspose.Cells for .NET", Utils.CloudStorage_Output_Folder);

                cellsService.Charts.DeleteWorksheetChartByIndex(name, "sheet4", 0, Utils.CloudStorage_Output_Folder);
                cellsService.Charts.ClearTheCharts(name, "sheet4", Utils.CloudStorage_Output_Folder);

                //storageService.File.DownloadFile(Utils.CloudStorage_Output_Folder + "/cells-sample.xlsx", Utils.Local_Output_Path + "/cells-sample.xlsx");
            }
            catch (Exception ex)
            {
                Assert.Fail(ex.Message);
            }
        }

        [TestMethod()]
        public void Cells_Hypelinks_Tests()
        {
            try
            {
                storageService.File.CopyFile(Utils.CloudStorage_Input_Folder + "/cells-sample.xlsx", Utils.CloudStorage_Output_Folder + "/cells-sample.xlsx");

                HyperlinksResponse hyperlinksResponse = cellsService.Hypelinks.GetWorksheetHyperlinks(name, "sheet1", Utils.CloudStorage_Output_Folder);
                CellsHyperlinkResponse cellsHyperlinkResponse = cellsService.Hypelinks.GetWorksheetHyperlinkByIndex(name, "sheet1", 0, Utils.CloudStorage_Output_Folder);

                CellsHyperlinkResponse cellsHyperlinkResponse2 = cellsService.Hypelinks.AddWorksheetHyperlink(name, "sheet1", 0, 0, 5, 5, "http://www.aspose.com", Utils.CloudStorage_Output_Folder);

                cellsHyperlinkResponse2.Hyperlink.TextToDisplay = "Aspose";

                CellsHyperlinkResponse cellsHyperlinkResponse3 = cellsService.Hypelinks.UpdateWorksheetHyperlinkByIndex(name, "sheet1", 0, cellsHyperlinkResponse2.Hyperlink, Utils.CloudStorage_Output_Folder);

                cellsService.Hypelinks.DeleteWorksheetHyperlinkByIndex(name, "sheet1", 0, Utils.CloudStorage_Output_Folder);
                cellsService.Hypelinks.DeleteAllHyperlinksInWorksheet(name, "sheet1", Utils.CloudStorage_Output_Folder);

                //storageService.File.DownloadFile(Utils.CloudStorage_Output_Folder + "/cells-sample.xlsx", Utils.Local_Output_Path + "/cells-sample.xlsx");
            }
            catch (Exception ex)
            {
                Assert.Fail(ex.Message);
            }
        }

        [TestMethod()]
        public void Cells_OleObjects_Tests()
        {
            try
            {
                storageService.File.CopyFile(Utils.CloudStorage_Input_Folder + "/cells-sample.xlsx", Utils.CloudStorage_Output_Folder + "/cells-sample.xlsx");

                CellsOleObjectsResponse cellsOleObjectsResponse = cellsService.OleObjects.GetWorksheetOleObjectsInfo(name, "Sheet5", Utils.CloudStorage_Output_Folder);
                CellsOleObjectResponse cellsOleObjectResponse2 = cellsService.OleObjects.GetOleObjectInfo(name, "sheet5", 0, Utils.CloudStorage_Output_Folder);

                CellsOleObject cellsOleObject = new CellsOleObject();
                cellsOleObject.SourceFullName = Utils.CloudStorage_Input_Folder + "/doc-sample.doc";
                cellsOleObject.ImageSourceFullName = Utils.CloudStorage_Output_Folder + "/signature.jpg";
                cellsOleObject.UpperLeftRow = 10;
                cellsOleObject.UpperLeftColumn = 10;
                cellsOleObject.Top = 10;
                cellsOleObject.Left = 20;
                cellsOleObject.Height = 20;
                cellsOleObject.Width = 20;

                CellsOleObjectResponse CellsOleObjectResponse3 = cellsService.OleObjects.AddOleObject(name, "sheet5", 1, 1, 200, 200, string.Empty, string.Empty, cellsOleObject, Utils.CloudStorage_Output_Folder);
                cellsService.OleObjects.UpdateOleObject(name, "sheet5", 0, cellsOleObject, Utils.CloudStorage_Output_Folder);

                cellsService.OleObjects.GetTheOleObjectInSomeFormat(name, "sheet5", 0, CellsOleObjectFormat.Gif, Utils.Local_Output_Path + "cells-ole-out.gif", Utils.CloudStorage_Output_Folder);

                cellsService.OleObjects.DeleteOleObject(name, "sheet5", 0, Utils.CloudStorage_Output_Folder);
                cellsService.OleObjects.DeleteAllOleObjects(name, "sheet5", Utils.CloudStorage_Output_Folder);

                //storageService.File.DownloadFile(Utils.CloudStorage_Output_Folder + "/cells-sample.xlsx", Utils.Local_Output_Path + "/cells-sample.xlsx");   
            }
            catch (Exception ex)
            {
                Assert.Fail(ex.Message);
            }
        }

        [TestMethod()]
        public void Cells_Pictures_Tests()
        {
            try
            {
                storageService.File.CopyFile(Utils.CloudStorage_Input_Folder + "/cells-sample.xlsx", Utils.CloudStorage_Output_Folder + "/cells-sample.xlsx");

                CellsPicturesResponse CellsPicturesResponse = cellsService.Pictures.ReadWorksheetPictures(name, "sheet3", Utils.CloudStorage_Output_Folder);
                CellsPicture cellsPicture = new CellsPicture();
                cellsPicture.AlternativeText = "Aspose";
                cellsPicture.BorderLineColor = new Color(5, 5, 5, 5);
                cellsPicture.BorderWeight = 2;
                cellsPicture.Height = 100;
                cellsPicture.Width = 100;
                cellsPicture.SourceFullName = Utils.CloudStorage_Input_Folder + "/signature.jpg";

                CellsPicturesResponse CellsPicturesResponse2 = cellsService.Pictures.AddANewWorksheetPicture(name, "sheet1", 1, 1, 5, 5, Utils.CloudStorage_Input_Folder + "/signature.jpg", cellsPicture, Utils.CloudStorage_Output_Folder);
                cellsService.Pictures.GetWorksheetPictureByNumber(name, "sheet3", 0, CellsPictureFormat.Png, Utils.Local_Output_Path + "cells-picture-out.png", Utils.CloudStorage_Output_Folder);
                
                CellsPicture cellsPicture2 = new CellsPicture();
                cellsPicture2.BorderLineColor = new Color(5, 5, 5, 5);
                cellsPicture2.BorderWeight = 2;
                cellsPicture2.OriginalHeight = 100;
                cellsPicture2.OriginalWidth = 5;
                cellsPicture2.ImageFormat = "Png";
                cellsPicture2.SourceFullName = Utils.CloudStorage_Input_Folder + "/signature.jpg";
                cellsPicture2.Name = "Aspose";
                cellsPicture2.MsoDrawingType = string.Empty;
                cellsPicture2.AutoShapeType = string.Empty;
                cellsPicture2.Placement = string.Empty;
                cellsPicture2.UpperLeftRow = 100;
                cellsPicture2.Top = 100;
                cellsPicture2.UpperLeftColumn = 100;
                cellsPicture2.Left = 100;
                cellsPicture2.LowerRightRow = 100;
                cellsPicture2.Bottom = 100;
                cellsPicture2.LowerRightColumn = 100;
                cellsPicture2.Right = 100;
                cellsPicture2.Width = 100;
                cellsPicture2.Height = 100;
                cellsPicture2.X = 100;
                cellsPicture2.Y = 100;
                cellsPicture2.RotationAngle = 190;
                cellsPicture2.HtmlText = string.Empty;
                cellsPicture2.Text = string.Empty;
                cellsPicture2.AlternativeText = string.Empty;
                cellsPicture2.TextHorizontalAlignment = string.Empty;
                cellsPicture2.TextHorizontalOverflow = string.Empty;
                cellsPicture2.TextOrientationType = string.Empty;
                cellsPicture2.TextVerticalAlignment = string.Empty;
                cellsPicture2.TextVerticalOverflow = string.Empty;
                cellsPicture2.IsGroup = false;
                cellsPicture2.IsHidden = false;
                cellsPicture2.IsLockAspectRatio = false;
                cellsPicture2.IsLocked = false;
                cellsPicture2.IsPrintable = false;
                cellsPicture2.IsTextWrapped = false;
                cellsPicture2.IsWordArt = false;
                cellsPicture2.LinkedCell = string.Empty;
                cellsPicture2.ZOrderPosition = 0;

                //cellsService.Pictures.UpdateWorksheetPictureByIndex(name, "sheet3", 0, Utils.CloudStorage_Output_Folder, cellsPicture2);

                cellsService.Pictures.DeleteAPictureObjectInWorksheet(name, "sheet3", 0, Utils.CloudStorage_Output_Folder);
                cellsService.Pictures.DeleteAllPicturesInWorksheet(name, "sheet3", Utils.CloudStorage_Output_Folder);

                storageService.File.DownloadFile(Utils.CloudStorage_Output_Folder + "/cells-sample.xlsx", Utils.Local_Output_Path + "/cells-sample.xlsx");
            }
            catch (Exception ex)
            {
                Assert.Fail(ex.Message);
            }
        }

        [TestMethod()]
        public void Cells_Properties_Tests()
        {
            try
            {
                storageService.File.CopyFile(Utils.CloudStorage_Input_Folder + "/cells-sample.xlsx", Utils.CloudStorage_Output_Folder + "/cells-sample.xlsx");

                DocumentPropertiesResponse documentPropertiesResponse = cellsService.Properties.ReadDocumentProperties(name, Utils.CloudStorage_Output_Folder);
                DocumentPropertyResponse documentPropertyResponse = cellsService.Properties.ReadDocumentPropertyByName(name, "Author", Utils.CloudStorage_Output_Folder);

                DocumentProperty property = new DocumentProperty();
                property.Name = "CustomProperty";
                property.Value = "Aspose";
                property.BuiltIn = false;

                DocumentPropertyResponse documentPropertyResponse2 = cellsService.Properties.SetCreateDocumentProperty(name, "Author", Utils.CloudStorage_Output_Folder, property);
                cellsService.Properties.DeleteDocumentProperty(name, "Author", Utils.CloudStorage_Output_Folder);
                cellsService.Properties.DeleteAllCustomDocumentPropertiesAndCleanBuiltInOnes(name, Utils.CloudStorage_Output_Folder);

                //storageService.File.DownloadFile(Utils.CloudStorage_Output_Folder + "/cells-sample.xlsx", Utils.Local_Output_Path + "/cells-sample.xlsx");
            }
            catch (Exception ex)
            {
                Assert.Fail(ex.Message);
            }
        }

        [TestMethod()]
        public void Cells_SaveAs_Tests()
        {
            try
            {
                //CellsSaveOptions - Please check http://www.aspose.com/docs/display/cellscloud/saveAs+%28Controller+resource%29 and supply xml/json as string
                //CellsSaveResultResponse CellsSaveResultResponse = cellsService.SaveAs.ConvertDocumentAndSaveResultToStorage(name, Utils.CloudStorage_Output_Folder + "/output.jpg", true, true, "saveOptionsXMLorJson", Utils.CloudStorage_Output_Folder);
            }
            catch (Exception ex)
            {
                Assert.Fail(ex.Message);
            }
        }

        [TestMethod()]
        public void Cells_Workbook_Tests()
        {
            try
            {
                storageService.File.CopyFile(Utils.CloudStorage_Input_Folder + "/cells-sample.xlsx", Utils.CloudStorage_Output_Folder + "/cells-sample.xlsx");

                WorkbookResponse WorkbookResponse = cellsService.Workbook.ReadWorkbookInfo(name, string.Empty, true, Utils.CloudStorage_Output_Folder);
                cellsService.Workbook.ExportWorkbook(name, WorkbookExportFormat.Pdf, string.Empty, false, Utils.CloudStorage_Output_Folder, Utils.Local_Output_Path + "cells-workbook-pdf.pdf");
                cellsService.Workbook.ExportWorkbook(name, WorkbookExportFormat.Pdf, string.Empty, false, Utils.CloudStorage_Output_Folder, Utils.CloudStorage_Output_Folder + "/cells-workbook-pdf.pdf");

                //To test
                //WorkbookResponse WorkbookResponse2 = cellsService.Workbook.CreateNewWorkbookUsingDeferentMethods(name, "templateFile", "dataFile", Utils.CloudStorage_Output_Folder);

                cellsService.Workbook.ConvertWorkbookToSomeFormat(WorkbookExportFormat.Pdf, string.Empty, Utils.CloudStorage_Output_Folder + "/cells-workbook-pdf2.pdf", Utils.Local_Input_Path + name);

                WorkbookEncryptionRequest workbookEncryptionRequest = new WorkbookEncryptionRequest(WorkbookEncryptionType.XOR, 128, "Aspose");
                cellsService.Workbook.EncriptDocument(name, Utils.CloudStorage_Output_Folder, workbookEncryptionRequest);
                cellsService.Workbook.DecryptDocument(name, Utils.CloudStorage_Output_Folder, workbookEncryptionRequest);

                WorkbookStyleResponse WorkbookStyleResponse = cellsService.Workbook.ReadWorkbookDefaultStyleInfo(name, Utils.CloudStorage_Output_Folder);
                WorkbookTextItemsResponse WorkbookTextItemsResponse = cellsService.Workbook.ReadWorkbooksTextItems(name, Utils.CloudStorage_Output_Folder);
                WorkbookNamesResponse WorkbookNamesResponse = cellsService.Workbook.ReadWorkbooksNames(name, Utils.CloudStorage_Output_Folder);
                WorkbookNameResponse WorkbookNameResponse = cellsService.Workbook.ReadWorkbooksName(name, "TestName", Utils.CloudStorage_Output_Folder);

                ProtectDocumentRequest protectDocumentRequest = new ProtectDocumentRequest();
                protectDocumentRequest.Password = "Aspose";

                cellsService.Workbook.ProtectDocumentFromChanges(name, Utils.CloudStorage_Output_Folder, protectDocumentRequest);
                cellsService.Workbook.UnprotectDocumentFromChanges(name, Utils.CloudStorage_Output_Folder, protectDocumentRequest);

                storageService.File.CopyFile(Utils.CloudStorage_Input_Folder + "/cells-sample.xlsx", Utils.CloudStorage_Output_Folder + "/cells-sample2.xlsx");
                WorkbookResponse WorkbookResponse4 = cellsService.Workbook.MergeWorkbooks(name, Utils.CloudStorage_Output_Folder + "/cells-sample2.xlsx", Utils.CloudStorage_Output_Folder);

                CellsTextItemsResponse CellsTextItemsResponse = cellsService.Workbook.SearchText(name, "Student1", Utils.CloudStorage_Output_Folder);
                WorkbookReplaceTextResponse WorkbookReplaceTextResponse = cellsService.Workbook.ReplaceText(name, "Student", "Employee", Utils.CloudStorage_Output_Folder);

                //To test
                //WorkbookResponse WorkbookResponse5 = cellsService.Workbook.SmartMarkerProcessingResult(name, "xmlFile", Utils.CloudStorage_Output_Folder, "outPath");

                WorkbookSplitResponse WorkbookSplitResponse = cellsService.Workbook.SplitWorkbook(name, CellsSplitFormat.Gif, 1, 2, 0, 0, Utils.CloudStorage_Output_Folder);

                //To test
                //cellsService.Workbook.ImportDataToWorkbook(name, Utils.CloudStorage_Output_Folder, WorkbookImportOption);

                cellsService.Workbook.CalculateAllFormulasInWorkbook(name, Utils.CloudStorage_Output_Folder);

                CellsAutoFitterOptionsRequest autoFitterOptions = new CellsAutoFitterOptionsRequest();
                autoFitterOptions.AutoFitMergedCells = true;
                autoFitterOptions.IgnoreHidden = true;
                autoFitterOptions.OnlyAuto = false;

                cellsService.Workbook.AutofitWorkbookRows(name, 1, 50, false, autoFitterOptions, Utils.CloudStorage_Output_Folder);

                //storageService.File.DownloadFile(Utils.CloudStorage_Output_Folder + "/cells-sample.xlsx", Utils.Local_Output_Path + "/cells-sample.xlsx");
            }
            catch (Exception ex)
            {
                Assert.Fail(ex.Message);
            }
        }

        [TestMethod()]
        public void Cells_Worksheets_Tests()
        {
            try
            {
                storageService.File.CopyFile(Utils.CloudStorage_Input_Folder + "/cells-sample.xlsx", Utils.CloudStorage_Output_Folder + "/cells-sample.xlsx");

                WorksheetsResponse WorksheetsResponse = cellsService.Worksheets.ReadWorksheetsInfo(name, Utils.CloudStorage_Output_Folder);
                WorksheetResponse ReadWorksheetInfo = cellsService.Worksheets.ReadWorksheetInfo(name, "sheet2", Utils.CloudStorage_Output_Folder);
                cellsService.Worksheets.ExportWorksheet(name, "sheet1", WorksheetExportFormat.Gif, 0, 0, Utils.CloudStorage_Output_Folder, Utils.Local_Output_Path + "cells_worksheet_gif.gif");

                cellsService.Worksheets.AddNewWorksheet(name, "sheetZ", Utils.CloudStorage_Output_Folder);
                cellsService.Worksheets.DeleteWorksheet(name, "sheetZ", Utils.CloudStorage_Output_Folder);

                ReadWorksheetInfo.Worksheet.IsSelected = true;
                WorksheetResponse WorksheetResponse = cellsService.Worksheets.UpdateWorksheetProperty(name, "sheet2", ReadWorksheetInfo.Worksheet, Utils.CloudStorage_Output_Folder);

                WorksheetResponse WorksheetResponse2 = cellsService.Worksheets.ChangeWorksheetVisibility(name, "sheet1", false, Utils.CloudStorage_Output_Folder);
                WorksheetResponse WorksheetResponse3 = cellsService.Worksheets.ChangeWorksheetVisibility(name, "sheet1", true, Utils.CloudStorage_Output_Folder);

                CellsMoveWorksheetRequest cellsMoveWorksheetRequest = new CellsMoveWorksheetRequest();
                cellsMoveWorksheetRequest.DestinationWorksheet = "sheet3";
                cellsMoveWorksheetRequest.Position = 3;

                cellsService.Worksheets.MoveWorksheet(name, "sheet1", cellsMoveWorksheetRequest, Utils.CloudStorage_Output_Folder);

                CellsProtectParameter cellsProtectParameter = new CellsProtectParameter(CellsProtectionType.All);
                cellsProtectParameter.Password = "Aspose";
                cellsService.Worksheets.ProtectWorksheet(name, "sheet1", cellsProtectParameter, Utils.CloudStorage_Output_Folder);
                cellsService.Worksheets.UnprotectWorksheet(name, "sheet1", cellsProtectParameter, Utils.CloudStorage_Output_Folder);

                CellsTextItemsResponse CellsTextItemsResponse = cellsService.Worksheets.GetWorksheetTextItems(name, "sheet1", Utils.CloudStorage_Output_Folder);

                CellsCommentRequest cellsCommentRequest = new CellsCommentRequest();
                cellsCommentRequest.Note = "Aspose.Cells for .NET";
                cellsCommentRequest.IsVisible = true;
                cellsCommentRequest.CellName = "A1";
                cellsCommentRequest.Width = 100;
                cellsCommentRequest.Height = 50;

                cellsService.Worksheets.AddWorksheetsCellComment(name, "sheet1", "A1", cellsCommentRequest, Utils.CloudStorage_Output_Folder);

                CellsCommentsResponse CellsCommentsResponse = cellsService.Worksheets.GetWorksheetComments(name, "sheet1", Utils.CloudStorage_Output_Folder);
                CellsCommentResponse CellsCommentResponse = cellsService.Worksheets.GetWorksheetCommentByCellName(name, "sheet1", "A1", Utils.CloudStorage_Output_Folder);

                cellsCommentRequest.Author = "Aspose";
                cellsService.Worksheets.UpdateWorksheetsCellComment(name, "sheet1", "A1", cellsCommentRequest, Utils.CloudStorage_Output_Folder);
                cellsService.Worksheets.DeleteWorksheetsCellComment(name, "sheet1", "A1", Utils.CloudStorage_Output_Folder);

                MergedCellsResponse MergedCellsResponse = cellsService.Worksheets.GetWorksheetMergedCells(name, "sheet1", Utils.CloudStorage_Output_Folder);
                MergedCellResponse MergedCellResponse = cellsService.Worksheets.GetWorksheetMergedCellByItsIndex(name, "sheet1", 0, Utils.CloudStorage_Output_Folder);

                CellsForumulaValueResponse CellsForumulaValueResponse = cellsService.Worksheets.CalculateFormulaValue(name, "sheet1", "D5*E5", Utils.CloudStorage_Output_Folder);
                CellsTextItemsResponse CellsTextItemsResponse2 = cellsService.Worksheets.SearchText(name, "sheet1", "Student1", Utils.CloudStorage_Output_Folder);
                CellsWorksheetMatchesResponse CellsWorksheetMatchesResponse = cellsService.Worksheets.ReplaceText(name, "sheet1", "Student", "Employee", Utils.CloudStorage_Output_Folder);

                CellsDataSorterRequest dataSorter = new CellsDataSorterRequest();
                dataSorter.CaseSensitive = true;
                dataSorter.HasHeaders = true;

                CellsKeyList cellsKeyList = new CellsKeyList();
                cellsKeyList.Key = 1;
                cellsKeyList.SortOrder = "DESCENDING";

                dataSorter.KeyList = new List<CellsKeyList>();
                dataSorter.KeyList.Add(cellsKeyList);

                cellsService.Worksheets.SortWorksheetRange(name, "sheet1", "B4:F19", dataSorter, Utils.CloudStorage_Output_Folder);

                CellsAutoFitterOptionsRequest autoFitterOptions = new CellsAutoFitterOptionsRequest();
                autoFitterOptions.OnlyAuto = true;
                cellsService.Worksheets.AutofitWorksheetRows(name, "sheet1", 1, 100, true, autoFitterOptions, Utils.CloudStorage_Output_Folder);
                cellsService.Worksheets.SetWorksheetBackgroundImage(name, "sheet1", Utils.Local_Input_Path + "signature.jpg", Utils.CloudStorage_Output_Folder);
                cellsService.Worksheets.DeleteWorksheetBackgroundImage(name, "sheet1", Utils.CloudStorage_Output_Folder);

                cellsService.Worksheets.SetFreezePanes(name, "sheet1", 4, 1, 1, 1, Utils.CloudStorage_Output_Folder);
                cellsService.Worksheets.UnfreezePanes(name, "sheet1", 4, 1, 1, 1, Utils.CloudStorage_Output_Folder);
                cellsService.Worksheets.CopyWorksheet(name, "sheetZ", "sheet1", Utils.CloudStorage_Output_Folder);
                cellsService.Worksheets.RenameWorksheet(name, "sheetZ", "sheetRenamed", Utils.CloudStorage_Output_Folder);

                //storageService.File.DownloadFile(Utils.CloudStorage_Output_Folder + "/cells-sample.xlsx", Utils.Local_Output_Path + "/cells-sample.xlsx");
            }
            catch (Exception ex)
            {
                Assert.Fail(ex.Message);
            }
        }

        [TestMethod()]
        public void Cells_WorksheetValidations_Tests()
        {
            try
            {
                storageService.File.CopyFile(Utils.CloudStorage_Input_Folder + "/cells-sample.xlsx", Utils.CloudStorage_Output_Folder + "/cells-sample.xlsx");

                CellsValidationsResponse CellsValidationsResponse = cellsService.WorksheetValidations.GetWorksheetValidations(name, "sheet5", Utils.CloudStorage_Output_Folder);            
                CellsValidationResponse CellsValidationResponse = cellsService.WorksheetValidations.GetWorksheetValidationByIndex(name, "sheet5", 0, Utils.CloudStorage_Output_Folder);

                //Not supported
                //CellsValidationResponse CellsValidationResponse2 = cellsService.WorksheetValidations.AddWorksheetValidationAtIndex(name, "sheet5", "A1:A2", Utils.CloudStorage_Output_Folder);
                //Not supported
                //cellsService.WorksheetValidations.UpdateWorksheetValidationByIndex(name, "sheet5", 1, Utils.CloudStorage_Output_Folder);

                cellsService.WorksheetValidations.DeleteWorksheetValidationByIndex(name, "sheet5", 0, Utils.CloudStorage_Output_Folder);

                //storageService.File.DownloadFile(Utils.CloudStorage_Output_Folder + "/cells-sample.xlsx", Utils.Local_Output_Path + "/cells-sample.xlsx");
            }
            catch (Exception ex)
            {
                Assert.Fail(ex.Message);
            }
        }

    }
}
